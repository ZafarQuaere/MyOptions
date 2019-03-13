package com.optiontown.app.view.fragment.fpo.redeem.mmp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.optiontown.R;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.redeem.SelectBookFlightFragment;

import static com.optiontown.app.utils.Utils.DEBUG;

/**
 * Created by amit on 12-11-2016.
 */
public class MMPPaymentFragment extends BaseFragment
{
    private View view;
    private WebView webView;
    private AppDialogLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_mmp_payment, container, false);
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        getUIReference();
        updateUI();
        return view;
    }

    private void getUIReference()
    {
        webView = (WebView) view.findViewById(R.id.webView);
        loader = AppDialogLoader.getLoader(getActivity());
    }

    private void updateUI()
    {
        loader.show();
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FpoMMPPayment);
        Utils.DEBUG("payment url : " + url);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sessionId = defaultSharedPreferences.getString("JSESSIONID", "");

        DEBUG("session id : " + sessionId);

        CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(webView.getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        cookieManager.setCookie(url, "JSESSIONID=" + sessionId);
        cookieSyncManager.sync();

        String cookie = cookieManager.getCookie(url);
        DEBUG("cookie ------> " + cookie);


        webView.setWebViewClient(new WebClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        DEBUG("Setting payment url : " + url);
        webView.loadUrl(url);


        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                DEBUG(">>>>>>>>>>>>>>>>>>>>> " + title);
                Utils.updateActionBarForFeatures(getActivity(), new MMPPaymentFragment().getClass().getName(), title, null);
            }
        });
    }

    private class WebClient extends WebViewClient
    {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            if(Utils.isValidSSLError(getActivity(), error))
            {
                handler.proceed();
            }
            else
            {
                handler.cancel();
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            loader.show();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Utils.DEBUG("shouldOverrideUrlLoading() >> url : " + url);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Utils.DEBUG("onPageFinished() >> url : " + url);
            super.onPageFinished(view, url);
            loader.hide();
            //https://192.168.64.10/bookFlight.do?processAction=MakePayment&isExpressCheckout=0&isBankAccount=0&isNetBanking=0&date=Tue%20Nov%2015%202016%2007:55:04%20GMT-0500%20(EST)
            if(url.contains("processAction=MakePayment"))
            {
                Utils.findAndUpdateSelectBookFlightFragment(getActivity());
            }

        }
    }



    @Override
    public void onBackEventPre() {

    }

    @Override
    public void onBackEventPost() {
        super.onBackEventPost();
        //Utils.clearBackstackTillManageMyPassFragment(getActivity());
        Utils.clearBackstackTillSelectBookFlightFragment(getActivity());
        Utils.DEBUG("onBackEventPost() called");
    }

    @Override
    public void onFocusEvent() {

    }
}

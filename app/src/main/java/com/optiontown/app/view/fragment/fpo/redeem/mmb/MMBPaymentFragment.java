package com.optiontown.app.view.fragment.fpo.redeem.mmb;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.fragment.BaseFragment;

import static com.optiontown.app.utils.Utils.DEBUG;

/**
 * Created by amit on 01-12-2016.
 */
public class MMBPaymentFragment extends BaseFragment
{
    private View view;
    private WebView webView;
    private AppDialogLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_mmb_payment, container, false);
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
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FpoMMBPayment);
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
                Utils.updateActionBarForFeatures(getActivity(), new MMBPaymentFragment().getClass().getName(), title, null);
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
        }
    }



    @Override
    public void onBackEventPre() {
        Utils.DEBUG("onBackEventPre() called >> url : " + webView.getUrl());
        if(webView.getUrl().indexOf("TGP_Term_Services.jsp") > -1 && webView.canGoBack())
        {
            webView.goBack();
        }
        /*else if(webView.getUrl().indexOf(getString(R.string.URL_FpoMMBPayment)) > -1)
        {
            ((MainActivity)getActivity()).onBackPressed();
        }
        */else
        {
            Utils.clearBackstackTillSelectBookFlightFragmentMMB(getActivity());
        }
    }

    @Override
    public void onBackEventPost() {
        Utils.DEBUG("onBackEventPost() called >> url : " + webView.getUrl());
        super.onBackEventPost();

    }

    @Override
    public void onFocusEvent() {

    }
}

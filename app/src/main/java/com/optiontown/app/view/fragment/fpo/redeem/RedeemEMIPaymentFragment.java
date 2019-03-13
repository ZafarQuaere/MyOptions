package com.optiontown.app.view.fragment.fpo.redeem;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
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
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.review.PassengerData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import static com.optiontown.app.utils.Utils.DEBUG;

/**
 * Created by amit on 06-10-2016.
 */
public class RedeemEMIPaymentFragment extends BaseFragment
{
    private View view;
    private WebView webView;
    private AppDialogLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_redeem_emi_payment_webview, container, false);
        getUIReference();
        updateUI();
        return view;
    }

    @Override
    public void onBackEventPost() {
        super.onBackEventPost();
        Utils.clearBackstackTillRedeemSearch(getActivity());
        Utils.DEBUG("onBackEventPost() called");
    }

    private void getUIReference()
    {
        webView = (WebView) view.findViewById(R.id.webView);
        loader = AppDialogLoader.getLoader(getActivity());
    }

    private void updateUI()
    {
        loader.show();
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_countinueBFPaymentMobile);
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
                Utils.updateActionBarForFeatures(getActivity(), new RedeemEMIPaymentFragment().getClass().getName(), title, null);
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
            super.onPageFinished(view, url);
            loader.hide();
        }
    }
}

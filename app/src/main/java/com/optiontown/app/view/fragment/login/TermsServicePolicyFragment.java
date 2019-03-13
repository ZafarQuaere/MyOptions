package com.optiontown.app.view.fragment.login;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.optiontown.R;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.fragment.BaseFragment;

import static com.optiontown.app.utils.Utils.DEBUG;

/**
 * Created by parasmani.sharma on 29/07/2016.
 */
public class TermsServicePolicyFragment extends BaseFragment {

    View view;
    WebView web;
    String url;
    private AppDialogLoader loader;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_terms_service_policy, container, false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "" ,null);
        url = this.getArguments().getString(getActivity().getString(R.string.key_serializable));
        getUIReference();

        return view;
    }

    private void getUIReference() {

        web = (WebView) view.findViewById(R.id.webview);
        loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        web.setWebViewClient(new myWebClient());
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setVerticalScrollBarEnabled(true);
        web.setHorizontalScrollBarEnabled(false);
        web.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                Utils.DEBUG(" title >>>>>>>>>>>>>>>>>>>>> " + title);
                Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), title ,null);
            }
        });

        web.loadUrl(getActivity().getString(R.string.URL_BASE) + url);

        Utils.DEBUG("url : " + web.getUrl());
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
        {
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
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
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

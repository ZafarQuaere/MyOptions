package com.optiontown.app.view.fragment.legproducts;

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
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.legreview.ReviewDataSend;
import com.optiontown.app.model.review.PassengerData;
import com.optiontown.app.model.review.ResultCode;
import com.optiontown.app.model.utosearchresult.BoostMypriority;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BFragment;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.HomeFragment;
import com.optiontown.app.view.fragment.payment.MakePaymentFragment;

import org.json.JSONException;
import org.json.JSONObject;

import static com.optiontown.app.utils.Utils.DEBUG;


/**
 * Created by amit on 29-06-2016.
 */
public class LegWebViewSummaryFragment extends BaseFragment
{
    private View view;
    private WebView webView;
    private AppDialogLoader loader;
    private PassengerData passengerData;
    InternationalizeData localization;
    private String express,isBankAccount,email,primaryCode,primaryMainPart,othercode,otherMainPart,CardCategoryId,totalAmountToPay,selectedKey;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_flight_pass_summary_webview, container, false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        init();

        ReviewDataSend sendData = new ReviewDataSend();
        sendData = ((ReviewDataSend) getArguments().getSerializable(getString(R.string.key_serializable)));



        email= sendData.getEmail();
        primaryCode= sendData.getPrimaryCode();
        othercode=  sendData.getOthercode();
        primaryMainPart=  sendData.getPrimaryMainPart();
        otherMainPart=  sendData.getOtherMainPart();
        CardCategoryId=sendData.getCardCategoryId();
        express=sendData.getExpress();
        isBankAccount= sendData.getIsBankAccount();
        totalAmountToPay= sendData.getTotalAmountToPay();
        selectedKey= sendData.getSelectedKey();

        if (CardCategoryId.equalsIgnoreCase("1")||CardCategoryId.equalsIgnoreCase("2")){
            AppVariables.paymentHeading = "Payment";
            Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "Payment", null);
        }
        else {
            String rulesShortName = rulesShortName(AppVariables.ProductName);
           // AppVariables.paymentHeading =rulesShortName+" Sign Up";
          //  Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), rulesShortName+" Sign Up", null);
        }

        LoadWebView(express,isBankAccount, email,primaryCode,primaryMainPart,othercode,otherMainPart, CardCategoryId,totalAmountToPay);

        return view;

    }

    @Override
    public void onBackEventPre() {
       // Utils.backSearchApiCall(getActivity(),productID, LanguageId, CountryId, MarketingAirlineId, pnr, lastName, isSearchBy, OCN, "", "", "", "", "", "", "", "", "", "");
        Utils.clearRecentBackStack(getActivity());
        Utils.clearRecentBackStack(getActivity());
        Utils.clearRecentBackStack(getActivity());
        Utils.updateActionBarForFeatures(getActivity(), new LegProductsHomeFragment().getClass().getName());
    }

    @Override
    public void onFocusEvent() {

    }

    public class WebClient extends WebViewClient
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

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loader.hide();
        }
    }

    private void init()
    {
        webView = (WebView) view.findViewById(R.id.webView);
        loader = AppDialogLoader.getLoader(getActivity());
    }



    private void LoadWebView(String express, String isBankAccount, String email, String promaryCode,
                             String primaryMainPart,String othercode,String otherMainPart, String CardCategoryId,String totalAmountToPay

                             )
    {
        loader.show();
        String url = getString(R.string.URL_BASE) +"/getSellerList.do?mobileAction=ConfirmUTO"+"&isExpressCheckout=" +
                express +
                "&isBankAccount=" +
                isBankAccount +
                "&email=" +
                email +
                "&promaryCode=" +
                promaryCode +
                "&primaryMainPart=" +
                primaryMainPart +
                "&othercode=" +
                othercode +
                "&otherMainPart=" +
                otherMainPart+
                "&CardCategoryId=" +
                CardCategoryId+
                "&totalpriceToPay=" +
                totalAmountToPay+
                "&selectedKey=" +
                selectedKey;

        SharedPreferences defaultSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());

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
                Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), title, null);
                /*if(title.equals("Summary")){
                    Utils.updateActionBarForFeatures(getActivity(), localization.getLABLFPOCartHeadingLabel());
                    return;
                }else if(title.equals("Make Payment")){
                    Utils.updateActionBarForFeatures(getActivity(),localization.getLABLMakePaymentHeadingLabel());
                    return;
                }else if(title.equals("Confirm")){
                    Utils.updateActionBarForFeatures(getActivity(), localization.getLABLGetTGPDirectConfirmButtonLabel());
                    return;
                }*/
            }
        });

    }

    private String rulesShortName(String productName) {

        if (productName.equalsIgnoreCase("Empty Seat ")) {
            return "ESo";
        } else if (productName.equalsIgnoreCase("Upgrade")) {
            return "UTo";
        } else if (productName.equalsIgnoreCase("Preferred Seat")) {
            return "PSo";
        } else if (productName.equalsIgnoreCase("EXtra Baggage")) {
            return "XBo";
        } else if (productName.equalsIgnoreCase("Lounge Access")) {
            return "LAo";
        } else if (productName.equalsIgnoreCase("Flexible Rewards")) {
            return "FRo";
        }
        return "";
    }



}

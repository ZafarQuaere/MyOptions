package com.optiontown.app.view.fragment.fpo.review;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.SystemClock;
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
import com.optiontown.app.model.review.PassengerData;
import com.optiontown.app.model.review.ResultCode;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.payment.MakePaymentFragment;

import org.json.JSONException;
import org.json.JSONObject;

import static com.optiontown.app.utils.Utils.DEBUG;

/**
 * Created by amit on 29-06-2016.
 */
public class FlightPassSummaryFragment extends BaseFragment
{
    private View view;
    private OTTextView txtTravelZone;
    private OTTextView txtCabin;
    private OTTextView txtNumberOfFlights;
    private OTTextView txtAdvanceBooking;
    private OTTextView txtTravelFlexibility;
    private OTTextView txtUsers;
    private OTTextView txtFareTaxesFees;
    private OTTextView txtPricePerFlight;
    private OTTextView txtNumberOfFlightsMultiply;
    private OTTextView txtTotalPassPriceInDetails;
    private OTTextView txtPass;
    private OTTextView txtFlights;
    private OTTextView txtValidity;
    private OTTextView txtValidityMonth;
    private OTTextView txtCurrency;
    private OTTextView txtPrice;
    private OTTextView txtInstantPayment;
    private OTTextView txtTotalPrice;
    private OTTextView txtProceedToPaymentForInstantPayment;
    private OTTextView txtFlexiblePayment;
    private OTTextView txtLowDownPaymentLabel;
    private OTTextView txtDownPayment;
    private OTTextView txtNumberOfInstallments;
    private OTTextView txtAmountEachInstallment;
    private OTTextView txtTotalPassPrice;
    private OTTextView txtProceedToPaymentForFlexiblePayment;
    private OTTextView txtViewDetails   ;
    private LinearLayout lytInstantPayment;
    private LinearLayout lytFlexiblePayment;
    private LinearLayout lytViewDetails;
    private OTTextView txtTravelPeriod;
    private OTTextView txtTravelZoneLabel;
    private OTTextView txtCabinLabel;
    private OTTextView txtNumberOfFlightsLabel;
    private OTTextView txtTravelPeriodLabel;
    private OTTextView txtAdvanceBookingLabel;
    private OTTextView txtTravelFlexibilityLabel;
    private OTTextView txtUsersLabel;
    private OTTextView txtFareTaxesFeesLabel;
    private OTTextView txtPricePerFlightLabel;
    private OTTextView txtNumberOfFlightsMultiplyLabel;
    private OTTextView txtTotalPassPriceInDetailsLabel;
    private OTTextView txtPassLabel;
    private OTTextView txtFlightsLabel;
    private OTTextView txtValidityLabel;
    private OTTextView txtPriceLabel;
    private OTTextView txtChoosePaymentPlanLabel;
    private OTTextView txtInfoOnePayment;
    private OTTextView txtInfoCharge;
    private WebView webView;
    private AppDialogLoader loader;
    private PassengerData passengerData;
    InternationalizeData localization;


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

    private void getUIReference()
    {
        webView = (WebView) view.findViewById(R.id.webView);
        loader = AppDialogLoader.getLoader(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_flight_pass_summary_webview, container, false);
       // Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        passengerData = ((PassengerData)getArguments().getSerializable(getString(R.string.key_serializable)));
        DEBUG("complete json received : " + ParseManager.getInstance().toJSON(passengerData));

        try {
            // sessionData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(parent)), SessionIdData.class);

             localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        getUIReference();

        callAddPassUserFromFlyer(passengerData);


        return view;

    }

    private void updateUI()
    {
        loader.show();
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FPO_PAYMENT_PAGE);

        if(passengerData.getIata_Display() == 1)
        {
            url = url + "&iata_Tour_Code=" + passengerData.getIata_Tour_Code() + "&iata_registration=" + passengerData.getIata_registration();
        }

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
                if(title.equals("Summary")){
                    Utils.updateActionBarForFeatures(getActivity(), localization.getLABLFPOCartHeadingLabel());
                    return;
                }else if(title.equals("Make Payment")){
                    Utils.updateActionBarForFeatures(getActivity(),localization.getLABLMakePaymentHeadingLabel());
                    return;
                }else if(title.equals("Confirm")){
                    Utils.updateActionBarForFeatures(getActivity(), localization.getLABLGetTGPDirectConfirmButtonLabel());
                    return;
                }
            }
        });

    }


    private void callAddPassUserFromFlyer(PassengerData passengerData)
    {
        String tag_json_obj = "json_obj_req";

        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_ADD_PASS_USER_FROM_FLYER);

        JSONObject requestObject = null;

        try {
            requestObject = new  JSONObject(ParseManager.getInstance().toJSON(passengerData));
        } catch (JSONException e) {
            requestObject = new JSONObject();
        }


        loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            return;
                        }
                        DEBUG("onResponse() called : " + response.toString());
                        ResultCode resultCode = ParseManager.getInstance().fromJSON(response, ResultCode.class);
                        if(resultCode.getResult().equals("Success"))
                        {
                            updateUI();
                        }
                        else
                        {
                            Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                        }

                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void ui()
    {
        txtTravelZoneLabel = (OTTextView) view.findViewById(R.id.txtTravelZoneLabel);
        txtCabinLabel = (OTTextView) view.findViewById(R.id.txtCabinLabel);
        txtNumberOfFlightsLabel = (OTTextView) view.findViewById(R.id.txtNumberOfFlightsLabel);
        txtTravelPeriodLabel = (OTTextView) view.findViewById(R.id.txtTravelPeriodLabel);
        txtAdvanceBookingLabel = (OTTextView) view.findViewById(R.id.txtAdvanceBookingLabel);
        txtTravelFlexibilityLabel = (OTTextView) view.findViewById(R.id.txtTravelFlexibilityLabel);
        txtUsersLabel = (OTTextView) view.findViewById(R.id.txtUsersLabel);
        txtFareTaxesFeesLabel = (OTTextView) view.findViewById(R.id.txtFareTaxesFeesLabel);
        txtPricePerFlightLabel = (OTTextView) view.findViewById(R.id.txtPricePerFlightLabel);
        txtNumberOfFlightsMultiplyLabel = (OTTextView) view.findViewById(R.id.txtNumberOfFlightsMultiplyLabel);
        txtTotalPassPriceInDetailsLabel = (OTTextView) view.findViewById(R.id.txtTotalPassPriceInDetailsLabel);

        txtTravelZone = (OTTextView) view.findViewById(R.id.txtTravelZone);
        txtCabin = (OTTextView) view.findViewById(R.id.txtCabin);
        txtNumberOfFlights = (OTTextView) view.findViewById(R.id.txtNumberOfFlights);
        txtTravelPeriod = (OTTextView) view.findViewById(R.id.txtTravelPeriod);
        txtAdvanceBooking = (OTTextView) view.findViewById(R.id.txtAdvanceBooking);
        txtTravelFlexibility = (OTTextView) view.findViewById(R.id.txtTravelFlexibility);
        txtUsers = (OTTextView) view.findViewById(R.id.txtUsers);
        txtFareTaxesFees = (OTTextView) view.findViewById(R.id.txtFareTaxesFees);
        txtPricePerFlight = (OTTextView) view.findViewById(R.id.txtPricePerFlight);
        txtNumberOfFlightsMultiply = (OTTextView) view.findViewById(R.id.txtNumberOfFlightsMultiply);
        txtTotalPassPriceInDetails = (OTTextView) view.findViewById(R.id.txtTotalPassPriceInDetails);


        txtPassLabel = (OTTextView) view.findViewById(R.id.txtPassLabel);
        txtFlightsLabel = (OTTextView) view.findViewById(R.id.txtFlightsLabel);
        txtValidityLabel = (OTTextView) view.findViewById(R.id.txtValidityLabel);
        txtPriceLabel = (OTTextView) view.findViewById(R.id.txtPriceLabel);
        txtChoosePaymentPlanLabel = (OTTextView) view.findViewById(R.id.txtChoosePaymentPlanLabel);
        txtInfoOnePayment = (OTTextView) view.findViewById(R.id.txtInfoOnePayment);
        txtInfoCharge = (OTTextView) view.findViewById(R.id.txtInfoCharge);


        txtPass = (OTTextView) view.findViewById(R.id.txtPass);
        txtFlights = (OTTextView) view.findViewById(R.id.txtFlights);
        txtValidity = (OTTextView) view.findViewById(R.id.txtValidity);
        txtValidityMonth = (OTTextView) view.findViewById(R.id.txtValidityMonth);
        txtCurrency = (OTTextView) view.findViewById(R.id.txtCurrency);
        txtPrice = (OTTextView) view.findViewById(R.id.txtPrice);
        txtInstantPayment = (OTTextView) view.findViewById(R.id.txtInstantPayment);
        txtInstantPayment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lytInstantPayment.setVisibility(View.VISIBLE);
                lytFlexiblePayment.setVisibility(View.GONE);
            }
        });
        lytInstantPayment = (LinearLayout) view.findViewById(R.id.lytInstantPayment);
        txtTotalPrice = (OTTextView) view.findViewById(R.id.txtTotalPrice);
        txtViewDetails = (OTTextView) view.findViewById(R.id.txtViewDetails);
        txtViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytViewDetails.setVisibility(lytViewDetails.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        lytViewDetails = (LinearLayout) view.findViewById(R.id.lytViewDetails);
        txtProceedToPaymentForInstantPayment = (OTTextView) view.findViewById(R.id.txtProceedToPaymentForInstantPayment);
        txtProceedToPaymentForInstantPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to payment page
                Utils.moveToFragment(getActivity(), new MakePaymentFragment(), null, 0);
            }
        });
        txtFlexiblePayment = (OTTextView) view.findViewById(R.id.txtFlexiblePayment);
        txtFlexiblePayment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lytInstantPayment.setVisibility(View.GONE);
                lytFlexiblePayment.setVisibility(View.VISIBLE);
            }
        });
        lytFlexiblePayment = (LinearLayout) view.findViewById(R.id.lytFlexiblePayment);
        txtLowDownPaymentLabel = (OTTextView) view.findViewById(R.id.txtLowDownPaymentLabel);
        txtDownPayment = (OTTextView) view.findViewById(R.id.txtDownPayment);
        txtNumberOfInstallments = (OTTextView) view.findViewById(R.id.txtNumberOfInstallments);
        txtAmountEachInstallment = (OTTextView) view.findViewById(R.id.txtAmountEachInstallment);
        txtTotalPassPrice = (OTTextView) view.findViewById(R.id.txtTotalPassPrice);
        txtProceedToPaymentForFlexiblePayment = (OTTextView) view.findViewById(R.id.txtProceedToPaymentForFlexiblePayment);
        txtProceedToPaymentForFlexiblePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to payment page
                Utils.moveToFragment(getActivity(), new MakePaymentFragment(), null, 0);
            }
        });
    }
}

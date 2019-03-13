package com.optiontown.app.view.fragment.fpo.redeem.mmp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonArray;
import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.mmp.CalculatePriceData;
import com.optiontown.app.model.redeem.mmp.MMPUserSelectedData;
import com.optiontown.app.model.redeem.mmp.PassSelectedParameterData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BFragment;

import org.json.JSONObject;

/**
 * Created by amit on 11-11-2016.
 */
public class MMPCalculateFeeFragment extends BFragment
{
    private OTTextView txtPassFareDifferenceLabel;
    private OTTextView txtPassFareDifference;
    private OTTextView txtChangeFeeLabel;
    private OTTextView txtChangeFee;
    private OTTextView txtTotalAmountToPayLabel;
    private OTTextView txtTotalAmountToPay;
    private View view;
    private MMPUserSelectedData mMPUserSelectedData;
    private CalculatePriceData calculatePriceData;
    private OTTextView txtProceedToPayment;
    private LinearLayout lytMain;
    private OTTextView txtSelectedPass;
    private ImageView imgEdit;
    private InternationalizeData localization;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Utils.DEBUG("onCreateView called");
        view = inflater.inflate(R.layout.fragment_mmp_calculate_fee, container, false);

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }

        mMPUserSelectedData = (MMPUserSelectedData) getArguments().getSerializable(getString(R.string.key_serializable));

        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        getUIReference();
        callpassManagmentNewPriceAPI();
        return view;
    }

    private void getUIReference()
    {
        lytMain = (LinearLayout) view.findViewById(R.id.lytMain);
        lytMain.setVisibility(View.GONE);
        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);
        imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.clearBackstackTillMMPSelectPassFragment(getActivity());
            }
        });
        txtPassFareDifferenceLabel = (OTTextView) view.findViewById(R.id.txtPassFareDifferenceLabel);
        txtPassFareDifference = (OTTextView) view.findViewById(R.id.txtPassFareDifference);
        txtChangeFeeLabel = (OTTextView) view.findViewById(R.id.txtChangeFeeLabel);
        txtChangeFee = (OTTextView) view.findViewById(R.id.txtChangeFee);
        txtTotalAmountToPayLabel = (OTTextView) view.findViewById(R.id.txtTotalAmountToPayLabel);
        txtTotalAmountToPay = (OTTextView) view.findViewById(R.id.txtTotalAmountToPay);
        txtProceedToPayment = (OTTextView) view.findViewById(R.id.txtProceedToPayment);
        txtProceedToPayment.setText(localization.getLABLProceedToPaymentLabel());
        txtProceedToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new MMPPaymentFragment(), null, 0);
            }
        });
    }

    private void updateUI()
    {
        lytMain.setVisibility(View.VISIBLE);
        txtSelectedPass.setText(mMPUserSelectedData.getSelectedPassLabel().replace("#", ":"));
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), calculatePriceData.getPassSelectedParameterData().getMangemypassrefreshdata().getLABLFPOChangeMyFlightPassLabel(), null);
        txtPassFareDifferenceLabel.setText(calculatePriceData.getFareDifferenceLabel());
        txtPassFareDifference.setText(calculatePriceData.getTransCurrencyCode().trim() + " " + calculatePriceData.getPriceFareDiff());
        txtChangeFeeLabel.setText(calculatePriceData.getChangeFeeLabel());
        txtChangeFee.setText(calculatePriceData.getTransCurrencyCode().trim() + " " + calculatePriceData.getChangeFeeAmount());
        txtTotalAmountToPayLabel.setText(calculatePriceData.getAmountLabel());
        txtTotalAmountToPay.setText(calculatePriceData.getTransCurrencyCode().trim() + " " + calculatePriceData.getTotalAmount());
    }
    private void callpassManagmentNewPriceAPI()
    {
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_passManagmentNewPrice);

        String strJSON = ParseManager.getInstance().toJSON(mMPUserSelectedData);

        JSONObject requestObject = null;
        try {
            requestObject = new JSONObject(strJSON);
            requestObject.remove("selectedPassLabel");
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Utils.DEBUG("Response : " + response.toString());
                        calculatePriceData = ParseManager.getInstance().fromJSON(response, CalculatePriceData.class);
                        updateUI();
                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }




    @Override
    public void onBackEventPre() {

    }

    @Override
    public void onBackEventPost() {

    }

    @Override
    public void onFocusEvent() {

    }
}

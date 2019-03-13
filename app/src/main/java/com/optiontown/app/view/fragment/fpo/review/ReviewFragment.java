

package com.optiontown.app.view.fragment.fpo.review;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.AddUserData;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.login.LoginDataUpdateProfile;
import com.optiontown.app.model.login.SelectedUserData;
import com.optiontown.app.model.redeem.UsersDetail;
import com.optiontown.app.model.review.FPOMobileReviewPassData;
import com.optiontown.app.model.review.FeatureDetail;
import com.optiontown.app.model.review.FfpNumberData;
import com.optiontown.app.model.review.InnerOuterIndexData;
import com.optiontown.app.model.review.OdListWithDate;
import com.optiontown.app.model.review.Odlist;
import com.optiontown.app.model.review.PassCMMIndexData;
import com.optiontown.app.model.review.PassengerData;
import com.optiontown.app.model.review.RestrictValue;
import com.optiontown.app.model.review.UserDetails;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.SelectFlightPassFragment;
import com.optiontown.app.view.fragment.YesNoDialog;
import com.optiontown.app.view.fragment.login.CreateAccountFromReviewFragment;
import com.optiontown.app.view.fragment.login.LoginFromReviewFragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by amit on 23-06-2016.
 */
public class ReviewFragment extends BaseFragment {
    public static Activity activity;
    public static int airlineId = 0;
    ArrayList<UserDetails> listUserDetails = new ArrayList<>();
    InternationalizeData localization;
    private View view;
    private OTTextView txtPass;
    private OTTextView txtCabin;
    private OTTextView txtNumberFlight;
    private OTTextView txtRoundTripInfo;
    private OTTextView txtTravelPeriod;
    private OTTextView txtTravelPeriodRange;
    private OTTextView txtUsers;
    private OTTextView txtAdvanceBooking;
    private OTTextView txtTravelFlexibility;
    private OTTextView txtPricePerFlight;
    private OTTextView txtNumberFlightMultiply;
    private OTTextView txtAdministrativeFees;
    private OTTextView txtTotalPassPrice;
    private OTTextView txtRegularTotalPrice;
    private OTTextView txtSavePrice;
    private OTTextView txtSavePercent;
    private OTTextView txtPayNow;
    private OTTextView txtCustomize;
    private OTTextView txtBuy;
    private OTTextView txtInformation1;
    private OTTextView txtInformation2;
    private OTTextView txtFlexiblePaymentPlan;
    private OTTextView txtChooseInstallment;
    private OTTextView txtKnowMore;
    private LinearLayout lytAdvanceBooking;
    private LinearLayout lytTravelFlexibility;
    private LinearLayout lytAdministrativeFees;
    private LinearLayout lytSaving;
    private LinearLayout lytFlexiblePaymentPlan;
    private OTTextView txtLabelPass;
    private OTTextView txtLabelCabin;
    private OTTextView txtLabelNumberFlight;
    private OTTextView txtLabelTravelPeriod;
    private OTTextView txtLabelUsers;
    private OTTextView txtLabelAdvanceBooking;
    private OTTextView txtLabelTravelFlexibility;
    private OTTextView txtLabelPricePerFlight;
    private OTTextView txtLabelNumberFlightMultiply;
    private OTTextView txtLabelAdministrativeFees;
    private OTTextView txtLabelTotalPassPrice;
    private OTTextView txtLabelRegularTotalPrice;
    private LinearLayout lytPassenger;
    private LinearLayout lytCustomizeBuy;
    private CardView lytAddUser;
    private OTTextView txtProceedToPayment;
    private OTTextView txtCancel;
    private OTTextView txtAddPassenger;
    private LinearLayout lytErrorPassenger;
    private LinearLayout lytErrorPassengerMessage;
    private OTEditText edtFirstName;
    private OTEditText edtMiddleName;
    private OTEditText edtLastName;
    private OTEditText edtEmail;
    private OTEditText edtMobile;
    private OTEditText edtClubNo;
    private OTTextView txtPrefix;
    private OTTextView txtCountryCode;
    private OTTextView txtDay;
    private OTTextView txtMonth;
    private OTTextView txtYear;
    private LinearLayout lytPrefix;
    private LinearLayout lytCountryCode;
    private LinearLayout lytDay;
    private LinearLayout lytMonth;
    private LinearLayout lytYear;
    private LinearLayout lytAddPassenger;
    private int MIN_USER_ALLOWED = 0;
    private int MAX_USER_ALLOWED = 0;
    private int count_added_user_ut = 0;
    private OTTextView txtSelectPassengerReference;
    private int idSelectedPassengerTag = 0;
    private LinearLayout lytErrorCommon;
    private LinearLayout lytErrorCommonMessage;
    private OTTextView txtEligiblePassenger;
    private OTTextView txtAddPassengerInformation;
    private CountryExtData countryExtData;
    private FPOMobileReviewPassData fPOMobileReviewPassData;
    private LinearLayout lytTop;
    private InnerOuterIndexData innerOuterIndexData;
    private PassCMMIndexData passCMMIndexData;
    private LoginData loginData;
    private OTTextView txtNewPassengerDetailsLabel;
    private LoginDataUpdateProfile loginDataUpdateProfile;
    private boolean isFromCustomize = false;
    private int passIndex;
    private int cmmIndex;
    private OTTextView txtClubNoLabel;
    private OTTextView txtEnterClubNoLabel;
    private SelectedUserData selectedUserData;
    private ScrollView svParent;

    private LinearLayout lytCorporateClient;
    private RelativeLayout rlytCorpClientDetail;
    private OTTextView txtCorporateClientShow;
    private OTTextView txtCorpClientDetail;
    private OTTextView txtCorpClientHide;
    private OTTextView txtTourCodeLabel;
    private OTTextView txtRegistrationNumberLabel;
    private OTEditText edtTourCode;
    private OTEditText edtRegistrationNumber;
    private LinearLayout lay_promocode_main;
    private OTTextView txtApplyPromo;
    private LinearLayout lay_promocode_first;
    private LinearLayout lay_promocode_status;
    private LinearLayout layRemovePromoCode;
    private OTTextView txtPromoTermsOfService;
    private LinearLayout lytPromoCode;
    private LinearLayout lay_promo_terms_second;
    private OTTextView txtPromoCodeHeading;
    private OTTextView txtPromoCodeLabel;
    private HtmlTextView txtPromoErrorMsg;
    private OTEditText edtPromoCode;
    private OTTextView txtPromotionalCode;
    private OTTextView txtPromoTermsAndConditionStatus;
    private OTTextView txtPromoCodeLabelTwo;
    private OTTextView txtRemove;
    private OTTextView txtDiscountLabel;
    private OTTextView txtDiscount;
    private OTTextView txtTotalPassLabel;
    private OTTextView txtTotalPass;
    private LinearLayout lytPromotionalCode;
    private OTTextView txtLabelPromotionalCode;
    private OTTextView txtPromotionCode;
    private OTTextView txtTnC;
    private OTTextView txtDiscountPercent;
    private ArrayList<String> listError;
    private String errorMsg;
    private LinearLayout lytCabin;
    private boolean isEditing = false;
    private int posEdit = 0;
    private Communicator communicator;
    private final int DEFAULT_SHOW_FM_VALUES = 5;
    private OTTextView txtPassDetails;
    private OTTextView txtPassDescription;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_review, container, false);
        activity = getActivity();

        /*to satisfy webview looping*/
        Utils.webview_actionabar_switch = true;

        Serializable s = (getArguments().getSerializable(getString(R.string.key_serializable)));
        if (s instanceof PassCMMIndexData) {
            passCMMIndexData = ((PassCMMIndexData) s);
        } else if (s instanceof InnerOuterIndexData) {
            innerOuterIndexData = ((InnerOuterIndexData) s);
        }


        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        try {
            countryExtData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getCountryExtData(getActivity())), CountryExtData.class);
        } catch (Exception e) {
            Utils.ERROR("Utils >> Error while parsing json : " + e.toString());
        }

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        getUIReference();

        callFpoMobileReviewPass(innerOuterIndexData, passCMMIndexData, true, false, false);

        updateSelectFlightPassFragment();


        return view;

    }

    private void updateSelectFlightPassFragment() {
        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFragmentName((new SelectFlightPassFragment()).getClass().getName());
        data.setFromReview(true);
        communicator.onResponse(data);

    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("ReviewFragment >> onAttach(Activity) called");
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }


    private void callApplyRemovePromotionalCodeAPI(String promoCode) {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST);
        final JSONObject requestObject = new JSONObject();
        if (promoCode == null) {
            url = url + getString(R.string.URL_RemovePromoCode);
        } else {
            url = url + getString(R.string.URL_PromoApply);
            try {
                requestObject.put("PromoCode", promoCode);
            } catch (JSONException e) {

            }
        }


        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        fPOMobileReviewPassData = ParseManager.getInstance().fromJSON(response, FPOMobileReviewPassData.class);
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

    private void callFpoMobileReviewPass(InnerOuterIndexData innerOuterIndexData, PassCMMIndexData passCMMIndexData, boolean isCallingFirst, final boolean flagAddUserAPI, boolean forceChangeURL) {
        String tag_json_obj = "json_obj_req";

        String url = null;

        if (forceChangeURL) {
            if (innerOuterIndexData != null) {
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_BUY_PASS_FROM_HOME_REVIEW)
                        + "&passIndex=" + innerOuterIndexData.getOuterIndex() + "&CMMIndex=" + innerOuterIndexData.getInnerIndex()
                        + "&ExpCheck=" + innerOuterIndexData.getExpCheck()
                        + (isCallingFirst ? "&isSelect=0" : "&isSelect=1");
            }
        } else {
            if (passCMMIndexData != null) {
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FPO_MOBILE_REVIEW_PASS)
                        + "&passIndex=" + passCMMIndexData.getPassIndex() + "&CMMIndex=" + passCMMIndexData.getCmmIndex()
                        + "&ExpCheck=" + passCMMIndexData.getExpCheck()
                        + (isCallingFirst ? "&isSelect=1" : "&isSelect=0");
            }
            if (innerOuterIndexData != null) {
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_BUY_PASS_FROM_HOME_REVIEW)
                        + "&outerIndex=" + innerOuterIndexData.getOuterIndex() + "&innerIndex=" + innerOuterIndexData.getInnerIndex()
                        + "&ExpCheck=" + innerOuterIndexData.getExpCheck()
                        + (isCallingFirst ? "&isSelect=0" : "&isSelect=1");
            }
        }


        final JSONObject requestObject = new JSONObject();

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        fPOMobileReviewPassData = ParseManager.getInstance().fromJSON(response, FPOMobileReviewPassData.class);
                        airlineId = fPOMobileReviewPassData.getAirlineId();
                        updateUI();

                        if (flagAddUserAPI) {
                            callAddUserApi(loginData);
                        }
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

    private void updateUI() {

        try
        {
            lytPassenger.setVisibility(fPOMobileReviewPassData.getIsRequiredAddPax() == 1 ? View.VISIBLE : View.GONE);

            lytTop.setVisibility(View.VISIBLE);
          //  txtLabelPass.setText(fPOMobileReviewPassData.getShortDescriptionLabel());
            txtLabelPass.setText(fPOMobileReviewPassData.getTravelZoneLabel());
            txtPass.setText(fPOMobileReviewPassData.getShortDescription());
            txtPassDescription.setText(fPOMobileReviewPassData.getTravelZone());

            //
            if(fPOMobileReviewPassData.getZonefeatureData() != null)
            {
                if(fPOMobileReviewPassData.getZonefeatureData().getDepArrvZoneArray().isEmpty())
                {
                    txtPassDetails.setVisibility(View.GONE);
                }
                else
                {
                    txtPassDetails.setVisibility(View.VISIBLE);
                }
            }
            else
            {
                txtPassDetails.setVisibility(View.GONE);
            }
            //txtPassDetails.setText("");//not coming from server
            txtPassDetails.setPaintFlags(txtPassDetails.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            //


            lytCabin.setVisibility(fPOMobileReviewPassData.getCabinLabel() == null ? View.GONE : View.VISIBLE);
            txtLabelCabin.setText(fPOMobileReviewPassData.getCabinLabel());
            txtCabin.setText(fPOMobileReviewPassData.getCabinDetail());

            txtLabelNumberFlight.setText(fPOMobileReviewPassData.getNumberOfFlightsLabel());
            txtNumberFlight.setText(fPOMobileReviewPassData.getNumberOfFlights());

            txtRoundTripInfo.setText(fPOMobileReviewPassData.getRoundBookingLbl());

            txtLabelTravelPeriod.setText(fPOMobileReviewPassData.getTravelPeriodLabel());
            txtTravelPeriod.setText(fPOMobileReviewPassData.getTravelPeriod());
            txtTravelPeriodRange.setText(fPOMobileReviewPassData.getTravelPeriodValidity());

            txtLabelUsers.setText(fPOMobileReviewPassData.getEligiblePassengersLabel());
            txtUsers.setText(fPOMobileReviewPassData.getEligiblePassengers()
                    + (fPOMobileReviewPassData.getEligiblePassengersMinLabel().equals("") ? "" : "\n") + fPOMobileReviewPassData.getEligiblePassengersMinLabel()
                    + (fPOMobileReviewPassData.getAddPurchaseMaxHrsLabel().equals("") ? "" : "\n") + fPOMobileReviewPassData.getAddPurchaseMaxHrsLabel());


            LinearLayout lytFmMatrixRow = (LinearLayout) view.findViewById(R.id.lytFMMatrix);
            addFmMatrixFeatures(lytFmMatrixRow);


            lytAdvanceBooking.setVisibility(fPOMobileReviewPassData.getAdvanceBookingLabel() == null || fPOMobileReviewPassData.getAdvanceBookingLabel().equals("") ? View.GONE : View.VISIBLE);
            txtLabelAdvanceBooking.setText(fPOMobileReviewPassData.getAdvanceBookingLabel());
            txtAdvanceBooking.setText(fPOMobileReviewPassData.getAdvanceBooking());

            if (fPOMobileReviewPassData.getIsPassFlexibilityDisplay() == 0) {
                lytTravelFlexibility.setVisibility(View.GONE);
            } else {
                txtLabelTravelFlexibility.setText(fPOMobileReviewPassData.getDepartFlexibilityLabel());
                txtTravelFlexibility.setText(fPOMobileReviewPassData.getDepartFlexibility());
            }

            txtLabelPricePerFlight.setText(fPOMobileReviewPassData.getPricePerFlightLabel());
            txtPricePerFlight.setText(fPOMobileReviewPassData.getPricePerFlight());

            txtLabelNumberFlightMultiply.setText(fPOMobileReviewPassData.getFlightCountLabel());
            txtNumberFlightMultiply.setText(fPOMobileReviewPassData.getFlightCountCredit());

            if (fPOMobileReviewPassData.getAdminFee().equals("")) {
                lytAdministrativeFees.setVisibility(View.GONE);
            } else {
                txtLabelAdministrativeFees.setText(fPOMobileReviewPassData.getAdminFeeLabel());
                txtAdministrativeFees.setText(fPOMobileReviewPassData.getAdminFee());
            }

            txtLabelTotalPassPrice.setText(fPOMobileReviewPassData.getTotalPriceLabel() + "*");
            txtTotalPassPrice.setText(fPOMobileReviewPassData.getCurrencySymbolAndAmount());

            if (fPOMobileReviewPassData.getIsPassSavingDisplay() == 0) {
                lytSaving.setVisibility(View.GONE);
            } else {
                txtLabelRegularTotalPrice.setText(fPOMobileReviewPassData.getRegularTotalPriceLabel() + "+");
                txtRegularTotalPrice.setText(fPOMobileReviewPassData.getRegularTotalPrice());

                txtRegularTotalPrice.setText(fPOMobileReviewPassData.getRegularTotalPrice());
                txtRegularTotalPrice.setPaintFlags(txtRegularTotalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txtSavePrice.setText(fPOMobileReviewPassData.getSaveLabel() + " " + fPOMobileReviewPassData.getCurrencySymbolSavingAmount());
                txtSavePercent.setText(fPOMobileReviewPassData.getPassSavingPercentLabel().trim());
            }


            txtInformation1.setText(fPOMobileReviewPassData.getPassTotalPriceHelpLabel());
            if(Integer.parseInt(fPOMobileReviewPassData.getIsFlexibleEMIDisplay()) == 1)
            {
                txtInformation2.setText("+" + fPOMobileReviewPassData.getPassBasedHistPriceLabel());
            }
            else
            {
                txtInformation2.setVisibility(View.GONE);
            }



            lytFlexiblePaymentPlan.setVisibility(fPOMobileReviewPassData.getIsFlexibleEMIDisplay().equals("0") ? View.GONE : View.VISIBLE);
            txtFlexiblePaymentPlan.setText(fPOMobileReviewPassData.getPassFlexiblePaymentLabel());
            txtChooseInstallment.setText(fPOMobileReviewPassData.getPassFlexiblePlanDescLabel());
            txtPayNow.setText(fPOMobileReviewPassData.getPassFlexiblePlanAmount());
            txtKnowMore.setText(fPOMobileReviewPassData.getLABLPassFlexiblePlanKnowMoreLabel());


            MIN_USER_ALLOWED = fPOMobileReviewPassData.getEligible_Passengers_Min();
            MAX_USER_ALLOWED = fPOMobileReviewPassData.getEligible_Passengers_Max_Count();
            txtEligiblePassenger.setText(fPOMobileReviewPassData.getAddUserHelpLabel());

            txtAddPassengerInformation.setText(fPOMobileReviewPassData.getUserAddPurchaseMinLabel());

            //
            /*if(fPOMobileReviewPassData.getAirlineId() == 975)
            {
                fPOMobileReviewPassData.setFFPNumberMandatory(1);
                fPOMobileReviewPassData.setIsDisplayFFPNumber(1);
                fPOMobileReviewPassData.setFFpnumberSortDesc("short description");
                fPOMobileReviewPassData.setFFpnumberHelpMessage("help message");
            }*/


            if (fPOMobileReviewPassData.getFFPNumberMandatory() == 1) {
                //Utils.showToast(getActivity(), "FFP mandatory");
            }
            txtClubNoLabel.setText(fPOMobileReviewPassData.getFFpnumberSortDesc() + (fPOMobileReviewPassData.getFFPNumberMandatory() == 1 ? " * " : ""));
            txtEnterClubNoLabel.setText(fPOMobileReviewPassData.getFFpnumberHelpMessage());

            txtClubNoLabel.setVisibility(fPOMobileReviewPassData.getIsDisplayFFPNumber() == 1 ? View.VISIBLE : View.GONE);
            txtEnterClubNoLabel.setVisibility(fPOMobileReviewPassData.getIsDisplayFFPNumber() == 1 ? View.VISIBLE : View.GONE);
            edtClubNo.setVisibility(fPOMobileReviewPassData.getIsDisplayFFPNumber() == 1 ? View.VISIBLE : View.GONE);

            if (fPOMobileReviewPassData.getFFPNumberMandatory() == 1 && fPOMobileReviewPassData.getAirlineId() == 975/*ba*/) {
                edtClubNo.setInputType(InputType.TYPE_CLASS_PHONE);
            } else {
                edtClubNo.setInputType(InputType.TYPE_CLASS_TEXT);
            }

            if (fPOMobileReviewPassData.getIata_Display() == 1) {
                txtCorporateClientShow.setText(fPOMobileReviewPassData.getIata_Link_Label());
                txtCorpClientDetail.setText(fPOMobileReviewPassData.getIata_Link_Label());
                txtCorpClientHide.setText(fPOMobileReviewPassData.getIata_Hide_Label());
                txtTourCodeLabel.setText(fPOMobileReviewPassData.getIata_Tour_Code_Label());
                txtRegistrationNumberLabel.setText(fPOMobileReviewPassData.getIata_registration());


                lytCorporateClient.setVisibility(View.VISIBLE);

            }
            lytPromotionalCode.setVisibility(View.GONE);

            if (fPOMobileReviewPassData.isPromo_DisplayPromoCode()) {
                if (fPOMobileReviewPassData.getPromo_Code() == null) {
                    txtPromoCodeHeading.setText(fPOMobileReviewPassData.getPromo_Have_Code_Label());
                    txtPromoCodeLabel.setText(fPOMobileReviewPassData.getPromo_Code_Display_Label());
                    txtApplyPromo.setText(fPOMobileReviewPassData.getPromo_Apply_Label());

                    if (fPOMobileReviewPassData.getPromo_Code_Error_Flag() == 1 || fPOMobileReviewPassData.getPromo_Code_Error_Flag() == 2) {

                        Utils.setBackground(edtPromoCode, getResources().getDrawable(R.drawable.round_corner_red));

                        txtPromoErrorMsg.setHtml((fPOMobileReviewPassData.getPromo_Code_Error_Message()));
                        txtPromoErrorMsg.setVisibility(View.VISIBLE);

                        txtPromoTermsOfService.setText(fPOMobileReviewPassData.getPromo_Code_TNC_Label());
                        txtPromoTermsOfService.setVisibility(View.VISIBLE);
                    } else {
                        txtPromoErrorMsg.setVisibility(View.GONE);
                        txtPromoTermsOfService.setVisibility(View.GONE);
                    }

                    lay_promocode_first.setVisibility(View.VISIBLE);
                    lay_promocode_status.setVisibility(View.GONE);
                } else {
                    //successfully applied
                    txtPromoCodeLabelTwo.setText(fPOMobileReviewPassData.getPromo_Code_Display_Label());

                    txtPromotionalCode.setText("" + fPOMobileReviewPassData.getPromo_Code().toString());
                    txtDiscountPercent.setText(fPOMobileReviewPassData.getPromo_DiscountPerCent().toString());

                    txtRemove.setText(fPOMobileReviewPassData.getPromo_Remove_Label());

                    txtDiscountLabel.setText(fPOMobileReviewPassData.getPromo_Discount_Text_Label());
                    txtDiscount.setText(fPOMobileReviewPassData.getPromo_Currency_Symbol() + " " + fPOMobileReviewPassData.getPromo_Discount_Value());
                    txtTotalPassLabel.setText(fPOMobileReviewPassData.getPromo_Total_Price_Per_Pass_Label());
                    txtTotalPass.setText(fPOMobileReviewPassData.getPromo_Currency_Symbol() + " " + fPOMobileReviewPassData.getPromo_Total_Price_Per_Pass());

                    txtPromoTermsAndConditionStatus.setText(fPOMobileReviewPassData.getPromo_Code_TNC_Label());

                    lytPromotionalCode.setVisibility(View.VISIBLE);
                    txtLabelPromotionalCode.setText(fPOMobileReviewPassData.getPromo_Code_Display_Label());
                    txtPromotionCode.setText(fPOMobileReviewPassData.getPromo_Code());
                    txtTnC.setText(fPOMobileReviewPassData.getPromo_Code_TNC_Label());


                    lay_promocode_first.setVisibility(View.GONE);
                    lay_promocode_status.setVisibility(View.VISIBLE);
                }

            } else {
                lay_promocode_first.setVisibility(View.GONE);
                lay_promocode_status.setVisibility(View.GONE);
            }


            localizeUI();
        }
        catch (Exception e)
        {
            Utils.ERROR("ReviewFragment >> updateUI() >> error : " + e.toString());
            e.printStackTrace();
        }

    }

    private void addFmMatrixFeatures(LinearLayout lytFmMatrixRow) {

        if (fPOMobileReviewPassData != null && lytFmMatrixRow != null) {

            lytFmMatrixRow.removeAllViews();
            ArrayList<RestrictValue> restrictValue = fPOMobileReviewPassData.getRestrictValues();

            if (restrictValue != null && (!restrictValue.isEmpty())) {
                for (int index = 0; index < restrictValue.size(); index++) {
                    LinearLayout linearLayout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.layout_restric_value_main, null, false);
                    OTTextView txtFeatureTypeName = (OTTextView) linearLayout.findViewById(R.id.txtFeatureTypeName);
                    txtFeatureTypeName.setText(restrictValue.get(index).getFeatureTypeName());

                    ArrayList<FeatureDetail> featureDetails = restrictValue.get(index).getFeatureDetails();
                    LinearLayout lytRstricValuesFeatureDetail = (LinearLayout) linearLayout.findViewById(R.id.lytRstricValuesFeatureDetail);
                    addRestrictValueFeatureDetails(lytRstricValuesFeatureDetail,featureDetails);


                   // linearLayout.setPadding(0, 10, 0, 10);
                    lytFmMatrixRow.addView(linearLayout);
                }
            }
        }

    }

    private void addRestrictValueFeatureDetails(LinearLayout lytRstricValuesFeatureDetail, ArrayList<FeatureDetail> featureDetails) {

        if (featureDetails != null && (!featureDetails.isEmpty())) {
            for (int i = 0; i < featureDetails.size(); i++) {

                LinearLayout linearLayout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.layout_restric_value_feature_details, null, false);
                OTTextView txtFeatureTitle = (OTTextView) linearLayout.findViewById(R.id.txtFeatureTitle);
                OTTextView txtRoutes = (OTTextView) linearLayout.findViewById(R.id.txtRoutes);
                OTTextView txtTravelPeriod = (OTTextView) linearLayout.findViewById(R.id.txtTravelPeriod);

                LinearLayout lytODListWithDate = (LinearLayout) linearLayout.findViewById(R.id.lytODListWithDate);

                txtFeatureTitle.setText(featureDetails.get(i).getFvShrtTitle());
                txtRoutes.setText(featureDetails.get(i).getRoutesLavel());
                txtTravelPeriod.setText(featureDetails.get(i).getTravelPeriod());

                ArrayList<OdListWithDate> odListWithDates = featureDetails.get(i).getOdListWithDates();
                String viewAll = featureDetails.get(i).getViewAllLabel();
                String viewLess = featureDetails.get(i).getViewLessLabel();
                addODListWithDate(lytODListWithDate,odListWithDates,viewAll,viewLess);

                //linearLayout.setPadding(0, 10, 0, 10);
                lytRstricValuesFeatureDetail.addView(linearLayout);
            }

        }

    }

    private void addODListWithDate(LinearLayout lytODListWithDate, ArrayList<OdListWithDate> odListWithDates, final String viewAll, final String viewLess) {

        if (odListWithDates != null && (!odListWithDates.isEmpty())){

            final LinearLayout lytODListWithDateChild = (LinearLayout) lytODListWithDate.findViewById(R.id.lytODListWithDateChild);
            boolean isChildLayoutEmpty = true;

            for (int j = 0; j < odListWithDates.size(); j++) {

                ArrayList<Odlist> odlists = odListWithDates.get(j).getOdlist();

                try {
                    if (odlists != null) {
                        for (int i = 0; i < odlists.size(); i++) {

                            LinearLayout  layout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.layout_restrict_value_odlist, null, false);
                            OTTextView txtRestrictRoute = (OTTextView) layout.findViewById(R.id.txtRestrictRoute);
                            OTTextView txtRestrictDateRange = (OTTextView) layout.findViewById(R.id.txtRestrictDateRange);
                            if(i == 0)
                            {
                                txtRestrictDateRange.setText(odListWithDates.get(j).getRestrictDateRange());
                            }
                            else
                            {
                                txtRestrictDateRange.setText("");
                            }

                            layout.findViewById(R.id.viewBorder).setVisibility(i == odlists.size()-1 ? View.VISIBLE:View.GONE);

                            txtRestrictRoute.setText(odlists.get(i).getOdPaIR());


                            if(j < DEFAULT_SHOW_FM_VALUES)
                            {
                                lytODListWithDate.addView(layout);
                            }
                            else
                            {
                                if(isChildLayoutEmpty)
                                {
                                    TextView txtViewAll = new TextView(lytODListWithDate.getContext());
                                    txtViewAll.setText(viewAll);

                                    txtViewAll.setTextColor(Color.BLUE);
                                    txtViewAll.setTextSize(12);
                                    txtViewAll.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lytODListWithDateChild.setVisibility(lytODListWithDateChild.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                                            ((TextView) v).setText(lytODListWithDateChild.getVisibility() == View.GONE ? viewAll : viewLess);

                                            svParent.fullScroll(View.SCROLL_INDICATOR_TOP);
                                        }
                                    });
                                    lytODListWithDate.addView(txtViewAll);
                                    isChildLayoutEmpty = false;
                                }

                                lytODListWithDateChild.addView(layout);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }
    }

    private void localizeUI() {
        txtCustomize.setText(localization.getLABLProcessStatusBarFPOCustomizeLabel());
        txtBuy.setText(localization.getLABLBuyFlightPassHomeLabel());

        ((OTTextView) view.findViewById(R.id.txtAddPassengerLabel)).setText(localization.getLABLAddPassengersLabel() != null ? localization.getLABLAddPassengersLabel() : "Add Passenger");
        ((OTTextView) view.findViewById(R.id.txtEligiblePassenger)).setText(localization.getLABLPassAddUserHelpLabel() + " : " + MAX_USER_ALLOWED + " Passenger(s)");
        ((OTTextView) view.findViewById(R.id.txtPersonalInformationLabel)).setText(localization.getLABLPersonalInformationLabel());

        ((OTTextView) view.findViewById(R.id.txtPrefixLabel)).setText(localization.getLABLPrefixLabel() + "*");
        ((OTTextView) view.findViewById(R.id.txtFirstNameLabel)).setText(localization.getLABLFirstNameLabel() + "*");
        ((OTTextView) view.findViewById(R.id.txtMiddleNameLabel)).setText(localization.getLABLMiddleNameLabel());
        ((OTTextView) view.findViewById(R.id.txtLastNameLabel)).setText(localization.getLABLLastNameLabel() + "*");
        ((OTTextView) view.findViewById(R.id.txtEmailLabel)).setText(localization.getLABLEmailLabel() + "*");
        ((OTTextView) view.findViewById(R.id.txtMobileLabel)).setText(localization.getLABLCellMobileLabel() + "*");
        ((OTTextView) view.findViewById(R.id.txtDateOfBirthLabel)).setText(localization.getLABLDateOfBirthLabel() + "*");
        txtPrefix.setText(localization.getLABLPrefixLabel() + "*");
        edtFirstName.setHint(localization.getLABLFirstNameLabel() + "*");
        edtMiddleName.setHint(localization.getLABLMiddleNameLabel());
        edtLastName.setHint(localization.getLABLLastNameLabel() + "*");
        edtEmail.setHint(localization.getLABLEmailLabel() + "*");


        txtAddPassenger.setText(localization.getLABLAddPassengersLabel() != null ? localization.getLABLAddPassengersLabel() : "Add Passenger");
        txtCancel.setText(localization.getLABLCancelLabel());


    }


    private void getUIReference() {

        lytCabin = (LinearLayout) view.findViewById(R.id.lytCabin);

        svParent = (ScrollView) view.findViewById(R.id.svParent);
        final ScrollView svPrefix = (ScrollView) view.findViewById(R.id.svPrefix);
        final ScrollView svCountryCode = (ScrollView) view.findViewById(R.id.svCountryCode);
        final ScrollView svDay = (ScrollView) view.findViewById(R.id.svDay);
        final ScrollView svMonth = (ScrollView) view.findViewById(R.id.svMonth);
        final ScrollView svYear = (ScrollView) view.findViewById(R.id.svYear);

        view.findViewById(R.id.svParent).setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                svPrefix.getParent().requestDisallowInterceptTouchEvent(false);
                svCountryCode.getParent().requestDisallowInterceptTouchEvent(false);
                svDay.getParent().requestDisallowInterceptTouchEvent(false);
                svMonth.getParent().requestDisallowInterceptTouchEvent(false);
                svYear.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        Utils.setInterceptTouchEvent(new View[]{svPrefix, svCountryCode, svDay, svMonth, svYear});

        view.findViewById(R.id.lytTop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svPrefix.setVisibility(View.GONE);
                svCountryCode.setVisibility(View.GONE);
                svDay.setVisibility(View.GONE);
                svMonth.setVisibility(View.GONE);
                svYear.setVisibility(View.GONE);
            }
        });


        txtNewPassengerDetailsLabel = (OTTextView) view.findViewById(R.id.txtNewPassengerDetailsLabel);
        lytTop = (LinearLayout) view.findViewById(R.id.lytTop);
        lytTop.setVisibility(View.GONE);


        lytCorporateClient = (LinearLayout) view.findViewById(R.id.lytCorporateClient);
        rlytCorpClientDetail = (RelativeLayout) view.findViewById(R.id.rlytCorpClientDetail);
        txtCorporateClientShow = (OTTextView) view.findViewById(R.id.txtCorporateClientShow);
        txtCorpClientDetail = (OTTextView) view.findViewById(R.id.txtCorpClientDetail);
        txtCorpClientHide = (OTTextView) view.findViewById(R.id.txtCorpClientHide);
        txtTourCodeLabel = (OTTextView) view.findViewById(R.id.txtTourCodeLabel);
        txtRegistrationNumberLabel = (OTTextView) view.findViewById(R.id.txtRegistrationNumberLabel);
        edtTourCode = (OTEditText) view.findViewById(R.id.edtTourCode);
        edtRegistrationNumber = (OTEditText) view.findViewById(R.id.edtRegistrationNumber);

        txtCorporateClientShow.setPaintFlags(txtCorporateClientShow.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtCorpClientHide.setPaintFlags(txtCorpClientHide.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtCorporateClientShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlytCorpClientDetail.setVisibility(View.VISIBLE);
                txtCorporateClientShow.setVisibility(View.GONE);
            }
        });
        txtCorpClientHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlytCorpClientDetail.setVisibility(View.GONE);
                txtCorporateClientShow.setVisibility(View.VISIBLE);
            }
        });

        txtLabelPass = (OTTextView) view.findViewById(R.id.txtLabelPass);
        txtPass = (OTTextView) view.findViewById(R.id.txtPass);
        txtPassDescription = (OTTextView) view.findViewById(R.id.txtPassDescription);
        txtPassDetails = (OTTextView) view.findViewById(R.id.txtPassDetails);
        txtPassDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.moveToFragment(getActivity(), new FPoZoneDetailFragment(), fPOMobileReviewPassData.getZonefeatureData(), 0);
            }
        });
        txtLabelCabin = (OTTextView) view.findViewById(R.id.txtLabelCabin);
        txtCabin = (OTTextView) view.findViewById(R.id.txtCabin);
        txtLabelNumberFlight = (OTTextView) view.findViewById(R.id.txtLabelNumberFlight);
        txtNumberFlight = (OTTextView) view.findViewById(R.id.txtNumberFlight);
        txtRoundTripInfo = (OTTextView) view.findViewById(R.id.txtRoundTripInfo);
        txtLabelTravelPeriod = (OTTextView) view.findViewById(R.id.txtLabelTravelPeriod);
        txtTravelPeriod = (OTTextView) view.findViewById(R.id.txtTravelPeriod);
        txtTravelPeriodRange = (OTTextView) view.findViewById(R.id.txtTravelPeriodRange);
        txtLabelUsers = (OTTextView) view.findViewById(R.id.txtLabelUsers);
        txtUsers = (OTTextView) view.findViewById(R.id.txtUsers);
        lytAdvanceBooking = (LinearLayout) view.findViewById(R.id.lytAdvanceBooking);
        txtLabelAdvanceBooking = (OTTextView) view.findViewById(R.id.txtLabelAdvanceBooking);
        txtAdvanceBooking = (OTTextView) view.findViewById(R.id.txtAdvanceBooking);
        lytTravelFlexibility = (LinearLayout) view.findViewById(R.id.lytTravelFlexibility);
        txtLabelTravelFlexibility = (OTTextView) view.findViewById(R.id.txtLabelTravelFlexibility);
        txtTravelFlexibility = (OTTextView) view.findViewById(R.id.txtTravelFlexibility);
        txtLabelPricePerFlight = (OTTextView) view.findViewById(R.id.txtLabelPricePerFlight);
        txtPricePerFlight = (OTTextView) view.findViewById(R.id.txtPricePerFlight);
        txtLabelNumberFlightMultiply = (OTTextView) view.findViewById(R.id.txtLabelNumberFlightMultiply);
        txtNumberFlightMultiply = (OTTextView) view.findViewById(R.id.txtNumberFlightMultiply);
        lytAdministrativeFees = (LinearLayout) view.findViewById(R.id.lytAdministrativeFees);
        txtLabelAdministrativeFees = (OTTextView) view.findViewById(R.id.txtLabelAdministrativeFees);
        txtAdministrativeFees = (OTTextView) view.findViewById(R.id.txtAdministrativeFees);
        txtLabelTotalPassPrice = (OTTextView) view.findViewById(R.id.txtLabelTotalPassPrice);
        txtTotalPassPrice = (OTTextView) view.findViewById(R.id.txtTotalPassPrice);
        lytSaving = (LinearLayout) view.findViewById(R.id.lytSaving);
        txtLabelRegularTotalPrice = (OTTextView) view.findViewById(R.id.txtLabelRegularTotalPrice);
        txtRegularTotalPrice = (OTTextView) view.findViewById(R.id.txtRegularTotalPrice);
        txtSavePrice = (OTTextView) view.findViewById(R.id.txtSavePrice);
        txtSavePercent = (OTTextView) view.findViewById(R.id.txtSavePercent);
        txtInformation1 = (OTTextView) view.findViewById(R.id.txtInformation1);
        txtInformation2 = (OTTextView) view.findViewById(R.id.txtInformation2);
        lytFlexiblePaymentPlan = (LinearLayout) view.findViewById(R.id.lytFlexiblePaymentPlan);
        txtFlexiblePaymentPlan = (OTTextView) view.findViewById(R.id.txtFlexiblePaymentPlan);
        txtChooseInstallment = (OTTextView) view.findViewById(R.id.txtChooseInstallment);
        txtPayNow = (OTTextView) view.findViewById(R.id.txtPayNow);
        txtKnowMore = (OTTextView) view.findViewById(R.id.txtKnowMore);
        txtCustomize = (OTTextView) view.findViewById(R.id.txtCustomize);
        if(Utils.getCurrentProductId(getActivity()) != getActivity().getResources().getInteger(R.integer.value_tgProductId_fpo))
        {
            txtCustomize.setVisibility(View.INVISIBLE);
        }
        txtCustomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                passCMMIndexData = new PassCMMIndexData();

                //if(innerOuterIndexData != null)
                {
                    passCMMIndexData.setCmmIndex(fPOMobileReviewPassData.getCmmIndex());
                    passCMMIndexData.setPassIndex(fPOMobileReviewPassData.getPassIndex());
                }

                Utils.moveToFragment(getActivity(), new CustomizeFragment(), passCMMIndexData, 0);
            }
        });


        txtBuy = (OTTextView) view.findViewById(R.id.txtBuy);
        txtBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isUserLoggedIn(getActivity())) {
                    try {
                        loginData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLoginData(getActivity())), LoginData.class);

                        //this loginData is not having correct information about password change, so use review data to figure out whether or not ask to change password
                        if (fPOMobileReviewPassData != null && fPOMobileReviewPassData.getPasswordValidationRequired().getPasswordRequired() == 1) {
                            loginData.setPasswordvalidationRequired(fPOMobileReviewPassData.getPasswordValidationRequired());
                            //move to CreateAccountFromReviewFragment
                            Utils.moveToFragment(getActivity(), new CreateAccountFromReviewFragment(), loginData, 1);//last param 1

                        } else if (loginData.getCustomerType() == getResources().getInteger(R.integer.value_customer_type_1)) {
                            //incomplete login data, need complete data, ask to complete details first.
                            if (fPOMobileReviewPassData != null) {
                                loginData.setFFPNumberMandatory(fPOMobileReviewPassData.getFFPNumberMandatory());
                                loginData.setFFpnumberSortDesc(fPOMobileReviewPassData.getFFpnumberSortDesc());
                                loginData.setFFpnumberErrorMessage(fPOMobileReviewPassData.getFFpnumberErrorMessage());
                                loginData.setIsDisplayFFPNumber(fPOMobileReviewPassData.getIsDisplayFFPNumber());
                                loginData.setPasswdValidationRequired(fPOMobileReviewPassData.getPasswordValidationRequired().getPasswordRequired()+"");
                                loginData.setPasswordHelpMsg(fPOMobileReviewPassData.getPasswordValidationRequired().getPasswordHelpMsg()+"");

                            }
                            Utils.moveToFragment(getActivity(), new CreateAccountFromReviewFragment(), loginData, 0);
                        } else {
                            //call review api to refresh the data, its because there may be a chance that user updated the pass by customizing it
                            if (innerOuterIndexData != null) {
                                innerOuterIndexData.setExpCheck(isFromCustomize ? 1 : 0);
                                if (isFromCustomize) {
                                    innerOuterIndexData.setOuterIndex(passIndex);
                                    innerOuterIndexData.setInnerIndex(cmmIndex);
                                    callFpoMobileReviewPass(innerOuterIndexData, passCMMIndexData, false, true, true);
                                    return;
                                }
                            }
                            if (passCMMIndexData != null) {
                                passCMMIndexData.setExpCheck(isFromCustomize ? 1 : 0);
                                if (isFromCustomize) {
                                    passCMMIndexData.setCmmIndex(cmmIndex);
                                    passCMMIndexData.setPassIndex(passIndex);
                                }
                            }
                            callFpoMobileReviewPass(innerOuterIndexData, passCMMIndexData, false, true, false);
                        }
                    } catch (Exception e) {
                        Utils.ERROR("Utils >> Error while parsing json : " + e.toString());
                    }
                } else {
                    LoginData lData = null;
                    if (fPOMobileReviewPassData != null) {
                        lData = new LoginData();
                        lData.setFFPNumberMandatory(fPOMobileReviewPassData.getFFPNumberMandatory());
                        lData.setFFpnumberSortDesc(fPOMobileReviewPassData.getFFpnumberSortDesc());
                        lData.setFFpnumberErrorMessage(fPOMobileReviewPassData.getFFpnumberErrorMessage());
                        lData.setIsDisplayFFPNumber(fPOMobileReviewPassData.getIsDisplayFFPNumber());
                        lData.setPasswordvalidationRequired(fPOMobileReviewPassData.getPasswordValidationRequired());
                    }
                    Utils.moveToFragment(getActivity(), new LoginFromReviewFragment(), lData, 0);
                }
            }
        });

        lytPrefix = (LinearLayout) view.findViewById(R.id.lytPrefix);
        txtPrefix = (OTTextView) view.findViewById(R.id.txtPrefix);
        txtPrefix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.createDropdownView(Utils.getPrefixList(getActivity()), lytPrefix, txtPrefix, new LinearLayout[]{lytCountryCode, lytDay, lytMonth, lytYear});
            }
        });

        lytCountryCode = (LinearLayout) view.findViewById(R.id.lytCountryCode);
        txtCountryCode = (OTTextView) view.findViewById(R.id.txtCountryCode);
        txtCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.createDropdownView(Utils.getCountryListExtArray(countryExtData.getData()), lytCountryCode, txtCountryCode, new LinearLayout[]{lytPrefix, lytDay, lytMonth, lytYear});
            }
        });


        lytDay = (LinearLayout) view.findViewById(R.id.lytDay);
        txtDay = (OTTextView) view.findViewById(R.id.txtDay);
        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.createDropdownView(getResources().getStringArray(R.array.array_day), lytDay, txtDay, new LinearLayout[]{lytPrefix, lytCountryCode, lytMonth, lytYear});
            }
        });

        lytMonth = (LinearLayout) view.findViewById(R.id.lytMonth);
        txtMonth = (OTTextView) view.findViewById(R.id.txtMonth);
        txtMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.createDropdownView(Utils.getLocalisedMonth(Locale.getDefault()), lytMonth, txtMonth, new LinearLayout[]{lytPrefix, lytCountryCode, lytDay, lytYear});
            }
        });

        lytYear = (LinearLayout) view.findViewById(R.id.lytYear);
        txtYear = (OTTextView) view.findViewById(R.id.txtYear);
        txtYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.createDropdownView(getResources().getStringArray(R.array.array_year), lytYear, txtYear, new LinearLayout[]{lytPrefix, lytCountryCode, lytDay, lytMonth});
            }
        });

        edtFirstName = (OTEditText) view.findViewById(R.id.edtFirstName);
        edtMiddleName = (OTEditText) view.findViewById(R.id.edtMiddleName);
        edtLastName = (OTEditText) view.findViewById(R.id.edtLastName);
        edtEmail = (OTEditText) view.findViewById(R.id.edtEmail);
        edtMobile = (OTEditText) view.findViewById(R.id.edtMobile);
        edtClubNo = (OTEditText) view.findViewById(R.id.edtClubNo);
        txtClubNoLabel = (OTTextView) view.findViewById(R.id.txtClubNoLabel);
        txtEnterClubNoLabel = (OTTextView) view.findViewById(R.id.txtEnterClubNoLabel);


        lytErrorPassenger = (LinearLayout) view.findViewById(R.id.lytErrorPassenger);
        lytErrorPassengerMessage = (LinearLayout) view.findViewById(R.id.lytErrorPassengerMessage);
        lytErrorCommon = (LinearLayout) view.findViewById(R.id.lytErrorCommon);
        lytErrorCommonMessage = (LinearLayout) view.findViewById(R.id.lytErrorCommonMessage);
        txtEligiblePassenger = (OTTextView) view.findViewById(R.id.txtEligiblePassenger);
        txtAddPassengerInformation = (OTTextView) view.findViewById(R.id.txtAddPassengerInformation);
        lytPassenger = (LinearLayout) view.findViewById(R.id.lytPassenger);
        lytCustomizeBuy = (LinearLayout) view.findViewById(R.id.lytCustomizeBuy);
        lytAddUser = (CardView) view.findViewById(R.id.lytAddUser);
        txtProceedToPayment = (OTTextView) view.findViewById(R.id.txtProceedToPayment);
        txtProceedToPayment.setText(localization.getLABLProceedToPaymentLabel());
        txtProceedToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<UserDetails> list = new ArrayList<>(listUserDetails);
                PassengerData passengerData = new PassengerData();
                passengerData.setUserDetails(list);
                Utils.DEBUG("complete json : " + ParseManager.getInstance().toJSON(passengerData));

                ArrayList<String> listError = validateAllInputsCommon();
                if (listError.size() > 0) {
                    //first clear all previos error message view
                    lytErrorCommonMessage.removeAllViews();
                    lytErrorCommon.setVisibility(View.VISIBLE);
                    txtAddPassengerInformation.setVisibility(View.GONE);

                    //show error message
                    for (int index = 0; index < listError.size(); index++) {
                        LinearLayout lytAddUesrErrorRow = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_add_user_error_row, null, false);
                        OTTextView txtErrorMessage = (OTTextView) lytAddUesrErrorRow.findViewById(R.id.txtErrorMessage);
                        txtErrorMessage.setText(listError.get(index).toString());

                        lytErrorCommonMessage.addView(lytAddUesrErrorRow);
                    }
                } else {
                    //all ok
                    lytErrorCommon.setVisibility(View.GONE);

                    passengerData.setIata_Display(fPOMobileReviewPassData.getIata_Display());
                    passengerData.setIata_Tour_Code(edtTourCode.getText().toString());
                    passengerData.setIata_registration(edtRegistrationNumber.getText().toString());

                    //move to summary page
                    Utils.moveToFragment(getActivity(), new FlightPassSummaryFragment(), passengerData, 0);

                    //set default
                    listUserDetails.clear();
                    lytPassenger.setVisibility(View.GONE);
                    lytCustomizeBuy.setVisibility(View.VISIBLE);
                    txtProceedToPayment.setVisibility(View.GONE);
                    count_added_user_ut = 0;
                    lytAddPassenger.removeAllViews();


                }

            }
        });
        lytAddPassenger = (LinearLayout) view.findViewById(R.id.lytAddPassenger);
        lytAddPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int index = 0; index < lytAddPassenger.getChildCount(); index++) {
                    LinearLayout lytUserDropdown = (LinearLayout) (lytAddPassenger.getChildAt(index)).findViewById(R.id.lytUserDropdown);
                    if (lytUserDropdown != null) {
                        //hide dropdown
                        lytUserDropdown.setVisibility(View.GONE);
                    }
                }
            }
        });


        txtCancel = (OTTextView) view.findViewById(R.id.txtCancel);
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    /*if (fPOMobileReviewPassData.getFFPNumberMandatory() == 1
                            && listUserDetails.get(idSelectedPassengerTag - 1).getFFPNumber() == null) {
                        ArrayList<String> listError = validateAllInputsPassenger();
                        if (listError.size() > 0) {
                            //first clear all previos error message view
                            lytErrorPassengerMessage.removeAllViews();
                            lytErrorPassenger.setVisibility(View.VISIBLE);

                            //show error message
                            for (int index = 0; index < listError.size(); index++) {
                                LinearLayout lytAddUesrErrorRow = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_add_user_error_row, null, false);
                                OTTextView txtErrorMessage = (OTTextView) lytAddUesrErrorRow.findViewById(R.id.txtErrorMessage);
                                txtErrorMessage.setText(listError.get(index).toString());

                                lytErrorPassengerMessage.addView(lytAddUesrErrorRow);
                            }
                        }
                    } else*/ {
                        lytAddUser.setVisibility(View.GONE);
                        lytErrorPassenger.setVisibility(View.GONE);
                        txtSelectPassengerReference.setText(localization.getLABLMasterUKLAOAvailableSelectLabel());
                        //removePassengerFromListDetailsJSON(listUserDetails.size() - 1);//remove last


                        //
                        // lytAddPassenger

                        /*OTTextView txtSelectPassenger = (OTTextView) lytAddPassenger.getChildAt(lytAddPassenger.getChildCount() - 1).findViewById(R.id.txtSelectPassenger);
                        txtSelectPassenger.setText(getString(R.string.string_select));*/

                        for (int index = 0; index < lytAddPassenger.getChildCount(); index++) {
                            View layout_add_passenger = lytAddPassenger.getChildAt(index);



                            if((Integer)layout_add_passenger.getTag() == idSelectedPassengerTag)
                            {
                                count_added_user_ut--;
                                if(lytAddPassenger.getChildCount() != 1)
                                {
                                    lytAddPassenger.removeView(layout_add_passenger);
                                    lytAddUser.setVisibility(View.GONE);
                                }

                                removePassengerFromListDetailsJSON((Integer) layout_add_passenger.getTag() - 1);

                                rearrangeUI();

                                break;
                            }


                        }



                    }
                } catch (Exception e) {

                    if (txtSelectPassengerReference.getText().toString().trim().equals(localization.getUserAddNewPass())) {
                        lytAddUser.setVisibility(View.GONE);
                        lytErrorPassenger.setVisibility(View.GONE);
                    } else {

                    }
                    Utils.DEBUG("" + txtSelectPassengerReference.getText().toString().trim() + "/" + e.toString());
                }


            }
        });

        txtAddPassenger = (OTTextView) view.findViewById(R.id.txtAddPassenger);
        txtAddPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listError = validateAllInputsPassenger();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(edtClubNo.getVisibility() == View.VISIBLE)
                        {
                            //when ffp number is required
                            if(fPOMobileReviewPassData.getFFPNumberMandatory() == 1 || (fPOMobileReviewPassData.getFFPNumberMandatory() == 0 ))
                            {
                                try {
                                    callToCheckFFPNumberValidity(edtClubNo.getText().toString().trim(), listError);
                                } catch (Exception e) {}
                            }
                        }else {
                            //ffp number not required
                            if(listError.size()>0)
                            {
                                //first clear all previos error message view
                                lytErrorPassengerMessage.removeAllViews();
                                lytErrorPassenger.setVisibility(View.VISIBLE);

                                //show error message
                                for (int index = 0; index < listError.size(); index++) {
                                    LinearLayout lytAddUesrErrorRow = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_add_user_error_row, null, false);
                                    OTTextView txtErrorMessage = (OTTextView) lytAddUesrErrorRow.findViewById(R.id.txtErrorMessage);
                                    txtErrorMessage.setText(listError.get(index).toString());

                                    lytErrorPassengerMessage.addView(lytAddUesrErrorRow);
                                }
                            }
                            else {
                                Utils.isValidEmailAddress(getActivity(), edtEmail.getText().toString());
                                try {
                                    //all ok except FFP number
                                        setUiAndJSON();
                                }catch (Exception e){}

                            }
                        }


                    }
                },200);
            }
        });


        //display promocode layout if condition true
        lytPromoCode = (LinearLayout) view.findViewById(R.id.lytPromoCode);
        lay_promocode_first = (LinearLayout) lytPromoCode.findViewById(R.id.lay_promocode_first);
        txtPromoTermsOfService = (OTTextView) view.findViewById(R.id.txtPromoTermsOfService);
        txtPromoTermsOfService.setPaintFlags(txtPromoTermsOfService.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtPromoTermsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //display popup
                Utils.showPopUpForPromoCode(getActivity(), fPOMobileReviewPassData.getPromo_Code_TNC_Label(), fPOMobileReviewPassData.getPromo_TNC_Label());

            }
        });
        txtApplyPromo = (OTTextView) lay_promocode_first.findViewById(R.id.txtApplyPromo);
        txtApplyPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callApplyRemovePromotionalCodeAPI(edtPromoCode.getText().toString());

            }
        });

        lay_promocode_status = (LinearLayout) lytPromoCode.findViewById(R.id.lay_promocode_status);
        layRemovePromoCode = (LinearLayout) lay_promocode_status.findViewById(R.id.layRemovePromoCode);
        layRemovePromoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callApplyRemovePromotionalCodeAPI(null);
            }
        });

        lay_promo_terms_second = (LinearLayout) lay_promocode_status.findViewById(R.id.lay_promo_terms_second);
        lay_promo_terms_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.showPopUpForPromoCode(getActivity(), fPOMobileReviewPassData.getPromo_Code_TNC_Label(), fPOMobileReviewPassData.getPromo_TNC_Label());

            }
        });

        txtPromoCodeLabelTwo = (OTTextView) lytPromoCode.findViewById(R.id.txtPromoCodeLabelTwo);
        txtRemove = (OTTextView) lytPromoCode.findViewById(R.id.txtRemove);
        txtDiscountLabel = (OTTextView) lytPromoCode.findViewById(R.id.txtDiscountLabel);
        txtDiscount = (OTTextView) lytPromoCode.findViewById(R.id.txtDiscount);
        txtTotalPassLabel = (OTTextView) lytPromoCode.findViewById(R.id.txtTotalPassLabel);
        txtTotalPass = (OTTextView) lytPromoCode.findViewById(R.id.txtTotalPass);
        txtPromoCodeHeading = (OTTextView) lytPromoCode.findViewById(R.id.txtPromoCodeHeading);
        txtPromoCodeLabel = (OTTextView) lytPromoCode.findViewById(R.id.txtPromoCodeLabel);
        txtPromoErrorMsg = (HtmlTextView) lytPromoCode.findViewById(R.id.txtPromoErrorMsg);
        edtPromoCode = (OTEditText) lytPromoCode.findViewById(R.id.edtPromoCode);


        txtPromotionalCode = (OTTextView) lytPromoCode.findViewById(R.id.txtPromotionalCode);
        txtDiscountPercent = (OTTextView) lytPromoCode.findViewById(R.id.txtDiscountPercent);
        txtPromoTermsAndConditionStatus = (OTTextView) lytPromoCode.findViewById(R.id.txtPromoTermsAndConditionStatus);

        lytPromotionalCode = (LinearLayout) view.findViewById(R.id.lytPromotionalCode);
        txtLabelPromotionalCode = (OTTextView) view.findViewById(R.id.txtLabelPromotionalCode);
        txtPromotionCode = (OTTextView) view.findViewById(R.id.txtPromotionCode);
        txtTnC = (OTTextView) view.findViewById(R.id.txtTnC);
        txtTnC.setPaintFlags(txtTnC.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtTnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showPopUpForPromoCode(getActivity(), fPOMobileReviewPassData.getPromo_Code_TNC_Label(), fPOMobileReviewPassData.getPromo_TNC_Label());
            }
        });


    }

    private void callToCheckFFPNumberValidity(final String ffpNumber, final ArrayList<String> listError) {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FfpValidation)+"&isSearchForm=0";


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("FFPNumber", ffpNumber);
            requestObject.put("isSignUp", "1");
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        FfpNumberData data = ParseManager.getInstance().fromJSON(response, FfpNumberData.class);


                        //all ok
                        if(data.getIsFFPValid() == 1)
                        {
                            /*appending zero , the condition updated on server 166
                             *  */

                            edtClubNo.setText(data.getUpdatedFfp().toString());
                        }
                        else
                        {
                            if(!listError.contains(fPOMobileReviewPassData.getFFpnumberErrorMessage()))
                            {
                                errorMsg = data.getMessage().toString();
                                listError.add(errorMsg);
                            }

                        }


                        if(listError.size()>0)
                        {
                            //first clear all previos error message view
                            lytErrorPassengerMessage.removeAllViews();
                            lytErrorPassenger.setVisibility(View.VISIBLE);

                            //show error message
                            for (int index = 0; index < listError.size(); index++) {
                                LinearLayout lytAddUesrErrorRow = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_add_user_error_row, null, false);
                                OTTextView txtErrorMessage = (OTTextView) lytAddUesrErrorRow.findViewById(R.id.txtErrorMessage);
                                txtErrorMessage.setText(listError.get(index).toString());

                                lytErrorPassengerMessage.addView(lytAddUesrErrorRow);
                            }
                        }
                        else
                        {
                            Utils.isValidEmailAddress(getActivity(), edtEmail.getText().toString());
                            try {
                                if(data.getIsFFPValid() == 1) {
                                    setUiAndJSON();
                                }
                            }catch (Exception e){}

                        }
                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.string_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void setUiAndJSON() {
        lytErrorCommon.setVisibility(View.GONE);
        txtSelectPassengerReference.setText(getNewlyAddedUserName());
        lytAddUser.setVisibility(View.GONE);
        lytErrorPassenger.setVisibility(View.GONE);
        rearrangeUI();
        createRefereceForJsonNewlyAddedorModifiedPassenger(idSelectedPassengerTag - 1);
    }


    private ArrayList<String> validateAllInputsCommon() {
        ArrayList<String> listError = new ArrayList<>();
        if (listUserDetails.size() < MIN_USER_ALLOWED) {
            if (fPOMobileReviewPassData.getUserAddPurchaseMinLabel().equals("")) {
                //empty from server
                listError.add("*At least " + MIN_USER_ALLOWED + " passenger must be added now to purchase the Flight Pass.");
            } else {
                listError.add(fPOMobileReviewPassData.getUserAddPurchaseMinLabel());
            }


            Utils.DEBUG("message : " + fPOMobileReviewPassData.getUserAddPurchaseMinLabel());
        }

        return listError;
    }


    /**
     * returns name of newly added user
     *
     * @return
     */
    private String getNewlyAddedUserName() {
        return txtPrefix.getText().toString() + " " + edtFirstName.getText().toString().trim() + " " + edtMiddleName.getText().toString().trim() + " " + edtLastName.getText().toString().trim();
    }


    /**
     * used to get full name of given user with prefix
     *
     * @param addUserData
     * @return
     */
    private String getUserName(AddUserData addUserData) {
        return Utils.getPrefixName(getActivity(), addUserData.getPrefixID()) + " " + addUserData.getFirstName() + " " + addUserData.getMiddleName() + " " + addUserData.getLastname();
    }

    /**
     * used to provide list of string for each error message
     *
     * @return
     */
    private ArrayList<String> validateAllInputsPassenger() {
        ArrayList<String> listError = new ArrayList<>();

        if (Utils.compareDefaultValues(txtPrefix, localization.getLABLPrefixErrorLabel())) {
            listError.add(localization.getLABLPrefixInputErrLabel());
        }

        if (Utils.compareDefaultValues(edtFirstName, "")) {
            listError.add(localization.getERRInvalidFirstNameErrorMessage());
        }

        if (Utils.compareDefaultValues(edtLastName, "")) {
            listError.add(localization.getERRInvalidLastNameErrorMessage());
        }

        if (!Utils.isValidEmail(edtEmail.getText().toString())) {
            listError.add(localization.getLABLEnterEmailAddressLabel());
        }

        if (Utils.compareDefaultValues(edtMobile, "") || Utils.compareDefaultValues(txtCountryCode, "")) {
            listError.add(localization.getERRInvalidPhoneNumberMessage());
        }

        if (Utils.compareDefaultValues(txtDay, localization.getLABLDaySmallcapLabel())
                || Utils.compareDefaultValues(txtMonth, localization.getLABLMonthValidityLabel())
                || Utils.compareDefaultValues(txtYear, localization.getLABLYearLabel())) {
            listError.add(localization.getLABLDateValidDOBErrorLabel());
        }else {

            //check if passenger 1 age > 18 && infant age > 2 ,  passenger 2 or more infant age > 2
            if(idSelectedPassengerTag == 0 || idSelectedPassengerTag == 1)//first passenger
            {
                if (!Utils.isUserAdult(txtYear.getText().toString(), getCalMonth(txtMonth.getText().toString()), txtDay.getText().toString()))
                {
                    //show error message that first passenger must be an adult
                    listError.add(localization.getLABLPassAddUserAdultFirstLabel());
                    txtYear.requestFocus();
                }
            }
            else
            {
                if (!Utils.isUserInfant(txtYear.getText().toString(), getCalMonth(txtMonth.getText().toString()), txtDay.getText().toString())) {
                    //conditional as prod returning null
                    listError.add(localization.getLABL_InfantPasngr_Label() == null ? "This passenger is an Infant. You cannot add an infant to booking using flight pass. You may contact airline to add infant to your booking." : localization.getLABL_InfantPasngr_Label());
                    txtYear.requestFocus();
                }
            }
        }

        if (fPOMobileReviewPassData.getFFPNumberMandatory() == 1 && Utils.compareDefaultValues(edtClubNo, "")) {
            listError.add(fPOMobileReviewPassData.getFFpnumberErrorMessage());
        }

        if(listError.isEmpty())
        {
            UsersDetail usersDetail = new UsersDetail();
            usersDetail.setDOBDay(txtDay.getText().toString());
            usersDetail.setDOBMonth(Utils.getEquivalentLocalisedMonth(txtMonth.getText().toString().trim(), Locale.getDefault(), Utils.getLocalForCommunication()));
            usersDetail.setDOBYear(txtYear.getText().toString());
            usersDetail.setFName(edtFirstName.getText().toString());
            usersDetail.setLName(edtLastName.getText().toString());
            usersDetail.setMName(edtMiddleName.getText().toString());
            Boolean duplicateUser = Utils.isDuplicateUser(usersDetail, listUserDetails, isEditing, posEdit);
            if(duplicateUser)
            {
                listError.add("A matching user already exists in your Pass. You may add a different user.");
            }else {
                isEditing = false;
                listError.clear();
            }
        }


        return listError;
    }

    /**
     * return index position of array-string of months
     * @param month
     * @return
     */
    private int getCalMonth(String month) {

        String ary[] = Utils.getLocalisedMonth(Locale.getDefault());/*getResources().getStringArray(R.array.array_month);*/
        for (int index = 0; index < ary.length; index++) {
            if(ary[index].equals(month))
                return index + 1;
        }

        return 1;
    }


    public void updateUIForUser(final FragmentCommunicationData message) {
        //Utils.showToast(getActivity(), "update review page.");
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        if (message.isCallBuy()) {
            //user checked out from CustomizeFragment
            isFromCustomize = true;
            cmmIndex = message.getCmmIndex();
            passIndex = message.getPassIndex();
            txtBuy.performClick();
            return;
        }


        //update login data

        loginData = message.getLoginData();
        loginDataUpdateProfile = message.getLoginDataUpdateProfile();

        lytPassenger.setVisibility(fPOMobileReviewPassData.getIsRequiredAddPax() == 1 ? View.VISIBLE : View.GONE);
        lytCustomizeBuy.setVisibility(View.GONE);
        txtProceedToPayment.setVisibility(View.VISIBLE);
        count_added_user_ut++;

        //add layout for passenger selection, for first passenger
        final View layout_add_passenger = LayoutInflater.from(getContext()).inflate(R.layout.layout_add_passenger, null, false);
        layout_add_passenger.setTag(count_added_user_ut);
        final LinearLayout lytUserDropdown = (LinearLayout) layout_add_passenger.findViewById(R.id.lytUserDropdown);
        OTTextView txtSelectFromAccountLabel = (OTTextView)lytUserDropdown.findViewById(R.id.txtSelectFromAccountLabel);
        txtSelectFromAccountLabel.setText(localization.getLABLPassCustomerMasterUserListLabel());
        OTTextView txtNewPassengerLabel = (OTTextView)lytUserDropdown.findViewById(R.id.txtNewPassengerLabel);
        txtNewPassengerLabel.setText(localization.getLABLNewPassengerLabel());
        final LinearLayout lytAllUsers = (LinearLayout) layout_add_passenger.findViewById(R.id.lytAllUsers);

        final ImageView imgEdit = (ImageView) layout_add_passenger.findViewById(R.id.imgEdit);
        final ImageView imgRemove = (ImageView) layout_add_passenger.findViewById(R.id.imgRemove);


        final OTTextView txtAddNewPassenger = (OTTextView) layout_add_passenger.findViewById(R.id.txtAddNewPassenger);

        OTTextView txtPassenger = (OTTextView) layout_add_passenger.findViewById(R.id.txtPassenger);
        txtPassenger.setText(localization.getLABLPassengerShortLabel() + " " + (count_added_user_ut) + "*");
        final OTTextView txtSelectPassenger = (OTTextView) layout_add_passenger.findViewById(R.id.txtSelectPassenger);
        txtSelectPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytUserDropdown.setVisibility(lytUserDropdown.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        final OTTextView txtAddNewPassengerLabel = (OTTextView) layout_add_passenger.findViewById(R.id.txtAddNewPassengerLabel);
        txtAddNewPassengerLabel.setText(localization.getUserAddNewPass() != null ? localization.getUserAddNewPass() : "Add New Passenger");
        txtAddNewPassengerLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isEditing = false;

                clearAllFields();
                lytUserDropdown.setVisibility(View.GONE);
                lytAddUser.setVisibility(View.VISIBLE);
                txtNewPassengerDetailsLabel.setText(localization.getUserAddNewPass());
                txtAddPassenger.setText(localization.getBookAddUserLabel());
                txtSelectPassenger.setText(txtAddNewPassengerLabel.getText().toString());
                rearrangeUI();
                imgEdit.setVisibility(View.GONE);
                imgRemove.setVisibility(View.GONE);
                txtSelectPassengerReference = txtSelectPassenger;
                idSelectedPassengerTag = (int) layout_add_passenger.getTag();

                if (lytAddPassenger.getChildCount() - 1 <= listUserDetails.size()) {
                    removePassengerFromListDetailsJSON((int) layout_add_passenger.getTag() - 1);
                }

                /*int actualAddedUser = lytAddPassenger.getChildCount();
                for (int i = 0; i < lytAddPassenger.getChildCount(); i++) {
                    View childAt = lytAddPassenger.getChildAt(i);

                    OTTextView txtAddNewPassengerLabel = (OTTextView) childAt.findViewById(R.id.txtAddNewPassengerLabel);
                    if(txtAddNewPassenger.getText().toString().equals(getString(R.string.string_add_new_passenger)))
                    {
                        actualAddedUser--;
                    }
                }*/


            }
        });


        lytAddPassenger.addView(layout_add_passenger);

        //add users in lytAllUsers
        SelectedUserData sData = message.getSelectedUserData();

        if (sData != null) {
            final ArrayList<AddUserData> listUserData = sData.getAddUserData();
            for (int index = 0; index < listUserData.size(); index++) {
                boolean isSelected = true;
                ArrayList<String> listSelectedPassenger = getAllSelectedNames(lytAddPassenger);
                for (int pos = 0; pos < listSelectedPassenger.size(); pos++) {
                    if (getUserName(listUserData.get(index)).equals(listSelectedPassenger.get(pos).toString())) {
                        isSelected = false;
                        break;
                    }
                }


                OTTextView textView = new OTTextView(getActivity());
                textView.setText(getUserName(listUserData.get(index)));
                textView.setPadding(0, 10, 0, 0);
                textView.getPaddingTop();
                textView.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_11)));
                textView.setTextColor(getResources().getColor(R.color.color_font_black));
                textView.setTypeface(null, Typeface.NORMAL);
                textView.setVisibility(isSelected ? View.VISIBLE : View.GONE);

                final int finalIndex = index;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //set text
                        txtSelectPassenger.setText(getUserName(listUserData.get(finalIndex)));
                        //dismiss dropdown list
                        lytUserDropdown.setVisibility(View.GONE);
                        lytAddUser.setVisibility(View.GONE);

                        rearrangeUI();
                        createRefereceForJson(listUserData.get(finalIndex), (int) layout_add_passenger.getTag() - 1);

                        //
                        if (fPOMobileReviewPassData.getFFPNumberMandatory() == 1
                                && listUserData.get(finalIndex).getFFPNumber() == null) {
                            setUIForEdit((int) layout_add_passenger.getTag() - 1);
                            lytAddUser.setVisibility(View.VISIBLE);

                            txtNewPassengerDetailsLabel.setText(getString(R.string.string_edit_passenger));
                            txtAddPassenger.setText(localization.getLABLSaveLabel());
                            txtSelectPassengerReference = txtSelectPassenger;
                            idSelectedPassengerTag = (int) layout_add_passenger.getTag();
                            txtAddNewPassenger.setVisibility(View.GONE);
                            imgEdit.setVisibility(View.GONE);
                            imgRemove.setVisibility(View.GONE);
                            //removePassengerFromListDetailsJSON(finalIndex);

                            //
                            ArrayList<String> listError = validateAllInputsPassenger();
                            if (listError.size() > 0) {
                                //first clear all previos error message view
                                lytErrorPassengerMessage.removeAllViews();
                                lytErrorPassenger.setVisibility(View.VISIBLE);
                                //svParent.fullScroll(ScrollView.FOCUS_UP);
                                svParent.scrollBy(0, 300);
                                //show error message
                                for (int index = 0; index < listError.size(); index++) {
                                    LinearLayout lytAddUesrErrorRow = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_add_user_error_row, null, false);
                                    OTTextView txtErrorMessage = (OTTextView) lytAddUesrErrorRow.findViewById(R.id.txtErrorMessage);
                                    txtErrorMessage.setText(listError.get(index).toString());

                                    lytErrorPassengerMessage.addView(lytAddUesrErrorRow);
                                }
                            }
                        }

                    }

                });

                //getAddMeAsFirstUserId functionality is only valid for first user
                if (listUserData.get(0).getSelectUserId() > 0 && listUserData.get(0).getSelectUserId() == message.getAddMeAsFirstUserId()) {
                    textView.performClick();
                    //disable getAddMeAsFirstUserId
                    message.setAddMeAsFirstUserId(0);
                }
                lytAllUsers.addView(textView);
            }
            applyListeners(txtSelectPassenger, layout_add_passenger, txtAddNewPassenger, imgEdit, imgRemove, message, listUserData);
        }

        if (message.isCallReviewAPI()) {
            //call review api to refresh the data, its because there may be a chance that user updated the pass by customizing it
            if (innerOuterIndexData != null) {
                innerOuterIndexData.setExpCheck(1);
            }
            if (passCMMIndexData != null) {
                passCMMIndexData.setExpCheck(1);
            }
            callFpoMobileReviewPass(innerOuterIndexData, passCMMIndexData, false, false, false);
        }
    }

    private void setUIForEdit(int position) {
        try {
            Utils.DEBUG("setUIForEdit() >> adding/modifying : " + position);
            isEditing = true;
            posEdit = position;


            UserDetails userDetails = listUserDetails.get(position);

            System.out.println("User bday  ..........  " +listUserDetails.get(position).getBDay().toString());


            txtPrefix.setText(Utils.getPrefixName(getActivity(), 1));
            edtFirstName.setText(userDetails.getFirstName());
            edtMiddleName.setText(userDetails.getMiddleName());
            edtLastName.setText(userDetails.getLastName());
            edtEmail.setText(userDetails.getEmailId());
            txtDay.setText(userDetails.getBDay());
            txtMonth.setText(Utils.getEquivalentLocalisedMonth(userDetails.getBMonth(), Utils.getLocalForCommunication(), Locale.getDefault()));
            txtYear.setText(userDetails.getBYear());
            txtCountryCode.setText("Afghanistan(+93)");                                /*/////////////////////*/
            edtMobile.setText(userDetails.getPrimaryPhoneNumber());
            edtClubNo.setText(userDetails.getFFPNumber());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }




        /*for(int i=0;i<selectedUserData.getAddUserData().size(); i++)
        {
            if(selectedUserData.getAddUserData().get(i).)
        }*/


    }

    private void clearAllFields() {
        txtPrefix.setText(localization.getLABLPrefixLabel() + "*");
        edtFirstName.setText("");
        edtMiddleName.setText("");
        edtLastName.setText("");
        edtEmail.setText("");
        txtDay.setText(localization.getLABLDaySmallcapLabel());
        txtMonth.setText(localization.getLABLMonthValidityLabel());
        txtYear.setText(localization.getLABLYearLabel());
        txtCountryCode.setText("Afghanistan(+93)");                                /*/////////////////////*/
        edtMobile.setText("");
        edtClubNo.setText("");
    }

    private void createRefereceForJsonNewlyAddedorModifiedPassenger(int position) {
        Utils.DEBUG("createRefereceForJsonNewlyAddedorModifiedPassenger() >> adding/modifying : " + position);
        UserDetails userDetails = new UserDetails();
        userDetails.setSelectUserId("");//as its new passenger
        userDetails.setFirstName(edtFirstName.getText().toString().trim());
        userDetails.setMiddleName(edtMiddleName.getText().toString().trim());
        userDetails.setLastName(edtLastName.getText().toString().trim());
        userDetails.setEmailId(edtEmail.getText().toString().trim());
        userDetails.setBDay(txtDay.getText().toString().trim());
        userDetails.setBMonth(Utils.getEquivalentLocalisedMonth(txtMonth.getText().toString().trim(), Locale.getDefault(), Utils.getLocalForCommunication()));
        userDetails.setBYear(txtYear.getText().toString().trim());
        userDetails.setPrimaryPhoneCode(txtCountryCode.getText().toString().trim());
        userDetails.setPrimaryPhoneNumber(edtMobile.getText().toString().trim());
        userDetails.setCustomerId(Integer.toString(loginData != null ? loginData.getCustomerId() : loginDataUpdateProfile.getCustomerId()));
        userDetails.setPrefixId(Integer.toString(Utils.getPrefixId(getActivity(), txtPrefix.getText().toString())));
        userDetails.setFFPNumber(edtClubNo.getText().toString().trim());

        if (position >= listUserDetails.size()) {
            listUserDetails.add(position, userDetails);
        } else {
            listUserDetails.set(position, userDetails);
        }

        Utils.DEBUG("createRefereceForJsonNewlyAddedorModifiedPassenger() >> Equivalent json : " + (ParseManager.getInstance().toJSON(userDetails)));

    }

    private void createRefereceForJson(AddUserData addUserData, int position) {
        Utils.DEBUG("createRefereceForJson() >> adding/modifying : " + position);
        if (addUserData != null) {
            UserDetails userDetails = new UserDetails();
            userDetails.setSelectUserId(Integer.toString(addUserData.getSelectUserId()));
            userDetails.setFirstName(addUserData.getFirstName());
            userDetails.setMiddleName(addUserData.getMiddleName());
            userDetails.setLastName(addUserData.getLastname());
            userDetails.setEmailId(addUserData.getEmail());
            userDetails.setBDay(getBirthData(addUserData.getDOB(), Calendar.DATE));
            userDetails.setBMonth(getBirthData(addUserData.getDOB(), Calendar.MONTH));
            userDetails.setBYear(getBirthData(addUserData.getDOB(), Calendar.YEAR));
            userDetails.setPrimaryPhoneCode("");                                              /*////////////////////////////*/
            userDetails.setPrimaryPhoneNumber(addUserData.getPrimaryPhNO());
            userDetails.setCustomerId(Integer.toString(loginData != null ? loginData.getCustomerId() : loginDataUpdateProfile.getCustomerId()));
            userDetails.setPrefixId(Integer.toString(addUserData.getPrefixID()));
            userDetails.setFFPNumber(addUserData.getFFPNumber());

            if (position >= listUserDetails.size()) {
                listUserDetails.add(position, userDetails);
            } else {
                listUserDetails.set(position, userDetails);
            }
            Utils.DEBUG("createRefereceForJson() >> Equivalent json : " + (ParseManager.getInstance().toJSON(userDetails)));

            //print complete json
            ArrayList<UserDetails> list = new ArrayList<>(listUserDetails);
            PassengerData passengerData = new PassengerData();
            passengerData.setUserDetails(list);
            Utils.DEBUG("complete json : " + ParseManager.getInstance().toJSON(passengerData));
        }
    }


    //

    /**
     * input '02-17-1984' output '1984' if type is Calendar.YEAR
     *
     * @param dob
     * @param type
     * @return
     */
    private String getBirthData(String dob, int type) {
        SimpleDateFormat sf = new SimpleDateFormat("dd-yyyy-MMM", Utils.getLocalForCommunication());//changed format in 18-1982-Mar
        try {
            Date date = sf.parse(dob);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            int value = cal.get(type);
            switch (type) {
                case Calendar.YEAR:
                    return Integer.toString(value);

                case Calendar.DATE:
                    return String.format("%02d", (value));

                case Calendar.MONTH:
                    return String.format("%02d", (value + 1));
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    private void applyListeners(final OTTextView txtSelectPassenger, final View layout_add_passenger, OTTextView txtAddNewPassenger, ImageView imgEdit, ImageView imgRemove, final FragmentCommunicationData message, final ArrayList<AddUserData> listUserData) {
        //allow to add
        //txtAddNewPassenger.setVisibility(View.VISIBLE);
        txtAddNewPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (listUserData.size() == count_added_user_ut - getNewlyAddedPassengerCount(listUserData) ) {
                    lytAddUser.setVisibility(View.VISIBLE);
                    txtSelectPassengerReference = txtSelectPassenger;
                    idSelectedPassengerTag = (int)layout_add_passenger.getTag();
                } else {
                    updateUIForUser(message);
                }*/

                updateUIForUser(message);
                v.setVisibility(View.GONE);
            }
        });

        if (count_added_user_ut == 1 || txtSelectPassenger.getText().toString().equals(localization.getLABLMasterUKLAOAvailableSelectLabel()))//first selected
        {
            imgEdit.setVisibility(View.GONE);
            imgRemove.setVisibility(View.GONE);
        } else {
            imgEdit.setVisibility(View.VISIBLE);
            imgRemove.setVisibility(View.VISIBLE);
        }

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUIForEdit((int) layout_add_passenger.getTag() - 1);
                lytAddUser.setVisibility(View.VISIBLE);

                txtNewPassengerDetailsLabel.setText(getString(R.string.string_edit_passenger));
                txtAddPassenger.setText(localization.getLABLSaveLabel());
                txtSelectPassengerReference = txtSelectPassenger;
                idSelectedPassengerTag = (int) layout_add_passenger.getTag();
            }
        });


        imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ask to remove
                YesNoDialog ynDialog = new YesNoDialog() {
                    @Override
                    public void onClickYes() {
                        count_added_user_ut--;
                        lytAddPassenger.removeView(layout_add_passenger);
                        lytAddUser.setVisibility(View.GONE);
                        removePassengerFromListDetailsJSON((int) layout_add_passenger.getTag() - 1);
                        rearrangeUI();
                        isEditing = false;

                        dismiss();
                    }

                    @Override
                    public void onClickNo() {
                        dismiss();
                    }
                };
                ynDialog.show(((FragmentActivity) getActivity()).getFragmentManager(), "dialog");
            }
        });
    }

    private int getNewlyAddedPassengerCount(ArrayList<AddUserData> listUserData) {

        int childCount = lytAddPassenger.getChildCount();
        int count = childCount;
        for (int index = 0; index < childCount; index++) {
            View child = lytAddPassenger.getChildAt(index);

            OTTextView txtSelectPassenger = (OTTextView) child.findViewById(R.id.txtSelectPassenger);
            txtSelectPassenger.setText(localization.getLABLMasterUKLAOAvailableSelectLabel());

            if (txtSelectPassenger != null) {
                for (int pos = 0; pos < listUserData.size(); pos++) {
                    Utils.DEBUG("getNewlyAddedPassengerCount() > " + getUserName(listUserData.get(pos)) + " vs " + txtSelectPassenger.getText().toString());
                    if (getUserName(listUserData.get(pos)).equals(txtSelectPassenger.getText().toString())) {
                        count--;
                        break;
                    }
                }

            }
        }
        Utils.DEBUG("getNewlyAddedPassengerCount() > " + count);
        return count;
    }

    private void removePassengerFromListDetailsJSON(int position) {
        Utils.DEBUG("ReviewFragment >> removePassengerFromListDetailsJSON() : " + position);
        try {
            isEditing = false;
            listUserDetails.remove(position);
        } catch (IndexOutOfBoundsException e) {
            Utils.ERROR("ReviewFragment >> removePassengerFromListDetailsJSON() error : " + e.toString());
        }

    }

    private void rearrangeUI() {
        int childCount = lytAddPassenger.getChildCount();

        for (int index = 0; index < childCount; index++) {
            View childAt = lytAddPassenger.getChildAt(index);
            childAt.setTag(index + 1);//rearrange tag also, may be someone got removed
            OTTextView txtPassenger = (OTTextView) childAt.findViewById(R.id.txtPassenger);

            if (txtPassenger != null) {
                txtPassenger.setText(localization.getLABLPassengerShortLabel() + " " + (index + 1) + "*");
            }

            OTTextView txtAddNewPassenger = (OTTextView) childAt.findViewById(R.id.txtAddNewPassenger);
            if (txtAddNewPassenger != null && index == childCount - 1 && lytAddUser.getVisibility() != View.VISIBLE
                    && index != MAX_USER_ALLOWED - 1)//last and lytAddUser should not visible and should not more than MAX_USER_ALLOWED
            {
                txtAddNewPassenger.setVisibility(View.VISIBLE);
            } else {
                txtAddNewPassenger.setVisibility(View.GONE);
            }


            ImageView imgEdit = (ImageView) childAt.findViewById(R.id.imgEdit);
            ImageView imgRemove = (ImageView) childAt.findViewById(R.id.imgRemove);

            imgEdit.setVisibility(imgEdit != null && index == 0 ? View.GONE : View.VISIBLE);
            imgRemove.setVisibility(imgRemove != null && index == 0 ? View.GONE : View.VISIBLE);


            LinearLayout lytAllUsers = (LinearLayout) childAt.findViewById(R.id.lytAllUsers);
            if (lytAllUsers != null) {
                int countElement = lytAllUsers.getChildCount();
                for (int pos = 0; pos < countElement; pos++) {
                    View element = lytAllUsers.getChildAt(pos);
                    if (element instanceof OTTextView) {
                        String strElement = ((OTTextView) element).getText().toString();
                        boolean isSelected = false;
                        ArrayList<String> listSelectedPassenger = getAllSelectedNames(lytAddPassenger);
                        for (int i = 0; i < listSelectedPassenger.size(); i++) {

                            if (strElement.equals(listSelectedPassenger.get(i))) {
                                isSelected = true;
                            }
                        }

                        element.setVisibility(isSelected ? View.GONE : View.VISIBLE);
                    }
                }
            }

        }
    }

    /**
     * used to get all selected passenger name
     *
     * @param lyt
     * @return
     */
    private ArrayList<String> getAllSelectedNames(LinearLayout lyt) {
        ArrayList<String> list = new ArrayList<>();
        if (lyt == null) {
            return list;
        }

        int childCount = lyt.getChildCount();

        for (int index = 0; index < childCount; index++) {
            View child = lyt.getChildAt(index);

            OTTextView txtSelectPassenger = (OTTextView) child.findViewById(R.id.txtSelectPassenger);

            if (txtSelectPassenger != null/* && !txtSelectPassenger.getText().toString().equals(getString(R.string.string_select))*/) {
                //Utils.DEBUG("getAllSelectedNames() selected name : >> " + txtSelectPassenger.getText().toString());
                list.add(txtSelectPassenger.getText().toString());
            }
        }

        return list;
    }


    private void callAddUserApi(final LoginData loginData) {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_ADD_USER);


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("customerId", Integer.toString(loginData.getCustomerId()));
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        selectedUserData = ParseManager.getInstance().fromJSON(response, SelectedUserData.class);

                        FragmentCommunicationData data = new FragmentCommunicationData();
                        data.setFragmentName(new ReviewFragment().getClass().getName());
                        data.setLoginData(loginData);
                        data.setSelectedUserData(selectedUserData);


                        updateUIForUser(data);

                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.string_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    @Override
    public void onBackEventPre() {
        super.onBackEventPre();
    }

    @Override
    public void onBackEventPost() {
        super.onBackEventPost();
        Utils.DEBUG("ReviewFragment >> onBackEventPost" + innerOuterIndexData + "/" + passCMMIndexData);
        if (innerOuterIndexData != null) {
            callBackSearchHomeBannerApi();
        }
    }

    @Override
    public void onFocusEvent() {
        super.onFocusEvent();
        Utils.DEBUG("ReviewFragment >> onFocusEvent");
    }

    private void callBackSearchHomeBannerApi() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_BackSearchHomeBanner);


        JSONObject requestObject = new JSONObject();

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.string_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}

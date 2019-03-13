package com.optiontown.app.view.fragment.legproducts;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.adapter.LegProductReviewFragmentAdapter;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.legproducthomepage.SearchHelper;
import com.optiontown.app.model.legreview.BackSearchData;
import com.optiontown.app.model.legreview.CardDetail;
import com.optiontown.app.model.legreview.FlightDetail;
import com.optiontown.app.model.legreview.GetMTListDatum;
import com.optiontown.app.model.legreview.LegReviewHome;
import com.optiontown.app.model.legreview.MtESORules;
import com.optiontown.app.model.legreview.PassLevelPriceSummary;
import com.optiontown.app.model.legreview.PaymentDetail;
import com.optiontown.app.model.legreview.PriceSummary;
import com.optiontown.app.model.legreview.ReviewDataSend;
import com.optiontown.app.model.legreview.ReviewDetails;
import com.optiontown.app.model.legreview.SelectedFlightDetail;
import com.optiontown.app.model.legviewdetails.Rule;
import com.optiontown.app.model.legviewdetails.UtoRules;
import com.optiontown.app.model.legviewdetails.ViewDetails;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.seatview.SelectedData;
import com.optiontown.app.model.utosearchresult.UtosearchresultHome;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.customview.OTTextViewHtml;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.login.TermsServicePolicyFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by zafar.imam on 05-08-2016.
 */
public class LegProductReviewFragment extends BaseFragment {
    private AppDialogLoader loader;
    private List<FlightDetail> flightDetailList;
    private String selectedKey = "";
    private View view;
    private RadioGroup radio_grp_payment;
    private Button btnContinueToNextSteps;
    private OTEditText edtEmail, edtCellMobile, edtOtherPhone;
    private LegProductReviewFragmentAdapter adapter;
    private LinearLayout lytHeaderThreeColumn, lytHeaderTwoColumn, lytPhonExtension, lytOtherPhonExtension, lytRules,
            lytPaypalCheck, lytMultiProduct, lytMultiRules, lyt_head_table, lyPaxCount, lytTotalAmountPay, lytTotalPrice;
    private RelativeLayout lytLegProductReview, rlytCheckBox, lytRadioGrp;
    private String rules = "";
    private CheckBox chkTerms, cbSelect;
    private ArrayList<String> ruleArray, multiRuleArray;
    private ScrollView scrollView;
    private int showPaypal;
    private List<String> cardDescriptionList;
    private NetworkImageView imgMultiPro;
    private String email, primaryNumber, otherNumber, primaryCc, otherCc, cardCategoryId, isExpressCheckout;
    private String title, checkValue, cardLabel;
    private AppSharedPrefs sp;
    private OTTextView txtUtoPriceSummaryLabel, txtFlightsLabel, txtSignUpPriceLabel, txtSignUpPriceRefundLabel, txtUpgradePriceLabel,
            txtEligiblePassengerLabel, txtContactInfoLabel, txtEmailLabel, txtTotalAmountToPay,
            txtCellMobileLabel, txtOtherPhoneLabel, txtPaymentModeLabel, txtNoOfPassenger, txtTotalPrice, txtMobileCC, txtOtherPhoneCode, txtFlightsLabelTwoColumn,
            txtUpgradePriceRefundLabel, txtTotalPriceLabel,
            txtUpgradePriceLabelTwoColumn, txtTermsOfService, txtUtoRules, txtPaypayCheckLabel, txtPaypalDescription, txtFreeSignupLabel, txtNumberOfRow, txtSelectLabel, txtSelectAmount, txtInformationLabel, txtViewRulesLabel, txtSaveLabel, txtPayWithCard;

    private RecyclerView recycleViewReview;
    private LinearLayoutManager mLinearLayoutManager;
    private CountryExtData countryExtData;
    private boolean joinCheck;
    private OTTextViewHtml txtTotalAmountToPayLabel;
    private String productID = "", LanguageId = "", CountryId = "", MarketingAirlineId = "", pnr = "", lastName = "", isSearchBy = "", OCN = "", arriveArptId, departArptId, departArptCode, arriveArptCode, dateOfJourney,
            totalAmountToPay, checkEmail;
    private ImageLoader imageLoader;

    private LinearLayout lytUpgradePassCreditSummary;
    private LinearLayout lytUpgradePassCreditSummaryRow;
    private OTTextView txtUpCreditSummaryLable;
    private OTTextView txtFlightLable;
    private OTTextView txtUpCreditRequiredLabel;
    private OTTextView txtTotalUpCreditRequired;
    private OTTextView txtTotalUpCreditRqrdLabel;
    private OTTextView txtSignUpPriceInfo;
    private OTTextView txtOptionPriceInfo;
    private boolean checked;
    private LoginData loginData;
    private HashMap<String, ArrayList<String>> mapSelectedPax;
    private LegReviewHome legReviewHome;
    private SearchHelper mSearchHelper;
    private SelectedData selectedData;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.leg_product_review, container, false);
        init(view);
        sp = AppSharedPrefs.getInstance(getActivity());
        cardCategoryId = "1";
        isExpressCheckout = "0";

        Utils.updateActionBarForFeatures(getActivity(), new LegProductsHomeFragment().getClass().getName());
        loader = AppDialogLoader.getLoader(getActivity());

        try {
            mSearchHelper = (SearchHelper) getArguments().get(getActivity().getString(R.string.key_serializable));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mSearchHelper != null) {
            checkEmail = mSearchHelper.getCheckEmail();
            joinCheck = mSearchHelper.isEmailCheck();
            mapSelectedPax = mSearchHelper.getSelectedPax();
            //   Utils.DEBUG("Review Selected Pax "+ Arrays.toString(mapSelectedPax));
            if (joinCheck == true) {
                checkValue = "1";
            } else {
                checkValue = "0";
            }
            Utils.DEBUG("checkEmail :" + checkEmail);
            Utils.DEBUG("joinCheck :" + joinCheck);
        }
        LoadReviewData(checkEmail, checkValue);

        imageLoader = AppController.getInstance().getImageLoader();
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        ArrayList<Benefit> mBenefits = new ArrayList<>();

        //-- Calling country code for mobile

        try {
            countryExtData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getCountryExtData(getActivity())), CountryExtData.class);

        } catch (Exception e) {
            Utils.ERROR("CreateAccountFromReviewFragment >> Error while parsing json : " + e.toString());
        }

        loadRulesData();

        return view;
    }


    private void init(View view) {

        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        scrollView.setVisibility(View.GONE);
        radio_grp_payment = (RadioGroup) view.findViewById(R.id.radio_grp_payment);
        btnContinueToNextSteps = (Button) view.findViewById(R.id.btnContinueToNextSteps);
        recycleViewReview = (RecyclerView) view.findViewById(R.id.recycleViewReview);

        txtUtoPriceSummaryLabel = (OTTextView) view.findViewById(R.id.txtUtoPriceSummaryLabel);
        txtFlightsLabel = (OTTextView) view.findViewById(R.id.txtFlightsLabel);
        txtFlightsLabelTwoColumn = (OTTextView) view.findViewById(R.id.txtFlightsLabelTwoColumn);
        txtUpgradePriceLabelTwoColumn = (OTTextView) view.findViewById(R.id.txtUpgradePriceLabelTwoColumn);
        txtSignUpPriceLabel = (OTTextView) view.findViewById(R.id.txtSignUpPriceLabel);
        txtSignUpPriceRefundLabel = (OTTextView) view.findViewById(R.id.txtSignUpPriceRefundLabel);
        txtUpgradePriceLabel = (OTTextView) view.findViewById(R.id.txtUpgradePriceLabel);
        txtUpgradePriceRefundLabel = (OTTextView) view.findViewById(R.id.txtUpgradePriceRefundLabel);
        txtEligiblePassengerLabel = (OTTextView) view.findViewById(R.id.txtEligiblePassengerLabel);
        txtNoOfPassenger = (OTTextView) view.findViewById(R.id.txtNoOfPassenger);
        txtTotalAmountToPayLabel = (OTTextViewHtml) view.findViewById(R.id.txtTotalAmountToPayLabel);
        txtTotalAmountToPay = (OTTextView) view.findViewById(R.id.txtTotalAmountToPay);

        txtTotalPriceLabel = (OTTextView) view.findViewById(R.id.txtTotalPriceLabel);
        txtContactInfoLabel = (OTTextView) view.findViewById(R.id.txtContactInfoLabel);
        txtEmailLabel = (OTTextView) view.findViewById(R.id.txtEmailLabel);
        txtCellMobileLabel = (OTTextView) view.findViewById(R.id.txtCellMobileLabel);
        txtOtherPhoneLabel = (OTTextView) view.findViewById(R.id.txtOtherPhoneLabel);
        txtPaymentModeLabel = (OTTextView) view.findViewById(R.id.txtPaymentModeLabel);
        txtTotalPrice = (OTTextView) view.findViewById(R.id.txtTotalPrice);
        txtMobileCC = (OTTextView) view.findViewById(R.id.txtMobileCC);
        txtOtherPhoneCode = (OTTextView) view.findViewById(R.id.txtOtherPhoneCode);
        txtUtoRules = (OTTextView) view.findViewById(R.id.txtUtoRules);
        txtTermsOfService = (OTTextView) view.findViewById(R.id.txtTermsOfService);
        txtPaypayCheckLabel = (OTTextView) view.findViewById(R.id.txtPaypayCheckLabel);
        txtPaypalDescription = (OTTextView) view.findViewById(R.id.txtPaypalDescription);
        txtPayWithCard = (OTTextView) view.findViewById(R.id.txtPayWithCard);

        lytUpgradePassCreditSummary = (LinearLayout) view.findViewById(R.id.lytUpgradePassCreditSummary);
        lytUpgradePassCreditSummaryRow = (LinearLayout) view.findViewById(R.id.lytUpgradePassCreditSummaryRow);
        txtUpCreditSummaryLable = (OTTextView) view.findViewById(R.id.txtUpCreditSummaryLable);
        txtFlightLable = (OTTextView) view.findViewById(R.id.txtFlightLable);
        txtUpCreditRequiredLabel = (OTTextView) view.findViewById(R.id.txtUpCreditRequiredLabel);
        txtTotalUpCreditRequired = (OTTextView) view.findViewById(R.id.txtTotalUpCreditRequired);
        txtTotalUpCreditRqrdLabel = (OTTextView) view.findViewById(R.id.txtTotalUpCreditRqrdLabel);
        txtSignUpPriceInfo = (OTTextView) view.findViewById(R.id.txtSignUpPriceInfo);
        txtOptionPriceInfo = (OTTextView) view.findViewById(R.id.txtOptionPriceInfo);

        txtUtoRules.setText(rulesShortName(AppVariables.ProductName) + " Rules");

        txtFreeSignupLabel = (OTTextView) view.findViewById(R.id.txtFreeSignupLabel);
        txtNumberOfRow = (OTTextView) view.findViewById(R.id.txtNumberOfRow);
        txtSelectLabel = (OTTextView) view.findViewById(R.id.txtSelectLabel);
        txtSaveLabel = (OTTextView) view.findViewById(R.id.txtSaveLabel);
        txtSelectAmount = (OTTextView) view.findViewById(R.id.txtSelectAmount);
        txtInformationLabel = (OTTextView) view.findViewById(R.id.txtInformationLabel);
        txtViewRulesLabel = (OTTextView) view.findViewById(R.id.txtViewRulesLabel);

        lytHeaderThreeColumn = (LinearLayout) view.findViewById(R.id.lytHeaderThreeColumn);
        lytHeaderTwoColumn = (LinearLayout) view.findViewById(R.id.lytHeaderTwoColumn);
        lyPaxCount = (LinearLayout) view.findViewById(R.id.lyPaxCount);
        lytTotalAmountPay = (LinearLayout) view.findViewById(R.id.lytTotalAmountPay);
        lytTotalPrice = (LinearLayout) view.findViewById(R.id.lytTotalPrice);


        lytPhonExtension = (LinearLayout) view.findViewById(R.id.lytPhonExtension);
        lytOtherPhonExtension = (LinearLayout) view.findViewById(R.id.lytOtherPhonExtension);
        lytPaypalCheck = (LinearLayout) view.findViewById(R.id.lytPaypalCheck);
        lytMultiProduct = (LinearLayout) view.findViewById(R.id.lytMultiProduct);
        lytMultiRules = (LinearLayout) view.findViewById(R.id.lytMultiRules);
        lyt_head_table = (LinearLayout) view.findViewById(R.id.lyt_head_table);

        lytLegProductReview = (RelativeLayout) view.findViewById(R.id.lytLegProductReview);
        rlytCheckBox = (RelativeLayout) view.findViewById(R.id.rlytCheckBox);
        lytRadioGrp = (RelativeLayout) view.findViewById(R.id.lytRadioGrp);

        imgMultiPro = (NetworkImageView) view.findViewById(R.id.imgMultiPro);

        edtEmail = (OTEditText) view.findViewById(R.id.edtEmail);
        edtCellMobile = (OTEditText) view.findViewById(R.id.edtCellMobile);
        edtOtherPhone = (OTEditText) view.findViewById(R.id.edtOtherPhone);

        chkTerms = (CheckBox) view.findViewById(R.id.chkTerms);
        cbSelect = (CheckBox) view.findViewById(R.id.cbSelect);


        setVisibilityContact(Utils.isPassFlow(getActivity()) ? View.GONE : View.VISIBLE);

        txtUtoRules.setPaintFlags(txtUtoRules.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtTermsOfService.setPaintFlags(txtTermsOfService.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btnContinueToNextSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateFields();

            }
        });


        lytLegProductReview = (RelativeLayout) view.findViewById(R.id.lytLegProductReview);
        final ScrollView scrollPrimaryCountryCode = (ScrollView) lytLegProductReview.findViewById(R.id.scrollPrimaryCountryCode);
        final ScrollView scrollOtherCountryCode = (ScrollView) lytLegProductReview.findViewById(R.id.scrollOtherCountryCode);

        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                scrollPrimaryCountryCode.getParent().requestDisallowInterceptTouchEvent(false);
                scrollOtherCountryCode.getParent().requestDisallowInterceptTouchEvent(false);

                return false;
            }
        });

        Utils.setInterceptTouchEvent(new View[]{scrollPrimaryCountryCode, scrollOtherCountryCode});


        lytLegProductReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollPrimaryCountryCode.setVisibility(View.GONE);
                scrollOtherCountryCode.setVisibility(View.GONE);

            }
        });


        txtMobileCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.createDropdownView(Utils.getCountryListExtArray(countryExtData.getData()), lytPhonExtension, txtMobileCC, new LinearLayout[]{lytOtherPhonExtension});
            }
        });

        txtOtherPhoneCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.createDropdownView(Utils.getCountryListExtArray(countryExtData.getData()), lytOtherPhonExtension, txtOtherPhoneCode, new LinearLayout[]{lytPhonExtension});
            }
        });

        txtUtoRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRulesDialog();
            }
        });
        txtTermsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new TermsServicePolicyFragment(), getString(R.string.URL_METHOD_TERMS_OF_SERVICE), 0);

            }
        });
        txtViewRulesLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytMultiRules.setVisibility(lytMultiRules.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        Utils.setCountryCodeDefault(getActivity(), txtMobileCC);
        Utils.setCountryCodeDefault(getActivity(), txtOtherPhoneCode);

        if (Utils.isUserLoggedIn(getActivity())) {
            try {
                loginData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLoginData(getActivity())), LoginData.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (loginData != null) {
                try {
                    Utils.DEBUG("Email ::" + loginData.getCompleteProfile().getEmail());
                    edtEmail.setText(loginData.getCompleteProfile().getEmail());
                    edtCellMobile.setText(loginData.getCompleteProfile().getPhoneNumber());
                    edtOtherPhone.setText(loginData.getCompleteProfile().getAlternateTelNumber());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }


    private String rulesShortName(String productName) {

        if (productName.equalsIgnoreCase("Empty Seat")) {
            return "ESo";
        } else if (productName.equalsIgnoreCase("Upgrade")) {
            return "UTo";
        } else if (productName.equalsIgnoreCase("Preferred Seat")) {
            return "PSo";
        } else if (productName.equalsIgnoreCase("Extra Baggage")) {
            return "XBo";
        } else if (productName.equalsIgnoreCase("Lounge Access")) {
            return "LAo";
        } else if (productName.equalsIgnoreCase("Flexible Rewards")) {
            return "FRo";
        } else if (productName.equalsIgnoreCase("Priority Handling")) {
            return "PHo";
        } else if (productName.equalsIgnoreCase("Upgrade Pass")) {
            return "Up";
        } else if (productName.equalsIgnoreCase("Empty Seat Pass")) {
            return "ESp";
        } else if (productName.equalsIgnoreCase("Preferred Seat Pass")) {
            return "PSp";
        } else if (productName.equalsIgnoreCase("Special Baggage")) {
            return "SBo";
        } else if (productName.equalsIgnoreCase("Carry-On Baggage")) {
            return "CBo";
        }

        return "";
    }

    public void LoadPaymentTypes(final List<CardDetail> number) {

        if (number.size() == 0) {
            cardCategoryId = "0";
            txtPaymentModeLabel.setVisibility(View.GONE);
            lytRadioGrp.setVisibility(View.GONE);
            if (Utils.isPassFlow(getActivity())) {
                rlytCheckBox.setVisibility(View.VISIBLE);
            } else {
                rlytCheckBox.setVisibility(View.GONE);
            }

        } else {
            lytRadioGrp.setVisibility(View.VISIBLE);
            cardDescriptionList = new ArrayList<>();
            for (int i = 0; i < number.size(); i++) {

                RadioButton rdbtn = new RadioButton(getActivity());
                rdbtn.setId(number.get(i).getCardCategoryId());
                cardLabel = number.get(i).getCardCategoryLabel();
                cardDescriptionList.add(number.get(i).getCardCategoryDescryption());
                try {
                    if (cardLabel.contains("www")) {
                        rdbtn.setCompoundDrawablesWithIntrinsicBounds(null, null, getActivity().getResources().getDrawable(R.drawable.paypal_icon), null);
                    } else {
                        rdbtn.setText(cardLabel);
                        rdbtn.setTextSize(12);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!(showPaypal == 0 && number.get(i).getCardCategoryId() == 3)) {
                    radio_grp_payment.addView(rdbtn);
                }
                if (i == 0) {
                    rdbtn.setChecked(true);
                }


            }
            txtPayWithCard.setText(cardDescriptionList.get(0));
        }
        radio_grp_payment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) radio_grp_payment.findViewById(checkedId);
                List<CardDetail> cardDetail = new ArrayList<CardDetail>();
                checkedRadioButton.setPadding(10, 0, 10, 0);
                cardCategoryId = checkedRadioButton.getId() + "";

                if (cardCategoryId.equals("1")) {
                    txtPayWithCard.setText(cardDescriptionList.get(0));
                } else if (cardCategoryId.equalsIgnoreCase("2")) {
                    txtPayWithCard.setText(cardDescriptionList.get(1));
                } else if (showPaypal == 1 && cardCategoryId.equalsIgnoreCase("3")) {
                    txtPayWithCard.setText(cardDescriptionList.get(2));
                } else {
                    txtPayWithCard.setText("");
                }
                //   Utils.showToast(getActivity(), checkedRadioButton.getText().toString());

                if (cardCategoryId.equals("1") || cardCategoryId.equals("2")) {
                    lytPaypalCheck.setVisibility(View.GONE);
                    isExpressCheckout = "0";

                    btnContinueToNextSteps.setText(legReviewHome.getSearchResult().getProceed_To_Payment_Now_Label());
                    btnContinueToNextSteps.setBackgroundResource(R.drawable.rounded_small_corner_search);
                    //  Utils.showToast(getActivity(), checkedRadioButton.getText().toString()+"isExpressCheckout is "+isExpressCheckout);
                    new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);

                            ViewGroup.LayoutParams layoutParams = btnContinueToNextSteps.getLayoutParams();
                            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                            btnContinueToNextSteps.setLayoutParams(layoutParams);
                        }
                    }.sendEmptyMessage(0);
                } else {
                    isExpressCheckout = "1";
                    if (cardCategoryId.equalsIgnoreCase("0")) {
                        isExpressCheckout = "0";
                    }
                    lytPaypalCheck.setVisibility(View.VISIBLE);
                    String pasyPalHead = number.get(2).getPaypalHeading();
                    String paypalDesc = number.get(2).getPaypalHelpLabel();
                    txtPaypayCheckLabel.setText(pasyPalHead);
                    txtPaypalDescription.setText(paypalDesc);
                    btnContinueToNextSteps.setBackgroundResource(R.drawable.btn_paypal_checkout);
                    btnContinueToNextSteps.setText("");
                    //  Utils.showToast(getActivity(), checkedRadioButton.getText().toString()+"isExpressCheckout is "+isExpressCheckout);
                    new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);

                            ViewGroup.LayoutParams layoutParams = btnContinueToNextSteps.getLayoutParams();
                            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                            btnContinueToNextSteps.setLayoutParams(layoutParams);
                        }
                    }.sendEmptyMessage(0);
                }

            }
        });
    }


    private void LoadReviewData(String emailAddress, String joinCheck) {
        loader.show();
        StringBuilder selectedpassid = new StringBuilder();

        if (Utils.getCurrentProductId(getActivity()) == getActivity().getResources().getInteger(R.integer.value_tgProductId_pso) && getActivity().getResources().getBoolean(R.bool.bool_enable_seatmap)) {
            selectedpassid = appendSeatSelected(selectedpassid);
        } else {
            selectedpassid = appendUrlEndPoint(selectedpassid);
        }


        String tag_json_obj = "json_obj_req";

        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SelectUTO);

        url = url + selectedpassid;

        JSONObject requestObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {

            for (int i = 0; i < 5; i++) {
                jsonArray.put(new JSONObject().put("styleid", i + ""));
            }
            requestObject.put("SelectedData", jsonArray);
            requestObject.put("emailAddress", emailAddress);
            requestObject.put("joinCheck", joinCheck);


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
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("Review Response sdf: " + response.toString());
                        legReviewHome = ParseManager.getInstance().fromJSON(response, LegReviewHome.class);
                        ReviewDetails mReviewDetails = legReviewHome.getReviewDetails();
                        reviewDetailData(mReviewDetails);
                        if (mReviewDetails.getGetMTListData() != null && mReviewDetails.getGetMTListData().size() > 0) {
                            showMultiProduct(mReviewDetails.getGetMTListData());
                            lytMultiProduct.setVisibility(View.VISIBLE);
                        } else {
                            lytMultiProduct.setVisibility(View.GONE);
                        }
                        PriceSummary priceSummary = mReviewDetails.getPriceSummary();
                        updatePassLevelPriceSummary(priceSummary);
                        loader.dismiss();
                        BackSearchData mBackSearchData = priceSummary.getBackSearchData();
                        backSearchData(mBackSearchData);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), "Server Timeout");
                loader.dismiss();


            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private StringBuilder appendSeatSelected(StringBuilder selectedpassid) {
        HashMap<String, Object> seatMapData = mSearchHelper.getSeatMapData();
        Set<String> strings = seatMapData.keySet();
        selectedpassid =  selectedpassid.append("&isSelected=");
        for (String key : strings) {
            selectedData = (SelectedData) seatMapData.get(key);
            Utils.DEBUG(">>> " + selectedData.getPaxFullName() + " " + selectedData.getSeatDesignator() + " " + selectedData.getStyleId2() + " "+selectedData.getPaxId());
            selectedpassid = selectedpassid.append(selectedData.getStyleId2()).append("_").append(selectedData.getPaxId()).append("!").append(selectedData.getSeatDesignator()).append("$");

        }

        selectedpassid = selectedpassid.deleteCharAt(selectedpassid.length()-1);

        return selectedpassid;
    }


    private StringBuilder appendUrlEndPoint(StringBuilder selectedpassid) {
        try {
            String SearchedDataAll = sp.get("SearchedDataAll").toString();
            UtosearchresultHome mUtosearchresultHome = ParseManager.getInstance().fromJSON(new JSONObject(SearchedDataAll), UtosearchresultHome.class);

            for (int i = 0; i < mUtosearchresultHome.getIfsObject().size(); i++) {

                for (int j = 0; j < mUtosearchresultHome.getIfsObject().get(i).getLegListObj().size(); j++) {

                    for (int k = 0; k < mUtosearchresultHome.getIfsObject().get(i).getLegListObj().get(j).getCabinDetailList().size(); k++) {
                        if (mUtosearchresultHome.getIfsObject().get(i).getLegListObj().get(j).getCabinDetailList().get(k).isConfirmed()) {
                            selectedpassid.append("&isConfirmUpgradeSelected=");
                            selectedpassid.append(mUtosearchresultHome.getIfsObject().get(i).getLegListObj().get(j).getCabinDetailList().get(k).getStyleIdUpcabinConfirm());
                        }
                        if (mUtosearchresultHome.getIfsObject().get(i).getLegListObj().get(j).getCabinDetailList().get(k).isStandBy()) {
                            selectedpassid.append("&isSelected=");
                            selectedpassid.append(mUtosearchresultHome.getIfsObject().get(i).getLegListObj().get(j).getCabinDetailList().get(k).getStyleIdUpcabinStandBy());
                            if (mapSelectedPax != null)
                                selectedpassid.append(addPaxList(i, j, mapSelectedPax).toString());

                        }
                    }

                }
            }
            Log.d("selectedpassid", selectedpassid.toString());
        } catch (Exception ex) {
            Utils.ERROR("LegProductReviewFragment >> LoadReviewData : " + ex.getMessage().toString());
        }

        return selectedpassid;
    }

    private StringBuilder addPaxList(int ifsPos, int legPos, HashMap<String, ArrayList<String>> mapSelectedPax) {
        ArrayList<String> list = mapSelectedPax.get(ifsPos + "_" + legPos);

        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < list.size(); index++) {
            if (index == 0) {
                builder.append("_");
            }
            builder.append(list.get(index));
            if (index != list.size() - 1) {
                builder.append("*");
            }
        }


        return builder;
    }

    private void updatePassLevelPriceSummary(PriceSummary priceSummary) {
        PassLevelPriceSummary passSummary = null;
        try {
            passSummary = priceSummary.getPassLevelPriceSummary();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (passSummary != null) {
            lytUpgradePassCreditSummary.setVisibility(View.VISIBLE);
            setPassCreditRow(lytUpgradePassCreditSummaryRow, passSummary.getSelectedFlightDetail());

            txtUpCreditSummaryLable.setText(passSummary.getOpCreditSummaryHed());
            txtFlightLable.setText(passSummary.getFlightLabel());
            txtUpCreditRequiredLabel.setText(passSummary.getOpCreditSummaryTitle());
            txtTotalUpCreditRequired.setText(passSummary.getTotalCreditToRedeem() + "");
            txtTotalUpCreditRqrdLabel.setText(passSummary.getOpTotalRedTitle() + "");
        } else {
            lytUpgradePassCreditSummary.setVisibility(View.GONE);
        }
    }

    private void setPassCreditRow(LinearLayout lytUpgradePassCreditSummaryRow, List<SelectedFlightDetail> detailList) {

        if (((detailList != null) && (!detailList.isEmpty()))) {
            for (int index = 0; index < detailList.size(); index++) {
                LinearLayout linearLayout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.pass_level_summary_row, null, false);
                TextView txtFlight_detail = (TextView) linearLayout.findViewById(R.id.txtFlight_detail);
                OTTextView txtCreditRqrd = (OTTextView) linearLayout.findViewById(R.id.txtCreditRqrd);
                txtFlight_detail.setText(Html.fromHtml(detailList.get(index).getFlightDetail().toString()));
                txtCreditRqrd.setText("" + detailList.get(index).getCreditRequired());
                lytUpgradePassCreditSummaryRow.addView(linearLayout);
            }

        }
    }

    private void showMultiProduct(List<GetMTListDatum> mTListData) {
        for (int i = 0; i < mTListData.size(); i++) {
            imgMultiPro.setImageUrl(mTListData.get(i).getMtimage(), imageLoader);
            txtFreeSignupLabel.setText(mTListData.get(i).getMtHeadingLabel());
            txtNumberOfRow.setText(mTListData.get(i).getUpCabinName());
            cbSelect.setText(mTListData.get(i).getCurrencySymbol() + " " + "0" + "*");
            txtSelectAmount.setText(" " + mTListData.get(i).getCurrencySymbol() + " " + mTListData.get(i).getSaveAmount() + "*");
            txtInformationLabel.setText(mTListData.get(i).getMtFreeOfferLabel());
            txtSelectLabel.setText(mTListData.get(i).getSelectLabel());
            txtViewRulesLabel.setText(mTListData.get(i).getViewLabel());
            txtSaveLabel.setText(mTListData.get(i).getSaveLabel());
            selectedKey = mTListData.get(i).getSelectedKey();
            txtViewRulesLabel.setPaintFlags(txtViewRulesLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            viewMultiRules(mTListData.get(i).getMtESORules());

        }
    }

    private void viewMultiRules(MtESORules mtESORules) {
        List<Rule> ruleList = mtESORules.getRules();
        multiRuleArray = new ArrayList<>();
        for (int i = 1; i < ruleList.size(); i++) {
            rules = ruleList.get(i).getRule();
            multiRuleArray.add(rules);
        }
        Utils.layoutForRules(getActivity(), lytMultiRules, multiRuleArray);
    }


    private void reviewDetailData(ReviewDetails mReviewDetails) {

        txtUtoPriceSummaryLabel.setVisibility(Utils.isPassFlow(getActivity()) ? View.GONE : View.VISIBLE);
        lyt_head_table.setVisibility(Utils.isPassFlow(getActivity()) ? View.GONE : View.VISIBLE);

        scrollView.setVisibility(View.VISIBLE);

        txtContactInfoLabel.setText(mReviewDetails.getContactMessage());
        txtOtherPhoneLabel.setText(mReviewDetails.getLABLOtherPhoneLabel());
        txtEmailLabel.setText(mReviewDetails.getLABLContactEmailLabel() + "*");
        txtCellMobileLabel.setText(mReviewDetails.getLABLTelephonePrimaryLabel() + "*");

        PriceSummary priceSummary = mReviewDetails.getPriceSummary();
        txtUtoPriceSummaryLabel.setText(priceSummary.getPriceSummaryHeading());

        if (priceSummary.getIsPartialOn()) {
            lytHeaderThreeColumn.setVisibility(View.GONE);
            lytHeaderTwoColumn.setVisibility(View.GONE);
            lyPaxCount.setVisibility(View.GONE);
            lytTotalAmountPay.setVisibility(View.GONE);
            txtSignUpPriceInfo.setText("*" + priceSummary.getTgpInitialPriceLabel() + " " + priceSummary.getTgpInitialPriceHelpLabel());
            txtOptionPriceInfo.setText("*" + "Uprade price" + " " + priceSummary.getShiftPriceHelpLabel());
            txtTotalPriceLabel.setText(priceSummary.getLABL_Total_Price_Label());
            txtTotalPrice.setText(priceSummary.getDisplayCurrencySymbol() + " " + priceSummary.getTotalAmountToPay());
            totalAmountToPay = priceSummary.getTotalAmountToPay() + "";
        } else {
            lytTotalPrice.setVisibility(View.GONE);
            txtSignUpPriceInfo.setVisibility(View.GONE);
            txtOptionPriceInfo.setVisibility(View.GONE);
            UpdateMainFlowUI(priceSummary);

        }

        flightDetailList = priceSummary.getFlightDetail();

        List<FlightDetail> tempflightDetailList = new ArrayList<>();

        for (int i = 0; i < flightDetailList.size(); i++) {
            if (flightDetailList.get(i).getUpcabinMaxShiftPrice() != null) {
                tempflightDetailList.add(flightDetailList.get(i));
            }
        }

        mLinearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleViewReview.setLayoutManager(mLinearLayoutManager);
        adapter = new LegProductReviewFragmentAdapter(getActivity(), tempflightDetailList, priceSummary);
        recycleViewReview.setAdapter(adapter);

        PaymentDetail paymentDetail = mReviewDetails.getPaymentDetail();
        List<CardDetail> cardDetail = paymentDetail.getCardDetail();

        try {
            showPaypal = paymentDetail.getIsDisplayExpressCheckout();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        LoadPaymentTypes(cardDetail);


    }

    private void UpdateMainFlowUI(PriceSummary priceSummary) {

        if (priceSummary.getTgpInitialPriceHelpLabel() == null) {
            AppVariables.noOfColumn = "two";
            lytHeaderThreeColumn.setVisibility(View.GONE);
            lytHeaderTwoColumn.setVisibility(View.VISIBLE);
            txtFlightsLabelTwoColumn.setText(priceSummary.getLABLFlightLabel());
            txtUpgradePriceLabelTwoColumn.setText(priceSummary.getShiftPriceHeadingLabel() + "\n" + priceSummary.getShiftPriceHelpLabel());
        } else {
            AppVariables.noOfColumn = "three";
            lytHeaderThreeColumn.setVisibility(View.VISIBLE);
            lytHeaderTwoColumn.setVisibility(View.GONE);
            txtFlightsLabel.setText(priceSummary.getLABLFlightLabel());
            txtSignUpPriceLabel.setText(priceSummary.getTgpInitialPriceLabel() + " " + priceSummary.getProductLabel());
            txtSignUpPriceRefundLabel.setText(priceSummary.getTgpInitialPriceHelpLabel());
            txtUpgradePriceLabel.setText(priceSummary.getShiftPriceHeadingLabel());
            txtUpgradePriceRefundLabel.setText(priceSummary.getShiftPriceHelpLabel());

        }

        txtEligiblePassengerLabel.setText(priceSummary.getLABLPaxCountLabel());
        txtNoOfPassenger.setText(priceSummary.getPaxCount() + "");
        txtTotalAmountToPayLabel.setHtml(priceSummary.getPurchaseAmountTopayLabel());
        txtTotalAmountToPay.setText(priceSummary.getDisplayCurrencySymbol() + " " + priceSummary.getTotalAmountToPay());

        totalAmountToPay = priceSummary.getTotalAmountToPay() + "";
    }


    private void ValidateFields() {

        email = edtEmail.getText().toString().trim();
        primaryNumber = edtCellMobile.getText().toString().trim();
        otherNumber = edtOtherPhone.getText().toString().trim();
        checked = chkTerms.isChecked();
        String pMobileCC = Utils.getCountryIdExt(countryExtData.getData(), txtMobileCC.getText().toString().trim());

        primaryCc = Utils.getCountryIdExt(countryExtData.getData(), txtMobileCC.getText().toString().trim());
        otherCc = Utils.getCountryIdExt(countryExtData.getData(), txtOtherPhoneCode.getText().toString().trim());
        Utils.DEBUG("PCountry Name >> " + txtMobileCC.getText().toString().trim());
        Utils.DEBUG("Other Mobile CC >> " + otherCc);
        Utils.DEBUG("primaryCc Mobile CC >> " + pMobileCC);
        Utils.DEBUG("cardCategory Id >> " + cardCategoryId);


        if (email.equals("")) {
            edtEmail.setError(getString(R.string.string_enter_your_email_address));
            edtEmail.requestFocus();
            // Utils.showToast(getActivity(), getString(R.string.string_enter_your_email_address));
        } else if (primaryNumber.equals("")) {
            edtCellMobile.requestFocus();
            //  Utils.showToast(getActivity(), getString(R.string.string_enter_mobile_number));
            edtCellMobile.setError(getString(R.string.string_enter_mobile_number));
        } else if (!(Utils.isValidEmail(email))) {
            edtEmail.setError(getString(R.string.string_enter_valid_email));
            edtEmail.requestFocus();
        } else if (!checked && rlytCheckBox.getVisibility() == View.VISIBLE) {
            Snackbar.make(lytLegProductReview, (getString(R.string.you_must_accept_rules_and_terms_of_service)),
                    Snackbar.LENGTH_SHORT).show();

        } else {
            ReviewDataSend sendData = new ReviewDataSend();
            sendData.setEmail(email);
            sendData.setPrimaryCode(primaryCc);
            sendData.setOthercode(otherCc);
            sendData.setPrimaryMainPart(primaryNumber);
            sendData.setOtherMainPart(otherNumber);
            sendData.setCardCategoryId(cardCategoryId);
            sendData.setExpress(isExpressCheckout);
            sendData.setTotalAmountToPay(totalAmountToPay);

            sendData.setSelectedKey(cbSelect.isChecked() ? selectedKey : "");
            sendData.setIsBankAccount("0");

            if (Utils.isPassFlow(getActivity())) {
                sendData.setConfirmPage(true);
                Utils.moveToFragment(getActivity(), new LegProductViewDetailsFragment(), sendData, 0);

            } else {

                Utils.moveToFragment(getActivity(), new LegWebViewSummaryFragment(), sendData, 0);

            }
        }
    }

    private void loadRulesData() {

        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_ItineraryViewDetail);
        JSONObject requestObject = new JSONObject();
        try {

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
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        ViewDetails mViewDetails = ParseManager.getInstance().fromJSON(response, ViewDetails.class);


                        UtoRules utoRules = null;
                        try {
                            utoRules = mViewDetails.getUtoRules();
                            rulesData(utoRules);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), "Server Timeout");


            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void rulesData(UtoRules utoRules) {
        String rules = "";
        String mrules = "";
        String nRules = "";
        List<Rule> ruleList = utoRules.getRules();
        title = ruleList.get(0).getTitle();
        //txtUToRulesLabel.setText(title);
        ruleArray = new ArrayList<>();
        for (int i = 1; i < ruleList.size(); i++) {
            rules = ruleList.get(i).getRule();
            nRules = rules + "<br />";
            String split[] = nRules.split("##");

            StringBuilder b = new StringBuilder();
            for (int index = 0; index < split.length; index++) {
                if (index != 0) {
                    b.append("<br />");
                    b.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>" + split[index].substring(0, 2) + "</b>" + split[index].substring(2, split[index].length()));
                } else {
                    b.append(split[index]);
                }

            }
            mrules = b.toString();
            //mrules = nRules.replace("##", "<br />");
            //mrules = "<b>" + mrules.substring(0, 2) + "</b>" + mrules.substring(2, mrules.length());
            //nUtoRules =  utoRules.replace("##","\n\t\t");
            ruleArray.add(mrules);

        }


    }

    private void showRulesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.leg_prod_rules, null);
        OTTextView txtTitle = (OTTextView) dialogView.findViewById(R.id.txtRulesTitle);
        lytRules = (LinearLayout) dialogView.findViewById(R.id.lytRules);
        builder.setView(dialogView);
        txtTitle.setText(title);
        Utils.layoutForRules(getActivity(), lytRules, ruleArray);
        // builder.setMessage()
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                    }
                });
        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    private void backSearchData(BackSearchData mBackSearchData) {
        if (mBackSearchData == null) {
            return;
        }
        lastName = mBackSearchData.getLastName();
        productID = mBackSearchData.getTgProductId() + "";
        OCN = mBackSearchData.getOCN();
        isSearchBy = mBackSearchData.getIsSearchBy();
        MarketingAirlineId = mBackSearchData.getMarketingAirlineId() + "";
        CountryId = mBackSearchData.getCountryId() + "";
        pnr = mBackSearchData.getPnr();
        LanguageId = mBackSearchData.getLanguageId() + "";
        loader.dismiss();
    }

    @Override
    public void onBackEventPre() {
        if (legReviewHome.getReviewDetails().getPriceSummary().getBackSearchData() != null)
            Utils.backSearchApiCall(getActivity(), productID, LanguageId, CountryId, MarketingAirlineId, pnr, lastName, isSearchBy, OCN, "", "", "", "", "", "", "", "", "", "");
    }

    @Override
    public void onFocusEvent() {

    }


    private void setVisibilityContact(int type) {

        txtContactInfoLabel.setVisibility(type);
        txtEmailLabel.setVisibility(type);
        txtCellMobileLabel.setVisibility(type);
        txtOtherPhoneLabel.setVisibility(type);

        edtEmail.setVisibility(type);
        txtMobileCC.setVisibility(type);
        edtCellMobile.setVisibility(type);
        txtOtherPhoneCode.setVisibility(type);
        edtOtherPhone.setVisibility(type);

    }


}







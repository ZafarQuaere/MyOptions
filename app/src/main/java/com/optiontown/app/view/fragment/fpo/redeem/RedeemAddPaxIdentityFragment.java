package com.optiontown.app.view.fragment.fpo.redeem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.AddPaxIdentityData;
import com.optiontown.app.model.review.FfpNumberData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPAddUserFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by parasmani.sharma on 29/09/2016.
 */
public class RedeemAddPaxIdentityFragment extends BaseFragment {

    private View view;
    private OTTextView txt_idcardtype;
    private OTEditText edt_idcardnumber;
    private OTTextView txt_countryissuance;
    private OTTextView txtContinue;
    private LinearLayout lytIdCardType;
    private LinearLayout lytCountryIssuance;
    private AddPaxIdentityData paramAddPaxInfo;
    private OTEditText edt_cityofissuance;
    private int eYear;
    private int eMonth;
    private int eDay;
    private String monthName;
    private OTEditText edtExpirationDate;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private String expYear;
    private String expMonth;
    private String expDay;
    private String cardSelectedId;
    private FragmentActivity fragmentActivity;
    private Communicator communicator;
    private String countrySelectedId;
    private TextInputLayout lyt_ExpirationDate;
    private TextInputLayout lay_edtClubMembershipNumb;
    private OTEditText edtClubMembershipNumb;
    private ArrayList<String> listError;
    private String errorMsg;
    private OTTextView txtIdentityProofLabel;
    private InternationalizeData localization;
    private OTTextView txt_label_countryissuance;
    private OTTextView txtMandatoryLabel;
    private TextInputLayout lay_idcardnumber;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_redeem_add_pax_indentity, container, false);

        paramAddPaxInfo = (AddPaxIdentityData) getArguments().getSerializable(getString(R.string.key_serializable));
        System.out.println("fname <<<<<<<<<<<<<<   " + paramAddPaxInfo.getFName().toString());

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        //redeemSearchResultData =  paramAddPaxInfo.getRedeemSearchResultData();

        getUiReference();


        return view;
    }

    private void getUiReference() {

        txtIdentityProofLabel = (OTTextView) view.findViewById(R.id.txtIdentityProofLabel);
        txtIdentityProofLabel.setText(localization.getLABL_ID_Identity_Proof_Label());

        lay_edtClubMembershipNumb = (TextInputLayout) view.findViewById(R.id.lay_edtClubMembershipNumb);
        edtClubMembershipNumb = (OTEditText) view.findViewById(R.id.edtClubMembershipNumb);
        if (paramAddPaxInfo != null) {
            if (paramAddPaxInfo.getIsDisplayFfpNumber() == 1) {
                lay_edtClubMembershipNumb.setVisibility(View.VISIBLE);
                if (paramAddPaxInfo.getFFPnumberMandatory() == 1) {
                    lay_edtClubMembershipNumb.setHint(paramAddPaxInfo.getFFpnumberHelpMessage() + "*");
                } else {
                    lay_edtClubMembershipNumb.setHint(paramAddPaxInfo.getFFpnumberHelpMessage());
                }
            } else {
                lay_edtClubMembershipNumb.setVisibility(View.GONE);
            }
        }

        lyt_ExpirationDate = (TextInputLayout) view.findViewById(R.id.lyt_ExpirationDate);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        lytIdCardType = (LinearLayout) view.findViewById(R.id.lytIdCardType);
        lytCountryIssuance = (LinearLayout) view.findViewById(R.id.lytCountryIssuance);

        final ScrollView svIdCardType = (ScrollView) view.findViewById(R.id.svIdCardType);
        final ScrollView svCountryIssuance = (ScrollView) view.findViewById(R.id.svCountryIssuance);

        view.findViewById(R.id.svParent).setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                svIdCardType.getParent().requestDisallowInterceptTouchEvent(false);
                svCountryIssuance.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        Utils.setInterceptTouchEvent(new View[]{svIdCardType, svCountryIssuance});

        view.findViewById(R.id.lytMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svIdCardType.setVisibility(View.GONE);
                svCountryIssuance.setVisibility(View.GONE);
            }
        });

        txt_idcardtype = (OTTextView) view.findViewById(R.id.txt_idcardtype);
        txt_idcardtype.setHint(localization.getLABL_ID_Card_Type_Label() + "*");
        lay_idcardnumber = (TextInputLayout) view.findViewById(R.id.lay_idcardnumber);
        lay_idcardnumber.setHint(localization.getLABL_ID_Card_Number_Label()+ "*");
        edt_idcardnumber = (OTEditText) view.findViewById(R.id.edt_idcardnumber);
        //edt_idcardnumber.setHint(localization.getLABL_ID_Card_Number_Label()+ "*");
        txt_label_countryissuance = (OTTextView) view.findViewById(R.id.txt_label_countryissuance);
        txt_label_countryissuance.setHint(localization.getLABLPNRSearchByFlightPhoneCountryCodeLabel());
        txt_countryissuance = (OTTextView) view.findViewById(R.id.txt_countryissuance);
        txt_countryissuance.setHint(localization.getLABLPNRSearchByFlightPhoneCountryCodeLabel()+"*");
        edt_cityofissuance = (OTEditText) view.findViewById(R.id.edt_cityofissuance);

        lyt_ExpirationDate  = (TextInputLayout) view.findViewById(R.id.lyt_ExpirationDate);
        lyt_ExpirationDate.setHint(localization.getLABL_Credit_Card_Expiration_Label()+ "*");
        edtExpirationDate = (OTEditText) view.findViewById(R.id.edtExpirationDate);
        //edtExpirationDate.setHint(localization.getLABL_Credit_Card_Expiration_Label()+ "*");

        txtMandatoryLabel = (OTTextView) view.findViewById(R.id.txtMandatoryLabel);
        txtMandatoryLabel.setText( "*" +  localization.getLABLMandatoryFieldWithoutAsteriskLabel());


        txtContinue = (OTTextView) view.findViewById(R.id.txtSave);
        txtContinue.setText(localization.getLABL_Continue_Button_Label());
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                listError = validateAllInputsCommon();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (paramAddPaxInfo.getIsDisplayFfpNumber() == 1) {
                            if (paramAddPaxInfo.getFFPnumberMandatory() == 1 || (paramAddPaxInfo.getFFPnumberMandatory() == 0)) {
                                //check ffp number valid or not
                                try {
                                    callToCheckFFPNumberValidity(edtClubMembershipNumb.getText().toString().trim());
                                } catch (Exception e) {
                                }
                            }
                        } else {

                            //ffp number not required
                            if (listError.size() > 0) {
                                //first clear all previos error message view
                                lytErrorMessage.removeAllViews();
                                lytError.setVisibility(View.VISIBLE);

                                //show error message
                                for (int index = 0; index < listError.size(); index++) {
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                                }
                            } else {
                                try {
                                    paramAddPaxInfo.setIDCardType(cardSelectedId);
                                    paramAddPaxInfo.setIDCardNumber(edt_idcardnumber.getText().toString());
                                    paramAddPaxInfo.setIDCountry(countrySelectedId);
                                    paramAddPaxInfo.setPIssuePlace(edt_cityofissuance.getText().toString());
                                    paramAddPaxInfo.setEXPDay(expDay);
                                    paramAddPaxInfo.setEXPMonth(expMonth);
                                    paramAddPaxInfo.setEXPYear(expYear);
                                    paramAddPaxInfo.setCountryListArray(null);
                                    paramAddPaxInfo.setCardTypeArray(null);
                                    if (paramAddPaxInfo.isUpgradRequired() == true) {
                                        communicateToFragment(paramAddPaxInfo);
                                        ((FragmentActivity) getActivity()).onBackPressed();

                                    } else {
                                        communicateToFragment(paramAddPaxInfo);
                                        ((FragmentActivity) getActivity()).onBackPressed();
                                        ((FragmentActivity) getActivity()).onBackPressed();
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }, 200);
            }
        });

        txt_idcardtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] cardData = new String[paramAddPaxInfo.getCardTypeArray().size() - 1];
                for (int i = 1; i < paramAddPaxInfo.getCardTypeArray().size(); i++) {
                    cardData[i - 1] = paramAddPaxInfo.getCardTypeArray().get(i).getLabel();
                }

                createDropdownView(cardData, lytIdCardType, txt_idcardtype, new LinearLayout[]{lytCountryIssuance});
            }
        });

        txt_countryissuance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] countryList = new String[paramAddPaxInfo.getCountryListArray().size()];
                for (int i = 0; i < paramAddPaxInfo.getCountryListArray().size(); i++) {
                    countryList[i] = paramAddPaxInfo.getCountryListArray().get(i).getLabel();
                }
                createDropdownView(countryList, lytCountryIssuance, txt_countryissuance, new LinearLayout[]{lytIdCardType});
            }
        });

        edtExpirationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();

            }
        });

        edtExpirationDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    datePicker();

                }
            }

        });


        //if upgrade is not require but ffp number is mendatory
        //set all data available in all respective fields

        if (paramAddPaxInfo != null) {

            if(paramAddPaxInfo.getIDCardNumber() != null)
            {
                if (paramAddPaxInfo.getIDCardNumber().equals("1")) {
                    //if id card number value is default "1" from server then editable is required
                    try {

                    /*txt_idcardtype.setText(paramAddPaxInfo.getIDCardType());
                    edt_idcardnumber.setText(paramAddPaxInfo.getIDCardNumber().toString());
                    txt_countryissuance.setText(paramAddPaxInfo.getIDCountry().toString());
                    edtExpirationDate.setText(paramAddPaxInfo.getEXPDay() + " " + paramAddPaxInfo.getEXPMonth() + " " + paramAddPaxInfo.getEXPYear() + "");

                    cardSelectedId = paramAddPaxInfo.getIDCardType();
                    countrySelectedId = paramAddPaxInfo.getIDCountry().toString();
                    expDay = paramAddPaxInfo.getEXPDay();
                    expMonth = paramAddPaxInfo.getEXPMonth();
                    expYear = paramAddPaxInfo.getEXPYear();*/

                        if (!paramAddPaxInfo.getFFPnumber().toString().equals("")) {
                            edtClubMembershipNumb.setText(paramAddPaxInfo.getFFPnumber().toString());
                            edtClubMembershipNumb.setEnabled(false);
                        }
                    } catch (Exception e) {
                    }
                }
            } else {

                try {
                    if (paramAddPaxInfo.getIDCardType() != null) {
                        txt_idcardtype.setText(paramAddPaxInfo.getIDCardType());
                        txt_idcardtype.setEnabled(false);
                        cardSelectedId = paramAddPaxInfo.getIDCardType();
                    }
                    if (paramAddPaxInfo.getIDCardNumber() != null) {
                        edt_idcardnumber.setText(paramAddPaxInfo.getIDCardNumber().toString());
                        edt_idcardnumber.setEnabled(false);
                    }
                    if (paramAddPaxInfo.getIDCountry() != null) {
                        txt_countryissuance.setText(paramAddPaxInfo.getIDCountry().toString());
                        txt_countryissuance.setEnabled(false);
                        countrySelectedId = paramAddPaxInfo.getIDCountry().toString();
                    }
                    if (paramAddPaxInfo.getEXPDay() != null && paramAddPaxInfo.getEXPMonth() != null && paramAddPaxInfo.getEXPYear() != null) {
                        edtExpirationDate.setText(paramAddPaxInfo.getEXPDay() + " " + paramAddPaxInfo.getEXPMonth() + " " + paramAddPaxInfo.getEXPYear() + "");
                        edtExpirationDate.setEnabled(false);
                        expDay = paramAddPaxInfo.getEXPDay();
                        expMonth = paramAddPaxInfo.getEXPMonth();
                        expYear = paramAddPaxInfo.getEXPYear();
                    }
                    if (!paramAddPaxInfo.getFFPnumber().toString().equals("")) {
                        edtClubMembershipNumb.setText(paramAddPaxInfo.getFFPnumber().toString());
                        edtClubMembershipNumb.setEnabled(false);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    private void datePicker() {
        Utils.hideKeyboard(getActivity(), edtExpirationDate);
        final Calendar c = Calendar.getInstance();
        eYear = c.get(Calendar.YEAR);
        eMonth = c.get(Calendar.MONTH);
        eDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {
                        // Display Selected date in textbox
                        expDay = (checkDigit(dayOfMonth));
                        expMonth = (checkDigit(monthOfYear + 1));
                        expYear = String.valueOf(year);
                        String dob = (checkDigit(dayOfMonth) + 7 + "-" + checkDigit(monthOfYear + 1) + "-" + year);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                        String dateInString = dob;
                        Date date = null;
                        try {

                            date = formatter.parse(dateInString);
                            System.out.println(date);
                            System.out.println(formatter.format(date));

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String monthname = (String) android.text.format.DateFormat.format("MMMM", date);
                        monthName = (String) android.text.format.DateFormat.format("MMM", date);
                        edtExpirationDate.setText(checkDigit(dayOfMonth) + "  " + monthName + "  " + year);
                    }
                }, eYear, eMonth, eDay);

        dpd.show();
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


    }

    @Override
    public void onBackEventPost() {
        super.onBackEventPost();
        Utils.DEBUG("onBackEventPost() called");
        if (paramAddPaxInfo.isUpgradRequired()) {

            FragmentCommunicationData data = new FragmentCommunicationData();
            data.setFragmentName((new RedeemAddPassengerFragment()).getClass().getName());
            data.setFlagRollbackAddPaxUI(true);
            communicator.onResponse(data);
        }
    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("RedeemAddPaxIdentityFragment >> onAttach(Activity) called");
        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }


    private void communicateToFragment(AddPaxIdentityData addUserData) {
        FragmentCommunicationData data = new FragmentCommunicationData();

        if (addUserData.isMmpUserAdditionCase() == true) {
            data.setFragmentName((new MMPAddUserFragment()).getClass().getName());
        } else {
            data.setFragmentName((new RedeemAddPassengerFragment()).getClass().getName());
        }

        data.setAddPaxCompleteData(addUserData);
        communicator.onResponse(data);
    }

    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();

        if (Utils.compareDefaultValues(txt_idcardtype, "")) {
            listError.add(localization.getLABL_Err_Select_Card_Type_Label());
        }

        /*if (txt_idcardtype.getText().toString().equals(paramAddPaxInfo.getCardTypeArray().get(0).getLabel())) {
            listError.add("Select Specific Card Type");
        }*/

        if (Utils.compareDefaultValues(edt_idcardnumber, "")) {
            listError.add(localization.getLABL_Err_Enter_Id_Number_Label());
        }

        if (Utils.compareDefaultValues(txt_countryissuance, "")) {
            listError.add(localization.getLABLMasterUKLAOAvailableSelectLabel() + " " + localization.getLABLPNRSearchByFlightPhoneCountryCodeLabel());
        }

        /*if (Utils.compareDefaultValues(edt_cityofissuance, "")) {
            listError.add("Enter City");
        }*/

        if (Utils.compareDefaultValues(edtExpirationDate, "")) {
            listError.add(localization.getLABL_Err_Enter_Expiration_Date_Label());
        }


        if (paramAddPaxInfo != null) {
            if (paramAddPaxInfo.getFFPnumberMandatory() == 1 && Utils.compareDefaultValues(edtClubMembershipNumb, "")) {
                listError.add(paramAddPaxInfo.getFFpnumberErrorMessage());
            }

        }

        /*String date = txtExpirationDate.getText().toString();
        String[] parts = date.split(" - ");
        String part1 = parts[0];
        String part2 = parts[1];

        if (part2.isEmpty() || part2.length()<1) {
            listError.add("Enter Expiration Date");
        }*/


        return listError;
    }

    public String checkDigit(int number) {

        return number <= 9 ? "0" + number : String.valueOf(number);

    }

    public void createDropdownView(final String[] aryDropdownOption, final LinearLayout lytParent, final OTTextView selectedView, LinearLayout aryOtherdropdown[]) {

        hideKeyboard(lytParent.getContext(), lytParent.getRootView());
        if (lytParent.getChildCount() == 0) {
            //first dismiss rest dropdowns
            for (int index = 0; index < aryOtherdropdown.length; index++) {
                if (aryOtherdropdown[index] != null) {
                    ((LinearLayout) aryOtherdropdown[index]).removeAllViews();
                    ((View) ((LinearLayout) aryOtherdropdown[index]).getParent()).setVisibility(View.GONE);
                }
            }

            for (int index = 0; index < aryDropdownOption.length; index++) {
                OTTextView textView = new OTTextView(lytParent.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins((int) Utils.conertDpToPixel(lytParent.getContext(), 10), (int) Utils.conertDpToPixel(lytParent.getContext(), 10), 0, (int) Utils.conertDpToPixel(lytParent.getContext(), 10));
                textView.setText(aryDropdownOption[index]);
                textView.setTextSize(Utils.convertPixelToDp(lytParent.getContext(), lytParent.getContext().getResources().getDimension(R.dimen.size_font_13)));
                textView.setTextColor(lytParent.getContext().getResources().getColor(R.color.color_font_black));
                textView.setTypeface(null, Typeface.NORMAL);

                final int finalIndex = index;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedView.setText(aryDropdownOption[finalIndex]);
                        ((View) (lytParent).getParent()).setVisibility(View.GONE);

                        if (selectedView.getId() == R.id.txt_idcardtype) {
                            for (int i = 0; i < paramAddPaxInfo.getCardTypeArray().size(); i++) {
                                if (paramAddPaxInfo.getCardTypeArray().get(i).getLabel().equals(aryDropdownOption[finalIndex])) {
                                    cardSelectedId = paramAddPaxInfo.getCardTypeArray().get(i).getValue().toString();
                                    break;
                                }

                            }
                        } else if (selectedView.getId() == R.id.txt_countryissuance) {
                            for (int i = 0; i < paramAddPaxInfo.getCountryListArray().size(); i++) {
                                if (paramAddPaxInfo.getCountryListArray().get(i).getLabel().equals(aryDropdownOption[finalIndex])) {
                                    countrySelectedId = paramAddPaxInfo.getCountryListArray().get(i).getValue().toString();
                                    break;
                                }

                            }
                        }


                    }
                });

                lytParent.addView(textView, params);
            }
            ((View) (lytParent).getParent()).setVisibility(View.VISIBLE);
        } else {
            ((View) (lytParent).getParent()).setVisibility(((View) (lytParent).getParent()).getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }

        Utils.calculateHeight(aryDropdownOption, lytParent);
    }


    public static void hideKeyboard(Context context, View view) {

        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private void callToCheckFFPNumberValidity(final String ffpNumber) {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FfpValidation) + "&isSearchForm=1";


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("FFPNumber", ffpNumber);
            requestObject.put("isSignUp", "0");
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
                        onSuccessfulResponse(response);
                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.string_common_error_message));
                loader.dismiss();
                //onSuccessfulResponse(new JSONObject( ));
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void onSuccessfulResponse(JSONObject response) {
        /*try {
            response = (new JSONObject(Utils.readFromAssets(getActivity(), "FfpValidation.txt")));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        if (response == null) {
            return;
        }
        Utils.DEBUG("onResponse() called : " + response.toString());

        FfpNumberData data = ParseManager.getInstance().fromJSON(response, FfpNumberData.class);

        //all ok
        if (data.getIsFFPValid() == 1) {
            edtClubMembershipNumb.setText(data.getUpdatedFfp().toString());
        } else {
            if (!listError.contains(paramAddPaxInfo.getFFpnumberErrorMessage())) {
                errorMsg = data.getMessage().toString();
                listError.add(errorMsg);
            }
        }


        if (listError.size() > 0) {
            //first clear all previos error message view
            lytErrorMessage.removeAllViews();
            lytError.setVisibility(View.VISIBLE);

            //show error message
            for (int index = 0; index < listError.size(); index++) {
                lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
            }
        } else {
            try {
                if (data.getIsFFPValid() == 1) {
                    paramAddPaxInfo.setIDCardType(cardSelectedId);
                    paramAddPaxInfo.setIDCardNumber(edt_idcardnumber.getText().toString());
                    paramAddPaxInfo.setIDCountry(countrySelectedId);
                    paramAddPaxInfo.setPIssuePlace(edt_cityofissuance.getText().toString());
                    paramAddPaxInfo.setEXPDay(expDay);
                    paramAddPaxInfo.setEXPMonth(expMonth);
                    paramAddPaxInfo.setEXPYear(expYear);
                    paramAddPaxInfo.setCountryListArray(null);
                    paramAddPaxInfo.setCardTypeArray(null);
                    paramAddPaxInfo.setFFPnumber(edtClubMembershipNumb.getText().toString());
                    if (paramAddPaxInfo.isUpgradRequired() == true) {
                        communicateToFragment(paramAddPaxInfo);
                        ((FragmentActivity) getActivity()).onBackPressed();

                    } else {
                        communicateToFragment(paramAddPaxInfo);
                        ((FragmentActivity) getActivity()).onBackPressed();
                        ((FragmentActivity) getActivity()).onBackPressed();
                    }
                }
            } catch (Exception e) {
            }
        }
    }


}


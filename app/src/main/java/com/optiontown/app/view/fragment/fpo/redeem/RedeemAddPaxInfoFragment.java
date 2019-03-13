package com.optiontown.app.view.fragment.fpo.redeem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.AddPaxIdentityData;
import com.optiontown.app.model.redeem.RedeemSearchResultData;
import com.optiontown.app.model.redeem.mmp.AddUserMmpData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

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
public class RedeemAddPaxInfoFragment extends BaseFragment {

    private View view;
    private OTTextView txtPrefix;
    private OTEditText edtFirstName;
    private OTEditText edtMiddleName;
    private OTEditText edtLastName;
    private OTEditText edtEmail;
    private OTEditText edtMobileNo;
    private OTTextView txtCountryCode;
    private OTEditText edtDateofBirth;
    private OTEditText edtClubMembershipNumb;
    private OTTextView txtContinue;
    private LinearLayout lytPrefix;
    private LinearLayout lytCountryCode;
    InternationalizeData localization;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytError;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String monthName;
    private RedeemSearchResultData redeemSearchResultData;
    private String phoneExtValue = "+2";//Afghanistan
    private String prefixId;
    private TextInputLayout lay_edtClubMembershipNumb;
    private int monthSelected;
    private int yearSelected;
    private int daySelected;
    private TextInputLayout lyt_DateOfBirth;
    AddPaxIdentityData param = new AddPaxIdentityData();
    private AddUserMmpData addUserMmpdata;
    private TextInputLayout lay_edtFirstName;
    private TextInputLayout lay_edtMiddleName;
    private TextInputLayout lay_edtLastName;
    private TextInputLayout lay_edtEmail;
    private OTTextView txtPersonalInformationLabel;
    private OTTextView txtlabelcellmobile;
    private OTTextView txtMandatoryLabel;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_redeem_add_pax_info, container, false);
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        try {
            redeemSearchResultData = (RedeemSearchResultData) getArguments().getSerializable(getString(R.string.key_serializable));

            param.setMmpUserAdditionCase(false);

        }catch (Exception e){
            if(redeemSearchResultData == null)
            {
                addUserMmpdata = (AddUserMmpData) getArguments().getSerializable(getString(R.string.key_serializable));
                param.setMmpUserAdditionCase(true);

            }else {
                param.setMmpUserAdditionCase(false);
            }
        }

        getUiReference();

        return view;
    }

    private void getUiReference() {

        txtMandatoryLabel = (OTTextView) view.findViewById(R.id.txtMandatoryLabel);
        txtMandatoryLabel.setText( "*" +  localization.getLABLMandatoryFieldWithoutAsteriskLabel());
        txtPersonalInformationLabel = (OTTextView) view.findViewById(R.id.txtPersonalInformationLabel);
        txtPersonalInformationLabel.setText(localization.getLABLPersonalInformationLabel());
        lay_edtFirstName = (TextInputLayout)view.findViewById(R.id.lay_edtFirstName);
        lay_edtMiddleName = (TextInputLayout)view.findViewById(R.id.lay_edtMiddleName);
        lay_edtLastName = (TextInputLayout)view.findViewById(R.id.lay_edtLastName);
        lay_edtEmail = (TextInputLayout)view.findViewById(R.id.lay_edtEmail);
        lyt_DateOfBirth = (TextInputLayout)view.findViewById(R.id.lyt_DateOfBirth);
        txtPrefix = (OTTextView)view.findViewById(R.id.txtPrefix);
        edtFirstName = (OTEditText)view.findViewById(R.id.edtFirstName);
        edtMiddleName = (OTEditText)view.findViewById(R.id.edtMiddleName);
        edtLastName = (OTEditText)view.findViewById(R.id.edtLastName);
        edtEmail = (OTEditText)view.findViewById(R.id.edtEmail);
        edtMobileNo = (OTEditText)view.findViewById(R.id.edtMobileNo);
        txtCountryCode = (OTTextView)view.findViewById(R.id.txtCountryCode);
        edtDateofBirth = (OTEditText) view.findViewById(R.id.edtDateofBirth);
        edtClubMembershipNumb = (OTEditText)view.findViewById(R.id.edtClubMembershipNumb);
        txtlabelcellmobile = (OTTextView)view.findViewById(R.id.txtlabelcellmobile);
        txtlabelcellmobile.setText(localization.getLABLCellMobileLabel());
        txtContinue = (OTTextView)view.findViewById(R.id.txtContinue);
        txtContinue.setText(localization.getLABL_Continue_Button_Label());

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

        lytPrefix = (LinearLayout) view.findViewById(R.id.lytPrefix);
        lytCountryCode = (LinearLayout) view.findViewById(R.id.lytCountryCode);
        lay_edtClubMembershipNumb = (TextInputLayout) view.findViewById(R.id.lay_edtClubMembershipNumb);

        /*if(redeemSearchResultData!=null)
        {
            if (redeemSearchResultData.getIsDisplayFFPNumber() == 1)
            {
                lay_edtClubMembershipNumb.setVisibility(View.VISIBLE);
                if(redeemSearchResultData.getFFPNumberMandatory()==1) {
                    lay_edtClubMembershipNumb.setHint(redeemSearchResultData.getFFpnumberHelpMessage() + "*");
                }else {
                    lay_edtClubMembershipNumb.setHint(redeemSearchResultData.getFFpnumberHelpMessage());
                }
            }
            else
            {
                lay_edtClubMembershipNumb.setVisibility(View.GONE);
            }
        }else {

            if (addUserMmpdata.getIsDisplayFFPNumber() == 1)
            {
                lay_edtClubMembershipNumb.setVisibility(View.VISIBLE);

                if(addUserMmpdata.getFFPNumberMandatory()==1) {
                    lay_edtClubMembershipNumb.setHint(addUserMmpdata.getFFpnumberHelpMessage() + "*");
                }else {
                    lay_edtClubMembershipNumb.setHint(addUserMmpdata.getFFpnumberHelpMessage());
                }
            }
            else
            {
                lay_edtClubMembershipNumb.setVisibility(View.GONE);
            }

        }*/

        getOnClickEvents();
        localizingUI();
    }

    private void localizingUI() {
        txtPrefix.setHint(localization.getLABLPrefixLabel()+"*");
        lay_edtFirstName.setHint(localization.getLABLFirstNameLabel()+"*");
        lay_edtMiddleName.setHint(localization.getLABLMiddleNameLabel());
        lay_edtLastName.setHint(localization.getLABLLastNameLabel()+"*");
        lay_edtEmail.setHint(localization.getLABLEmailLabel()+"*");
        lyt_DateOfBirth.setHint(localization.getLABLDateOfBirthLabel()+"*");

        txtCountryCode.setHint(localization.getLABLChangeCountryLabel());
        edtMobileNo.setHint(localization.getLABLTelephoneNumberLabel()+"*");
    }

    private void getOnClickEvents() {

        final ScrollView svPrefix = (ScrollView) view.findViewById(R.id.svPrefix);
        final ScrollView svCountryCode = (ScrollView) view.findViewById(R.id.svCountryCode);

        view.findViewById(R.id.svParent).setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event)
            {
                svPrefix.getParent().requestDisallowInterceptTouchEvent(false);
                svCountryCode.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        Utils.setInterceptTouchEvent(new View[]{svPrefix, svCountryCode});

        view.findViewById(R.id.lytMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svPrefix.setVisibility(View.GONE);
                svCountryCode.setVisibility(View.GONE);
            }
        });


        edtDateofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();

            }
        });

        edtDateofBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    datePicker();

                }
            }

        });



        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.DEBUG("txtContinue Clicked :>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                //first clear all previos error message view
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                ArrayList<String> listError = validateAllInputsCommon();
                if(listError.size() > 0)
                {
                    lytError.setVisibility(View.VISIBLE);
                    //show error message
                    for (int index = 0; index < listError.size(); index++)
                    {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }
                }
                else {

                    setUiAndJSON();
                    //all ok except FFP number
                   /* if(redeemSearchResultData != null)
                    {
                        if(redeemSearchResultData.getFFPNumberMandatory() == 1
                                || (redeemSearchResultData.getFFPNumberMandatory() == 0 && edtClubMembershipNumb.getText().toString().trim().length() > 0))
                        {
                            //check ffp number valid or not
                            callToCheckFFPNumberValidity(edtClubMembershipNumb.getText().toString().trim());
                        }
                        else
                        {
                            setUiAndJSON();
                        }
                    }
                    else
                    {
                        if(addUserMmpdata.getFFPNumberMandatory() == 1
                                || (addUserMmpdata.getFFPNumberMandatory() == 0 && edtClubMembershipNumb.getText().toString().trim().length() > 0))
                        {
                            //check ffp number valid or not
                            callToCheckFFPNumberValidity(edtClubMembershipNumb.getText().toString().trim());
                        }
                        else
                        {
                            setUiAndJSON();
                        }
                    }*/

                }
            }
        });

        txtPrefix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(redeemSearchResultData!=null)
                {
                    String[] prefixLabel = new String[redeemSearchResultData.getPrefixArray().size()-1];
                    for(int i = 1 ; i< redeemSearchResultData.getPrefixArray().size(); i++)
                    {
                        prefixLabel[i-1] = redeemSearchResultData.getPrefixArray().get(i).getLabel();
                    }

                    createDropdownView(prefixLabel, lytPrefix, txtPrefix, new LinearLayout[]{lytCountryCode});
                }else {
                    String[] prefixLabel = new String[addUserMmpdata.getPrefixArray().size()-1];
                    for(int i = 1 ; i< addUserMmpdata.getPrefixArray().size(); i++)
                    {
                        prefixLabel[i-1] = addUserMmpdata.getPrefixArray().get(i).getLabel();
                    }

                    createDropdownView(prefixLabel, lytPrefix, txtPrefix, new LinearLayout[]{lytCountryCode});
                }
            }
        });

        txtCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(redeemSearchResultData!=null)
                {
                    String[] phoneExt = new String[redeemSearchResultData.getPhoneExtArray().size()];
                    for (int i=0 ; i<redeemSearchResultData.getPhoneExtArray().size(); i++)
                    {
                        phoneExt[i] = redeemSearchResultData.getPhoneExtArray().get(i).getLabel();
                    }
                    createDropdownView(phoneExt, lytCountryCode, txtCountryCode, new LinearLayout[]{lytPrefix});
                }else {

                    String[] phoneExt = new String[addUserMmpdata.getPhoneExtArray().size()];
                    for (int i=0 ; i<addUserMmpdata.getPhoneExtArray().size(); i++)
                    {
                        phoneExt[i] = addUserMmpdata.getPhoneExtArray().get(i).getLabel();
                    }
                    createDropdownView(phoneExt, lytCountryCode, txtCountryCode, new LinearLayout[]{lytPrefix});

                }
            }
        });

    }

    /*private void callToCheckFFPNumberValidity(final String ffpNumber)
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FfpValidation);


        JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("FFPNumber", ffpNumber);
            requestObject.put("isSignUp", "0");
        }
        catch (Exception e)
        {
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
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        FfpNumberData data = ParseManager.getInstance().fromJSON(response, FfpNumberData.class);

                        //all ok
                        if(data.getIsFFPValid() == 1)
                        {
                            setUiAndJSON();
                        }
                        else
                        {
                            //first clear all previous error message view
                            lytErrorMessage.removeAllViews();
                            lytError.setVisibility(View.VISIBLE);

                            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), data.getMessage()));
                        }


                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.string_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }*/

    private void setUiAndJSON() {
        try
        {

            //if upgrade required
            param.setUpgradRequired(false);
            param.setPrefix(prefixId);
            param.setFName(edtFirstName.getText().toString());
            param.setMName(edtMiddleName.getText().toString());
            param.setLName(edtLastName.getText().toString());
            param.setEmail(edtEmail.getText().toString());
            param.setMobExt(phoneExtValue);
            param.setMobNum(edtMobileNo.getText().toString());
            param.setDOBDay(checkDigit(daySelected));
            param.setDOBMonth(monthName);
            param.setDOBYear(yearSelected+"");

            if(redeemSearchResultData!=null)
            {
                param.setIsDisplayFfpNumber(redeemSearchResultData.getIsDisplayFFPNumber());
                param.setFFPnumberMandatory(redeemSearchResultData.getFFPNumberMandatory());
                param.setFFpnumberHelpMessage(redeemSearchResultData.getFFpnumberHelpMessage());
                param.setFFpnumberErrorMessage(redeemSearchResultData.getFFpnumberErrorMessage());

                param.setCardTypeArray(redeemSearchResultData.getCardTypeArray());
                param.setCountryListArray(redeemSearchResultData.getCountryListArray());
            }else {
                param.setIsDisplayFfpNumber(addUserMmpdata.getIsDisplayFFPNumber());
                param.setFFPnumberMandatory(addUserMmpdata.getFFPNumberMandatory());
                param.setFFpnumberHelpMessage(addUserMmpdata.getFFpnumberHelpMessage());
                param.setFFpnumberErrorMessage(addUserMmpdata.getFFpnumberErrorMessage());

                param.setCardTypeArray(addUserMmpdata.getCardTypeArray());
                param.setCountryListArray(addUserMmpdata.getCountryListArray());
            }

            Utils.moveToFragment(getActivity(),new RedeemAddPaxIdentityFragment(), param, 0);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void datePicker()
    {
        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {
                        // Display Selected date in textbox
                        monthSelected = monthOfYear;
                        yearSelected = year;
                        daySelected = dayOfMonth;

                        String dob = (checkDigit(dayOfMonth) + "-" + checkDigit(monthOfYear + 1) + "-" + year);
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
                        edtDateofBirth.setText(checkDigit(dayOfMonth) + "  " + monthName + "  " + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
    }

    public String checkDigit(int number) {

        return number <= 9 ? "0" + number : String.valueOf(number);

    }

    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();

        if (Utils.compareDefaultValues(txtPrefix, "")) {
            listError.add(localization.getLABLPrefixInputErrLabel());
        }

        if (Utils.compareDefaultValues(edtFirstName, "")) {
            listError.add(localization.getERRInvalidFirstNameErrorMessage());
        }
        else if(edtFirstName.getText().toString().trim().length() < 2)
        {
            listError.add(localization.getERRInvalidFirstNameErrorMessage() + " (Minimum 2 alphabets)");
        }

        if (Utils.compareDefaultValues(edtLastName, "")) {
            listError.add(localization.getERRInvalidLastNameErrorMessage());
        }
        else if(edtLastName.getText().toString().trim().length() < 2)
        {
            listError.add(localization.getERRInvalidLastNameErrorMessage() + " (Minimum 2 alphabets)");
        }

        if (!Utils.isValidEmail(edtEmail.getText().toString().trim())) {
            listError.add(localization.getERRInvalidD7EmailAddressErrorMessage());
            edtEmail.requestFocus();
        }

        if (Utils.compareDefaultValues(txtCountryCode, "")) {
            listError.add("Please select country");
        }

        if (Utils.compareDefaultValues(edtDateofBirth, "")) {
            listError.add(localization.getLABLDateValidDOBErrorLabel());
        }
        else
        {

            if (!Utils.isUserInfant(yearSelected+"",monthSelected, daySelected+"")) {
                listError.add(getResources().getString(R.string.string_error_age_infant));
            }
        }

        /*if(redeemSearchResultData !=null)
        {
            if(redeemSearchResultData.getFFPNumberMandatory() == 1 && Utils.compareDefaultValues(edtClubMembershipNumb, ""))
            {
                listError.add(redeemSearchResultData.getFFpnumberErrorMessage());
            }
        }else {
            if (addUserMmpdata.getIsDisplayFFPNumber() == 1)
            {
                if(addUserMmpdata.getFFPNumberMandatory()==1)
                {
                    if (Utils.compareDefaultValues(edtClubMembershipNumb, "")) {
                        listError.add(addUserMmpdata.getFFpnumberErrorMessage());
                    }
                }
            }
        }*/


        /*if (!Utils.isUserAdult(txtYear.getText().toString(), getCalMonth(txtMonth.getText().toString()), txtDay.getText().toString())) {

            listError.add(localization.getLABLPassAddUserAdultFirstLabel());
            txtYear.requestFocus();
        }*/

        if (!Utils.isValidMobileNumber(edtMobileNo.getText().toString())) {
            listError.add(localization.getERRInvalidPhoneNumberMessage());
            edtMobileNo.requestFocus();

        }

        return listError;
    }

    private int getCalMonth(String month) {

        String ary[] = getResources().getStringArray(R.array.array_month);
        for (int index = 0; index < ary.length; index++) {
            if(ary[index].equals(month))
                return index + 1;
        }

        return 1;
    }

    public void createDropdownView(final String[] aryDropdownOption, final LinearLayout lytParent, final OTTextView selectedView, LinearLayout aryOtherdropdown[])
    {

        hideKeyboard(lytParent.getContext(), lytParent.getRootView());
        if(lytParent.getChildCount() == 0)
        {
            //first dismiss rest dropdowns
            for (int index = 0; index < aryOtherdropdown.length; index++) {
                if(aryOtherdropdown[index] != null)
                {
                    ((LinearLayout) aryOtherdropdown[index]).removeAllViews();
                    ((View)((LinearLayout) aryOtherdropdown[index]).getParent()).setVisibility(View.GONE);
                }
            }

            for (int index = 0; index < aryDropdownOption.length; index++)
            {
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
                        ((View)(lytParent).getParent()).setVisibility(View.GONE);

                        if(redeemSearchResultData == null)
                        {
                            if(selectedView.getId() == R.id.txtCountryCode)
                            {
                                for (int i=0 ; i<addUserMmpdata.getPhoneExtArray().size(); i++)
                                {
                                    if(addUserMmpdata.getPhoneExtArray().get(i).getLabel().equals(aryDropdownOption[finalIndex]))
                                    {
                                        phoneExtValue = addUserMmpdata.getPhoneExtArray().get(i).getValue().toString();
                                        break;
                                    }

                                }
                            }
                            else if(selectedView.getId() == R.id.txtPrefix)
                            {
                                for (int i=1 ; i<addUserMmpdata.getPrefixArray().size(); i++)
                                {
                                    if(addUserMmpdata.getPrefixArray().get(i).getLabel().equals(aryDropdownOption[finalIndex]))
                                    {
                                        prefixId = addUserMmpdata.getPrefixArray().get(i).getValue().toString();
                                        break;
                                    }

                                }
                            }
                        }else {
                            if (selectedView.getId() == R.id.txtCountryCode) {
                                for (int i = 0; i < redeemSearchResultData.getPhoneExtArray().size(); i++) {
                                    if (redeemSearchResultData.getPhoneExtArray().get(i).getLabel().equals(aryDropdownOption[finalIndex])) {
                                        phoneExtValue = redeemSearchResultData.getPhoneExtArray().get(i).getValue().toString();
                                        break;
                                    }

                                }
                            } else if (selectedView.getId() == R.id.txtPrefix) {
                                for (int i = 1; i < redeemSearchResultData.getPrefixArray().size(); i++) {
                                    if (redeemSearchResultData.getPrefixArray().get(i).getLabel().equals(aryDropdownOption[finalIndex])) {
                                        prefixId = redeemSearchResultData.getPrefixArray().get(i).getValue().toString();
                                        break;
                                    }

                                }
                            }
                        }

                    }
                });

                lytParent.addView(textView, params);
            }
            ((View)(lytParent).getParent()).setVisibility(View.VISIBLE);
        }
        else
        {
            ((View)(lytParent).getParent()).setVisibility(((View)(lytParent).getParent()).getVisibility()== View.GONE ? View.VISIBLE : View.GONE);
        }

        Utils.calculateHeight(aryDropdownOption, lytParent);
    }

    public static void hideKeyboard(Context context, View view) {

        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

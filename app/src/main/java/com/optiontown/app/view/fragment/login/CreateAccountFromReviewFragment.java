package com.optiontown.app.view.fragment.login;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.CompleteProfile;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.login.LoginDataUpdateProfile;
import com.optiontown.app.model.login.PasswordValidationData;
import com.optiontown.app.model.login.PasswordvalidationRequired;
import com.optiontown.app.model.login.SelectedUserData;
import com.optiontown.app.model.review.FFPData;
import com.optiontown.app.model.review.FfpNumberData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.review.ReviewFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by amit on 29-06-2016.
 */
public class CreateAccountFromReviewFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    private OTEditText edtEmail;
    private OTEditText edtConfirmEmail;
    private OTEditText edtPassword;
    private OTTextView txtPasswordHelpLabel;
    private OTEditText edtConfirmPassword;
    private OTTextView txtPrefix;
    private OTEditText edtFirstName;
    private OTEditText edtMiddleName;
    private OTEditText edtLastName;
    private OTTextView txtCountryCode;
    private OTEditText edtMobileNo;
    private OTTextView txtDay;
    private OTTextView txtMonth;
    private OTTextView txtYear;
    private CheckBox cbAdd;
    private OTTextView txtCreateAccount;
    private CheckBox cbAcceptTerm;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private LoginData loginData;
    private LoginDataUpdateProfile loginDataUpdateProfile;
    private Communicator communicator;
    private LinearLayout lytPrefix;
    private LinearLayout lytCountryCode;
    private LinearLayout lytDay;
    private LinearLayout lytMonth;
    private LinearLayout lytYear;
    private LoginData loginDataFromArgument;
    private RelativeLayout lytEmailPassword;
    private OTTextView txtAdditionalInformationLabel;
    private CountryExtData countryExtData;
    private String[] strCountryCode;
    private OTTextView txtTermsOfService;
    InternationalizeData localization;
    private LinearLayout laycb_terms_service;
    private OTTextView txtClubNoLabel;
    private OTEditText edtClubNo;
    private FFPData ffpData;
    private int showPasswordFields = 0;// 0 - dont show
    private PasswordvalidationRequired passwordvalidationRequired;
    private String errorMsg;
    private ArrayList<String> listError;
    private FfpNumberData data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_create_account_from_review, container, false);

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());

        loginDataFromArgument = ((LoginData) getArguments().getSerializable(getString(R.string.key_serializable)));
        showPasswordFields = (int)getArguments().getInt(getString(R.string.key_view_type));


        if(loginDataFromArgument != null)
        {
            passwordvalidationRequired = loginDataFromArgument.getPasswordvalidationRequired();
            ffpData = new FFPData();
            ffpData.setFFPNumberMandatory(loginDataFromArgument.getFFPNumberMandatory());
            ffpData.setFFpnumberSortDesc(loginDataFromArgument.getFFpnumberSortDesc());
            ffpData.setFFpnumberErrorMessage(loginDataFromArgument.getFFpnumberErrorMessage());
            ffpData.setIsDisplayFFPNumber(loginDataFromArgument.getIsDisplayFFPNumber());
            if(loginDataFromArgument.getCompleteProfile() == null)
            {
                loginDataFromArgument = null;
            }
        }

        Utils.DEBUG("loginDataFromArgument json : " + ParseManager.getInstance().toJSON(loginDataFromArgument));

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        try
        {
            countryExtData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getCountryExtData(getActivity())), CountryExtData.class);
            strCountryCode = Utils.getCountryListExtArray(countryExtData.getData());
        }
        catch (Exception e)
        {
            Utils.ERROR("CreateAccountFromReviewFragment >> Error while parsing json : " + e.toString());
        }

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        getUIReference();




        return view;

    }

    private void localiseUI() {

        ((OTTextView) view.findViewById(R.id.txtMandatoryLabel)).setText("*"+localization.getLABLMandatoryFieldWithoutAsteriskLabel());
        edtEmail.setHint(localization.getLABLEmailLabel()+"*");
        edtConfirmEmail.setHint(localization.getLABLConfirmEmailInputLabel()+"*");
        edtPassword.setHint(localization.getLABLPasswordLabel()+"*");
        edtConfirmPassword.setHint(localization.getLABLConfirmPasswordLabel()+"*");


        ((OTTextView) view.findViewById(R.id.txtPersonalInformationLabel)).setText("*"+localization.getLABLPersonalInformationLabel());

        txtPrefix.setText(localization.getLABLPrefixLabel()+"*");
        edtFirstName.setHint(localization.getLABLFirstNameLabel()+"*");
        edtMiddleName.setHint(localization.getLABLMiddleNameLabel());
        edtLastName.setHint(localization.getLABLLastNameLabel()+"*");
        edtMobileNo.setHint(localization.getLABLTelephoneNumberLabel()+"*");
        ((OTTextView) view.findViewById(R.id.txtDateOfBirthLabel)).setText(localization.getLABLDateOfBirthLabel()+"*");
        txtDay.setText(localization.getLABLDaySmallcapLabel());
        txtMonth.setText(localization.getLABLMonthValidityLabel());
        //txtYear.setText(localization.getLABLYearLabel());
        if(loginDataFromArgument == null){
            txtCreateAccount.setText(localization.getLABLMACreateAccountPageHeadingLabel());
        }else {
            txtCreateAccount.setText(localization.getLABLSaveLabel());
        }
        txtTermsOfService.setText(localization.getLABLTermsLabel());


        updateUI();

    }

    private void updateUI()
    {
        if(loginDataFromArgument != null)
        {
            CompleteProfile completeProfile = loginDataFromArgument.getCompleteProfile();

            edtEmail.setText(completeProfile.getEmail());
            edtConfirmEmail.setText(completeProfile.getEmail());

            txtPrefix.setText(Utils.getPrefixName(getActivity(), completeProfile.getPrefix()));
            edtFirstName.setText(completeProfile.getFirstName());
            edtMiddleName.setText(completeProfile.getMiddleName());
            edtLastName.setText(completeProfile.getLastName());
            edtMobileNo.setText(completeProfile.getPhoneNumber());

            if(showPasswordFields == 1)
            {
                edtPassword.setVisibility(View.VISIBLE);
                edtConfirmPassword.setVisibility(View.VISIBLE);
                txtPasswordHelpLabel.setVisibility(View.VISIBLE);
                txtPasswordHelpLabel.setText(loginDataFromArgument.getPasswordHelpMsg());

                //first clear error message view
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.VISIBLE);

                //show error message
                lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), passwordvalidationRequired.getUpdateErrorHelpMsg()));
            }
            /*else if (loginDataFromArgument.getPasswdValidationRequired().equals("1")){
                edtPassword.setVisibility(View.VISIBLE);
                edtConfirmPassword.setVisibility(View.VISIBLE);
                txtPasswordHelpLabel.setVisibility(View.VISIBLE);
            }*/
            else
            {
                edtPassword.setVisibility(View.GONE);
                edtConfirmPassword.setVisibility(View.GONE);
                txtPasswordHelpLabel.setVisibility(View.GONE);
            }

            edtEmail.setVisibility(View.GONE);
            edtConfirmEmail.setVisibility(View.GONE);
            txtAdditionalInformationLabel.setVisibility(View.VISIBLE);
            laycb_terms_service.setVisibility(View.GONE);

            txtDay.setText(completeProfile.getBirthDay());
            txtMonth.setText(Utils.getEquivalentLocalisedMonth(completeProfile.getBirthMonth(), Utils.getLocalForCommunication(), Locale.getDefault()));
            txtYear.setText(completeProfile.getBirthYear());

        }
        else
        {
            edtEmail.setVisibility(View.VISIBLE);
            edtConfirmEmail.setVisibility(View.VISIBLE);
            edtPassword.setVisibility(View.VISIBLE);
            edtConfirmPassword.setVisibility(View.VISIBLE);
            txtPasswordHelpLabel.setVisibility(View.VISIBLE);
            if(passwordvalidationRequired != null)
            {
                txtPasswordHelpLabel.setText(passwordvalidationRequired.getPasswordHelpMsg() + "-appended");
            }

            txtAdditionalInformationLabel.setVisibility(View.GONE);
            laycb_terms_service.setVisibility(View.VISIBLE);
        }


        if(ffpData.getIsDisplayFFPNumber() == 1)
        {
            if(ffpData.getFFPNumberMandatory() == 1 || ffpData.getFFPNumberMandatory() == 0)
            {
                txtClubNoLabel.setVisibility(View.VISIBLE);
                edtClubNo.setVisibility(View.VISIBLE);
            }

        }else {

            txtClubNoLabel.setVisibility(View.GONE);
            edtClubNo.setVisibility(View.GONE);

        }

        /*if(ffpData.getFFPNumberMandatory() == 1 || ffpData.getFFPNumberMandatory() == 0)
        {
            txtClubNoLabel.setVisibility(View.VISIBLE);
            edtClubNo.setVisibility(View.VISIBLE);
        }else {
            txtClubNoLabel.setVisibility(View.GONE);
            edtClubNo.setVisibility(View.GONE);
        }*/

        txtClubNoLabel.setText(ffpData.getFFpnumberSortDesc() + (ffpData.getFFPNumberMandatory() == 1 ? " * " : ""));

        txtCountryCode.setText(strCountryCode[0]);


    }

    private void getUIReference()
    {
        laycb_terms_service = (LinearLayout) view.findViewById(R.id.laycb_terms_service);
        final ScrollView svPrefix = (ScrollView) view.findViewById(R.id.svPrefix);
        final ScrollView svCountryCode = (ScrollView) view.findViewById(R.id.svCountryCode);
        final ScrollView svDay = (ScrollView) view.findViewById(R.id.svDay);
        final ScrollView svMonth = (ScrollView) view.findViewById(R.id.svMonth);
        final ScrollView svYear = (ScrollView) view.findViewById(R.id.svYear);

        view.findViewById(R.id.svParent).setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event)
            {
                svPrefix.getParent().requestDisallowInterceptTouchEvent(false);
                svCountryCode.getParent().requestDisallowInterceptTouchEvent(false);
                svDay.getParent().requestDisallowInterceptTouchEvent(false);
                svMonth.getParent().requestDisallowInterceptTouchEvent(false);
                svYear.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        Utils.setInterceptTouchEvent(new View[]{svPrefix, svCountryCode, svDay, svMonth, svYear});

        view.findViewById(R.id.lytMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svPrefix.setVisibility(View.GONE);
                svCountryCode.setVisibility(View.GONE);
                svDay.setVisibility(View.GONE);
                svMonth.setVisibility(View.GONE);
                svYear.setVisibility(View.GONE);
            }
        });

        txtTermsOfService = (OTTextView) view.findViewById(R.id.txtTermsOfService);
        txtTermsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new TermsServicePolicyFragment(),  getString(R.string.URL_METHOD_TERMS_OF_SERVICE), 0);
            }
        });


        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        edtEmail = (OTEditText) view.findViewById(R.id.edtEmail);
        edtConfirmEmail = (OTEditText) view.findViewById(R.id.edtConfirmEmail);
        edtPassword = (OTEditText) view.findViewById(R.id.edtPassword);
        edtConfirmPassword = (OTEditText) view.findViewById(R.id.edtConfirmPassword);

        lytEmailPassword = (RelativeLayout) view.findViewById(R.id.lytEmailPassword);


        txtAdditionalInformationLabel = (OTTextView) view.findViewById(R.id.txtAdditionalInformationLabel);

        lytPrefix = (LinearLayout) view.findViewById(R.id.lytPrefix);

        txtPasswordHelpLabel = (OTTextView) view.findViewById(R.id.txtPasswordHelpLabel);


        txtPrefix = (OTTextView) view.findViewById(R.id.txtPrefix);
        txtPrefix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(Utils.getPrefixList(getActivity()), lytPrefix, txtPrefix, new LinearLayout[]{lytCountryCode, lytDay, lytMonth, lytYear});
            }
        });
        edtFirstName = (OTEditText) view.findViewById(R.id.edtFirstName);
        edtMiddleName = (OTEditText) view.findViewById(R.id.edtMiddleName);
        edtLastName = (OTEditText) view.findViewById(R.id.edtLastName);

        lytCountryCode = (LinearLayout) view.findViewById(R.id.lytCountryCode);
        txtCountryCode = (OTTextView) view.findViewById(R.id.txtCountryCode);
        txtCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.DEBUG("CountryExtData >> " + countryExtData.getData());
                Utils.DEBUG("CountryExtData >> " + countryExtData.getData().size());
                Utils.createDropdownView(strCountryCode, lytCountryCode, txtCountryCode, new LinearLayout[]{lytPrefix, lytDay, lytMonth, lytYear});
            }
        });

        edtMobileNo = (OTEditText) view.findViewById(R.id.edtMobileNo);

        lytDay = (LinearLayout) view.findViewById(R.id.lytDay);
        txtDay = (OTTextView) view.findViewById(R.id.txtDay);
        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(getResources().getStringArray(R.array.array_day), lytDay, txtDay, new LinearLayout[]{lytPrefix, lytCountryCode, lytMonth, lytYear});
            }
        });

        lytMonth = (LinearLayout) view.findViewById(R.id.lytMonth);
        txtMonth = (OTTextView) view.findViewById(R.id.txtMonth);
        txtMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(Utils.getLocalisedMonth(Locale.getDefault()), lytMonth, txtMonth, new LinearLayout[]{lytPrefix, lytCountryCode, lytDay, lytYear});
            }
        });

        lytYear = (LinearLayout) view.findViewById(R.id.lytYear);
        txtYear = (OTTextView) view.findViewById(R.id.txtYear);
        txtYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(getResources().getStringArray(R.array.array_year), lytYear, txtYear, new LinearLayout[]{lytPrefix, lytCountryCode, lytDay, lytMonth});
            }
        });



        cbAdd = (CheckBox) view.findViewById(R.id.cbAdd);
        cbAdd.setText(localization.getAddMeAsFirstPaxLabel());
        cbAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /*if(isChecked)
                {
                    if(ReviewFragment.airlineId == 975)
                    {
                        txtClubNoLabel.setVisibility(View.VISIBLE);
                        edtClubNo.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        txtClubNoLabel.setVisibility(View.GONE);
                        edtClubNo.setVisibility(View.GONE);
                    }
                }
                else
                {
                    txtClubNoLabel.setVisibility(View.GONE);
                    edtClubNo.setVisibility(View.GONE);
                }*/
            }
        });

        txtClubNoLabel = (OTTextView) view.findViewById(R.id.txtClubNoLabel);
        edtClubNo = (OTEditText) view.findViewById(R.id.edtClubNo);

        txtCreateAccount = (OTTextView) view.findViewById(R.id.txtCreateAccount);
        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                listError = validateAllInputsCommon();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(edtClubNo.getVisibility() == View.VISIBLE)
                        {
                            //when ffp number is required
                            if(ffpData.getFFPNumberMandatory() == 1 || (ffpData.getFFPNumberMandatory() == 0 ))
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
                                lytErrorMessage.removeAllViews();
                                lytError.setVisibility(View.VISIBLE);

                                //show error message
                                for (int index = 0; index < listError.size(); index++)
                                {
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                                }
                            }
                            else {
                                Utils.isValidEmailAddress(getActivity(), edtEmail.getText().toString());
                                try {

                                        if (loginDataFromArgument == null) {
                                            //call api to register
                                            callCreateAccountApi();
                                        } else {
                                            //type 1 customer, call UpdateProfile api to update DOB etc
                                            callUpdateProfileApi();
                                        }

                                }catch (Exception e){}

                            }
                        }


                    }
                },200);
            }
        });
        cbAcceptTerm = (CheckBox) view.findViewById(R.id.cbAcceptTerm);


        localiseUI();

    }


    private String callToCheckFFPNumberValidity(final String ffpNumber, final ArrayList<String> listError)
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FfpValidation) + "&isSearchForm=0";


        JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("FFPNumber", ffpNumber);
            requestObject.put("isSignUp", "1");
        }
        catch (Exception e)
        {
            Utils.ERROR("Error while creating json request callToCheckFFPNumberValidity(): " + e.toString());
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
                        data = ParseManager.getInstance().fromJSON(response, FfpNumberData.class);

                        //all ok
                        if(data.getIsFFPValid() == 1)
                        {
                            /*appending zero , the condition updated on server 166
                             *  */

                            edtClubNo.setText(data.getUpdatedFfp().toString());
                        }
                        else
                        {
                            if(!listError.contains(ffpData.getFFpnumberErrorMessage()))
                            {
                                errorMsg = data.getMessage().toString();
                                listError.add(errorMsg);
                            }

                        }


                        if(listError.size()>0)
                        {
                            //first clear all previos error message view
                            lytErrorMessage.removeAllViews();
                            lytError.setVisibility(View.VISIBLE);

                            //show error message
                            for (int index = 0; index < listError.size(); index++)
                            {
                                lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                            }
                        }
                        else {
                            Utils.isValidEmailAddress(getActivity(), edtEmail.getText().toString());
                            try {
                                if(data.getIsFFPValid() == 1) {

                                    if (loginDataFromArgument == null) {
                                        //call api to register
                                        callCreateAccountApi();
                                    } else {
                                        //type 1 customer, call UpdateProfile api to update DOB etc
                                        callUpdateProfileApi();
                                    }
                                }
                            }catch (Exception e){}

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

        return errorMsg;
    }


    private ArrayList<String> validateAllInputsCommon()
    {
        ArrayList<String> listError = new ArrayList<>();

        if(loginDataFromArgument == null)
        {
            if (!Utils.isValidEmail(edtEmail.getText().toString().trim())) {
                listError.add(localization.getERRInvalidD7EmailAddressErrorMessage());
                edtEmail.requestFocus();
            }

            if (Utils.isValidEmail(edtConfirmEmail.getText().toString().trim())) {
                if (!edtEmail.getText().toString().trim().equals(edtConfirmEmail.getText().toString().trim())) {
                    listError.add(localization.getERRInvalidEmailOrConfirmEmailAddress());
                    edtConfirmEmail.requestFocus();
                }
            } else {

                if(!listError.contains(localization.getERRInvalidD7EmailAddressErrorMessage()))
                {
                    listError.add(localization.getERRInvalidD7EmailAddressErrorMessage());
                }
                listError.add(localization.getERRInvalidEmailOrConfirmEmailAddress());
                edtConfirmEmail.requestFocus();
            }

            if(!edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString().trim()))
            {
                listError.add(localization.getPasswordConfirmPassDoNotMatch());
            }

            /*if (!Utils.isValidPassword(edtPassword.getText().toString().trim())) {
                listError.add(localization.getERRInvalidPasswordOrConfirmPassword());
                edtPassword.requestFocus();
            }

            if (!Utils.isValidPassword(edtConfirmPassword.getText().toString().trim())) {
                if (!edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString().trim())) {
                    listError.add(localization.getERRInvalidPasswordOrConfirmPassword());
                    edtConfirmPassword.requestFocus();
                } else {
                    listError.add(localization.getPasswordConfirmPassDoNotMatch());
                    edtConfirmPassword.requestFocus();
                }
            }*/
        }

        if (Utils.compareDefaultValues(txtDay, localization.getLABLDaySmallcapLabel())
                || Utils.compareDefaultValues(txtMonth, localization.getLABLMonthValidityLabel())
                || Utils.compareDefaultValues(txtYear, localization.getLABLYearLabel())) {
            listError.add(localization.getLABLDateValidDOBErrorLabel());
        }
        else
        {
            if(cbAdd.isChecked())
            {
                if (!Utils.isUserAdult(txtYear.getText().toString(), getCalMonth(txtMonth.getText().toString()), txtDay.getText().toString())) {

                    listError.add(localization.getLABLPassAddUserAdultFirstLabel());
                    txtYear.requestFocus();
                }
            }else {

                if (!Utils.isUserInfant(txtYear.getText().toString(), getCalMonth(txtMonth.getText().toString()), txtDay.getText().toString())) {
                    //conditional as prod returning null
                    listError.add(localization.getLABL_InfantPasngr_Label() == null ? "This passenger is an Infant. You cannot add an infant to booking using flight pass. You may contact airline to add infant to your booking." : localization.getLABL_InfantPasngr_Label());
                    txtYear.requestFocus();
                }
            }
        }


        if (!Utils.isValidMobileNumber(edtMobileNo.getText().toString())) {
            listError.add(localization.getERRInvalidPhoneNumberMessage());
            edtMobileNo.requestFocus();

        }

        if (Utils.compareDefaultValues(txtPrefix, localization.getLABLPrefixErrorLabel())) {
            listError.add(localization.getLABLPrefixInputErrLabel());
        }

        if (Utils.compareDefaultValues(txtPrefix, localization.getLABLPrefixErrorLabel()+"*")) {
            listError.add(localization.getLABLPrefixInputErrLabel());
        }

        if (Utils.compareDefaultValues(edtFirstName, "")) {
            listError.add(localization.getERRInvalidFirstNameErrorMessage());
        }

        if (Utils.compareDefaultValues(edtLastName, "")) {
            listError.add(localization.getERRInvalidLastNameErrorMessage());
        }

        /*if(ffpData.getFFPNumberMandatory() == 1  && Utils.compareDefaultValues(edtClubNo, ""))
        {
            listError.add(ffpData.getFFpnumberErrorMessage());
        }*/

        if(!cbAcceptTerm.isChecked() && loginDataFromArgument == null)
        {
            listError.add(localization.getLABLTermConditionErrLabel());
        }

        if(ffpData.getFFPNumberMandatory() == 1  && Utils.compareDefaultValues(edtClubNo, ""))
        {
            listError.add(ffpData.getFFpnumberErrorMessage());
        }

        if(loginDataFromArgument != null && showPasswordFields == 1)
        {
            if(Utils.compareDefaultValues(edtPassword, ""))
            {
                listError.add(loginDataFromArgument.getPasswordvalidationRequired().getUpdateErrorHelpMsg());
            }

            if(!edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString().trim()))
            {
                listError.add(localization.getPasswordConfirmPassDoNotMatch());
            }
        }
         /*if (loginDataFromArgument != null && loginDataFromArgument.getPasswordvalidationRequired().getPasswordRequired()==1){
             if(Utils.compareDefaultValues(edtPassword, ""))
             {
                 listError.add(loginDataFromArgument.getPasswordvalidationRequired().getUpdateErrorHelpMsg());
             }

             if(!edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString().trim()))
             {
                 listError.add(localization.getPasswordConfirmPassDoNotMatch());
             }
        }*/
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

    private void  callCreateAccountApi()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_ADD_MOBILE_USER) + getString(R.string.URL_CREATE_ACCOUNT);

        JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("customer.prefixid", Integer.toString(Utils.getPrefixId(getActivity(), txtPrefix.getText().toString().trim())));
            requestObject.put("customer.firstName", edtFirstName.getText().toString().trim());
            requestObject.put("customer.middleName", edtMiddleName.getText().toString().trim());
            requestObject.put("customer.lastName", edtLastName.getText().toString().trim());
            requestObject.put("customer.emailAddress", edtEmail.getText().toString().trim());
            requestObject.put("customer.confirmEmailAddress", edtConfirmEmail.getText().toString().trim());
            requestObject.put("customer.password", edtPassword.getText().toString().trim());
            requestObject.put("customer.confirmpassword", edtConfirmPassword.getText().toString().trim());
            requestObject.put("customer.TelephoneExt", Utils.getCountryIdExt(countryExtData.getData(), txtCountryCode.getText().toString().trim()));
            requestObject.put("customer.TelephoneMainPart", edtMobileNo.getText().toString().trim());
            requestObject.put("customer.checkForRules", Boolean.toString(cbAcceptTerm.isChecked()));
            requestObject.put("customer.sex",  Integer.toString(0));
            requestObject.put("BirthDay", txtDay.getText().toString().trim());
            requestObject.put("BirthMonth", Utils.getEquivalentLocalisedMonth(txtMonth.getText().toString().trim(), Locale.getDefault(), Utils.getLocalForCommunication()));
            requestObject.put("BirthYear", txtYear.getText().toString().trim());
            requestObject.put("FFPNumber", edtClubNo.getText().toString().trim());
            requestObject.put("isReview", "1");

        }
        catch (Exception e)
        {
            Utils.ERROR("Error while creating json request callCreateAccountApi(): " + e.toString());
            e.printStackTrace();
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
                        loginData = ParseManager.getInstance().fromJSON(response, LoginData.class);

                        if(loginData.getResult().equals(getString(R.string.string_success)))
                        {
                            //call UpdateProfile api to update DOB etc
                            callUpdateProfileApi();
                        }
                        else if(loginData.getResult().equals(getString(R.string.string_failure)))
                        {
                            //show error message received
                            lytErrorMessage.removeAllViews();
                            lytError.setVisibility(View.VISIBLE);

                            if(loginData.getIsPasswrdValidationFail())
                            {
                                ArrayList<PasswordValidationData> listErrorPassword = loginData.getPasswordErrorList().getPasswordValidationData();
                                for (int index = 0; index < listErrorPassword.size(); index++) {
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listErrorPassword.get(index).getErrorMessage()));
                                }
                            }
                            else {
                                lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), loginData.getMessage()));
                            }
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
    }

    private void callUpdateProfileApi()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_PAYMENT_PAGE) + getString(R.string.URL_UPDATE_PROFILE);


        JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("PrefixId", Integer.toString(Utils.getPrefixId(getActivity(), txtPrefix.getText().toString().trim())));
            requestObject.put("FirstName", edtFirstName.getText().toString().trim());
            requestObject.put("MiddleName", edtMiddleName.getText().toString().trim());
            requestObject.put("LastName", edtLastName.getText().toString().trim());
            requestObject.put("TelephoneCodeEXT", Utils.getCountryIdExt(countryExtData.getData(), txtCountryCode.getText().toString().trim()));
            requestObject.put("TelephoneMainPart", edtMobileNo.getText().toString().trim());
            requestObject.put("Sex", Integer.toString(0));
            requestObject.put("AlternateTelephoneCode", "");
            requestObject.put("AlternateTelephoneNumberMainPart", "");
            requestObject.put("BirthDay", txtDay.getText().toString().trim());
            requestObject.put("BirthMonth", Utils.getEquivalentLocalisedMonth(txtMonth.getText().toString().trim(), Locale.getDefault(), Utils.getLocalForCommunication()));
            requestObject.put("BirthYear", txtYear.getText().toString().trim());
            requestObject.put("setId", Integer.toString(getCustomerId()));
            requestObject.put("UserName", edtEmail.getText().toString().trim());
            requestObject.put("FFPNumber", edtClubNo.getText().toString().trim());

            //if(showPasswordFields == 1 || loginDataFromArgument.getPasswdValidationRequired().equals("1"))
            {
                requestObject.put("Password", edtPassword.getText().toString().trim());
                requestObject.put("ConfirmPassword", edtConfirmPassword.getText().toString().trim());
            }


        }
        catch (Exception e)
        {
            Utils.ERROR("Error while creating json request callUpdateProfileApi(): " + e.toString());
            e.printStackTrace();
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
                        Utils.DEBUG("onResponse() updateProfile called : " + response.toString());
                        loginDataUpdateProfile = ParseManager.getInstance().fromJSON(response, LoginDataUpdateProfile.class);

                        try {

                            if (loginDataUpdateProfile.getMessage().equals(getString(R.string.string_success))) {
                                //set login data
                                Utils.setLoginData(getActivity(), response.toString());

                                Utils.setUsername(getActivity(), edtEmail.getText().toString().trim());
                                Utils.setPassword(getActivity(), edtPassword.getText().toString().trim());

                                callAddUserApi();//to get users of this login
                            } else if(loginDataUpdateProfile.getMessage().equals(getString(R.string.string_failure)))
                            {
                                //show error message received
                                lytErrorMessage.removeAllViews();
                                lytError.setVisibility(View.VISIBLE);

                                if(loginDataUpdateProfile.getIsPasswrdValidationFail())
                                {
                                    ArrayList<PasswordValidationData> listErrorPassword = loginDataUpdateProfile.getPasswordErrorList().getPasswordValidationData();
                                    for (int index = 0; index < listErrorPassword.size(); index++) {
                                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listErrorPassword.get(index).getErrorMessage()));
                                    }
                                }
                                else {
                                    //lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), loginData.getMessage()));
                                }
                            }
                            else
                            {
                                //Error
                                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));

                            }
                        }catch (Exception e){

                            e.printStackTrace();
                            callAddUserApi();//to get users of this login
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
    }

    /**
     * used to get customer id
     * @return
     */
    private int getCustomerId()
    {
        if(loginDataFromArgument == null)
        {
            return loginData.getCustomerId();
        }
        else
        {
            return loginDataFromArgument.getCustomerId();
        }
    }

    private void callAddUserApi()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_ADD_USER);


        JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("customerId", Integer.toString(getCustomerId()));
        }
        catch (Exception e)
        {
            Utils.ERROR("Error while creating json request callAddUserApi(): " + e.toString());
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
                        SelectedUserData selectedUserData = ParseManager.getInstance().fromJSON(response, SelectedUserData.class);

                        //move to review page
                        FragmentCommunicationData data = new FragmentCommunicationData();
                        data.setFragmentName(new ReviewFragment().getClass().getName());
                        data.setLoginData(loginData);
                        data.setLoginDataUpdateProfile(loginDataUpdateProfile);
                        data.setSelectedUserData(selectedUserData);
                        data.setCallReviewAPI(true);
                        //if user is opted for 'add me as first user' then send its id in data, also it will definitely at 0th position
                        data.setAddMeAsFirstUserId(cbAdd.isChecked() ? selectedUserData.getAddUserData().get(0).getSelectUserId() : 0);
                        communicator.onResponse(data);

                        loader.dismiss();

                        Utils.clearLoginFromBackStack(getActivity());
                        //update top action bar
                        Utils.updateActionBarForFeatures(getActivity(), new ReviewFragment().getClass().getName());
                        /*((MainActivity)getActivity()).onBackPressed();
                        ((MainActivity)getActivity()).onBackPressed();*/


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
    }


    @Override
    public void onAttach(Activity activity)
    {
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }


}

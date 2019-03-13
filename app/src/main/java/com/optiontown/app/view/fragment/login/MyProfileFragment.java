package com.optiontown.app.view.fragment.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.countryextlist.CountryList;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.CompleteProfile;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.login.PasswordValidationData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by amit on 02-07-2016.
 */
public class MyProfileFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    private OTEditText edtUsernameEmail;
    private OTEditText edtPassword;
    private OTEditText edtConfirmPassword;
    private CheckBox cbChangePassword;
    private OTEditText edtEmail;
    private OTEditText edtConfirmEmail;
    private CheckBox cbAlert;
    private CheckBox cbSpecialOffer;
    private OTTextView txtPrefix;
    private OTEditText edtFirstName;
    private OTEditText edtMiddleName;
    private OTEditText edtLastName;
    private OTTextView txtSuffix;
    private OTTextView txtSex;
    private OTTextView txtDay;
    private OTTextView txtMonth;
    private OTTextView txtYear;
    private OTEditText edtPaypalEmail;
    private OTEditText edtConfirmPaypalEmail;
    private OTEditText edtCreditCheckRecipient;
    private OTEditText edtAddressLine1;
    private OTEditText edtAddressLine2;
    private OTEditText edtCity;
    private OTEditText edtZip;
    private OTEditText edtStateProvince;
    private OTTextView txtCountry;
    private OTEditText edtMobileCode;
    private OTEditText edtMobileNumber;
    private OTEditText edtOtherPhoneCode;
    private OTEditText edtOtherPhoneNumber;
    private LoginData loginData;
    private OTTextView txtCancel;
    private OTTextView txtSave;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytPrefix;
    private LinearLayout lytSuffix;
    private LinearLayout lytSex;
    private LinearLayout lytDay;
    private LinearLayout lytMonth;
    private LinearLayout lytYear;
    private LinearLayout lytCountry;
    private CountryExtData countryExtData;
    InternationalizeData localization;
    private AppDialogLoader loader;
    private FragmentActivity fragmentActivity;
    private Communicator communicator;
    private String errorMessage;
    private OTTextView txtPasswordHintLabel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        errorMessage = ((String) getArguments().getSerializable(getString(R.string.key_serializable)));

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        loader = AppDialogLoader.getLoader(getActivity());

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        try
        {
            loginData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLoginData(getActivity())), LoginData.class);
            countryExtData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getCountryExtData(getActivity())), CountryExtData.class);

            getUIReference();

            updateUI();
        }
        catch (Exception e)
        {
            Utils.ERROR("Utils >> Error while parsing json : " + e.toString());
            e.printStackTrace();
        }

        return view;

    }

    private void localiseUI(){


        ((OTTextView) view.findViewById(R.id.txt_myprofile)).setText("My Profile");
        ((OTTextView) view.findViewById(R.id.txt_mandatoryfields)).setText("*"+localization.getLABLMandatoryFieldWithoutAsteriskLabel());
        ((OTTextView) view.findViewById(R.id.txt_logininfo_label)).setText(localization.getLABLLoginInformationLabel());

        ((OTTextView) view.findViewById(R.id.txtUsernameEmailLabel)).setText(localization.getLABLEmailLabel()+"*");
        ((OTTextView) view.findViewById(R.id.txtUsernameHintLabel)).setText(localization.getLABLUsernameHelpLabel()+"*");
        ((OTTextView) view.findViewById(R.id.txtPasswordLabel)).setText(localization.getLABLPasswordLabel()+"*");
        ((OTTextView) view.findViewById(R.id.txtConfirmPasswordLabel)).setText(localization.getLABLConfirmPasswordLabel()+"*");
        ((CheckBox) view.findViewById(R.id.cbChangePassword)).setText(localization.getLABLPasswordChangeLabel());
        ((CheckBox) view.findViewById(R.id.cbAlert)).setText(localization.getLABLTGPAlertCheckboxLabel());
        ((CheckBox) view.findViewById(R.id.cbSpecialOffer)).setText(localization.getLABLSpecialOfferLabel());

        ((OTTextView) view.findViewById(R.id.txtEmailLabel)).setText(localization.getLABLEmailLabel()+"*");
        ((OTTextView) view.findViewById(R.id.txtConfirmEmailLabel)).setText(localization.getLABLConfirmEmailInputLabel()+"*");
        ((OTTextView) view.findViewById(R.id.txt_joinbuz)).setText(localization.getLABLTGPAlertBoxHeadingLabel());
        ((OTTextView) view.findViewById(R.id.txt_signup)).setText(localization.getLABLTGPAlertBoxHelpLabel());
        ((OTTextView) view.findViewById(R.id.txtPersonalInformationLabel)).setText(localization.getLABLPersonalInformationLabel());
        ((OTTextView) view.findViewById(R.id.txtPrefixLabel)).setText(localization.getLABLPrefixLabel()+"*");
        txtPrefix.setText(localization.getLABLPrefixLabel()+"*");
        ((OTTextView) view.findViewById(R.id.txtFirstNameLabel)).setText(localization.getLABLFirstNameLabel()+"*");
        ((OTEditText) view.findViewById(R.id.edtFirstName)).setHint(localization.getLABLFirstNameLabel());
        ((OTTextView) view.findViewById(R.id.txtMiddleNameLabel)).setHint(localization.getLABLMiddleNameLabel());
        ((OTTextView) view.findViewById(R.id.txtLastNameLabel)).setText(localization.getLABLLastNameLabel()+"*");
        ((OTEditText) view.findViewById(R.id.edtLastName)).setHint(localization.getLABLLastNameLabel());
        ((OTTextView) view.findViewById(R.id.txtSuffixLabel)).setText(localization.getLABLSuffixLabel());
        txtSuffix.setText(localization.getLABLSuffixLabel());
        ((OTTextView) view.findViewById(R.id.txtSexLabel)).setText(localization.getLABLSexLabel());
        ((OTTextView) view.findViewById(R.id.txtDateOfBirthLabel)).setText(localization.getLABLDateOfBirthLabel());
        txtDay.setText(localization.getLABLDaySmallcapLabel());
        txtMonth.setText(localization.getLABLMonthsValidityLabel());
        txtYear.setText(localization.getLABLYearLabel());
        ((OTTextView) view.findViewById(R.id.txtPaypalEmailLabel)).setText(localization.getLABLPayPalDefaultAccountEmailLabel());
        ((OTTextView) view.findViewById(R.id.txtConfirmPaypalEmailLabel)).setText(localization.getLABLPayPalAccountConfirmEmailLabel());
        ((OTTextView) view.findViewById(R.id.txtCreditCheckRecipientLabel)).setText(localization.getLABLCheckDefaultNameLabel());
        ((OTTextView) view.findViewById(R.id.txtContactInformationLabel)).setText(localization.getLABLContactInformationLabel());
        ((OTTextView) view.findViewById(R.id.txtAddressLine1Label)).setText(localization.getLABLAddressLine1Label());
        ((OTTextView) view.findViewById(R.id.txtAddressLine2Label)).setText(localization.getLABLAddressLine2Label());
        ((OTTextView) view.findViewById(R.id.txtCityLabel)).setText(localization.getLABLCityLabel());
        ((OTTextView) view.findViewById(R.id.txtZipLabel)).setText(localization.getLABLZipPostalCodeLabel());
        ((OTTextView) view.findViewById(R.id.txtStateProvinceLabel)).setText(localization.getLABLStateProvinceLabel());
        ((OTTextView) view.findViewById(R.id.txtCountryLabel)).setText(localization.getLABLPNRSearchByFlightPhoneCountryCodeLabel());
        ((OTEditText) view.findViewById(R.id.edtMobileCode)).setHint(localization.getCodeLabel());
        ((OTEditText) view.findViewById(R.id.edtOtherPhoneCode)).setHint(localization.getCodeLabel());
        ((OTTextView) view.findViewById(R.id.txtMobileLabel)).setText(localization.getLABLCellMobileLabel());
        ((OTTextView) view.findViewById(R.id.txtEnterDigitsOnlyLabel)).setText(localization.getMTPTelephoneValidationMessage());
        ((OTTextView) view.findViewById(R.id.txtOtherPhoneLabel)).setText(localization.getLABLOtherPhoneLabel());
        txtCancel.setText(localization.getLABLCancelLabel());
        txtSave.setText(localization.getLABLSaveLabel());
    }

    private void updateUI()
    {
        CompleteProfile completeProfile = loginData.getCompleteProfile();
        edtUsernameEmail.setText(completeProfile.getEmail());
        edtEmail.setText(completeProfile.getEmail());
        edtConfirmEmail.setText(completeProfile.getEmail());
        cbAlert.setChecked(completeProfile.getOPAlert() == 0 ? false : true);
        cbSpecialOffer.setChecked(completeProfile.getReceiveOffers() == 0 ? false : true);
        txtPrefix.setText(Utils.getPrefixName(getActivity(), completeProfile.getPrefix()));
        edtFirstName.setText(completeProfile.getFirstName());
        edtMiddleName.setText(completeProfile.getMiddleName());
        edtLastName.setText(completeProfile.getLastName());
        txtSuffix.setText(Utils.getSuffixName(getActivity(), completeProfile.getSuffix()));
        txtSex.setText(Utils.getGenderName(getActivity(), Integer.parseInt(completeProfile.getSex())));
        txtDay.setText(completeProfile.getBirthDay());
        txtMonth.setText(Utils.getEquivalentLocalisedMonth(completeProfile.getBirthMonth(), Utils.getLocalForCommunication(), Locale.getDefault()));
        txtYear.setText(completeProfile.getBirthYear());
        edtPaypalEmail.setText(completeProfile.getPaypalAccountEmail());
        edtConfirmPaypalEmail.setText(completeProfile.getPaypalAccountEmail());
        edtCreditCheckRecipient.setText(completeProfile.getCreditRecipient());
        edtAddressLine1.setText(completeProfile.getAddress1());
        edtAddressLine2.setText(completeProfile.getAddress2());
        edtCity.setText(completeProfile.getCity());
        edtZip.setText(completeProfile.getZipCode());
        edtStateProvince.setText(completeProfile.getState());
        txtCountry.setText(Utils.getCountry(countryExtData.getCountryList(), completeProfile.getCountry()));
        edtMobileCode.setText(completeProfile.getExtension());
        edtMobileNumber.setText(completeProfile.getPhoneNumber());
        edtOtherPhoneCode.setText(completeProfile.getAlternateTelCode());
        edtOtherPhoneNumber.setText(completeProfile.getAlternateTelNumber());
        txtPasswordHintLabel.setText(loginData != null && loginData.getPasswordvalidationRequired() != null ? loginData.getPasswordvalidationRequired().getPasswordHelpMsg() : "");



        if(errorMessage != null)
        {
            lytError.setVisibility(View.VISIBLE);
            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), errorMessage));

            cbChangePassword.setChecked(true);
            cbChangePassword.setEnabled(false);

        }


        edtPassword.setEnabled(cbChangePassword.isChecked());
        edtConfirmPassword.setEnabled(cbChangePassword.isChecked());
    }




    private void getUIReference()
    {
        resolveScrollingConflict();

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        edtUsernameEmail = (OTEditText) view.findViewById(R.id.edtUsernameEmail);
        edtPassword = (OTEditText) view.findViewById(R.id.edtPassword);
        edtConfirmPassword = (OTEditText) view.findViewById(R.id.edtConfirmPassword);
        cbChangePassword = (CheckBox) view.findViewById(R.id.cbChangePassword);
        cbChangePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edtPassword.setEnabled(isChecked);
                edtConfirmPassword.setEnabled(isChecked);
            }
        });
        edtEmail = (OTEditText) view.findViewById(R.id.edtEmail);
        edtConfirmEmail = (OTEditText) view.findViewById(R.id.edtConfirmEmail);
        cbAlert = (CheckBox) view.findViewById(R.id.cbAlert);
        cbSpecialOffer = (CheckBox) view.findViewById(R.id.cbSpecialOffer);

        lytPrefix = (LinearLayout) view.findViewById(R.id.lytPrefix);
        txtPrefix = (OTTextView) view.findViewById(R.id.txtPrefix);
        txtPrefix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(Utils.getPrefixList(getActivity()), lytPrefix, txtPrefix, new LinearLayout[]{lytSuffix, lytSex, lytDay, lytMonth, lytYear, lytCountry});
            }
        });
        edtFirstName = (OTEditText) view.findViewById(R.id.edtFirstName);
        edtMiddleName = (OTEditText) view.findViewById(R.id.edtMiddleName);
        edtLastName = (OTEditText) view.findViewById(R.id.edtLastName);

        lytSuffix = (LinearLayout) view.findViewById(R.id.lytSuffix);
        txtSuffix = (OTTextView) view.findViewById(R.id.txtSuffix);
        txtSuffix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(Utils.getSuffixList(getActivity()), lytSuffix, txtSuffix, new LinearLayout[]{lytPrefix, lytSex, lytDay, lytMonth, lytYear, lytCountry});
            }
        });

        lytSex = (LinearLayout) view.findViewById(R.id.lytSex);
        txtSex = (OTTextView) view.findViewById(R.id.txtSex);
        txtSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(Utils.getGenderList(getActivity()), lytSex, txtSex, new LinearLayout[]{lytPrefix, lytSuffix, lytDay, lytMonth, lytYear, lytCountry});
            }
        });

        lytDay = (LinearLayout) view.findViewById(R.id.lytDay);
        txtDay = (OTTextView) view.findViewById(R.id.txtDay);
        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(getResources().getStringArray(R.array.array_day), lytDay, txtDay, new LinearLayout[]{lytPrefix, lytSuffix, lytSex, lytMonth, lytYear, lytCountry});
            }
        });

        lytMonth = (LinearLayout) view.findViewById(R.id.lytMonth);
        txtMonth = (OTTextView) view.findViewById(R.id.txtMonth);
        txtMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(Utils.getLocalisedMonth(Locale.getDefault()), lytMonth, txtMonth, new LinearLayout[]{lytPrefix, lytSuffix, lytSex, lytDay, lytYear, lytCountry});
            }
        });

        lytYear = (LinearLayout) view.findViewById(R.id.lytYear);
        txtYear = (OTTextView) view.findViewById(R.id.txtYear);
        txtYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(getResources().getStringArray(R.array.array_year), lytYear, txtYear, new LinearLayout[]{lytPrefix, lytSuffix, lytSex, lytDay, lytMonth, lytCountry});
            }
        });

        edtPaypalEmail = (OTEditText) view.findViewById(R.id.edtPaypalEmail);
        edtConfirmPaypalEmail = (OTEditText) view.findViewById(R.id.edtConfirmPaypalEmail);
        edtCreditCheckRecipient = (OTEditText) view.findViewById(R.id.edtCreditCheckRecipient);
        edtAddressLine1 = (OTEditText) view.findViewById(R.id.edtAddressLine1);
        edtAddressLine2 = (OTEditText) view.findViewById(R.id.edtAddressLine2);
        edtCity = (OTEditText) view.findViewById(R.id.edtCity);
        edtZip = (OTEditText) view.findViewById(R.id.edtZip);
        edtStateProvince = (OTEditText) view.findViewById(R.id.edtStateProvince);

        lytCountry = (LinearLayout) view.findViewById(R.id.lytCountry);
        txtCountry = (OTTextView) view.findViewById(R.id.txtCountry);
        txtCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(getCountryListArray(countryExtData.getCountryList()), lytCountry, txtCountry, new LinearLayout[]{lytCountry, lytSuffix, lytSex, lytDay, lytMonth, lytYear});
            }
        });

        edtMobileCode = (OTEditText) view.findViewById(R.id.edtMobileCode);
        edtMobileNumber = (OTEditText) view.findViewById(R.id.edtMobileNumber);
        edtOtherPhoneCode = (OTEditText) view.findViewById(R.id.edtOtherPhoneCode);
        edtOtherPhoneNumber = (OTEditText) view.findViewById(R.id.edtOtherPhoneNumber);

        txtCancel = (OTTextView) view.findViewById(R.id.txtCancel);
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).finish();
            }
        });
        txtSave = (OTTextView) view.findViewById(R.id.txtSave);
        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //first clear all previos error message view
                lytErrorMessage.removeAllViews();


                ArrayList<String> listError = validateAllInputs();
                if(listError.size() > 0)
                {
                    //show error message
                    lytError.setVisibility(View.VISIBLE);
                    for (int index = 0; index < listError.size(); index++)
                    {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }
                }
                else
                {
                    callSimpleUpdateProfileApi();
                }
            }
        });
        txtPasswordHintLabel = (OTTextView) view.findViewById(R.id.txtPasswordHintLabel);
        localiseUI();
    }

    private void resolveScrollingConflict()
    {
        final ScrollView svPrefix = (ScrollView) view.findViewById(R.id.svPrefix);
        final ScrollView svSuffix = (ScrollView) view.findViewById(R.id.svSuffix);
        final ScrollView svSex = (ScrollView) view.findViewById(R.id.svSex);
        final ScrollView svDay = (ScrollView) view.findViewById(R.id.svDay);
        final ScrollView svMonth = (ScrollView) view.findViewById(R.id.svMonth);
        final ScrollView svYear = (ScrollView) view.findViewById(R.id.svYear);
        final ScrollView svCountry = (ScrollView) view.findViewById(R.id.svCountry);
        view.findViewById(R.id.svParent).setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event)
            {
                svPrefix.getParent().requestDisallowInterceptTouchEvent(false);
                svSuffix.getParent().requestDisallowInterceptTouchEvent(false);
                svSex.getParent().requestDisallowInterceptTouchEvent(false);
                svDay.getParent().requestDisallowInterceptTouchEvent(false);
                svMonth.getParent().requestDisallowInterceptTouchEvent(false);
                svYear.getParent().requestDisallowInterceptTouchEvent(false);
                svCountry.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        Utils.setInterceptTouchEvent(new View[]{svPrefix, svSuffix, svSex, svDay, svMonth, svYear, svCountry});

        view.findViewById(R.id.lytMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svPrefix.setVisibility(View.GONE);
                svSuffix.setVisibility(View.GONE);
                svDay.setVisibility(View.GONE);
                svMonth.setVisibility(View.GONE);
                svYear.setVisibility(View.GONE);
                svCountry.setVisibility(View.GONE);
            }
        });
    }


    /**
     * used to get array of String of country
     * @param countryList
     * @return
     */
    private String[] getCountryListArray(ArrayList<CountryList> countryList)
    {
        String[] ary = new String[countryList.size()];
        for (int index = 0; index < countryList.size(); index++)
        {
            ary[index] = countryList.get(index).getCountry();
        }
        return ary;
    }

    /**
     * used to validate all input fields and return list of errors
     * @return
     */
    private ArrayList<String> validateAllInputs()
    {
        ArrayList<String> listError = new ArrayList<>();

        if(!Utils.isValidEmail(edtEmail.getText().toString().trim()))
        {
            listError.add(localization.getERRInvalidD7EmailAddressErrorMessage());
        }

        if(!edtEmail.getText().toString().trim().equals(edtConfirmEmail.getText().toString().trim())
                || !Utils.isValidEmail(edtEmail.getText().toString().trim()))
        {
            listError.add(localization.getERRInvalidD7EmailAddressErrorMessage());
        }

        if(cbChangePassword.isChecked() && Utils.compareDefaultValues(edtPassword, ""))
        {
            listError.add(loginData.getPasswordvalidationRequired().getUpdateErrorHelpMsg());
        }

        if(!edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString().trim()))
        {
            listError.add(localization.getPasswordConfirmPassDoNotMatch());
        }

        if(Utils.compareDefaultValues(txtPrefix, localization.getLABLPrefixErrorLabel()))
        {
            listError.add(localization.getLABLPrefixInputErrLabel());
        }

        if(Utils.compareDefaultValues(txtPrefix, localization.getLABLPrefixErrorLabel()+"*"))
        {
            listError.add(localization.getLABLPrefixInputErrLabel());
        }

        if(Utils.compareDefaultValues(edtFirstName, ""))
        {
            listError.add(localization.getERRInvalidFirstNameErrorMessage());
        }

        if(Utils.compareDefaultValues(edtLastName, ""))
        {
            listError.add(localization.getERRInvalidLastNameErrorMessage());
        }


        return listError;
    }

    /**
     * return index position of array-string of months
     * @param month
     * @return
     */
    private int getCalMonth(String month) {

        String ary[] = getResources().getStringArray(R.array.array_month);
        for (int index = 0; index < ary.length; index++) {
            if(ary[index].equals(month))
                return index + 1;
        }

        return 1;
    }

    private void callSimpleUpdateProfileApi() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SIMPLE_UPDATE_PROFILE);

        loader.show();
        JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("UserName", edtUsernameEmail.getText().toString().trim());
            requestObject.put("CustomerId", Integer.toString(loginData.getCustomerId()));
            requestObject.put("Password", edtPassword.getText().toString().trim());
            requestObject.put("PassChangeCheck", cbChangePassword.isChecked() ? "1" : "0");
            requestObject.put("OPAlert", cbAlert.isChecked() ? "1" : "0");
            requestObject.put("ReceiveOffers", cbSpecialOffer.isChecked() ? "1" : "0");
            requestObject.put("PrefixId", Integer.toString(Utils.getPrefixId(getActivity(), txtPrefix.getText().toString().trim())));
            requestObject.put("FirstName", edtFirstName.getText().toString().trim());
            requestObject.put("MiddleName", edtMiddleName.getText().toString().trim());
            requestObject.put("LastName", edtLastName.getText().toString().trim());
            requestObject.put("SuffixId", Integer.toString(Utils.getSuffixId(getActivity(), txtSuffix.getText().toString().trim())));
            requestObject.put("EmailId", edtEmail.getText().toString().trim());
            requestObject.put("Sex", Integer.toString(Utils.getGenderId(getActivity(), txtSex.getText().toString().trim())));
            requestObject.put("BirthDay", txtDay.getText().toString().trim());
            requestObject.put("BirthMonth", Utils.getEquivalentLocalisedMonth(txtMonth.getText().toString().trim(), Locale.getDefault(), Utils.getLocalForCommunication()));
            requestObject.put("BirthYear", txtYear.getText().toString().trim());
            requestObject.put("Address1", edtAddressLine1.getText().toString().trim());
            requestObject.put("Address2", edtAddressLine2.getText().toString().trim());
            requestObject.put("City", edtCity.getText().toString().trim());
            requestObject.put("ZipCode", edtZip.getText().toString().trim());
            requestObject.put("State", edtStateProvince.getText().toString().trim());
            requestObject.put("CountryId", (Utils.getCountryId(countryExtData.getCountryList(), txtCountry.getText().toString().trim())));
            requestObject.put("TelephoneCodeEXT", edtMobileCode.getText().toString().trim());
            requestObject.put("TelephoneMainPart", edtMobileNumber.getText().toString().trim());
            requestObject.put("AlternateTelephoneCode", edtOtherPhoneCode.getText().toString().trim());
            requestObject.put("AlternateTelephoneNumberMainPart", edtOtherPhoneNumber.getText().toString().trim());
            requestObject.put("DefaultPayPalAccount", edtPaypalEmail.getText().toString().trim());
            requestObject.put("CreditRecipient", edtCreditCheckRecipient.getText().toString().trim());

            /*json.put("UserName", username);
            json.put("CustomerId", CustomerId);
            json.put("Password", password);
            json.put("PassChangeCheck", chk_pswd);
            json.put("OPAlert", chk_alert);
            json.put("ReceiveOffers", chk_wish);
            json.put("PrefixId", pre_id + "");
            json.put("FirstName", fName);
            json.put("MiddleName", mName);
            json.put("LastName", lName);
            json.put("SuffixId", sufId + "");
            json.put("EmailId", email);
            json.put("Sex", sexId + "");
            json.put("BirthDay", birthDay);
            json.put("BirthMonth", birthMonth);
            json.put("BirthYear", birthYear);
            json.put("Address1", add1);
            json.put("Address2", add2);
            json.put("City", city);
            json.put("ZipCode", postalCode);
            json.put("State", state);
            json.put("CountryId", countryId);
            json.put("TelephoneCodeEXT", pExtension.replace("+",""));
            json.put("TelephoneMainPart", pMobile);
            json.put("AlternateTelephoneCode", sExtension.replace("+",""));
            json.put("AlternateTelephoneNumberMainPart", sMobile);
            json.put("DefaultPayPalAccount", defaultPaypal);
            json.put("CreditRecipient", defaultCredit);*/

        }
        catch (Exception e)
        {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }


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
                        CompleteProfile completeProfile = ParseManager.getInstance().fromJSON(response, CompleteProfile.class);

                        if(completeProfile.getResult().equals(getString(R.string.string_success)))
                        {
                            if(cbChangePassword.isChecked())
                            {
                                Utils.setPassword(getActivity(), edtPassword.getText().toString().trim());
                            }
                            errorMessage = null;
                            callLoginAPI();
                        }
                        else if(completeProfile.getResult().equals(getString(R.string.string_failure)))
                        {
                            if(completeProfile.getIsPasswrdValidationFail())
                            {
                                ArrayList<PasswordValidationData> listErrorPassword = completeProfile.getPasswordErrorList().getPasswordValidationData();
                                for (int index = 0; index < listErrorPassword.size(); index++) {
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listErrorPassword.get(index).getErrorMessage()));
                                }
                            }
                            else {
                                lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), completeProfile.getMessage()));
                            }
                            loader.dismiss();
                        }
                        else
                        {
                            //Error
                            Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                            loader.dismiss();
                        }


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


    private void callLoginAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_GET_LOGIN) + getString(R.string.URL_LOGIN);


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("customer.userName", Utils.getUsername(getActivity()));
            requestObject.put("customer.passwd", Utils.getPassword(getActivity()));
            requestObject.put("isFromMyaccountPage", "1");
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
                        LoginData loginData = ParseManager.getInstance().fromJSON(response, LoginData.class);

                        if (loginData.getResult().equals(getString(R.string.string_success))) {
                            //set login data
                            Utils.setLoginData(getActivity(), response.toString());
                            DashboardFragment dashboardFragment = (DashboardFragment) getActivity().getSupportFragmentManager().findFragmentByTag(new DashboardFragment().getClass().getName());
                            if(dashboardFragment == null)
                            {
                                Utils.moveToFragment(getActivity(), new DashboardFragment(), loginData, 0);
                            }
                            else
                            {
                                communicateToFragment(loginData);
                                ((MainActivity)getActivity()).onBackPressed();
                            }

                        } else if (loginData.getResult().equals(getString(R.string.string_failure))) {
                            Utils.setLoginData(getActivity(), null);
                        }


                        loader.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.string_common_error_message));
                loader.hide();
            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    private void communicateToFragment(LoginData updateLoginData)
    {
        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFragmentName((new DashboardFragment()).getClass().getName());
        data.setLoginData(updateLoginData);
        communicator.onResponse(data);
    }


    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("Dashboard fragment called");
        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }




}

package com.optiontown.app.view.fragment.login;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.login.PasswordValidationData;
import com.optiontown.app.model.session.SessionIdData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 02-07-2016.
 */
public class CreateAccountFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    private OTTextView txtLogin;
    private OTTextView txtAlreadyMemberLabel;
    private OTTextView txtPrefixLabel;
    private OTTextView txtPrefix;
    private OTTextView txtFirstNameLabel;
    private OTEditText edtFirstName;
    private OTTextView txtLastNameLabel;
    private OTEditText edtLastName;
    private OTTextView txtEmailLabel;
    private OTEditText edtEmail;
    private OTTextView txtConfirmEmailLabel;
    private OTEditText edtConfirmEmail;
    private OTTextView txtPasswordLabel;
    private OTEditText edtPassword;
    private OTTextView txtPasswordHintLabel;
    private OTTextView txtConfirmPasswordLabel;
    private OTEditText edtConfirmPassword;
    private OTTextView txtSubmit;
    private OTTextView txtClickingSubmitLabel;
    private OTTextView txtTermsOfService;
    private OTTextView txtPrivacyPolicy;
    private CheckBox cbJoinBuzz;
    private CheckBox cbSpecialOffer;
    private CheckBox cbAcceptTerm;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private LoginData loginData;
    private LinearLayout lytPrefix;
    InternationalizeData localization ;
    ScrollView svParent;
    private TextView txt_plzacceptterms;
    private SessionIdData sessionData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_create_account, container, false);

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);
            sessionData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(getActivity())), SessionIdData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        getUIReference();
        localiseUI();

        //updateUI();

        return view;

    }

    private void localiseUI(){
        if(localization == null)
        {
            return;
        }

        ((OTTextView) view.findViewById(R.id.txtMyOptiontownLabel)).setText(localization.getLABLMACreateAccountPageHeadingLabel());
        ((OTTextView) view.findViewById(R.id.txtAlreadyMemberLabel)).setText(localization.getLABLMAAccountMemberLabel());
        ((OTTextView) view.findViewById(R.id.txtFieldsMandatoryLabel)).setText(localization.getLABLMandatoryAllLabel());

        ((OTTextView) view.findViewById(R.id.txtPrefixLabel)).setText(localization.getLABLPrefixLabel());
         txtPrefix.setText(localization.getLABLPrefixLabel());
        ((OTTextView) view.findViewById(R.id.txtFirstNameLabel)).setText(localization.getLABLFirstNameLabel());
        ((OTEditText) view.findViewById(R.id.edtFirstName)).setHint(localization.getLABLFirstNameLabel());
        ((OTTextView) view.findViewById(R.id.txtLastNameLabel)).setText(localization.getLABLLastNameLabel());
        ((OTEditText) view.findViewById(R.id.edtLastName)).setHint(localization.getLABLLastNameLabel());
        ((OTTextView) view.findViewById(R.id.txtEmailLabel)).setText(localization.getLABLEmailLabel());
        ((OTTextView) view.findViewById(R.id.txtConfirmEmailLabel)).setText(localization.getLABLConfirmEmailInputLabel());
        ((OTTextView) view.findViewById(R.id.txtPasswordLabel)).setText(localization.getLABLPasswordLabel());
        ((OTTextView) view.findViewById(R.id.txtConfirmPasswordLabel)).setText(localization.getLABLConfirmPasswordLabel());

        ((OTTextView) view.findViewById(R.id.txt_joinbuz)).setText(localization.getLABLTGPAlertBoxHeadingLabel());
        ((OTTextView) view.findViewById(R.id.txt_signup)).setText(localization.getLABLTGPAlertBoxHelpLabel());
        ((CheckBox) view.findViewById(R.id.cbSpecialOffer)).setText(localization.getLABLSpecialOfferLabel());
        ((OTTextView) view.findViewById(R.id.txtTermsOfService)).setText(localization.getLABLTermsLabel());

        ((OTTextView) view.findViewById(R.id.txtSubmit)).setText(localization.getLABLSubmitButtonLabel());
        ((OTTextView) view.findViewById(R.id.txtClickingSubmitLabel)).setText(localization.getLABLMACreateAccountTermsIAcceptLabel());
        ((OTTextView) view.findViewById(R.id.txtPrivacyPolicy)).setText(localization.getLABLPrivacyPolicyLabel());

        txtPasswordHintLabel.setText(sessionData.getPasswordValidatioJson().getPasswordRule().getPasswordHelpMsg());

    }

    private void getUIReference()
    {
        txt_plzacceptterms = (TextView) view.findViewById(R.id.txt_plzacceptterms);

        svParent = (ScrollView) view.findViewById(R.id.svParent);

        final ScrollView svPrefix = (ScrollView) view.findViewById(R.id.svPrefix);
        svParent.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event)
            {
                svPrefix.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        Utils.setInterceptTouchEvent(new View[]{svPrefix});

        view.findViewById(R.id.lytMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svPrefix.setVisibility(View.GONE);
            }
        });

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);


        txtAlreadyMemberLabel = (OTTextView) view.findViewById(R.id.txtAlreadyMemberLabel);
        txtAlreadyMemberLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new LoginFragment(), null, 0);
            }
        });
        txtPrefixLabel = (OTTextView) view.findViewById(R.id.txtPrefixLabel);
        lytPrefix = (LinearLayout) view.findViewById(R.id.lytPrefix);
        txtPrefix = (OTTextView) view.findViewById(R.id.txtPrefix);
        txtPrefix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Utils.createDropdownView(Utils.getPrefixList(getActivity()), lytPrefix, txtPrefix, new LinearLayout[]{});
            }
        });
        txtFirstNameLabel = (OTTextView) view.findViewById(R.id.txtFirstNameLabel);
        edtFirstName = (OTEditText) view.findViewById(R.id.edtFirstName);
        txtLastNameLabel = (OTTextView) view.findViewById(R.id.txtLastNameLabel);
        edtLastName = (OTEditText) view.findViewById(R.id.edtLastName);
        txtEmailLabel = (OTTextView) view.findViewById(R.id.txtEmailLabel);
        edtEmail = (OTEditText) view.findViewById(R.id.edtEmail);
        txtConfirmEmailLabel = (OTTextView) view.findViewById(R.id.txtConfirmEmailLabel);
        edtConfirmEmail = (OTEditText) view.findViewById(R.id.edtConfirmEmail);
        txtPasswordLabel = (OTTextView) view.findViewById(R.id.txtPasswordLabel);
        edtPassword = (OTEditText) view.findViewById(R.id.edtPassword);
        txtPasswordHintLabel = (OTTextView) view.findViewById(R.id.txtPasswordHintLabel);
        txtConfirmPasswordLabel = (OTTextView) view.findViewById(R.id.txtConfirmPasswordLabel);
        edtConfirmPassword = (OTEditText) view.findViewById(R.id.edtConfirmPassword);
        txtSubmit = (OTTextView) view.findViewById(R.id.txtSubmit);
        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //first clear all previos error message view
                svParent.fullScroll(ScrollView.FOCUS_UP);
                lytErrorMessage.removeAllViews();



                ArrayList<String> listError = validateAllInputs();
                if(listError.size() > 0)
                {
                    lytError.setVisibility(View.VISIBLE);
                    //show error message
                    for (int index = 0; index < listError.size(); index++)
                    {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }
                }
                else
                {
                    lytError.setVisibility(View.GONE);
                    //call api to register
                    lytError.setVisibility(View.GONE);
                    callCreateAccountApi();
                }
            }
        });
        txtClickingSubmitLabel = (OTTextView) view.findViewById(R.id.txtClickingSubmitLabel);
        cbJoinBuzz = (CheckBox) view.findViewById(R.id.cbJoinBuzz);
        cbSpecialOffer = (CheckBox) view.findViewById(R.id.cbSpecialOffer);
        cbAcceptTerm = (CheckBox) view.findViewById(R.id.cbAcceptTerm);
        txtTermsOfService = (OTTextView) view.findViewById(R.id.txtTermsOfService);
        /*txtTermsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new TermsServicePolicyFragment(),  getString(R.string.URL_METHOD_TERMS_OF_SERVICE), 0);
            }
        });*/
        txtPrivacyPolicy = (OTTextView) view.findViewById(R.id.txtPrivacyPolicy);
        txtPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new TermsServicePolicyFragment(), getString(R.string.URL_METHOD_PRIVACY_POLICY), 0);
            }
        });


        String mainString = localization.getLABLTermConditionErrLabel();
        int pos = mainString.indexOf(localization.getLABLTermsLabel());
        String s1 = mainString.substring(0, pos);
        String s2 = mainString.substring(pos);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString str1= new SpannableString(s1);
        str1.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str1.length(), 0);
        builder.append(str1);
        SpannableString str2 = new SpannableString(s2);
        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                // do some thing
                Utils.moveToFragment(getActivity(), new TermsServicePolicyFragment(),  getString(R.string.URL_METHOD_TERMS_OF_SERVICE), 0);
            }
        };
        str2.setSpan(span1, 0, str2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        str2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, str2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(str2);
        txt_plzacceptterms.setText(builder);
        txt_plzacceptterms.setMovementMethod(LinkMovementMethod.getInstance());

    }

    /**
     * used to validate all input fields and return list of errors
     * @return
     */
    private ArrayList<String> validateAllInputs()
    {
        ArrayList<String> listError = new ArrayList<>();

        if(txtPrefix.getText().toString().equals(localization.getLABLPrefixLabel()))
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

        if(!edtEmail.getText().toString().trim().equals(edtConfirmEmail.getText().toString().trim())
                || !Utils.isValidEmail(edtEmail.getText().toString().trim()))
        {
            listError.add(localization.getERRInvalidD7EmailAddressErrorMessage());
        }

        if(Utils.compareDefaultValues(edtPassword, ""))
        {
            listError.add(sessionData.getPasswordValidatioJson().getPasswordRule().getUpdateErrorHelpMsg());
        }

        if(!edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString().trim()))
        {
            listError.add(localization.getPasswordConfirmPassDoNotMatch());
        }

        if(!cbAcceptTerm.isChecked())
        {
            listError.add(localization.getLABLTermConditionErrLabel());
        }

        return listError;
    }

    private void callCreateAccountApi()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_ADD_MOBILE_USER) + getString(R.string.URL_CREATE_ACCOUNT);


        JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("customer.prefixid", Integer.toString(Utils.getPrefixId(getActivity(), txtPrefix.getText().toString().trim())));
            requestObject.put("customer.firstName", edtFirstName.getText().toString().trim());
            requestObject.put("customer.middleName", "");
            requestObject.put("customer.lastName", edtLastName.getText().toString().trim());
            requestObject.put("customer.emailAddress", edtEmail.getText().toString().trim());
            requestObject.put("customer.confirmEmailAddress", edtConfirmEmail.getText().toString().trim());
            requestObject.put("customer.password", edtPassword.getText().toString().trim());
            requestObject.put("customer.confirmpassword", edtConfirmPassword.getText().toString().trim());
            requestObject.put("customer.TelephoneExt", "");
            requestObject.put("customer.TelephoneMainPart", "");
            requestObject.put("customer.checkForRules", Boolean.toString(cbAcceptTerm.isChecked()));
            requestObject.put("customer.sex", Integer.toString(getResources().getIntArray(R.array.array_sex_id)[1]));
            requestObject.put("BirthDay", "");
            requestObject.put("BirthMonth", "");
            requestObject.put("BirthYear", "");
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
                        loginData = ParseManager.getInstance().fromJSON(response, LoginData.class);

                        //show error message received
                        lytErrorMessage.removeAllViews();
                        lytError.setVisibility(View.VISIBLE);

                        if(loginData.getResult().equals(getString(R.string.string_success)))
                        {
                            //set login data
                            Utils.setLoginData(getActivity(), response.toString());

                            Utils.clearLoginFromBackStack(getActivity());

                            Utils.setUsername(getActivity(), edtEmail.getText().toString().trim());
                            Utils.setPassword(getActivity(), edtPassword.getText().toString().trim());

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                Utils.setUsernameForFingerprint(getActivity(), edtEmail.getText().toString().trim());
                                Utils.setPasswordForFingerprint(getActivity(), edtPassword.getText().toString().trim());
                            }

                            Utils.moveToFragment(getActivity(), new DashboardFragment(), null, 0);
                        }
                        else if(loginData.getResult().equals(getString(R.string.string_failure)))
                        {
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





}

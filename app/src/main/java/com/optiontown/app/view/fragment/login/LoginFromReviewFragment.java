package com.optiontown.app.view.fragment.login;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.login.SelectedUserData;
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

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * Created by amit on 24-06-2016.
 */
public class LoginFromReviewFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    private OTEditText edtUsername;
    private OTEditText edtPassword;
    private OTTextView txtForgotPassword;
    private OTTextView txtLogin;
    private OTTextView txtCreateAccount;
    private LoginData loginData;
    private Communicator communicator;
    private SelectedUserData selectedUserData;
    InternationalizeData localization;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private LoginData loginDataFromArgument;
    private RelativeLayout lytCaptchaParent;
    private String radomValue;
    private OTEditText edtCaptcha;
    private LinearLayout lytCaptcha;
    private ImageButton btnCaptchaRefresh;
    private OTTextView txtCaptchaLabel;
    private OTTextView txtCaseSensitiveInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_login_from_review, container, false);

        loginDataFromArgument = (LoginData)(getArguments().getSerializable(getString(R.string.key_serializable)));

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        getUIReference();

        radomValue =  generateRandomString(6);
        //updateUI();

        return view;

    }

    private void localiseUI() {
        ((OTTextView) view.findViewById(R.id.txtLoginLabel)).setText(localization.getLABLMALoginPageHeadingLabel());
        txtForgotPassword.setText(localization.getLABLForgotUsernameAndPasswordLabel());
        txtLogin.setText(localization.getLABLLoginLabel());
        txtCreateAccount.setText(localization.getLABLMACreateLabel());
        edtPassword.setHint(localization.getLABLPasswordLabel());
    }

    private void updateUI()
    {
        if(loginData.getResult().equals(getString(R.string.string_failure)))
        {
            Utils.showToast(getActivity(), getString(R.string.warning_common_input_error_message));
        }
        else if(loginData.getResult().equals(getString(R.string.string_success)))

        {
            //save json in sp
            String loginJson = ParseManager.getInstance().toJSON(loginData);
            sp.put(getString(R.string.key_json_loginData), loginJson);

            //update review fragment and move to review fragment
            FragmentCommunicationData data = new FragmentCommunicationData();
            data.setFragmentName(new ReviewFragment().getClass().getName());
            data.setLoginData(loginData);
            data.setSelectedUserData(selectedUserData);
            data.setCallReviewAPI(true);
            communicator.onResponse(data);

            Utils.clearLoginFromBackStack(getActivity());
            //update top action bar
            Utils.updateActionBarForFeatures(getActivity(), new ReviewFragment().getClass().getName());
        }
    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("LoginFragment >> onAttach(Activity) called");
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    private void getUIReference()
    {
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

        edtUsername = (OTEditText) view.findViewById(R.id.edtUsername);
        edtPassword = (OTEditText) view.findViewById(R.id.edtPassword);

        lytCaptchaParent = (RelativeLayout) view.findViewById(R.id.lytCaptchaParent);
        lytCaptcha = (LinearLayout) lytCaptchaParent.findViewById(R.id.lytCaptcha);
        edtCaptcha = (OTEditText)lytCaptchaParent.findViewById(R.id.edtCaptcha);
        btnCaptchaRefresh = (ImageButton) lytCaptchaParent.findViewById(R.id.btnCaptchaRefresh);
        txtCaptchaLabel = (OTTextView) lytCaptchaParent.findViewById(R.id.txtCaptchaLabel);
        txtCaseSensitiveInfo = (OTTextView) lytCaptchaParent.findViewById(R.id.txtCaseSensitiveInfo);

        lytCaptchaParent.setVisibility(getActivity().getResources().getBoolean(R.bool.bool_enable_captcha_verification)?View.VISIBLE:View.GONE);

        txtCreateAccount = (OTTextView) view.findViewById(R.id.txtCreateAccount);
        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new CreateAccountFromReviewFragment(), loginDataFromArgument, 0);
            }
        });
        txtLogin = (OTTextView) view.findViewById(R.id.txtProceedToPayment);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytError.setVisibility(View.GONE);
                if(edtUsername.getText().toString().trim().length() == 0 || edtPassword.getText().toString().trim().length() == 0)
                {
                    lytErrorMessage.removeAllViews();
                    lytError.setVisibility(View.VISIBLE);
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), getString(R.string.warning_common_input_error_message)));
                }
                else
                {
                    Utils.isValidEmailAddress(getActivity(), edtUsername.getText().toString());
                    //TODO have to register recaptcha api for this app from optiontown app.
                    if (getActivity().getResources().getBoolean(R.bool.bool_enable_captcha_verification)) {

                        String string = edtCaptcha.getText().toString();
                        Utils.DEBUG("Captcha Value :" + radomValue + "\nEditTextValue : " + string);
                        if (string.equals(radomValue)) {
                            callLoginApi();
                            lytError.setVisibility(View.GONE);
                        } else {
                            lytErrorMessage.removeAllViews();
                            lytError.setVisibility(View.VISIBLE);
                            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), getString(R.string.enter_valid_captcha)));
                        }

                    }
                    else {
                        callLoginApi();
                    }

                }
            }
        });

        btnCaptchaRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lytCaptcha.removeAllViews();
                edtCaptcha.setText("");
                radomValue = generateRandomString(6);
            }
        });

        txtForgotPassword = (OTTextView) view.findViewById(R.id.txtForgotPassword);
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new ForgotPasswordFragment(), null, 0);

            }
        });

        localiseUI();
    }

    private String generateRandomString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder( len );
        SecureRandom rnd = new SecureRandom();
        for( int i = 0; i < len; i++ ) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        displayCaptcha(sb.toString());
        return sb.toString();
    }

    private void displayCaptcha(String randomString) {
        Utils.DEBUG("Random Value : "+randomString);

        LinearLayout lytCaptcha = (LinearLayout) lytCaptchaParent.findViewById(R.id.lytCaptcha);
        //setting length for edittext
        edtCaptcha.setFilters(new InputFilter[] { new InputFilter.LengthFilter(randomString.length()) });

        if (randomString != null && (!randomString.equals(""))){
            for (int i = 0; i < randomString.length(); i++) {
                TextView rndText = new TextView(getActivity());
                rndText.setText(randomString.charAt(i)+"");
                rndText.setTextColor(Color.parseColor("#ffffff"));
                rndText.setTextSize(Utils.convertPixelToDp(getActivity(),25));
                rndText.setPadding(5,5,5,5);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                //layoutParams.gravity = i%2==0 ? Gravity.BOTTOM : Gravity.TOP;
                rndText.setLayoutParams(layoutParams);
                rndText.setGravity(i%2==0 ? Gravity.BOTTOM : Gravity.TOP);
                lytCaptcha.addView(rndText);
            }
        }
    }


    private void callLoginApi()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_GET_LOGIN) + getString(R.string.URL_LOGIN);


        final JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("customer.userName", edtUsername.getText().toString().trim());
            requestObject.put("customer.passwd", edtPassword.getText().toString().trim());
            requestObject.put("isFromMyaccountPage", "0");
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

                    if(loginData.getResult().equals("Success"))
                    {
                        lytError.setVisibility(View.GONE);

                        if(loginData.getPasswordvalidationRequired().getPasswordRequired() == 1)
                        {
                            //set login data
                            Utils.setLoginData(getActivity(), response.toString());

                            Utils.setUsername(getActivity(), edtUsername.getText().toString().trim());
                            Utils.setPassword(getActivity(), edtPassword.getText().toString().trim());

                            if(loginDataFromArgument != null)
                            {
                                //get ffp info and pass to CreateAccountFromReviewFragment
                                loginData.setFFpnumberSortDesc(loginDataFromArgument.getFFpnumberSortDesc());
                                loginData.setFFPNumberMandatory(loginDataFromArgument.getFFPNumberMandatory());
                                loginData.setFFpnumberErrorMessage(loginDataFromArgument.getFFpnumberErrorMessage());
                            }
                            Utils.clearLoginFromBackStack(getActivity());
                            Utils.moveToFragment(getActivity(), new CreateAccountFromReviewFragment(), loginData, 1);//last param 1
                        }
                        else
                        {
                            if (loginData.getCustomerType() == getResources().getInteger(R.integer.value_customer_type_1))
                            {
                                //set login data
                                Utils.setLoginData(getActivity(), response.toString());

                                Utils.setUsername(getActivity(), edtUsername.getText().toString().trim());
                                Utils.setPassword(getActivity(), edtPassword.getText().toString().trim());

                                //incomplete login data, need complete data, ask to complete details first.
                                if(loginDataFromArgument != null)
                                {
                                    //get ffp info and pass to CreateAccountFromReviewFragment
                                    loginData.setFFpnumberSortDesc(loginDataFromArgument.getFFpnumberSortDesc());
                                    loginData.setFFPNumberMandatory(loginDataFromArgument.getFFPNumberMandatory());
                                    loginData.setFFpnumberErrorMessage(loginDataFromArgument.getFFpnumberErrorMessage());
                                }
                                Utils.clearLoginFromBackStack(getActivity());
                                Utils.moveToFragment(getActivity(), new CreateAccountFromReviewFragment(), loginData, 0);//last param 0
                            } else if (loginData.getCustomerType() == getResources().getInteger(R.integer.value_customer_type_2)) {
                                //set login data
                                Utils.setLoginData(getActivity(), response.toString());

                                Utils.setUsername(getActivity(), edtUsername.getText().toString().trim());
                                Utils.setPassword(getActivity(), edtPassword.getText().toString().trim());

                                //complete login data, call api to get all associated users
                                callAddUserApi();
                            }
                        }




                    }else if(loginData.getResult().equals("Failure")){

                        lytErrorMessage.removeAllViews();
                        lytError.setVisibility(View.VISIBLE);
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), loginData.getMessage()));
                    }

                    /*else
                    {
                        try {
                            Utils.showToast(getActivity(), response.getString("Message").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }*/

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

    private void callAddUserApi()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_ADD_USER);


        JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("customerId", Integer.toString(loginData.getCustomerId()));
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
                        selectedUserData = ParseManager.getInstance().fromJSON(response, SelectedUserData.class);
                        updateUI();
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

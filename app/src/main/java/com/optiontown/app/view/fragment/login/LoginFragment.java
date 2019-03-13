package com.optiontown.app.view.fragment.login;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.selectproduct.FlightPassDealData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.redeem.SelectBookFlightFragment;
import com.optiontown.app.view.fragment.login.fingerprint.FingerprintHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by amit on 02-07-2016.
 */
public class LoginFragment extends BaseFragment {
    private View view;
    private AppSharedPrefs sp;
    private OTTextView txtLogin;
    private OTTextView txtMyOptiontown;
    private OTEditText edtUsername;
    private OTEditText edtPassword;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private OTTextView txtForgotPassword;
    InternationalizeData localization;
    private String moveTo;
    private OTTextView txt_join_optiontown;
    private OTTextView txt_simple_easy_way;
    private OTTextView txt_view_youor_previous;
    private OTTextView txt_news_and_quick;
    private AppDialogLoader loader;
    private RelativeLayout lytCaptchaParent;
    private String radomValue;
    private OTEditText edtCaptcha;
    private LinearLayout lytCaptcha;
    private ImageButton btnCaptchaRefresh;
    private OTTextView txtCaptchaLabel;
    private OTTextView txtCaseSensitiveInfo;
    private LinearLayout lay_fingerprint;
    private FingerprintManager fingerprintManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private static final String KEY_NAME = "paraskey";
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;
    private static final String DIALOG_FRAGMENT_TAG = "myFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        moveTo = ((String) getArguments().getSerializable(getString(R.string.key_serializable)));

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        getUIReference();
        localiseUI();
        radomValue =  generateRandomString(6);
        //updateUI();

        return view;

    }

    private void localiseUI() {

        ((OTTextView) view.findViewById(R.id.txtLoginLabel)).setText(localization.getLABLMALoginPageHeadingLabel());
        txtForgotPassword.setText(localization.getLABLForgotUsernameAndPasswordLabel());
        txtLogin.setText(localization.getLABLLoginLabel());
        txtMyOptiontown.setText(localization.getLABLJoinOptiontownLabel());
        ((OTTextView) view.findViewById(R.id.txt_joinbuz)).setText(localization.getLABLTGPAlertBoxHeadingLabel());
        ((OTTextView) view.findViewById(R.id.txt_signup)).setText(localization.getLABLTGPAlertBoxHelpLabel());
        txt_join_optiontown.setText(localization.getLABLMALoginJoinHeadingLabel());
        txt_simple_easy_way.setText(localization.getLABLMALoginJoinBenefit1Message());
        txt_view_youor_previous.setText(localization.getLABLMALoginJoinBenefit2Message());
        txt_news_and_quick.setText(localization.getLABLMALoginJoinBenefit3Message());
        edtUsername.setHint(localization.getLABLUsernameLabel());
        edtPassword.setHint(localization.getLABLPasswordLabel());
    }

    private void getUIReference() {

        txt_join_optiontown = (OTTextView) view.findViewById(R.id.txt_join_optiontown);
        txt_simple_easy_way = (OTTextView) view.findViewById(R.id.txt_simple_easy_way);
        txt_view_youor_previous = (OTTextView) view.findViewById(R.id.txt_view_youor_previous);
        txt_news_and_quick = (OTTextView) view.findViewById(R.id.txt_news_and_quick);


        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

        lytCaptchaParent = (RelativeLayout) view.findViewById(R.id.lytCaptchaParent);
        lytCaptcha = (LinearLayout) lytCaptchaParent.findViewById(R.id.lytCaptcha);
        edtCaptcha = (OTEditText) lytCaptchaParent.findViewById(R.id.edtCaptcha);
        btnCaptchaRefresh = (ImageButton) lytCaptchaParent.findViewById(R.id.btnCaptchaRefresh);
        txtCaptchaLabel = (OTTextView) lytCaptchaParent.findViewById(R.id.txtCaptchaLabel);
        txtCaseSensitiveInfo = (OTTextView) lytCaptchaParent.findViewById(R.id.txtCaseSensitiveInfo);

        lytCaptchaParent.setVisibility(getActivity().getResources().getBoolean(R.bool.bool_enable_captcha_verification) ? View.VISIBLE : View.GONE);

        edtUsername = (OTEditText) view.findViewById(R.id.edtUsername);
        edtPassword = (OTEditText) view.findViewById(R.id.edtPassword);


        txtLogin = (OTTextView) view.findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytError.setVisibility(View.GONE);
                if (edtUsername.getText().toString().trim().length() == 0 || edtPassword.getText().toString().trim().length() == 0) {
                    lytErrorMessage.removeAllViews();
                    lytError.setVisibility(View.VISIBLE);
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), getString(R.string.warning_common_input_error_message)));
                } else {
                    Utils.isValidEmailAddress(getActivity(), edtUsername.getText().toString());
                    if (getActivity().getResources().getBoolean(R.bool.bool_enable_captcha_verification)) {
                        String string = edtCaptcha.getText().toString();
                        Utils.DEBUG("Captcha Value :" + radomValue + "\nEditTextValue : " + string);
                        if (string.equals(radomValue)) {
                            callLoginApi(false);
                            lytError.setVisibility(View.GONE);
                        } else {
                            lytErrorMessage.removeAllViews();
                            lytError.setVisibility(View.VISIBLE);
                            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), getString(R.string.enter_valid_captcha)));
                        }
                    } else {
                        callLoginApi(false);
                        lytError.setVisibility(View.GONE);
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

        txtMyOptiontown = (OTTextView) view.findViewById(R.id.txtMyOptiontown);
        txtMyOptiontown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new CreateAccountFragment(), null, 0);
                lytError.setVisibility(View.GONE);
            }
        });

        txtForgotPassword = (OTTextView) view.findViewById(R.id.txtForgotPassword);
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new ForgotPasswordFragment(), null, 0);
                lytError.setVisibility(View.GONE);
            }
        });

        lay_fingerprint = (LinearLayout) view.findViewById(R.id.lay_fingerprint);

        if (getResources().getBoolean(R.bool.bool_enable_fingerprint_verification)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && Utils.checkingFingerprint_isEnabled(getActivity())
                && Utils.getUsernameForFingerprint(getActivity()) != null) {
            fingerprintManager = getActivity().getSystemService(FingerprintManager.class);
            setFingerPrintUI();
        } else {
            lay_fingerprint.setVisibility(View.GONE);
        }
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

    }

    private void setFingerPrintUI() {

        lay_fingerprint.setVisibility(View.VISIBLE);
        lay_fingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (Utils.checkingFingerprint_isHardwareDetected(getActivity())) {
                        // If everything goes fine then register finger print authentication
                        try {
                            generateKey();
                        } catch (FingerprintException e) {
                            e.printStackTrace();
                        }

                        if (initCipher()) {
                            //If the cipher is initialized successfully, then create a CryptoObject instance//
                            cryptoObject = new FingerprintManager.CryptoObject(cipher);

                            showPopUpForFingerPrint(getActivity(), fingerprintManager, cipher);

                        }
                    }
                }
            }
        });

    }

    //Create the generateKey method that we’ll use to gain access to the Android keystore and generate the encryption key//

    private void generateKey() throws FingerprintException {
        try {
            // Obtain a reference to the Keystore using the standard Android keystore container identifier (“AndroidKeystore”)//
            keyStore = KeyStore.getInstance("AndroidKeyStore");

            //Generate the key//
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            //Initialize an empty KeyStore//
            keyStore.load(null);

            //Initialize the KeyGenerator//
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                keyGenerator.init(new

                        //Specify the operation(s) this key can be used for//
                        KeyGenParameterSpec.Builder(KEY_NAME,
                        KeyProperties.PURPOSE_ENCRYPT |
                                KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)

                        //Configure this key so that the user has to confirm their identity with a fingerprint each time they want to use it//
                        .setUserAuthenticationRequired(true)
                        .setEncryptionPaddings(
                                KeyProperties.ENCRYPTION_PADDING_PKCS7)
                        .build());
            }

            //Generate the key//
            keyGenerator.generateKey();

        } catch (KeyStoreException
                | NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | CertificateException
                | IOException exc) {
            exc.printStackTrace();
            throw new FingerprintException(exc);
        }
    }

    //Create a new method that we’ll use to initialize our cipher//
    @TargetApi(Build.VERSION_CODES.M)
    public boolean initCipher() {
        try {
            //Obtain a cipher instance and configure it with the properties required for fingerprint authentication//
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);


        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            System.out.println("------------------Cipher Initialized >>>> : " + cipher.toString());
            //Return true if the cipher has been initialized successfully//
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {

            //Return false if cipher initialization failed//
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    private class FingerprintException extends Exception {
        public FingerprintException(Exception e) {
            super(e);
        }
    }

    public void showPopUpForFingerPrint(final Context context, FingerprintManager fingerprintManager, Cipher cipher) {
        if (context != null) {
            final Dialog dialogmsg = new Dialog(context);
            dialogmsg.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT));
            dialogmsg.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogmsg.setCanceledOnTouchOutside(false);
            dialogmsg.getWindow().setLayout(ActionBar.LayoutParams.FILL_PARENT,
                    ActionBar.LayoutParams.FILL_PARENT);
            dialogmsg.setContentView(R.layout.fingerprint_dialog_container);
            dialogmsg.show();

            final ImageView fingerprint_icon = (ImageView) dialogmsg.findViewById(R.id.fingerprint_icon);
            final TextView fingerprint_status = (TextView) dialogmsg.findViewById(R.id.fingerprint_status);
            fingerprint_icon.setImageResource(R.drawable.ic_fingerprint_black_24dp);
            fingerprint_status.setTextColor(context.getResources().getColor(R.color.caldroid_gray));
            fingerprint_status.setText("Touch Fingerprint Sensor !");
            Button mCancelButton = (Button) dialogmsg.findViewById(R.id.cancel_button);
            mCancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialogmsg.dismiss();

                }
            });


            View mFingerprintContent = dialogmsg.findViewById(R.id.fingerprint_container);
            FingerprintHandler helper = new FingerprintHandler(context, new FingerprintHandler.onFingerPrintAuthenticationStatus() {
                @Override
                public void onSuccessFingerprint(String authentication) {

                    //Toast.makeText(context, "Success!", Toast.LENGTH_LONG).show();
                    fingerprint_icon.setImageResource(R.drawable.ic_fingerprint_green_24dp);
                    fingerprint_status.setTextColor(context.getResources().getColor(R.color.color_font_green));
                    fingerprint_status.setText("Fingerprint Authentication Successfull !");
                    callLoginApi(true);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            dialogmsg.dismiss();

                        }
                    }, 3000);
                }

                @Override
                public void onFailedFingerprint(String authentication) {

                    if (authentication.contains("!!")) {
                        fingerprint_icon.setImageResource(R.drawable.ic_fingerprint_black_24dp);
                        fingerprint_status.setTextColor(context.getResources().getColor(R.color.caldroid_gray));
                        fingerprint_status.setText("Touch Fingerprint Sensor !");
                    } else {
                        fingerprint_icon.setImageResource(R.drawable.ic_fingerprint_error);
                        fingerprint_status.setTextColor(context.getResources().getColor(R.color.color_font_red));
                        fingerprint_status.setText(authentication);
                    }

                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                helper.startAuth(fingerprintManager, new FingerprintManager.CryptoObject(cipher));
            }


        }
    }


    private void callLoginApi(final boolean isFromFingerprint) {


        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_GET_LOGIN) + getString(R.string.URL_LOGIN);


        JSONObject requestObject = new JSONObject();
        try {

            if (isFromFingerprint) {
                requestObject.put("customer.userName", Utils.getUsernameForFingerprint(getActivity()));
                requestObject.put("customer.passwd", Utils.getPasswordForFingerprint(getActivity()));
                requestObject.put("isFromMyaccountPage", "1");
            } else {
                requestObject.put("customer.userName", edtUsername.getText().toString().trim());
                requestObject.put("customer.passwd", edtPassword.getText().toString().trim());
                requestObject.put("isFromMyaccountPage", "1");
            }


        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }
        loader = AppDialogLoader.getLoader(getActivity());
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
                        LoginData loginData = ParseManager.getInstance().fromJSON(response, LoginData.class);

                        //show error message received
                        lytErrorMessage.removeAllViews();
                        lytError.setVisibility(View.VISIBLE);

                        if (loginData.getResult().equals(getString(R.string.string_success))) {
                            //set login data
                            Utils.setLoginData(getActivity(), response.toString());

                            Utils.clearLoginFromBackStack(getActivity());

                            Utils.setUsername(getActivity(), edtUsername.getText().toString().trim());
                            Utils.setPassword(getActivity(), edtPassword.getText().toString().trim());

                            if (!isFromFingerprint) {
                                //when direct login : clean previous fingerprint data :
                                // ask user to register this login for future fingerprint authentication on dashboard
                                // first check if same login entered again
                                if(!(Utils.getUsernameForFingerprint(getActivity()) != null &&
                                        Utils.getUsernameForFingerprint(getActivity()).equals(edtUsername.getText().toString())))
                                {
                                    Utils.setUsernameForFingerprint(getActivity(), null);
                                    Utils.setPasswordForFingerprint(getActivity(), null);

                                }
                            }

                            if (loginData.getPasswordvalidationRequired().getPasswordRequired() == 1) {
                                //move to my profile page, asking user to change the password
                                Utils.moveToFragment(getActivity(), new MyProfileFragment(), loginData.getPasswordvalidationRequired().getUpdateErrorHelpMsg(), 0);
                            } else {
                                //move to
                                if (moveTo == null) {
                                    Utils.moveToFragment(getActivity(), new DashboardFragment(), null, 0);
                                } else if (moveTo.equals(new SelectBookFlightFragment().getClass().getName())) {
                                    Utils.moveToFragment(getActivity(), new SelectBookFlightFragment(), null, 0);
                                }
                            }


                            lytError.setVisibility(View.GONE);
                        } else if (loginData.getResult().equals(getString(R.string.string_failure))) {
                            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), loginData.getMessage()));
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
}

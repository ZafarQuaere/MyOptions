package com.optiontown.app.view.fragment.fpo.redeem.mmp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.ForgotPasswordData;
import com.optiontown.app.model.redeem.mmp.MMP;
import com.optiontown.app.model.redeem.mmp.MmpLabel;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemViewDetailsFragment;
import com.optiontown.app.view.fragment.login.TermsServicePolicyFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zafar.imam on 11-10-2016.
 */
public class MMPChangePasswordFragment extends BaseFragment implements View.OnClickListener{

    private View view;
    private TextInputLayout tilNewPassword,tilOldPassword,lytChangePassword,lytUpdateUsers,lytInvoice;
    private OTEditText editOldPassword,editConfirmPassword;
    private OTEditText editNewPassword;
    private OTTextView txtMandatoryFieldLabel,txtSave;
    private String oldPassword,newPassword,confirmPassword;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private ArrayList<Object> listError;
    private MMP mmp;
    private OTTextView txtPasswordHelpLabel;
    private InternationalizeData localization;
    private TextInputLayout tilConfirmPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_my_password, container, false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(),false);

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }

        mmp = (MMP) getArguments().getSerializable(getString(R.string.key_serializable));
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        txtMandatoryFieldLabel = (OTTextView) view.findViewById(R.id.txtMandatoryFieldLabel);
        txtMandatoryFieldLabel.setText("*" + localization.getLABLMandatoryFieldWithoutAsteriskLabel());
        editOldPassword = (OTEditText) view.findViewById(R.id.editOldPassword);

        tilNewPassword = (TextInputLayout) view.findViewById(R.id.tilNewPassword);
        tilNewPassword.setHint(localization.getLABL_New_Password_Label());

        tilConfirmPassword = (TextInputLayout) view.findViewById(R.id.tilConfirmPassword);
        tilConfirmPassword.setHint(localization.getLABLConfirmPasswordLabel());

        tilOldPassword = (TextInputLayout) view.findViewById(R.id.tilOldPassword);
        tilOldPassword.setHint(localization.getLABL_Old_Password_Label());

        editNewPassword = (OTEditText) view.findViewById(R.id.editNewPassword);
        editConfirmPassword = (OTEditText) view.findViewById(R.id.editConfirmPassword);

        txtSave = (OTTextView) view.findViewById(R.id.txtSave);
        txtSave.setText(localization.getLABLSaveLabel());
        txtSave.setOnClickListener(this);

        txtPasswordHelpLabel = (OTTextView) view.findViewById(R.id.txtPasswordHelpLabel);
        if(mmp != null && mmp.getPasswordValidationRequired() != null)
        {
            txtPasswordHelpLabel.setText(mmp.getPasswordValidationRequired().getUpdateErrorHelpMsg());
        }


       /* SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString str1= new SpannableString("Old Password");
        str1.setSpan(new ForegroundColorSpan(Color.GRAY), 0, str1.length(), 0);
        builder.append(str1);
        SpannableString str2 = new SpannableString("*");
        str2.setSpan(new ForegroundColorSpan(Color.RED), 0, str2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(str2);
        editOldPassword.setHint(builder);*/
        //tilOldPassword.setHint(builder);
       // tilOldPassword.setHint(builder);
    }

    @Override
    public void onClick(View v) {
        oldPassword = editOldPassword.getText().toString().trim();
        newPassword = editNewPassword.getText().toString().trim();
        confirmPassword = editConfirmPassword.getText().toString().trim();
        if (v == txtSave){
            changePassword();

        }

    }

    private void changePassword() {

        listError = new ArrayList<>();

        if (oldPassword.equalsIgnoreCase("")){

            listError.add(localization.getLABL_Err_Enter_Old_Password_Label());
        }
        if (newPassword.equalsIgnoreCase("")){

            listError.add(localization.getLABL_Err_Enter_New_Password_Label());
        }
        if (confirmPassword.equalsIgnoreCase("")){

            listError.add(localization.getLABL_Err_Confirm_Password_Label());
        }
        if (!confirmPassword.matches(newPassword)){

            listError.add(localization.getLABL_Err_Same_Password_Label());
        }

        lytErrorMessage.removeAllViews();
        lytError.setVisibility(View.GONE);
        if(listError.size() > 0)
        {
            lytError.setVisibility(View.VISIBLE);

            for (int index = 0; index < listError.size(); index++) {
                lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
            }
        }else {
            callChangePasswordApi();
        }
    }

    private void callChangePasswordApi()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + "PassSaveandUpdate&checkPoint=1" ;


        JSONObject requestObject = new JSONObject();
        try
        {
            //empty
            requestObject.put("OldPass",oldPassword);
            requestObject.put("NewPass",newPassword);
            requestObject.put("ConfirmPass",confirmPassword);
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
                    public String message;
                    public String status;

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            Utils.showToast(getActivity(),"No response");
                            return;
                        }

                        Utils.DEBUG("onResponse() change password called : " + response.toString());
                        try {
                            message = response.getString("Message");
                            status = response.getString("Result");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(status.equals("Success")) {
                            Utils.showToast(getActivity(),message);
                            ((MainActivity) getActivity()).onBackPressed();
                        }else {

                            lytErrorMessage.removeAllViews();
                            lytError.setVisibility(View.GONE);
                            listError.add(message);
                            if(listError.size() > 0)
                            {
                                lytError.setVisibility(View.VISIBLE);
                                for (int index = 0; index < listError.size(); index++) {
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                                }
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

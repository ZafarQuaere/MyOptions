package com.optiontown.app.view.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.ForgotPasswordData;
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
 * Created by amit on 05-07-2016.
 */
public class ForgotPasswordFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    private OTEditText edtEmail;
    private OTTextView txtSentUsernamePasswordLabel;
    private OTTextView txtSentUsernameLabel;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private OTTextView txtForgotUsernamePasswordLabel;
    private OTTextView txtEnterEmailLabel;
    InternationalizeData localization ;
    ForgotPasswordData forgotPasswordData;
    private ImageView img_frgt_password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

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

    private void localiseUI() {

        txtForgotUsernamePasswordLabel.setText(localization.getLABLForgotUsernameAndPasswordLabel());
        txtEnterEmailLabel.setText(localization.getERRInvalidD7EmailAddressErrorMessage());
        txtSentUsernamePasswordLabel.setText(localization.getLABLSendUsernamePasswordLabel());
        txtSentUsernameLabel.setText(localization.getLABLSendUsernameLabel());

    }

    private void getUIReference()
    {
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        img_frgt_password = (ImageView) view.findViewById(R.id.img_frgt_password);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

        txtForgotUsernamePasswordLabel = (OTTextView) view.findViewById(R.id.txtForgotUsernamePasswordLabel);
        txtEnterEmailLabel = (OTTextView) view.findViewById(R.id.txtEnterEmailLabel);

        edtEmail = (OTEditText) view.findViewById(R.id.edtEmail);
        txtSentUsernamePasswordLabel = (OTTextView) view.findViewById(R.id.txtSentUsernamePasswordLabel);
        txtSentUsernamePasswordLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                lytError.setVisibility(View.GONE);
                showErrorAndCallForgotApi();
            }
        });
        txtSentUsernameLabel = (OTTextView) view.findViewById(R.id.txtSentUsernameLabel);
        txtSentUsernameLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytError.setVisibility(View.GONE);
                showErrorAndCallForgotApi();
            }
        });
    }

    private void showErrorAndCallForgotApi() {
        //first clear all previos error message view
        lytErrorMessage.removeAllViews();


        ArrayList<String> listError = validateAllInputs();
        if(listError.size() > 0)
        {
            //show error message
            lytError.setVisibility(View.VISIBLE);
            img_frgt_password.setImageResource(R.drawable.img_forgot_password);
            for (int index = 0; index < listError.size(); index++)
            {
                lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
            }
        }
        else
        {
            lytError.setVisibility(View.GONE);
            //call api to register
            callForgotPasswordApi();
        }
    }

    private void callForgotPasswordApi()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_FORGOT_LOGIN_PASS_APP) + getString(R.string.URL_FORGOT_PASSWORD) + edtEmail.getText().toString().trim();


        JSONObject requestObject = new JSONObject();
        try
        {
            //empty
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
                        forgotPasswordData = ParseManager.getInstance().fromJSON(response, ForgotPasswordData.class);

                        //remove error message
                        if(forgotPasswordData != null){

                            lytErrorMessage.removeAllViews();
                            if(forgotPasswordData.getMessage().toString() != null){

                                String listResponse = validateForResponse();
                                lytError.setVisibility(View.VISIBLE);
                                if(listResponse != null)
                                {
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listResponse.toString()));
                                }
                                if(forgotPasswordData.getResult().equals("Success")){
                                    img_frgt_password.setImageResource(R.drawable.img_success_forgot_password);
                                    ImageView img_cross_error = (ImageView) lytErrorMessage.findViewById(R.id.img_cross_error);
                                    img_cross_error.setVisibility(View.GONE);
                                }else {
                                    img_frgt_password.setImageResource(R.drawable.img_forgot_password);
                                }


                            }

                        }


                       /* txtForgotUsernamePasswordLabel.setTextColor(getResources().getColor(R.color.color_font_blue));
                        txtEnterEmailLabel.setText(forgotPasswordData.getMessage());

                        edtEmail.setVisibility(View.GONE);
                        txtSentUsernamePasswordLabel.setVisibility(View.GONE);
                        txtSentUsernameLabel.setVisibility(View.GONE);

                        lytError.setVisibility(View.GONE);*/

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
     * used to validate email input field and return error if any
     * @return
     */
    private ArrayList<String> validateAllInputs()
    {
        ArrayList<String> listError = new ArrayList<>();

        if(!Utils.isValidEmail(edtEmail.getText().toString().trim()))
        {
            listError.add(localization.getERRInvalidD7EmailAddressErrorMessage());
        }
        return listError;
    }

    private String validateForResponse()
    {
        String listResponse = null;

        if(forgotPasswordData != null){

            listResponse = forgotPasswordData.getMessage().toString();
        }
        return listResponse;
    }
}

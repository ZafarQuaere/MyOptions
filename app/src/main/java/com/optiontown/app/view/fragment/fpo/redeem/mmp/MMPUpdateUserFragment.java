package com.optiontown.app.view.fragment.fpo.redeem.mmp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.redeem.mmp.ExtensionList;
import com.optiontown.app.model.redeem.mmp.FlightsList;
import com.optiontown.app.model.redeem.mmp.UpdateUserData;
import com.optiontown.app.model.redeem.mmp.UsersAdded;
import com.optiontown.app.model.review.FfpNumberData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zafar.imam on 08-11-2016.
 */

public class MMPUpdateUserFragment extends BFragment implements View.OnClickListener{

    private View view,viewBaClub;
    private AppDialogLoader loader;
    private FlightsList passDetail;
    private UpdateUserData updateUserData;
    private OTTextView txtMandatoryFieldLabel;
    private OTTextView txtCellMobileLabel;
    private OTTextView txtPassengerLabel;
    private OTTextView txtPassenger;
    private OTTextView txtCellMobileExtension;
    private OTEditText editBritishAirwayClubNumber;
    private OTTextView txtBritishAirwayClubNumberLabel;
    private OTTextView txtSave;
    private OTTextView txtSelectedPass;
    private LinearLayout lytPassInfo;
    private LinearLayout lytExtensionScroll;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytError;
    private ScrollView scrollView;
    private String[] strCountryCode;
    private UsersAdded userData;
    private OTEditText editEmail;
    private OTEditText editCellMobileNumber;
    private CountryExtData countryExtData;
    private RelativeLayout rlyUpdateUser;
    private RelativeLayout rlyScroll;
    private String email;
    private String phoneNumber;
    private String mobileExtension;
    private TextInputLayout tilFFPNumberLabel;
    private ImageView imgEdit;
    private ImageView imgErroWala;
    private ImageView imgSuccessWala;
    private TextInputLayout tilEmail;
    List<ExtensionList> extensionLists;
    private ArrayList<String> listError;
    private String errorMsg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmp_update_user, container, false);
        loader = AppDialogLoader.getLoader(getActivity());
        initUi(view);
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "Update Passengers", null);
        try {
            userData = (UsersAdded) getArguments().getSerializable(getString(R.string.key_serializable));

            countryExtData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getCountryExtData(getActivity())), CountryExtData.class);
            strCountryCode = Utils.getCountryListExtArray(countryExtData.getData());

        } catch (Exception e) {
            Utils.ERROR("CreateAccountFromReviewFragment >> Error while parsing json : " + e.toString());
        }
        updateUI(userData);
        return view;
    }

    private void initUi(final View view) {

        editEmail = (OTEditText) view.findViewById(R.id.editEmail);
        editCellMobileNumber = (OTEditText) view.findViewById(R.id.editCellMobileNumber);
        imgErroWala = (ImageView) view.findViewById(R.id.imgErroWala);
        imgSuccessWala = (ImageView) view.findViewById(R.id.imgSuccessWala);
        txtCellMobileLabel = (OTTextView) view.findViewById(R.id.txtCellMobileLabel);
        txtCellMobileExtension = (OTTextView) view.findViewById(R.id.txtCellMobileExtension);


        editBritishAirwayClubNumber = (OTEditText) view.findViewById(R.id.editBritishAirwayClubNumber);
        txtSave = (OTTextView) view.findViewById(R.id.txtSave);
        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);

        imgSuccessWala = (ImageView) view.findViewById(R.id.imgSuccessWala);
        imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
        txtMandatoryFieldLabel = (OTTextView) view.findViewById(R.id.txtMandatoryFieldLabel);
        txtPassenger = (OTTextView) view.findViewById(R.id.txtPassenger);
        txtPassengerLabel = (OTTextView) view.findViewById(R.id.txtPassengerLabel);
        rlyUpdateUser = (RelativeLayout) view.findViewById(R.id.rlyUpdateUser);
        rlyScroll = (RelativeLayout) view.findViewById(R.id.rlyScroll);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        tilEmail = (TextInputLayout) view.findViewById(R.id.tilEmail);
        tilFFPNumberLabel = (TextInputLayout) view.findViewById(R.id.tilFFPNumberLabel);
        lytExtensionScroll = (LinearLayout) view.findViewById(R.id.lytExtensionScroll);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytPassInfo = (LinearLayout) view.findViewById(R.id.lytPassInfo);

        hideDropdownView();


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.clearBackstackTillMMPSelectPassFragment(getActivity());
                //Utils.clearBackstackTillMMPSelectPassFragment("Update Users",getActivity());
            }
        });

        viewBaClub = (View) view.findViewById(R.id.viewBaClub);
        txtCellMobileExtension.setOnClickListener(this);
        txtSave.setOnClickListener(this);
    }

    private void hideDropdownView() {
        rlyUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });

        rlyScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });
       /* txtPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });*/
        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });
        editBritishAirwayClubNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });
        editCellMobileNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });

    /*    txtPassengerLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });

        lytPassInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });

        txtMandatoryFieldLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });

        lytError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.svExtensionCode).setVisibility(View.GONE);
            }

        });
*/
    }


    private void updateUI(UsersAdded userData)
    {

        editEmail.setText(userData.getEmail());
        try {
            updateUserData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getPhoneExtentionData(getActivity())), UpdateUserData.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        extensionLists = updateUserData.getExtensionList();
        mobileExtension = userData.getPhCode();
        Utils.DEBUG("Mobile Extension from Api "+userData.getPhCode());

        txtMandatoryFieldLabel.setText(updateUserData.getMandatoryLabel());
        tilEmail.setHint(updateUserData.getEmailLabel()+"*");

        txtCellMobileLabel.setText(updateUserData.getPrimaryPhoneLabel()+"* ");
        txtSave.setText(updateUserData.getSaveLabel());
        txtPassengerLabel.setText(updateUserData.getPassanger_Label());

        for (int i = 0; i<extensionLists.size(); i++){
            if (mobileExtension.equalsIgnoreCase(extensionLists.get(i).getPhExt())){
                txtCellMobileExtension.setText(extensionLists.get(i).getPhLabel());
                Utils.DEBUG("matching Phone Code "+extensionLists.get(i).getPhLabel());
                mobileExtension = extensionLists.get(i).getPhExt();
            }
            else {
              //  Utils.DEBUG("not matching Phone Code ");
            }
        }


        txtPassenger.setText(userData.getUserName());
        editCellMobileNumber.setText(userData.getPrimaryPhNum());

        txtSelectedPass.setText(userData.getPassLabel());
        /*int i = userData.getDisplayFFPNumber();
        if (i!=0){
            tilFFPNumberLabel.setVisibility(View.VISIBLE);
            editBritishAirwayClubNumber.setText(userData.getFFPNumber());
        }
        else {
            tilFFPNumberLabel.setVisibility(View.GONE);
            editBritishAirwayClubNumber.setVisibility(View.GONE);
            viewBaClub.setVisibility(View.GONE);
        }*/

        if(userData.getDisplayFFPNumber() == 1)
        {
            tilFFPNumberLabel.setVisibility(View.VISIBLE);
            if(userData.getIsFfpMandatory() == 1)
            {
                tilFFPNumberLabel.setHint(updateUserData.getFFpnumberSortDesc() + "*");
            }else {
                tilFFPNumberLabel.setHint(updateUserData.getFFpnumberSortDesc());
            }

            try {
                if (userData.getFFPNumber().length() > 0) {
                    editBritishAirwayClubNumber.setText(userData.getFFPNumber());
                }
            }catch (Exception e){}
        }else {
            tilFFPNumberLabel.setVisibility(View.GONE);
        }


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.txtCellMobileExtension:
                Utils.createDropdownView(strCountryCode, lytExtensionScroll, txtCellMobileExtension, new LinearLayout[]{});
              //  Utils.createDropdownView(strCountryCode, lytCountryCode, txtCountryCode, new LinearLayout[]{lytPrefix, lytDay, lytMonth, lytYear});
                break;

            case R.id.txtSave:
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                listError = validateAllInputs();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(userData.getDisplayFFPNumber() == 1)
                        {
                            if(userData.getIsFfpMandatory() == 1 || (userData.getIsFfpMandatory() == 0 ))
                            {
                                //check ffp number valid or not
                                try {
                                    callToCheckFFPNumberValidity(editBritishAirwayClubNumber.getText().toString().trim());
                                }catch (Exception e){}
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
                                try {
                                    updateUserApi();
                                }catch (Exception e){}
                            }
                        }
                    }
                },200);







                /*if(listError.size() > 0)
                {
                    lytError.setVisibility(View.VISIBLE);
                    imgErroWala.setVisibility(View.VISIBLE);
                    imgSuccessWala.setVisibility(View.GONE);
                    //show error message
                    for (int index = 0; index < listError.size(); index++)
                    {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }
                }
                else
                {
                    lytError.setVisibility(View.GONE);

                    if(userData.getDisplayFFPNumber() == 1)
                    {
                        if(userData.getIsFfpMandatory() == 1
                                || (userData.getIsFfpMandatory() == 0 ))
                        {
                            //check ffp number valid or not
                            callToCheckFFPNumberValidity(editBritishAirwayClubNumber.getText().toString().trim());
                        }
                        else {

                            //callToCheckFFPNumberValidity(editBritishAirwayClubNumber.getText().toString().trim());
                        }
                    }else {

                        //call api
                        updateUserApi();

                    }
                }*/
                break;
        }

    }

    private void callToCheckFFPNumberValidity(final String ffpNumber)
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FfpValidation)+"&isSearchForm=1";


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
                        onSuccessfulResponse(response);

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

        if(response == null)
        {
            return;
        }
        Utils.DEBUG("onResponse() called : " + response.toString());

        FfpNumberData data = ParseManager.getInstance().fromJSON(response, FfpNumberData.class);

        //all ok
        if(data.getIsFFPValid() == 1)
        {
            editBritishAirwayClubNumber.setText(data.getUpdatedFfp().toString());
        }
        else
        {
            if(!listError.contains(userData.getFFpnumberErrorMessage()))
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
            try {
                if(data.getIsFFPValid() == 1) {
                    updateUserApi();
                }
            }catch (Exception e){}
        }
    }

    private ArrayList<String> validateAllInputs() {

        mobileExtension = Utils.getCountryIdExt(countryExtData.getData(), txtCellMobileExtension.getText().toString());
        email = editEmail.getText().toString().trim();
        phoneNumber = editCellMobileNumber.getText().toString().trim();

        Utils.DEBUG("mobile Extension :"+mobileExtension);
        Utils.DEBUG("email email :"+email);
        Utils.DEBUG("mobile phoneNumber :"+phoneNumber);

        ArrayList<String> listError = new ArrayList<>();


        if (email.equalsIgnoreCase("")|| (!Utils.isValidEmail(email))){
            listError.add(getString(R.string.enter_valid_email));
        }
        if (phoneNumber.equalsIgnoreCase("")){
            listError.add(getString(R.string.string_enter_mobile_number));
        }

        if(userData !=null)
        {
            if(userData.getIsFfpMandatory() == 1 && Utils.compareDefaultValues(editBritishAirwayClubNumber, ""))
            {
                listError.add(userData.getFFpnumberErrorMessage());
            }

        }

        return listError;
    }

    private void updateUserApi() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_MMP_SAVE_UPDATE_USER) ;

        loader.show();
        final JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("PassId", userData.getPassId());
            requestObject.put("SelectedUserId", userData.getUserId());
            requestObject.put("MobExt", Utils.getCountryIdExt(countryExtData.getData(), txtCellMobileExtension.getText().toString()));
            requestObject.put("MobNum", phoneNumber);
            requestObject.put("Email", email);
            requestObject.put("FFPnumber", editBritishAirwayClubNumber.getText().toString().trim());

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
                        Utils.DEBUG("saveUpdateUserResponse : " + response.toString());
                        try {
                            JSONObject object = new JSONObject(response.toString());
                            String result = object.getString("Result");
                            if (result!=null){
                                lytError.setVisibility(View.VISIBLE);
                                lytErrorMessage.removeAllViews();
                                if (result.equalsIgnoreCase("Success")){
                                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                                    imgSuccessWala.setVisibility(View.VISIBLE);
                                    imgErroWala.setVisibility(View.GONE);
                                    imgSuccessWala.setImageResource(R.drawable.img_success_forgot_password);
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), object.getString("Message")));
                                    ImageView img_cross_error = (ImageView) lytErrorMessage.findViewById(R.id.img_cross_error);
                                    img_cross_error.setVisibility(View.GONE);

                                }
                                else {
                                    imgSuccessWala.setVisibility(View.VISIBLE);
                                    imgErroWala.setVisibility(View.GONE);
                                    imgSuccessWala.setImageResource(R.drawable.img_forgot_password);
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), object.getString("Message")));
                                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                                    ImageView img_cross_error = (ImageView) lytErrorMessage.findViewById(R.id.img_cross_error);
                                    img_cross_error.setVisibility(View.GONE);

                                }
                            }
                            else {
                                lytError.setVisibility(View.GONE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    @Override
    public void onBackEventPre() {

    }

    @Override
    public void onBackEventPost() {
        Utils.DEBUG("onBackEventPost() called");
        final FragmentActivity activity = getActivity();
        new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Utils.clearBackstackTillMMPSelectPassFragment(activity);
            }
        }.sendEmptyMessage(0);
    }

    @Override
    public void onFocusEvent() {

    }
}

package com.optiontown.app.view.fragment.fpo.redeem.mmp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.MMPAddPassengerRecyclerViewAdapter;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.AddPaxIdentityData;
import com.optiontown.app.model.redeem.mmp.AddUserMmpData;
import com.optiontown.app.model.redeem.mmp.FlightsList;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemAddPaxInfoFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.optiontown.app.utils.Utils.DEBUG;
import static com.optiontown.app.utils.Utils.localization;

/**
 * Created by zafar.imam on 11-10-2016.
 */
public class MMPAddUserFragment extends BaseFragment {

    private View view;
    private LinearLayout lytChangeMyPass,lytAddUsers,lytChangePassword,lytUpdateUsers,lytInvoice;
    private FlightsList passSelected;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private OTTextView txtInformation1;
    private OTTextView txtInformation2;
    private RecyclerView recyclerView;
    private OTTextView txtContinue;
    private LinearLayout layout_adduser;
    private AddUserMmpData addUserMmpdata;
    private AddPaxIdentityData addPaxCompleteData;
    private RelativeLayout main_relative_layout;
    private ImageView imgEdit;
    private OTTextView txtSelectedPass;
    private InternationalizeData localization;
    private OTTextView txtAddPassenger;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_my_flightpass, container, false);
        //Utils.updateBottomBarFpoRedeemForFeatures(view, this.getClass().getName(), false);

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }


        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(),false);


        try {
            passSelected = (FlightsList) getArguments().getSerializable(getString(R.string.key_serializable));
            //Utils.showToast(getActivity(), "pass >>> " +passSelected.getId().toString());
            //Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), passSelected.getI, null);
        }catch (Exception e) {}
        getUIReference();
        callMmpAddUserApi(passSelected.getId().toString());
        return view;
    }

    private void callMmpAddUserApi(String selectedId) {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_METHOD_MMP_ADDUser_LIST)+"&selectedId="+selectedId;


        JSONObject requestObject = new JSONObject();
        try
        {

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
                        Utils.DEBUG("onResponse() addUser called : " + response.toString());
                        //loginDataUpdateProfile = ParseManager.getInstance().fromJSON(response, LoginDataUpdateProfile.class);
                        addUserMmpdata = ParseManager.getInstance().fromJSON(response, AddUserMmpData.class);

                        if(addUserMmpdata !=null)
                        {
                            updateUI();
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

    /*@Override
    public void onBackEventPost() {
        super.onBackEventPost();
        Utils.clearBackstackTillMMPaddUser(getActivity());
        Utils.DEBUG("onBackEventPost() called");
    }*/

    private void getUIReference()
    {
        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);
        txtSelectedPass.setText(passSelected.getLabel().replace("#", ":"));
        imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utils.clearBackstackTillMMPSelectPassFragment("Add Passengers",getActivity());
                Utils.clearBackstackTillMMPSelectPassFragment(getActivity());

            }
        });
        main_relative_layout = (RelativeLayout) view.findViewById(R.id.main_relative_layout);
        main_relative_layout.setVisibility(View.GONE);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        layout_adduser = (LinearLayout) view.findViewById(R.id.layout_adduser);
        layout_adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);
                Utils.moveToFragment(getActivity(), new RedeemAddPaxInfoFragment(),addUserMmpdata,0);

            }
        });
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        txtInformation1 = (OTTextView) view.findViewById(R.id.txtInformation1);
        txtInformation1.setText(localization.getLABL_CurrentListOfPassengers_Label());
        txtInformation2 = (OTTextView) view.findViewById(R.id.txtInformation2);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        txtContinue = (OTTextView) view.findViewById(R.id.txtSave);
        txtContinue.setText(localization.getLABL_Continue_Button_Label());
        txtContinue.setVisibility(View.GONE);
        txtAddPassenger = (OTTextView) view.findViewById(R.id.txtAddPassenger);
        txtAddPassenger.setText(localization.getUserAddNewPass());
    }

    private void updateUI()
    {
        if(addUserMmpdata!=null) {

            main_relative_layout.setVisibility(View.VISIBLE);
            int userRequired = addUserMmpdata.getMaxUserCount() - addUserMmpdata.getUserList().size();
            if (userRequired > 0) {
                layout_adduser.setVisibility(View.VISIBLE);
            } else {
                layout_adduser.setVisibility(View.GONE);
            }


            NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);

            MMPAddPassengerRecyclerViewAdapter adapter = new MMPAddPassengerRecyclerViewAdapter(getActivity(), addUserMmpdata.getMaxUserCount(), addUserMmpdata.getUserList(), new MMPAddPassengerRecyclerViewAdapter.IRecyclerViewHolderClicks() {
                @Override
                public void onClickRecyclerItemSelect(View v, HashMap<View, String> listUsersDetail, boolean addNewUserLayout) {

                    // Utils.showToast(getActivity(), "Selected");
                    lytErrorMessage.removeAllViews();
                    lytError.setVisibility(View.GONE);

                }
            });
            recyclerView.setAdapter(adapter);
        }
    }

    public void updateLayout(FragmentCommunicationData message) {

        addPaxCompleteData = message.getAddPaxCompleteData();
        callAddUserForMMp(passSelected.getId().toString());

    }

    private void callAddUserForMMp(String selectedId) {
        String tag_json_obj = "json_obj_req";
        StringBuilder bufferURL = null;

        if(addPaxCompleteData.isUpgradRequired() == true)
        {
           /* bufferURL = new StringBuilder(getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_UpdatePassUserAjax));
            addPaxCompleteData.setUpgradRequired(false);*/
        }
        else
        {
            bufferURL = new StringBuilder(getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FpoMMPSaveAddUser));
        }
        /*for (int index = 0; index < listSelectedUsers.size(); index++) {
            bufferURL.append("&selectedIndex=" + listSelectedUsers.get(index).getIndex());
        }*/

        /*JSONObject requestObject = null;
        try {
            requestObject = new JSONObject(Utils.readFromAssets(getActivity(), "addNewPassengerTestData.txt"));
            //requestObject = new JSONObject(Utils.readFromAssets(getActivity(), addPaxCompleteData));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        addPaxCompleteData.setPassUserId(selectedId);
        String s = ParseManager.getInstance().toJSON(addPaxCompleteData);

        JSONObject requestObject = null;
        try {
            requestObject = new JSONObject(s);
            requestObject.remove("isUpgradRequired");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                bufferURL.toString(),
                requestObject,
                new Response.Listener<JSONObject>()
                {
                    public String msg;
                    public String result;

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            return;
                        }
                        DEBUG("onResponse() after adding user called : >>>>>>>>>>>>" + response.toString());

                        try {
                             result = response.getString("Result");
                             msg = response.getString("Message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {}

                        if(result.equals("Failure"))
                        {
                            //first clear all previos error message view
                            lytErrorMessage.removeAllViews();
                            lytError.setVisibility(View.GONE);

                            ArrayList<String> listError = validateAllInputsCommon(msg);

                            if (listError.size() > 0) {
                                lytError.setVisibility(View.VISIBLE);
                                //show error message
                                for (int index = 0; index < listError.size(); index++) {
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                                }
                            }

                        }else {
                            Utils.findAndUpdateSelectBookFlightFragment(getActivity());
                            callMmpAddUserApi(passSelected.getId().toString());
                        }
                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private ArrayList<String> validateAllInputsCommon(String msg) {
        ArrayList<String> listError = new ArrayList<>();

            listError.add(msg);

        return listError;
    }
}

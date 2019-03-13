package com.optiontown.app.view.fragment.fpo.redeem.mmp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.optiontown.app.adapter.MMPUpdateUserAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.mmp.ExtensionList;
import com.optiontown.app.model.redeem.mmp.FlightsList;
import com.optiontown.app.model.redeem.mmp.PassUser;
import com.optiontown.app.model.redeem.mmp.UpdateUserData;
import com.optiontown.app.model.redeem.mmp.UsersAdded;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zafar.imam on 11-10-2016.
 */
public class MMPUpdateUserSelectFragment extends BaseFragment {

    private View view;
    private OTTextView txtSelectPassengerLabel;
    private OTTextView txtContinue;
    private OTTextView txtSelectedPass;
    private RelativeLayout rlytUpdateUser;
    private AppDialogLoader loader;
    private FlightsList passDetail;
    private UpdateUserData updateUserData;
    private RecyclerView recyclerViewUpdateUser;
    private RecyclerView.LayoutManager recylerViewLayoutManager;
    private UsersAdded userData;
    private  List<UsersAdded> usersAdded;
    private String passId;
    private ImageView imgEdit;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private ScrollView scrollView;
    private InternationalizeData localization;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmp_update_user_select, container, false);
        loader = AppDialogLoader.getLoader(getActivity());

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }




        try {
            passDetail = (FlightsList) getArguments().getSerializable(getString(R.string.key_serializable));
        } catch (Exception e) {
            Utils.ERROR("CreateAccountFromReviewFragment >> Error while parsing json : " + e.toString());
        }

        initUi(view);

        passId = passDetail.getPassId();


        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        callUpdateUserApi(passId);
        return view;
    }

    private void initUi(View view) {
        txtSelectPassengerLabel = (OTTextView) view.findViewById(R.id.txtSelectPassengerLabel);
        txtSelectPassengerLabel.setText(localization.getLABL_Select_Passenger_Label());
        txtContinue = (OTTextView) view.findViewById(R.id.txtSave);
        txtContinue.setText(localization.getLABL_Continue_Button_Label());
        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);

        imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.clearBackstackTillMMPSelectPassFragment(getActivity());
                //Utils.clearBackstackTillMMPSelectPassFragment("Update Users",getActivity());

            }
        });
        rlytUpdateUser = (RelativeLayout) view.findViewById(R.id.rlytUpdateUser);
        rlytUpdateUser.setVisibility(View.GONE);
        recyclerViewUpdateUser = (RecyclerView) view.findViewById(R.id.recyclerViewUpdateUser);
        recylerViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewUpdateUser.setLayoutManager(recylerViewLayoutManager);

        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first clear all previos error message view
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                ArrayList<String> listError = validateAllInputsCommon();
                if (listError.size() > 0) {
                    lytError.setVisibility(View.VISIBLE);
                    //show error message
                    try {
                        for (int index = 0; index < listError.size(); index++) {
                            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                        }
                    }catch (Exception e){

                    }
                }
                else {
                    Utils.moveToFragment(getActivity(),new MMPUpdateUserFragment(),userData,0);
                }

            }
        });
    }


    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();

        if (userData== null ) {
            listError.add(localization.getLABL_Choose_Passengers_Label());
            scrollView.fullScroll(ScrollView.FOCUS_UP);
        }

        return listError;
    }

    private void callUpdateUserApi(String passId) {
        loader.show();
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_UPDATE_USER)+"&checkPoint=2";
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("PassId", passId);
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
                        Utils.DEBUG("UpdateUser Response : " + response.toString());
                        Utils.setPhoneExtentionData(getActivity(),response.toString());
                        updateUserData = ParseManager.getInstance().fromJSON(response, UpdateUserData.class);

                        if (updateUserData!=null){
                            updateUI(updateUserData);
                        }

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

    private void updateUI(final UpdateUserData updateUserData) {
        final String passId = passDetail.getId();
        txtSelectedPass.setText(passDetail.getLabel().replace("#", " : "));
        loader.dismiss();
        final int displayId = updateUserData.getIsDisplayFFPNumber();
        final int isFfpMandatory = updateUserData.getFFPNumberMandatory();
        List<PassUser> passUsers = updateUserData.getPassUsers();

        rlytUpdateUser.setVisibility(View.VISIBLE);
        for (int i = 0; i < passUsers.size(); i++) {
            if (passId.equalsIgnoreCase(passUsers.get(i).getId())) {

                usersAdded = passUsers.get(i).getUsersAdded();
                MMPUpdateUserAdapter adapter = new MMPUpdateUserAdapter(getActivity(), usersAdded, new MMPUpdateUserAdapter.RecyclerViewHolderClicks() {
                    @Override
                    public void onClickRecyclerItemDetail(View v, UsersAdded label) {
                        userData = label;
                        lytError.setVisibility(View.GONE);
                        userData.setPassId(passId);
                        userData.setDisplayFFPNumber(displayId);
                        userData.setIsFfpMandatory(isFfpMandatory);
                        userData.setFFpnumberHelpMessage(updateUserData.getFFpnumberHelpMessage());
                        userData.setFFpnumberErrorMessage(updateUserData.getFFpnumberErrorMessage());
                        userData.setFFpnumberSortDesc(updateUserData.getFFpnumberSortDesc());
                        userData.setPassLabel(passDetail.getLabel().replace("#", " : "));
                    }

                });
                recyclerViewUpdateUser.setHasFixedSize(true);
                recyclerViewUpdateUser.setAdapter(adapter);

            }
        }
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

}

package com.optiontown.app.view.fragment.fpo.redeem;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.SelectBookFlightRecyclerViewAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.redeem.ListOfPass;
import com.optiontown.app.model.redeem.PassListData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.login.LoginFragment;
import com.optiontown.app.view.fragment.passes.PNRSearchInputFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by amit on 03-09-2016.
 */
public class SelectBookFlightFragment extends BaseFragment
{
    private View view;
    private RecyclerView recyclerView;
    private int customerId;
    private PassListData passListData;
    private OTTextView txtError;
    private InternationalizeData localization;
    private boolean isShowInActivePass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select_book_flight, container, false);


        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());


        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }


        try {
            isShowInActivePass = (boolean) getArguments().getSerializable(getActivity().getString(R.string.key_serializable));
        } catch (Exception e) {

        }
        getReference();

        checkAndGetData();



        return view;
    }

    private void checkAndGetData() {
        if(Utils.isUserLoggedIn(getActivity()))
        {
            try {
                LoginData loginData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLoginData(getActivity())), LoginData.class);
                customerId = loginData.getCustomerId();
                callGetUserFlightPass();
            } catch (JSONException e) {
                Utils.ERROR("SelectBookFlightFragment >> Error while parsing login data : " + e.toString());
            }
        }
        else
        {
            ((FragmentActivity)getActivity()).onBackPressed();
            Utils.moveToFragment(getActivity(), new LoginFragment(), this.getClass().getName(), 0);
        }
    }

    private void getReference() {
        txtError = (OTTextView) view.findViewById(R.id.txtError);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

    }


    public void callGetUserFlightPass() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_setRedeemLoginDetails);

        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("customerId", Integer.toString(customerId));
        } catch (Exception e) {
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
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        passListData = ParseManager.getInstance().fromJSON(response, PassListData.class);

                        try {
                            Utils.setDisplayRedeemMMB(getActivity(),passListData.getTgpAirlineLang().getIsDisplayLeftNavMMB());
                            Utils.setDisplayRedeemMMP(getActivity(),passListData.getTgpAirlineLang().getIsDisplayLeftNavMMP());
                        } catch (Exception e) {
                            //e.printStackTrace();
                        }

                        Utils.updateBottomBarFpoRedeemForFeatures(view, new SelectBookFlightFragment().getClass().getName().toString()+"_" + isShowInActivePass, false);

                        initialize();

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

    private void initialize()
    {

        if(passListData.getListOfPasses() == null && !isShowInActivePass)
        {
            txtError.setText(passListData.getMessage());
            txtError.setVisibility(View.VISIBLE);
        }
        else
        {
            if(isShowInActivePass && (passListData.getListOfInactivePasses() == null || passListData.getListOfInactivePasses().isEmpty()))
            {
                //show error message, we dont have message here
                return;
            }

            txtError.setVisibility(View.GONE);

            NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);

            SelectBookFlightRecyclerViewAdapter adapter = new SelectBookFlightRecyclerViewAdapter(isShowInActivePass, getActivity(), passListData, new SelectBookFlightRecyclerViewAdapter.IRecyclerViewHolderClicks() {
                @Override
                public void onClickRecyclerItem(View v, ListOfPass listOfPass) {

                    //Utils.showToast(getActivity(), "clicked : " + listOfPass.getPassFullView().getConfirmationNumber());
                    int id = v.getId();
                    if(id == R.id.txtViewDetails)
                    {

                        try {
                            Utils.moveToFragment(getActivity(),new RedeemViewDetailsFragment(),listOfPass, isShowInActivePass ? 1 : 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_fpo)))
                        {
                            Utils.moveToFragment(getActivity(),new SearchFlightInputFragment(), listOfPass.getPassFullView().getFlightPassListId(),0);
                        }
                        else
                        {
                            Utils.moveToFragment(getActivity(),new PNRSearchInputFragment(), listOfPass, 0);
                        }

                    }


                }
            });
            recyclerView.setAdapter(adapter);
        }
    }
}

package com.optiontown.app.view.fragment.fpo.redeem.mmb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.redeem.mmb.MMBData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPSelectPassFragment;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 22/11/2016.
 */
public class MmbHomeFragment extends BaseFragment{


    private View view;
    private CardView card_view;
    private RecyclerView recyclerView;
    private LinearLayoutManager recylerViewLayoutManager;
    private AppDialogLoader loader;
    private MMBData mmbData;
    private ArrayList<String> showLabelList;
    private OTTextView txt_label_heading;
    private OTTextView txt_label;
    private LinearLayout lay_main;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_my_booking, container, false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "", null);
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        callApi();
        initUi(view);
        //updateUi();
        return view;
    }

    private void initUi(View view) {

        card_view = (CardView) view.findViewById(R.id.card_view);
        card_view.setVisibility(View.GONE);
        txt_label_heading = (OTTextView) view.findViewById(R.id.txt_label_heading);
        txt_label = (OTTextView) view.findViewById(R.id.txt_label);
        lay_main = (LinearLayout) view.findViewById(R.id.lay_main);
        lay_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.moveToFragment(getActivity(),new MMBSelectPassFragment(), mmbData,0);

            }
        });


    }

    private void callApi() {

        //https://192.168.64.10/getSellerList.do?mobileAction=ManageMybooking
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_MANAGE_MY_BOOKING);
        loader = AppDialogLoader.getLoader(getActivity());
        loader.show();
        JSONObject requestObject = new JSONObject();
        try {

            requestObject.put("selectedpassid", "0");

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
                        Utils.DEBUG("ManageMyBooking Response >>>>>>>>>>: " + response.toString());
                        mmbData = ParseManager.getInstance().fromJSON(response, MMBData.class);

                        updateUi(mmbData);


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

    private void updateUi(MMBData mmbData) {
        String header = mmbData.getManageMyBookingHeadingLabel();
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), header, null);

        card_view.setVisibility(View.VISIBLE);

        if(mmbData != null)
        {
            txt_label_heading.setText(mmbData.getChangeFlightHeadingLabel());
            txt_label.setText(mmbData.getChangeFlightSubheadingLabel());
        }

        loader.dismiss();
    }

}

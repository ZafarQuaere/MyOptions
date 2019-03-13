package com.optiontown.app.view.fragment.fpo.redeem.mmp;

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
import com.optiontown.app.adapter.ManageMyPassAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.mmp.MMP;
import com.optiontown.app.model.redeem.mmp.MmpLabel;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zafar.imam on 10-10-2016.
 */

public class ManageMyPassFragment extends BaseFragment {


    private View view;
    private RecyclerView recyclerView;
    private CardView card_view;
    private RecyclerView.LayoutManager recylerViewLayoutManager;
    private List<MmpLabel> labelList;
    private String mainLabel;
    private List<MmpLabel> showLabelList;
    private AppDialogLoader loader;
    private MMP mmp;
    private InternationalizeData localization;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_my_pass, container, false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }

        callApi();
        initUi(view);
        //updateUi();
        return view;
    }

    private void initUi(View view) {

        card_view = (CardView) view.findViewById(R.id.card_view);
        card_view.setVisibility(View.GONE);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recylerViewLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recylerViewLayoutManager);


    }


    private void callApi() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_MANAGE_YOUR_PASS);
        loader = AppDialogLoader.getLoader(getActivity());
        loader.show();
        JSONObject requestObject = new JSONObject();
        try {
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
                        Utils.DEBUG("ManageMyPassResponse : " + response.toString());
                        mmp = ParseManager.getInstance().fromJSON(response, MMP.class);
                        labelList = mmp.getMmpLabels();
                        mainLabel = labelList.get(0).getManageMypassLabelList();
                        updateUi();


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

    private void updateUi() {
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), mainLabel, null);
        card_view.setVisibility(View.VISIBLE);

        showLabelList = new ArrayList<>();
        if (labelList != null) {

            for (int i = 1; i < labelList.size(); i++) {
                showLabelList.add(labelList.get(i));
            }

            ManageMyPassAdapter adapter = new ManageMyPassAdapter(getActivity(), showLabelList, new ManageMyPassAdapter.RecyclerViewHolderClicks() {
                @Override
                public void onClickRecyclerItemDetail(View v, MmpLabel label) {

                    if (label.getId() == 1) {
                        Utils.showToast(getActivity(), localization.getLABLManageMyPass());
                    } else if (label.getId() == 2) {
                        Utils.moveToFragment(getActivity(),new MMPSelectPassFragment(),label,0);
                    } else if (label.getId() == 3) {
                        Utils.moveToFragment(getActivity(),new MMPSelectPassFragment(),label,0);
                    } else if (label.getId() == 4) {
                        Utils.moveToFragment(getActivity(),new MMPChangePasswordFragment(),mmp,0);
                    } else if (label.getId() == 5) {
                        Utils.moveToFragment(getActivity(),new MMPSelectPassFragment(),label,0);
                    } else if (label.getId() == 6) {
                        Utils.moveToFragment(getActivity(),new MMPSelectPassFragment(),label,0);
                    }

                }
            });
            //   recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            recyclerView.setHasFixedSize(true);

            recyclerView.setAdapter(adapter);
            loader.dismiss();
        }
    }

}

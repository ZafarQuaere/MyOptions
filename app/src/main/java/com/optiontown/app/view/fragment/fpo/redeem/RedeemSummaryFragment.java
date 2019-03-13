package com.optiontown.app.view.fragment.fpo.redeem;

import android.os.Bundle;
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
import com.optiontown.app.adapter.RedeemSearchResultRecyclerViewAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.LegList;
import com.optiontown.app.model.redeem.RedeemSearchResultData;
import com.optiontown.app.model.redeem.Segment;
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
 * Created by amit on 20-09-2016.
 */
public class RedeemSummaryFragment extends BaseFragment
{
    private View view;
    private OTTextView txtInformation;
    private RecyclerView recyclerView;
    private OTTextView txtContinue;
    private RedeemSearchResultData redeemSearchResultData;
    private ArrayList<Itinerarry> listItinarySelected;
    private InternationalizeData localization;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Utils.DEBUG("onCreateView called");
        view = inflater.inflate(R.layout.fragment_redeem_summary, container, false);
        listItinarySelected = (ArrayList<Itinerarry>) getArguments().getSerializable(getString(R.string.key_serializable));
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }



        getUIReference();

        callRedeemReviewAPI();

        return view;
    }

    @Override
    public void onBackEventPost() {
        super.onBackEventPost();
        Utils.clearBackstackTillRedeemSearch(getActivity());
        Utils.DEBUG("onBackEventPost() called");
    }

    private void callRedeemReviewAPI()
    {
        String tag_json_obj = "json_obj_req";


        //----
        String itinaryId = "";
        String segmentId = "";
        for (int i = 0; i < listItinarySelected.size(); i++) {

            ArrayList<Segment> segments = listItinarySelected.get(i).getSegments();
            for (int j = 0; j < segments.size(); j++) {

                ArrayList<LegList> legList = segments.get(j).getLegList();
                for (int k = 0; k < legList.size(); k++) {
                    Utils.DEBUG("selected : " + legList.get(k).getFlightSmallView().getDepartAirlineTime() + "/" + legList.get(k).getFlightSmallView().getArrivalAirlineTime());
                    itinaryId = ((legList.get(k).getFlightSmallView().getIndexes().split("_"))[0]);
                    segmentId = ((legList.get(k).getFlightSmallView().getIndexes().split("_"))[1]);
                }
            }
        }
        //---

        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SelectPassFlightReviewAndreviewEqualsreview)
                + "&ifsIndex=" + itinaryId + "&itinSegIndex=" + segmentId;



        JSONObject requestObject = new JSONObject();
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
                        if(response == null)
                        {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response);
                        redeemSearchResultData = ParseManager.getInstance().fromJSON(response, RedeemSearchResultData.class);
                        updateUI();
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

    private void updateUI()
    {
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), redeemSearchResultData.getAddPassFormLabels().getSelection_Summary_Label(), null);

        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        RedeemSearchResultRecyclerViewAdapter adapter = new RedeemSearchResultRecyclerViewAdapter(this.getClass().getName(), getActivity(), null, 0, redeemSearchResultData, new RedeemSearchResultRecyclerViewAdapter.IRecyclerViewHolderClicks() {

            @Override
            public void onClickRecyclerItemSelect(View v, List<Itinerarry> listItinary)
            {

            }

            @Override
            public void onClickRecyclerItemDetail(View v, Itinerarry itinary)
            {

            }
        });
        txtInformation.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(adapter);
    }

    private void getUIReference()
    {
        txtInformation = (OTTextView) view.findViewById(R.id.txtInformation);
        txtInformation.setVisibility(View.GONE);
        txtInformation.setText(localization.getLABL_Review_Flight_Page_Description_Label());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        txtContinue = (OTTextView) view.findViewById(R.id.txtSave);
        txtContinue.setText(localization.getLABL_Continue_Button_Label());
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new RedeemAddPassengerFragment(), redeemSearchResultData, 0);
            }
        });


    }
}

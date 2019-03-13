package com.optiontown.app.view.fragment.fpo.redeem;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.RedeemSearchResultRecyclerViewAdapter;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.LegList;
import com.optiontown.app.model.redeem.PassDetail;
import com.optiontown.app.model.redeem.RedeemSearchParam;
import com.optiontown.app.model.redeem.RedeemSearchParamOpenJaw;
import com.optiontown.app.model.redeem.RedeemSearchResultData;
import com.optiontown.app.model.redeem.Segment;
import com.optiontown.app.model.redeem.SelectedFlightData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 07-09-2016.
 */
public class RedeemSearchResultFragment extends BaseFragment
{
    private View view;
    private RedeemSearchParam redeemSearchParam;
    private RedeemSearchResultData redeemSearchResultData;
    private RecyclerView recyclerView;
    private OTTextView txtTravelPeriod;
    private OTTextView txtPassengerCabinType;
    private OTTextView txtInformation;
    private OTTextView txtCompareTrip;
    private OTTextView txtFlightByFlight;
    private OTTextView txtContinue;
    private ImageView imgEditSearch;
    private ArrayList<Itinerarry> listItinarySelected = new ArrayList<>();
    //private ArrayList<Itinerarry> listItinarySelectedFnFToAndFrom = new ArrayList<>();
    private int viewType = 0;
    private LinearLayout lytTripType;
    private boolean flagSecondPageCalled = false;
    private RelativeLayout lytParent;
    private FragmentActivity fragmentActivity;
    private Communicator communicator;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Utils.DEBUG("onCreateView called");
        view = inflater.inflate(R.layout.fragment_redeem_search_result, container, false);

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "", null);
        redeemSearchParam = (RedeemSearchParam) getArguments().getSerializable(getString(R.string.key_serializable));

        /*System.out.println("selectedId <<<<<<<<<<<<<<   " + redeemSearchParam.getSelectedpassid().toString());
        System.out.println("tripType <<<<<<<<<<<<<<    " + redeemSearchParam.getJourneyType().toString());
        System.out.println("txtPassenger <<<<<<<<<<<<<<   " + redeemSearchParam.getNumberOfPax().toString());
        System.out.println("txtDepart <<<<<<<<<<<<<<   " + redeemSearchParam.getDepartDateJourney1());
        //System.out.println("txtReturn <<<<<<<<<<<<<<   " + redeemSearchParam.getDe);
        System.out.println("depart1 <<<<<<<<<<<<<<   " + redeemSearchParam.getDepartAirportJourney1());
        System.out.println("arrive1 <<<<<<<<<<<<<<   " + redeemSearchParam.getArriveAirportJourney1());
        System.out.println("departAirportValueIdFlight2 <<<<<<<<<<<<<<   " + redeemSearchParam.getDepartAirportJourney2());
        System.out.println("arriveAirportValueIdFlight2 <<<<<<<<<<<<<<   " + redeemSearchParam.getArriveAirportJourney2());*/

        getUIReference();

        callSelectPassFlightListApi(viewType);


        return view;
    }

    private void getUIReference()
    {
        lytParent = (RelativeLayout) view.findViewById(R.id.lytParent);
        lytParent.setVisibility(View.GONE);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        txtTravelPeriod = (OTTextView) view.findViewById(R.id.txtTravelPeriod);
        txtPassengerCabinType = (OTTextView) view.findViewById(R.id.txtPassengerCabinType);
        txtInformation = (OTTextView) view.findViewById(R.id.txtInformation);
        lytTripType = (LinearLayout) view.findViewById(R.id.lytTripType);
        lytTripType.setVisibility(redeemSearchParam.getJourneyType().equals("1") ? View.GONE : View.VISIBLE);

        txtCompareTrip = (OTTextView) view.findViewById(R.id.txtCompareTrip);
        txtCompareTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItinarySelected.clear();
                viewType = 1;
                adjustColor(viewType);
                callSelectPassFlightListApi(viewType);
            }
        });

        txtFlightByFlight = (OTTextView) view.findViewById(R.id.txtFlightByFlight);
        txtFlightByFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItinarySelected.clear();
                viewType = 0;
                adjustColor(viewType);
                callSelectPassFlightListApi(viewType);
            }
        });

        adjustColor(viewType);

        txtContinue = (OTTextView) view.findViewById(R.id.txtSave);
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(redeemSearchResultData.getItinerarry().size() > 0 && listItinarySelected.size() > 0)
                {
                    if(!flagSecondPageCalled)
                    {
                        callSelectPassFlightListApi(2);
                    }
                    else
                    {
                        if(viewType == 0 && redeemSearchParam.getJourneyType().equals("1"))
                        {
                            Utils.moveToFragment(getActivity(), new RedeemSummaryFragment(), listItinarySelected, 0);
                        }
                        else
                        {
                            callSelectPassFlightListApi(3);
                        }

                    }
                }
                else if(listItinarySelected.isEmpty())
                {
                    lytErrorMessage.removeAllViews();
                    lytError.setVisibility(View.VISIBLE);
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), redeemSearchResultData.getTopMessage()));
                }
            }
        });
        imgEditSearch = (ImageView) view.findViewById(R.id.imgEditSearch);
        imgEditSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicateToFragment(true);
                ((FragmentActivity)getActivity()).onBackPressed();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("RedeemSearchResultFragment >> onAttach(Activity) called");
        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }


    private void communicateToFragment(boolean isModifyPassData)
    {
        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFragmentName((new SearchFlightInputFragment()).getClass().getName());
        data.setRedeemModifyPassDetails(isModifyPassData);
        communicator.onResponse(data);
    }



    private void adjustColor(int type)
    {
        if(type == 0)
        {
            txtFlightByFlight.setTextColor( Color.parseColor("#FF0000"));
            txtFlightByFlight.setPaintFlags(txtFlightByFlight.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

            txtCompareTrip.setTextColor( Color.parseColor("#484848"));
            txtCompareTrip.setPaintFlags(0);
        }
        else if(type  == 1)
        {
            txtCompareTrip.setTextColor( Color.parseColor("#FF0000"));
            txtCompareTrip.setPaintFlags(txtCompareTrip.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

            txtFlightByFlight.setTextColor( Color.parseColor("#484848"));
            txtFlightByFlight.setPaintFlags(0);
        }
    }

    private void callSelectPassFlightListApi(final int type)
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) ;
        String s = null;
        flagSecondPageCalled = false;

        if(type == 0)
        {
            if(redeemSearchParam.getJourneyType().equals("4"))
            {
                RedeemSearchParamOpenJaw data = (RedeemSearchParamOpenJaw) redeemSearchParam;
                url = url + getString(R.string.URL_SelectPassFlightList) + "&ojD1="+ data.getDepartAirportJourney1() + "&ojA1=" + data.getArriveAirportJourney1()+ "&ojD2="  + data.getDepartAirportJourney2()+ "&ojA2=" + data.getArriveAirportJourney2();
            }
            else
            {
                url = url + getString(R.string.URL_SelectPassFlightList);
            }

            s = ParseManager.getInstance().toJSON(redeemSearchParam);
        }
        else if(type == 1)
        {
            url = url + getString(R.string.URL_SelectPassFlightListTab2);
            s = ParseManager.getInstance().toJSON(redeemSearchParam);
        }
        else if(type == 2 || type == 3)
        {
            url = url + getString(R.string.URL_setSelectedFlights);
            SelectedFlightData data = new SelectedFlightData();

            ArrayList<PassDetail> listPass = new ArrayList<>();
            for (int i = 0; i < listItinarySelected.size(); i++) {
                String itinaryId = "";
                ArrayList<Segment> segments = listItinarySelected.get(i).getSegments();
                for (int j = 0; j < segments.size(); j++) {
                    ArrayList<LegList> legList = segments.get(j).getLegList();
                    for (int k = 0; k < legList.size(); k++) {
                        Utils.DEBUG("selected : " + legList.get(k).getFlightSmallView().getDepartAirlineTime() + "/" + legList.get(k).getFlightSmallView().getArrivalAirlineTime());
                        itinaryId = ((legList.get(k).getFlightSmallView().getIndexes().split("_"))[0]);
                    }
                }


                int tab = 0;
                if((redeemSearchParam.getJourneyType().equals("2") || redeemSearchParam.getJourneyType().equals("4")) && viewType == 0)
                {
                    tab = 1;
                }
                else if((redeemSearchParam.getJourneyType().equals("2") || redeemSearchParam.getJourneyType().equals("4")) && viewType == 1)
                {
                    tab = 2;
                }

                PassDetail pd = new PassDetail();
                pd.setIfsIndex(itinaryId);
                pd.setTab(tab+"");
                listPass.add(pd);

            }
            data.setPassDetails(listPass);

            s = ParseManager.getInstance().toJSON(data);
        }



        JSONObject requestObject = null;
        try {
            requestObject = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Utils.DEBUG("" + url);
        Utils.DEBUG("" + requestObject.toString());



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
                        if(type == 2)
                        {
                            if(viewType == 0 && !redeemSearchParam.getJourneyType().equals("1"))
                            {
                                //https://192.168.64.10/getSellerList.do?mobileAction=SecondPartFlightList&ifsIndex=0&itinSegIndex=0
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
                                callSecondPartFlightListAPI(itinaryId, segmentId);
                                listItinarySelected.clear();
                            }
                            else if(viewType == 0 && redeemSearchParam.getJourneyType().equals("1"))
                            {
                                Utils.moveToFragment(getActivity(), new RedeemSummaryFragment(), listItinarySelected, 0);
                            }
                            else if(viewType == 1)
                            {
                                Utils.moveToFragment(getActivity(), new RedeemSummaryFragment(), listItinarySelected, 0);
                            }
                        }
                        else if(type == 3)
                        {
                            Utils.moveToFragment(getActivity(), new RedeemSummaryFragment(), listItinarySelected, 0);
                        }
                        else
                        {
                            redeemSearchResultData = ParseManager.getInstance().fromJSON(response, RedeemSearchResultData.class);
                            initialize(null);
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

    private void callSecondPartFlightListAPI(String ifsIndex, String itinSegIndex)
    {
        String tag_json_obj = "json_obj_req";
        //https://192.168.64.10/getSellerList.do?mobileAction=SecondPartFlightList&ifsIndex=0&itinSegIndex=0
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SecondPartFlightList)
                + "&ifsIndex=" + ifsIndex + "&itinSegIndex=" + itinSegIndex;


        JSONObject requestObject = new JSONObject();

        Utils.DEBUG("" + url);
        Utils.DEBUG("" + requestObject.toString());



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
                        flagSecondPageCalled = true;
                        redeemSearchResultData = ParseManager.getInstance().fromJSON(response, RedeemSearchResultData.class);
                        initialize(null);
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

    public void initialize(FragmentCommunicationData message)
    {

        //Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), redeemSearchResultData.getDepartArr(), null);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), redeemSearchResultData.getDepartArr(), null);
        lytParent.setVisibility(View.VISIBLE);
        txtTravelPeriod.setText(Utils.getMonthDateOfTravel(redeemSearchResultData.getTravelDate()));
        txtPassengerCabinType.setText(redeemSearchResultData.getPassengers() + " . " + redeemSearchResultData.getCabinName() + " . " + redeemSearchResultData.getJourneyType());
        txtInformation.setText(redeemSearchResultData.getTopMessage());
        txtInformation.setTextColor(redeemSearchResultData.getItinerarry().size() > 0 ? Color.parseColor("#42599D") : Color.parseColor("#ff0000"));
        txtCompareTrip.setText(redeemSearchResultData.getCompleteTrip_Label());
        txtFlightByFlight.setText(redeemSearchResultData.getFlightByFlight_Label());
        txtContinue.setText(redeemSearchResultData.getContinue_Label());

        if(message != null)
        {
            boolean flagDuplicate = false;
            for (int i = 0; i < listItinarySelected.size(); i++)
            {
                if(listItinarySelected.get(i).equals(message.getItinerarry()))
                {
                    flagDuplicate = true;
                }
            }

            if(!flagDuplicate)
            {
                if(viewType == 0)
                {
                    if(!redeemSearchParam.getJourneyType().equals("1")) {
                        listItinarySelected.clear();
                    }
                    listItinarySelected.add(message.getItinerarry());
                }
                else
                {
                    listItinarySelected.add(message.getItinerarry());
                }

            }

        }

        ArrayList<Boolean> temp = new ArrayList<>();
        for (int index = 0; index < redeemSearchResultData.getItinerarry().size(); index++) {
            boolean flagAdded = false;
            for (int i = 0; i < listItinarySelected.size(); i++) {
                if(listItinarySelected.get(i).equals(redeemSearchResultData.getItinerarry().get(index)))
                {
                    flagAdded = true;
                    temp.add(true);
                }
            }
            if(!flagAdded)
            {
                temp.add(false);
            }
        }


        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        RedeemSearchResultRecyclerViewAdapter adapter = new RedeemSearchResultRecyclerViewAdapter(this.getClass().getName(), getActivity(), temp, viewType, redeemSearchResultData, new RedeemSearchResultRecyclerViewAdapter.IRecyclerViewHolderClicks() {

            @Override
            public void onClickRecyclerItemSelect(View v, List<Itinerarry> listItinary) {

                lytError.setVisibility(View.GONE);
                listItinarySelected.clear();
                listItinarySelected.addAll(listItinary);
                for (int i = 0; i < listItinarySelected.size(); i++) {
                    ArrayList<Segment> segments = listItinarySelected.get(i).getSegments();
                    for (int j = 0; j < segments.size(); j++) {
                        ArrayList<LegList> legList = segments.get(j).getLegList();
                        for (int k = 0; k < legList.size(); k++) {
                            Utils.DEBUG("selected : " + legList.get(k).getFlightSmallView().getDepartAirlineTime() + "/" + legList.get(k).getFlightSmallView().getArrivalAirlineTime());
                        }
                    }
                }

                /*if(viewType == 0 && (redeemSearchParam.getJourneyType().equals("2") || redeemSearchParam.getJourneyType().equals("4")))
                {
                    if(listItinarySelectedFnFToAndFrom.size() == 0)
                    {
                        listItinarySelectedFnFToAndFrom.addAll(listItinary);
                    }
                    else if(listItinarySelectedFnFToAndFrom.size() == 1)
                    {
                        if(!flagSecondPageCalled)
                        {
                            listItinarySelectedFnFToAndFrom.clear();
                            listItinarySelectedFnFToAndFrom.addAll(listItinary);
                        }
                        else
                        {
                            listItinarySelectedFnFToAndFrom.addAll(listItinary);
                        }
                    }
                    else if(listItinarySelectedFnFToAndFrom.size() == 2)
                    {
                        listItinarySelectedFnFToAndFrom.remove(1);
                        listItinarySelectedFnFToAndFrom.addAll(listItinary);
                    }
                }*/
            }

            @Override
            public void onClickRecyclerItemDetail(View v, Itinerarry itinary)
            {
                itinary.setDepartArr(redeemSearchResultData.getDepartArr());
                itinary.setTravelDate(redeemSearchResultData.getTravelDate());
                itinary.setPassengerCabinTripType(txtPassengerCabinType.getText().toString());
                itinary.setPer_Person(redeemSearchResultData.getPer_Person());
                Utils.moveToFragment(getActivity(), new RedeemSearchResultDetailFragment(), itinary, 0);
            }
        });
        recyclerView.setAdapter(adapter);
    }




}

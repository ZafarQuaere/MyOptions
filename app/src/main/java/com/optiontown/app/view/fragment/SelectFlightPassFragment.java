package com.optiontown.app.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.SelectFlightPassRecyclerViewAdapter;
import com.optiontown.app.model.fpogetpass.FlightCountList;
import com.optiontown.app.model.fpogetpass.FpoGetPassData;
import com.optiontown.app.model.fpogetpass.PassObject;
import com.optiontown.app.model.fpogetpass.ValidityList;
import com.optiontown.app.model.fpogetpass.ZoneMemberSet;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.review.PassCMMIndexData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.fpo.review.ReviewFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by amit on 16-06-2016.
 */
public class SelectFlightPassFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    private FpoGetPassData fpoGetPassData;
    private RecyclerView recyclerView;
    private OTTextView txtPassCount;
    InternationalizeData localization;
    private LinearLayout lytSortingDropDown;
    private OTTextView txtShowAll;
    private OTTextView txtSortingLowToHigh;
    private OTTextView txtSortingHighToLow;
    private OTTextView txtSortingTravelPeriod;
    private OTTextView txtSortingFlights;
    private ArrayList<PassObject> listFiltered = new ArrayList<PassObject>();
    private OTTextView txtMessage;
    private int typeSorting = 3;
    private boolean isFromReview = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select_flight_pass, container, false);

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());



        /*final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                loader.dismiss();
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //---getting data for localisation

                    handler.sendEmptyMessage(0);
                } catch (Exception e) {
                    Utils.ERROR("Error while  : " + e.toString());
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();*/


        callApiForInternationalizeApp();


        return view;
    }


    private void callApiForInternationalizeApp() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_API_LABELS);


        JSONObject requestObject = new JSONObject();
        try {
            //requestObject.put("tgProductId", Integer.toString(getResources().getInteger(R.integer.value_tgProductId_flight_pass)));
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
                        if (response == null) {
                            return;
                        }
                        //Utils.DEBUG("onResponse() localization called : " + response.toString());

                        //save
                        Utils.setInternationalLanguage(getActivity(), response.toString());

                        //--
                        try {
                            InternationalizeData labelLocalization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);
                            Utils.DEBUG("upgrage name : " + labelLocalization.getLABLUpgradeTravelOptionShortLabel());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        //--


                        try {
                            localization = ParseManager.getInstance().fromJSON(response, InternationalizeData.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //initialise shared prefs manager
                        sp = AppSharedPrefs.getInstance(getActivity());

                        Utils.DEBUG(this.getClass().getSimpleName() + " >> received : " + getArguments().toString());

                        fpoGetPassData = ((FpoGetPassData) getArguments().getSerializable(getString(R.string.key_serializable)));
                        Utils.DEBUG("status : " + sp + "/" + fpoGetPassData);
                        setKeyValuesInSharedPrefs();

                        initialize();
                        loader.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.hide();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }




    private void initialize() {



        FrameLayout lytMain = (FrameLayout) view.findViewById(R.id.lytMain);
        lytMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytSortingDropDown.setVisibility(View.GONE);
            }
        });
        lytSortingDropDown = (LinearLayout) view.findViewById(R.id.lytSortingDropDown);

        final OTTextView txtSortingType = (OTTextView) view.findViewById(R.id.txtSortingType);
        txtSortingType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytSortingDropDown.setVisibility(lytSortingDropDown.getVisibility() == View.GONE ? (View.VISIBLE) : (View.GONE));
            }
        });

        txtSortingLowToHigh = (OTTextView) view.findViewById(R.id.txtSortingLowToHigh);
        txtSortingLowToHigh.setText(fpoGetPassData.getPassSortDropDownList().get(0).getPassSortDropDownLabel());
        txtSortingLowToHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSortingType.setText(txtSortingLowToHigh.getText().toString());
                lytSortingDropDown.setVisibility(View.GONE);
                typeSorting = 0;
                Collections.sort(listFiltered, new Comparator<PassObject>() {
                    @Override
                    public int compare(PassObject lhs, PassObject rhs) {
                        //Utils.DEBUG("comparing : " + lhs.getPricePerPass() + "/" + rhs.getPricePerPass());
                        if(lhs.getPricePerPass() == rhs.getPricePerPass())
                        {
                            return 0;
                        }
                        else if(lhs.getPricePerPass() > rhs.getPricePerPass())
                        {
                            return 1;
                        }
                        else
                        {
                            return -1;
                        }
                    }
                });

                /*for (int index = 0; index < listFiltered.size(); index++) {
                    Utils.DEBUG("" + listFiltered.get(index).getPricePerPass());
                }*/
                FragmentCommunicationData fragmentCommunicationData = new FragmentCommunicationData();
                fragmentCommunicationData.setShowAll(true);
                updateRecyclerView(fragmentCommunicationData);
            }
        });


        txtSortingHighToLow = (OTTextView) view.findViewById(R.id.txtSortingHighToLow);
        txtSortingHighToLow.setText(fpoGetPassData.getPassSortDropDownList().get(1).getPassSortDropDownLabel());
        txtSortingHighToLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSortingType.setText(txtSortingHighToLow.getText().toString());
                lytSortingDropDown.setVisibility(View.GONE);
                typeSorting = 1;
                Collections.sort(listFiltered, new Comparator<PassObject>() {
                    @Override
                    public int compare(PassObject lhs, PassObject rhs) {
                        if(lhs.getPricePerPass() == rhs.getPricePerPass())
                        {
                            return 0;
                        }
                        else if(lhs.getPricePerPass() < rhs.getPricePerPass())
                        {
                            return 1;
                        }
                        else
                        {
                            return -1;
                        }
                    }
                });

                for (int index = 0; index < listFiltered.size(); index++) {
                    Utils.DEBUG("" + listFiltered.get(index).getPricePerPass());
                }
                FragmentCommunicationData fragmentCommunicationData = new FragmentCommunicationData();
                fragmentCommunicationData.setShowAll(true);
                updateRecyclerView(fragmentCommunicationData);
            }
        });

        txtSortingTravelPeriod = (OTTextView) view.findViewById(R.id.txtSortingTravelPeriod);
        txtSortingTravelPeriod.setText(fpoGetPassData.getPassSortDropDownList().get(2).getPassSortDropDownLabel());
        txtSortingTravelPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSortingType.setText(txtSortingTravelPeriod.getText().toString());
                lytSortingDropDown.setVisibility(View.GONE);
                typeSorting = 2;
                Collections.sort(listFiltered, new Comparator<PassObject>() {
                    @Override
                    public int compare(PassObject lhs, PassObject rhs) {
                        //return (lhs.getPassValidityValue() > rhs.getPassValidityValue() ? 0 : 1);
                        if(lhs.getPassValidityValue() == rhs.getPassValidityValue())
                        {
                            return 0;
                        }
                        else if(lhs.getPassValidityValue() > rhs.getPassValidityValue())
                        {
                            return 1;
                        }
                        else
                        {
                            return -1;
                        }
                    }
                });

                FragmentCommunicationData fragmentCommunicationData = new FragmentCommunicationData();
                fragmentCommunicationData.setShowAll(true);
                updateRecyclerView(fragmentCommunicationData);
            }
        });

        txtSortingFlights = (OTTextView) view.findViewById(R.id.txtSortingFlights);
        txtSortingFlights.setText(fpoGetPassData.getPassSortDropDownList().get(3).getPassSortDropDownLabel());
        txtSortingType.setText(txtSortingFlights.getText().toString());//default
        txtSortingFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSortingType.setText(txtSortingFlights.getText().toString());
                lytSortingDropDown.setVisibility(View.GONE);
                typeSorting = 3;
                Collections.sort(listFiltered, new Comparator<PassObject>() {
                    @Override
                    public int compare(PassObject lhs, PassObject rhs) {
                        //return (Integer.parseInt(lhs.getCreditValue()) > Integer.parseInt(rhs.getCreditValue()) ? 0 : 1);
                        if(Integer.parseInt(lhs.getCreditValue()) == Integer.parseInt(rhs.getCreditValue()))
                        {
                            return 0;
                        }
                        else if(Integer.parseInt(lhs.getCreditValue()) > Integer.parseInt(rhs.getCreditValue()))
                        {
                            return 1;
                        }
                        else
                        {
                            return -1;
                        }
                    }
                });

                FragmentCommunicationData fragmentCommunicationData = new FragmentCommunicationData();
                fragmentCommunicationData.setShowAll(true);
                updateRecyclerView(fragmentCommunicationData);
            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        listFiltered.clear();
        listFiltered.addAll(getPassObjectForRecycleViewFromFilter(true));
        SelectFlightPassRecyclerViewAdapter adapter = new SelectFlightPassRecyclerViewAdapter(localization,getActivity(), listFiltered, new SelectFlightPassRecyclerViewAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, PassObject passObject) {
                //hide drop down first
                lytSortingDropDown.setVisibility(View.GONE);

                PassCMMIndexData data = new PassCMMIndexData();
                data.setPassIndex(passObject.getPassIndex());
                data.setCmmIndex(passObject.getCmmIndex());

                Utils.moveToFragment(getActivity(), new ReviewFragment(), data, 0);
            }
        }, fpoGetPassData.getPerflightLabel());
        recyclerView.setAdapter(adapter);

        txtPassCount = (OTTextView) view.findViewById(R.id.txtPassCount);
        txtPassCount.setText(listFiltered.size() + " of " + fpoGetPassData.getPassObject().size() + " Passes");
        long current_final_fragment_oncreateview_rendered = System.currentTimeMillis();
        Utils.DEBUG("current_final_fragment_oncreateview_rendered : " + current_final_fragment_oncreateview_rendered);

        txtMessage = (OTTextView) view.findViewById(R.id.txtMessage);
        txtMessage.setText(fpoGetPassData.getError_Message());
        if(fpoGetPassData.getError_Message() == null)
        {
            txtMessage.setVisibility(View.GONE);
        }
        else
        {
            txtMessage.setVisibility(fpoGetPassData.getError_Message().length() > 0 ? View.VISIBLE : View.GONE);
        }

        if(fpoGetPassData.getCountryId() != 0)
        {
            Utils.setUserSelectedCountryId(getActivity(), fpoGetPassData.getCountryId());
            Utils.setUserSelectedLanguageId(getActivity(), fpoGetPassData.getLanguageId());
        }



        txtShowAll = (OTTextView) view.findViewById(R.id.txtShowAll);
        txtShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentCommunicationData data = new FragmentCommunicationData();
                data.setShowAll(true);
                data.setFilterApply(false);
                updateRecyclerView(data);
            }
        });


        if(fpoGetPassData.getPassObject().size() == 1)
        {
            String strPassNotAvailable = fpoGetPassData.getPassObject().get(0).getError_Pass_Not_Available();
            if(strPassNotAvailable != null)
            {
                txtMessage.setText(strPassNotAvailable);
                txtMessage.setVisibility(View.VISIBLE);

                txtShowAll.setOnClickListener(null);

                txtPassCount.setText("0 of 0 Passes");
            }
        }


        localiseUI();
    }

    private void localiseUI() {

        ((OTTextView) view.findViewById(R.id.txtShowAll)).setText(localization.getLABLGetTGP9ShowAllPassLabel());
    }


    /**
     * used to set the values in shared prefs to avoid exception
     */
    private void setKeyValuesInSharedPrefs()
    {
        //Utils.DEBUG("setKeyValuesInSharedPrefs() called ");

        if(fpoGetPassData == null)
        {
            return;
        }

        //for travel zones
        for (int i = 0; i < fpoGetPassData.getZoneMemberSet().size(); i++) {
            if(i == 0)
            {
                sp.put(getString(R.string.key_filter_travel_zone_ZoneId) + "_" + i, (int)sp.get(getString(R.string.key_selected_travel_zone_id)));
            }
            else
            {
                sp.put(getString(R.string.key_filter_travel_zone_ZoneId) + "_" + i, 0);//for zones 0 means not selected, 1 means personalised zone
            }
        }

        //flight
        for (int i = 0; i < fpoGetPassData.getFlightCountList().size(); i++) {
            if(i == 0)
            {
                sp.put(getString(R.string.key_filter_number_flight_creditId) + "_" + i, (int)sp.get(getString(R.string.key_selected_flight_id)));
            }
            else
            {
                sp.put(getString(R.string.key_filter_number_flight_creditId) + "_" + i, 0);
            }
        }

        //validity
        for (int i = 0; i < fpoGetPassData.getValidityList().size(); i++) {
            if(i == 0)
            {
                sp.put(getString(R.string.key_filter_travel_period_validityId) + "_" + i, (int)sp.get(getString(R.string.key_selected_travel_period_month_id))/30);//as id in days so changed
            }
            else
            {
                sp.put(getString(R.string.key_filter_travel_period_validityId) + "_" + i, 0);
            }
        }

        //price per flight
        sp.put(getString(R.string.key_filter_price_per_flight_range_min), fpoGetPassData.getMinPrice());
        sp.put(getString(R.string.key_filter_price_per_flight_range_max), fpoGetPassData.getMaxPrice());


    }

    private ArrayList<PassObject> getPassObjectForRecycleViewFromFilter(boolean flagPrint)
    {
        ArrayList<PassObject> list = fpoGetPassData.getPassObject();

        ArrayList<PassObject> temp = new ArrayList<PassObject>();

        for (int index = 0; index < list.size(); index++)
        {
            boolean flagContainsZone = false;
            boolean flagContainsFlight = false;
            boolean flagContainsTravelPeriod = false;
            boolean flagContainsFlightPerPrice = false;

            ArrayList<ZoneMemberSet> zoneMemberSet = fpoGetPassData.getZoneMemberSet();
            for (int pos = 0; pos < zoneMemberSet.size(); pos++) {

                if((int)sp.get(getString(R.string.key_filter_travel_zone_ZoneId) + "_" + pos) == getZoneId(list.get(index).getPassName()))
                {
                    flagContainsZone = true;
                    break;
                }
            }

            ArrayList<FlightCountList> flightCountList = fpoGetPassData.getFlightCountList();
            for (int pos = 0; pos < flightCountList.size(); pos++) {

                //Utils.DEBUG("common >> filter >> " + (getString(R.string.key_filter_number_flight_creditId) + "_" + pos) + " vs " + (int)sp.get(getString(R.string.key_filter_number_flight_creditId) + "_" + pos));
                if((int)sp.get(getString(R.string.key_filter_number_flight_creditId) + "_" + pos) == Integer.parseInt(list.get(index).getCreditValue()))
                {
                    //Utils.DEBUG("common >> filter >> " + (getString(R.string.key_filter_number_flight_creditId) + "_" + pos) + " vs " + list.get(index).getCreditValue() + " >> added");

                    flagContainsFlight = true;
                    break;
                }
            }

            ArrayList<ValidityList> validityList = fpoGetPassData.getValidityList();
            for (int pos = 0; pos < validityList.size(); pos++) {

                //Utils.DEBUG("common >> filter >> " + (getString(R.string.key_filter_travel_period_validityId) + "_" + pos) + " vs " + (int)sp.get(getString(R.string.key_filter_travel_period_validityId) + "_" + pos));
                if((int)sp.get(getString(R.string.key_filter_travel_period_validityId) + "_" + pos) == (list.get(index).getPassValidityValue()))
                {
                    //Utils.DEBUG("common >> filter >> " + (getString(R.string.key_filter_travel_period_validityId) + "_" + pos) + " vs " + list.get(index).getPassValidityValue() + " >> added");

                    flagContainsTravelPeriod = true;
                    break;
                }
            }

            for (int pos = 0; pos < validityList.size(); pos++)
            {
                int min = (int) sp.get(getString(R.string.key_filter_price_per_flight_range_min));
                int max = (int) sp.get(getString(R.string.key_filter_price_per_flight_range_max));

                if(list.get(index).getPricePerCredit() >= min && list.get(index).getPricePerCredit() <= max)
                {
                    flagContainsFlightPerPrice = true;
                    break;
                }
            }


            //should satisfy all condition
            if(flagContainsZone && flagContainsFlight && flagContainsTravelPeriod && flagContainsFlightPerPrice)
            {
                temp.add(list.get(index));
            }
        }

        if(flagPrint)
        {
            for (int i = 0; i < temp.size(); i++) {
                Utils.DEBUG("filtered : " + temp.get(i).getPassName() + ", "
                        + temp.get(i).getCreditValue() + ", "
                        + temp.get(i).getPassValidityValue() + ", ");
            }
        }
        return temp;
    }


    /**
     * used to get zone id from given zone name
     * @param passName
     * @return
     */
    private int getZoneId(String passName)
    {
        if(passName == null)
        {
            return 0;
        }
        ArrayList<ZoneMemberSet> zoneMemberSet = fpoGetPassData.getZoneMemberSet();

        for (int index = 0; index < zoneMemberSet.size(); index++) {
            if(passName.equals(zoneMemberSet.get(index).getShortDescription()))
            {
                //Utils.DEBUG("getZoneId() >> " + passName);
                return zoneMemberSet.get(index).getZoneId();
            }
        }

        return 0;

    }

    public void updateFromReview(FragmentCommunicationData data)
    {
        Utils.DEBUG("SelectFlightPassFragment >> updateFromReview() called");
        isFromReview = data.isFromReview();
    }

    public void updateRecyclerView(FragmentCommunicationData message)
    {
        Utils.DEBUG("SelectFlightPassFragment >> updateRecyclerView() called");


        if(message.isShowAll())
        {
            listFiltered.clear();
            listFiltered.addAll(fpoGetPassData.getPassObject());

            ArrayList<PassObject> list = new ArrayList<>(listFiltered);//another reference

            if(typeSorting == 0)
            {
                Collections.sort(list, new Comparator<PassObject>() {
                    @Override
                    public int compare(PassObject lhs, PassObject rhs) {
                        //Utils.DEBUG("comparing : " + lhs.getPricePerPass() + "/" + rhs.getPricePerPass());
                        if(lhs.getPricePerPass() == rhs.getPricePerPass())
                        {
                            return 0;
                        }
                        else if(lhs.getPricePerPass() > rhs.getPricePerPass())
                        {
                            return 1;
                        }
                        else
                        {
                            return -1;
                        }
                    }
                });
            }
            else if(typeSorting == 1)
            {
                Collections.sort(list, new Comparator<PassObject>() {
                    @Override
                    public int compare(PassObject lhs, PassObject rhs) {
                        if(lhs.getPricePerPass() == rhs.getPricePerPass())
                        {
                            return 0;
                        }
                        else if(lhs.getPricePerPass() < rhs.getPricePerPass())
                        {
                            return 1;
                        }
                        else
                        {
                            return -1;
                        }
                    }
                });
            }
            else if(typeSorting == 2)
            {
                Collections.sort(list, new Comparator<PassObject>() {
                    @Override
                    public int compare(PassObject lhs, PassObject rhs) {
                        //return (lhs.getPassValidityValue() > rhs.getPassValidityValue() ? 0 : 1);
                        if(lhs.getPassValidityValue() == rhs.getPassValidityValue())
                        {
                            return 0;
                        }
                        else if(lhs.getPassValidityValue() > rhs.getPassValidityValue())
                        {
                            return 1;
                        }
                        else
                        {
                            return -1;
                        }
                    }
                });
            }
            else if(typeSorting == 3)
            {
                Collections.sort(list, new Comparator<PassObject>() {
                    @Override
                    public int compare(PassObject lhs, PassObject rhs) {
                        //return (Integer.parseInt(lhs.getCreditValue()) > Integer.parseInt(rhs.getCreditValue()) ? 0 : 1);
                        if(Integer.parseInt(lhs.getCreditValue()) == Integer.parseInt(rhs.getCreditValue()))
                        {
                            return 0;
                        }
                        else if(Integer.parseInt(lhs.getCreditValue()) > Integer.parseInt(rhs.getCreditValue()))
                        {
                            return 1;
                        }
                        else
                        {
                            return -1;
                        }
                    }
                });
            }





            txtPassCount.setText(list.size() + " of " + fpoGetPassData.getPassObject().size() + " Passes");
            ((SelectFlightPassRecyclerViewAdapter)recyclerView.getAdapter()).notifyDataSetChanged(list);

        }
        else if(message.isFilterApply())
        {
            final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
            loader.show();

            final Handler handler = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    ArrayList<PassObject> list = new ArrayList<>(listFiltered);//another reference
                    txtPassCount.setText(list.size() + " of " + fpoGetPassData.getPassObject().size() + " Passes");
                    ((SelectFlightPassRecyclerViewAdapter)recyclerView.getAdapter()).notifyDataSetChanged(list);
                    loader.hide();
                }
            };
            new Thread()
            {
                @Override
                public void run() {
                    listFiltered.clear();
                    listFiltered.addAll(getPassObjectForRecycleViewFromFilter(true));




                    handler.sendEmptyMessage(0);
                }
            }.start();

        }


    }

    @Override
    public void onBackEventPre() {
        super.onBackEventPre();
        Utils.DEBUG("SelectFlightPassFragment >> onBackEventPre");
    }

    @Override
    public void onBackEventPost() {
        super.onBackEventPost();
        Utils.DEBUG("SelectFlightPassFragment >> onBackEventPost");
    }

    @Override
    public void onFocusEvent() {
        super.onFocusEvent();
        Utils.DEBUG("SelectFlightPassFragment >> onFocusEvent");
        if(isFromReview)
        {
            //reset
            isFromReview = false;
            //user came from Review page
            callApiBackPass();
        }
    }



    private void callApiBackPass() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_backSearchPass);


        JSONObject requestObject = new JSONObject();

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                true,
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
                        //Utils.DEBUG("onResponse() called : " + response.toString());

                        fpoGetPassData = ParseManager.getInstance().fromJSON(response, FpoGetPassData.class);

                        //save in json
                        Utils.setFPOPassData(getActivity(), response.toString());

                        setKeyValuesInSharedPrefs();

                        initialize();

                        loader.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.hide();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
}

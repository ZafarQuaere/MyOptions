package com.optiontown.app.view.fragment.legproducts;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.adapter.FlightSeatViewAdapter;
import com.optiontown.app.adapter.ShowSelectedPassengerAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.legproducthomepage.SearchHelper;
import com.optiontown.app.model.seatview.LegListArray;
import com.optiontown.app.model.seatview.PassengerArray;
import com.optiontown.app.model.seatview.SeatMapRootData;
import com.optiontown.app.model.seatview.SeatMarkarr;
import com.optiontown.app.model.seatview.Seatdetailarr;
import com.optiontown.app.model.seatview.SelectedData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by parasmani.sharma on 22/07/2017.
 */

public class FlightSeatViewFragment extends BaseFragment {

    private View view;
    private String classType;
    private int numberOfColumn = 0;
    private LinearLayout parentViewForRecycler;

    private RecyclerView recycler_seatSummary;
    private GridLayoutManager gridLayoutManager;
    private ShowSelectedPassengerAdapter adapterPassengerList;

    private ScrollView scrollView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private LayoutInflater onSeatSelectionViewInflator;
    private static View SELECTED_SEAT_VIEW;
    private View popupView;
    private RecyclerView recycler_view;
    private RelativeLayout lyt_seat;
    private ImageView img_arrow;
    private AppDialogLoader loader;
    private SeatMapRootData seatMapData;
    private LinearLayout layout_columnSeatMark;
    private RecyclerView lyt_selectedPassenger;
    public ImageLoader imageLoader;
    private OTTextView txt_aircraft_name_top;
    private NetworkImageView img_lavatory_top;
    private NetworkImageView img_pentry_top;
    private NetworkImageView img_lavatory_bottom_one;
    private NetworkImageView img_lavatory_bottom_two;
    private NetworkImageView img_pentry_bottom;
    private boolean allowedToSelectSeat;
    private HashMap<String,Object> hmap;
    private FlightSeatViewAdapter adapterSeatMap;
    private LinearLayout lytLeftVerticalRow;
    private RelativeLayout relative_seatmap_parent;
    private OTTextView txt_label_seat_summary;
    private OTTextView txtDone;
    private Seatdetailarr seatSummaryData;
    private PassengerArray passengerSelectedData;
    private LinearLayout lay_summary_done_button;
    private String selectedPassenger;
    private OTTextView txt_leg_airline;
    private InternationalizeData localization;
    private OTTextView txt_label_selectedPassenger;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.legproduct_flight_seat_view, container, false);

        Utils.updateActionBarForFeatures(getActivity(), new FlightSeatViewFragment().getClass().getName());
        loader = AppDialogLoader.getLoader(getActivity());
        imageLoader = AppController.getInstance().getImageLoader();

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }

        initUi();
        loadSeatMapDataApi();


        return view;
    }



    private void loadSeatMapDataApi() {

        loader.show();
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SeatMap) +
                "&ifsIndex="+Utils.getIfsIndexForPickASeat(getActivity());


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
                        Utils.DEBUG("onResponse() Seat Map Data called : " + response.toString());
                        seatMapData = ParseManager.getInstance().fromJSON(response, SeatMapRootData.class);

                        if(seatMapData != null)
                        {
                            designCompleteSeatMapUi();
                        }

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

    private void initUi() {

        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        txt_leg_airline = (OTTextView) view.findViewById(R.id.txt_leg_airline);
        txt_label_selectedPassenger = (OTTextView) view.findViewById(R.id.txt_label_selectedPassenger);
        parentViewForRecycler = (LinearLayout) view.findViewById(R.id.parentViewForRecycler);
        lyt_seat = (RelativeLayout) view.findViewById(R.id.lyt_seat);
        lyt_seat.setVisibility(View.GONE);
        recycler_seatSummary = (RecyclerView) view.findViewById(R.id.recycler_seatSummary);
        layout_columnSeatMark = (LinearLayout) view.findViewById(R.id.layout_columnSeatMark);
        lyt_selectedPassenger = (RecyclerView) view.findViewById(R.id.lyt_selectedPassenger);

        txt_aircraft_name_top = (OTTextView) view.findViewById(R.id.txt_aircraft_name_top);
        img_lavatory_top = (NetworkImageView) view.findViewById(R.id.img_lavatory_top);
        img_pentry_top = (NetworkImageView) view.findViewById(R.id.img_pentry_top);
        img_lavatory_bottom_one = (NetworkImageView) view.findViewById(R.id.img_lavatory_bottom_one);
        img_lavatory_bottom_two = (NetworkImageView) view.findViewById(R.id.img_lavatory_bottom_two);
        img_pentry_bottom = (NetworkImageView) view.findViewById(R.id.img_pentry_bottom);

        lytLeftVerticalRow = (LinearLayout) view.findViewById(R.id.lytLeftVerticalRow);
        relative_seatmap_parent = (RelativeLayout) view.findViewById(R.id.relative_seatmap_parent);

        lay_summary_done_button = (LinearLayout) view.findViewById(R.id.lay_summary_done_button);
        lay_summary_done_button.setVisibility(View.GONE);
        txt_label_seat_summary = (OTTextView) view.findViewById(R.id.txt_label_seat_summary);

        txtDone = (OTTextView) view.findViewById(R.id.txtDone);



        txt_label_seat_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopUpForSeatSummary(getActivity(),passengerSelectedData,seatMapData.getSegmentArray().get(0).getLegListArray().get(0), "");
                Utils.DEBUG("Pax Id :"+passengerSelectedData.getPaxId());
            }
        });

        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hmap.size() != seatMapData.getPassengerArray().size())
                {
                    Utils.showToast(getActivity(), "Select All Available Passengers");
                    return;
                }

                SelectedData selectedData = null;
                Set<String> strings = hmap.keySet();
                for (String key : strings) {
                    selectedData = (SelectedData) hmap.get(key);
                    // Utils.DEBUG(">>> " + selectedData.getPaxFullName() + " " + selectedData.getSeatDesignator() + " " + selectedData.getStyleId2() + " "+selectedData.getPaxId());
                }

                SearchHelper helper = new SearchHelper();
                helper.setSeatMapData(hmap);
                helper.setCheckEmail("");
                helper.setEmailCheck(false);
                helper.setSelectedPax(null);
                Utils.moveToFragment(getActivity(),new LegProductReviewFragment(),helper,0);

            }
        });

    }

    /*
    * New Implementation after UI changes made as per website
    *
    * */

    private void designCompleteSeatMapUi() {

        //load NetworkImageView
        loadNetworkImages();

        labelsLocalize();
        //passenger design
        designViewForSelectedPassengerList(seatMapData.getPassengerArray());

        //cabin design
        designColumnBySeatMarker(seatMapData.getSeatMarkarr(), layout_columnSeatMark);

        designSeatByColumn(seatMapData.getSeatdetailarr());

        lyt_seat.setVisibility(View.VISIBLE);

        setLeftVerticalRow(seatMapData.getSeatdetailarr(), lytLeftVerticalRow, parentViewForRecycler, relative_seatmap_parent);


    }

    private void labelsLocalize() {

        txt_aircraft_name_top.setText(seatMapData.getAircraftName().toString());
        txt_label_seat_summary.setText(seatMapData.getSeat_Summary_Label());
        txtDone.setText(localization.getLABLSelectionDoneLabel());
        txt_label_selectedPassenger.setText(seatMapData.getSelectPassengerLbl());
        txt_leg_airline.setText(seatMapData.getSegmentArray().get(0).getLegListArray().get(0).getDepartCOde()+" > "+seatMapData.getSegmentArray().get(0).getLegListArray().get(0).getArriveCOde());
    }

    private void setLeftVerticalRow(final ArrayList<Seatdetailarr> seatdetailarr, final LinearLayout lytLeftVerticalRow, final LinearLayout parentViewForRecycler,final RelativeLayout relative_seatmap_parent) {

        final TreeSet<Seatdetailarr> hashSet = new TreeSet<>(seatdetailarr);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Iterator<Seatdetailarr> iterator = hashSet.iterator();
                while (iterator.hasNext())
                {
                    Seatdetailarr next = iterator.next();
                    if(next.getColumnNumber() != 0)
                    {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)Utils.conertDpToPixel(getActivity(), 30), (int)Utils.conertDpToPixel(getActivity(), 30));
                        TextView tv = new TextView(getActivity());
                        tv.setText(next.getColumnNumber() + "");
                        //tv.setPadding(d);

                        lytLeftVerticalRow.addView(tv, params);
                    }
                }


                Utils.DEBUG(" Position X >> "+ parentViewForRecycler.getX() + " " + parentViewForRecycler.getY() + "/" + hashSet.size());

                lytLeftVerticalRow.setY(relative_seatmap_parent.getY() + parentViewForRecycler.getY()+10);
                lytLeftVerticalRow.setX(Utils.conertDpToPixel(getActivity(), 40));

            }
        },300);



    }

    private void loadNetworkImages() {

        img_lavatory_top.setImageUrl(seatMapData.getLavatoryImage(), imageLoader);
        img_pentry_top.setImageUrl(seatMapData.getPantryImage(), imageLoader);
        img_lavatory_bottom_one.setImageUrl(seatMapData.getLavatoryImage(), imageLoader);
        img_lavatory_bottom_two.setImageUrl(seatMapData.getLavatoryImage(), imageLoader);
        img_pentry_bottom.setImageUrl(seatMapData.getPantryImage(), imageLoader);

    }

    private void designViewForSelectedPassengerList(ArrayList<PassengerArray> passengerArray) {

        gridLayoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        lyt_selectedPassenger.setLayoutManager(gridLayoutManager);
        adapterPassengerList = new ShowSelectedPassengerAdapter(getActivity(), passengerArray, new ShowSelectedPassengerAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, PassengerArray passengerSelectedThis) {

                //OTTextView txtv = (OTTextView)v.findViewById(R.id.txt_passeger);
                if(passengerSelectedThis.isAllowedToSelectSeat())
                {
                    //make passenger editable
                    Log.i(" Passenger Selected >> ", passengerSelectedThis.getPaxFullName());

                    //set selected passenger for seat summary
                    if(passengerSelectedThis.isSeatSelectionDone())
                    {
                        passengerSelectedData = passengerSelectedThis;
                        lay_summary_done_button.setVisibility(View.VISIBLE);
                    }else
                    {
                        lay_summary_done_button.setVisibility(View.GONE);
                    }

                    designSeatView(numberOfColumn, classType, seatMapData.getSeatdetailarr(), passengerSelectedThis, hmap);
                }
                else
                {

                }

            }
        });

        lyt_selectedPassenger.setAdapter(adapterPassengerList);

    }

    private View designColumnBySeatMarker(ArrayList<SeatMarkarr> seatMarkarr, LinearLayout layout_columnSeatMark) {

        LayoutInflater layoutInfralte = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + seatMarkarr.size());
        //List views = new ArrayList();

        for (int i = 0; i < seatMarkarr.size(); i++) {
            View view = layoutInfralte.inflate(R.layout.child_seat_column_mark, null);
            LinearLayout lytTop = (LinearLayout) view.findViewById(R.id.lytTop);
            TextView tv = (TextView) lytTop.findViewById(R.id.txtlearmore);
            if(seatMarkarr.get(i).getSeatMark().equals("||"))
            {
                tv.setText("   ");
            }else {
                tv.setText(seatMarkarr.get(i).getSeatMark());
            }
           /* view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            view.setPadding(0,0,0,0);*/

            layout_columnSeatMark.addView(view);
        }

        return layout_columnSeatMark;
    }

    private void designSeatByColumn(final ArrayList<Seatdetailarr> Seatdetailarr) {

        if (Seatdetailarr == null)
            return;

        hmap = new HashMap<String, Object>();
        //hmap.put("","");
        //number of column = 3 seats + 1 blankSeat + 3 seats;
        numberOfColumn = 7;
        classType = "Economy";  //Same UI seat map design till now.
        PassengerArray passenger = new PassengerArray();
        passenger.setAllowedToSelectSeat(false);
        designSeatView(numberOfColumn, classType, Seatdetailarr, passenger, hmap);
    }

    private void designSeatView(final int numberOfColumn, String classType, final ArrayList<Seatdetailarr> Seatdetailarr, final PassengerArray passengerSelectedThis, final HashMap<String, Object> hmap) {


        recycler_view = new RecyclerView(getActivity(), null, RecyclerView.VERTICAL);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(numberOfColumn, 1);
        recycler_view.setLayoutManager(staggeredGridLayoutManager);

        LinearLayout.LayoutParams setLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams.gravity = Gravity.CENTER;
        recycler_view.setLayoutParams(setLayoutParams);
        adapterSeatMap = new FlightSeatViewAdapter(getActivity(), classType, passengerSelectedThis, hmap, Seatdetailarr, new FlightSeatViewAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, int position, Seatdetailarr seatdata, PassengerArray passengerSelected) {

                if(seatdata != null)
                {
                    SelectedData selectedData = new SelectedData();
                    selectedData.setPaxFullName(passengerSelected.getPaxFullName());
                    selectedData.setPaxId(passengerSelected.getPaxId()+"");
                    selectedData.setSeatDesignator(seatdata.getSeatDesignator());
                    selectedData.setStyleId2(seatdata.getStyleId2());

                    // FlightSeatViewFragment.this.hmap.put(passengerSelected.getPaxFullName(), seatdata.getSeatDesignator().toString());
                    FlightSeatViewFragment.this.hmap.put(selectedData.getPaxFullName(), selectedData);


                    Set set = FlightSeatViewFragment.this.hmap.entrySet();
                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()) {
                        Map.Entry mentry = (Map.Entry)iterator.next();
                        selectedPassenger = mentry.getKey().toString();
                        System.out.println("Pass Name ="+ selectedPassenger + " Seat Number = " + mentry.getValue().toString());
                    }


                    // tick green mark to passenger
                    //passengerSelected.setPassengerSeatEditable(true);
                    passengerSelected.setSeatAmount("$"+seatdata.getSeatShiftPrice());
                    passengerSelected.setSeatSelectedNumber(seatdata.getSeatDesignator());
                    passengerSelected.setSeatSelectionDone(true);
                    adapterPassengerList.notifyDataSetChanged();


                    // set seat summary data
                    passengerSelectedData = passengerSelected;
                    lay_summary_done_button.setVisibility(View.VISIBLE);

                    //and show pop up for seat info
                    showPopUpForSeatInfo(getActivity(), seatdata, "");
                }
                else
                {
                    //remove this seat as well as passenger from hashmap
                    if(FlightSeatViewFragment.this.hmap.size() > 0) {

                        Utils.DEBUG("Passenger Removed :  >>>> "+passengerSelected.getPaxFullName());
                        FlightSeatViewFragment.this.hmap.remove(passengerSelected.getPaxFullName());
                        if(FlightSeatViewFragment.this.hmap.size() == 0)
                        {
                            lay_summary_done_button.setVisibility(View.GONE);
                        }
                        Set set = FlightSeatViewFragment.this.hmap.entrySet();
                        Iterator iterator = set.iterator();
                        while(iterator.hasNext()) {
                            Map.Entry mentry = (Map.Entry)iterator.next();
                            System.out.println("key is: Passenger = "+ mentry.getKey() + " <<<<>>>> Value is: Seat Number = " + mentry.getValue());
                        }
                    }

                    // set seat summary data
                    passengerSelectedData = null;
                    lay_summary_done_button.setVisibility(View.GONE);


                    // remove green mark to passenger
                    //passengerSelected.setPassengerSeatEditable(false);

                    passengerSelected.setSeatSelectionDone(false);
                    adapterPassengerList.notifyDataSetChanged();
                }

            }
        });

        if(!passengerSelectedThis.isAllowedToSelectSeat())
        {
            recycler_view.setAdapter(adapterSeatMap);
            recycler_view.setBackgroundColor(Color.parseColor("#FFFFFF"));
            parentViewForRecycler.addView(recycler_view);
        }
        else
        {
            //adapterSeatMap.notifyDataSetChanged();
            parentViewForRecycler.removeAllViews();
            recycler_view.setAdapter(adapterSeatMap);
            recycler_view.setBackgroundColor(Color.parseColor("#FFFFFF"));
            parentViewForRecycler.addView(recycler_view);
        }



    }


    public static void showPopUpForSeatInfo(final Context context,
                                            Seatdetailarr selectedSeat, String msg) {
        if (context != null) {
            final Dialog dialogmsg = new Dialog(context);
            dialogmsg.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialogmsg.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogmsg.setCanceledOnTouchOutside(false);
            dialogmsg.getWindow().setLayout(ActionBar.LayoutParams.FILL_PARENT,
                    ActionBar.LayoutParams.FILL_PARENT);
            dialogmsg.setContentView(R.layout.legproduct_onseatselection);

            dialogmsg.show();

            NetworkImageView img_popseat = (NetworkImageView) dialogmsg.findViewById(R.id.img_popseat);
            OTTextView txt_seat_toplabel_number = (OTTextView) dialogmsg.findViewById(R.id.txt_seat_toplabel_number);
            OTTextView txt_label_regularseat = (OTTextView) dialogmsg.findViewById(R.id.txt_label_regularseat);
            OTTextView txt_amount = (OTTextView) dialogmsg.findViewById(R.id.txt_amount);
            LinearLayout lyt_benefit = (LinearLayout) dialogmsg.findViewById(R.id.lyt_benefit);
            LinearLayout lyt_drawBacks = (LinearLayout) dialogmsg.findViewById(R.id.lyt_drawBacks);
            OTTextView txt_seatBenefit = (OTTextView) dialogmsg.findViewById(R.id.txt_seatBenefit);
            OTTextView txt_seatDrawBacks = (OTTextView) dialogmsg.findViewById(R.id.txt_seatDrawBacks);

            try {
                txt_seat_toplabel_number.setText(selectedSeat.getSeatDesignator().toString());
                txt_label_regularseat.setText(selectedSeat.getSeatName().toString());
                txt_amount.setText("$" + selectedSeat.getSeatShiftPrice().toString());
                img_popseat.setImageUrl(selectedSeat.getSeatImageDiscription(), AppController.getInstance().getImageLoader());

                if (selectedSeat.getSeatBenefit().equals("-")) {
                    lyt_benefit.setVisibility(View.GONE);
                } else {
                    lyt_benefit.setVisibility(View.VISIBLE);
                    txt_seatBenefit.setText(selectedSeat.getSeatBenefit().toString());
                }

                if (selectedSeat.getSeatDrawbacks().equals("-")) {
                    lyt_drawBacks.setVisibility(View.GONE);
                } else {
                    lyt_drawBacks.setVisibility(View.VISIBLE);
                    txt_seatDrawBacks.setText(selectedSeat.getSeatDrawbacks().toString());
                }
            }
            catch (Exception e)
            {
                Utils.ERROR("Seat Pop Error >>> ");
            }

            OTTextView txt_close = (OTTextView) dialogmsg.findViewById(R.id.txt_close);
            txt_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialogmsg.dismiss();

                }
            });

        }

    }


    public void showPopUpForSeatSummary(final Context context,PassengerArray passengerSelectedData, LegListArray legListArray, String msg) {
        if (context != null) {
            final Dialog dialog = new Dialog(context);
            dialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().setLayout(ActionBar.LayoutParams.FILL_PARENT,
                    ActionBar.LayoutParams.FILL_PARENT);
            dialog.setContentView(R.layout.legproduct_seat_summary_popup);
            dialog.show();

            TextView txt_seat_toplabel_seat_summary = (TextView) dialog.findViewById(R.id.txt_seat_toplabel_seat_summary);
            TextView txt_label_ooutbound = (TextView) dialog.findViewById(R.id.txt_label_ooutbound);
            txt_label_ooutbound.setText(seatMapData.getOutbound_Journey_Label());
            TextView txt_pnh_rep = (TextView) dialog.findViewById(R.id.txt_pnh_rep);
            txt_pnh_rep.setText(legListArray.getDepartCOde()+" > "+ legListArray.getArriveCOde());
            TextView txt_passenger = (TextView) dialog.findViewById(R.id.txt_passenger);
            txt_passenger.setText(passengerSelectedData.getPaxFullName());
            TextView txt_amount = (TextView) dialog.findViewById(R.id.txt_amount);
            txt_amount.setText(passengerSelectedData.getSeatAmount());
            TextView txt_grand_total_label = (TextView) dialog.findViewById(R.id.txt_grand_total_label);
            txt_grand_total_label.setText(seatMapData.getGrand_Total_Label());
            TextView txt_grand_total = (TextView) dialog.findViewById(R.id.txt_grand_total);
            txt_grand_total.setText(passengerSelectedData.getSeatAmount());

            TextView txt_continue = (TextView) dialog.findViewById(R.id.txt_continue);
            txt_continue.setText(localization.getLABL_Continue_Button_Label());
            txt_continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    //go to payment page

                    if(hmap.size() != seatMapData.getPassengerArray().size())
                    {
                        Utils.showToast(getActivity(), "Select All Available Passengers");
                        return;
                    }

                    SelectedData selectedData = null;
                    Set<String> strings = hmap.keySet();
                    for (String key : strings) {
                        selectedData = (SelectedData) hmap.get(key);
                        // Utils.DEBUG(">>> " + selectedData.getPaxFullName() + " " + selectedData.getSeatDesignator() + " " + selectedData.getStyleId2() + " "+selectedData.getPaxId());
                    }

                    SearchHelper helper = new SearchHelper();
                    helper.setSeatMapData(hmap);
                    helper.setCheckEmail("");
                    helper.setEmailCheck(false);
                    helper.setSelectedPax(null);
                    Utils.moveToFragment(getActivity(),new LegProductReviewFragment(),helper,0);
                }
            });

        }

    }

}
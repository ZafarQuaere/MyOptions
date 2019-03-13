package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.optiontown.R;
import com.optiontown.app.interfaces.PassengerGroupDropdownInterface;
import com.optiontown.app.model.selectproduct.AirlineDropDownArray;
import com.optiontown.app.model.selectproduct.CabinArray;
import com.optiontown.app.model.selectproduct.FlightPassDealData;
import com.optiontown.app.model.selectproduct.FlightsList;
import com.optiontown.app.model.selectproduct.PasTypeGroupList;
import com.optiontown.app.model.selectproduct.PassDataGroup;
import com.optiontown.app.model.selectproduct.PassDataNormal;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter for search select recycler view
 */
public class SearchSelectRecyclerViewAdapter extends RecyclerView.Adapter<SearchSelectRecyclerViewAdapter.ViewHolder> {
    public static final int VIEW_TYPE_AIRLINE = 1;
    public static final int VIEW_TYPE_CABIN = 2;
    public static final int VIEW_TYPE_PASSENGER = 3;
    public static final int VIEW_TYPE_FLIGHT = 4;
    public static final int VIEW_TYPE_TRAVEL_PERIOD = 5;
    public static final int VIEW_TYPE_ADVANCE_BOOKING = 6;
    public static final int VIEW_TYPE_FLEXIBILITY = 7;
    public static final int VIEW_TYPE_FM_MATRIX_BASE = 1000;//base value for FM Matrix entities, draw ui for FM Matrix if value of key R.string.key_view_type is more than this (1000) value
    private List<Object> list;
    private FlightPassDealData data;
    private IRecyclerViewHolderClicks listener;
    private Context context;
    private AppSharedPrefs instanceSharedPrefs;
    private LinearLayout[] aryLytPassGroupDropdown;
    private OTTextView[] aryTxtPassengerGroup;

    public SearchSelectRecyclerViewAdapter(Context context, int viewType, FlightPassDealData data, IRecyclerViewHolderClicks l) {
        this.instanceSharedPrefs = AppSharedPrefs.getInstance(context);
        this.context = context;
        this.data = data;
        this.list = getList(viewType);
        this.listener = l;
        try{
            this.aryLytPassGroupDropdown = new LinearLayout[list.size()];
            this.aryTxtPassengerGroup = new OTTextView[list.size()];
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        //testData();

    }

    private List getList(int viewType) {


        switch (viewType) {
            case VIEW_TYPE_AIRLINE:
                List<AirlineDropDownArray> listAirline = data.getAirLineList().getAirlineDropDownArray();
                for (AirlineDropDownArray ary : listAirline) {
                    if (ary.getAirlline().equals(instanceSharedPrefs.get(context.getString(R.string.key_selected_airline_tag)))) {
                        ary.setSelectedAirline(true);
                    } else {
                        ary.setSelectedAirline(false);
                    }
                }
                return listAirline;

            case VIEW_TYPE_CABIN:
                List<CabinArray> listCabin = data.getCabinList().getCabinArray();
                for (CabinArray ary : listCabin) {
                    if (ary.getCabinName().equals(instanceSharedPrefs.get(context.getString(R.string.key_selected_cabin_tag)))) {
                        ary.setSelectedCabin(true);
                        instanceSharedPrefs.put(context.getString(R.string.key_selected_cabin_id), ary.getCabinId());
                        instanceSharedPrefs.put(context.getString(R.string.key_selected_cabin_tag), ary.getCabinName());
                    } else {
                        ary.setSelectedCabin(false);
                    }
                }
                return listCabin;


            case VIEW_TYPE_PASSENGER:

                if (!data.getPassList().getPassDataNormal().isEmpty()) {

                    List<PassDataNormal> normalPassList = data.getPassList().getPassDataNormal();
                    for (PassDataNormal ary : normalPassList) {
                        if (ary.getValue() == ((int) instanceSharedPrefs.get(context.getString(R.string.key_selected_passenger_id)))) {

                            ary.setSelectedPassenger(true);
                            instanceSharedPrefs.put(context.getString(R.string.key_selected_passenger_id), ary.getValue());
                            instanceSharedPrefs.put(context.getString(R.string.key_selected_passenger_tag), ary.getLABLPassengers());
                            instanceSharedPrefs.put(context.getString(R.string.key_selected_passenger_pass_type_id), ary.getPassTypeId());
                        } else {
                            ary.setSelectedPassenger(false);
                        }
                    }
                    return normalPassList;
                } else {

                    List<PassDataGroup> mGroupList = data.getPassList().getPassDataGroup();

                    for (int i = 0; i < mGroupList.size(); i++) {

                        PassDataGroup passDataGroup = mGroupList.get(i);

                        ArrayList<PasTypeGroupList> mPassTypeGroupLists = passDataGroup.getPasTypeGroupList();

                       for (PasTypeGroupList grpList : mPassTypeGroupLists) {
                            if (grpList.getPassTypeId() == (int)instanceSharedPrefs.get(context.getString(R.string.key_selected_passenger_pass_type_id)) &&
                                    grpList.getValue().equals(instanceSharedPrefs.get(context.getString(R.string.key_selected_passenger_id)))){
                                grpList.setSelectedPassenger(true);
                                instanceSharedPrefs.put(context.getString(R.string.key_selected_passenger_id), grpList.getValue());
                                instanceSharedPrefs.put(context.getString(R.string.key_selected_passenger_tag), grpList.getLABLPassengers());
                                instanceSharedPrefs.put(context.getString(R.string.key_selected_passenger_pass_type_id), grpList.getPassTypeId());

                            } else {
                                grpList.setSelectedPassenger(false);
                            }

                        }

                    }

                    return mGroupList;
                }


            case VIEW_TYPE_FLIGHT:
                List<FlightsList> listFlight = data.getFList().getFlightsList();
                for (FlightsList ary : listFlight) {
                    if (ary.getCreditValue().equals(instanceSharedPrefs.get(context.getString(R.string.key_selected_flight_tag)))) {
                        ary.setSelectedCredit(true);
                        instanceSharedPrefs.put(context.getString(R.string.key_selected_flight_id), ary.getCreditId());
                        instanceSharedPrefs.put(context.getString(R.string.key_selected_flight_tag), ary.getCreditValue());
                    } else {
                        ary.setSelectedCredit(false);
                    }
                }
                return listFlight;


        }

        return null;
    }

    private void testData() {
        AirlineDropDownArray d = new AirlineDropDownArray();
        d.setAirlline("aa");
        this.list.add(d);

        AirlineDropDownArray d1 = new AirlineDropDownArray();
        d1.setAirlline("bb");
        this.list.add(d1);

        AirlineDropDownArray d2 = new AirlineDropDownArray();
        d2.setAirlline("cc");
        this.list.add(d2);

        AirlineDropDownArray d3 = new AirlineDropDownArray();
        d3.setAirlline("dd");
        this.list.add(d3);

        AirlineDropDownArray d4 = new AirlineDropDownArray();
        d4.setAirlline("ee");
        this.list.add(d4);

        AirlineDropDownArray d5 = new AirlineDropDownArray();
        d5.setAirlline("ff");
        this.list.add(d5);

        AirlineDropDownArray d6 = new AirlineDropDownArray();
        d6.setAirlline("gg");
        this.list.add(d6);

        AirlineDropDownArray d7 = new AirlineDropDownArray();
        d7.setAirlline("hh");
        this.list.add(d7);

        AirlineDropDownArray d8 = new AirlineDropDownArray();
        d8.setAirlline("ii");
        this.list.add(d8);
    }

    @Override
    public SearchSelectRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_select_recycler_row, parent, false);


        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        aryLytPassGroupDropdown[position] = holder.lytPassGroupDropdown;
        aryTxtPassengerGroup[position] = holder.txtPassengerGroup;
        holder.lytSearchSelectRecyclerRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onClickRecyclerItem(v, position, list.get(position));

                //--
                Object item = list.get(position);
                if (item instanceof AirlineDropDownArray) {
                    if (!((AirlineDropDownArray) item).isSelectedAirline()) {
                        holder.imgTick.setVisibility(View.VISIBLE);
                        resetList(position);
                    }

                } else if (item instanceof CabinArray) {
                    holder.txtAirline.setText(((CabinArray) item).getCabinName());

                    if (!((CabinArray) item).isSelectedCabin()) {
                        holder.imgTick.setVisibility(View.VISIBLE);
                        resetList(position);
                    }
                } else if (item instanceof PassDataNormal) {
                    if (!((PassDataNormal) item).isSelectedPassenger()) {
                        holder.imgTick.setVisibility(View.VISIBLE);
                        resetList(position);
                    }
                }else if (item instanceof PasTypeGroupList) {
                    if (!((PasTypeGroupList) item).isSelectedPassenger()) {

                        holder.imgTick.setVisibility(View.GONE);
                        resetList(position);
                    }
                } else if (item instanceof FlightsList) {
                    if (!((FlightsList) item).isSelectedCredit()) {
                        holder.imgTick.setVisibility(View.VISIBLE);
                        resetList(position);
                    }
                }

                notifyDataSetChanged();
            }
        });

        final Object item = list.get(position);
        if (item instanceof AirlineDropDownArray) {
            holder.txtAirline.setText((((AirlineDropDownArray) item).getAirlline()));
            holder.imgTick.setVisibility(((AirlineDropDownArray) item).isSelectedAirline() ? View.VISIBLE : View.GONE);


        } else if (item instanceof CabinArray) {
            holder.txtAirline.setText(((CabinArray) item).getCabinName());
            holder.imgTick.setVisibility(((CabinArray) item).isSelectedCabin() ? View.VISIBLE : View.GONE);


        } else if (item instanceof PassDataNormal) {
            holder.txtAirline.setText(((PassDataNormal) item).getLABLPassengers());
            holder.imgTick.setVisibility(((PassDataNormal) item).isSelectedPassenger() ? View.VISIBLE : View.GONE);


        } else if (item instanceof PassDataGroup) {

            final ArrayList<PasTypeGroupList> groupArrayList1 = ((PassDataGroup) item).getPasTypeGroupList();
            final ArrayList<String> passngrlist = new ArrayList<>();

            for (PasTypeGroupList passenger : groupArrayList1) {
                passngrlist.add(passenger.getLABLPassengers());
            }
            final String[] arr = passngrlist.toArray(new String[passngrlist.size()]);


           /* new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                     arr = passngrlist.toArray(new String[passngrlist.size()]);
                }
            }, 200);*/


            holder.txtPassengerGroup.setVisibility(View.VISIBLE);

            holder.txtPassengerGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    Utils.createDropdownView(arr, holder.lytPassGroupDropdown, holder.txtPassengerGroup, aryLytPassGroupDropdown, new PassengerGroupDropdownInterface()
                    {
                        @Override
                        public void onDropdownClick(String passenger) {

                            for (int i = 0; i < groupArrayList1.size(); i++) {

                                if (passenger.equalsIgnoreCase(groupArrayList1.get(i).getLABLPassengers())){

                                    Utils.DEBUG("Passenger Value : "+groupArrayList1.get(i).getValue());
                                    Utils.DEBUG("Passenger  : "+groupArrayList1.get(i).getLABLPassengers());
                                    Utils.DEBUG("PassTypeId  : "+groupArrayList1.get(i).getPassTypeId());

                                    listener.onClickRecyclerItem(v, i, groupArrayList1.get(i));

                                    instanceSharedPrefs.put(context.getString(R.string.key_selected_passenger_id),groupArrayList1.get(i).getValue());
                                    instanceSharedPrefs.put(context.getString(R.string.key_selected_passenger_tag), groupArrayList1.get(i).getLABLPassengers());
                                    instanceSharedPrefs.put(context.getString(R.string.key_selected_passenger_pass_type_id), groupArrayList1.get(i).getPassTypeId());

                                }
                            }

                            //reset rest dropdown
                            for (int i = 0; i < aryTxtPassengerGroup.length; i++) {
                                if (i != position)
                                aryTxtPassengerGroup[i].setText(((PassDataGroup)list.get(i)).getPasTypeRange());
                            }

                        }
                    });

                }

            });

            //for smooth scrolling of views
            holder.lytSearchSelectRecyclerRow.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event)
                {
                    holder.svGroupList.getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            });
            Utils.setInterceptTouchEvent(new View[]{holder.svGroupList});


            holder.txtAirline.setText(((PassDataGroup) item).getPasTypeGroupName());

            for (int i = 0; i < groupArrayList1.size(); i++) {

                try {
                    if (groupArrayList1.get(i).getPassTypeId() == Integer.parseInt(instanceSharedPrefs.get(context.getString(R.string.key_selected_passenger_pass_type_id))+"") &&
                            Integer.parseInt(groupArrayList1.get(i).getValue()) == ((int)instanceSharedPrefs.get(context.getString(R.string.key_selected_passenger_id)))){

                        holder.txtPassengerGroup.setText(instanceSharedPrefs.get(context.getString(R.string.key_selected_passenger_tag))+"");
                        break;
                    }
                    else {
                        holder.txtPassengerGroup.setText(((PassDataGroup) item).getPasTypeRange());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        } else if (item instanceof FlightsList) {
            holder.txtAirline.setText(((FlightsList) item).getCreditValue());
            holder.imgTick.setVisibility(((FlightsList) item).isSelectedCredit() ? View.VISIBLE : View.GONE);

        }

    }

    private void resetList(int position) {
        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i);
            if (i == position) {
                if (item instanceof AirlineDropDownArray) {
                    ((AirlineDropDownArray) item).setSelectedAirline(true);
                } else if (item instanceof CabinArray) {
                    ((CabinArray) item).setSelectedCabin(true);
                } else if (item instanceof PassDataNormal) {
                    ((PassDataNormal) item).setSelectedPassenger(true);
                } else if (item instanceof PasTypeGroupList) {
                    ((PasTypeGroupList) item).setSelectedPassenger(true);

                } else if (item instanceof FlightsList) {
                    ((FlightsList) item).setSelectedCredit(true);
                }
            } else {
                if (item instanceof AirlineDropDownArray) {
                    ((AirlineDropDownArray) item).setSelectedAirline(false);
                } else if (item instanceof CabinArray) {
                    ((CabinArray) item).setSelectedCabin(false);
                } else if (item instanceof PassDataNormal) {
                    ((PassDataNormal) item).setSelectedPassenger(false);
                } else if (item instanceof PasTypeGroupList) {
                    ((PasTypeGroupList) item).setSelectedPassenger(false);
                } else if (item instanceof FlightsList) {
                    ((FlightsList) item).setSelectedCredit(false);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int position, Object item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgTick;
        private OTTextView txtAirline;
        private OTTextView txtPassengerGroup;
        private RelativeLayout lytSearchSelectRecyclerRow;
        private LinearLayout lytPassGroupDropdown;
        private ScrollView svGroupList;

        public ViewHolder(View v) {
            super(v);
            this.imgTick = (ImageView) v.findViewById(R.id.imgTick);
            this.txtAirline = (OTTextView) v.findViewById(R.id.txtSelectedItem);
            this.txtPassengerGroup = (OTTextView) v.findViewById(R.id.txtPassengerGroup);
            this.lytSearchSelectRecyclerRow = (RelativeLayout) v.findViewById(R.id.lytSearchSelectRecyclerRow);
            this.lytPassGroupDropdown = (LinearLayout) v.findViewById(R.id.lytPassGroupDropdown);
            this.svGroupList = (ScrollView) v.findViewById(R.id.svGroupList);


        }
    }
}

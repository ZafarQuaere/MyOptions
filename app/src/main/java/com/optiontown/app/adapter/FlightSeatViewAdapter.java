package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.optiontown.R;
import com.optiontown.app.model.seatview.PassengerArray;
import com.optiontown.app.model.seatview.Seatdetailarr;
import com.optiontown.app.model.seatview.SelectedData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by parasmani.sharma on 22/07/2017.
 */

public class FlightSeatViewAdapter extends RecyclerView.Adapter<FlightSeatViewAdapter.ViewHolder> {


    private static PassengerArray passengerSelected;
    private final HashMap<String, Object> hmap;
    private String PREMIUM_ECONOMY_REGULAR_SEAT = "premium_economy_regular_seat";
    private String PREMIUM_ECONOMY_BLOCKED_SEAT = "premium_economy_blocked_seat";
    private Context context;
    private ArrayList<Seatdetailarr> chairData;
    private IRecyclerViewHolderClicks iRecyclerViewHolderClicks;
    private String classType = null;
    private int count = 0;

    public FlightSeatViewAdapter(Context context, String classType, PassengerArray passengerSelected, HashMap<String, Object> hmap, ArrayList<Seatdetailarr> Seatdetailarr, IRecyclerViewHolderClicks iRecyclerViewHolderClicks) {

        this.context = context;
        this.classType = classType;
        this.passengerSelected = passengerSelected;
        this.hmap = hmap;
        this.chairData = Seatdetailarr;
        this.iRecyclerViewHolderClicks = iRecyclerViewHolderClicks;
    }

    @Override
    public FlightSeatViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_column_seat_pso, parent, false);
        FlightSeatViewAdapter.ViewHolder vh = new FlightSeatViewAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FlightSeatViewAdapter.ViewHolder holder, final int position) {

        //Currently : only economic case is coming from the API

        if (classType.equals("Bussiness")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(45, 45);
            holder.img_chair.setLayoutParams(layoutParams);
            holder.img_chair.setImageResource(R.drawable.singleseat);

        } else if (classType.equals("Economy")) {

            /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(45, 45);
            params.setMargins(0,12,12,0);*/

            //set tag value to seat image so that view can be used for later

            //condition for type of seat image // remove True when data become dynamic
            //API Current Response : getSeatAssignable == 0
            //                 and : getSeatAvailable == 0

            // Total Condition Available
            /*
            *
            * 1-1 : Available
            * 0-1 : Occupied Seat
            * 1-0 : Unavailable Seat
            * 0-0 : Blocked Seat
            *
            *
            * */


            if (chairData.get(position).getSeatAssignable() == 1 && chairData.get(position).getSeatAvailable() == 1 || true) {
                holder.img_chair.setTag(PREMIUM_ECONOMY_REGULAR_SEAT);
                holder.img_chair.setImageResource(R.drawable.premium_economy_regular_seat);


                if (chairData.get(position).getSeatAssignable() == 0 && chairData.get(position).getSeatAvailable() == 1) {
                    //occupied seat
                    holder.img_chair.setTag(PREMIUM_ECONOMY_BLOCKED_SEAT);
                    holder.img_chair.setImageResource(R.drawable.premium_economy_blocked_seat);

                    // if passenger reselected to edit seat selection
                    if (hmap.size() > 0) {
                        String previouslySeatSelectedByThisPassenger = "";
                        if (hmap.containsKey(passengerSelected.getPaxFullName())) {
                            // hmap.get(Object key)
                            SelectedData data = (SelectedData) hmap.get(passengerSelected.getPaxFullName());
                            previouslySeatSelectedByThisPassenger = data.getSeatDesignator();
                            // previouslySeatSelectedByThisPassenger = hmap.get(passengerSelected.getPaxFullName());

                            if (previouslySeatSelectedByThisPassenger.equals(chairData.get(position).getSeatDesignator())) {
                                //if seat is editable
                                holder.img_chair.setTag(PREMIUM_ECONOMY_BLOCKED_SEAT);
                                chairData.get(position).setSeatAssignable(0);   // change int value according to api
                                chairData.get(position).setSeatAvailable(1);    // change int value according to api
                                holder.img_chair.setImageResource(R.drawable.selected_seat);
                            }
                        }
                    }
                }
            } else if (chairData.get(position).getSeatAssignable() == 0 && chairData.get(position).getSeatAvailable() == 1) {
                //occupied seat
                holder.img_chair.setTag(PREMIUM_ECONOMY_BLOCKED_SEAT);
                holder.img_chair.setImageResource(R.drawable.premium_economy_blocked_seat);
            } else if (chairData.get(position).getSeatAssignable() == 1 && chairData.get(position).getSeatAvailable() == 0) {
                //unavailable seat
                holder.img_chair.setTag(PREMIUM_ECONOMY_BLOCKED_SEAT);
                holder.img_chair.setImageResource(R.drawable.premium_economy_blocked_seat);
            } else if (chairData.get(position).getSeatAssignable() == 0 && chairData.get(position).getSeatAvailable() == 0) {
                //blocked seat
                holder.img_chair.setTag(PREMIUM_ECONOMY_BLOCKED_SEAT);
                holder.img_chair.setImageResource(R.drawable.premium_economy_blocked_seat);
            }

            // set tag for view relative layout, leaving 3rd column
            if (position % 7 != 3) {
                holder.layout_seat_tag.setTag(chairData.get(position).getSeatDesignator().toString());
            }

            //---


            /*if(position % 7 == 0)
            {
                holder.txt_columnNumber.setVisibility(View.GONE);
                holder.txt_columnNumber.setText(chairData.get(position).getColumnNumber()+"");
            }else {

                holder.txt_columnNumber.setVisibility(View.GONE);
            }*/

            holder.layout_seat_tag.setVisibility(position % 7 == 3 ? View.GONE : View.VISIBLE);

            //set Seat layout position
            /*if(position % 7 == 0)
            {
                //set tag for view layout

                //Right margin ColumnNumber
                holder.txt_columnNumber.setVisibility(View.VISIBLE);
                holder.txt_columnNumber.setText(chairData.get(position).getColumnNumber()+"");
                //holder.txt_columnNumber.setLayoutParams(params);

                //params.addRule(RelativeLayout.RIGHT_OF,holder.txt_columnNumber.getId());
                holder.img_chair.setVisibility(View.VISIBLE);
                //holder.img_chair.setLayoutParams(params);

            }
            else if(position % 7 == 1)
            {
                holder.txt_columnNumber.setVisibility(View.GONE);
                //params.setMargins(10, 0, 0, 0);
                holder.img_chair.setVisibility(View.VISIBLE);
                //holder.img_chair.setLayoutParams(params);

            }
            else if(position % 7 == 2)
            {
                //right margin
                holder.txt_columnNumber.setVisibility(View.GONE);
                holder.img_chair.setVisibility(View.VISIBLE);
                //holder.img_chair.setLayoutParams(params);

            }
            else if (position % 7 == 3)
            {
                //left margin
                holder.img_chair.setVisibility(View.GONE);
                holder.txt_columnNumber.setText("    ");
                holder.txt_columnNumber.setVisibility(View.VISIBLE);
                //params.setMargins(0, 0, 0, 0);
                //holder.txt_columnNumber.setLayoutParams(params);

            }
            else if (position % 7 == 4)
            {
                //left margin
                holder.txt_columnNumber.setVisibility(View.GONE);
               // params.setMargins(20, 0, 0, 0);
                holder.img_chair.setVisibility(View.VISIBLE);
                //holder.img_chair.setLayoutParams(params);

            }
            else if (position % 7 == 5)
            {
                //left margin
                holder.txt_columnNumber.setVisibility(View.GONE);
                //params.setMargins(0, 0, 0, 0);
                holder.img_chair.setVisibility(View.VISIBLE);
                //holder.img_chair.setLayoutParams(params);

            }
            else
            {
                //params.setMargins(0, 0, 0, 0);
                holder.txt_columnNumber.setVisibility(View.GONE);
                //params.setMargins(0, 0, 0, 0);
                holder.img_chair.setVisibility(View.VISIBLE);
                //holder.img_chair.setLayoutParams(params);
            }*/
        }


        //---

        holder.img_chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passengerSelected.isAllowedToSelectSeat()) {
                    ImageView img = (ImageView) v.findViewById(R.id.img_chair);

                    // true is static condition  remove True when data become dynamic
                    if (chairData.get(position).getSeatAssignable() == 1 && chairData.get(position).getSeatAvailable() == 1 || true) {
                        //available case

                        if (img.getTag().equals(PREMIUM_ECONOMY_REGULAR_SEAT))   //unselected seat
                        {
                            //1) check hashmap data for same user selected again
                            //2) if on selecting seat same passenger is already present with some other or same seat data,
                            // then deselect that previous seat and select new seat
                            /*if same user select any (or this) seat then unselect previous seat*/
                            //3) if hash map contains same user already find this already selected seatimage in view heirarchy and make it white again.
                            //4) Mark green to new seat as previously implemented.


                            if (hmap.size() > 0) {

                                String previouslySeatSelectedByThisPassenger = "";
                                if (hmap.containsKey(passengerSelected.getPaxFullName())) {
                                    // hmap.get(Object key)
                                    //previouslySeatSelectedByThisPassenger = hmap.get(passengerSelected.getPaxFullName());
                                    SelectedData data = (SelectedData) hmap.get(passengerSelected.getPaxFullName());
                                    previouslySeatSelectedByThisPassenger = data.getSeatDesignator();

                                    if (previouslySeatSelectedByThisPassenger.equals(chairData.get(position).getSeatDesignator())) {
                                        // show msg : (if same seat selected again)
                                        Toast.makeText(context, "This seat already selected try select any other seat", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Remove previous seat : (if different seat selected)

                                        View view = (View) v.getParent();   // Relative layout
                                        View viewRecycler = (View) view.getParent();  //recycler view
                                        RecyclerView recycler = (RecyclerView) viewRecycler;
                                        LinearLayout layoutSelectedPreviously = (LinearLayout) recycler.findViewWithTag(previouslySeatSelectedByThisPassenger);
                                        ImageView imgSelectedPreviously = (ImageView) layoutSelectedPreviously.findViewById(R.id.img_chair);
                                        imgSelectedPreviously.setTag(PREMIUM_ECONOMY_REGULAR_SEAT);
                                        int childPosition = recycler.getChildLayoutPosition(layoutSelectedPreviously);
                                        chairData.get(childPosition).setSeatAssignable(0); // change int value according to api
                                        chairData.get(childPosition).setSeatAvailable(0);   // change int value according to api
                                        imgSelectedPreviously.setImageResource(R.drawable.premium_economy_regular_seat);

                                    }
                                }

                            }

                            //this seat has been occupied by current passenger
                            img.setTag(PREMIUM_ECONOMY_BLOCKED_SEAT);
                            chairData.get(position).setSeatAssignable(0); // change int value according to api
                            chairData.get(position).setSeatAvailable(1);   // change int value according to api
                            img.setImageResource(R.drawable.selected_seat);
                            iRecyclerViewHolderClicks.onClickRecyclerItem(v, position, chairData.get(position), passengerSelected);

                        } else if (img.getTag().equals(PREMIUM_ECONOMY_BLOCKED_SEAT)) {
                            //Toast.makeText(context,"Already Blocked By Other User", Toast.LENGTH_SHORT).show();

                            if (hmap.size() > 0) {
                                String previouslySeatSelectedByThisPassenger = "";
                                if (hmap.containsKey(passengerSelected.getPaxFullName())) {
                                    // hamp value = hmap.get(Object key)
                                    // previouslySeatSelectedByThisPassenger/*value(seat number)*/ = hmap.get(passengerSelected.getPaxFullName()/*key*/);
                                    SelectedData data = (SelectedData) hmap.get(passengerSelected.getPaxFullName());
                                    previouslySeatSelectedByThisPassenger = data.getSeatDesignator();

                                    if (previouslySeatSelectedByThisPassenger.equals(chairData.get(position).getSeatDesignator())) {
                                        // show msg : (if same seat selected again)
                                        img.setTag(PREMIUM_ECONOMY_REGULAR_SEAT);
                                        chairData.get(position).setSeatAssignable(0); // change int value according to api
                                        chairData.get(position).setSeatAvailable(0);   // change int value according to api
                                        img.setImageResource(R.drawable.premium_economy_regular_seat);
                                        iRecyclerViewHolderClicks.onClickRecyclerItem(v, position, null, passengerSelected);
                                    }
                                }
                            }
                        } else {

                            img.setTag(PREMIUM_ECONOMY_REGULAR_SEAT);
                            chairData.get(position).setSeatAssignable(0); // change int value according to api
                            chairData.get(position).setSeatAvailable(0);   // change int value according to api
                            img.setImageResource(R.drawable.premium_economy_regular_seat);

                            // send null when passenger removed
                            iRecyclerViewHolderClicks.onClickRecyclerItem(v, position, null, passengerSelected);
                        }
                    } else if (chairData.get(position).getSeatAssignable() == 0 && chairData.get(position).getSeatAvailable() == 1) {
                        //occupied seat
                    } else if (chairData.get(position).getSeatAssignable() == 1 && chairData.get(position).getSeatAvailable() == 0) {
                        //unavailable seat
                    } else if (chairData.get(position).getSeatAssignable() == 0 && chairData.get(position).getSeatAvailable() == 0) {
                        //blocked seat
                    }
                } else {
                    Toast.makeText(context, "Please Select Any One Passenger", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return chairData.size();
    }

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int position, Seatdetailarr str, PassengerArray passengerSelected);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout layout_seat_tag;
        public ImageView img_chair;
        public TextView txt_columnNumber;


        public ViewHolder(View itemView) {
            super(itemView);

            this.img_chair = (ImageView) itemView.findViewById(R.id.img_chair);
            this.txt_columnNumber = (TextView) itemView.findViewById(R.id.txt_col_number);
            this.layout_seat_tag = (LinearLayout) itemView.findViewById(R.id.layout_seat_tag);
        }
    }


}
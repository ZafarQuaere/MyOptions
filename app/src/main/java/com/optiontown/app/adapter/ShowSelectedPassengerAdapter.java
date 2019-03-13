package com.optiontown.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.optiontown.R;
import com.optiontown.app.model.seatview.PassengerArray;
import com.optiontown.app.view.customview.OTTextView;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;

/**
 * Created by parasmani.sharma on 01/08/2017.
 */

public class ShowSelectedPassengerAdapter extends RecyclerView.Adapter<ShowSelectedPassengerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<PassengerArray> passengerData;
    private IRecyclerViewHolderClicks l;

    public ShowSelectedPassengerAdapter(Context context, ArrayList<PassengerArray> passengerArray, IRecyclerViewHolderClicks iRecyclerViewHolderClicks) {

        this.context = context;
        this.passengerData = passengerArray;
        this.l = iRecyclerViewHolderClicks;
    }

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, PassengerArray position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img_tick_mark;
        private final LinearLayout lyt_background;
        private OTTextView txt_passeger;
        private OTTextView txt_seat_number;


        public ViewHolder(View itemView) {
            super(itemView);

            this.txt_passeger = (OTTextView) itemView.findViewById(R.id.txt_passeger);
            this.txt_seat_number = (OTTextView) itemView.findViewById(R.id.txt_seat_number);
            this.img_tick_mark = (ImageView) itemView.findViewById(R.id.img_tick_mark);
            this.lyt_background = (LinearLayout) itemView.findViewById(R.id.lyt_background);

        }
    }

    @Override
    public ShowSelectedPassengerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.legproduct_seatview_row_popup, parent, false);
        ShowSelectedPassengerAdapter.ViewHolder vh = new ShowSelectedPassengerAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ShowSelectedPassengerAdapter.ViewHolder holder, final int position) {

        // tick green mark when seat was selected for this position
        if(passengerData.get(position).isSeatSelectionDone())
        {
            holder.txt_passeger.setText(passengerData.get(position).getPaxFullName());
            holder.txt_passeger.setTag("Selected");
            holder.img_tick_mark.setVisibility(View.VISIBLE);
            holder.txt_seat_number.setText(passengerData.get(position).getSeatSelectedNumber());
            holder.txt_seat_number.setVisibility(View.VISIBLE);
        }
        else {
            holder.txt_passeger.setText(passengerData.get(position).getPaxFullName());
            holder.txt_passeger.setTag("Unselected");
            holder.img_tick_mark.setVisibility(View.GONE);
            holder.txt_passeger.setTextColor(Color.BLACK);
            holder.lyt_background.setBackground(context.getResources().getDrawable(R.color.color_white));
            holder.txt_seat_number.setVisibility(View.GONE);
        }


        holder.txt_passeger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = (View) v.getParent();   // Linear layout
                LinearLayout lytrow = (LinearLayout)view.getParent();  //Linear lay row

                try {

                    // unselect if any previous view is selected already
                    RecyclerView recycler = (RecyclerView)lytrow.getParent();
                    for(int i=0; i < recycler.getChildCount(); i++)
                    {
                        ViewHolder viewHolder = (ViewHolder) recycler.findViewHolderForAdapterPosition(i);

                        if (viewHolder.txt_passeger.getTag().toString().equals("Selected")) {
                            viewHolder.lyt_background.setBackground(context.getResources().getDrawable(R.color.color_white));
                            viewHolder.txt_passeger.setTag("Unselected");
                            viewHolder.txt_passeger.setTextColor(Color.BLACK);
                        }
                    }
                }catch (Exception e){}

                //select current selected view

                OTTextView txtv = (OTTextView)v.findViewById(R.id.txt_passeger);
                LinearLayout lyt_background = (LinearLayout)v.getParent();

                if(txtv.getTag().toString().equals("Unselected"))
                {
                    // if passenger is selected

                    txtv.setTag("Selected");
                    txtv.setTextColor(Color.WHITE);
                    lyt_background.setBackground(context.getResources().getDrawable(R.color.color_blue));
                    passengerData.get(position).setAllowedToSelectSeat(true);
                }
                else
                {
                    // if passenger unselected

                    txtv.setTag("Unselected");
                    txtv.setTextColor(Color.BLACK);
                    lyt_background.setBackground(context.getResources().getDrawable(R.color.color_white));
                    passengerData.get(position).setAllowedToSelectSeat(false);
                }

                l.onClickRecyclerItem(v, passengerData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return passengerData.size();
    }


}

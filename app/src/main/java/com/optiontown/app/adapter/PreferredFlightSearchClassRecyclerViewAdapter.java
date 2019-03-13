package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;

/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author Ravi Kumar
 */
public class PreferredFlightSearchClassRecyclerViewAdapter extends RecyclerView.Adapter<PreferredFlightSearchClassRecyclerViewAdapter.ViewHolder> {


    ArrayList<Benefit> benefits;
    private ImageLoader imageLoader;
    Context ctx;
    public static Boolean chBxBg ;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView imgOption;
        private OTTextView txtActualRateStandBy, txtActualRateConfirmed;
        private CheckBox chkSelectPFo;
        private RelativeLayout rLytSelectPFo, lytConfirmedBooking;
        // private RadioButton rdpConfirmedBooking;


        public ViewHolder(View v) {
            super(v);

            chkSelectPFo = (CheckBox) v.findViewById(R.id.chkSelectPFo);
            rLytSelectPFo = (RelativeLayout) v.findViewById(R.id.rLytSelectPFo);
              }
    }

    public PreferredFlightSearchClassRecyclerViewAdapter(Context ctx, ArrayList<Benefit> benefits) {
        this.benefits = benefits;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx = ctx;

        chBxBg = true;
    }

    @Override
    public PreferredFlightSearchClassRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.preferred_flight_available_or_not_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.rLytSelectPFo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.chkSelectPFo.setChecked(true);
                if (holder.chkSelectPFo.isChecked()) {

                    if (chBxBg) {
                        Utils.setBackground(holder.rLytSelectPFo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button_selected));
                        chBxBg = false;
                    } else {
                        holder.chkSelectPFo.setChecked(false);
                        Utils.setBackground(holder.rLytSelectPFo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button));
                        chBxBg = true;
                    }

                } else {
                    Utils.setBackground(holder.rLytSelectPFo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button));
                    holder.chkSelectPFo.setChecked(false);
                }
            }
        });
        holder.chkSelectPFo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Utils.setBackground(holder.rLytSelectPFo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button_selected));
                } else {
                    Utils.setBackground(holder.rLytSelectPFo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button));
                }
            }
        });



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return benefits.size();
    }
}
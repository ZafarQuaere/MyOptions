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
public class MultipleBookingSearchClassRecyclerViewAdapter extends RecyclerView.Adapter<MultipleBookingSearchClassRecyclerViewAdapter.ViewHolder> {


    ArrayList<Benefit> benefits;
    private ImageLoader imageLoader;
    Context ctx;
    public static Boolean chBxBg ;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView imgMbo;
        private CheckBox chkselectMbo;
        private RelativeLayout rLytSelectMbo, rLytMboNotAvailable;
        OTTextView txtMBoAvailable,txtMboNotAVailable,txtMboPrice,txtMboLable;



        public ViewHolder(View v) {
            super(v);

            chkselectMbo = (CheckBox) v.findViewById(R.id.chkselectMbo);
            rLytSelectMbo = (RelativeLayout) v.findViewById(R.id.rLytSelectMbo);
            rLytMboNotAvailable = (RelativeLayout) v.findViewById(R.id.rLytMboNotAvailable);
            txtMBoAvailable = (OTTextView) v.findViewById(R.id.txtMBoAvailable);
            txtMboNotAVailable = (OTTextView) v.findViewById(R.id.txtMboNotAVailable);
            txtMboPrice = (OTTextView) v.findViewById(R.id.txtMboPrice);
            txtMboLable = (OTTextView) v.findViewById(R.id.txtMboLable);
            imgMbo = (NetworkImageView) v.findViewById(R.id.imgMbo);
            imgMbo.setDefaultImageResId(R.drawable.empty_seat2by4);

        }
    }

    public MultipleBookingSearchClassRecyclerViewAdapter(Context ctx, ArrayList<Benefit> benefits) {
        this.benefits = benefits;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx = ctx;

        chBxBg = true;
    }

    @Override
    public MultipleBookingSearchClassRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_booking_available_or_not_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // holder.txtActualRateStandBy.setPaintFlags(holder.txtActualRateStandBy.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        // holder.txtActualRateConfirmed.setPaintFlags(holder.txtActualRateConfirmed.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        holder.rLytSelectMbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.chkselectMbo.setChecked(true);
                if (holder.chkselectMbo.isChecked()) {

                    if (chBxBg) {
                        Utils.setBackground(holder.rLytSelectMbo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button_selected));
                        chBxBg = false;
                    } else {
                        holder.chkselectMbo.setChecked(false);
                        Utils.setBackground(holder.rLytSelectMbo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button));
                        chBxBg = true;
                    }

                } else {
                    Utils.setBackground(holder.rLytSelectMbo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button));
                    holder.chkselectMbo.setChecked(false);
                }
            }
        });
        holder.chkselectMbo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Utils.setBackground(holder.rLytSelectMbo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button_selected));
                } else {
                    Utils.setBackground(holder.rLytSelectMbo, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button));
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
package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.optiontown.R;
import com.optiontown.app.model.fpotestimonial.AirlineList;
import com.optiontown.app.model.fpotestimonial.OptionList;
import com.optiontown.app.model.fpotestimonial.TestimonialMaster;
import com.optiontown.app.model.selectproduct.AirLineList;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 28/04/2017.
 */

public class SelectedFlightTestimonialAdapter extends RecyclerView.Adapter<SelectedFlightTestimonialAdapter.ViewHolder> {

    private final TestimonialMaster testimonialMaster;
    private final ArrayList<AirlineList> airlineList = new ArrayList<>();
    private final ArrayList<OptionList> optionList = new ArrayList<>();
    //private final ArrayList<OptionList> optionList;
    private final Context context;
    private final IRecyclerViewHolderClicks listner;
    private final String selection;
    private final ScrollView svParent;

    public SelectedFlightTestimonialAdapter(ScrollView svParent, FragmentActivity activity, TestimonialMaster testimonialMaster, String selection, IRecyclerViewHolderClicks l)
    {

        this.svParent = svParent;
        this.context = activity;
        this.testimonialMaster = testimonialMaster;
        this.listner = l;
        this.selection = selection;

        try {
            if (selection.equals("selectAirline")) {
                for (int i = 1; i < testimonialMaster.getAirlineList().size(); i++) {
                    airlineList.add(testimonialMaster.getAirlineList().get(i));
                }

            } else {

                for (int i = 0; i <= testimonialMaster.getOptionList().size() - 1; i++) {
                    optionList.add(testimonialMaster.getOptionList().get(i));
                }
            }
        }catch (Exception e){}

    }

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, String label, String id);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final OTTextView textAirlineOptionLabel;

        public ViewHolder(View v)
        {

            super(v);
            this.textAirlineOptionLabel =  (OTTextView) v.findViewById(R.id.textAirlineOptionLabel);


        }
    }


    @Override
    public SelectedFlightTestimonialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_airline_option, parent, false);


        SelectedFlightTestimonialAdapter.ViewHolder vh = new SelectedFlightTestimonialAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(selection.equals("selectAirline"))
        {
            holder.textAirlineOptionLabel.setText(airlineList.get(position).getAirlineLabel());
            holder.textAirlineOptionLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listner.onClickRecyclerItem(v, airlineList.get(position).getAirlineLabel().toString(), airlineList.get(position).getAirlineID().toString() );

                }
            });

        }else {

            holder.textAirlineOptionLabel.setText(optionList.get(position).getOptionLabel());
            holder.textAirlineOptionLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listner.onClickRecyclerItem(v, optionList.get(position).getOptionLabel().toString(), optionList.get(position).getOptionID().toString() );

                }
            });
        }

    }

    @Override
    public int getItemCount() {

        if(selection.equals("selectAirline"))
        {
            return airlineList.size();
        }else {
            return optionList.size();
        }
    }
}

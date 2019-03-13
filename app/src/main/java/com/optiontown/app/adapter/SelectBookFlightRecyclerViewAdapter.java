package com.optiontown.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.optiontown.R;
import com.optiontown.app.model.redeem.ListOfPass;
import com.optiontown.app.model.redeem.PassListData;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

/**
 * Created by amit on 03-09-2016.
 */
public class SelectBookFlightRecyclerViewAdapter extends RecyclerView.Adapter<SelectBookFlightRecyclerViewAdapter.ViewHolder>
{
    private final Context context;
    private final IRecyclerViewHolderClicks listener;
    private PassListData passListData = null;
    private boolean isInActivePass;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, ListOfPass listOfPass);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout lytSelectBookFlightPassRow;
        private LinearLayout lytAdvanceBooking;
        private OTTextView txtTravelZoneName;
        private OTTextView txtTravelPeriodLabel;
        private OTTextView txtTravelPeriod;
        private OTTextView txtAvailableFlightsLabel;
        private OTTextView txtAvailableFlights;
        private OTTextView txtAdvanceBookingLabel;
        private OTTextView txtAdvanceBooking;
        private OTTextView txtBookFlight;
        private OTTextView txtOCN;
        private OTTextView txtViewDetails;
        private View view;

        public ViewHolder(View v)
        {
            super(v);
            this.lytSelectBookFlightPassRow = (LinearLayout)  v.findViewById(R.id.lytSelectBookFlightPassRow);
            this.lytAdvanceBooking          = (LinearLayout)  v.findViewById(R.id.lytAdvanceBooking);
            this.txtTravelZoneName          = (OTTextView)    v.findViewById(R.id.txtTravelZoneName);
            this.txtTravelPeriodLabel       = (OTTextView)    v.findViewById(R.id.txtTravelPeriodLabel);
            this.txtTravelPeriod            = (OTTextView)    v.findViewById(R.id.txtTravelPeriod);
            this.txtAvailableFlightsLabel   = (OTTextView)    v.findViewById(R.id.txtAvailableFlightsLabel);
            this.txtAvailableFlights        = (OTTextView)    v.findViewById(R.id.txtAvailableFlights);
            this.txtAdvanceBookingLabel     = (OTTextView)    v.findViewById(R.id.txtAdvanceBookingLabel);
            this.txtAdvanceBooking          = (OTTextView)    v.findViewById(R.id.txtAdvanceBooking);
            this.txtBookFlight              = (OTTextView)    v.findViewById(R.id.txtBookFlight);
            this.txtOCN                     = (OTTextView)    v.findViewById(R.id.txtOCN);
            this.txtViewDetails             = (OTTextView)    v.findViewById(R.id.txtViewDetails);
            this.view                       = (View)          v.findViewById(R.id.view);

        }
    }

    public SelectBookFlightRecyclerViewAdapter(boolean isInActivePass, Context context, PassListData list, IRecyclerViewHolderClicks l)
    {
        this.context = context;
        this.passListData = list;
        this.listener = l;
        this.isInActivePass = isInActivePass;

    }

    @Override
    public SelectBookFlightRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_book_flight_pass_row, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ListOfPass listOfPass = isInActivePass ? passListData.getListOfInactivePasses().get(position) : passListData.getListOfPasses().get(position);

        if (!(Utils.getCurrentProductId((Activity) context) == context.getResources().getInteger(R.integer.value_tgProductId_fpo))) {
            holder.lytAdvanceBooking.setVisibility(View.INVISIBLE);
        } else {
            holder.lytAdvanceBooking.setVisibility(View.VISIBLE);
        }

        holder.txtBookFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInActivePass) {
                    listener.onClickRecyclerItem(v, listOfPass);
                }

            }
        });

        holder.txtTravelZoneName.setText(listOfPass.getPassSmallView().getTravelZoneTitle());
        holder.txtTravelPeriodLabel.setText(listOfPass.getPassFullView().getValidityPeriodLabel());
        holder.txtTravelPeriod.setText(listOfPass.getPassFullView().getValidityStartDate() + " - " + listOfPass.getPassFullView().getValidityEndDate());
        holder.txtAvailableFlightsLabel.setText(listOfPass.getPassFullView().getAvailableFlightsLabel());
        holder.txtAvailableFlights.setText("" + listOfPass.getPassFullView().getAvailableFlightsNumber());
        holder.txtAdvanceBookingLabel.setText(listOfPass.getPassFullView().getAdvanceBookingLabel());
        holder.txtAdvanceBooking.setText("" + listOfPass.getPassFullView().getAdvanceNumberBookingDays());
        holder.txtBookFlight.setText(listOfPass.getPassFullView().getBookFlightLabel());
        holder.txtOCN.setText("OCN # " + listOfPass.getPassFullView().getConfirmationNumber());
        holder.txtViewDetails.setText(listOfPass.getPassSmallView().getLABLViewDetailsLabel());
        holder.txtViewDetails.setPaintFlags(holder.txtViewDetails.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.txtViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItem(v, listOfPass);
            }
        });

        if (position == (isInActivePass ? passListData.getListOfInactivePasses().size() : passListData.getListOfPasses().size()) - 1) {
            holder.view.setVisibility(View.VISIBLE);
        } else {
            holder.view.setVisibility(View.GONE);
        }

        if (!isInActivePass)
        {
            holder.txtBookFlight.getBackground().setAlpha(255);
        }
        else
        {
            holder.txtBookFlight.getBackground().setAlpha(128);
        }


    }

    @Override
    public int getItemCount()
    {
        return  isInActivePass ? passListData.getListOfInactivePasses().size() : passListData.getListOfPasses().size();
    }


}

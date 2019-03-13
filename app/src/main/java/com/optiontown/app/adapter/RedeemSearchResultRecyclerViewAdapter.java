package com.optiontown.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.redeem.FlightSmallView;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.RedeemSearchResultData;
import com.optiontown.app.model.redeem.Segment;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemConfirmBookFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemReviewBookFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 08-09-2016.
 */
public class RedeemSearchResultRecyclerViewAdapter extends RecyclerView.Adapter<RedeemSearchResultRecyclerViewAdapter.ViewHolder>
{
    private final Context context;
    private final IRecyclerViewHolderClicks listener;
    private RedeemSearchResultData redeemSearchResultData = null;
    private ImageLoader imageLoader;
    private ArrayList<Boolean> listSelected;
    private int viewType;
    private String className;
    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItemSelect(View v, List<Itinerarry> listItinary);
        public void onClickRecyclerItemDetail(View v, Itinerarry itinary);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final OTTextView txtOperation;
        private final OTTextView txtSelect;
        private final ImageView imgSelected;
        private final LinearLayout lytSelect;
        private OTTextView txtAirlineName;
        private NetworkImageView imgAirline;
        private View viewMargin;
        private LinearLayout lytRow;


        private LinearLayout lytSegments;




        public ViewHolder(View v)
        {
            super(v);
            this.txtAirlineName = (OTTextView)  v.findViewById(R.id.txtAirlineName);
            this.imgAirline = (NetworkImageView) v.findViewById(R.id.imgAirline);
            this.viewMargin = (View) v.findViewById(R.id.viewMargin);
            this.txtOperation = (OTTextView)  v.findViewById(R.id.txtOperation);
            this.txtSelect = (OTTextView)  v.findViewById(R.id.txtSelect);
            this.imgSelected = (ImageView)  v.findViewById(R.id.imgSelected);
            this.lytSelect = (LinearLayout) v.findViewById(R.id.lytSelect);
            this.lytRow = (LinearLayout) v.findViewById(R.id.lytRow);


            this.lytSegments = (LinearLayout) v.findViewById(R.id.lytSegments);
        }
    }

    public RedeemSearchResultRecyclerViewAdapter(String className, Context context, ArrayList<Boolean> listSelected, int viewType, RedeemSearchResultData list, IRecyclerViewHolderClicks l)
    {
        this.context = context;
        this.redeemSearchResultData = list;
        this.listener = l;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.viewType = viewType;
        this.listSelected = listSelected;
        this.className = className;

        /*for (int index = 0; index < redeemSearchResultData.getItinerarry().size(); index++) {
            listSelected.add(false);
        }*/
    }

    @Override
    public RedeemSearchResultRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.redeem_search_result_row, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Itinerarry itinerarry = redeemSearchResultData.getItinerarry().get(position);
        if(listSelected != null)
        {
            holder.lytSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LinearLayout lyt = (LinearLayout) v;
                    OTTextView txtSelect = (OTTextView) lyt.findViewById(R.id.txtSelect);
                    ImageView imgSelect = (ImageView) lyt.findViewById(R.id.imgSelected);

                    List<Itinerarry> data = new ArrayList<Itinerarry>();
                    for (int index = 0; index < redeemSearchResultData.getItinerarry().size(); index++)
                    {
                        if(index == position)
                        {
                            if(isReachedLimit(listSelected))
                            {
                                listSelected.set(index, false);
                            }
                            else
                            {
                                listSelected.set(index, !listSelected.get(index));
                            }
                        }
                        else
                        {
                            if((viewType == 0 && redeemSearchResultData.getJourneyTypeID() != 1) || redeemSearchResultData.getFlexibilityRangeHours() == 0)
                            {
                                listSelected.set(index, false);
                            }
                        }

                        if(listSelected.get(index))
                        {
                            data.add(redeemSearchResultData.getItinerarry().get(index));
                        }
                    }
                    notifyDataSetChanged();
                    listener.onClickRecyclerItemSelect(v, data);
                }

                private boolean isReachedLimit(ArrayList<Boolean> listSelected) {
                    int count = 0;
                    for (int index = 0; index < listSelected.size(); index++) {
                        if(listSelected.get(index))
                        {
                            count++;
                        }
                    }
                    //max 3 allowed
                    return count >= 3;
                }
            });
        }


        holder.lytRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItemDetail(v, redeemSearchResultData.getItinerarry().get(position));
            }
        });

        holder.lytSegments.removeAllViews();

        for (int index = 0; index < itinerarry.getSegments().size(); index++)
        {
            View v = LayoutInflater.from(context).inflate(R.layout.segment_select_row, null, false);
            Segment segment = itinerarry.getSegments().get(index);

            OTTextView txtDepartTimeOne = (OTTextView) v.findViewById(R.id.txtDepartTimeOne);
            OTTextView txtDepartAirportOne = (OTTextView)  v.findViewById(R.id.txtDepartAirportOne);
            OTTextView txtArriveTimeOne = (OTTextView)  v.findViewById(R.id.txtArriveTimeOne);
            OTTextView txtArriveAirportOne = (OTTextView)  v.findViewById(R.id.txtArriveAirportOne);
            OTTextView txtTravelTimeOne = (OTTextView)  v.findViewById(R.id.txtTravelTimeOne);

            ImageView imgArrowLeftOne = (ImageView) v.findViewById(R.id.imgArrowLeftOne);
            OTTextView txtConnectCount = (OTTextView)  v.findViewById(R.id.txtConnectCount);
            ImageView imgArrowRightOne = (ImageView) v.findViewById(R.id.imgArrowRightOne);

            FlightSmallView flightSmallViewFirst = segment.getLegList().get(0).getFlightSmallView();
            FlightSmallView flightSmallViewLast = segment.getLegList().get(segment.getLegList().size() - 1).getFlightSmallView();
            txtDepartTimeOne.setText(flightSmallViewFirst.getDepartAirlineTime());
            txtArriveTimeOne.setText(flightSmallViewLast.getArrivalAirlineTime());
            txtDepartAirportOne.setText(flightSmallViewFirst.getDepartAirlineCode());
            txtArriveAirportOne.setText(flightSmallViewLast.getArrivalAirlineCode());
            txtTravelTimeOne.setText(flightSmallViewFirst.getFlightDuration());

            if(segment.getLegList().size() > 1)
            {
                txtConnectCount.setText(Integer.toString(segment.getLegList().size() - 1));
            }
            else
            {
                imgArrowLeftOne.setImageResource(R.drawable.arrow_connecting);
                txtConnectCount.setVisibility(View.GONE);
                imgArrowRightOne.setVisibility(View.GONE);
            }

            holder.lytSegments.addView(v);
        }

        if(listSelected != null)
        {
            holder.txtSelect.setText(listSelected.get(position) ? itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getSelectedButtonLabel() : itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getSelectButtonLabel());
            holder.imgSelected.setVisibility(listSelected.get(position) ? View.VISIBLE : View.GONE);
            Utils.setBackground(holder.lytSelect, listSelected.get(position) ? context.getResources().getDrawable(R.drawable.rounded_green) : context.getResources().getDrawable(R.drawable.rounded_stroke_green));
            holder.txtSelect.setTextColor(listSelected.get(position) ? Color.parseColor("#ffffff") : Color.parseColor("#FF38971C"));

            holder.lytSelect.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.lytSelect.setVisibility(View.GONE);
        }




        holder.txtOperation.setText(itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getFooterLabel());
        holder.txtOperation.setVisibility(itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getFooterLabel().equals("") ? View.GONE : View.VISIBLE);
        String url = itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getAirlineLogo();
        holder.imgAirline.setImageUrl(url, imageLoader);
        holder.txtAirlineName.setText(itinerarry.getSegments().get(0).getLegList().get(0).getFlightFullView().getDisplayName());

        if(position == redeemSearchResultData.getItinerarry().size() - 1
                && !className.equals(new RedeemReviewBookFragment().getClass().getName())
                && !className.equals(new RedeemConfirmBookFragment().getClass().getName()))
        {
            holder.viewMargin.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.viewMargin.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount()
    {
        return  redeemSearchResultData.getItinerarry().size();
    }

}

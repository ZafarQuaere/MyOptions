package com.optiontown.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.legreview.FlightDetail;
import com.optiontown.app.model.legreview.PriceSummary;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.List;

/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author Ravi Kumar
 */
public class LegProductReviewFragmentAdapter extends RecyclerView.Adapter<LegProductReviewFragmentAdapter.ViewHolder> {


    List<FlightDetail> flightDetailList;
     Context mContext;
    private ImageLoader imageLoader;
    private PriceSummary mPriceSummary;


    public LegProductReviewFragmentAdapter(Context ctx, List<FlightDetail> flightDetailList, PriceSummary priceSummary) {
        this.flightDetailList = flightDetailList;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.mContext = ctx;
        mPriceSummary = priceSummary;

    }

    @Override
    public LegProductReviewFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v;
        if (mPriceSummary.getIsPartialOn()) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.legproduct_review_price_summary_row, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lp_review_list_row, parent, false);
        }

        ViewHolder vh = new ViewHolder(v);


        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (mPriceSummary.getIsPartialOn()) {
            if (flightDetailList.get(position).getUpcabinMaxShiftPrice() != null) {
                holder.imgAirlineLogo.setImageUrl(flightDetailList.get(position).getAirlineLogoImage(), imageLoader);
                holder.txtSignUpPriceLable.setText(flightDetailList.get(position).getTgpInitialPriceLabel());
                holder.txtAirlineId.setText(flightDetailList.get(position).getAirportCode() + " " + flightDetailList.get(position).getFlightNumber());
                holder.txtFlightFrom.setText(flightDetailList.get(position).getDepartureAirportDisplayName() + "(" + flightDetailList.get(position).getDepartureAirportCode() + ")");
                holder.txtFlightTo.setText(flightDetailList.get(position).getArriveAirportDisplayName() + "(" + flightDetailList.get(position).getDepartureAirportCode() + ")");
                holder.txtSignUpPrice.setText(flightDetailList.get(position).getDisplayCurrencySymbol() + " " + flightDetailList.get(position).getCombineOptionPrices());
                holder.txtOptionPriceLable.setText(flightDetailList.get(position).getShiftPriceHeadingLabel());
                holder.txtOptionPrice.setText(flightDetailList.get(position).getDisplayCurrencySymbol() + " " + flightDetailList.get(position).getUpcabinMaxShiftPrice());
                holder.txtPaxCountLable.setText(flightDetailList.get(position).getLABLPaxCountLabel());

                holder.txtPaxCount.setText(flightDetailList.get(position).getPaxCount().toString());

                holder.txtSubTotalLabel.setText(flightDetailList.get(position).getLABLSubtotalLabel());
                holder.txtSubTotal.setText(flightDetailList.get(position).getDisplayCurrencySymbol() + " " + flightDetailList.get(position).getAmountToPay());

            }
        } else {
            if (flightDetailList.get(position).getUpcabinMaxShiftPrice() != null) {
                if (AppVariables.noOfColumn.equalsIgnoreCase("three")) {
                    holder.txtToFromAp.setText(flightDetailList.get(position).getDepartureAirportDisplayName() + " - " + flightDetailList.get(position).getArriveAirportDisplayName());
                    holder.txtSignUpPriceP.setText(flightDetailList.get(position).getDisplayCurrencySymbol() + " " + flightDetailList.get(position).getCombineOptionPrices());
                    holder.txtUpgradePriceT.setText(flightDetailList.get(position).getDisplayCurrencySymbol() + " " + flightDetailList.get(position).getUpcabinMaxShiftPrice());
                } else {
                    holder.lytTwoColumn.setVisibility(View.VISIBLE);
                    holder.lytThreeColumn.setVisibility(View.GONE);
                    holder.txtToFromApTwoColumn.setText(flightDetailList.get(position).getDepartureAirportDisplayName() + " - " + flightDetailList.get(position).getArriveAirportDisplayName());
                    holder.txtUpgradePriceTwoColumn.setText(flightDetailList.get(position).getDisplayCurrencySymbol() + " " + flightDetailList.get(position).getUpcabinMaxShiftPrice());
                }
            }
        }


        if (Utils.isPassFlow((Activity) mContext)) {
            //holder.txtUpgradePriceTwoColumn.setText
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return flightDetailList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private OTTextView txtAirlineId, txtFlightFrom, txtSignUpPrice, txtFlightTo, txtSignUpPriceLable, txtOptionPriceLable,
                txtOptionPrice, txtPaxCountLable, txtPaxCount, txtSubTotalLabel, txtSubTotal, txtToFromAp, txtSignUpPriceP, txtUpgradePriceT,
                txtToFromApTwoColumn, txtUpgradePriceTwoColumn;
        private NetworkImageView imgAirlineLogo;

        private LinearLayout lytThreeColumn;
        private LinearLayout lytTwoColumn;


        public ViewHolder(View v) {
            super(v);

            lytTwoColumn = (LinearLayout) v.findViewById(R.id.lytTwoColumn);
            lytThreeColumn = (LinearLayout) v.findViewById(R.id.lytThreeColumn);
            txtToFromAp = (OTTextView) v.findViewById(R.id.txtToFromAp);
            txtSignUpPriceP = (OTTextView) v.findViewById(R.id.txtSignUpPriceP);
            txtUpgradePriceT = (OTTextView) v.findViewById(R.id.txtUpgradePriceT);
            txtToFromApTwoColumn = (OTTextView) v.findViewById(R.id.txtToFromApTwoColumn);
            txtUpgradePriceTwoColumn = (OTTextView) v.findViewById(R.id.txtUpgradePriceTwoColumn);


            imgAirlineLogo = (NetworkImageView) v.findViewById(R.id.imgAirlineLogo);
            txtAirlineId = (OTTextView) v.findViewById(R.id.txtAirlineId);
            txtFlightFrom = (OTTextView) v.findViewById(R.id.txtFlightFrom);
            txtFlightTo = (OTTextView) v.findViewById(R.id.txtFlightTo);
            txtSignUpPriceLable = (OTTextView) v.findViewById(R.id.txtSignUpPriceLable);
            txtSignUpPrice = (OTTextView) v.findViewById(R.id.txtSignUpPrice);
            txtOptionPriceLable = (OTTextView) v.findViewById(R.id.txtOptionPriceLable);
            txtOptionPrice = (OTTextView) v.findViewById(R.id.txtOptionPrice);
            txtPaxCountLable = (OTTextView) v.findViewById(R.id.txtPaxCountLable);
            txtPaxCount = (OTTextView) v.findViewById(R.id.txtPaxCount);
            txtSubTotalLabel = (OTTextView) v.findViewById(R.id.txtSubTotalLabel);
            txtSubTotal = (OTTextView) v.findViewById(R.id.txtSubTotal);

        }


    }
}
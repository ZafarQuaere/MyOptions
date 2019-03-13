package com.optiontown.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.utosearchresult.LegListObj;
import com.optiontown.app.model.utosearchresult.PaxDetail;
import com.optiontown.app.model.utosearchresult.UtosearchresultHome;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to attach item with data for recycler view at home fragment
 * @author Ravi Kumar
 */

public class ESoSearchResultLegListRecyclerViewAdapter extends
        RecyclerView.Adapter<ESoSearchResultLegListRecyclerViewAdapter.ViewHolder> {


    List<LegListObj> mLegListObj;
    private ImageLoader imageLoader;
    private LinearLayoutManager mLinearLayoutManager;
    private UtosearchresultHome mUtosearchresultHome;
    private RecyclerView.Adapter adapter;
    private Context ctx;
    private String shiftPriceLabel = "";
    private boolean canShowPriceComment;
    private IRecyclerViewHolderClicks listener;
    private IRecyclerViewHolderCheckChangeListerner checkListener;
    private  IRecyclerViewHolderPaxSelectedListener selectedPaxListener;
    private boolean isPartialShown = false;
    private Boolean checkedStatus = false;
    private List<PaxDetail> paxList;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int legPos, int cabinPos, Boolean isChecked,Boolean isPartialShown);
    }

    public static interface IRecyclerViewHolderCheckChangeListerner {
        public void onCheckedChangeItem(View v, int legPos, int cabinPos, Boolean isChecked,Boolean isPartialShown);

    }

    public static interface IRecyclerViewHolderPaxSelectedListener
    {
        public void onPaxSelectedClick(View v, int legPos, ArrayList<String> listSelectedPax);
    }



    public ESoSearchResultLegListRecyclerViewAdapter(Context ctx, String shiftPriceLabel, boolean canShowPriceComment, List<LegListObj> mLegListObj, UtosearchresultHome mUtosearchresultHome,
                                                     ESoSearchResultLegListRecyclerViewAdapter.IRecyclerViewHolderClicks listener, ESoSearchResultLegListRecyclerViewAdapter.IRecyclerViewHolderCheckChangeListerner checkListener
            ,  IRecyclerViewHolderPaxSelectedListener selectedPaxListener) {
        this.mLegListObj = mLegListObj;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx = ctx;
        this.mUtosearchresultHome = mUtosearchresultHome;
        this.shiftPriceLabel = shiftPriceLabel;
        this.listener = listener;
        this.checkListener = checkListener;
        this.selectedPaxListener = selectedPaxListener;
        this.canShowPriceComment = canShowPriceComment;
    }

    @Override
    public ESoSearchResultLegListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eso_search_result_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        LegListObj mLegListObjs = mLegListObj.get(position);
        holder.txtEsoAirlineId.setText(mLegListObjs.getOperatingAirlineCode() + " " + mLegListObjs.getOperatingAirlineCarrierFlightNumber());
        holder.txtEsoFromAirport.setText(mLegListObjs.getDepartAirportName());
        holder.txtEsoFromCode.setText(" (" + mLegListObjs.getDepartAirportCode() + ")");
        holder.txtEsoToAirport.setText(mLegListObjs.getArriveAirportName());
        holder.txtEsoToCode.setText(" (" + mLegListObjs.getArriveAirportCode() + ")");
        holder.txtEsoFromDate.setText(mLegListObjs.getDepartTime() + " " + mLegListObjs.getDepartTimeFormatted());
        holder.txtEsoToDate.setText(mLegListObjs.getArriveTime() + " " + mLegListObjs.getArriveTimeFormatted());

        holder.imgAirlineLogo.setImageUrl("https://www.optiontown.com/images/" + mLegListObjs.getOperatingAirlineLogo(), imageLoader);


        mLinearLayoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
        holder.recycleViewEmptySeatRow.setLayoutManager(mLinearLayoutManager);
        adapter = new ESoSearchClassRecyclerViewAdapter(ctx, mLegListObjs.getCabinDetailList(), mUtosearchresultHome, new ESoSearchClassRecyclerViewAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, int cabinPos, Boolean isChecked) {
                isPartialShown = holder.lytPassengerSelect.getVisibility()==View.VISIBLE? true : false;
                listener.onClickRecyclerItem(v, position, cabinPos, isChecked, isPartialShown);
                checkAllPaxStatus(holder.lytPassengerSelect, isChecked);
            }
        }, new ESoSearchClassRecyclerViewAdapter.IRecyclerViewHolderCheckChangeListerner() {
            @Override
            public void onCheckedChangeItem(View v, int cabinPos, Boolean isChecked) {
                isPartialShown = holder.lytPassengerSelect.getVisibility()==View.VISIBLE? true : false;
                checkListener.onCheckedChangeItem(v, position, cabinPos, isChecked, isPartialShown);
                checkAllPaxStatus(holder.lytPassengerSelect, isChecked);
            }
        }, mLegListObjs.getIsPndDoneOnLeg(), mLegListObjs.getIsPurchasedLeg(), mLegListObjs.getIsValidLeg());
        holder.recycleViewEmptySeatRow.setAdapter(adapter);

        holder.txtEsoEmptySeatPrice.setText(shiftPriceLabel);//+"*");



        if (mLegListObjs.getIsPurchasedLeg() && mLegListObjs.getIsDisplaySignUpPrice() > 0 && mLegListObjs.getOptionPrice() > 0) {
            holder.txtEsoSignUpPrice.setVisibility(View.VISIBLE);
            holder.txtEsoSignUpPriceLabel.setText(mUtosearchresultHome.getIfsObject().get(0).getInitialPriceLabel() + " ");
            holder.txtEsoSignUpPrice.setText(mUtosearchresultHome.getIfsObject().get(0).getDisplayCurrencySymbol() + " " + mLegListObjs.getOptionPrice().toString());
        }


        //Partial Sign Up Flow..
        RelativeLayout lytBuyForIndividualPassenger = (RelativeLayout) holder.lytPartialUpgrade.findViewById(R.id.lytBuyForIndividualPassenger);

        //Partial Sign up layout visibility condition
        holder.lytPartialUpgrade.setVisibility(mLegListObj.get(position).getIsPartialOn()?View.VISIBLE:View.GONE);

        final LinearLayout lytPassengerSelect = (LinearLayout) holder.lytPartialUpgrade.findViewById(R.id.lytPassengerSelect);
        final ImageView imgUserIcon = (ImageView) holder.lytPartialUpgrade.findViewById(R.id.imgUserIcon);
        final ImageView imgPlus = (ImageView) holder.lytPartialUpgrade.findViewById(R.id.imgPlus);
        final OTTextView txtBuyIndividualPassngerLabel = (OTTextView) holder.lytPartialUpgrade.findViewById(R.id.txtBuyIndividualPassngerLabel);
        txtBuyIndividualPassngerLabel.setText(mLegListObj.get(position).getPartialSignupTitle());

        lytBuyForIndividualPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytPassengerSelect.setVisibility(lytPassengerSelect.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                imgUserIcon.setImageResource(lytPassengerSelect.getVisibility() == View.VISIBLE ? R.drawable.pax_minus_icon : R.drawable.pax_plush_icon);
                imgPlus.setImageResource(lytPassengerSelect.getVisibility() == View.VISIBLE ? R.drawable.minus_icon : R.drawable.plus_icon);
            }
        });

        //Pax List for selection for Partial sign up
        paxList = mLegListObj.get(position).getPaxDetail();
        final ArrayList<String> listSelectedPax = new ArrayList<>();

        addPaxList(holder,listSelectedPax,checkedStatus,position);


    }

    private void addPaxList(ViewHolder holder, final ArrayList<String> listSelectedPax, Boolean checkedStatus, final int position) {
        for (int i = 0; i < paxList.size(); i++) {
            CheckBox checkBox = new CheckBox(ctx);
            checkBox.setText(paxList.get(i).getPaxName());
            checkBox.setTag(paxList.get(i).getPaxID());
            checkBox.setTextSize(Utils.convertPixelToDp(ctx, ctx.getResources().getDimension(R.dimen.size_font_13)));
            checkBox.setTextColor(Color.parseColor("#000000"));

            Utils.DEBUG("Checke Status : "+checkedStatus+" Checked Position : "+position);
            if (checkedStatus != null){
                checkBox.setChecked(checkedStatus);
            }

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        listSelectedPax.add(((CheckBox)buttonView).getTag().toString());
                    }
                    else
                    {
                        listSelectedPax.remove(((CheckBox)buttonView).getTag().toString());
                    }
                    selectedPaxListener.onPaxSelectedClick(buttonView, position, listSelectedPax);
                }
            });

            holder.lytPassengerSelect.addView(checkBox);
        }

    }


    private void checkAllPaxStatus(LinearLayout lytPassengerSelect, Boolean isChecked) {
        if(lytPassengerSelect == null)
            return;;

        if(lytPassengerSelect.getVisibility() == View.VISIBLE)
            return;


        for (int index = 0; index < lytPassengerSelect.getChildCount(); index++) {
            CheckBox childAt = (CheckBox)lytPassengerSelect.getChildAt(index);

            childAt.setChecked(isChecked);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mLegListObj.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recycleViewEmptySeatRow;
        private NetworkImageView imgAirlineLogo;
        private OTTextView txtEsoAirlineId, txtEsoFromAirport, txtEsoFromCode, txtEsoToAirport, txtEsoToCode, txtEsoFromDate, txtEsoToDate, txtEsoSignUpPriceLabel,
                txtEsoSignUpPrice, txtEsoEmptySeatPrice, txtEsoRefundNotice;
        private LinearLayout lytPartialUpgrade,lytPassengerSelect;

        public ViewHolder(View v) {
            super(v);
            recycleViewEmptySeatRow = (RecyclerView) v.findViewById(R.id.recycleViewEmptySeatRow);
            recycleViewEmptySeatRow.setHasFixedSize(true);

            txtEsoAirlineId = (OTTextView) v.findViewById(R.id.txtEsoAirlineId);
            txtEsoFromAirport = (OTTextView) v.findViewById(R.id.txtEsoFromAirport);
            txtEsoFromCode = (OTTextView) v.findViewById(R.id.txtEsoFromCode);
            txtEsoToAirport = (OTTextView) v.findViewById(R.id.txtEsoToAirport);
            txtEsoToCode = (OTTextView) v.findViewById(R.id.txtEsoToCode);
            txtEsoFromDate = (OTTextView) v.findViewById(R.id.txtEsoFromDate);
            txtEsoToDate = (OTTextView) v.findViewById(R.id.txtEsoToDate);
            txtEsoSignUpPriceLabel = (OTTextView) v.findViewById(R.id.txtEsoSignUpPriceLabel);
            txtEsoSignUpPrice = (OTTextView) v.findViewById(R.id.txtEsoSignUpPrice);
            txtEsoEmptySeatPrice = (OTTextView) v.findViewById(R.id.txtEsoEmptySeatPrice);

            lytPartialUpgrade = (LinearLayout) v.findViewById(R.id.lytPartialUpgrade);

            lytPassengerSelect = (LinearLayout) lytPartialUpgrade.findViewById(R.id.lytPassengerSelect);

            imgAirlineLogo = (NetworkImageView) v.findViewById(R.id.imgAirlineLogo);
            imgAirlineLogo.setDefaultImageResId(R.drawable.vistara);


        }
    }
}
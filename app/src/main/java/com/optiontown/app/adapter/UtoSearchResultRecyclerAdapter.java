package com.optiontown.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.toolbox.ImageLoader;
import com.optiontown.R;
import com.optiontown.app.model.selectproduct.BoostMyPrioritySelectedData;
import com.optiontown.app.model.utosearchresult.IfsObject;
import com.optiontown.app.model.utosearchresult.LegListObj;
import com.optiontown.app.model.utosearchresult.UtosearchresultHome;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author Ravi Kumar
 */
public class UtoSearchResultRecyclerAdapter extends RecyclerView.Adapter<UtoSearchResultRecyclerAdapter.ViewHolder> {
    List<IfsObject> mIfsObject;
    BoostMyPrioritySelectedData boostPriorityData;
    private ImageLoader imageLoader;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private Context ctx;
    private UtosearchresultHome mUtosearchresultHome;
    private IRecyclerViewHolderClicks listener;
    private IRecyclerViewHolderCheckChangeListerner checkListener;
    private IRecyclerViewHolderPaxSelectedListener selectedPaxListener;

    public UtoSearchResultRecyclerAdapter(Context ctx, List<IfsObject> mIfsObject, UtosearchresultHome mUtosearchresultHome,
                                          IRecyclerViewHolderClicks listener, IRecyclerViewHolderCheckChangeListerner checkListener,
                                         IRecyclerViewHolderPaxSelectedListener selectedPaxListener) {
        this.mIfsObject = mIfsObject;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx = ctx;
        this.mUtosearchresultHome = mUtosearchresultHome;

        this.listener = listener;
        this.checkListener = checkListener;
        this.selectedPaxListener = selectedPaxListener;
    }

    @Override
    public UtoSearchResultRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_upgrad_search_legproduct, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        List<LegListObj> mLegListObjs = mIfsObject.get(position).getLegListObj();
        mLinearLayoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
        String journeyType = mIfsObject.get(position).getJourneyTypeLabel();


        try {
            if (mUtosearchresultHome.getIsPassFlow()) {
                holder.txtNotice.setVisibility(View.GONE);
            } else {
                if (mIfsObject.get(position).isCanShowPriceComment()) {
                    holder.txtNotice.setVisibility(View.VISIBLE);
                } else {
                    holder.txtNotice.setVisibility(View.GONE);
                }

            }
        } catch (Exception e) {

        }

        if (position == 0) {
            if (mUtosearchresultHome != null) {

                try {
                    if (mUtosearchresultHome.getIsPassFlow() && mUtosearchresultHome.getShowContinueButton()) {

                        holder.lytLegPass.setVisibility(View.VISIBLE);
                        holder.txtCreditHelpMsg.setText(mUtosearchresultHome.getCreditHelpMessage());
                        holder.txtTgpFgSortDesc.setText(mUtosearchresultHome.getTgpFgSortDesc());
                        holder.txtPassCabinNames.setText(mUtosearchresultHome.getPassCabinNames());
                    } else {
                        holder.lytLegPass.setVisibility(View.GONE);
                    }
                } catch (Exception e) {

                }
            }
        } else {
            holder.lytLegPass.setVisibility(View.GONE);
        }
        holder.txtNotice.setText("*"+mIfsObject.get(position).getUTOAvailableHeadingGuideLabel());
        //mLegListObjs.get(position).getAvailableSelect();
        holder.txtFlightType.setText(mIfsObject.get(position).getJourneyTypeLabel());


        if (Utils.getCurrentProductId((Activity) ctx) == ((Activity) ctx).getResources().getInteger(R.integer.value_tgProductId_utp)) {
            holder.txtSignUpPrice.setVisibility(View.GONE);
        } else {
            if (mLegListObjs.get(0).getOptionPrice() > 0) {
                holder.txtSignUpPrice.setVisibility(View.VISIBLE);
                holder.txtSignUpPrice.setText(mUtosearchresultHome.getIfsObject().get(0).getInitialPriceLabel() + " " + mUtosearchresultHome.getIfsObject().get(0).getDisplayCurrencySymbol() + " " + mLegListObjs.get(0).getOptionPrice());
            } else {
                holder.txtSignUpPrice.setVisibility(View.GONE);
            }
        }


        holder.recycleLegList.setLayoutManager(mLinearLayoutManager);
        adapter = new UtoSearchResultLegDataRecyclerAdapter(ctx, journeyType, mLegListObjs, mUtosearchresultHome, new UtoSearchResultLegDataRecyclerAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, int legPos, int cabinPos, Boolean checked, Boolean isPartialShown) {
                listener.onClickRecyclerItem(v, position, legPos, cabinPos,checked,isPartialShown);
            }
        }, new UtoSearchResultLegDataRecyclerAdapter.IRecyclerViewHolderCheckChangeListerner() {
            @Override
            public void onCheckedChangeItem(View v, int legPos, int cabinPos, Boolean checked, Boolean isPartialShown) {
                checkListener.onCheckedChangeItem(v, position, legPos, cabinPos,checked,isPartialShown);
            }
        }, new UtoSearchResultLegDataRecyclerAdapter.IRecyclerViewHolderPaxSelectedListener() {
            @Override
            public void onPaxSelectedClick(View v, int legPos, ArrayList<String> listSelectedPax) {
                selectedPaxListener.onPaxSelectedClick(v, position, legPos, listSelectedPax);
            }

        });
        holder.recycleLegList.setAdapter(adapter);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mIfsObject.size();
    }

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int ifsObjectPos, int legPos, int cabinPos, Boolean isChecked,
                                        boolean isPartialShown);


    }

    public static interface IRecyclerViewHolderCheckChangeListerner {
        public void onCheckedChangeItem(View v, int ifsObjectPos, int legPos, int cabinPos, Boolean isChecked,
                                        boolean isPartialShown);

    }

    public static interface IRecyclerViewHolderPaxSelectedListener
    {
        public void onPaxSelectedClick(View v, int ifsObjectPos, int legPos, ArrayList<String> listSelectedPax);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recycleLegList;
        private OTTextView txtNotice, txtFlightType, txtSignUpPrice;

        private LinearLayout lytLegPass;
        private OTTextView txtCreditHelpMsg;
        private OTTextView txtTgpFgSortDesc;
        private OTTextView txtPassCabinNames;

        public ViewHolder(View v) {
            super(v);
            txtNotice = (OTTextView) v.findViewById(R.id.txtNotice);
            recycleLegList = (RecyclerView) v.findViewById(R.id.recycleLegList);
            recycleLegList.setHasFixedSize(true);
            txtFlightType = (OTTextView) v.findViewById(R.id.txtFlightType);
            txtSignUpPrice = (OTTextView) v.findViewById(R.id.txtSignUpPrice);


            lytLegPass = (LinearLayout) v.findViewById(R.id.lytLegPass);
            txtCreditHelpMsg = (OTTextView) v.findViewById(R.id.txtCreditHelpMsg);
            txtTgpFgSortDesc = (OTTextView) v.findViewById(R.id.txtTgpFgSortDesc);
            txtPassCabinNames = (OTTextView) v.findViewById(R.id.txtPassCabinNames);
        }
    }
}
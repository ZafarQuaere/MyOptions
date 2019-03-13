package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.utosearchresult.IfsObject;
import com.optiontown.app.model.utosearchresult.LegListObj;
import com.optiontown.app.model.utosearchresult.UtosearchresultHome;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author Ravi Kumar
 */
public class ESoSearchResultRecyclerViewAdapter extends RecyclerView.Adapter<ESoSearchResultRecyclerViewAdapter.ViewHolder> {


    List<IfsObject> mIfsObject;
    private ImageLoader imageLoader;
    private LinearLayoutManager mLinearLayoutManager;
    private UtosearchresultHome mUtosearchresultHome;
    private RecyclerView.Adapter adapter;
    private Context ctx;
    private IRecyclerViewHolderClicks listener;
    private IRecyclerViewHolderCheckChangeListerner checkListener;
    private IRecyclerViewHolderPaxSelectedListener selectedPaxListener;

    public ESoSearchResultRecyclerViewAdapter(Context ctx, List<IfsObject> mIfsObject, UtosearchresultHome mUtosearchresultHome,
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
    public ESoSearchResultRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eso_search_result_row_leg, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        List<LegListObj> mLegListObjs = mIfsObject.get(position).getLegListObj();
        mLinearLayoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
        holder.recycleEsoLegList.setLayoutManager(mLinearLayoutManager);
        String shiftPriceLabel = mIfsObject.get(position).getLABLTSRShiftPriceLabel();
        boolean canShowPriceComment = mIfsObject.get(position).isCanShowPriceComment();

        adapter = new ESoSearchResultLegListRecyclerViewAdapter(ctx, shiftPriceLabel, canShowPriceComment, mLegListObjs, mUtosearchresultHome, new ESoSearchResultLegListRecyclerViewAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, int legPos, int cabinPos, Boolean isChecked,Boolean isPartialShown) {
                listener.onClickRecyclerItem(v, position, legPos, cabinPos, isChecked,isPartialShown);

            }
        }, new ESoSearchResultLegListRecyclerViewAdapter.IRecyclerViewHolderCheckChangeListerner() {
            @Override
            public void onCheckedChangeItem(View v, int legPos, int cabinPos, Boolean isChecked,Boolean isPartialShown) {
                checkListener.onCheckedChangeItem(v, position, legPos, cabinPos, isChecked,isPartialShown);
            }
        },  new ESoSearchResultLegListRecyclerViewAdapter.IRecyclerViewHolderPaxSelectedListener() {
            @Override
            public void onPaxSelectedClick(View v, int legPos, ArrayList<String> listSelectedPax) {
                selectedPaxListener.onPaxSelectedClick(v, position, legPos, listSelectedPax);
            }

        });


        if (position == 0) {
            if (mUtosearchresultHome != null) {
                if (mUtosearchresultHome.getIsPassFlow() && mUtosearchresultHome.getShowContinueButton()) {
                    holder.lytPass.setVisibility(View.VISIBLE);
                    holder.txtCreditHelpMsg.setText(mUtosearchresultHome.getCreditHelpMessage());
                    holder.txtTgpFgSortDesc.setText(mUtosearchresultHome.getTgpFgSortDesc());
                    holder.txtPassCabinNames.setText(mUtosearchresultHome.getPassCabinNames());
                } else {
                    holder.lytPass.setVisibility(View.GONE);
                }
            }
        } else {
            holder.lytPass.setVisibility(View.GONE);
        }

        //  holder.txtSignUpPrice.setText(mUtosearchresultHome.getIfsObject().get(0).getInitialPriceLabel()+" "+mUtosearchresultHome.getIfsObject().get(0).getDisplayCurrencySymbol()+" "+mLegListObjs.get(0).getOptionPrice());

        holder.recycleEsoLegList.setAdapter(adapter);
        holder.txtEsoRefundNotice.setText("*" + "" + mUtosearchresultHome.getIfsObject().get(position).getUTOAvailableHeadingGuideLabel());
        if (canShowPriceComment) {
            holder.txtEsoRefundNotice.setVisibility(View.VISIBLE);
        } else {
            holder.txtEsoRefundNotice.setVisibility(View.GONE);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mIfsObject.size();
    }

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int ifsObject, int legPos, int cabinPos, Boolean isChecked, Boolean isPartialShown);
    }


    public static interface IRecyclerViewHolderCheckChangeListerner {
        public void onCheckedChangeItem(View v, int ifsObject, int legPos, int cabinPos, Boolean isChecked, Boolean isPartialShown);

    }

    public interface IRecyclerViewHolderPaxSelectedListener {
        public void onPaxSelectedClick(View v, int ifsObjectPos, int legPos, ArrayList<String> listSelectedPax);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recycleEsoLegList;
        private NetworkImageView imgAirlineLogo;
        private OTTextView txtSignUpPrice, txtEsoRefundNotice;
        private LinearLayout lytPass;
        private LinearLayout lytBottom;

        private OTTextView txtCreditHelpMsg;
        private OTTextView txtTgpFgSortDesc;
        private OTTextView txtPassCabinNames;

        public ViewHolder(View v) {
            super(v);
            recycleEsoLegList = (RecyclerView) v.findViewById(R.id.recycleEsoLegList);
            recycleEsoLegList.setHasFixedSize(true);
            txtEsoRefundNotice = (OTTextView) v.findViewById(R.id.txtEsoRefundNotice);
            txtSignUpPrice = (OTTextView) v.findViewById(R.id.txtSignUpPrice);

            lytBottom = (LinearLayout) v.findViewById(R.id.lytBottom);
            lytPass = (LinearLayout) v.findViewById(R.id.lytPass);
            txtCreditHelpMsg = (OTTextView) v.findViewById(R.id.txtCreditHelpMsg);
            txtTgpFgSortDesc = (OTTextView) v.findViewById(R.id.txtTgpFgSortDesc);
            txtPassCabinNames = (OTTextView) v.findViewById(R.id.txtPassCabinNames);


        }
    }
}
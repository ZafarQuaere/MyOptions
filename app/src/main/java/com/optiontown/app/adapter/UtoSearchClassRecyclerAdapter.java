package com.optiontown.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.legproducthomepage.UpgradeHelper;
import com.optiontown.app.model.selectproduct.BoostMyPrioritySelectedData;
import com.optiontown.app.model.utosearchresult.CabinDetailList;
import com.optiontown.app.model.utosearchresult.IfsObject;
import com.optiontown.app.model.utosearchresult.UtosearchresultHome;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.view.customview.OTTextView;

import java.util.List;

/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author Ravi Kumar
 */
public class UtoSearchClassRecyclerAdapter extends RecyclerView.Adapter<UtoSearchClassRecyclerAdapter.ViewHolder> {

    List<CabinDetailList> mCabinDetailLists;
    Context ctx;
    AppSharedPrefs sharedPrefs;
    private ImageLoader imageLoader;
    private boolean PNDDone, PurchagedLeg, validLeg;
    private List<IfsObject> mIfsObject;
    private UtosearchresultHome mUtosearchresultHome;

    private String saveAmount = "";
    private IRecyclerViewHolderClicks listener;
    private String selectUpgrade;
    private IRecyclerViewHolderCheckChangeListerner checkListener;

    public UtoSearchClassRecyclerAdapter(Context ctx, String selectUpgrade, String saveAmount, List<CabinDetailList> mCabinDetailLists, boolean isPNDDone,
                                         boolean IsPurchagedLeg, UtosearchresultHome mUtosearchresultHome,  Boolean validLeg,
                                         IRecyclerViewHolderClicks listener, IRecyclerViewHolderCheckChangeListerner checkListener) {
        this.mCabinDetailLists = mCabinDetailLists;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx = ctx;
        this.PNDDone = isPNDDone;
        this.PurchagedLeg = IsPurchagedLeg;
        this.validLeg = validLeg;
        sharedPrefs = AppSharedPrefs.getInstance(ctx);
        this.mUtosearchresultHome = mUtosearchresultHome;

        this.listener = listener;
        this.checkListener = checkListener;
        this.selectUpgrade = selectUpgrade;
        this.saveAmount = saveAmount;

    }

    @Override
    public UtoSearchClassRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_available_class, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.lytConfirmedBooking.setBackgroundDrawable(SetBackgroundcolor("#CD3301", "#000000"));
        holder.lytStandBy.setBackgroundDrawable(SetBackgroundcolor("#CD3301", "#000000"));
        String cabinName, ConfirmedUpgradePrice = "", tOriginalShiftPrice1;
        boolean isStandBy, isConfirmed, isConfirmDisplay;

        holder.txtPNDMessage.setText(mCabinDetailLists.get(position).getStatusLabel());
        holder.imgPNDIcon.setVisibility(View.GONE);

        cabinName = mCabinDetailLists.get(position).getCabinName();
        isStandBy = mCabinDetailLists.get(position).isStandBy();
        holder.txtClassName.setText(cabinName);


        if ( mCabinDetailLists.get(position).getConfirmUpgradePrice().equals("") ||  mCabinDetailLists.get(position).getConfirmUpgradePrice() == null) {
            ConfirmedUpgradePrice = mCabinDetailLists.get(position).getConfirmedUpgradePrice();
        }
        tOriginalShiftPrice1 = mCabinDetailLists.get(position).getOriginalShiftPrice1();
        isConfirmed = mCabinDetailLists.get(position).isConfirmed();
        isConfirmDisplay = mCabinDetailLists.get(position).getIsConfirmUpgradeDisplay();
        if (saveAmount.equalsIgnoreCase("") || saveAmount.equalsIgnoreCase("0")) {
            holder.txtActualRateStandBy.setVisibility(View.GONE);
        } else {
            holder.txtActualRateStandBy.setText(saveAmount);
            holder.txtActualRateStandBy.setVisibility(View.VISIBLE);

        }

        holder.txtActualRateStandBy.setPaintFlags(holder.txtActualRateStandBy.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.chkStandBy.setChecked(isStandBy);


        holder.rdpConfirmedBooking.setChecked(isConfirmed);
        holder.rdpConfirmedBooking.setText(ConfirmedUpgradePrice);

        if (isStandBy) {
            holder.lytStandBy.setBackgroundDrawable(SetBackgroundcolor("#FCF6D2", "#C8C8C8"));
        } else {
            holder.lytStandBy.setBackgroundDrawable(SetBackgroundcolor("#CD3301", "#00000000"));
        }
        if (isConfirmed) {
            holder.lytConfirmedBooking.setBackgroundDrawable(SetBackgroundcolor("#FCF6D2", "#C8C8C8"));
        } else {
            holder.lytConfirmedBooking.setBackgroundDrawable(SetBackgroundcolor("#CD3301", "#00000000"));
        }
        if (isConfirmDisplay) {
            holder.lytConfirmedBooking.setVisibility(View.VISIBLE);
            holder.txtConfrmUpgrade.setVisibility(View.GONE);
            holder.lytSelectUpgrade.setVisibility(View.GONE);
            holder.txtActualRateStandBy.setVisibility(View.GONE);

        } else {
            holder.lytSelectUpgrade.setVisibility(View.VISIBLE);
            holder.lytConfirmedBooking.setVisibility(View.GONE);
            holder.txtConfrmUpgrade.setVisibility(View.GONE);

        }
        holder.chkStandBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.chkStandBy.isChecked()) {
                    listener.onClickRecyclerItem(v, position, true);
                    holder.chkStandBy.setTextColor(ctx.getResources().getColor(R.color.color_font_black));
                    mCabinDetailLists.get(position).setStandBy(true);
                    for (int i = 0; i < mCabinDetailLists.size(); i++) {
                        mCabinDetailLists.get(i).setConfirmed(false);
                    }
                    notifyDataSetChanged();
                } else {
                    listener.onClickRecyclerItem(v, position, false);
                    mCabinDetailLists.get(position).setStandBy(false);
                    holder.chkStandBy.setTextColor(ctx.getResources().getColor(R.color.caldroid_white));
                    notifyDataSetChanged();
                }
                UpgradeHelper mUpgradeHelper = new UpgradeHelper();
                mUpgradeHelper.setmCabinDetailLists(mCabinDetailLists);
                sharedPrefs.put("SearchedData", ParseManager.getInstance().toJSON(mUpgradeHelper));
                sharedPrefs.put("SearchedDataAll", ParseManager.getInstance().toJSON(mUtosearchresultHome));
            }


        });

        holder.rdpConfirmedBooking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkListener.onCheckedChangeItem(buttonView, position, true);
                    holder.txtActualRateConfirmed.setVisibility(View.GONE);
                    holder.rdpConfirmedBooking.setTextColor(ctx.getResources().getColor(R.color.color_font_black));
                    for (int i = 0; i < mCabinDetailLists.size(); i++) {
                        if (i == position) {
                            mCabinDetailLists.get(i).setConfirmed(true);
                        } else {
                            mCabinDetailLists.get(i).setConfirmed(false);
                        }
                        mCabinDetailLists.get(i).setStandBy(false);
                        notifyDataSetChanged();
                    }
                } else {
                    checkListener.onCheckedChangeItem(buttonView, position, false);
                    holder.rdpConfirmedBooking.setTextColor(ctx.getResources().getColor(R.color.caldroid_white));
                }
                UpgradeHelper mUpgradeHelper = new UpgradeHelper();
                mUpgradeHelper.setmCabinDetailLists(mCabinDetailLists);
                sharedPrefs.put("SearchedData", ParseManager.getInstance().toJSON(mUpgradeHelper));
                sharedPrefs.put("SearchedDataAll", ParseManager.getInstance().toJSON(mUtosearchresultHome));
            }
        });


       // holder.txtClassPND.setText(mCabinDetailLists.get(position).getCabinName());
        holder.txtSelectUpgrade.setText(selectUpgrade);

        if (PurchagedLeg && !mCabinDetailLists.get(position).getPndDoneOnLeg() &&
                mCabinDetailLists.get(position).getIsAvailable() == 0) {



            holder.lytContent.setVisibility(View.GONE);
            holder.lytBeforePND.setVisibility(View.VISIBLE);
            holder.imgPNDIcon.setVisibility(View.VISIBLE);
            holder.imgPNDIcon.setImageUrl("http://www.optiontown.com/images/uto_selected.gif", imageLoader);
            holder.imgPNDIcon.setDefaultImageResId(R.drawable.uto_selected);
            holder.txtPNDMessage.setText(mCabinDetailLists.get(position).getStatusLabel());
            if (mCabinDetailLists.get(position).getStatusLabel().contains("Not Available")) {

                holder.txtPNDMessage.setText(mCabinDetailLists.get(position).getStatusLabel());
                holder.imgPNDIcon.setVisibility(View.GONE);
                holder.txtPNDMessage.setTextColor(Color.parseColor("#333333"));
                holder.lytBeforePND.setBackgroundColor(Color.parseColor("#F9F89B"));
            } else {
                holder.txtPNDMessage.setTextColor(Color.parseColor("#CC3300"));
            }
            holder.txtClassPND.setTextColor(Color.parseColor("#000000"));
            holder.txtClassPND.setText(cabinName);

        } else if (PurchagedLeg && mCabinDetailLists.get(position).getPndDoneOnLeg() && mCabinDetailLists.get(position).getIsAvailable() == 0) {

            holder.txtClassPND.setText(cabinName);
            holder.txtClassPND.setTextColor(Color.parseColor("#000000"));

            holder.lytContent.setVisibility(View.GONE);
            holder.lytBeforePND.setVisibility(View.VISIBLE);
            holder.imgPNDIcon.setVisibility(View.VISIBLE);
            holder.imgPNDIcon.setImageUrl("http://www.optiontown.com/images/uto_selected.gif", imageLoader);
            holder.imgPNDIcon.setDefaultImageResId(R.drawable.uto_selected);
            holder.txtPNDMessage.setText(mCabinDetailLists.get(position).getStatusLabel());

            if (mCabinDetailLists.get(position).getStatusLabel().contains("Not Available")) {
                holder.imgPNDIcon.setVisibility(View.GONE);
                holder.txtPNDMessage.setTextColor(Color.parseColor("#333333"));
                holder.lytBeforePND.setBackgroundColor(Color.parseColor("#F9F89B"));
            } else {
                holder.txtPNDMessage.setTextColor(Color.parseColor("#CC3300"));
            }

        } else if (mCabinDetailLists.get(position).getIsAvailable() == 1) {

            try {
                if (mUtosearchresultHome.getIsPassFlow()) {
                        try {
                            if (mCabinDetailLists.get(position).getCreditRequiredLabel() > 0) {
                                holder.lytBeforePND.setVisibility(View.GONE);
                                holder.lytContent.setVisibility(View.VISIBLE);
                                holder.chkStandBy.setText(mCabinDetailLists.get(position).getCreditRequiredLabel() + " " + mCabinDetailLists.get(position).getCreditTitle());

                            } else {
                                holder.lytBeforePND.setVisibility(View.VISIBLE);
                                holder.lytContent.setVisibility(View.GONE);
                                holder.txtClassPND.setText(mCabinDetailLists.get(position).getCabinName());
                                holder.txtPNDMessage.setText(mCabinDetailLists.get(position).getUtoAvailablePassNotApp());

                            }
                        } catch (Exception e) {

                        }

                } else {
                    holder.lytBeforePND.setVisibility(View.GONE);
                    holder.chkStandBy.setText(tOriginalShiftPrice1);
                }
            } catch (Exception e) {

            }
        } else if (mCabinDetailLists.get(position).getIsAvailable() == 0 && !PurchagedLeg) {

            holder.txtPNDMessage.setText(mCabinDetailLists.get(position).getStatusLabel());
            holder.txtClassPND.setText(mCabinDetailLists.get(position).getCabinName());
            holder.imgPNDIcon.setVisibility(View.GONE);
            holder.lytContent.setVisibility(View.GONE);

        }
        else {
            holder.lytBeforePND.setVisibility(View.GONE);
        }


    }


    @Override
    public int getItemCount() {
        return mCabinDetailLists.size();
    }

    private GradientDrawable SetBackgroundcolor(String backgrondcolor, String stockolor) {
        int backcolor = Color.parseColor(backgrondcolor);
        int strockcolor = Color.parseColor(stockolor);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(backcolor);
        gradientDrawable.setStroke(3, strockcolor);
        gradientDrawable.setCornerRadius(25.0f);
        gradientDrawable.setCornerRadius(14);
        return gradientDrawable;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView imgOption, imgPNDIcon;
        private OTTextView txtActualRateStandBy, txtActualRateConfirmed, txtClassName, txtClassPND, txtPNDMessage, txtUtoNotAvailable, txtSelectUpgrade, txtConfrmUpgrade;
        private CheckBox chkStandBy;
        private LinearLayout lytStandBy, lytConfirmedBooking, lytContent, lytBeforePND, UpgradeNotAvailLyt, lytSelectUpgrade;
        private RadioButton rdpConfirmedBooking;

        public ViewHolder(View v) {
            super(v);
            txtActualRateStandBy = (OTTextView) v.findViewById(R.id.txtActualRateStandBy);
            txtActualRateConfirmed = (OTTextView) v.findViewById(R.id.txtActualRateConfirmed);
            txtClassName = (OTTextView) v.findViewById(R.id.txtClassName);
            txtClassPND = (OTTextView) v.findViewById(R.id.txtClassPND);
            txtPNDMessage = (OTTextView) v.findViewById(R.id.txtPNDMessage);
            txtUtoNotAvailable = (OTTextView) v.findViewById(R.id.txtUtoNotAvailable);
            txtSelectUpgrade = (OTTextView) v.findViewById(R.id.txtSelectUpgrade);
            txtConfrmUpgrade = (OTTextView) v.findViewById(R.id.txtConfrmUpgrade);
            imgPNDIcon = (NetworkImageView) v.findViewById(R.id.imgPNDIcon);
            chkStandBy = (CheckBox) v.findViewById(R.id.chkStandBy);
            lytStandBy = (LinearLayout) v.findViewById(R.id.lytStandBy);
            lytConfirmedBooking = (LinearLayout) v.findViewById(R.id.lytConfirmedBooking);
            lytContent = (LinearLayout) v.findViewById(R.id.lytContent);
            lytBeforePND = (LinearLayout) v.findViewById(R.id.lytBeforePND);
            UpgradeNotAvailLyt = (LinearLayout) v.findViewById(R.id.UpgradeNotAvailLyt);
            lytSelectUpgrade = (LinearLayout) v.findViewById(R.id.lytSelectUpgrade);
            rdpConfirmedBooking = (RadioButton) v.findViewById(R.id.rdpConfirmedBooking);
        }
    }

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int cabinPos, Boolean isChecked);
    }

    public static interface IRecyclerViewHolderCheckChangeListerner {
        public void onCheckedChangeItem(View v, int cabinPos, Boolean isChecked);

    }
}
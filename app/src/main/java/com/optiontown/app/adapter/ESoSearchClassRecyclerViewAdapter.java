package com.optiontown.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.legproducthomepage.UpgradeHelper;
import com.optiontown.app.model.utosearchresult.CabinDetailList;
import com.optiontown.app.model.utosearchresult.IfsObject;
import com.optiontown.app.model.utosearchresult.UtosearchresultHome;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.legproducts.FlightSeatViewFragment;

import java.util.List;

/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author Zafar Imam
 */
public class ESoSearchClassRecyclerViewAdapter extends RecyclerView.Adapter<ESoSearchClassRecyclerViewAdapter.ViewHolder> {

    public static Boolean chBxBg;
    List<CabinDetailList> mCabinDetailList;
    AppSharedPrefs sharedPrefs;
    String selectEso;
    private ImageLoader imageLoader;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private Context ctx;
    private List<IfsObject> mIfsObject;
    private boolean PNDDone, purchasedLeg, validLeg;
    private UtosearchresultHome mUtosearchresultHome;
    private boolean chkPnd;
    private IRecyclerViewHolderClicks listener;
    private IRecyclerViewHolderCheckChangeListerner checkListener;

    public ESoSearchClassRecyclerViewAdapter(Context ctx, List<CabinDetailList> mCabinDetailList, UtosearchresultHome mUtosearchresultHome,  IRecyclerViewHolderClicks listener,
                                             ESoSearchClassRecyclerViewAdapter.IRecyclerViewHolderCheckChangeListerner checkListener, boolean PNDDone, boolean purchasedLeg, boolean validLeg) {
        this.mCabinDetailList = mCabinDetailList;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx = ctx;
        this.mUtosearchresultHome = mUtosearchresultHome;
        sharedPrefs = AppSharedPrefs.getInstance(ctx);
        this.listener = listener;
        this.checkListener = checkListener;
        this.PNDDone = PNDDone;
        this.purchasedLeg = purchasedLeg;
        this.validLeg = validLeg;
    }

    @Override
    public ESoSearchClassRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eso_available_or_not_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        selectEso = mUtosearchresultHome.getIfsObject().get(0).getLegListObj().get(0).getAvailableSelect();
        chkPnd = mUtosearchresultHome.getIfsObject().get(0).getLegListObj().get(0).getIsPurchasedLeg();
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String ClassName, tOriginalShiftPrice1;
        //  String styleIdUpcabinStandBy = "";
        // boolean isStandBy, isConfirmed, isConfirmDisplay;
        ClassName = mCabinDetailList.get(position).getCabinName();
        tOriginalShiftPrice1 = mCabinDetailList.get(position).getOriginalShiftPrice1();
        holder.txtEsoavailableSeat.setText(ClassName);
        holder.imgEmtpySeat.setImageUrl("https://www.optiontown.com/images/" + mCabinDetailList.get(position).getUp_Cabin_image(), imageLoader);

        holder.chk_selectEso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    checkListener.onCheckedChangeItem(buttonView, position, true);
                    Utils.setBackground(holder.rLytSelectEso, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button_selected));
                    chBxBg = true;
                    mCabinDetailList.get(position).setStandBy(true);
                    UpgradeHelper mUpgradeHelper = new UpgradeHelper();
                    mUpgradeHelper.setmCabinDetailLists(mCabinDetailList);
                    sharedPrefs.put("SearchedData", ParseManager.getInstance().toJSON(mUpgradeHelper));
                    sharedPrefs.put("SearchedDataAll", ParseManager.getInstance().toJSON(mUtosearchresultHome));
                } else {
                    mCabinDetailList.get(position).setStandBy(false);
                    sharedPrefs.put("SearchedDataAll", ParseManager.getInstance().toJSON(mUtosearchresultHome));
                    checkListener.onCheckedChangeItem(buttonView, position, false);
                    mCabinDetailList.get(position).setStandBy(false);
                    Utils.setBackground(holder.rLytSelectEso, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button));
                    chBxBg = false;
                }
            }
        });

        holder.rLytSelectEso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.chk_selectEso.setChecked(true);
                listener.onClickRecyclerItem(v, position, true);
                if ((chBxBg) && (holder.chk_selectEso.isChecked())) {
                    listener.onClickRecyclerItem(v, position, true);
                    holder.chk_selectEso.setChecked(true);
                    Utils.setBackground(holder.rLytSelectEso, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button_selected));
                    chBxBg = false;
                    mCabinDetailList.get(position).setStandBy(true);
                    // styleIdUpcabinStandBy = mCabinDetailList.get(position).getStyleIdUpcabinStandBy();
                    UpgradeHelper mUpgradeHelper = new UpgradeHelper();
                    mUpgradeHelper.setmCabinDetailLists(mCabinDetailList);
                    sharedPrefs.put("SearchedData", ParseManager.getInstance().toJSON(mUpgradeHelper));
                    sharedPrefs.put("SearchedDataAll", ParseManager.getInstance().toJSON(mUtosearchresultHome));
                } else {
                    mCabinDetailList.get(position).setStandBy(false);
                    listener.onClickRecyclerItem(v, position, false);
                    holder.chk_selectEso.setChecked(false);
                    mCabinDetailList.get(position).setStandBy(false);
                    Utils.setBackground(holder.rLytSelectEso, ctx.getResources().getDrawable(R.drawable.empty_seat_gradiant_button));

                }
            }
        });
        if (purchasedLeg && !mCabinDetailList.get(position).getPndDoneOnLeg()&&mCabinDetailList.get(position).getIsAvailable() == 0) {
            holder.rLytSelectEso.setVisibility(View.GONE);
            holder.rLytBeforePnd.setVisibility(View.VISIBLE);
            holder.imgDecisionPending.setImageUrl("http://www.optiontown.com/images/uto_selected.gif", imageLoader);
            holder.imgDecisionPending.setDefaultImageResId(R.drawable.uto_selected);
            holder.txtBeforePnd.setText(mCabinDetailList.get(position).getStatusLabel());
           // btnContinue.setVisibility(View.GONE);
            if (mCabinDetailList.get(position).getStatusLabel().contains("Not Available")){
                holder.imgDecisionPending.setVisibility(View.GONE);
                holder.txtBeforePnd.setTextColor(Color.parseColor("#333333"));
                holder.rLytBeforePnd.setBackgroundColor(Color.parseColor("#F9F89B"));
            }
            else if (mCabinDetailList.get(position).getStatusLabel().contains("Not Assigned")){
                holder.imgDecisionPending.setVisibility(View.GONE);
                holder.txtBeforePnd.setTextColor(Color.parseColor("#CC3300"));
            }
            else {
                holder.txtBeforePnd.setTextColor(Color.parseColor("#CC3300"));
            }



        } else if (purchasedLeg && mCabinDetailList.get(position).getPndDoneOnLeg()&&mCabinDetailList.get(position).getIsAvailable() == 0) {
            holder.rLytSelectEso.setVisibility(View.GONE);
            holder.rLytBeforePnd.setVisibility(View.VISIBLE);
            holder.imgDecisionPending.setImageUrl("http://www.optiontown.com/images/uto_selected.gif", imageLoader);
            holder.imgDecisionPending.setDefaultImageResId(R.drawable.uto_selected);
           // btnContinue.setVisibility(View.GONE);
            if (mCabinDetailList.get(position).getStatusLabel().contains("Not Available")){
                holder.imgDecisionPending.setVisibility(View.GONE);
                holder.txtBeforePnd.setTextColor(Color.parseColor("#333333"));
            }
           else if (mCabinDetailList.get(position).getStatusLabel().contains("Not Assigned")){
                holder.imgDecisionPending.setVisibility(View.GONE);
                holder.rlytImgAvailable.setVisibility(View.GONE);

                new Handler()
                {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);

                        ViewGroup.LayoutParams layoutParams = holder.rLytBeforePnd.getLayoutParams();
                        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        //layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                        holder.rLytBeforePnd.setLayoutParams(layoutParams);
                    }
                }.sendEmptyMessage(0);

               // holder.rLytBeforePnd.setWeight(2);

                holder.txtBeforePnd.setTextColor(Color.parseColor("#CC3300"));
            }
            else {
                holder.txtBeforePnd.setTextColor(Color.parseColor("#CC3300"));
                holder.imgDecisionPending.setDefaultImageResId(R.drawable.congrats_icon);
                holder.imgDecisionPending.setImageUrl("https://www.optiontown.com/images/uto_upgrade.gif", imageLoader);
            }
            holder.txtBeforePnd.setText(mCabinDetailList.get(position).getStatusLabel().replaceAll("!"," \n"));

        } else if (mCabinDetailList.get(position).getIsAvailable() == 1) {
            holder.txt_selectEsoLabel.setText(mUtosearchresultHome.getIfsObject().get(0).getLegListObj().get(0).getAvailableSelect());

            if (mUtosearchresultHome.getIsPassFlow()) {

                try {
                    if (mCabinDetailList.get(position).getCreditRequiredLabel()> 0) {
                        holder.txt_esoPrice.setText(mCabinDetailList.get(position).getCabinName());
                        holder.rLytEsoNotAvailable.setVisibility(View.GONE);
                        holder.rLytSelectEso.setVisibility(View.VISIBLE);
                    } else {
                        holder.rLytEsoNotAvailable.setVisibility(View.VISIBLE);
                        holder.rLytSelectEso.setVisibility(View.GONE);
                       // holder.txtClassPND.setText(mCabinDetailList.get(position).getCabinName());
                        holder.txt_esoNotAVailable.setText(mCabinDetailList.get(position).getUtoAvailablePassNotApp());
                        holder.txt_esoNotAVailable.setTextColor(Color.parseColor("#CD3301"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {
                holder.rLytSelectEso.setVisibility(View.VISIBLE);
                holder.txt_esoPrice.setText(tOriginalShiftPrice1);
                holder.txt_selectEsoLabel.setText(mUtosearchresultHome.getIfsObject().get(0).getLegListObj().get(0).getAvailableSelect());
            }
        }
        else if (mCabinDetailList.get(position).getIsAvailable() == 0&&!purchasedLeg){
                holder.rLytEsoNotAvailable.setVisibility(View.VISIBLE);
                 holder.rLytSelectEso.setVisibility(View.GONE);
          //  holder.txt_esoNotAVailable.setText(mCabinDetailList.get(position).getBeforePndLabel());
            holder.txt_esoNotAVailable.setText(mCabinDetailList.get(position).getStatusLabel());

                if (chkPnd && mCabinDetailList.get(position).getPndDoneOnLeg() == false) {

                } else {
                    String isUpgraded = mCabinDetailList.get(position).getIsUpgraded() + "";
                    if (isUpgraded.equals("1")) {
                        holder.txt_esoNotAVailable.setTextColor(Color.parseColor("#CC3300"));
                        holder.txt_esoNotAVailable.setTextSize(13);
                        holder.txt_esoNotAVailable.setText(mCabinDetailList.get(position).getStatusLabel().replace("!", "\n"));
                    } else {
                        holder.txt_esoNotAVailable.setText(mCabinDetailList.get(position).getStatusLabel());
                    }
                }
            }
        //Seat Map Show for PSO product
        try {
            holder.rlytPickASeat.setVisibility(mCabinDetailList.get(position).getSeatMapShow() && ctx.getResources().getBoolean(R.bool.bool_enable_seatmap) ? View.VISIBLE : View.GONE);
            if (mCabinDetailList.get(position).getSeatMapShow() && ctx.getResources().getBoolean(R.bool.bool_enable_seatmap)){
                holder.rLytSelectEso.setVisibility(View.GONE);
                holder.imgEmtpySeat.setVisibility(View.GONE);
                holder.txtPickASeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String styleIdUpcabinStandBy = mCabinDetailList.get(position).getStyleIdUpcabinStandBy();
                        Utils.setIfsIndexForPickASeat(ctx,getFirstIndex((styleIdUpcabinStandBy)));
                        Utils.moveToFragment((Activity)ctx,new FlightSeatViewFragment(),null,0);
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private String getFirstIndex(String string) {
        return string.substring(0,(string.length())-4);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCabinDetailList.size();
    }

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int cabinPos, Boolean isChecked);
    }

    public static interface IRecyclerViewHolderCheckChangeListerner {
        public void onCheckedChangeItem(View v, int cabinPos, Boolean isChecked);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView imgEmtpySeat,imgDecisionPending;
        private OTTextView txtEsoavailableSeat, txt_esoPrice, txt_selectEsoLabel, txt_esoNotAVailable, txtBeforePnd, txtPickASeat;
        private CheckBox chk_selectEso;
        private RelativeLayout rLytSelectEso, rLytEsoNotAvailable, rLytBeforePnd, rlytPickASeat,rlytImgAvailable;


        public ViewHolder(View v) {
            super(v);
            chk_selectEso = (CheckBox) v.findViewById(R.id.chk_selectEso);
            rLytSelectEso = (RelativeLayout) v.findViewById(R.id.rLytSelectEso);
            rLytEsoNotAvailable = (RelativeLayout) v.findViewById(R.id.rLytEsoNotAvailable);
            rLytBeforePnd = (RelativeLayout) v.findViewById(R.id.rLytBeforePnd);
            rlytPickASeat = (RelativeLayout) v.findViewById(R.id.rlytPickASeat);
            rlytImgAvailable = (RelativeLayout) v.findViewById(R.id.rlytImgAvailable);

            imgEmtpySeat = (NetworkImageView) v.findViewById(R.id.imgEmtpySeat);
            imgDecisionPending = (NetworkImageView) v.findViewById(R.id.imgDecisionPending);
            txt_esoPrice = (OTTextView) v.findViewById(R.id.txt_esoPrice);
            txtEsoavailableSeat = (OTTextView) v.findViewById(R.id.txtEsoavailableSeat);
            txt_selectEsoLabel = (OTTextView) v.findViewById(R.id.txt_selectEsoLabel);
            txt_esoNotAVailable = (OTTextView) v.findViewById(R.id.txt_esoNotAVailable);
            txtBeforePnd = (OTTextView) v.findViewById(R.id.txtBeforePnd);
            txtPickASeat = (OTTextView) v.findViewById(R.id.txtPickASeat);

            imgEmtpySeat.setDefaultImageResId(R.drawable.empty_seat2by4);
            imgDecisionPending.setDefaultImageResId(R.drawable.uto_selected);
        }
    }
}
package com.optiontown.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.utosearchresult.CabinDetailList;
import com.optiontown.app.model.utosearchresult.LegListObj;
import com.optiontown.app.model.utosearchresult.PaxDetail;
import com.optiontown.app.model.utosearchresult.UtosearchresultHome;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BidForPriorityFragment;
import com.optiontown.app.view.fragment.legproducts.BoostMyPriorityFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author Ravi Kumar
 */

public class UtoSearchResultLegDataRecyclerAdapter extends RecyclerView.Adapter<UtoSearchResultLegDataRecyclerAdapter.ViewHolder> {


    List<LegListObj> mLegListObj;
    private ImageLoader imageLoader;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private Context ctx;
    private UtosearchresultHome mUtosearchresultHome;
    private String journeyType ="";
    String saveAmount = "";
    private IRecyclerViewHolderClicks listener;
    private IRecyclerViewHolderCheckChangeListerner checkListener;
    private IRecyclerViewHolderPaxSelectedListener selectedPaxListener;
    private int checkedPosition;
    private Boolean checkedStatus = false;
    private List<PaxDetail> paxList;
    private boolean isPartialShown = false;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int legPos, int cabinPos, Boolean checked, Boolean isPartialShown);
    }

    public static interface IRecyclerViewHolderCheckChangeListerner{
        public void onCheckedChangeItem(View v, int legPos, int cabinPos, Boolean checked, Boolean isPartialShown);
    }

    public static interface IRecyclerViewHolderPaxSelectedListener {
        public void onPaxSelectedClick(View v, int legPos, ArrayList<String> listSelectedPax);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout lytPassengerSelect;
        private NetworkImageView imgOption;
        private OTTextView txtOptionName;//,txtSignUpPrice;
        private Button btnBoostPriority, btnBidPriority;
        private RecyclerView mRecyclerView;
        private NetworkImageView imgAirlines;
        private LinearLayout lytPartialUpgrade;



        private OTTextView txtFlightName, txtDate, txtStandByLabel, txtConfirmLabel, txtNotice, txtFrom, txtTo,txtUtoNotAvailable;
        LinearLayout lytOptions111,UpgradeNotAvailLyt;

        public ViewHolder(View v) {
            super(v);
            mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerBookingOptionsqqqq);
            btnBoostPriority = (Button) v.findViewById(R.id.btnBoostPriority);
            btnBidPriority = (Button) v.findViewById(R.id.btnBidPriority);



            lytPartialUpgrade = (LinearLayout) v.findViewById(R.id.lytPartialUpgrade);

            txtFlightName = (OTTextView) v.findViewById(R.id.txtFlightName);
            txtFrom = (OTTextView) v.findViewById(R.id.txtFrom);
            txtTo = (OTTextView) v.findViewById(R.id.txtTo);
            txtDate = (OTTextView) v.findViewById(R.id.txtDate);
            txtStandByLabel = (OTTextView) v.findViewById(R.id.txtStandByLabel);
            txtConfirmLabel = (OTTextView) v.findViewById(R.id.txtConfirmLabel);

            lytPassengerSelect = (LinearLayout) lytPartialUpgrade.findViewById(R.id.lytPassengerSelect);

            txtNotice = (OTTextView) v.findViewById(R.id.txtNotice);
            txtUtoNotAvailable = (OTTextView) v.findViewById(R.id.txtUtoNotAvailable);
            imgAirlines = (NetworkImageView) v.findViewById(R.id.imgAirlines);
            lytOptions111 = (LinearLayout) v.findViewById(R.id.lytOptions111);
            UpgradeNotAvailLyt = (LinearLayout) v.findViewById(R.id.UpgradeNotAvailLyt);
            mRecyclerView.setHasFixedSize(true);


        }
    }

    public UtoSearchResultLegDataRecyclerAdapter(Context ctx, String journeyType, List<LegListObj> mLegListObj, UtosearchresultHome mUtosearchresultHome,
                                                 IRecyclerViewHolderClicks listener, IRecyclerViewHolderCheckChangeListerner checkListener,
                                                 IRecyclerViewHolderPaxSelectedListener selectedPaxListener) {
        this.mLegListObj = mLegListObj;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx = ctx;
        this.mUtosearchresultHome= mUtosearchresultHome;
        this.journeyType=journeyType;
        this.listener=listener;
        this.checkListener=checkListener;
        this.selectedPaxListener = selectedPaxListener;

    }

    @Override
    public UtoSearchResultLegDataRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_upgrad_search_legproduct_legrecursion, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        List<CabinDetailList> mCabinDetailLists = mLegListObj.get(position).getCabinDetailList();
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String stringTo = mLegListObj.get(position).getDepartAirportName();
        SpannableString spanStr= new SpannableString(stringTo);
        spanStr.setSpan(new ForegroundColorSpan(Color.parseColor("#2C39AC")),0,stringTo.length(),0);
        holder.txtTo.setText((stringTo + "\n(" + mLegListObj.get(position).getDepartAirportCode() + ")"));

        String selectUpgrade = mLegListObj.get(position).getAvailableSelect();
        /*if (mLegListObj.get(0).getOptionPrice()>0){
            holder.txtSignUpPrice.setVisibility(View.VISIBLE);
            holder.txtSignUpPrice.setText(mUtosearchresultHome.getIfsObject().get(0).getInitialPriceLabel()+" "+mUtosearchresultHome.getIfsObject().get(0).getDisplayCurrencySymbol()+" "+mLegListObj.get(0).getOptionPrice());
        }*/
        if (mLegListObj.get(position).getSaveAmount()==null || mLegListObj.get(position).getSaveAmount().equalsIgnoreCase("")){
             saveAmount = "";
        }
        else {
            saveAmount =mLegListObj.get(position).getSaveAmount();
        }

        holder.txtFrom.setText(mLegListObj.get(position).getArriveAirportName() + "\n(" + mLegListObj.get(position).getArriveAirportCode() + ")");
      //  holder.txtDate.setText(mLegListObj.get(position).getDepartTime() + " " + mLegListObj.get(position).getArriveTimeFormatted());
        holder.txtDate.setText(mLegListObj.get(position).getDepartTime() + " " + mLegListObj.get(position).getDepartTimeFormatted() +" - "+mLegListObj.get(position).getArriveTimeFormatted());
        holder.txtFlightName.setText(mLegListObj.get(position).getOperatingAirlineCode() + " " + mLegListObj.get(position).getOperatingAirlineCarrierFlightNumber());
        holder.imgAirlines.setImageUrl("https://www.optiontown.com/images/"+mLegListObj.get(position).getOperatingAirlineLogo(), imageLoader);
        holder.txtStandByLabel.setText(mLegListObj.get(position).getStandByOption());
        holder.txtConfirmLabel.setText(mLegListObj.get(position).getConfirmUpgradeSelectLabel());
        //holder.txtFlightType.setText(journeyType);
        mLinearLayoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
        holder.mRecyclerView.setLayoutManager(mLinearLayoutManager);


        //Partial Sign Up Flow..
        RelativeLayout lytBuyForIndividualPassenger = (RelativeLayout) holder.lytPartialUpgrade.findViewById(R.id.lytBuyForIndividualPassenger);

        try {
            holder.lytPartialUpgrade.setVisibility(mLegListObj.get(position).getIsPartialOn()?View.VISIBLE:View.GONE);
        } catch (Exception e) {

        }
        //Pax List for selection for Partial sign up
        paxList = mLegListObj.get(position).getPaxDetail();
        final ArrayList<String> listSelectedPax = new ArrayList<>();

        final ImageView imgUserIcon = (ImageView) holder.lytPartialUpgrade.findViewById(R.id.imgUserIcon);
        final ImageView imgPlus = (ImageView) holder.lytPartialUpgrade.findViewById(R.id.imgPlus);
        final OTTextView txtBuyIndividualPassngerLabel = (OTTextView) holder.lytPartialUpgrade.findViewById(R.id.txtBuyIndividualPassngerLabel);
        txtBuyIndividualPassngerLabel.setText(mLegListObj.get(position).getPartialSignupTitle());

        lytBuyForIndividualPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.lytPassengerSelect.setVisibility(holder.lytPassengerSelect.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
                imgUserIcon.setImageResource(holder.lytPassengerSelect.getVisibility()==View.VISIBLE?R.drawable.pax_minus_icon:R.drawable.pax_plush_icon);
                imgPlus.setImageResource(holder.lytPassengerSelect.getVisibility()==View.VISIBLE?R.drawable.minus_icon:R.drawable.plus_icon);
            }
        });

        addPaxList(holder,listSelectedPax,checkedStatus,position);

        if (mCabinDetailLists.size()==0 && (!mLegListObj.get(position).getIsValidLeg())){
            holder.UpgradeNotAvailLyt.setVisibility(View.VISIBLE);
            holder.lytOptions111.setVisibility(View.GONE);

            holder.txtUtoNotAvailable.setText(ctx.getString(R.string.string_upgrade_not_available));

        }
        else{
            holder.UpgradeNotAvailLyt.setVisibility(View.GONE);
            holder.lytOptions111.setVisibility(View.VISIBLE);
            adapter = new UtoSearchClassRecyclerAdapter(ctx,selectUpgrade,saveAmount, mCabinDetailLists, mLegListObj.get(position).getIsPndDoneOnLeg(),
                    mLegListObj.get(position).getIsPurchasedLeg(), mUtosearchresultHome, mLegListObj.get(position).getIsValidLeg(), new UtoSearchClassRecyclerAdapter.IRecyclerViewHolderClicks() {
                @Override
                public void onClickRecyclerItem(View v, int cabinPos, Boolean isChecked) {
                    isPartialShown = holder.lytPassengerSelect.getVisibility()==View.VISIBLE? true : false;
                    listener.onClickRecyclerItem(v, position, cabinPos, isChecked, isPartialShown);

                    checkAllPaxStatus(holder.lytPassengerSelect, isChecked);
                }
            }, new UtoSearchClassRecyclerAdapter.IRecyclerViewHolderCheckChangeListerner() {
                @Override
                public void onCheckedChangeItem(View v, int cabinPos, Boolean isChecked) {
                    isPartialShown = holder.lytPassengerSelect.getVisibility()==View.VISIBLE? true : false;
                    checkListener.onCheckedChangeItem(v, position, cabinPos, isChecked,isPartialShown);

                    checkAllPaxStatus(holder.lytPassengerSelect, isChecked);

                }
            });

        }
        if (mLegListObj.get(position).getBoostMypriority() == null || mLegListObj.get(position).getUtoNotAvailavble().length()>0) {
            holder.btnBoostPriority.setVisibility(View.GONE);
        }
        else {
            holder.btnBoostPriority.setVisibility(View.VISIBLE);
        }
        if (mLegListObj.get(position).getBiddingMypriority() == null) {
            holder.btnBidPriority.setVisibility(View.GONE);
        } else {
            holder.btnBidPriority.setVisibility(View.VISIBLE);
        }
        if (mLegListObj.get(position).getCabinDetailList().size()!=0){
            if (mLegListObj.get(position).getCabinDetailList().get(0).getIsConfirmUpgradeDisplay()) {
                holder.lytOptions111.setVisibility(View.VISIBLE);
            } else {
                holder.lytOptions111.setVisibility(View.GONE);
            }
        }

        holder.mRecyclerView.setAdapter(adapter);
        holder.btnBoostPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* BoostMypriority boostMypriorities = mLegListObj.get(position).getBoostMypriority();*/
               LegListObj legListObj =  mLegListObj.get(position);
                Utils.moveToFragment((Activity) ctx, new BoostMyPriorityFragment(), legListObj, 0);
            }
        });

        holder.btnBidPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) ctx, new BidForPriorityFragment(), null, 0);
            }
        });

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




    @Override
    public int getItemCount() {
        return mLegListObj.size();
    }
}
package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.optiontown.R;
import com.optiontown.app.model.utosearchresult.BoostListWithDisplayValue;
import com.optiontown.app.model.utosearchresult.BoostNameList;
import com.optiontown.app.model.utosearchresult.PriceUpcabinDetail;
import com.optiontown.app.model.utosearchresult.UpcabinNameDetail;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.List;

/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author Ravi Kumar
 */
public class BoostPriorityRecyclerViewAdapter extends RecyclerView.Adapter<BoostPriorityRecyclerViewAdapter.ViewHolder> {
    List<PriceUpcabinDetail> priceUpcabinDetailList;
    String priceCC, indexStr, labelDisplay;
    int pricesGetP;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private Context ctx;
    private List<UpcabinNameDetail> upcabinNameDetails;
    private List<BoostNameList> boostNameList;
    private IBoostMyPriority l;
    private RecyclerView recyclerView;

    public interface IBoostMyPriority

    {
        public void onClickBMP(String price, String index);
    }


    public BoostPriorityRecyclerViewAdapter(IBoostMyPriority listener, Context ctx, List<PriceUpcabinDetail> priceUpcabinList,
                                            List<BoostNameList> boostNameList, List<UpcabinNameDetail> upcabinNameDetails) {

        this.priceUpcabinDetailList = priceUpcabinList;
        this.boostNameList = boostNameList;
        this.ctx = ctx;
        this.upcabinNameDetails = upcabinNameDetails;
        this.l = listener;
    }

    @Override
    public BoostPriorityRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.boost_my_priority_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.txtBoostPriority.setText(boostNameList.get(position).getBoostNameLabel());




        for (int i = 0; i < upcabinNameDetails.size(); i++) {
            List<BoostListWithDisplayValue> displayList = priceUpcabinDetailList.get(i).getBoostListWithDisplayValue();

            final RadioButton rb = new RadioButton(ctx);
            rb.setText(displayList.get(position).getPricesCurrencyCode()+""+displayList.get(position).getPricesgetPrice());
            rb.setTextSize(Utils.convertPixelToDp(ctx, ctx.getResources().getDimension(R.dimen.size_font_13)));
            rb.setTag(displayList.get(position).getIndexString());
            rb.setPadding(5,0,0,0);
            Log.v("Radio Checked : ",rb.getTag().toString());
//            rb.setId(Integer.parseInt(displayList.get(i).getIndexString()));//Number format Exception



            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("Radio Checked : ",v.getTag().toString());
                    l.onClickBMP(v.getTag().toString()," ");

                    unCheckRest(recyclerView, (RadioButton)v);


                }
            });

            holder.radioGroupBMP.addView(rb);
        }

    }

    private void unCheckRest(ViewGroup parentView, RadioButton radioButton) {
        int numChildViews = parentView.getChildCount();

        for (int i = 0; i < numChildViews; i++) {
            View childView = parentView.getChildAt(i);
            if (childView instanceof ViewGroup) {
                unCheckRest((ViewGroup) childView, radioButton);
            } else if (childView instanceof RadioButton) {

                if(((String)childView.getTag()).equals((String)radioButton.getTag()))
                {
                    //Log.d("", "childView : " + ((RadioButton) childView).getTag().toString());
                    ((RadioButton) childView).setChecked(true);
                    ((RadioButton) childView).setSelected(true);
                }
                else {
                    Log.d("", "childView : " + ((RadioButton) childView).getTag().toString());
                    ((RadioButton) childView).setChecked(false);
                    ((RadioButton) childView).setSelected(false);
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return boostNameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        OTTextView txtBoostPriority;
        //RadioButton rbPremiumBed;
        LinearLayout radioGroupBMP;

        public ViewHolder(View v) {
            super(v);
            txtBoostPriority = (OTTextView) v.findViewById(R.id.txtBoostPriority);
          //  rbPremiumBed = (RadioButton) v.findViewById(R.id.rbPremiumBed);
            radioGroupBMP = (LinearLayout) v.findViewById(R.id.radioGroupBMP);


        }
    }
}
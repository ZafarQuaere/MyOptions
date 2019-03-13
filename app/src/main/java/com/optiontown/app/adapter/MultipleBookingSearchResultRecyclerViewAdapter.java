package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.utils.AppController;

import java.util.ArrayList;

/**
 * Adapter to attach item with data for recycler view at home fragment
 * @author Ravi Kumar
 */
public class MultipleBookingSearchResultRecyclerViewAdapter extends RecyclerView.Adapter<MultipleBookingSearchResultRecyclerViewAdapter.ViewHolder> {


    ArrayList<Benefit> benefits;
    private ImageLoader imageLoader;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private Context ctx;




    public static class ViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView imgOption;
        private TextView txtOptionName;
        RecyclerView mRecyclerView;
        public ViewHolder(View v)
        {
            super(v);
            mRecyclerView=(RecyclerView) v.findViewById(R.id.recyViewMultipleBookRow);
            mRecyclerView.setHasFixedSize(true);


        }
    }

    public MultipleBookingSearchResultRecyclerViewAdapter(Context ctx, ArrayList<Benefit> benefits)
    {
        this.benefits= benefits;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx= ctx;
    }

    @Override
    public MultipleBookingSearchResultRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_booking_search_result_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        ArrayList<Benefit> mBenefits= new ArrayList<>();
        for(int i=0; i< 2; i++){
            Benefit mBenefit= new Benefit();
            mBenefit.setBenefitName("s"+" "+ i);
            mBenefit.setImageURL("https://www.optiontown.com/images/alt/UTo_Benefits_Small_Image_1.jpg");
            mBenefit.setId(i);
            mBenefits.add(mBenefit);
        }
        mLinearLayoutManager = new LinearLayoutManager(ctx,  LinearLayoutManager.VERTICAL,false);
        holder.mRecyclerView.setLayoutManager(mLinearLayoutManager);
        adapter= new MultipleBookingSearchClassRecyclerViewAdapter(ctx, mBenefits);
        holder.mRecyclerView.setAdapter(adapter);



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return benefits.size();
    }
}
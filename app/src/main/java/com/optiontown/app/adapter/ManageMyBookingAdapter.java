package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.optiontown.R;

import com.optiontown.app.model.redeem.mmp.MmpLabel;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 22/11/2016.
 */
public class ManageMyBookingAdapter extends RecyclerView.Adapter<ManageMyBookingAdapter.ViewHolder>{

    private final Context context;
    private final ArrayList<String> list;
    private final RecyclerViewHolderClicks listener;
    private View view1;
    private ViewHolder viewHolder1;

    public ManageMyBookingAdapter(Context context, ArrayList<String> showLabelList, RecyclerViewHolderClicks l) {

        this.context = context;
        this.list = showLabelList;
        this.listener = l;

    }

    @Override
    public ManageMyBookingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(context).inflate(R.layout.mmp_home_row,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ManageMyBookingAdapter.ViewHolder holder,final int position) {

        holder.textView.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItemDetail(v,list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface RecyclerViewHolderClicks {

        public void onClickRecyclerItemDetail(View v, String manageMybookLabel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final OTTextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (OTTextView)itemView.findViewById(R.id.txtMMP);

        }
    }
}

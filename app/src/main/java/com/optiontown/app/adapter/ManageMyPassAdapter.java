package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.optiontown.R;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.mmp.MmpLabel;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.List;

/**
 * Created by zafar.imam on 27-10-2016.
 */
public class ManageMyPassAdapter extends RecyclerView.Adapter<ManageMyPassAdapter.ViewHolder>{
    List<MmpLabel> list;
    Context context;
    View view1;
    ViewHolder viewHolder1;

    private final ManageMyPassAdapter.RecyclerViewHolderClicks listener;

    public interface RecyclerViewHolderClicks {
        public void onClickRecyclerItemDetail(View v, MmpLabel manageMypassLabelList);
    }

    public ManageMyPassAdapter(Context context, List<MmpLabel> list,RecyclerViewHolderClicks listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ManageMyPassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(context).inflate(R.layout.mmp_home_row,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ManageMyPassAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getManageMypassLabelList());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItemDetail(v,list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public OTTextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

                textView = (OTTextView)itemView.findViewById(R.id.txtMMP);


        }
    }
}

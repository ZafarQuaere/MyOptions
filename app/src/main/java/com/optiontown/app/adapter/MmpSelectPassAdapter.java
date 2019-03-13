package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.optiontown.R;
import com.optiontown.app.model.redeem.mmb.MapOfPassAndConfirmedBooking;
import com.optiontown.app.model.redeem.mmp.FlightsList;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parasmani.sharma on 03/11/2016.
 */
public class MmpSelectPassAdapter extends RecyclerView.Adapter<MmpSelectPassAdapter.ViewHolder> {

    List<FlightsList> mmpList;
    ArrayList<MapOfPassAndConfirmedBooking> mmbList;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    private final MmpSelectPassAdapter.RecyclerViewHolderClicks listener;

    public MmpSelectPassAdapter(Context context, List<FlightsList> mmpList, RecyclerViewHolderClicks listener) {
        this.context = context;
        this.mmpList = mmpList;
        this.listener = listener;
    }



    @Override
    public MmpSelectPassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.mmp_pass_select_row, parent, false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(MmpSelectPassAdapter.ViewHolder holder, final int position) {

        String passData = removeHashChar(mmpList.get(position).getLabel());
        holder.txtMMP.setText(passData);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                listener.onClickRecyclerItemDetail(v, position == 0 ? null : mmpList.get(position));
            }
        });
    }

    private String removeHashChar(String data) {

        String newData = data.replace("#", ":");

        return newData;
    }

    @Override
    public int getItemCount() {
        return mmpList.size();
    }

    public interface RecyclerViewHolderClicks {
        public void onClickRecyclerItemDetail(View v, FlightsList flightsList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public OTTextView txtMMP;

        public ViewHolder(View itemView) {
            super(itemView);
            txtMMP = (OTTextView) itemView.findViewById(R.id.txtMMP);
        }
    }
}

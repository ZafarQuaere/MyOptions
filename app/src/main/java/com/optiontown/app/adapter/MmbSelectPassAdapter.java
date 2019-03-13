package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.optiontown.R;
import com.optiontown.app.model.redeem.mmb.MapOfPassAndConfirmedBooking;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 23/11/2016.
 */
public class MmbSelectPassAdapter extends RecyclerView.Adapter<MmbSelectPassAdapter.ViewHolder>{
    private final Context context;
    private final ArrayList<MapOfPassAndConfirmedBooking> list;
    private final RecyclerViewHolderClicks listener;
    private View view1;
    private ViewHolder viewHolder1;

    public MmbSelectPassAdapter(Context context, ArrayList<MapOfPassAndConfirmedBooking> list, RecyclerViewHolderClicks l) {

        this.context = context;
        this.list = list;
        this.listener = l;

    }

    @Override
    public MmbSelectPassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(context).inflate(R.layout.mmp_pass_select_row, parent, false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(MmbSelectPassAdapter.ViewHolder holder, final int position) {

        String passData = removeHashChar(list.get(position).getPassLabel().toString());
        holder.txtMMP.setText(passData);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                listener.onClickRecyclerItemDetail(v, list.get(position));
               // listener.onClickRecyclerItemDetail(v, position == 0 ? null : list.get(position));


            }
        });

    }

    private String removeHashChar(String data) {

        String newData = data.replace("#", ":");

        return newData;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerViewHolderClicks {

        public void onClickRecyclerItemDetail(View v, MapOfPassAndConfirmedBooking selected);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public OTTextView txtMMP;

        public ViewHolder(View itemView) {
            super(itemView);
            txtMMP = (OTTextView) itemView.findViewById(R.id.txtMMP);
        }
    }
}

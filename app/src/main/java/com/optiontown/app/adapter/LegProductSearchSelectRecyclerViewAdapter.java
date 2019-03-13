package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.optiontown.R;
import com.optiontown.app.model.legproducthomepage.AirlineDropDownList;
import com.optiontown.app.model.legproducthomepage.Home;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.view.customview.OTTextView;

import java.util.List;

/**
 * adapter for search select recycler view
 */
public class LegProductSearchSelectRecyclerViewAdapter extends RecyclerView.Adapter<LegProductSearchSelectRecyclerViewAdapter.ViewHolder> {

    private Home data;

    private IRecyclerViewHolderClicks listener;
    private Context context;
    private AppSharedPrefs instanceSharedPrefs;

    private final  List<AirlineDropDownList> arrayListData;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItems(View v, int position, Object item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgTick;
        private OTTextView txtAirline;
        private RelativeLayout lytSearchSelectRecyclerRow;

        public ViewHolder(View v) {
            super(v);
            this.imgTick = (ImageView) v.findViewById(R.id.imgTick);
            this.txtAirline = (OTTextView) v.findViewById(R.id.txtSelectedItem);
            this.lytSearchSelectRecyclerRow = (RelativeLayout) v.findViewById(R.id.lytSearchSelectRecyclerRow);
        }
    }

    public LegProductSearchSelectRecyclerViewAdapter(Context context, List<AirlineDropDownList> arrayList, IRecyclerViewHolderClicks l) {
        this.instanceSharedPrefs = AppSharedPrefs.getInstance(context);
        this.context = context;
        this.arrayListData = arrayList;
        this.listener = l;


    }


    @Override
    public LegProductSearchSelectRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_select_recycler_row, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.txtAirline.setText(arrayListData.get(position).getAirlineName());
        if (arrayListData.get(position).isSelectedAirline()) {
            holder.imgTick.setVisibility(View.VISIBLE);
        } else {
            holder.imgTick.setVisibility(View.GONE);
        }


        holder.lytSearchSelectRecyclerRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItems(v, position, arrayListData.get(position));
                for (int i = 0; i < arrayListData.size(); i++) {
                        if (i == position) {
                            arrayListData.get(i).setSelectedAirline(true);
                        } else {
                            arrayListData.get(i).setSelectedAirline(false);
                        }
                }
                ccccc();

            }
        });


    }
    public void ccccc()
    {
        Log.d("d", arrayListData.size()+"");
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return arrayListData.size();
    }
}

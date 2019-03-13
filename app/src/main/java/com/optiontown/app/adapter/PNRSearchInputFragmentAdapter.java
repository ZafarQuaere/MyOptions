package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.optiontown.R;
import com.optiontown.app.model.utosearchresult.PassDropDownList;
import com.optiontown.app.model.utosearchresult.PnrList;
import com.optiontown.app.view.customview.OTTextView;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 04/05/2017.
 */

public class PNRSearchInputFragmentAdapter extends RecyclerView.Adapter<PNRSearchInputFragmentAdapter.ViewHolder>{

    private final Context context;
    private static String updateRecyclerViewDataFor = "listofpasses";
    private ArrayList<PassDropDownList> passList = new ArrayList<>();
    private ArrayList<PnrList> listBooking = new ArrayList<>();
    private RecyclerViewHolderClicks listener;
    private RecyclerViewHolderClicksBooking listenerBooking;
    private View view1;
    private ViewHolder viewHolder1;

    public PNRSearchInputFragmentAdapter(Context context, String updateRecyclerViewDataFor, ArrayList<PassDropDownList> passList, RecyclerViewHolderClicks l) {

        this.context = context;
        this.passList = passList;
        this.listener = l;
        this.updateRecyclerViewDataFor = updateRecyclerViewDataFor;

    }

    public PNRSearchInputFragmentAdapter(Context context, String updateRecyclerViewDataFor, ArrayList<PnrList> list, RecyclerViewHolderClicksBooking listenBooking) {

        this.context = context;
        this.listBooking = list;
        this.listenerBooking = listenBooking;
        this.updateRecyclerViewDataFor = updateRecyclerViewDataFor;

    }

    public interface RecyclerViewHolderClicks {

        public void onClickRecyclerItemDetail(View v, PassDropDownList passId);

    }

    public interface RecyclerViewHolderClicksBooking{

        public void onClickRecyclerItemBookingDetail(View v, String selected);
    }

    @Override
    public PNRSearchInputFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(context).inflate(R.layout.upgrade_pass_select_row_adapter, parent, false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(PNRSearchInputFragmentAdapter.ViewHolder holder,final int position) {

        if(updateRecyclerViewDataFor.equals("listofpasses"))
        {
            //String passData = removeHashChar(list.get(position).getPassSmallView().getTravelZoneTitle()+ " "+ "OCN # " + list.get(position).getPassFullView().getConfirmationNumber());
            holder.txtMMP.setText(passList.get(position).getPasDisplayLevel());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    listener.onClickRecyclerItemDetail(v, passList.get(position));
                    // listener.onClickRecyclerItemDetail(v, position == 0 ? null : list.get(position));
                }
            });

        }else {
                String pnrData = listBooking.get(position).getPnrlistValue();
                holder.txtMMP.setText(pnrData);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {

                        listenerBooking.onClickRecyclerItemBookingDetail(v,listBooking.get(position).getPnrlistValue());
                        // listener.onClickRecyclerItemDetail(v, position == 0 ? null : list.get(position));
                    }
                });
        }
    }

    private String removeHashChar(String data) {

        String newData = data.replace("#", ":");

        return newData;
    }

    @Override
    public int getItemCount() {
        if(updateRecyclerViewDataFor.equals("listofpasses"))
        {
            return passList.size();
        }else {
            return listBooking.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public OTTextView txtMMP;

        public ViewHolder(View itemView) {
            super(itemView);
            txtMMP = (OTTextView) itemView.findViewById(R.id.txtMMP);
        }
    }
}

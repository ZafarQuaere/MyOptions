package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.optiontown.R;

import com.optiontown.app.model.session.FaqQuestion;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;

/**
 * Created by amit on 11-07-2016.
 */
public class FAQRecyclerViewAdapter extends RecyclerView.Adapter<FAQRecyclerViewAdapter.ViewHolder>
{
    private final ArrayList<FaqQuestion> data;
    private IRecyclerViewHolderClicks listener;
    private Context context;
    private AppSharedPrefs instanceSharedPrefs;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private OTTextView txtFAQTitle;
        private RelativeLayout lytFAQRecyclerRow;

        public ViewHolder(View v)
        {
            super(v);
            this.txtFAQTitle        = (OTTextView)  v.findViewById(R.id.txtFAQTitle);
            this.lytFAQRecyclerRow  = (RelativeLayout) v.findViewById(R.id.lytFAQRecyclerRow);
        }
    }

    public FAQRecyclerViewAdapter(Context context, ArrayList<FaqQuestion> data, IRecyclerViewHolderClicks l)
    {
        this.instanceSharedPrefs = AppSharedPrefs.getInstance(context);
        this.context = context;
        this.data = data;
        this.listener = l;
    }


    @Override
    public FAQRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_recycler_row, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.txtFAQTitle.setText(data.get(position).getFaqNAme());
        holder.lytFAQRecyclerRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItem(v, position);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Utils.DEBUG("FAQRecyclerViewAdapter >> size : " + data.size());
        return data.size();
    }

}

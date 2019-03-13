package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.optiontown.R;

/**
 * Adapter to attach item with data for recycler view at home fragment
 * @author amit
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    private String[] aryOptionImages;
    private String[] aryOptionNames;

    private IRecyclerViewHolderClicks listener;
    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgOption;
        private TextView txtOptionName;

        public ViewHolder(View v) {
            super(v);
            this.imgOption      = (ImageView)   v.findViewById(R.id.imgOption);
            this.txtOptionName  = (TextView)    v.findViewById(R.id.txtOptionName);
        }
    }

    public HomeRecyclerViewAdapter(String[] aryOptionImages, String[] aryOptionNames, IRecyclerViewHolderClicks l)
    {
        this.aryOptionImages = aryOptionImages;
        this.aryOptionNames = aryOptionNames;
        this.listener = l;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HomeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_grid_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Context context = holder.imgOption.getContext();
        int id = context.getResources().getIdentifier(aryOptionImages[position], "drawable", context.getPackageName());
        holder.imgOption.setBackgroundResource(id);
        holder.txtOptionName.setText(aryOptionNames[position]);
        holder.imgOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItem(v, position);
            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return aryOptionImages.length;
    }
}
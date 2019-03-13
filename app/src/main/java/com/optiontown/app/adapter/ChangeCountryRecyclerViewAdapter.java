package com.optiontown.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;

import com.optiontown.app.model.session.CountryList;
import com.optiontown.app.model.session.CountryListAPI;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

/**
 * Created by amit on 17-07-2016.
 */
public class ChangeCountryRecyclerViewAdapter extends RecyclerView.Adapter<ChangeCountryRecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private CountryListAPI list;
    private ImageLoader imageLoader;


    private IRecyclerViewHolderClicks listener;
    private String perFlightLabel;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(CountryList country);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private OTTextView txtCountryName;
        private LinearLayout lytChangeCountryRow;
        private ImageView imgTick;
        private NetworkImageView imgCountry;


        public ViewHolder(View v)
        {
            super(v);
            this.txtCountryName  = (OTTextView)    v.findViewById(R.id.txtCountryName);
            this.lytChangeCountryRow = (LinearLayout) v.findViewById(R.id.lytChangeCountryRow);
            this.imgCountry = (NetworkImageView) v.findViewById(R.id.imgCountry);
            this.imgTick = (ImageView) v.findViewById(R.id.imgTick);

        }
}

    public ChangeCountryRecyclerViewAdapter(Context context, CountryListAPI list, IRecyclerViewHolderClicks l)
    {
        this.context = context;
        this.list = list;
        this.listener = l;
        this.imageLoader = AppController.getInstance().getImageLoader();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChangeCountryRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_change_country_row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.txtCountryName.setText(list.getCountryList().get(position).getCountryName() + " - " + list.getCountryList().get(position).getLanguage());

        if(Utils.getUserSelectedCountryId((Activity) context) == list.getCountryList().get(position).getCountryID()
                && Utils.getUserSelectedLanguageId((Activity) context) == list.getCountryList().get(position).getLanguageID())
        {
            holder.imgTick.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.imgTick.setVisibility(View.GONE);
        }

        holder.lytChangeCountryRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItem(list.getCountryList().get(position));

            }
        });

        holder.imgCountry.setImageUrl(list.getCountryList().get(position).getCountryLogo(), imageLoader);
    }





    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return  list.getCountryList().size();
    }


}
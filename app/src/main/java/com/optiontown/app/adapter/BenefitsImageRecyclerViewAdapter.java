package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.session.Benifit;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;

import java.util.ArrayList;

/**
 * Created by zafar.imam on 16-02-2017.
 */
public class BenefitsImageRecyclerViewAdapter extends RecyclerView.Adapter<BenefitsImageRecyclerViewAdapter.ViewHolder>{

    private final ArrayList<Benifit> mBenfitList;
    private Context mContext;
    private ImageLoader mImageLoader;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView imgOption;
        private TextView txtOptionName;
        public ViewHolder(View v)
        {
            super(v);
            this.imgOption      = (NetworkImageView)   v.findViewById(R.id.imgOption);
            this.txtOptionName  = (TextView)    v.findViewById(R.id.txtOptionName);
        }
    }
    
    public BenefitsImageRecyclerViewAdapter(Context context, ArrayList<Benifit> benfitList) {
        mBenfitList = benfitList;
        mContext = context;
        mImageLoader = AppController.getInstance().getImageLoader();
    }

    @Override
    public BenefitsImageRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_grid_row, parent, false);
        BenefitsImageRecyclerViewAdapter.ViewHolder vh = new BenefitsImageRecyclerViewAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.imgOption.getContext();
        holder.txtOptionName.setText(mBenfitList.get(position).getTitleSubHeading());
        Utils.DEBUG(""+ mBenfitList.get(position).getImage());

        holder.imgOption.setImageUrl(mBenfitList.get(position).getImage(), mImageLoader);
    }

    @Override
    public int getItemCount() {
        return mBenfitList.size();
    }
    // Create new views (invoked by the layout manager)

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int position);
    }

   
}

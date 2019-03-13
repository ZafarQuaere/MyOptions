package com.optiontown.app.intro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.utils.AppController;

/**
 * Created by zafar.imam on 11-08-2017.
 */

class OurPartnersAdapter extends RecyclerView.Adapter<OurPartnersAdapter.ViewHolder> {
  //  private final ImageLoader imageLoader;
    private Context mContext;
    private int imgArr[];

    public OurPartnersAdapter(Context context, int[] partnerArray) {
        mContext = context;
        imgArr = partnerArray;
     //   this.imageLoader = AppController.getInstance().getImageLoader();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.our_partner_row, parent, false);
        OurPartnersAdapter.ViewHolder vh = new OurPartnersAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.imgOption.getContext();
       holder.imgOption.setImageResource(imgArr[position]);
    }


    @Override
    public int getItemCount() {
        return imgArr.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgOption;

        public ViewHolder(View v) {
            super(v);
            this.imgOption = (ImageView) v.findViewById(R.id.imgOption);
        }
    }
}

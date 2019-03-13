package com.optiontown.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Adapter to attach item with data for recycler view at home fragment
 * @author ravi
 */
public class ProductBeneifitsRecyclerViewAdapter extends RecyclerView.Adapter<ProductBeneifitsRecyclerViewAdapter.ViewHolder> {

    ArrayList<Benefit> benefits;
    private ImageLoader imageLoader;
    private IRecyclerViewHolderClicks listener;
    private int textColor;


    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int position);
    }
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

    public ProductBeneifitsRecyclerViewAdapter(int textColor, Context ctx, ArrayList<Benefit> benefits, IRecyclerViewHolderClicks l)
    {
        this.textColor = textColor;
        this.benefits= getFilterTwoLegs(ctx, getSegBenefits(ctx, benefits));
        //this.benefits= getFilterTwoLegs(ctx, benefits);
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.listener=l;
    }

    /**
     * used to get update list after checking seg/2 leg products enable or not
     * @param context
     * @param benefits
     * @return
     */
    private ArrayList<Benefit> getSegBenefits(Context context, ArrayList<Benefit> benefits) {

        if(context.getResources().getBoolean(R.bool.bool_show_seg))
        {
            return benefits;
        }
        ListIterator<Benefit> iterator = benefits.listIterator();
        while (iterator.hasNext())
        {
            Benefit next = iterator.next();
            if(next.getId() == 2 || next.getId() == 3 || next.getId() == 4)
            {
                iterator.remove();
            }
        }
        return benefits;
    }

    private ArrayList<Benefit> getFilterTwoLegs(Context context, ArrayList<Benefit> benefits) {

        if(context.getResources().getBoolean(R.bool.bool_show_cbo_sbo))
        {
            return benefits;
        }
        ListIterator<Benefit> iterator = benefits.listIterator();
        while (iterator.hasNext())
        {
            Benefit next = iterator.next();
            if(next.getId() == 22 || next.getId() == 18)
            {
                iterator.remove();
            }
        }
        return benefits;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductBeneifitsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
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
        holder.txtOptionName.setText(benefits.get(position).getBenefitName());
        Utils.DEBUG(""+ benefits.get(position).getImageURL());

        holder.imgOption.setImageUrl(benefits.get(position).getImageURL(), imageLoader);
        holder.txtOptionName.setTextColor(textColor);


       /* if(mCabinDetailList.get(position).getImageURL().indexOf("https://")>0){
            holder.imgOption.setImageUrl(mCabinDetailList.get(position).getImageURL(), imageLoader);
        }else {
            holder.imgOption.setImageUrl("https://" + mCabinDetailList.get(position).getImageURL(), imageLoader);
        }*/

        holder.imgOption.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItem(v, position);
            }

        });

    }

    @Override
    public int getItemCount() {
        return benefits.size();
    }
}
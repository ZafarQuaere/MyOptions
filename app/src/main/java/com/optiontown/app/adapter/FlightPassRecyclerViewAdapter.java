package com.optiontown.app.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.fpogetpass.PassObject;
import com.optiontown.app.model.selectproduct.PassArray;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to attach item with data for recycler view at flight pass fragment
 * @author amit
 */
public class FlightPassRecyclerViewAdapter extends RecyclerView.Adapter<FlightPassRecyclerViewAdapter.ViewHolder>
{
    private List<PassArray> list;
    private IRecyclerViewHolderClicks listener;
    private ImageLoader imageLoader;
    private Context context;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int position, PassArray passArray);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private NetworkImageView imgDeal;
        private OTTextView txtCabinName;
        private OTTextView txtPassFullName;
        private OTTextView txtFlights;
        private OTTextView txtCreditCount;
        private OTTextView txtMonth;
        private OTTextView txtMonthCount;
        private OTTextView txtCurrency;
        private OTTextView txtAmount;
        private OTTextView txtPerFlight;
        private OTTextView txtPerOneWay;
        private OTTextView txtRegularPrice;
        private OTTextView txtStrikedRegularPrice;
        private OTTextView txtSaveLabel;
        private OTTextView txtUpto;
        private OTTextView txtSavePrice;
        private OTTextView txtSavePercent;
        private OTTextView txtOff;
        private RelativeLayout lay_main_banner;



        public ViewHolder(View v)
        {
            super(v);
            this.imgDeal            = (NetworkImageView)   v.findViewById(R.id.imgDeal);
            this.txtCabinName = (OTTextView)  v.findViewById(R.id.txtCabinName);
            this.txtPassFullName = (OTTextView)  v.findViewById(R.id.txtPassFullName);
            this.txtFlights             = (OTTextView)  v.findViewById(R.id.txtFlights);
            this.txtCreditCount = (OTTextView)  v.findViewById(R.id.txtCreditCount);
            this.txtMonth             = (OTTextView)  v.findViewById(R.id.txtMonth);
            this.txtMonthCount      = (OTTextView)  v.findViewById(R.id.txtMonthCount);
            this.txtCurrency        = (OTTextView)  v.findViewById(R.id.txtCurrency);
            this.txtAmount          = (OTTextView)  v.findViewById(R.id.txtAmount);
            this.txtPerFlight       = (OTTextView)  v.findViewById(R.id.txtPerFlight);
            this.txtPerOneWay       = (OTTextView)  v.findViewById(R.id.txtPerOneWay);
            this.txtRegularPrice    = (OTTextView)  v.findViewById(R.id.txtRegularPrice);
            this.txtStrikedRegularPrice    = (OTTextView)  v.findViewById(R.id.txtStrikedRegularPrice);
            this.txtSaveLabel       = (OTTextView)  v.findViewById(R.id.txtSaveLable);
            this.txtUpto       = (OTTextView)  v.findViewById(R.id.txtUpto);
            this.txtSavePrice       = (OTTextView)  v.findViewById(R.id.txtSavePrice);
            this.txtSavePercent     = (OTTextView)  v.findViewById(R.id.txtSavePercent);
            this.txtOff             = (OTTextView)  v.findViewById(R.id.txtOff);
            this.lay_main_banner    = (RelativeLayout) v.findViewById(R.id.lay_main_banner);


        }
    }

    public FlightPassRecyclerViewAdapter(Context context, ArrayList<PassArray> list, IRecyclerViewHolderClicks l)
    {
        this.context = context;
        this.list = list;
        this.listener = l;
        this.imageLoader = AppController.getInstance().getImageLoader();
    }

    @Override
    public FlightPassRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_pass_recycler_grid_row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

//        private ImageView imgDeal;
        holder.txtCabinName.setText(list.get(position).getCabinName());
        holder.txtPassFullName.setText(list.get(position).getPassFullName());
        holder.txtFlights.setText(list.get(position).getFlightsLabel());
        holder.txtCreditCount.setText("" + list.get(position).getCreditCount());
        holder.txtMonth.setText(getMonthLabel(list.get(position).getMonth()));
        holder.txtMonthCount.setText(getMonthCount(list.get(position).getMonth()));
        holder.txtCurrency.setText(list.get(position).getCurrencyCode());
        holder.txtAmount.setText(getAmount(list.get(position).getPerFlightCredit()));
        holder.txtPerOneWay.setText(getPerFlightText(list.get(position).getPerFlightCredit()));
        holder.txtPerFlight.setText(list.get(position).getPerFlightLabl());
        holder.txtRegularPrice.setText(list.get(position).getRegularPriceLabel());
        holder.txtStrikedRegularPrice.setText("" + list.get(position).getStrikedRegularPrice());
        holder.txtStrikedRegularPrice.setPaintFlags(holder.txtStrikedRegularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.txtSaveLabel.setText(list.get(position).getSaveLabel());
        holder.txtUpto.setText(" " + list.get(position).getUpToLabel());//add space to separate words
        holder.txtSavePrice.setText(" " + list.get(position).getSavePrice());
        holder.txtSavePercent.setText(list.get(position).getSavingPercentage() + "%");
        /*holder.imgDeal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItemSelect(v, position, list.get(position));
            }
        });*/
        holder.lay_main_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRecyclerItem(v, position, list.get(position));
            }
        });

        holder.imgDeal.setImageUrl(context.getString(R.string.URL_BASE) + context.getString(R.string.URL_IMAGE) + list.get(position).getBannerImage(), imageLoader);

        //Utils.DEBUG((String)ParseManager.getInstance().toJSON(list.get(position)));

    }

    /**
     * method used to get month count from given input
     * @param content ex: 'Month 3'
     * @return output will be '3'
     */
    private String getMonthCount(String content)
    {
        String val = "";
        if(content == null || content.length() == 0)
        {
            return val;
        }
        else
        {
            try
            {
                val = content.substring(content.indexOf(" ") + 1);
            }
            catch (IndexOutOfBoundsException e)
            {

            }
            Utils.DEBUG("Per flight text : " + val);
            return val;
        }
    }

    /**
     * method used to get month label from given input
     * @param content ex: 'Month 3',
     * @return output will be 'Month'
     */
    private String getMonthLabel(String content)
    {
        String val = "";
        if(content == null || content.length() == 0)
        {
            return val;
        }
        else
        {
            try
            {
                val = content.substring(0, content.indexOf(" "));
            }
            catch (IndexOutOfBoundsException e)
            {

            }
            Utils.DEBUG("Per flight text : " + val);
            return val;
        }
    }

    /**
     * method used to get per flight text from given input
     * @param perFlightCredit example input type '9661 per flight', output will be 'per flight'
     * @return
     */
    private String getPerFlightText(String perFlightCredit)
    {
        String content = "";
        if(perFlightCredit == null || perFlightCredit.length() == 0)
        {
            return content;
        }
        else
        {
            try
            {
                content = perFlightCredit.substring(perFlightCredit.indexOf(" ") + 1);
            }
            catch (IndexOutOfBoundsException e)
            {

            }
            Utils.DEBUG("Per flight text : " + content);
            return content;
        }
    }

    /**
     * used to get price of per flight from given input
     * @param perFlightCredit example input type '9661 per flight' output will be '9661'
     * @return
     */
    private String getAmount(String perFlightCredit)
    {
        String amount = "";
        if(perFlightCredit == null || perFlightCredit.length() == 0)
        {
            return amount;
        }
        else
        {
            try
            {
                amount = perFlightCredit.substring(0, perFlightCredit.indexOf(" "));
            }
            catch (IndexOutOfBoundsException e)
            {

            }
            Utils.DEBUG("Per flight amount : " + amount);
            return amount;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void notifyDataSetChanged(ArrayList<PassArray> list)
    {
        Utils.DEBUG(getClass().getSimpleName() + " >> notifyDataSetChanged >> " + list.size());
        this.list.clear();;
        this.list.addAll(list);
        super.notifyDataSetChanged();
    }

}

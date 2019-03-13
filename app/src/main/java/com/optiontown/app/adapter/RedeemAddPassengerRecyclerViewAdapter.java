package com.optiontown.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.redeem.FlightSmallView;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.RedeemSearchResultData;
import com.optiontown.app.model.redeem.Segment;
import com.optiontown.app.model.redeem.UsersDetail;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 22-09-2016.
 */
public class RedeemAddPassengerRecyclerViewAdapter extends RecyclerView.Adapter<RedeemAddPassengerRecyclerViewAdapter.ViewHolder>
{
    private final Context context;
    private final IRecyclerViewHolderClicks listener;
    private RedeemSearchResultData redeemSearchResultData = null;
    private ArrayList<Boolean> listChecked = new ArrayList<>();
    private int paxCount;
    private int maxCanAddUser;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItemSelect(View v, ArrayList<UsersDetail> listUsersDetail, boolean isDefault);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private OTTextView txtName;
        private ImageView imgUserIcon;
        private ImageView imgCheck;
        private View viewSeparator;
        private View viewMargin;
        private RelativeLayout lytRow;


        public ViewHolder(View v)
        {
            super(v);
            this.txtName = (OTTextView)  v.findViewById(R.id.txtName);
            this.imgUserIcon = (ImageView) v.findViewById(R.id.imgUserIcon);
            this.imgCheck = (ImageView) v.findViewById(R.id.imgCheck);
            this.viewMargin = (View) v.findViewById(R.id.viewMargin);
            this.viewSeparator = (View) v.findViewById(R.id.viewSeparator);
            this.lytRow = (RelativeLayout) v.findViewById(R.id.lytRow);
        }
    }

    public RedeemAddPassengerRecyclerViewAdapter(Context context, RedeemSearchResultData data, IRecyclerViewHolderClicks l)
    {
        this.context = context;
        this.redeemSearchResultData = data;
        this.listener = l;
        this.paxCount = Integer.parseInt(data.getPassengers_Count());
        this.maxCanAddUser = data.getMax_Passengers();

        ArrayList<UsersDetail> temp = new ArrayList<UsersDetail>();
        for (int index = 0; index < this.redeemSearchResultData.getUsersDetail().size() + 1; index++) {
            if(index != redeemSearchResultData.getUsersDetail().size())
            {
                boolean isSelected = redeemSearchResultData.getUsersDetail().get(index).isSelectedPassUser();
                listChecked.add(isSelected);
                if(isSelected)
                {
                    temp.add(redeemSearchResultData.getUsersDetail().get(index));
                }
            }
            else
            {
                listChecked.add(false);
            }

        }

        listener.onClickRecyclerItemSelect(null, temp, true);
    }

    @Override
    public RedeemAddPassengerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_redeem_add_passenger_row, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.lytRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position != redeemSearchResultData.getUsersDetail().size())
                {
                    ArrayList<UsersDetail> temp = new ArrayList<UsersDetail>();

                    if(isReachedLimit())
                    {
                        if(listChecked.get(position))
                        {
                            //listChecked.set(position, false);
                        }
                        else
                        {
                            return;
                        }
                    }

                    listChecked.set(position, !listChecked.get(position));

                    for (int index = 0; index < listChecked.size(); index++) {
                        if(listChecked.get(index))
                        {
                            temp.add(redeemSearchResultData.getUsersDetail().get(index));
                        }
                    }
                    listener.onClickRecyclerItemSelect(v, temp, false);


                    notifyDataSetChanged();
                }
                else
                {
                    if(!isReachedLimit() && maxCanAddUser > redeemSearchResultData.getUsersDetail().size())
                    {
                        listener.onClickRecyclerItemSelect(v, null, false);
                    }

                }
            }


        });

        if(position != redeemSearchResultData.getUsersDetail().size())
        {
            UsersDetail usersDetail = redeemSearchResultData.getUsersDetail().get(position);
            holder.imgCheck.setVisibility(listChecked.get(position) ? View.VISIBLE : View.GONE);
            holder.txtName.setText(getName(usersDetail));
            holder.txtName.setTextColor(context.getResources().getColor(R.color.color_font_black));
            holder.imgUserIcon.setBackgroundResource(listChecked.get(position) ? R.drawable.green_user : R.drawable.black_user);
            holder.lytRow.setBackgroundColor(Color.parseColor("#F3F3F3"));
            holder.viewMargin.setVisibility(View.GONE);
            holder.viewSeparator.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.imgCheck.setVisibility(View.GONE);
            holder.txtName.setText(redeemSearchResultData.getAdd_New_Passenger_Label());
            holder.txtName.setTextColor(context.getResources().getColor(R.color.color_font_blue_dark));
            holder.imgUserIcon.setBackgroundResource(R.drawable.blue_add_user);
            holder.lytRow.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.viewMargin.setVisibility(View.VISIBLE);
            holder.viewSeparator.setVisibility(View.GONE);
        }

        if(position == redeemSearchResultData.getUsersDetail().size())
        {
            if(!isReachedLimit() && maxCanAddUser > redeemSearchResultData.getUsersDetail().size())
            {
                holder.lytRow.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.lytRow.setVisibility(View.GONE);
            }
        }
        else
        {
            holder.lytRow.setVisibility(View.VISIBLE);
        }


    }

    private boolean isReachedLimit() {
        int count = 0;
        for (int index = 0; index < listChecked.size(); index++) {
            if(listChecked.get(index))
            {
                count++;
            }
        }
        return (count == paxCount ? true : false);
    }

    private String getName(UsersDetail usersDetail)
    {
        if(!usersDetail.getMName().equals(""))
        {
            return usersDetail.getFName() + " " + usersDetail.getMName() + " " + usersDetail.getLName();
        }
        else
        {
            return usersDetail.getFName() + " " + usersDetail.getLName();
        }
    }

    @Override
    public int getItemCount()
    {
        return  redeemSearchResultData.getUsersDetail().size() + 1;
    }
}

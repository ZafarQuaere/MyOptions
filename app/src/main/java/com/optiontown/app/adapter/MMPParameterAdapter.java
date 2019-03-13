package com.optiontown.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.optiontown.R;
import com.optiontown.app.model.redeem.ListOfPass;
import com.optiontown.app.model.redeem.UsersDetail;
import com.optiontown.app.model.redeem.mmp.ChangeFlightParameter;
import com.optiontown.app.model.redeem.mmp.MmpLabel;
import com.optiontown.app.model.redeem.mmp.SelectedParameterList;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by amit on 07-11-2016.
 */
public class MMPParameterAdapter extends RecyclerView.Adapter<MMPParameterAdapter.ViewHolder>
{
    private Object list;
    private Context context;
    private int changeParameterId;
    private ArrayList<Boolean> listChecked = new ArrayList<>();

    private final MMPParameterAdapter.RecyclerViewHolderClicks listener;


    public interface RecyclerViewHolderClicks {
        public void onClickRecyclerItemDetail(View v, Object data);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout lytRow;
        private OTTextView txtName;
        private ImageView imgCheck;
        private ImageView imgUserIcon;
        private View viewSeparator;


        public ViewHolder(View v)
        {
            super(v);
            this.lytRow             = (RelativeLayout)     v.findViewById(R.id.lytRow);
            this.txtName            = (OTTextView)         v.findViewById(R.id.txtName);
            this.imgCheck           = (ImageView)          v.findViewById(R.id.imgCheck);
            this.imgUserIcon        = (ImageView)          v.findViewById(R.id.imgUserIcon);
            this.viewSeparator      = (View)               v.findViewById(R.id.viewSeparator);
        }
    }

    public MMPParameterAdapter(Context context, int changeParameterId, Object list, RecyclerViewHolderClicks listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        this.changeParameterId = changeParameterId;

        if(changeParameterId != 0)
        {
            try
            {
                final ArrayList<SelectedParameterList> l = (ArrayList<SelectedParameterList>) this.list;
                for (int index = 0; index < l.size(); index++)
                {
                    listChecked.add(false);
                }
            }
            catch (Exception e)
            {
                Utils.ERROR("error while casting : " + e.toString());
            }
        }
    }



    @Override
    public MMPParameterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mmp_parameter_row, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.lytRow.setBackgroundColor(Color.parseColor("#F3F3F3"));
        if(changeParameterId == 0)
        {
            try
            {
                final ArrayList<ChangeFlightParameter> list = (ArrayList<ChangeFlightParameter>) this.list;
                holder.lytRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClickRecyclerItemDetail(v, list.get(position));
                    }
                });
                holder.txtName.setText(list.get(position).getPasslabelname());
                holder.imgUserIcon.setBackgroundResource(listChecked.get(position) ? R.drawable.green_user : R.drawable.black_user);
                holder.imgUserIcon.setVisibility(View.GONE);

                holder.viewSeparator.setVisibility(position == list.size() - 1 ? View.GONE : View.VISIBLE);
            }
            catch (Exception e)
            {
                //Utils.ERROR("error while casting1 : " + e.toString());
            }
        }
        else
        {
            try
            {
                final ArrayList<SelectedParameterList> list = (ArrayList<SelectedParameterList>) this.list;
                holder.lytRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        if(list.get(0).getErr_NotValid() == null) {
                            listChecked.set(position, !listChecked.get(position));
                            ArrayList<SelectedParameterList> temp = new ArrayList<SelectedParameterList>();
                            for (int index = 0; index < list.size(); index++) {
                                if (listChecked.get(index)) {
                                    temp.add(list.get(index));
                                    Utils.DEBUG("selected item : " + list.get(index));
                                }
                                if (changeParameterId != 8)//replace user
                                {
                                    listChecked.set(index, Boolean.FALSE);
                                }
                            }

                            listener.onClickRecyclerItemDetail(v, temp);
                            notifyDataSetChanged();
                        }


                    }
                });

                    holder.txtName.setText(list.get(position).getLabel());
                    if(changeParameterId == 8)//replace user
                    {
                        holder.imgCheck.setVisibility(listChecked.get(position) ? View.VISIBLE : View.INVISIBLE);
                        holder.imgUserIcon.setVisibility(View.VISIBLE);
                        holder.imgUserIcon.setBackgroundResource(listChecked.get(position) ? R.drawable.green_user : R.drawable.black_user);
                    }
                    else
                    {
                        holder.imgUserIcon.setVisibility(View.GONE);
                    }

                    holder.viewSeparator.setVisibility(position == list.size() - 1 ? View.GONE : View.VISIBLE);

                if(!list.get(0).getErr_NotValid().isEmpty() || list.get(0).getErr_NotValid() != null)
                {
                    holder.txtName.setText(list.get(0).getErr_NotValid());
                    holder.imgCheck.setVisibility(View.GONE);
                    holder.imgUserIcon.setVisibility(View.GONE);
                }

            }
            catch (Exception e)
            {
                //Utils.ERROR("error while casting2 : " + e.toString());
            }
        }
    }

    @Override
    public int getItemCount()
    {
        try
        {
            final ArrayList<ChangeFlightParameter> list = (ArrayList<ChangeFlightParameter>) this.list;
            return list.size();
        }
        catch (Exception e)
        {
            Utils.ERROR("error while casting11 : " + e.toString());
        }

        try
        {
            final ArrayList<SelectedParameterList> list = (ArrayList<SelectedParameterList>) this.list;
            return list.size();
        }
        catch (Exception e)
        {
            Utils.ERROR("error while casting22 : " + e.toString());
        }
        return  0;
    }

}

package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.optiontown.R;
import com.optiontown.app.model.redeem.mmp.UsersAdded;
import com.optiontown.app.view.customview.OTTextView;

import java.util.List;

/**
 * Created by zafar.imam on 27-10-2016.
 */
public class MMPUpdateUserAdapter extends RecyclerView.Adapter<MMPUpdateUserAdapter.ViewHolder> {
    private final MMPUpdateUserAdapter.RecyclerViewHolderClicks listener;
    private List<UsersAdded> usersAddedList;
    private Context context;
    private View view1;
    private ViewHolder viewHolder1;

    public MMPUpdateUserAdapter(Context context, List<UsersAdded> usersAddedList, RecyclerViewHolderClicks listener) {
        this.context = context;
        this.usersAddedList = usersAddedList;
        this.listener = listener;
    }

    @Override
    public MMPUpdateUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = LayoutInflater.from(context).inflate(R.layout.layout_mmp_add_passenger_row, parent, false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final MMPUpdateUserAdapter.ViewHolder holder, final int position) {
        holder.imgUserIcon.setImageResource(R.drawable.black_user);
        holder.txtName.setText(usersAddedList.get(position).getUserName());
        if (usersAddedList.get(position).isSelectedUser()) {
            holder.imgCheck.setVisibility(View.VISIBLE);
            holder.imgUserIcon.setImageResource(R.drawable.green_user);
        } else {
            holder.imgCheck.setVisibility(View.GONE);
            holder.imgUserIcon.setImageResource(R.drawable.black_user);
        }

        holder.lytRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onClickRecyclerItemDetail(v, usersAddedList.get(position));

                for (int i = 0; i < usersAddedList.size(); i++) {
                    if (i == position) {
                        usersAddedList.get(i).setSelectedUser(true);
                    } else {
                        usersAddedList.get(i).setSelectedUser(false);
                    }
                }
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersAddedList.size();
    }


    public interface RecyclerViewHolderClicks {
        public void onClickRecyclerItemDetail(View v, UsersAdded usersAddedLabel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public OTTextView txtName;
        private ImageView imgUserIcon;
        private ImageView imgCheck;
        private View viewSeparator;
        private View viewMargin;
        private RelativeLayout lytRow;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (OTTextView) itemView.findViewById(R.id.txtName);
            this.imgUserIcon = (ImageView) itemView.findViewById(R.id.imgUserIcon);
            this.imgCheck = (ImageView) itemView.findViewById(R.id.imgCheck);
            this.viewMargin = (View) itemView.findViewById(R.id.viewMargin);
            this.viewSeparator = (View) itemView.findViewById(R.id.viewSeparator);
            this.lytRow = (RelativeLayout) itemView.findViewById(R.id.lytRow);

        }
    }
}

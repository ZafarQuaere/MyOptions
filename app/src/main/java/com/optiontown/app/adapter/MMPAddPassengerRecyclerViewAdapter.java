package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.UsersDetail;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemAddPaxIdentityFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemAddPaxInfoFragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by parasmani.sharma on 05/11/2016.
 */
public class MMPAddPassengerRecyclerViewAdapter extends RecyclerView.Adapter<MMPAddPassengerRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final IRecyclerViewHolderClicks listener;
    private ArrayList<String> data = null;
    private ArrayList<Boolean> listChecked = new ArrayList<>();
    private int paxCount;
    private int maxCanAddUser;
    HashMap<View,String> checkedPassengers = new HashMap<>();
    private boolean addNewUserLayout;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItemSelect(View v, HashMap<View,String> listUsersDetail, boolean isDefault);
    }

    public MMPAddPassengerRecyclerViewAdapter(Context context,int userCount, ArrayList<String> data, IRecyclerViewHolderClicks l)
    {
        this.context = context;
        this.data = data;
        this.listener = l;
        this.paxCount = data.size();
        this.maxCanAddUser = userCount;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private OTTextView txtName;
        private ImageView imgUserIcon;
        private ImageView imgCheck;
        private View viewSeparator;
        private View viewMargin;
        private RelativeLayout lytRow;
        private CardView card_view;



        public ViewHolder(View v)
        {
            super(v);
            this.card_view = (CardView)  v.findViewById(R.id.card_view);
            this.txtName = (OTTextView)  v.findViewById(R.id.txtName);
            this.imgUserIcon = (ImageView) v.findViewById(R.id.imgUserIcon);
            this.imgCheck = (ImageView) v.findViewById(R.id.imgCheck);
            this.viewMargin = (View) v.findViewById(R.id.viewMargin);
            this.viewSeparator = (View) v.findViewById(R.id.viewSeparator);
            this.lytRow = (RelativeLayout) v.findViewById(R.id.lytRow);
        }
    }

    @Override
    public MMPAddPassengerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mmp_add_passenger_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.imgCheck.setVisibility(View.GONE);
        holder.imgUserIcon.setImageResource(R.drawable.black_user);
        holder.lytRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //checkRemainingSelection(data.get(position), position,v);

                //int userRequireValue = addUserTextShow();
                //listener.onClickRecyclerItemSelect(v, checkedPassengers, addNewUserLayout);

            }




        });

        holder.txtName.setText(data.get(position));


    }

    private int addUserTextShow() {

       int userRequire = maxCanAddUser-paxCount;
        if(userRequire > 0)
        {
            addNewUserLayout = true;
            return userRequire;
        }else {
            addNewUserLayout = false;
            return  userRequire;
        }

    }

    private void checkRemainingSelection(String selectedPassenger, int position, View v) {


        //first check if passengers are already added or not

        boolean checkedStatus = checkIfAlreadySelected(v);

        if(checkedPassengers.size() < maxCanAddUser || checkedPassengers.size() == maxCanAddUser) {
            if (checkedStatus)
            {
                uncheckLayout(v);
                addNewUserLayout = true;
            }
            else {

                if(checkedPassengers.size() == maxCanAddUser)
                {
                    addNewUserLayout = false;
                    return;
                }

                if(checkedPassengers.size() < maxCanAddUser)
                {
                    checkLayout(selectedPassenger, v);

                    if(checkedPassengers.size() == maxCanAddUser)
                    {
                        addNewUserLayout = false;
                        return;
                    }else {
                        addNewUserLayout = true;
                    }
                }

            }


        }else{
            addNewUserLayout = false;
        }
    }

    private void checkLayout(String selectedPassenger, View v) {

        System.out.println("False >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: Add data");
        checkedPassengers.put(v,selectedPassenger);
        System.out.println("==================== " + checkedPassengers.toString());
        ImageView checkImg = (ImageView) v.findViewById(R.id.imgCheck);
        ImageView imgUserIcon = (ImageView) v.findViewById(R.id.imgUserIcon);
        imgUserIcon.setImageResource(R.drawable.green_user);
        checkImg.setVisibility(View.VISIBLE);
    }

    private void uncheckLayout(View v) {

        System.out.println("TRUE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<: remove data");
        checkedPassengers.remove(v);
        System.out.println("=================== " + checkedPassengers.toString());
        ImageView checkImg = (ImageView) v.findViewById(R.id.imgCheck);
        ImageView imgUserIcon = (ImageView) v.findViewById(R.id.imgUserIcon);
        imgUserIcon.setImageResource(R.drawable.black_user);
        checkImg.setVisibility(View.GONE);
    }

    private boolean checkIfAlreadySelected(View v) {

        if(checkedPassengers.containsKey(v))
        {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.optiontown.R;
import com.optiontown.app.model.fpogetpass.PassObject;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 16-06-2016.
 */
public class SelectFlightPassRecyclerViewAdapter extends RecyclerView.Adapter<SelectFlightPassRecyclerViewAdapter.ViewHolder>
{
    private final InternationalizeData localization;
    private ArrayList<PassObject> listParent;
    private ArrayList<PassObject> listChild = new ArrayList<PassObject>();

    private Context context;

    private static final int MAX_LIST_VIEW = 10;
    private int SELECTED_PAGE = 1;

    private boolean moveToFirst = false;

    private IRecyclerViewHolderClicks listener;
    private String perFlightLabel;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, PassObject passObject);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lytPagination;
        private LinearLayout lytSearchPass;
        private LinearLayout lytSelectFlightPassRow;



        private OTTextView txtFirst;
        private OTTextView txtBackward;
        private OTTextView txtOne;
        private OTTextView txtTwo;
        private OTTextView txtThree;
        private OTTextView txtFour;
        private OTTextView txtForward;
        private OTTextView txtLast;

        private OTTextView txtZoneName;
        private OTTextView txtAdvanceBooking;
        private OTTextView txtPassenger;
        private OTTextView txtFlightsLabel;
        private OTTextView txtMonthLabel;
        private OTTextView txtTotalPrice;
        private OTTextView txtFlightsCount;
        private OTTextView txtMonthCount;
        private OTTextView txtCurrency;
        private OTTextView txtPerFlightAmount;
        private OTTextView txtPerFlightLabel;
        private OTTextView txtSave;
        InternationalizeData localization;



        public ViewHolder(View v) {
            super(v);

            this.lytPagination = (LinearLayout) v.findViewById(R.id.lytPagination);
            this.lytSearchPass = (LinearLayout) v.findViewById(R.id.lytSearchPass);
            this.lytSelectFlightPassRow  = (LinearLayout)    v.findViewById(R.id.lytSelectFlightPassRow);


            this.txtFirst  = (OTTextView)    v.findViewById(R.id.txtFirst);
            this.txtBackward  = (OTTextView)    v.findViewById(R.id.txtBackward);
            this.txtOne  = (OTTextView)    v.findViewById(R.id.txtOne);
            this.txtTwo  = (OTTextView)    v.findViewById(R.id.txtTwo);
            this.txtThree  = (OTTextView)    v.findViewById(R.id.txtThree);
            this.txtFour  = (OTTextView)    v.findViewById(R.id.txtFour);
            this.txtForward  = (OTTextView)    v.findViewById(R.id.txtForward);
            this.txtLast  = (OTTextView)    v.findViewById(R.id.txtLast);

            this.txtZoneName = (OTTextView)    v.findViewById(R.id.txtZoneName);;
            this.txtAdvanceBooking = (OTTextView)    v.findViewById(R.id.txtAdvanceBooking);;
            this.txtPassenger = (OTTextView)    v.findViewById(R.id.txtPassenger);;
            this.txtFlightsLabel = (OTTextView)    v.findViewById(R.id.txtFlightsLabel);;
            this.txtMonthLabel = (OTTextView)    v.findViewById(R.id.txtMonthLabel);;
            this.txtTotalPrice = (OTTextView)    v.findViewById(R.id.txtTotalPrice);;
            this.txtFlightsCount = (OTTextView)    v.findViewById(R.id.txtFlightsCount);;
            this.txtMonthCount = (OTTextView)    v.findViewById(R.id.txtMonthCount);;
            this.txtCurrency = (OTTextView)    v.findViewById(R.id.txtCurrency);;
            this.txtPerFlightAmount = (OTTextView)    v.findViewById(R.id.txtPerFlightAmount);;
            this.txtPerFlightLabel = (OTTextView)    v.findViewById(R.id.txtPerFlightLabel);;
            this.txtSave = (OTTextView)    v.findViewById(R.id.txtSave);;

        }
    }

    public SelectFlightPassRecyclerViewAdapter(InternationalizeData localization, Context context, ArrayList<PassObject> list, IRecyclerViewHolderClicks l, String perFlightLabel)
    {
        this.context = context;
        this.listParent = list;
        this.listener = l;
        this.perFlightLabel = perFlightLabel;
        this.localization = localization;
        resetChildList();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SelectFlightPassRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_flight_pass_row, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Utils.DEBUG("onBindViewHolder() >> position : " + position);

        try {
            if (getSize() - 1 == position)//last
            {
                holder.lytPagination.setVisibility(View.VISIBLE);
                holder.lytSearchPass.setVisibility(View.GONE);

                holder.lytSelectFlightPassRow.setOnClickListener(null);

                resetPagination(holder, (holder.txtOne).getText().toString());


            } else {
                holder.lytPagination.setVisibility(View.GONE);
                holder.lytSearchPass.setVisibility(View.VISIBLE);


                holder.lytSelectFlightPassRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClickRecyclerItem(v, listChild.get(position));
                    }
                });
                if (position % 2 == 0) {
                    holder.lytSelectFlightPassRow.setBackgroundColor(context.getResources().getColor(R.color.color_gray_light));
                } else {
                    holder.lytSelectFlightPassRow.setBackgroundColor(context.getResources().getColor(R.color.color_white));
                }


                holder.txtZoneName.setText(listChild.get(position).getPassName());
                if(listChild.get(position).getAdvanceBooking().getTag() != null)
                {
                    holder.txtAdvanceBooking.setText(listChild.get(position).getAdvanceBooking().getTag() + " " + listChild.get(position).getAdvanceBooking().getName());
                }
                else
                {
                    holder.txtAdvanceBooking.setText("");
                }
                holder.txtPassenger.setText(listChild.get(position).getPassengerLabel() + " " + listChild.get(position).getPassenger());
                holder.txtFlightsLabel.setText(listChild.get(position).getOnlyFlightsLabel());
                holder.txtMonthLabel.setText(listChild.get(position).getPassValidityTimeName());
                holder.txtTotalPrice.setText("Total " + listChild.get(position).getTotalPassPriceData());
                holder.txtFlightsCount.setText("" + listChild.get(position).getCreditValue());
                holder.txtMonthCount.setText("" + listChild.get(position).getPassValidityValue());
                holder.txtCurrency.setText(listChild.get(position).getCurrencySymbol());
                holder.txtPerFlightAmount.setText("" + listChild.get(position).getPricePerCredit());
                holder.txtPerFlightLabel.setText(perFlightLabel);
                if (listChild.get(position).getPassObjComplete().getSavingPercent() > 0) {
                    holder.txtSave.setText(listChild.get(position).getPassObjComplete().getPassSavingAmountLabel() + " " +
                            listChild.get(position).getPassObjComplete().getSavingPercent() + "%");
                } else {
                    holder.txtSave.setText("");
                }


            }
        }catch (Exception e)
        {}
    }

    private void resetPagination(final ViewHolder holder, String txtClicked)
    {
        Utils.DEBUG("resetPagination() called >> clicked : " + txtClicked);
        int page = (listParent.size()/MAX_LIST_VIEW) + ((listParent.size() % MAX_LIST_VIEW) == 0 ? 0 : 1);

        if(moveToFirst)
        {
            moveToFirst = false;
            txtClicked = "First";
        }

        if(txtClicked.equals("First"))
        {
            SELECTED_PAGE = 1;
        }
        else if(txtClicked.equals("<"))
        {
            SELECTED_PAGE--;
        }
        else if(txtClicked.equals(">"))
        {
            SELECTED_PAGE++;
        }
        else if(txtClicked.equals("Last"))
        {
            SELECTED_PAGE = page;
        }
        else if(txtClicked.equals("1"))
        {
            if(SELECTED_PAGE == 1)
            {
                SELECTED_PAGE = Integer.parseInt(txtClicked);
            }
            else
            {
                //do nothing
            }
        }
        else
        {
            SELECTED_PAGE = Integer.parseInt(txtClicked);
        }

        resetChildList();

        //
        //Utils.DEBUG("resetPagination() called >> clicked : " + txtClicked + ", selected page : " + SELECTED_PAGE + ", total page : " + page);

        if(SELECTED_PAGE > 1)
        {
            holder.txtBackward.setTextColor(context.getResources().getColor(R.color.color_blue));
            addListenerAndUpdateUI(holder.txtBackward, holder, R.color.color_blue);

            holder.txtFirst.setTextColor(context.getResources().getColor(R.color.color_blue));
            holder.txtFirst.setText(localization.getLABLFirstLabel());
            holder.txtFirst.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    resetPagination(holder, ((OTTextView)v).getText().toString());
                }
            });
        }
        else
        {
            holder.txtFirst.setOnClickListener(null);
            holder.txtFirst.setTextColor(context.getResources().getColor(R.color.color_font_gray));
            holder.txtFirst.setText(localization.getLABLFirstLabel());
            holder.txtBackward.setOnClickListener(null);
            holder.txtBackward.setTextColor(context.getResources().getColor(R.color.color_font_gray));
        }

        holder.txtOne.setText(Integer.toString(SELECTED_PAGE));
        holder.txtOne.setTextColor(context.getResources().getColor(R.color.color_red));



        if(SELECTED_PAGE + 1 <= page)
        {
            holder.txtTwo.setVisibility(View.VISIBLE);
            holder.txtTwo.setText(Integer.toString(SELECTED_PAGE + 1));
            addListenerAndUpdateUI(holder.txtTwo, holder, R.color.color_blue);
        }
        else
        {
            holder.txtTwo.setOnClickListener(null);
            holder.txtTwo.setTextColor(context.getResources().getColor(R.color.color_font_gray));
            holder.txtTwo.setVisibility(View.GONE);
        }

        if(SELECTED_PAGE + 2 <= page)
        {
            holder.txtThree.setVisibility(View.VISIBLE);
            holder.txtThree.setText(Integer.toString(SELECTED_PAGE + 2));
            addListenerAndUpdateUI(holder.txtThree, holder, R.color.color_blue);
        }
        else
        {
            holder.txtThree.setOnClickListener(null);
            holder.txtThree.setTextColor(context.getResources().getColor(R.color.color_font_gray));
            holder.txtThree.setVisibility(View.GONE);
        }

        if(SELECTED_PAGE + 3 <= page)
        {
            holder.txtFour.setVisibility(View.VISIBLE);
            holder.txtFour.setText(Integer.toString(SELECTED_PAGE + 3));
            addListenerAndUpdateUI(holder.txtFour, holder, R.color.color_blue);
        }
        else
        {
            holder.txtFour.setOnClickListener(null);
            holder.txtFour.setTextColor(context.getResources().getColor(R.color.color_font_gray));
            holder.txtFour.setVisibility(View.GONE);
        }

        if(SELECTED_PAGE + 4 <= page)
        {
            addListenerAndUpdateUI(holder.txtForward, holder, R.color.color_blue);
            holder.txtLast.setTextColor(context.getResources().getColor(R.color.color_blue));
            holder.txtLast.setText(localization.getLABLLastLabel());
            holder.txtLast.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    resetPagination(holder, ((OTTextView)v).getText().toString());
                }
            });
        }
        else
        {
            holder.txtForward.setOnClickListener(null);
            holder.txtForward.setTextColor(context.getResources().getColor(R.color.color_font_gray));
            holder.txtLast.setOnClickListener(null);
            holder.txtLast.setTextColor(context.getResources().getColor(R.color.color_font_gray));
            holder.txtLast.setText(localization.getLABLLastLabel());
        }
    }

    private void addListenerAndUpdateUI(OTTextView view, final ViewHolder holder, int color)
    {
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resetPagination(holder, ((OTTextView)v).getText().toString());
                notifyDataSetChanged();

                Utils.DEBUG("addListenerAndUpdateUI() called >> notifyDataSetChanged() initiated");
            }
        });
        view.setTextColor(context.getResources().getColor(color));
    }

    private void resetChildList()
    {
        listChild.clear();
        Utils.DEBUG(getClass().getSimpleName() + ">> resetChildList() >> SELECTED_PAGE = >> " + SELECTED_PAGE);
        for (int index = (SELECTED_PAGE - 1)*MAX_LIST_VIEW; index < (SELECTED_PAGE - 1)*MAX_LIST_VIEW + (listParent.size() >= MAX_LIST_VIEW ? MAX_LIST_VIEW : listParent.size()) ; index++)
        {
           // Utils.DEBUG("resetChildList() >> child list items >> " + index);
            try
            {
                listChild.add(listParent.get(index));
                //Utils.DEBUG(ParseManager.getInstance().toJSON(listParent.get(index)));
            }
            catch (Exception e)
            {
                Utils.DEBUG("Error : " + e.toString());
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return  getSize();
    }

    private int getSize()
    {
        int count;
        if(MAX_LIST_VIEW > listChild.size())
        {
            count = listChild.size() + 1;
        }
        else
        {
            count = MAX_LIST_VIEW + 1;
        }
        //Utils.DEBUG("getSize() >> size is : " + count);
        return count;
    }


    public void notifyDataSetChanged(ArrayList<PassObject> list)
    {
        Utils.DEBUG(getClass().getSimpleName() + " >> notifyDataSetChanged >> " + list.size());
        this.listParent.clear();;
        this.listParent.addAll(list);
        moveToFirst = true;
        resetChildList();
        super.notifyDataSetChanged();
    }
}
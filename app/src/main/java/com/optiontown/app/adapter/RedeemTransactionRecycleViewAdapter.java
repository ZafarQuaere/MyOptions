package com.optiontown.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.optiontown.R;
import com.optiontown.app.model.redeem.ListOfPass;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

/**
 * Created by parasmani.sharma on 22/09/2016.
 */
public class RedeemTransactionRecycleViewAdapter extends RecyclerView.Adapter<RedeemTransactionRecycleViewAdapter.ViewHolder> {

    private final Context context;
    private ListOfPass listOfPass;

    public RedeemTransactionRecycleViewAdapter(FragmentActivity activity, ListOfPass listOfPass) {
        this.context = activity;
        this.listOfPass = listOfPass;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final OTTextView txt_label_event;
        private final OTTextView txt_event;
        private final OTTextView txt_label_desc;
        private final OTTextView txt_desc;
        private final OTTextView txt_label_amount;
        private final OTTextView txt_amount;
        private final OTTextView txt_label_account;
        private final OTTextView txt_account;
        private final OTTextView txt_label, txt_data;
        private final OTTextView txt_label_pass_credits, txt_credit_description;
        private final LinearLayout lay_pass_credits;

        public ViewHolder(View view) {
            super(view);
            this.txt_label = (OTTextView) view.findViewById(R.id.txt_label);
            this.txt_data = (OTTextView) view.findViewById(R.id.txt_data);

            this.txt_label_event = (OTTextView) view.findViewById(R.id.txt_label_event);
            this.txt_event = (OTTextView) view.findViewById(R.id.txt_event);

            this.txt_label_desc = (OTTextView) view.findViewById(R.id.txt_label_desc);
            this.txt_desc = (OTTextView) view.findViewById(R.id.txt_desc);

            this.txt_label_amount = (OTTextView) view.findViewById(R.id.txt_label_amount);
            this.txt_amount = (OTTextView) view.findViewById(R.id.txt_amount);

            this.txt_label_account = (OTTextView) view.findViewById(R.id.txt_label_account);
            this.txt_account = (OTTextView) view.findViewById(R.id.txt_account);

            this.lay_pass_credits = (LinearLayout) view.findViewById(R.id.lay_pass_credits);
            this.txt_label_pass_credits = (OTTextView) view.findViewById(R.id.txt_label_pass_credits);
            this.txt_credit_description = (OTTextView) view.findViewById(R.id.txt_credit_description);

        }
    }


    @Override
    public RedeemTransactionRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_redeem_fpo_transaction_details, parent, false);

        ViewHolder vh = new ViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (listOfPass != null) {

            if (!(Utils.getCurrentProductId((Activity) context) == context.getResources().getInteger(R.integer.value_tgProductId_fpo))) {

                holder.lay_pass_credits.setVisibility(View.VISIBLE);
                holder.txt_label_pass_credits.setText(listOfPass.getTransactionHistory().getAddedRedeemed());
                holder.txt_credit_description.setText(listOfPass.getTransactionHistory().getTransdetail().get(position).getEventTypeDescriptionValue());

            }
            else {
                holder.lay_pass_credits.setVisibility(View.GONE);
              }

            holder.txt_label.setText(listOfPass.getTransactionHistory().getLABLTransactionDateLabel());
            holder.txt_label_event.setText(listOfPass.getTransactionHistory().getTransHistoryEventLabel());
            holder.txt_label_desc.setText(listOfPass.getTransactionHistory().getLABLEventDescriptionLabel());
            holder.txt_label_amount.setText(listOfPass.getTransactionHistory().getTotalAmountLabel());
            holder.txt_label_account.setText(listOfPass.getTransactionHistory().getLABLTansactionHistoryAccountHeadingLabel());

            holder.txt_data.setText(listOfPass.getTransactionHistory().getTransdetail().get(position).getTransactionDateFormat());
            holder.txt_event.setText(listOfPass.getTransactionHistory().getTransdetail().get(position).getEventLabel());
            holder.txt_amount.setText(listOfPass.getTransactionHistory().getTransdetail().get(position).getPaymentCurrency() + " " + listOfPass.getTransactionHistory().getTransdetail().get(position).getPrice() + "");
            holder.txt_account.setText(listOfPass.getTransactionHistory().getTransdetail().get(position).getCreditCardNoEncyptd() + "");

            if (listOfPass.getTransactionHistory().getTransdetail().get(position).getTgpEventId() == 1) {
                holder.txt_desc.setText(listOfPass.getTransactionHistory().getTransdetail().get(position).getTransactionHistoryLabel() +
                        "\n" + listOfPass.getTransactionHistory().getTransdetail().get(position).getEventTypeDescriptionValue());
            } else if (listOfPass.getTransactionHistory().getTransdetail().get(position).getTgpEventId() == 92) {
                holder.txt_desc.setText(listOfPass.getTransactionHistory().getTransdetail().get(position).getTransactionHistoryLabel() +
                        "\n" + listOfPass.getTransactionHistory().getTransdetail().get(position).getEventTypeDescriptionValue());

            } else if (listOfPass.getTransactionHistory().getTransdetail().get(position).getTgpEventId() == 5) {
                holder.txt_desc.setText(listOfPass.getTransactionHistory().getTransdetail().get(position).getChangetype() +
                        "\n" + listOfPass.getTransactionHistory().getTransdetail().get(position).getOldValueLabel() +
                        "\n" + listOfPass.getTransactionHistory().getTransdetail().get(position).getNewValueLabel());

            } else {
                holder.txt_desc.setText(listOfPass.getTransactionHistory().getTransdetail().get(position).getChangetype() +
                        "\n" + listOfPass.getTransactionHistory().getTransdetail().get(position).getOldValueLabel() +
                        "\n" + listOfPass.getTransactionHistory().getTransdetail().get(position).getNewValueLabel() +
                        "\n" + listOfPass.getTransactionHistory().getTransdetail().get(position).getTransactionHistoryLabel() +
                        "\n" + listOfPass.getTransactionHistory().getTransdetail().get(position).getEventTypeDescriptionValue());
            }
        }
    }


    @Override
    public int getItemCount() {
        return listOfPass.getTransactionHistory().getTransdetail().size();
    }
}

package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.optiontown.R;
import com.optiontown.app.model.legviewdetails.TransactionList;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.customview.OTTextViewHtml;

import java.util.ArrayList;

/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author Ravi Kumar
 */
public class ViewDetailsMyTransactionsRecyclerViewAdapter extends RecyclerView.Adapter<ViewDetailsMyTransactionsRecyclerViewAdapter.ViewHolder> {


    ArrayList<TransactionList> transactionList;
    String rupee = "";
    private ImageLoader imageLoader;
    private OTTextView txtDate;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private Context ctx;


    public ViewDetailsMyTransactionsRecyclerViewAdapter(Context ctx, ArrayList<TransactionList> transactionList) {
        this.transactionList = transactionList;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx = ctx;
        rupee = ctx.getString(R.string.string_rs_html_2);
    }

    @Override
    public ViewDetailsMyTransactionsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_details_my_transactios_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.txtTransactionDate.setText(transactionList.get(position).getLegTransactionDate());
        holder.txtAirlineCode.setText(transactionList.get(position).getAirlineCode());
        holder.txtDepartArriveAp.setText(transactionList.get(position).getDepartureArriveAirport());
        holder.txtDepartDate.setText(transactionList.get(position).getDepartureDate());
        if(transactionList.get(position).getTransHistoryEventLabel()!=null)
            holder.txtEventLabel.setHtml(transactionList.get(position).getTransHistoryEventLabel());
        if(transactionList.get(position).getTotalAmountLabel()!=null)
            holder.txtTotalAmountLable.setHtml(transactionList.get(position).getTotalAmountLabel());
        if (transactionList.get(position).getTotalAmountValue() != null) {
            holder.txtTotalAmount.setHtml(transactionList.get(position).getDisplayCurrencySymbol()+"" + transactionList.get(position).getTotalAmountValue());
        } else {
            holder.txtTotalAmount.setHtml(" - ");
        }
        if ((transactionList.get(position).getUpCabinName()!=null)&&(transactionList.get(position).getCabinPrice()!=null)){
            holder.txtUpCabinName.setText(transactionList.get(position).getUpCabinName());
            holder.txtUpCabinPrice.setHtml(transactionList.get(position).getDisplayCurrencySymbol() + transactionList.get(position).getCabinPrice());
        }
        else {
            holder.txtUpCabinName.setText("");
            holder.txtUpCabinPrice.setHtml("");
        }

        holder.txtPayAccountLable.setText(transactionList.get(position).getTransHistoryAccountLabel());
        holder.txtPaymentAccount.setText(transactionList.get(position).getCardNumberCurrencyAmount());
        holder.txtStatusLable.setText(transactionList.get(position).getTransHistoryStatusLabel());
        holder.txtStatus.setText(transactionList.get(position).getStatusLabel());
        try{
            if (!transactionList.get(position).getTrans_History_Event_Label_leg().equalsIgnoreCase("")){
                holder.txtUpCabinName.setText(transactionList.get(position).getTrans_History_Event_Label_leg());
            }
        }catch (Exception e){
            e.printStackTrace();
        }




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private OTTextView txtTransactionDate, txtAirlineCode, txtDepartDate, txtDepartArriveAp,  txtUpCabinName,
                 txtPayAccountLable, txtPaymentAccount, txtStatusLable, txtStatus;
        private OTTextViewHtml txtTotalAmountLable, txtUpCabinPrice,txtTotalAmount,txtEventLabel;

        ///   private Button btnBoostPriority;
        //  RecyclerView mRecyclerView;
        public ViewHolder(View v) {
            super(v);
            txtTransactionDate = (OTTextView) v.findViewById(R.id.txtTransactionDate);
            txtEventLabel = (OTTextViewHtml) v.findViewById(R.id.txtEventLabel);
            txtAirlineCode = (OTTextView) v.findViewById(R.id.txtAirlineCode);
            txtDepartDate = (OTTextView) v.findViewById(R.id.txtDepartDate);
            txtDepartArriveAp = (OTTextView) v.findViewById(R.id.txtDepartArriveAp);
            txtTotalAmount = (OTTextViewHtml) v.findViewById(R.id.txtTotalAmount);
            txtPaymentAccount = (OTTextView) v.findViewById(R.id.txtPaymentAccount);
            txtPayAccountLable = (OTTextView) v.findViewById(R.id.txtPayAccountLable);
            txtStatusLable = (OTTextView) v.findViewById(R.id.txtStatusLable);
            txtStatus = (OTTextView) v.findViewById(R.id.txtStatus);
            txtUpCabinName = (OTTextView) v.findViewById(R.id.txtUpCabinName);
            txtTotalAmountLable = (OTTextViewHtml) v.findViewById(R.id.txtTotalAmountLable);
            txtUpCabinPrice = (OTTextViewHtml) v.findViewById(R.id.txtUpCabinPrice);


        }
    }
}
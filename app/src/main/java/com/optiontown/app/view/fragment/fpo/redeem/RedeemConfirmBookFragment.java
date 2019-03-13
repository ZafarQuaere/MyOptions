package com.optiontown.app.view.fragment.fpo.redeem;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.RedeemSearchResultRecyclerViewAdapter;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.RedeemSearchResultData;
import com.optiontown.app.model.redeem.TransactionHistoryDetail;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.optiontown.app.utils.Utils.DEBUG;

/**
 * Created by amit on 30-09-2016.
 */
public class RedeemConfirmBookFragment extends BaseFragment
{
    private View view;
    private OTTextView txtMessage;
    private OTTextView txtBookingSummaryTitle;
    private OTTextView txtConfirmation;
    private OTTextView txtAirline;
    private OTTextView txtFlightPass;
    private OTTextView txtPNR;
    private OTTextView txtPassengersLabel;
    private OTTextView txtTransactionTitle;

    private OTTextView txtBookMoreFlights;
    private RedeemSearchResultData redeemSearchResultData;
    private RecyclerView recyclerView;
    private LinearLayout lytPassengersName;
    private LinearLayout lytBookingSummary;
    private LinearLayout lytTransaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Utils.DEBUG("onCreateView called");
        view = inflater.inflate(R.layout.fragment_redeem_confirm_book, container, false);

        redeemSearchResultData = (RedeemSearchResultData) getArguments().getSerializable(getString(R.string.key_serializable));



        getUIReference();
        updateUI();
        return view;
    }

    private void getUIReference()
    {
        lytBookingSummary = (LinearLayout) view.findViewById(R.id.lytBookingSummary);
        lytTransaction = (LinearLayout) view.findViewById(R.id.lytTransaction);
        lytPassengersName = (LinearLayout) view.findViewById(R.id.lytPassengersName);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        txtMessage = (OTTextView) view.findViewById(R.id.txtMessage);
        txtBookingSummaryTitle = (OTTextView) view.findViewById(R.id.txtBookingSummaryTitle);
        txtConfirmation = (OTTextView) view.findViewById(R.id.txtConfirmation);
        txtAirline = (OTTextView) view.findViewById(R.id.txtAirline);
        txtFlightPass = (OTTextView) view.findViewById(R.id.txtFlightPass);
        txtPNR = (OTTextView) view.findViewById(R.id.txtPNR);
        txtPassengersLabel = (OTTextView) view.findViewById(R.id.txtPassengersLabel);

        txtTransactionTitle = (OTTextView) view.findViewById(R.id.txtTransactionTitle);



        txtBookMoreFlights = (OTTextView) view.findViewById(R.id.txtBookMoreFlights);
        txtBookMoreFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToBookMoreFlight();
            }
        });
    }

    private void moveToBookMoreFlight() {
        android.support.v4.app.FragmentManager fm = ((FragmentActivity) getActivity()).getSupportFragmentManager();
        fm.popBackStack(new SelectBookFlightFragment().getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Utils.moveToFragment(getActivity(), new SelectBookFlightFragment(), null, 0);
    }

    @Override
    public void onBackEventPost() {
        super.onBackEventPost();
        Utils.DEBUG("RedeemConfirmBookFragment() >> onBackEventPost() called");
        moveToBookMoreFlight();
    }

    private void updateUI()
    {
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), redeemSearchResultData.getBook_Confirmation_Label(), null);

        if(redeemSearchResultData.getBooking_Process_Error().equals("1"))
        {
            lytBookingSummary.setVisibility(View.GONE);
            lytTransaction.setVisibility(View.GONE);
            Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), redeemSearchResultData.getBooking_Process_Label(), null);

        }
        else if(redeemSearchResultData.getBooking_Confirm_Page().equals("1"))
        {
            lytBookingSummary.setVisibility(View.VISIBLE);
            txtConfirmation.setText(redeemSearchResultData.getConfirmation_Number_Label() + " # " + redeemSearchResultData.getConfirmation_number());
            txtFlightPass.setText(redeemSearchResultData.getOptiontown_Flight_Pass_Label() + " : " + redeemSearchResultData.getOptiontown_Flight_Pass_ID());
            txtAirline.setText(redeemSearchResultData.getAirline_Label() + " : " + redeemSearchResultData.getAirline_Name());
            txtPNR.setText(redeemSearchResultData.getPNR_Airline_Label() + " : " + redeemSearchResultData.getPNR_Airline_Num());


            lytTransaction.setVisibility(View.VISIBLE);
            txtTransactionTitle.setText(redeemSearchResultData.getTransaction_History_Heading_Label());

            for (int index = 0; index < redeemSearchResultData.getTransaction_History_Detail().size(); index++)
            {
                View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_transaction_history_row, null, false);

                TransactionHistoryDetail historyDetail = redeemSearchResultData.getTransaction_History_Detail().get(index);

                OTTextView txtDateLabel = (OTTextView) v.findViewById(R.id.txtDateLabel);
                OTTextView txtDate = (OTTextView) v.findViewById(R.id.txtDate);
                OTTextView txtEventLabel = (OTTextView) v.findViewById(R.id.txtEventLabel);
                OTTextView txtEvent = (OTTextView) v.findViewById(R.id.txtEvent);
                OTTextView txtDescriptionLabel = (OTTextView) v.findViewById(R.id.txtDescriptionLabel);
                OTTextView txtDescription = (OTTextView) v.findViewById(R.id.txtDescription);
                OTTextView txtPaidLable = (OTTextView) v.findViewById(R.id.txtPaidLable);
                OTTextView txtPaid = (OTTextView) v.findViewById(R.id.txtPaid);
                OTTextView txtAccountLabel = (OTTextView) v.findViewById(R.id.txtAccountLabel);
                OTTextView txtAccount = (OTTextView) v.findViewById(R.id.txtAccount);

                txtDateLabel.setText(redeemSearchResultData.getTransaction_Date_Label());
                txtDate.setText(historyDetail.getTransactionDate());
                txtEventLabel.setText(redeemSearchResultData.getTransaction_History_Event_Description_Label());
                txtEvent.setText(historyDetail.getEventLabel());
                txtDescriptionLabel.setText(redeemSearchResultData.getTransaction_Event_Description_Label());
                txtDescription.setText(historyDetail.getTgpFgShortDescription());
                txtPaidLable.setText(redeemSearchResultData.getTransaction_History_Total_Amount_FPO_Label());
                txtPaid.setText(historyDetail.getPriceWithCurrency());
                txtAccountLabel.setText(redeemSearchResultData.getTansaction_History_Account_Heading_Label());
                txtAccount.setText(historyDetail.getAccountNumber());

                lytTransaction.addView(v);
            }

        }
        else if(redeemSearchResultData.getBooking_Purchase_Page().equals("1"))
        {
            lytBookingSummary.setVisibility(View.GONE);
            lytTransaction.setVisibility(View.GONE);
        }

        txtPassengersLabel.setText(redeemSearchResultData.getPassengers_Label());
        txtMessage.setText(redeemSearchResultData.getBooking_Process_Message());
        txtBookMoreFlights.setText(redeemSearchResultData.getBook_More_Label());

        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        RedeemSearchResultRecyclerViewAdapter adapter = new RedeemSearchResultRecyclerViewAdapter(this.getClass().getName() ,getActivity(), null, 0, redeemSearchResultData, new RedeemSearchResultRecyclerViewAdapter.IRecyclerViewHolderClicks() {

            @Override
            public void onClickRecyclerItemSelect(View v, List<Itinerarry> listItinary)
            {

            }

            @Override
            public void onClickRecyclerItemDetail(View v, Itinerarry itinary)
            {

            }
        });
        recyclerView.setAdapter(adapter);

        ArrayList<String> usersDisplayNameList = redeemSearchResultData.getUsersDisplayNameList();
        for (int index = 0; index < usersDisplayNameList.size(); index++) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_redeem_add_passenger_row, null, false);

            ImageView imgUserIcon = (ImageView) v.findViewById(R.id.imgUserIcon);
            imgUserIcon.setBackgroundResource(R.drawable.settings);
            OTTextView txtName = (OTTextView) v.findViewById(R.id.txtName);
            txtName.setText(usersDisplayNameList.get(index));
            ImageView imgCheck = (ImageView) v.findViewById(R.id.imgCheck);
            imgCheck.setVisibility(View.GONE);


            lytPassengersName.addView(v);
        }
    }
}

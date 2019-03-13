package com.optiontown.app.view.fragment.fpo.redeem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.optiontown.R;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.RedeemTransactionRecycleViewAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.ListOfPass;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by parasmani.sharma on 22/09/2016.
 */
public class RedeemTransactionDetails extends BaseFragment {

    private View view;
    private ListOfPass listOfPass;
    private LinearLayout parent_lay;
    private RecyclerView recyclerView;
    private RedeemTransactionRecycleViewAdapter mAdapter;
    private OTTextView txt_book_flights;
    private OTTextView txt_update_passenger;
    private InternationalizeData localization;
    private OTTextView txt_transaction_details;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fpo_redeem_transaction_details, container, false);
        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), new RedeemTransactionDetails().getClass().getName());

        listOfPass = ((ListOfPass) getArguments().getSerializable(getString(R.string.key_serializable)));

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        getUIReference();

        return view;

    }

    private void getUIReference() {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new RedeemTransactionRecycleViewAdapter(getActivity(),listOfPass);
        recyclerView.setAdapter(mAdapter);


        txt_book_flights = (OTTextView)view.findViewById(R.id.txt_book_flights);
        txt_update_passenger = (OTTextView)view.findViewById(R.id.txt_update_passenger);

        txt_book_flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.moveToFragment(getActivity(), new RedeemViewDetailsFragment(),listOfPass, 0);
            }
        });

        txt_update_passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Utils.moveToFragment(getActivity(), new RedeemTransactionDetails(), listOfPass, 0);

            }
        });

        txt_transaction_details = (OTTextView) view.findViewById(R.id.txt_transaction_details);

        localize();

    }

    private void localize() {

        Utils.DEBUG("View Details Label : "+localization.getLabl_View_Details_Labl());

        if (localization.getLabl_View_Details_Labl() == null) {
            txt_book_flights.setText(listOfPass.getPassSmallView().getLABLViewDetailsLabel());
        } else {
            txt_book_flights.setText(localization.getLabl_View_Details_Labl());
        }

        txt_transaction_details.setText(localization.getTransactionDetailsLabel());

    }


}

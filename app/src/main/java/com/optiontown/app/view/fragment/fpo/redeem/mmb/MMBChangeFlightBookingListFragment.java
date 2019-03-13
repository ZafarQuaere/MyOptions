package com.optiontown.app.view.fragment.fpo.redeem.mmb;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.optiontown.R;
import com.optiontown.app.adapter.MMBBookingListAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.mmb.MMBData;
import com.optiontown.app.model.redeem.mmb.PassBookingList;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 23/11/2016.
 */
public class MMBChangeFlightBookingListFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager recylerViewLayoutManager;
    private View view;
    private AppDialogLoader loader;
    private OTTextView txtContinue;
    private OTTextView txtflightpass;
    private CardView list_item;
    private String selectedPassId;
    private String selecteOCNNumber;
    private MMBData mmbData;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytError;
    private LinearLayout lytSelectFligthPass;
    private OTTextView label_select_flight;
    private  ArrayList<PassBookingList> bookingLists;
    private PassBookingList passBookingList;
    private OTTextView txtSelectedPass;
    private ImageView imgEdit;
    private InternationalizeData localization;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmp_select_pass_fragment, container, false);



        try {
            mmbData = (MMBData) getArguments().getSerializable(getString(R.string.key_serializable));
        }catch (Exception e){}

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(),mmbData.getChangeFlightHeadingLabel(),null);
        loader = AppDialogLoader.getLoader(getActivity());

        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);


        //selectedPassId = passSelected.getPassId();
        Utils.DEBUG("Mmb Data   "+mmbData);
        initUi(view);
        //callApi(selectedPassId);
       //updateUi(passSelected);

        return view;
    }




    private void initUi(View view) {

        lytSelectFligthPass = (LinearLayout) view.findViewById(R.id.lytSelectFligthPass);
        lytSelectFligthPass.setVisibility(View.GONE);

        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);
        //selecteOCNNumber = passSelected.getPassLabel().toString().replace("#", ":");

        for(int i=0; i<mmbData.getMapOfPassAndConfirmedBooking().size(); i++)
        {
            if(mmbData.getMapOfPassAndConfirmedBooking().get(i).getPassId().toString().equals(mmbData.getPassId()))
            {
                selecteOCNNumber =  mmbData.getMapOfPassAndConfirmedBooking().get(i).getPassLabel().toString().replace("#",":");
                txtSelectedPass.setText(selecteOCNNumber);
            }
        }

        label_select_flight = (OTTextView) view.findViewById(R.id.label_select_flight);

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        list_item = (CardView) view.findViewById(R.id.list_item);
        txtflightpass = (OTTextView) view.findViewById(R.id.txtflightpass);

        (view.findViewById(R.id.lytSelect)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_item.setVisibility(list_item.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.clearBackstackTillMMBSelectPassFragment(getActivity());
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recylerViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recylerViewLayoutManager);
        txtContinue = (OTTextView) view.findViewById(R.id.txtContinue);
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //first clear all previos error message view
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                ArrayList<String> listError = validateAllInputsCommon();
                if (listError.size() > 0) {
                    lytError.setVisibility(View.VISIBLE);
                    //show error message
                    for (int index = 0; index < listError.size(); index++) {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }
                } else {
                    Utils.moveToFragment(getActivity(), new MMBChangeFlightSelectFragment(), passBookingList, 0);


                }
            }
        });

        updateUi(mmbData);
    }

    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();

        if (Utils.compareDefaultValues(txtflightpass, mmbData.getBooking_Label()) || Utils.compareDefaultValues(txtflightpass, "")) {
            listError.add("Please select any booking");
        }

        return listError;
    }

    private void updateUi(MMBData mmbData) {

        lytSelectFligthPass.setVisibility(View.VISIBLE);
        if (mmbData != null) {
            Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(),mmbData.getChangeFlightHeadingLabel(),null);
            txtContinue.setText(mmbData.getContinueButtonLabel());
            label_select_flight.setText(localization.getLABL_Select_the_booking_for_flight_change_Label());
            label_select_flight.setTypeface(null, Typeface.BOLD);

            //final String msgSelect = "Booking";
            final String msgSelect = mmbData.getBooking_Label();
            txtflightpass.setText(msgSelect);
            for (int i =0; i<mmbData.getMapOfPassAndConfirmedBooking().size();i++){

                if (mmbData.getPassId().matches(mmbData.getMapOfPassAndConfirmedBooking().get(i).getPassId())) {
                    bookingLists =mmbData.getMapOfPassAndConfirmedBooking().get(i).getPassBookingList();
                    MMBBookingListAdapter adapter = new MMBBookingListAdapter(getActivity(), bookingLists, new MMBBookingListAdapter.RecyclerViewHolderClicks() {
                        @Override
                        public void onClickRecyclerItemDetail(View v, PassBookingList flightDataSelected) {
                            list_item.setVisibility(View.GONE);
                            passBookingList = flightDataSelected;

                            if (flightDataSelected != null) {
                                passBookingList.setOCNNumber(selecteOCNNumber);
                                txtflightpass.setText(flightDataSelected.getFlight_PNR_Label());
                            } else {
                                txtflightpass.setText(msgSelect);
                            }


                        }
                    });
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);
                }
            }
            loader.dismiss();
        }
    }




}

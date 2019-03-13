package com.optiontown.app.view.fragment.fpo.redeem.mybookings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.optiontown.R;
import com.optiontown.app.adapter.MyBookingViewDetailRecyclerViewAdapter;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.RedeemSearchResultRecyclerViewAdapter;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.LegList;
import com.optiontown.app.model.redeem.mybooking.ConfirmedBooking;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemSearchResultDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parasmani.sharma on 19/10/2016.
 */
public class MyBookingViewDetailsFragment extends BaseFragment {

    private View view;
    private OTTextView txt_label_bookingheading;
    private OTTextView txt_airportCity;
    private OTTextView txt_processing;
    private OTTextView txt_date;
    private OTTextView txt_ocn_label;
    private OTTextView txt_ocn_number;
    private OTTextView txt_pnr_label;
    private OTTextView txt_vistara_pnr_number;
    private OTTextView txt_optionotown_fp_label;
    private OTTextView txt_fp_number;
    private OTTextView txt_book_request_date_label;
    private OTTextView txt_request_date;
    private OTTextView txt_from_label;
    private OTTextView txt_from;
    private OTTextView txt_to_label;
    private OTTextView txt_to;
    private OTTextView txt_selected_pasenger_label;
    private OTTextView txt_selected_pasenger;
    private OTTextView txt_cabin_label;
    private OTTextView txt_cabin;
    private OTTextView txt_airline_label;
    private OTTextView txt_airline_name;
    private int viewBookingType;
    private ConfirmedBooking bookingData;
    private LinearLayout lay_passengers;
    private RecyclerView recyclerView;
    private OTTextView txt_close_button;
    private LinearLayout lay_return;
    private LinearLayout lay_ocn;
    private LinearLayout lay_pnr;
    private LinearLayout lay_cabin;
    private LinearLayout lay_passenger;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_redeem_mybooking_viewdetails, container, false);

        bookingData = ((ConfirmedBooking) getArguments().getSerializable(getString(R.string.key_serializable)));

        try {

            Utils.viewDetailsNameDynamic = bookingData.getConfirmedBookingLabel();
            Utils.updateActionBarForFeatures(getActivity(),new MyBookingViewDetailsFragment().getClass().getName());

        }catch (Exception e)
        {

        }



        getUiReference();
        updateUI();
        return view;
    }

    private void getUiReference() {

        txt_close_button = (OTTextView) view.findViewById(R.id.txt_close_button);
        txt_close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back
                ((MainActivity)getActivity()).onBackPressed();
            }
        });
        lay_passengers = (LinearLayout) view.findViewById(R.id.lay_passengers);
        txt_label_bookingheading = (OTTextView) view.findViewById(R.id.txt_label_bookingheading);
        txt_airportCity = (OTTextView) view.findViewById(R.id.txt_airportCity);
        txt_processing = (OTTextView) view.findViewById(R.id.txt_processing);
        txt_date = (OTTextView) view.findViewById(R.id.txt_date);
        txt_ocn_label = (OTTextView) view.findViewById(R.id.txt_ocn_label);
        txt_ocn_number = (OTTextView) view.findViewById(R.id.txt_ocn_number);
        txt_pnr_label = (OTTextView) view.findViewById(R.id.txt_pnr_label);
        txt_vistara_pnr_number = (OTTextView) view.findViewById(R.id.txt_vistara_pnr_number);
        txt_optionotown_fp_label = (OTTextView) view.findViewById(R.id.txt_optionotown_fp_label);
        txt_fp_number = (OTTextView) view.findViewById(R.id.txt_fp_number);
        txt_airline_label = (OTTextView) view.findViewById(R.id.txt_airline_label);
        txt_airline_name = (OTTextView) view.findViewById(R.id.txt_airline_name);
        txt_book_request_date_label = (OTTextView) view.findViewById(R.id.txt_book_request_date_label);
        txt_request_date = (OTTextView) view.findViewById(R.id.txt_request_date);
        txt_from_label = (OTTextView) view.findViewById(R.id.txt_from_label);
        txt_from = (OTTextView) view.findViewById(R.id.txt_from);
        txt_to_label = (OTTextView) view.findViewById(R.id.txt_to_label);
        txt_to = (OTTextView) view.findViewById(R.id.txt_to);
        txt_selected_pasenger_label = (OTTextView) view.findViewById(R.id.txt_selected_pasenger_label);
       // txt_selected_pasenger = (OTTextView) view.findViewById(R.id.txt_selected_pasenger);
        txt_cabin_label = (OTTextView) view.findViewById(R.id.txt_cabin_label);
        txt_cabin = (OTTextView) view.findViewById(R.id.txt_cabin);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        lay_return = (LinearLayout) view.findViewById(R.id.lay_return);
        lay_ocn = (LinearLayout) view.findViewById(R.id.lay_ocn);
        lay_pnr = (LinearLayout) view.findViewById(R.id.lay_pnr);
        lay_cabin = (LinearLayout) view.findViewById(R.id.lay_cabin);
        lay_passenger = (LinearLayout) view.findViewById(R.id.lay_passenger);




    }

    private void updateUI()
    {


        txt_label_bookingheading.setText(bookingData.getConfirmedBookingLabel());
        txt_airportCity.setText(bookingData.getFlightDepartureAirportDisplayName()+" "+"("+ bookingData.getFlightDepartureAirportCode()+")");
        txt_processing.setText(bookingData.getLABLFPoBookingConfirmedLabel());
        txt_date.setText(bookingData.getFlightDateDeparture()+"");
        txt_ocn_label.setText(bookingData.getLABLOCNShortLabel());
        txt_ocn_number.setText(bookingData.getTgpPaxBookingConfirmationNumber()+"");
        txt_pnr_label.setText(bookingData.getLABLPNRLabel());
        txt_vistara_pnr_number.setText(bookingData.getBookingPnrNumber()+"");
        txt_airline_label.setText(bookingData.getAirlineLabel());
        txt_airline_name.setText(bookingData.getAirlineName());
        txt_optionotown_fp_label.setText(bookingData.getLABLOptiontownPassIdLabel());
        txt_fp_number.setText(bookingData.getPassTransId()+"");
        txt_book_request_date_label.setText(bookingData.getBookingRequestDateLabel());
        txt_request_date.setText("Empty");
        txt_from_label.setText(bookingData.getOutboundLabel());
        txt_to_label.setText(bookingData.getReturnLabel());
        txt_from.setText(bookingData.getLABLSELECTEDZONE() + " " + bookingData.getFlightDateDeparture());
        txt_cabin_label.setText(bookingData.getLABLCabinLabel());
        txt_cabin.setText(bookingData.getFlightCabinData());

        txt_selected_pasenger_label.setText(bookingData.getLABLPassengerLabel());





        try {

            if (!bookingData.getItinerarry().isEmpty()) {

                txt_label_bookingheading.setVisibility(View.VISIBLE);

                txt_close_button.setText(bookingData.getItinerarry().get(0).getSegments().get(0).getLegList().get(0).getFlightSmallView().getCloseLabel());
                ArrayList<LegList> legList = bookingData.getItinerarry().get(0).getSegments().get(0).getLegList();
                txt_from.setText(legList.get(0).getFlightFullView().getDeptAirportCode() + " - " + legList.get(0).getFlightFullView().getArrivalAirportCode() + "  "
                        + bookingData.getItinerarry().get(0).getSegments().get(0).getLegList().get(0).getFlightFullView().getDepartAirlineDate() + "");



                if (bookingData.getItinerarry().get(0).getSegments().get(0).getLegList().get(0).getFlightSmallView().isDisplayReturn_Flag() == true) {
                    lay_return.setVisibility(View.VISIBLE);
                } else {

                    lay_return.setVisibility(View.GONE);
                }
            }


            ArrayList e = new ArrayList();
            for(int i = 0 ; i<bookingData.getPassengers().size(); i++)
            {
                e.add(bookingData.getPassengers().get(i).getNumberOfSelectedPassengers());
            }
            Utils.layLoopforpassenger(getActivity(),lay_passengers, e);
            // recycler view
            NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);



            MyBookingViewDetailRecyclerViewAdapter adapter = new MyBookingViewDetailRecyclerViewAdapter(getActivity(), bookingData.getItinerarry(), new MyBookingViewDetailRecyclerViewAdapter.IRecyclerViewHolderClicks(){

                @Override
                public void onClickRecyclerItemDetail(View v, Itinerarry itinary)
                {
                    if(bookingData.isDisplayReturn_Flag())
                    {
                        ArrayList<LegList> legList0 = itinary.getSegments().get(0).getLegList();
                        ArrayList<LegList> legList1 = itinary.getSegments().get(1).getLegList();
                        itinary.setDepartArr(legList0.get(0).getFlightFullView().getDeptAirportCode()
                                + " - " + legList0.get(legList0.size()-1).getFlightFullView().getArrivalAirportCode()
                        + "/"
                                + legList1.get(0).getFlightFullView().getDeptAirportCode()
                                + " - " + legList1.get(legList1.size()-1).getFlightFullView().getArrivalAirportCode());
                    }
                    else
                    {
                        ArrayList<LegList> legList = itinary.getSegments().get(0).getLegList();
                        itinary.setDepartArr(legList.get(0).getFlightFullView().getDeptAirportCode()
                                + " - " + legList.get(legList.size()-1).getFlightFullView().getArrivalAirportCode());
                    }

                    itinary.setPer_Person(" ");
                    itinary.setFromMyBooking(true);
                    Utils.moveToFragment(getActivity(), new RedeemSearchResultDetailFragment(), itinary, 0);
                }
            });
            recyclerView.setAdapter(adapter);


        }catch (Exception e)
        {

        }


        if(bookingData.getBookingType() == 0)
        {
            lay_return.setVisibility(View.VISIBLE);
            lay_ocn.setVisibility(View.VISIBLE);
            lay_pnr.setVisibility(View.VISIBLE);
            lay_cabin.setVisibility(View.VISIBLE);
            lay_passenger.setVisibility(View.VISIBLE);

        }else {

            lay_return.setVisibility(View.GONE);
            lay_ocn.setVisibility(View.GONE);
            lay_pnr.setVisibility(View.GONE);
            lay_cabin.setVisibility(View.GONE);
            lay_passenger.setVisibility(View.GONE);

        }


        if(bookingData.isDisplayReturn_Flag())
        {
            ArrayList<LegList> legList = bookingData.getItinerarry().get(0).getSegments().get(1).getLegList();
            txt_to.setText(legList.get(0).getFlightFullView().getDeptAirportCode() + " - " + legList.get(0).getFlightFullView().getArrivalAirportCode() +
                    "  " + legList.get(0).getFlightFullView().getDepartAirlineDate() + "");
            lay_return.setVisibility(View.VISIBLE);
        }else {
            lay_return.setVisibility(View.GONE);
        }




    }


}

package com.optiontown.app.view.fragment.fpo.redeem;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.redeem.FlightFullView;
import com.optiontown.app.model.redeem.FlightSmallView;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.LegList;
import com.optiontown.app.model.redeem.Segment;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

/**
 * Created by amit on 09-09-2016.
 */
public class RedeemSearchResultDetailFragment extends BaseFragment
{
    //private FlightSearchData flightSearchData;
    private View view;
    private OTTextView txtTravelPeriod;
    private OTTextView txtPassengerCabinType;
    private TextView txtInformation;
    private OTTextView txtDA;
    private OTTextView txtDate;
    private OTTextView txtTime;
    private OTTextView txtAirlineName;

    private OTTextView txtOperation;
    private OTTextView txtDepartTime;
    private OTTextView txtTravelTime;
    private OTTextView txtArrivalTime;
    private OTTextView txtDepartAirport;
    private OTTextView txtOvernight;
    private OTTextView txtArriveAirport;
    private OTTextView txtCabin;
    private OTTextView txtAirbus;
    private OTTextView txtDepartAbbreviation;
    private OTTextView txtDepartFull;
    private OTTextView txtArrivalAbbreviation;
    private OTTextView txtArrivalFull;
    private OTTextView txtLayoverTime;
    private NetworkImageView imgAirlineLogo;
    private OTTextView txtSelect;
    private Itinerarry itinerarry;
    private Communicator communicator;
    private FragmentActivity fragmentActivity;
    private LinearLayout lytPageContainer;
    private LinearLayout lytTitle;
    private ImageView imgEditSearch;
    private RelativeLayout lytDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_redeem_search_result_detail, container, false);

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "", null);
        itinerarry = ((Itinerarry) getArguments().getSerializable(getString(R.string.key_serializable)));

        Utils.DEBUG(ParseManager.getInstance().toJSON(itinerarry));

        getUIReference(view);

        updateUI();


        return view;
    }

    private void updateUI()
    {
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), itinerarry.getDepartArr(), null);
        try
        {
            String[] split = itinerarry.getPer_Person().split("##");
            if(split[0].trim().equals("INR"))
            {
                txtInformation.setText(getString(R.string.string_rs) + " "+ split[1]);
            }
            else
            {
                txtInformation.setText(split[0] + split[1]);
            }
        }
        catch (Exception e)
        {
            Utils.ERROR("Error while splitting String : " + e.toString());
        }
        if(itinerarry.isFromMyBooking())
        {
            lytDetail.setVisibility(View.GONE);
            txtSelect.setVisibility(View.GONE);
            txtInformation.setVisibility(View.GONE);
        }else {

            txtInformation.setVisibility(View.VISIBLE);
            txtTravelPeriod.setText(Utils.getMonthDateOfTravel(itinerarry.getTravelDate()));

            txtPassengerCabinType.setText(itinerarry.getPassengerCabinTripType());

        }


        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        for (int index = 0; index < itinerarry.getSegments().size(); index++)
        {
            Segment segment = itinerarry.getSegments().get(index);

            FlightSmallView flightSmallViewFirst = segment.getLegList().get(0).getFlightSmallView();
            FlightFullView flightFullViewFirst = segment.getLegList().get(0).getFlightFullView();
            FlightSmallView flightSmallViewLast = segment.getLegList().get(segment.getLegList().size() - 1).getFlightSmallView();


            for (int i = 0; i < segment.getLegList().size(); i++)
            {
                LegList leg = segment.getLegList().get(i);
                View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_redeem_flight_detail_page, null, false);
                lytTitle = (LinearLayout) v.findViewById(R.id.lytTitle);
                txtDA = (OTTextView) v.findViewById(R.id.txtDA);
                txtDate = (OTTextView) v.findViewById(R.id.txtDate);
                txtTime = (OTTextView) v.findViewById(R.id.txtTime);
                imgAirlineLogo = (NetworkImageView) v.findViewById(R.id.imgAirlineLogo);
                txtAirlineName = (OTTextView) v.findViewById(R.id.txtAirlineName);
                txtOperation = (OTTextView) v.findViewById(R.id.txtOperation);
                txtDepartTime = (OTTextView) v.findViewById(R.id.txtDepartTime);
                txtTravelTime = (OTTextView) v.findViewById(R.id.txtTravelTime);
                txtArrivalTime = (OTTextView) v.findViewById(R.id.txtArrivalTime);
                txtDepartAirport = (OTTextView) v.findViewById(R.id.txtDepartAirport);
                txtOvernight = (OTTextView) v.findViewById(R.id.txtOvernight);
                txtArriveAirport = (OTTextView) v.findViewById(R.id.txtArriveAirport);
                txtCabin = (OTTextView) v.findViewById(R.id.txtCabin);
                final LinearLayout lytAbbreviation = (LinearLayout) v.findViewById(R.id.lytAbbreviation);
                ImageView imgExpand = (ImageView) v.findViewById(R.id.imgExpand);
                imgExpand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lytAbbreviation.setVisibility(lytAbbreviation.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                        ((ImageView) v).setImageResource(lytAbbreviation.getVisibility() == View.GONE ? R.drawable.plus : R.drawable.minus);
                    }
                });

                txtAirbus = (OTTextView) v.findViewById(R.id.txtAirbus);
                txtDepartAbbreviation = (OTTextView) v.findViewById(R.id.txtDepartAbbreviation);
                txtDepartFull = (OTTextView) v.findViewById(R.id.txtDepartFull);
                txtArrivalAbbreviation = (OTTextView) v.findViewById(R.id.txtArrivalAbbreviation);
                txtArrivalFull = (OTTextView) v.findViewById(R.id.txtArrivalFull);
                txtLayoverTime = (OTTextView) v.findViewById(R.id.txtLayoverTime);


                lytTitle.setVisibility(i == 0 ? View.VISIBLE : View.GONE);
                if(i == 0)
                {
                    txtDA.setText(flightSmallViewFirst.getDepartAirlineCode() + " > " + flightSmallViewLast.getArrivalAirlineCode());
                    txtDate.setText(flightFullViewFirst.getDepartAirlineDate());
                    int flightDuration = 0;
                    for (int pos = 0; pos < segment.getLegList().size(); pos++)
                    {
                        flightDuration = flightDuration + segment.getLegList().get(pos).getFlightSmallView().getFlight_Duration_Min();
                    }
                    txtTime.setText((flightDuration/60 == 0 ? "" : flightDuration/60 + "h") + " " + flightDuration % 60 + "m");

                }


                imgAirlineLogo.setImageUrl(leg.getFlightSmallView().getAirlineLogo(), imageLoader);
                txtAirlineName.setText(leg.getFlightFullView().getDisplayName());
                txtOperation.setText(leg.getFlightSmallView().getFooterLabel());
                txtOperation.setVisibility(leg.getFlightSmallView().getFooterLabel().equals("") ? View.GONE : View.VISIBLE);
                txtDepartTime.setText(leg.getFlightFullView().getDeptFormattedTime());
                txtTravelTime.setText(leg.getFlightSmallView().getFlightDuration());
                txtArrivalTime.setText(leg.getFlightFullView().getArrivalAirlineTime());
                txtDepartAirport.setText(leg.getFlightFullView().getDeptAirportCode());
                txtOvernight.setText(leg.getFlightFullView().getOverNight());
                txtArriveAirport.setText(leg.getFlightFullView().getArrivalAirportCode());
                txtCabin.setText(leg.getFlightFullView().getAirbusLabel().split("\\|")[0]);
                txtAirbus.setText(leg.getFlightFullView().getAirbusLabel().split("\\|")[1]);
                txtDepartAbbreviation.setText(leg.getFlightFullView().getDeptAirportCode());
                txtDepartFull.setText(leg.getFlightFullView().getDepart_Airport_Display_Name());
                txtArrivalAbbreviation.setText(leg.getFlightFullView().getArrivalAirportCode());
                txtArrivalFull.setText(leg.getFlightFullView().getArrival_Airport_Display_Name());
                txtLayoverTime.setText(leg.getFlightFullView().getLayOverTime());
                txtSelect.setText(itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getSelectButtonLabel());


                //


                lytPageContainer.addView(v);
            }
        }
    }

    private void getUIReference(View v)
    {
        lytDetail = (RelativeLayout) v.findViewById(R.id.lytDetail);
        txtTravelPeriod = (OTTextView) v.findViewById(R.id.txtTravelPeriod);
        txtPassengerCabinType = (OTTextView) v.findViewById(R.id.txtPassengerCabinType);
        txtInformation = (TextView) v.findViewById(R.id.txtInformation);

        lytPageContainer = (LinearLayout) v.findViewById(R.id.lytPageContainer);
        txtSelect = (OTTextView) v.findViewById(R.id.txtSelect);
        txtSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentCommunicationData data = new FragmentCommunicationData();
                data.setFragmentName(new RedeemSearchResultFragment().getClass().getName());
                data.setItinerarry(itinerarry);
                communicator.onResponse(data);

                fragmentActivity.onBackPressed();
            }
        });

        imgEditSearch = (ImageView) view.findViewById(R.id.imgEditSearch);
        imgEditSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((FragmentActivity)getActivity()).onBackPressed();
                ((FragmentActivity)getActivity()).onBackPressed();
                communicateToFragment(true);
            }
        });

    }


    private void communicateToFragment(boolean isModifyPassData)
    {
        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFragmentName((new SearchFlightInputFragment()).getClass().getName());
        data.setRedeemModifyPassDetails(isModifyPassData);
        communicator.onResponse(data);
    }

    @Override
    public void onAttach(Activity activity) {

        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }


}

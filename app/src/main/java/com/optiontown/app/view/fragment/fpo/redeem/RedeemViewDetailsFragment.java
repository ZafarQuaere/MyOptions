package com.optiontown.app.view.fragment.fpo.redeem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.ListOfPass;
import com.optiontown.app.model.redeem.SelectedPassDataForSearchFlight;
import com.optiontown.app.model.review.FeatureDetail;
import com.optiontown.app.model.review.OdListWithDate;
import com.optiontown.app.model.review.Odlist;
import com.optiontown.app.model.review.RestrictValue;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.review.FPoZoneDetailFragment;
import com.optiontown.app.view.fragment.passes.PNRSearchInputFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 19/09/2016.
 */
public class RedeemViewDetailsFragment extends BaseFragment{

    private View view;
    private OTTextView txt_label_op_confirm;
    private OTTextView txt_del_mum_goa;
    private TextView txt_travel_valid_story;
    private OTTextView txt_label_cabin;
    private OTTextView txt_cabin;
    private OTTextView txt_label_number_of_flights;
    private OTTextView txt_number_of_flights;
    private OTTextView txt_label_available_flights;
    private OTTextView txt_available_flights;
    private OTTextView txt_label_flights;
    private OTTextView txt_label_used_flights;
    private OTTextView txt_used_flights;
    private OTTextView txt_labelflights;
    private OTTextView txt_label_total_flights;
    private OTTextView txt_total_flights;
    private OTTextView txt_label_flight;
    private OTTextView txt_label_travelperiod;
    private OTTextView txt_travel_date_start;
    private OTTextView txt_travel_range;
    private OTTextView txt_label_advbooking;
    private OTTextView txt_advance_book_start_date;
    private OTTextView txt_advance_book_storyline;
    private OTTextView txt_label_travelflex;
    private OTTextView txt_day_flexibility;
    private OTTextView txt_flexibility_storyline;
    private OTTextView txt_label_passenger;
    private OTTextView txt_passenger;
    private OTTextView txt_passenger_name;
    private OTTextView txt_label_inprocess;
    private OTTextView txt_in_process;
    private OTTextView txt_label_flights_inprocess;
  //  private OTTextView txt_readmore_popup;
    private LinearLayout lay_list_passenger;
    private LinearLayout lay_travel_flexibility;
    private LinearLayout lay_advance_booking;
    private LinearLayout lytRestrictedValues;
    private OTTextView txt_label_travelzone;
    private ListOfPass listOfPass;
    private OTTextView txt_book_flights;
    private OTTextView txt_transaction_details;
    private InternationalizeData localization;
    private boolean isActivePass;
    private int DEFAULT_SHOW_FM_VALUES = 5;
    private LinearLayout lay_cabin;
    private OTTextView txt_airline;
    private OTTextView txt_label_airline;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fpo_redeem_view_details, container, false);

        listOfPass = ((ListOfPass) getArguments().getSerializable(getString(R.string.key_serializable)));

        try {
            isActivePass = ((int) getArguments().getSerializable(getString(R.string.key_view_type))) == 0 ? true : false;
        } catch (Exception e) {
            //e.printStackTrace();
        }

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), new RedeemViewDetailsFragment().getClass().getName(), listOfPass.getPassSmallView().getTravelZoneTitle(),null);


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

        txt_book_flights = (OTTextView)view.findViewById(R.id.txt_book_flights);
        txt_transaction_details = (OTTextView)view.findViewById(R.id.txt_transaction_details);

        txt_label_op_confirm = (OTTextView)view.findViewById(R.id.txt_label_op_confirm);

        txt_airline = (OTTextView)view.findViewById(R.id.txt_airline);
        txt_label_airline = (OTTextView)view.findViewById(R.id.txt_label_airline);

        txt_label_travelzone = (OTTextView)view.findViewById(R.id.txt_label_travelzone);
        txt_del_mum_goa = (OTTextView)view.findViewById(R.id.txt_del_mum_goa);
        txt_travel_valid_story = (TextView)view.findViewById(R.id.txt_travel_valid_story);

        txt_label_cabin = (OTTextView)view.findViewById(R.id.txt_label_cabin);
        txt_cabin = (OTTextView)view.findViewById(R.id.txt_cabin);

        txt_label_number_of_flights = (OTTextView)view.findViewById(R.id.txt_label_number_of_flights);
        txt_number_of_flights = (OTTextView)view.findViewById(R.id.txt_number_of_flights);
        txt_label_available_flights = (OTTextView)view.findViewById(R.id.txt_label_available_flights);
        txt_available_flights = (OTTextView)view.findViewById(R.id.txt_available_flights);
        txt_label_flights = (OTTextView)view.findViewById(R.id.txt_label_flights);
        txt_label_used_flights = (OTTextView)view.findViewById(R.id.txt_label_used_flights);
        txt_used_flights = (OTTextView)view.findViewById(R.id.txt_used_flights);
        txt_labelflights = (OTTextView)view.findViewById(R.id.txt_flights);
        txt_label_inprocess = (OTTextView)view.findViewById(R.id.txt_label_inprocess);
        txt_in_process = (OTTextView)view.findViewById(R.id.txt_in_process);
        txt_label_flights_inprocess = (OTTextView)view.findViewById(R.id.txt_label_flights_inprocess);
        txt_label_total_flights = (OTTextView)view.findViewById(R.id.txt_label_total_flights);
        txt_total_flights = (OTTextView)view.findViewById(R.id.txt_total_flights);
        txt_label_flight = (OTTextView)view.findViewById(R.id.txt_label_flight);

        txt_label_travelperiod = (OTTextView)view.findViewById(R.id.txt_label_travelperiod);
        txt_travel_date_start = (OTTextView)view.findViewById(R.id.txt_travel_date_start);
        txt_travel_range = (OTTextView)view.findViewById(R.id.txt_travel_range);

        txt_label_advbooking = (OTTextView)view.findViewById(R.id.txt_label_advbooking);
        txt_advance_book_start_date = (OTTextView)view.findViewById(R.id.txt_advance_book_start_date);
        txt_advance_book_storyline = (OTTextView)view.findViewById(R.id.txt_advance_book_storyline);

        txt_label_travelflex = (OTTextView)view.findViewById(R.id.txt_label_travelflex);
        txt_day_flexibility = (OTTextView)view.findViewById(R.id.txt_day_flexibility);
        txt_flexibility_storyline = (OTTextView)view.findViewById(R.id.txt_flexibility_storyline);

        txt_label_passenger = (OTTextView)view.findViewById(R.id.txt_label_passenger);
        txt_passenger = (OTTextView)view.findViewById(R.id.txt_passenger);
       //txt_passenger_name = (OTTextView)view.findViewById(R.id.txt_passenger_name);

     //   txt_readmore_popup = (OTTextView)view.findViewById(R.id.txt_readmore_popup);

        lay_list_passenger = (LinearLayout)view.findViewById(R.id.lay_list_passenger);

        lay_travel_flexibility = (LinearLayout)view.findViewById(R.id.lay_travel_flexibility);
        lay_advance_booking = (LinearLayout)view.findViewById(R.id.lay_advance_booking);

        lytRestrictedValues = (LinearLayout)view.findViewById(R.id.lytRestrictedValues);

        lay_cabin = (LinearLayout) view.findViewById(R.id.lay_cabin);


        updateUI();

    }

    private void updateUI() {

        txt_book_flights.setText(listOfPass.getPassSmallView().getBookFlightLabel());
        txt_book_flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isActivePass) {
                    if ((Utils.getCurrentProductId(getActivity()) == getActivity().getResources().getInteger(R.integer.value_tgProductId_fpo))) {
                        Utils.moveToFragment(getActivity(), new SearchFlightInputFragment(), listOfPass.getPassFullView().getFlightPassListId(), 0);
                    }
                    else {
                        Utils.moveToFragment(getActivity(), new PNRSearchInputFragment(), listOfPass, 0);
                    }
                }
            }
        });

        txt_transaction_details.setText(localization.getTransactionDetailsLabel());
        txt_transaction_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.moveToFragment(getActivity(), new RedeemTransactionDetails(), listOfPass, 0);

            }
        });

        txt_label_op_confirm.setText(listOfPass.getPassSmallView().getLABLOptiontownCNLabel() + " " + listOfPass.getPassSmallView().getConfirmationNumber()+"");

        txt_label_airline.setText(listOfPass.getPassSmallView().getLABLAirlineLabel());
        txt_airline.setText(listOfPass.getPassSmallView().getAirlineName());

        txt_label_travelzone.setText(listOfPass.getPassFullView().getTravelZoneLabel());
        txt_del_mum_goa.setText(listOfPass.getPassSmallView().getTravelZoneTitle());

        setZoneDetail();

        addRestrictValueInfo(lytRestrictedValues);

        txt_label_cabin.setText(listOfPass.getPassFullView().getCreditTypeTitle());
        txt_cabin.setText(listOfPass.getPassSmallView().getUpCabinName());

        txt_label_number_of_flights.setText(listOfPass.getPassFullView().getCreditCountLabel());
        txt_number_of_flights.setText(listOfPass.getPassSmallView().getAvailableFlightsNumber()+"");
        txt_label_available_flights.setText(listOfPass.getPassFullView().getAvailableFlightsLabel());
        txt_available_flights.setText(listOfPass.getPassFullView().getAvailableFlightsNumber()+"");
        txt_label_flights.setText(listOfPass.getTransactionHistory().getTransdetail().get(0).getLABLFlightsLabel());
        txt_label_used_flights.setText(listOfPass.getPassFullView().getUsedFlightsLabel());
        txt_used_flights.setText(listOfPass.getPassFullView().getUsedFlightsNumber()+"");
        txt_labelflights.setText(listOfPass.getTransactionHistory().getTransdetail().get(0).getLABLFlightsLabel());
        txt_label_inprocess.setText(listOfPass.getPassFullView().getInProcessLabel());
        txt_in_process.setText(listOfPass.getPassFullView().getInProcessFlightNumber()+"");
        txt_label_flights_inprocess.setText(listOfPass.getTransactionHistory().getTransdetail().get(0).getLABLFlightsLabel());
        txt_label_total_flights.setText("Total");
        txt_total_flights.setText(listOfPass.getPassFullView().getTotalFlightNumber()+"");
        txt_label_flight.setText(listOfPass.getTransactionHistory().getTransdetail().get(0).getLABLFlightsLabel());

        txt_label_travelperiod.setText(listOfPass.getPassSmallView().getValidityPeriodLabel());
        txt_travel_date_start.setText(listOfPass.getPassSmallView().getPassValidityValue()+""+" "+listOfPass.getPassSmallView().getTimeUnitNamePlural()+" "+ "from " + listOfPass.getPassFullView().getValidityStartDate()+"");
        txt_travel_range.setText(listOfPass.getPassFullView().getValidityStartDate()+" "+"to " + listOfPass.getPassFullView().getValidityEndDate());


        if (!(Utils.getCurrentProductId(getActivity()) == getActivity().getResources().getInteger(R.integer.value_tgProductId_fpo))){
            lay_advance_booking.setVisibility(View.GONE);
            lay_travel_flexibility.setVisibility(View.GONE);

        }
        else {
            txt_label_advbooking.setText(listOfPass.getPassFullView().getAdvanceBookingLabel());
            txt_advance_book_start_date.setText(listOfPass.getPassSmallView().getLABLBookPassReviewImageLabel());
            txt_advance_book_storyline.setText(getCorrectText(listOfPass.getPassFullView().getAdvanceBookingDays(), listOfPass.getPassFullView().getAdvanceNumberBookingDays()));

            txt_label_travelflex.setText(listOfPass.getPassFullView().getFlexibilityRangeLabel());
            txt_day_flexibility.setText(listOfPass.getPassSmallView().getFlexibilityRangeValue()+""+" "+ "Day Flexibility");
            txt_flexibility_storyline.setText(listOfPass.getPassFullView().getFlexibilityRangeValue());

           if(Utils.getCurrentProductId(getActivity()) == getActivity().getResources().getInteger(R.integer.value_tgProductId_fpo)){
               try {
                   if(listOfPass.getPassFullView().getFlexibilityRangeLabel().equals("") || listOfPass.getPassFullView().getFlexibilityRangeLabel() == null)
                   {
                       lay_travel_flexibility.setVisibility(View.GONE);
                   }
                   else {
                       lay_travel_flexibility.setVisibility(View.VISIBLE);
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
            }

        }


        txt_label_passenger.setText(listOfPass.getPassFullView().getPassUserLabel());
        int numberofPassenger = listOfPass.getPassFullView().getPassengerNameArray().size();
        //txt_passenger.setText(numberofPassenger+""+" "+listOfPass.getPassFullView().getPassUserLabel());
        txt_passenger.setText(listOfPass.getPassFullView().getUserTip());
        //txt_passenger_name.setText(listOfPass);

        ArrayList<Object> allPassenger = new ArrayList<>();
        for(int j = 0 ; j < listOfPass.getPassFullView().getPassengerNameArray().size(); j++)
        {
            allPassenger.add(listOfPass.getPassFullView().getPassengerNameArray().get(j));
        }
        Utils.layLoopforpassenger(getActivity(),lay_list_passenger,allPassenger);

        addPlusUserMessage(getActivity(),lay_list_passenger,listOfPass);

       /* txt_readmore_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.showPopUp(getActivity(), listOfPass.getPassFullView().getTravelZoneTips(), listOfPass.getPassSmallView().getTravelZoneTitle());

            }
        });*/

        txt_book_flights.getBackground().setAlpha(!isActivePass ? 128 : 255);

        lay_cabin.setVisibility(listOfPass.getPassSmallView().getCabinShow() == 0 ? View.GONE : View.VISIBLE);

    }

    private String getCorrectText(String advanceBookingDays, String advanceNumberBookingDays) {

        if(advanceBookingDays == null || advanceNumberBookingDays == null)
        {
            return "";
        }
        int indexFirst = advanceBookingDays.indexOf(advanceNumberBookingDays);
        return advanceBookingDays.substring(indexFirst + advanceNumberBookingDays.length());
    }

    private void setZoneDetail() {

        /*if(Utils.getCurrentProductId(getActivity()) == getActivity().getResources().getInteger(R.integer.value_tgProductId_fpo))
        {*/
            if(listOfPass.getZonefeatureData() != null)
            {
                if(listOfPass.getZonefeatureData().getDepArrvZoneArray().isEmpty())
                {
                    txt_travel_valid_story.setVisibility(View.GONE);
                }
                else
                {
                    txt_travel_valid_story.setVisibility(View.VISIBLE);
                }
            }
            else
            {
                txt_travel_valid_story.setVisibility(View.GONE);
            }
            txt_travel_valid_story.setTextColor(getResources().getColor(R.color.color_font_blue));
            txt_travel_valid_story.setTextSize(Utils.convertPixelToDp(getContext(), getResources().getDimension(R.dimen.size_font_10)));
            txt_travel_valid_story.setText(listOfPass.getPassSmallView().getLABLViewDetailsLabel());
            txt_travel_valid_story.setPaintFlags(txt_travel_valid_story.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            txt_travel_valid_story.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.moveToFragment(getActivity(), new FPoZoneDetailFragment(), listOfPass.getZonefeatureData(), 0);
                }
            });

        /*}
        else
        {
            String s = listOfPass.getPassFullView().getTravelZoneTips()+" "+" ";
            s = s.substring(0, s.length() > 80 ? 80 : s.length()) + "... "+ getString(R.string.string_read_more);
            SpannableString ss = new SpannableString(s);
            String readMore = getString(R.string.string_read_more);
            int readMoreIndex = s.toString().indexOf(readMore);

            //ss.setSpan(readMoreClick,readMoreIndex, readMoreIndex+readMore.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new myClickableSpan(1),readMoreIndex, readMoreIndex+readMore.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            txt_travel_valid_story.setMovementMethod(LinkMovementMethod.getInstance());
            txt_travel_valid_story.setText(ss);
        }*/
    }

    private void addRestrictValueInfo(LinearLayout lytRestrictedValues) {
        if(listOfPass != null ){
            try {
                ArrayList<RestrictValue> restrictValue = listOfPass.getPassFullView().getRestrictValues();
                if (!restrictValue.isEmpty() ){
                    for (int index = 0; index < restrictValue.size(); index++) {
                        LinearLayout linearLayout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.layout_restric_value_redeem_main, null, false);
                        OTTextView txtFeatureTypeName = (OTTextView) linearLayout.findViewById(R.id.txtFeatureTypeName);
                        txtFeatureTypeName.setText(restrictValue.get(index).getFeatureTypeName());

                        ArrayList<FeatureDetail> featureDetails = restrictValue.get(index).getFeatureDetails();
                        LinearLayout lytRstricValuesFeatureDetail = (LinearLayout) linearLayout.findViewById(R.id.lytRstricValuesFeatureDetail);
                        addRestrictValueFeatureDetails(lytRstricValuesFeatureDetail,featureDetails);


                        // linearLayout.setPadding(0, 10, 0, 10);
                        lytRestrictedValues.addView(linearLayout);
                    }                }
            }
           catch (Exception e){

           }
        }


    }

    private void addRestrictValueFeatureDetails(LinearLayout lytRstricValuesFeatureDetail, ArrayList<FeatureDetail> featureDetails) {

        if (featureDetails != null && (!featureDetails.isEmpty())) {
            for (int i = 0; i < featureDetails.size(); i++) {

                LinearLayout linearLayout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.layout_restric_value_feature_details, null, false);
                OTTextView txtFeatureTitle = (OTTextView) linearLayout.findViewById(R.id.txtFeatureTitle);
                OTTextView txtRoutes = (OTTextView) linearLayout.findViewById(R.id.txtRoutes);
                OTTextView txtTravelPeriod = (OTTextView) linearLayout.findViewById(R.id.txtTravelPeriod);

                LinearLayout lytODListWithDate = (LinearLayout) linearLayout.findViewById(R.id.lytODListWithDate);

                txtFeatureTitle.setText(featureDetails.get(i).getFvShrtTitle());
                txtRoutes.setText(featureDetails.get(i).getRoutesLavel());
                txtTravelPeriod.setText(featureDetails.get(i).getTravelPeriod());

                ArrayList<OdListWithDate> odListWithDates = featureDetails.get(i).getOdListWithDates();
                String viewAll = featureDetails.get(i).getViewAllLabel();
                String viewLess = featureDetails.get(i).getViewLessLabel();
                addODListWithDate(lytODListWithDate,odListWithDates,viewAll,viewLess);

                //linearLayout.setPadding(0, 10, 0, 10);
                lytRstricValuesFeatureDetail.addView(linearLayout);
            }

        }

    }

    private void addODListWithDate(LinearLayout lytODListWithDate, ArrayList<OdListWithDate> odListWithDates, final String viewAll, final String viewLess) {

        if (odListWithDates != null && (!odListWithDates.isEmpty())){

            final LinearLayout lytODListWithDateChild = (LinearLayout) lytODListWithDate.findViewById(R.id.lytODListWithDateChild);
            boolean isChildLayoutEmpty = true;

            for (int j = 0; j < odListWithDates.size(); j++) {

                ArrayList<Odlist> odlists = odListWithDates.get(j).getOdlist();

                try {
                    if (odlists != null) {
                        for (int i = 0; i < odlists.size(); i++) {

                            LinearLayout  layout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.layout_restrict_value_odlist, null, false);
                            OTTextView txtRestrictRoute = (OTTextView) layout.findViewById(R.id.txtRestrictRoute);
                            OTTextView txtRestrictDateRange = (OTTextView) layout.findViewById(R.id.txtRestrictDateRange);
                            if(i == 0)
                            {
                                txtRestrictDateRange.setText(odListWithDates.get(j).getRestrictDateRange());
                            }
                            else
                            {
                                txtRestrictDateRange.setText("");
                            }

                            layout.findViewById(R.id.viewBorder).setVisibility(i == odlists.size()-1 ? View.VISIBLE:View.GONE);

                            txtRestrictRoute.setText(odlists.get(i).getOdPaIR());


                            if(j < DEFAULT_SHOW_FM_VALUES)
                            {
                                lytODListWithDate.addView(layout);
                            }
                            else
                            {
                                if(isChildLayoutEmpty)
                                {
                                    TextView txtViewAll = new TextView(lytODListWithDate.getContext());
                                    txtViewAll.setText(viewAll);

                                    txtViewAll.setTextColor(Color.BLUE);
                                    txtViewAll.setTextSize(12);
                                    txtViewAll.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lytODListWithDateChild.setVisibility(lytODListWithDateChild.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                                            ((TextView) v).setText(lytODListWithDateChild.getVisibility() == View.GONE ? viewAll : viewLess);

                                            //svParent.fullScroll(View.SCROLL_INDICATOR_TOP);
                                        }
                                    });
                                    lytODListWithDate.addView(txtViewAll);
                                    isChildLayoutEmpty = false;
                                }

                                lytODListWithDateChild.addView(layout);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }
    }


    private void addPlusUserMessage(FragmentActivity activity, LinearLayout lay_list_passenger, ListOfPass listOfPass)
    {
        int yetToAdd = (listOfPass.getPassFullView().getLABLMTPUserLabel() - listOfPass.getPassFullView().getPassengerNameArray().size());
        if(yetToAdd > 0)
        {
            LayoutInflater layoutInfralte=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInfralte.inflate(R.layout.child_passenger, null);
            OTTextView tv=(OTTextView) view.findViewById(R.id.txtlearmore);
            tv.setText("+ " + yetToAdd + (yetToAdd == 1 ? " User" : " Users"));
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            lay_list_passenger.addView(view);
        }
    }

    public void showDialog(FragmentCommunicationData d) {

        SelectedPassDataForSearchFlight data = new SelectedPassDataForSearchFlight();
        data.setTYPE_DIALOG(2);
        data.setZoneLatLongitudes(listOfPass.getPassSmallView().getZoneLatLongitudes());
        data.setClose_Label(listOfPass.getPassSmallView().getClose_Label());
        data.setTravelZoneTitleLabel(listOfPass.getPassSmallView().getTravelZoneLabel());
        data.setZoneTipLabel(listOfPass.getPassFullView().getTravelZoneTips());

        OTDialogFragment dialog = new OTDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(getActivity().getString(R.string.key_serializable), (Serializable) data);
        dialog.setArguments(bundle);

        dialog.show(((FragmentActivity) getActivity()).getSupportFragmentManager(), "");

    }

    public class myClickableSpan extends ClickableSpan {

        int pos;
        public myClickableSpan(int position){
            this.pos=position;
        }

        @Override
        public void onClick(View widget) {
            Utils.showPopUp(getActivity(), listOfPass.getPassFullView().getTravelZoneTips(), listOfPass.getPassSmallView().getTravelZoneTitle());

        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
            ds.setColor(getActivity().getResources().getColor(R.color.color_font_blue));
        }
    }
}

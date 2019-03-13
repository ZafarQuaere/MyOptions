package com.optiontown.app.view.fragment.fpo.flightpass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.adapter.SearchSelectRecyclerViewAdapter;
import com.optiontown.app.model.fpogetpass.FpoGetPassData;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.selectproduct.CabinArray;
import com.optiontown.app.model.selectproduct.FlightPassDealData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.model.selectproduct.Restriction;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.SelectFlightPassFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Flight pass search page
 */
public class  FlightPassSearchFragment extends BaseFragment
{
    private View view;
    private OTTextView txtSearch;
    private OTTextView txtAirlineName;
    private OTTextView txtTravelZoneName;
    private OTTextView txtCabinName;
    private OTTextView txtPassengerCount;
    private OTTextView txtFlightsCount;
    private OTTextView txtTravelPeriod;
    private OTTextView txtAdvanceBooking;
    private OTTextView txtFlexibility;

    private AppSharedPrefs sharedPrefs;
    private OTTextView txtAdvanceSearch;
    private FlightPassDealData flightPassDealData;
    private LinearLayout lytCabin;
    private InternationalizeData localization;
    private LinearLayout lytAdvanceSearch;
    private LinearLayout lytAdvanceBooking;
    private ImageLoader imageLoader;
    private LinearLayout lytFMMatrix;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_flight_pass_search, container, false);

        //---get data for localization
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        //initialise shared prefs manager
        sharedPrefs = AppSharedPrefs.getInstance(getActivity());

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        Utils.updateBottomBarForFeatures(view, this.getClass().getName());

        LinearLayout lytAirlines = (LinearLayout) view.findViewById(R.id.lytAirlines);
        lytAirlines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new FlightPassSearchSelectFragment(), flightPassDealData, SearchSelectRecyclerViewAdapter.VIEW_TYPE_AIRLINE);
            }
        });

        LinearLayout lytTravelZone = (LinearLayout) view.findViewById(R.id.lytTravelZone);
        lytTravelZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new FlightPassTravelZoneFragment(), flightPassDealData, 0);
            }
        });

        lytCabin = (LinearLayout) view.findViewById(R.id.lytCabin);
        lytCabin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new FlightPassSearchSelectFragment(), flightPassDealData, SearchSelectRecyclerViewAdapter.VIEW_TYPE_CABIN);
            }
        });

        LinearLayout lytPassengers = (LinearLayout) view.findViewById(R.id.lytPassengers);
        lytPassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new FlightPassSearchSelectFragment(), flightPassDealData, SearchSelectRecyclerViewAdapter.VIEW_TYPE_PASSENGER);
            }
        });

        LinearLayout lytFlights = (LinearLayout) view.findViewById(R.id.lytFlights);
        lytFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new FlightPassSearchSelectFragment(), flightPassDealData, SearchSelectRecyclerViewAdapter.VIEW_TYPE_FLIGHT);
            }
        });

        LinearLayout lytTravelPeriod = (LinearLayout) view.findViewById(R.id.lytTravelPeriod);
        lytTravelPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.updateActionBarForFeatures(getActivity(),localization.getLABLPassValidityLabel());
                Utils.moveToFragment(getActivity(), new FlightPassSearchSelectFragment(), flightPassDealData, SearchSelectRecyclerViewAdapter.VIEW_TYPE_TRAVEL_PERIOD);

            }
        });

        lytAdvanceBooking = (LinearLayout) view.findViewById(R.id.lytAdvanceBooking);
        lytAdvanceBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new FlightPassSearchSelectFragment(), flightPassDealData, SearchSelectRecyclerViewAdapter.VIEW_TYPE_ADVANCE_BOOKING);
            }
        });


        try
        {
            flightPassDealData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getHomePassBannerData(getActivity())), FlightPassDealData.class);
            Utils.setSharedPrefsForSearch(getActivity(), flightPassDealData);
            updateUI();

            txtAdvanceSearch = (OTTextView) view.findViewById(R.id.txtAdvanceSearch);
            txtAdvanceSearch.setText(localization.getLabl_Advance_Search_Label());
            txtAdvanceSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateUIForAdvanceSearch();
                }
            });
        }
        catch (Exception e)
        {
            Utils.ERROR("FlightPassSearchFragment >> Error while parsing json : " + e.toString());
            e.printStackTrace();
        }


        localizeUI(localization);

        return view;
    }


    private void localizeUI(InternationalizeData localization) {

        if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_fpo)))
        {
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLFlightPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLUKBuyFlightPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(localization.getLABL_RedeemPass_Label());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

        }else if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_utp)))
        {
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLUpgradePassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLBuyUpgradePassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

        }else if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_esp)))
        {
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLEmptySeatPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLBuyEmptySeatPassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());


        }else if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_psp)))
        {
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLPreferredSeatPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLBuyPreferredSeatPassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

        }

    }



    private void updateUIForAdvanceSearch()
    {
        try
        {
            imageLoader = AppController.getInstance().getImageLoader();
            lytAdvanceSearch = (LinearLayout) view.findViewById(R.id.lytAdvanceSearch);
            lytFMMatrix = (LinearLayout) view.findViewById(R.id.lytFMMatrix);
            lytAdvanceSearch.setVisibility(View.VISIBLE);

            txtAdvanceSearch.setText(localization.getLABLMTPSearchLabel());
            txtAdvanceSearch.setBackgroundResource(R.drawable.rounded_small_corner_search);
            txtAdvanceSearch.setTextColor(getResources().getColor(R.color.color_font_white));

            try {
                createFMMatrixUI();
            } catch (Exception e) {
                e.printStackTrace();
            }

            txtSearch.setVisibility(View.GONE);

            //if(lytAdvanceSearch.getVisibility() == View.VISIBLE)
            {
                txtAdvanceSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        callAPIToSearch();
                    }
                });
            }
        }
        catch (Exception e)
        {
            Utils.ERROR("Error in updateUIForAdvanceSearch() : " + e.toString());
        }
    }

    /**
     * used to create fm feature matrix ui
     */
    private void createFMMatrixUI()
    {
        lytFMMatrix.removeAllViews();;
        if(flightPassDealData != null && flightPassDealData.getRestrictions() != null)
        {
            for (int index = 0; index < flightPassDealData.getRestrictions().size(); index++)
            {
                View inflated = getActivity().getLayoutInflater().inflate(R.layout.layout_flight_pass_search_fm_matrix_row, null);
                inflated.setId(SearchSelectRecyclerViewAdapter.VIEW_TYPE_FM_MATRIX_BASE + flightPassDealData.getRestrictions().get(index).getFID());

                OTTextView txtFMMatrixLabel = (OTTextView) inflated.findViewById(R.id.txtFMMatrixLabel);
                OTTextView txtFMMatrix = (OTTextView) inflated.findViewById(R.id.txtFMMatrix);
                NetworkImageView imgFMMatrix = (NetworkImageView) inflated.findViewById(R.id.imgFMMatrix);

                txtFMMatrixLabel.setText(flightPassDealData.getRestrictions().get(index).getFName());
                txtFMMatrix.setText(flightPassDealData.getDefaultValues().getRestrictedValues().get(index).getFeatureName());
                imgFMMatrix.setImageUrl(getString(R.string.URL_BASE) + getString(R.string.URL_IMAGE_RESTRICTED)+ flightPassDealData.getRestrictions().get(index).getHelpImage(), imageLoader);

                LinearLayout lytTop =  (LinearLayout)inflated.findViewById(R.id.lytTop);


                final int finalIndex = index;
                lytTop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.moveToFragment(getActivity(), new FlightPassSearchSelectFragment(), flightPassDealData, SearchSelectRecyclerViewAdapter.VIEW_TYPE_FM_MATRIX_BASE + flightPassDealData.getRestrictions().get(finalIndex).getFID());
                    }
                });

                lytFMMatrix.addView(inflated);
            }
        }
    }

    private boolean isValidBATravelPeriod()
    {
        return true;
        /*OTTextView txtBAMessage = (OTTextView) view.findViewById(R.id.txtBAMessage);
        if((int)sharedPrefs.get(getString(R.string.key_selected_airline_id)) == 975)
        {
            Calendar instance = Calendar.getInstance();
            instance.setTime(Utils.convertToDate_ddMMMyyyy((String) sharedPrefs.get(getString(R.string.key_selected_travel_period_valid_from))));
            instance.add(Calendar.DATE, (int) sharedPrefs.get(getString(R.string.key_selected_travel_period_month_id)));
            Date time = instance.getTime();


            if(time.after(Utils.convertToDate_ddMMMyyyy("31 DEC 2017")))
            {
                txtBAMessage.setVisibility(View.VISIBLE);
                txtBAMessage.setText("Sorry, all passes must expire on or before 31 Dec 2017. Prepone the begin date or shorten the duration or Advance Booking and search again.");
                return false;
            }
            else
            {
                txtBAMessage.setVisibility(View.GONE);
            }
        }
        else
        {
            txtBAMessage.setVisibility(View.GONE);
        }
        return true;*/
    }


    private void callAPIToSearch()
    {
        if(!isValidBATravelPeriod())
        {
            return;
        }

        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FPO_GET_PASS);


        final JSONObject requestObject = new JSONObject();
        try
        {

            requestObject.put("TgProductId", Integer.toString(Utils.getCurrentProductId(getActivity())));
            requestObject.put("CountryId", Integer.toString(Utils.getUserSelectedCountryId(getActivity())));
            requestObject.put("LanguageId", Integer.toString(Utils.getUserSelectedLanguageId(getActivity())));
            requestObject.put("IndustryId", "-1");
            requestObject.put("PassValueId", "0");
            requestObject.put("UserCountId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_passenger_id))));
            requestObject.put("PassValidityBeginDate", ((String)sharedPrefs.get(getString(R.string.key_selected_travel_period_valid_from)))).toString();
            requestObject.put("isDefaultPassSearch", "0");
            requestObject.put("MarketingAirlineId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_airline_id))));
            requestObject.put("searchCabinId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_cabin_id))));
            requestObject.put("TgpFgId", Long.toString((long) sharedPrefs.get(getString(R.string.key_selected_travel_zone_tgp_fg_id))));
            requestObject.put("ZoneGroupId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_travel_zone_group_id))));
            requestObject.put("ZoneSubGroupId", (((String)sharedPrefs.get(getString(R.string.key_selected_travel_zone_sub_group_id))))).toString();
            requestObject.put("ZoneId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_travel_zone_id))));
            requestObject.put("ZoneName", ((String)sharedPrefs.get(getString(R.string.key_selected_travel_zone_tag)))).toString();
            requestObject.put("flexibilityRangeId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_flexibility_id))));
            requestObject.put("PassTypeId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_passenger_pass_type_id))));
            requestObject.put("PassUserCount", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_passenger_id))));
            requestObject.put("PassCreditId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_flight_id))));
            requestObject.put("PassValidityId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_travel_period_month_id))));
            requestObject.put("advanceBookingId", Integer.toString((int)sharedPrefs.get(getString(R.string.key_selected_advance_booking_id))));
            requestObject.put("fmJSONString", createFMJSONString());//for FM Feature matrix
            //requestObject.put("PassMatrixId", "0");
            //requestObject.put("ConfigMatrixMemberId", "0");
        }
        catch (Exception e)
        {
            Utils.ERROR("Error while creating json request : " + e.toString());
            e.printStackTrace();
        }
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();



        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                true,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            return;
                        }
                        Utils.DEBUG("FlightPassSearch Response : " + response.toString());
                        FpoGetPassData fpoGetPassData = ParseManager.getInstance().fromJSON(response, FpoGetPassData.class);

                        if((boolean)sharedPrefs.get(getString(R.string.key_selected_travel_is_personalised_zone)))
                        {
                            sharedPrefs.put(getString(R.string.key_selected_travel_zone_id), 1);;//1 for personalised zone
                        }


                        //save in json
                        Utils.setFPOPassData(getActivity(), response.toString());

                        Utils.moveToFragment(getActivity(), new SelectFlightPassFragment(), fpoGetPassData, 0);

                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private String createFMJSONString()
    {
        if(flightPassDealData == null || flightPassDealData.getRestrictions() == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int index = 0; index < flightPassDealData.getRestrictions().size(); index++)
        {
            int fId = flightPassDealData.getRestrictions().get(index).getFID();
            int featureId = (int) sharedPrefs.get(getString(R.string.key_selected_fm_matrix_id) + fId);

            buffer.append("\"fm" + fId + "\":\"" + fId + "_" + featureId + "\"" + (index == flightPassDealData.getRestrictions().size() - 1 ? "" : ","));
        }
        return buffer.toString();
    }


    public void updateSelectionDone(FragmentCommunicationData fragmentCommunicationData)
    {
        Utils.DEBUG("updateSelectionDone() called : selected airline : " + fragmentCommunicationData.getSelectedAirline());
        txtAirlineName.setText(fragmentCommunicationData.getSelectedAirline());
        txtCabinName.setText(fragmentCommunicationData.getSelectedCabin());
        txtPassengerCount.setText(fragmentCommunicationData.getSelectedPassenger());
        txtFlightsCount.setText(fragmentCommunicationData.getSelectedCredit());
        txtTravelPeriod.setText(fragmentCommunicationData.getSelectedTravelPeriod());
        txtAdvanceBooking.setText(fragmentCommunicationData.getSelectedAdvanceBooking());
        txtFlexibility.setText(fragmentCommunicationData.getSelectedFlexibility());
        txtTravelZoneName.setText(fragmentCommunicationData.getSelectedTravelZone());


        //start FM Matrix update
        //first find the selected FID which we will update
        for (int index = 0; index < flightPassDealData.getRestrictions().size(); index++)
        {
            Restriction restriction = flightPassDealData.getRestrictions().get(index);
            for (int pos = 0; pos < restriction.getValues().size(); pos++) {
                int featureId = fragmentCommunicationData.getSelectedFMMatrixId();
                if(restriction.getValues().get(pos).getFeatureId() == featureId)
                {
                    //we have selectedFID, now update UI
                    //SearchSelectRecyclerViewAdapter.VIEW_TYPE_FM_MATRIX_BASE + restriction.getFID();
                    for (int loc = 0; loc < lytAdvanceSearch.getChildCount(); loc++) {
                        View childAt = lytAdvanceSearch.getChildAt(loc);
                        if(childAt.getId() == SearchSelectRecyclerViewAdapter.VIEW_TYPE_FM_MATRIX_BASE + restriction.getFID())
                        {
                            //update UI here
                            OTTextView txtFMMatrix = (OTTextView) childAt.findViewById(R.id.txtFMMatrix);
                            txtFMMatrix.setText(fragmentCommunicationData.getSelectedFMMatrixTag());
                            break;
                        }
                    }
                    break;
                }
            }
        }
        //end FM Matrix update

        if(fragmentCommunicationData.isAirlineChanged())
        {
            flightPassDealData = fragmentCommunicationData.getFlightPassDealData();
            Utils.setSharedPrefsForSearch(getActivity(), flightPassDealData);
            updateCabinLayout(flightPassDealData.getCabinList().getCabinArray());

            if(lytAdvanceSearch != null && lytAdvanceSearch.getVisibility() == View.VISIBLE)
            {
                updateUIForAdvanceSearch();
            }
        }

        updateUI();
        isValidBATravelPeriod();
    }




    /**
     * call this after api calling
     */
    private void updateUI()
    {
        lytAdvanceBooking.setVisibility((flightPassDealData.getAdvanceBookingList().getAdvanceBookingList1() == null && flightPassDealData.getAdvanceBookingList().getAdvanceBookingList2() == null)
                 ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.lytAdvanceBookingSeparator).setVisibility(lytAdvanceBooking.getVisibility());

        LinearLayout lytFlexibility = (LinearLayout) view.findViewById(R.id.lytFlexibility);

        //--
        lytFlexibility.setVisibility(flightPassDealData.getIsPassFlexibilityDisplay() == 0 ? View.GONE : View.VISIBLE);
        try
        {
            if(flightPassDealData.getFlexibilitySetList() == null || (flightPassDealData.getFlexibilitySetList().getFlexibilitySetList().isEmpty()
                    && flightPassDealData.getFlexibilitySetList().getNotflexibilitySetList().isEmpty()))
            {
                lytFlexibility.setVisibility(View.GONE);
            }
        }catch (Exception e)
        {
            //
        }

        //---
        lytFlexibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new FlightPassSearchSelectFragment(), flightPassDealData, SearchSelectRecyclerViewAdapter.VIEW_TYPE_FLEXIBILITY);
            }
        });

        txtSearch = (OTTextView) view.findViewById(R.id.txtSearch);
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPIToSearch();
            }
        });

        txtAirlineName      = (OTTextView) view.findViewById(R.id.txtAirlineName);
        txtAirlineName.setText((String) sharedPrefs.get(getString(R.string.key_selected_airline_tag)));

        txtTravelZoneName   = (OTTextView) view.findViewById(R.id.txtTravelZoneName);
        txtTravelZoneName.setText((String) sharedPrefs.get(getString(R.string.key_selected_travel_zone_tag)));

        txtCabinName        = (OTTextView) view.findViewById(R.id.txtCabinName);
        txtCabinName.setText((String) sharedPrefs.get(getString(R.string.key_selected_cabin_tag)));

        updateCabinLayout(flightPassDealData.getCabinList().getCabinArray());

        txtPassengerCount   = (OTTextView) view.findViewById(R.id.txtPassengerCount);
        txtPassengerCount.setText((String) sharedPrefs.get(getString(R.string.key_selected_passenger_tag)));

        txtFlightsCount   = (OTTextView) view.findViewById(R.id.txtFlightsCount);
        txtFlightsCount.setText((String) sharedPrefs.get(getString(R.string.key_selected_flight_tag)));

        txtTravelPeriod   = (OTTextView) view.findViewById(R.id.txtTravelPeriod);
        txtTravelPeriod.setText(Utils.getSelectedTextForValidPeriod(getActivity(), (String) sharedPrefs.get(getString(R.string.key_selected_travel_period_valid_from)), (String) sharedPrefs.get(getString(R.string.key_selected_travel_period_month_tag))));

        txtAdvanceBooking = (OTTextView) view.findViewById(R.id.txtAdvanceBooking);
        txtAdvanceBooking.setText((String) sharedPrefs.get(getString(R.string.key_selected_advance_booking_tag)));

        txtFlexibility   = (OTTextView) view.findViewById(R.id.txtFlexibility);
        txtFlexibility.setText(((String) sharedPrefs.get(getString(R.string.key_selected_flexibility_tag))));


        localiseUI();
    }

    private void localiseUI() {

        ((OTTextView) view.findViewById(R.id.txtAirlineLabel)).setText(localization.getLABLAirlineLabel());
        ((OTTextView) view.findViewById(R.id.txtTravelZoneLabel)).setText(localization.getLABLSelectTravelZoneLabel());
        ((OTTextView) view.findViewById(R.id.txtCabinLabel)).setText(localization.getLABLCabinGetTGPLabel());
        ((OTTextView) view.findViewById(R.id.txtPassengersLabel)).setText(localization.getLablUserHelpTextLabel());
        ((OTTextView) view.findViewById(R.id.txtFlightsLabel)).setText(localization.getLABLCreditInputLabel());
        ((OTTextView) view.findViewById(R.id.txtTravelPeriodLabel)).setText(localization.getLABLPassValidityLabel());
        ((OTTextView) view.findViewById(R.id.txtAdvanceBookingLabel)).setText(localization.getLABLAdvanceBookInputLabel());
        ((OTTextView) view.findViewById(R.id.txtFlexibilityLabel)).setText(localization.getLABLFlexibilityTitleLabel());
        txtSearch.setText(localization.getLABLMTPSearchLabel());


        if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_fpo)))
        {
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLFlightPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLUKBuyFlightPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

        }else if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_utp)))
        {
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLUpgradePassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLBuyUpgradePassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

        }else if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_esp)))
        {
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLEmptySeatPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLBuyEmptySeatPassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

        }else if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_psp)))
        {
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLPreferredSeatPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLBuyPreferredSeatPassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());
        }

    }

    /**
     * show hide cabin layout view after passing list of cabinArray
     * @param cabinArray
     */
    private void updateCabinLayout(ArrayList<CabinArray> cabinArray) {
        if(cabinArray.size() == 0)
        {
            view.findViewById(R.id.viewSeparatorCabin).setVisibility(View.GONE);
            lytCabin.setVisibility(View.GONE);
        }
        else {
            view.findViewById(R.id.viewSeparatorCabin).setVisibility(View.VISIBLE);
            lytCabin.setVisibility(View.VISIBLE);
        }
    }

}

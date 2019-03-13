package com.optiontown.app.view.fragment.fpo.flightpass;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.fpogetpass.CustomZoneData;
import com.optiontown.app.model.selectproduct.FlightPassDealData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.model.selectproduct.SubChild;
import com.optiontown.app.model.selectproduct.Zone;
import com.optiontown.app.model.selectproduct.ZoneChild;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.PredicateLayout;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * UI for travel zone
 * @author amit
 */
public class FlightPassTravelZoneFragment extends BaseFragment
{
    private String TEXT_PERSONALISE = "";
    private String TEXT_ROUTES = " routes";
    private View view;
    private AppSharedPrefs sf;
    private FlightPassDealData flightPassDealData;
    private LinearLayout lytZoneName;
    private LinearLayout lytZoneShortDescription;
    private ImageView imgInformation;
    private RelativeLayout lytInformation;
    private ImageView imgClose;
    private OTTextView txtInformation;
    private LinearLayout lytOriginSuggestion;

    private LinearLayout lytOriginInput;
    private PredicateLayout lytSelectedOriginAirport;
    private OTTextView txtHelp;
    private OTEditText edtOriginAirport;
    private OTEditText edtDestinationAirport;
    private ArrayList<String> listAirportSet;
    private ArrayList<String> listCitySet;
    private ArrayList<String> listStateSet;
    private ArrayList<String> listCountrySet;
    private ArrayList<String> listAllOriginDestination;
    private ArrayList<String> listAirportData;


    private final int TYPE_AIRPORT = 0;
    private final int TYPE_CITY = 1;
    private final int TYPE_STATE = 2;
    private final int TYPE_COUNTRY = 3;

    private final int TYPE_ORIGIN = 100;
    private final int TYPE_DESTINATION = 101;

    private final String ALL_AIRPORTS = " - All Airports";


    private ArrayList<String> listSelectedOrigin = new ArrayList<>();
    private ArrayList<String> listSelectedDestination = new ArrayList<>();

    private ArrayList<String> listValidSelectedOrigin = new ArrayList<>();
    private ArrayList<String> listValidSelectedDestination = new ArrayList<>();

    private OTTextView txtOriginError;
    private LinearLayout lytDestinationInput;
    private LinearLayout lytDestinationSuggestion;

    private RelativeLayout rlytDestinationLabel;
    private RelativeLayout rlytOriginLabel;
    private PredicateLayout lytSelectedDestinationAirport;
    private OTTextView txtDestinationError;
    private OTTextView txtResultError;
    private Communicator communicator;

    private interface ZoneSelectListener
    {
        void onClickZone(String ids);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_flight_pass_travel_zone, container, false);


        //initialise shared prefs manager
        sf = AppSharedPrefs.getInstance(getActivity());

        flightPassDealData = ((FlightPassDealData) getArguments().getSerializable(getString(R.string.key_serializable)));
        TEXT_PERSONALISE = flightPassDealData.getLABL_Personalised_Zone_Label();

        listAirportData = flightPassDealData.getOriginData().getAirportDataList();
        listAllOriginDestination = flightPassDealData.getOriginData().getOriginDestinationList();
        listAirportSet = /*AddSuffixAllAirports*/(flightPassDealData.getOriginData().getAirportSetList());
        listCitySet = AddSuffixAllAirports(flightPassDealData.getOriginData().getCitySetList());
        listStateSet = AddSuffixAllAirports(flightPassDealData.getOriginData().getStateSetList());
        listCountrySet = AddSuffixAllAirports(flightPassDealData.getOriginData().getCountrySetList());

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), flightPassDealData.getZoneData().getTitle(), flightPassDealData.getZoneData().getHelp());
        Utils.updateBottomBarForFeatures(view, this.getClass().getName());

        lytZoneName = (LinearLayout) view.findViewById(R.id.lytZoneName);
        lytZoneShortDescription = (LinearLayout) view.findViewById(R.id.lytZoneShortDescription);

        addZoneNames();
        updateLayoutForShortDescription(flightPassDealData.getZoneData().getZone().get(0).getZoneName());

        lytInformation = (RelativeLayout) view.findViewById(R.id.lytInformation);
        imgClose = (ImageView) view.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytInformation.setVisibility(View.GONE);
            }
        });
        txtInformation = (OTTextView) view.findViewById(R.id.txtInformation);
        txtInformation.setText(flightPassDealData.getOriginData().getHelpLabel());


        imgInformation = (ImageView) view.findViewById(R.id.imgInformation);
        imgInformation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                lytInformation.setVisibility(View.VISIBLE);
            }
        });


        //origin
        lytOriginInput = (LinearLayout) view.findViewById(R.id.lytOriginInput);
        lytOriginSuggestion = (LinearLayout) view.findViewById(R.id.lytOriginSuggestion);
        lytSelectedOriginAirport = (PredicateLayout) view.findViewById(R.id.lytSelectedOriginAirport);
        txtOriginError = (OTTextView) view.findViewById(R.id.txtOriginError);

        edtOriginAirport = (OTEditText) view.findViewById(R.id.edtOriginAirport);
        edtOriginAirport.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Utils.DEBUG("onTextChanged >> " + s.toString() + ", count : " + count);
                CreateSuggestionView(TYPE_ORIGIN, lytOriginSuggestion, s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //destination
        lytDestinationInput = (LinearLayout) view.findViewById(R.id.lytDestinationInput);
        lytDestinationSuggestion = (LinearLayout) view.findViewById(R.id.lytDestinationSuggestion);

        rlytOriginLabel = (RelativeLayout) view.findViewById(R.id.rlytOriginLabel);
        rlytDestinationLabel = (RelativeLayout) view.findViewById(R.id.rlytDestinationLabel);

        lytSelectedDestinationAirport = (PredicateLayout) view.findViewById(R.id.lytSelectedDestinationAirport);
        txtDestinationError = (OTTextView) view.findViewById(R.id.txtDestinationError);

        edtDestinationAirport = (OTEditText) view.findViewById(R.id.edtDestinationAirport);
        edtDestinationAirport.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Utils.DEBUG("onTextChanged >> " + s.toString() + ", count : " + count);
                CreateSuggestionView(TYPE_DESTINATION, lytDestinationSuggestion, s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        txtResultError = (OTTextView) view.findViewById(R.id.txtResultError);

        if (Integer.parseInt(flightPassDealData.getOriginData().getCZParamEntity().getIsCustomSearchOn()) == 0){
            lytDestinationInput.setVisibility(View.GONE);
            lytOriginInput.setVisibility(View.GONE);
            rlytOriginLabel.setVisibility(View.GONE);
            rlytDestinationLabel.setVisibility(View.GONE);

        }


        return  view;
    }

    /**
     * used to add suffix 'All Airports'
     * @param list
     * @return
     */
    private ArrayList<String> AddSuffixAllAirports(ArrayList<String> list) {

        if(list == null)
        {
            return new ArrayList<String>();
        }

        for (int index = 0; index < list.size(); index++) {
            list.set(index, list.get(index) + ALL_AIRPORTS);
        }

        return list;
    }

    /**
     * used to create suggestion view based on given string
     * @param strEntered
     */
    private void CreateSuggestionView(int typeOriginDestination, LinearLayout lytSuggestion, String strEntered)
    {
        txtOriginError.setVisibility(View.GONE);
        txtDestinationError.setVisibility(View.GONE);
        txtResultError.setVisibility(View.GONE);

        lytSuggestion.removeAllViews();
        if(strEntered.length() < 2)
        {
            lytSuggestion.setVisibility(View.GONE);
            return;
        }

        createSuggestiveViewForType(lytSuggestion, listAirportSet, strEntered, TYPE_AIRPORT, typeOriginDestination);
        createSuggestiveViewForType(lytSuggestion, listCitySet, strEntered, TYPE_CITY, typeOriginDestination);
        createSuggestiveViewForType(lytSuggestion, listStateSet, strEntered, TYPE_STATE, typeOriginDestination);
        createSuggestiveViewForType(lytSuggestion, listCountrySet, strEntered, TYPE_COUNTRY, typeOriginDestination);

        lytSuggestion.setVisibility(lytSuggestion.getChildCount() == 0 ? View.GONE : View.VISIBLE);

        if(lytSuggestion.getChildCount() > 0)
        {
            (lytSuggestion.getChildAt(0)).findViewById(R.id.txtAirportSeparator).setVisibility(View.GONE);
        }

    }

    private void createSuggestiveViewForType(LinearLayout lytSuggestion, List list, String strEntered, int type, int typeOriginDestination)
    {
        boolean isSeparaturViewVisible = false;
        for (int index = 0; index < list.size(); index++)
        {
            if(list.get(index).toString().toLowerCase().contains(strEntered.toLowerCase()))
            {
                if(!isSeparaturViewVisible)
                {
                    switch (type)
                    {
                        case TYPE_AIRPORT:
                            addInSuggestionSeparatorView(lytSuggestion, "Airports", R.drawable.airports_icon);
                            isSeparaturViewVisible = true;
                            break;

                        case TYPE_CITY:
                            addInSuggestionSeparatorView(lytSuggestion, "Cities", R.drawable.cities_icon);
                            isSeparaturViewVisible = true;
                            break;

                        case TYPE_STATE:
                            addInSuggestionSeparatorView(lytSuggestion, "States", R.drawable.states_icon);
                            isSeparaturViewVisible = true;
                            break;

                        case TYPE_COUNTRY:
                            addInSuggestionSeparatorView(lytSuggestion, "Countries", R.drawable.countries_icon);
                            isSeparaturViewVisible = true;
                            break;

                    }
                }
                addInSuggestionView(lytSuggestion, list.get(index).toString()/*.replace(strEntered, "<font color='red'>" + strEntered + "</font>")*/, strEntered, typeOriginDestination);
            }
        }
    }






    /**
     * add zone name to the view and enable clicks
     */
    private void addZoneNames()
    {
        final List<Zone> listZone = flightPassDealData.getZoneData().getZone();

        for(int index = 0; index < listZone.size(); index++)
        {
            final LinearLayout v = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_zone_name, null, false);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) Utils.conertDpToPixel(getActivity(), 40));

            OTTextView txtZoneName = (OTTextView) v.findViewById(R.id.txtZoneName);
            txtZoneName.setText(listZone.get(index).getZoneName());

            final View viewStripSelected = (View) v.findViewById(R.id.viewStripSelected);
            if(index == 0)//first selected by default
            {
                viewStripSelected.setVisibility(View.VISIBLE);
                v.setBackgroundColor(getResources().getColor(R.color.color_gray_dark));
            }

            final int finalIndex = index;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unselectResetZones(finalIndex);
                    updateLayoutForShortDescription(listZone.get(finalIndex).getZoneName());
                }

                private void unselectResetZones(int finalIndex)
                {
                    for (int i = 0; i < lytZoneName.getChildCount(); i++)
                    {
                        if(finalIndex == i)
                        {
                            lytZoneName.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.color_gray_dark));
                            (lytZoneName.getChildAt(i).findViewById(R.id.viewStripSelected)).setVisibility(View.VISIBLE);

                        }
                        else
                        {
                            lytZoneName.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.color_gray_light));
                            (lytZoneName.getChildAt(i).findViewById(R.id.viewStripSelected)).setVisibility(View.GONE);

                        }

                    }
                }
            });
            lytZoneName.addView(v, params);
        }
    }

    private void updateLayoutForShortDescription(final String zoneName)
    {
        //first remove all view forom lytZoneShortDescription
        lytZoneShortDescription.removeAllViews();

        List<Zone> listZone = flightPassDealData.getZoneData().getZone();
        for(int index = 0; index < listZone.size(); index++)
        {
            Zone zone = listZone.get(index);
            if(zone.getZoneName().equals(zoneName))
            {
                final List<ZoneChild> listZoneChild = zone.getZoneChild();

                for(int pos = 0; pos < listZoneChild.size(); pos++)
                {


                    RelativeLayout v = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_zone_select, null, false);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) Utils.conertDpToPixel(getActivity(), 40));

                    OTTextView txtZoneShortDescription = (OTTextView) v.findViewById(R.id.txtZoneShortDescription);
                    txtZoneShortDescription.setText(listZoneChild.get(pos).getShortDesc());

                    ImageView imgArrow = (ImageView) v.findViewById(R.id.imgArrow);
                    imgArrow.setVisibility(View.GONE);

                    if(haveChild(zoneName, pos))
                    {
                        v.findViewById(R.id.txtSelect).setVisibility(View.GONE);
                    }
                    else
                    {
                        v.findViewById(R.id.txtSelect).setVisibility(View.VISIBLE);
                    }

                    final int finalPos = pos;
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(!haveChild(zoneName, finalPos))
                            {
                                setInSP(false, listZoneChild.get(finalPos).getShortDesc(), listZoneChild.get(finalPos).getZoneSubGroupId(),
                                        listZoneChild.get(finalPos).getZoneGroupId(), listZoneChild.get(finalPos).getZoneId(), listZoneChild.get(finalPos).getTGpFGId());

                                moveToBack();
                            }
                            if(!showHideChild(finalPos))
                            {
                                //Utils.showToast(getActivity(), listZoneChild.get(finalPos).getShortDesc());
                                /*setInSP(listZoneChild.get(finalPos).getShortDesc(), listZoneChild.get(finalPos).getZoneSubGroupId(),
                                        listZoneChild.get(finalPos).getZoneGroupId(), listZoneChild.get(finalPos).getZoneId(), listZoneChild.get(finalPos).getTGpFGId());

                                moveToBack();*/
                            }
                        }


                    });

                    lytZoneShortDescription.addView(v, params);

                    //for sub child
                    final ArrayList<SubChild> listSubChild = listZoneChild.get(pos).getSubChild();
                    for (int loc = 0; loc < listSubChild.size(); loc++)
                    {
                        if(listSubChild.get(loc).getExist().equals("1"))
                        {
                            RelativeLayout childView = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_zone_select, null, false);
                            childView.setTag(pos);
                            LinearLayout.LayoutParams paramsChild = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) Utils.conertDpToPixel(getActivity(), 40));

                            ((OTTextView) childView.findViewById(R.id.txtZoneShortDescription)).setText(listSubChild.get(loc).getShortDesc());
                            ((ImageView) childView.findViewById(R.id.imgArrow)).setVisibility(View.VISIBLE);;
                            childView.findViewById(R.id.txtSelect).setVisibility(View.VISIBLE);


                            final int finalLoc = loc;
                            childView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Utils.showToast(getActivity(), listSubChild.get(finalLoc).getShortDesc());
                                    setInSP(false, listZoneChild.get(finalPos).getShortDesc(), listZoneChild.get(finalPos).getZoneSubGroupId(),
                                            listZoneChild.get(finalPos).getZoneGroupId(), listZoneChild.get(finalPos).getZoneId(), listZoneChild.get(finalPos).getTGpFGId());

                                    moveToBack();
                                }
                            });
                            childView.setVisibility(View.GONE);
                            lytZoneShortDescription.addView(childView, paramsChild);
                        }
                    }
                }
            }
        }
    }

    private boolean haveChild(String zoneName, int loc)
    {
        List<Zone> listZone = flightPassDealData.getZoneData().getZone();
        for(int index = 0; index < listZone.size(); index++)
        {
            Zone zone = listZone.get(index);
            if(zone.getZoneName().equals(zoneName))
            {
                final List<ZoneChild> listZoneChild = zone.getZoneChild();

                for(int pos = 0; pos < listZoneChild.size(); pos++)
                {
                    if(loc == pos)
                    {
                        ArrayList<SubChild> listSubChild = listZoneChild.get(pos).getSubChild();
                        for (int l = 0; l < listSubChild.size(); l++)
                        {
                            if(listSubChild.get(l).getExist().equals("1"))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean showHideChild(int tag) {
        boolean haveChild = false;
        for (int i = 0; i < lytZoneShortDescription.getChildCount(); i++) {
            View child = lytZoneShortDescription.getChildAt(i);
            if(child != null && child.getTag() != null)
            {
                if(tag == (int)child.getTag())
                {
                    child.setVisibility(child.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                    haveChild = true;
                }
                else
                {
                    //hide rest
                    child.setVisibility(View.GONE);
                    haveChild = false;
                }

            }

        }
        return haveChild;
    }
    private void moveToBack() {
        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFragmentName((new FlightPassSearchFragment()).getClass().getName());
        data.setSelectedAirline((String) sf.get(getString(R.string.key_selected_airline_tag)));
        data.setSelectedTravelZone((String) sf.get(getString(R.string.key_selected_travel_zone_tag)));
        data.setSelectedCabin((String) sf.get(getString(R.string.key_selected_cabin_tag)));
        data.setSelectedPassenger((String) sf.get(getString(R.string.key_selected_passenger_tag)));
        data.setSelectedCredit((String) sf.get(getString(R.string.key_selected_flight_tag)));
        data.setSelectedTravelPeriod(Utils.getSelectedTextForValidPeriod(getActivity(), (String) sf.get(getString(R.string.key_selected_travel_period_valid_from)), (String) sf.get(getString(R.string.key_selected_travel_period_month_tag))));
        data.setSelectedAdvanceBooking((String) sf.get(getString(R.string.key_selected_advance_booking_tag)));
        data.setSelectedFlexibility((String) sf.get(getString(R.string.key_selected_flexibility_tag)));
        data.setAirlineChanged(false);


        communicator.onResponse(data);

        ((MainActivity)getActivity()).onBackPressed();
    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("FlightPassTravelZoneFragment >> onAttach(Activity) called");
        communicator = (Communicator) getActivity();
        super.onAttach(activity);
    }

    private void setInSP(boolean isPersonalisedZone, String zoneName, String zoneSubGroupId, int zoneGroupId, int zoneId, long tgpFgId)
    {
        Utils.DEBUG("isPersonalisedZone : " + isPersonalisedZone);
        Utils.DEBUG("zoneName : " + zoneName);
        Utils.DEBUG("zoneSubGroupId : " + zoneSubGroupId);
        Utils.DEBUG("zoneGroupId : " + zoneGroupId);
        Utils.DEBUG("zoneId : " + zoneId);
        Utils.DEBUG("tgpFgId : " + tgpFgId);

        sf.put(getString(R.string.key_selected_travel_zone_tag), zoneName);
        sf.put(getString(R.string.key_selected_travel_zone_sub_group_id), zoneSubGroupId);
        if(!isPersonalisedZone)
        {
            sf.put(getString(R.string.key_selected_travel_zone_group_id), zoneGroupId);
        }

        sf.put(getString(R.string.key_selected_travel_zone_id), zoneId);
        sf.put(getString(R.string.key_selected_travel_zone_tgp_fg_id), tgpFgId);
        sf.put(getString(R.string.key_selected_travel_is_personalised_zone), isPersonalisedZone);


    }

    public void updateHelpLayout(FragmentCommunicationData message)
    {
        txtHelp = (OTTextView) view.findViewById(R.id.txtHelp);
        txtHelp.setText(message.getHelp());
        txtHelp.setVisibility(message.isShowHelp() ? View.VISIBLE : View.GONE);
    }


    /**
     * used to add separator line view in suggestion list
     * @param lytSuggestion
     * @param txtSeparator
     * @param imgSeparatorResourceId
     */
    private void addInSuggestionSeparatorView(LinearLayout lytSuggestion, String txtSeparator, int imgSeparatorResourceId)
    {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_airport_suggestion_separator, null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int) Utils.conertDpToPixel(getActivity(), 25));
        params.setMargins(20, 0, 20, 0);

        OTTextView txtSuggestionType = (OTTextView) v.findViewById(R.id.txtSuggestionType);
        txtSuggestionType.setText(txtSeparator);
        ImageView imgSuggestionType = (ImageView) v.findViewById(R.id.imgSuggestionType);
        imgSuggestionType.setImageResource(imgSeparatorResourceId);

        lytSuggestion.addView(v, params);
    }

    /**
     * used to add the view in filtered suggestion list
     * @param lytSuggestion
     * @param txtSuggestion
     */
    private void addInSuggestionView(LinearLayout lytSuggestion, final String txtSuggestion, final String txtEntered, final int typeOriginDestination)
    {
        final ArrayList<String> list = (typeOriginDestination == TYPE_ORIGIN ? listSelectedOrigin : listSelectedDestination);
        final EditText editText = (typeOriginDestination == TYPE_ORIGIN ? edtOriginAirport : edtDestinationAirport);
        final OTTextView txtError = (typeOriginDestination == TYPE_ORIGIN ? txtOriginError : txtDestinationError);

        OTTextView tv = new OTTextView(getActivity());
        //Utils.DEBUG(txtSuggestion);
        //tv.setTextColor(Color.parseColor("#000000"));
        tv.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
        tv.setTypeface(null, Typeface.NORMAL);
        tv.setText(txtSuggestion);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utils.showToast(getActivity(), ((OTTextView)v).getText().toString());
                ((View)v.getParent()).setVisibility(View.GONE);
                (editText).setText(null);


                if(isAlreadySelected(list, (txtSuggestion)))
                {
                    //show error message
                    txtError.setText(getString(R.string.string_error_selection));
                    txtError.setVisibility(View.VISIBLE);
                }
                else
                {
                    if(typeOriginDestination == TYPE_DESTINATION)
                    {
                        if(listSelectedOrigin.size() == 0)
                        {
                            //show error message
                            txtError.setText(getString(R.string.string_error_selection_origin));
                            txtError.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            //add to list
                            (list).add((txtSuggestion));
                            //add to view selected selection
                            addSelectedSuggestion(typeOriginDestination, ((OTTextView)v).getText().toString());
                        }
                    }
                    else
                    {
                        //add to list
                        (list).add((txtSuggestion));
                        //add to view selected selection
                        addSelectedSuggestion(typeOriginDestination, ((OTTextView)v).getText().toString());
                    }
                }

                calculateODAndShowOnUI();


            }
        });
        lytSuggestion.addView(tv);
    }



    private void calculateODAndShowOnUI() {
        listValidSelectedOrigin.clear();
        listValidSelectedOrigin.addAll(getValidSelectedList(listSelectedOrigin));
        listValidSelectedDestination.clear();
        listValidSelectedDestination.addAll(getValidSelectedList(listSelectedDestination));


        for (int index = 0; index < listSelectedOrigin.size(); index++) {
            Utils.DEBUG("user selected origins : " + listSelectedOrigin.get(index));
        }

        for (int index = 0; index < listValidSelectedOrigin.size(); index++) {
            Utils.DEBUG("valid selected origins : " + listValidSelectedOrigin.get(index));
        }

        for (int index = 0; index < listSelectedDestination.size(); index++) {
            Utils.DEBUG("user selected destinations : " + listSelectedDestination.get(index));
        }

        for (int index = 0; index < listValidSelectedDestination.size(); index++) {
            Utils.DEBUG("valid selected destinations : " + listValidSelectedDestination.get(index));
        }

        //getValidZoneList();
        getValidZoneListJs();
        updateUIForFilteredTravelZones();
    }


    private ArrayList<String> getValidSelectedList(ArrayList<String> list)
    {
        ArrayList<String> temp = new ArrayList<>();
        for (int index = 0; index < listAirportData.size(); index++)
        {
            String strOD = listAirportData.get(index);
            for (int pos = 0; pos < list.size(); pos++)
            {
                if(strOD.contains(list.get(pos).replace(ALL_AIRPORTS, "")))
                {
                    String[] ary = strOD.split("\\$");
                    if(!isAlreadySelected(temp, ary[ary.length - 1]))
                    {
                        temp.add(ary[ary.length - 1]);
                    }
                }
            }
        }
        return temp;
    }

    private void updateUIForFilteredTravelZones()
    {
        lytZoneShortDescription.removeAllViews();
        lytZoneName.removeAllViews();
        txtResultError.setVisibility(View.GONE);


        if(listValidSelectedOrigin.size() == 0 || validSelectedODList.size() == 0)
        {
            if(validSelectedODList.size() == 0)
            {
                txtResultError.setText("No travel zones found for search criteria. Please try a different route. For best results, it is advised to include a hub airport as origin OR destination in the search.");
                txtResultError.setVisibility(View.VISIBLE);
            }
            addZoneNames();
            updateLayoutForShortDescription(flightPassDealData.getZoneData().getZone().get(0).getZoneName());
            return;
        }


        if((listValidSelectedOrigin.size() > 1 || listValidSelectedDestination.size() > 1))
        {
            if(fullMatchZoneDataList.size() == 0 && exactMatchZoneDataList.size() == 0)
            {
                txtResultError.setText("Some of your desired routes are not available");
                txtResultError.setVisibility(View.VISIBLE);
            }



            boolean isShowingFullMatchTitle = false;
            if(validSelectedODList.size() > 0 /*&& listValidSelectedDestination.size() > 0*/)
            {
                //add title
                addZoneView(lytZoneShortDescription, "Full Match");
                isShowingFullMatchTitle = true;

                String text = "";
                if(validSelectedODList.size() == 1)
                {
                    text = validSelectedODList.get(0);
                }
                else if(validSelectedODList.size() == 2)
                {
                    text = validSelectedODList.get(0) + " / " + validSelectedODList.get(1);
                }
                else
                {
                    text = validSelectedODList.size() + TEXT_ROUTES;
                }


                addZoneView(lytZoneShortDescription, TEXT_PERSONALISE + text);

            }
            for (int index = 0; index < exactMatchZoneDataList.size(); index++)
            {
                if(index == 0 && !isShowingFullMatchTitle)
                {
                    //add title
                    isShowingFullMatchTitle = true;
                    addZoneView(lytZoneShortDescription, "Full Match");
                }
                addZoneView(lytZoneShortDescription, exactMatchZoneDataList.get(index));
            }

            for (int index = 0; index < fullMatchZoneDataList.size(); index++)
            {
                if(index == 0 && !isShowingFullMatchTitle)
                {
                    //add title
                    isShowingFullMatchTitle = true;
                    addZoneView(lytZoneShortDescription, "Full Match");
                }
                addZoneView(lytZoneShortDescription, fullMatchZoneDataList.get(index));
            }

            for (int index = 0; index < atLeastOneMatchZoneDataList.size(); index++)
            {
                if(index == 0)
                {
                    //add title
                    addZoneView(lytZoneShortDescription, "Partial Match");
                }
                addZoneView(lytZoneShortDescription, atLeastOneMatchZoneDataList.get(index));
            }
        }
        else
        {
            //its not the case of atLeastOneMatchZoneDataList
            for (int index = 0; index < exactMatchZoneDataList.size(); index++)
            {
                addZoneView(lytZoneShortDescription, exactMatchZoneDataList.get(index));
            }

            for (int index = 0; index < fullMatchZoneDataList.size(); index++)
            {
                addZoneView(lytZoneShortDescription, fullMatchZoneDataList.get(index));
            }

        }
    }

    private boolean isContainsIds(String[] val)
    {
        for (int i = 0; i < val.length; i++) {
            try
            {
                Integer.parseInt(val[i]);
            }catch (Exception e)
            {
                return false;
            }
        }
        return true;
    }
    private void addZoneView(LinearLayout parent, final String description) {

        final String[] split = description.split("/");
        final boolean isContainsIds = isContainsIds(split);
        //Your personalized zone : 3 routes
        //Your personalized zone : DEL/GUI
        //Your personalized zone : DEL-BOM
        final boolean isPersonalisedZone = description.contains(TEXT_PERSONALISE) && (description.contains("/")
                                                || description.contains(TEXT_ROUTES) || description.contains("-"));



        RelativeLayout v = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_zone_select, null, false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) Utils.conertDpToPixel(getActivity(), 40));

        OTTextView txtZoneShortDescription = (OTTextView) v.findViewById(R.id.txtZoneShortDescription);
        String zoneName = null;
        txtZoneShortDescription.setText("" + Html.fromHtml(isContainsIds ? zoneName = getOtherZone(Integer.parseInt(split[0]), Integer.parseInt(split[1])) : description));
        if(!(txtZoneShortDescription.getText().toString().equals("Full Match") || txtZoneShortDescription.getText().toString().equals("Partial Match")))
        {
            txtZoneShortDescription.setTypeface(null, Typeface.NORMAL);
            v.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        else
        {
            txtZoneShortDescription.setTypeface(null, Typeface.BOLD);
            v.setBackgroundColor(Color.parseColor("#F3F3F3"));
        }


        ImageView imgArrow = (ImageView) v.findViewById(R.id.imgArrow);
        imgArrow.setVisibility(View.GONE);

        OTTextView txtSelect = (OTTextView) v.findViewById(R.id.txtSelect);
        txtSelect.setVisibility(isContainsIds || isPersonalisedZone ? View.VISIBLE : View.GONE);

        final String finalZoneName = zoneName;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isContainsIds || isPersonalisedZone)
                {
                    //Utils.showToast(getActivity(), finalZoneName);
                    Object object = null;
                    if(isPersonalisedZone)
                    {
                        for (int index = 0; index < atLeastOneMatchZoneDataList.size(); index++) {
                            String[] split1 = atLeastOneMatchZoneDataList.get(index).toString().split("/");
                            String zoneDescription = getOtherZone(Integer.parseInt(split1[0]), Integer.parseInt(split1[1]));
                            try {
                                if(zoneDescription.contains("-") || zoneDescription.contains("⇌"))//Paris⇌Rome/Delhi-Mumbai
                                {
                                    object = findSelectedZoneDetails(Integer.parseInt(split1[0]), Integer.parseInt(split1[1]));
                                    break;
                                }
                            }catch (Exception e)
                            {
                                break;
                            }

                        }

                    }
                    else
                    {
                        object = findSelectedZoneDetails(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    }

                    if(object != null)
                    {
                        if(object instanceof ZoneChild)
                        {
                            ZoneChild selectedZoneDetails = (ZoneChild) object;
                            setInSP(isPersonalisedZone, isPersonalisedZone ? description : selectedZoneDetails.getShortDesc(), selectedZoneDetails.getZoneSubGroupId(),
                                    selectedZoneDetails.getZoneGroupId(), selectedZoneDetails.getZoneId(), selectedZoneDetails.getTGpFGId());
                        }
                        else if(object instanceof SubChild)
                        {
                            SubChild selectedZoneDetails = (SubChild) object;
                            setInSP(isPersonalisedZone, isPersonalisedZone ? description : selectedZoneDetails.getShortDesc(), selectedZoneDetails.getZoneSubGroupId(),
                                    selectedZoneDetails.getZoneGroupId(), selectedZoneDetails.getZoneId(), selectedZoneDetails.getTGpFGId());
                        }

                        if(isPersonalisedZone)
                        {
                            callCustomZoneAPI(description);
                        }
                        else
                        {
                            moveToBack();
                        }


                    }
                }
            }
        });

        parent.addView(v, params);
    }


    private int isBidirectional = 0;
    private boolean isCustomeZonePartialMatch = false;
    ArrayList<String> exactMatchZoneDataList = new ArrayList<>();
    ArrayList<String> fullMatchZoneDataList = new ArrayList<>();
    ArrayList<String> atLeastOneMatchZoneDataList = new ArrayList<>();
    ArrayList<String> validSelectedODList = new ArrayList<>();
    ArrayList<String> validSelectedODList2 = new ArrayList<>();


    private void getValidZoneListJs()
    {
        int zoneId;
        int zoneGroupMemberId;
        String departAirportSetString;
        String ODstring;
        String[] ODList;
        int ODcount;

        exactMatchZoneDataList.clear();
        fullMatchZoneDataList.clear();
        atLeastOneMatchZoneDataList.clear();
        validSelectedODList.clear();
        validSelectedODList2.clear();

        ArrayList<String> selectedDepartCodeList = listValidSelectedOrigin;
        ArrayList<String> selectedArriveCodeList = listValidSelectedDestination;



        ArrayList<String> allODList = listAllOriginDestination;

        for (int k=0; k<allODList.size(); k++)
        {
            String str4 = allODList.get(k);
            boolean isExactMatch = false;
            boolean isFullMatch = false;
            boolean isPartialMatch = false;
            String[] array2 = str4.split("\\$");
            zoneId = Integer.parseInt(array2[0]);
            zoneGroupMemberId = Integer.parseInt(array2[1]);
            departAirportSetString = array2[2];
            ODstring = array2[3];
            ODList = ODstring.split("/");
            //ODList.splice(ODList.length - 1, 1);
            ODcount = Integer.parseInt(array2[4]);
            isBidirectional = Integer.parseInt(array2[5]);


            if (selectedArriveCodeList.size()==0){
                ArrayList<String> matchedDepartList = new ArrayList<>();
                int matchedODCount = 0;
                for(int j=0; j<ODList.length; j++){
                    for(int i=0; i<selectedDepartCodeList.size(); i++){
                        if(ODList[j].split("-")[0].indexOf(selectedDepartCodeList.get(i))>-1){
                            matchedODCount++;
                            isPartialMatch = true;
                            if(!isAlreadySelected(matchedDepartList, selectedDepartCodeList.get(i))){
                                matchedDepartList.add(selectedDepartCodeList.get(i));
                            }
                            if(!isAlreadySelected(validSelectedODList, ODList[j])){
                                validSelectedODList.add(ODList[j]);
                                validSelectedODList2.add(getLabel(ODList[j].split("-")[0])+"-"+getLabel(ODList[j].split("-")[1])+"#"+Integer.toString(isBidirectional));
                            }
                        }
                    }
                }
                int matchCount = matchedDepartList.size();
                if(isBidirectional==1){
                    if(matchCount==selectedDepartCodeList.size() && matchedODCount*2==ODcount){
                        isExactMatch = true;
                    }else if(matchCount==selectedDepartCodeList.size() && matchedODCount*2<ODcount){
                        isFullMatch = true;
                    }
                }else{
                    if(matchCount==selectedDepartCodeList.size() && matchedODCount==ODcount){
                        isExactMatch = true;
                    }else if(matchCount==selectedDepartCodeList.size() && matchedODCount<ODcount){
                        isFullMatch = true;
                    }
                }
            }
            else {
                int selectedODcount = 0;
                ArrayList<String> matchedODList = new ArrayList<>();
                for(int i=0; i<selectedDepartCodeList.size(); i++){
                    for(int j=0; j<selectedArriveCodeList.size(); j++){
                        selectedODcount++;
                        String selectedODString = selectedDepartCodeList.get(i)+"-"+selectedArriveCodeList.get(j);
                        if(ODstring.indexOf(selectedODString)>-1){
                            isPartialMatch = true;
                            if(!isAlreadySelected(validSelectedODList, selectedODString)){
                                validSelectedODList.add(selectedODString);
                                validSelectedODList2.add(getLabel(selectedDepartCodeList.get(i))+"-"+getLabel(selectedArriveCodeList.get(j))+"#"+Integer.toString(isBidirectional));
                            }
                            if(!isAlreadySelected(matchedODList, selectedODString)){
                                matchedODList.add(selectedODString);
                            }
                        }
                    }
                }
                int matchCount = matchedODList.size();
                if(isBidirectional==1){
                    if(matchCount==selectedODcount && matchCount*2==ODcount){
                        isExactMatch = true;
                    }else if(matchCount==selectedODcount && matchCount*2<ODcount){
                        isFullMatch = true;
                    }
                }else{
                    if(matchCount==selectedODcount && matchCount==ODcount){
                        isExactMatch = true;
                    }else if(matchCount==selectedODcount && matchCount<ODcount){
                        isFullMatch = true;
                    }
                }
            }

            if(isExactMatch){
                exactMatchZoneDataList.add(zoneGroupMemberId + "/" + zoneId);
                Utils.DEBUG("getValidZoneListJs() >> exactMatchZoneDataList : " + zoneGroupMemberId + "/" + zoneId + " > " + getOtherZone(zoneGroupMemberId, zoneId));
            }else if(isFullMatch){
                fullMatchZoneDataList.add(zoneGroupMemberId + "/" + zoneId);
                Utils.DEBUG("getValidZoneListJs() >> fullMatchZoneDataList : " + zoneGroupMemberId + "/" + zoneId + " > " + getOtherZone(zoneGroupMemberId, zoneId));
            }else if(isPartialMatch){
                atLeastOneMatchZoneDataList.add(zoneGroupMemberId + "/" + zoneId);
                Utils.DEBUG("getValidZoneListJs() >> atLeastOneMatchZoneDataList : " + zoneGroupMemberId + "/" + zoneId + " > " + getOtherZone(zoneGroupMemberId, zoneId));
            }
        }

        Collections.sort(validSelectedODList,
                new Comparator<String>()
                {
                    public int compare(String f1, String f2)
                    {
                        return f1.toString().compareTo(f2.toString());
                    }
                });

        Collections.sort(validSelectedODList2,
                new Comparator<String>()
                {
                    public int compare(String f1, String f2)
                    {
                        return f1.toString().compareTo(f2.toString());
                    }
                });

        if(selectedDepartCodeList.size() * selectedArriveCodeList.size() > validSelectedODList.size()){
            isCustomeZonePartialMatch = true;
        }

        filterZones(exactMatchZoneDataList, fullMatchZoneDataList, atLeastOneMatchZoneDataList);

        Utils.DEBUG("getValidZoneListJs() >> suggested zones size : " + validSelectedODList.size());
        for (int index = 0; index < validSelectedODList.size(); index++) {
            Utils.DEBUG("getValidZoneListJs() >> suggested zones : " + validSelectedODList.get(index));
        }

        /*for (int index = 0; index < exactMatchZoneDataList.size(); index++) {
            Utils.DEBUG("getValidZoneListJs() >> exactMatchZoneDataList : " + exactMatchZoneDataList.get(index));
        }

        for (int index = 0; index < fullMatchZoneDataList.size(); index++) {
            Utils.DEBUG("getValidZoneListJs() >> fullMatchZoneDataList : " + fullMatchZoneDataList.get(index));
        }

        for (int index = 0; index < atLeastOneMatchZoneDataList.size(); index++) {
            Utils.DEBUG("getValidZoneListJs() >> atLeastOneMatchZoneDataList : " + atLeastOneMatchZoneDataList.get(index));
        }*/

        for (int index = 0; index < validSelectedODList.size(); index++) {
            //Utils.DEBUG("getValidZoneListJs() >> validSelectedODList : " + validSelectedODList.get(index));
        }

        for (int index = 0; index < validSelectedODList2.size(); index++) {
            //Utils.DEBUG("getValidZoneListJs() >> validSelectedODList2 : " + validSelectedODList2.get(index));
        }
    }

    private void filterZones(ArrayList<String> exactMatchList, ArrayList<String> fullMatchList, ArrayList<String> atLeastOneMatchList)
    {
        if((listSelectedOrigin.size()>0 || listSelectedDestination.size()>0) && (exactMatchList.size()>0 || fullMatchList.size()>0 || atLeastOneMatchList.size()>0))
        {

        }

    }


    private String getLabel(String airportCode){
        String label = "";
        for (int i=0; i<listAirportData.size(); i++){
            String[] arr = listAirportData.get(i).split("$");
            if(arr[arr.length-1].equals(airportCode)){
                label = arr[1].split(",")[0]+" ("+airportCode+")";
                break;
            }
        }
        return label;
    }

    private void getValidZoneList()
    {
        int isBidirectional = 0;
        boolean isCustomeZonePartialMatch = false;
        boolean isExactMatch = false;
        boolean isFullMatch = false;
        boolean isPartialMatch = false;
        ArrayList<String> listValidSelectedOD = new ArrayList<>();

        for (int index = 0; index < listAllOriginDestination.size(); index++) {
            String strODAtPos = listAllOriginDestination.get(index);

            String[] arySplit = strODAtPos.split("\\$");
            int zoneId = Integer.parseInt(arySplit[0]);
            int zoneGroupMemberId = Integer.parseInt(arySplit[1]);
            String strOriginAirportSet = arySplit[2];
            String strODSet = arySplit[3];
            String[] aryOD = strODSet.split("/");
            int countOD = Integer.parseInt(arySplit[4]);
            isBidirectional = Integer.parseInt(arySplit[5]);

            if(listValidSelectedDestination.size() == 0)
            {
                int matchedODCount = 0;
                ArrayList<String> listMatchedOrigin = new ArrayList<>();
                for (int j = 0; j < aryOD.length; j++)
                {
                    for (int i = 0; i < listValidSelectedOrigin.size(); i++)
                    {
                        if(aryOD[j].split("\\-")[0].contains(listValidSelectedOrigin.get(i)))
                        {
                            matchedODCount++;
                            isPartialMatch = true;
                            if(!isAlreadySelected(listMatchedOrigin, listValidSelectedOrigin.get(i)))
                            {
                                listMatchedOrigin.add(listValidSelectedOrigin.get(i));
                            }

                            if(!isAlreadySelected(listValidSelectedOD, aryOD[j]))
                            {
                                listValidSelectedOD.add(aryOD[j]);
                                Utils.DEBUG("getOtherZone >> " + zoneGroupMemberId + "/" + zoneId + " > " + getOtherZone(zoneGroupMemberId, zoneId));;
                            }
                        }
                    }
                }

                if(isBidirectional == 1)
                {
                    if(listMatchedOrigin.size() == listValidSelectedOrigin.size() && matchedODCount*2 == countOD){
                        isExactMatch = true;
                    }else if(listMatchedOrigin.size() == listValidSelectedOrigin.size() && matchedODCount*2 < countOD){
                        isFullMatch = true;
                    }
                }else{
                    if(listMatchedOrigin.size() == listValidSelectedOrigin.size() && matchedODCount == countOD){
                        isExactMatch = true;
                    }else if(listMatchedOrigin.size() == listValidSelectedOrigin.size() && matchedODCount < countOD){
                        isFullMatch = true;
                    }
                }
            }
            else
            {
                int selectedODcount = 0;
                ArrayList<String> listMatchedOD = new ArrayList<>();
                for (int i = 0; i < listValidSelectedOrigin.size(); i++)
                {
                    for (int j = 0; j < listValidSelectedDestination.size(); j++)
                    {
                        selectedODcount++;
                        String selectedODString = listValidSelectedOrigin.get(i) + "-" + listValidSelectedDestination.get(j);

                        if(strODSet.contains(selectedODString))
                        {
                            isPartialMatch = true;

                            if(!isAlreadySelected(listMatchedOD, selectedODString))
                            {
                                listMatchedOD.add(selectedODString);
                            }

                            if(!isAlreadySelected(listValidSelectedOD, selectedODString))
                            {
                                listValidSelectedOD.add(selectedODString);
                                Utils.DEBUG("getOtherZone >> " + zoneGroupMemberId + "/" + zoneId + " > " + getOtherZone(zoneGroupMemberId, zoneId));;
                            }
                        }
                    }
                }

                if(isBidirectional == 1)
                {
                    if(listMatchedOD.size() == selectedODcount && listMatchedOD.size()*2 == countOD){
                        isExactMatch = true;
                    }else if(listMatchedOD.size() == selectedODcount && listMatchedOD.size()*2 < countOD){
                        isFullMatch = true;
                    }
                }else{
                    if(listMatchedOD.size() == selectedODcount && listMatchedOD.size() == countOD){
                        isExactMatch = true;
                    }else if(listMatchedOD.size() == selectedODcount && listMatchedOD.size() < countOD){
                        isFullMatch = true;
                    }
                }
            }

            if(isExactMatch){
                //exactMatchZoneDataList.push($.trim(zoneGroupMemberId) + "/" + $.trim(zoneId));
                //Utils.DEBUG("exactMatchZoneDataList >> " + zoneGroupMemberId + "/" + zoneId);
                //getOtherZone(zoneGroupMemberId, zoneId);
            }else if(isFullMatch){
                //fullMatchZoneDataList.push($.trim(zoneGroupMemberId) + "/" + $.trim(zoneId));
                //Utils.DEBUG("fullMatchZoneDataList >> " + zoneGroupMemberId + "/" + zoneId);
                //getOtherZone(zoneGroupMemberId, zoneId);
            }else if(isPartialMatch){
                //atLeastOneMatchZoneDataList.push($.trim(zoneGroupMemberId) + "/" + $.trim(zoneId));
                //Utils.DEBUG("atLeastOneMatchZoneDataList >> " + zoneGroupMemberId + "/" + zoneId);
                //getOtherZone(zoneGroupMemberId, zoneId);
            }
        }

        if(listValidSelectedOrigin.size() * listValidSelectedDestination.size() > listValidSelectedOD.size()){
            isCustomeZonePartialMatch = true;
        }

        Utils.DEBUG("getValidZoneList() >> suggested zones size : " + listValidSelectedOD.size());
        for (int index = 0; index < listValidSelectedOD.size(); index++) {
            Utils.DEBUG("getValidZoneList() >> suggested zones : " + listValidSelectedOD.get(index));
        }
    }

    private String getOtherZone(int zoneGroupMemberId, int zoneId)
    {
        List<Zone> listZone = flightPassDealData.getZoneData().getZone();
        for(int index = 0; index < listZone.size(); index++) {
            Zone zone = listZone.get(index);
            final List<ZoneChild> listZoneChild = zone.getZoneChild();

            for (int pos = 0; pos < listZoneChild.size(); pos++) {
                final ArrayList<SubChild> listSubChild = listZoneChild.get(pos).getSubChild();

                if(listZoneChild.get(pos).getTGpFGId() == zoneGroupMemberId && listZoneChild.get(pos).getZoneId() == zoneId)
                {
                    //Utils.DEBUG("getOtherZone() >> " + zoneGroupMemberId + "/" + zoneId + " >> " +  listZoneChild.get(pos).getShortDesc());
                    return listZoneChild.get(pos).getShortDesc();
                }

                for (int loc = 0; loc < listSubChild.size(); loc++)
                {
                    if(listSubChild.get(loc).getTGpFGId() == zoneGroupMemberId && listSubChild.get(loc).getZoneId() == zoneId)
                    {
                        //Utils.DEBUG("getOtherZone() >> " + zoneGroupMemberId + "/" + zoneId + " >> " +  listZoneChild.get(loc).getShortDesc());
                        return listSubChild.get(loc).getShortDesc();
                    }
                }
            }
        }
        return "";
    }

    private Object findSelectedZoneDetails(int zoneGroupMemberId, int zoneId)
    {
        List<Zone> listZone = flightPassDealData.getZoneData().getZone();
        for(int index = 0; index < listZone.size(); index++) {
            Zone zone = listZone.get(index);
            final List<ZoneChild> listZoneChild = zone.getZoneChild();

            for (int pos = 0; pos < listZoneChild.size(); pos++) {
                final ArrayList<SubChild> listSubChild = listZoneChild.get(pos).getSubChild();

                if(listZoneChild.get(pos).getTGpFGId() == zoneGroupMemberId && listZoneChild.get(pos).getZoneId() == zoneId)
                {
                    //Utils.DEBUG("getOtherZone() >> " + zoneGroupMemberId + "/" + zoneId + " >> " +  listZoneChild.get(pos).getShortDesc());
                    return listZoneChild.get(pos);
                }

                for (int loc = 0; loc < listSubChild.size(); loc++)
                {
                    if(listSubChild.get(loc).getTGpFGId() == zoneGroupMemberId && listSubChild.get(loc).getZoneId() == zoneId)
                    {
                        //Utils.DEBUG("getOtherZone() >> " + zoneGroupMemberId + "/" + zoneId + " >> " +  listZoneChild.get(loc).getShortDesc());
                        return listSubChild.get(loc);
                    }
                }
            }
        }
        return null;
    }

    /**
     * used to set view for selected option
     * @param txtName
     */
    private void addSelectedSuggestion(final int typeOriginDestination, final String txtName)
    {
        final PredicateLayout layout = typeOriginDestination == TYPE_ORIGIN ? lytSelectedOriginAirport : lytSelectedDestinationAirport;
        final List list = (typeOriginDestination == TYPE_ORIGIN ? listSelectedOrigin : listSelectedDestination);
        final EditText editText = (typeOriginDestination == TYPE_ORIGIN ? edtOriginAirport : edtDestinationAirport);

        final LinearLayout v = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_airport_select, null, false);

        OTTextView txtZoneShortDescription = (OTTextView) v.findViewById(R.id.txtZoneShortDescription);
        txtZoneShortDescription.setText(getTrimmedNameToShow(txtName));

        ImageView imgClose = (ImageView) v.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.removeView(v);
                layout.invalidate();

                list.remove(txtName);

                editText.setHint((list.size() > 0 ? (getString(R.string.string_add_more_letter)) : (getString(R.string.string_airport_city_country_letter))));

                calculateODAndShowOnUI();

                if(typeOriginDestination == TYPE_ORIGIN && list.size() == 1 && ((int)sf.get(getString(R.string.key_selected_airline_id))) == 975)
                {
                    lytOriginInput.setVisibility(View.GONE);
                }
                else if(typeOriginDestination == TYPE_ORIGIN && list.size() == 0 && ((int)sf.get(getString(R.string.key_selected_airline_id))) == 975)
                {
                    lytOriginInput.setVisibility(View.VISIBLE);
                }

            }
        });
        editText.setHint((list.size() > 0 ? (getString(R.string.string_add_more_letter)) : (getString(R.string.string_airport_city_country_letter))));

        if(typeOriginDestination == TYPE_ORIGIN && list.size() == 1 && ((int)sf.get(getString(R.string.key_selected_airline_id))) == 975)
        {
            lytOriginInput.setVisibility(View.GONE);
        }



        layout.addView(v);
    }


    /**
     * return true if given txtName exits in list otherwise false
     * @param list
     * @param txtName
     * @return
     */
    private boolean isAlreadySelected(ArrayList<String> list, String txtName) {
        for (int index = 0; index < list.size(); index++) {
            if(txtName.trim().equals(list.get(index)))
            {
                return true;
            }
        }
        return false;
    }


    /**
     * used to trimmed name to show to user as selected option  text
     * @param txtName
     * @return
     */
    private String getTrimmedNameToShow(String txtName)
    {
        if(txtName == null)
        {
            return "";
        }

        if(txtName.length() > 14)
        {
            return txtName.substring(0, 14) + "..";
        }
        else
        {
            return txtName;
        }
    }


    private void callCustomZoneAPI(String personaliseZoneName)
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_customZoneData);

        final JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("departCodeList", getCommaSeparatedValues(listValidSelectedOrigin));//BOM,DEL
            requestObject.put("arriveCodeList", getCommaSeparatedValues(listValidSelectedDestination));//GOI
            requestObject.put("zoneName", personaliseZoneName);//Your personalized zone : BOM-GOI / DEL-GOI
            requestObject.put("zoneDescLong", "");//Travel valid on all flights operated by Vistara on the following routes:<br/>BOM-GOI,DEL-GOI
            requestObject.put("customZoneDescLong", "");//
            /*<p>Travel valid on all flights operated by Vistara on the following routes:</p>

            <table class="headTable" cellspacing="0" cellpadding="0" border="0" width="100%">
            <tbody><tr><td style="width:30%;">Origin</td><td style="width:60%;">Destination</td><td style="text-align:center;">Direction</td>	</tr>
            </tbody></table>
            <div style="max-height: 200px; overflow-x: hidden; overflow-y:auto;">
            <table id="zoneDescTable" cellspacing="0" cellpadding="0" border="0" width="100%"><tbody><tr><td style="width:30%; padding-right: 5px;">New Delhi (DEL)</td> <td style="width:60%; padding-right: 5px; ">Goa (GOI)</td><td style="width:10%; text-align:center; ">BothWays</td></tr><tr><td style="width:30%; padding-right: 5px;">Mumbai (BOM)</td> <td style="width:60%; padding-right: 5px; ">Goa (GOI)</td><td style="width:10%; text-align:center; ">BothWays</td></tr></tbody></table>
            </div>
            <div id="detailDescShowButton" class="detailDescButton">[+] View All Routes</div>
            <div id="detailDescHideButton" class="detailDescButton" style="display:none">[-] Hide All Routes</div>
            <div id="detailDescDiv">
            <table class="headTable" cellspacing="0" cellpadding="0" border="0" width="100%">
            <tbody><tr><td style="width:30%;"><strong>Origin</strong></td><td style="width:60%;"><strong>Destination</strong></td><td style="text-align:center;"><strong>Direction</strong></td>	</tr>
            </tbody></table>
            <div style="max-height: 300px; overflow-x: hidden; overflow-y:auto">
            <table id="detailDesc" style="z-index: 210" cellspacing="0" cellpadding="0" border="0" width="100%"><tbody><tr><td style="width:30%; padding-right: 5px;">Mumbai (BOM)</td> <td style="width:60%; padding-right: 5px; ">Goa (GOI)</td><td style="width:10%; text-align:center; ">BothWays</td></tr><tr><td style="width:30%; padding-right: 5px;">New Delhi (DEL)</td> <td style="width:60%; padding-right: 5px; ">Goa (GOI)</td><td style="width:10%; text-align:center; ">BothWays</td></tr></tbody></table>
            </div>
            </div>*/

            requestObject.put("tgpFgShortDesc", getCommaSeparatedValues(validSelectedODList));//BOM-GOI,DEL-GOI
        }
        catch (Exception e)
        {
            Utils.ERROR("Error while creating json request : " + e.toString());
            e.printStackTrace();
        }
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();



        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
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
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        try
                        {
                            CustomZoneData customZoneData = ParseManager.getInstance().fromJSON(response, CustomZoneData.class);

                            if(customZoneData.getSucess())
                            {
                                setInSP(true, customZoneData.getZoneName(), customZoneData.getZonesubGroupId(),
                                        0, customZoneData.getZoneId(), customZoneData.getTgpFgId());//0 as api does not return 'zoneGroupId'

                                moveToBack();
                            }
                        }catch (Exception e)
                        {
                            //error
                        }

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

    private String getCommaSeparatedValues(ArrayList<String> listValidSelectedOrigin) {

        if(listValidSelectedOrigin == null || listValidSelectedOrigin.isEmpty())
        {
            return "";
        }

        StringBuffer buffer = new StringBuffer();
        for (int index = 0; index < listValidSelectedOrigin.size(); index++)
        {
            buffer.append(listValidSelectedOrigin.get(index).toString());
            if(index != listValidSelectedOrigin.size() - 1)//last
            {
                buffer.append(",");
            }

        }

        return buffer.toString();
    }
}

package com.optiontown.app.view.fragment.fpo.flightpass;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.adapter.SearchSelectRecyclerViewAdapter;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.selectproduct.AdvanceBookingList1;
import com.optiontown.app.model.selectproduct.AdvanceBookingList2;
import com.optiontown.app.model.selectproduct.AirlineDropDownArray;
import com.optiontown.app.model.selectproduct.CabinArray;
import com.optiontown.app.model.selectproduct.FlexibilitySetList;
import com.optiontown.app.model.selectproduct.FlightPassDealData;
import com.optiontown.app.model.selectproduct.FlightsList;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.model.selectproduct.NotflexibilitySetList;
import com.optiontown.app.model.selectproduct.PasTypeGroupList;
import com.optiontown.app.model.selectproduct.PassDataNormal;
import com.optiontown.app.model.selectproduct.Restriction;
import com.optiontown.app.model.selectproduct.VList;
import com.optiontown.app.model.selectproduct.Validity;
import com.optiontown.app.model.selectproduct.Value;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTRadioButton;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by amit on 04-06-2016.
 */
public class FlightPassSearchSelectFragment extends BaseFragment {
    InternationalizeData localization;
    private View view;
    private OTTextView txtSearch;
    private OTTextView txtSelectedItem;
    private ImageView imgSelectedItem;
    private NetworkImageView imgSelectedItemNetwork;
    private ImageLoader imageLoader;
    private Communicator communicator;
    private AppSharedPrefs sf;
    private FlightPassDealData flightPassDealData;
    private int viewType = 0;
    private ImageView imgDone;
    private String title;
    private RadioGroup radioGroupAdvanceBooking;
    private LinearLayout lytAdvanceBooking;
    private OTTextView txtTravelValidFrom;
    private OTTextView txtTravelPeriod;
    private LinearLayout lytMonthList;
    private OTTextView txtHelp;
    private FragmentActivity fragmentActivity;
    private String strHelp;
    private LinearLayout lytFlexibility;
    private RadioGroup radioGroupFliexibility;
    /**
     * to keep temp values
     */
    private String tag = "";
    private int id = 0;
    private int passTypeId = 1;
    private String travel_from = "";
    private OTTextView txtTravelValidFromLabel;
    private ScrollView svTravelPeriod;
    private LinearLayout lytFMMatrix;
    private RadioGroup radioGroupFMMatrix;
    private String passGroupName;
    private LinearLayout lytMonthListRow;
    private LinearLayout lytTravelPeriod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_flight_pass_search_select, container, false);

        //---get data for localization
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }



        sf = AppSharedPrefs.getInstance(getActivity());

        Utils.DEBUG(this.getClass().getSimpleName() + " >> received : " + getArguments().toString());


        flightPassDealData = ((FlightPassDealData) getArguments().getSerializable(getString(R.string.key_serializable)));
        viewType = (int) getArguments().getInt(getString(R.string.key_view_type));

        //--

        initUI(view);



        return view;
    }

    private void initUI(View view) {

        lytAdvanceBooking = (LinearLayout) this.view.findViewById(R.id.lytAdvanceBooking);
        radioGroupAdvanceBooking = (RadioGroup) this.view.findViewById(R.id.radioGroupAdvanceBooking);

        svTravelPeriod = (ScrollView) this.view.findViewById(R.id.svTravelPeriod);
        lytTravelPeriod = (LinearLayout) this.view.findViewById(R.id.lytTravelPeriod);
        txtTravelValidFrom = (OTTextView) this.view.findViewById(R.id.txtTravelValidFrom);
        txtTravelValidFromLabel = (OTTextView) this.view.findViewById(R.id.txtTravelValidFromLabel);
        txtTravelPeriod = (OTTextView) this.view.findViewById(R.id.txtTravelPeriod);
        lytMonthList = (LinearLayout) this.view.findViewById(R.id.lytMonthList);

        lytFlexibility = (LinearLayout) this.view.findViewById(R.id.lytFlexibility);
        radioGroupFliexibility = (RadioGroup) this.view.findViewById(R.id.radioGroupFliexibility);

        lytFMMatrix = (LinearLayout) this.view.findViewById(R.id.lytFMMatrix);
        radioGroupFMMatrix = (RadioGroup) this.view.findViewById(R.id.radioGroupFMMatrix);



        RecyclerView recyclerView = (RecyclerView) this.view.findViewById(R.id.recyclerViewSelect);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        RecyclerView.Adapter adapter = new SearchSelectRecyclerViewAdapter(getActivity(), viewType, flightPassDealData, new SearchSelectRecyclerViewAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, int position, Object item) {
                if (item instanceof AirlineDropDownArray) {
                    tag = (((AirlineDropDownArray) item).getAirlline());
                    id = (((AirlineDropDownArray) item).getAirlineId());
                    txtSelectedItem.setText(tag);
                } else if (item instanceof CabinArray) {
                    tag = ((CabinArray) item).getCabinName();
                    id = ((CabinArray) item).getCabinId();
                    txtSelectedItem.setText(tag);
                } else if (item instanceof PassDataNormal) {
                    tag = ((PassDataNormal) item).getLABLPassengers();
                    id = ((PassDataNormal) item).getValue();
                    passTypeId = ((PassDataNormal) item).getPassTypeId();
                    txtSelectedItem.setText(tag);
                } else if (item instanceof PasTypeGroupList) {

                    tag = ((PasTypeGroupList) item).getLABLPassengers();
                    id = Integer.parseInt(((PasTypeGroupList) item).getValue());
                    passTypeId = ((PasTypeGroupList) item).getPassTypeId();

                    txtSelectedItem.setText(tag);
                } else if (item instanceof FlightsList) {
                    tag = ((FlightsList) item).getCreditValue();
                    id = ((FlightsList) item).getCreditId();
                    txtSelectedItem.setText(tag);
                }
            }
        });
        recyclerView.setAdapter(adapter);

        imgDone = (ImageView) view.findViewById(R.id.imgDone);
        imgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_AIRLINE == viewType) {
                    imgDone.setOnClickListener(null);
                    Utils.DEBUG("selected airline id : " + id + ", selected airline name : " + tag);
                    sf.put(getString(R.string.key_selected_airline_id), id);
                    sf.put(getString(R.string.key_selected_airline_tag), tag);

                    callUpdateAirlineAPI();

                    return;
                } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_CABIN == viewType) {
                    Utils.DEBUG("selected cabin id : " + id + ", selected cabin name : " + tag);
                    if (id != 0) {
                        sf.put(getString(R.string.key_selected_cabin_id), id);
                        sf.put(getString(R.string.key_selected_cabin_tag), tag);
                    }
                } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_PASSENGER == viewType) {
                    Utils.DEBUG("selectedPassenger id : " + id + ", selectedPassenger name : " + tag + ", selectedPassenger PassTypeId : "+passTypeId);
                    if (id != 0) {
                        sf.put(getString(R.string.key_selected_passenger_id), id);
                        sf.put(getString(R.string.key_selected_passenger_tag), tag);
                        sf.put(getString(R.string.key_selected_passenger_pass_type_id), passTypeId);

                    }
                } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_FLIGHT == viewType) {
                    if (id != 0) {
                        sf.put(getString(R.string.key_selected_flight_id), id);
                        sf.put(getString(R.string.key_selected_flight_tag), tag);
                    }
                } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_TRAVEL_PERIOD == viewType) {
                    Utils.DEBUG("selected travel period id : " + id + ", selected travel period name : " + tag + ", travel_from : " + travel_from);
                    if (id != 0) {
                        sf.put(getString(R.string.key_selected_travel_period_month_id), id);
                        sf.put(getString(R.string.key_selected_travel_period_month_tag), tag);
                        sf.put(getString(R.string.key_selected_travel_period_valid_from), travel_from);
                    }
                } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_ADVANCE_BOOKING == viewType) {
                    sf.put(getString(R.string.key_selected_advance_booking_id), id);
                    sf.put(getString(R.string.key_selected_advance_booking_tag), tag);
                } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_FLEXIBILITY == viewType) {
                    sf.put(getString(R.string.key_selected_flexibility_id), id);
                    sf.put(getString(R.string.key_selected_flexibility_tag), tag);
                } else if (viewType - SearchSelectRecyclerViewAdapter.VIEW_TYPE_FM_MATRIX_BASE >= 0)//FM Matrix feature
                {
                    int fId = viewType - SearchSelectRecyclerViewAdapter.VIEW_TYPE_FM_MATRIX_BASE;
                    sf.put(getString(R.string.key_selected_fm_matrix_id) + fId, id);
                    sf.put(getString(R.string.key_selected_fm_matrix_tag) + fId, tag);
                }
                //update FlightPassSearchFragment
                communicateToFragment(false);

                //back
                ((MainActivity)getActivity()).onBackPressed();
            }
        });

        /*imgDone = (ImageView) view.findViewById(R.id.imgDone);
        imgDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });*/

        txtSelectedItem = (OTTextView) this.view.findViewById(R.id.txtSelectedItem);
        imgSelectedItem = (ImageView) this.view.findViewById(R.id.imgSelectedItem);
        imgSelectedItemNetwork = (NetworkImageView) this.view.findViewById(R.id.imgSelectedItemNetwork);
        imageLoader = AppController.getInstance().getImageLoader();

        if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_AIRLINE == viewType) {
            lytFMMatrix.setVisibility(View.GONE);
            lytFlexibility.setVisibility(View.GONE);
            lytAdvanceBooking.setVisibility(View.GONE);
            svTravelPeriod.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            txtSelectedItem.setText((String) sf.get(getString(R.string.key_selected_airline_tag)));
            imgSelectedItem.setImageResource(R.drawable.airline);
            title = flightPassDealData.getAirLineList().getTitle();
            strHelp = flightPassDealData.getAirLineList().getHelp();

        } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_CABIN == viewType) {
            lytFMMatrix.setVisibility(View.GONE);
            lytFlexibility.setVisibility(View.GONE);
            lytAdvanceBooking.setVisibility(View.GONE);
            svTravelPeriod.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            txtSelectedItem.setText((String) sf.get(getString(R.string.key_selected_cabin_tag)));
            imgSelectedItem.setImageResource(R.drawable.cabin);
            title = flightPassDealData.getCabinList().getTitle();
            strHelp = flightPassDealData.getCabinList().getHelp();
        } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_PASSENGER == viewType) {
            lytFMMatrix.setVisibility(View.GONE);
            lytFlexibility.setVisibility(View.GONE);
            lytAdvanceBooking.setVisibility(View.GONE);
            svTravelPeriod.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            txtSelectedItem.setText((String) sf.get(getString(R.string.key_selected_passenger_tag)));
            imgSelectedItem.setImageResource(R.drawable.newpassenger_icon);
            title = flightPassDealData.getPassList().getTitle();
            strHelp = flightPassDealData.getPassList().getHelp();
        } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_FLIGHT == viewType) {
            lytFMMatrix.setVisibility(View.GONE);
            lytFlexibility.setVisibility(View.GONE);
            lytAdvanceBooking.setVisibility(View.GONE);
            svTravelPeriod.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            txtSelectedItem.setText((String) sf.get(getString(R.string.key_selected_flight_tag)));
            imgSelectedItem.setImageResource(R.drawable.flight);
            title = flightPassDealData.getFList().getTitle();
            strHelp = flightPassDealData.getFList().getHelp();
        } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_TRAVEL_PERIOD == viewType) {
            lytFMMatrix.setVisibility(View.GONE);
            lytFlexibility.setVisibility(View.GONE);
            lytAdvanceBooking.setVisibility(View.GONE);
            svTravelPeriod.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            txtSelectedItem.setText(Utils.getSelectedTextForValidPeriod(getActivity(), (String) sf.get(getString(R.string.key_selected_travel_period_valid_from)), (String) sf.get(getString(R.string.key_selected_travel_period_month_tag))));
            imgSelectedItem.setImageResource(R.drawable.depart_date);
            travel_from = (String) sf.get(getString(R.string.key_selected_travel_period_valid_from));
            tag = (String) sf.get(getString(R.string.key_selected_travel_period_month_tag));
            id = (int) sf.get(getString(R.string.key_selected_travel_period_month_id));

            txtTravelValidFrom.setText((String) sf.get(getString(R.string.key_selected_travel_period_valid_from)));
            txtTravelValidFromLabel.setText(flightPassDealData.getFList().getValidity().getTravelValidLabel());
            txtTravelPeriod.setText((String) sf.get(getString(R.string.key_selected_travel_period_month_tag)));
            strHelp = flightPassDealData.getFList().getValidity().getHelp();
            title = flightPassDealData.getFList().getValidity().getTitle();

            txtTravelValidFrom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initializeCalendar();
                    lytMonthList.setVisibility(View.GONE);
                }
            });

            txtTravelPeriod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initalizeMonthDropDownList();

                }
            });

            lytTravelPeriod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lytMonthList.setVisibility(View.GONE);

                }
            });

        } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_ADVANCE_BOOKING == viewType) {
            lytFMMatrix.setVisibility(View.GONE);
            lytFlexibility.setVisibility(View.GONE);
            lytAdvanceBooking.setVisibility(View.VISIBLE);
            svTravelPeriod.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            txtSelectedItem.setText((String) sf.get(getString(R.string.key_selected_advance_booking_tag)));
            imgSelectedItem.setImageResource(R.drawable.earlybook_icon);
            title = flightPassDealData.getAdvanceBookingList().getTitle();
            strHelp = flightPassDealData.getAdvanceBookingList().getHelp();

            //add radio option and label
            int advanceBookingId = flightPassDealData.getAdvanceBookingList().getAdvanceBookingId();
            ArrayList<AdvanceBookingList1> listAdvanceBooking1 = flightPassDealData.getAdvanceBookingList().getAdvanceBookingList1();
            ArrayList<AdvanceBookingList2> listAdvanceBooking2 = flightPassDealData.getAdvanceBookingList().getAdvanceBookingList2();

            OTTextView txtBookingMainLabel1 = new OTTextView(getActivity());
            radioGroupAdvanceBooking.addView(txtBookingMainLabel1);
            txtBookingMainLabel1.setText(listAdvanceBooking1.get(0).getBookingMainLabel());

            for (final AdvanceBookingList1 data : listAdvanceBooking1) {

                OTRadioButton radioButton = new OTRadioButton(getActivity());
                //radioButton.setChecked(advanceBookingId == data.getAdvanceBookingId());
                radioButton.setText(data.getAdvanceAnyTimeLabel());
                radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
                radioButton.setTextColor(getResources().getColor(R.color.color_font_gray_dark));
                radioButton.setTypeface(null, Typeface.NORMAL);
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            tag = data.getBeforeTravelTag();
                            id = data.getAdvanceBookingId();
                            txtSelectedItem.setText(tag);
                        }

                    }
                });


                radioGroupAdvanceBooking.addView(radioButton);
                if (advanceBookingId == data.getAdvanceBookingId()) {
                    radioGroupAdvanceBooking.check(radioButton.getId());
                }
            }

            OTTextView txtBookingMainLabel2 = new OTTextView(getActivity());
            radioGroupAdvanceBooking.addView(txtBookingMainLabel2);
            try{
                txtBookingMainLabel2.setText(listAdvanceBooking2.get(0).getBookingMainLabel());
            }catch (Exception e){
                e.printStackTrace();
            }


            for (final AdvanceBookingList2 data : listAdvanceBooking2) {
                OTRadioButton radioButton = new OTRadioButton(getActivity());
                //radioButton.setChecked(advanceBookingId == data.getAdvanceBookingId());
                radioButton.setText(data.getAdvanceAnyTimeLabel());
                radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
                radioButton.setTextColor(getResources().getColor(R.color.color_font_gray_dark));
                radioButton.setTypeface(null, Typeface.NORMAL);
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            tag = data.getBeforeTravelTag();
                            id = data.getAdvanceBookingId();
                            txtSelectedItem.setText(tag);
                        }
                    }
                });


                radioGroupAdvanceBooking.addView(radioButton);
                if (advanceBookingId == data.getAdvanceBookingId()) {
                    radioGroupAdvanceBooking.check(radioButton.getId());
                }
            }

        } else if (SearchSelectRecyclerViewAdapter.VIEW_TYPE_FLEXIBILITY == viewType) {
            lytFMMatrix.setVisibility(View.GONE);
            lytFlexibility.setVisibility(View.VISIBLE);
            lytAdvanceBooking.setVisibility(View.GONE);
            svTravelPeriod.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            txtSelectedItem.setText((String) sf.get(getString(R.string.key_selected_flexibility_tag)));
            imgSelectedItem.setImageResource(R.drawable.travel_flexibility_customize);
            title = flightPassDealData.getFlexibilitySetList().getTitle();
            strHelp = flightPassDealData.getFlexibilitySetList().getHelp();

            final ArrayList<NotflexibilitySetList> notflexibilitySetLists = flightPassDealData.getFlexibilitySetList().getNotflexibilitySetList();
            final ArrayList<FlexibilitySetList> flexibilitySetLists = flightPassDealData.getFlexibilitySetList().getFlexibilitySetList();

            int flexibilityId = flightPassDealData.getFlexibilitySetList().getFlexibilityId();

            for (int index = 0; index < notflexibilitySetLists.size(); index++) {
                OTRadioButton radioButton = new OTRadioButton(getActivity());
                radioButton.setText(notflexibilitySetLists.get(index).getFlexibilityLabel());
                radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
                radioButton.setTextColor(getResources().getColor(R.color.color_font_gray_dark));
                radioButton.setTypeface(null, Typeface.NORMAL);
                final int finalIndex = index;
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            tag = Utils.getFlexibilityLabelTag(notflexibilitySetLists.get(finalIndex).getFlexibilityLabel());
                            id = notflexibilitySetLists.get(finalIndex).getFlexibilityRangeId();
                            txtSelectedItem.setText(tag);
                        }
                    }
                });

                radioGroupFliexibility.addView(radioButton);
                if (flexibilityId == notflexibilitySetLists.get(index).getFlexibilityRangeId()) {
                    radioGroupFliexibility.check(radioButton.getId());
                }
            }

            for (int index = 0; index < flexibilitySetLists.size(); index++) {
                OTRadioButton radioButton = new OTRadioButton(getActivity());
                radioButton.setText(flexibilitySetLists.get(index).getLabel1());
                radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
                radioButton.setTextColor(getResources().getColor(R.color.color_font_gray_dark));
                radioButton.setTypeface(null, Typeface.NORMAL);
                final int finalIndex = index;
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            tag = Utils.getFlexibilityLabelTag(flexibilitySetLists.get(finalIndex).getLabel1());
                            id = flexibilitySetLists.get(finalIndex).getFlexibilityRangeId();
                            txtSelectedItem.setText(tag);
                        }
                    }
                });

                radioGroupFliexibility.addView(radioButton);
                if (flexibilityId == flexibilitySetLists.get(index).getFlexibilityRangeId()) {
                    radioGroupFliexibility.check(radioButton.getId());
                }
            }

        } else if (viewType - SearchSelectRecyclerViewAdapter.VIEW_TYPE_FM_MATRIX_BASE >= 0)//FM Matrix feature
        {
            try {
                if (flightPassDealData != null || flightPassDealData.getRestrictions() != null || flightPassDealData.getDefaultValues() != null) {
                    int fId = viewType - SearchSelectRecyclerViewAdapter.VIEW_TYPE_FM_MATRIX_BASE;
                    Restriction restriction = null;
                    for (int loc = 0; loc < flightPassDealData.getRestrictions().size(); loc++) {
                        if (flightPassDealData.getRestrictions().get(loc).getFID() == fId) {
                            restriction = flightPassDealData.getRestrictions().get(loc);

                            final ArrayList<Value> listValues = restriction.getValues();

                            for (int index = 0; index < listValues.size(); index++) {
                                View inflated = getActivity().getLayoutInflater().inflate(R.layout.layout_flight_pass_search_fm_matrix_inner_row, null);

                                LinearLayout lytHeading = (LinearLayout) inflated.findViewById(R.id.lytHeading);
                                lytHeading.setVisibility(index == 0 ? View.VISIBLE : View.GONE);

                                OTTextView txtSavingLabel = (OTTextView) inflated.findViewById(R.id.txtSavingLabel);
                                txtSavingLabel.setText(restriction.getHeadingLabel());

                                OTTextView txtFlexibilityLabel = (OTTextView) inflated.findViewById(R.id.txtFlexibilityLabel);
                                txtFlexibilityLabel.setText(restriction.getFlexibilityHeading());

                                RadioButton rbFeatureName = (RadioButton) inflated.findViewById(R.id.rbFeatureName);
                                rbFeatureName.setId(10000 + index);
                                rbFeatureName.setText(listValues.get(index).getFeatureName());

                                int savedFeatureId = (int) sf.get(getString(R.string.key_selected_fm_matrix_id) + listValues.get(index).getFeatureTypeID());

                                if (listValues.get(index).getFeatureId() == savedFeatureId) {
                                    //radioGroupFMMatrix.check(rbFeatureName.getId());
                                    rbFeatureName.setChecked(true);
                                } else {
                                    rbFeatureName.setChecked(false);
                                }
                                final int finalIndex = index;
                                rbFeatureName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                        if (isChecked) {
                                            tag = listValues.get(finalIndex).getFeatureName();
                                            id = listValues.get(finalIndex).getFeatureId();
                                            txtSelectedItem.setText(tag);

                                            uncheckRestRadioButton(radioGroupFMMatrix, ((RadioButton) buttonView).getId());
                                        }

                                    }


                                });


                                OTTextView txtFeatureShortTitle = (OTTextView) inflated.findViewById(R.id.txtFeatureShortTitle);
                                txtFeatureShortTitle.setText(listValues.get(index).getFeatureShortTitle());

                                if(rbFeatureName.getText().toString().equals(txtFeatureShortTitle.getText().toString()))
                                {
                                    txtFeatureShortTitle.setVisibility(View.GONE);
                                }

                                LinearLayout lytFeatureSaving = (LinearLayout) inflated.findViewById(R.id.lytFeatureSaving);
                                for (int i = 0; i < listValues.get(index).getSavingValues().size(); i++) {
                                    NetworkImageView img = new NetworkImageView(getActivity());

                                    /*//img.setImageUrl(listValues.get(index).getSavingValues().get(i).toString(), imageLoader);//image is not available at optiontown.com
                                    //img.setImageUrl("http://192.168.64.10/images/saving.png", imageLoader);
                                    img.setImageUrl(getActivity().getString(R.string.URL_BASE) + "/images/saving.png", imageLoader);*/
                                    Utils.setBackground(img, getResources().getDrawable(R.drawable.saving));

                                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(20, 20);

                                    lytFeatureSaving.addView(img, param);
                                    // lytFeatureSaving.addView(img);
                                }

                                LinearLayout lytFeatureFlexibility = (LinearLayout) inflated.findViewById(R.id.lytFeatureFlexibility);
                                for (int i = 0; i < listValues.get(index).getFlexibilityValues().size(); i++) {
                                    NetworkImageView img = new NetworkImageView(getActivity());

                                    /*//img.setImageUrl(listValues.get(index).getFlexibilityValues().get(i).toString(), imageLoader);//image is not available at optiontown.com
                                    //img.setImageUrl("http://192.168.64.10/images/flexibility.png", imageLoader);
                                    img.setImageUrl(getActivity().getString(R.string.URL_BASE) + "/images/flexibility.png", imageLoader);*/
                                    Utils.setBackground(img, getResources().getDrawable(R.drawable.flexibility));

                                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(20, 20);
                                    lytFeatureFlexibility.addView(img, param);
                                    //lytFeatureFlexibility.addView(img);
                                }
                                radioGroupFMMatrix.addView(inflated);
                            }
                            imgSelectedItemNetwork.setImageUrl(getString(R.string.URL_BASE) + getString(R.string.URL_IMAGE_RESTRICTED)+ flightPassDealData.getRestrictions().get(loc).getHelpImage(), imageLoader);
                            imgSelectedItemNetwork.setVisibility(View.VISIBLE);
                            break;
                        }
                    }

                    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

                    lytFMMatrix.setVisibility(View.VISIBLE);
                    lytFlexibility.setVisibility(View.GONE);
                    lytAdvanceBooking.setVisibility(View.GONE);
                    svTravelPeriod.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);

                    txtSelectedItem.setText((String) sf.get(getString(R.string.key_selected_fm_matrix_tag) + fId));
                    imgSelectedItem.setVisibility(View.GONE);
                    title = restriction.getFName();
                    strHelp = restriction.getLongTitle();
                }
            } catch (Exception e) {
                Utils.ERROR("error : " + e.toString());
                e.printStackTrace();
            }
        }


        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), title, strHelp);
        Utils.updateBottomBarForFeatures(view, this.getClass().getName());

        localiseUI();


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

    private void uncheckRestRadioButton(ViewGroup radioGroupFMMatrix, int id) {
        for (int i = 0; i < radioGroupFMMatrix.getChildCount(); i++) {
            View child = radioGroupFMMatrix.getChildAt(i);
            if (child instanceof ViewGroup) {
                uncheckRestRadioButton((ViewGroup) child, id);
            } else if (child instanceof RadioButton) {
                if (child != null && id != child.getId()) {
                    //Utils.DEBUG("child : " + child);
                    ((RadioButton) child).setChecked(false);
                }
            }
        }
    }

    private void localiseUI() {

        ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLFlightPassHomeLabel());
        ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLUKBuyFlightPassHomeLabel());
        ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
        ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
        ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

        ((OTTextView) view.findViewById(R.id.txtTravelPeriodLabel)).setText(localization.getLABLPassValidityLabel());


        localizeUI(localization);


    }



    private void communicateToFragment(boolean isAirlineChanged) {
        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFragmentName((new FlightPassSearchFragment()).getClass().getName());
        //data.setFragmentName((new LegProductSearchFragment()).getClass().getName());
        data.setFlightPassDealData(flightPassDealData);
        try {
            data.setSelectedAirline((String) sf.get(getString(R.string.key_selected_airline_tag)));
        } catch (Exception e) {
        }
        data.setSelectedCabin((String) sf.get(getString(R.string.key_selected_cabin_tag)));
        data.setSelectedPassenger((String) sf.get(getString(R.string.key_selected_passenger_tag)));
        data.setSelectedCredit((String) sf.get(getString(R.string.key_selected_flight_tag)));
        data.setSelectedTravelPeriod(Utils.getSelectedTextForValidPeriod(getActivity(), (String) sf.get(getString(R.string.key_selected_travel_period_valid_from)), (String) sf.get(getString(R.string.key_selected_travel_period_month_tag))));
        data.setSelectedAdvanceBooking((String) sf.get(getString(R.string.key_selected_advance_booking_tag)));
        data.setSelectedFlexibility((String) sf.get(getString(R.string.key_selected_flexibility_tag)));
        int fId = viewType - SearchSelectRecyclerViewAdapter.VIEW_TYPE_FM_MATRIX_BASE;
        try {
            data.setSelectedFMMatrixTag((String) sf.get(getString(R.string.key_selected_fm_matrix_tag) + fId));
            data.setSelectedFMMatrixId((int) sf.get(getString(R.string.key_selected_fm_matrix_id) + fId));
        } catch (Exception e) {
            Utils.ERROR("communicateToFragment() No FM in values in SP : >> " + e.toString());
            //e.printStackTrace();
        }


        data.setAirlineChanged(isAirlineChanged);
        communicator.onResponse(data);
    }


    public void updateHelpLayout(FragmentCommunicationData message) {
        txtHelp = (OTTextView) view.findViewById(R.id.txtHelp);
        txtHelp.setText("" + Html.fromHtml(message.getHelp()));
        txtHelp.setVisibility(message.isShowHelp() ? View.VISIBLE : View.GONE);
    }

    private void initalizeMonthDropDownList() {
        //first remove all child if any
        lytMonthList.removeAllViews();
       // lytMonthList.setVisibility(View.VISIBLE);
        lytMonthList.setVisibility(lytMonthList.getVisibility() == View.VISIBLE ?View.GONE:View.VISIBLE);

        Validity validityData = flightPassDealData.getFList().getValidity();
        final ArrayList<VList> vList = validityData.getVList();

        for (int index = 0; index < vList.size(); index++) {
            lytMonthListRow = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_month_list_row, null, false);

            OTTextView txtMonth = (OTTextView) lytMonthListRow.findViewById(R.id.txtMonth);
            txtMonth.setText(vList.get(index).getValidity());

            final int finalIndex = index;
            lytMonthListRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tag = vList.get(finalIndex).getValidity();
                    id = vList.get(finalIndex).getId();


                    txtTravelPeriod.setText(tag);

                    txtSelectedItem.setText(Utils.getSelectedTextForValidPeriod(getActivity(), travel_from, tag));

                    //remove all child
                    lytMonthList.removeAllViews();
                    lytMonthList.setVisibility(View.GONE);
                }
            });

            lytMonthList.addView(lytMonthListRow);
        }


    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("FlightPassSearchSelectFragment >> onAttach(Activity) called");
        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    public void initializeCalendar() {
        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        Utils.updateActionBarForFeatures(this.getActivity(), new CaldroidFragment().getClass().getName());


        android.support.v4.app.FragmentTransaction t = fragmentActivity.getSupportFragmentManager().beginTransaction();
        t.add(R.id.lytMain, caldroidFragment, caldroidFragment.getClass().getName());
        t.addToBackStack(caldroidFragment.getClass().getName());
        t.commit();

        //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        caldroidFragment.setMinDate(new Date());


        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                //Utils.showToast(getActivity(), Utils.converDateToFormat_ddMMMyyyy(date));

                travel_from = Utils.converDateToFormat_ddMMMyyyy(date, Utils.getLocalForCommunication());
                txtTravelValidFrom.setText(Utils.converDateToFormat_ddMMMyyyy(date));

                String temp = tag.equals("") ? (String) sf.get(getString(R.string.key_selected_travel_period_month_tag)) : tag;


                txtSelectedItem.setText(Utils.getSelectedTextForValidPeriod(getActivity(), Utils.converDateToFormat_ddMMMyyyy(date), temp));

                android.support.v4.app.FragmentManager manager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                manager.popBackStack();
                Utils.updateActionBarForFeatures(getActivity(), new FlightPassSearchSelectFragment().getClass().getName(), title, strHelp);
            }
        });

    }


    private void callUpdateAirlineAPI() {
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_HOME_PASS_BANNER);


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("TgProductId", Integer.toString(Utils.getCurrentProductId(getActivity())));
            requestObject.put("CountryId", Utils.getUserSelectedCountryId(getActivity()) + "");
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(getActivity()) + "");
            requestObject.put("AirlineId", Integer.toString((int) sf.get(getActivity().getString(R.string.key_selected_airline_id))));
            requestObject.put("tgpLevel", Integer.toString(getResources().getInteger(R.integer.value_tgpLevel)));
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }



        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Utils.DEBUG(response.toString() + "");
                        if (response == null || response.toString().trim().length() == 0) {
                            return;
                        }
                        flightPassDealData = ParseManager.getInstance().fromJSON(response, FlightPassDealData.class);


                        if (flightPassDealData == null) {
                            //Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                            return;
                        }

                        /*FragmentManager fm = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                        int count = fm.getBackStackEntryCount();

                        //pop back till FlightPassSearchFragment fragment, then to restart just moveToFragment FlightPassSearchFragment with latest flightPassDealData
                        for (int index = count - 1; index >= 0; index--)
                        {
                            FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(index);
                            Utils.DEBUG("removing " + entry.getName());

                            fm.popBackStack();


                            if(entry.getName().equals(new FlightPassSearchFragment().getClass().getName()))
                            {
                                Utils.moveToFragment(getActivity(), new FlightPassSearchFragment(), flightPassDealData, 0);
                                break;
                            }
                        }*/

                        //update FlightPassSearchFragment
                        communicateToFragment(true);


                        loader.dismiss();
                        ((MainActivity) getActivity()).onBackPressed();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

}

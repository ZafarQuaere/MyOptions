package com.optiontown.app.view.fragment.fpo.flightpass;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.optiontown.R;
import com.optiontown.app.model.fpogetpass.FlightCountList;
import com.optiontown.app.model.fpogetpass.FpoGetPassData;
import com.optiontown.app.model.fpogetpass.ValidityList;
import com.optiontown.app.model.fpogetpass.ZoneMemberSet;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.SelectFlightPassFragment;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 17-06-2016.
 */
public class FilterFlightPassFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    private FpoGetPassData fpoGetPassData;
    private RelativeLayout lytFilterSelectZone;
    private RelativeLayout lytFilterNumberFlight;
    private RelativeLayout lytFilterTravelPeriod;
    private RelativeLayout lytFilterPricePerFlight;

    boolean flagClickedNmberFlightSlider = false;
    boolean flagClickedTravelPeriodSlider = false;

    private final int KEY_TAG_ZONE = 1;
    private final int KEY_TAG_NUMBER_FLIGHT = 2;
    private final int KEY_TAG_TRAVEL_PERIOD = 3;
    private final int KEY_TAG_PRICE_PER_FLIGHT = 4;

    private FragmentActivity fragmentActivity;
    private Communicator communicator;
    private LinearLayout lytFilterPanel;
    private LinearLayout lytLeftPanel;
    InternationalizeData localization;
    OTTextView txtTravelPeriodApply, txtTravelPeriodClearAll,txtTravelPeriodSelectAll, txtTravelPeriodReset,
            txtTravelZoneSelectAll, txtTravelZoneClearAll,txtTravelZoneApply, txtTravelZoneReset,txtNumberFlightSelectAll,
            txtNumberFlightClearAll,txtNumberFlightApply,txtNumberFlightReset,txtPricePerFlightApply;
    private OTTextView txtTravelValidFromLabel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filter_flight_pass, container, false);

        //----data for localisation
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        Utils.DEBUG(this.getClass().getSimpleName() + " >> received : " + getArguments().toString());

        referenceUI();

        localize();

        lytFilterSelectZone = (RelativeLayout) view.findViewById(R.id.lytFilterSelectZone);
        lytFilterSelectZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onClickFilterSelectZone();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        lytFilterNumberFlight = (RelativeLayout) view.findViewById(R.id.lytFilterNumberFlight);
        lytFilterNumberFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onClickNumberFlight();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        lytFilterTravelPeriod = (RelativeLayout) view.findViewById(R.id.lytFilterTravelPeriod);
        lytFilterTravelPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onClickTravelPeriod();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        lytFilterPricePerFlight = (RelativeLayout) view.findViewById(R.id.lytFilterPricePerFlight);
        lytFilterPricePerFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onClickPricePerFlight();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        lytFilterPanel = (LinearLayout) view.findViewById(R.id.lytFilterPanel);
        lytLeftPanel = (LinearLayout) view.findViewById(R.id.lytLeftPanel);

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();


        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //default selection
                if(fpoGetPassData.getPassObject().size() == 1)
                {
                    String strPassNotAvailable = fpoGetPassData.getPassObject().get(0).getError_Pass_Not_Available();
                    if(strPassNotAvailable != null)
                    {
                        loader.dismiss();
                        return;
                    }
                }
                else
                {
                    ((OTTextView) view.findViewById(R.id.txtFilterPricePerFlight)).setText(fpoGetPassData.getPerflightLabel());
                    ((OTTextView) view.findViewById(R.id.txtFilterNumberFlight)).setText(fpoGetPassData.getNumberOfFlightLabel());
                    ((OTTextView) view.findViewById(R.id.txtTotalFlightLabel)).setText(""/*localization.getLABLTotalFlightSelectLabel()*/);
                }

                try {
                    onClickFilterSelectZone();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                lytFilterPanel.setVisibility(View.VISIBLE);
                lytLeftPanel.setVisibility(View.VISIBLE);
                loader.dismiss();
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fpoGetPassData = ((FpoGetPassData) ParseManager.getInstance().fromJSON(new JSONObject(Utils.getFPOPassData(getActivity())), FpoGetPassData.class));
                    handler.sendEmptyMessage(0);
                } catch (JSONException e) {
                    Utils.ERROR("Error while parsing json : " + e.toString());
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();









        return view;
    }

    private void referenceUI(){
        txtTravelPeriodReset = (OTTextView) view.findViewById(R.id.txtTravelPeriodReset);
        txtTravelPeriodClearAll = (OTTextView) view.findViewById(R.id.txtTravelPeriodClearAll);
        txtTravelPeriodSelectAll = (OTTextView) view.findViewById(R.id.txtTravelPeriodSelectAll);
        txtTravelPeriodApply = (OTTextView) view.findViewById(R.id.txtTravelPeriodApply);
        txtNumberFlightApply = (OTTextView) view.findViewById(R.id.txtNumberFlightApply);
        txtNumberFlightSelectAll = (OTTextView) view.findViewById(R.id.txtNumberFlightSelectAll);
        txtNumberFlightClearAll = (OTTextView) view.findViewById(R.id.txtNumberFlightClearAll);
        txtNumberFlightReset = (OTTextView) view.findViewById(R.id.txtNumberFlightReset);
        txtTravelZoneApply = (OTTextView) view.findViewById(R.id.txtTravelZoneApply);
        txtTravelZoneSelectAll = (OTTextView) view.findViewById(R.id.txtTravelZoneSelectAll);
        txtTravelZoneClearAll = (OTTextView) view.findViewById(R.id.txtTravelZoneClearAll);
        txtTravelZoneReset = (OTTextView) view.findViewById(R.id.txtTravelZoneReset);
        txtPricePerFlightApply = (OTTextView) view.findViewById(R.id.txtPricePerFlightApply);
        txtTravelValidFromLabel = (OTTextView) view.findViewById(R.id.txtTravelValidFromLabel);

    }


    private void localize() {

        ((OTTextView) view.findViewById(R.id.txtFilterZoneSelect)).setText(localization.getLABLSelectTravelZoneLabel());
        //((OTTextView) view.findViewById(R.id.txtFilterNumberFlight)).setText(localization.getLABLCreditTitleLabel());
        ((OTTextView) view.findViewById(R.id.txtFilterTravelPeriod)).setText(localization.getLABLPassValidityLabel());
        //((OTTextView) view.findViewById(R.id.txtFilterPricePerFlight)).setText(localization.getLABLPricePerFlight());

        //((OTTextView) view.findViewById(R.id.txtTotalFlightLabel)).setText(localization.getLABLTotalFlightSelectLabel());
        ((OTTextView) view.findViewById(R.id.txtTravelPeriodLabel)).setText(localization.getLABLPassValidityLabel());

        txtTravelPeriodClearAll.setText(localization.getLABLClearAllLabel());
        txtTravelPeriodSelectAll.setText(localization.getSelectAllLabel() + " | ");
        txtTravelPeriodApply.setText(localization.getLABLApplyLabel());
        txtTravelPeriodReset.setText(localization.getLABLResetLabel());

        txtTravelZoneSelectAll.setText(localization.getSelectAllLabel() + " | ");
        txtTravelZoneClearAll.setText(localization.getLABLClearAllLabel());
        txtTravelZoneApply.setText(localization.getLABLApplyLabel());
        txtTravelZoneReset.setText(localization.getLABLResetLabel());

        txtNumberFlightSelectAll.setText(localization.getSelectAllLabel() + " | ");
        txtNumberFlightClearAll.setText(localization.getLABLClearAllLabel());
        txtNumberFlightApply.setText(localization.getLABLApplyLabel());
        txtNumberFlightReset.setText(localization.getLABLResetLabel());

        txtPricePerFlightApply.setText(localization.getLABLApplyLabel());
        txtTravelValidFromLabel.setText(localization.getLABLValidityBeginDateLabel());
        ((OTTextView) view.findViewById(R.id.txtPricePerFlightReset)).setText(localization.getLABLResetLabel());
    }

    private void onClickPricePerFlight() throws Exception
    {
        setSelector(R.id.viewSelectorPricePerFlight);

        final OTTextView txtSelectedPricePerFlightRange = (OTTextView) view.findViewById(R.id.txtSelectedPricePerFlightRange);
        RangeSeekBar seekBarPricePerFlight = (RangeSeekBar) view.findViewById(R.id.seekBarPricePerFlight);

        int startValue = (fpoGetPassData.getMinPrice());
        int endValue = (fpoGetPassData.getMaxPrice());
        txtSelectedPricePerFlightRange.setText(fpoGetPassData.getCurrencyUnit() + " " + startValue + " to " + fpoGetPassData.getCurrencyUnit() + " " + endValue);
        final int factor = 1;

        seekBarPricePerFlight.setRangeValues(startValue/factor, endValue/factor);
        seekBarPricePerFlight.setNotifyWhileDragging(true);
        seekBarPricePerFlight.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                minValue = minValue*factor;
                maxValue *= factor;
                txtSelectedPricePerFlightRange.setText(fpoGetPassData.getCurrencyUnit() + " " + minValue + " to " + fpoGetPassData.getCurrencyUnit() + " " + maxValue);
                Utils.DEBUG("onClickNumberFlight() >> seekbar >> " + (minValue + " : " + maxValue));

                sp.put(getString(R.string.key_filter_price_per_flight_range_min), minValue);
                sp.put(getString(R.string.key_filter_price_per_flight_range_max), maxValue);

            }
        });

        seekBarPricePerFlight.setSelectedMinValue((int)sp.get(getString(R.string.key_filter_price_per_flight_range_min)));
        seekBarPricePerFlight.setSelectedMaxValue((int)sp.get(getString(R.string.key_filter_price_per_flight_range_max)));

        txtPricePerFlightApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUIAndMoveBack();
            }
        });
    }

    private void onClickTravelPeriod() throws Exception
    {
        setSelector(R.id.viewSelectorTravelPeriod);

        final LinearLayout lytAvailableTravelPeriod = (LinearLayout) view.findViewById(R.id.lytAvailableTravelPeriod);
        final OTTextView txtTravelPeriodMinMonths = (OTTextView) view.findViewById(R.id.txtTravelPeriodMinMonths);
        final OTTextView txtTravelPeriodMaxMonths = (OTTextView) view.findViewById(R.id.txtTravelPeriodMaxMonths);
        final OTTextView txtSelectedTravelPeriodRange = (OTTextView) view.findViewById(R.id.txtSelectedTravelPeriodRange);
        final ImageView imgTravelPeriodSlider = (ImageView) view.findViewById(R.id.imgTravelPeriodSlider);
        final RangeSeekBar seekBarTravelPeriod = (RangeSeekBar) view.findViewById(R.id.seekBarTravelPeriod);
        OTTextView txtTravelValidFrom = (OTTextView) view.findViewById(R.id.txtTravelValidFrom);
        txtTravelValidFrom.setText(Utils.converDateToFormat_mmddyyyy(Utils.convertToDate_ddMMMyyyy((String) sp.get(getString(R.string.key_selected_travel_period_valid_from)))));


        final ArrayList<ValidityList> validityList = fpoGetPassData.getValidityList();

        if(validityList != null) {
            txtTravelPeriodMinMonths.setText(validityList.get(0).getName());//first
            txtTravelPeriodMaxMonths.setText(validityList.get(validityList.size() - 1).getName());//last


            int startValue = (validityList.get(0).getPassValidityValue());
            int endValue = (validityList.get(validityList.size() - 1).getPassValidityValue());

            int selectedValue = (int)sp.get(getString(R.string.key_selected_travel_period_month_id))/30;

            Utils.DEBUG("selectedValue : " + selectedValue);

            txtSelectedTravelPeriodRange.setText(selectedValue + " months - " + selectedValue + " months");


            final int factor = 1;

            final Handler handlerTP = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    txtSelectedTravelPeriodRange.setText(msg.arg1 + " months - " + msg.arg2 + " months");
                }
            };


            //RangeSeekBar seekBar = new RangeSeekBar(getActivity());
            seekBarTravelPeriod.setRangeValues(startValue / factor, endValue / factor);
            seekBarTravelPeriod.setNotifyWhileDragging(true);
            seekBarTravelPeriod.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
                @Override
                public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, final Integer minValue, final Integer maxValue) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Integer max = maxValue;
                            Integer min = minValue * factor;
                            max *= factor;

                            Message message = new Message();
                            message.arg1 = min;
                            message.arg2 = max;
                            handlerTP.sendMessage(message);
                            //Utils.DEBUG("onClickNumberFlight() >> seekbar >> " + (minValue + " : " + maxValue));

                            //set values in shared prefs
                            ArrayList<Integer> selectedIds = getSelectedIdsForSeekbar(minValue, maxValue, factor);
                            for (int i = 0; i < validityList.size(); i++)
                            {
                                int id = (validityList.get(i).getPassValidityValue());

                                for (int index = 0; index < selectedIds.size(); index++) {
                                    int idSelected = selectedIds.get(index);
                                    if(getActivity() == null)
                                    {
                                        break;
                                    }
                                    String key = getActivity().getString(R.string.key_filter_travel_period_validityId) + "_" + i;
                                    if(id == idSelected)
                                    {
                                        //Utils.DEBUG("common >> " + (getString(R.string.key_filter_travel_period_validityId) + "_" + i) + ", " + id);
                                        sp.put(key, id);

                                        break;
                                    }
                                    else
                                    {
                                        //Utils.DEBUG("common >> " + (getString(R.string.key_filter_travel_period_validityId) + "_" + i) + ", " + 0);
                                        sp.put(key, 0);
                                    }
                                }
                            }

                        }
                    }).start();


                }
            });

            //for already selected seekbar
            seekBarTravelPeriod.setSelectedMinValue(selectedValue);
            seekBarTravelPeriod.setSelectedMaxValue(selectedValue);

            int aryMinMax[] = getMinMaxSelectedOfRangeSeekBarForTravelPeriod(validityList);

            if(aryMinMax[0] > 0)
            {
                seekBarTravelPeriod.setSelectedMinValue(aryMinMax[0]/factor);
                seekBarTravelPeriod.setSelectedMaxValue(aryMinMax[1]/factor);
                txtSelectedTravelPeriodRange.setText(aryMinMax[0] + " months - " + aryMinMax[1] + " months");
            }

            flagClickedTravelPeriodSlider = !flagClickedTravelPeriodSlider;
            imgTravelPeriodSlider.performClick();

            imgTravelPeriodSlider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    flagClickedTravelPeriodSlider = !flagClickedTravelPeriodSlider;
                    setVisibilityOfView(flagClickedTravelPeriodSlider ? View.GONE : View.VISIBLE);

                    if (flagClickedTravelPeriodSlider) {
                        //add checkbox
                        for (int index = 0; index < validityList.size(); index++) {
                            CheckBox checkBox = new CheckBox(getActivity());
                            checkBox.setText(validityList.get(index).getName());
                            checkBox.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_10)));
                            checkBox.setTextColor(getResources().getColor(R.color.color_font_gray_dark));
                            checkBox.setTypeface(null, Typeface.NORMAL);

                            checkBox.setTag(validityList.get(index).getPassValidityValue());

                            final int finalIndex = index;
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    Utils.DEBUG("onClickNumberFlight() >> checkbox status changed to : " + isChecked);
                                    if (isChecked)
                                    {
                                        sp.put(getString(R.string.key_filter_travel_period_validityId) + "_" + finalIndex, (validityList.get(finalIndex).getPassValidityValue()));
                                    }
                                    else
                                    {
                                        sp.put(getString(R.string.key_filter_travel_period_validityId) + "_" + finalIndex, 0);//0 indicates not selected
                                    }
                                }
                            });


                            for (int pos = 0; pos < validityList.size(); pos++)
                            {
                                Utils.DEBUG("comparing : " + index + ", " + validityList.get(index).getPassValidityValue() + " vs " +  (((int)sp.get(getString(R.string.key_filter_travel_period_validityId) + "_" + pos))));

                                if(validityList.get(index).getPassValidityValue() == (((int)sp.get(getString(R.string.key_filter_travel_period_validityId) + "_" + pos))))// id in days so changed
                                {
                                    checkBox.setChecked(true);
                                    break;
                                }
                                else
                                {
                                    checkBox.setChecked(false);
                                }
                            }



                            lytAvailableTravelPeriod.addView(checkBox);
                        }
                    } else {
                        //remove view
                        removeAllCheckbox(lytAvailableTravelPeriod);
                    }

                }

                private void setVisibilityOfView(int visibility) {
                    txtSelectedTravelPeriodRange.setVisibility(visibility);
                    seekBarTravelPeriod.setVisibility(visibility);
                    txtTravelPeriodMinMonths.setVisibility(visibility);
                    txtTravelPeriodMaxMonths.setVisibility(visibility);

                    if (visibility == View.GONE) {
                        imgTravelPeriodSlider.setBackgroundResource(R.drawable.list_icon);
                    } else {
                        imgTravelPeriodSlider.setBackgroundResource(R.drawable.slider_icon);
                    }
                }
            });
        }

        txtTravelPeriodApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUIAndMoveBack();
            }
        });


        txtTravelPeriodSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllCheckboxStatus(lytAvailableTravelPeriod, true);
            }
        });


        txtTravelPeriodClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllCheckboxStatus(lytAvailableTravelPeriod, false);
            }
        });


        txtTravelPeriodReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                resetAllCheckboxStatus(lytAvailableTravelPeriod, ((int)sp.get(getString(R.string.key_selected_travel_period_month_id))));
            }
        });
    }


    /**
     * used to get array of min max flights
     * @param validityList
     * @return
     */
    private int[] getMinMaxSelectedOfRangeSeekBarForTravelPeriod(ArrayList<ValidityList> validityList)
    {
        int aryMinMax[] = new int[2];

        if(validityList == null)
        {
            return aryMinMax;
        }

        for (int i = 0; i < validityList.size(); i++)
        {
            int id = (int)sp.get(getString(R.string.key_filter_travel_period_validityId) + "_" + i);
            //Utils.DEBUG("debugging >> : " + );
            Utils.DEBUG("debugging >> : position " + i + ", value : " + id);
            if(id > 0)
            {
                if(aryMinMax[0] == 0)
                {
                    aryMinMax[0] = id;
                }

                if(aryMinMax[1] > 0)
                {
                    aryMinMax[0] = 0;
                    aryMinMax[1] = 0;

                    break;
                }
            }
            else
            {
                if(aryMinMax[0] > 0 && aryMinMax[1] == 0)
                {
                    aryMinMax[1] = (int)sp.get(getString(R.string.key_filter_travel_period_validityId) + "_" + (i - 1));
                }
            }
        }
        Utils.DEBUG("debugging >> : range " + aryMinMax[0] + ", " + aryMinMax[1]);

        return aryMinMax;
    }

    private void onClickNumberFlight() throws Exception
    {
        setSelector(R.id.viewSelectorNumberFlight);
        final LinearLayout lytAvailableFlights = (LinearLayout) view.findViewById(R.id.lytAvailableFlights);
        final OTTextView txtNumberFlightMinFlights = (OTTextView) view.findViewById(R.id.txtNumberFlightMinFlights);
        final OTTextView txtNumberFlightMaxFlights = (OTTextView) view.findViewById(R.id.txtNumberFlightMaxFlights);
        final OTTextView txtSelectedFlightRange = (OTTextView) view.findViewById(R.id.txtSelectedFlightRange);
        final ImageView imgNmberFlightSlider = (ImageView) view.findViewById(R.id.imgNmberFlightSlider);
        final RangeSeekBar seekBarNumberFlight = (RangeSeekBar) view.findViewById(R.id.seekBarNumberFlight);


        final ArrayList<FlightCountList> flightCountList = fpoGetPassData.getFlightCountList();

        if(flightCountList != null) {
            txtNumberFlightMinFlights.setText(flightCountList.get(0).getPassValue() + " "+ fpoGetPassData.getFlightLabel());//first
            txtNumberFlightMaxFlights.setText(flightCountList.get(flightCountList.size() - 1).getPassValue() + " "+fpoGetPassData.getFlightLabel());//last


            int startValue = Integer.parseInt(flightCountList.get(0).getPassValue());
            int endValue = Integer.parseInt(flightCountList.get(flightCountList.size() - 1).getPassValue());
            int selectedValue = (int)sp.get(getString(R.string.key_selected_flight_id));
            txtSelectedFlightRange.setText(selectedValue + " " + fpoGetPassData.getFlightLabel() + " - " + selectedValue +  " " + fpoGetPassData.getFlightLabel());

            final int factor = 2;

            seekBarNumberFlight.setRangeValues(startValue / factor, endValue / factor);
            //seekBarNumberFlight.setNotifyWhileDragging(true);
            seekBarNumberFlight.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
                @Override
                public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                    minValue = minValue * factor;
                    maxValue *= factor;

                    txtSelectedFlightRange.setText(minValue + " " + fpoGetPassData.getFlightLabel()+ " - " + maxValue + " " + fpoGetPassData.getFlightLabel());
                    Utils.DEBUG("onClickNumberFlight() >> seekbar >> " + (minValue + " : " + maxValue));

                    //set values in shared prefs
                    ArrayList<Integer> selectedIds = getSelectedIdsForSeekbar(minValue, maxValue, factor);
                    for (int i = 0; i < flightCountList.size(); i++)
                    {
                        int id = Integer.parseInt(flightCountList.get(i).getPassValue());

                        for (int index = 0; index < selectedIds.size(); index++) {
                            int idSelected = selectedIds.get(index);

                            if(id == idSelected)
                            {
                                Utils.DEBUG("common >> " + (getString(R.string.key_filter_number_flight_creditId) + "_" + i) + ", " + id);
                                sp.put(getString(R.string.key_filter_number_flight_creditId) + "_" + i, id);

                                break;
                            }
                            else
                            {
                                Utils.DEBUG("common >> " + (getString(R.string.key_filter_number_flight_creditId) + "_" + i) + ", " + 0);
                                sp.put(getString(R.string.key_filter_number_flight_creditId) + "_" + i, 0);
                            }
                        }
                    }

                }
            });




            //for already selected seekbar
            seekBarNumberFlight.setSelectedMinValue(selectedValue/factor);
            seekBarNumberFlight.setSelectedMaxValue(selectedValue/factor);
            int aryMinMax[] = getMinMaxSelectedOfRangeSeekBarForNumberFlight(flightCountList);

            if(aryMinMax[0] > 0)
            {
                seekBarNumberFlight.setSelectedMinValue(aryMinMax[0]/factor);
                seekBarNumberFlight.setSelectedMaxValue(aryMinMax[1]/factor);
                if(flightCountList.size() == 1)
                {
                    txtSelectedFlightRange.setText(aryMinMax[0] + " " + fpoGetPassData.getFlightLabel()+ " - " + aryMinMax[0] + " " + fpoGetPassData.getFlightLabel());
                }
                else
                {
                    txtSelectedFlightRange.setText(aryMinMax[0]+ " " + fpoGetPassData.getFlightLabel() + " - " + aryMinMax[1]+ " " + fpoGetPassData.getFlightLabel());
                }
            }


            flagClickedNmberFlightSlider = !flagClickedNmberFlightSlider;
            imgNmberFlightSlider.performClick();

            imgNmberFlightSlider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    flagClickedNmberFlightSlider = !flagClickedNmberFlightSlider;
                    setVisibilityOfNumberFlight(flagClickedNmberFlightSlider ? View.GONE : View.VISIBLE);

                    if (flagClickedNmberFlightSlider) {
                        //add checkbox
                        for (int index = 0; index < flightCountList.size(); index++) {
                            CheckBox checkBox = new CheckBox(getActivity());
                            checkBox.setText(flightCountList.get(index).getPassValue() + " " + fpoGetPassData.getFlightLabel());
                            checkBox.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_10)));
                            checkBox.setTextColor(getResources().getColor(R.color.color_font_gray_dark));
                            checkBox.setTypeface(null, Typeface.NORMAL);
                            checkBox.setTag(flightCountList.get(index).getPassValue());

                            final int finalIndex = index;
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    Utils.DEBUG("onClickNumberFlight() >> checkbox status changed to : " + isChecked);
                                    if (isChecked)
                                    {
                                        sp.put(getString(R.string.key_filter_number_flight_creditId) + "_" + finalIndex, Integer.parseInt(flightCountList.get(finalIndex).getPassValue()));
                                    }
                                    else
                                    {
                                        sp.put(getString(R.string.key_filter_number_flight_creditId) + "_" + finalIndex, 0);//0 indicates not selected
                                    }
                                }
                            });

                            if(flightCountList.get(index).getPassValue().equals(Integer.toString((int)sp.get(getString(R.string.key_filter_number_flight_creditId) + "_" + index))))
                            {
                                checkBox.setChecked(true);
                            }
                            else
                            {
                                checkBox.setChecked(false);
                            }

                            lytAvailableFlights.addView(checkBox);
                        }
                    } else {
                        //remove view
                        removeAllCheckbox(lytAvailableFlights);
                    }

                }

                private void setVisibilityOfNumberFlight(int visibility) {
                    txtSelectedFlightRange.setVisibility(visibility);
                    seekBarNumberFlight.setVisibility(visibility);
                    txtNumberFlightMinFlights.setVisibility(visibility);
                    txtNumberFlightMaxFlights.setVisibility(visibility);

                    if (visibility == View.GONE) {
                        imgNmberFlightSlider.setBackgroundResource(R.drawable.list_icon);
                    } else {
                        imgNmberFlightSlider.setBackgroundResource(R.drawable.slider_icon);
                    }
                }
            });
        }


        txtNumberFlightApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUIAndMoveBack();
            }
        });


        txtNumberFlightSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllCheckboxStatus(lytAvailableFlights, true);
            }
        });


        txtNumberFlightClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllCheckboxStatus(lytAvailableFlights, false);
            }
        });


        txtNumberFlightReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                resetAllCheckboxStatus(lytAvailableFlights, Integer.toString((int)sp.get(getString(R.string.key_selected_flight_id))));
            }
        });
    }

    /**
     * used to get array of min max flights
     * @param flightCountList
     * @return
     */
    private int[] getMinMaxSelectedOfRangeSeekBarForNumberFlight(ArrayList<FlightCountList> flightCountList)
    {
        int aryMinMax[] = new int[2];

        if(flightCountList == null)
        {
            return aryMinMax;
        }

        for (int i = 0; i < flightCountList.size(); i++)
        {
            int id = (int)sp.get(getString(R.string.key_filter_number_flight_creditId) + "_" + i);
            //Utils.DEBUG("debugging >> : " + );
            Utils.DEBUG("debugging >> : position " + i + ", value : " + id);
            if(id > 0)
            {
                if(aryMinMax[0] == 0)
                {
                    aryMinMax[0] = Integer.parseInt(flightCountList.get(i).getPassValue());
                }

                if(aryMinMax[1] > 0)
                {
                    aryMinMax[0] = 0;
                    aryMinMax[1] = 0;

                    break;
                }
            }
            else
            {
                if(aryMinMax[0] > 0 && aryMinMax[1] == 0)
                {
                    aryMinMax[1] = Integer.parseInt(flightCountList.get(i - 1).getPassValue());
                }
            }
        }
        Utils.DEBUG("debugging >> : range " + aryMinMax[0] + ", " + aryMinMax[1]);

        return aryMinMax;
    }


    private ArrayList<Integer> getSelectedIdsForSeekbar(Integer minValue, Integer maxValue, int factor)
    {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(minValue);

        int count = 0;

        while (minValue + count <= maxValue)
        {
            ids.add(minValue + count);
            count = count + factor;
        }

        /*for (int i = 0; i < ids.size(); i++) {
            Utils.DEBUG("getSelectedIdsForSeekbar() >> selected credit ids > " + ids.get(i));
        }*/

        return  ids;
    }

    private void onClickFilterSelectZone() throws Exception
    {
        final LinearLayout lytAvailableZone = (LinearLayout) view.findViewById(R.id.lytAvailableZone);


        setSelector(R.id.viewSelectorTravelZone);
        final ArrayList<ZoneMemberSet> listZones = fpoGetPassData.getZoneMemberSet();

        if(listZones != null)
        {
            for (int index = 0; index < listZones.size(); index++)
            {

                CheckBox checkBox = new CheckBox(getActivity());
                checkBox.setText(listZones.get(index).getShortDescription());
                checkBox.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_10)));
                checkBox.setTextColor(getResources().getColor(R.color.color_font_gray_dark));
                checkBox.setTypeface(null, Typeface.NORMAL);
                checkBox.setTag(listZones.get(index).getZoneId());

                final int finalIndex = index;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Utils.DEBUG("onClickFilterSelectZone() >> checkbox status changed to : " + isChecked);
                        if (isChecked)
                        {
                            sp.put(getString(R.string.key_filter_travel_zone_ZoneId) + "_" + finalIndex, listZones.get(finalIndex).getZoneId());
                        }
                        else
                        {
                            sp.put(getString(R.string.key_filter_travel_zone_ZoneId) + "_" + finalIndex, 0);//0 indicates not selected
                        }
                    }
                });

                if(listZones.get(index).getZoneId() == (int)sp.get(getString(R.string.key_filter_travel_zone_ZoneId) + "_" + index))
                {
                    checkBox.setChecked(true);//only one zone will be selected,
                }
                else
                {
                    checkBox.setChecked(false);
                }

                lytAvailableZone.addView(checkBox);
            }


            txtTravelZoneApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    updateUIAndMoveBack();
                }
            });


            txtTravelZoneSelectAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllCheckboxStatus(lytAvailableZone, true);
                }
            });


            txtTravelZoneClearAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllCheckboxStatus(lytAvailableZone, false);
                }
            });


            txtTravelZoneReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    resetAllCheckboxStatus(lytAvailableZone, (int)sp.get(getString(R.string.key_selected_travel_zone_id)));
                }
            });

        }

    }

    private void updateUIAndMoveBack()
    {
        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFilterApply(true);
        data.setShowAll(false);
        data.setFragmentName((new SelectFlightPassFragment()).getClass().getName());
        communicator.onResponse(data);


        ((MainActivity) fragmentActivity).onBackPressed();
    }


    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("FilterFlightPassFragment >> onAttach(Activity) called");
        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    private void setSelector(int selected)
    {
        int selectedRedBarIds[] = {R.id.viewSelectorTravelZone, R.id.viewSelectorNumberFlight, R.id.viewSelectorTravelPeriod, R.id.viewSelectorPricePerFlight};
        int selectedLayoutIds[] = {R.id.lytDetailsTravelZone, R.id.lytDetailsNumberFlight, R.id.lytDetailsTravelPeriod, R.id.lytDetailsPricePerFlight};
        int selectedAvailableIds[] = {R.id.lytAvailableZone, R.id.lytAvailableFlights, R.id.lytAvailableTravelPeriod, R.id.lytAvailablePricePerFlight};


        for (int index = 0; index < selectedRedBarIds.length; index++) {
            if(selectedRedBarIds[index] == selected)
            {
                view.findViewById(selectedRedBarIds[index]).setVisibility(View.VISIBLE);
                view.findViewById(selectedLayoutIds[index]).setVisibility(View.VISIBLE);
            }
            else
            {
                view.findViewById(selectedRedBarIds[index]).setVisibility(View.INVISIBLE);
                view.findViewById(selectedLayoutIds[index]).setVisibility(View.GONE);
            }

            removeAllCheckbox((LinearLayout) view.findViewById(selectedAvailableIds[index]));
        }
    }

    private void removeAllCheckbox(LinearLayout lytAvailable)
    {
        if(lytAvailable == null)
        {
            return;
        }
        int count = lytAvailable.getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            View child = lytAvailable.getChildAt(i);

            if(child instanceof CheckBox)
            {
                lytAvailable.removeViewAt(i);//after removing all child views shifts, so always remove at 0th position
            }
        }
    }

    private void setAllCheckboxStatus(LinearLayout lytAvailable, boolean isChecked)
    {
        if(lytAvailable == null)
        {
            return;
        }
        int count = lytAvailable.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = lytAvailable.getChildAt(i);

            if(child instanceof CheckBox)
            {
                ((CheckBox)lytAvailable.getChildAt(i)).setChecked(isChecked);
                //sp.put(getString(R.string.key_filter_travel_zone_ZoneId) + "_" + i, isChecked ? (int)((CheckBox)lytAvailable.getChildAt(i)).getTag(keyType) : 0);
            }
        }
    }

    private void resetAllCheckboxStatus(LinearLayout lytAvailable, Object id)
    {
        if(lytAvailable == null)
        {
            return;
        }
        int count = lytAvailable.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = lytAvailable.getChildAt(i);

            if(child instanceof CheckBox)
            {
                if(id instanceof Integer)
                {
                    if((int)child.getTag() == (int)id)
                    {
                        ((CheckBox)lytAvailable.getChildAt(i)).setChecked(true);
                    }
                    else
                    {
                        ((CheckBox)lytAvailable.getChildAt(i)).setChecked(false);
                    }
                }
                else if(id instanceof String)
                {
                    if(((String)child.getTag()).equals((String)id))
                    {
                        ((CheckBox)lytAvailable.getChildAt(i)).setChecked(true);
                    }
                    else
                    {
                        ((CheckBox)lytAvailable.getChildAt(i)).setChecked(false);
                    }
                }


            }
        }
    }


}

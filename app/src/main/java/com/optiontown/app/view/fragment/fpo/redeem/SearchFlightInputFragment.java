package com.optiontown.app.view.fragment.fpo.redeem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.BlackListDates;
import com.optiontown.app.model.redeem.RedeemSearchParam;
import com.optiontown.app.model.redeem.RedeemSearchParamOpenJaw;
import com.optiontown.app.model.redeem.SelectedDataForFlight2;
import com.optiontown.app.model.redeem.SelectedPassDataForSearchFlight;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.InterceptedLinearLayout;
import com.optiontown.app.view.customview.OTRadioButton;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.optiontown.app.model.redeem.RedeemSearchParamTwoWay;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by parasmani.sharma on 05/09/2016.
 */
public class SearchFlightInputFragment extends BaseFragment {

    private View view;
    InternationalizeData localization;
    RelativeLayout lytMain;
    OTTextView txtAirlineName, txtFrom, txtTo, txtDepart, txtReturn, txtTravelFlexibility, txtReturnFlexibility, txtPassenger, txtCabin;
    NestedScrollView svParent;
    LinearLayout  lytFromScroll, lytToScroll, lytDepartScroll, lytReturnScroll, lytDepartFlexibilityScroll, lytReturnFlexibilityScroll, lytPassengerSV, lytCavinSv;
    private InterceptedLinearLayout lytFlightPassScroll;
    private int selectedId;
    private SelectedPassDataForSearchFlight selectedPassDataForSearchFlight;
    private RadioGroup rGroupTrips;
    private String passValue = null;
    private OTTextView txtHelpMeChooseFrom;
    private OTTextView txtHelpMeChooseTo;
    private OTTextView txtHelpMeChooseReturnFlexibity;
    private OTTextView txtHelpMeChooseReturn;
    private LinearLayout lytReturn;
    private LinearLayout lytReturnFlexibility;
    private int tripType;
    private OTTextView txtTravelFlexibilityLabel;
    private LinearLayout lytDepartAndReturnFlexibility;
    private OTTextView txtSearch;
    private String departAirportValueId;
    private String arriveAirportValueId;
    private String departAirportValueIdFlight2;
    private String arriveAirportValueIdFlight2;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytFromRepeat;
    private LinearLayout lytToRepeat;
    private OTTextView txtFromRepeat;
    private OTTextView txtToRepeat;
    private OTTextView txtFlight1;
    private OTTextView txtFlight2;
    private NestedScrollView svFrom;
    private NestedScrollView svSelectFlightPass;
    private NestedScrollView svTo;
    private NestedScrollView svlytDepartFlexibility;
    private NestedScrollView svlytReturnFlexibility;
    private NestedScrollView svPassenger;
    private String[] toListDropDownToRepeat;
    private String[] fromListDropDownFromRepeat;
    private String[] toListDropDown;
    private String[] fromListDropDown;
    private SelectedDataForFlight2 selectedDataForFlight2;
    private OTTextView txtHelpMeChooseToRepeat;
    private OTTextView txtHelpMeChooseFromRepeat;
    private OTTextView txtpassengerlabel;
    private OTTextView txtCabinLabel;
    private LinearLayout lytSubFrom;
    private LinearLayout lytSubTo;
    private LinearLayout lytSubDepart;
    private LinearLayout lytSubTravelFlexibility;
    private LinearLayout lytSubFromRepeat;
    private LinearLayout lytSubToRepeat;
    private LinearLayout lytSubReturn;
    private LinearLayout lytSubReturnFlexibility;
    public static Date departDate;
    private LinearLayout lytSelectFligthPass;
    private View lytAppBottomBar;
    private LinearLayout lytCloseDone;
    private OTTextView txtClose;
    private OTTextView txtDone;
    private int previosTripType;
    private BlackListDates blackListData;
    private ArrayList<Date> disabledDatesDepart = new ArrayList<Date>();
    private ArrayList<Date> disabledDatesReturn = new ArrayList<Date>();
    private RelativeLayout bottom_bar_layout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fpo_redeem_search_flight_input, container, false);

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), new SearchFlightInputFragment().getClass().getName());
        Utils.updateBottomBarFpoRedeemForFeatures(view, new SearchFlightInputFragment().getClass().getName(), false);

        try {
            selectedId = ((int) getArguments().getSerializable(getString(R.string.key_serializable)));
            System.out.println("selected Pass id >>>>>>>>>>>>>>>>>>" + selectedId);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }

        tripType = 1;
        getUIReference();
        callApiUpadteUI(selectedId);

        return view;
    }

    public void callApiUpadteUI(final int SelectedId) {

        System.out.println("selected id>>>>>>>>>>>>>>>" + SelectedId);
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_Book_Flight_LabelData)
                + "&selectedId=" + SelectedId;


        final JSONObject requestObject = new JSONObject();

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null)
                        {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        selectedPassDataForSearchFlight = ParseManager.getInstance().fromJSON(response, SelectedPassDataForSearchFlight.class);

                        getUIReference();
                        updateUI();


                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void getUIReference() {

        txtCabinLabel = (OTTextView) view.findViewById(R.id.txtCabinLabel);
        txtpassengerlabel = (OTTextView) view.findViewById(R.id.txtpassengerlabel);
        lytFromRepeat = (LinearLayout) view.findViewById(R.id.lytFromRepeat);
        lytToRepeat = (LinearLayout) view.findViewById(R.id.lytToRepeat);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        txtSearch = (OTTextView) view.findViewById(R.id.txtSearch);
        txtTravelFlexibilityLabel = (OTTextView) view.findViewById(R.id.txtTravelFlexibilityLabel);
        lytDepartAndReturnFlexibility = (LinearLayout) view.findViewById(R.id.lytDepartAndReturnFlexibility);
        lytReturn = (LinearLayout) view.findViewById(R.id.lytReturn);
        lytReturnFlexibility = (LinearLayout) view.findViewById(R.id.lytReturnFlexibility);
        rGroupTrips = (RadioGroup) view.findViewById(R.id.rGroupTrips);
        txtAirlineName = (OTTextView) view.findViewById(R.id.txtAirlineName);
        txtFrom = (OTTextView) view.findViewById(R.id.txtFrom);
        txtTo = (OTTextView) view.findViewById(R.id.txtTo);
        txtFromRepeat = (OTTextView) view.findViewById(R.id.txtFromRepeat);
        txtToRepeat = (OTTextView) view.findViewById(R.id.txtToRepeat);
        txtDepart = (OTTextView) view.findViewById(R.id.txtDepart);
        txtReturn = (OTTextView) view.findViewById(R.id.txtReturn);
        txtTravelFlexibility = (OTTextView) view.findViewById(R.id.txtTravelFlexibility);
        txtReturnFlexibility = (OTTextView) view.findViewById(R.id.txtReturnFlexibility);
        txtPassenger = (OTTextView) view.findViewById(R.id.txtPassenger);
        txtCabin = (OTTextView) view.findViewById(R.id.txtCabin);
        txtFlight1 = (OTTextView) view.findViewById(R.id.txtFlight1);
        txtFlight2 = (OTTextView) view.findViewById(R.id.txtFlight2);

        lytSubFrom = (LinearLayout) view.findViewById(R.id.lytSubFrom);
        lytSubTo = (LinearLayout) view.findViewById(R.id.lytSubTo);
        lytSubFromRepeat = (LinearLayout) view.findViewById(R.id.lytSubFromRepeat);
        lytSubToRepeat = (LinearLayout) view.findViewById(R.id.lytSubToRepeat);
        lytSubDepart = (LinearLayout) view.findViewById(R.id.lytSubDepart);
        lytSubReturn = (LinearLayout) view.findViewById(R.id.lytSubReturn);
        lytSubTravelFlexibility = (LinearLayout) view.findViewById(R.id.lytSubTravelFlexibility);
        lytSubReturnFlexibility = (LinearLayout) view.findViewById(R.id.lytSubReturnFlexibility);

        lytMain = (RelativeLayout) view.findViewById(R.id.lytMain);
        lytMain.setVisibility(View.GONE);

        svParent = (NestedScrollView) view.findViewById(R.id.svParent);
        lytMain = (RelativeLayout) svParent.findViewById(R.id.lytMain);

        svSelectFlightPass = (NestedScrollView) lytMain.findViewById(R.id.svSelectFlightPass);
        svFrom = (NestedScrollView) lytMain.findViewById(R.id.svFrom);
        svTo = (NestedScrollView) lytMain.findViewById(R.id.svTo);
        svlytDepartFlexibility = (NestedScrollView) lytMain.findViewById(R.id.svlytDepartFlexibility);
        svlytReturnFlexibility = (NestedScrollView) lytMain.findViewById(R.id.svlytReturnFlexibility);
        svPassenger = (NestedScrollView) lytMain.findViewById(R.id.svPassenger);

        lytFlightPassScroll = (InterceptedLinearLayout) svSelectFlightPass.findViewById(R.id.lytFlightPassScroll);
        lytFromScroll = (LinearLayout) svFrom.findViewById(R.id.lytFromScroll);
        lytToScroll = (LinearLayout) svTo.findViewById(R.id.lytToScroll);
        lytDepartFlexibilityScroll = (LinearLayout) svlytDepartFlexibility.findViewById(R.id.lytDepartFlexibilityScroll);
        lytReturnFlexibilityScroll = (LinearLayout) svlytReturnFlexibility.findViewById(R.id.lytReturnFlexibilityScroll);
        lytPassengerSV = (LinearLayout) svPassenger.findViewById(R.id.lytPassengerSV);

        lytMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svSelectFlightPass.setVisibility(View.GONE);
                svFrom.setVisibility(View.GONE);
                svTo.setVisibility(View.GONE);
                svlytDepartFlexibility.setVisibility(View.GONE);
                svlytReturnFlexibility.setVisibility(View.GONE);
                svPassenger.setVisibility(View.GONE);
            }
        });

        bottom_bar_layout = (RelativeLayout) view.findViewById(R.id.bottom_bar_layout);
        lytAppBottomBar = (View) view.findViewById(R.id.app_bottom_bar_fpo_redeem);
        lytCloseDone = (LinearLayout) view.findViewById(R.id.lytCloseDone);

        txtClose = (OTTextView) view.findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onBackPressed();
            }
        });
        txtDone = (OTTextView) view.findViewById(R.id.txtDone);
        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSearch.performClick();
            }
        });

    }

    private void showDialog(int type) {
        selectedPassDataForSearchFlight.setTYPE_DIALOG(type);
        OTDialogFragment dialog = new OTDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(getActivity().getString(R.string.key_serializable), (Serializable) selectedPassDataForSearchFlight);
        dialog.setArguments(bundle);

        dialog.show(((FragmentActivity) getActivity()).getSupportFragmentManager(), "");
    }

    private void updateUI() {
        lytMain.setVisibility(View.VISIBLE);
        txtAirlineName.setText(selectedPassDataForSearchFlight.getPassListDropDown().get(0).getLabel());
        txtFrom.setText(selectedPassDataForSearchFlight.getFromLabel());
        txtTo.setText(selectedPassDataForSearchFlight.getToLabel());
        txtFromRepeat.setText(selectedPassDataForSearchFlight.getFromLabel());
        txtToRepeat.setText(selectedPassDataForSearchFlight.getToLabel());
        txtDepart.setText(selectedPassDataForSearchFlight.getDepartAirportLabel());
        txtReturn.setText(selectedPassDataForSearchFlight.getReturnLabel());
        txtpassengerlabel.setText(selectedPassDataForSearchFlight.getPaxLabel());
        txtPassenger.setText(selectedPassDataForSearchFlight.getPaxLabel());
        txtCabinLabel.setText(selectedPassDataForSearchFlight.getCabinLabel());
        txtSearch.setText(selectedPassDataForSearchFlight.getSearchLabel());

        /*
        * Check Travel Flexibility Flag
        * */

        for (int i = 0; i < selectedPassDataForSearchFlight.getTripDetails().size(); i++) {
            if (selectedPassDataForSearchFlight.getTripDetails().get(i).getValue().equals("1")) {
                tripType = 1;

                lytFromRepeat.setVisibility(View.GONE);
                lytToRepeat.setVisibility(View.GONE);
                lytReturn.setVisibility(View.GONE);
                lytReturnFlexibility.setVisibility(View.GONE);

                txtFlight1.setVisibility(View.GONE);
                txtFlight2.setVisibility(View.GONE);

                break;
            } else if (selectedPassDataForSearchFlight.getTripDetails().get(i).getValue().equals("2")) {

                tripType = 2;

                lytFromRepeat.setVisibility(View.GONE);
                lytToRepeat.setVisibility(View.GONE);
                lytReturn.setVisibility(View.VISIBLE);
                if (selectedPassDataForSearchFlight.getTravelFlexibilityFlag() == 1) {
                    lytReturnFlexibility.setVisibility(View.VISIBLE);
                }

                txtFlight1.setVisibility(View.GONE);
                txtFlight2.setVisibility(View.GONE);

                break;
            } else if (selectedPassDataForSearchFlight.getTripDetails().get(i).getValue().equals("4")) {

                if (selectedPassDataForSearchFlight.getTripDetails().get(0).getValue().equals("") && selectedPassDataForSearchFlight.getTripDetails().get(1).getValue().equals("")) {
                    tripType = 4;

                    lytReturn.setVisibility(View.VISIBLE);
                    lytFromRepeat.setVisibility(View.VISIBLE);
                    txtFlight1.setVisibility(View.VISIBLE);
                    txtFlight2.setVisibility(View.VISIBLE);
                    lytToRepeat.setVisibility(View.VISIBLE);
                }

                break;
            }
        }


        if (selectedPassDataForSearchFlight.getTravelFlexibilityFlag() == 1) {
            txtTravelFlexibilityLabel.setVisibility(View.VISIBLE);
            lytDepartAndReturnFlexibility.setVisibility(View.VISIBLE);
        } else if (selectedPassDataForSearchFlight.getTravelFlexibilityFlag() == 0) {
            txtTravelFlexibilityLabel.setVisibility(View.GONE);
            lytDepartAndReturnFlexibility.setVisibility(View.GONE);
        }


        updateOnClickEvents();

    }

    private void updateOnClickEvents() {

        try {
            String alreadySelectedPassId = null;
            for (int i = 0; i < selectedPassDataForSearchFlight.getPassListDropDown().size(); i++) {
                if (selectedPassDataForSearchFlight.getPassListDropDown().get(i).getValue().equals(selectedId + "")) {
                    alreadySelectedPassId = selectedPassDataForSearchFlight.getPassListDropDown().get(i).getLabel();
                }
            }
            txtAirlineName.setText(alreadySelectedPassId);
            txtAirlineName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] passListDropDown = new String[selectedPassDataForSearchFlight.getPassListDropDown().size()];
                    for (int i = 0; i < selectedPassDataForSearchFlight.getPassListDropDown().size(); i++) {
                        passListDropDown[i] = selectedPassDataForSearchFlight.getPassListDropDown().get(i).getLabel();
                    }
                    createDropdownViewForPass(passListDropDown, lytFlightPassScroll, txtAirlineName, new LinearLayout[]{lytFromScroll, lytToScroll, lytDepartScroll,
                            lytReturnScroll, lytDepartFlexibilityScroll, lytReturnFlexibilityScroll, lytPassengerSV});

                    //callApiUpadteUI(selectedId);
                }
            });

            setRadioButtonForTrip(tripType);

            setRadioButtonClickEvent(tripType);

            lytSubFrom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);

                    p.addRule(RelativeLayout.BELOW, R.id.lytFrom);
                    p.addRule(RelativeLayout.ALIGN_LEFT, R.id.lytFrom);
                    p.addRule(RelativeLayout.ALIGN_RIGHT, R.id.lytFrom);
                    svFrom.setLayoutParams(p);

                    fromListDropDown = new String[0];
                    //fromListDropDown = setDropDownListData(fromListDropDown, txtTo);
                    fromListDropDown = dropDownListForAirport(fromListDropDown, txtFrom, txtTo);

                    createDropdownView(fromListDropDown, lytFromScroll, txtFrom, new LinearLayout[]{lytFlightPassScroll, lytToScroll, lytDepartFlexibilityScroll, lytReturnFlexibilityScroll, lytPassengerSV});
                }
            });

            lytSubTo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);

                    p.addRule(RelativeLayout.BELOW, R.id.lytTo);
                    p.addRule(RelativeLayout.ALIGN_LEFT, R.id.lytTo);
                    p.addRule(RelativeLayout.ALIGN_RIGHT, R.id.lytTo);
                    svTo.setLayoutParams(p);


                    toListDropDown = new String[0];
                    //toListDropDown = setDropDownListData(toListDropDown, txtFrom);
                    toListDropDown = dropDownListForAirport(toListDropDown, txtTo, txtFrom);


                    if (toListDropDown == null) {
                        //Utils.showToast(getActivity(), "Select departure Airport");
                    } else {

                        createDropdownView(toListDropDown, lytToScroll, txtTo, new LinearLayout[]{lytFlightPassScroll, lytFromScroll, lytDepartFlexibilityScroll, lytReturnFlexibilityScroll, lytPassengerSV});

                    }

                }
            });


            lytSubFromRepeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);

                    p.addRule(RelativeLayout.BELOW, R.id.lytFromRepeat);
                    p.addRule(RelativeLayout.ALIGN_LEFT, R.id.lytFromRepeat);
                    p.addRule(RelativeLayout.ALIGN_RIGHT, R.id.lytFromRepeat);
                    svFrom.setLayoutParams(p);

                    if (selectedDataForFlight2 != null) {
                        fromListDropDownFromRepeat = new String[selectedDataForFlight2.getDepartAirport().size()];
                        for (int i = 0; i < selectedDataForFlight2.getDepartAirport().size(); i++) {
                            fromListDropDownFromRepeat[i] = selectedDataForFlight2.getDepartAirport().get(i).getDeptLabel().toString();
                            System.out.println("Depart Airport : " + selectedDataForFlight2.getDepartAirport().get(i).getDeptLabel().toString());
                        }
                    }

                    if (fromListDropDownFromRepeat != null) {
                        createDropdownView(fromListDropDownFromRepeat, lytFromScroll, txtFromRepeat, new LinearLayout[]{lytFlightPassScroll, lytToScroll, lytDepartFlexibilityScroll, lytReturnFlexibilityScroll, lytPassengerSV});
                    }

                }
            });

            lytSubToRepeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);

                    p.addRule(RelativeLayout.BELOW, R.id.lytToRepeat);
                    p.addRule(RelativeLayout.ALIGN_LEFT, R.id.lytToRepeat);
                    p.addRule(RelativeLayout.ALIGN_RIGHT, R.id.lytToRepeat);
                    svTo.setLayoutParams(p);


                    if (selectedDataForFlight2 != null) {
                        toListDropDownToRepeat = new String[selectedDataForFlight2.getArriveAirport().size()];
                        for (int i = 0; i < selectedDataForFlight2.getArriveAirport().size(); i++) {
                            toListDropDownToRepeat[i] = selectedDataForFlight2.getArriveAirport().get(i).getDeptLabel();
                        }
                    }

                    if (toListDropDownToRepeat == null) {
                        //Utils.showToast(getActivity(), "Select departure Airport");
                    } else {
                        createDropdownView(toListDropDownToRepeat, lytToScroll, txtToRepeat, new LinearLayout[]{lytFlightPassScroll, lytFromScroll, lytDepartFlexibilityScroll, lytReturnFlexibilityScroll, lytPassengerSV});
                    }

                }
            });


            lytSubDepart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtReturn.setText(selectedPassDataForSearchFlight.getReturnLabel());
                    initializeCalendar(disabledDatesDepart,txtDepart, txtReturn);
                }
            });

            lytSubReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initializeCalendar(disabledDatesReturn,txtReturn, txtDepart);
                }
            });

            lytSubTravelFlexibility.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] travelFlexibilityData = new String[selectedPassDataForSearchFlight.getDepartList().size()];
                    for (int i = 0; i < selectedPassDataForSearchFlight.getDepartList().size(); i++) {
                        travelFlexibilityData[i] = selectedPassDataForSearchFlight.getDepartList().get(i).getLabel();
                    }
                    createDropdownView(travelFlexibilityData, lytDepartFlexibilityScroll, txtTravelFlexibility, new LinearLayout[]{lytFlightPassScroll, lytFromScroll,
                            lytToScroll, lytReturnFlexibilityScroll, lytPassengerSV});
                }
            });

            lytSubReturnFlexibility.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] returnFlexibilityData = new String[selectedPassDataForSearchFlight.getDepartList().size()];
                    for (int i = 0; i < selectedPassDataForSearchFlight.getDepartList().size(); i++) {
                        returnFlexibilityData[i] = selectedPassDataForSearchFlight.getDepartList().get(i).getLabel();
                    }
                    createDropdownView(returnFlexibilityData, lytReturnFlexibilityScroll, txtReturnFlexibility, new LinearLayout[]{lytFlightPassScroll, lytFromScroll,
                            lytToScroll, lytDepartFlexibilityScroll, lytPassengerSV});
                }
            });

            txtPassenger.setText(selectedPassDataForSearchFlight.getPaxLabel().toString());
            txtPassenger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String[] noOfPassenger = new String[selectedPassDataForSearchFlight.getPaxList().size()];
                    for (int i = 0; i < selectedPassDataForSearchFlight.getPaxList().size(); i++) {
                        noOfPassenger[i] = selectedPassDataForSearchFlight.getPaxList().get(i).getLabel();
                    }
                    createDropdownView(noOfPassenger, lytPassengerSV, txtPassenger, new LinearLayout[]{lytFlightPassScroll, lytFromScroll, lytToScroll, lytDepartFlexibilityScroll, lytReturnFlexibilityScroll});
                }
            });

            txtCabin.setText(selectedPassDataForSearchFlight.getCabinName().toString());


            /*
            * dialog box event
            * */

            txtHelpMeChooseFrom = (OTTextView) view.findViewById(R.id.txtHelpMeChooseFrom);
            txtHelpMeChooseFrom.setText(localization.getLABL_Help_Me_Choose_Label());
            txtHelpMeChooseFrom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(2);
                }
            });

            txtHelpMeChooseTo = (OTTextView) view.findViewById(R.id.txtHelpMeChooseTo);
            txtHelpMeChooseTo.setText(localization.getLABL_Help_Me_Choose_Label());
            txtHelpMeChooseTo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(2);
                }
            });

            /*
            * flight 2
            * */
            txtHelpMeChooseFromRepeat = (OTTextView) view.findViewById(R.id.txtHelpMeChooseFromRepeat);
            txtHelpMeChooseFromRepeat.setText(localization.getLABL_Help_Me_Choose_Label());
            txtHelpMeChooseFromRepeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(2);
                }
            });
            txtHelpMeChooseToRepeat = (OTTextView) view.findViewById(R.id.txtHelpMeChooseToRepeat);
            txtHelpMeChooseToRepeat.setText(localization.getLABL_Help_Me_Choose_Label());
            txtHelpMeChooseToRepeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(2);
                }
            });

            txtHelpMeChooseReturn = (OTTextView) view.findViewById(R.id.txtHelpMeChooseReturn);
            txtHelpMeChooseReturn.setText(localization.getLABL_Help_Me_Choose_Label());
            txtHelpMeChooseReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(3);
                }
            });

            txtHelpMeChooseReturnFlexibity = (OTTextView) view.findViewById(R.id.txtHelpMeChooseReturnFlexibity);
            txtHelpMeChooseReturnFlexibity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(1);
                }
            });


            txtSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    /*svParent.fullScroll(NestedScrollView.FOCUS_UP);
                    lytErrorMessage.removeAllViews();*/
                    Utils.updateActionBarForFeatures(getActivity(), new SearchFlightInputFragment().getClass().getName(), localization.getBookFlightsLabel(), null);
                    svParent.fullScroll(NestedScrollView.FOCUS_UP);
                    lytErrorMessage.removeAllViews();

                    ArrayList<String> listError = validateAllInputs();
                    if (listError.size() > 0) {
                        //show error message

                        lytError.setVisibility(View.VISIBLE);

                        for (int index = 0; index < listError.size(); index++) {
                            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                        }

                    } else {
                        lytError.setVisibility(View.GONE);

                        /* *//**//*
                          * send data using bundle to next fragment
                          * *//**//**/
                        ;


                        String datetxtDepart = txtDepart.getText().toString();//Utils.converDateToFormat_ddMMMyyyy(Utils.convertToDate_ddMMMyyyy(txtDepart.getText().toString()), Utils.getLocalForCommunication()).toString().replace(' ', '-');
                        String datetxtReturn = txtReturn.getText().toString();

                        try {

                            System.out.println("selectedId <<<<<<<<<<<<<<   " + selectedId);
                            System.out.println("tripType <<<<<<<<<<<<<<    " + tripType);
                            System.out.println("txtPassenger <<<<<<<<<<<<<<   " + txtPassenger.getText().toString());
                            System.out.println("txtDepart <<<<<<<<<<<<<<   " + datetxtDepart);
                            System.out.println("txtReturn <<<<<<<<<<<<<<   " + datetxtReturn);
                            System.out.println("depart1 <<<<<<<<<<<<<<   " + departAirportValueId);
                            System.out.println("arrive1 <<<<<<<<<<<<<<   " + arriveAirportValueId);
                            System.out.println("departAirportValueIdFlight2 <<<<<<<<<<<<<<   " + departAirportValueIdFlight2);
                            System.out.println("arriveAirportValueIdFlight2 <<<<<<<<<<<<<<   " + arriveAirportValueIdFlight2);

                            if (tripType == 1) {


                                RedeemSearchParam param = new RedeemSearchParam();
                                param.setPassId(selectedId + "");
                                param.setJourneyType(tripType + "");
                                param.setNumberOfPax(txtPassenger.getText().toString());
                                param.setDepartDateJourney1(datetxtDepart);
                                param.setDepartAirportJourney1(departAirportValueId);
                                param.setArriveAirportJourney1(arriveAirportValueId);

                                if(selectedPassDataForSearchFlight.getTravelFlexibilityFlag() == 1)
                                {
                                    for (int i = 0; i < selectedPassDataForSearchFlight.getDepartList().size(); i++) {
                                        if(selectedPassDataForSearchFlight.getDepartList().get(i).getLabel().equals(txtTravelFlexibility.getText().toString()))
                                        {
                                            param.setSelectFlexbilityHours1(selectedPassDataForSearchFlight.getDepartList().get(i).getValue());
                                            break;
                                        }
                                        else
                                        {
                                            param.setSelectFlexbilityHours1("0");
                                        }
                                    }
                                }
                                else
                                {
                                    param.setSelectFlexbilityHours1("0");
                                }



                                Utils.moveToFragment(getActivity(), new RedeemSearchResultFragment(), param, 0);
                            } else if (tripType == 2) {
                                RedeemSearchParamTwoWay param = new RedeemSearchParamTwoWay();
                                param.setPassId(selectedId + "");
                                param.setJourneyType(tripType + "");
                                param.setNumberOfPax(txtPassenger.getText().toString());
                                param.setDepartDateJourney1(datetxtDepart);
                                param.setDepartDateJourney2(datetxtReturn);
                                param.setDepartAirportJourney1(departAirportValueId);
                                param.setArriveAirportJourney1(arriveAirportValueId);

                                if(selectedPassDataForSearchFlight.getTravelFlexibilityFlag() == 1)
                                {
                                    for (int i = 0; i < selectedPassDataForSearchFlight.getDepartList().size(); i++) {
                                        if(selectedPassDataForSearchFlight.getDepartList().get(i).getLabel().equals(txtTravelFlexibility.getText().toString()))
                                        {
                                            param.setSelectFlexbilityHours1(selectedPassDataForSearchFlight.getDepartList().get(i).getValue());
                                            break;
                                        }
                                        else
                                        {
                                            param.setSelectFlexbilityHours1("0");
                                        }
                                    }

                                    for (int i = 0; i < selectedPassDataForSearchFlight.getDepartList().size(); i++) {
                                        if(selectedPassDataForSearchFlight.getDepartList().get(i).getLabel().equals(txtReturnFlexibility.getText().toString()))
                                        {
                                            param.setSelectFlexbilityHours2(selectedPassDataForSearchFlight.getDepartList().get(i).getValue());
                                            break;
                                        }
                                        else
                                        {
                                            param.setSelectFlexbilityHours2("0");
                                        }
                                    }
                                }
                                else
                                {
                                    param.setSelectFlexbilityHours1("0");
                                    param.setSelectFlexbilityHours2("0");
                                }



                                Utils.moveToFragment(getActivity(), new RedeemSearchResultFragment(), param, 0);
                            } else if (tripType == 4) {
                                RedeemSearchParamOpenJaw param = new RedeemSearchParamOpenJaw();
                                param.setPassId(selectedId + "");
                                param.setJourneyType(tripType + "");
                                param.setNumberOfPax(txtPassenger.getText().toString());
                                param.setDepartDateJourney1(datetxtDepart);
                                param.setDepartDateJourney2(datetxtReturn);
                                param.setDepartAirportJourney1(departAirportValueId);
                                param.setArriveAirportJourney1(arriveAirportValueId);
                                param.setDepartAirportJourney2(departAirportValueIdFlight2);
                                param.setArriveAirportJourney2(arriveAirportValueIdFlight2);

                                if(selectedPassDataForSearchFlight.getTravelFlexibilityFlag() == 1)
                                {
                                    for (int i = 0; i < selectedPassDataForSearchFlight.getDepartList().size(); i++) {
                                        if(selectedPassDataForSearchFlight.getDepartList().get(i).getLabel().equals(txtTravelFlexibility.getText().toString()))
                                        {
                                            param.setSelectFlexbilityHours1(selectedPassDataForSearchFlight.getDepartList().get(i).getValue());
                                            break;
                                        }
                                        else
                                        {
                                            param.setSelectFlexbilityHours1("0");
                                        }
                                    }

                                    for (int i = 0; i < selectedPassDataForSearchFlight.getDepartList().size(); i++) {
                                        if(selectedPassDataForSearchFlight.getDepartList().get(i).getLabel().equals(txtReturnFlexibility.getText().toString()))
                                        {
                                            param.setSelectFlexbilityHours2(selectedPassDataForSearchFlight.getDepartList().get(i).getValue());
                                            break;
                                        }
                                        else
                                        {
                                            param.setSelectFlexbilityHours2("0");
                                        }
                                    }
                                }
                                else
                                {
                                    param.setSelectFlexbilityHours1("0");
                                    param.setSelectFlexbilityHours2("0");
                                }

                                Utils.moveToFragment(getActivity(), new RedeemSearchResultFragment(), param, 0);
                            }



                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ArrayList<String> validateAllInputs() {
        ArrayList<String> listError = new ArrayList<>();

        if(txtAirlineName.getText().toString().equals(selectedPassDataForSearchFlight.getPassListDropDown().get(0).getLabel()))
        {
            listError.add("Please select a pass");
        }

        if (txtFrom.getText().toString().equals(selectedPassDataForSearchFlight.getFromLabel())) {
            listError.add("Select Departure Airport");
        }

        if (txtTo.getText().toString().equals(selectedPassDataForSearchFlight.getToLabel())) {
            listError.add("Select Arrival Airport");
        }



        if(tripType == 4)
        {
            if (txtFromRepeat.getText().toString().equals(selectedPassDataForSearchFlight.getFromLabel())) {
                listError.add("Select Departure Airport For Flight 2");
            }

            if (txtToRepeat.getText().toString().equals(selectedPassDataForSearchFlight.getToLabel())) {
                listError.add("Select Arrival Airport For Flight 2");
            }
        }

        if (txtDepart.getText().toString().equals(selectedPassDataForSearchFlight.getDepartAirportLabel())) {
            listError.add("Select Depart Date");
        }

        if(tripType == 2 || tripType == 4)
        {
            if (txtReturn.getText().toString().equals(selectedPassDataForSearchFlight.getReturnLabel())) {
                listError.add("Select Return Date");
            }
        }

        if(selectedPassDataForSearchFlight.getTravelFlexibilityFlag() == 1)
        {
            if (txtTravelFlexibility.getText().toString().equals(getString(R.string.string_depart))) {
                listError.add("Select Travel Flexibility");
            }


        }

        if((tripType == 2 || tripType == 4) && selectedPassDataForSearchFlight.getTravelFlexibilityFlag() == 1)
        {
            if (txtReturnFlexibility.getText().toString().equals(getString(R.string.string_return))) {
                listError.add("Select Return Flexibility");
            }

        }



        if (txtPassenger.getText().toString().equals(selectedPassDataForSearchFlight.getPaxLabel())) {
            listError.add("Select Passengers");
        }



        return listError;
    }

    private void setRadioButtonClickEvent(final int trip) {



        System.out.println("TripType >>>>>>>>>>>>>>>>>>>>>  " + trip);
        if(trip == 4)
        {
            txtTo.setText(selectedPassDataForSearchFlight.getToLabel());
            txtFromRepeat.setText(selectedPassDataForSearchFlight.getFromLabel());
            txtToRepeat.setText(selectedPassDataForSearchFlight.getToLabel());

        }

            txtFrom.setText(selectedPassDataForSearchFlight.getFromLabel());
            txtTo.setText(selectedPassDataForSearchFlight.getToLabel());
            txtFromRepeat.setText(selectedPassDataForSearchFlight.getFromLabel());
            txtToRepeat.setText(selectedPassDataForSearchFlight.getToLabel());
            txtDepart.setText(selectedPassDataForSearchFlight.getDepartAirportLabel());
            txtReturn.setText(selectedPassDataForSearchFlight.getReturnLabel());
            txtpassengerlabel.setText(selectedPassDataForSearchFlight.getPaxLabel());
            txtPassenger.setText(selectedPassDataForSearchFlight.getPaxLabel());
            txtCabinLabel.setText(selectedPassDataForSearchFlight.getCabinLabel());
            txtTravelFlexibility.setText(selectedPassDataForSearchFlight.getDepartAirportLabel());
            txtReturnFlexibility.setText(selectedPassDataForSearchFlight.getReturnLabel());



        int childCount = rGroupTrips.getChildCount();
        System.out.println("childCount >>>>>>>>>>>>>>>>>>>>>  " + childCount);


        for (int k = 0; k < rGroupTrips.getChildCount(); k++) {
            if (rGroupTrips.getChildAt(k).isShown()) {
                LinearLayout oneWayLay = (LinearLayout) rGroupTrips.getChildAt(k);
                RadioButton radioBut = (RadioButton) oneWayLay.getChildAt(0);

                if (radioBut.getTag().toString().equals(trip + "")) {
                    radioBut.setChecked(true);
                    break;
                }

            }

        }

    }

    private void setRadioButtonForTrip(final int trip) {

        try {
            rGroupTrips.removeAllViews();


            for (int i = 0; i < selectedPassDataForSearchFlight.getTripDetails().size(); i++) {

                LinearLayout ll = new LinearLayout(getActivity());
                ll.setOrientation(LinearLayout.HORIZONTAL);
                rGroupTrips.addView(ll);
                final OTRadioButton radioButton = new OTRadioButton(getActivity());


                if (selectedPassDataForSearchFlight.getTripDetails().get(i).getValue().equals("1")) {
                    radioButton.setText(selectedPassDataForSearchFlight.getTripDetails().get(i).getLabel());
                    radioButton.setTag(selectedPassDataForSearchFlight.getTripDetails().get(i).getValue());
                    ll.addView(radioButton);
                } else if (selectedPassDataForSearchFlight.getTripDetails().get(i).getValue().equals("2")) {
                    radioButton.setText(selectedPassDataForSearchFlight.getTripDetails().get(i).getLabel());
                    radioButton.setTag(selectedPassDataForSearchFlight.getTripDetails().get(i).getValue());
                    ll.addView(radioButton);
                } else if (selectedPassDataForSearchFlight.getTripDetails().get(i).getValue().equals("4")) {
                    radioButton.setText(selectedPassDataForSearchFlight.getTripDetails().get(i).getLabel());
                    radioButton.setTag(selectedPassDataForSearchFlight.getTripDetails().get(i).getValue());
                    ll.addView(radioButton);
                } else {
                    try {
                        rGroupTrips.removeViewAt(i);
                    }catch (Exception e)
                    {

                    }

                }


                radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
                radioButton.setTextColor(getResources().getColor(R.color.color_font_black));
                radioButton.setTypeface(null, Typeface.NORMAL);
                radioButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //radioButton.setChecked(true);

                        //remove all error msgs
                        lytErrorMessage.removeAllViews();
                        lytError.setVisibility(View.GONE);

                        rGroupTrips.clearCheck();

                        Object tagRadio = v.getTag();
                        if (tagRadio.equals("1")) {

                           /* LinearLayout lay1 = (LinearLayout) rGroupTrips.getChildAt(0);
                            OTRadioButton rbut = (OTRadioButton) lay1.getChildAt(0);
                            rbut.setChecked(true);
                            LinearLayout lay2 = (LinearLayout) rGroupTrips.getChildAt(1);
                            OTRadioButton rbut2 = (OTRadioButton) lay2.getChildAt(0);
                            rbut2.setChecked(false);*/
                            txtFlight1.setVisibility(View.GONE);
                            txtFlight2.setVisibility(View.GONE);

                            lytFromRepeat.setVisibility(View.GONE);
                            lytToRepeat.setVisibility(View.GONE);
                            lytReturn.setVisibility(View.GONE);
                            lytReturnFlexibility.setVisibility(View.GONE);
                            tripType = 1;
                            setRadioButtonForTrip(tripType);
                            //setRadioButtonClickEvent(tripType);


                        } else if (tagRadio.equals("2")) {

                           /* LinearLayout lay1 = (LinearLayout) rGroupTrips.getChildAt(0);
                            OTRadioButton rbut = (OTRadioButton) lay1.getChildAt(0);
                            rbut.setChecked(false);
                            LinearLayout lay2 = (LinearLayout) rGroupTrips.getChildAt(1);
                            OTRadioButton rbut2 = (OTRadioButton) lay2.getChildAt(0);
                            rbut2.setChecked(true);*/
                            txtFlight1.setVisibility(View.GONE);
                            txtFlight2.setVisibility(View.GONE);

                            lytFromRepeat.setVisibility(View.GONE);
                            lytToRepeat.setVisibility(View.GONE);

                            lytReturn.setVisibility(View.VISIBLE);
                            if (selectedPassDataForSearchFlight.getTravelFlexibilityFlag() == 1) {
                                lytReturnFlexibility.setVisibility(View.VISIBLE);
                            }
                            tripType = 2;
                            //setRadioButtonClickEvent(tripType);
                            setRadioButtonForTrip(tripType);

                        } else if (tagRadio.equals("4")) {

                            lytReturn.setVisibility(View.VISIBLE);
                            lytFromRepeat.setVisibility(View.VISIBLE);
                            lytToRepeat.setVisibility(View.VISIBLE);

                            txtFlight1.setVisibility(View.VISIBLE);
                            txtFlight2.setVisibility(View.VISIBLE);

                            tripType = 4;
                            //setRadioButtonClickEvent(tripType);
                            setRadioButtonForTrip(tripType);

                        }

                    }

                });


            }

            previosTripType = tripType;
            setRadioButtonClickEvent(tripType);


        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initializeCalendar(final ArrayList disabledDates,final OTTextView txtDepart, final OTTextView otherTextView) {

        final String[] getDate = {null};
        final CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();

        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        Utils.updateActionBarForFeatures(this.getActivity(), "CalenderFromRedeem : ");


        Date beginDate = null;
        Date startDate = null;
        Date endDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Utils.getLocalForCommunication());
        try {
            beginDate = sdf.parse(selectedPassDataForSearchFlight.getTravel_Period_VAlidity_Begin_Date().toString());
            endDate = sdf.parse(selectedPassDataForSearchFlight.getTravel_Period_VAlidity_End_Date().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        startDate = isStartDateAfterBeginDate(Integer.parseInt(selectedPassDataForSearchFlight.getPass_Review_Image_Value().toString()), beginDate);
        System.out.println("Dates >>>>>>>>>>>>>>>> Start Date :  " + startDate + "   End date : " + endDate);

        //open calender view from start date
        int month = 0;
        int year = 0;
        if(txtDepart.getId() == R.id.txtReturn)
        {
            //if depart date already selected then range starts from depart date
            caldroidFragment.setMinDate(departDate);
            month = departDate.getMonth();
            year = departDate.getYear();
            args.putInt(CaldroidFragment.MONTH, month + 1);
            args.putInt(CaldroidFragment.YEAR, year);
        }else {
            caldroidFragment.setMinDate(startDate);
            month = startDate.getMonth();
            year = startDate.getYear();
            args.putInt(CaldroidFragment.MONTH, month + 1);
            args.putInt(CaldroidFragment.YEAR, year);
        }

        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        caldroidFragment.setMaxDate(endDate);

        //Enabling blacklisted dates
        if(!disabledDates.isEmpty())
        {
            try {
                caldroidFragment.setDisableDates(disabledDates);
                caldroidFragment.refreshView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

                if(txtDepart.getId() == R.id.txtDepart)
                {
                    departDate = date;
                }

                System.out.println("class name >> " +  new SearchFlightInputFragment().getClass().getName());
                Utils.updateActionBarForFeatures(getActivity(),new SearchFlightInputFragment().getClass().getName());
                android.support.v4.app.FragmentManager manager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                manager.popBackStack();
                txtDepart.setText(Utils.convertDateToFormat_ddMMyyyy(date));

            }
        });

        android.support.v4.app.FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.add(R.id.lytMain, caldroidFragment, caldroidFragment.getClass().getName());
        t.addToBackStack(caldroidFragment.getClass().getName());
        t.commit();

        new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ViewGroup.LayoutParams layoutParams = caldroidFragment.getView().getLayoutParams();
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                caldroidFragment.getView().setLayoutParams(layoutParams);
            }
        }.sendEmptyMessage(0);

    }

    /**
     *
     * @param i
     * @param beginDate
     * @return
     */
    private Date isStartDateAfterBeginDate(int i, Date beginDate) {

        Calendar c = Calendar.getInstance(Utils.getLocalForCommunication());
        c.add(Calendar.DATE, i);  //number of days added (i)
        System.out.println("added time => " + c.getTime());
        Date startDate = c.getTime();   //current date + number of days added

        if(startDate.after(beginDate))
        {
            return startDate;
        }else {
            return beginDate;
        }

    }

    public Date getDate(String strDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(strDate);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.setTime(date);

        return calendar.getTime();
    }

    public void createDropdownView(final String[] aryDropdownOption, final LinearLayout lytParent, final OTTextView selectedView, LinearLayout aryOtherdropdown[]) {

        System.out.println("list length >>>>>>>>>>>>>" + aryDropdownOption.length);
        hideKeyboard(lytParent.getContext(), lytParent.getRootView());

        if (lytParent.getChildCount() == 0) {
            //first dismiss rest dropdowns
            for (int index = 0; index < aryOtherdropdown.length; index++) {
                if (aryOtherdropdown[index] != null) {
                    ((LinearLayout) aryOtherdropdown[index]).removeAllViews();
                    ((View) ((LinearLayout) aryOtherdropdown[index]).getParent()).setVisibility(View.GONE);
                }
            }

            for (int index = 0; index < aryDropdownOption.length; index++) {
                OTTextView textView = new OTTextView(lytParent.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setPadding((int) Utils.conertDpToPixel(lytParent.getContext(), 10), (int) Utils.conertDpToPixel(lytParent.getContext(), 10), 0, (int) Utils.conertDpToPixel(lytParent.getContext(), 10));
                textView.setText(aryDropdownOption[index]);
                textView.setTextSize(Utils.convertPixelToDp(lytParent.getContext(), lytParent.getContext().getResources().getDimension(R.dimen.size_font_13)));
                textView.setTextColor(lytParent.getContext().getResources().getColor(R.color.color_font_black));
                textView.setTypeface(null, Typeface.NORMAL);

                final int finalIndex = index;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedView.setText(aryDropdownOption[finalIndex]);

                        if (selectedView.getId() == R.id.txtFrom) {

                            txtTo.setText(selectedPassDataForSearchFlight.getToLabel());
                            txtFromRepeat.setText(selectedPassDataForSearchFlight.getFromLabel());
                            txtToRepeat.setText(selectedPassDataForSearchFlight.getToLabel());
                            txtDepart.setText(selectedPassDataForSearchFlight.getDepartAirportLabel());
                            txtTravelFlexibility.setText(selectedPassDataForSearchFlight.getDepartAirportLabel());
                            txtReturn.setText(selectedPassDataForSearchFlight.getReturnLabel());
                            txtReturnFlexibility.setText(selectedPassDataForSearchFlight.getReturnLabel());
                            txtpassengerlabel.setText(selectedPassDataForSearchFlight.getPaxLabel());
                            txtPassenger.setText(selectedPassDataForSearchFlight.getPaxLabel());


                            if (txtTo.getText().toString().equals(aryDropdownOption[finalIndex])) {
                                txtTo.setText(selectedPassDataForSearchFlight.getToLabel());
                                //Utils.showToast(getActivity(), "Select Arrival Airport");
                            }
                        }

                        if (selectedView.getId() == R.id.txtToRepeat) {
                            if (txtTo.getText().toString().equals(aryDropdownOption[finalIndex])) {
                                txtTo.setText(selectedPassDataForSearchFlight.getToLabel());
                                //Utils.showToast(getActivity(), "Select Arrival Airport");
                            }
                        }

                        try {


                            /*
                            * store value Id in global var for all depart and arrival airport
                            * */

                            if (selectedView.getId() == R.id.txtFrom || selectedView.getId() == R.id.txtTo) {
                                for (int i = 0; i < selectedPassDataForSearchFlight.getDepartAirportList().size(); i++) {

                                    /*
                                    * replace all "white space " from string
                                    * if "white space " is present replace it with ""
                                    *
                                    * */

                                    String selected_trim_space = aryDropdownOption[finalIndex].replaceAll("\\s+","").trim();
                                    String json_data_trimmed = selectedPassDataForSearchFlight.getDepartAirportList().get(i).getLabel().trim().replaceAll("\\s+","").trim();


                                    if (selected_trim_space.equals(json_data_trimmed)) {

                                        if (selectedView.getId() == R.id.txtFrom) {
                                            departAirportValueId = selectedPassDataForSearchFlight.getDepartAirportList().get(i).getValue().toString();

                                        } else if (selectedView.getId() == R.id.txtTo) {
                                            arriveAirportValueId = selectedPassDataForSearchFlight.getDepartAirportList().get(i).getValue().toString();

                                            if (tripType == 4) {
                                                callApiUpadteForFlight2(departAirportValueId, arriveAirportValueId);

                                            }

                                        }
                                    }

                                    /*if (aryDropdownOption[finalIndex].trim().equals(selectedPassDataForSearchFlight.getDepartAirportList().get(i).getLabel().trim())) {

                                        if (selectedView.getId() == R.id.txtFrom) {
                                            departAirportValueId = selectedPassDataForSearchFlight.getDepartAirportList().get(i).getValue().toString();

                                        } else if (selectedView.getId() == R.id.txtTo) {
                                            arriveAirportValueId = selectedPassDataForSearchFlight.getDepartAirportList().get(i).getValue().toString();

                                            if (tripType == 4) {
                                                callApiUpadteForFlight2(departAirportValueId, arriveAirportValueId);

                                            }

                                        }
                                    }*/
                                }
                            } else if (selectedView.getId() == R.id.txtFromRepeat) {
                                for (int i = 0; i < selectedDataForFlight2.getDepartAirport().size(); i++) {

                                    String selected_trim_space = aryDropdownOption[finalIndex].replaceAll("\\s+","").trim();
                                    String json_data_trimmed = selectedDataForFlight2.getDepartAirport().get(i).getDeptLabel().trim().replaceAll("\\s+","").trim();


                                    if (selected_trim_space.equals(json_data_trimmed)) {
                                        departAirportValueIdFlight2 = selectedDataForFlight2.getDepartAirport().get(i).getDeptValue().toString();
                                    }

                                    /*if (aryDropdownOption[finalIndex].equals(selectedDataForFlight2.getDepartAirport().get(i).getDeptLabel())) {
                                        departAirportValueIdFlight2 = selectedDataForFlight2.getDepartAirport().get(i).getDeptValue().toString();
                                    }*/

                                }

                            } else if (selectedView.getId() == R.id.txtToRepeat) {
                                for (int i = 0; i < selectedDataForFlight2.getArriveAirport().size(); i++) {

                                    String selected_trim_space = aryDropdownOption[finalIndex].replaceAll("\\s+","").trim();
                                    String json_data_trimmed = selectedDataForFlight2.getArriveAirport().get(i).getDeptLabel().trim().replaceAll("\\s+","").trim();

                                    if (selected_trim_space.equals(json_data_trimmed)) {
                                        arriveAirportValueIdFlight2 = selectedDataForFlight2.getArriveAirport().get(i).getDeptValue().toString();
                                    }

                                    /*if (aryDropdownOption[finalIndex].equals(selectedDataForFlight2.getArriveAirport().get(i).getDeptLabel())) {
                                        arriveAirportValueIdFlight2 = selectedDataForFlight2.getArriveAirport().get(i).getDeptValue().toString();
                                    }*/
                                }
                            }


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (selectedView.getId() == R.id.txtTo){
                                        if(arriveAirportValueId != null) {
                                            callApiDisableBlacklistedDates(departAirportValueId, arriveAirportValueId);
                                        }
                                    }

                                    if (selectedView.getId() == R.id.txtToRepeat){
                                        if(arriveAirportValueIdFlight2 != null) {
                                            callApiDisableBlacklistedDates(departAirportValueIdFlight2, arriveAirportValueIdFlight2);
                                        }
                                    }


                                }
                            },200);



                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ((View) (lytParent).getParent()).setVisibility(View.GONE);
                    }
                });

                lytParent.addView(textView, params);
            }
            ((View) (lytParent).getParent()).setVisibility(View.VISIBLE);
        } else {
            ((View) (lytParent).getParent()).setVisibility(((View) (lytParent).getParent()).getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }

        Utils.calculateHeight(aryDropdownOption, lytParent);



    }

    public void createDropdownViewForPass(final String[] aryDropdownOption, final LinearLayout lytParent, final OTTextView selectedView, LinearLayout aryOtherdropdown[]) {


        hideKeyboard(lytParent.getContext(), lytParent.getRootView());
        if (lytParent.getChildCount() == 0) {
            //first dismiss rest dropdowns
            for (int index = 0; index < aryOtherdropdown.length; index++) {
                if (aryOtherdropdown[index] != null) {
                    ((LinearLayout) aryOtherdropdown[index]).removeAllViews();
                    ((View) ((LinearLayout) aryOtherdropdown[index]).getParent()).setVisibility(View.GONE);
                }
            }

            for (int index = 0; index < aryDropdownOption.length; index++) {
                OTTextView textView = new OTTextView(lytParent.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins((int) Utils.conertDpToPixel(lytParent.getContext(), 10), (int) Utils.conertDpToPixel(lytParent.getContext(), 10), 0, (int) Utils.conertDpToPixel(lytParent.getContext(), 10));
                textView.setText(aryDropdownOption[index]);
                textView.setTextSize(Utils.convertPixelToDp(lytParent.getContext(), lytParent.getContext().getResources().getDimension(R.dimen.size_font_13)));
                textView.setTextColor(lytParent.getContext().getResources().getColor(R.color.color_font_black));
                textView.setTypeface(null, Typeface.NORMAL);

                final int finalIndex = index;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedView.setText(aryDropdownOption[finalIndex]);
                        final String selectedPassId = aryDropdownOption[finalIndex];

                       // ((View) (lytParent).getParent()).setVisibility(View.GONE);

                        for (int i = 0; i < selectedPassDataForSearchFlight.getPassListDropDown().size(); i++) {
                            if (selectedPassDataForSearchFlight.getPassListDropDown().get(i).getLabel().equals(selectedPassId)) {
                                passValue = selectedPassDataForSearchFlight.getPassListDropDown().get(i).getValue();
                            }
                        }

                        Handler mainHandler = new Handler(getActivity().getMainLooper());
                        Runnable myRunnable = new Runnable() {
                            @Override
                            public void run() {
                                selectedId = Integer.parseInt(passValue);
                                Utils.isFlightPassChanged = true;
                                //callApiUpadteUI(selectedId);
                                if(passValue.equals("0"))
                                {
                                    //Utils.showToast(getActivity(), selectedPassDataForSearchFlight.getPassListDropDown().get(0).getLabel());
                                }
                                else
                                {
                                    tripType = 1;
                                    callApiUpadteUI(selectedId);
                                }
                            } // This is your code
                        };
                        mainHandler.post(myRunnable);
                        ((View) (lytParent).getParent()).setVisibility(View.GONE);

                    }
                });

                lytParent.addView(textView, params);
            }
            ((View) (lytParent).getParent()).setVisibility(View.VISIBLE);
        } else {
            ((View) (lytParent).getParent()).setVisibility(((View) (lytParent).getParent()).getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }

        Utils.calculateHeight(aryDropdownOption, lytParent);
    }

    public void hideKeyboard(Context context, View view) {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public String[] dropDownListForAirport(String[] toListDropDown, OTTextView clickedTxtView, OTTextView otherTxtView) {

        ArrayList airportList = new ArrayList();

        if (clickedTxtView.getId() == R.id.txtTo) {
            clickedTxtView.setText(selectedPassDataForSearchFlight.getToLabel());
        }



        if (clickedTxtView.getText().toString().equals(selectedPassDataForSearchFlight.getFromLabel())) {
            for (int i = 0; i < selectedPassDataForSearchFlight.getDepartAirportList().size(); i++) {
                airportList.add(selectedPassDataForSearchFlight.getDepartAirportList().get(i).getLabel());
            }
        } else if (clickedTxtView.getText().toString().equals(selectedPassDataForSearchFlight.getToLabel())) {
            if (otherTxtView.getText().toString().equals(selectedPassDataForSearchFlight.getFromLabel())) {
                return null;
            } else {
                String localStringAirportCheck = null;

                for (int i = 0; i < selectedPassDataForSearchFlight.getDepartAirportList().size(); i++) {
                    if (selectedPassDataForSearchFlight.getDepartAirportList().get(i).getLabel().equals(otherTxtView.getText().toString())) {
                        for (int j = 0; j < selectedPassDataForSearchFlight.getArriveDepart().size(); j++) {
                            if (selectedPassDataForSearchFlight.getArriveDepart().get(j).getDepartCityName().equals(otherTxtView.getText().toString())) {

                                if (airportList.contains(selectedPassDataForSearchFlight.getArriveDepart().get(j).getArriveCityName())) {

                                } else {
                                    localStringAirportCheck = selectedPassDataForSearchFlight.getArriveDepart().get(j).getArriveCityName();
                                    airportList.add(selectedPassDataForSearchFlight.getArriveDepart().get(j).getArriveCityName());
                                }
                            } else if (selectedPassDataForSearchFlight.getArriveDepart().get(j).getArriveCityName().equals(otherTxtView.getText().toString())) {
                                if (airportList.contains(selectedPassDataForSearchFlight.getArriveDepart().get(j).getDepartCityName())) {

                                } else {
                                    localStringAirportCheck = selectedPassDataForSearchFlight.getArriveDepart().get(j).getArriveCityName();
                                    airportList.add(selectedPassDataForSearchFlight.getArriveDepart().get(j).getDepartCityName());
                                }
                            }
                        }

                        break;
                    }
                }
            }
        } else {

            if (clickedTxtView.getId() == R.id.txtFrom) {
                for (int i = 0; i < selectedPassDataForSearchFlight.getDepartAirportList().size(); i++) {
                    airportList.add(selectedPassDataForSearchFlight.getDepartAirportList().get(i).getLabel());
                }

                //otherTxtView.setText(selectedPassDataForSearchFlight.getToLabel());
            }
                /*else
                {
                    for (int i = 0; i < selectedPassDataForSearchFlight.getDepartAirportList().size(); i++)
                    {
                        if (selectedPassDataForSearchFlight.getDepartAirportList().get(i).getLabel().equals(otherTxtView.getText().toString())) {
                            for (int j = 0; j < selectedPassDataForSearchFlight.getArriveDepart().size(); j++) {
                                if (selectedPassDataForSearchFlight.getArriveDepart().get(j).getDepartCityName().equals(otherTxtView.getText().toString())) {
                                    airportList.add(selectedPassDataForSearchFlight.getArriveDepart().get(j).getArriveCityName());
                                } else if (selectedPassDataForSearchFlight.getArriveDepart().get(j).getArriveCityName().equals(otherTxtView.getText().toString())) {
                                    airportList.add(selectedPassDataForSearchFlight.getArriveDepart().get(j).getDepartCityName());
                                }
                            }
                        }
                    }
                }*/

        }


        toListDropDown = new String[airportList.size()];
        toListDropDown = (String[]) airportList.toArray(toListDropDown);
        for (String s : toListDropDown)
            System.out.println("ListDropDown data >>>>>>>>>>>>>>" + s);

        return toListDropDown;
    }

    public void callApiUpadteForFlight2(String departDateJourney1, String arriveDateJourney1) {

        System.out.println("departAirportValueId id>>>>>>>>>>>>>>>" + departDateJourney1);
        System.out.println("arriveAirportValueId id>>>>>>>>>>>>>>>" + arriveDateJourney1);
        String tag_json_obj = "json_obj_req";


        //String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_Flight_2_List);

        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_Flight_2_List)
                + "&arriveAirportCodeF1=" + arriveDateJourney1 + "&departAirportCodeF1=" + departDateJourney1;


        final JSONObject requestObject = new JSONObject();

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Utils.DEBUG("onResponse() flight 2 open jaw data called : >>>>>>>>>>>>>" + response.toString());
                        selectedDataForFlight2 = ParseManager.getInstance().fromJSON(response, SelectedDataForFlight2.class);

                       /* getUIReference();
                        updateUI();*/


                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    public void updateLayout(FragmentCommunicationData message) {

        Utils.DEBUG("SearchFlightInputFragment >> updateLayout() called.");
        if(message.getRedeemModifyPassDetails()== true)
        {
            lytSelectFligthPass = (LinearLayout)view.findViewById(R.id.lytSelectFligthPass);
            lytSelectFligthPass.setVisibility(View.GONE);
            Utils.updateActionBarForFeatures(getActivity(), new SearchFlightInputFragment().getClass().getName(), "Modify Search", null);

            //lytAppBottomBar.setVisibility(View.GONE);
            bottom_bar_layout.setVisibility(View.GONE);
            lytCloseDone.setVisibility(View.VISIBLE);
            txtSearch.setVisibility(View.GONE);
        }
        else
        {
            lytSelectFligthPass = (LinearLayout)view.findViewById(R.id.lytSelectFligthPass);
            lytSelectFligthPass.setVisibility(View.VISIBLE);
        }

    }

    public void callApiDisableBlacklistedDates(final String depAirportId, final String arrAirportId ) {

        System.out.println("selected id>>>>>>>>>>>>>>>" + depAirportId);
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_getBlackListDate);


        final JSONObject requestObject = new JSONObject();

        try {
            requestObject.put("depAirportId", depAirportId);
            requestObject.put("arrAirportId", arrAirportId);
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null)
                        {
                            return;
                        }
                        Utils.DEBUG("blacklist dates onResponse() called : " + response.toString());
                        blackListData = ParseManager.getInstance().fromJSON(response, BlackListDates.class);



                        if(!disabledDatesDepart.isEmpty())
                        {
                            if(disabledDatesReturn.isEmpty())
                            {
                                disabledDatesReturn = generateBlacklistDatesArray(blackListData);
                            }
                        }else {

                            disabledDatesDepart = generateBlacklistDatesArray(blackListData);
                            callApiDisableBlacklistedDates(arrAirportId, depAirportId);

                        }

                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private ArrayList generateBlacklistDatesArray(BlackListDates blackListData) {

        ArrayList<Date> blacklistDatesArray = new ArrayList<>();
        Date date = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        try {
            for (String retval : blackListData.getBlackListDays().split("#")) {
                System.out.println(retval);
                try {
                    date = sdf.parse(retval);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                blacklistDatesArray.add(date);
            }
        }catch (Exception e) {
        }

        return blacklistDatesArray;
    }
}

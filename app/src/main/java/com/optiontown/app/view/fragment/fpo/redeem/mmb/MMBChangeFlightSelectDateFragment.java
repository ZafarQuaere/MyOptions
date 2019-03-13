package com.optiontown.app.view.fragment.fpo.redeem.mmb;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.redeem.FlightSmallView;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.Segment;
import com.optiontown.app.model.redeem.mmb.MMBChangeFlightCurrentData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyClickableSpan;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zafar.imam on 22-11-2016.
 */

public class MMBChangeFlightSelectDateFragment extends BaseFragment {
    private View view;
    private AppDialogLoader loader;
    private OTTextView txtAirlineLabel;
    private OTTextView txtAirline;
    private OTTextView txtSelectedPass;
    private OTTextView txtPassengerLabel;
    private TextView txtPassenger;
    private OTTextView txtAirlineBookingRefLabel;
    private OTTextView txtAirlineBookingNo;
    private OTTextView txtSelectFlightLabel;
    private OTTextView txtNewDateLabel;
    private OTTextView txtSelectedNewDate;
    private OTTextView txtAdvanceBookingLabel;
    private OTTextView txtContinue;
    private OTTextView txtSelectDesireDateLabel;
    private OTTextView txtAdvanceBookingDescription;
    private LinearLayout lytDate;
    private RelativeLayout rlytBody;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String monthName;
    private int monthSelected;
    private int yearSelected;
    private int daySelected;
    private MMBChangeFlightCurrentData mMBChangeFlightCurrentData;
    private LinearLayout lytItinerary;
    private String strSelectedIFSIndex;
    private String strDateDDMMYYYY = "";
    private ArrayList<String> passengerlist;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytError;
    private ImageView imgEdit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmb_change_flight_select_date, container, false);
        strSelectedIFSIndex = (String) getArguments().getSerializable(getString(R.string.key_serializable));

        loader = AppDialogLoader.getLoader(getActivity());
        initUi(view);
        loader.show();
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(),"Change Flight",null);
        callApi();


        return view;
    }

    private void initUi(View view) {
        rlytBody = (RelativeLayout) view.findViewById(R.id.rlytBody);
        rlytBody.setVisibility(View.GONE);
        lytItinerary = (LinearLayout) view.findViewById(R.id.lytItinerary);
        txtAirlineLabel = (OTTextView) view.findViewById(R.id.txtAirlineLabel);
        txtAirline = (OTTextView) view.findViewById(R.id.txtAirline);

        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);
        txtSelectedPass.setText(Utils.getSelectedPass(getActivity()));

        txtPassengerLabel = (OTTextView) view.findViewById(R.id.txtPassengerLabel);
        txtPassenger = (TextView) view.findViewById(R.id.txtPassenger);
        txtAirlineBookingRefLabel = (OTTextView) view.findViewById(R.id.txtAirlineBookingRefLabel);
        txtAirlineBookingNo = (OTTextView) view.findViewById(R.id.txtAirlineBookingNo);
        txtSelectFlightLabel = (OTTextView) view.findViewById(R.id.txtSelectFlightLabel);
        txtSelectDesireDateLabel = (OTTextView) view.findViewById(R.id.txtSelectDesireDateLabel);
        txtNewDateLabel = (OTTextView) view.findViewById(R.id.txtNewDateLabel);
        txtSelectedNewDate = (OTTextView) view.findViewById(R.id.txtSelectedNewDate);
        txtAdvanceBookingLabel = (OTTextView) view.findViewById(R.id.txtAdvanceBookingLabel);
        txtAdvanceBookingDescription = (OTTextView) view.findViewById(R.id.txtAdvanceBookingDescription);

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

        lytDate = (LinearLayout) view.findViewById(R.id.lytDate);
        lytDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
                lytError.setVisibility(View.GONE);
            }
        });
        txtContinue = (OTTextView) view.findViewById(R.id.txtContinue);
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                ArrayList<String> listError = validateAllInputsCommon();
                if (listError.size() > 0) {
                    lytError.setVisibility(View.VISIBLE);
                    //show error message
                    for (int index = 0; index < listError.size(); index++) {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }
                }
                else
                {
                    Utils.moveToFragment(getActivity(),new MMBChangeFlightNewDateFragment(),strDateDDMMYYYY,0);
                }

            }
        });
        imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.clearBackstackTillMMBSelectPassFragment(getActivity());
            }
        });

    }

    public void showHighVolumeErrorMessage(FragmentCommunicationData message)
    {
        Utils.DEBUG(this.getClass().getSimpleName() + " >> showHighVolumeErrorMessage called : " + message.getErrorMessage());
        lytError.setVisibility(View.VISIBLE);
        //show error message
        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), message.getErrorMessage()));
    }



    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();
        try {
            if (strDateDDMMYYYY.equals("")) {
                listError.add(mMBChangeFlightCurrentData.getError_Message());
            }
        } catch (Exception e) {
            Utils.ERROR("" + e.toString());
        }
        return listError;
    }
    private void datePicker() {
        // Process to get Current Date
        final Calendar c = Calendar.getInstance(Locale.getDefault());
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {
                        // Display Selected date in textbox
                        monthSelected = monthOfYear;
                        yearSelected = year;
                        daySelected = dayOfMonth;

                        //String dob = checkDigit(dayOfMonth) + "-" + checkDigit(monthOfYear + 1) + "-" + year;
                        String dob = (dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year;
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String dateInString = dob;
                        Date date = null;
                        try {
                            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy", Utils.getLocalForCommunication());
                            date = formatter.parse(dateInString);
                            System.out.println(date);
                            System.out.println(formatter.format(date));
                            strDateDDMMYYYY = formatter2.format(date);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String monthname = (String) android.text.format.DateFormat.format("MMMM", date);
                        monthName = (String) android.text.format.DateFormat.format("MMM", date);
                        txtSelectedNewDate.setText(dayOfMonth+ "  " + monthName + "  " + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000+getDaysBefore(mMBChangeFlightCurrentData.getBooking_Days_Value()));
        dpd.getDatePicker().setMaxDate(
                System.currentTimeMillis() - 1000 + getDaysBefore(mMBChangeFlightCurrentData.getBooking_Days_Value())
                + getDaysBefore(mMBChangeFlightCurrentData.getMax_Range_Days()));
    }

    public long getDaysBefore(long days) {
        days = (1000*60*60*24*days);
        return days;
    }



    private void callApi() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_ChangeFlightCurrent)
                + strSelectedIFSIndex;

        JSONObject requestObject = new JSONObject();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("ManageMyBookingResponse : " + response.toString());
                        mMBChangeFlightCurrentData = ParseManager.getInstance().fromJSON(response, MMBChangeFlightCurrentData.class);
                        updateUi();
                        loader.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loader.dismiss();
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));

            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void updateUi()
    {
        rlytBody.setVisibility(View.VISIBLE);
     /*   String heading = mMBChangeFlightCurrentData.getChange_Flight_Heading_Label();
        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>"+heading);*/
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(),mMBChangeFlightCurrentData.getChange_Flight_Heading_Label(),null);
        txtAdvanceBookingLabel.setText(mMBChangeFlightCurrentData.getAdvance_Booking_Label());
        txtAdvanceBookingDescription.setText(mMBChangeFlightCurrentData.getAdvance_Booking_MMB_Desc_Label());
        txtSelectFlightLabel.setText(mMBChangeFlightCurrentData.getCurrent_Flight_Label());
        txtContinue.setText(mMBChangeFlightCurrentData.getContinue_Button_Label());
        txtSelectDesireDateLabel.setText(mMBChangeFlightCurrentData.getNew_Dept_Date_Label());
        txtNewDateLabel.setText(mMBChangeFlightCurrentData.getNew_Depart_Date_Label());

        txtAirlineLabel.setText(mMBChangeFlightCurrentData.getLABL_Airline_Label()+" : ");
        txtAirline.setText(mMBChangeFlightCurrentData.getAirline_Name_Label());
        txtPassengerLabel.setText(mMBChangeFlightCurrentData.getPassenger_Label()+" : ");
        txtAirlineBookingRefLabel.setText(mMBChangeFlightCurrentData.getBooking_Ref_Label()+" : ");
        txtAirlineBookingNo.setText(mMBChangeFlightCurrentData.getBooking_Ref_Pnr());

        String pass = "";
        passengerlist = mMBChangeFlightCurrentData.getPassengers_List();
        String dialogBody = "";
        for (int i =0; i<passengerlist.size();i++){

            if (passengerlist.size()>1){

                pass = passengerlist.get(i)+"\n"+ getString(R.string.string_more)+"..";
                dialogBody = dialogBody+ passengerlist.get(i)+"\n";
                String passenger = pass+ passengerlist.get(i)+"\n";
                passenger = passenger.substring(0, passenger.length() > 30 ? 20 : passenger.length()) + ".."+ getString(R.string.string_more);
                SpannableString ss = new SpannableString(passenger);
                String readMore = getString(R.string.string_more);
                int readMoreIndex = pass.toString().indexOf(readMore);

                ss.setSpan(new MyClickableSpan(getActivity(),1,dialogBody,"Passengers"),readMoreIndex, readMoreIndex+readMore.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                txtPassenger.setMovementMethod(LinkMovementMethod.getInstance());
                txtPassenger.setText(ss);
            }
            else {
                pass = pass + passengerlist.get(i);
                txtPassenger.setText(pass);
            }

            pass = passengerlist.get(i);

            System.out.println("Server Passengers: "+pass);
        }
      //  txtPassenger.setText(mMBChangeFlightCurrentData.getPassenger_Label());

        showItinerary();
    }

    private void showItinerary()
    {
        if(mMBChangeFlightCurrentData.getItinerarry() == null)
        {
            return;
        }

        for (int pos = 0; pos < mMBChangeFlightCurrentData.getItinerarry().size(); pos++)//size 1
        {
            Itinerarry itinerarry = mMBChangeFlightCurrentData.getItinerarry().get(pos);
            for (int index = 0; index < itinerarry.getSegments().size(); index++)//size max 1
            {
                View viewParent = LayoutInflater.from(getActivity()).inflate(R.layout.redeem_search_result_row, null, false);
                OTTextView txtJourneyType = (OTTextView) viewParent.findViewById(R.id.txtJourneyType);
                OTTextView txtAirlineName = (OTTextView) viewParent.findViewById(R.id.txtAirlineName);
                NetworkImageView imgAirline = (NetworkImageView) viewParent.findViewById(R.id.imgAirline);
                View viewMargin = (View) viewParent.findViewById(R.id.viewMargin);
                OTTextView txtOperation = (OTTextView) viewParent.findViewById(R.id.txtOperation);
                OTTextView txtSelect = (OTTextView) viewParent.findViewById(R.id.txtSelect);
                ImageView imgSelected = (ImageView) viewParent.findViewById(R.id.imgSelected);
                LinearLayout lytSelect = (LinearLayout) viewParent.findViewById(R.id.lytSelect);
                LinearLayout lytSegments = (LinearLayout) viewParent.findViewById(R.id.lytSegments);

                View viewChild = LayoutInflater.from(getActivity()).inflate(R.layout.segment_select_row, null, false);

                Segment segment = itinerarry.getSegments().get(index);

                OTTextView txtDepartTimeOne = (OTTextView) viewChild.findViewById(R.id.txtDepartTimeOne);
                OTTextView txtDepartAirportOne = (OTTextView)  viewChild.findViewById(R.id.txtDepartAirportOne);
                OTTextView txtArriveTimeOne = (OTTextView)  viewChild.findViewById(R.id.txtArriveTimeOne);
                OTTextView txtArriveAirportOne = (OTTextView)  viewChild.findViewById(R.id.txtArriveAirportOne);
                OTTextView txtTravelTimeOne = (OTTextView)  viewChild.findViewById(R.id.txtTravelTimeOne);

                ImageView imgArrowLeftOne = (ImageView) viewChild.findViewById(R.id.imgArrowLeftOne);
                OTTextView txtConnectCount = (OTTextView)  viewChild.findViewById(R.id.txtConnectCount);
                ImageView imgArrowRightOne = (ImageView) viewChild.findViewById(R.id.imgArrowRightOne);

                FlightSmallView flightSmallViewFirst = segment.getLegList().get(0).getFlightSmallView();
                FlightSmallView flightSmallViewLast = segment.getLegList().get(segment.getLegList().size() - 1).getFlightSmallView();
                txtDepartTimeOne.setText(flightSmallViewFirst.getDepartAirlineTime());
                txtArriveTimeOne.setText(flightSmallViewLast.getArrivalAirlineTime());
                txtDepartAirportOne.setText(flightSmallViewFirst.getDepartAirlineCode());
                txtArriveAirportOne.setText(flightSmallViewLast.getArrivalAirlineCode());
                txtTravelTimeOne.setText(flightSmallViewFirst.getFlightDuration());

                if(segment.getLegList().size() > 1)
                {
                    txtConnectCount.setText(Integer.toString(segment.getLegList().size() - 1));
                }
                else
                {
                    imgArrowLeftOne.setImageResource(R.drawable.arrow_connecting);
                    txtConnectCount.setVisibility(View.GONE);
                    imgArrowRightOne.setVisibility(View.GONE);
                }

                lytSegments.addView(viewChild);


                //
                txtJourneyType.setVisibility(View.VISIBLE);
                txtJourneyType.setText(index == 0 ? "Outbound" : "Return");
                txtOperation.setText(itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getFooterLabel());
                txtOperation.setVisibility(itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getFooterLabel().equals("") ? View.GONE : View.VISIBLE);
                String url = itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getAirlineLogo();
                imgAirline.setImageUrl(url, AppController.getInstance().getImageLoader());
                txtAirlineName.setText(itinerarry.getSegments().get(0).getLegList().get(0).getFlightSmallView().getAirlineDisName());
                lytSelect.setVisibility(View.GONE);

                viewMargin.setVisibility(View.GONE);

                lytItinerary.addView(viewParent);
            }
        }
    }
}

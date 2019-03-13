package com.optiontown.app.view.fragment.fpo.redeem.mybookings;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.redeem.mybooking.ConfirmedBooking;
import com.optiontown.app.model.redeem.mybooking.Labels;
import com.optiontown.app.model.redeem.mybooking.MyBookings;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.faq.FAQFragment;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by zafar.imam on 19-10-2016.
 */

public class MyBookingsFragment extends BaseFragment{
    private View view;
    private OTTextView txtCurrentBookingLabel, txtPendingBookingLabel;
    private ImageView imgPBExpand, imgCBExpand;

    private final int TYPE_CONFIRM = 0;
    private final int TYPE_PENDING = 1;

    private LinearLayout lytCurrentBooking;
    private LinearLayout lytPendingBooking;
    private ScrollView scrollView;
    private boolean isBottomBarFromMmp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_bookings, container, false);

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        Utils.updateBottomBarFpoRedeemForFeatures(view, this.getClass().getName(), false);

        callApi();
        getViewReference(view);


        return view;
    }



    private void getViewReference(View view) {

        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        scrollView.setVisibility(View.GONE);
        txtCurrentBookingLabel = (OTTextView) view.findViewById(R.id.txtCurrentBookingLabel);
        txtPendingBookingLabel = (OTTextView) view.findViewById(R.id.txtPendingBookingLabel);

        lytCurrentBooking = (LinearLayout) view.findViewById(R.id.lytCurrentBooking);
        lytPendingBooking = (LinearLayout) view.findViewById(R.id.lytPendingBooking);

        imgPBExpand = (ImageView) view.findViewById(R.id.imgPBExpand);
        imgCBExpand = (ImageView) view.findViewById(R.id.imgCBExpand);


        imgCBExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytCurrentBooking.setVisibility(lytCurrentBooking.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                imgCBExpand.setImageResource(lytCurrentBooking.getVisibility() == View.VISIBLE ? R.drawable.minus_icon : R.drawable.plus_icon);

            }
        });

        imgPBExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytPendingBooking.setVisibility(lytPendingBooking.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                imgPBExpand.setImageResource(lytPendingBooking.getVisibility() == View.VISIBLE ? R.drawable.minus_icon : R.drawable.plus_icon);
            }
        });

    }

    private void callApi() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_MY_FLIGHT_BOOKING_HISTORY);
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();
        JSONObject requestObject = new JSONObject();
        try {

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
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("MyBookingResponse : " + response.toString());
                        MyBookings myBooking = ParseManager.getInstance().fromJSON(response, MyBookings.class);
                        updateUi(myBooking);

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

    private void createConfirmPendingUI(final int type, MyBookings data)
    {
        final List<ConfirmedBooking> confirmedBooking = type == TYPE_PENDING ? data.getPendingBookings() : data.getConfirmedBooking();
        if(confirmedBooking != null)
        {
            for (int i = 0; i < confirmedBooking.size(); i++) {
                View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_my_booking_card_row, null, false);

                OTTextView txtCBDestination = (OTTextView) v.findViewById(R.id.txtCBDestination);
                OTTextView txtDepartDate = (OTTextView) v.findViewById(R.id.txtDepartDate);
                OTTextView txtOCNLabel = (OTTextView) v.findViewById(R.id.txtOCNLabel);
                OTTextView txtOCN = (OTTextView) v.findViewById(R.id.txtOCN);
                OTTextView txtPNRLabel = (OTTextView) v.findViewById(R.id.txtPNRLabel);
                OTTextView txtPNR = (OTTextView) v.findViewById(R.id.txtPNR);
                OTTextView txtCBPassStatus = (OTTextView) v.findViewById(R.id.txtCBPassStatus);
                OTTextView txtViewDetails = (OTTextView) v.findViewById(R.id.txtViewDetails);
                OTTextView txtFAQ = (OTTextView) v.findViewById(R.id.txtFAQ);
                OTTextView txtAirlineLabel = (OTTextView) v.findViewById(R.id.txtAirlineLabel);
                OTTextView txtAirline = (OTTextView) v.findViewById(R.id.txtAirline);
                LinearLayout lytOCN = (LinearLayout) v.findViewById(R.id.lytOCN);
                LinearLayout lytPnr = (LinearLayout) v.findViewById(R.id.lytPnr);


                txtCBDestination.setText(confirmedBooking.get(i).getFlightDepartureAirportDisplayName()+" ("+confirmedBooking.get(i).getFlightDepartureAirportCode()+")");
                txtCBPassStatus.setText(confirmedBooking.get(i).getLABLFPoBookingConfirmedLabel());
                txtDepartDate.setText(confirmedBooking.get(i).getFlightDateArrival());
                txtOCNLabel.setText(confirmedBooking.get(i).getLABLOCNShortLabel());
                txtAirlineLabel.setText(data.getLabels().getLABL_Airline_Label());
                txtAirline.setText(": "+confirmedBooking.get(i).getAirlineName());
                txtOCN.setText(": "+confirmedBooking.get(i).getTgpPaxBookingConfirmationNumber());
                txtPNR.setText(": "+confirmedBooking.get(i).getBookingPnrNumber());
                txtPNRLabel.setText(confirmedBooking.get(i).getAirlineName() + " " + confirmedBooking.get(i).getLABLPNRLabel());

                txtViewDetails.setText(data.getLabels().getViewDetailLabel());
                txtFAQ.setText(data.getLabels().getFaqLabel());

                final int finalI = i;
                txtViewDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmedBooking.get(finalI).setBookingType(type);
                        Utils.moveToFragment(getActivity(),new MyBookingViewDetailsFragment(), confirmedBooking.get(finalI), 0);
                    }
                });

                txtFAQ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.moveToFragment(getActivity(),new FAQFragment(), null, 0);
                    }
                });

                if(type == TYPE_PENDING)
                {
                    txtCBPassStatus.setTextColor(Color.parseColor("#ff0000"));
                    lytOCN.setVisibility(View.GONE);
                    lytPnr.setVisibility(View.GONE);
                    
                    lytPendingBooking.addView(v);
                }
                else if(type == TYPE_CONFIRM)
                {
                    lytCurrentBooking.addView(v);
                }

            }

            if(confirmedBooking.isEmpty())
            {
                OTTextView v = new OTTextView(getActivity());
                v.setTextColor(Color.parseColor("#000000"));
                v.setTextSize(Utils.convertPixelToDp(getActivity(), getActivity().getResources().getDimension(R.dimen.size_font_15)));
                v.setPadding((int) Utils.conertDpToPixel(getActivity(), 20), 0, (int) Utils.conertDpToPixel(getActivity(), 20), 0);


                if(type == TYPE_PENDING)
                {
                    v.setText(data.getLabels().getLABLNoBookingsDescLabel());
                    lytPendingBooking.addView(v);
                }
                else if(type == TYPE_CONFIRM)
                {
                    v.setText(data.getLabels().getLABLNoBookingsDescLabel());
                    lytCurrentBooking.addView(v);
                }
            }
        }
    }

    private void updateUi(MyBookings myBooking) {
        scrollView.setVisibility(View.VISIBLE);
        Labels label = myBooking.getLabels();
        txtCurrentBookingLabel.setText(label.getLABLBookingTripsLabel());
        txtPendingBookingLabel.setText(label.getLABLPendingBookingLabel());

        createConfirmPendingUI(TYPE_CONFIRM, myBooking);
        createConfirmPendingUI(TYPE_PENDING, myBooking);

        imgCBExpand.performClick();
        imgPBExpand.performClick();
    }


}
  /* CardView cv = (CardView) v.getRootView();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (Build.VERSION_CODES.LOLLIPOP == Build.VERSION.SDK_INT) {

                    params.setMargins(20, 20, 20, 20);
                }
                else {
                    cv.setMaxCardElevation(20);
                }
                cv.setCardElevation(20);
                cv.setLayoutParams(params);*/
package com.optiontown.app.view.fragment.fpo.redeem.mmb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.redeem.FlightSmallView;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.Segment;
import com.optiontown.app.model.redeem.mmb.MMBSelectFlightData;
import com.optiontown.app.model.redeem.mmb.ManageMybooking;
import com.optiontown.app.model.redeem.mmb.PassBookingList;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyClickableSpan;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zafar.imam on 22-11-2016.
 */

public class MMBChangeFlightSelectFragment extends BaseFragment {
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
    private OTTextView txtContinue;
    private PassBookingList passBookingListData;
    private ArrayList<String> passengerlist;
    private MMBSelectFlightData mMBSelectFlightData;
    private LinearLayout lytItinerary;
    private String strSelectedIFSIndex = "";
    private RelativeLayout rlytBody;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytError;
    private  ManageMybooking mmbData;
    private ImageView imgEdit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmb_change_flight_select, container, false);
        loader = AppDialogLoader.getLoader(getActivity());
        initUi(view);
        try {
            passBookingListData = (PassBookingList) getArguments().getSerializable(getString(R.string.key_serializable));
        } catch (Exception e) {
        }

        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(),"",null);
        callApi(passBookingListData.getBooking_Ref_Num());
        //redeem_search_result_row

        return view;
    }

    private void initUi(View view) {
        rlytBody = (RelativeLayout) view.findViewById(R.id.rlytBody);
        rlytBody.setVisibility(View.GONE);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

        lytItinerary = (LinearLayout) view.findViewById(R.id.lytItinerary);
        txtAirlineLabel = (OTTextView) view.findViewById(R.id.txtAirlineLabel);
        txtAirline = (OTTextView) view.findViewById(R.id.txtAirline);
        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);
        txtPassengerLabel = (OTTextView) view.findViewById(R.id.txtPassengerLabel);
        txtPassenger = (TextView) view.findViewById(R.id.txtPassenger);
        txtAirlineBookingRefLabel = (OTTextView) view.findViewById(R.id.txtAirlineBookingRefLabel);
        txtAirlineBookingNo = (OTTextView) view.findViewById(R.id.txtAirlineBookingNo);
        txtSelectFlightLabel = (OTTextView) view.findViewById(R.id.txtSelectFlightLabel);
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
                    Utils.moveToFragment(getActivity(), new MMBChangeFlightSelectDateFragment(), strSelectedIFSIndex, 0);
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
    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();
        try {
            if (strSelectedIFSIndex.equals("")) {
                listError.add(mMBSelectFlightData.getTop_Message());
            }
        } catch (Exception e) {
            Utils.ERROR("" + e.toString());
        }
        return listError;
    }


    private void callApi(String Pnr) {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_MMB_CHANGE_FLIGHT_SELECT);
        loader = AppDialogLoader.getLoader(getActivity());
        loader.show();
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("Pnr", Pnr);
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
                        Utils.DEBUG("MMBSelectResponse: " + response.toString());
                        mMBSelectFlightData = ParseManager.getInstance().fromJSON(response, MMBSelectFlightData.class);
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
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


    }

    private void updateUi() {
        showItinerary();
        loader.dismiss();
        rlytBody.setVisibility(View.VISIBLE);
        txtSelectedPass.setText(Utils.getSelectedPass(getActivity()));

         mmbData = mMBSelectFlightData.getManageMybooking();
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(),mmbData.getChange_Flight_Heading_Label(),null);
        txtAirlineLabel.setText(mmbData.getLABLAirlineLabel()+" : ");
        txtAirline.setText(mmbData.getAirlineNameLabel());
        txtPassengerLabel.setText(mmbData.getLABLPassengerLabel()+" : ");
        txtAirlineBookingRefLabel.setText(mmbData.getBooking_Ref_Label()+" : ");
        txtAirlineBookingNo.setText(mmbData.getBooking_Ref_Pnr());
        String pass = "";
         passengerlist = mMBSelectFlightData.getPassengerNamelist();
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

       //  passengers = passengers.substring(0, passengers.length() > 20 ? 20 : passengers.length()) + "... "+ getString(R.string.string_more);






    }

    private void showItinerary()
    {
        if(mMBSelectFlightData.getItinerarry() == null)
        {
            return;
        }

        for (int pos = 0; pos < mMBSelectFlightData.getItinerarry().size(); pos++)//size 1
        {
            final Itinerarry itinerarry = mMBSelectFlightData.getItinerarry().get(pos);
            for (int index = 0; index < itinerarry.getSegments().size(); index++)//size max 2
            {
                final View viewParent = LayoutInflater.from(getActivity()).inflate(R.layout.redeem_search_result_row, null, false);
                OTTextView txtJourneyType = (OTTextView) viewParent.findViewById(R.id.txtJourneyType);
                OTTextView txtAirlineName = (OTTextView) viewParent.findViewById(R.id.txtAirlineName);
                NetworkImageView imgAirline = (NetworkImageView) viewParent.findViewById(R.id.imgAirline);
                View viewMargin = (View) viewParent.findViewById(R.id.viewMargin);
                OTTextView txtOperation = (OTTextView) viewParent.findViewById(R.id.txtOperation);
                final OTTextView txtSelect = (OTTextView) viewParent.findViewById(R.id.txtSelect);
                final ImageView imgSelected = (ImageView) viewParent.findViewById(R.id.imgSelected);
                final LinearLayout lytSelect = (LinearLayout) viewParent.findViewById(R.id.lytSelect);
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
                txtSelect.setText("Select");

                viewMargin.setVisibility(View.GONE);

                final int finalIndex = index;
                lytSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = txtSelect.getText().toString().equals("Select") ? true : false;
                        txtSelect.setText(checked ? "Selected" : "Select");
                        imgSelected.setVisibility(View.GONE);
                        Utils.setBackground(lytSelect, checked ? getActivity().getResources().getDrawable(R.drawable.rounded_green) : getActivity().getResources().getDrawable(R.drawable.rounded_stroke_green));
                        txtSelect.setTextColor(checked ? Color.parseColor("#ffffff") : Color.parseColor("#FF38971C"));
                        lytSelect.setVisibility(View.VISIBLE);
                        lytError.setVisibility(View.GONE);
                        if(checked)
                        {
                            strSelectedIFSIndex = itinerarry.getSegments().get(finalIndex).getLegList().get(0).getFlightSmallView().getIndexes().split("_")[0];
                        }
                        else
                        {
                            strSelectedIFSIndex = "";
                        }

                        //deselect from others
                        for (int index = 0; index < lytItinerary.getChildCount(); index++) {
                            View childAt = lytItinerary.getChildAt(index);
                            if(!childAt.equals(viewParent))
                            {
                                OTTextView txtSelect = (OTTextView) childAt.findViewById(R.id.txtSelect);
                                LinearLayout lytSelect = (LinearLayout) childAt.findViewById(R.id.lytSelect);


                                txtSelect.setText("Select");
                                Utils.setBackground(lytSelect, getActivity().getResources().getDrawable(R.drawable.rounded_stroke_green));
                                txtSelect.setTextColor(Color.parseColor("#FF38971C"));
                            }
                        }

                    }
                });


                lytItinerary.addView(viewParent);
            }
        }
    }

}

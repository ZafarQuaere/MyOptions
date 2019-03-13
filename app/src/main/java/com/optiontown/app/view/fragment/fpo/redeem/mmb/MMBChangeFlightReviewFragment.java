package com.optiontown.app.view.fragment.fpo.redeem.mmb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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
import com.optiontown.app.model.redeem.mmb.MMBChangeFlightReviewData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyClickableSpan;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by zafar.imam on 22-11-2016.
 */

public class MMBChangeFlightReviewFragment extends BaseFragment {
    private View view;
    private AppDialogLoader loader;
    private OTTextView txtAirlineLabel;
    private OTTextView txtAirline;
    private OTTextView txtSelectedPass;
    private OTTextView txtPassengerLabel;
    private TextView txtPassenger;
    private OTTextView txtAirlineBookingRefLabel;
    private OTTextView txtAirlineBookingNo;

    private OTTextView txtReviewFlightLabel;
    private OTTextView txtChangeFee;
    private OTTextView txtProceedToPayment;
    private OTTextView txtChangeFeeTitleLabel;
    private OTTextView txtChangeFeeLabel;
    private OTTextView txtFareDifferenceLabel;
    private OTTextView txtFareDifference;
    private OTTextView txtTotalAmountLabel;
    private OTTextView txtTotalAmount;
    private OTTextView txtPaymentDesc;
    private RecyclerView recyclerView;
    private RelativeLayout rlytBody;
    private String strIFSIndex;
    private MMBChangeFlightReviewData mMBChangeFlightReviewData;
    private LinearLayout lytItineraryCurrent;
    private LinearLayout lytItineraryNew;
    private OTTextView txtCurrentFlightLabel;
    private OTTextView txtNewFlightLabel;
    private ArrayList<String> passengerlist;
    private ImageView imgEdit;
    private LinearLayout lytFeeCalculation;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmb_change_flight_review, container, false);
        strIFSIndex = (String) getArguments().getSerializable(getString(R.string.key_serializable));
        loader = AppDialogLoader.getLoader(getActivity());
        initUi(view);
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        callAPI();
        return view;
    }

    private void initUi(View view) {
        rlytBody = (RelativeLayout) view.findViewById(R.id.rlytBody);
        rlytBody.setVisibility(View.GONE);

        txtAirlineLabel = (OTTextView) view.findViewById(R.id.txtAirlineLabel);
        txtAirline = (OTTextView) view.findViewById(R.id.txtAirline);

        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);
        txtSelectedPass.setText(Utils.getSelectedPass(getActivity()));

        txtPassengerLabel = (OTTextView) view.findViewById(R.id.txtPassengerLabel);
        txtPassenger = (TextView) view.findViewById(R.id.txtPassenger);
        txtAirlineBookingRefLabel = (OTTextView) view.findViewById(R.id.txtAirlineBookingRefLabel);
        txtAirlineBookingNo = (OTTextView) view.findViewById(R.id.txtAirlineBookingNo);

        lytItineraryCurrent = (LinearLayout) view.findViewById(R.id.lytItineraryCurrent);
        lytItineraryNew = (LinearLayout) view.findViewById(R.id.lytItineraryNew);
        txtCurrentFlightLabel = (OTTextView) view.findViewById(R.id.txtCurrentFlightLabel);
        txtNewFlightLabel = (OTTextView) view.findViewById(R.id.txtNewFlightLabel);

        txtReviewFlightLabel = (OTTextView) view.findViewById(R.id.txtReviewFlightLabel);
        txtChangeFeeTitleLabel = (OTTextView) view.findViewById(R.id.txtChangeFeeTitleLabel);
        txtChangeFee = (OTTextView) view.findViewById(R.id.txtChangeFee);
        txtChangeFeeLabel = (OTTextView) view.findViewById(R.id.txtChangeFeeLabel);
        txtFareDifferenceLabel = (OTTextView) view.findViewById(R.id.txtFareDifferenceLabel);
        txtFareDifference = (OTTextView) view.findViewById(R.id.txtFareDifference);
        txtTotalAmountLabel = (OTTextView) view.findViewById(R.id.txtTotalAmountLabel);
        txtTotalAmount = (OTTextView) view.findViewById(R.id.txtTotalAmount);
        txtPaymentDesc = (OTTextView) view.findViewById(R.id.txtPaymentDesc);

        txtProceedToPayment = (OTTextView) view.findViewById(R.id.txtProceedToPayment);
        txtProceedToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to payment page
                Utils.moveToFragment(getActivity(), new MMBPaymentFragment(), null, 0);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.clearBackstackTillMMBSelectPassFragment(getActivity());
            }
        });

        lytFeeCalculation = (LinearLayout) view.findViewById(R.id.lytFeeCalculation);
    }

    private void callAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_ChangeFlightReview)
                + strIFSIndex;
        loader = AppDialogLoader.getLoader(getActivity());
        loader.show();
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
                        Utils.DEBUG("Response : " + response.toString());
                        mMBChangeFlightReviewData = ParseManager.getInstance().fromJSON(response, MMBChangeFlightReviewData.class);
                        updateUI();
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

    private void updateUI()
    {

        txtAirlineLabel.setText(mMBChangeFlightReviewData.getLABLAirlineLabel()+" : ");
        txtAirline.setText(mMBChangeFlightReviewData.getAirlineNameLabel());
        txtPassengerLabel.setText(mMBChangeFlightReviewData.getPassengerLabel()+" : ");
        txtAirlineBookingRefLabel.setText(mMBChangeFlightReviewData.getBookingRefLabel()+" : ");
        txtAirlineBookingNo.setText(mMBChangeFlightReviewData.getBookingRefPnr());

        showMoreText();

        txtCurrentFlightLabel.setText(mMBChangeFlightReviewData.getCurrentFlightLabel());
        txtNewFlightLabel.setText(mMBChangeFlightReviewData.getLABLMMBNewFlightLabel());
        txtReviewFlightLabel.setText(mMBChangeFlightReviewData.getLABLReviewNewFlightLabel());
        txtChangeFeeTitleLabel.setText(mMBChangeFlightReviewData.getLABLPassChangeFeeLabel());
        txtChangeFeeLabel.setText(mMBChangeFlightReviewData.getLABLPassChangeFeeLabel());
        txtFareDifferenceLabel.setText(mMBChangeFlightReviewData.getLABLMMOFareDifferenceLabel());
        txtTotalAmountLabel.setText(mMBChangeFlightReviewData.getLABLTotalLabel());
        txtPaymentDesc.setText(mMBChangeFlightReviewData.getLABLPayAmountNowLabel());
        txtChangeFee.setText(mMBChangeFlightReviewData.getTransactionCurrencyCode() + " " + mMBChangeFlightReviewData.getPassNewPriceFeevalue());
        txtFareDifference.setText(mMBChangeFlightReviewData.getTransactionCurrencyCode() + " " + mMBChangeFlightReviewData.getPassNewPriceFarediff());
        txtTotalAmount.setText(mMBChangeFlightReviewData.getTransactionCurrencyCode() + " " + mMBChangeFlightReviewData.getTotalamount());
        txtProceedToPayment.setText(mMBChangeFlightReviewData.isDisplayPrice() ? mMBChangeFlightReviewData.getLABLProceedToPaymentLabel() : mMBChangeFlightReviewData.getLABL_Process_Status_Bar_FPO_Confirm_Label());
        lytFeeCalculation.setVisibility(mMBChangeFlightReviewData.isDisplayPrice() ? View.VISIBLE : View.GONE);


        showItinerary(lytItineraryNew, mMBChangeFlightReviewData.getItinerarry_New());
        showItinerary(lytItineraryCurrent, mMBChangeFlightReviewData.getItinerarry_Current());
        rlytBody.setVisibility(View.VISIBLE);
    }

    private void showMoreText() {
        String pass = "";
        passengerlist = mMBChangeFlightReviewData.getPassengersList();
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
    }

    private void showItinerary(LinearLayout lytItinerary, ArrayList<Itinerarry> itinerarryData)
    {
        if(itinerarryData == null || itinerarryData == null)
        {
            return;
        }

        for (int pos = 0; pos < itinerarryData.size(); pos++)//size 1
        {
            final Itinerarry itinerarry = itinerarryData.get(pos);
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
                lytSelect.setVisibility(View.GONE);

                viewMargin.setVisibility(View.GONE);

                lytItinerary.addView(viewParent);
            }
        }
    }
}

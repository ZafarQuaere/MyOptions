package com.optiontown.app.view.fragment.fpo.redeem.mmb;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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
import com.optiontown.R;
import com.optiontown.app.adapter.MMBSelectFlightRecyclerViewAdapter;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.mmb.MMBChangeFlightSelectNewData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyClickableSpan;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zafar.imam on 22-11-2016.
 */

public class MMBChangeFlightNewDateFragment extends BaseFragment {
    private View view;
    private AppDialogLoader loader;
    private OTTextView txtAirlineLabel;
    private OTTextView txtAirline;
    private OTTextView txtSelectedPass;
    private OTTextView txtPassengerLabel;
    private TextView txtPassenger;
    private OTTextView txtAirlineBookingRefLabel;
    private OTTextView txtAirlineBookingNo;
    private RelativeLayout rlytBody;

    private OTTextView txtNewDepartDateLabel;
    private OTTextView txtNewDepartDate;
    private OTTextView txtSelectFlightLabel;
    private OTTextView txtContinue;

    private RecyclerView recyclerView;
    private MMBChangeFlightSelectNewData mMBChangeFlightSelectNewData;
    private String strDateDDMMYYYY;
    private String strIFSIndex = "";
    private ArrayList<String> passengerlist;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytError;
    private ImageView imgEdit;
    private Communicator communicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmb_change_flight_new_date, container, false);
        strDateDDMMYYYY = (String) getArguments().getSerializable(getString(R.string.key_serializable));
        loader = AppDialogLoader.getLoader(getActivity());
        initUi(view);
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(),"Change Flight",null);
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

        txtNewDepartDateLabel = (OTTextView) view.findViewById(R.id.txtNewDepartDateLabel);
        txtNewDepartDate = (OTTextView) view.findViewById(R.id.txtNewDepartDate);
        txtSelectFlightLabel = (OTTextView) view.findViewById(R.id.txtSelectFlightLabel);

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

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
                 else {
                    Utils.moveToFragment(getActivity(), new MMBChangeFlightReviewFragment(), strIFSIndex, 0);
                }
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
    }
    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();
        try {
            if (strIFSIndex.equals("")) {
                listError.add(mMBChangeFlightSelectNewData.getTop_Message());
            }
        } catch (Exception e) {
            Utils.ERROR("" + e.toString());
        }
        return listError;
    }

    private void callAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_ChangeFlightSelectNew);
        loader = AppDialogLoader.getLoader(getActivity());
        loader.show();
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("selectedDate", strDateDDMMYYYY);
        } catch (JSONException e) {
            e.printStackTrace();
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
                        Utils.DEBUG("ManageMyBookingResponse : " + response.toString());
                        mMBChangeFlightSelectNewData = ParseManager.getInstance().fromJSON(response, MMBChangeFlightSelectNewData.class);
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

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("MMBChangeFlightNewDateFragment >> onAttach(Activity) called");
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    private void updateUI() {


        if(mMBChangeFlightSelectNewData.getError() == 1)
        {
            FragmentCommunicationData data = new FragmentCommunicationData();
            data.setFragmentName(new MMBChangeFlightSelectDateFragment().getClass().getName());
            data.setErrorMessage(mMBChangeFlightSelectNewData.getMessage());

            communicator.onResponse(data);

            ((MainActivity)getActivity()).onBackPressed();

            return;
        }


        rlytBody.setVisibility(View.VISIBLE);

        txtNewDepartDate.setText(strDateDDMMYYYY.replace("-", " "));
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(),mMBChangeFlightSelectNewData.getChange_Flight_Heading_Label(),null);
        txtAirlineLabel.setText(mMBChangeFlightSelectNewData.getLABL_Airline_Label() + " : ");
        txtAirline.setText(mMBChangeFlightSelectNewData.getAirline_Name_Label());
        txtPassengerLabel.setText(mMBChangeFlightSelectNewData.getPassenger_Label() + " : ");
        txtAirlineBookingRefLabel.setText(mMBChangeFlightSelectNewData.getBooking_Ref_Label() + " : ");
        txtAirlineBookingNo.setText(mMBChangeFlightSelectNewData.getBooking_Ref_Pnr());

        String pass = "";
        passengerlist = mMBChangeFlightSelectNewData.getPassengerNamelist();
        String dialogBody = "";
        for (int i = 0; i < passengerlist.size(); i++) {

            if (passengerlist.size() > 1) {

                pass = passengerlist.get(i) + "\n" + getString(R.string.string_more) + "..";
                dialogBody = dialogBody + passengerlist.get(i) + "\n";
                String passenger = pass + passengerlist.get(i) + "\n";
                passenger = passenger.substring(0, passenger.length() > 30 ? 20 : passenger.length()) + ".." + getString(R.string.string_more);
                SpannableString ss = new SpannableString(passenger);
                String readMore = getString(R.string.string_more);
                int readMoreIndex = pass.toString().indexOf(readMore);

                ss.setSpan(new MyClickableSpan(getActivity(), 1, dialogBody, "Passengers"), readMoreIndex, readMoreIndex + readMore.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                txtPassenger.setMovementMethod(LinkMovementMethod.getInstance());
                txtPassenger.setText(ss);
            } else {
                pass = pass + passengerlist.get(i);
                txtPassenger.setText(pass);
            }

            pass = passengerlist.get(i);

            System.out.println("Server Passengers: " + pass);
        }
        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        MMBSelectFlightRecyclerViewAdapter adapter = new MMBSelectFlightRecyclerViewAdapter(getActivity(), mMBChangeFlightSelectNewData.getItinerarry(), new MMBSelectFlightRecyclerViewAdapter.IRecyclerViewHolderClicks() {

            @Override
            public void onClickRecyclerItemSelect(View v, Itinerarry itinary) {
                lytError.setVisibility(View.GONE);
                Utils.DEBUG("onClickRecyclerItemSelect called : ");
                if (itinary != null) {
                    strIFSIndex = itinary.getSegments().get(0).getLegList().get(0).getFlightSmallView().getIndexes().split("_")[0];
                } else {
                    strIFSIndex = "";
                }

            }
        });
        recyclerView.setAdapter(adapter);
    }
}

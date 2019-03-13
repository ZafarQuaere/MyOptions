package com.optiontown.app.view.fragment.fpo.redeem.mmb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.MmbSelectPassAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.mmb.MMBData;
import com.optiontown.app.model.redeem.mmb.MapOfPassAndConfirmedBooking;
import com.optiontown.app.model.redeem.mmp.UsersAdded;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.util.ArrayList;


public class MMBSelectPassFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager recylerViewLayoutManager;
    private View view;
    private AppDialogLoader loader;
    private OTTextView txtContinue;
    private OTTextView label_select_flight;
    private OTTextView txtflightpass;
    private CardView list_item;
    private MMBData mmbData;
    private MapOfPassAndConfirmedBooking passSelected;
    private UsersAdded user;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytError;
    private LinearLayout lyt_ocn;
    private InternationalizeData localization;
    private ArrayList<String> listError;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmp_select_pass_fragment, container, false);
        user = new UsersAdded();
        try {
            mmbData = (MMBData) getArguments().getSerializable(getString(R.string.key_serializable));
        } catch (Exception e) {
        }

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }


        initUi(view);
        updateUi(mmbData);


        loader = AppDialogLoader.getLoader(getActivity());
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), localization.getChangeMyPassLabel(), null);
        initUi(view);
        //callInvoiceApi();

        return view;
    }


    private void initUi(View view) {

        lyt_ocn = (LinearLayout) view.findViewById(R.id.lyt_ocn);
        lyt_ocn.setVisibility(View.GONE);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        list_item = (CardView) view.findViewById(R.id.list_item);
        txtflightpass = (OTTextView) view.findViewById(R.id.txtflightpass);
        (view.findViewById(R.id.lytSelect)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_item.setVisibility(list_item.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recylerViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recylerViewLayoutManager);
        label_select_flight = (OTTextView) view.findViewById(R.id.label_select_flight);
        txtContinue = (OTTextView) view.findViewById(R.id.txtContinue);
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //first clear all previos error message view
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                listError = validateAllInputsCommon();
                if (listError.size() > 0) {
                    lytError.setVisibility(View.VISIBLE);
                    //show error message
                    for (int index = 0; index < listError.size(); index++) {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }
                } else {


                    callApi(passSelected.getPassId());

                }
            }
        });

        labelUpdate();
    }

    private void callApi(final String passId) {

        //https://192.168.64.10/getSellerList.do?mobileAction=ManageMybooking
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_MANAGE_MY_BOOKING);
        loader = AppDialogLoader.getLoader(getActivity());
        loader.show();
        JSONObject requestObject = new JSONObject();
        try {

            requestObject.put("selectedpassid", passId);

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
                        Utils.DEBUG("SelectPassResponse >>>>>>>>>>: " + response.toString());
                        mmbData = ParseManager.getInstance().fromJSON(response, MMBData.class);

                        if(mmbData.getMapOfPassAndConfirmedBooking().get(0).getPassBookingList().size()>1)
                        {
                            //success
                            loader.dismiss();
                            lytError.setVisibility(View.GONE);
                            mmbData.setPassId(passId);
                            Utils.moveToFragment(getActivity(), new MMBChangeFlightBookingListFragment(), mmbData, 0);
                        }else {

                            //SHOW ERROR
                            loader.dismiss();
                            listError.add(localization.getLABL_ERR_NO_VALID_BOOKING_TO_CHANGE_Label());
                            if (listError.size() > 0) {
                                lytError.setVisibility(View.VISIBLE);
                                //show error message
                                for (int index = 0; index < listError.size(); index++) {
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                                }
                            }

                        }



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

    private void labelUpdate() {

        label_select_flight.setText(localization.getLABLFPOSelectFlightPassLabel());
        txtflightpass.setText(localization.getLABL_Select_Your_Pass_Label());

    }

    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();
        try {
            if (Utils.compareDefaultValues(txtflightpass, localization.getLABL_Select_Your_Pass_Label()) || Utils.compareDefaultValues(txtflightpass, "")) {
                listError.add(localization.getLABL_ERR_select_pass_Label());
            }
        } catch (Exception e) {
            Utils.ERROR("" + e.toString());
        }


        return listError;
    }


    private void updateUi(MMBData mmbData) {

        if (mmbData != null) {
           // label_select_flight.setText("");
            txtContinue.setText(mmbData.getContinueButtonLabel());
            final String msgSelect = localization.getLABL_Select_Your_Pass_Label();
            txtflightpass.setText(msgSelect);
            MmbSelectPassAdapter adapter = new MmbSelectPassAdapter(getActivity(), mmbData.getMapOfPassAndConfirmedBooking(), new MmbSelectPassAdapter.RecyclerViewHolderClicks() {
                @Override
                public void onClickRecyclerItemDetail(View v, MapOfPassAndConfirmedBooking flightDataSelected) {
                    list_item.setVisibility(View.GONE);
                    passSelected = flightDataSelected;
                   flightDataSelected.setPassId(passSelected.getPassId());

                    if (flightDataSelected != null) {
                        txtflightpass.setText(passSelected.getPassLabel().replace("#", ":"));
                        flightDataSelected.setPassId(passSelected.getPassId());
                        Utils.setSelectedPass(getActivity(),passSelected.getPassLabel().replace("#", ":"));
                    } else {
                        txtflightpass.setText(msgSelect);
                    }


                }
            });
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }
    }


}

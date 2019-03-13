package com.optiontown.app.view.fragment.fpo.redeem.mmp;

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
import com.optiontown.app.adapter.MmpSelectPassAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.mmp.FlightsList;
import com.optiontown.app.model.redeem.mmp.InvoiceData;
import com.optiontown.app.model.redeem.mmp.MmpLabel;
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

/**
 * Created by parasmani.sharma on 03/11/2016.
 */
public class MMPSelectPassFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager recylerViewLayoutManager;
    private View view;
    private AppDialogLoader loader;
    private InvoiceData invoiceData;
    private OTTextView txtContinue;
    private FlightsList passSelected;
    private OTTextView txtflightpass;
    private CardView list_item;
    private MmpLabel typeSelected;
    private String mmpType;
    private UsersAdded user;
    private LinearLayout lytErrorMessage;
    private LinearLayout lytError;
    private LinearLayout lyt_ocn;
    private InternationalizeData localization;
    private OTTextView label_select_flight;
    private String topLabel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmp_select_pass_fragment, container, false);
        user = new UsersAdded();

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }




        try {
            typeSelected = (MmpLabel) getArguments().getSerializable(getString(R.string.key_serializable));
            topLabel = typeSelected.getManageMypassLabelList().toString();

            //Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), typeSelected.getManageMypassLabelList(), null);
            Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), topLabel, null);

        } catch (Exception e) {

        }


        loader = AppDialogLoader.getLoader(getActivity());
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        initUi(view);
        callInvoiceApi();

        return view;
    }


    @Override
    public void onBackEventPost() {
        super.onBackEventPost();
        Utils.clearBackstackTillMmpSelectFlightPass(getActivity(), localization.getLABLManageMyPass());
        Utils.DEBUG("onBackEventPost() called");
    }


    private void initUi(View view) {

        label_select_flight = (OTTextView) view.findViewById(R.id.label_select_flight);
        label_select_flight.setText(topLabel  != null ? topLabel : localization.getLABLFPOSelectFlightPassLabel());
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
        txtContinue = (OTTextView) view.findViewById(R.id.txtContinue);
        txtContinue.setText(localization.getLABL_Continue_Button_Label());
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //first clear all previos error message view
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                ArrayList<String> listError = validateAllInputsCommon();
                if (listError.size() > 0) {
                    lytError.setVisibility(View.VISIBLE);
                    //show error message
                    for (int index = 0; index < listError.size(); index++) {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }
                } else {
                    if (passSelected != null) {

                    if (typeSelected.getId() == 2) {
                        Utils.moveToFragment(getActivity(), new MMPSelectParameterFragment(), passSelected, 0);
                    } else if (typeSelected.getId() == 3) {

                            Utils.moveToFragment(getActivity(), new MMPAddUserFragment(), passSelected, 0);

                        } else if (typeSelected.getId() == 4) {

                        } else if (typeSelected.getId() == 5) {
                            Utils.moveToFragment(getActivity(), new MMPUpdateUserSelectFragment(), passSelected, 0);
                        } else if (typeSelected.getId() == 6) {
                            Utils.moveToFragment(getActivity(), new MMPInvoiceFragment(), passSelected, 0);
                        }
                    } else {
                        Utils.moveToFragment(getActivity(), new MMPUpdateUserFragment(), null, 0);
                    }

                }
            }
        });
    }

    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();
        try
        {
            if (Utils.compareDefaultValues(txtflightpass, invoiceData.getFlightsList().get(0).getLabel()) || Utils.compareDefaultValues(txtflightpass, "")) {
                listError.add(localization.getLABL_ERR_select_pass_Label());
            }
        }catch (Exception e)
        {
            Utils.ERROR("" + e.toString());
        }


        return listError;
    }


    private void updateUi(InvoiceData invoiceData) {

        if (invoiceData != null) {
            final String msgSelect = /*"Select your eCoupon"*/ invoiceData.getFlightsList().get(0).getLabel();
            txtflightpass.setText(msgSelect);
            MmpSelectPassAdapter adapter = new MmpSelectPassAdapter(getActivity(), invoiceData.getFlightsList(), new MmpSelectPassAdapter.RecyclerViewHolderClicks() {
                @Override
                public void onClickRecyclerItemDetail(View v, FlightsList flightsList) {
                    list_item.setVisibility(View.GONE);
                    passSelected = flightsList;

                    if (flightsList != null) {
                        txtflightpass.setText(passSelected.getLabel().replace("#", ":"));
                        flightsList.setPassId(passSelected.getId());
                    } else {
                        txtflightpass.setText(msgSelect);
                    }


                }
            });
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }
    }

    private void callInvoiceApi() {
        loader.show();
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_Select_invoice);
        if (typeSelected.getId() == 2) {
            url = url + "&MId=1";
        }
        JSONObject requestObject = new JSONObject();
        try {
            /*requestObject.put("country", country);*/
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
                        Utils.DEBUG("MMPSelect Response : " + response.toString());
                        invoiceData = ParseManager.getInstance().fromJSON(response, InvoiceData.class);
                        updateUi(invoiceData);
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

}

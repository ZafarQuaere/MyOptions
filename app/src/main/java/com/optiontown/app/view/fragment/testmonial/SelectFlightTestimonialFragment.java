package com.optiontown.app.view.fragment.testmonial;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.SelectedFlightTestimonialAdapter;
import com.optiontown.app.model.fpotestimonial.TestimonialMaster;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 28/04/2017.
 */

public class SelectFlightTestimonialFragment extends BaseFragment {


    private View view;
    private Spinner airlineSpinner;
    private OTTextView txtAirlineLabel;
    private OTTextView txtProductLabel;
    private Spinner productSpinner;
    private AppDialogLoader loader;
    private TestimonialMaster testimonialData;
    private String airlineId = "-1";
    private String optionId = "-1";
    private RecyclerView airlineRecycler;
    private RecyclerView optionRecycler;
    private OTTextView txtAirlineVal;
    private OTTextView txtOptionVal;
    private OTTextView submit;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private ArrayList<String> listError;
    private InternationalizeData localization;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_selectflight_testimonial, container, false);

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }


        loader = AppDialogLoader.getLoader(getActivity());

        initUI();

        callContactUSLabelsAPI(airlineId, optionId);

        return view;
    }

    private void callContactUSLabelsAPI(final String airlineId, final String optionId) {

        loader.show();
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_CONTACT_US_LABEL);

        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("tgProductId", Integer.toString(getResources().getInteger(R.integer.value_tgProductId_fpo)));
            requestObject.put("CountryId", Utils.getUserSelectedCountryId(getActivity()) + "");
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(getActivity()) + "");

            if (!airlineId.equals("-1")) {
                requestObject.put("airlineid", airlineId);
            } else {
                requestObject.put("airlineid", "-1");
            }

            if (!optionId.equals("-1")) {
                requestObject.put("lsproductid", optionId);
            } else {
                requestObject.put("lsproductid", "-1");
            }

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
                        testimonialData = ParseManager.getInstance().fromJSON(response, TestimonialMaster.class);

                        if (testimonialData != null) {
                            airlineRecycler.setVisibility(View.GONE);
                            optionRecycler.setVisibility(View.GONE);
                            updateUI(testimonialData, airlineId, optionId);

                            if (testimonialData.getCateogyList().size() > 0) {
                                Utils.moveToFragment(getActivity(), new WriteTestimonialFragment(), testimonialData, 0);
                            }
                        }
                        loader.dismiss();
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

    private void updateUI(final TestimonialMaster testimonialData, final String airlineIDSet, String optionIdSet) {

        try {
            if (!airlineIDSet.equals("-1")) {
                for (int i = 1; i < testimonialData.getAirlineList().size(); i++) {
                    if (airlineIDSet.equals(testimonialData.getAirlineList().get(i).getAirlineID())) {
                        txtAirlineVal.setText(testimonialData.getAirlineList().get(i).getAirlineLabel());
                    }
                }
            } else {

                txtAirlineVal.setText(testimonialData.getAirlineList().get(0).getAirlineLabel());
            }

            if (!optionIdSet.equals("-1")) {
                for (int i = 1; i < testimonialData.getAirlineList().size(); i++) {
                    if (optionIdSet.equals(testimonialData.getOptionList().get(i).getOptionID())) {
                        txtOptionVal.setText(testimonialData.getOptionList().get(i).getOptionLabel());
                    }
                }
            } else {
                txtOptionVal.setText(testimonialData.getOptionList().get(0).getOptionLabel());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        airlineRecycler.setLayoutManager(gridLayoutManager);

       /* SelectedFlightTestimonialAdapter adapterAirline = new SelectedFlightTestimonialAdapter(getActivity(), testimonialData, "selectAirline", new SelectedFlightTestimonialAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, String label, String id) {

                airlineRecycler.setVisibility(View.GONE);
                txtAirlineVal.setText(label);
                txtOptionVal.setText(testimonialData.getOptionList().get(0).getOptionLabel());
                optionId = testimonialData.getOptionList().get(0).getOptionID();
                airlineId = id;
                callContactUSLabelsAPI(airlineId, optionId);
            }
        });
        airlineRecycler.setAdapter(adapterAirline);

        NpaGridLayoutManager gridLayoutManagerOption = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        optionRecycler.setLayoutManager(gridLayoutManagerOption);
        SelectedFlightTestimonialAdapter adapterOption = new SelectedFlightTestimonialAdapter(getActivity(), testimonialData, "selectOption", new SelectedFlightTestimonialAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, String label, String id) {

                optionRecycler.setVisibility(View.GONE);
                txtOptionVal.setText(label);
                optionId = id;
                //callContactUSLabelsAPI(airlineId, optionId);
            }
        });
        optionRecycler.setAdapter(adapterOption);*/

        txtOptionVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txtAirlineVal.getText().toString().equalsIgnoreCase(testimonialData.getAirlineList().get(0).getAirlineLabel()))
                {
                    airlineRecycler.setVisibility(View.GONE);
                    optionRecycler.setVisibility(optionRecycler.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }

            }
        });

        txtAirlineVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                airlineRecycler.setVisibility(airlineRecycler.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                optionRecycler.setVisibility(View.GONE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                listError = validateAllInputsCommon();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //ffp number not required
                        if (listError.size() > 0) {
                            //first clear all previos error message view
                            lytErrorMessage.removeAllViews();
                            lytError.setVisibility(View.VISIBLE);

                            //show error message
                            for (int index = 0; index < listError.size(); index++) {
                                lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                            }
                        } else {

                            airlineRecycler.setVisibility(View.GONE);
                            optionRecycler.setVisibility(View.GONE);
                            callContactUSLabelsAPI(airlineId, optionId);

                        }
                    }
                }, 200);
            }
        });
    }

    private void initUI() {

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        ((OTTextView) view.findViewById(R.id.txt_heading_contactOT)).setText(localization.getLABLMTPContactPageHeadingLabel());
        ((OTTextView) view.findViewById(R.id.txtMendatory)).setText("*" + localization.getLABLMandatoryFieldWithoutAsteriskLabel());

        ((OTTextView) view.findViewById(R.id.txtAirlineLabel)).setText(localization.getLABLAirlineLabel() + "*");
        ((OTTextView) view.findViewById(R.id.txtProductLabel)).setText(localization.getLABL_Product_Label() != null ? localization.getLABL_Product_Label() + "*" : "Product*");

        txtAirlineVal = (OTTextView) view.findViewById(R.id.txtAirlineVal);
        txtAirlineLabel = (OTTextView) view.findViewById(R.id.txtAirlineLabel);
        airlineRecycler = (RecyclerView) view.findViewById(R.id.airlineRecycler);
        txtOptionVal = (OTTextView) view.findViewById(R.id.txtOptionVal);
        txtProductLabel = (OTTextView) view.findViewById(R.id.txtProductLabel);
        optionRecycler = (RecyclerView) view.findViewById(R.id.optionRecycler);
        submit = (OTTextView) view.findViewById(R.id.submit);
        submit.setText(localization.getLABLSubmitButtonLabel());

    }

    private ArrayList<String> validateAllInputsCommon() {

        ArrayList<String> listError = new ArrayList<>();

        if (Utils.compareDefaultValues(txtAirlineVal, testimonialData.getAirlineList().get(0).getAirlineLabel())) {
            listError.add(testimonialData.getErrorMandatoryField());
        }else {

            if (Utils.compareDefaultValues(txtOptionVal, testimonialData.getOptionList().get(0).getOptionLabel())) {
                listError.add(testimonialData.getErrorMandatoryField());
            }
        }

        return listError;
    }
}

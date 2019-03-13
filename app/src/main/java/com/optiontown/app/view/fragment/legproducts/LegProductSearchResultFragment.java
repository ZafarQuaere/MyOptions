package com.optiontown.app.view.fragment.legproducts;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;
import com.optiontown.R;
import com.optiontown.app.adapter.UtoSearchResultLegDataRecyclerAdapter;
import com.optiontown.app.adapter.UtoSearchResultRecyclerAdapter;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.legproducthomepage.SearchHelper;
import com.optiontown.app.model.selectproduct.BoostMyPrioritySelectedData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.model.utosearchresult.AutoAccount;
import com.optiontown.app.model.utosearchresult.IfsObject;
import com.optiontown.app.model.utosearchresult.PnrSearchInputData;
import com.optiontown.app.model.utosearchresult.UtosearchresultHome;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.login.LoginFragment;
import com.optiontown.app.view.fragment.passes.PNRSearchInputFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

/**
 * home UI
 *
 * @author ravi
 */
public class LegProductSearchResultFragment extends BaseFragment {

    private AppDialogLoader loader;
    private GifImageView gifImages;
    private Button btnContinue;
    private String indextString, selectedPrice;
    private LinearLayout lytSearchResult, lytBottom, lyt_recyleview, lytEmailChbx, lytJoinOT, lytError, lytErrorMessage;
    private CheckBox chk_eso_joinOT;
    private OTEditText edit_Email;
    private SearchHelper mSearchHelper;
    private String errorMsg = "";

    private AppSharedPrefs sp;
    private OTTextView txtActualRateStandBy, txtActualRateConfirmed, txtViewDetails, txtEsoEmailLabel, txtEsoJoinOT, txtEsoJoinOTExtra, txtBesideCheckBox;
    private RecyclerView recyclerViewSearchResult;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private boolean error;
    private Communicator communicator;
    private FragmentActivity fragmentActivity;
    private AppSharedPrefs sf;
    private PnrSearchInputData searchData;
    private View view;
    private BoostMyPrioritySelectedData boostPriorityData;
    private List<IfsObject> mIfsObjects;
    private UtosearchresultHome mUtosearchresultHome;
    private ArrayList<String> listChecked = new ArrayList<>();
    private HashMap<String, ArrayList<String>> mapSelectedPax = new HashMap<String, ArrayList<String>>();
    private boolean partialVisibility ;


    UtoSearchResultRecyclerAdapter.IRecyclerViewHolderClicks listener = new UtoSearchResultRecyclerAdapter.IRecyclerViewHolderClicks() {

        @Override
        public void onClickRecyclerItem(View v, int ifsObjectPos, int legPos, int cabinPos, Boolean isChecked,boolean isPartialShown) {
            if (isChecked) {
                listChecked.add(ifsObjectPos + "_" + legPos + "_" + cabinPos);
            } else {
                listChecked.remove(ifsObjectPos + "_" + legPos + "_" + cabinPos);
            }
            if (!listChecked.isEmpty()) {
                lytError.setVisibility(View.GONE);
            }
            partialVisibility = isPartialShown;
            Utils.DEBUG("Partial Visibility : "+isPartialShown);


        }
    };
    UtoSearchResultRecyclerAdapter.IRecyclerViewHolderCheckChangeListerner chkListener = new UtoSearchResultRecyclerAdapter.IRecyclerViewHolderCheckChangeListerner() {

        @Override
        public void onCheckedChangeItem(View v, int ifsObjectPos, int legPos, int cabinPos, Boolean isChecked,boolean isPartialShown) {
            if (isChecked) {
                listChecked.add(ifsObjectPos + "_" + legPos + "_" + cabinPos);
            } else {
                listChecked.remove(ifsObjectPos + "_" + legPos + "_" + cabinPos);
            }

            if (!listChecked.isEmpty()) {
                lytError.setVisibility(View.GONE);
            }
            partialVisibility = isPartialShown;
            Utils.DEBUG("Partial Visibility : "+isPartialShown);
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_legproduct_search_result, container, false);
        loader = AppDialogLoader.getLoader(getActivity());
        sp = AppSharedPrefs.getInstance(getActivity());
        Init(view);
        sf = AppSharedPrefs.getInstance(getActivity());

        try {
            mSearchHelper = (SearchHelper) getArguments().get(getActivity().getString(R.string.key_serializable));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        if (mSearchHelper != null) {
            //if mSearchHelper != null means we are dealing UTO
            SearchForUto(mSearchHelper.getTgProductId(), mSearchHelper.getLanguageId(), mSearchHelper.getCountryId(),
                    mSearchHelper.getMarketingAirlineId(), mSearchHelper.getPnr(), mSearchHelper.getLastName(),
                    mSearchHelper.getIsSearchBy(), mSearchHelper.getOCN(), mSearchHelper.getArriveAirlineCode(),
                    mSearchHelper.getDepartAirlineCode(),
                    mSearchHelper.getArriveArptCode(), mSearchHelper.getDepartArptCode(), mSearchHelper.getDepartDate(),
                    mSearchHelper.getFlightNumber(), mSearchHelper.getCountryId(),
                    mSearchHelper.getMobileNumber(), mSearchHelper.getEmail(), mSearchHelper.getFirstName());
        } else {
            //we are dealing with UP
            try {
                searchData = (PnrSearchInputData) getArguments().get(getActivity().getString(R.string.key_serializable));
            } catch (Exception e) {

            }
            if (searchData != null) {

                Utils.DEBUG("PNR :::::: " + searchData.getPNR());

                SearchForUto(Utils.getCurrentProductId(getActivity()) + "", Utils.getUserSelectedLanguageId(getActivity()) + "", Utils.getUserSelectedCountryId(getActivity()) + "",
                        searchData.getPass_airline_id(), searchData.getPNR(), searchData.getLastName(), "1", searchData.getOCN(), searchData.getArriveAirlineCode(),
                        searchData.getDepartAirlineCode(),
                        searchData.getArriveArptCode(), searchData.getDepartArptCode(), searchData.getDepartDate(),
                        searchData.getFlightNumber(), Utils.getUserSelectedCountryId(getActivity()) + "",
                        searchData.getMobileNumber(), searchData.getEmail(), searchData.getFirstName());

            }

        }
        indextString = sp.get(getString(R.string.key_selected_index_string)) + "";
        selectedPrice = sp.get(getString(R.string.key_selected_price)) + "";

        return view;
    }

    public void Init(View vw) {

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        recyclerViewSearchResult = (RecyclerView) vw.findViewById(R.id.recyclerViewSearchResult);
        txtViewDetails = (OTTextView) vw.findViewById(R.id.txtViewDetails);
        txtEsoEmailLabel = (OTTextView) vw.findViewById(R.id.txtEsoEmailLabel);
        txtEsoJoinOT = (OTTextView) vw.findViewById(R.id.txtEsoJoinOT);
        txtEsoJoinOTExtra = (OTTextView) vw.findViewById(R.id.txtEsoJoinOTExtra);
        txtBesideCheckBox = (OTTextView) vw.findViewById(R.id.txtBesideCheckBox);
        gifImages = (GifImageView) vw.findViewById(R.id.gifImages);
        lytBottom = (LinearLayout) vw.findViewById(R.id.lytBottom);
        lytSearchResult = (LinearLayout) vw.findViewById(R.id.lytSearchResult);
        lyt_recyleview = (LinearLayout) vw.findViewById(R.id.lyt_recyleview);
        lytEmailChbx = (LinearLayout) vw.findViewById(R.id.lytEmailChbx);
        lytJoinOT = (LinearLayout) vw.findViewById(R.id.lytJoinOT);
        lytError = (LinearLayout) vw.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) vw.findViewById(R.id.lytErrorMessage);
        chk_eso_joinOT = (CheckBox) vw.findViewById(R.id.chk_eso_joinOT);
        edit_Email = (OTEditText) vw.findViewById(R.id.edit_Email);

        recyclerViewSearchResult.setHasFixedSize(true);

        btnContinue = (Button) vw.findViewById(R.id.btnContinue);

        txtViewDetails.setPaintFlags(txtViewDetails.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtEsoJoinOT.setPaintFlags(txtEsoJoinOT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtEsoJoinOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new LoginFragment(), null, 0);
            }
        });
        chk_eso_joinOT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {
                    edit_Email.setText("");
                }
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listChecked.isEmpty()) {
                    lytError.setVisibility(View.VISIBLE);
                    lytErrorMessage.removeAllViews();
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), getString(R.string.string_plz_select_one_option)));

                }
                else if (partialVisibility && mapSelectedPax.isEmpty()){
                    lytError.setVisibility(View.VISIBLE);
                    lytErrorMessage.removeAllViews();
                    String partailErrorMsg = mUtosearchresultHome.getIfsObject().get(0).getLegListObj().get(0).getPartailErrorMsg();
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), partailErrorMsg));

                }
                else {
                    lytError.setVisibility(View.GONE);
                    if (chk_eso_joinOT.isChecked()) {
                        if (edit_Email.getText().toString().equalsIgnoreCase("")) {
                            edit_Email.requestFocus();
                            edit_Email.setError("enter email");
                        } else if (!Utils.isValidEmail(edit_Email.getText().toString())) {
                            edit_Email.requestFocus();
                            edit_Email.setError("enter valid email");
                        } else {
                            Utils.moveToFragment(getActivity(), new LegProductReviewFragment(), getEmailStatus(), 0);
                        }
                    } else {
                        if (edit_Email.getText().toString().trim().length() > 0 && !Utils.isValidEmail(edit_Email.getText().toString())) {
                            edit_Email.requestFocus();
                            edit_Email.setError("enter valid email");
                        } else {
                            Utils.moveToFragment(getActivity(), new LegProductReviewFragment(), getEmailStatus(), 0);
                        }

                    }
                }

            }
        });

        txtViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new LegProductViewDetailsFragment(), null, 0);
            }
        });
    }

    private SearchHelper getEmailStatus() {

        SearchHelper mSearchHelper = new SearchHelper();
        mSearchHelper.setCheckEmail(edit_Email.getText().toString());//empty or valid email address
        mSearchHelper.setEmailCheck(chk_eso_joinOT.isChecked());
        if (!mapSelectedPax.isEmpty()){
            mSearchHelper.setSelectedPax(mapSelectedPax);
        }

        return mSearchHelper;
    }


    private void SearchForUto(String productID, String LanguageId, String CountryId, String MarketingAirlineId,
                              String pnr, String lastName, String isSearchBy, String OCN,
                              String arriveArptId, String departArptId, String departArptCode, String arriveArptCode, String dateOfJourney,
                              String flightNumber, String countryCode, String number, String emailAddress, String fname) {

        // loader.show();
        String tag_json_obj = "json_obj_req";
        String url = null;
        if (isSearchBy.equalsIgnoreCase("0")) {
            url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_DetailPnrSearch);
        }
        else if (isSearchBy.equalsIgnoreCase("1")) {
            if (Utils.getCurrentProductId(getActivity()) == getActivity().getResources().getInteger(R.integer.value_tgProductId_utp)) {

                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_OpSearchItIn) + "&passId=" + searchData.getPassId() + "&opID=" + Utils.getCurrentProductId(getActivity());
            } else {
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.ITINERARY_SEARCH);
            }
        }
        else if (isSearchBy.equals("2")){

            url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.ITINERARY_SEARCH);
        }

        JSONObject requestObject = new JSONObject();
        try {

            if (isSearchBy.equalsIgnoreCase("0")) {

                requestObject.put("MarketingAirlineId", MarketingAirlineId);
                requestObject.put("lastName", lastName);
                requestObject.put("arriveArptId", arriveArptId);
                requestObject.put("departArptId", departArptId);
                requestObject.put("departArptCode", departArptCode);
                requestObject.put("arriveArptCode", arriveArptCode);
                requestObject.put("dateOfJourney", dateOfJourney);
                requestObject.put("flightNumber", flightNumber);
                requestObject.put("firstName", fname);
                requestObject.put("countryCode", countryCode);
                requestObject.put("number", number);
                requestObject.put("emailAddress", emailAddress);


            } else if (Utils.getCurrentProductId(getActivity()) == getActivity().getResources().getInteger(R.integer.value_tgProductId_utp)) {

                requestObject.put("tgProductId", productID);
                requestObject.put("LanguageId", LanguageId);
                requestObject.put("CountryId", CountryId);
                requestObject.put("MarketingAirlineId", MarketingAirlineId);
                requestObject.put("pnr", pnr);
                requestObject.put("lastName", lastName);
                requestObject.put("isSearchBy", isSearchBy);
                requestObject.put("OCN", OCN);

                requestObject.put("arriveArptId", arriveArptId);
                requestObject.put("departArptId", departArptId);
                requestObject.put("departArptCode", departArptCode);
                requestObject.put("arriveArptCode", arriveArptCode);
                requestObject.put("dateOfJourney", dateOfJourney);
                requestObject.put("flightNumber", flightNumber);
                requestObject.put("firstName", fname);
                requestObject.put("countryCode", countryCode);
                requestObject.put("number", number);
                requestObject.put("emailAddress", emailAddress);


            } else {
                requestObject.put("tgProductId", productID);
                requestObject.put("LanguageId", LanguageId);
                requestObject.put("CountryId", CountryId);
                requestObject.put("MarketingAirlineId", MarketingAirlineId);
                requestObject.put("pnr", pnr);
                requestObject.put("lastName", lastName);
                requestObject.put("isSearchBy", isSearchBy);
                requestObject.put("OCN", OCN);

                requestObject.put("arriveArptId", arriveArptId);
                requestObject.put("departArptId", departArptId);
                requestObject.put("departArptCode", departArptCode);
                requestObject.put("arriveArptCode", arriveArptCode);
                requestObject.put("dateOfJourney", dateOfJourney);
                requestObject.put("flightNumber", flightNumber);
                requestObject.put("firstName", fname);
                requestObject.put("countryCode", countryCode);
                requestObject.put("number", number);
                requestObject.put("emailAddress", emailAddress);
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
                            Utils.DEBUG("LegProductSearchResultResponse : " + response.toString());

                            UpdateResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), "Server Timeout");
                gifImages.setVisibility(View.GONE);

            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void UpdateResponse(JSONObject response) {

        try {
            error = response.getBoolean("errorExists");
        } catch (JSONException e) {
            //  e.printStackTrace();
        }
        if (error) {
            try {
                errorMsg = response.getString("errorMessage");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            sf.put(getString(R.string.search_error_msg), errorMsg);
            sf.put(getString(R.string.is_search_error), error);
            communicateToFragment(false);
            getActivity().onBackPressed();
            //((MainActivity)getActivity()).onBackPressed();

            loader.dismiss();
        } else {

            try {
                sf.put(getString(R.string.is_search_error), error);
            } catch (Exception e) {

            }
            AppVariables.searchResult = false;

            mUtosearchresultHome = ParseManager.getInstance().fromJSON(response, UtosearchresultHome.class);

            LoadSearchResult(mUtosearchresultHome);

            List<IfsObject> ifs = mUtosearchresultHome.getIfsObject();
            boostPriorityData(ifs);
            AutoAccount autoAccount = mUtosearchresultHome.getAutoAccount();
            if (autoAccount != null && autoAccount.getLABLMAAccountCreatedAutoFillBoxLabel() != null) {
                lytBottom.setVisibility(View.VISIBLE);
                if (autoAccount.getJoinOTCheck()) {
                    lytEmailChbx.setVisibility(View.VISIBLE);
                    lytJoinOT.setVisibility(View.GONE);
                    txtEsoEmailLabel.setText(autoAccount.getLABLEmailShortLabel());
                    txtBesideCheckBox.setText(autoAccount.getLABLMAAccountCreatedAutoFillBoxLabel());
                } else {

                    lytEmailChbx.setVisibility(View.GONE);
                    lytJoinOT.setVisibility(View.VISIBLE);
                    txtEsoJoinOT.setText(autoAccount.getLABLMAAccountCreatedAutoFillBoxLabel());
                    txtEsoJoinOTExtra.setText(autoAccount.getSpecial_offer());
                    if (Utils.isUserLoggedIn(getActivity())) {
                        lytJoinOT.setVisibility(View.GONE);
                    }
                }
            }

            try {
                if (mUtosearchresultHome.getIsPassFlow()) {
                    lytBottom.setVisibility(View.GONE);
                }
            } catch (Exception e) {

            }

            loader.dismiss();
        }

}


    private void boostPriorityData(List<IfsObject> ifsObj) {
        Utils.setLegListData(getActivity(), ifsObj.toString());

    }

    public void LoadSearchResult(UtosearchresultHome mUtosearchresultHome) {

        if (mUtosearchresultHome != null) {
            mIfsObjects = mUtosearchresultHome.getIfsObject();
            gifImages.setVisibility(View.GONE);
            lytSearchResult.setVisibility(View.VISIBLE);

            try {
                if (mUtosearchresultHome.getShowContinueButton()) {
                    btnContinue.setVisibility(View.VISIBLE);
                    btnContinue.setText(mUtosearchresultHome.getProceedToPaymentNowLabel());
                } else {
                    btnContinue.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            mLinearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerViewSearchResult.setLayoutManager(mLinearLayoutManager);
            adapter = new UtoSearchResultRecyclerAdapter(getActivity(), mIfsObjects, mUtosearchresultHome, listener, chkListener,new UtoSearchResultRecyclerAdapter.IRecyclerViewHolderPaxSelectedListener(){

                @Override
                public void onPaxSelectedClick(View v, int ifsPos, int legPos, ArrayList<String> listSelectedPax) {
                    Utils.DEBUG("ifsPos : " + ifsPos + ", legPosition :"+legPos+" Selected pax : "+ listSelectedPax.toString());
                    mapSelectedPax.put(ifsPos+ "_" + legPos, listSelectedPax);


                }
            });
            recyclerViewSearchResult.setAdapter(adapter);
        }
    }


    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("LegProductSearchResultFragment >> onAttach(Activity) called");

        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    private void communicateToFragment(boolean isErrorOccur) {
        FragmentCommunicationData data = new FragmentCommunicationData();

        try {
            if (mSearchHelper != null) {
                if (mSearchHelper.getIsSearchBy().equalsIgnoreCase("2")) {
                    data.setFragmentName((new LegProductsCheckStatusFragment()).getClass().getName());
                } else {
                    data.setFragmentName((new LegProductSearchFragment()).getClass().getName());
                }
            } else {
                //dealing with upgrade pass
                data.setFragmentName(new PNRSearchInputFragment().getClass().getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        data.setSearchErrorMsg((String) sf.get(getString(R.string.search_error_msg)));
        data.setSearchError((boolean) sf.get(getString(R.string.is_search_error)));
        AppVariables.searchResult = true;

        try {
            communicator.onResponse(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateUIForBoostMyPriority(FragmentCommunicationData data) {
        boostPriorityData = data.getBoostMyPrioritySelectedData();
        Utils.DEBUG("Price : " + boostPriorityData.getSelectedPrice() + " index : " + boostPriorityData.getSelectedIndex());
        callBMPConfirmApi(boostPriorityData.getSelectedIndex());
    }

    private void callBMPConfirmApi(String selectedIndex) {
        String[] split = selectedIndex.split("/");


        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_BMP_CONFIRM)+"&P="+split[0]+"&C="+split[1]+"&L@@@@="+split[2];

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
                        Utils.DEBUG("BmpConfirm Response : " + response.toString());
                        UpdateResponse(response);

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

   /* private boolean doesViewGroupContainButton(ViewGroup parentView) {

        int numChildViews = parentView.getChildCount();
        for (int i = 0; i < numChildViews; i++) {
            View childView = parentView.getChildAt(i);
            if (childView instanceof ViewGroup) {
                if (doesViewGroupContainButton((ViewGroup) childView)) {
                    return true;
                }
            } else if (childView instanceof Button) {
                return true;
            }
        }
        return false;

    }*/

}

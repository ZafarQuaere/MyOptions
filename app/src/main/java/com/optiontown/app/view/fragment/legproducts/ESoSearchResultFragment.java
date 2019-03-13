package com.optiontown.app.view.fragment.legproducts;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
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
import com.optiontown.R;
import com.optiontown.app.adapter.ESoSearchResultRecyclerViewAdapter;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.legproducthomepage.SearchHelper;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by zafar.imam on 03-08-2016.
 */
public class ESoSearchResultFragment extends BaseFragment {

    private OTEditText edit_Email;
    private CheckBox chk_eso_joinOT;
    private View view;
    private String checkEmail;
    private GifImageView gifImages;
    private Button btnContinue;
    private AppDialogLoader loader;
    private LinearLayout lytBottom, lyt_recyleview, lytEmailChbx, lytJoinOT, lytError, lytErrorMessage;
    private OTTextView txt_eso_header, txt_eso_enjy_flatbed, txt_eso_viewDetails, txtEsoEmailLabel, txtEsoJoinOT, txtEsoJoinOTExtra, txtBesideCheckBox;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private boolean error;
    private Communicator communicator;
    private FragmentActivity fragmentActivity;
    private AppSharedPrefs sf;
    private String errorMsg = "";
    private SearchHelper mSearchHelper;
    private ArrayList<String> listChecked = new ArrayList<>();
    private boolean partialVisibility ;

    ESoSearchResultRecyclerViewAdapter.IRecyclerViewHolderClicks listener = new ESoSearchResultRecyclerViewAdapter.IRecyclerViewHolderClicks() {
        @Override
        public void onClickRecyclerItem(View v, int ifsObjectPos, int legPos, int cabinPos, Boolean isChecked,Boolean isPartialShown) {

            if(isChecked)
            {
                listChecked.add(ifsObjectPos + "_" + legPos + "_" + cabinPos);
            }
            else
            {
                listChecked.remove(ifsObjectPos + "_" + legPos + "_" + cabinPos);
            }

            if(!listChecked.isEmpty())
            {
                lytError.setVisibility(View.GONE);
            }
            partialVisibility = isPartialShown;

        }
    };

    ESoSearchResultRecyclerViewAdapter.IRecyclerViewHolderCheckChangeListerner chkListener = new ESoSearchResultRecyclerViewAdapter.IRecyclerViewHolderCheckChangeListerner() {
        @Override
        public void onCheckedChangeItem(View v, int ifsObjectPos, int legPos, int cabinPos, Boolean isChecked,Boolean isPartialShown) {

            if(isChecked)
            {
                listChecked.add(ifsObjectPos + "_" + legPos + "_" + cabinPos);
            }
            else
            {
                listChecked.remove(ifsObjectPos + "_" + legPos + "_" + cabinPos);
            }
            if(!listChecked.isEmpty())
            {
                lytError.setVisibility(View.GONE);
            }
            partialVisibility = isPartialShown;

        }
    };
    private PnrSearchInputData searchData;
    private HashMap<String, ArrayList<String>> mapSelectedPax = new HashMap<String, ArrayList<String>>();
    private UtosearchresultHome mUtosearchresultHome;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_eso_search_result, container, false);
        Init(view);
     //   loader = AppDialogLoader.getLoader(getActivity(),R.style.ProgressDialogTheme);
        loader = AppDialogLoader.getLoader(getActivity());
        sf = AppSharedPrefs.getInstance(getActivity());

        try {
            mSearchHelper = (SearchHelper) getArguments().get(getActivity().getString(R.string.key_serializable));
        } catch (Exception e) {

        }
        if (mSearchHelper != null) {
            SearchForLeg(mSearchHelper.getTgProductId(), mSearchHelper.getLanguageId(), mSearchHelper.getCountryId(),
                    mSearchHelper.getMarketingAirlineId(), mSearchHelper.getPnr(), mSearchHelper.getLastName(),
                    mSearchHelper.getIsSearchBy(), mSearchHelper.getOCN(), mSearchHelper.getArriveAirlineCode(),
                    mSearchHelper.getDepartAirlineCode(),
                    mSearchHelper.getArriveArptCode(), mSearchHelper.getDepartArptCode(), mSearchHelper.getDepartDate(),
                    mSearchHelper.getFlightNumber(), mSearchHelper.getCountryId(),
                    mSearchHelper.getMobileNumber(), mSearchHelper.getEmail(), mSearchHelper.getFirstName());
        }
        else {


            try {
                searchData = (PnrSearchInputData) getArguments().get(getActivity().getString(R.string.key_serializable));
            } catch (Exception e) {

            }
            if (searchData != null){

                Utils.DEBUG("PNr :::::: "+searchData.getPNR());
                SearchForLeg(Utils.getCurrentProductId(getActivity())+"", Utils.getUserSelectedLanguageId(getActivity())+"", Utils.getUserSelectedCountryId(getActivity())+"",
                        searchData.getPass_airline_id(), searchData.getPNR(), searchData.getLastName(), "1", searchData.getOCN(), searchData.getArriveAirlineCode(),
                        searchData.getDepartAirlineCode(),
                        searchData.getArriveArptCode(), searchData.getDepartArptCode(), searchData.getDepartDate(),
                        searchData.getFlightNumber(), Utils.getUserSelectedCountryId(getActivity())+"",
                        searchData.getMobileNumber(), searchData.getEmail(), searchData.getFirstName());

            }

        }
        txt_eso_viewDetails.setText(getString(R.string.string_view_details_underline));

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        txt_eso_viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new LegProductViewDetailsFragment(), null, 0);
            }
        });
       // SearchForLeg();


        return view;
    }

    private void Init(View view) {
        txt_eso_header = (OTTextView) view.findViewById(R.id.txt_eso_header);
        txt_eso_enjy_flatbed = (OTTextView) view.findViewById(R.id.txt_eso_enjy_flatbed);
        txt_eso_viewDetails = (OTTextView) view.findViewById(R.id.txt_eso_viewDetails);
        txtEsoEmailLabel = (OTTextView) view.findViewById(R.id.txtEsoEmailLabel);
        txtEsoJoinOT = (OTTextView) view.findViewById(R.id.txtEsoJoinOT);
        txtEsoJoinOTExtra = (OTTextView) view.findViewById(R.id.txtEsoJoinOTExtra);
        txtBesideCheckBox = (OTTextView) view.findViewById(R.id.txtBesideCheckBox);
        edit_Email = (OTEditText) view.findViewById(R.id.edit_Email);
        chk_eso_joinOT = (CheckBox) view.findViewById(R.id.chk_eso_joinOT);

        txt_eso_viewDetails.setPaintFlags(txt_eso_viewDetails.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
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
                if (isChecked){

                }
                else {
                    edit_Email.setText("");
                }
            }
        });
        gifImages = (GifImageView) view.findViewById(R.id.gifImages);
        lytBottom = (LinearLayout) view.findViewById(R.id.lytBottom);
        lyt_recyleview = (LinearLayout) view.findViewById(R.id.lyt_recyleview);
        lytJoinOT = (LinearLayout) view.findViewById(R.id.lytJoinOT);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        lytEmailChbx = (LinearLayout) view.findViewById(R.id.lytEmailChbx);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyViewEmptySeat);
        mRecyclerView.setHasFixedSize(true);
        btnContinue = (Button) view.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int index = 0; index < listChecked.size(); index++) {
                    Utils.DEBUG("selected checkboxs : " + listChecked.get(index).toString());
                }
                if (listChecked.isEmpty()){
                    lytError.setVisibility(View.VISIBLE);
                    lytErrorMessage.removeAllViews();
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(),getString(R.string.string_plz_select_one_option) ));

                }
                else if (partialVisibility && mapSelectedPax.isEmpty()){
                    lytError.setVisibility(View.VISIBLE);
                    lytErrorMessage.removeAllViews();
                    String partailErrorMsg = mUtosearchresultHome.getIfsObject().get(0).getLegListObj().get(0).getPartailErrorMsg();
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), partailErrorMsg));

                }
                else {
                    lytError.setVisibility(View.GONE);
                    checkEmail = edit_Email.getText().toString().trim();
                    boolean checkEsoJoinOt = chk_eso_joinOT.isChecked();
                    if (checkEsoJoinOt){
                        if (checkEmail.equalsIgnoreCase("")) {
                            edit_Email.requestFocus();
                            edit_Email.setError("enter email");
                        }
                        else if (!Utils.isValidEmail(checkEmail)){
                            edit_Email.requestFocus();
                            edit_Email.setError("enter valid email");
                        }else {
                            Utils.moveToFragment(getActivity(), new LegProductReviewFragment(), getEmailStatus(), 0);
                        }
                    }
                    else {
                        if(edit_Email.getText().toString().trim().length() > 0 && !Utils.isValidEmail(edit_Email.getText().toString()))
                        {
                            edit_Email.requestFocus();
                            edit_Email.setError("enter valid email");
                        }
                        else
                        {
                            Utils.moveToFragment(getActivity(), new LegProductReviewFragment(), getEmailStatus(), 0);
                        }

                    }

                }


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

    private void SearchForLeg(String productID, String LanguageId, String CountryId, String MarketingAirlineId,
                              String pnr, String lastName, String isSearchBy, String OCN,
                              String arriveArptId, String departArptId, String departArptCode, String arriveArptCode, String dateOfJourney,
                              String flightNumber, String countryCode, String number, String emailAddress, String fname) {

        String tag_json_obj = "json_obj_req";
        String url = null;
        if (isSearchBy.equalsIgnoreCase("0")) {
            url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_DetailPnrSearch);
        }
        if (isSearchBy.equalsIgnoreCase("1")) {
            if (Utils.getCurrentProductId(getActivity())== getActivity().getResources().getInteger(R.integer.value_tgProductId_esp)
                    || Utils.getCurrentProductId(getActivity())== getActivity().getResources().getInteger(R.integer.value_tgProductId_psp)){

                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_OpSearchItIn)+"&passId="+searchData.getPassId()+"&opID="+Utils.getCurrentProductId(getActivity());
            }
            else{
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.ITINERARY_SEARCH);
            }
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
            }
            if (isSearchBy.equalsIgnoreCase("1")) {
                if (Utils.getCurrentProductId(getActivity())== getActivity().getResources().getInteger(R.integer.value_tgProductId_esp)
                        || Utils.getCurrentProductId(getActivity())== getActivity().getResources().getInteger(R.integer.value_tgProductId_psp)){

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
                else {
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
                        Utils.DEBUG("EsoSearchResultResponse : " + response.toString());
                        JSONObject obj = null;
                        try {

                            obj = new JSONObject(response.toString());
                            error = obj.getBoolean("errorExists");
                            if (error){
                                errorMsg = obj.getString("errorMessage");
                               // Utils.showToast(getActivity(), obj.getString("errorMessage"));
                                sf.put(getString(R.string.search_error_msg), errorMsg);
                                sf.put(getString(R.string.is_search_error), error);
                                communicateToFragment(false);
                                getActivity().onBackPressed();
                                //((MainActivity)getActivity()).onBackPressed();
                                loader.dismiss();
                            }
                            else {
                                AppVariables.searchResult = false;
                                 mUtosearchresultHome = ParseManager.getInstance().fromJSON(response, UtosearchresultHome.class);
                                LoadSearchResult(mUtosearchresultHome);
                                List<IfsObject> ifs = mUtosearchresultHome.getIfsObject();
                                AutoAccount autoAccount = mUtosearchresultHome.getAutoAccount();
                                if (autoAccount.getLABLMAAccountCreatedAutoFillBoxLabel()!=null){
                                   lytBottom.setVisibility(View.VISIBLE);
                                    if (autoAccount.getJoinOTCheck()){
                                        lytEmailChbx.setVisibility(View.VISIBLE);
                                        lytJoinOT.setVisibility(View.GONE);
                                        txtEsoEmailLabel.setText(autoAccount.getLABLEmailShortLabel());
                                        txtBesideCheckBox.setText(autoAccount.getLABLMAAccountCreatedAutoFillBoxLabel());
                                    }
                                    else {
                                        lytEmailChbx.setVisibility(View.GONE);
                                        lytJoinOT.setVisibility(View.VISIBLE);
                                        txtEsoJoinOT.setText(autoAccount.getLABLMAAccountCreatedAutoFillBoxLabel());
                                        txtEsoJoinOTExtra.setText(autoAccount.getSpecial_offer());
                                        if (Utils.isUserLoggedIn(getActivity())) {
                                            lytJoinOT.setVisibility(View.GONE);
                                        }
                                    }
                                }
                                if(mUtosearchresultHome.getIsPassFlow())
                                {
                                    lytBottom.setVisibility(View.GONE);
                                }

                                loader.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private void LoadSearchResult(UtosearchresultHome mUtosearchresultHome) {
        gifImages.setVisibility(View.GONE);
        lyt_recyleview.setVisibility(View.VISIBLE);
        lytBottom.setVisibility(View.VISIBLE);
        txt_eso_viewDetails.setVisibility(View.VISIBLE);
        loader.dismiss();
        List<IfsObject> mIfsObjects = mUtosearchresultHome.getIfsObject();
        mRecyclerView.setVisibility(View.VISIBLE);
        if (mUtosearchresultHome.getShowContinueButton()) {
            btnContinue.setVisibility(View.VISIBLE);
            btnContinue.setText(mUtosearchresultHome.getProceedToPaymentNowLabel());
        } else {
            btnContinue.setVisibility(View.GONE);
        }

            txt_eso_header.setVisibility(mUtosearchresultHome.getIsPassFlow()?View.GONE:View.GONE);
        txt_eso_enjy_flatbed.setVisibility(mUtosearchresultHome.getIsPassFlow()?View.GONE:View.VISIBLE);

        mLinearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        adapter = new ESoSearchResultRecyclerViewAdapter(getActivity(), mIfsObjects, mUtosearchresultHome ,listener,chkListener,new ESoSearchResultRecyclerViewAdapter.IRecyclerViewHolderPaxSelectedListener(){

            @Override
            public void onPaxSelectedClick(View v, int ifsPos, int legPos, ArrayList<String> listSelectedPax) {
                Utils.DEBUG("ifsPos : " + ifsPos + ", legPosition :"+legPos+" Selected pax : "+ listSelectedPax.toString());
                mapSelectedPax.put(ifsPos+ "_" + legPos, listSelectedPax);


            }
        });
        mRecyclerView.setAdapter(adapter);
        txt_eso_header.setText(mIfsObjects.get(0).getProuductLabel());
        txt_eso_enjy_flatbed.setText(mIfsObjects.get(0).getProductHelpMobileLabel());
    }

    @Override
    public void onAttach(Activity activity) {
        Utils.DEBUG("LegProductSearchResultFragment >> onAttach(Activity) called");
        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }


    private void communicateToFragment(boolean isErrorOccur)
    {
        FragmentCommunicationData data = new FragmentCommunicationData();

        try {
            if (mSearchHelper.getIsSearchBy().equalsIgnoreCase("2")){
                data.setFragmentName((new LegProductsCheckStatusFragment()).getClass().getName());
            }
            else {
                data.setFragmentName((new LegProductSearchFragment()).getClass().getName());
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
}




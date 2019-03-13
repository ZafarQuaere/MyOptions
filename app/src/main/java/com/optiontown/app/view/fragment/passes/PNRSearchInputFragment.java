package com.optiontown.app.view.fragment.passes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.PNRSearchInputFragmentAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.ListOfPass;
import com.optiontown.app.model.utosearchresult.PassDropDownList;
import com.optiontown.app.model.utosearchresult.PnrSearchInputData;
import com.optiontown.app.model.utosearchresult.UToPnrInformation;
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
import com.optiontown.app.view.fragment.legproducts.ESoSearchResultFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductSearchResultFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 03/05/2017.
 */

public class PNRSearchInputFragment extends BaseFragment {

    private View view;
    private OTTextView txtHeadingWarningLabel;
    private OTTextView txtUpgradePassLabel;
    private OTTextView txtSelectYourPass;
    private OTTextView txtBookingLabel;
    private OTTextView txtBookingRef;
    private OTTextView txtBookUpgradeOnNewBooking;
    private OTTextView txtPNRLabel;
    private OTEditText edtPNR;
    private OTTextView txtBookUpgradeOnExistingBookingLabel;
    private OTTextView txtLastnameLabel;
    private OTEditText edtLastName;
    private OTTextView txtShowUpgradeOption;
    private InternationalizeData localization;
    private AppDialogLoader loader;
    private RecyclerView recylerPassList;
    private LinearLayout lytRecyclerView;
    private RecyclerView recylerBookingList;
    private LinearLayout lytRecyclerViewBooking;
    private static String updateRecyclerViewDataFor = "";
    private LinearLayout lytBooking;
    private LinearLayout lytPNRLastName;
    private LinearLayout lytError,lytErrorMessage;
    private RelativeLayout lyt_parent;
    private String airlineIdSelected;
    private int passIdSelected;
    private String pnrlistValue;
    private ListOfPass listOfPass;
    private UToPnrInformation utoPnrInformation;
    private AppSharedPrefs sharedPrefs;
    private LinearLayout lytWarningLabel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view  = inflater.inflate(R.layout.fragment_pnr_search_input, container, false);

        loader = AppDialogLoader.getLoader(getActivity());
        //initialise shared prefs manager
        //sp = AppSharedPrefs.getInstance(getActivity());

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        sharedPrefs = AppSharedPrefs.getInstance(getActivity());
        listOfPass = ((ListOfPass) getArguments().getSerializable(getString(R.string.key_serializable)));

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), listOfPass.getPassSmallView().getPassUtilityLabel(), null);

        initUi();

        callPnrListApi(listOfPass.getPass_transaction_id().toString());

        return view;
    }

    private void callPnrListApi(String passId) {


        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_OpredeemPassDrpDwn)+"&passId=" + passId +"&opID="+String.valueOf(Utils.getCurrentProductId(getActivity()));


        JSONObject requestObject = new JSONObject();
        try {
            //requestObject.put("LanguageID", Integer.toString(Utils.getUserSelectedLanguageId(this)));
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

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
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        utoPnrInformation = ParseManager.getInstance().fromJSON(response, UToPnrInformation.class);

                        if(utoPnrInformation != null)
                        {
                            updateUI(utoPnrInformation);
                            updateRecyclerViewDataFor = "listofpasses";
                            showDropDownRecyclerView(recylerPassList, utoPnrInformation, updateRecyclerViewDataFor);
                        }


                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.hide();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void updateUI(final UToPnrInformation utoPnrInformation) {

        //txtSelectYourPass.setText(utoPnrInformation.getPassDropDownList().get(0).getPasDisplayLevel());
        if(!utoPnrInformation.getPnrList().isEmpty())
        {
            txtBookingRef.setText(utoPnrInformation.getPnrList().get(0).getPnrlistValue());
        }
        txtHeadingWarningLabel.setText(utoPnrInformation.getErrUserAddLbl());
        txtBookUpgradeOnNewBooking.setText(utoPnrInformation.getBookEsoOnNewBooking());
        txtBookUpgradeOnExistingBookingLabel.setText(utoPnrInformation.getBookEsoOnExistingBooking());
        txtShowUpgradeOption.setText(utoPnrInformation.getShowRedeemButton());

        txtSelectYourPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateRecyclerViewDataFor = "listofpasses";
                lytRecyclerViewBooking.setVisibility(View.GONE);
                lytRecyclerView.setVisibility(lytRecyclerView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                showDropDownRecyclerView(recylerPassList, utoPnrInformation, updateRecyclerViewDataFor);

            }
        });

        txtBookingRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateRecyclerViewDataFor = "bookingpnr";
                lytRecyclerView.setVisibility(View.GONE);
                lytRecyclerViewBooking.setVisibility(lytRecyclerViewBooking.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                //showDropDownRecyclerView(recylerBookingList, passListData, updateRecyclerViewDataFor);
                showDropDownRecyclerView(recylerBookingList, utoPnrInformation, updateRecyclerViewDataFor);

            }
        });


        txtBookUpgradeOnNewBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytBooking.setVisibility(View.GONE);
                lytRecyclerView.setVisibility(View.GONE);
                lytRecyclerViewBooking.setVisibility(View.GONE);
                lytWarningLabel.setVisibility(View.VISIBLE);
                lytPNRLastName.setVisibility(View.VISIBLE);
            }
        });


        txtBookUpgradeOnExistingBookingLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytBooking.setVisibility(View.VISIBLE);
                lytPNRLastName.setVisibility(View.GONE);
                lytRecyclerView.setVisibility(View.GONE);
                lytRecyclerViewBooking.setVisibility(View.GONE);
                lytError.setVisibility(View.GONE);
                lytErrorMessage.removeAllViews();
                lytWarningLabel.setVisibility(View.GONE);

                if(utoPnrInformation.getPnrList().isEmpty())
                {
                    lytBooking.setVisibility(View.GONE);
                    lytPNRLastName.setVisibility(View.VISIBLE);
                }
            }
        });

        txtShowUpgradeOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytError.setVisibility(View.GONE);
                lytErrorMessage.removeAllViews();
                ArrayList<String> listError = validateAllInputs();
                if(listError.size() > 0)
                {
                    lytError.setVisibility(View.VISIBLE);
                    //show error message
                    for (int index = 0; index < listError.size(); index++)
                    {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }

                    lytError.requestFocus();
                }
                else
                {
                    PnrSearchInputData searchData = new PnrSearchInputData();
                    searchData.setPassId(String.valueOf(passIdSelected));
                    searchData.setPass_airline_id(listOfPass.getPass_airline_id().toString());

                    //searchData.setAirLineId(airlineIdSelected);


                    if(lytPNRLastName.getVisibility() == View.VISIBLE)
                    {
                        searchData.setLastName(edtLastName.getText().toString());
                        searchData.setPNR(edtPNR.getText().toString());
                    }else {

                        //searchData.setPNR(pnrlistValue);
                        searchData.setPNR(txtBookingRef.getText().toString());
                    }



                    if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_utp)))
                    {
                        Utils.moveToFragment(getActivity(), new LegProductSearchResultFragment(), searchData, 0);
                    }
                    else
                    {
                        Utils.moveToFragment(getActivity(),new ESoSearchResultFragment(), searchData, 0);
                    }
                }

            }
        });

        lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytRecyclerView.setVisibility(View.GONE);
                lytRecyclerViewBooking.setVisibility(View.GONE);

            }
        });
    }

    private void initUi() {

        lytWarningLabel = (LinearLayout) view.findViewById(R.id.lytWarningLabel);
        lyt_parent = (RelativeLayout) view.findViewById(R.id.lyt_parent);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        txtHeadingWarningLabel = (OTTextView) view.findViewById(R.id.txtHeadingWarningLabel);
        txtUpgradePassLabel = (OTTextView) view.findViewById(R.id.txtUpgradePassLabel);
        txtSelectYourPass = (OTTextView) view.findViewById(R.id.txtSelectYourPass);
        txtBookingLabel = (OTTextView) view.findViewById(R.id.txtBookingLabel);
        txtBookingRef = (OTTextView) view.findViewById(R.id.txtBookingRef);
        txtBookUpgradeOnNewBooking = (OTTextView) view.findViewById(R.id.txtBookUpgradeOnNewBooking);
        txtPNRLabel = (OTTextView) view.findViewById(R.id.txtPNRLabel);
        edtPNR = (OTEditText) view.findViewById(R.id.edtPNR);
        lytPNRLastName = (LinearLayout) view.findViewById(R.id.lytPNRLastName);
        txtBookUpgradeOnExistingBookingLabel = (OTTextView) view.findViewById(R.id.txtBookUpgradeOnExistingBookingLabel);
        txtLastnameLabel = (OTTextView) view.findViewById(R.id.txtLastnameLabel);
        edtLastName = (OTEditText) view.findViewById(R.id.edtLastName);
        txtShowUpgradeOption = (OTTextView) view.findViewById(R.id.txtShowUpgradeOption);
        lytPNRLastName = (LinearLayout) view.findViewById(R.id.lytPNRLastName);
        lytRecyclerView = (LinearLayout) view.findViewById(R.id.lytRecyclerView);
        recylerPassList = (RecyclerView) view.findViewById(R.id.recylerPassList);

        lytRecyclerViewBooking = (LinearLayout) view.findViewById(R.id.lytRecyclerViewBooking);
        recylerBookingList = (RecyclerView) view.findViewById(R.id.recylerBookingList);

        lytBooking = (LinearLayout) view.findViewById(R.id.lytBooking);
        txtBookUpgradeOnNewBooking = (OTTextView) view.findViewById(R.id.txtBookUpgradeOnNewBooking);

        lytWarningLabel.setVisibility(View.GONE);
        lytBooking.setVisibility(View.GONE);
        txtShowUpgradeOption.setVisibility(View.GONE);
        lytPNRLastName.setVisibility(View.GONE);

        localizeUi();

    }

    private ArrayList<String> validateAllInputs() {

        ArrayList<String> listError = new ArrayList<>();

        if(lytPNRLastName.getVisibility() == View.VISIBLE)
        {
            if(Utils.compareDefaultValues(edtLastName, ""))
            {
                listError.add(localization.getERRInvalidLastNameErrorMessage());
            }
            if(Utils.compareDefaultValues(edtPNR, ""))
            {
                listError.add(localization.getLABLPNRLabel());
            }
        }

        return listError;
    }

    private void showDropDownRecyclerView(RecyclerView recylerPassList, final UToPnrInformation utoPnrInformation, final String updateRecyclerViewDataFor) {

        if (utoPnrInformation != null) {
            NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
            recylerPassList.setLayoutManager(gridLayoutManager);

            if(updateRecyclerViewDataFor.equals("listofpasses"))
            {

                PNRSearchInputFragmentAdapter adapter = new PNRSearchInputFragmentAdapter(getActivity(), updateRecyclerViewDataFor,utoPnrInformation.getPassDropDownList(), new PNRSearchInputFragmentAdapter.RecyclerViewHolderClicks() {

                    @Override
                    public void onClickRecyclerItemDetail(View v, PassDropDownList passDropDownList) {

                        lytRecyclerView.setVisibility(View.GONE);
                        txtSelectYourPass.setText(passDropDownList.getPasDisplayLevel().toString());

                        //change airline id if needed
                        airlineIdSelected = listOfPass.getPass_airline_id();
                        passIdSelected = Integer.parseInt(passDropDownList.getPasDisplayValue());


                        if(!txtSelectYourPass.getText().toString().equals(utoPnrInformation.getPassDropDownList().get(0).getPasDisplayLevel()))
                        {
                            showDropDownRecyclerView(recylerBookingList, utoPnrInformation,"bookingpnr");

                        }else {

                            lytWarningLabel.setVisibility(View.GONE);
                            lytBooking.setVisibility(View.GONE);
                            lytPNRLastName.setVisibility(View.GONE);
                            txtShowUpgradeOption.setVisibility(View.GONE);

                        }

                        callPnrListApi(String.valueOf(passIdSelected));
                    }
                });
            /*recylerPassList.setHasFixedSize(true);*/
                recylerPassList.setAdapter(adapter);
            }else {

                if(utoPnrInformation.getPnrList().isEmpty())
                {
                    lytBooking.setVisibility(View.GONE);
                    lytPNRLastName.setVisibility(View.VISIBLE);
                    lytRecyclerView.setVisibility(View.GONE);
                    lytRecyclerViewBooking.setVisibility(View.GONE);
                    txtShowUpgradeOption.setVisibility(View.VISIBLE);
                    return;
                }

                if(lytPNRLastName.getVisibility() == View.VISIBLE)
                {
                    lytBooking.setVisibility(View.GONE);
                }else {
                    lytBooking.setVisibility(View.VISIBLE);
                }

                txtShowUpgradeOption.setVisibility(View.VISIBLE);

                if( utoPnrInformation.getPnrList().size() > 0)
                {
                    txtBookingRef.setText(utoPnrInformation.getPnrList().get(0).getPnrlistValue());
                    PNRSearchInputFragmentAdapter adapterBooking = new PNRSearchInputFragmentAdapter(getActivity(),updateRecyclerViewDataFor, utoPnrInformation.getPnrList(), new PNRSearchInputFragmentAdapter.RecyclerViewHolderClicksBooking() {

                        @Override
                        public void onClickRecyclerItemBookingDetail(View v, String selected) {

                            lytRecyclerViewBooking.setVisibility(View.GONE);
                            lytRecyclerView.setVisibility(View.GONE);
                            txtBookingRef.setText(selected);
                            pnrlistValue = selected;

                        }
                    });
            /*recylerBookingList.setHasFixedSize(true);*/
                    recylerPassList.setAdapter(adapterBooking);
                }


            }
        }

        return;

    }

    private void localizeUi() {

        txtBookingLabel.setText(localization.getBooking_Label());
        txtUpgradePassLabel.setText(listOfPass.getPassSmallView().getPassUtilityLabel());
        txtSelectYourPass.setText(localization.getLABL_Select_Your_Pass_Label());
        txtPNRLabel.setText(localization.getLABLPNRLabel());
        edtPNR.setHint(localization.getLABLPNRLabel());
        txtLastnameLabel.setText(localization.getLABLLastNameLabel());
        edtLastName.setHint(localization.getLABLLastNameLabel());

    }

    @Override
    public void onFocusEvent() {
        if (getActivity() == null)
            return;

        boolean searchError;
        try {
            searchError = (Boolean) sharedPrefs.get(getString(R.string.is_search_error));
        } catch (Exception e) {
            e.printStackTrace();
            searchError = false;
        }
        if (searchError && AppVariables.searchResult) {
            lytError.setVisibility(View.VISIBLE);
            lytErrorMessage.removeAllViews();

            String errorMsg = (String) sharedPrefs.get(getString(R.string.search_error_msg));
            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), errorMsg));
            AppVariables.searchResult = false;
            //  Snackbar.make((RelativeLayout) view.findViewById(R.id.relSearchLayout), error,Snackbar.LENGTH_SHORT).show();
        } else {
            // lytError.setVisibility(View.GONE);
        }


    }
}

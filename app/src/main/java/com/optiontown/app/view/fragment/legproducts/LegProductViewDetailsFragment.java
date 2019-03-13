package com.optiontown.app.view.fragment.legproducts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.ViewDetailsMyTransactionsRecyclerViewAdapter;
import com.optiontown.app.adapter.ViewDetailsOutboundRecyclerViewAdapter;
import com.optiontown.app.adapter.ViewDetailsReturnRecyclerViewAdapter;
import com.optiontown.app.model.legreview.ReviewDataSend;
import com.optiontown.app.model.legviewdetails.PassengersFullList;
import com.optiontown.app.model.legviewdetails.Rule;
import com.optiontown.app.model.legviewdetails.TransactionList;
import com.optiontown.app.model.legviewdetails.UtoRules;
import com.optiontown.app.model.legviewdetails.ViewDetails;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.customview.OTTextViewHtml;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zafar.imam on 05-08-2016.
 */
public class LegProductViewDetailsFragment extends BaseFragment {
    View view;
    RecyclerView recycleViewOutbound, recycleViewMyTransaction, recycleViewReturn;
    ViewDetailsMyTransactionsRecyclerViewAdapter adapter;
    ViewDetailsOutboundRecyclerViewAdapter outboundAdapter;
    ViewDetailsReturnRecyclerViewAdapter returnAdapter;
    OTTextViewHtml txtUToRules;
    AppDialogLoader loader;
    LinearLayout lytMyTransaction, lytRules;
    RelativeLayout rlytPassenger, rlytAirline, rlytBookingReference, rlytOTConfirmation, rlytEmail, rlytCellMobile, rlyOtherPhone;
    private ScrollView lytMain;
    private OTTextView txtAirlineLabel, txtAirline, txtBookingRef, txtBookingRefLabel, txtOTConfirmationLable, txtOTConfirmation, txtContactEmailLabel, txtContactEmail, txtCellMobileLabel,
            txtCellMobile, txtOtherPhoneLabel, txtOtherPhone, txtPassengersLabel, txtPassengers, txtOutbound, txtReturn, txtMyTransactionLabel, txtUToRulesLabel;
    private LinearLayoutManager mLinearLayoutManager;
    private AppSharedPrefs sp;
    private boolean isConfirmPage = false;
    private ReviewDataSend sendData;
    private String email;
    private String primaryCode;
    private String othercode;
    private String primaryMainPart;
    private String otherMainPart;
    private String CardCategoryId;
    private String express;
    private String isBankAccount;
    private String totalAmountToPay;
    private String selectedKey;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.uto_search_result_view_details, container, false);
        initUI(view);
        //Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        String rulesShortName = rulesShortName(AppVariables.ProductName);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "Your " + rulesShortName + " Summary", null);
        loader = AppDialogLoader.getLoader(getActivity());
        sp = AppSharedPrefs.getInstance(getActivity());


        try {
            sendData = ((ReviewDataSend) getArguments().getSerializable(getString(R.string.key_serializable)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sendData != null) {
            email = sendData.getEmail();
            primaryCode = sendData.getPrimaryCode();
            othercode = sendData.getOthercode();
            primaryMainPart = sendData.getPrimaryMainPart();
            otherMainPart = sendData.getOtherMainPart();
           /* CardCategoryId=sendData.getCardCategoryId();
            express=sendData.getExpress();
            isBankAccount= sendData.getIsBankAccount();*/

            isConfirmPage = sendData.isConfirmPage();
        }


        CallViewDetails();
        return view;
    }

    private void initUI(View view) {
        lytMain = (ScrollView) view.findViewById(R.id.lytMain);
        lytMain.setVisibility(View.GONE);
        recycleViewOutbound = (RecyclerView) view.findViewById(R.id.recycleViewOutbound);
        recycleViewMyTransaction = (RecyclerView) view.findViewById(R.id.recycleViewMyTransaction);
        recycleViewReturn = (RecyclerView) view.findViewById(R.id.recycleViewReturn);

        txtAirlineLabel = (OTTextView) view.findViewById(R.id.txtAirlineLabela);
        txtAirline = (OTTextView) view.findViewById(R.id.txtAirline);
        txtBookingRef = (OTTextView) view.findViewById(R.id.txtBookingRef);
        txtBookingRefLabel = (OTTextView) view.findViewById(R.id.txtBookingRefLabel);
        txtOTConfirmationLable = (OTTextView) view.findViewById(R.id.txtOTConfirmationLable);
        txtOTConfirmation = (OTTextView) view.findViewById(R.id.txtOTConfirmation);
        txtContactEmailLabel = (OTTextView) view.findViewById(R.id.txtContactEmailLabel);
        txtContactEmail = (OTTextView) view.findViewById(R.id.txtContactEmail);
        txtCellMobileLabel = (OTTextView) view.findViewById(R.id.txtCellMobileLabel);
        txtCellMobile = (OTTextView) view.findViewById(R.id.txtCellMobile);
        txtOtherPhoneLabel = (OTTextView) view.findViewById(R.id.txtOtherPhoneLabel);
        txtOtherPhone = (OTTextView) view.findViewById(R.id.txtOtherPhone);
        txtPassengersLabel = (OTTextView) view.findViewById(R.id.txtPassengersLabel);
        txtPassengers = (OTTextView) view.findViewById(R.id.txtPassengers);
        txtOutbound = (OTTextView) view.findViewById(R.id.txtOutbound);
        txtReturn = (OTTextView) view.findViewById(R.id.txtReturn);
        txtMyTransactionLabel = (OTTextView) view.findViewById(R.id.txtMyTransactionLabel);
        txtUToRulesLabel = (OTTextView) view.findViewById(R.id.txtUToRulesLabel);
        txtUToRules = (OTTextViewHtml) view.findViewById(R.id.txtUToRules);
        lytMyTransaction = (LinearLayout) view.findViewById(R.id.lytMyTransaction);
        lytRules = (LinearLayout) view.findViewById(R.id.lytRules);

        rlytOTConfirmation = (RelativeLayout) view.findViewById(R.id.rlytOTConfirmation);
        rlytEmail = (RelativeLayout) view.findViewById(R.id.rlytEmail);
        rlytCellMobile = (RelativeLayout) view.findViewById(R.id.rlytCellMobile);
        rlyOtherPhone = (RelativeLayout) view.findViewById(R.id.rlyOtherPhone);



       /* mLinearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleViewOutbound.setLayoutManager(mLinearLayoutManager);
        outboundAdapter = new ViewDetailsOutboundRecyclerViewAdapter(getActivity(), mBenefits, new LegProductViewDetailsFragment());
        recycleViewOutbound.setAdapter(outboundAdapter);

        mLinearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleViewReturn.setLayoutManager(mLinearLayoutManager);
        returnAdapter = new ViewDetailsReturnRecyclerViewAdapter(getActivity(), mBenefits, new LegProductViewDetailsFragment());
        recycleViewReturn.setAdapter(outboundAdapter);*/
    }


    private void CallViewDetails() {
        loader.show();
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST);

        if (isConfirmPage) {
            url = url + getString(R.string.URL_opConfirm);
        } else {
            url = url + getString(R.string.URL_ItineraryViewDetail);
        }

        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("email",email);
            requestObject.put("primaryCountryCode",primaryCode);
            requestObject.put("primaryMainPart",primaryMainPart);
            requestObject.put("otherCode",othercode);
            requestObject.put("otherMainPart",otherMainPart);

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
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        ViewDetails mViewDetails = ParseManager.getInstance().fromJSON(response, ViewDetails.class);
                        headersData(mViewDetails);
                        lytMain.setVisibility(View.VISIBLE);
                        if (mViewDetails.getTransactionList().size() == 0) {
                            lytMyTransaction.setVisibility(View.GONE);
                        } else {
                            lytMyTransaction.setVisibility(View.VISIBLE);
                            txtMyTransactionLabel.setText(mViewDetails.getTransactionHistoryHeading());
                            transactionData(mViewDetails.getTransactionList());
                        }
                        ArrayList<PassengersFullList> passengerList = (ArrayList<PassengersFullList>) mViewDetails.getPassengersFullList();
                        passengers(passengerList);

                        UtoRules utoRules = mViewDetails.getUtoRules();
                        utoRulesData(utoRules);


                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), "Server Timeout");


            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    private void transactionData(ArrayList<TransactionList> transactionList) {
      /*  for (int i = 0; i < transactionList.size(); i++) {
            TransactionList transaction = new TransactionList();
            transaction.setAirlineCode(transactionList.get(i).getAirlineCode());
            transaction.s
            transaction.setImageURL("https://www.optiontown.com/images/alt/UTo_Benefits_Small_Image_1.jpg");
            transaction.setId(i);
            transactionList.add(transaction);
        }*/
        mLinearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleViewMyTransaction.setLayoutManager(mLinearLayoutManager);
        adapter = new ViewDetailsMyTransactionsRecyclerViewAdapter(getActivity(), transactionList);
        recycleViewMyTransaction.setAdapter(adapter);

    }

    private void headersData(ViewDetails mViewDetails) {

        txtAirlineLabel.setText(mViewDetails.getAirline());
        txtAirline.setText(mViewDetails.getMarketingAirline());
        txtBookingRef.setText(mViewDetails.getPnr());
        txtBookingRefLabel.setText(mViewDetails.getPnrLabel());
        if (mViewDetails.getConfirmationNumber() != null) {
            txtOTConfirmation.setText(mViewDetails.getConfirmationNumber());
            txtOTConfirmationLable.setText(mViewDetails.getConfirmationLabel());
        } else {
            rlytOTConfirmation.setVisibility(View.GONE);
        }

        if (!mViewDetails.getFormattedPhoneNumber().equals("")) {
            txtCellMobileLabel.setText(mViewDetails.getPrimaryPhoneLabel());
            txtCellMobile.setText(mViewDetails.getFormattedPhoneNumber());
        } else {
            rlytCellMobile.setVisibility(View.GONE);
        }
        if (!mViewDetails.getContactEmail().equals("")) {
            txtContactEmailLabel.setText(mViewDetails.getLABLContactEmailLabel());
            txtContactEmail.setText(mViewDetails.getContactEmail().equals("null") ? "" : mViewDetails.getContactEmail());
        } else {
            rlytEmail.setVisibility(View.GONE);
        }
        if (!mViewDetails.getFormattedOtherPhoneNumber().equals("")) {
            txtOtherPhoneLabel.setText(mViewDetails.getOtherPhoneLabel());
            txtOtherPhone.setText(mViewDetails.getFormattedOtherPhoneNumber());
        } else {
            rlyOtherPhone.setVisibility(View.GONE);
        }
        txtPassengersLabel.setText(mViewDetails.getPassengersLabel());


    }

    private void utoRulesData(UtoRules utoRules) {
        String rules = "";
        String mrules = "";
        String nRules = "";
        List<Rule> ruleList = utoRules.getRules();

        String title = null;
        if (!ruleList.isEmpty()) {
            title = ruleList.get(0).getTitle();
        }
        txtUToRulesLabel.setText(title);
        ArrayList<String> arrayList = new ArrayList<>();


        try {
            for (int i = 1; i < ruleList.size(); i++) {
                rules = ruleList.get(i).getRule();
                nRules = rules + "<br />";
                String split[] = nRules.split("##");

                StringBuilder b = new StringBuilder();
                for (int index = 0; index < split.length; index++) {
                    if (index != 0) {
                        b.append("<br />");
                        b.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>" + split[index].substring(0, 2) + "</b>" + split[index].substring(2, split[index].length()));
                    } else {
                        b.append(split[index]);
                    }

                }
                mrules = b.toString();
                //mrules = nRules.replace("##", "<br />");
                //mrules = "<b>" + mrules.substring(0, 2) + "</b>" + mrules.substring(2, mrules.length());
                //nUtoRules =  utoRules.replace("##","\n\t\t");
                arrayList.add(mrules);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Utils.layoutForRules(getActivity(), lytRules, arrayList);

    }

    /*private void utoRulesData(UtoRules mUtoRules) {
        String utoRules = "";
        String title = "";
        String nUtoRules = "";
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(mUtoRules);
            JSONArray array = json.getJSONArray("Rules");
            JSONObject obj = array.getJSONObject(0);
            title = obj.getString("Title");
            for (int i = 1; i < array.length();i++){
                JSONObject obj1 = array.getJSONObject(i);
                utoRules =utoRules+obj1.getString(i+"")+"\n";
                nUtoRules =  utoRules.replace("##","\n\t\t");
                arrayList.add(nUtoRules);
            }

            Utils.layoutForRules(getActivity(), lytRules,  arrayList);
            LegRules rules = new LegRules();
            rules.setRules(arrayList);
            rules.setTitle(title);
        } catch (JSONException e) {
            e.printStackTrace();
        }

      //  txtUToRules.setText(nUtoRules);

       txtUToRulesLabel.setText(title);

      //  txtUToRules.setHtml(mUtoRules);
    }*/

    private void passengers(ArrayList<PassengersFullList> passengerList) {
        String pass = " ";
        for (int i = 0; i < passengerList.size(); i++) {
            if (i ==0 ){
                pass =  passengerList.get(i).getPaxName();
            }
            pass = pass + "\n" + passengerList.get(i).getPaxName();
        }
        txtPassengers.setText(pass);
    }

    private String rulesShortName(String productName) {

        if (productName.equalsIgnoreCase("Empty Seat ")) {
            return "ESo";
        } else if (productName.equalsIgnoreCase("Upgrade")) {
            return "UTo";
        } else if (productName.equalsIgnoreCase("Preferred Seat")) {
            return "PSo";
        } else if (productName.equalsIgnoreCase("EXtra Baggage")) {
            return "XBo";
        } else if (productName.equalsIgnoreCase("Lounge Access")) {
            return "LAo";
        } else if (productName.equalsIgnoreCase("Flexible Rewards")) {
            return "FRo";
        } else if (productName.equalsIgnoreCase("Priority Handling")) {
            return "PHo";
        } else if (productName.equalsIgnoreCase("Upgrade Pass")) {
            return "Up";
        } else if (productName.equalsIgnoreCase("Empty Seat Pass")) {
            return "ESp";
        } else if (productName.equalsIgnoreCase("Preferred Seat Pass")) {
            return "PSp";
        }
        return "";
    }
}

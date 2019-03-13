package com.optiontown.app.view.fragment.fpo.redeem;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.RedeemSearchResultRecyclerViewAdapter;
import com.optiontown.app.model.redeem.Itinerarry;
import com.optiontown.app.model.redeem.RedeemSearchResultData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.login.TermsServicePolicyFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.optiontown.app.utils.Utils.DEBUG;

/**
 * Created by amit on 28-09-2016.
 */
public class RedeemReviewBookFragment extends BaseFragment
{
    private RedeemSearchResultData redeemSearchResultData;
    private View view;
    private LinearLayout lytPassengersName;
    private OTTextView txtPassengersLabel;
    private RecyclerView recyclerView;
    private OTTextView txtConfirm;
    private CheckBox cbAgree;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private TextView txt_ckeck_termsofservive;
    private LinearLayout lytInstallment;
    private OTTextView txtInstallmentTitle;
    private OTTextView txtTotalPassPriceLabel;
    private OTTextView txtTotalPassPrice;
    private OTTextView txtTotalAmountPaidTillDateLabel;
    private OTTextView txtTotalAmountPaidTillDate;
    private OTTextView txtInstallmentAmountToPayNowLabel;
    private OTTextView txtInstallmentAmountToPayNow;
    private OTTextView txtInformationLabel;

    private LinearLayout lytCorporateClient;
    private RelativeLayout rlytCorpClientDetail;
    private OTTextView txtCorporateClientShow;
    private OTTextView txtCorpClientDetail;
    private OTTextView txtCorpClientHide;
    private OTTextView txtTourCodeLabel;
    private OTTextView txtRegistrationNumberLabel;
    private OTEditText edtTourCode;
    private OTEditText edtRegistrationNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Utils.DEBUG("onCreateView called");
        view = inflater.inflate(R.layout.fragment_redeem_review_book, container, false);

        redeemSearchResultData = (RedeemSearchResultData) getArguments().getSerializable(getString(R.string.key_serializable));

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), redeemSearchResultData.getBook_Label(), null);
        getUIReference();
        updateUI();
        return view;
    }

    @Override
    public void onBackEventPost() {
        super.onBackEventPost();

        Utils.clearBackstackTillRedeemSearch(getActivity());
        Utils.DEBUG("onBackEventPost() called");
    }

    private void getUIReference()
    {
        txt_ckeck_termsofservive = (TextView) view.findViewById(R.id.txt_ckeck_termsofservive);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        cbAgree = (CheckBox) view.findViewById(R.id.cbAgree);
        lytPassengersName = (LinearLayout) view.findViewById(R.id.lytPassengersName);
        txtPassengersLabel = (OTTextView) view.findViewById(R.id.txtPassengersLabel);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        lytInstallment = (LinearLayout) view.findViewById(R.id.lytInstallment);
        txtInstallmentTitle = (OTTextView) view.findViewById(R.id.txtInstallmentTitle);
        txtTotalPassPriceLabel = (OTTextView) view.findViewById(R.id.txtTotalPassPriceLabel);
        txtTotalPassPrice = (OTTextView) view.findViewById(R.id.txtTotalPassPrice);
        txtTotalAmountPaidTillDateLabel = (OTTextView) view.findViewById(R.id.txtTotalAmountPaidTillDateLabel);
        txtTotalAmountPaidTillDate = (OTTextView) view.findViewById(R.id.txtTotalAmountPaidTillDate);
        txtInstallmentAmountToPayNowLabel = (OTTextView) view.findViewById(R.id.txtInstallmentAmountToPayNowLabel);
        txtInstallmentAmountToPayNow = (OTTextView) view.findViewById(R.id.txtInstallmentAmountToPayNow);
        txtInformationLabel = (OTTextView) view.findViewById(R.id.txtInformationLabel);


        lytCorporateClient = (LinearLayout) view.findViewById(R.id.lytCorporateClient);
        rlytCorpClientDetail = (RelativeLayout) view.findViewById(R.id.rlytCorpClientDetail);
        txtCorporateClientShow = (OTTextView) view.findViewById(R.id.txtCorporateClientShow);
        txtCorpClientDetail = (OTTextView) view.findViewById(R.id.txtCorpClientDetail);
        txtCorpClientHide = (OTTextView) view.findViewById(R.id.txtCorpClientHide);
        txtTourCodeLabel = (OTTextView) view.findViewById(R.id.txtTourCodeLabel);
        txtRegistrationNumberLabel = (OTTextView) view.findViewById(R.id.txtRegistrationNumberLabel);
        edtTourCode = (OTEditText) view.findViewById(R.id.edtTourCode);
        edtRegistrationNumber = (OTEditText) view.findViewById(R.id.edtRegistrationNumber);

        txtCorporateClientShow.setPaintFlags(txtCorporateClientShow.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtCorpClientHide.setPaintFlags(txtCorpClientHide.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtCorporateClientShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlytCorpClientDetail.setVisibility(View.VISIBLE);
                txtCorporateClientShow.setVisibility(View.GONE);
            }
        });
        txtCorpClientHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlytCorpClientDetail.setVisibility(View.GONE);
                txtCorporateClientShow.setVisibility(View.VISIBLE);
            }
        });


        txtConfirm = (OTTextView) view.findViewById(R.id.txtConfirm);
        txtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytError.setVisibility(View.GONE);
                lytErrorMessage.removeAllViews();

                if(!redeemSearchResultData.isEMI_Flag())
                {
                    if(cbAgree.isChecked())
                    {
                        callGoingToBookAPI();
                    }
                    else
                    {
                        //show error
                        lytError.setVisibility(View.VISIBLE);
                        lytErrorMessage.removeAllViews();
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), "Please accept terms of service."));
                    }
                }
                else
                {
                    Utils.moveToFragment(getActivity(), new RedeemEMIPaymentFragment(), null, 0);
                }
            }
        });
    }

    private void callGoingToBookAPI()
    {
        String tag_json_obj = "json_obj_req";

        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_GoingToBookFlight)
                + "&isExpressCheckout=0";

        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("Agree", true);

            if(redeemSearchResultData.getIata_Display() == 1)
            {
                requestObject.put("iata_Tour_Code", edtTourCode.getText().toString());
                requestObject.put("iata_registration", edtRegistrationNumber.getText().toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            return;
                        }
                        DEBUG("onResponse() called : " + response.toString());
                        redeemSearchResultData = ParseManager.getInstance().fromJSON(response, RedeemSearchResultData.class);

                        if(redeemSearchResultData.getBooking_Purchase_Page().equals("1"))
                        {
                            lytError.setVisibility(View.VISIBLE);
                            lytErrorMessage.removeAllViews();
                            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), redeemSearchResultData.getBooking_Process_Message()));
                        }
                        else
                        {
                            lytError.setVisibility(View.GONE);
                            lytErrorMessage.removeAllViews();
                            Utils.moveToFragment(getActivity(), new RedeemConfirmBookFragment(), redeemSearchResultData, 0);
                        }


                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void updateUI()
    {
        txtPassengersLabel.setText(redeemSearchResultData.getPassengers_Label());
        txtConfirm.setText(redeemSearchResultData.isEMI_Flag() ? redeemSearchResultData.getProceed_To_Payment_Label() : redeemSearchResultData.getConform_Label());
        cbAgree.setVisibility(redeemSearchResultData.isEMI_Flag() ? View.GONE : View.VISIBLE);
        txt_ckeck_termsofservive.setVisibility(redeemSearchResultData.isEMI_Flag() ? View.GONE : View.VISIBLE);
        lytInstallment.setVisibility(redeemSearchResultData.isEMI_Flag() ? View.VISIBLE : View.GONE);
        if(redeemSearchResultData.isEMI_Flag())
        {
            txtInstallmentTitle.setText(redeemSearchResultData.getPayment_Installment_Label());
            txtTotalPassPriceLabel.setText(redeemSearchResultData.getPass_Total_Price_Label());
            txtTotalPassPrice.setText(redeemSearchResultData.getCurrency_Code() + " " +  redeemSearchResultData.getAgreed_Price());
            txtTotalAmountPaidTillDateLabel.setText(redeemSearchResultData.getTotal_Paid_Disc_Label());
            txtTotalAmountPaidTillDate.setText(redeemSearchResultData.getCurrency_Code() + " " +  redeemSearchResultData.getBook_Amount_Paid());
            txtInstallmentAmountToPayNowLabel.setText(redeemSearchResultData.getInstallment_To_Pay_Label());
            txtInstallmentAmountToPayNow.setText(redeemSearchResultData.getCurrency_Code() + " " +  redeemSearchResultData.getBookFlight_TaxToPay());
            txtInformationLabel.setText(redeemSearchResultData.getInstallment_To_Pay_Disc_Label());
        }
        else {
            //handle two type of data
            //1st type >> 'texttext##url1##texttext'
            //2nd type >> 'texttext##url1##texttext##url2##texttext'

            String strAgree = redeemSearchResultData.getAgree_Check();
            final String[] data = strAgree.trim().split("##");

            SpannableStringBuilder builder = new SpannableStringBuilder();

            for (int index = 0; index < data.length; index++) {
                if(index % 2 == 1)
                {
                    SpannableString ss = new SpannableString(data[index]);
                    final int finalIndex = index;
                    ClickableSpan span1 = new ClickableSpan() {
                        @Override
                        public void onClick(View textView) {
                            if(data.length == 5)//2nd type
                            {
                                if(finalIndex == 1)
                                {
                                    Utils.moveToFragment(getActivity(), new TermsServicePolicyFragment(),  getString(R.string.URL_TGP_RULES), 0);
                                }
                                else
                                {
                                    Utils.moveToFragment(getActivity(), new TermsServicePolicyFragment(),  getString(R.string.URL_METHOD_TERMS_OF_SERVICE), 0);
                                }
                            }
                            else
                            {
                                Utils.moveToFragment(getActivity(), new TermsServicePolicyFragment(),  getString(R.string.URL_METHOD_TERMS_OF_SERVICE), 0);
                            }
                        }
                    };
                    ss.setSpan(span1, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new ForegroundColorSpan(Color.BLUE), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.append(ss);
                }
                else
                {
                    SpannableString ss= new SpannableString(data[index]);
                    ss.setSpan(new ForegroundColorSpan(Color.GRAY), 0, ss.length(), 0);
                    builder.append(ss);
                }

            }
            txt_ckeck_termsofservive.setText(builder);
            txt_ckeck_termsofservive.setMovementMethod(LinkMovementMethod.getInstance());
        }






        txtConfirm.setText(redeemSearchResultData.getConform_Label());
        txtConfirm.setMovementMethod(LinkMovementMethod.getInstance());
        txtConfirm.setHighlightColor(Color.TRANSPARENT);

        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);


        RedeemSearchResultRecyclerViewAdapter adapter = new RedeemSearchResultRecyclerViewAdapter(this.getClass().getName() ,getActivity(), null, 0, redeemSearchResultData, new RedeemSearchResultRecyclerViewAdapter.IRecyclerViewHolderClicks() {

            @Override
            public void onClickRecyclerItemSelect(View v, List<Itinerarry> listItinary)
            {

            }

            @Override
            public void onClickRecyclerItemDetail(View v, Itinerarry itinary)
            {

            }
        });
        recyclerView.setAdapter(adapter);

        //
        ArrayList<String> usersDisplayNameList = redeemSearchResultData.getUsersDisplayNameList();
        for (int index = 0; index < usersDisplayNameList.size(); index++) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_redeem_add_passenger_row, null, false);

            ImageView imgUserIcon = (ImageView) v.findViewById(R.id.imgUserIcon);
            imgUserIcon.setBackgroundResource(R.drawable.settings);
            OTTextView txtName = (OTTextView) v.findViewById(R.id.txtName);
            txtName.setText(usersDisplayNameList.get(index));
            ImageView imgCheck = (ImageView) v.findViewById(R.id.imgCheck);
            imgCheck.setVisibility(View.GONE);


            lytPassengersName.addView(v);
        }


        //
        if(redeemSearchResultData.getIata_Display() == 1)
        {
            txtCorporateClientShow.setText(redeemSearchResultData.getIata_Link_Label());
            txtCorpClientDetail.setText(redeemSearchResultData.getIata_Link_Label());
            txtCorpClientHide.setText(redeemSearchResultData.getIata_Hide_Label());
            txtTourCodeLabel.setText(redeemSearchResultData.getIata_Tour_Code_Label());
            txtRegistrationNumberLabel.setText(redeemSearchResultData.getIata_registration());

            edtTourCode.setText(redeemSearchResultData.getIata_Tour_Code());
            edtRegistrationNumber.setText(redeemSearchResultData.getIata_Registration_Code());

            edtTourCode.setEnabled(redeemSearchResultData.getIata_Tour_Code().equals("") ? true : false);
            edtRegistrationNumber.setEnabled(redeemSearchResultData.getIata_Registration_Code().equals("") ? true : false);

            lytCorporateClient.setVisibility(View.VISIBLE);

        }
    }
}

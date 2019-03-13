package com.optiontown.app.view.fragment.fpo.redeem.mmp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.mmp.CountryList;
import com.optiontown.app.model.redeem.mmp.FlightsList;
import com.optiontown.app.model.redeem.mmp.InvoiceData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.InterceptedLinearLayout;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zafar.imam on 11-10-2016.
 */
public class MMPInvoiceFragment extends BaseFragment {

    private View view,viewInvoiceSent;
    private OTTextView txtMandatoryFieldLabel;
    private OTTextView txtBillingAddressLabel;
    private OTTextView txtContinue;
    private OTTextView txtCountry;
    private OTTextView txtCountryLabel;
    private OTTextView txtInvoiceInformation;
    private OTTextView txtInvoiceSentDateLabel;
    private OTTextView txtInvoiceSentDate;
    private OTEditText editEmail;
    private OTEditText editNameOnInvoice;
    private OTEditText editAddress1;
    private OTEditText editAddress2;
    private OTEditText editCity;
    private OTEditText editState;
    private OTEditText editZipPostal;
    private NestedScrollView svCountry;
    private InterceptedLinearLayout lytCountryScroll;
    private TextInputLayout tilEmail;
    private TextInputLayout tilNameOnInvoice;
    private TextInputLayout tilAddress1;
    private TextInputLayout tilAddress2;
    private TextInputLayout tilCity;
    private TextInputLayout tilState;
    private TextInputLayout tilZipPostalCode;
    private String email, name, address1, address2, city, country, state, zip;
    private CountryExtData countryExtData;
    private AppDialogLoader loader;
    private ArrayList<CountryList> countryList;
    private List<FlightsList> flightList;
    private RelativeLayout rlytInvoiceInformation;
    private ScrollView scrollView;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private String[] countrlyListDropDown;
    private InvoiceData invoiceData;
    private String countryId;
    private String countryName;
    private FlightsList passDetail;
    private ImageView imgEdit;
    private ImageView imgErroWala;
    private ImageView imgSuccessWala;
    private OTTextView txtSelectedPass;
    private InternationalizeData localization;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mmp_invoice, container, false);
        loader = AppDialogLoader.getLoader(getActivity());

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }


        callInvoiceApi();

        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(),false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        try {
            passDetail = (FlightsList) getArguments().getSerializable(getString(R.string.key_serializable));
            countryExtData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getCountryExtData(getActivity())), CountryExtData.class);

        } catch (Exception e) {
            Utils.ERROR("CreateAccountFromReviewFragment >> Error while parsing json : " + e.toString());
        }

        initUi(view);

        return view;
    }


    private void initUi(View view) {
        txtMandatoryFieldLabel = (OTTextView) view.findViewById(R.id.txtMandatoryFieldLabel);
        txtBillingAddressLabel = (OTTextView) view.findViewById(R.id.txtBillingAddressLabel);
        txtContinue = (OTTextView) view.findViewById(R.id.txtSave);
        txtCountry = (OTTextView) view.findViewById(R.id.txtCountry);
        txtCountryLabel = (OTTextView) view.findViewById(R.id.txtCountryLabel);
        txtInvoiceInformation = (OTTextView) view.findViewById(R.id.txtInvoiceInformation);
        txtInvoiceSentDateLabel = (OTTextView) view.findViewById(R.id.txtInvoiceSentDateLabel);
        txtInvoiceSentDate = (OTTextView) view.findViewById(R.id.txtInvoiceSentDate);

        imgErroWala = (ImageView) view.findViewById(R.id.imgErroWala);
        imgSuccessWala = (ImageView) view.findViewById(R.id.imgSuccessWala);

        viewInvoiceSent = (View)view.findViewById(R.id.viewInvoiceSent);
        editEmail = (OTEditText) view.findViewById(R.id.editEmail);
        editNameOnInvoice = (OTEditText) view.findViewById(R.id.editNameOnInvoice);
        editAddress1 = (OTEditText) view.findViewById(R.id.editAddress1);
        editAddress2 = (OTEditText) view.findViewById(R.id.editAddress2);
        editCity = (OTEditText) view.findViewById(R.id.editCity);
        editState = (OTEditText) view.findViewById(R.id.editState);
        editZipPostal = (OTEditText) view.findViewById(R.id.editZipPostal);

        tilEmail = (TextInputLayout) view.findViewById(R.id.tilEmail);
        tilEmail.setHint("*"+localization.getLABLEmailLabel());
        tilNameOnInvoice = (TextInputLayout) view.findViewById(R.id.tilNameOnInvoice);
        tilAddress1 = (TextInputLayout) view.findViewById(R.id.tilAddress1);
        tilAddress2 = (TextInputLayout) view.findViewById(R.id.tilAddress2);
        tilCity = (TextInputLayout) view.findViewById(R.id.tilCity);
        tilState = (TextInputLayout) view.findViewById(R.id.tilState);
        tilZipPostalCode = (TextInputLayout) view.findViewById(R.id.tilZipPostalCode);

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

        rlytInvoiceInformation = (RelativeLayout) view.findViewById(R.id.rlytInvoiceInformation);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        scrollView.setVisibility(View.GONE);
        svCountry = (NestedScrollView) view.findViewById(R.id.svCountry);
        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);
        txtSelectedPass.setText(passDetail.getLabel().replace("#", " : "));

        imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.clearBackstackTillMMPSelectPassFragment(getActivity());
                //Utils.clearBackstackTillMMPSelectPassFragment("Invoice",getActivity());

            }
        });

        lytCountryScroll = (InterceptedLinearLayout) view.findViewById(R.id.lytCountryScroll);
        txtCountry.setText(countryName);


        txtCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDropdownViewForCountry(countrlyListDropDown, lytCountryScroll, txtCountry, new LinearLayout[]{});
                //Utils.createDropdownView(Utils.getCountryListExtArray(countryExtData.getData()), lytCountry, txtCountry, new LinearLayout[]{});
            }
        });
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytErrorMessage.removeAllViews();
                ArrayList<String> listError = validateInputs();
                if (listError.size() > 0) {
                    lytError.setVisibility(View.VISIBLE);
                    imgErroWala.setVisibility(View.VISIBLE);
                    imgSuccessWala.setVisibility(View.GONE);
                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                    //show error message
                    for (int index = 0; index < listError.size(); index++) {
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                    }
                } else {

                    //call api
                    lytError.setVisibility(View.GONE);
                    sendInvoice();
                }
            }
        });

    }

    private ArrayList<String> validateInputs() {
        ArrayList<String> listError = new ArrayList<>();
        email = editEmail.getText().toString().trim();
        name = editNameOnInvoice.getText().toString().trim();
        address1 = editAddress1.getText().toString().trim();
        address2 = editAddress2.getText().toString().trim();
        city = editCity.getText().toString().trim();
        state = editState.getText().toString().trim();
        zip = editZipPostal.getText().toString().trim();
        country = txtCountry.getText().toString().trim();


        /*if () {
            listError.add(getString(R.string.enter_valid_email));
        } */
        if (email.equalsIgnoreCase("") || (!Utils.isValidEmail(email))) {
            listError.add(localization.getERRInvalidD7EmailAddressErrorMessage());
            //editEmail.requestFocus();

        }
        if (name.equalsIgnoreCase("")) {
            listError.add(localization.getERR_Invoice_customer_name());

        }
        if (address1.equalsIgnoreCase("")) {
            listError.add(localization.getERR_Invalid_Street_Address_Message());
        }
        if (city.equalsIgnoreCase("")) {
            listError.add(localization.getERR_Invalid_City_Message());
        }
        if (state.equalsIgnoreCase("")) {
            listError.add(localization.getLABLStateProvinceLabel());
        }
        if (zip.equalsIgnoreCase("")) {
            listError.add(localization.getLABLZipCodeInputErrLabel());

        }
        return listError;
    }


    private void callInvoiceApi() {
        loader.show();
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_Select_invoice);
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
                        Utils.DEBUG("Invoice Response : " + response.toString());
                        invoiceData = ParseManager.getInstance().fromJSON(response, InvoiceData.class);
                        updateUI(invoiceData);

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

    private void updateUI(InvoiceData invoiceData) {
        countryList = invoiceData.getCountryList();
        flightList = invoiceData.getFlightsList();
        scrollView.setVisibility(View.VISIBLE);
        if (passDetail != null && passDetail.getInvoiceSent()) {
            rlytInvoiceInformation.setVisibility(View.VISIBLE);
            txtCountry.setClickable(false);
            txtCountry.setTextColor(getResources().getColor(R.color.color_font_gray));
            txtInvoiceInformation.setText(passDetail.getInvoiceNameBillingAddressLabel());
            txtInvoiceSentDateLabel.setText(passDetail.getInvoice_Sent_Label());
            txtInvoiceSentDate.setText(passDetail.getInvoiceDate());
            txtInvoiceSentDate.setTextColor(getResources().getColor(R.color.color_font_gray));
            editEmail.setText(passDetail.getCustomerEmail());
            editNameOnInvoice.setText(passDetail.getCustomerName());
            editNameOnInvoice.setTextColor(getResources().getColor(R.color.color_font_gray));
            editNameOnInvoice.setEnabled(false);
            txtCountry.setTextColor(getResources().getColor(R.color.color_font_gray));
            editAddress1.setText(passDetail.getCustomerAdd1());
            editAddress1.setTextColor(getResources().getColor(R.color.color_font_gray));
            editAddress1.setEnabled(false);
            if (!passDetail.getCustomerAdd2().equalsIgnoreCase("")) {
                editAddress2.setText(passDetail.getCustomerAdd2());
                editAddress2.setEnabled(false);
                editAddress2.setTextColor(getResources().getColor(R.color.color_font_gray));
            }
            editCity.setText(passDetail.getCustomerCity());
            editCity.setEnabled(false);
            editCity.setTextColor(getResources().getColor(R.color.color_font_gray));
            editState.setText(passDetail.getCustomerState());
            editState.setEnabled(false);
            editState.setTextColor(getResources().getColor(R.color.color_font_gray));
            editZipPostal.setText(passDetail.getCustomerZipCode());
            editZipPostal.setEnabled(false);
            editZipPostal.setTextColor(getResources().getColor(R.color.color_font_gray));
            txtCountry.setText(passDetail.getCustomerCountry());
            txtContinue.setText(passDetail.getSubmit_Label());
            countryId = passDetail.getCustomerCountryID();
        } else {
            rlytInvoiceInformation.setVisibility(View.GONE);
            txtMandatoryFieldLabel.setVisibility(View.VISIBLE);
            txtContinue.setText(passDetail.getSubmit_Label());
            for (int i = 0; i < countryList.size(); i++) {
                if (Utils.getUserSelectedCountryId(getActivity()) == Integer.parseInt(countryList.get(i).getCId().toString().replace("+", ""))) {
                    txtCountry.setText(countryList.get(i).getCName());
                    countryId = countryList.get(i).getCId();
                    Utils.DEBUG("Country Name selelction :" + countryList.get(i).getCName());

                }
            }
        }
        String redStar = "<font color='red'>*</font>";


        tilEmail.setHint(invoiceData.getEmailLabel() + Html.fromHtml(redStar));
        tilNameOnInvoice.setHint(invoiceData.getNameOnInvoiceLabel() + "*");
        tilAddress1.setHint(invoiceData.getAddressLine1Label() + "*");
        tilAddress2.setHint(invoiceData.getAddressLine2Label());
        tilCity.setHint(invoiceData.getCityLabel() + Html.fromHtml(redStar));
        tilState.setHint(invoiceData.getStateProvinceLabel() + Html.fromHtml(redStar));
        tilZipPostalCode.setHint(invoiceData.getZipPostal() + "*");
        txtBillingAddressLabel.setText(invoiceData.getBillingAddressLabel());
        txtCountryLabel.setText(invoiceData.getCountryLabel() + "*");

        txtMandatoryFieldLabel.setText("*" + invoiceData.getMandatory_Label());

        countrlyListDropDown = new String[invoiceData.getCountryList().size()];
        for (int i = 0; i < invoiceData.getCountryList().size(); i++) {
            countrlyListDropDown[i] = invoiceData.getCountryList().get(i).getCName();
        }


    }

    public void createDropdownViewForCountry(final String[] aryDropdownOption, final LinearLayout lytParent, final OTTextView selectedView, LinearLayout aryOtherdropdown[]) {


        if (lytParent.getChildCount() == 0) {
            //first dismiss rest dropdowns
            for (int index = 0; index < aryOtherdropdown.length; index++) {
                if (aryOtherdropdown[index] != null) {
                    ((LinearLayout) aryOtherdropdown[index]).removeAllViews();
                    ((View) ((LinearLayout) aryOtherdropdown[index]).getParent()).setVisibility(View.GONE);
                }
            }

            for (int index = 0; index < aryDropdownOption.length; index++) {
                OTTextView textView = new OTTextView(lytParent.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins((int) Utils.conertDpToPixel(lytParent.getContext(), 10), (int) Utils.conertDpToPixel(lytParent.getContext(), 10), 0, (int) Utils.conertDpToPixel(lytParent.getContext(), 10));
                textView.setText(aryDropdownOption[index]);

                textView.setTextSize(Utils.convertPixelToDp(lytParent.getContext(), lytParent.getContext().getResources().getDimension(R.dimen.size_font_13)));
                textView.setTextColor(lytParent.getContext().getResources().getColor(R.color.color_font_black));
                textView.setTypeface(null, Typeface.NORMAL);

                final int finalIndex = index;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedView.setText(aryDropdownOption[finalIndex]);
                        final String selectedPassId = aryDropdownOption[finalIndex];


                        for (int i = 0; i < invoiceData.getCountryList().size(); i++) {
                            if (invoiceData.getCountryList().get(i).getCName().equals(selectedPassId)) {
                                countryId = invoiceData.getCountryList().get(i).getCId();
                                countryName = invoiceData.getCountryList().get(i).getCName();

                                //Utils.showToast(getActivity(), countryName+" " +countryId);
                                svCountry.setVisibility(View.GONE);
                            }
                        }


                    }
                });

                lytParent.addView(textView, params);
            }
            ((View) (lytParent).getParent()).setVisibility(View.VISIBLE);
        } else {
            ((View) (lytParent).getParent()).setVisibility(((View) (lytParent).getParent()).getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }

        Utils.calculateHeight(aryDropdownOption, lytParent);
    }


    private void sendInvoice() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SEND_INVOICE) + "&Id=1";

        loader.show();
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("InvoiceCustomerEmail", email);
            requestObject.put("InvoiceCustomerName", name);
            requestObject.put("InvoiceCustomerAddress1", address1);
            requestObject.put("InvoiceCustomerAddress2", address2);
            requestObject.put("InvoiceCustomerCity", city);
            requestObject.put("InvoiceCustomerState", state);
            requestObject.put("InvoiceCustomerZipCode", zip);
            requestObject.put("InvoiceCustomerCountry", countryId);
            requestObject.put("InvoicePassId", passDetail.getId());
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
                        Utils.DEBUG("send Invoice REsponse : " + response.toString());
                        try {
                            JSONObject object = new JSONObject(response.toString());
                            String result = object.getString("Result");
                            if (result!=null) {
                                lytError.setVisibility(View.VISIBLE);
                                lytErrorMessage.removeAllViews();
                                if (result.equalsIgnoreCase("Failure")) {
                                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                                    imgSuccessWala.setVisibility(View.VISIBLE);
                                    imgErroWala.setVisibility(View.GONE);
                                    imgSuccessWala.setImageResource(R.drawable.img_forgot_password);
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), object.getString("Message")));
                                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                                    ImageView img_cross_error = (ImageView) lytErrorMessage.findViewById(R.id.img_cross_error);
                                    img_cross_error.setVisibility(View.GONE);


                                    // scrollView.pageScroll(ScrollView.FOCUS_UP);
                                } else {
                                    //scrollView.bringToFront();
                                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                                    imgSuccessWala.setVisibility(View.VISIBLE);
                                    imgErroWala.setVisibility(View.GONE);
                                    imgSuccessWala.setImageResource(R.drawable.img_success_forgot_password);
                                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), object.getString("Message")));
                                    ImageView img_cross_error = (ImageView) lytErrorMessage.findViewById(R.id.img_cross_error);
                                    img_cross_error.setVisibility(View.GONE);
                                }
                            }
                            else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
}

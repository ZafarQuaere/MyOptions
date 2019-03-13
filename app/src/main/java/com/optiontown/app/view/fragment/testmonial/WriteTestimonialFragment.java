package com.optiontown.app.view.fragment.testmonial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.SelectedFlightTestimonialAdapter;
import com.optiontown.app.model.fpotestimonial.AirlineList;
import com.optiontown.app.model.fpotestimonial.CateogyList;
import com.optiontown.app.model.fpotestimonial.CountryCodeList;
import com.optiontown.app.model.fpotestimonial.CreditCardList;
import com.optiontown.app.model.fpotestimonial.OptionList;
import com.optiontown.app.model.fpotestimonial.PrefixList;
import com.optiontown.app.model.fpotestimonial.TelephoneCodeList;
import com.optiontown.app.model.fpotestimonial.TestimonialMaster;
import com.optiontown.app.model.fpotestimonial.WriteTestimonialResponse;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassFragment;
import com.optiontown.app.view.fragment.login.ForgotPasswordFragment;
import com.optiontown.app.view.fragment.login.LoginFragment;
import com.optiontown.app.view.fragment.login.MyProfileFragment;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by parasmani.sharma on 02/08/2016.
 */
public class WriteTestimonialFragment extends BaseFragment {

    View view;
    AppDialogLoader loader;
    TestimonialMaster testimonialData;
    ArrayList<PrefixList> arrayList = new ArrayList<PrefixList>();
    OTTextView txtMandatory,txtwritetestimonial, txtPrefixname, txtCountry, txtMobilePrefix,txt_CardType, submit, txtMessage;
    LinearLayout lytCategory, lytPrefix,lytCountry,lytAirline,lytOption,lytMobilePrefix,lytError,lytErrorMessage, lytCreditCard;
    RelativeLayout lyt_complete_details;
    OTEditText edtFirstName,edtMiddleName,edtLastName,edtpnr,edtUsernameEmail,edtMobileNumber, edtdescribeqsn;
    String getPrefixId, telephoneCountryCode, categoryId;
    InternationalizeData localization;
    RelativeLayout lyt_sub_deatails;
    ScrollView svParent;
    OTTextView txt_emaillink_label;
    private String categorySelectedId = "11";
    private LinearLayout lytcredit_card;
    private OTTextView txtcardtypelabel;
    private OTTextView txt_card_type_data;
    private OTTextView txtLastfourdigit_label;
    private OTEditText edtLastFourDigit;
    private OTTextView txtMendatory;
    private OTTextView txt_categorylabel;
    private LinearLayout lytsecondheading;
    private OTTextView txtPrefixLabelname;
    private OTTextView txtFirstNameLabe;
    private OTTextView txtMiddleName;
    private OTTextView txtLastNameLabel;
    private OTTextView txt_heading_contactInfo;
    private OTTextView txtUsernameEmailLabel;
    private OTTextView txtMobileLabel;
    private OTTextView txtCountryLabel;
    private OTTextView txt_mailingAdd;
    private OTTextView txt_mailingAddres;
    private OTTextView txtpnrlabel;
    private LinearLayout lytcontact_info;
    private LinearLayout lytDescription;
    private OTTextView txt_booking_by_flight;
    private OTTextView txt_booking_by_fligth_label;
    private OTTextView txt_label_if_you_still_have_qsn;
    private OTTextView txt_edit_contact_details;
    private OTTextView txt_forgor_username_password;
    private OTTextView txt_for_any_other_issue_label;
    private String creditCardIdSelected;
    private OTTextView txtdescribeqsn;
    private OTTextView txt_bookingInfo;

    private String airlineId = "-1";
    private String optionId = "-1";
    private RecyclerView airlineRecycler;
    private RecyclerView optionRecycler;
    private OTTextView txtAirlineVal;
    private OTTextView txtOptionVal;
    private OTTextView txtAirlineLabel;
    private OTTextView txtProductLabel;
    private ArrayList<String> listError;
    private RelativeLayout lyt_deatails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_write_testimonial, container, false);

        loader = AppDialogLoader.getLoader(getActivity());
        //initialise shared prefs manager
        //sp = AppSharedPrefs.getInstance(getActivity());
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        testimonialData = (TestimonialMaster) getArguments().getSerializable(getString(R.string.key_serializable));

        getUIReference();



        callContactUSLabelsAPI(airlineId, optionId);

        return view;
    }
    protected void sendEmail() {
        String[] TO = {"customercare@optiontown.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
        catch (android.content.ActivityNotFoundException ex) {
            Utils.getErrorOneRowView(getActivity(),"There is no email client installed.");
        }
    }

    private void getUIReference() {

        txt_for_any_other_issue_label = (OTTextView)view.findViewById(R.id.txt_for_any_other_issue_label);
        txt_bookingInfo = (OTTextView)view.findViewById(R.id.txt_bookingInfo);

        txt_edit_contact_details = (OTTextView)view.findViewById(R.id.txt_edit_contact_details);
        txt_edit_contact_details.setPaintFlags(txt_edit_contact_details.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txt_forgor_username_password = (OTTextView)view.findViewById(R.id.txt_forgor_username_password);
        txt_forgor_username_password.setPaintFlags(txt_forgor_username_password.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txt_label_if_you_still_have_qsn = (OTTextView)view.findViewById(R.id.txt_label_if_you_still_have_qsn);

        txt_booking_by_flight = (OTTextView)view.findViewById(R.id.txt_booking_by_flight);
        txt_booking_by_flight.setPaintFlags(txt_booking_by_flight.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txt_booking_by_fligth_label = (OTTextView)view.findViewById(R.id.txt_booking_by_fligth_label);

        txtdescribeqsn = (OTTextView) view.findViewById(R.id.txtdescribeqsn);

        txtMendatory = (OTTextView)view.findViewById(R.id.txtMendatory);
        txt_categorylabel = (OTTextView)view.findViewById(R.id.txt_categorylabel);
        lytsecondheading = (LinearLayout)view.findViewById(R.id.lytsecondheading);
        txtPrefixLabelname = ((OTTextView) view.findViewById(R.id.txtPrefixLabelname));
        txtFirstNameLabe = ((OTTextView) view.findViewById(R.id.txtFirstNameLabe));
        txtMiddleName = ((OTTextView) view.findViewById(R.id.txtMiddleName));
        txtLastNameLabel = ((OTTextView) view.findViewById(R.id.txtLastNameLabel));
        //txt_airline = ((OTTextView) view.findViewById(R.id.txt_airline));
        txt_heading_contactInfo = ((OTTextView) view.findViewById(R.id.txt_heading_contactInfo));
        txtUsernameEmailLabel  = ((OTTextView) view.findViewById(R.id.txtUsernameEmailLabel));
        txtMobileLabel  = ((OTTextView) view.findViewById(R.id.txtMobileLabel));
        txtCountryLabel = ((OTTextView) view.findViewById(R.id.txtCountryLabel)) ;
        txt_mailingAdd =  ((OTTextView) view.findViewById(R.id.txt_mailingAdd));
        txt_mailingAddres = ((OTTextView) view.findViewById(R.id.txt_mailingAddres));
        txt_emaillink_label = ((OTTextView) view.findViewById(R.id.txt_emaillink_label));
        txtpnrlabel = ((OTTextView) view.findViewById(R.id.txtpnrlabel));
        //txtoption = ((OTTextView) view.findViewById(R.id.txtoption));
        lytcontact_info = ((LinearLayout) view.findViewById(R.id.lytcontact_info));
        lytDescription = (LinearLayout)view.findViewById(R.id.lytDescription);

        txt_emaillink_label = (OTTextView) view.findViewById(R.id.txt_emaillink_label);
        txt_emaillink_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        svParent = (ScrollView) view.findViewById(R.id.svParent);
        txtMandatory = (OTTextView) view.findViewById(R.id.txtMendatory);

        lyt_sub_deatails = (RelativeLayout) view.findViewById(R.id.lyt_sub_deatails);
        final ScrollView svPrefix = (ScrollView) lyt_sub_deatails.findViewById(R.id.svPrefix);
        final ScrollView svOption = (ScrollView) lyt_sub_deatails.findViewById(R.id.svOption);
        final ScrollView svAirline = (ScrollView) lyt_sub_deatails.findViewById(R.id.svAirline);
        final ScrollView svMobile = (ScrollView) lyt_sub_deatails.findViewById(R.id.svMobile);
        final ScrollView svCountry = (ScrollView) lyt_sub_deatails.findViewById(R.id.svCountry);
        final ScrollView svCategory = (ScrollView) lyt_sub_deatails.findViewById(R.id.svCategory);
        final ScrollView svCreditCard = (ScrollView) lyt_sub_deatails.findViewById(R.id.svCreditCard);

        /*final ScrollView svCountryCode = (ScrollView) view.findViewById(R.id.svCountryCode);
        final ScrollView svDay = (ScrollView) view.findViewById(R.id.svDay);
        final ScrollView svMonth = (ScrollView) view.findViewById(R.id.svMonth);
        final ScrollView svYear = (ScrollView) view.findViewById(R.id.svYear);*/

        svParent.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event)
            {
                svPrefix.getParent().requestDisallowInterceptTouchEvent(false);
                svOption.getParent().requestDisallowInterceptTouchEvent(false);
                svAirline.getParent().requestDisallowInterceptTouchEvent(false);
                svMobile.getParent().requestDisallowInterceptTouchEvent(false);
                svCountry.getParent().requestDisallowInterceptTouchEvent(false);
                svCategory.getParent().requestDisallowInterceptTouchEvent(false);
                svCreditCard.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        Utils.setInterceptTouchEvent(new View[]{svPrefix, svOption, svAirline, svMobile, svCountry,svCategory, svCreditCard});


        lyt_sub_deatails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svPrefix.setVisibility(View.GONE);
                svOption.setVisibility(View.GONE);
                svAirline.setVisibility(View.GONE);
                svMobile.setVisibility(View.GONE);
                svCountry.setVisibility(View.GONE);
                svCategory.setVisibility(View.GONE);
                svCreditCard.setVisibility(View.GONE);
            }
        });

        txtMessage = (OTTextView) view.findViewById(R.id.txtMessage);
        lyt_complete_details = (RelativeLayout) view.findViewById(R.id.lyt_complete_details);
        edtFirstName = (OTEditText) view.findViewById(R.id.edtFirstName);
        edtMiddleName = (OTEditText) view.findViewById(R.id.edtMiddleName);
        edtLastName = (OTEditText) view.findViewById(R.id.edtLastName);
        edtpnr = (OTEditText) view.findViewById(R.id.edtpnr);
        edtUsernameEmail = (OTEditText) view.findViewById(R.id.edtUsernameEmail);
        edtMobileNumber = (OTEditText) view.findViewById(R.id.edtMobileNumber);
        edtdescribeqsn = (OTEditText) view.findViewById(R.id.edtdescribeqsn);

        submit = (OTTextView) view.findViewById(R.id.submit);

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

        lytCategory = (LinearLayout) view.findViewById(R.id.lytCategory);
        txtwritetestimonial = (OTTextView) view.findViewById(R.id.txtwritetestimonial);

        lytPrefix = (LinearLayout) view.findViewById(R.id.lytPrefix);
        txtPrefixname = (OTTextView) view.findViewById(R.id.txtPrefixname);

        lytOption = (LinearLayout) view.findViewById(R.id.lytOption);
        //edtoption = (OTTextView) view.findViewById(R.id.edtoption);

        lytAirline = (LinearLayout) view.findViewById(R.id.lytAirline);
        //txtairline = (OTTextView) view.findViewById(R.id.txtairline);

        lytCountry = (LinearLayout) view.findViewById(R.id.lytCountry);
        txtCountry = (OTTextView) view.findViewById(R.id.txtCountry);

        lytMobilePrefix = (LinearLayout) view.findViewById(R.id.lytMobilePrefix);
        txtMobilePrefix = (OTTextView) view.findViewById(R.id.txtMobilePrefix);

        lytCreditCard = (LinearLayout) view.findViewById(R.id.lytCreditCard);
        txt_card_type_data = (OTTextView) view.findViewById(R.id.txt_card_type_data);


        /*
        * credit card ui
        * */

        lytcredit_card = (LinearLayout)view.findViewById(R.id.lytcredit_card);

        txtcardtypelabel = (OTTextView)view.findViewById(R.id.txtcardtypelabel);
        txtLastfourdigit_label = (OTTextView)view.findViewById(R.id.txtLastfourdigit_label);
        edtLastFourDigit = (OTEditText)view.findViewById(R.id.edtLastFourDigit);




        lyt_deatails = (RelativeLayout) view.findViewById(R.id.lyt_deatails);

        txtAirlineVal = (OTTextView) view.findViewById(R.id.txtAirlineVal);
        txtAirlineLabel = (OTTextView) view.findViewById(R.id.txtAirlineLabel);
        airlineRecycler = (RecyclerView) view.findViewById(R.id.airlineRecycler);
        txtOptionVal = (OTTextView) view.findViewById(R.id.txtOptionVal);
        txtProductLabel = (OTTextView) view.findViewById(R.id.txtProductLabel);
        optionRecycler = (RecyclerView) view.findViewById(R.id.optionRecycler);
        submit = (OTTextView) view.findViewById(R.id.submit);
        submit.setText(localization.getLABLSubmitButtonLabel());

        lyt_complete_details.setVisibility(View.GONE);
        lyt_deatails.setVisibility(View.VISIBLE);


        localiseUI();
    }

    private void localiseUI() {

        try {
            ((OTTextView) view.findViewById(R.id.txtAirlineLabel)).setText(localization.getLABLAirlineLabel() + "*");
            ((OTTextView) view.findViewById(R.id.txtProductLabel)).setText(localization.getLABL_Product_Label() != null ? localization.getLABL_Product_Label() + "*" : "Product*");

            ((OTTextView) view.findViewById(R.id.txt_heading_contactOT)).setText(localization.getLABLMTPContactPageHeadingLabel());
            ((OTTextView) view.findViewById(R.id.txtMendatory)).setText("*" + localization.getLABLMandatoryFieldWithoutAsteriskLabel());
            ((OTTextView) view.findViewById(R.id.txt_plz_fill_details)).setText(localization.getLABLReplyInformationLabel());
            ((OTTextView) view.findViewById(R.id.txt_categorylabel)).setText(localization.getLABLCategoryLabel());
            ((OTTextView) view.findViewById(R.id.txtPrefixLabelname)).setText(localization.getLABLPrefixLabel() + "*");
            ((OTTextView) view.findViewById(R.id.txtFirstNameLabe)).setText(localization.getLABLFirstNameLabel() + "*");
            ((OTTextView) view.findViewById(R.id.txtMiddleName)).setText(localization.getLABLMiddleNameLabel());
            ((OTTextView) view.findViewById(R.id.txtLastNameLabel)).setText(localization.getLABLLastNameLabel() + "*");
            ((OTTextView) view.findViewById(R.id.txt_airline)).setText(localization.getLABLAirlineLabel() + "*");
            ((OTTextView) view.findViewById(R.id.txt_heading_contactInfo)).setText(localization.getLABLContactInformationLabel());
            ((OTTextView) view.findViewById(R.id.txtUsernameEmailLabel)).setText(localization.getLABLEmailLabel() + "*");
            ((OTTextView) view.findViewById(R.id.txtMobileLabel)).setText(localization.getLABLCellMobileLabel() + "*");
            ((OTTextView) view.findViewById(R.id.txtCountryLabel)).setText(localization.getLABLPNRSearchByFlightPhoneCountryCodeLabel() + "*");
            ((OTTextView) view.findViewById(R.id.txt_mailingAdd)).setText(localization.getLABLContactUsAddressHeadingLabel());
            ((OTTextView) view.findViewById(R.id.txt_mailingAddres)).setText(localization.getLABLContactUsAddressLabel());
            ((OTTextView) view.findViewById(R.id.txt_emaillink_label)).setText(localization.getLABLEmailLinkLabel());
            txtPrefixname.setHint(localization.getLABLPrefixLabel());
            edtFirstName.setHint(localization.getLABLFirstNameLabel());
            edtMiddleName.setHint(localization.getLABLMiddleNameLabel());
            edtLastName.setHint(localization.getLABLLastNameLabel());
            //edtoption.setHint(localization.getLABLMasterUKLAOAvailableSelectLabel());
            //txtairline.setHint(localization.getLABLMasterUKLAOAvailableSelectLabel());
            txtMobilePrefix.setHint(localization.getLABLMasterUKLAOAvailableSelectLabel());
            edtMobileNumber.setHint(localization.getLABLTelephoneNumberLabel());
            txtCountry.setHint(localization.getLABLPNRSearchByFlightPhoneCountryCodeLabel());
            submit.setText(localization.getLABLSubmitButtonLabel());
            edtdescribeqsn.setHint(localization.getLABL_Write_here_Label()!=null ? localization.getLABL_Write_here_Label()+".." : "Write here....");
            txtdescribeqsn.setText(localization.getLABL_Describe_your_questions_or_thoughts_here_Label()!=null ? localization.getLABL_Describe_your_questions_or_thoughts_here_Label() : "Describe your question or thoughts here.");
            txtwritetestimonial.setText(localization.getLABLMTPWriteTestimonialLabel());
            txt_bookingInfo.setText(localization.getLABL_Booking_Information_Label()!=null ? localization.getLABL_Booking_Information_Label() : "Booking Information.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

  /*  private void callContactUSLabels()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_CONTACT_US_LABEL);

        JSONObject requestObject = new JSONObject();
        try
        {
            *//*requestObject.put("tgProductId", Integer.toString(getResources().getInteger(R.integer.value_tgProductId_flight_pass)));*//*
        }
        catch (Exception e)
        {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }

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
                        Utils.DEBUG("onResponse() CONTACT : " + response.toString());
                        testimonialData = ParseManager.getInstance().fromJSON(response, TestimonialMaster.class);

                        //updateUiEvents();

                        loader.hide();

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.hide();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
*/
    private void updateUiEvents() {

        try {
            txtCountry.setText(getDefaultCountyName(testimonialData.getCountryCodeList()));
            txt_label_if_you_still_have_qsn.setText(testimonialData.getRefundStatusDescLabel());
            txt_booking_by_flight.setText(testimonialData.getSearchBookingFlight1Label());
            txt_booking_by_fligth_label.setText(testimonialData.getSearchBookingFlight2BigLabel());

            txt_forgor_username_password.setText(testimonialData.getForgotUsernameAndPasswordLabel());
            txt_edit_contact_details.setText(testimonialData.getEditContactDetailsLabel());
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        txt_forgor_username_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new ForgotPasswordFragment(), null, 0);
            }
        });

        txt_edit_contact_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isUserLoggedIn(getActivity()))
                {
                    Utils.moveToFragment(getActivity(), new MyProfileFragment(), null, 0);
                }
                else
                {
                    Utils.moveToFragment(getActivity(), new LoginFragment(), null, 0);
                }
            }
        });

        txt_booking_by_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new FlightPassFragment(), null, 0);
            }
        });




        txtwritetestimonial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String[] category = getCategory(testimonialData.getCateogyList());
                createDropdownView(category, lytCategory, txtwritetestimonial, new LinearLayout[]{lytPrefix, lytAirline, lytCountry,lytMobilePrefix, lytCreditCard});
            }
        });


        txtPrefixname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               String[] prefix = getPrefix(testimonialData.getPrefixList());
               Utils.createDropdownView(prefix, lytPrefix, txtPrefixname, new LinearLayout[]{lytCategory, lytAirline, lytCountry, lytMobilePrefix, lytCreditCard});
            }
        });


        /*edtoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String[] options = getOption(testimonialData.getOptionList());
                Utils.createDropdownView(options, lytOption, edtoption, new LinearLayout[]{lytPrefix, lytAirline, lytCategory, lytCountry, lytMobilePrefix, lytCreditCard});
            }
        });*/

        /*txtairline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String[] airline = getAirline(testimonialData.getAirlineList());
                Utils.createDropdownView(airline, lytAirline, txtairline, new LinearLayout[]{lytPrefix, lytOption, lytCategory, lytCountry, lytMobilePrefix, lytCreditCard});
            }
        });*/

        txtCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String[] country = getCountry(testimonialData.getCountryCodeList());
                Utils.createDropdownView(country, lytCountry, txtCountry, new LinearLayout[]{lytPrefix, lytCategory, lytAirline, lytMobilePrefix, lytCreditCard});
            }
        });

        txtMobilePrefix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String[] telephonePrefix = getTelephoneCode(testimonialData.getTelephoneCodeList());
                Utils.createDropdownView(telephonePrefix, lytMobilePrefix , txtMobilePrefix, new LinearLayout[]{lytPrefix, lytCategory, lytAirline, lytCountry, lytCreditCard});
            }
        });


        txt_card_type_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String[] creditCards = getCreditCard(testimonialData.getCreditCardList());
                createDropdownView(creditCards, lytCreditCard , txt_card_type_data, new LinearLayout[]{lytPrefix, lytCategory, lytAirline, lytCountry, lytMobilePrefix});
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytError.setVisibility(View.VISIBLE);
                lytErrorMessage.removeAllViews();

                getPrefixId = getPrefixId(testimonialData.getPrefixList(),txtPrefixname.getText().toString());
                telephoneCountryCode = getTelephoneCode(testimonialData.getTelephoneCodeList(),txtMobilePrefix.getText().toString());
                airlineId = testimonialData.getAirlineid().toString();
                optionId = testimonialData.getLsproductid().toString();
                //categoryId = getCategoryId(testimonialData.getCateogyList(), txtwritetestimonial.getText().toString());


                ArrayList<String> listError = validateAllInputs();
                if(listError.size() > 0)
                {
                    svParent.fullScroll(ScrollView.FOCUS_UP);
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
                    callApiForUserTestimonial(categorySelectedId);
                }

            }
        });

    }


    private void callApiForUserTestimonial(String categorySelectedId) {

        String tag_json_obj = "json_obj_req";
        int countryId = getCountryId(txtCountry.getText().toString(), testimonialData.getCountryCodeList());
        String url;
        if(categorySelectedId.equals("18"))
        {
             url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_GET_LOGIN) + getString(R.string.URL_Save_Testimonial_FPO)+"&AirlineId="+airlineId+"&Option="+optionId+"&CategoryValue="+categorySelectedId+"&CountryId="+countryId+"&CreditCardId="+creditCardIdSelected+"&phonecountryCode="+telephoneCountryCode+"&prefix="+getPrefixId;

        }
        else
        {
             url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_GET_LOGIN) + getString(R.string.URL_Save_Testimonial_FPO)+"&AirlineId="+airlineId+"&Option="+optionId+"&CategoryValue="+categorySelectedId+"&CountryId="+countryId+"&CreditCardId=-1"+"&phonecountryCode="+telephoneCountryCode+"&prefix="+getPrefixId;
        }

        JSONObject requestObject = new JSONObject();
        try
        {
            requestObject.put("FName",edtFirstName.getText().toString().trim());
            requestObject.put("MName",edtMiddleName.getText().toString().trim());
            requestObject.put("Lname", edtLastName.getText().toString().trim());
            requestObject.put("PNR", edtpnr.getText().toString());
            requestObject.put("Description", edtdescribeqsn.getText().toString());
            requestObject.put("PhoneNumber", edtMobileNumber.getText().toString());
            requestObject.put("Email",edtUsernameEmail.getText().toString().trim());
            requestObject.put("lastfourDigit",edtLastFourDigit.getText().toString().trim());
        }
        catch (Exception e)
        {
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
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            return;
                        }
                        Utils.DEBUG("onResponse() Testimonial Send.. : " + response.toString());
                        WriteTestimonialResponse writeTestResp = ParseManager.getInstance().fromJSON(response, WriteTestimonialResponse.class);

                        if(writeTestResp.getResult().equals(getString(R.string.string_success)))
                        {
                            lyt_complete_details.setVisibility(View.GONE);
                            lyt_deatails.setVisibility(View.GONE);
                            txtMessage.setText("" + Html.fromHtml(writeTestResp.getMessage().toString()));
                            txtMessage.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                        }

                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.string_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private ArrayList<String> validateAllInputs()
    {
        ArrayList<String> listError = new ArrayList<>();



        if(Utils.compareDefaultValues(txtPrefixname, localization.getLABLPrefixErrorLabel()) || Utils.compareDefaultValues(txtPrefixname, ""))
        {
            listError.add(localization.getLABLPrefixInputErrLabel());
        }

        if(Utils.compareDefaultValues(edtFirstName, ""))
        {
            listError.add(localization.getERRInvalidFirstNameErrorMessage());
        }

        if(Utils.compareDefaultValues(edtLastName, ""))
        {
            listError.add(localization.getERRInvalidLastNameErrorMessage());
        }

        /*if(Utils.compareDefaultValues(edtoption, ""))
        {
            listError.add(localization.getLABL_Select_Preferred_Option_Label()!=null ? localization.getLABL_Select_Preferred_Option_Label() : "Select Preferred Option.");
        }*/

        /*if(Utils.compareDefaultValues(txtairline, ""))
        {
            listError.add(localization.getLABL_Select_Airline_Label()!=null ? localization.getLABL_Select_Airline_Label() : "Select Airline.");
        }*/



        if(categorySelectedId.equals("18"))
        {
            if(edtLastFourDigit.getText().toString().length()<4)
            {
                listError.add(localization.getLABL_Err_Enter_last_four_digit_in_Credit_Card_Label()!=null ? localization.getLABL_Err_Enter_last_four_digit_in_Credit_Card_Label() : "Enter last four digit in Credit Card.");
            }

        }

        //if(!categorySelectedId.equals("13"))
        {
            if(!Utils.isValidEmail(edtUsernameEmail.getText().toString().trim()))
            {
                listError.add(localization.getERRInvalidD7EmailAddressErrorMessage());
            }

            if(!Utils.isValidMobileNumber(edtMobileNumber.getText().toString()))
            {
                listError.add(localization.getERRInvalidPhoneNumberMessage());
            }

        }


        if (Utils.compareDefaultValues(txtAirlineVal, testimonialData.getAirlineList().get(0).getAirlineLabel())) {
            listError.add(testimonialData.getErrorMandatoryField());
        }
        if (Utils.compareDefaultValues(txtOptionVal, testimonialData.getAirlineList().get(0).getAirlineLabel())) {
            listError.add(testimonialData.getErrorMandatoryField());
        }

        return listError;
    }
    private String[] getCategory(List<CateogyList> arrayList)
    {
        String[] category = new String[arrayList.size()];
        for (int index = 0; index < arrayList.size(); index++)
        {

            category[index] = arrayList.get(index).getCategoryLabel();
        }
        return category;
    }
    private String getCategoryId(List<CateogyList> arrayList , String categoryType)
    {
        String categoryId = null;
        for (int index = 0; index < arrayList.size(); index++)
        {
            if(arrayList.get(index).getCategoryLabel().equals(categoryType)){
                categoryId = arrayList.get(index).getCategoryID()+"";
            }
        }
        return categoryId;
    }
    private String[] getPrefix(List<PrefixList> arrayList)
    {
        String[] prefix = new String[arrayList.size()];
        for (int index = 0; index < arrayList.size(); index++)
        {
            prefix[index] = arrayList.get(index).getOptionLabel();

        }
        return prefix;
    }
    private String getPrefixId(List<PrefixList> arrayList , String prefixname)
    {
        String prefixID = null;
        for (int index = 0; index < arrayList.size(); index++)
        {
            if(arrayList.get(index).getOptionLabel().equals(prefixname)){
                prefixID = arrayList.get(index).getPrefixID()+"";
                Utils.showToast(getActivity(),prefixID);
            }
        }
        return prefixID;
    }
    private String[] getOption(List<OptionList> arrayList)
    {
        String[] optionlist = new String[arrayList.size()];
        for (int index = 0; index < arrayList.size(); index++)
        {

            optionlist[index] = arrayList.get(index).getOptionLabel();
        }
        return optionlist;
    }
    private String getOptionId(List<OptionList> arrayList , String optionType)
    {
        String optionId = null;
        for (int index = 0; index < arrayList.size(); index++)
        {
            if(arrayList.get(index).getOptionLabel().equals(optionType)){
                optionId = arrayList.get(index).getOptionID()+"";
            }
        }
        return optionId;
    }
    private String[] getAirline(List<AirlineList> arrayList)
    {
        String[] airlineList = new String[arrayList.size()];
        for (int index = 0; index < arrayList.size(); index++)
        {

            airlineList[index] = arrayList.get(index).getAirlineLabel();
        }
        return airlineList;
    }
    private String getAirlineId(List<AirlineList> arrayList , String airlineType)
    {
        String airlineId = null;
        for (int index = 0; index < arrayList.size(); index++)
        {
            if(arrayList.get(index).getAirlineLabel().equals(airlineType)){
                airlineId = arrayList.get(index).getAirlineID()+"";
            }
        }
        return airlineId;
    }
    private String[] getCountry(List<CountryCodeList> arrayList)
    {
        String[] countryList = new String[arrayList.size()];
        for (int index = 0; index < arrayList.size(); index++)
        {

            countryList[index] = arrayList.get(index).getCountryLabel();
        }
        return countryList;
    }

    private String getDefaultCountyName(List<CountryCodeList> arrayList)
    {
        return arrayList.get(0).getCountryLabel();
    }


    private int getCountryId(String countryName, List<CountryCodeList> arrayList)
    {
        String[] countryList = new String[arrayList.size()];
        for (int index = 0; index < arrayList.size(); index++)
        {
            if(arrayList.get(index).getCountryLabel().equals(countryName))
            {
                return Integer.parseInt(arrayList.get(index).getCountryID());
            }
        }
        return 0;

    }


    private String[] getTelephoneCode(List<TelephoneCodeList> arrayList)
    {
        String[] telephonePrefix = new String[arrayList.size()];
        for (int index = 0; index < arrayList.size(); index++)
        {

            telephonePrefix[index] = arrayList.get(index).getTelephoneCountryLabel();
        }
        return telephonePrefix;
    }
    private String getTelephoneCode(List<TelephoneCodeList> arrayList , String countryTelLabel)
    {
        String telCountryCode = null;
        for (int index = 0; index < arrayList.size(); index++)
        {
            if(arrayList.get(index).getTelephoneCountryLabel().equals(countryTelLabel)){
                telCountryCode = arrayList.get(index).getTelephoneCountryID()+"";
            }
        }
        return telCountryCode;
    }
    private String[] getCreditCard(List<CreditCardList> arrayList)
    {
        String[] creditCard = new String[arrayList.size()];
        for (int index = 0; index < arrayList.size(); index++)
        {

            creditCard[index] = arrayList.get(index).getCreditCardLabel();
        }
        return creditCard;
    }



    public void createDropdownView(final String[] aryDropdownOption, final LinearLayout lytParent, final OTTextView selectedView, LinearLayout aryOtherdropdown[])
    {

        hideKeyboard(lytParent.getContext(), lytParent.getRootView());
        if(lytParent.getChildCount() == 0)
        {
            //first dismiss rest dropdowns
            for (int index = 0; index < aryOtherdropdown.length; index++) {
                if(aryOtherdropdown[index] != null)
                {
                    ((LinearLayout) aryOtherdropdown[index]).removeAllViews();
                    ((View)((LinearLayout) aryOtherdropdown[index]).getParent()).setVisibility(View.GONE);
                }
            }

            for (int index = 0; index < aryDropdownOption.length; index++)
            {
                final OTTextView textView = new OTTextView(lytParent.getContext());
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

                        //remove all error labels
                        if(selectedView.getId() == R.id.txtwritetestimonial)
                        {
                            lytError.setVisibility(View.GONE);
                            lytErrorMessage.removeAllViews();
                        }


                        selectedView.setText(aryDropdownOption[finalIndex]);
                        ((View)(lytParent).getParent()).setVisibility(View.GONE);


                        String categorySelected = aryDropdownOption[finalIndex];

                        if(selectedView.getId() == R.id.txt_card_type_data)
                        {
                            for(int i = 0; i<testimonialData.getCreditCardList().size(); i++)
                            {

                                if(testimonialData.getCreditCardList().get(i).getCreditCardLabel().equals(categorySelected))
                                {
                                    creditCardIdSelected = testimonialData.getCreditCardList().get(i).getCreditCardID().toString();

                                    break;
                                }
                            }
                        }
                        else {
                            for(int i = 0; i<testimonialData.getCateogyList().size(); i++)
                            {

                                if(testimonialData.getCateogyList().get(i).getCategoryLabel().equals(categorySelected))
                                {
                                    categorySelectedId = testimonialData.getCateogyList().get(i).getCategoryID().toString();

                                    setLayoutUsingCategory(categorySelectedId);

                                    break;
                                }
                            }
                        }

                    }
                });

                lytParent.addView(textView, params);
            }
            ((View)(lytParent).getParent()).setVisibility(View.VISIBLE);
        }
        else
        {
            ((View)(lytParent).getParent()).setVisibility(((View)(lytParent).getParent()).getVisibility()== View.GONE ? View.VISIBLE : View.GONE);
        }

        Utils.calculateHeight(aryDropdownOption, lytParent);
    }

    public void hideKeyboard(Context context, View view) {

        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }






    private void setCategoryUI(int viewType)
    {
        txtMendatory.setVisibility(viewType);
        txt_categorylabel.setVisibility(viewType);
        txtwritetestimonial.setVisibility(viewType);
    }

    private void setBookingUI(int viewType)
    {

        lytsecondheading.setVisibility(viewType);
        txtPrefixLabelname.setVisibility(viewType);
        txtPrefixname.setVisibility(viewType);
        txtFirstNameLabe.setVisibility(viewType);
        edtFirstName.setVisibility(viewType);
        txtMiddleName.setVisibility(viewType);
        edtMiddleName.setVisibility(viewType);
        txtLastNameLabel.setVisibility(viewType);
        edtLastName.setVisibility(viewType);
        txtpnrlabel.setVisibility(viewType);
        edtpnr.setVisibility(viewType);
        //txtoption.setVisibility(viewType);
        //edtoption.setVisibility(viewType);
        //txt_airline.setVisibility(viewType);
        //txtairline.setVisibility(viewType);

    }

    private void setContactInfoUI(int viewType)
    {
        lytcontact_info.setVisibility(viewType);
        txtUsernameEmailLabel.setVisibility(viewType);
        edtUsernameEmail.setVisibility(viewType);
        txtMobileLabel.setVisibility(viewType);
        txtMobilePrefix.setVisibility(viewType);
        edtMobileNumber.setVisibility(viewType);
        txtCountryLabel.setVisibility(viewType);
        txtCountry.setVisibility(viewType);
    }

    private void setCreditLayoutView(int viewType)
    {
        lytcredit_card.setVisibility(viewType);
        txtcardtypelabel.setVisibility(viewType);
        txt_card_type_data.setVisibility(viewType);
        txtLastfourdigit_label.setVisibility(viewType);
        edtLastFourDigit.setVisibility(viewType);
    }

    private void setDescriptionUI(int viewType)
    {
        lytDescription.setVisibility(viewType);
    }

    private void setSubmitButUI(int viewType)
    {
        submit.setText("Submit");
        submit.setVisibility(viewType);
    }

    private void setIcantFindBookUi(int viewType)
    {
        txt_booking_by_flight.setVisibility(viewType);
        txt_booking_by_fligth_label.setVisibility(viewType);
    }

    private void setMendatoryLabel(int viewType)
    {
        txtMendatory.setVisibility(viewType);
    }

    private void setLabelIfYouStillHave(int viewType)
    {
        if(viewType == View.VISIBLE)
        {
            for(int i = 0; i<testimonialData.getCateogyList().size(); i++)
            {
                if(testimonialData.getCateogyList().get(i).getCategoryID().equals("13"))
                {
                    submit.setText(testimonialData.getCateogyList().get(i).getCategoryLabel());

                }
            }

            txt_label_if_you_still_have_qsn.setVisibility(viewType);
        }
        else if(viewType == View.GONE)
        {
            submit.setText(localization.getLABLSubmitButtonLabel());
            txt_label_if_you_still_have_qsn.setVisibility(viewType);
        }

    }

    private void setMyOptionTownAccountLabel(int viewType)
    {
        txt_forgor_username_password.setVisibility(viewType);
        txt_edit_contact_details.setVisibility(viewType);
        txt_for_any_other_issue_label.setVisibility(viewType);

    }

    private void setTxtForAnyOtherTextTop(int viewType)
    {
        if(categorySelectedId.equals("16"))
        {
            txt_for_any_other_issue_label.setText(testimonialData.getCombineMultipleBookingLabel());
            txt_for_any_other_issue_label.setVisibility(viewType);
            txtdescribeqsn.setText(testimonialData.getCombineMultipleBookingDescLabel());
        }
        else
        {
            txt_for_any_other_issue_label.setText(testimonialData.getOptiobTownAccountLabel());
            txt_for_any_other_issue_label.setVisibility(viewType);
            txtdescribeqsn.setText(testimonialData.getOptiobTownAccountDescLabel());
        }

    }



    public void setLayoutUsingCategory(String categorySelected)
    {
        //My Payment Failed
        if(categorySelected.equals("18"))
        {
            setCategoryUI(View.VISIBLE);
            setBookingUI(View.VISIBLE);
            setContactInfoUI(View.VISIBLE);
            setCreditLayoutView(View.VISIBLE);
            setDescriptionUI(View.VISIBLE);
            setSubmitButUI(View.VISIBLE);

            setIcantFindBookUi(View.GONE);
            setLabelIfYouStillHave(View.GONE);
            setMyOptionTownAccountLabel(View.GONE);
            setTxtForAnyOtherTextTop(View.GONE);
            setMendatoryLabel(View.VISIBLE);
        }

        //I cant find my booking
        else if(categorySelected.equals("12"))
        {
            setCategoryUI(View.VISIBLE);
            setBookingUI(View.GONE);
            setContactInfoUI(View.GONE);
            setCreditLayoutView(View.GONE);
            setDescriptionUI(View.GONE);
            setSubmitButUI(View.GONE);

            setMendatoryLabel(View.GONE);
            setLabelIfYouStillHave(View.GONE);
            setMyOptionTownAccountLabel(View.GONE);
            setTxtForAnyOtherTextTop(View.GONE);
            setIcantFindBookUi(View.VISIBLE);
        }

        //check refund status
        else if(categorySelected.equals("13"))
        {
            setCategoryUI(View.VISIBLE);
            setBookingUI(View.VISIBLE);
            setContactInfoUI(View.VISIBLE);
            setCreditLayoutView(View.GONE);
            setDescriptionUI(View.GONE);
            setSubmitButUI(View.VISIBLE);

            setIcantFindBookUi(View.GONE);
            setMendatoryLabel(View.GONE);
            setMyOptionTownAccountLabel(View.GONE);
            setTxtForAnyOtherTextTop(View.GONE);
            setLabelIfYouStillHave(View.VISIBLE);
        }

        //my option town account
        else if(categorySelected.equals("6"))
        {
            //setBookingInfoForRefundStatus(View.GONE);
            setCategoryUI(View.VISIBLE);
            setBookingUI(View.VISIBLE);
            setContactInfoUI(View.VISIBLE);
            setCreditLayoutView(View.GONE);
            setDescriptionUI(View.VISIBLE);
            setSubmitButUI(View.VISIBLE);

            setIcantFindBookUi(View.GONE);
            setLabelIfYouStillHave(View.GONE);
            setTxtForAnyOtherTextTop(View.GONE);
            setMyOptionTownAccountLabel(View.VISIBLE);
            setMendatoryLabel(View.VISIBLE);
        }

        //combine multiple booking
        else if(categorySelected.equals("16"))
        {
            //setBookingInfoForRefundStatus(View.GONE);
            setCategoryUI(View.VISIBLE);
            setBookingUI(View.VISIBLE);
            setContactInfoUI(View.VISIBLE);
            setCreditLayoutView(View.GONE);
            setDescriptionUI(View.VISIBLE);
            setSubmitButUI(View.VISIBLE);


            setIcantFindBookUi(View.GONE);
            setLabelIfYouStillHave(View.GONE);
            setMyOptionTownAccountLabel(View.GONE);
            setTxtForAnyOtherTextTop(View.VISIBLE);
            setMendatoryLabel(View.VISIBLE);

        }

        //set default layout common for other case
        else
        {
            //setBookingInfoForRefundStatus(View.GONE);
            setCategoryUI(View.VISIBLE);
            setBookingUI(View.VISIBLE);
            setContactInfoUI(View.VISIBLE);
            setCreditLayoutView(View.GONE);
            setDescriptionUI(View.VISIBLE);
            setSubmitButUI(View.VISIBLE);

            setIcantFindBookUi(View.GONE);
            setLabelIfYouStillHave(View.GONE);
            setMyOptionTownAccountLabel(View.GONE);
            setTxtForAnyOtherTextTop(View.GONE);
            setMendatoryLabel(View.VISIBLE);
        }

    }


    //updates were done

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

                            try {
                                if (testimonialData.getCateogyList().size() > 0) {
                                    //Utils.moveToFragment(getActivity(), new WriteTestimonialFragment(), testimonialData, 0);

                                    lyt_complete_details.setVisibility(View.VISIBLE);
                                    updateUiEvents();
                                }
                            }catch (Exception e){}
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

                try {
                    for (int i = 1; i < testimonialData.getAirlineList().size(); i++) {
                        if (airlineIDSet.equals(testimonialData.getAirlineList().get(i).getAirlineID())) {
                            airlineId = airlineIDSet;
                            txtAirlineVal.setText(testimonialData.getAirlineList().get(i).getAirlineLabel());
                        }
                    }
                }catch (IndexOutOfBoundsException e){}
            } else {

                txtAirlineVal.setText(testimonialData.getAirlineList().get(0).getAirlineLabel());
                airlineId = testimonialData.getAirlineList().get(0).getAirlineID();

                /*if(testimonialData.getCateogyList().size() == 0)
                {
                    callContactUSLabelsAPI(airlineId, optionIdSet);
                }*/
            }

            if (!optionIdSet.equals("-1")) {

                //manual case

                try {
                    for (int i = 1; i < testimonialData.getOptionList().size(); i++) {
                        if (optionIdSet.equals(testimonialData.getOptionList().get(i).getOptionID())) {

                            txtOptionVal.setText(testimonialData.getOptionList().get(i).getOptionLabel());

                            if(testimonialData.getCateogyList().isEmpty())
                            {
                                callContactUSLabelsAPI(airlineId, optionId);
                            }
                            /*if(isDataChangedByUser)
                            {
                                callContactUSLabelsAPI(airlineId, optionIdSet);
                            }*/
                        /*if(!txtOptionVal.getText().toString().equals(testimonialData.getOptionList().get(0).getOptionLabel()))
                        {
                            optionId = optionIdSet;

                            if(testimonialData.getCateogyList().size() == 0)
                            {
                                callContactUSLabelsAPI(airlineId, optionIdSet);
                            }
                        }*/
                        }
                    }
                }catch (IndexOutOfBoundsException e){}

            } else {

                //default case

                if(testimonialData.getOptionList().size() > 0 && !airlineIDSet.equals("-1"))
                {
                    //default case if data is present
                    txtOptionVal.setText(testimonialData.getOptionList().get(0).getOptionLabel());
                    optionId = testimonialData.getOptionList().get(0).getOptionID();
                    if(testimonialData.getCateogyList().isEmpty())
                    {
                        callContactUSLabelsAPI(airlineId, optionId);
                    }

                }else {

                    txtOptionVal.setText(testimonialData.getOptionList().get(0).getOptionLabel());
                    optionId = testimonialData.getOptionList().get(0).getOptionID();
                    //default case if data is not present
                    //txtOptionVal.setText(testimonialData.getOptionList().get(0).getOptionLabel());
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        airlineRecycler.setLayoutManager(gridLayoutManager);

        SelectedFlightTestimonialAdapter adapterAirline = new SelectedFlightTestimonialAdapter(svParent,getActivity(), testimonialData, "selectAirline", new SelectedFlightTestimonialAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, String label, String id) {

                lyt_complete_details.setVisibility(View.GONE);
                airlineRecycler.setVisibility(View.GONE);
                optionRecycler.setVisibility(View.GONE);
                txtAirlineVal.setText(label);
                //txtOptionVal.setText(testimonialData.getOptionList().get(0).getOptionLabel());
                //optionId = testimonialData.getOptionList().get(0).getOptionID();
                optionId = "-1";
                airlineId = id;
                callContactUSLabelsAPI(airlineId, optionId);
            }
        });
        airlineRecycler.setAdapter(adapterAirline);




        NpaGridLayoutManager gridLayoutManagerOption = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        optionRecycler.setLayoutManager(gridLayoutManagerOption);
        SelectedFlightTestimonialAdapter adapterOption = new SelectedFlightTestimonialAdapter(svParent,getActivity(), testimonialData, "selectOption", new SelectedFlightTestimonialAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, String label, String id) {

                lyt_complete_details.setVisibility(View.GONE);
                airlineRecycler.setVisibility(View.GONE);
                optionRecycler.setVisibility(View.GONE);
                txtOptionVal.setText(label);
                optionId = id;
                //callContactUSLabelsAPI(airlineId, optionId);
                callContactUSLabelsAPI(airlineId, optionId);

            }
        });
        optionRecycler.setAdapter(adapterOption);


        txtOptionVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txtAirlineVal.getText().toString().equalsIgnoreCase(testimonialData.getAirlineList().get(0).getAirlineLabel()))
                {
                    airlineRecycler.setVisibility(View.GONE);
                    optionRecycler.setVisibility(optionRecycler.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }else {
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




            }
        });
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

package com.optiontown.app.view.fragment.legproducts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.adapter.AirlineArriveDepartAdapter;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.legproducthomepage.AdvanceSearchForm;
import com.optiontown.app.model.legproducthomepage.ArriveDropDownList;
import com.optiontown.app.model.legproducthomepage.BuyFormObject;
import com.optiontown.app.model.legproducthomepage.Home;
import com.optiontown.app.model.legproducthomepage.HomePageData;
import com.optiontown.app.model.legproducthomepage.SearchHelper;
import com.optiontown.app.model.segproduct.SegInputData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
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
import com.optiontown.app.view.fragment.segproducts.SegInputDetailsFragment;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Upgrade Pass Search Page
 */
public class LegProductSearchFragment extends BaseFragment implements View.OnClickListener{

    NetworkImageView imgPNR, imgEmail, img_airline, imgDepart, imgArrive, imgDepartDate, imgFlightNo, imgFirstUser, imgLastUser, imgCell;
    // TextInputLayout tilDepart, tilArrive,  tilFlightNumber, tilFirstName, tilPNR,tilDepartDate;
    RelativeLayout lytCountryCode, lytPhNo;
    LinearLayout lytPhonExtension, lytAirlines, lytDepart, lytArrive, lytDepartDate, lytFlightNumber, lytPnr, lytFirstName, lytLastName,
            lytEmail, lytFAQ, lytError, lytErrorMessage;

    String airlineName, depart, departDate, arrive, flightNumber, firstName, lastName, phoneNumber, pnr, phExtension, email;
    AppDialogLoader loader;
    ScrollView scrollParent, scrollCountryCode;
    AirlineArriveDepartAdapter mAirlineArriveDepartAdapter;
    String productName, productID, AirLineID, pMobileCC, arriveAirlineCode, departAirlineCode, arrivalid, departureid;
    Benefit mBenefit;
    boolean searchError = false;
    private View view, viewDepart, viewArrive, viewDepartDate, viewFlightNumber, viewPnr, viewFirstName, viewLastName, viewEmail;
    private OTEditText editFlightNumber, editFirstName, editLastName, editPhoneNumber, editEmail, editPNR;
    private OTTextView txtSearch, txtAdvanceSearch, txtCountryCode, txtCellNumberLabel, txtAirlineLabel, txtAirlineName, txtDepartDate, txtPnrLabel, txtLastNameLabel, txtEmailLabel,
            txtDepartLabel, txtArriveLabel, txtDepartDateLabel, txtFlightNoLabel, txtFirstNameLabel, txtMessage;
    private AutoCompleteTextView editArrive, editDepart;
    private AppSharedPrefs sharedPrefs;
    private CountryExtData countryExtData;
    private FragmentActivity fragmentActivity;
    private Home homeData;
    private Communicator communicator;
    private ImageLoader imageLoader;
    private ImageView imgErroWala;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_legproduct_search, container, false);
        sharedPrefs = AppSharedPrefs.getInstance(getActivity());

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mBenefit = (Benefit) getArguments().get(getActivity().getString(R.string.key_serializable));
        try {
            if (mBenefit.getBenefitName() != null) {
                productName = mBenefit.getBenefitName();
                productID = mBenefit.getId() + "";
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        imageLoader = AppController.getInstance().getImageLoader();
        updateUI();

        loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        try {
            countryExtData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getCountryExtData(getActivity())), CountryExtData.class);

        } catch (Exception e) {
            Utils.ERROR("CreateAccountFromReviewFragment >> Error while parsing json : " + e.toString());
        }

        callSessionData();
        editPhoneNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // txtCellNumberLabel.setText("Number");
                return false;
            }
        });

        try {
            homeData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLegProductSessionData(getActivity())), Home.class);
            //   Utils.setLegProductSharedPrefsForSearch(getActivity(), homeData.getHomePageData().getAirlineDropDownList());
            // updateUIChanges();
        } catch (Exception e) {
            Utils.ERROR("LegProductSearchFragment >> Error while parsing json : " + e.toString());
        }
        arriveDepartData(homeData);

        Utils.updateLegProductBottomBarForFeatures(getActivity(), view, this.getClass().getName());

        lytError.setVisibility(View.GONE);
        editArrive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArriveDropDownList mArriveDropDownList = (ArriveDropDownList) parent.getAdapter().getItem(position);
                arriveAirlineCode = mArriveDropDownList.getArriveCode();

                String str = mArriveDropDownList.getArriveName();
                if (str.equalsIgnoreCase("Arrive")|| str.equalsIgnoreCase("Depart")){
                    editArrive.setText("");
                    return;
                }
                String code = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                arrivalid = code;
                Utils.DEBUG("arrivalid : "+arrivalid);

            }
        });
        editDepart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArriveDropDownList mArriveDropDownList = (ArriveDropDownList) parent.getAdapter().getItem(position);
                departAirlineCode = mArriveDropDownList.getArriveCode();
                String str = mArriveDropDownList.getArriveName();
                if (str.equalsIgnoreCase("Arrive")|| str.equalsIgnoreCase("Depart")){
                    editDepart.setText("");
                    return;
                }
                String code = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                departureid = code;
                Utils.DEBUG("departureid : "+departureid);

            }
        });

        sharedPrefs.put(getActivity().getString(R.string.key_LP_selected_airline_tag), null);
        return view;
    }



    private void callSessionData() {

        try {
            String session = Utils.getLegProductSessionData(getActivity());
            if (session == null) {
                return;
            }
            JSONObject json = new JSONObject(session);

            Home data = (ParseManager.getInstance().fromJSON(json, Home.class));
            HomePageData mHomePageData = data.getHomePageData();
            AdvanceSearchForm strSearchFormData = mHomePageData.getAdvanceSearchForm();
            BuyFormObject buyFormObject = mHomePageData.getBuyFormObject();
            try {
                imgPNR.setImageUrl(buyFormObject.getPnrImage().trim(), imageLoader);
                imgEmail.setImageUrl(strSearchFormData.getEmailImage().trim(), imageLoader);
                img_airline.setImageUrl(mHomePageData.getAirlineImage().trim(), imageLoader);
                imgDepart.setImageUrl(strSearchFormData.getDepartImage().trim(), imageLoader);
                imgArrive.setImageUrl(strSearchFormData.getArriveImage().trim(), imageLoader);
                imgDepartDate.setImageUrl(strSearchFormData.getDepartDateImage().trim(), imageLoader);
                imgFlightNo.setImageUrl(strSearchFormData.getFlightNumberImage().trim(), imageLoader);
                imgFirstUser.setImageUrl(strSearchFormData.getFirstNameImage().trim(), imageLoader);
                imgLastUser.setImageUrl(strSearchFormData.getFirstNameImage().trim(), imageLoader);
                imgCell.setImageUrl(strSearchFormData.getCellMobileImage().trim(), imageLoader);

                txtPnrLabel.setText(buyFormObject.getLABLPNRLabel());
                txtAdvanceSearch.setText(buyFormObject.getLABLTgpStatusButtonLabel());
                txtSearch.setText(buyFormObject.getLABLTgpSignUpButtonLabel());
                txtAirlineLabel.setText(mHomePageData.getLABLAirlineLabel());
                txtAirlineName.setText(mHomePageData.getLABLAirlineLabel());

                txtLastNameLabel.setText(strSearchFormData.getLABLLastNameLabel());
                editLastName.setHint(strSearchFormData.getLABLLastNameLabel());
                txtFirstNameLabel.setText(strSearchFormData.getLABLFirstNameLabel());
                txtEmailLabel.setText(strSearchFormData.getLABLEmailShortLabel());
                txtCellNumberLabel.setText(strSearchFormData.getLABLTelephoneNumberLabel());
                txtDepartLabel.setText(strSearchFormData.getLABLFromCity());
                txtArriveLabel.setText(strSearchFormData.getLABLToCity());
                txtDepartDateLabel.setText(strSearchFormData.getLABLDepartDateTimeLabel());
                txtFlightNoLabel.setText(strSearchFormData.getLABLFlightNumberLabel());
                loader.dismiss();

                callHomePassBannerAPI();


            } catch (Exception e) {

            }


        } catch (JSONException e) {
            loader.dismiss();
            Utils.DEBUG("Error while parsing json : " + e.toString());
        }

    }

    private void updateUIForAdvanceSearch() {
        txtAdvanceSearch.setText(getString(R.string.string_search));
        txtAdvanceSearch.setBackgroundResource(R.drawable.rounded_small_corner_search);
        // txtAdvanceSearch.setBackgroundResource(R.drawable.rounded_corner_home);
        txtAdvanceSearch.setTextColor(getResources().getColor(R.color.color_font_white));
        txtAdvanceSearch.setTextSize(17);
        txtSearch.setVisibility(View.GONE);

        lytPhNo.setVisibility(View.VISIBLE);
        lytCountryCode.setVisibility(View.VISIBLE);
        lytArrive.setVisibility(View.VISIBLE);
        lytDepart.setVisibility(View.VISIBLE);
        lytFlightNumber.setVisibility(View.VISIBLE);
        lytDepartDate.setVisibility(View.VISIBLE);
        lytPnr.setVisibility(View.VISIBLE);
        lytFirstName.setVisibility(View.VISIBLE);
        lytLastName.setVisibility(View.VISIBLE);
        lytEmail.setVisibility(View.VISIBLE);
        lytPnr.setVisibility(View.GONE);

        viewDepart.setVisibility(View.VISIBLE);
        viewArrive.setVisibility(View.VISIBLE);
        viewDepartDate.setVisibility(View.VISIBLE);
        viewFlightNumber.setVisibility(View.VISIBLE);
        viewPnr.setVisibility(View.VISIBLE);
        viewFirstName.setVisibility(View.VISIBLE);
        viewLastName.setVisibility(View.VISIBLE);
        viewEmail.setVisibility(View.VISIBLE);
        viewPnr.setVisibility(View.GONE);


    }
   /* public void UpdateUiForNormal(){
        lytPhNo.setVisibility(View.GONE);
        lytCountryCode.setVisibility(View.GONE);
        lytArrive.setVisibility(View.GONE);
        lytDepart.setVisibility(View.GONE);
        lytFlightNumber.setVisibility(View.GONE);
        lytDepartDate.setVisibility(View.GONE);
        lytPnr.setVisibility(View.VISIBLE);
        lytFirstName.setVisibility(View.GONE);
        lytLastName.setVisibility(View.VISIBLE);
        lytEmail.setVisibility(View.VISIBLE);

        lytPnr.setVisibility(View.VISIBLE);

        viewDepart.setVisibility(View.GONE);
        viewArrive.setVisibility(View.GONE);
        viewDepartDate.setVisibility(View.GONE);
        viewFlightNumber.setVisibility(View.GONE);
        viewPnr.setVisibility(View.GONE);
        viewFirstName.setVisibility(View.GONE);
        viewLastName.setVisibility(View.GONE);
        viewEmail.setVisibility(View.GONE);

        viewPnr.setVisibility(View.VISIBLE);
    }*/
    /**
     * Updating UI
     */
    private void updateUI() {
        editArrive = (AutoCompleteTextView) view.findViewById(R.id.editArrive);
        editDepart = (AutoCompleteTextView) view.findViewById(R.id.editDepart);


        editFlightNumber = (OTEditText) view.findViewById(R.id.editFlightNumber);
        editFirstName = (OTEditText) view.findViewById(R.id.editFirstName);
        editLastName = (OTEditText) view.findViewById(R.id.editLastName);
        editPhoneNumber = (OTEditText) view.findViewById(R.id.editPhoneNumber);
        editEmail = (OTEditText) view.findViewById(R.id.editEmail);
        editPNR = (OTEditText) view.findViewById(R.id.editPNR);

        imgPNR = (NetworkImageView) view.findViewById(R.id.imgPNR);
        imgEmail = (NetworkImageView) view.findViewById(R.id.imgEmail);
        img_airline = (NetworkImageView) view.findViewById(R.id.img_airline);
        imgDepart = (NetworkImageView) view.findViewById(R.id.imgDepart);
        imgArrive = (NetworkImageView) view.findViewById(R.id.imgArrive);
        imgDepartDate = (NetworkImageView) view.findViewById(R.id.imgDepartDate);
        imgFlightNo = (NetworkImageView) view.findViewById(R.id.imgFlightNo);
        imgFirstUser = (NetworkImageView) view.findViewById(R.id.imgFirstUser);
        imgLastUser = (NetworkImageView) view.findViewById(R.id.imgLastUser);
        imgCell = (NetworkImageView) view.findViewById(R.id.imgCell);

        lytPhNo = (RelativeLayout) view.findViewById(R.id.lytPhNo);
        lytCountryCode = (RelativeLayout) view.findViewById(R.id.lytCountryCode);
        lytPhonExtension = (LinearLayout) view.findViewById(R.id.lytPhonExtension);
        lytAirlines = (LinearLayout) view.findViewById(R.id.lytAirlines);

        lytDepart = (LinearLayout) view.findViewById(R.id.lytDepart);
        lytArrive = (LinearLayout) view.findViewById(R.id.lytArrive);
        lytDepartDate = (LinearLayout) view.findViewById(R.id.lytDepartDate);
        lytFlightNumber = (LinearLayout) view.findViewById(R.id.lytFlightNumber);
        lytPnr = (LinearLayout) view.findViewById(R.id.lytPnr);
        lytFirstName = (LinearLayout) view.findViewById(R.id.lytFirstName);
        lytLastName = (LinearLayout) view.findViewById(R.id.lytLastName);
        lytEmail = (LinearLayout) view.findViewById(R.id.lytEmail);

        viewEmail = view.findViewById(R.id.viewEmail);
        viewDepart = view.findViewById(R.id.viewDepart);
        viewArrive = view.findViewById(R.id.viewArrive);
        viewDepartDate = view.findViewById(R.id.viewDepartDate);
        viewFlightNumber = view.findViewById(R.id.viewFlightNumber);
        viewPnr = view.findViewById(R.id.viewPnr);
        viewFirstName = view.findViewById(R.id.viewFirstName);
        viewLastName = view.findViewById(R.id.viewLastName);
        viewEmail = view.findViewById(R.id.viewEmail);

        txtSearch = (OTTextView) view.findViewById(R.id.txtSearch);
        txtAdvanceSearch = (OTTextView) view.findViewById(R.id.txtAdvanceSearch);
        txtAirlineLabel = (OTTextView) view.findViewById(R.id.txtAirlineLabel);
        txtAirlineName = (OTTextView) view.findViewById(R.id.txtAirlineName);
        txtCellNumberLabel = (OTTextView) view.findViewById(R.id.txtCellNumberLabel);
        txtDepartDate = (OTTextView) view.findViewById(R.id.txtDepartDate);
        txtCountryCode = (OTTextView) view.findViewById(R.id.txtCountryCode);
        txtDepartLabel = (OTTextView) view.findViewById(R.id.txtDepartLabel);
        txtArriveLabel = (OTTextView) view.findViewById(R.id.txtArriveLabel);
        txtDepartDateLabel = (OTTextView) view.findViewById(R.id.txtDepartDateLabel);
        txtFlightNoLabel = (OTTextView) view.findViewById(R.id.txtFlightNoLabel);
        txtPnrLabel = (OTTextView) view.findViewById(R.id.txtPnrLabel);
        txtFirstNameLabel = (OTTextView) view.findViewById(R.id.txtFirstNameLabel);

        txtMessage = (OTTextView) view.findViewById(R.id.txtMessage);

        txtLastNameLabel = (OTTextView) view.findViewById(R.id.txtLastNameLabel);
        txtEmailLabel = (OTTextView) view.findViewById(R.id.txtEmailLabel);

        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        imgErroWala = (ImageView) view.findViewById(R.id.imgErroWala);

        lytFAQ = (LinearLayout) view.findViewById(R.id.lytFAQ);
        lytFAQ.setOnClickListener(this);

      //  txtAirlineName.setText(getString(R.string.string_airline_without_star));

        txtCountryCode.setOnClickListener(this);
        txtSearch.setOnClickListener(this);
        txtAdvanceSearch.setOnClickListener(this);
        // txtAirlineName.setOnClickListener(this);
        lytAirlines.setOnClickListener(this);
        txtDepartDate.setOnClickListener(this);

        scrollCountryCode = (ScrollView) view.findViewById(R.id.scrollCountryCode);
        scrollParent = (ScrollView) view.findViewById(R.id.scrollParent);

        scrollParent.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                scrollCountryCode.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        Utils.setInterceptTouchEvent(new View[]{scrollCountryCode});
        Utils.setCountryCodeDefault(getActivity(), txtCountryCode);

        addTextChangeListener();

    }

    //Updating lable UI while entering data in text fields
    private void addTextChangeListener() {

        editPNR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtPnrLabel.setVisibility(editPNR.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        txtAirlineName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtAirlineLabel.setVisibility(!txtAirlineName.getText().toString().equalsIgnoreCase("Airline")?View.VISIBLE:View.INVISIBLE);
                txtAirlineName.setTextColor(!txtAirlineName.getText().toString().equalsIgnoreCase("Airline")? getResources().getColor(R.color.color_font_black):getResources().getColor(R.color.color_gray_dark));
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtLastNameLabel.setVisibility(editLastName.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtEmailLabel.setVisibility(editEmail.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        editDepart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtDepartLabel.setVisibility(editDepart.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editArrive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtArriveLabel.setVisibility(editArrive.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        txtDepartDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtDepartDateLabel.setVisibility(txtDepartDate.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
                txtDepartDate.setTextColor(txtDepartDateLabel.getVisibility()==View.VISIBLE? getResources().getColor(R.color.color_font_black):getResources().getColor(R.color.color_gray_dark));
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editFlightNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtFlightNoLabel.setVisibility(editFlightNumber.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtFirstNameLabel.setVisibility(editFirstName.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtCellNumberLabel.setVisibility(editPhoneNumber.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public void onAttach(Activity activity) {
        Utils.DEBUG("LegProductSearchFragment >> onAttach(Activity) called");
        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }


    public void initializeCalendar() {
        final CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        Calendar c = Calendar.getInstance();
        System.out.println("added time => " + c.getTime());
        Date startDate = c.getTime();
        caldroidFragment.setMinDate(startDate);

        android.support.v4.app.FragmentTransaction t = fragmentActivity.getSupportFragmentManager().beginTransaction();
        t.add(R.id.lytMain, caldroidFragment, caldroidFragment.getClass().getName());
        t.addToBackStack(caldroidFragment.getClass().getName());
        t.commit();

        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                //Utils.showToast(getActivity(), Utils.converDateToFormat_ddMMMyyyy(date));

                departDate = Utils.converDateToFormat_mmddyyyy(date);
                txtDepartDate.setText(departDate);
                // txtDepartDate.setText(departDate);
                //txtDepartDate.setError("");

                android.support.v4.app.FragmentManager manager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                manager.popBackStack();
            }
        });

        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ViewGroup.LayoutParams layoutParams = caldroidFragment.getView().getLayoutParams();
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                caldroidFragment.getView().setLayoutParams(layoutParams);
            }
        }.sendEmptyMessage(0);
    }

    /**
     * @param searchType
     */
    private void validateInputs(String searchType) {
        Utils.DEBUG("Country Name >> " + txtCountryCode.getText().toString().trim());
        pMobileCC = Utils.getCountryIdExt(countryExtData.getData(), txtCountryCode.getText().toString().trim());
        Utils.DEBUG("Country Id2 " + pMobileCC);
        Utils.DEBUG("arriveAirlineCode " + arriveAirlineCode);
        Utils.DEBUG("departAirlineCode " + departAirlineCode);

        airlineName = txtAirlineName.getText().toString().trim();
        depart = editDepart.getText().toString().trim();
        arrive = editArrive.getText().toString().trim();
        flightNumber = editFlightNumber.getText().toString().trim();
        firstName = editFirstName.getText().toString().trim();
        lastName = editLastName.getText().toString().trim();
        phoneNumber = editPhoneNumber.getText().toString().trim();
        phExtension = txtCountryCode.getText().toString().trim();
        pnr = editPNR.getText().toString().trim();
        email = editEmail.getText().toString().trim();

        departDate = txtDepartDate.getText().toString().trim();

        if (searchType.equalsIgnoreCase("Search")) {
            ArrayList<String> listError = validateNormalSearch();
            lytErrorMessage.removeAllViews();
            if (listError.size() > 0) {
                lytError.setVisibility(View.VISIBLE);
                imgErroWala.setVisibility(View.VISIBLE);
                scrollParent.fullScroll(ScrollView.FOCUS_UP);
                //show error message
                for (int index = 0; index < listError.size(); index++) {
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), listError.get(index).toString()));
                }
            } else {

                lytError.setVisibility(View.GONE);
                //call api
                SearchHelper mSearchHelper = new SearchHelper();
                mSearchHelper.setCountryId(sharedPrefs.get(getString(R.string.key_selected_country_id)).toString());
                mSearchHelper.setIsSearchBy("1");
                mSearchHelper.setLanguageId(sharedPrefs.get(getString(R.string.key_selected_language_id)).toString());
                mSearchHelper.setTgProductId(sharedPrefs.get(getString(R.string.key_selected_productId)).toString());
                mSearchHelper.setLastName(lastName);
                mSearchHelper.setMarketingAirlineId(sharedPrefs.get(getString(R.string.key_LP_selected_airline_id)).toString());
                mSearchHelper.setPnr(pnr);
                mSearchHelper.setOCN("");

                switch (sharedPrefs.get(getString(R.string.key_selected_productId)).toString()) {
                    case "1":
                        // LegProductReviewFragment
                        Utils.moveToFragment(getActivity(), new LegProductSearchResultFragment(), mSearchHelper, 0);
                        break;
                    case "5":
                        Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), mSearchHelper, 0);
                        break;
                    case "6":
                        Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), mSearchHelper, 0);
                        break;
                    case "8":
                        Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), mSearchHelper, 0);
                        break;
                    case "7":
                        Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), mSearchHelper, 0);
                        break;
                    case "19":
                        Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), mSearchHelper, 0);
                        //   Utils.moveToFragment(getActivity(), new FlexibilityRewardSearchResultFragment(), null, 0);
                        break;
                    case "18":
                       // Utils.showToast(getActivity(), "Carry-On Baggage");
                        Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), mSearchHelper, 0);
                        break;

                    case "2":
                        //Utils.showToast(getActivity(), "Under Development");
                        //  Utils.moveToFragment(getActivity(), new PreferredFlightSearchResultFragment(), null, 0);
                        SearchForSeg(mSearchHelper.getTgProductId(), mSearchHelper.getLanguageId(), mSearchHelper.getCountryId(),
                                mSearchHelper.getMarketingAirlineId(), mSearchHelper.getPnr(), mSearchHelper.getLastName(),
                                mSearchHelper.getIsSearchBy(), mSearchHelper.getOCN(), mSearchHelper.getArriveAirlineCode(),
                                mSearchHelper.getDepartAirlineCode(),
                                mSearchHelper.getArriveArptCode(), mSearchHelper.getDepartArptCode(), mSearchHelper.getDepartDate(),
                                mSearchHelper.getFlightNumber(), mSearchHelper.getCountryId(),
                                mSearchHelper.getMobileNumber(), mSearchHelper.getEmail(), mSearchHelper.getFirstName());
                        break;
                    case "22":
                        //Utils.showToast(getActivity(), "Special Baggage");
                        Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), mSearchHelper, 0);
                        break;
                    default:
                        Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), mSearchHelper, 0);

                }
            }
        } else {
            if (airlineName.equalsIgnoreCase("Airline")) {
                lytError.setVisibility(View.VISIBLE);
                lytErrorMessage.removeAllViews();
                lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), "Select Any Airline"));

            } else if (depart.equalsIgnoreCase("")) {
                editDepart.requestFocus();
                editDepart.setError(getString(R.string.input_mandatory));
            } else if (arrive.equalsIgnoreCase("")) {

                editArrive.requestFocus();
                editArrive.setError(getString(R.string.input_mandatory));
            } else if (departDate.equalsIgnoreCase("")) {

                txtDepartDate.setError(getString(R.string.input_mandatory));

            } else if (flightNumber.equalsIgnoreCase("")) {
                editFlightNumber.requestFocus();
                editFlightNumber.setError(getString(R.string.input_mandatory));
            } else if (firstName.equalsIgnoreCase("")) {
                editFirstName.requestFocus();
                editFirstName.setError(getString(R.string.input_mandatory));

            } else if (lastName.equalsIgnoreCase("")) {
                editLastName.requestFocus();
                editLastName.setError(getString(R.string.input_mandatory));

            } else if (phExtension.equalsIgnoreCase("")) {
                Utils.showToast(view.getContext(), "Select Phone Code");
            } else if (phoneNumber.equalsIgnoreCase("")) {
                editPhoneNumber.requestFocus();
                editPhoneNumber.setError(getString(R.string.input_mandatory));

            } else if (email.equalsIgnoreCase("")) {
                editEmail.requestFocus();
                editEmail.setError(getString(R.string.string_enter_your_email_address));

            } else if (!Utils.isValidEmail(email)) {
                editEmail.requestFocus();
                editEmail.setError(getString(R.string.enter_valid_email));

            } else {

                SearchHelper mSearchHelper = new SearchHelper();
                mSearchHelper.setCountryId(sharedPrefs.get(getString(R.string.key_selected_country_id)).toString());
                mSearchHelper.setIsSearchBy("0");
                mSearchHelper.setLanguageId(sharedPrefs.get(getString(R.string.key_selected_language_id)).toString());
                mSearchHelper.setTgProductId(sharedPrefs.get(getString(R.string.key_selected_productId)).toString());
                mSearchHelper.setLastName(lastName);
                mSearchHelper.setMarketingAirlineId(sharedPrefs.get(getString(R.string.key_LP_selected_airline_id)).toString());
                mSearchHelper.setArriveAirlineCode(arriveAirlineCode);
                mSearchHelper.setDepartAirlineCode(departAirlineCode);
                mSearchHelper.setArriveArptCode(arrivalid);
                mSearchHelper.setDepartArptCode(departureid);
                mSearchHelper.setDepartDate(departDate);
                mSearchHelper.setEmail(email);
                mSearchHelper.setFirstName(firstName);
                mSearchHelper.setFlightNumber(flightNumber);
                mSearchHelper.setMobileCode(pMobileCC);
                mSearchHelper.setMobileNumber(phoneNumber);
                mSearchHelper.setOCN("");
                loader.show();
                SearchForUto(mSearchHelper.getTgProductId(), mSearchHelper.getLanguageId(), mSearchHelper.getCountryId(),
                        mSearchHelper.getMarketingAirlineId(), mSearchHelper.getPnr(), mSearchHelper.getLastName(),
                        mSearchHelper.getIsSearchBy(), mSearchHelper.getOCN(), mSearchHelper.getArriveAirlineCode(),
                        mSearchHelper.getDepartAirlineCode(),
                        mSearchHelper.getArriveArptCode(), mSearchHelper.getDepartArptCode(), mSearchHelper.getDepartDate(),
                        mSearchHelper.getFlightNumber(), mSearchHelper.getCountryId(),
                        mSearchHelper.getMobileNumber(), mSearchHelper.getEmail(), mSearchHelper.getFirstName());


               /* switch (productID) {
                    case "1":
                        Utils.moveToFragment(getActivity(), new LegProductSearchResultFragment(), null, 0);
                        break;
                    case "5":
                        Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), null, 0);
                        break;
                    case "6":
                        Utils.moveToFragment(getActivity(), new PreferredSeatSearchResultFragment(), null, 0);
                        break;
                    case "8":
                        Utils.moveToFragment(getActivity(), new ExtraBaggageSearchResultFragment(), null, 0);
                        break;
                    case "7":
                        Utils.moveToFragment(getActivity(), new LoungeAccessSearchResultFragment(), null, 0);
                        break;
                    case "4":
                        Utils.moveToFragment(getActivity(), new FlexibilityRewardSearchResultFragment(), null, 0);
                        break;

                    case "2":
                        Utils.moveToFragment(getActivity(), new PreferredFlightSearchResultFragment(), null, 0);
                        break;
                    case "3":
                        Utils.moveToFragment(getActivity(), new MultipleBookingSearchResultFragment(), null, 0);
                        break;
                }*/

            }
        }
    }

    private ArrayList<String> validateNormalSearch() {
        ArrayList<String> listError = new ArrayList<>();
            try {
                if (airlineName.equalsIgnoreCase("Airline")) {
                    listError.add(getString(R.string.string_select_airline_associated_to_your_reference));
                }
                if (pnr.equalsIgnoreCase("")) {
                    listError.add(getString(R.string.pnr_validation));
                }
                if (lastName.equalsIgnoreCase("")) {
                    listError.add(getString(R.string.string_enter_last_name));
                }
                if (!email.equalsIgnoreCase("") && (!Utils.isValidEmail(email))) {
                    listError.add(getString(R.string.enter_valid_email));
                }
            } catch (Exception e) {
                Utils.ERROR("" + e.toString());
            }
        return listError;
    }

    private void SearchForUto(String productID, String LanguageId, String CountryId, String MarketingAirlineId,
                              String pnr, String lastName, String isSearchBy, String OCN,
                              String arriveArptId, String departArptId, String departArptCode, String arriveArptCode, String dateOfJourney,
                              String flightNumber, String countryCode, String number, String emailAddress, String fname) {

        String tag_json_obj = "json_obj_req";
        String url;
        if (isSearchBy.equalsIgnoreCase("0")) {
            url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_DetailPnrSearch);
        } else {
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
            loader.dismiss();
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
                        //{"response":"Your request has been submitted successfully. We will get back to you within 6 hours.","displaySelect":false}
                        JSONObject jsonObject;
                        boolean displaySelect;
                        try {
                            jsonObject = new JSONObject(response.toString());
                            if (!jsonObject.optBoolean("displaySelect")) {

                                txtMessage.setVisibility(View.VISIBLE);
                                txtMessage.setText(jsonObject.optString("response"));
                                txtMessage.setFocusable(true);
                                //Utils.showToast(getActivity(), jsonObject.optString("response"));
                            } else {
                            }
                        } catch (Exception ex) {
                            loader.dismiss();
                        }
                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                loader.dismiss();
                 Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));


            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public void onResume() {
        super.onResume();
        editPNR.clearFocus();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtDepartDate:
                initializeCalendar();
                break;


            case R.id.txtCountryCode:
                Utils.createDropdownView(Utils.getCountryListExtArray(countryExtData.getData()), lytPhonExtension, txtCountryCode, new LinearLayout[]{});
                break;
            case R.id.txtSearch:
                validateInputs(getString(R.string.string_search));

                break;
            case R.id.txtAdvanceSearch:
                if (txtAdvanceSearch.getText().toString().equals(getString(R.string.string_search))) {
                    validateInputs("Advance");
                } else {
                    updateUIForAdvanceSearch();
                }


                break;


            case R.id.lytAirlines:
                //  Utils.moveToFragment(getActivity(), new FlightPassSearchSelectFragment(), flightPassDealData, SearchSelectRecyclerViewAdapter.VIEW_TYPE_AIRLINE);
                Utils.moveToFragment(getActivity(), new LegProductSearchSelectFragment(), homeData, 0);
                // txtAirlineLabel.setText("Airline");
                updateUI();

                break;


        }
    }

    public void updateSelectedAirline(FragmentCommunicationData message) {
        Utils.DEBUG("updateSelectionDone() called : selected airline : " + message.getSelectedAirline());
        lytError.setVisibility(View.GONE);
        txtAirlineName.setText(message.getSelectedAirline());
    }


    private void callHomePassBannerAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_HOME_PASS_BANNER);

        loader.show();
        JSONObject requestObject = new JSONObject();
        try {

            requestObject.put("TgProductId", productID);
            requestObject.put("CountryId", Utils.getSelectedCountryId(getActivity()));
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(getActivity()) + "");
            requestObject.put("AirlineId", "0");


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
                        Utils.DEBUG("LegProductResponse : " + response.toString());
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

    private void arriveDepartData(Home homeData) {
        HomePageData mHomePageData = homeData.getHomePageData();
        List<ArriveDropDownList> arriveList = mHomePageData.getArriveDropDownList();
        final AirlineArriveDepartAdapter mAirlineArriveDepartAdapter = new AirlineArriveDepartAdapter(getActivity(), R.layout.dropdown_item_row, arriveList);
        editDepart.setAdapter(mAirlineArriveDepartAdapter);
        editDepart.setThreshold(1);
        editArrive.setThreshold(1);
        editArrive.setAdapter(mAirlineArriveDepartAdapter);


    }


    @Override
    public void onBackEventPre() {


    }

    @Override
    public void onBackEventPost() {

        //UpdateUiForNormal();
    }

    @Override
    public void onFocusEvent() {
        if (getActivity() == null)
            return;

        try {
            searchError = (Boolean) sharedPrefs.get(getString(R.string.is_search_error));
        } catch (Exception e) {
            e.printStackTrace();
            searchError = false;
        }
        if (searchError && AppVariables.searchResult) {
            lytError.setVisibility(View.VISIBLE);
            lytErrorMessage.removeAllViews();
            txtAirlineName.setText((String) sharedPrefs.get(getString(R.string.key_LP_selected_airline_tag)));
            String errorMsg = (String) sharedPrefs.get(getString(R.string.search_error_msg));
            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), errorMsg));
            AppVariables.searchResult = false;
            //  Snackbar.make((RelativeLayout) view.findViewById(R.id.relSearchLayout), error,Snackbar.LENGTH_SHORT).show();
        } else {
            // lytError.setVisibility(View.GONE);
        }

    }
    private void SearchForSeg(String productID, String LanguageId, String CountryId, String MarketingAirlineId,
                              String pnr, String lastName, String isSearchBy, String OCN,
                              String arriveArptId, String departArptId, String departArptCode, String arriveArptCode, String dateOfJourney,
                              String flightNumber, String countryCode, String number, String emailAddress, String fname) {

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();
        String tag_json_obj = "json_obj_req";
        String url = null;
        if (isSearchBy.equalsIgnoreCase("0")) {
            url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_DetailPnrSearch);
        }
        if (isSearchBy.equalsIgnoreCase("1")) {
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
                        Utils.DEBUG("Seg search data : " + response.toString());

                        SegInputData data = ParseManager.getInstance().fromJSON(response, SegInputData.class);

                        if(data == null)
                        {
                            return;
                        }
                        else if(data.getErrorExists())
                        {
                            lytError.setVisibility(View.VISIBLE);
                            lytErrorMessage.removeAllViews();
                            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), data.getErrorMessage()));
                        }
                        else
                        {
                            Utils.moveToFragment(getActivity(), new SegInputDetailsFragment(), data, 0);
                        }
                        loader.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                loader.hide();
                Utils.showToast(getActivity(), "Server Timeout");

            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

}

package com.optiontown.app.view.fragment.login;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;

import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.login.LogoutData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.model.session.CountryListAPI;
import com.optiontown.app.model.session.SessionIdData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by amit on 02-07-2016.
 */
public class DashboardFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    private OTTextView txtMyProfile;
    private OTTextView txtWelcome;
    private OTTextView txtUsernameEmail;
    private OTTextView txtEmailAddress;
    private LoginData loginData;
    private OTTextView txtLogout;
    InternationalizeData localization;
    NetworkImageView imgCountry;
    OTTextView txtUserName;
    private CheckBox checkbox_fingerprint;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        loginData = ((LoginData) getArguments().getSerializable(getString(R.string.key_serializable)));
        Utils.DEBUG("loginData json : " + ParseManager.getInstance().toJSON(loginData));

        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        try
        {
            loginData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLoginData(getActivity())), LoginData.class);

            getUIReference();

            updateUI();
        }
        catch (Exception e)
        {
            Utils.ERROR("Utils >> Error while parsing json : " + e.toString());
        }

        return view;

    }

    private void updateUI()
    {
        SessionIdData sessionIdData = null;
        try {
            sessionIdData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(getActivity())), SessionIdData.class);
        } catch (Exception e) {
            Utils.ERROR("MainActivity >> Error while parsing json : " + e.toString());
        }
        final ImageLoader imageLoader = AppController.getInstance().getImageLoader();


        txtWelcome.setText(loginData.getMessage());
        txtUsernameEmail.setText(loginData.getEmail());
        txtEmailAddress.setText(loginData.getEmail());
        txtUserName.setText(loginData.getCompleteProfile().getFirstName());
        imgCountry.setImageUrl(getCountryLogoURL(Utils.getUserSelectedCountryId(getActivity()), sessionIdData.getCountryListAPI()), imageLoader);

    }

    private String getCountryLogoURL(int userSelectedCountryId, CountryListAPI countryListAPI) {

        for (int index = 0; index < countryListAPI.getCountryList().size(); index++) {
            if (countryListAPI.getCountryList().get(index).getCountryID() == userSelectedCountryId) {
                return countryListAPI.getCountryList().get(index).getCountryLogo();
            }
        }
        return "";
    }

    private void getUIReference()
    {
        txtUserName = (OTTextView) view.findViewById(R.id.txtUserName);
        imgCountry = (NetworkImageView) view.findViewById(R.id.imgCountry);
        txtWelcome = (OTTextView) view.findViewById(R.id.txtWelcome);
        txtUsernameEmail = (OTTextView) view.findViewById(R.id.txtUsernameEmail);
        txtEmailAddress = (OTTextView) view.findViewById(R.id.txtEmailAddress);

        txtMyProfile = (OTTextView) view.findViewById(R.id.txtMyProfile);
        txtMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new MyProfileFragment(), null, 0);
            }
        });

        txtLogout = (OTTextView) view.findViewById(R.id.txtLogout);
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                callLogoutApi();
            }
        });

        checkbox_fingerprint = (CheckBox) view.findViewById(R.id.checkbox_fingerprint);
        checkbox_fingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Utils.checkingFingerprint_isEnabled(getActivity()))
                {
                    // save user credentials for fingerprint
                    if(checkbox_fingerprint.isChecked() == true)
                    {
                        Utils.setUsernameForFingerprint(getActivity(), Utils.getUsername(getActivity()));
                        Utils.setPasswordForFingerprint(getActivity(), Utils.getPassword(getActivity()));
                    }
                    else
                    {
                        Utils.setUsernameForFingerprint(getActivity(), null);
                        Utils.setPasswordForFingerprint(getActivity(), null);
                    }
                }
                else
                {
                    checkbox_fingerprint.setChecked(false);
                    Utils.showToast(getActivity(), "Set up a fingerprint in your Settings\\nRegister atleast one fingerprint.");
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Utils.checkingFingerprint_isHardwareDetected(getActivity()))
        {
            checkbox_fingerprint.setVisibility(View.VISIBLE);
            if(Utils.getUsernameForFingerprint(getActivity()) == null)
            {
                checkbox_fingerprint.setChecked(false);
            }else
            {
                checkbox_fingerprint.setChecked(true);
            }

        }else {

            checkbox_fingerprint.setVisibility(View.GONE);
            checkbox_fingerprint.setChecked(false);
        }

        localizUI();
    }



    private void localizUI() {

        ((OTTextView) view.findViewById(R.id.txtMyOptiontownLabel)).setText(localization.getLABLJoinOptiontownLabel());
        ((OTTextView) view.findViewById(R.id.txtEmailAddressLabel)).setText(localization.getLABLEmailLabel());
        ((OTTextView) view.findViewById(R.id.txtMyProfileDescription)).setText(localization.getLABLMAHomeUpdateLabel());
        ((OTTextView) view.findViewById(R.id.txtMyTrips)).setText(localization.getLABLMyTripsLabel());
        ((OTTextView) view.findViewById(R.id.txtMyTripsDescription)).setText(localization.getLABLMAHomeSaveLabel());
        ((OTTextView) view.findViewById(R.id.txt_joinbuz)).setText(localization.getLABLTGPAlertBoxHeadingLabel());
        ((OTTextView) view.findViewById(R.id.txt_signup)).setText(localization.getLABLTGPAlertBoxHelpLabel());
        txtLogout.setText(localization.getLABLLogoutLabel());

    }

    public void updateDashboardLoginData(FragmentCommunicationData fragmentCommunicationData)
    {
        loginData = fragmentCommunicationData.getLoginData();
        updateUI();
    }

    private void callLogoutApi()
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_GET_LOGIN) + getString(R.string.URL_LOGOUT);


        JSONObject requestObject = new JSONObject();
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
                        Utils.DEBUG("onResponse() called : " + response.toString());

                        LogoutData logoutData = ParseManager.getInstance().fromJSON(response, LogoutData.class);
                        if(logoutData.getMessage().equals("done"))
                        {
                            //removed record from local, user logged out
                            Utils.setLoginData(getActivity(), null);

                            //clear all back stack
                            Utils.clearAllBackStack(getActivity());

                            loader.dismiss();

                            Utils.moveToFragment(getActivity(), new FlightPassFragment(), null, 0);
                        }


                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}

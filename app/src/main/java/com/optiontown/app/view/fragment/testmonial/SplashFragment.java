package com.optiontown.app.view.fragment.testmonial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.intro.IntroFragment;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.model.session.CountryListAPI;
import com.optiontown.app.model.session.SessionIdData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.DownloadIntentService;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.HomeFragment;
import com.optiontown.app.view.fragment.login.MyProfileFragment;

import org.json.JSONObject;

/**
 * Created by amit on 23-07-2016.
 */
public class SplashFragment extends BaseFragment {
    AppDialogLoader loader = null;
    private AppSharedPrefs sp;
    private Communicator communicator;
    private RelativeLayout lytTopBar;
    private SessionIdData sessionData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        loader = AppDialogLoader.getLoader(getActivity());
        sp = AppSharedPrefs.getInstance(getActivity());

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());


        lytTopBar = (RelativeLayout) getActivity().findViewById(R.id.lytTopBar);
        lytTopBar.setVisibility(View.GONE);

        //first call session api
        callSessionIdAPI();

        //  moveToPreferredSearchFragment();

        return view;
    }

   /* private void moveToPreferredSearchFragment() {
        FragmentManager manager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new PreferredSeatSearchResultFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }
*/

    /**
     * call to add home fragment in current activity
     */
    private void moveToHomeFragment() {
        if(getActivity().getResources().getBoolean(R.bool.bool_show_intro)) {
            // if (Utils.isFirstTimeLaunch(getActivity())) {

            FragmentManager manager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment f = new IntroFragment();
            transaction.add(R.id.lytMain, f);
            transaction.commit();
        /*} else {
            //Utils.moveToFragment(getActivity(), new HomeFragment(), null, 0);
            FragmentManager manager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment f = new HomeFragment();
            transaction.add(R.id.lytMain, f);
            transaction.commit();
        }*/
        }
        else {
            FragmentManager manager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment f = new HomeFragment();
            transaction.add(R.id.lytMain, f);
            transaction.commit();
        }
    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("LoginFragment >> onAttach(Activity) called");
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    private void callSessionIdAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SESSION_ID);


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("tgProductId", Integer.toString(getResources().getInteger(R.integer.value_tgProductId_fpo)));

            /*
            *.. when user hit session first time languageId and countryId is 0... */
           /* if()
            */

            /*SessionIdData sessionIdData = null;
            try {
                sessionIdData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(getActivity())), SessionIdData.class);
            } catch (Exception e) {
                Utils.ERROR("MainActivity >> Error while parsing json : " + e.toString());
            }

            if (sessionIdData == null || sessionIdData.getCountryListAPI() == null) {
                requestObject.put("CountryId","0");
                requestObject.put("LanguageId","0");
            }else{
                requestObject.put("CountryId",sessionIdData.getCountryId()+"");
                requestObject.put("LanguageId",sessionIdData.getLanguageId()+"");
            }*/

            requestObject.put("CountryId", Utils.getUserSelectedCountryId(getActivity()) + "");
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(getActivity()) + "");
            requestObject.put("isFromMyaccountPage", "1");

        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }

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
                        Utils.DEBUG("onResponse() sessionAPI called : " + response.toString());
                        sessionData = ParseManager.getInstance().fromJSON(response, SessionIdData.class);

                        System.out.println("Country Id : ... " + sessionData.getCountryId());
                        System.out.println("Language Id : ... " + sessionData.getLanguageId());
                        //save
                        Utils.setSessionData(getActivity(), response.toString());
                        Utils.setUserSelectedCountryId(getActivity(), sessionData.getCountryId());
                        Utils.setUserSelectedLanguageId(getActivity(), sessionData.getLanguageId());

                        //now we have country/language list, update 'change language/country' UI and functionality
                        FragmentCommunicationData data = new FragmentCommunicationData();
                        data.setFragmentName(new MainActivity().getClass().getName());
                        communicator.onResponse(data);

                        callCountryExtListAPI();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.hide();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    /**
     * call this method if want to download and set the image separately
     *
     * @param countryList
     */
    private void callServiceToDownloadCountryImage(CountryListAPI countryList) {
        if (countryList == null) {
            return;
        }
        String s = ParseManager.getInstance().toJSON(countryList);
        Intent intent = new Intent(getActivity(), DownloadIntentService.class);
        intent.putExtra(getString(R.string.key_country_data), s);
        ((MainActivity) getActivity()).startService(intent);
    }


    private void callCountryExtListAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_COUNTRY_EXT_LIST);


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("LanguageID", Utils.getUserSelectedLanguageId(getActivity()) + "");
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
                        Utils.DEBUG("onResponse() countryAPI called : " + response.toString());
                        CountryExtData countryExtData = ParseManager.getInstance().fromJSON(response, CountryExtData.class);

                        //save
                        Utils.setCountryExtData(getActivity(), response.toString());

                        callApiForInternationalizeApp(sessionData.getLanguageId());


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

    private void callApiForInternationalizeApp(int Id) {
        loader.show();
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_API_LABELS);


        JSONObject requestObject = new JSONObject();
        try {
            //requestObject.put("tgProductId", Integer.toString(getResources().getInteger(R.integer.value_tgProductId_flight_pass)));
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
                        Utils.DEBUG("onResponse() localization called : " + response.toString());

                        //save
                        Utils.setInternationalLanguage(getActivity(), response.toString());

                        if (Utils.isUserLoggedIn(getActivity()) && Utils.isValidEmailAddress(getActivity(), Utils.getUsername(getActivity()))) {
                            //call session api before calling login api
                            callLoginApi();
                        } else {
                            loader.hide();
                            lytTopBar.setVisibility(View.VISIBLE);
                            moveToHomeFragment();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.hide();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


    private void callLoginApi() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_GET_LOGIN) + getString(R.string.URL_LOGIN);


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("customer.userName", Utils.getUsername(getActivity()));
            requestObject.put("customer.passwd", Utils.getPassword(getActivity()));
            requestObject.put("isFromMyaccountPage", "1");

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
                        LoginData loginData = ParseManager.getInstance().fromJSON(response, LoginData.class);

                        if (loginData.getResult().equals(getString(R.string.string_success))) {
                            //set login data
                            Utils.setLoginData(getActivity(), response.toString());
                        } else if (loginData.getResult().equals(getString(R.string.string_failure))) {
                            Utils.setLoginData(getActivity(), null);
                        }
                        loader.hide();
                        lytTopBar.setVisibility(View.VISIBLE);

                        //check password change required or not
                        if (loginData.getPasswordvalidationRequired().getPasswordRequired() == 1) {
                            //first clear stack
                            Utils.clearAllBackStack(getActivity());

                            //move to my profile page, asking user to change the password
                            Utils.moveToFragment(getActivity(), new MyProfileFragment(), loginData.getPasswordvalidationRequired().getUpdateErrorHelpMsg(), 0);
                        } else {
                            moveToHomeFragment();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                //Utils.showToast(getActivity(), getString(R.string.string_common_error_message));
                loader.hide();
            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}

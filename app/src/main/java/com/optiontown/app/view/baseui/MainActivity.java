package com.optiontown.app.view.baseui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.adapter.ChangeCountryRecyclerViewAdapter;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.intro.IntroFragment;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.fpogetpass.PassObject;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.redeem.RedeemSearchParamTwoWay;
import com.optiontown.app.model.redeem.SelectedPassDataForSearchFlight;
import com.optiontown.app.model.review.PassCMMIndexData;
import com.optiontown.app.model.segproduct.SegInputData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.model.session.CountryList;
import com.optiontown.app.model.session.CountryListAPI;
import com.optiontown.app.model.session.SessionIdData;
import com.optiontown.app.model.utosearchresult.UtosearchresultHome;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BFragment;
import com.optiontown.app.view.fragment.HomeFragment;
import com.optiontown.app.view.fragment.SelectFlightPassFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassSearchFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassSearchSelectFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassTravelZoneFragment;
import com.optiontown.app.view.fragment.fpo.redeem.OTDialogFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemAddPassengerFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemAddPaxIdentityFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemAddPaxInfoFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemSearchResultFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemViewDetailsFragment;
import com.optiontown.app.view.fragment.fpo.redeem.SearchFlightInputFragment;
import com.optiontown.app.view.fragment.fpo.redeem.SelectBookFlightFragment;
import com.optiontown.app.view.fragment.fpo.redeem.TestFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmb.MMBChangeFlightSelectDateFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmb.MMBPaymentFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPAddUserFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPChangePasswordFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.ManageMyPassFragment;
import com.optiontown.app.view.fragment.fpo.review.CustomizeFragment;
import com.optiontown.app.view.fragment.fpo.review.ReviewFragment;
import com.optiontown.app.view.fragment.learnmore.LearnMoreFragment;
import com.optiontown.app.view.fragment.legproducts.BoostMyPriorityFragment;
import com.optiontown.app.view.fragment.legproducts.FlightSeatViewFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductReviewFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductSearchFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductSearchResultFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductsHomeFragment;
import com.optiontown.app.view.fragment.login.CreateAccountFragment;
import com.optiontown.app.view.fragment.login.DashboardFragment;
import com.optiontown.app.view.fragment.login.LoginFragment;
import com.optiontown.app.view.fragment.login.MyProfileFragment;
import com.optiontown.app.view.fragment.segproducts.SegInputDetailsFragment;
import com.optiontown.app.view.fragment.segproducts.SegInputSelectFragment;
import com.optiontown.app.view.fragment.segproducts.SegSelectFlightFragment;
import com.optiontown.app.view.fragment.testmonial.SplashFragment;
import com.optiontown.app.view.fragment.testmonial.WriteTestimonialFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Random;


/**
 * Main UI(Launching) of the application, also contains the side navigation drawer
 *
 * @author amit
 */
public class MainActivity extends BaseActivity implements Communicator {


    AppDialogLoader loader = null;
    OTTextView txt_nav_changecountry, txt_nav_heading_buyflightpass, txt_nav_upgrade, txt_nav_flightpass, txt_nav_payless,
            txt_nav_emptyseat, txt_nav_preferredseat, txt_nav_extrabaggage, txt_nav_extrabag_sub, txt_nav_loungeaccess,
            txt_nav_loungeacc_sub, txt_nav_flexibiltyreward, txt_nav_flexibiltyrew_sub, txt_nav_bookflight, txt_nav_upgrade_uto,
            txt_nav_upgrade_uto_sub, txt_nav_upgrade_up, txt_nav_upgrade_up_sub, txt_nav_emptyseat_eso, txt_nav_emptyseat_eso_sub,
            txt_nav_emptyseat_esp, txt_nav_emptyseat_esp_sub, txt_nav_preferredseat_pso, txt_nav_preferredseat_pso_sub, txt_nav_preferredseat_psp,
            txt_nav_preferredseat_psp_sub;
    OTTextView txtLogin, txtCreateMyAccount;
    InternationalizeData labelLocalization;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            OTTextView txtInternetWarning = (OTTextView) findViewById(R.id.txtInternetWarning);
            if (Utils.isOnline(context)) {
                txtInternetWarning.setVisibility(View.GONE);
            } else {
                txtInternetWarning.setVisibility(View.VISIBLE);
            }
        }
    };
    private LinearLayout lytFlightOptionContainer;
    private LinearLayout lytMyFlightPassContainer;
    private RelativeLayout lytNotLoggedIn;
    private RelativeLayout lytLoggedIn;
    private AppSharedPrefs sp;
    private OTTextView txtName;
    private OTTextView txtEmail;
    private View headerView;
    private LinearLayout lytOptionPassContaier;

    AppSharedPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.DEBUG("MainActivity >> onCreate() called." + savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

        loader = AppDialogLoader.getLoader(this);
        prefs = AppSharedPrefs.getInstance(this);

        getViewReferences();

        moveToSplashFragment();

        getDeepLinkData();

      //  moveToPracticeFragment();
        //moveToIntroFragment();
        //moveToSeatView();
        //moveToSegSelect();
        //moveToSegInputDetailsFragment();
        //moveToMMPChangePasswordFragment();
        //moveToMMPaddUser();
        //moveToRedeemPAxInfoIdentity();
        //moveToRedeemPAxIdentity();
        //moveToRedeemAddPaxInfo();
        //moveToSelectBookFlightFragment();
        //moveToSearchSelectFlightInputFragment();
        //testing start
        //moveToFlightPassFragment();
        //moveToLegProductReviewFragment();
        //moveToHomeFragment();
        //moveToReviewFragment();
        //moveToCreateAccountFragment();
        //moveToMyProfileFragment();
        // moveToLegProductSearchFragment();
        //moveToCustomizeFragment();
        //moveToWriteTestimonial();
        //moveToRedeemSearchResultFragment();
        //moveToDropDownFragment();
        //moveToTestFragment();
        //moveToMap();
      //  moveToBoostMyPriorityFragment();
        //testing end

    }

    private void moveToIntroFragment() {

        Utils.moveToFragment(this, new IntroFragment(), null, 0);
    }

    private void moveToBoostMyPriorityFragment() {


        try {
            UtosearchresultHome utosearchresultHome = ParseManager.getInstance().fromJSON(new JSONObject(Utils.readFromAssets(this, "BoostMyPriority.txt")), UtosearchresultHome.class);

            Utils.moveToFragment(this,new BoostMyPriorityFragment(),utosearchresultHome.getIfsObject().get(1).getLegListObj().get(1),0);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void moveToSegInputDetailsFragment() {

            
        try {
            //String s = Utils.readFromAssets(this, "ItinerarySearchSeg_Both.txt");
            //String s = Utils.readFromAssets(this, "ItinerarySearchSeg_Connecting.txt");
            //String s = Utils.readFromAssets(this, "ItinerarySearchSeg_Outbound_NA_Return_NA.txt");
            //String s = Utils.readFromAssets(this, "ItinerarySearchSeg_Both.txt");
            //String s = Utils.readFromAssets(this, "ItinerarySearchSeg_Outbound_Only.txt");
            String s = Utils.readFromAssets(this, "ItinerarySearchSeg_1stNA_2nd_Available_3rd_Available.txt");
            SegInputData segInputData = ParseManager.getInstance().fromJSON(new JSONObject(s), SegInputData.class);

            Utils.moveToFragment(this, new SegInputDetailsFragment(), segInputData, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void getDeepLinkData()
    {
        /*Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        Utils.DEBUG("getDeepLinkData() >> " + action + "/" + data);
        //https://www.optiontown.com/signIn.do?processAction=SignIn
        if(data == null)
            return;
        Utils.DEBUG("getDeepLinkData() >> " + data.getAuthority());
        Utils.DEBUG("getDeepLinkData() >> " + data.getScheme());
        Utils.DEBUG("getDeepLinkData() >> " + data.getEncodedPath());
        Utils.DEBUG("getDeepLinkData() >> " + data.getEncodedQuery());
        Utils.DEBUG("getDeepLinkData() >> " + data.getHost());
        Utils.DEBUG("getDeepLinkData() >> " + data.getLastPathSegment());
        Utils.DEBUG("getDeepLinkData() >> " + data.getPath());
        Utils.DEBUG("getDeepLinkData() >> " + data.getQueryParameter("processAction"));
        Utils.DEBUG("getDeepLinkData() >> " + data.getSchemeSpecificPart());
        Utils.DEBUG("getDeepLinkData() >> " + data.getPathSegments());
        Utils.DEBUG("getDeepLinkData() >> " + data.getPort());
        Utils.DEBUG("getDeepLinkData() >> " + data.getQueryParameterNames());
        Utils.DEBUG("getDeepLinkData() >> " + data);*/


    }
    private void moveToSegSelect()
    {
        Utils.moveToFragment(this, new SegSelectFlightFragment(), null, 0);
    }
    private void moveToMap()
    {
        SelectedPassDataForSearchFlight selectedPassDataForSearchFlight = null;
        try {
            selectedPassDataForSearchFlight = ParseManager.getInstance().fromJSON(new JSONObject(Utils.readFromAssets(this, "Book_Flight_LabelData.txt")), SelectedPassDataForSearchFlight.class);
            selectedPassDataForSearchFlight.setTYPE_DIALOG(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OTDialogFragment dialog = new OTDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.key_serializable), (Serializable) selectedPassDataForSearchFlight);
        dialog.setArguments(bundle);

        dialog.show(((FragmentActivity) this).getSupportFragmentManager(), "");
    }

    private void moveToTestFragment() {
        Utils.moveToFragment(this, new TestFragment(), null, 0);
    }

    private void moveToDropDownFragment() {
        Utils.moveToFragment(this, new ManageMyPassFragment(), null, 0);
    }

    private void moveToRedeemSearchResultFragment() {
        /*RedeemSearchParam param = new RedeemSearchParam();
        param.setSelectedpassid("1000010011");
        param.setJourneyType("1");
        param.setNumberOfPax("1");
        param.setDepartDateJourney1("28-Sep-2016");
        param.setDepartAirportJourney1("18442");
        param.setArriveAirportJourney1("18434");*/

        RedeemSearchParamTwoWay param = new RedeemSearchParamTwoWay();
        param.setPassId("1000010011");
        param.setJourneyType("2");
        param.setNumberOfPax("1");
        param.setDepartDateJourney1("20-Sep-2016");
        param.setDepartDateJourney2("30-Sep-2016");
        param.setDepartAirportJourney1("18442");
        param.setArriveAirportJourney1("18434");

        Utils.moveToFragment(this, new RedeemSearchResultFragment(), param, 0);
    }

    private void moveToWriteTestimonial() {
        Utils.moveToFragment(this, new WriteTestimonialFragment(), null, 0);
    }

    private void moveToFlightPassFragment() {
        Utils.moveToFragment(this, new FlightPassFragment(), null, 0);
    }

    private void moveToCustomizeFragment() {
        PassCMMIndexData data = new PassCMMIndexData();
        data.setCmmIndex(0);
        data.setPassIndex(0);
        Utils.moveToFragment(this, new CustomizeFragment(), data, 0);
    }

    private void moveToLearnMoreFragment() {
        Utils.moveToFragment(this, new LearnMoreFragment(), null, 0);
    }

    private void moveToMyProfileFragment() {
        Utils.moveToFragment(this, new MyProfileFragment(), null, 0);
    }

    private void moveToCreateAccountFragment() {
        Utils.moveToFragment(this, new CreateAccountFragment(), null, 0);
    }

    private void moveToLegProductReviewFragment() {

        Utils.moveToFragment(this, new LegProductReviewFragment(), null, 0);
    }

    private void moveToRedeemPAxInfoIdentity() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new RedeemAddPaxInfoFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    private void moveToReviewFragment() {
        try {
            PassObject passObject = ParseManager.getInstance().fromJSON(new JSONObject(Utils.readFromAssets(this, "PassObject.txt")), PassObject.class);
            Utils.moveToFragment(this, new ReviewFragment(), passObject, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void moveToSearchSelectFlightInputFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new SearchFlightInputFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    private void moveToSelectBookFlightFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new SelectBookFlightFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    private void moveToRedeemPAxIdentity() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new RedeemAddPaxIdentityFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    private void moveToRedeemAddPaxInfo() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new RedeemAddPaxInfoFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    /**
     * call to add home fragment in current activity
     */
    private void moveToHomeFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new HomeFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    private void moveToSeatView() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new FlightSeatViewFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    private void moveToSplashFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new SplashFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }
    private void moveToMMPaddUser() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new MMPAddUserFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    private void moveToMMPChangePasswordFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new MMPChangePasswordFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    private void getViewReferences() {
        sp = AppSharedPrefs.getInstance(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageView imgDrawerIcon = (ImageView) findViewById(R.id.imgActionBarDrawerIcon);
        imgDrawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setDrawableLockMode(drawer,DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                closeDrawer();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //super.onDrawerOpened(drawerView);

                openDrawer();
            }
        };
        drawer.setDrawerListener(mDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        disableNavigationViewScrollbars(navigationView);

        headerView = navigationView.getHeaderView(0);
        setUIForSegProduct();
        setUIForSBoCBoProduct();

        lytFlightOptionContainer = (LinearLayout) headerView.findViewById(R.id.lytFlightOptionContainer);
        lytMyFlightPassContainer = (LinearLayout) headerView.findViewById(R.id.lytMyFlightPassContaier);

        lytOptionPassContaier = (LinearLayout) headerView.findViewById(R.id.lytOptionPassContaier);

        txt_nav_changecountry = (OTTextView) headerView.findViewById(R.id.txt_nav_changecountry);
        txt_nav_heading_buyflightpass = (OTTextView) headerView.findViewById(R.id.txt_nav_heading_buyflightpass);
        txt_nav_upgrade = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_upgrade);
        txt_nav_flightpass = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_flightpass);
        txt_nav_payless = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_payless);
        txt_nav_emptyseat = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_emptyseat);
        txt_nav_preferredseat = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_preferredseat);
        txt_nav_extrabaggage = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_extrabaggage);
        txt_nav_extrabag_sub = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_extrabag_sub);
        txt_nav_loungeaccess = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_loungeaccess);
        txt_nav_loungeacc_sub = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_loungeacc_sub);
        txt_nav_flexibiltyreward = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_flexibiltyreward);
        txt_nav_flexibiltyrew_sub = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_flexibiltyrew_sub);

        txt_nav_upgrade_uto = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_upgrade_uto);
        txt_nav_upgrade_uto_sub = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_upgrade_uto_sub);
        txt_nav_upgrade_up = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_upgrade_up);
        txt_nav_upgrade_up_sub = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_upgrade_up_sub);

        txt_nav_emptyseat_eso = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_emptyseat_eso);
        txt_nav_emptyseat_eso_sub = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_emptyseat_eso_sub);
        txt_nav_emptyseat_esp = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_emptyseat_esp);
        txt_nav_emptyseat_esp_sub = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_emptyseat_esp_sub);

        txt_nav_preferredseat_pso = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_preferredseat_pso);
        txt_nav_preferredseat_pso_sub = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_preferredseat_pso_sub);
        txt_nav_preferredseat_psp = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_preferredseat_psp);
        txt_nav_preferredseat_psp_sub = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txt_nav_preferredseat_psp_sub);

        txt_nav_bookflight = (OTTextView) lytMyFlightPassContainer.findViewById(R.id.txt_nav_bookflight);

        txtLogin = (OTTextView) headerView.findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                String email = "";
                try {
                    LoginData loginData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLoginData(MainActivity.this)), LoginData.class);
                    email = loginData.getCompleteProfile().getEmail();
                } catch (Exception e) {
                    Utils.ERROR("MainActivity >> Error while parsing json : " + e.toString());
                }
                Utils.isValidEmailAddress(MainActivity.this, email);
                Utils.moveToFragment(MainActivity.this, new LoginFragment(), null, 0);
            }
        });

        txtCreateMyAccount = (OTTextView) headerView.findViewById(R.id.txtCreateMyAccount);
        txtCreateMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                Utils.moveToFragment(MainActivity.this, new CreateAccountFragment(), null, 0);
            }
        });

        lytNotLoggedIn = (RelativeLayout) headerView.findViewById(R.id.lytNotLoggedIn);
        lytLoggedIn = (RelativeLayout) headerView.findViewById(R.id.lytLoggedIn);

        txtName = (OTTextView) headerView.findViewById(R.id.txtName);
        txtEmail = (OTTextView) headerView.findViewById(R.id.txtEmail);

        addChangeLanguageCountryFunctionality();
        updateNavigationUI();
    }

    private void setUIForSegProduct() {
        RelativeLayout lytMultipleBooking = (RelativeLayout) headerView.findViewById(R.id.lytMultipleBooking);
        RelativeLayout lytPreferredFlight = (RelativeLayout) headerView.findViewById(R.id.lytPreferredFlight);
        RelativeLayout lytFlexibilityReward = (RelativeLayout) headerView.findViewById(R.id.lytFlexibilityReward);

        lytMultipleBooking.setVisibility(getResources().getBoolean(R.bool.bool_show_seg) ? View.VISIBLE : View.GONE);
        lytPreferredFlight.setVisibility(getResources().getBoolean(R.bool.bool_show_seg) ? View.VISIBLE : View.GONE);
        lytFlexibilityReward.setVisibility(getResources().getBoolean(R.bool.bool_show_seg) ? View.VISIBLE : View.GONE);

    }

    private void setUIForSBoCBoProduct() {
        RelativeLayout lytSpecialBaggage = (RelativeLayout) headerView.findViewById(R.id.lytSpecialBaggage);
        RelativeLayout lytCarryOnBaggage = (RelativeLayout) headerView.findViewById(R.id.lytCarryOnBaggage);

        lytSpecialBaggage.setVisibility(getResources().getBoolean(R.bool.bool_show_cbo_sbo) ? View.VISIBLE : View.GONE);
        lytCarryOnBaggage.setVisibility(getResources().getBoolean(R.bool.bool_show_cbo_sbo) ? View.VISIBLE : View.GONE);


    }

    /**
     * Used to lock navigation drawer
     * @param lock
     */
    public void setDrawableLockMode(DrawerLayout drawer, int lock) {

        drawer.setDrawerLockMode(lock);

    }

    /**
     * used to add 'change language/country' functionality
     */
    private void addChangeLanguageCountryFunctionality() {
        Utils.DEBUG("MainActivity >> addChangeLanguageCountryFunctionality() called.");
        final OTTextView txtCountry = (OTTextView) headerView.findViewById(R.id.txtCountry);
        final NetworkImageView imgCountry = (NetworkImageView) headerView.findViewById(R.id.imgCountryLogo);
        final ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        SessionIdData sessionIdData = null;
        try {
            sessionIdData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(this)), SessionIdData.class);
        } catch (Exception e) {
            Utils.ERROR("MainActivity >> Error while parsing json : " + e.toString());
        }

        if (sessionIdData == null || sessionIdData.getCountryListAPI() == null) {
            return;
        }

        System.out.println("Country Id ....>>>>>>>>>>>>>>>>>>>>>" + Utils.getUserSelectedCountryId(this) + "");
        txtCountry.setText(getCountryName(Utils.getUserSelectedCountryId(this), sessionIdData.getCountryListAPI()));
        imgCountry.setImageUrl(getCountryLogoURL(Utils.getUserSelectedCountryId(this), sessionIdData.getCountryListAPI()), imageLoader);

        final LinearLayout lytNavHeaderWithOutCountryList = (LinearLayout) headerView.findViewById(R.id.lytNavHeaderWithOutCountryList);
        final LinearLayout lytNavHeaderWithCountryList = (LinearLayout) headerView.findViewById(R.id.lytNavHeaderWithCountryList);
        final RecyclerView recyclerViewCountryList = (RecyclerView) headerView.findViewById(R.id.recyclerViewCountryList);
        (headerView.findViewById(R.id.imgBackCountryList)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
            }
        });


        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        recyclerViewCountryList.setLayoutManager(gridLayoutManager);

        final ChangeCountryRecyclerViewAdapter adapter = new ChangeCountryRecyclerViewAdapter(this, sessionIdData.getCountryListAPI(), new ChangeCountryRecyclerViewAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(CountryList country) {
                //Utils.showToast(MainActivity.this, "" + country.getCountryName());

                Utils.setUserSelectedCountryId(MainActivity.this, country.getCountryID());
                Utils.setUserSelectedLanguageId(MainActivity.this, country.getLanguageID());
                //Utils.setUserSelectedCountryLogoURL(MainActivity.this, country.getCountryLogo());

                imgCountry.setImageUrl(country.getCountryLogo(), imageLoader);
                txtCountry.setText(country.getCountryName());
                closeDrawer();

                updateOnLanguageChange();
                //callApiForInternationalizeApp();

            }
        });
        recyclerViewCountryList.setAdapter(adapter);

        RelativeLayout lytChangeCountry = (RelativeLayout) headerView.findViewById(R.id.lytChangeCountry);
        lytChangeCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytNavHeaderWithCountryList.setVisibility(View.VISIBLE);
                lytNavHeaderWithOutCountryList.setVisibility(View.GONE);

                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     *
     */

    private void callApiForInternationalizeApp() {
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
                this,
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
                        labelLocalization = ParseManager.getInstance().fromJSON(response, InternationalizeData.class);

                        //save
                        Utils.setInternationalLanguage(MainActivity.this, response.toString());

                        //now we have country/language list, update 'change language/country' UI and functionality
                        //addChangeLanguageCountryFunctionality();

                        //callCountryExtListAPI();
                        updateNavigationUI();

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

    private void updateNavigationUI() {

        try {

            try {
                labelLocalization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(this)), InternationalizeData.class);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            Utils.DEBUG("upgrade name : " + labelLocalization.getLABLUpgradeTravelOptionShortLabel());
            txt_nav_changecountry.setText(labelLocalization.getLABLChangeCountryLabel());
            txt_nav_heading_buyflightpass.setText(labelLocalization.getLABLBuyFlightOptionHeadingLabel());
            txt_nav_upgrade.setText(labelLocalization.getLABLUpgradeTravelOptionShortLabel());
            txt_nav_flightpass.setText(labelLocalization.getLABLPassHomeHeadingLabel());
            txt_nav_payless.setText(labelLocalization.getLABLFPODescriptionShortLabel());
            txt_nav_emptyseat.setText(labelLocalization.getLABLEmptySeatOptionShortLabel());
            txt_nav_preferredseat.setText(labelLocalization.getLABLPrefferedSeatOptionShortLabel());
            txt_nav_extrabaggage.setText(labelLocalization.getLABLXtraBaggageOptionShortLabel());
            txt_nav_extrabag_sub.setHint(labelLocalization.getLABLXBODescriptionShortLabel());
            txt_nav_loungeaccess.setText(labelLocalization.getLABLLoungeAccessOptionShortLabel());
            //txt_nav_loungeacc_sub.setHint(labelLocalization.getLABLChangeCountryLabel());
            txt_nav_flexibiltyreward.setText(labelLocalization.getLABLFlexibilityRewardOptionShortLabel());
            txt_nav_flexibiltyrew_sub.setHint(labelLocalization.getLABLFRODescriptionShortLabel());

            txt_nav_bookflight.setText(labelLocalization.getLABLBookFlightButtonLabel());

            txt_nav_upgrade_uto.setText(labelLocalization.getLABLUpgradeTravelOptionLabel());
            txt_nav_upgrade_uto_sub.setHint(labelLocalization.getLABLUTODescriptionShortLabel());
            txt_nav_upgrade_up.setText(labelLocalization.getLABLUpgradePassLabel());
            txt_nav_upgrade_up_sub.setHint(labelLocalization.getLABLUPDescriptionShortLabel());

            txt_nav_emptyseat_eso.setText(labelLocalization.getLABLEmptySeatOptionLabel());
            //txt_nav_emptyseat_eso_sub.setHint(labelLocalization.getLABLFRODescriptionShortLabel());
            txt_nav_emptyseat_esp.setText(labelLocalization.getLABLEmptySeatPassLabel());
            //txt_nav_emptyseat_esp_sub.setHint(labelLocalization.getLABLFRODescriptionShortLabel());

            txt_nav_preferredseat_pso.setText(labelLocalization.getLABLAMPreferredSeatOptionLabel());
            txt_nav_preferredseat_pso_sub.setHint(labelLocalization.getLABLPSODescriptionShortLabel());
            txt_nav_preferredseat_psp.setText(labelLocalization.getLABLPreferredSeatPassLabel());
            txt_nav_preferredseat_psp_sub.setHint(labelLocalization.getLABLPSPDescriptionShortLabel());

            txtLogin.setText(labelLocalization.getLABLLoginLabel());
            txtCreateMyAccount.setText(labelLocalization.getCreateMyAccountLabel());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void updateOnLanguageChange() {
        //first check current page name, it can be two either HomeFragment or FlightPassFragment
        //if it is HomeFragment then call callSessionIdAPI(), callCountryExtListAPI() and callLoginApi()
        //if it is FlightPassFragment then callHomePassBannerAPI() also

        callSessionIdAPI();
    }

    private void callSessionIdAPI() {
        loader = AppDialogLoader.getLoader(this);
        loader.show();
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SESSION_ID);


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("tgProductId", Integer.toString(getResources().getInteger(R.integer.value_tgProductId_fpo)));
            requestObject.put("CountryId", Utils.getUserSelectedCountryId(this) + "");
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(this) + "");

        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }


        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                this,
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("Response after language change called : " + response.toString());
                        SessionIdData sessionData = ParseManager.getInstance().fromJSON(response, SessionIdData.class);

                        //save
                        Utils.setSessionData(MainActivity.this, response.toString());

                        //now we have country/language list, update 'change language/country' UI and functionality
                        addChangeLanguageCountryFunctionality();

                        callApiForInternationalizeApp();


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

    private void callCountryExtListAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_COUNTRY_EXT_LIST);


        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("LanguageID", Integer.toString(Utils.getUserSelectedLanguageId(this)));
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }


        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                this,
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
                        CountryExtData countryExtData = ParseManager.getInstance().fromJSON(response, CountryExtData.class);

                        //save
                        Utils.setCountryExtData(MainActivity.this, response.toString());

                        if (Utils.isUserLoggedIn(MainActivity.this)) {
                            //call session api before calling login api
                            callLoginApi();
                        } else {
                            loader.hide();
                        }

                        /*//check password change required or not
                        try {
                            LoginData loginData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLoginData(MainActivity.this)), LoginData.class);
                            if(loginData.getPasswordvalidationRequired().getPasswordRequired() == 1)
                            {
                                //move to my profile page, asking user to change the password
                                moveToMyProfileFragment();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e){
                            e.printStackTrace();
                        }*/


                        if (Utils.getTopFragmentInBackStack(MainActivity.this).equals(new HomeFragment().getClass().getName())) {
                            moveToHomeFragment();
                        } else if (Utils.getTopFragmentInBackStack(MainActivity.this).equals(new FlightPassFragment().getClass().getName())) {
                            //remove this FlightPassFragment from back stack then move again
                            Utils.clearRecentBackStack(MainActivity.this);
                            Utils.moveToFragment(MainActivity.this, new FlightPassFragment(), null, 0);

                        }

                        else  if (Utils.getTopFragmentInBackStack(MainActivity.this).equals(new LegProductsHomeFragment().getClass().getName())){

                            try {
                                Benefit benefit = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLegBenefitData(MainActivity.this)),Benefit.class);
                                Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), benefit, 0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(MainActivity.this, getString(R.string.warning_common_error_message));
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
            requestObject.put("customer.userName", Utils.getUsername(MainActivity.this));
            requestObject.put("customer.passwd", Utils.getPassword(MainActivity.this));
            requestObject.put("isFromMyaccountPage", "1");
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }
        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                this,
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
                            Utils.setLoginData(MainActivity.this, response.toString());

                        } else if (loginData.getResult().equals(getString(R.string.string_failure))) {
                            Utils.setLoginData(MainActivity.this, null);
                        }
                        loader.hide();

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

    /**
     * used to get the country name from given country id
     *
     * @param userSelectedCountryId
     * @param countryListAPI
     * @return
     */
    private String getCountryName(int userSelectedCountryId, CountryListAPI countryListAPI) {

        for (int index = 0; index < countryListAPI.getCountryList().size(); index++) {
            if (countryListAPI.getCountryList().get(index).getCountryID() == userSelectedCountryId) {
                return countryListAPI.getCountryList().get(index).getCountryName();
            }
        }
        return "";
    }

    /**
     * used to get country logo url from given country id
     *
     * @param userSelectedCountryId
     * @param countryListAPI
     * @return
     */
    private String getCountryLogoURL(int userSelectedCountryId, CountryListAPI countryListAPI) {

        for (int index = 0; index < countryListAPI.getCountryList().size(); index++) {
            if (countryListAPI.getCountryList().get(index).getCountryID() == userSelectedCountryId) {
                Utils.DEBUG("getCountryLogoURL() >> " + countryListAPI.getCountryList().get(index).getCountryLogo());
                return countryListAPI.getCountryList().get(index).getCountryLogo();
            }
        }
        Utils.DEBUG("getCountryLogoURL() >> ");
        return "";
    }

    /**
     * update UI after checking user logging status
     */
    private void updateUIForLogin() {
        if (Utils.isUserLoggedIn(this)) {
            lytLoggedIn.setVisibility(View.VISIBLE);
            lytNotLoggedIn.setVisibility(View.GONE);

            try {
                LoginData loginData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLoginData(this)), LoginData.class);

                txtName.setText(loginData.getCompleteProfile().getFirstName());
                txtEmail.setText(loginData.getCompleteProfile().getEmail());
                Utils.isValidEmailAddress(this, txtEmail.getText().toString());
            } catch (Exception e) {
                Utils.ERROR("MainActivity >> Error while parsing json : " + e.toString());
            }
        } else {
            lytLoggedIn.setVisibility(View.GONE);
            lytNotLoggedIn.setVisibility(View.VISIBLE);

            txtName.setText("");
            txtEmail.setText("");
        }
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (count > 0) {
            FragmentManager.BackStackEntry a = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1);//top
            Fragment baseFrag = (Fragment) getSupportFragmentManager().findFragmentByTag(a.getName());
            if (baseFrag instanceof BFragment) {
                ((BFragment) baseFrag).onBackEventPre();
                if(a.getName().equals(new MMBPaymentFragment().getClass().getName()))
                {
                    return;
                }
            }

            //control navigation drawer
            Utils.controlNavigationDrawer(MainActivity.this, a.getName());


            //pop back stack
            getSupportFragmentManager().popBackStack();
            //now update action bar, depending upon screen
            try {
                FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 2);


                String titleFromCache = Utils.getTitleFromCache(this, entry.getName());
                Utils.DEBUG("onBackPressed >> moving to " + entry.getName() + ", title in cache : " + titleFromCache);

                if (!titleFromCache.equals("")) {
                    Utils.updateActionBarForFeatures(this, entry.getName(), titleFromCache, null);
                } else {
                    Utils.updateActionBarForFeatures(this, entry.getName());
                }


                Fragment baseFragment = (Fragment) getSupportFragmentManager().findFragmentByTag(entry.getName());
                if (baseFrag instanceof BFragment) {
                    ((BFragment) baseFragment).onFocusEvent();
                }

            } catch (Exception e) {
                //user is moving at home
                Utils.ERROR("onBackPressed >> Error while poping back fragment at : " + (getSupportFragmentManager().getBackStackEntryCount() - 2));
                Utils.updateActionBarForFeatures(this, new HomeFragment().getClass().getName());
            }
            if (baseFrag instanceof BFragment) {
                ((BFragment) baseFrag).onBackEventPost();
            }
            Utils.hideKeyboard(this, baseFrag.getView());
        } else {
            super.onBackPressed();
        }
    }

    /**
     * call this method to close left menu drawer
     */
    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    /**
     * call this method to open left menu drawer
     */
    public void openDrawer() {
        Utils.DEBUG("MainActivity >> openDrawer() >> called from " + Utils.getTopFragmentInBackStack(this));

        updateCountyLayout(headerView.findViewById(R.id.lytChangeCountry));

        //first check user login status and change UI accordingly
        updateUIForLogin();

        //hide country/language UI
        (headerView.findViewById(R.id.lytNavHeaderWithOutCountryList)).setVisibility(View.VISIBLE);
        (headerView.findViewById(R.id.lytNavHeaderWithCountryList)).setVisibility(View.GONE);

        //


        updateNavigationUI();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);

        /**/
    }

    private void updateCountyLayout(View lytChangeCountry) {
        Utils.DEBUG("updateCountyLayout() called.");
        OTTextView txtCountry = (OTTextView) lytChangeCountry.findViewById(R.id.txtCountry);
        NetworkImageView imgCountry = (NetworkImageView) lytChangeCountry.findViewById(R.id.imgCountryLogo);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        SessionIdData sessionIdData = null;
        try {
            sessionIdData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(this)), SessionIdData.class);
        } catch (Exception e) {
            Utils.ERROR("MainActivity >> Error while parsing json : " + e.toString());
        }

        if (sessionIdData == null || sessionIdData.getCountryListAPI() == null) {
            return;
        }

        String ran = Integer.toString(new Random().nextInt(1000));
        Utils.DEBUG("updateCountyLayout() : " + ran);
        txtCountry.setText(getCountryName(Utils.getUserSelectedCountryId(this), sessionIdData.getCountryListAPI()));


        String url = /*sessionIdData.getCountryListAPI().getCountryList().get(new Random().nextInt(10)).getCountryLogo();*/getCountryLogoURL(Utils.getUserSelectedCountryId(this), sessionIdData.getCountryListAPI());
        //AppController.getInstance().getRequestQueue().getCache().remove(url);
        Utils.DEBUG("updateCountyLayout() : " + url);
        imgCountry.setVisibility(View.GONE);
        imgCountry.setImageUrl(url, imageLoader);
        imgCountry.setVisibility(View.VISIBLE);
        Utils.DEBUG("updateCountyLayout() called. end");
    }

    public void onClickBuyFlightOptionExpand(View v) {
        if (lytFlightOptionContainer != null) {
            lytFlightOptionContainer.findViewById(R.id.lytUpgradeTravelOption).setVisibility(View.VISIBLE);
            lytFlightOptionContainer.findViewById(R.id.lytUpgradePass).setVisibility(View.VISIBLE);
            lytFlightOptionContainer.findViewById(R.id.lytEmptySeatOption).setVisibility(View.VISIBLE);
            lytFlightOptionContainer.findViewById(R.id.lytEmptySeatPass).setVisibility(View.VISIBLE);
            lytFlightOptionContainer.findViewById(R.id.lytPreferredSeatOption).setVisibility(View.VISIBLE);
            lytFlightOptionContainer.findViewById(R.id.lytPreferredSeatPass).setVisibility(View.VISIBLE);

            ((OTTextView) lytFlightOptionContainer.findViewById(R.id.txtUpgradePlusMinus)).setBackgroundResource(R.drawable.menu_plus_icon);
            ((OTTextView) lytFlightOptionContainer.findViewById(R.id.txtEmptySeatPlusMinus)).setBackgroundResource(R.drawable.menu_plus_icon);
            ((OTTextView) lytFlightOptionContainer.findViewById(R.id.txtPreferredSeatPlusMinus)).setBackgroundResource(R.drawable.menu_plus_icon);


            OTTextView txtBuyFlightOptionPlusMinus = (OTTextView) v.findViewById(R.id.txtBuyFlightOptionPlusMinus);
            for (int i = 0; i < lytFlightOptionContainer.getChildCount(); i++) {
                View child = lytFlightOptionContainer.getChildAt(i);
                if (child.getVisibility() == View.VISIBLE) {
                    txtBuyFlightOptionPlusMinus.setBackgroundResource(R.drawable.menu_plus_icon);
                    (child).setVisibility(View.GONE);

                    //for seg
                    lytFlightOptionContainer.findViewById(R.id.lytMultipleBooking).setVisibility(View.GONE);
                    lytFlightOptionContainer.findViewById(R.id.lytPreferredFlight).setVisibility(View.GONE);
                    lytFlightOptionContainer.findViewById(R.id.lytFlexibilityReward).setVisibility(View.GONE);
                } else {
                    txtBuyFlightOptionPlusMinus.setBackgroundResource(R.drawable.menu_minus_icon);
                    (child).setVisibility(View.VISIBLE);

                    //for seg
                    lytFlightOptionContainer.findViewById(R.id.lytMultipleBooking).setVisibility(getResources().getBoolean(R.bool.bool_show_seg) ? View.VISIBLE : View.GONE);
                    lytFlightOptionContainer.findViewById(R.id.lytPreferredFlight).setVisibility(getResources().getBoolean(R.bool.bool_show_seg) ? View.VISIBLE : View.GONE);
                    lytFlightOptionContainer.findViewById(R.id.lytFlexibilityReward).setVisibility(getResources().getBoolean(R.bool.bool_show_seg) ? View.VISIBLE : View.GONE);
                }

            }

        }
    }

    public void onClickUpgradeExpand(View v) {
        if (lytFlightOptionContainer != null) {
            OTTextView txtUpgradePlusMinus = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txtUpgradePlusMinus);
            RelativeLayout lytUpgradeTravelOption = (RelativeLayout) lytFlightOptionContainer.findViewById(R.id.lytUpgradeTravelOption);
            RelativeLayout lytUpgradePass = (RelativeLayout) lytFlightOptionContainer.findViewById(R.id.lytUpgradePass);

            if (lytUpgradeTravelOption.getVisibility() == View.VISIBLE) {
                lytUpgradeTravelOption.setVisibility(View.GONE);
                lytUpgradePass.setVisibility(View.GONE);
                txtUpgradePlusMinus.setBackgroundResource(R.drawable.menu_plus_icon);
            } else {
                lytUpgradeTravelOption.setVisibility(View.VISIBLE);
                lytUpgradePass.setVisibility(View.VISIBLE);
                txtUpgradePlusMinus.setBackgroundResource(R.drawable.menu_minus_icon);
            }
        }
    }

    public void onClickEmptySeatExpand(View v) {

        // lytBuyFlightOptions
        if (lytFlightOptionContainer != null) {
            OTTextView txtEmptySeatPlusMinus = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txtEmptySeatPlusMinus);
            RelativeLayout lytEmptySeatOption = (RelativeLayout) lytFlightOptionContainer.findViewById(R.id.lytEmptySeatOption);
            RelativeLayout lytEmptySeatPass = (RelativeLayout) lytFlightOptionContainer.findViewById(R.id.lytEmptySeatPass);

            if (lytEmptySeatOption.getVisibility() == View.VISIBLE) {
                lytEmptySeatOption.setVisibility(View.GONE);
                lytEmptySeatPass.setVisibility(View.GONE);
                txtEmptySeatPlusMinus.setBackgroundResource(R.drawable.menu_plus_icon);
            } else {
                lytEmptySeatOption.setVisibility(View.VISIBLE);
                lytEmptySeatPass.setVisibility(View.VISIBLE);
                txtEmptySeatPlusMinus.setBackgroundResource(R.drawable.menu_minus_icon);
            }
        }
    }

    public void onClickPreferredSeatExpand(View v) {
        if (lytFlightOptionContainer != null) {
            OTTextView txtPreferredSeatPlusMinus = (OTTextView) lytFlightOptionContainer.findViewById(R.id.txtPreferredSeatPlusMinus);
            RelativeLayout lytPreferredSeatOption = (RelativeLayout) lytFlightOptionContainer.findViewById(R.id.lytPreferredSeatOption);
            RelativeLayout lytPreferredSeatPass = (RelativeLayout) lytFlightOptionContainer.findViewById(R.id.lytPreferredSeatPass);

            if (lytPreferredSeatOption.getVisibility() == View.VISIBLE) {
                lytPreferredSeatOption.setVisibility(View.GONE);
                lytPreferredSeatPass.setVisibility(View.GONE);
                txtPreferredSeatPlusMinus.setBackgroundResource(R.drawable.menu_plus_icon);
            } else {
                lytPreferredSeatOption.setVisibility(View.VISIBLE);
                lytPreferredSeatPass.setVisibility(View.VISIBLE);
                txtPreferredSeatPlusMinus.setBackgroundResource(R.drawable.menu_minus_icon);
            }
        }
    }

    public void onClickMyFlightPassExpand(View v) {
        if (lytMyFlightPassContainer != null) {
            OTTextView txtMyFlightPassPlusMinus = (OTTextView) v.findViewById(R.id.txtMyFlightPassPlusMinus);
            for (int i = 0; i < lytMyFlightPassContainer.getChildCount(); i++) {
                View child = lytMyFlightPassContainer.getChildAt(i);
                if (child.getVisibility() == View.VISIBLE) {
                    txtMyFlightPassPlusMinus.setBackgroundResource(R.drawable.menu_plus_icon);
                    (child).setVisibility(View.GONE);
                } else {
                    txtMyFlightPassPlusMinus.setBackgroundResource(R.drawable.menu_minus_icon);
                    (child).setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void onClickMyOptionPassExpand(View v) {

            OTTextView txtOptionPassPlusMinus = (OTTextView) v.findViewById(R.id.txtOptionPassPlusMinus);
            for (int i = 0; i < lytOptionPassContaier.getChildCount(); i++) {
                View child = lytOptionPassContaier.getChildAt(i);
                if (child.getVisibility() == View.VISIBLE) {
                    txtOptionPassPlusMinus.setBackgroundResource(R.drawable.menu_plus_icon);
                    (child).setVisibility(View.GONE);
                } else {
                    txtOptionPassPlusMinus.setBackgroundResource(R.drawable.menu_minus_icon);
                    (child).setVisibility(View.VISIBLE);
                }
            }

    }

    public void onClickFlightPass(View v) {
        closeDrawer();
        if (Utils.getTopFragmentInBackStack(MainActivity.this).equals(new FlightPassFragment().getClass().getName())) {
            Utils.clearRecentBackStack(MainActivity.this);
        }
        Utils.setCurrentProductId(MainActivity.this,(getResources().getInteger(R.integer.value_tgProductId_fpo)));
        //getSessionAndCallFlightPassFragment(null);
        Utils.moveToFragment(MainActivity.this, new FlightPassFragment(), null, 0);
    }

    public void onClickXtraBaggage(View v) {

        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Xtra Baggage");
        mBenefit.setImageURL("");
        AppVariables.ProductName = "Xtra Baggage";
        mBenefit.setId(Integer.parseInt("8"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);

        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickLoungeAccess(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Lounge Access");
        mBenefit.setImageURL("");
        AppVariables.ProductName = "Lounge Access";
        mBenefit.setId(Integer.parseInt("7"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickFlexibleRewards(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Flexible Rewards");
        AppVariables.ProductName = "Flexible Rewards";
        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("4"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickPreferredFlight(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Preferred Flight");
        AppVariables.ProductName = "Preferred Flight";
        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("2"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickUTO(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Upgrade");
        AppVariables.ProductName = "Upgrade";
        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("1"));

        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);

        Utils.setCurrentProductId(MainActivity.this,getResources().getInteger(R.integer.value_tgProductId_uto));
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickUTOStatus(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.setCurrentProductId(MainActivity.this,getResources().getInteger(R.integer.value_tgProductId_utp));
        Utils.clearAllBackStack(this);

        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Upgrade");
        AppVariables.ProductName = "Upgrade Pass";

        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("31"));

        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.moveToFragment(MainActivity.this, new FlightPassFragment(), null, 0);
     //  getSessionAndCallFlightPassFragment(null);

    }

    private void getSessionAndCallFlightPassFragment(final Benefit mBenefit)
    {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SESSION_ID);


        JSONObject requestObject = new JSONObject();
        try {
            //requestObject.put("tgProductId", Integer.toString(getResources().getInteger(R.integer.value_tgProductId_utp)));
            requestObject.put("tgProductId", Utils.getCurrentProductId(MainActivity.this)+"");
            requestObject.put("CountryId", Utils.getUserSelectedCountryId(this) + "");
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(this) + "");

        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }
        final AppDialogLoader loader = AppDialogLoader.getLoader(this);
        if(loader.CheckLoaderStatus()){
            loader.show();
        }else {
            loader.hide();
        }

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                this,
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
                        //save
                        Utils.setSessionData(MainActivity.this, response.toString());
                        //clear if instance exists
                        if (Utils.getTopFragmentInBackStack(MainActivity.this).equals(new FlightPassFragment().getClass().getName())) {
                            Utils.clearRecentBackStack(MainActivity.this);
                        }
                        //move




                        if(Utils.getCurrentProductId(MainActivity.this) == (getResources().getInteger(R.integer.value_tgProductId_fpo)))
                        {
                            Utils.moveToFragment(MainActivity.this, new FlightPassFragment(), null, 0);

                        }else if(Utils.getCurrentProductId(MainActivity.this) == (getResources().getInteger(R.integer.value_tgProductId_utp)))
                        {
                            Utils.moveToFragment(MainActivity.this, new FlightPassFragment(), null, 0);

                        }else if(Utils.getCurrentProductId(MainActivity.this)  == (getResources().getInteger(R.integer.value_tgProductId_esp)))
                        {
                            //Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
                            Utils.moveToFragment(MainActivity.this, new FlightPassFragment(), null, 0);

                        }else if(Utils.getCurrentProductId(MainActivity.this) == (getResources().getInteger(R.integer.value_tgProductId_psp)))
                        {
                            //Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
                            Utils.moveToFragment(MainActivity.this, new FlightPassFragment(), null, 0);
                        }


                        loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(MainActivity.this, getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    public void onClickESo(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Empty Seat");
        AppVariables.ProductName = "Empty Seat";

        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("5"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);

        Utils.setCurrentProductId(MainActivity.this,getResources().getInteger(R.integer.value_tgProductId_eso));
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickESoStatus(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();

        mBenefit.setBenefitName("Empty Seat");

        AppVariables.ProductName = "Empty Seat Pass";

        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("35"));

        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.setCurrentProductId(MainActivity.this, getResources().getInteger(R.integer.value_tgProductId_esp));
        Utils.moveToFragment(MainActivity.this, new FlightPassFragment(), null, 0);

       // getSessionAndCallFlightPassFragment(mBenefit);
    }

    public void onClickPSo(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Preferred Seat");
        AppVariables.ProductName = "Preferred Seat";
        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("6"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.setCurrentProductId(MainActivity.this,getResources().getInteger(R.integer.value_tgProductId_pso));
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickPSoStatus(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Preferred Seat");
        AppVariables.ProductName = "Preferred Seat Pass";
        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("36"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.setCurrentProductId(MainActivity.this, getResources().getInteger(R.integer.value_tgProductId_psp));
        Utils.moveToFragment(MainActivity.this, new FlightPassFragment(), null, 0);
       // getSessionAndCallFlightPassFragment(mBenefit);
    }

    public void onClickMultipleBooking(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Multiple Booking");
        AppVariables.ProductName = "Multiple Booking";
        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("3"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickSpecialBaggage(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Special Baggage");
        AppVariables.ProductName = "Special Baggage";
        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("22"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickCarryOnBaggage(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Carry-On Baggage");
        AppVariables.ProductName = "Carry-On Baggage";
        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("18"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickPriorityHandling(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.clearAllBackStack(this);
        Benefit mBenefit = new Benefit();
        mBenefit.setBenefitName("Priority Handling");
        AppVariables.ProductName = "Priority Handling";
        mBenefit.setImageURL("");
        mBenefit.setId(Integer.parseInt("19"));
        String benefitJSON = ParseManager.getInstance().toJSON(mBenefit);
        Utils.setLegBenefitData(MainActivity.this, benefitJSON);
        Utils.moveToFragment(MainActivity.this, new LegProductsHomeFragment(), mBenefit, 0);
    }

    public void onClickBookFlight(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.moveToFragment(this, new SelectBookFlightFragment(), null, 0);
    }

    public void onClickOptionPass(View v) {
        if (this.getResources().getBoolean(R.bool.bool_default_fpo)) {
            return;
        }
        closeDrawer();
        Utils.startOptionPass(this);
    }

    /**
     * method used to dismiss the side navigation bar's scroll bar
     *
     * @param navigationView
     */
    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    @Override
    public void onResponse(FragmentCommunicationData message) {
        if (message.getFragmentName().equals(new FlightPassSearchFragment().getClass().getName())) {
            FlightPassSearchFragment flightPassSearchFragment = (FlightPassSearchFragment) getSupportFragmentManager().findFragmentByTag(new FlightPassSearchFragment().getClass().getName());
            flightPassSearchFragment.updateSelectionDone(message);
        } else if (message.getFragmentName().equals(new LegProductSearchFragment().getClass().getName())) {
            LegProductSearchFragment flightPassSearchFragment = (LegProductSearchFragment) getSupportFragmentManager().findFragmentByTag(new LegProductSearchFragment().getClass().getName());
            flightPassSearchFragment.updateSelectedAirline(message);
        } else if (message.getFragmentName().equals(new FlightPassSearchSelectFragment().getClass().getName())) {
            FlightPassSearchSelectFragment flightPassSearchSelectFragment = (FlightPassSearchSelectFragment) getSupportFragmentManager().findFragmentByTag(new FlightPassSearchSelectFragment().getClass().getName());
            flightPassSearchSelectFragment.updateHelpLayout(message);
        } else if (message.getFragmentName().equals(new FlightPassTravelZoneFragment().getClass().getName())) {
            FlightPassTravelZoneFragment flightPassTravelZoneFragment = (FlightPassTravelZoneFragment) getSupportFragmentManager().findFragmentByTag(new FlightPassSearchSelectFragment().getClass().getName());
            flightPassTravelZoneFragment.updateHelpLayout(message);
        } else if (message.getFragmentName().equals(new SelectFlightPassFragment().getClass().getName())) {
            SelectFlightPassFragment selectFlightPassFragment = (SelectFlightPassFragment) getSupportFragmentManager().findFragmentByTag(new SelectFlightPassFragment().getClass().getName());
            if(selectFlightPassFragment != null)
            {
                if(message.isFromReview())
                {
                    selectFlightPassFragment.updateFromReview(message);
                }
                else
                {
                    selectFlightPassFragment.updateRecyclerView(message);
                }
            }


        } else if (message.getFragmentName().equals(new ReviewFragment().getClass().getName())) {
            ReviewFragment reviewFragment = (ReviewFragment) getSupportFragmentManager().findFragmentByTag(new ReviewFragment().getClass().getName());
            reviewFragment.updateUIForUser(message);
        } else if (message.getFragmentName().equals(new CustomizeFragment().getClass().getName())) {
            CustomizeFragment customizeFragment = (CustomizeFragment) getSupportFragmentManager().findFragmentByTag(new CustomizeFragment().getClass().getName());
            customizeFragment.updateHelpLayout(message);
        } else if (message.getFragmentName().equals(new SegInputSelectFragment().getClass().getName())) {
            SegInputSelectFragment segInputSelectFragment = (SegInputSelectFragment) getSupportFragmentManager().findFragmentByTag(new SegInputSelectFragment().getClass().getName());
            segInputSelectFragment.updateHelpLayout(message);
        } else if (message.getFragmentName().equals(new MainActivity().getClass().getName())) {
            addChangeLanguageCountryFunctionality();
        } else if (message.getFragmentName().equals(new DashboardFragment().getClass().getName())) {
            DashboardFragment dashboardFragment = (DashboardFragment) getSupportFragmentManager().findFragmentByTag(new DashboardFragment().getClass().getName());
            if(dashboardFragment != null)
            {
                dashboardFragment.updateDashboardLoginData(message);
            }
            else
            {
                Utils.moveToFragment(this, new DashboardFragment(), message.getLoginData(), 0);
            }

        } else if (message.getFragmentName().equals(new RedeemSearchResultFragment().getClass().getName())) {
            RedeemSearchResultFragment redeemSearchResultFragment = (RedeemSearchResultFragment) getSupportFragmentManager().findFragmentByTag(new RedeemSearchResultFragment().getClass().getName());
            redeemSearchResultFragment.initialize(message);
        } else if (message.getFragmentName().equals(new RedeemViewDetailsFragment().getClass().getName())) {
            RedeemViewDetailsFragment RedeemViewDetailsFragment = (RedeemViewDetailsFragment) getSupportFragmentManager().findFragmentByTag(new RedeemViewDetailsFragment().getClass().getName());
            RedeemViewDetailsFragment.showDialog(message);
        } else if (message.getFragmentName().equals(new SearchFlightInputFragment().getClass().getName())) {
            SearchFlightInputFragment SearchFlightInputFragment = (SearchFlightInputFragment) getSupportFragmentManager().findFragmentByTag(new SearchFlightInputFragment().getClass().getName());
            SearchFlightInputFragment.updateLayout(message);
        } else if (message.getFragmentName().equals(new RedeemAddPassengerFragment().getClass().getName())) {
            RedeemAddPassengerFragment RedeemAddPassengerFragment = (RedeemAddPassengerFragment) getSupportFragmentManager().findFragmentByTag(new RedeemAddPassengerFragment().getClass().getName());
            RedeemAddPassengerFragment.updateLayout(message);
        }
        else if (message.getFragmentName().equals(new SegInputDetailsFragment().getClass().getName())) {
            SegInputDetailsFragment segInputDetailsFragment = (SegInputDetailsFragment) getSupportFragmentManager().findFragmentByTag(new SegInputDetailsFragment().getClass().getName());
            segInputDetailsFragment.updateUIFromSelect(message);
        } else if (message.getFragmentName().equals(new MMPAddUserFragment().getClass().getName())) {
            MMPAddUserFragment MMPAddUserFragment = (MMPAddUserFragment) getSupportFragmentManager().findFragmentByTag(new MMPAddUserFragment().getClass().getName());
            MMPAddUserFragment.updateLayout(message);
        } else if (message.getFragmentName().equals(new MMBChangeFlightSelectDateFragment().getClass().getName())) {
            MMBChangeFlightSelectDateFragment mMBChangeFlightSelectDateFragment = (MMBChangeFlightSelectDateFragment) getSupportFragmentManager().findFragmentByTag(new MMBChangeFlightSelectDateFragment().getClass().getName());
            mMBChangeFlightSelectDateFragment.showHighVolumeErrorMessage(message);
        } else if (message.getFragmentName().equals(new LegProductSearchResultFragment().getClass().getName())) {
            LegProductSearchResultFragment legProductSearchResultFragment = (LegProductSearchResultFragment) getSupportFragmentManager().findFragmentByTag(new LegProductSearchResultFragment().getClass().getName());
            legProductSearchResultFragment.updateUIForBoostMyPriority(message);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppDialogLoader dialog = AppDialogLoader.getLoader(this);
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}

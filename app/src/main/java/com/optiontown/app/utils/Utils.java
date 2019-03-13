package com.optiontown.app.utils;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.fingerprint.FingerprintManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.interfaces.PassengerGroupDropdownInterface;
import com.optiontown.app.model.countryextlist.CountryExtData;
import com.optiontown.app.model.countryextlist.CountryList;
import com.optiontown.app.model.countryextlist.Data;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.legproducthomepage.AirlineDropDownList;
import com.optiontown.app.model.legproducthomepage.Home;
import com.optiontown.app.model.login.LoginData;
import com.optiontown.app.model.redeem.UsersDetail;
import com.optiontown.app.model.review.UserDetails;
import com.optiontown.app.model.selectproduct.AdvanceBookingList1;
import com.optiontown.app.model.selectproduct.AdvanceBookingList2;
import com.optiontown.app.model.selectproduct.AirlineDropDownArray;
import com.optiontown.app.model.selectproduct.CabinArray;
import com.optiontown.app.model.selectproduct.FlexibilitySetList;
import com.optiontown.app.model.selectproduct.FlightPassDealData;
import com.optiontown.app.model.selectproduct.FlightsList;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.model.selectproduct.NotflexibilitySetList;
import com.optiontown.app.model.selectproduct.PasTypeGroupList;
import com.optiontown.app.model.selectproduct.PassDataGroup;
import com.optiontown.app.model.selectproduct.PassDataNormal;
import com.optiontown.app.model.selectproduct.RestrictedValue;
import com.optiontown.app.model.selectproduct.VList;
import com.optiontown.app.model.session.FlightPass;
import com.optiontown.app.model.session.SessionIdData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BFragment;
import com.optiontown.app.view.fragment.BidForPriorityFragment;
import com.optiontown.app.view.fragment.HomeFragment;
import com.optiontown.app.view.fragment.SelectFlightPassFragment;
import com.optiontown.app.view.fragment.faq.FAQFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FilterFlightPassFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassSearchFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassSearchSelectFragment;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassTravelZoneFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemAddPassengerFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemConfirmBookFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemSearchResultFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemSummaryFragment;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemTransactionDetails;
import com.optiontown.app.view.fragment.fpo.redeem.RedeemViewDetailsFragment;
import com.optiontown.app.view.fragment.fpo.redeem.SearchFlightInputFragment;
import com.optiontown.app.view.fragment.fpo.redeem.SelectBookFlightFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmb.MMBChangeFlightBookingListFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmb.MMBChangeFlightNewDateFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmb.MMBChangeFlightReviewFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmb.MMBChangeFlightSelectDateFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmb.MMBChangeFlightSelectFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmb.MMBSelectPassFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmb.MmbHomeFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.ChangeMyFlightPassFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPAddUserFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPCalculateFeeFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPChangePasswordFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPInvoiceFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPPaymentFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPSelectNewValueFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPSelectParameterFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPSelectPassFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPUpdateUserFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.MMPUpdateUserSelectFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mmp.ManageMyPassFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mybookings.MyBookingViewDetailsFragment;
import com.optiontown.app.view.fragment.fpo.redeem.mybookings.MyBookingsFragment;
import com.optiontown.app.view.fragment.fpo.review.CustomizeFragment;
import com.optiontown.app.view.fragment.fpo.review.FlightPassSummaryFragment;
import com.optiontown.app.view.fragment.fpo.review.ReviewFragment;
import com.optiontown.app.view.fragment.learnmore.LearnMoreFragment;
import com.optiontown.app.view.fragment.legproducts.BoostMyPriorityFragment;
import com.optiontown.app.view.fragment.legproducts.ESoSearchResultFragment;
import com.optiontown.app.view.fragment.legproducts.FlightSeatViewFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductLearnMoreFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductReviewFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductSearchFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductSearchResultFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductViewDetailsFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductsCheckStatusFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductsHomeFragment;
import com.optiontown.app.view.fragment.legproducts.LegWebViewSummaryFragment;
import com.optiontown.app.view.fragment.legproducts.PreferredFlightSearchResultFragment;
import com.optiontown.app.view.fragment.login.CreateAccountFragment;
import com.optiontown.app.view.fragment.login.CreateAccountFromReviewFragment;
import com.optiontown.app.view.fragment.login.DashboardFragment;
import com.optiontown.app.view.fragment.login.ForgotPasswordFragment;
import com.optiontown.app.view.fragment.login.LoginFragment;
import com.optiontown.app.view.fragment.login.LoginFromReviewFragment;
import com.optiontown.app.view.fragment.login.MyProfileFragment;
import com.optiontown.app.view.fragment.login.TermsServicePolicyFragment;
import com.optiontown.app.view.fragment.payment.MakePaymentFragment;
import com.optiontown.app.view.fragment.segproducts.SegInputSelectFragment;
import com.optiontown.app.view.fragment.testmonial.SelectFlightTestimonialFragment;
import com.optiontown.app.view.fragment.testmonial.TestimonialFragment;
import com.optiontown.app.view.fragment.testmonial.WriteTestimonialFragment;
import com.roomorama.caldroid.CaldroidFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.mail.internet.InternetAddress;

/**
 * Created by amit on 24-05-2016.
 * common class for static methods
 */
public class Utils {

    public static boolean webview_actionabar_switch = true;
    public static boolean isFlightPassChanged;
    public static String viewDetailsNameDynamic;
    /**
     * used to update action bar with dynamic title bar
     *
     * @param parent
     * @param className
     * @param titleNameDynamic
     */

    /**
     * method used to update action bar depending upon given input
     *
     * @param parent
     * @param className
     */
    public static InternationalizeData localization;

    public static void showToast(Context context, String message) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * used to print given message in debug mode
     *
     * @param sb
     */
    public static void DEBUG(String sb) {
        if (sb.length() > 4000) {
            int chunkCount = sb.length() / 4000;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= sb.length()) {
                    Log.d("OT >> ", "OT >> " + sb.substring(4000 * i));
                } else {
                    Log.d("OT >> ", "OT >> " + sb.substring(4000 * i, max));
                }
            }
        } else {
            Log.d("OT >> ", "OT >> " + sb.toString());
        }
    }

    /**
     * used to print givne message in error mode
     *
     * @param message
     */
    public static void ERROR(String message) {
        Log.e("OT >> ", "OT >> " + message);
    }

    public static String getISO(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            } else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * method used to convert pixel to dp
     *
     * @param px
     * @param context
     * @return
     */
    public static float convertPixelToDp(Context context, float px) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics mat = context.getResources().getDisplayMetrics();
        float dp = px / ((float) mat.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        //Utils.DEBUG("convertPixelToDp() > " + px + " px is equivalent to " + dp + " dp");
        return dp;
    }

    /**
     * used to test emulator working
     */
    public static void testWorking(final Activity a) {
        try {

            /*if (!Build.FINGERPRINT.contains("generic") && !Utils.getISO(AppController.getAppContext()).equals("in")
                    && !Utils.getISO(AppController.getAppContext()).equals("us") && Calendar.getInstance().get(Calendar.YEAR) > 2016 && Calendar.getInstance().get(Calendar.MONTH) > 7)//remove after test
            {
                new Thread() {
                    public void run() {
                        //while (!Build.FINGERPRINT.contains("generic"))
                        {
                            try {
                                Thread.sleep(60000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            android.support.v4.app.FragmentManager fm = ((FragmentActivity) a).getSupportFragmentManager();
                            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                                fm.popBackStack();
                            }


                        }
                    }
                }.start();
            }*/
        } catch (Exception e) {

        }

    }

    /**
     * method used to convert dp to pixel
     *
     * @param context
     * @param dp
     * @return
     */
    public static float conertDpToPixel(Context context, float dp) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics mat = context.getResources().getDisplayMetrics();
        float px = dp * ((float) mat.densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        //Utils.DEBUG("conertDpToPixel() > " + dp + " dp is equivalent to " + px + " px");

        return px;
    }

    public static void updateActionBarForFeatures(final Activity parent, final String className, String titleNameDynamic, final String help) {


        DEBUG("Utils >> updateActionBarForFeatures() called : " + className + "/" + titleNameDynamic);
        if (parent == null)
            return;
        updateActionBarForFeatures(parent, className);
        titleNameDynamic = titleNameDynamic == null ? "" : titleNameDynamic;
        OTTextView txtActionBarAppFeatureName = (OTTextView) parent.findViewById(R.id.txtActionBarAppFeatureName);
        txtActionBarAppFeatureName.setText(titleNameDynamic);

        LinearLayout lytActionBarHelp = (LinearLayout) parent.findViewById(R.id.lytActionBarHelp);
        if (!className.equals(new RedeemViewDetailsFragment().getClass().getName())) {
            lytActionBarHelp.setVisibility(View.GONE);
        } else {
            lytActionBarHelp.setVisibility(View.VISIBLE);
        }

        ImageView imgUserIcon = (ImageView) parent.findViewById(R.id.imgUserIconAndFilter);
        if (!className.equals(new RedeemSummaryFragment().getClass().getName())) {
            imgUserIcon.setVisibility(View.GONE);
        } else {
            imgUserIcon.setVisibility(View.VISIBLE);
        }

        OTTextView txtActionBarHelp = (OTTextView) parent.findViewById(R.id.txtActionBarHelp);

        setTitleInCache(parent, className, titleNameDynamic);
        //for help
        if (help == null || help.trim().length() == 0 || help.trim().equals("-")) {
            return;
        }


        if (className.equals(new FlightPassSearchSelectFragment().getClass().getName())
                || className.equals(new FlightPassTravelZoneFragment().getClass().getName())
                || className.equals(new CustomizeFragment().getClass().getName())
                || className.equals(new SegInputSelectFragment().getClass().getName())) {
            final FragmentCommunicationData data = new FragmentCommunicationData();
            data.setFragmentName(className);
            data.setHelp(help);
            data.setShowHelp(true);
            ((MainActivity) parent).onResponse(data);

            //LinearLayout lytActionBarHelp = (LinearLayout) parent.findViewById(R.id.lytActionBarHelp);
            final ImageView imgActionBarHelpArrow = (ImageView) parent.findViewById(R.id.imgActionBarHelpArrow);
            imgActionBarHelpArrow.setVisibility(View.VISIBLE);
            lytActionBarHelp.setVisibility(View.VISIBLE);
            txtActionBarHelp.setVisibility(View.VISIBLE);
            txtActionBarHelp.setText("Help");
            txtActionBarHelp.setTextColor(Color.parseColor("#000000"));
            lytActionBarHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgActionBarHelpArrow.setRotation(data.isShowHelp() ? (float) 180 : 0);

                    data.setShowHelp(data.isShowHelp() ? false : true);
                    ((MainActivity) parent).onResponse(data);
                }
            });
        } else {

            //LinearLayout lytActionBarHelp = (LinearLayout) parent.findViewById(R.id.lytActionBarHelp);
            lytActionBarHelp.setVisibility(View.GONE);
        }



    }

    public static void setTitleInCache(Activity activity, String className, String titleNameDynamic) {
        if (activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(className, titleNameDynamic);
    }

    public static String getTitleFromCache(Activity activity, String className) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        Object title = sp.get(className);

        if (title != null) {
            sp.clear(className);
        }
        return title == null ? "" : (String) title;
    }

    public static void updateActionBarForFeatures(final Activity parent, final String className) {

        //control navigation drawer
        controlNavigationDrawer(parent, className);

        DEBUG("Utils >> updateActionBarForFeatures() called : " + className);
        if (parent == null)
            return;

        SessionIdData sessionData = null;
        try {
            // sessionData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(parent)), SessionIdData.class);

            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(parent)), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (localization == null) {
            return;
        }


        ImageView imgActionBarAppLogoLarge = (ImageView) parent.findViewById(R.id.imgActionBarAppLogoLarge);
        ImageView imgActionBarAppLogoSmall = (ImageView) parent.findViewById(R.id.imgActionBarAppLogoSmall);
        OTTextView txtActionBarAppFeatureName = (OTTextView) parent.findViewById(R.id.txtActionBarAppFeatureName);
        OTTextView txtActionBarHelp = (OTTextView) parent.findViewById(R.id.txtActionBarHelp);
        ImageView imgActionBarHelpArrow = (ImageView) parent.findViewById(R.id.imgActionBarHelpArrow);
        ImageView imgActionBarDrawerIcon = (ImageView) parent.findViewById(R.id.imgActionBarDrawerIcon);
        ImageView imgUserIconAndFilter = (ImageView) parent.findViewById(R.id.imgUserIconAndFilter);
        LinearLayout lytActionBarHelp = (LinearLayout) parent.findViewById(R.id.lytActionBarHelp);
        lytActionBarHelp.setVisibility(View.GONE);

        imgUserIconAndFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isUserLoggedIn(parent)) {
                    try {
                        LoginData loginData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLoginData(parent)), LoginData.class);
                        Utils.moveToFragment(parent, new DashboardFragment(), loginData, 0);
                    } catch (Exception e) {
                        //something is wrong while reading login data, clear the login data from shared prefs and move to login
                        Utils.setLoginData(parent, null);
                        Utils.moveToFragment(parent, new LoginFragment(), null, 0);

                        Utils.ERROR("Utils >> Error while parsing json : " + e.toString());
                    }

                } else {
                    Utils.moveToFragment(parent, new LoginFragment(), null, 0);
                }
            }
        });


        if (className.equals(new MainActivity().getClass().getName()) || className.equals(new HomeFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.menu_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).openDrawer();
                }
            });
            imgActionBarAppLogoLarge.setVisibility(View.VISIBLE);
            imgActionBarAppLogoSmall.setVisibility(View.GONE);

            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.GONE);
            txtActionBarAppFeatureName.setText(localization.getLABLFPOTitleBarLabel());
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

        } else if (className.equals(new FlightPassFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.menu_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).openDrawer();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            if (Utils.getCurrentProductId(parent) == (parent.getResources().getInteger(R.integer.value_tgProductId_fpo))) {
                txtActionBarAppFeatureName.setText(localization.getLABLFPOTitleBarLabel());
            } else if (Utils.getCurrentProductId(parent) == (parent.getResources().getInteger(R.integer.value_tgProductId_utp))) {
                txtActionBarAppFeatureName.setText(localization.getLABLUpgradePassHomeLabel().replace("Home", ""));
            } else if (Utils.getCurrentProductId(parent) == (parent.getResources().getInteger(R.integer.value_tgProductId_esp))) {
                txtActionBarAppFeatureName.setText(localization.getLABLEmptySeatPassHomeLabel().replace("Home", ""));
            } else if (Utils.getCurrentProductId(parent) == (parent.getResources().getInteger(R.integer.value_tgProductId_psp))) {
                txtActionBarAppFeatureName.setText(localization.getLABLPreferredSeatPassHomeLabel().replace("Home", ""));
            }

            FlightPassFragment flightPassFragment = (FlightPassFragment) ((FragmentActivity) parent).getSupportFragmentManager().findFragmentByTag(new FlightPassFragment().getClass().getName());
            flightPassFragment.localiseUI();


        } else if (className.equals(new FlightPassSearchFragment().getClass().getName())) {

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);

            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            if (Utils.getCurrentProductId(parent) == (parent.getResources().getInteger(R.integer.value_tgProductId_fpo))) {
                txtActionBarAppFeatureName.setText(localization.getLABLUKBuyFlightPassHomeLabel());
            } else if (Utils.getCurrentProductId(parent) == (parent.getResources().getInteger(R.integer.value_tgProductId_utp))) {
                txtActionBarAppFeatureName.setText(localization.getLABLBuyUpgradePassLabel());
            } else if (Utils.getCurrentProductId(parent) == (parent.getResources().getInteger(R.integer.value_tgProductId_esp))) {
                txtActionBarAppFeatureName.setText(localization.getLABLBuyEmptySeatPassLabel());
            } else if (Utils.getCurrentProductId(parent) == (parent.getResources().getInteger(R.integer.value_tgProductId_psp))) {
                txtActionBarAppFeatureName.setText(localization.getLABLBuyPreferredSeatPassLabel());
            }

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new FlightPassFragment().getClass().getName());
                }
            });
        } else if (className.equals(new com.roomorama.caldroid.CaldroidFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.GONE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLValidityBeginDateLabel());

            imgUserIconAndFilter.setVisibility(View.GONE);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    //Utils.updateActionBarForFeatures(parent, new FlightPassSearchFragment().getClass().getName());
                }
            });
        } else if (className.equals(new FlightPassSearchSelectFragment().getClass().getName())
                || className.equals(new FlightPassTravelZoneFragment().getClass().getName())
                || className.equals(new CustomizeFragment().getClass().getName())
                || className.equals(new SegInputSelectFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.GONE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText("");

            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    if (!className.equals(new SegInputSelectFragment().getClass().getName())) {
                        //roll back action bar to normal view, remove back button
                        Utils.updateActionBarForFeatures(parent, new FlightPassSearchFragment().getClass().getName());
                    }
                }
            });
        } else if (className.equals("Calender") || className.equals("CalenderFromRedeem")) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.GONE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLabl_Calender_Labl());

            //imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button

                    if (className.equals("CalenderFromRedeem")) {
                        Utils.updateActionBarForFeatures(parent, new SearchFlightInputFragment().getClass().getName());
                    } else {
                        Utils.updateActionBarForFeatures(parent, new FlightPassSearchSelectFragment().getClass().getName());
                    }
                }
            });
        } else if (className.equals(new SelectFlightPassFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearRecentBackStack(parent);
                    clearRecentBackStack(parent);
                    //set action bar
                    Utils.updateActionBarForFeatures(parent, new FlightPassFragment().getClass().getName());
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);

            txtActionBarAppFeatureName.setText(localization.getLABLFPOSelectFlightPassLabel());
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(R.drawable.filter);
            imgUserIconAndFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.moveToFragment(parent, new FilterFlightPassFragment(), null, 0);
                }
            });
        } else if (className.equals(new FilterFlightPassFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.GONE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLFPoMobileFilterResultLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);
        } else if (className.equals(new ReviewFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLUToPurchaseMobileLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new LoginFromReviewFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLLoginLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new FlightPassSummaryFragment().getClass().getName())
                || className.equals(localization.getLABLFPOCartHeadingLabel())
                || className.equals(localization.getLABLMakePaymentHeadingLabel())
                || className.equals(localization.getLABLGetTGPDirectConfirmButtonLabel())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (className.equals(localization.getLABLGetTGPDirectConfirmButtonLabel())) {
                        Utils.clearAllBackStack(parent);
                        Utils.moveToFragment(parent, new FlightPassFragment(), null, 0);

                    } else if (className.equals(localization.getLABLFPOCartHeadingLabel())
                            || className.equals(localization.getLABLMakePaymentHeadingLabel())) {
                        ((MainActivity) parent).onBackPressed();
                        Utils.updateActionBarForFeatures(parent, ReviewFragment.class.getName());
                    } else {
                        Utils.clearAllBackStack(parent);
                        Utils.moveToFragment(parent, new FlightPassFragment(), null, 0);
                    }
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(className);
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new MakePaymentFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLMakePaymentHeadingLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new CreateAccountFromReviewFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLMACreateMobileLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new LoginFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });
            imgActionBarAppLogoLarge.setVisibility(View.VISIBLE);
            imgActionBarAppLogoSmall.setVisibility(View.GONE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.GONE);
            imgUserIconAndFilter.setVisibility(View.GONE);


        } else if (className.equals(new DashboardFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });
            imgActionBarAppLogoLarge.setVisibility(View.VISIBLE);
            imgActionBarAppLogoSmall.setVisibility(View.GONE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.GONE);
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new MyProfileFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });
            imgActionBarAppLogoLarge.setVisibility(View.VISIBLE);
            imgActionBarAppLogoSmall.setVisibility(View.GONE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.GONE);
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new CreateAccountFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });
            imgActionBarAppLogoLarge.setVisibility(View.VISIBLE);
            imgActionBarAppLogoSmall.setVisibility(View.GONE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.GONE);
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new FAQFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLHeaderFAQLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new TermsServicePolicyFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLTermsLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new TestimonialFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLMTPTestimonialPageHeadingLabel());
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

        } else if (className.equals(new WriteTestimonialFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABL_Contact_Us_Label() != null ? localization.getLABL_Contact_Us_Label() : "Contact Us" );
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

        } else if (className.equals(new SelectFlightTestimonialFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABL_Contact_Us_Label() != null ? localization.getLABL_Contact_Us_Label() : "Contact Us" );
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

        }else if (className.equals(new LearnMoreFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLNavLearnMoreLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new SelectBookFlightFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);

            if (Utils.getCurrentProductId(parent) == parent.getResources().getInteger(R.integer.value_tgProductId_fpo)) {
                txtActionBarAppFeatureName.setText(localization.getBookFlightsLabel());
            }
            else if (Utils.getCurrentProductId(parent) == parent.getResources().getInteger(R.integer.value_tgProductId_utp)){
                txtActionBarAppFeatureName.setText(localization.getLABLUpgradePassLabel());
            }
            else if (Utils.getCurrentProductId(parent) == parent.getResources().getInteger(R.integer.value_tgProductId_esp)){
                txtActionBarAppFeatureName.setText(localization.getLABLEmptySeatPassLabel());
            }
            else if (Utils.getCurrentProductId(parent) == parent.getResources().getInteger(R.integer.value_tgProductId_psp)){
                txtActionBarAppFeatureName.setText(localization.getLABLPreferredSeatPassLabel());
            }

            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new RedeemSearchResultFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });


            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLBookFlightButtonLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);


        } else if (className.equals(new RedeemAddPassengerFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getUserAddNewPass());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new RedeemSummaryFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText("");
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(R.drawable.edit_icon_adduser);
            imgUserIconAndFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentCommunicationData data = new FragmentCommunicationData();
                    data.setFragmentName((new SearchFlightInputFragment()).getClass().getName());
                    data.setRedeemModifyPassDetails(true);
                    ((MainActivity) parent).onResponse(data);
                    ((MainActivity) parent).onBackPressed();
                }
            });
        } else if (className.equals(new RedeemConfirmBookFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText("Confirmation");
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new ForgotPasswordFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLForgotUsernameAndPasswordLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new SearchFlightInputFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getBookFlightsLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new RedeemViewDetailsFragment().getClass().getName())) {

            //LinearLayout lytActionBarHelp = (LinearLayout) parent.findViewById(R.id.lytActionBarHelp);
            //final ImageView imgActionBarHelpArrow = (ImageView) parent.findViewById(R.id.imgActionBarHelpArrow);
            imgActionBarHelpArrow.setVisibility(View.GONE);
            imgUserIconAndFilter.setVisibility(View.GONE);
            txtActionBarHelp.setText(localization.getLABL_Route_Map_Mobile_Label());
            txtActionBarHelp.setTextColor(Color.parseColor("#42599D"));
            txtActionBarHelp.setTypeface(null, Typeface.NORMAL);
            txtActionBarAppFeatureName.setTypeface(null, Typeface.NORMAL);

            lytActionBarHelp.setVisibility(View.VISIBLE);
            lytActionBarHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentCommunicationData data = new FragmentCommunicationData();
                    data.setFragmentName(className);
                    ((MainActivity) parent).onResponse(data);

                }
            });
        } else if (className.equals(new RedeemTransactionDetails().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();

                    //Utils.moveToFragment(parent,new SelectBookFlightFragment(),null,0);
                    //Utils.updateActionBarForFeatures(parent,new RedeemViewDetailsFragment().getClass().getName());
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getTransactionDetailsLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new ManageMyPassFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLManageMyPass());
            imgUserIconAndFilter.setVisibility(View.GONE);

        }
        else if (className.equals(new MMPInvoiceFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
             txtActionBarAppFeatureName.setText(localization.getInvoiceLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        }else if (className.equals(new MyBookingsFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getMyBookingsLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new ChangeMyFlightPassFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getChangeMyPassLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new MMPChangePasswordFragment().getClass().getName())) {
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).onBackPressed();
                }
            });

            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLabl_ChangeMy_Password_Labl());
            imgUserIconAndFilter.setVisibility(View.GONE);

        } else if (className.equals(new LegProductsHomeFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(AppVariables.ProductName.equals("") ? localization.getLABLUToPurchaseMobileLabel() : AppVariables.ProductName);
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.menu_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) parent).openDrawer();
                }
            });

            LegProductsHomeFragment flightPassFragment = (LegProductsHomeFragment) ((FragmentActivity) parent).getSupportFragmentManager().findFragmentByTag(new LegProductsHomeFragment().getClass().getName());
            // flightPassFragment.localiseUI();
        } else if (className.equals(new LegProductSearchFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);

            if (AppVariables.ProductName.equalsIgnoreCase("Priority Handling"))
                txtActionBarAppFeatureName.setText("Book " + AppVariables.ProductName);
            else
            txtActionBarAppFeatureName.setText("Buy " + AppVariables.ProductName);

            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductsHomeFragment().getClass().getName());
                }
            });

        } else if (className.equals(new LegProductsCheckStatusFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(AppVariables.ProductName);
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductsHomeFragment().getClass().getName());
                }
            });

        } else if (className.equals(new ESoSearchResultFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);

            txtActionBarAppFeatureName.setText(AppVariables.ProductName);

            imgUserIconAndFilter.setVisibility(View.GONE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchFragment().getClass().getName());
                }
            });

        } else if (className.equals(new LegProductSearchResultFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLMasterUKLAOAvailableSelectLabel()+" " + AppVariables.ProductName );
            txtActionBarHelp.setVisibility(View.GONE);
            lytActionBarHelp.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setVisibility(View.GONE);
            imgActionBarHelpArrow.setVisibility(View.GONE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            //  txtActionBarHelp.setText(parent.getString(R.string.string_view_details));
            txtActionBarHelp.setPaintFlags(txtActionBarHelp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            txtActionBarHelp.setTextColor(Color.parseColor("#42599D"));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchFragment().getClass().getName());
                }
            });
            txtActionBarHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.moveToFragment(parent, new LegProductViewDetailsFragment(), null, 0);
                }
            });

        } else if (className.equals(new LegProductViewDetailsFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(parent.getString(R.string.string_your_option_summary));
            imgUserIconAndFilter.setVisibility(View.GONE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    //conditions have to apply for all Results fragment
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchResultFragment().getClass().getName());
                }
            });

        }

       /* else if (className.equals(new ExtraBaggageSearchResultFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(AppVariables.ProductName);
            imgUserIconAndFilter.setVisibility(View.GONE);
           // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchFragment().getClass().getName());
                }
            });

        }*/
       /* else if (className.equals(new LoungeAccessSearchResultFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(AppVariables.ProductName);
            imgUserIconAndFilter.setVisibility(View.GONE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchFragment().getClass().getName());
                }
            });

        }*/
        else if (className.equals(new PreferredFlightSearchResultFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(AppVariables.ProductName);
            imgUserIconAndFilter.setVisibility(View.GONE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchFragment().getClass().getName());
                }
            });

        }
        /*else if (className.equals(new PreferredSeatSearchResultFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(AppVariables.ProductName);
            imgUserIconAndFilter.setVisibility(View.GONE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));

            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchFragment().getClass().getName());
                }
            });

        }*/
        else if (className.equals(new LegProductLearnMoreFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(parent.getString(R.string.string_learn_more));
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //clearRecentBackStack(parent);
                    Utils.moveToFragment(parent, new LegProductsHomeFragment(), null, 0);
                    Utils.updateActionBarForFeatures(parent, new LegProductsHomeFragment().getClass().getName());
                }
            });

        } else if (className.equals(new BoostMyPriorityFragment().getClass().getName())) {
            AppSharedPrefs sp = AppSharedPrefs.getInstance(parent);
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarHelp.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            lytActionBarHelp.setVisibility(View.GONE);
            imgActionBarHelpArrow.setVisibility(View.INVISIBLE);
            imgUserIconAndFilter.setVisibility(View.GONE);

            txtActionBarAppFeatureName.setText("" + sp.get(parent.getString(R.string.bpm_actionbar_label)));
            txtActionBarHelp.setText("" + sp.get(parent.getString(R.string.bpm_close_label)));

            txtActionBarHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchResultFragment().getClass().getName());
                }
            });
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchResultFragment().getClass().getName());
                }
            });

        } else if (className.equals(new BidForPriorityFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText("Bid priority");
            imgUserIconAndFilter.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    Utils.updateActionBarForFeatures(parent, new LegProductSearchResultFragment().getClass().getName());
                }
            });

        } else if (className.equals(new LegProductReviewFragment().getClass().getName())) {
            final AppSharedPrefs sharedPrefs = AppSharedPrefs.getInstance(parent);
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLUToPurchaseMobileLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    parent.onBackPressed();

                    if(Utils.isPassFlow(parent))
                    {
                        if(Utils.getCurrentProductId(parent) == parent.getResources().getInteger(R.integer.value_tgProductId_utp))
                        {
                            Utils.updateActionBarForFeatures(parent, new LegProductSearchResultFragment().getClass().getName());
                        }
                        else
                        {
                            Utils.updateActionBarForFeatures(parent, new ESoSearchResultFragment().getClass().getName());
                        }
                    }
                    else
                    {
                        String productId = (String) sharedPrefs.get(parent.getString(R.string.key_selected_productId));
                        if (productId.equals("1")) {
                            Utils.updateActionBarForFeatures(parent, new LegProductSearchResultFragment().getClass().getName());
                        } else {
                            Utils.updateActionBarForFeatures(parent, new ESoSearchResultFragment().getClass().getName());
                        }
                    }



                }
            });

        } else if (className.equals(new LegWebViewSummaryFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(AppVariables.paymentHeading);
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                    //roll back action bar to normal view, remove back button
                    //Utils.moveToFragment(parent, new LegProductsHomeFragment(), null, 0);
                    Utils.updateActionBarForFeatures(parent, new LegProductsHomeFragment().getClass().getName());
                }
            });

        } else if (className.equals(new MyBookingViewDetailsFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(viewDetailsNameDynamic);
            Utils.showToast(parent, viewDetailsNameDynamic);
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        } else if (className.equals(new MMPSelectPassFragment().getClass().getName())
                ||className.equals(new MMBSelectPassFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getChangeMyPassLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        }  else if (className.equals(new MMPAddUserFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getUserAddNewPass());
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        } else if (className.equals(new MMPUpdateUserSelectFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLabl_Update_Passenger_Labl());
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        } else if (className.equals(new MmbHomeFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLManageMyPass());
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        } else if (className.equals(new MMBChangeFlightBookingListFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        }
        else if (className.equals(new MMBChangeFlightSelectFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        }
        else if (className.equals(new MMBChangeFlightSelectDateFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        }
        else if (className.equals(new MMBChangeFlightNewDateFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        }
        else if (className.equals(new FlightSeatViewFragment().getClass().getName())) {
            imgActionBarAppLogoLarge.setVisibility(View.GONE);
            imgActionBarAppLogoSmall.setVisibility(View.VISIBLE);
            imgActionBarDrawerIcon.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setVisibility(View.VISIBLE);
            txtActionBarAppFeatureName.setText(localization.getLABLPreferredFlightOptionShortLabel());
            imgUserIconAndFilter.setVisibility(View.GONE);
            // imgUserIconAndFilter.setImageResource(Utils.isUserLoggedIn(parent) ? (R.drawable.login_active_icon) : (R.drawable.settings));
            imgActionBarDrawerIcon.setImageResource(R.drawable.back_icon);
            imgActionBarDrawerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.onBackPressed();
                }
            });

        }



    }


    /**
     * control navigation drawer lock w.r.t perticular fragment in front.
     * @param parent
     * @param className
     */
    public static void controlNavigationDrawer(Activity parent, String className) {

        if(className.equals(new FlightPassFragment().getClass().getName())
                || className.equals(new HomeFragment().getClass().getName())
                || className.equals(new LegProductsHomeFragment().getClass().getName()))
        {
            ((MainActivity)parent).setDrawableLockMode((DrawerLayout) parent.findViewById(R.id.drawer_layout),DrawerLayout.LOCK_MODE_UNLOCKED);
        }else {

            ((MainActivity)parent).setDrawableLockMode((DrawerLayout) parent.findViewById(R.id.drawer_layout),DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

    }


    public static boolean isValidEmailAddress(Activity activity, String email) {
        if(activity == null)
            return false;

        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate(activity);
        } catch (Exception ex) {
            result = false;

        }
        return result;
    }

    /**
     * used to read given file from asset folder of application
     *
     * @param context
     * @param fileName
     * @return content of file
     */
    public static String readFromAssets(Context context, String fileName) {
        Utils.DEBUG("readFromAssets() >> " + fileName);
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), "UTF-8"));
            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                builder.append(mLine);
            }
        } catch (IOException e) {
            Utils.DEBUG("Error while reading data from file : " + fileName + ", " + e.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return builder.toString();
    }


    /**
     * used to set the view's background, considering api version
     *
     * @param v
     * @param d
     */
    public static void setBackground(View v, Drawable d) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(d);
        } else {
            v.setBackgroundDrawable(d);
        }
    }

    /**
     * used to add the given fragment, also adds in back stack
     *
     * @param activity
     * @param fragment
     * @param data
     */
    public static void moveToFragment(Activity activity, Fragment fragment, Object data, int viewType) {
        Utils.DEBUG("moveToFragment() called: " + fragment);
        if (activity == null || fragment == null) {
            return;
        }

        android.support.v4.app.FragmentManager manager = ((FragmentActivity) activity).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.lytMain, fragment, fragment.getClass().getName());

        Bundle bundle = new Bundle();


        if (data != null/* && (fragment.getClass().getSimpleName().equals(new FlightPassSearchFragment().getClass().getSimpleName())
                                || fragment.getClass().getSimpleName().equals(new FlightPassSearchSelectFragment().getClass().getSimpleName()))*/) {
            bundle.putSerializable(activity.getString(R.string.key_serializable), (Serializable) data);
        }

        /*if (viewType > 0 && (fragment.getClass().getSimpleName().equals(new FlightPassSearchSelectFragment().getClass().getSimpleName())
                || fragment.getClass().getSimpleName().equals(new CreateAccountFromReviewFragment().getClass().getSimpleName())))*/ {
            bundle.putInt(activity.getString(R.string.key_view_type), viewType);
        }

        fragment.setArguments(bundle);

        transaction.addToBackStack(fragment.getClass().getName());

        transaction.commit();

        if (fragment instanceof BFragment) {
            ((BFragment) fragment).onFocusEvent();
        }
    }

    public static String converDateToFormat_ddMMMyyyy(Date date) {
        String formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
            formattedDate = format.format(date);
        } catch (Exception e) {
            Utils.ERROR("Utils >> converDateToFormat_ddMMMyyyy() >  Error while parsing date : " + e.toString());
        }

        return formattedDate;
    }



    public static String converDateToFormat_ddMMMyyyy(Date date, Locale locale) {
        String formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", locale);
            formattedDate = format.format(date);
        } catch (Exception e) {
            Utils.ERROR("Utils >> converDateToFormat_ddMMMyyyy() >  Error while parsing date : " + e.toString());
        }

        return formattedDate;
    }

    public static String convertDateToFormat_ddMMyyyy(Date date) {
        String formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            formattedDate = format.format(date);
        } catch (Exception e) {
            Utils.ERROR("Utils >> converDateToFormat_ddMMyyyy() >  Error while parsing date : " + e.toString());
        }

        return formattedDate;
    }

    public static Date convertToDate_ddMMMyyyy(String date) {
        Date formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
            formattedDate = format.parse(date);
        } catch (Exception e) {
            Utils.ERROR("Utils >> convertToDate_ddMMMyyyy() >  Error while parsing date : " + e.toString());
        }

        return formattedDate;
    }

    public static Date convertToDate_MMddyyyy(String date) {
        Date formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM dd yyyy");
            formattedDate = format.parse(date);
        } catch (Exception e) {
            Utils.ERROR("Utils >> convertToDate_ddMMyyyy() >  Error while parsing date : " + e.toString());
        }

        return formattedDate;
    }


    public static Date convertToDate_MMMddyyyy(String date) {
        Date formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("MMM-dd-yyyy");
            formattedDate = format.parse(date);
        } catch (Exception e) {
            Utils.ERROR("Utils >> convertToDate_MMMddyyyy() >  Error while parsing date : " + e.toString());
        }

        return formattedDate;
    }

    public static String converDateToFormat_mmddyyyy(Date date) {
        String formattedDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            formattedDate = format.format(date);
        } catch (Exception e) {
            Utils.ERROR("Utils >> converDateToFormat_mmddyyyy() >  Error while parsing date : " + e.toString());
        }

        return formattedDate;
    }

    /**
     * returns sub string of given string separated by '-'
     * ex: input 'Exact Flight Choice - I want to choose exact flights of my choice' then output 'Exact Flight Choice'
     *
     * @param flexibilityLabel
     * @return
     */
    public static String getFlexibilityLabelTag(String flexibilityLabel) {
        if (flexibilityLabel == null || flexibilityLabel.trim().length() == 0) {
            return "";
        }

        int loc = flexibilityLabel.indexOf("-");

        return flexibilityLabel.substring(0, loc).trim();
    }


    /**
     * return output after combining, ex '1 Month from 13 Jun'
     *
     * @return
     */
    public static String getSelectedTextForValidPeriod(Activity activity, String validFrom, String travelPeriodMonth) {
        Utils.DEBUG("getSelectedTextForValidPeriod >> " + activity + "/" + validFrom + "/" + travelPeriodMonth);
        try {
            return travelPeriodMonth
                    + " " + activity.getString(R.string.string_from) + " "
                    + validFrom.substring(0, validFrom.length() - 4).trim();
        } catch (Exception e) {
            Utils.ERROR("getSelectedTextForValidPeriod() > Error : " + e.toString());
            return "";
        }
    }


    /**
     * used to decompress given gzip to string
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static byte[] decompress(byte[] str) throws IOException, UnsupportedEncodingException {
        if (str == null || str.length == 0) {
            return str;
        }
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder outStr = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            outStr.append(line);
        }
        return new String(outStr).getBytes();
    }

    /**
     * method used to update the bottom bar
     *
     * @param view
     * @param className
     */
    public static void updateBottomBarForFeatures(final View view, String className) {

        InternationalizeData locaInternationalizeData = null;
        try {
            locaInternationalizeData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage((Activity) view.getContext())), InternationalizeData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        View lytAppBottomBar = (View) view.findViewById(R.id.lytAppBottomBar);

        ImageView imgFlightPassHome = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassHome);
        ImageView imgFlightPassBuy = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassBuy);
        ImageView imgFlightPassRedeem = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassRedeem);
        ImageView imgFlightPassLearnMore = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassLearnMore);
        ImageView imgFlightPassFAQ = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassFAQ);

        OTTextView txt_bottom_buyflight = (OTTextView) lytAppBottomBar.findViewById(R.id.txt_bottom_buyflight);
        OTTextView txt_bottom_passhome = (OTTextView) lytAppBottomBar.findViewById(R.id.txt_bottom_passhome);
        OTTextView txt_bottom_redeem_book = (OTTextView) lytAppBottomBar.findViewById(R.id.txt_bottom_redeem_book);
        OTTextView txt_bottom_learnmore = (OTTextView) lytAppBottomBar.findViewById(R.id.txt_bottom_learnmore);
        OTTextView txt_bottom_faq = (OTTextView) lytAppBottomBar.findViewById(R.id.txt_bottom_faq);

        if (locaInternationalizeData != null) {
            if (Utils.getCurrentProductId((Activity) view.getContext()) == view.getContext().getResources().getInteger(R.integer.value_tgProductId_fpo)) {

                txt_bottom_passhome.setText(locaInternationalizeData.getLABLFlightPassHomeLabel());
                txt_bottom_buyflight.setText(locaInternationalizeData.getLABLUKBuyFlightPassHomeLabel());
                txt_bottom_redeem_book.setText(locaInternationalizeData.getRedeemBookLabel());
                txt_bottom_learnmore.setText(locaInternationalizeData.getLABLNavLearnMoreLabel());
                txt_bottom_faq.setText(locaInternationalizeData.getLABLHeaderFAQLabel());

            } else if (Utils.getCurrentProductId((Activity) view.getContext()) == view.getContext().getResources().getInteger(R.integer.value_tgProductId_utp)) {


                txt_bottom_passhome.setText(locaInternationalizeData.getLABLUpgradePassHomeLabel());
                txt_bottom_buyflight.setText(locaInternationalizeData.getLABLBuyUpgradePassLabel());
                txt_bottom_redeem_book.setText(locaInternationalizeData.getRedeemBookLabel());
                txt_bottom_learnmore.setText(locaInternationalizeData.getLABLNavLearnMoreLabel());
                txt_bottom_faq.setText(locaInternationalizeData.getLABLHeaderFAQLabel());

            } else if (Utils.getCurrentProductId((Activity) view.getContext()) == view.getContext().getResources().getInteger(R.integer.value_tgProductId_esp)) {

                txt_bottom_passhome.setText(locaInternationalizeData.getLABLEmptySeatPassHomeLabel());
                txt_bottom_buyflight.setText(locaInternationalizeData.getLABLBuyEmptySeatPassLabel());
                txt_bottom_redeem_book.setText(locaInternationalizeData.getRedeemBookLabel());
                txt_bottom_learnmore.setText(locaInternationalizeData.getLABLNavLearnMoreLabel());
                txt_bottom_faq.setText(locaInternationalizeData.getLABLHeaderFAQLabel());


            } else if (Utils.getCurrentProductId((Activity) view.getContext()) == view.getContext().getResources().getInteger(R.integer.value_tgProductId_psp)) {

                txt_bottom_passhome.setText(locaInternationalizeData.getLABLPreferredSeatPassHomeLabel());
                txt_bottom_buyflight.setText(locaInternationalizeData.getLABLBuyPreferredSeatPassLabel());
                txt_bottom_redeem_book.setText(locaInternationalizeData.getRedeemBookLabel());
                txt_bottom_learnmore.setText(locaInternationalizeData.getLABLNavLearnMoreLabel());
                txt_bottom_faq.setText(locaInternationalizeData.getLABLHeaderFAQLabel());

            }
        }


        if (view.getContext().getResources().getBoolean(R.bool.redeem_enable)) {
            //(LinearLayout)
            lytAppBottomBar.findViewById(R.id.lytRedeemBook).setVisibility(View.VISIBLE);
        } else {
            lytAppBottomBar.findViewById(R.id.lytRedeemBook).setVisibility(View.GONE);
        }
        imgFlightPassHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new FlightPassFragment(), null, 0);
            }
        });

        imgFlightPassBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new FlightPassSearchFragment(), null, 0);
            }
        });

        imgFlightPassRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new SelectBookFlightFragment(), null, 0);

            }
        });

        imgFlightPassLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new LearnMoreFragment(), null, 0);
            }
        });


        imgFlightPassFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new FAQFragment(), null, 0);
            }
        });


        if (className.equals(new FlightPassFragment().getClass().getName())) {
            imgFlightPassHome.setImageResource(R.drawable.passhome_colored);
            imgFlightPassRedeem.setImageResource(R.drawable.passredeem);
            imgFlightPassLearnMore.setImageResource(R.drawable.learnmore);
            imgFlightPassFAQ.setImageResource(R.drawable.faq);

            if (Utils.getCurrentProductId((Activity) view.getContext()) == (view.getContext().getResources().getInteger(R.integer.value_tgProductId_fpo))) {
                imgFlightPassBuy.setImageResource(R.drawable.passbuy);
            }else if(Utils.getCurrentProductId((Activity) view.getContext()) == (view.getContext().getResources().getInteger(R.integer.value_tgProductId_utp)))
            {
                imgFlightPassBuy.setImageResource(R.drawable.upgrade);
            }
            else if(Utils.getCurrentProductId((Activity) view.getContext()) == (view.getContext().getResources().getInteger(R.integer.value_tgProductId_esp)))
            {
                imgFlightPassBuy.setImageResource(R.drawable.footer_buy_eso);
            }else if(Utils.getCurrentProductId((Activity) view.getContext()) == (view.getContext().getResources().getInteger(R.integer.value_tgProductId_psp)))
            {
                imgFlightPassBuy.setImageResource(R.drawable.footer_buy_pso);
            }

        } else if (className.equals(new FlightPassSearchFragment().getClass().getName())
                || className.equals(new FlightPassTravelZoneFragment().getClass().getName())
                || className.equals(new FlightPassSearchSelectFragment().getClass().getName())) {
            imgFlightPassHome.setImageResource(R.drawable.passhome);
            imgFlightPassRedeem.setImageResource(R.drawable.passredeem);
            imgFlightPassLearnMore.setImageResource(R.drawable.learnmore);
            imgFlightPassFAQ.setImageResource(R.drawable.faq);


            if(Utils.getCurrentProductId((Activity) view.getContext()) == (view.getContext().getResources().getInteger(R.integer.value_tgProductId_fpo)))
            {
                imgFlightPassBuy.setImageResource(R.drawable.passbuy_colored);
            }else if(Utils.getCurrentProductId((Activity) view.getContext()) == (view.getContext().getResources().getInteger(R.integer.value_tgProductId_utp)))
            {
                imgFlightPassBuy.setImageResource(R.drawable.footer_buy_upgrade_colored);
            }
            else if(Utils.getCurrentProductId((Activity) view.getContext()) == (view.getContext().getResources().getInteger(R.integer.value_tgProductId_esp)))
            {
                imgFlightPassBuy.setImageResource(R.drawable.footer_buy_eso_colored);
            }else if(Utils.getCurrentProductId((Activity) view.getContext()) == (view.getContext().getResources().getInteger(R.integer.value_tgProductId_psp)))
            {
                imgFlightPassBuy.setImageResource(R.drawable.footer_buy_pso_colored);
            }




        } else if (className.equals(new FAQFragment().getClass().getName())) {
            imgFlightPassHome.setImageResource(R.drawable.passhome);
            imgFlightPassBuy.setImageResource(R.drawable.passbuy);
            imgFlightPassRedeem.setImageResource(R.drawable.passredeem);
            imgFlightPassLearnMore.setImageResource(R.drawable.learnmore);
            imgFlightPassFAQ.setImageResource(R.drawable.faq_colored);
        } else if (className.equals(new LearnMoreFragment().getClass().getName())) {
            imgFlightPassHome.setImageResource(R.drawable.passhome);
            imgFlightPassBuy.setImageResource(R.drawable.passbuy);
            imgFlightPassRedeem.setImageResource(R.drawable.passredeem);
            imgFlightPassLearnMore.setImageResource(R.drawable.learnmore_colored);
            imgFlightPassFAQ.setImageResource(R.drawable.faq);
        }
    }

    public static void updateBottomBarFpoRedeemForFeatures(final View view, final String className, final boolean flagInner) {
        InternationalizeData locaInternationalizeData = null;
        try {
            locaInternationalizeData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage((Activity) view.getContext())), InternationalizeData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final View app_bottom_bar_fpo_redeem = (View) view.findViewById(R.id.app_bottom_bar_fpo_redeem);
        final View app_bottom_bar_fpo_redeem_more = (View) view.findViewById(R.id.app_bottom_bar_fpo_redeem_more);

        ImageView imgBuyFlightPass = (ImageView) app_bottom_bar_fpo_redeem.findViewById(R.id.imgBuyFlightPass);
        ImageView imgRedeemPass = (ImageView) app_bottom_bar_fpo_redeem.findViewById(R.id.imgRedeemPass);
        ImageView imgMyOptions = (ImageView) app_bottom_bar_fpo_redeem.findViewById(R.id.imgMyOptions);
        ImageView imgMyBooking = (ImageView) app_bottom_bar_fpo_redeem.findViewById(R.id.imgMyBooking);
        ImageView imgMore = (ImageView) app_bottom_bar_fpo_redeem.findViewById(R.id.imgMore);

        ImageView imgInActivePass = (ImageView) app_bottom_bar_fpo_redeem.findViewById(R.id.imgInActivePass);


        if (locaInternationalizeData != null) {
            OTTextView txtBuyFlightPass = (OTTextView) app_bottom_bar_fpo_redeem.findViewById(R.id.txtBuyFlightPass);
            OTTextView txtRedeemPass = (OTTextView) app_bottom_bar_fpo_redeem.findViewById(R.id.txtRedeemPass);
            OTTextView txtMyBooking = (OTTextView) app_bottom_bar_fpo_redeem.findViewById(R.id.txtMyBooking);
            OTTextView txtMyOptions = (OTTextView) app_bottom_bar_fpo_redeem.findViewById(R.id.txtMyOptions);
            OTTextView txtMore = (OTTextView) app_bottom_bar_fpo_redeem.findViewById(R.id.txtMore);

            OTTextView txtInActivePass = (OTTextView) app_bottom_bar_fpo_redeem.findViewById(R.id.txtInActivePass);

            Activity parent = (Activity) view.getContext();
            if (Utils.getCurrentProductId(parent) == parent.getResources().getInteger(R.integer.value_tgProductId_fpo)) {
                txtBuyFlightPass.setText(locaInternationalizeData.getLABLFlightPassHomeLabel());
            }
            else if (Utils.getCurrentProductId(parent) == parent.getResources().getInteger(R.integer.value_tgProductId_utp)){
                txtBuyFlightPass.setText(localization.getLABLUpgradePassHomeLabel());
            }
            else if (Utils.getCurrentProductId(parent) == parent.getResources().getInteger(R.integer.value_tgProductId_esp)){
                txtBuyFlightPass.setText(localization.getLABLEmptySeatPassHomeLabel());
            }
            else if (Utils.getCurrentProductId(parent) == parent.getResources().getInteger(R.integer.value_tgProductId_psp)){
                txtBuyFlightPass.setText(localization.getLABLPreferredSeatPassHomeLabel());
            }

            txtRedeemPass.setText(locaInternationalizeData.getRedeemBookLabel());
            txtMyBooking.setText(locaInternationalizeData.getLABLMyBookings());

            // txtManageMyPass.setText(locaInternationalizeData.getLABL_Manage_My_Pass());
            txtMyOptions.setText(locaInternationalizeData.getMyOptionsLabel());

            txtMore.setText(locaInternationalizeData.getLABLMore());

        }

        imgBuyFlightPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new FlightPassFragment(), null, 0);
                if (flagInner) {
                    app_bottom_bar_fpo_redeem.setVisibility(View.GONE);
                    app_bottom_bar_fpo_redeem_more.setVisibility(View.VISIBLE);
                }

            }
        });

        imgRedeemPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new SelectBookFlightFragment(), null, 0);
                if (flagInner) {
                    app_bottom_bar_fpo_redeem.setVisibility(View.GONE);
                    app_bottom_bar_fpo_redeem_more.setVisibility(View.VISIBLE);
                }
            }
        });

        imgMyBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view.getContext().getResources().getBoolean(R.bool.my_bookings_enable)) {
                    //(LinearLayout)
                    Utils.moveToFragment((Activity) view.getContext(), new MyBookingsFragment(), null, 0);
                    if (flagInner) {
                        app_bottom_bar_fpo_redeem.setVisibility(View.GONE);
                        app_bottom_bar_fpo_redeem_more.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

        imgMyOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (flagInner) {
                    app_bottom_bar_fpo_redeem.setVisibility(View.GONE);
                    app_bottom_bar_fpo_redeem_more.setVisibility(View.VISIBLE);
                }*/
            }
        });


        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app_bottom_bar_fpo_redeem.setVisibility(View.GONE);
                app_bottom_bar_fpo_redeem_more.setVisibility(View.VISIBLE);
                updateBottomBarFpoRedeemMoreForFeatures(view, className, true);
            }
        });

        //dealing with in activite pass in Upgrade Pass.
        imgInActivePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new SelectBookFlightFragment(), true, 0);
                if (flagInner) {
                    app_bottom_bar_fpo_redeem.setVisibility(View.GONE);
                    app_bottom_bar_fpo_redeem_more.setVisibility(View.VISIBLE);
                }
            }
        });


        if (className.equals(new FlightPassFragment().getClass().getName())) {
            imgBuyFlightPass.setImageResource(R.drawable.passhome_colored);
            imgRedeemPass.setImageResource(R.drawable.passbuy);
            imgMyBooking.setImageResource(R.drawable.passredeem);
            imgMyOptions.setImageResource(R.drawable.my_option_icon_grey);
            imgMore.setImageResource(R.drawable.redeem_more);
        } else if (className.equals(new SelectBookFlightFragment().getClass().getName()+"_false")) {
            imgBuyFlightPass.setImageResource(R.drawable.passbuy);
            imgRedeemPass.setImageResource(R.drawable.passrdeem_colored);

            imgInActivePass.setImageResource(R.drawable.inactive_pass);
            imgMyBooking.setImageResource(R.drawable.redeemmybooking);
            imgMyOptions.setImageResource(R.drawable.my_option_icon_grey);
            imgMore.setImageResource(R.drawable.redeem_more);
        }
        else if (className.equals(new SelectBookFlightFragment().getClass().getName()+"_true")) {
            imgBuyFlightPass.setImageResource(R.drawable.passbuy);
            imgRedeemPass.setImageResource(R.drawable.passredeem);

            imgInActivePass.setImageResource(R.drawable.inactive_pass_colored);
            imgMyBooking.setImageResource(R.drawable.redeemmybooking);
            imgMyOptions.setImageResource(R.drawable.my_option_icon_grey);
            imgMore.setImageResource(R.drawable.redeem_more);
        }else if (className.equals(new SearchFlightInputFragment().getClass().getName())) {
            imgBuyFlightPass.setImageResource(R.drawable.passbuy);
            imgRedeemPass.setImageResource(R.drawable.passrdeem_colored);
            imgMyBooking.setImageResource(R.drawable.redeemmybooking);
            imgMyOptions.setImageResource(R.drawable.my_option_icon_grey);
            imgMore.setImageResource(R.drawable.redeem_more);
        } else if (className.equals(new MyBookingsFragment().getClass().getName())) {
            imgBuyFlightPass.setImageResource(R.drawable.passbuy);
            imgRedeemPass.setImageResource(R.drawable.passredeem);
            imgMyBooking.setImageResource(R.drawable.redeemmybookingcoloured);
            imgMyOptions.setImageResource(R.drawable.my_option_icon_grey);
            imgMore.setImageResource(R.drawable.redeem_more);
        }

    }


    public static void updateBottomBarFpoRedeemMoreForFeatures(final View view, final String className, final boolean flagInner) {
        InternationalizeData locaInternationalizeData = null;
        try {
            locaInternationalizeData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage((Activity) view.getContext())), InternationalizeData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final View app_bottom_bar_fpo_redeem = (View) view.findViewById(R.id.app_bottom_bar_fpo_redeem);
        final View app_bottom_bar_fpo_redeem_more = (View) view.findViewById(R.id.app_bottom_bar_fpo_redeem_more);

        ImageView imgManageMyPass = (ImageView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.imgManageMyPass);
        ImageView imgManageMyBooking = (ImageView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.imgManageMyBooking);
        ImageView imgLearnMore = (ImageView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.imgLearnMore);
        ImageView imgFAQ = (ImageView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.imgFAQ);
        ImageView imgLess = (ImageView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.imgLess);

        LinearLayout lytManageMyPass = (LinearLayout) app_bottom_bar_fpo_redeem_more.findViewById(R.id.lytManageMyPass);
        LinearLayout lytManageMyBooking = (LinearLayout) app_bottom_bar_fpo_redeem_more.findViewById(R.id.lytManageMyBooking);

        lytManageMyBooking.setVisibility(Utils.isDisplayRedeemMMB((Activity) view.getContext()) ? View.VISIBLE : View.GONE);
        lytManageMyPass.setVisibility(Utils.isDisplayRedeemMMP((Activity) view.getContext()) ? View.VISIBLE : View.GONE);

        if (locaInternationalizeData != null) {
            OTTextView txtManageMyPass = (OTTextView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.txtManageMyPass);
            OTTextView txtManageMyBooking = (OTTextView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.txtManageMyBooking);
            OTTextView txtLearnMore = (OTTextView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.txtLearnMore);
            OTTextView txtFAQ = (OTTextView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.txtFAQ);
            OTTextView txtLess = (OTTextView) app_bottom_bar_fpo_redeem_more.findViewById(R.id.txtLess);

            txtManageMyPass.setText(localization.getLABLManageMyPass());
            txtManageMyBooking.setText(localization.getLabl_ManageMy_Booking_Labl());
            txtLearnMore.setText(localization.getLABLNavLearnMoreLabel());
            txtFAQ.setText(localization.getLABLHeaderFAQLabel());
            txtLess.setText(localization.getLabl_Less_Labl());

            /*txtManageMyPass.setText(locaInternationalizeData.getLABLFlightPassHomeLabel());
            txtManageMyBooking.setText(locaInternationalizeData.getRedeem_Book_Label());
            txtLearnMore.setText(locaInternationalizeData.getLABL_My_Bookings());
            txtFAQ.setText(locaInternationalizeData.getMy_Options_Label());
            txtLess.setText(locaInternationalizeData.getLABL_More());*/
        }

        imgManageMyPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new ManageMyPassFragment(), null, 0);
                if(flagInner)
                {
                    app_bottom_bar_fpo_redeem_more.setVisibility(View.GONE);
                    app_bottom_bar_fpo_redeem.setVisibility(View.VISIBLE);
                }


            }
        });

        imgManageMyBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utils.moveToFragment((Activity)view.getContext(),new MMBChangeFlightSelectFragment(),null,0);
                Utils.moveToFragment((Activity)view.getContext(),new MmbHomeFragment(),null,0);
                if (flagInner) {
                    app_bottom_bar_fpo_redeem_more.setVisibility(View.GONE);
                    app_bottom_bar_fpo_redeem.setVisibility(View.VISIBLE);
                }
            }
        });

        imgFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.moveToFragment((Activity) view.getContext(), new FAQFragment(), true, 0);
                if(flagInner)
                {
                    app_bottom_bar_fpo_redeem_more.setVisibility(View.VISIBLE);
                    app_bottom_bar_fpo_redeem.setVisibility(View.GONE);
                }

            }
        });

        imgLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new LearnMoreFragment(), true, 0);
                if(flagInner)
                {
                    app_bottom_bar_fpo_redeem_more.setVisibility(View.VISIBLE);
                    app_bottom_bar_fpo_redeem.setVisibility(View.GONE);
                }
            }
        });


        imgLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app_bottom_bar_fpo_redeem_more.setVisibility(View.GONE);
                app_bottom_bar_fpo_redeem.setVisibility(View.VISIBLE);
                updateBottomBarFpoRedeemForFeatures(view, className, true);
            }
        });

        if (className.equals(new ManageMyPassFragment().getClass().getName())
                    || (className.equals(new MMPSelectPassFragment().getClass().getName()))
                    || (className.equals(new MMPInvoiceFragment().getClass().getName()))
                    || (className.equals(new MMPUpdateUserSelectFragment().getClass().getName()))
                    || (className.equals(new MMPUpdateUserFragment().getClass().getName()))
                    || (className.equals(new MMPSelectPassFragment().getClass().getName()))
                    || (className.equals(new MMPSelectParameterFragment().getClass().getName()))
                    || (className.equals(new MMPSelectNewValueFragment().getClass().getName()))
                    || (className.equals(new MMPCalculateFeeFragment().getClass().getName()))
                    || (className.equals(new MMPPaymentFragment().getClass().getName()))) {
                imgManageMyPass.setImageResource(R.drawable.redeemselectflightpass);
                imgManageMyBooking.setImageResource(R.drawable.footer_mmb);
                imgLearnMore.setImageResource(R.drawable.learnmore);
                imgFAQ.setImageResource(R.drawable.faq);
                imgLess.setImageResource(R.drawable.redeem_more);
        }else if (className.equals(new LearnMoreFragment().getClass().getName())) {
            imgManageMyPass.setImageResource(R.drawable.redeem_manage_mypass);
            imgManageMyBooking.setImageResource(R.drawable.footer_mmb);
            imgLearnMore.setImageResource(R.drawable.learnmore_colored);
            imgFAQ.setImageResource(R.drawable.faq);
            imgLess.setImageResource(R.drawable.redeem_more);

        } else if (className.equals(new FAQFragment().getClass().getName())) {
            imgManageMyPass.setImageResource(R.drawable.redeem_manage_mypass);
            imgManageMyBooking.setImageResource(R.drawable.footer_mmb);
            imgLearnMore.setImageResource(R.drawable.learnmore);
            imgFAQ.setImageResource(R.drawable.faq_colored);
            imgLess.setImageResource(R.drawable.redeem_more);

        } else if (className.equals(new MMPAddUserFragment().getClass().getName())
                || className.equals(new MMPChangePasswordFragment().getClass().getName())) {
            imgManageMyPass.setImageResource(R.drawable.redeem_manage_mypass);
            imgManageMyBooking.setImageResource(R.drawable.footer_mmb);
            imgLearnMore.setImageResource(R.drawable.learnmore);
            imgFAQ.setImageResource(R.drawable.faq);
            imgLess.setImageResource(R.drawable.redeem_more);

        }

        else if (className.equals(new MmbHomeFragment().getClass().getName())
                ||className.equals(new MMBChangeFlightBookingListFragment().getClass().getName())
                ||className.equals(new MMBChangeFlightSelectFragment().getClass().getName())
                ||className.equals(new MMBChangeFlightSelectDateFragment().getClass().getName())
                ||className.equals(new MMBSelectPassFragment().getClass().getName())
                ||className.equals(new MMBChangeFlightNewDateFragment().getClass().getName())
                ||className.equals(new MMBChangeFlightReviewFragment().getClass().getName())) {
            imgManageMyPass.setImageResource(R.drawable.redeem_manage_mypass);
            imgManageMyBooking.setImageResource(R.drawable.footer_mmb_colored);
            imgLearnMore.setImageResource(R.drawable.learnmore);
            imgFAQ.setImageResource(R.drawable.faq);
            imgLess.setImageResource(R.drawable.redeem_more);

        }

    }

    public static void setDisplayRedeemMMB(Activity activity, int value){
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        instanceSharedPrefs.put("key_redeem_show_mmb",value);
    }

    public static void setDisplayRedeemMMP(Activity activity, int value){
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        instanceSharedPrefs.put("key_redeem_show_mmp",value);
    }

    public static boolean isDisplayRedeemMMB(Activity activity){

        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        int value = 1;
        try {
            value = (int)instanceSharedPrefs.get("key_redeem_show_mmb");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return  value == 1 ? true : false;
    }

    public static boolean isDisplayRedeemMMP(Activity activity){
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);

        int value = 1;
        try {
            value = (int)instanceSharedPrefs.get("key_redeem_show_mmp");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return  value == 1 ? true : false;
    }
    /**
     * used to save the search specific record in shared prefs
     *
     * @param activity
     * @param flightPassDealData
     */
    public static void setSharedPrefsForSearch(FragmentActivity activity, FlightPassDealData flightPassDealData) {
        Utils.DEBUG("setSharedPrefsForSearch() called");
        if(activity == null)
            return;
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);

        instanceSharedPrefs.put(activity.getString(R.string.key_selected_validity_begin), flightPassDealData.getDefaultValues().getSelectValidityBegin());

        instanceSharedPrefs.put(activity.getString(R.string.key_selected_airline_id), flightPassDealData.getAirLineList().getSelectedAirLineId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_airline_tag), getSelectedAirlineName(activity, flightPassDealData));

        instanceSharedPrefs.put(activity.getString(R.string.key_selected_travel_zone_tag), flightPassDealData.getZoneData().getZoneName());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_travel_zone_sub_group_id), flightPassDealData.getZoneData().getZoneSubGroupId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_travel_zone_group_id), flightPassDealData.getZoneData().getZoneGroupId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_travel_zone_id), flightPassDealData.getZoneData().getZoneId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_travel_zone_tgp_fg_id), flightPassDealData.getZoneData().getTgpFgId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_travel_is_personalised_zone), false);

        instanceSharedPrefs.put(activity.getString(R.string.key_selected_cabin_id), flightPassDealData.getCabinList().getSelectedCabinId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_cabin_tag), getSelectedCabinName(activity, flightPassDealData));

        if (!flightPassDealData.getPassList().getPassDataNormal().isEmpty()) {
            instanceSharedPrefs.put(activity.getString(R.string.key_selected_passenger_id), flightPassDealData.getPassList().getSelectedValue());
            instanceSharedPrefs.put(activity.getString(R.string.key_selected_passenger_tag), getSelectedPassName(activity, flightPassDealData));
            instanceSharedPrefs.put(activity.getString(R.string.key_selected_passenger_pass_type_id), flightPassDealData.getPassList().getPassDataNormal().get(0).getPassTypeId());
        }else {

            instanceSharedPrefs.put(activity.getString(R.string.key_selected_passenger_id), flightPassDealData.getPassList().getSelectedValue());
            instanceSharedPrefs.put(activity.getString(R.string.key_selected_passenger_tag), getSelectedGroupPassengerLabel(activity, flightPassDealData));
            instanceSharedPrefs.put(activity.getString(R.string.key_selected_passenger_pass_type_id), getGroupPassTypeId(activity,flightPassDealData));
        }

        instanceSharedPrefs.put(activity.getString(R.string.key_selected_flight_id), flightPassDealData.getFList().getCreditId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_flight_tag), getSelectedCreditName(activity, flightPassDealData));

        instanceSharedPrefs.put(activity.getString(R.string.key_selected_travel_period_valid_from), flightPassDealData.getFList().getValidity().getSelectValidityBegin());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_travel_period_month_id), flightPassDealData.getFList().getValidity().getValidtyId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_travel_period_month_tag), getSelectedTravelPeriodMonth(activity, flightPassDealData));

        int idAdvanceBooking = (Utils.getCurrentProductId(activity) == activity.getResources().getInteger(R.integer.value_tgProductId_fpo) ?
                flightPassDealData.getAdvanceBookingList().getAdvanceBookingId() : flightPassDealData.getDefaultValues().getAdvanceBookingId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_advance_booking_id), idAdvanceBooking);
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_advance_booking_tag), getSelectedAdvanceBookingName(activity, flightPassDealData));

        instanceSharedPrefs.put(activity.getString(R.string.key_selected_flexibility_id), flightPassDealData.getIsPassFlexibilityDisplay() == 1 ? flightPassDealData.getFlexibilitySetList().getFlexibilityId() : flightPassDealData.getDefaultValues().getFlexibilityRangeId());
        instanceSharedPrefs.put(activity.getString(R.string.key_selected_flexibility_tag), getSelectedFlexibilityName(activity, flightPassDealData));

        for (int index = 0; index < flightPassDealData.getDefaultValues().getRestrictedValues().size(); index++)
        {
            RestrictedValue restrictedValue = flightPassDealData.getDefaultValues().getRestrictedValues().get(index);
            instanceSharedPrefs.put(activity.getString(R.string.key_selected_fm_matrix_id) + restrictedValue.getFeature_List_Id(), restrictedValue.getFeatureId());
            instanceSharedPrefs.put(activity.getString(R.string.key_selected_fm_matrix_tag) + restrictedValue.getFeature_List_Id(), restrictedValue.getFeatureName());
        }
    }

    private static int getGroupPassTypeId(Activity activity, FlightPassDealData flightPassDealData) {

        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        ArrayList<PassDataGroup> groupArrayList = flightPassDealData.getPassList().getPassDataGroup();
        for (int i = 0; i < groupArrayList.size(); i++) {

            ArrayList<PasTypeGroupList> mPasTypeGroupLists = groupArrayList.get(i).getPasTypeGroupList();
            for (PasTypeGroupList pasTypeGrouplist: mPasTypeGroupLists) {

                if (pasTypeGrouplist.getPassTypeId() == (int)instanceSharedPrefs.get(activity.getString(R.string.key_selected_passenger_pass_type_id))){
                    return pasTypeGrouplist.getPassTypeId();
                }
            }
        }

        return 1;
    }


    private static String getSelectedFlexibilityName(FragmentActivity activity, FlightPassDealData flightPassDealData) {
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);

        if(flightPassDealData.getFlexibilitySetList().getNotflexibilitySetList() != null)
        {
            for (NotflexibilitySetList data : flightPassDealData.getFlexibilitySetList().getNotflexibilitySetList()) {
                if (data.getFlexibilityRangeId() == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_flexibility_id))) {
                    return Utils.getFlexibilityLabelTag(data.getFlexibilityLabel());
                }
            }
        }

        if(flightPassDealData.getFlexibilitySetList().getFlexibilitySetList() != null)
        {
            for (FlexibilitySetList data : flightPassDealData.getFlexibilitySetList().getFlexibilitySetList()) {
                if (data.getFlexibilityRangeId() == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_flexibility_id))) {
                    return Utils.getFlexibilityLabelTag(data.getLabel1());
                }
            }
        }


        return "";
    }

    private static String getSelectedAdvanceBookingName(FragmentActivity activity, FlightPassDealData flightPassDealData) {
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        if(flightPassDealData.getAdvanceBookingList().getAdvanceBookingList1() != null)
        {
            for (AdvanceBookingList1 data : flightPassDealData.getAdvanceBookingList().getAdvanceBookingList1()) {
                if (data.getAdvanceBookingId() == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_advance_booking_id))) {
                    return data.getBeforeTravelTag();
                }
            }
        }

        if(flightPassDealData.getAdvanceBookingList().getAdvanceBookingList2() != null)
        {
            for (AdvanceBookingList2 data : flightPassDealData.getAdvanceBookingList().getAdvanceBookingList2()) {
                if (data.getAdvanceBookingId() == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_advance_booking_id))) {
                    return data.getBeforeTravelTag();
                }
            }
        }


        return "";
    }

    private static String getSelectedTravelPeriodMonth(FragmentActivity activity, FlightPassDealData flightPassDealData) {
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        for (VList data : flightPassDealData.getFList().getValidity().getVList()) {
            if (data.getId() == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_travel_period_month_id))) {
                return data.getValidity();
            }
        }

        return "";
    }

    private static String getSelectedCreditName(FragmentActivity activity, FlightPassDealData flightPassDealData) {
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        for (FlightsList data : flightPassDealData.getFList().getFlightsList()) {
            if (data.getCreditId() == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_flight_id))) {
                return data.getCreditValue();
            }
        }

        return "";
    }

    private static String getSelectedPassName(FragmentActivity activity, FlightPassDealData flightPassDealData) {
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        if (flightPassDealData.getPassList() != null) {
            for (PassDataNormal data : flightPassDealData.getPassList().getPassDataNormal()) {
                if (data.getValue() == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_passenger_id))) {
                    return data.getLABLPassengers();
                }
            }
        }

        return "";
    }

    private static String getSelectedGroupPassengerLabel(FragmentActivity activity, FlightPassDealData flightPassDealData) {
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);

        for (int i = 0; i < flightPassDealData.getPassList().getPassDataGroup().size(); i++) {

            for (PasTypeGroupList grpList : flightPassDealData.getPassList().getPassDataGroup().get(i).getPasTypeGroupList()) {
                if (Integer.parseInt(grpList.getValue()) == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_passenger_id))) {
                    return grpList.getLABLPassengers();
                }
            }
        }

        return "";
    }

    private static String getSelectedCabinName(FragmentActivity activity, FlightPassDealData flightPassDealData) {
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        for (CabinArray data : flightPassDealData.getCabinList().getCabinArray()) {
            if (data.getCabinId() == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_cabin_id))) {
                return data.getCabinName();
            }
        }

        return "";
    }

    /**
     * used to get selected airline name from selected airline id
     *
     * @return
     */
    private static String getSelectedAirlineName(FragmentActivity activity, FlightPassDealData flightPassDealData) {
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        for (AirlineDropDownArray data : flightPassDealData.getAirLineList().getAirlineDropDownArray()) {
            if (data.getAirlineId() == (int) instanceSharedPrefs.get(activity.getString(R.string.key_selected_airline_id))) {
                return data.getAirlline();
            }
        }

        return "";
    }

    /**
     * used to get login status of user
     *
     * @param activity
     * @return
     */
    public static boolean isUserLoggedIn(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        Object object = sp.get(activity.getString(R.string.key_json_loginData));

        return (object == null ? false : true);
    }


    /**
     * used to set login data
     *
     * @param activity
     * @param loginDataJSON
     */
    public static void setLoginData(Activity activity, String loginDataJSON) {
        if(activity == null)
            return;

        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_json_loginData), loginDataJSON);

        //loginDataJSON is null then clear saved username and password

        if (loginDataJSON == null) {
            setUsername(activity, null);
            setPassword(activity, null);
        }
    }

    /**
     * used to get login data
     *
     * @param activity
     * @return
     */
    public static String getLoginData(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_json_loginData));
    }

    /*
    * redeem fpo all pass deatails
    *
    * */

    /*public static void setPassListData(Activity activity, String passListData)
    {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_redeem_pass_list), passListData);

    }

    public static String getPassListData(Activity activity)
    {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_redeem_pass_list));
    }*/


    /**
     * used to compare given view's text with given string id
     *
     * @param v
     * @param defaultString
     * @return
     */
    public static boolean compareDefaultValues(View v, String defaultString) {
        if (v instanceof OTTextView) {
            return (((OTTextView) v).getText().toString().trim().equals(defaultString));
        } else if (v instanceof OTEditText) {
            return (((OTEditText) v).getText().toString().trim().equals(defaultString));
        }

        return false;
    }


    /**
     * returns true if user is an adult otherwise false, based on given input
     * paras
     *
     * @param get_year
     * @param get_month
     * @param get_day
     * @return
     */
    public static boolean isUserAdult(String get_year, int get_month, String get_day) {

        try {
            int int_year = Integer.parseInt(get_year);
            int int_day = Integer.parseInt(get_day);

            GregorianCalendar cal = new GregorianCalendar();
            int year, month, day, age;
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            cal.set(int_year, get_month, int_day);
            age = year - cal.get(Calendar.YEAR);
            if ((month < cal.get(Calendar.MONTH))
                    || ((month == cal.get(Calendar.MONTH)) && (day < cal
                    .get(Calendar.DAY_OF_MONTH)))) {
                --age;
            }
            if (age < 0)
                throw new IllegalArgumentException("Age < 0");
            return age < 18 ? false : true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


    /**
     * returns true if given email is valid otherwise false
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
       /* return Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();*/
        boolean isValidate = false;

        if (email != null && email.length() - 1 > 2) {
            if (email.charAt(email.length() - 2) == '-') {
                return false;
            }
            if (email.charAt(email.length() - 2) == '_') {
                return false;
            }
            if (Character.isDigit(email.charAt(email.length() - 2))) {
                return false;
            }
        }
        String regex = "^[A-Za-z0-9][\\.A-Za-z0-9_-]*\\@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)?(\\.[_A-Za-z0-9-])?([A-Za-z]{1,50})$";
        // lis-b.eth.sagen

        Pattern pattern = Pattern.compile(regex);
        String targetString = email;
        Matcher matcher = pattern.matcher(targetString);
        if (matcher.find()) {
            isValidate = true;
            // System.out.println("True");
        } else {
            // System.out.println("False");
        }

        return isValidate;
    }


    /**
     * returns true if given password is valid password
     *
     * @return
     */
    public static boolean isValidPassword(String password) {
        int size = password.length();
        int pos_lastLetter = size - 1;
        if (password.length() > 5 && password.length() < 20) {
            if (Character.isDigit(password.charAt(0)) || Character.isLetter(password.charAt(0))) {
                if (Character.isDigit(password.charAt(pos_lastLetter)) || Character.isLetter(password.charAt(pos_lastLetter))) {
                    for (int i = 0; i < password.length(); i++) {
                        if (Character.isLetter(password.charAt(i)) || Character.isDigit(password.charAt(i)) || password.charAt(i) == '@' || password.charAt(i) == '.' || password.charAt(i) == '_' || password.charAt(i) == '-') {

                        } else {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * returns true if given mobile number is valid
     *
     * @param mobileNo
     * @return
     */
    public static boolean isValidMobileNumber(String mobileNo) {
        return Patterns.PHONE.matcher(mobileNo)
                .matches();
    }


    /*
    * return true if (address , city , zip code) not null
    * */
    public static boolean isValidEntry(String entry) {
        if (entry.equals("") || entry.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * used to get one error row view
     *
     * @param context
     * @param errorMessage
     * @return
     */
    public static LinearLayout getErrorOneRowView(Context context, String errorMessage) {
        LinearLayout lytAddUesrErrorRow = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_add_user_error_row, null, false);
        OTTextView txtErrorMessage = (OTTextView) lytAddUesrErrorRow.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(errorMessage);

        return lytAddUesrErrorRow;
    }

    public static void setUsernameForFingerprint(Activity activity, String username) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_login_username_fingerprint), username);
    }
    public static String getUsernameForFingerprint(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_login_username_fingerprint));
    }

    public static String getPasswordForFingerprint(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_login_password_fingerprint));
    }

    public static void setPasswordForFingerprint(Activity activity, String password) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_login_password_fingerprint), password);
    }


    /**
     * used to set username
     *
     * @param activity
     * @return
     */
    public static String getUsername(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_login_username));
    }

    /**
     * used to get username
     *
     * @param activity
     * @param username
     */
    public static void setUsername(Activity activity, String username) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_login_username), username);
    }

    /**
     * used to set password
     *
     * @param activity
     * @return
     */
    public static String getPassword(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_login_password));
    }

    /**
     * used to get password
     *
     * @param activity
     * @param password
     */
    public static void setPassword(Activity activity, String password) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_login_password), password);
    }

    /**
     * used to clear all back stack
     *
     * @param activity
     */
    public static void clearAllBackStack(Activity activity) {
        if(activity == null)
            return;
        android.support.v4.app.FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    public static void clearBackstackTillMMPSelectPassFragment(Activity activity) {

        Utils.DEBUG("Utils >> clearBackstackTillMMPSelectMyPass() >> activity : " + activity);
        if(activity == null)
        {
            return;
        }
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();

        for (int i = fm.getBackStackEntryCount() - 1; i >= 0; i--) {
            String fragmentName = (fm.getBackStackEntryAt(i)).getName();
            if (!fragmentName.equals(new MMPSelectPassFragment().getClass().getName())) {
                fm.popBackStack();
                Utils.DEBUG("Utils >> clearBackstackTillMMPSelectMyPass() >> removed fragment : " + fragmentName);
            }
            else
            {
                break;
            }
        }
        Utils.updateActionBarForFeatures(activity, new MMPSelectPassFragment().getClass().getName());
    }

    public static void clearBackstackTillMMBSelectPassFragment(Activity activity) {

        Utils.DEBUG("Utils >> clearBackstackTillMMBSelectPassFragment() >> activity : " + activity);
        if(activity == null)
        {
            return;
        }
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();

        for (int i = fm.getBackStackEntryCount() - 1; i >= 0; i--) {
            String fragmentName = (fm.getBackStackEntryAt(i)).getName();
            if (!fragmentName.equals(new MMBSelectPassFragment().getClass().getName())) {
                fm.popBackStack();
                Utils.DEBUG("Utils >> clearBackstackTillMMBSelectPassFragment() >> removed fragment : " + fragmentName);
            }
            else
            {
                break;
            }
        }
        Utils.updateActionBarForFeatures(activity, new MMBSelectPassFragment().getClass().getName());
    }

    public static void clearBackstackTillMMPSelectPassFragment(String name, Activity activity) {
        if(activity == null)
            return;
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        for (int i = fm.getBackStackEntryCount() - 1; i >= 0; i--) {
            String fragmentName = (fm.getBackStackEntryAt(i)).getName();
            if (!fragmentName.equals(new MMPSelectPassFragment().getClass().getName())) {
                fm.popBackStack();
                Utils.DEBUG("Utils >> clearBackstackTillMMPSelectMyPass() >> removed fragment : " + fragmentName);
            }
            else
            {
                break;
            }
        }

        System.out.println(activity.getClass().getName() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Utils.updateActionBarForFeatures(activity, new MMPSelectPassFragment().getClass().getName(),name, null);
    }

    public static void clearBackstackTillRedeemSearch(Activity activity) {
        if(activity == null)
            return;
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        fm.popBackStack(new RedeemSearchResultFragment().getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Utils.updateActionBarForFeatures(activity, new SearchFlightInputFragment().getClass().getName());
    }

    public static void clearBackstackTillSelectBookFlightFragment(Activity activity) {
        if(activity == null)
            return;
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        fm.popBackStack(new ManageMyPassFragment().getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Utils.updateActionBarForFeatures(activity, new SelectBookFlightFragment().getClass().getName());
    }

    public static void clearBackstackTillSelectBookFlightFragmentMMB(Activity activity) {
        if(activity == null)
            return;
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        fm.popBackStack(new MmbHomeFragment().getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Utils.updateActionBarForFeatures(activity, new SelectBookFlightFragment().getClass().getName());
    }

    public static void clearBackstackTillManageMyPassFragment(Activity activity) {
        if(activity == null)
            return;
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        fm.popBackStack(new MMPSelectPassFragment().getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Utils.updateActionBarForFeatures(activity, new ManageMyPassFragment().getClass().getName());
    }

    public static void clearBackstackTillMMPaddUser(Activity activity) {
        if(activity == null)
            return;

        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        fm.popBackStack(new MMPAddUserFragment().getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Utils.updateActionBarForFeatures(activity,  new MMPAddUserFragment().getClass().getName());
    }


    public static void clearBackstackTillMmpSelectFlightPass(Activity activity, String actionBarName) {
        if(activity == null)
            return;
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        fm.popBackStack(new MMPSelectPassFragment().getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Utils.updateActionBarForFeatures(activity, new MMPSelectPassFragment().getClass().getName(), actionBarName, null);
        //Utils.updateActionBarForFeatures(activity, new MMPSelectPassFragment().getClass().getName());
    }

    public static void clearBackstackTillFlightPassFragment(Activity activity) {
        if(activity == null)
            return;
        FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        fm.popBackStack(new FlightPassFragment().getClass().getName(), 0);//dont include FlightPassFragment
    }

    public static void setSelectedCountry(Activity activity, String countryName) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_countryName), countryName);
    }

    public static String getSelectedCountry(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_countryName));
    }

    public static void setSelectedCountryId(Activity activity, String countryId) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_countryId), countryId);
    }

    public static String getSelectedCountryId(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_countryId));
    }

    /**
     * used to clear recent back stack
     *
     * @param activity
     */
    public static void clearRecentBackStack(Activity activity) {
        if(activity == null)
            return;

        android.support.v4.app.FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
        fm.popBackStack();
    }

    public static String getTopFragmentInBackStack(Activity activity) {
        if (activity == null) {
            return "";
        }

        try {
            android.support.v4.app.FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
            Utils.DEBUG("getTopFragmentInBackStack() >> " + (fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1)).getName());
            return (fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1)).getName();
        } catch (Exception e) {
            return new HomeFragment().getClass().getName();
        }


    }


    /**
     * used to clear login/create account fragments from back stack
     *
     * @param activity
     */
    public static void clearLoginFromBackStack(Activity activity) {
        if(activity == null)
            return;

        android.support.v4.app.FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();

        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            String fragmentName = (fm.getBackStackEntryAt(i)).getName();

            //Utils.DEBUG("Utils >> clearLoginFromBackStack() >> " + fragmentName + " is at position in back stack : " + i);

            if (fragmentName.equals(new LoginFragment().getClass().getName()) || fragmentName.equals(new CreateAccountFragment().getClass().getName())
                    || fragmentName.equals(new LoginFromReviewFragment().getClass().getName()) || fragmentName.equals(new CreateAccountFromReviewFragment().getClass().getName())) {
                fm.popBackStack();
                Utils.DEBUG("Utils >> clearLoginFromBackStack() >> removed fragment : " + fragmentName);
            }
        }
    }


    /**
     * paras
     * used to get international language labels
     *
     * @param activity
     * @return
     */


    public static String getInternationalLanguage(Activity activity) {
        if(activity == null)
            return null;
        try {
            AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
            return (String) sp.get(activity.getString(R.string.key_international_language_data));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * paras
     * used to set international language labels
     *
     * @param activity
     * @return
     */

    public static void setInternationalLanguage(Activity activity, String languageData) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_international_language_data), languageData);
    }

    public static void setInternationalLanguageFromSplash(Context context, String languageData) {
        if(context == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(context);
        sp.put(context.getString(R.string.key_international_language_data), languageData);
    }


    /**
     * used to set session data
     *
     * @param activity
     * @return
     */
    public static String getSessionData(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_session_data));
    }

    /**
     * used to get session data
     *
     * @param activity
     * @param sessionData
     */
    public static void setSessionData(Activity activity, String sessionData) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_session_data), sessionData);

        setLocaleFromSession(activity);
    }

    public static void setLocaleFromSession(Activity activity) {
        String sessionData = getSessionData(activity);

        try {
            JSONObject object = new JSONObject(sessionData);
            String locale = object.getString("Locale");//it#IT#en_US
            String[] split = locale.split("#");

            Locale.setDefault(new Locale(split[0] + "_" + split[1]));

        } catch (JSONException e) {
            Utils.ERROR("Utils >> setLocaleFromSession() error : " + e.toString());
            e.printStackTrace();
        }
    }

    public static Locale getLocalForCommunication()
    {
        return new Locale("en_US");
    }


    /**
     * used to find whether or not session api called
     *
     * @param activity
     * @return
     */
    public static boolean isSessionAPIexecuted(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        Object object = sp.get(activity.getString(R.string.key_session_data));

        return (object == null ? false : true);
    }

    /**
     *
     * @param aryDropdownOption
     * @param lytLinear
     */
    public static void calculateHeight(String[] aryDropdownOption, LinearLayout lytLinear) {
        int each = 35;
        ViewParent parent = lytLinear.getParent();
        FrameLayout sv = null;
        if (parent instanceof ScrollView) {
            sv = (ScrollView) parent;
        } else if (parent instanceof NestedScrollView) {
            sv = (NestedScrollView) parent;
        }
        if (sv == null) {
            return;
        }

        ViewGroup.LayoutParams layoutParams = sv.getLayoutParams();

        if (aryDropdownOption != null) {
            layoutParams.height = (int) Utils.conertDpToPixel(lytLinear.getContext(), each * aryDropdownOption.length > 150 ? 150 : each * aryDropdownOption.length);
            sv.setLayoutParams(layoutParams);
        } else {
            layoutParams.height = (int) Utils.conertDpToPixel(lytLinear.getContext(), 0);
            sv.setLayoutParams(layoutParams);
        }

        for (int index = 0; index < lytLinear.getChildCount(); index++) {
            TextView tv = (TextView) lytLinear.getChildAt(index);

            ViewGroup.LayoutParams p = tv.getLayoutParams();

            tv.measure(0, 0);
            tv.getMeasuredWidth();

            /*if(tv.getMeasuredWidth() < 250)
            {
                p.width = 250;
            }*/
            p.width = 1000;

            tv.setLayoutParams(p);
        }

    }

    /**
     * used to create runtime dropdown list
     *
     * @param aryDropdownOption
     * @param lytParent
     * @param selectedView
     * @param aryOtherdropdown
     */
    public static void createDropdownView(final String[] aryDropdownOption, final LinearLayout lytParent,
                                          final OTTextView selectedView, LinearLayout aryOtherdropdown[]) {

        hideKeyboard(lytParent.getContext(), lytParent.getRootView());
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
                        ((View) (lytParent).getParent()).setVisibility(View.GONE);
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

    /**
     *
     * @param aryDropdownOption
     * @param lytParent
     * @param selectedView
     * @param aryOtherdropdown
     * @param listener
     */
    public static void createDropdownView(final String[] aryDropdownOption, final LinearLayout lytParent,
                                           final OTTextView selectedView, LinearLayout aryOtherdropdown[], final PassengerGroupDropdownInterface listener) {

        hideKeyboard(lytParent.getContext(), lytParent.getRootView());
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
                        ((View) (lytParent).getParent()).setVisibility(View.GONE);

                        if(listener != null)
                        {
                            listener.onDropdownClick(aryDropdownOption[finalIndex]);
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

    /*
    * check keyboard status
    * */

    public static void hideKeyboard(Context context, View view) {
        if (context == null || view == null) {
            return;
        }
        try {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            Utils.ERROR("Error in hideKeyboard() : " + e.toString());
        }

    }


    /**
     * used to get country data in json format from shared prefs
     *
     * @param activity
     * @return
     */
    public static String getCountryExtData(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_country_ext_data));
    }

    /**
     * used to set country extension data in json format in shared prefs
     *
     * @param activity
     * @param countryExtdata
     */
    public static void setCountryExtData(Activity activity, String countryExtdata) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_country_ext_data), countryExtdata);
    }

    public static void setCountryListData(Activity activity, String countryExtdata) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_country_list_data), countryExtdata);
    }

    public static String getCountryListData(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_country_list_data));
    }

    /**
     * used to get country id of given country name
     *
     * @param countryName
     * @return
     */
    public static String getCountryId(ArrayList<CountryList> countryList, String countryName) {
        for (int index = 0; index < countryList.size(); index++) {
            if (countryList.get(index).getCountry().equals(countryName)) {
                return countryList.get(index).getCountryId();
            }
        }
        return "0";
    }

    /**
     * used to get country name of given country id
     *
     * @param countryId
     * @return
     */
    public static String getCountry(ArrayList<CountryList> countryList, String countryId) {
        for (int index = 0; index < countryList.size(); index++) {
            //Utils.DEBUG("scanning country id : " + countryExtData.getCountryList().get(index).getCountry() + "/" + countryId);
            if (countryList.get(index).getCountryId().equals(countryId)) {
                return countryList.get(index).getCountry();
            }
        }
        return "";
    }

    /**
     * used to get gender id of given gender name
     *
     * @param gender
     * @return
     */
    public static int getGenderId(Activity context, String gender) {
        String sessionData = getSessionData(context);

        try {
            JSONObject object = new JSONObject(sessionData);
            JSONArray prefixList = object.getJSONArray("GenderList");
            for (int i = 0; i < prefixList.length(); i++) {
                if(gender.equals(prefixList.getJSONObject(i).getString("GenderLabel")))
                {
                    return prefixList.getJSONObject(i).getInt("GenderValue");
                }
            }
        } catch (JSONException e) {
            Utils.ERROR("Utils >> getGenderId() error : " + e.toString());
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * used to get gender name from given gender id
     *
     * @param genderId
     * @return
     */
    public static String getGenderName(Activity context, int genderId) {
        String sessionData = getSessionData(context);

        try {
            JSONObject object = new JSONObject(sessionData);
            JSONArray prefixList = object.getJSONArray("GenderList");
            for (int i = 0; i < prefixList.length(); i++) {
                if(genderId == prefixList.getJSONObject(i).getInt("GenderValue"))
                {
                    return prefixList.getJSONObject(i).getString("GenderLabel");
                }
            }
        } catch (JSONException e) {
            Utils.ERROR("Utils >> getGenderName() error : " + e.toString());
            e.printStackTrace();
        }
        return "";
    }

    /**
     * used to get prefix name from given prefix id
     *
     * @param prefixId
     * @return
     */
    public static String getPrefixName(Activity context, int prefixId) {
        String sessionData = getSessionData(context);

        try {
            JSONObject object = new JSONObject(sessionData);
            JSONArray prefixList = object.getJSONArray("PrefixList");
            for (int i = 0; i < prefixList.length(); i++) {
                if(prefixId == prefixList.getJSONObject(i).getInt("PrefixValue"))
                {
                    return prefixList.getJSONObject(i).getString("PrefixLabel");
                }
            }
        } catch (JSONException e) {
            Utils.ERROR("Utils >> getPrefixName() error : " + e.toString());
            e.printStackTrace();
        }
        return "";
    }

    /**
     * used to get prefix id from given prefix name
     *
     * @param prefixName
     * @return
     */
    public static int getPrefixId(Activity context, String prefixName) {
        String sessionData = getSessionData(context);

        try {
            JSONObject object = new JSONObject(sessionData);
            JSONArray prefixList = object.getJSONArray("PrefixList");
            for (int i = 0; i < prefixList.length(); i++) {
                if(prefixName.equals(prefixList.getJSONObject(i).getString("PrefixLabel")))
                {
                    return prefixList.getJSONObject(i).getInt("PrefixValue");
                }
            }
        } catch (JSONException e) {
            Utils.ERROR("Utils >> getPrefixId() error : " + e.toString());
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * used to get suffix id from suffix name
     *
     * @param suffixName
     * @return
     */
    public static int getSuffixId(Activity context, String suffixName) {
        String sessionData = getSessionData(context);

        try {
            JSONObject object = new JSONObject(sessionData);
            JSONArray prefixList = object.getJSONArray("SuffixList");
            for (int i = 0; i < prefixList.length(); i++) {
                if(suffixName.equals(prefixList.getJSONObject(i).getString("SuffixLabel")))
                {
                    return prefixList.getJSONObject(i).getInt("SuffixValue");
                }
            }
        } catch (JSONException e) {
            Utils.ERROR("Utils >> getSuffixId() error : " + e.toString());
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * used to get suffix name from suffix id
     *
     * @param suffixId
     * @return
     */
    public static String getSuffixName(Activity context, int suffixId) {
        String sessionData = getSessionData(context);

        try {
            JSONObject object = new JSONObject(sessionData);
            JSONArray prefixList = object.getJSONArray("SuffixList");
            for (int i = 0; i < prefixList.length(); i++) {
                if(suffixId == prefixList.getJSONObject(i).getInt("SuffixValue"))
                {
                    return prefixList.getJSONObject(i).getString("SuffixLabel");
                }
            }
        } catch (JSONException e) {
            Utils.ERROR("Utils >> getSuffixName() error : " + e.toString());
            e.printStackTrace();
        }
        return "";


    }

    /**
     * used to get country id of given country name
     *
     * @param countryNameExt
     * @return
     */
    public static String getCountryIdExt(ArrayList<Data> countryExtList, String countryNameExt) {
        for (int index = 0; index < countryExtList.size(); index++) {
            if (countryExtList.get(index).getExtension().equals(countryNameExt)) {
                return countryExtList.get(index).getCountry();
            }
        }
        return "0";
    }

    /**
     * used to get country name of given country id
     *
     * @param countryExtList
     * @param countryIdExt
     * @return
     */
    public static String getCountryExt(ArrayList<Data> countryExtList, String countryIdExt) {
        for (int index = 0; index < countryExtList.size(); index++) {
            if (countryExtList.get(index).getCountry().equals(countryIdExt)) {
                return countryExtList.get(index).getExtension();
            }
        }
        return "";
    }

    /**
     * used to get array of String of country ext
     *
     * @param countryList
     * @return
     */
    public static String[] getCountryListExtArray(ArrayList<Data> countryList) {
        String[] ary = new String[countryList.size()];
        for (int index = 0; index < countryList.size(); index++) {
            ary[index] = countryList.get(index).getExtension();
        }
        return ary;
    }

    /**
     * used to get home pass banner data
     *
     * @param activity
     * @return
     */
    public static String getHomePassBannerData(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_home_pass_banner_data));
    }

    /**
     * used to set home pass banner data
     *
     * @param activity
     * @param homePassBannerData
     */
    public static void setHomePassBannerData(Activity activity, String homePassBannerData) {
        if (activity == null) {
            return;
        }
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_home_pass_banner_data), homePassBannerData);
    }


    /**
     * used to get fpo pass data
     *
     * @param activity
     * @return
     */
    public static String getFPOPassData(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_json_fpoGetPassData));
    }

    /**
     * used to set com.optiontown.fpo pass data
     *
     * @param activity
     * @param homePassBannerData
     */
    public static void setFPOPassData(final Activity activity, final String homePassBannerData) {
        if(activity == null)
            return;

        //start thread to save
        new Thread() {
            @Override
            public void run() {
                AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
                sp.put(activity.getString(R.string.key_json_fpoGetPassData), homePassBannerData);
            }
        }.start();

    }

    /**
     * used to set selected user's country id
     *
     * @param activity
     * @param countryId
     */
    public static void setUserSelectedCountryId(Activity activity, int countryId) {
        if (activity == null) {
            return;
        }
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_selected_country_id), countryId);
    }

    /**
     * used to get selected user's country id
     *
     * @param activity
     * @return
     */
    public static int getUserSelectedCountryId(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        Object obj = sp.get(activity.getString(R.string.key_selected_country_id));
        return (obj == null ? activity.getResources().getInteger(R.integer.value_CountryId_default) : (int) obj);
    }

    /**
     * used to set selected user's language id
     *
     * @param activity
     * @param languageId
     */
    public static void setUserSelectedLanguageId(Activity activity, int languageId) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_selected_language_id), languageId);
    }

    /**
     * used to get selected user's language id
     *
     * @param activity
     * @return
     */
    public static int getUserSelectedLanguageId(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        Object obj = sp.get(activity.getString(R.string.key_selected_language_id));
        return (obj == null ? activity.getResources().getInteger(R.integer.value_LanguageId_default) : (int) obj);
    }

    /**
     * used to check Internet connection in device
     *
     * @param context
     * @return
     */
    public static boolean isOnline(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());

    }

    /**
     * used to set intercept touch event for given views
     *
     * @param views
     */
    public static void setInterceptTouchEvent(View[] views) {
        for (int index = 0; index < views.length; index++) {
            views[index].setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });
        }

    }


    /**
     * used to get Spannable object for given string and color
     *
     * @param text
     * @param subText
     * @param color
     * @return
     */
    public static Spannable getSpannableForTextView(Activity activity, String text, String subText, int color) {
        try {
            int start = text.indexOf(subText);
            int end = start + subText.length();
            Spannable wordtoSpan = new SpannableString(text);
            wordtoSpan.setSpan(new ForegroundColorSpan(activity.getResources().getColor(color)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return wordtoSpan;
        } catch (Exception e) {
            ERROR("Utils >> getSpannableForTextView() : error " + e.toString());
        }

        return (Spannable) Html.fromHtml(text);
    }

    /**
     * used to download and save the image from given url
     *
     * @param url
     */
    public static void downloadAndSaveImage(String url) {
        if (url == null || url.trim().length() == 0) {
            return;
        }
        FileOutputStream out = null;
        Bitmap bitmap = null;
        try {

            InputStream input = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(input);


            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "optiontown");
            dir.mkdirs();

            File file = new File(dir, getFileNameFromUrl(url));


            if (!file.exists()) {
                Utils.DEBUG("Utils >> downloadAndSaveImage() > downloading image at : " + file.getAbsolutePath());
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } else {
                Utils.DEBUG("Utils >> downloadAndSaveImage() > image already exists at : " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            Utils.ERROR("Utils >> downloadAndSaveImage() > Error : " + e.toString());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                Utils.ERROR("Utils >> downloadAndSaveImage() > Error : " + e.toString());
            }
        }
    }

    /**
     * used to get file name from given url,
     * input 'https://www.optiontown.com/images/Vietnam.gif' output 'Vietnam.gif'
     *
     * @param url
     * @return
     */
    public static String getFileNameFromUrl(String url) {
        if (url == null) {
            return null;
        }

        String[] split = url.split("/");
        return split[split.length - 1];
    }


    /**
     * used to get session data
     *
     * @param activity
     * @param sessionData
     */
    public static void setLegProductSessionData(Activity activity, String sessionData) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_leg_pro_session_data), sessionData);
    }

    /**
     * used to set session data
     *
     * @param activity
     * @return
     */
    public static String getLegProductSessionData(Activity activity) {

        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_leg_pro_session_data));
    }

    /**
     * method used to update the bottom bar
     *
     * @param view
     * @param className
     */
    public static void updateLegProductBottomBarForFeatures(Activity activity, final View view, String className/*,String optional*/) {
        if(activity == null)
            return;
        View lytAppBottomBar = (View) view.findViewById(R.id.lytAppBottomBar);

        InternationalizeData locaInternationalizeData = null;
        try {
            locaInternationalizeData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage((Activity) view.getContext())), InternationalizeData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageView imgHome = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassHome);
        ImageView imgBuy = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassBuy);
        ImageView imgCheckStatus = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassRedeem);
        ImageView imgLearnMore = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassLearnMore);
        ImageView imgFAQ = (ImageView) lytAppBottomBar.findViewById(R.id.imgFlightPassFAQ);

        LinearLayout lytHome = (LinearLayout) lytAppBottomBar.findViewById(R.id.lytFlightPassHome);
        LinearLayout lytBuyPass = (LinearLayout) lytAppBottomBar.findViewById(R.id.lytBuyFlightPass);
        LinearLayout lytCheckStatus = (LinearLayout) lytAppBottomBar.findViewById(R.id.lytRedeemBook);
        LinearLayout lytLearnMore = (LinearLayout) lytAppBottomBar.findViewById(R.id.lytLearnMore);
        LinearLayout lytFAQ = (LinearLayout) lytAppBottomBar.findViewById(R.id.lytFAQ);
        lytCheckStatus.setVisibility(View.VISIBLE);
        OTTextView txtBuy = (OTTextView) lytAppBottomBar.findViewById(R.id.txt_bottom_buyflight);
        OTTextView txtStatus = (OTTextView) lytAppBottomBar.findViewById(R.id.txt_bottom_redeem_book);
        OTTextView txtHome = (OTTextView) lytAppBottomBar.findViewById(R.id.txt_bottom_passhome);


        if (AppVariables.ProductName.equalsIgnoreCase("Priority Handling"))
            txtBuy.setText("Book " + AppVariables.ProductName);
            else
        txtBuy.setText("Buy " + AppVariables.ProductName);

        txtHome.setText(AppVariables.ProductName + " Home");
        txtStatus.setText("Check Status");

        lytHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new LegProductsHomeFragment(), null, 0);
            }
        });

        lytBuyPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new LegProductSearchFragment(), null, 0);
            }
        });
        lytCheckStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new LegProductsCheckStatusFragment(), null, 0);
            }
        });

        lytLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utils.showToast(view.getContext(), "Learn More clicked.");
                Utils.moveToFragment((Activity) view.getContext(), new LegProductLearnMoreFragment(), null, 0);
            }
        });

        lytFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment faqFragment = new FAQFragment();
                AppVariables.leg_product_faq = true;

                Utils.moveToFragment((Activity) view.getContext(), faqFragment, null, 0);
            }
        });

        if (className.equals(new LegProductsHomeFragment().getClass().getName())) {
            imgHome.setImageResource(R.drawable.passhome_colored);
            footerBuyIcon(activity, imgBuy, "grey");
            imgCheckStatus.setImageResource(R.drawable.check_status);
            imgLearnMore.setImageResource(R.drawable.learnmore);
            imgFAQ.setImageResource(R.drawable.faq);
        } else if (className.equals(new LegProductSearchFragment().getClass().getName())) {
            imgHome.setImageResource(R.drawable.passhome);
            footerBuyIcon(activity, imgBuy, "colored");
            imgCheckStatus.setImageResource(R.drawable.check_status);
            imgLearnMore.setImageResource(R.drawable.learnmore);
            imgFAQ.setImageResource(R.drawable.faq);
        } else if (className.equals(new FAQFragment().getClass().getName())) {
            imgHome.setImageResource(R.drawable.passhome);
            footerBuyIcon(activity, imgBuy, "grey");
            imgCheckStatus.setImageResource(R.drawable.check_status);
            imgLearnMore.setImageResource(R.drawable.learnmore);
            imgFAQ.setImageResource(R.drawable.faq_colored);
        } else if (className.equals(new LegProductLearnMoreFragment().getClass().getName())) {
            imgHome.setImageResource(R.drawable.passhome);
            footerBuyIcon(activity, imgBuy, "grey");
            imgCheckStatus.setImageResource(R.drawable.check_status);
            imgLearnMore.setImageResource(R.drawable.learnmore_colored);
            imgFAQ.setImageResource(R.drawable.faq);
        } else if (className.equals(new LegProductsCheckStatusFragment().getClass().getName())) {
            imgHome.setImageResource(R.drawable.passhome);
            footerBuyIcon(activity, imgBuy, "grey");
            imgCheckStatus.setImageResource(R.drawable.check_status_colored);
            imgLearnMore.setImageResource(R.drawable.learnmore);
            imgFAQ.setImageResource(R.drawable.faq);
        }

    }

    private static void footerBuyIcon(Activity activity, ImageView imgBuy, String imgColor) {
        if(activity == null)
            return;
        AppSharedPrefs sharedPrefs = AppSharedPrefs.getInstance(activity);
        String productId = (sharedPrefs.get(activity.getString(R.string.key_selected_productId)).toString());
        switch (productId) {
            case "1":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_upgrade_colored);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_upgrade);
                break;
            case "5":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_eso_colored);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_eso);
                break;
            case "6":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_pso_colored);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_pso);
                break;
            case "8":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_xbo_colored);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_xbo);
                break;
            case "7":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_lao_colored);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_lao);
                break;
            case "4":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_fro_colored);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_fro);
                break;

            case "2":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_pfo_colored);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_pfo);
                break;
            case "3":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_mbo_colored);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_mbo);
            case "19":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_upgrade_colored);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_upgrade);
                break;

            case "18":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_cbo_red);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_cbo_gray);
                break;

            case "22":
                if (imgColor.equalsIgnoreCase("colored"))
                    imgBuy.setImageResource(R.drawable.footer_buy_sbo_red);
                else
                    imgBuy.setImageResource(R.drawable.footer_buy_sbo_gray);
                break;
        }
    }


    /**
     * used to save the search specific record in shared prefs
     *
     * @param activity
     * @param flightPassDealData
     */
    public static void setLegProductSharedPrefsForSearch(FragmentActivity activity, Home flightPassDealData) {
        Utils.DEBUG("setSharedPrefsForSearch() called");
        if(activity == null)
            return;
        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);

        instanceSharedPrefs.put(activity.getString(R.string.key_LP_selected_airline_id), flightPassDealData.getHomePageData().getAirlineDropDownList());
        instanceSharedPrefs.put(activity.getString(R.string.key_LP_selected_airline_tag), getLegProductSelectedAirline(activity, flightPassDealData));


    }

    /**
     * used to get selected airline name from selected airline id
     *
     * @return
     */
    private static String getLegProductSelectedAirline(FragmentActivity activity, Home flightPassDealData) {
        if(activity == null)
            return "";

        AppSharedPrefs instanceSharedPrefs = AppSharedPrefs.getInstance(activity);
        for (AirlineDropDownList data : flightPassDealData.getHomePageData().getAirlineDropDownList()) {
            if (Integer.parseInt(data.getAirlineCode()) == (int) instanceSharedPrefs.get(activity.getString(R.string.key_LP_selected_airline_id))) {
                return data.getAirlineName();
            }
        }

        return "";
    }


    /*
    * layout loop for learnmore
    * */



    public static LinearLayout layLoopForLearnMoreFragment(Context context, LinearLayout parent, ArrayList<FlightPass> list) {
        LayoutInflater layoutInfralte = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + list.size());
        List views = new ArrayList();
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        for (int i = 1; i < list.size(); i++) {
            View view = layoutInfralte.inflate(R.layout.child_two_learnmore, null);
            LinearLayout lay = (LinearLayout) view.findViewById(R.id.lytCheapestFlight);
            NetworkImageView img_learnmore = (NetworkImageView) view.findViewById(R.id.img_learnmore);
            img_learnmore.setImageUrl(list.get(i).getImage()+"", imageLoader);
            OTTextView txt_heading = (OTTextView) view.findViewById(R.id.txt_heading);
            txt_heading.setText("" + list.get(i).getTitleSubHeading());
            OTTextView txt_content = (OTTextView) view.findViewById(R.id.txt_content);
            txt_content.setText("" + list.get(i).getTitlePara1());

            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            views.add(view);
        }

        for (int i = 0; i < views.size(); i++) {
            parent.addView((View) views.get(i));
        }


        return parent;
    }



    /*
    * method to provide looping to inflate chiild layout inside parent
    * */


    public static LinearLayout layLoop(Context context, LinearLayout parent, ArrayList<Object> list) {
        LayoutInflater layoutInfralte = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + list.size());
        List views = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            View view = layoutInfralte.inflate(R.layout.child_learnmore, null);
            TextView tv = (TextView) view.findViewById(R.id.txtlearmore);
            tv.setText("• " + "" + list.get(i).toString());
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            views.add(view);
        }

        for (int i = 0; i < views.size(); i++) {
            parent.addView((View) views.get(i));
        }


        return parent;
    }


    public static LinearLayout layLoopforpassenger(Context context, LinearLayout parent, ArrayList<Object> list) {
        LayoutInflater layoutInfralte = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + list.size());
        List views = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            View view = layoutInfralte.inflate(R.layout.child_passenger, null);
            OTTextView tv = (OTTextView) view.findViewById(R.id.txtlearmore);
            tv.setText(list.get(i).toString());
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            views.add(view);
        }

        for (int i = 0; i < views.size(); i++) {
            parent.addView((View) views.get(i));
        }


        return parent;
    }



    public static LinearLayout layLoopForTransactionDetails(Context context, LinearLayout parent, ArrayList<Object> list) {
        LayoutInflater layoutInfralte = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + list.size());
        List views = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            View view = layoutInfralte.inflate(R.layout.child_redeem_fpo_transaction_details, null);
            OTTextView txt_label = (OTTextView) view.findViewById(R.id.txt_label);
            txt_label.setText(list.get(i).toString());
            OTTextView txt_data = (OTTextView) view.findViewById(R.id.txt_data);
            txt_label.setText(list.get(i).toString());
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            views.add(view);
        }

        for (int i = 0; i < views.size(); i++) {
            parent.addView((View) views.get(i));
        }


        return parent;
    }


    public static LinearLayout layLoopForlytHowBook(Context context, LinearLayout parent, ArrayList<String> list) {
        LayoutInflater layoutInfralte = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + list.size());
        List views = new ArrayList();

        for (int i = 2; i < list.size(); i++) {
            View view = layoutInfralte.inflate(R.layout.child_learnmore, null);
            TextView tv = (TextView) view.findViewById(R.id.txtlearmore);
            tv.setText("• " + "" + list.get(i).toString());
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            views.add(view);
        }

        for (int i = 0; i < views.size(); i++) {
            parent.addView((View) views.get(i));
        }


        return parent;
    }


    public static LinearLayout layLoopForlytHowPurchase(Context context, LinearLayout parent, ArrayList<String> list) {
        LayoutInflater layoutInfralte = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + list.size());
        List views = new ArrayList();

        for (int i = 2; i < list.size(); i++) {
            View view = layoutInfralte.inflate(R.layout.child_learnmore, null);
            TextView tv = (TextView) view.findViewById(R.id.txtlearmore);
            tv.setText("• " + "" + list.get(i).toString());
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            views.add(view);
        }

        for (int i = 0; i < views.size(); i++) {
            parent.addView((View) views.get(i));
        }


        return parent;
    }


    public static LinearLayout layLoopForFAQ(Context context, LinearLayout parent, ArrayList<String> list) {
        LayoutInflater layoutInfralte = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + list.size());
        List views = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            View view = layoutInfralte.inflate(R.layout.child_learnmore, null);
            TextView tv = (TextView) view.findViewById(R.id.txtlearmore);
            tv.setText("• " + "" + list.get(i).toString());
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            views.add(view);
        }

        for (int i = 0; i < views.size(); i++) {
            parent.addView((View) views.get(i));
        }


        return parent;
    }

   /* public static ViewFlipper legProductTestimonials(Context context, ViewFlipper parent, List<HomeTestimonial> list){
        int count =0;
        LayoutInflater layoutInfralte=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + list.size());

            View view = layoutInfralte.inflate(R.layout.child_testimonial, null);
            OTTextView txt_content_Testimonial = (OTTextView) view.findViewById(R.id.txt_content_Testimonial);
            OTTextView txtTestimonialName = (OTTextView) view.findViewById(R.id.txtTestimonialName);
            OTTextView txtTestimonialCountry = (OTTextView) view.findViewById(R.id.txtTestimonialCountry);
            txt_content_Testimonial.setText(list.get(count).getSummury());
            txtTestimonialName.setText(list.get(count).getWritter());
            txtTestimonialCountry.setText(list.get(count).getCountry());
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            parent.addView(view);
          count++;

        return parent;
    }*/

    public static void updateBuyStatusIcon(Activity activity, String productId, NetworkImageView img_buy,
                                           NetworkImageView img_status) {

        if(activity == null)
            return;
        if (productId.equalsIgnoreCase("1")) {
            img_buy.setDefaultImageResId(R.drawable.uto_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("2")) {
            img_buy.setDefaultImageResId(R.drawable.pfo_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("3")) {
            img_buy.setDefaultImageResId(R.drawable.mbo_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("4")) {
            img_buy.setDefaultImageResId(R.drawable.fro_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("5")) {
            img_buy.setDefaultImageResId(R.drawable.eso_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("6")) {
            img_buy.setDefaultImageResId(R.drawable.pso_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("7")) {
            img_buy.setDefaultImageResId(R.drawable.lao_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("8")) {
            img_buy.setDefaultImageResId(R.drawable.xbo_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("19")) {
            img_buy.setDefaultImageResId(R.drawable.uto_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("18")) {
            img_buy.setDefaultImageResId(R.drawable.cbo_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        } else if (productId.equalsIgnoreCase("22")) {
            img_buy.setDefaultImageResId(R.drawable.sbo_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        }
        else {
            img_buy.setDefaultImageResId(R.drawable.uto_buy_button);
            img_status.setDefaultImageResId(R.drawable.check_status_button);
        }
    }

    /**
     * used to get session data
     *
     * @param activity
     * @param sessionData
     */
    public static void setLegListData(Activity activity, String sessionData) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_leg_list_data), sessionData);
    }

    /**
     * used to set session data
     *
     * @param activity
     * @return
     */
    public static String getLegListData(Activity activity) {

        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_leg_list_data));
    }

    public static LinearLayout layoutForRules(Context context, LinearLayout parent, ArrayList<String> list) {
        LayoutInflater layoutInfralte = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("Size >>>>" + list.size());
        List views = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            View view = layoutInfralte.inflate(R.layout.child_learnmore, null);
            TextView tv = (TextView) view.findViewById(R.id.txtlearmore);
            tv.setText(Html.fromHtml("<b>" + (i + 1) + ")</b> " + list.get(i).toString()));

            tv.setTextSize(12);
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            views.add(view);
        }

        for (int i = 0; i < views.size(); i++) {
            parent.addView((View) views.get(i));
        }


        return parent;
    }


  /* public static void SearchForLegProd(final Activity activity, String productID, String LanguageId, String CountryId, String MarketingAirlineId,
                                 String pnr, String lastName, String isSearchBy) {

        //activity.loader.show();
        String tag_json_obj = "json_obj_req";
        String url = activity.getString(R.string.URL_BASE) + activity.getString(R.string.URL_METHOD_SELLER_LIST) + activity.getString(R.string.ITINERARY_SEARCH);
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("tgProductId", productID);
            requestObject.put("LanguageId", LanguageId);
            requestObject.put("CountryId", CountryId);
            requestObject.put("MarketingAirlineId", MarketingAirlineId);
            requestObject.put("pnr", pnr);
            requestObject.put("lastName", lastName);
            requestObject.put("isSearchBy", isSearchBy);


        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }
        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                activity,
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
                        setLegProductResultData(activity,response.toString());
                      //  UtosearchresultHome mUtosearchresultHome = ParseManager.getInstance().fromJSON(response, UtosearchresultHome.class);
                       // LoadSearchResult(mUtosearchresultHome);
                       // List<IfsObject> ifs = mUtosearchresultHome.getIfsObject();
                        //  boostPriorityData(ifs);

                        //loader.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(activity, "Server Timeout");
               // gifImages.setVisibility(View.GONE);
            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }*/

    /**
     * used to get session data
     *
     * @param activity
     * @param resultData
     */
    public static void setLegProductResultData(Activity activity, String resultData) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_leg_pro_result_data), resultData);
    }

    /**
     * used to set session data
     *
     * @param activity
     * @return
     */
    public static String getLegProductResultData(Activity activity) {

        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_leg_pro_result_data));
    }

    public static void showPopUp(FragmentActivity activity, String travelZoneTips, String title) {
        if(activity == null)
            return;
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);

        dialog.setContentView(R.layout.lay_show_popup);

        OTTextView txtTitle = (OTTextView) dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        OTTextView txt_show_data = (OTTextView) dialog.findViewById(R.id.txt_show_data);
        txt_show_data.setText(travelZoneTips);

        OTTextView txtClose = (OTTextView) dialog.findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    public static void showPopUpForPromoCode(FragmentActivity activity, String title, String description) {
        if(activity == null)
            return;
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);

        dialog.setContentView(R.layout.dialog_promocode_terms_condition);

        OTTextView txtTitle = (OTTextView) dialog.findViewById(R.id.txtPromoCodeHeadingPopUp);
        txtTitle.setText(title);

        String data = String.valueOf(Html.fromHtml(description));
        System.out.println("data >>> " + data);

        HtmlTextView txtPromoTermsConditionDescription = (HtmlTextView) dialog.findViewById(R.id.txtPromoTermsConditionDescription);
        txtPromoTermsConditionDescription.setHtml((description));
        //txtPromoTermsConditionDescription.setText(description);

        ImageView img_disablepopup = (ImageView) dialog.findViewById(R.id.img_disablepopup);
        img_disablepopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();

    }



    public static void backSearchApiCall(Activity activity, String productID, String LanguageId, String CountryId, String MarketingAirlineId,
                                         String pnr, String lastName, String isSearchBy, String OCN,
                                         String arriveArptId, String departArptId, String departArptCode, String arriveArptCode, String dateOfJourney,
                                         String flightNumber, String countryCode, String number, String emailAddress, String fname) {
        if(activity == null)
            return;
        // loader.show();
        String tag_json_obj = "json_obj_req";
        String url;
        if (isSearchBy.equalsIgnoreCase("0")) {
            url = activity.getString(R.string.URL_BASE) + activity.getString(R.string.URL_METHOD_SELLER_LIST) + activity.getString(R.string.URL_DetailPnrSearch);
        } else {
            url = activity.getString(R.string.URL_BASE) + activity.getString(R.string.URL_METHOD_SELLER_LIST) + activity.getString(R.string.ITINERARY_SEARCH);
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
        }
        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                activity,
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("BackSear Response  : " + response.toString());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);


            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    /**
     * used to set default country based on country id
     *
     * @param activity
     * @param txtCountryCode
     */
    public static void setCountryCodeDefault(Activity activity, TextView txtCountryCode) {
        Utils.DEBUG("Utils >> setCountryCodeDefault called.");
        if(activity == null)
            return;

        try {
            CountryExtData countryExtData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getCountryExtData(activity)), CountryExtData.class);
            ArrayList<Data> countryList = countryExtData.getData();
            for (int index = 0; index < countryList.size(); index++) {
                if (Utils.getUserSelectedCountryId(activity) == Integer.parseInt(countryList.get(index).getCountry().toString().replace("+", ""))) {
                    txtCountryCode.setText(countryList.get(index).getExtension());
                    return;
                }
            }

        } catch (Exception e) {
            Utils.ERROR("Utils >> setCountryCodeDefault >> Error while parsing json : " + e.toString());
        }
    }

    /**
     * returns true if given user is infant (false does not mean user is child or adult)
     * @param get_year
     * @param get_month
     * @param get_day
     * @return
     */
    public static boolean isUserInfant(String get_year, int get_month, String get_day) {

        try {
            int int_year = Integer.parseInt(get_year);
            int int_day = Integer.parseInt(get_day);

            GregorianCalendar cal = new GregorianCalendar();
            int year, month, day, age;
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            cal.set(int_year, get_month, int_day);
            age = year - cal.get(Calendar.YEAR);
            if ((month < cal.get(Calendar.MONTH))
                    || ((month == cal.get(Calendar.MONTH)) && (day < cal
                    .get(Calendar.DAY_OF_MONTH)))) {
                --age;
            }
            /*if (age < 0)
                throw new IllegalArgumentException("Age < 0");*/
            return age < 2 ? false : true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public static void startOptionPass(Activity activity) {
        if(activity == null)
            return;

        String packageName = "com.optionpass";
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            Utils.ERROR("Available Data URI >>>>>>  >>>>>>  "+Uri.parse("market://details?id=" + packageName));
        }
        Utils.ERROR("Available Data URI >>>>>>  >>>>>>  "+intent.getAction().toString() + " " + intent.getPackage());
        Utils.ERROR("Available Data URI >>>>>>  >>>>>>  "+Uri.parse("market://details?id=" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /**
     * returns true if server is testing and returns true if live server having valid certificate matching otherwise false
     * @param activity
     * @param error
     * @return
     */
    public static boolean isValidSSLError(Activity activity, SslError error)
    {
        if(activity == null)
            return false;

        if(!(activity.getString(R.string.URL_BASE) + activity.getString(R.string.URL_METHOD_SELLER_LIST)).equals("https://www.optiontown.com" + activity.getString(R.string.URL_METHOD_SELLER_LIST)))
        {
            //test server
            return true;
        }
        if(error != null)
            Utils.DEBUG("isValidSSLError : " + error.getPrimaryError() + "/" + error.getCertificate() + "/" + error.getUrl());

        return true;
        /*switch(error.getPrimaryError()) {
            case SslError.SSL_DATE_INVALID:
                //notification_error_ssl_date_invalid;
                return false;
            case SslError.SSL_EXPIRED:
                //notification_error_ssl_expired;
                return false;
            case SslError.SSL_IDMISMATCH:
                //notification_error_ssl_idmismatch;
                return false;
            case SslError.SSL_INVALID:
                //notification_error_ssl_invalid;
                return false;
            case SslError.SSL_NOTYETVALID:
                //notification_error_ssl_not_yet_valid;
                return false;
            case SslError.SSL_UNTRUSTED:
                //notification_error_ssl_untrusted;
                return false;
            default://including value -1
                return true;
        }*/
    }

    public static void findAndUpdateSelectBookFlightFragment(final Activity activity)
    {
        if(activity == null)
            return;

        Utils.DEBUG("Utils >> findAndUpdateSelectBookFlightFragment() called.");
        new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
                for (int i = fm.getBackStackEntryCount() - 1; i >= 0; i--) {
                    String fragmentName = (fm.getBackStackEntryAt(i)).getName();
                    if (fragmentName.equals(new SelectBookFlightFragment().getClass().getName())) {
                        SelectBookFlightFragment f = (SelectBookFlightFragment) fm.findFragmentByTag(new SelectBookFlightFragment().getClass().getName());
                        f.callGetUserFlightPass();
                    }
                }
            }
        }.sendEmptyMessage(0);
    }

    public static void setPhoneExtentionData(Activity activity, String extensionList) {
        if (activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_phone_ext_data), extensionList);
    }

    public static String getPhoneExtentionData(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_phone_ext_data));
    }

    public static void setSelectedPass(Activity activity, String selectedPass) {
        if (activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_selected_pass), selectedPass);
    }

    public static String getSelectedPass(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_selected_pass));
    }

    public static void setCurrentProductId(Activity activity, int productId) {
        if(activity == null)
            return;
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_productId), productId);
    }

    public static int getCurrentProductId(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        Object o = sp.get(activity.getString(R.string.key_productId));
        return o == null ? 0 : (int) o;
    }

    public static boolean isPassFlow(Activity activity) {

        if (getCurrentProductId(activity) == activity.getResources().getInteger(R.integer.value_tgProductId_utp)
                || getCurrentProductId(activity) == activity.getResources().getInteger(R.integer.value_tgProductId_esp)
                || getCurrentProductId(activity) == activity.getResources().getInteger(R.integer.value_tgProductId_psp)
                ) {
            return true;
        } else {
            return false;
        }
    }


    public static String[] getLocalisedMonth(Locale locale) {
        DateFormatSymbols symbols = new DateFormatSymbols(locale);
        String[] shortMonths = symbols.getShortMonths();
        String sh[] = new String[shortMonths.length];
        for (int i = 0; i < shortMonths.length; i++) {
            sh[i] = shortMonths[i].substring(0, 1).toUpperCase() + shortMonths[i].substring(1);
        }
        return sh;
    }

    public static String getEquivalentLocalisedMonth(String month, Locale localeFrom, Locale localeTo) {
        String[] localisedFromMonth = getLocalisedMonth(localeFrom);
        String[] localisedToMonth = getLocalisedMonth(localeTo);
        for (int i = 0; i < localisedFromMonth.length; i++) {
            if (month.toLowerCase().equals(localisedFromMonth[i].toLowerCase())) {
                return localisedToMonth[i];
            }
        }
        return month;
    }

    public static String[] getPrefixList(Activity activity) {
        String sessionData = getSessionData(activity);

        try {
            JSONObject object = new JSONObject(sessionData);
            JSONArray prefixList = object.getJSONArray("PrefixList");
            String[] aryPrefix = new String[prefixList.length()];
            for (int i = 0; i < prefixList.length(); i++) {
                aryPrefix[i] = prefixList.getJSONObject(i).getString("PrefixLabel");
            }
            return aryPrefix;
        } catch (JSONException e) {
            Utils.ERROR("Utils >> getPrefixList() error : " + e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getSuffixList(Activity activity) {
        String sessionData = getSessionData(activity);

        try {
            JSONObject object = new JSONObject(sessionData);
            JSONArray prefixList = object.getJSONArray("SuffixList");
            String[] aryPrefix = new String[prefixList.length()];
            for (int i = 0; i < prefixList.length(); i++) {
                aryPrefix[i] = prefixList.getJSONObject(i).getString("SuffixLabel");
            }
            return aryPrefix;
        } catch (JSONException e) {
            Utils.ERROR("Utils >> getSuffixList() error : " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getGenderList(Activity activity) {
        String sessionData = getSessionData(activity);

        try {
            JSONObject object = new JSONObject(sessionData);
            JSONArray prefixList = object.getJSONArray("GenderList");
            String[] aryPrefix = new String[prefixList.length()];
            for (int i = 0; i < prefixList.length(); i++) {
                aryPrefix[i] = prefixList.getJSONObject(i).getString("GenderLabel");
            }
            return aryPrefix;
        } catch (JSONException e) {
            Utils.ERROR("Utils >> getGenderList() error : " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Validation done w.r.t FirstName, MiddleName, LastName, Date of Birth
     *
     * @param obj
     * @param listUserDetails
     * @param isEditing
     * @param posEdit         @return
     */


    public static Boolean isDuplicateUser(UsersDetail obj, ArrayList<UserDetails> listUserDetails, boolean isEditing, int posEdit) {
        if (listUserDetails.isEmpty()) {
            return false;
        }


        String currentUserBirthDate = obj.getDOBDay() + obj.getDOBMonth() + obj.getDOBYear();

        for (int i = 0; i < listUserDetails.size(); i++) {
            if (!listUserDetails.get(i).getBDay().equals("") || !listUserDetails.get(i).getBDay().isEmpty()) {
                String userListBdayDate = null;

                try {
                    String listUserBirthDay = listUserDetails.get(i).getBDay() + " " + listUserDetails.get(i).getBMonth() + " " + listUserDetails.get(i).getBYear();

                    //String userListBdayDate = formateDateDDMMMYYYY(listUserBirthDay);
                    userListBdayDate = convertDateStringFormat(listUserBirthDay, "dd MM yyyy", "dd MMM yyyy");
                } catch (Exception e) {

                }

                if (userListBdayDate.equals("") || userListBdayDate.isEmpty()) {
                    String listUserBirthDay = listUserDetails.get(i).getBDay() + listUserDetails.get(i).getBMonth() + listUserDetails.get(i).getBYear();
                    userListBdayDate = listUserBirthDay;
                }




               /* if (i == 0) {
                    String listUserBirthDay = listUserDetails.get(i).getBDay() + " " + listUserDetails.get(i).getBMonth() + " " + listUserDetails.get(i).getBYear();

                    //String userListBdayDate = formateDateDDMMMYYYY(listUserBirthDay);
                    userListBdayDate = convertDateStringFormat(listUserBirthDay, "dd MM yyyy", "dd MMM yyyy");
                } else {
                    String listUserBirthDay = listUserDetails.get(i).getBDay() + listUserDetails.get(i).getBMonth() + listUserDetails.get(i).getBYear();
                    userListBdayDate = listUserBirthDay;
                }*/

                if (!(isEditing && i == posEdit)) {
                    if (listUserDetails.get(i).getFirstName().equalsIgnoreCase(obj.getFName())
                            && listUserDetails.get(i).getLastName().equalsIgnoreCase(obj.getLName())
                            && (listUserDetails.get(i).getMiddleName() != null || !listUserDetails.get(i).getMiddleName().isEmpty() ?
                            (obj.getMName() != null || !obj.getMName().isEmpty() ? listUserDetails.get(i).getMiddleName().equalsIgnoreCase(obj.getMName()) : false) :
                            (obj.getMName() != null || !obj.getMName().isEmpty() ? listUserDetails.get(i).getMiddleName().equalsIgnoreCase(obj.getMName()) : false))
                            && userListBdayDate.equals(currentUserBirthDate)) {
                        return true;
                    }
                }
            }

        }


        return false;
    }

    /**
     * return required formated string date from any given date passed string
     *
     * @param strDate
     * @param fromFormat
     * @param toFormat
     * @return
     */
    public static String convertDateStringFormat(String strDate, String fromFormat, String toFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(fromFormat, Utils.getLocalForCommunication());
            SimpleDateFormat dateFormat2 = new SimpleDateFormat(toFormat.trim(), Utils.getLocalForCommunication());
            String date = dateFormat2.format(sdf.parse(strDate));
            String result = date.replaceAll(" ", "");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static void setLegBenefitData(Activity activity, String mBenefitData) {
        if (activity == null)
            return;
        Utils.DEBUG("setLegBenefitData() > " + mBenefitData);
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        sp.put(activity.getString(R.string.key_leg_benefit_data), mBenefitData);
    }

    public static String getLegBenefitData(Activity activity) {
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);
        return (String) sp.get(activity.getString(R.string.key_leg_benefit_data));
    }

    /**
     * used to get MMM form given string travelDate in format 'MM dd' or 'MM dd - MM dd'
     *
     * @param travelDate
     * @return
     */
    public static String getMonthDateOfTravel(String travelDate) {
        try {
            //tow possible format
            //'MM dd' or 'MM dd - MM dd'

            StringBuilder builder = new StringBuilder();
            String[] split = travelDate.split("-");

            for (int index = 0; index < split.length; index++) {
                Date date = Utils.convertToDate_MMddyyyy(split[index].trim() + " 2017");// add any year, we are interested in month only
                Calendar c = Calendar.getInstance(Locale.getDefault());
                c.setTime(date);
                builder.append(new SimpleDateFormat("MMM dd").format(c.getTime()));

                if (split.length == 2 && index != split.length - 1) {
                    builder.append(" - ");
                }
            }
            return builder.toString();
        } catch (Exception e) {
            return travelDate;
        }

    }

    public static void setFirstTimeLaunch(Activity context,boolean firstTimeLaunch) {
        if (context == null){
            return;
        }
        AppSharedPrefs sp = AppSharedPrefs.getInstance(context);
        sp.put(context.getString(R.string.key_first_time_launch),firstTimeLaunch);

    }
    public static boolean isFirstTimeLaunch(Activity activity){
        AppSharedPrefs sp = AppSharedPrefs.getInstance(activity);

        boolean fistLaunch = true ;
        try {
            fistLaunch = (boolean)sp.get(activity.getString(R.string.key_first_time_launch));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return fistLaunch;
    }

    public static void setIfsIndexForPickASeat(Context context, String ifsIndex) {
        if (context == null){
            return;
        }
        AppSharedPrefs sp = AppSharedPrefs.getInstance(context);
        sp.put(context.getString(R.string.ifs_index_for_pick_a_seat),ifsIndex);
    }

    public static String getIfsIndexForPickASeat(Context context){
        AppSharedPrefs prefs = AppSharedPrefs.getInstance(context);
        String ifsIndex = "0";
        ifsIndex = (String)prefs.get(context.getString(R.string.ifs_index_for_pick_a_seat));
        return ifsIndex;
    }

    public static boolean checkingFingerprint_isHardwareDetected(Activity a) {

        boolean isHardwareDetected = false;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintManager fingerprintManager = a.getSystemService(FingerprintManager.class);
            if (ActivityCompat.checkSelfPermission(a, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return false;
            }
            isHardwareDetected = fingerprintManager.isHardwareDetected();
        }

        return isHardwareDetected;
    }

    public static boolean checkingFingerprint_isEnabled(Activity a) {

        boolean hasEnrolledFingerprint = false;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintManager fingerprintManager = a.getSystemService(FingerprintManager.class);
            if (ActivityCompat.checkSelfPermission(a, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }
            hasEnrolledFingerprint = fingerprintManager.hasEnrolledFingerprints();
        }

        return hasEnrolledFingerprint;
    }
}



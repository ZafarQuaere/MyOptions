package com.optiontown.app.view.fragment.fpo.flightpass;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.adapter.BenefitsImageRecyclerViewAdapter;
import com.optiontown.app.adapter.FlightPassRecyclerViewAdapter;
import com.optiontown.app.interfaces.PassengerGroupDropdownInterface;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.review.InnerOuterIndexData;
import com.optiontown.app.model.selectproduct.FlightPassDealData;
import com.optiontown.app.model.selectproduct.HomeSliderImage;
import com.optiontown.app.model.selectproduct.PassArray;
import com.optiontown.app.model.selectproduct.PassBanner;
import com.optiontown.app.model.session.Benifit;
import com.optiontown.app.model.session.SessionIdData;
import com.optiontown.app.model.session.Testimonial;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.customview.OTTextViewHtml;
import com.optiontown.app.view.fragment.BaseFragment;
import com.optiontown.app.view.fragment.faq.FAQFragment;
import com.optiontown.app.view.fragment.fpo.redeem.SelectBookFlightFragment;
import com.optiontown.app.view.fragment.fpo.review.ReviewFragment;
import com.optiontown.app.view.fragment.learnmore.LearnMoreFragment;
import com.optiontown.app.view.fragment.testmonial.TestimonialFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * used to show Flight Pass UI
 *
 * @author amit
 */
public class FlightPassFragment extends BaseFragment {
    private View view;
    private Handler handlerBanner = new Handler(Looper.getMainLooper());
    private boolean flagExpandedLytFlightPassBenefits = false;
    private FlightPassDealData flightPassDealData;
    private AppSharedPrefs instanceSharedPrefs;
    private OTTextView txtBuyFlightPass;
    private OTTextView txtRedeemBook;
    private LinearLayout lytHowPurchase;
    private LinearLayout lytHowBook;
    private OTTextView txt_hyperlink_webview_optiontown, txt_secondline_howpurchase, txt_howpurchse_titlepara2;
    private ScrollView scrollview_main_home;
    private OTTextView txt_how_book;
    private LinearLayout lytLearnAboutFlightPass;
    private SessionIdData sessiondata;
    private ImageLoader imageLoader;
    private ViewFlipper testimonial_viewflip;
    private int countTestimonialReviews;
    private LinearLayout lytLegBenefits;

    private RecyclerView.Adapter adapter;
    private GridLayoutManager gridLayoutManager;
    private ImageView img_buy;
    private OTTextView txtFlightHeading;
    private LinearLayout lytParent;
    private RelativeLayout lytRedeemBook;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_flight_pass, container, false);

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        Utils.updateBottomBarForFeatures(view, this.getClass().getName());


        instanceSharedPrefs = AppSharedPrefs.getInstance(this.getActivity());
        imageLoader = AppController.getInstance().getImageLoader();
        lytParent = (LinearLayout) view.findViewById(R.id.lytParent);
        lytParent.setVisibility(View.GONE);

        try {
            sessiondata = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(getActivity())), SessionIdData.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /**
         * check product id "if already called respective session : (call only banner) else call : (session and banner)"
         */
        if(sessiondata != null)
        {
            String sessionId = String.valueOf(Utils.getCurrentProductId(getActivity()));

            if(!sessiondata.getTgProductId().equals(sessionId))
            {
                callSessionApi();
            }else {

                callHomePassBannerAPI();
            }
        }


        // callHomePassBannerAPI();
        // getUIReference();
        // makeChangesForTestimonial();

        return view;
    }


    private void callSessionApi() {

        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SESSION_ID);


        JSONObject requestObject = new JSONObject();
        try {
            //requestObject.put("tgProductId", Integer.toString(getResources().getInteger(R.integer.value_tgProductId_utp)));
            requestObject.put("tgProductId", Utils.getCurrentProductId(getActivity()) + "");
            requestObject.put("CountryId", Utils.getUserSelectedCountryId(getActivity()) + "");
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(getActivity()) + "");

        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        if (loader.CheckLoaderStatus()) {
            loader.show();
        } else {
            loader.hide();
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
                        //save
                        Utils.setSessionData(getActivity(), response.toString());
                        //clear if instance exists
                       /* if (Utils.getTopFragmentInBackStack(getActivity()).equals(new FlightPassFragment().getClass().getName())) {
                            Utils.clearRecentBackStack(getActivity());
                        }*/

                        //move
                        callHomePassBannerAPI();
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


    public void localiseUI() {

        InternationalizeData localization;
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

            localizeUI(localization);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }

    }

    private void localizeUI(InternationalizeData localization) {

        if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_fpo))) {
            txtBuyFlightPass.setText(localization.getLABLUKBuyFlightPassHomeLabel());
            txtRedeemBook.setText(localization.getLABL_RedeemPass_Label());
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLFlightPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLUKBuyFlightPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(localization.getLABL_RedeemPass_Label());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

        } else if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_utp))) {
            //txtFlightHeading.setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitleSubHeading());
            img_buy.setImageResource(R.drawable.upgrade);
            txtBuyFlightPass.setText(localization.getLABLBuyUpgradePassLabel());
            txtRedeemBook.setText(localization.getLABL_RedeemPass_Label());
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLUpgradePassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLBuyUpgradePassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

            ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtLearnMoreTitle)).setVisibility(View.VISIBLE);
            ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getHeadingLearnAboutFlightPass());


        } else if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_esp))) {
            //txtFlightHeading.setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitleSubHeading());
            img_buy.setImageResource(R.drawable.empty);
            txtBuyFlightPass.setText(localization.getLABLBuyEmptySeatPassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLEmptySeatPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLBuyEmptySeatPassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

            ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtLearnMoreTitle)).setVisibility(View.GONE);
            ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitleHeading());


        } else if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_psp))) {
            //txtFlightHeading.setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitleSubHeading());
            img_buy.setImageResource(R.drawable.preferred);
            txtBuyFlightPass.setText(localization.getLABLBuyPreferredSeatPassLabel());
            txtRedeemBook.setText(localization.getLABL_RedeemPass_Label());
            ((OTTextView) view.findViewById(R.id.txt_bottom_passhome)).setText(localization.getLABLPreferredSeatPassHomeLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_buyflight)).setText(localization.getLABLBuyPreferredSeatPassLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_redeem_book)).setText(flightPassDealData.getRedeemBookLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_learnmore)).setText(localization.getLABLNavLearnMoreLabel());
            ((OTTextView) view.findViewById(R.id.txt_bottom_faq)).setText(localization.getLABLHeaderFAQLabel());

            ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtLearnMoreTitle)).setVisibility(View.GONE);
            ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitleHeading());

        }

    }

    private void getUIReference() {

        lytRedeemBook = (RelativeLayout) view.findViewById(R.id.lytRedeemBook);
        txtBuyFlightPass = (OTTextView) view.findViewById(R.id.txtBuyFlightPass);
        txtRedeemBook = (OTTextView) view.findViewById(R.id.txtRedeemBook);

        img_buy = (ImageView) view.findViewById(R.id.img_buy);
        txtFlightHeading = (OTTextView) view.findViewById(R.id.txtFlightHeading);

        //-- Flight Pass Benefits
        final LinearLayout lytFlightPassBenefits = (LinearLayout) view.findViewById(R.id.lytFlightPassBenefits);

        lytLegBenefits = (LinearLayout) view.findViewById(R.id.lytLegBenefits);

        if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_fpo))) {
            lytLegBenefits.setVisibility(View.GONE);
            lytFlightPassBenefits.setVisibility(View.VISIBLE);
            flighPassBenefits(lytFlightPassBenefits);

        } else {
            lytLegBenefits.setVisibility(View.VISIBLE);
            lytFlightPassBenefits.setVisibility(View.GONE);

            legProductBenefits(lytLegBenefits);

        }


        //--Learn about Flight Pass
        lytLearnAboutFlightPass = (LinearLayout) view.findViewById(R.id.lytLearnAboutFlightPass);

        final NetworkImageView imgLearnMore = (NetworkImageView) lytLearnAboutFlightPass.findViewById(R.id.imgLearnMore);
        imgLearnMore.setImageUrl(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getImage() + "", imageLoader);
        imgLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-5QkZh4nHkc&list=UUSULxgi1JZanNRJWMbAq4SA"));
                    startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    Utils.ERROR("Error: " + "Error WebView");
                    e.printStackTrace();
                }
            }
        });

        ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getHeadingLearnAboutFlightPass());
        ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtLearnMoreTitle)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitleHeading());
        ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtLearnMoreDescription)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitlePara1());
        ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtFlightUpto50less)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitlePara2());

        final LinearLayout lytBottomToHide = (LinearLayout) lytLearnAboutFlightPass.findViewById(R.id.lytBottomToHide);
        lytBottomToHide.setVisibility(View.GONE);
        final LinearLayout parent = Utils.layLoop(getActivity(), lytBottomToHide, sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getInnerList());
        final ImageView imgExpandLearnAbout = (ImageView) lytLearnAboutFlightPass.findViewById(R.id.imgExpand);
        imgExpandLearnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgExpandLearnAbout.setImageResource(lytBottomToHide.getVisibility() == View.GONE ? R.drawable.minus_icon : R.drawable.plus_icon);
                lytBottomToHide.setVisibility(lytBottomToHide.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                txtFlightHeading.setVisibility(txtFlightHeading.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);

                if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_fpo))) {
                    txtFlightHeading.setVisibility(View.GONE);
                }

            }
        });
        final OTTextView txtReadMore = (OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtReadMore);
        txtReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new LearnMoreFragment(), null, 0);
            }
        });

        //--How Can I Purchase Flight Pass?
        lytHowPurchase = (LinearLayout) view.findViewById(R.id.lytHowPurchase);

        ((OTTextView) lytHowPurchase.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getTitleHeading());
        final NetworkImageView img_howpurchase = (NetworkImageView) lytHowPurchase.findViewById(R.id.img_howpurchase);
        img_howpurchase.setImageUrl(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getImage() + "", imageLoader);

        final LinearLayout lytBottomToHideHowPurchase = (LinearLayout) lytHowPurchase.findViewById(R.id.lytBottomToHide);
        lytBottomToHideHowPurchase.setVisibility(View.GONE);
        final LinearLayout parenthowpurchase = Utils.layLoopForlytHowPurchase(getActivity(), lytBottomToHideHowPurchase, sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getInnerList());
        final ImageView imgExpandHowPurchase = (ImageView) lytHowPurchase.findViewById(R.id.imgExpand);
        imgExpandHowPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgExpandHowPurchase.setImageResource(lytBottomToHideHowPurchase.getVisibility() == View.GONE ? R.drawable.minus_icon : R.drawable.plus_icon);
                lytBottomToHideHowPurchase.setVisibility(lytBottomToHideHowPurchase.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });


        //--How can I book flights with my Flight Pass?

        lytHowBook = (LinearLayout) view.findViewById(R.id.lytHowBook);

        final NetworkImageView img_how_can_i_book_flight = (NetworkImageView) lytHowBook.findViewById(R.id.img_how_can_i_book_flight);
        img_how_can_i_book_flight.setImageUrl(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getImage() + "", imageLoader);
        final LinearLayout lytBottomToHidelytHowBook = (LinearLayout) lytHowBook.findViewById(R.id.lytBottomToHide);
        lytBottomToHide.setVisibility(View.GONE);
        final LinearLayout parentlytHowBook = Utils.layLoopForlytHowBook(getActivity(), lytBottomToHidelytHowBook, sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getInnerList());

        final LinearLayout lytBottomToHideHowBook = (LinearLayout) lytHowBook.findViewById(R.id.lytBottomToHide);
        lytBottomToHideHowBook.setVisibility(View.GONE);
        ((OTTextView) lytHowBook.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getTitleHeading());
        ((OTTextView) lytHowBook.findViewById(R.id.txt_choos_flight_pass_you_want)).setText("• " + sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getInnerList().get(1));

        final ImageView imgExpandHowBook = (ImageView) lytHowBook.findViewById(R.id.imgExpand);
        imgExpandHowBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgExpandHowBook.setImageResource(lytBottomToHideHowBook.getVisibility() == View.GONE ? R.drawable.minus_icon : R.drawable.plus_icon);
                lytBottomToHideHowBook.setVisibility(lytBottomToHideHowBook.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        txt_how_book = (OTTextView) lytHowBook.findViewById(R.id.txt_webview_ot_how_book);
        txt_how_book.setText("• " + sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getInnerList().get(0));
        txt_how_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.optiontown.com"));
                    startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    Utils.ERROR("Error: " + "Error WebView");
                    e.printStackTrace();
                }
            }
        });


        //--Frequently Asked Questions (FAQs)
        final LinearLayout lytFAQ = (LinearLayout) view.findViewById(R.id.lytFAQ);
        final LinearLayout lytBottomToHideFAQ = (LinearLayout) lytFAQ.findViewById(R.id.lytBottomToHide);
        ((OTTextView) lytFAQ.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getFrequentlyAskedQuestions().getFAQs().get(0).getTitleHeading());
        final NetworkImageView img_faq = (NetworkImageView) view.findViewById(R.id.img_faq);
        img_faq.setImageUrl(sessiondata.getLearnAboutFlightPass().getFrequentlyAskedQuestions().getFAQs().get(0).getImage() + "", imageLoader);
        final LinearLayout lay_faq = (LinearLayout) view.findViewById(R.id.lay_faq);
        final LinearLayout parentlytFAQ = Utils.layLoopForFAQ(getActivity(), lay_faq, sessiondata.getLearnAboutFlightPass().getFrequentlyAskedQuestions().getFAQs().get(0).getInnerList());
        final ImageView imgExpandFAQ = (ImageView) lytFAQ.findViewById(R.id.imgExpand);
        imgExpandFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to next UI
                Utils.moveToFragment(getActivity(), new FAQFragment(), null, 0);
            }
        });


        //--Testimonials
        final LinearLayout lytTestimonials = (LinearLayout) view.findViewById(R.id.lytTestimonials);
        final LinearLayout lytBottomToHideTestimonials = (LinearLayout) lytTestimonials.findViewById(R.id.lytBottomToHide);
        ((OTTextView) lytTestimonials.findViewById(R.id.txtTitle)).setText(sessiondata.getTestimonialLabel());
        NetworkImageView img_testimonial = (NetworkImageView) view.findViewById(R.id.img_testimonial);
        img_testimonial.setImageUrl(sessiondata.getTestimonialImage() + "", imageLoader);

        final ImageView imgExpandTestimonials = (ImageView) lytTestimonials.findViewById(R.id.imgExpand);
        imgExpandTestimonials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to next UI
                Utils.moveToFragment(getActivity(), new TestimonialFragment(), null, 0);
            }
        });
        testimonial_viewflip = (ViewFlipper) view.findViewById(R.id.testimonial_viewflip);

        /*
        * showing only four static content from assets of testimonial
        * */

        try {
            ArrayList<Testimonial> list = getListToShow(sessiondata.getTestimonial());
           /* ArrayList<Testimonial> list = new ArrayList<>();

            Testimonial t1 = new Testimonial();
            t1.setCountry("India");
            t1.setLastName("Dhingra");
            t1.setText("It was like a dream,'flat bed' and service ! For me it is YES i2..My greetings and best wishes to Optiontown for a wonderful and unique scheme ..\t");

            Testimonial t2 = new Testimonial();
            t2.setCountry("Australia");
            t2.setLastName("Aspinwall");
            t2.setText("I am so excited I cant believe it , I am gonna put it on Facebook Tonight.");

            Testimonial t3 = new Testimonial();
            t3.setCountry("Vietnam");
            t3.setLastName("Tran");
            t3.setText("It's great! My wife and daughter are really happy with UTO services. It's our first time upgraded to Business Class with Vietnam Airlines! Thanks Option Town.");

            list.add(t1);
            list.add(t2);
            list.add(t3);*/
            if (list.size() > 0) {
                while (countTestimonialReviews < 4) {
                    final ViewFlipper parent_lyttestimonial = layLoopForTestimonial(getActivity(), testimonial_viewflip, list);
                    parent_lyttestimonial.setFlipInterval(5000);
                    parent_lyttestimonial.setInAnimation(getActivity(), R.anim.slide_in_up);
                    parent_lyttestimonial.setOutAnimation(getActivity(), R.anim.slide_out_down);
                    parent_lyttestimonial.startFlipping();
                }
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }


        ((RelativeLayout) view.findViewById(R.id.lytBuyFlightPass))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_fpo))) {
                            if (flightPassDealData != null) {
                                //move to Flight Pass Search fragment
                                Utils.moveToFragment(getActivity(), new FlightPassSearchFragment(), null, 0);

                                Utils.setSharedPrefsForSearch(getActivity(), flightPassDealData);
                            }
                        } else /*if (Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_utp)))*/ {
                            if (flightPassDealData != null) {

                                Utils.moveToFragment(getActivity(), new FlightPassSearchFragment(), flightPassDealData, 0);

                                //Utils.setSharedPrefsForSearch(getActivity(), flightPassDealData);
                            }
                        }
                    }
                });
        if (getResources().getBoolean(R.bool.redeem_enable)) {

            lytRedeemBook.setVisibility(View.VISIBLE);
        } else {
            lytRedeemBook.setVisibility(View.GONE);
        }

        lytRedeemBook
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.moveToFragment(getActivity(), new SelectBookFlightFragment(), null, 0);

                    }
                });

        updateUI();

    }

    private void legProductBenefits(LinearLayout lytLegBenefits) {


        final RelativeLayout lytLegProductBenefits = (RelativeLayout) view.findViewById(R.id.lytLegProductBenefits);
        final ImageView imgExpandBenefits = (ImageView) lytLegProductBenefits.findViewById(R.id.imgExpand);
        final RecyclerView recyclerViewBenefit = (RecyclerView) view.findViewById(R.id.recyclerViewBenefit);
        ((OTTextView) lytLegBenefits.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getHeadingFlightPassBenefits());

        final ArrayList<Benifit> benfitList = sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit();
        gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerViewBenefit.setLayoutManager(gridLayoutManager);

        recyclerViewBenefit.setHasFixedSize(true);
        Utils.DEBUG("benfit list :" + benfitList.size());

        final ArrayList<Benifit> tempList = new ArrayList<>();

        for (int i = 0; i < benfitList.size(); i++) {
            Benifit mb = new Benifit();
            mb.setTitleSubHeading(benfitList.get(i).getTitleSubHeading());
            mb.setImage(benfitList.get(i).getImage());
            tempList.add(mb);

            if (i == 3) {
                break;
            }
        }
        adapter = new BenefitsImageRecyclerViewAdapter(getActivity(), tempList);
        recyclerViewBenefit.setAdapter(adapter);

        imgExpandBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Benifit> tempBenefits = new ArrayList<Benifit>();
                if (flagExpandedLytFlightPassBenefits) {
                    for (int i = 0; i < benfitList.size(); i++) {
                        Benifit mBenefit = new Benifit();
                        mBenefit.setTitleSubHeading(benfitList.get(i).getTitleSubHeading());
                        mBenefit.setImage(benfitList.get(i).getImage());

                        tempBenefits.add(mBenefit);
                        if (i == 3)
                            break;
                    }
                } else {
                    for (int i = 0; i < benfitList.size(); i++) {
                        Benifit mBenefit = new Benifit();
                        mBenefit.setTitleSubHeading(benfitList.get(i).getTitleSubHeading());
                        mBenefit.setImage(benfitList.get(i).getImage());

                        tempBenefits.add(mBenefit);
                    }
                }

                adapter = new BenefitsImageRecyclerViewAdapter(getActivity(), tempBenefits);
                recyclerViewBenefit.setAdapter(adapter);

                imgExpandBenefits.setImageResource(!flagExpandedLytFlightPassBenefits ? R.drawable.minus_icon : R.drawable.plus_icon);
                flagExpandedLytFlightPassBenefits = !flagExpandedLytFlightPassBenefits;
            }

        });

    }

    private void flighPassBenefits(final LinearLayout lytFlightPassBenefits) {

        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_subheading_booklast)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(4).getTitleSubHeading());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_content_booklast)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(4).getTitlePara1());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_subheading_morevacation)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(5).getTitleSubHeading());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_content_morevacation)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(5).getTitlePara1());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_subheading_payonly)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(6).getTitleSubHeading());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_content_payonly)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(6).getTitlePara1());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_subheading_mostcostefficient)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(7).getTitleSubHeading());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_content_mostcosteffcient)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(7).getTitlePara1());

        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getHeadingFlightPassBenefits());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_subheading_flymore)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(0).getTitleSubHeading());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_content_flymore)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(0).getTitlePara1());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_subheading_poolshare)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(1).getTitleSubHeading());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_content_poolshare)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(1).getTitlePara1());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_subheading_personalised)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(2).getTitleSubHeading());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_content_personalised)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(2).getTitlePara1());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_subheading_peacemind)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(3).getTitleSubHeading());
        ((OTTextView) lytFlightPassBenefits.findViewById(R.id.txt_content_peacemind)).setText(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(3).getTitlePara1());
        ((NetworkImageView) lytFlightPassBenefits.findViewById(R.id.img_flymore)).setImageUrl(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(0).getImage(), imageLoader);
        ((NetworkImageView) lytFlightPassBenefits.findViewById(R.id.img_pool)).setImageUrl(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(1).getImage(), imageLoader);
        ((NetworkImageView) lytFlightPassBenefits.findViewById(R.id.img_personalised)).setImageUrl(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(2).getImage(), imageLoader);
        ((NetworkImageView) lytFlightPassBenefits.findViewById(R.id.img_peace)).setImageUrl(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(3).getImage(), imageLoader);


        ((NetworkImageView) lytFlightPassBenefits.findViewById(R.id.img_book)).setImageUrl(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(4).getImage(), imageLoader);
        ((NetworkImageView) lytFlightPassBenefits.findViewById(R.id.img_morevac)).setImageUrl(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(5).getImage(), imageLoader);
        ((NetworkImageView) lytFlightPassBenefits.findViewById(R.id.img_payonly)).setImageUrl(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(6).getImage(), imageLoader);
        ((NetworkImageView) lytFlightPassBenefits.findViewById(R.id.img_mostcost)).setImageUrl(sessiondata.getLearnAboutFlightPass().getFlightPassBenifits().getBenifit().get(7).getImage(), imageLoader);

        final ImageView imgExpandBenefits = (ImageView) lytFlightPassBenefits.findViewById(R.id.imgExpand);
        imgExpandBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.DEBUG("Clicked." + lytFlightPassBenefits.getChildCount());
                for (int i = 1; i < lytFlightPassBenefits.getChildCount(); i++)//title is at 0th position so started from 1
                {
                    if (flagExpandedLytFlightPassBenefits) {
                        if (i <= 4) {
                            lytFlightPassBenefits.getChildAt(i).setVisibility(View.VISIBLE);
                        } else {
                            lytFlightPassBenefits.getChildAt(i).setVisibility(View.GONE);
                        }
                    } else {
                        lytFlightPassBenefits.getChildAt(i).setVisibility(View.VISIBLE);
                    }
                }

                imgExpandBenefits.setImageResource(!flagExpandedLytFlightPassBenefits ? R.drawable.minus_icon : R.drawable.plus_icon);
                flagExpandedLytFlightPassBenefits = !flagExpandedLytFlightPassBenefits;

            }
        });

    }

    private ArrayList<Testimonial> getListToShow(ArrayList<Testimonial> testimonial) {

        ArrayList<Testimonial> temp = new ArrayList<Testimonial>();

        for (int index = 0; index < testimonial.size(); index++) {
            if (!isHTML(testimonial.get(index).getText())) {
                temp.add(testimonial.get(index));

                if (temp.size() == 3) {
                    return temp;
                }
            }
        }


        return testimonial;
    }

    public ViewFlipper layLoopForTestimonial(Context context, ViewFlipper parent, ArrayList<Testimonial> list) {
        LayoutInflater layoutInfralte = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //System.out.println("Size >>>>" + list.size());
        View view = layoutInfralte.inflate(R.layout.child_testimonial, null);
        OTTextViewHtml txt_content_Testimonial = (OTTextViewHtml) view.findViewById(R.id.txt_content_Testimonial);
        OTTextView txtTestimonialName = (OTTextView) view.findViewById(R.id.txtTestimonialName);
        OTTextView txtTestimonialCountry = (OTTextView) view.findViewById(R.id.txtTestimonialCountry);

        while (isHTML(list.get(countTestimonialReviews).toString())) {
            countTestimonialReviews++;
        }

        txt_content_Testimonial.setHtml(list.get(countTestimonialReviews).getText());
        txtTestimonialName.setText(list.get(countTestimonialReviews).getLastName());
        txtTestimonialCountry.setText(list.get(countTestimonialReviews).getCountry());

        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        parent.addView(view);

        countTestimonialReviews = (countTestimonialReviews + 1);

        return parent;
    }

    private boolean isHTML(String s) {

        return s.contains("<table") ? true : false;
    }

    private void makeChangesForTestimonial() {

        try {
            String baseURL = "";
            String session = Utils.getSessionData(getActivity());
            if (session == null) {
                return;
            }
            JSONObject json = new JSONObject(session);
            SessionIdData data = (ParseManager.getInstance().fromJSON(json, SessionIdData.class));
            //Utils.DEBUG("Testimonial json : " + json.getString("Testimonial"));
            ArrayList<Testimonial> testimonialList = data.getTestimonial();
            /*for (int index = 0; index < testimonialList.size(); index++) {

            }*/

        } catch (JSONException e) {
            Utils.DEBUG("Error while parsing json : " + e.toString());
        }

    }


    /**
     * used to schedule/show top banner,
     *
     * @param data
     */
    private void scheduleTopBanner(final ArrayList<HomeSliderImage> data) {
        Utils.DEBUG("scheduleTopBanner() called : " + data);
        new Thread() {
            @Override
            public void run() {
                int pauseTime = 8000;
                final int rotationRound = 3;

                final ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                final RelativeLayout lytTopBanner = (RelativeLayout) view.findViewById(R.id.lytTopBanner);
                final OTTextView txtTopBanner = (OTTextView) view.findViewById(R.id.txtTopBanner);
                final BitmapDrawable imgDrawable[] = new BitmapDrawable[data.size()];
                for (int index = 0; index < data.size(); index++) {
                    //to check fragment is attached in the activity, check this fragment at top
                    if (!Utils.getTopFragmentInBackStack(getActivity()).equals(new FlightPassFragment().getClass().getName())) {
                        return;
                    }
                    try {
                        sleep(pauseTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int finalIndex = index;
                    Utils.DEBUG("image url : " + data.get(finalIndex).getImage());
                    handlerBanner.post(new Runnable() {
                        @Override
                        public void run() {
                            imageLoader.get(data.get(finalIndex).getImage(), new ImageLoader.ImageListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Utils.ERROR("Image Load Error: " + error.getMessage());
                                }

                                @Override
                                public void onResponse(final ImageLoader.ImageContainer response, boolean arg1) {
                                    if (Utils.getTopFragmentInBackStack(getActivity()).equals(new FlightPassFragment().getClass().getName())) {
                                        if (response.getBitmap() != null) {
                                            Utils.setBackground(lytTopBanner, imgDrawable[finalIndex] = new BitmapDrawable(getResources(), response.getBitmap()));
                                            txtTopBanner.setText(getTopBannerMessage(data.get(finalIndex).getMessage()));
                                        }
                                    }
                                }
                            });
                        }
                    });
                }

                for (int index = 0; index < imgDrawable.length * rotationRound; index++) {
                    try {
                        sleep(pauseTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (Utils.getTopFragmentInBackStack(getActivity()).equals(new FlightPassFragment().getClass().getName())) {
                        final int finalIndex = index;
                        handlerBanner.post(new Runnable() {
                            @Override
                            public void run() {
                                Utils.setBackground(lytTopBanner, imgDrawable[finalIndex % imgDrawable.length]);
                                txtTopBanner.setText(getTopBannerMessage(data.get(finalIndex % imgDrawable.length).getMessage()));
                            }
                        });
                    } else {
                        return;
                    }
                }
            }
        }.start();

    }

    private String getTopBannerMessage(String message) {
        if (message == null)
            return "";

        if (message.equals("null")) {
            return "";
        }
        return message;
    }


    /**
     * used to set layout when data received from server, multiple recycler view
     */
    private void setRecyclerViewForDeals(final FlightPassDealData data) {
        if (data == null) {
            return;
        }

        if (data.getPassBanner() == null) {
            return;
        }

        LinearLayout lytDeals = (LinearLayout) view.findViewById(R.id.lytDeals);
        if (Utils.getCurrentProductId(getActivity()) != (getResources().getInteger(R.integer.value_tgProductId_fpo))) {
            lytDeals.setVisibility(View.GONE);
        } else {

            for (int index = 0; index < data.getPassBanner().size(); index++) {
                lytDeals.setVisibility(View.VISIBLE);
                if (getActivity() == null) {
                    return;
                }
                View lytEachDealView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_deals_each, null, false);

                RecyclerView recyclerView = (RecyclerView) lytEachDealView.findViewById(R.id.recyclerViewDeals);
                recyclerView.setHasFixedSize(true);

                OTTextView txtFlightName = (OTTextView) lytEachDealView.findViewById(R.id.txtFlightName);
                txtFlightName.setText(data.getPassBanner().get(index).getHeading());
                OTTextView txtDealName = (OTTextView) lytEachDealView.findViewById(R.id.txtDealName);
                txtDealName.setText(data.getPassBanner().get(index).getHotDeals());

                final LinearLayout lytCabin = (LinearLayout) lytEachDealView.findViewById(R.id.lytCabin);
                final ScrollView svCabin = (ScrollView) lytEachDealView.findViewById(R.id.svCabin);
                final OTTextView txtCabin = (OTTextView) lytEachDealView.findViewById(R.id.txtCabin);
                final String[] cabinArray = getCabinArray(flightPassDealData.getPassBanner().get(index));
                txtCabin.setText(cabinArray[0]);
                txtCabin.setVisibility(cabinArray.length > 1 ? View.VISIBLE : View.GONE);
                View view = (View) recyclerView.getRootView();
                lytEachDealView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        svCabin.setVisibility(View.GONE);
                    }
                });


                GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(gridLayoutManager);

                final FlightPassRecyclerViewAdapter adapter = new FlightPassRecyclerViewAdapter(getActivity(), getFilteredBannerData(txtCabin.getText().toString(), data.getPassBanner().get(index).getPassArray()), new FlightPassRecyclerViewAdapter.IRecyclerViewHolderClicks() {
                    @Override
                    public void onClickRecyclerItem(View v, int position, PassArray passArray) {
                        InnerOuterIndexData data = new InnerOuterIndexData();
                        data.setInnerIndex(passArray.getInnerIndex());
                        data.setOuterIndex(passArray.getOuterIndex());
                        svCabin.setVisibility(View.GONE);
                        Utils.moveToFragment(getActivity(), new ReviewFragment(), data, 0);

                    }
                });
                recyclerView.setAdapter(adapter);


                final int finalIndex = index;
                txtCabin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Utils.createDropdownView(cabinArray, lytCabin, txtCabin, new LinearLayout[]{}, new PassengerGroupDropdownInterface() {
                            @Override
                            public void onDropdownClick(String strSelected) {
                                adapter.notifyDataSetChanged(getFilteredBannerData(strSelected, data.getPassBanner().get(finalIndex).getPassArray()));
                            }
                        });
                    }
                });
                lytDeals.addView(lytEachDealView);
            }
        }
    }

    private ArrayList<PassArray> getFilteredBannerData(String s, ArrayList<PassArray> passArray) {

        ArrayList<PassArray> list = new ArrayList<>();
        if(s == null || passArray == null)
        {
            return  list;
        }


        for (int index = 0; index < passArray.size(); index++)
        {
            if(s.equals(passArray.get(index).getCabinName()))
            {
                list.add(passArray.get(index));
            }
        }

        return list;
    }

    /**
     * used to get pass cabin array
     * @param passBanner
     * @return
     */
    private String[] getCabinArray(PassBanner passBanner)
    {
        Set<String> list = new HashSet<>();

        for (int index = 0; index < passBanner.getPassArray().size(); index++)
        {
            list.add(passBanner.getPassArray().get(index).getCabinName());
        }

        return list.toArray(new String[list.size()]);
    }


    private void callHomePassBannerAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_HOME_PASS_BANNER);


        JSONObject requestObject = new JSONObject();
        try {
            //requestObject.put("TgProductId", getResources().getInteger(R.integer.value_tgProductId_fpo) + "");
            requestObject.put("TgProductId", Utils.getCurrentProductId(getActivity()) + "");
            requestObject.put("CountryId", Utils.getUserSelectedCountryId(getActivity()) + "");
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(getActivity()) + "");
            requestObject.put("AirlineId", "0");//default always at FlightPassFragment
            requestObject.put("tgpLevel", getResources().getInteger(R.integer.value_tgpLevel) + "");
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }
        final AppDialogLoader loader = AppDialogLoader.getLoader(FlightPassFragment.this.getActivity());
        if (loader.CheckLoaderStatus()) {
            loader.show();
        } else {
            loader.hide();
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
                        flightPassDealData = ParseManager.getInstance().fromJSON(response, FlightPassDealData.class);
                        //Utils.DEBUG("pass at 0th pos : " + (String)ParseManager.getInstance().toJSON(flightPassDealData.getPassBanner().get(0).getPassArray().get(0)));

                        Utils.setHomePassBannerData(getActivity(), response.toString());

                        try {
                            sessiondata = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(getActivity())), SessionIdData.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (sessiondata != null) {

                                    getUIReference();
                                    makeChangesForTestimonial();
                                    loader.dismiss();
                                }
                            }
                        }, 300);
                        //Utils.showToast(getActivity(), "total views : " + data.getPassBanner().size() + ", currency : " + data.getPassBanner().get(0).getPassArray().get(0).getCurrencyCode());
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

    private void updateUI() {


        setRecyclerViewForDeals(flightPassDealData);

        scheduleTopBanner(flightPassDealData.getHomeSliderImage());



        txt_hyperlink_webview_optiontown = (OTTextView) lytHowPurchase.findViewById(R.id.txt_hyperlink_webview_optiontown);
        txt_hyperlink_webview_optiontown.setText("• "+sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getInnerList().get(0));
        txt_secondline_howpurchase = (OTTextView) lytHowPurchase.findViewById(R.id.txt_secondline_howpurchase);
        txt_secondline_howpurchase.setText("• " + sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getInnerList().get(1));
        txt_howpurchse_titlepara2 = (OTTextView) lytHowPurchase.findViewById(R.id.txt_howpurchse_titlepara2);
        txt_howpurchse_titlepara2.setText(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getTitlePara2());
        txt_hyperlink_webview_optiontown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.optiontown.com"));
                    startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    Utils.ERROR("Error: " + "Error WebView");
                    e.printStackTrace();
                }
            }
        });

        localiseUI();

        lytParent.setVisibility(View.VISIBLE);

    }
}

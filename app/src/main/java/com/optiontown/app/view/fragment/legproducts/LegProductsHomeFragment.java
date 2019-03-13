package com.optiontown.app.view.fragment.legproducts;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.adapter.BegifitsRecyclerViewAdapter;
import com.optiontown.app.adapter.ProductBeneifitsRecyclerViewAdapter;
import com.optiontown.app.interfaces.LegProductHome;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.model.legproducthomepage.HomeSliderImagesText;

import com.optiontown.app.model.legproducthomepage.FAQ;
import com.optiontown.app.model.legproducthomepage.Home;
import com.optiontown.app.model.legproducthomepage.HomeBannerObject;
import com.optiontown.app.model.legproducthomepage.HomePageData;
import com.optiontown.app.model.legproducthomepage.HomeTestimonial;
import com.optiontown.app.model.legproducthomepage.LABLHomeBenifitLabel;
import com.optiontown.app.model.legproducthomepage.LABLHomeFAQsLabel;
import com.optiontown.app.model.legproducthomepage.LABLHomeLearnLabel;
import com.optiontown.app.model.legproducthomepage.LABLHomeStepsLabel;
import com.optiontown.app.model.legproducthomepage.LABLMaximizeYourChanceLabel;
import com.optiontown.app.model.legproducthomepage.LearnAbout;
import com.optiontown.app.model.selectproduct.HomeSliderImage;
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
import com.optiontown.app.view.fragment.faq.FAQFragment;
import com.optiontown.app.view.fragment.testmonial.TestimonialFragment;

import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * home UI
 *
 * @author ravi
 */
public class LegProductsHomeFragment extends BaseFragment implements View.OnClickListener {
    private Handler handlerBanner = new Handler(Looper.getMainLooper());

    private GridLayoutManager gridLayoutManager;
    private RecyclerView.Adapter adapter;

    private OTTextView txtTopBanner, txtTitle, txtBuyFlightPass, txtCheckStatus, txtLpTopBanner, txtLearnAboutReadMore;

    OTTextViewHtml txtQuestions,txtFroChances, txtSteps2Upgrade;

    String productName, productID,steps2line;
    private RelativeLayout lytBuyFlightPass, lytRedeemBook, lytLearnAbout, lytTopBanner;
    // View lytFlightPassBenefits;
    LegProductHome itrLegProductHome;
    LinearLayout lyt_steps2Upgrade, lytTestimonials, lytFAQ, lytDeals, lytSegBenifits,lytFroMaximizeChances,lytTop;
    View view;
    NetworkImageView img_buy, img_status, imgSteps2Upgrade, imgsFaq,imgFroChances;
    private RecyclerView recyclerViewBenefit,recyclerViewLearnAbout, recyclerSegBenefits;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageLoader imageLoader;
    AppDialogLoader loader;
    RelativeLayout lytTestimonialTitle, lytLPTopBanner,rlytSteps2Upgrade;
    Benefit mBenefit;
    private ViewFlipper testimonial_viewflip;
    private static int flipCount;
    private boolean flagExpandedLytFlightPassBenefits = false;
    private boolean flagExpandedLytLegLearnMore = false;
    private boolean flagExpandedLytStepToUpgrade = false;
    private boolean flagExpandedLytMaximizeChances = false;
    private List<LearnAbout> mJsonArrayLearnMore;
    private String learnBenefitLabel;


    ArrayList<Benefit> mBenefits;

    String[] stepsToUpgrade;
    private AppSharedPrefs sharedPrefs;
    private String label;
    private String img;
    private String mainTitle;
    private String froMaxImg;
    private String froMaxLabel;
    private String mainMaxTitle;
    private ImageView imgLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_legproducts_home, container, false);
        Init(view);
        sharedPrefs = AppSharedPrefs.getInstance(getActivity());
        mBenefit = (Benefit) getArguments().get(getActivity().getString(R.string.key_serializable));
        try {
            if (mBenefit.getBenefitName() != null) {
                productName = mBenefit.getBenefitName();
                productID = mBenefit.getId() + "";
                sharedPrefs.put(getString(R.string.key_selected_productId), productID);

            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageLoader = AppController.getInstance().getImageLoader();

        Utils.updateLegProductBottomBarForFeatures(getActivity(), view, this.getClass().getName());
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        callSessionIdAPI(sharedPrefs.get(getString(R.string.key_selected_productId)).toString());

        if ((sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("4"))
                || (sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("2"))
                || (sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("3"))) {
            lytDeals.setVisibility(View.GONE);
            lytSegBenifits.setVisibility(View.VISIBLE);
        } else {
            lytDeals.setVisibility(View.VISIBLE);
            lytSegBenifits.setVisibility(View.GONE);
        }

       // lytFlightPassBenefits = view.findViewById(R.id.lytFlightPassBenefits);
     //   lytFlightPassBenefits.setVisibility(View.GONE);
        loader = AppDialogLoader.getLoader(getActivity());
        //loader = AppDialogLoader.getLoader(getActivity(),R.style.ProgressDialogTheme);
        loader.show();
        lytTop.setVisibility(View.GONE);
        Utils.updateBuyStatusIcon(getActivity(),sharedPrefs.get(getString(R.string.key_selected_productId)).toString(),img_buy,img_status);
        return view;

    }

    public void Init(View vw) {

        recyclerViewBenefit = (RecyclerView) vw.findViewById(R.id.recyclerViewBenefit);
        recyclerViewLearnAbout = (RecyclerView) vw.findViewById(R.id.recyclerViewLearnAbout);
        recyclerSegBenefits = (RecyclerView) vw.findViewById(R.id.recyclerSegBenefits);
        // recyclerViewTestimonial = (RecyclerView) vw.findViewById(R.id.recyclerViewTestimonial); lytDeals, lytSegBenifits

        lytFAQ = (LinearLayout) vw.findViewById(R.id.lytFAQ);
        lytDeals = (LinearLayout) vw.findViewById(R.id.lytDeals);
        lytSegBenifits = (LinearLayout) vw.findViewById(R.id.lytSegBenifits);
        lytFroMaximizeChances = (LinearLayout) vw.findViewById(R.id.lytFroMaximizeChances);
        lytTop = (LinearLayout) vw.findViewById(R.id.lytTop);

        txtTitle = (OTTextView) vw.findViewById(R.id.txtTitle);
        txtLearnAboutReadMore = (OTTextView) vw.findViewById(R.id.txtLearnAboutReadMore);
        txtBuyFlightPass = (OTTextView) vw.findViewById(R.id.txtBuyFlightPass);
        txtCheckStatus = (OTTextView) vw.findViewById(R.id.txtCheckStatus);
        txtLpTopBanner = (OTTextView) vw.findViewById(R.id.txtLpTopBanner);

        txtSteps2Upgrade = (OTTextViewHtml) vw.findViewById(R.id.txtSteps2Upgrade);
        txtQuestions = (OTTextViewHtml) vw.findViewById(R.id.txtQuestions);
        txtFroChances = (OTTextViewHtml) vw.findViewById(R.id.txtFroChances);

        imgLoader = (ImageView) vw.findViewById(R.id.imgLoader);
        img_buy = (NetworkImageView) vw.findViewById(R.id.img_buy);
        img_status = (NetworkImageView) vw.findViewById(R.id.img_status);
        imgSteps2Upgrade = (NetworkImageView) vw.findViewById(R.id.imgSteps2Upgrade);
        imgsFaq = (NetworkImageView) vw.findViewById(R.id.imgsFaq);
        imgFroChances = (NetworkImageView) vw.findViewById(R.id.imgFroChances);




        lyt_steps2Upgrade = (LinearLayout) vw.findViewById(R.id.lyt_steps2Upgrade);
        lytTestimonials = (LinearLayout) vw.findViewById(R.id.lytTestimonials);

        lytLearnAbout = (RelativeLayout) vw.findViewById(R.id.lytLearnAbout);
        rlytSteps2Upgrade = (RelativeLayout) vw.findViewById(R.id.rlytSteps2Upgrade);

        lytLPTopBanner = (RelativeLayout) vw.findViewById(R.id.lytLPTopBanner);

        lytBuyFlightPass = (RelativeLayout) vw.findViewById(R.id.lytBuyFlightPass);
        lytBuyFlightPass.setOnClickListener(this);

        lytTopBanner = (RelativeLayout) vw.findViewById(R.id.lytTopBanner);

        lytRedeemBook = (RelativeLayout) vw.findViewById(R.id.lytRedeemBook);
        lytRedeemBook.setOnClickListener(this);

        final RelativeLayout lytLegProductBenefits = (RelativeLayout) view.findViewById(R.id.lytLegProductBenefits);
        final ImageView imgExpandBenefits = (ImageView) lytLegProductBenefits.findViewById(R.id.imgExpand);
        imgExpandBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Benefit> tempBenefits = new ArrayList<Benefit>();
                if (flagExpandedLytFlightPassBenefits) {
                    if (mBenefits != null) {
                        for (int i = 0; i < mBenefits.size(); i++) {
                            Benefit mBenefit = new Benefit();
                            mBenefit.setBenefitName(mBenefits.get(i).getBenefitName());
                            mBenefit.setImageURL(mBenefits.get(i).getImageURL());
                            mBenefit.setId(i);
                            tempBenefits.add(mBenefit);
                            if (i == 3)
                                break;
                        }
                    }
                } else {
                    if (mBenefits != null) {
                        for (int i = 0; i < mBenefits.size(); i++) {
                            Benefit mBenefit = new Benefit();
                            mBenefit.setBenefitName(mBenefits.get(i).getBenefitName());
                            mBenefit.setImageURL(mBenefits.get(i).getImageURL());
                            mBenefit.setId(i);
                            tempBenefits.add(mBenefit);
                        }
                    }
                }
                gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
                recyclerViewBenefit.setLayoutManager(gridLayoutManager);

                adapter = new ProductBeneifitsRecyclerViewAdapter(Color.parseColor("#FFD700"), getActivity(), tempBenefits, new ProductBeneifitsRecyclerViewAdapter.IRecyclerViewHolderClicks() {
                    @Override
                    public void onClickRecyclerItem(View v, int position) {

                    }
                });
                recyclerViewBenefit.setAdapter(adapter);
                recyclerViewBenefit.setNestedScrollingEnabled(false);


                imgExpandBenefits.setImageResource(!flagExpandedLytFlightPassBenefits ? R.drawable.minus_icon : R.drawable.plus_icon);
                flagExpandedLytFlightPassBenefits = !flagExpandedLytFlightPassBenefits;
            }
        });
    }

    private void LoadBenifits(List<com.optiontown.app.model.legproducthomepage.Benefit> mBenefitses, String benefitLabel) {
        recyclerViewBenefit.setHasFixedSize(false);
        try {
            mBenefits = new ArrayList<>();
            for (int i = 0; i < mBenefitses.size(); i++) {
                Benefit mBenefit = new Benefit();
                mBenefit.setBenefitName(mBenefitses.get(i).getLabel());
                mBenefit.setImageURL(mBenefitses.get(i).getImage());
                mBenefit.setId(i);
                mBenefits.add(mBenefit);
            }
            ArrayList<Benefit> tempBenefits = new ArrayList<Benefit>();
            for (int i = 0; i < mBenefits.size(); i++) {
                Benefit mBenefit = new Benefit();
                mBenefit.setBenefitName(mBenefits.get(i).getBenefitName());
                mBenefit.setImageURL(mBenefits.get(i).getImageURL());
                mBenefit.setId(i);
                tempBenefits.add(mBenefit);
                if (i == 3)
                    break;
            }

            if ((sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("4")) || (sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("2")) || (sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("3"))) {
                lytSegBenifits.setVisibility(View.VISIBLE);
                lytDeals.setVisibility(View.GONE);

                loadSegProduct(mBenefits, benefitLabel,true);



            } else {
                gridLayoutManager = new GridLayoutManager(this.getActivity(), 2, LinearLayoutManager.VERTICAL, false);
                recyclerViewBenefit.setLayoutManager(gridLayoutManager);
                int spanCount = 2; // 3 columns
                int spacing = 30; // 50px
                boolean includeEdge = true;
                recyclerViewBenefit.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
                adapter = new ProductBeneifitsRecyclerViewAdapter(Color.parseColor("#FFD700"), getActivity(), tempBenefits, new ProductBeneifitsRecyclerViewAdapter.IRecyclerViewHolderClicks() {
                    @Override
                    public void onClickRecyclerItem(View v, int position) {

                    }
                });
                recyclerViewBenefit.setAdapter(adapter);
                recyclerViewBenefit.setNestedScrollingEnabled(false);
                recyclerViewBenefit.setHasFixedSize(false);

            }


        } catch (Exception exc) {
        }
        txtTitle.setText(benefitLabel);
    }

    @Override
    public void onResume() {
        super.onResume();

        //Utils.updateActionBarForFeatures(getActivity(), productName + " Option");
    }


    public void onClickBuy() {
      Utils.moveToFragment(getActivity(), new LegProductSearchFragment(), mBenefit, 0);
     //   Utils.moveToFragment(getActivity(), new DummyFragment(), mBenefit, 0);
    }


    public void onClickStatus() {
        Utils.moveToFragment(getActivity(), new LegProductsCheckStatusFragment(), mBenefit, 0);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lytBuyFlightPass:
                onClickBuy();
                break;

            case R.id.lytRedeemBook:
                onClickStatus();
                break;

           /* case R.id.lytTestimonialTitle:
                Utils.moveToFragment(getActivity(), new TestimonialFragment(), null, 0);
                break;*/
        }
    }


    private void callSessionIdAPI(String productID) {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SESSION_ID);
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("tgProductId", productID);
            requestObject.put("CountryId", Utils.getUserSelectedCountryId(getActivity()) + "");
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(getActivity()) + "");

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


                        Utils.setLegProductSessionData(getActivity(), response.toString());
                        Home mHome = ParseManager.getInstance().fromJSON(response, Home.class);
                        HomePageData mHomePageData = mHome.getHomePageData();
                        loader.dismiss();
                        lytTop.setVisibility(View.VISIBLE);
                        if (mHomePageData != null) {

                            LABLHomeBenifitLabel lablHomeBenifitLabel = mHomePageData.getLABLHomeBenifitLabel();
                            List<com.optiontown.app.model.legproducthomepage.Benefit> mBenefitses = lablHomeBenifitLabel.getBenefits();
                            String benefitLabel = lablHomeBenifitLabel.getMainTitle();
                            LoadBenifits(mBenefitses, benefitLabel);
                            LABLHomeLearnLabel mLABLHomeLearnLabel = mHomePageData.getLABLHomeLearnLabel();
                            mJsonArrayLearnMore = mLABLHomeLearnLabel.getLearnAbout();
                            learnBenefitLabel = mLABLHomeLearnLabel.getMainTitle();
                            loadLearnAbout(mJsonArrayLearnMore, learnBenefitLabel,false);
                            LABLHomeFAQsLabel lablHomeFAQsLabel = mHomePageData.getLABLHomeFAQsLabel();
                            loadFaqs(lablHomeFAQsLabel.getFAQs());
                            HomeBannerObject banner = mHomePageData.getHomeBannerObject();
                            txtBuyFlightPass.setText(banner.getLABLTgpSignUpTitleLabel());
                            txtCheckStatus.setText(banner.getLABLTgpStatusTitleLabel());
                            img_status.setImageUrl(banner.getStatusIconImage(), imageLoader);
                            img_buy.setImageUrl(banner.getBuyIcon().trim(), imageLoader);
                            ArrayList<HomeSliderImage> data = new ArrayList<>();
                            List<HomeSliderImagesText> sliderImage = banner.getHomeSliderImagesText();
                            if (sliderImage != null && sliderImage.size() > 0) {
                                for (int i = 0; i < sliderImage.size(); i++) {
                                    HomeSliderImage mSliderImage1 = new HomeSliderImage();
                                    mSliderImage1.setImage(sliderImage.get(i).getSliderImage());
                                    mSliderImage1.setMessage(sliderImage.get(i).getSliderImageText());
                                    data.add(mSliderImage1);
                                }
                            }
                            scheduleTopBanner(data);
                            try {
                                LABLHomeStepsLabel step2 = mHomePageData.getLABLHomeStepsLabel();
                                label = step2.getSteps().get(0).getLabel();
                                img = step2.getSteps().get(0).getImage();
                                mainTitle = step2.getMainTitle();
                                step2Upgrade(img, label, mainTitle, false);
                                if (sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("4")){
                                    LABLMaximizeYourChanceLabel froMaxLable = mHomePageData.getlABLMaximizeYourChanceLabel();
                                    froMaxImg = froMaxLable.getMaximize().get(0).getImage();
                                    froMaxLabel = froMaxLable.getMaximize().get(0).getLabel();
                                    mainMaxTitle = froMaxLable.getMainTitle();
                                    maximizeFroChances(froMaxImg,froMaxLabel,mainMaxTitle, false);
                                }
                                String testiImgUrl = mHome.getTestimonial_Image();
                                testimonialData(mHomePageData.getHomeTestimonial(),testiImgUrl,mHome.getTestimonial_Label());
                            } catch (Exception ew) {
                                Utils.DEBUG(ew.toString());
                                loader.dismiss();
                            }
                        } else {
                            Utils.ERROR("Error: Missing HomePageData");
                            Utils.showToast(getActivity(), getActivity().getResources().getString(R.string.warning_common_error_message));
                            loader.dismiss();
                            lytTop.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                loader.dismiss();
            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }



    private void loadFaqs(List<FAQ> FAQs) {
        if(getActivity() == null)
            return;

        String img = FAQs.get(0).getImage();
        String desc = FAQs.get(0).getLabel();
        String[] faqArray = desc.split("##");

        if ((sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("4")) || (sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("2")) || (sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("3"))) {
            txtQuestions.setHtml(faqArray[1].toString());

        } else {
            StringBuilder faqDescription = new StringBuilder();
            for (int i = 0; i < faqArray.length; i++) {
                if (i == 0) {
                    continue;
                }
                faqDescription.append("\n• ").append(faqArray[i]);
            }
            txtQuestions.setText(faqDescription.toString());

        }

        imgsFaq.setImageUrl(img, imageLoader);
        if (faqArray.length > 0 && faqArray != null) {
            ((OTTextView) lytFAQ.findViewById(R.id.txtTitle)).setText(faqArray[0]);
        }

        ((ImageView) lytFAQ.findViewById(R.id.imgExpand)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment faqFragment = new FAQFragment();
                AppVariables.leg_product_faq = true;
                Utils.moveToFragment((Activity) view.getContext(), faqFragment, null, 0);
            }
        });



    }
    private void maximizeFroChances(String froImg, final String froLabel, final String mainTitle,  boolean lytExpand) {
        imgFroChances.setImageUrl(froImg,imageLoader);
        final int mid = froLabel.length() / 2;
        String[] parts = {
                froLabel.substring(0, mid), // 1st part
                froLabel.substring(mid), // 2nd part
        };

        txtFroChances.setHtml("");
        if(lytExpand)
        {
            txtFroChances.setHtml(parts[0] + parts[1]);
        }else {
            txtFroChances.setHtml(parts[0]);
        }


        ((OTTextView) lytFroMaximizeChances.findViewById(R.id.txtTitle)).setText(mainTitle);
        final ImageView imgExpand = (ImageView) lytFroMaximizeChances.findViewById(R.id.imgExpand);
        imgExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flagExpandedLytMaximizeChances) {
                    /*String[] parts = {
                            froLabel.substring(0, mid), // 1st part
                            froLabel.substring(mid), // 2nd part
                    };
                    txtFroChances.setHtml(parts[0] + parts[1]);*/

                    maximizeFroChances(froMaxImg,froMaxLabel,mainMaxTitle, false);
                } else {
                    /*String[] parts = {
                            froLabel.substring(0, mid), // 1st part
                            froLabel.substring(mid), // 2nd part
                    };
                    txtFroChances.setHtml(parts[0]);*/
                    maximizeFroChances(froMaxImg,froMaxLabel,mainMaxTitle, true);
                }

                imgExpand.setImageResource(!flagExpandedLytMaximizeChances ? R.drawable.minus_icon : R.drawable.plus_icon);
                flagExpandedLytMaximizeChances = !flagExpandedLytMaximizeChances;
            }
        });
    }
    private void step2Upgrade(final String img, final String label, String title , boolean lytExpand) {

        stepsToUpgrade = label.split("##");
        final StringBuilder faqDescription = new StringBuilder();

        if (lytExpand) {
            if(label.contains("##"))
            {
                for (int i = 0; i < stepsToUpgrade.length; i++) {
                    faqDescription.append("\n ").append(stepsToUpgrade[i]);
                    if (i==1){
                        steps2line = faqDescription.toString();
                        txtSteps2Upgrade.setHtml(steps2line);
                    }else {
                        steps2line = faqDescription.toString();
                        txtSteps2Upgrade.setHtml(steps2line);
                    }
                }
            }else {
                final int mid = label.length() / 2;
                String[] parts = {
                        label.substring(0, mid), // 1st part
                        label.substring(mid), // 2nd part
                };
                txtSteps2Upgrade.setHtml(parts[0] + parts[1]);
            }

        } else {

            if(label.contains("##"))
            {
                for (int i = 0; i < stepsToUpgrade.length; i++) {
                    faqDescription.append("\n ").append(stepsToUpgrade[i]);
                    if (i==1){
                        steps2line = faqDescription.toString();
                        txtSteps2Upgrade.setHtml(steps2line);
                    }else {
                        steps2line = faqDescription.toString();
                        txtSteps2Upgrade.setHtml(steps2line);
                    }
                }
            }else {
                final int mid = label.length() / 2;
                String[] parts = {
                        label.substring(0, mid), // 1st part
                        label.substring(mid), // 2nd part
                };
                txtSteps2Upgrade.setHtml(parts[0]);
            }
        }

        imgSteps2Upgrade.setImageUrl(img, imageLoader);
      //  txtSteps2Upgrade.setText(faqDescription.toString());

        if (stepsToUpgrade.length > 0 && stepsToUpgrade != null) {
            ((OTTextView) lyt_steps2Upgrade.findViewById(R.id.txtTitle)).setText(title);
        }


        final ImageView imgExpandBenefits = (ImageView) lyt_steps2Upgrade.findViewById(R.id.imgExpand);
        imgExpandBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flagExpandedLytStepToUpgrade) {
                    //txtSteps2Upgrade.setHtml(steps2line);
                    step2Upgrade(img, label, mainTitle, false);
                } else {
                    //txtSteps2Upgrade.setHtml(faqDescription.toString());
                    step2Upgrade(img, label, mainTitle, true);
                }


                imgExpandBenefits.setImageResource(!flagExpandedLytStepToUpgrade ? R.drawable.minus_icon : R.drawable.plus_icon);
                flagExpandedLytStepToUpgrade = !flagExpandedLytStepToUpgrade;
            }
        });


    }


    private void testimonialData(List<HomeTestimonial> testimonial, String testiImgUrl, String testimonial_label) {
        //--Testimonials
        AppVariables.count = 0;
       ((OTTextView) lytTestimonials.findViewById(R.id.txtTitle)).setText(testimonial_label);
       NetworkImageView imgTestim = (NetworkImageView) lytTestimonials.findViewById(R.id.img_testimonial);
        imgTestim.setImageUrl(testiImgUrl,imageLoader);
        imgTestim.setDefaultImageResId(R.drawable.testimonials_size);


        testimonial_viewflip = (ViewFlipper) view.findViewById(R.id.testimonial_viewflip);
        ((ImageView) lytTestimonials.findViewById(R.id.imgExpand)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Utils.moveToFragment(getActivity(), new TestimonialFragment(), null, 0);
            }
        });
        /*
        * showing only four dynamic content of testimonial
        * */
        //when testimonial list is empty load the static data
        if (testimonial == null || testimonial.isEmpty()){
            testimonial = addStaticTestimonial();
        }
        while (flipCount < 4) {
          //  AppVariables.count--;
            final ViewFlipper parent_lyttestimonial = legProductTestimonials(getActivity(), testimonial_viewflip, testimonial);
            parent_lyttestimonial.setFlipInterval(6000);
            parent_lyttestimonial.setInAnimation(getActivity(), R.anim.slide_in_up);
            parent_lyttestimonial.setOutAnimation(getActivity(), R.anim.slide_out_down);
            parent_lyttestimonial.startFlipping();
        }

        loader.dismiss();
    }



    private void loadSegProduct(final ArrayList<Benefit> mBenefitReceived,final String benefitLabel, boolean lessElements) {


        recyclerSegBenefits.setHasFixedSize(true);

        ArrayList<Benefit> mBenefits = new ArrayList<>();
        for (int i = 0; i < mBenefitReceived.size(); i++) {

            Benefit mBenefit = new Benefit();

            mBenefit.setBenefitName(mBenefitReceived.get(i).getBenefitName());
            mBenefit.setBenefitDescription(mBenefitReceived.get(i).getBenefitDescription());
            mBenefit.setImageURL(mBenefitReceived.get(i).getImageURL());

            if(lessElements && i == 3)
            {
                break;
            }

            mBenefits.add(mBenefit);
        }
        //txtTitle.setText(mBenefitReceived.get);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerSegBenefits.setLayoutManager(mLinearLayoutManager);
        adapter = new BegifitsRecyclerViewAdapter(getActivity(), (ArrayList<Benefit>) mBenefits, new LegProductsHomeFragment().getClass().getName());
        recyclerSegBenefits.setAdapter(adapter);
        ((OTTextView) lytSegBenifits.findViewById(R.id.txtTitle)).setText(benefitLabel);
        if (sharedPrefs.get(getString(R.string.key_selected_productId)).toString().equalsIgnoreCase("4")){
            lytFroMaximizeChances.setVisibility(View.VISIBLE);
        }else {
            lytFroMaximizeChances.setVisibility(View.GONE);
        }

        final ImageView imgExpand= ((ImageView) lytSegBenifits.findViewById(R.id.imgExpand));
        imgExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Benefit> tempBenefits = new ArrayList<Benefit>();
                if (flagExpandedLytLegLearnMore) {
                    //txtLearnAboutReadMore.setVisibility(View.GONE);
                    loadSegProduct(mBenefitReceived, benefitLabel,true);
                } else {
                    //txtLearnAboutReadMore.setVisibility(View.VISIBLE);
                    loadSegProduct(mBenefitReceived, benefitLabel,false);
                }

                imgExpand.setImageResource(!flagExpandedLytLegLearnMore ? R.drawable.minus_icon : R.drawable.plus_icon);
                flagExpandedLytLegLearnMore = !flagExpandedLytLegLearnMore;
            }
        });


    }

    private void loadLearnAbout(final List<LearnAbout> mJsonArrayLearnMore, String benefitLabel, boolean singlePara) {
        recyclerViewLearnAbout.setHasFixedSize(true);

        String title = "";
        String description = "";
        String spliter = "##";
        ArrayList<Benefit> mBenefits = new ArrayList<>();
        int maxNoOfElements = 0;
        if(Utils.getCurrentProductId(getActivity()) == getResources().getInteger(R.integer.value_tgProductId_FlexibilityReward))
        {
            if(singlePara)
            {
                maxNoOfElements = 5;
            }else {
                maxNoOfElements = 1;
            }

        }else {
            maxNoOfElements = 2;
        }
        for (int i = 0; i < maxNoOfElements; i++) {
            try {
                Benefit mBenefit = new Benefit();
                String s = mJsonArrayLearnMore.get(i).getLabel();
                if (s.indexOf(spliter) > 0) {

                    title = s.split(spliter)[0].toString();
                    description = s.substring(title.length() + spliter.length());
                    mBenefit.setBenefitName(title);
                    mBenefit.setBenefitDescription(description);
                    if (!singlePara && i == 1) {
                        break;
                    }else {

                        if (i==2)
                            break;
                    }

                } else {
                    mBenefit.setBenefitDescription(s);
                }


                mBenefit.setImageURL(mJsonArrayLearnMore.get(i).getImage());
                mBenefit.setId(i);

               // if (i>1)
                mBenefits.add(mBenefit);
            } catch (Exception exc) {

            }
        }
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewLearnAbout.setLayoutManager(mLinearLayoutManager);
        adapter = new BegifitsRecyclerViewAdapter(getActivity(), mBenefits, new LegProductsHomeFragment().getClass().getName());
        recyclerViewLearnAbout.setAdapter(adapter);

        ((OTTextView) lytLearnAbout.findViewById(R.id.txtTitle)).setText(benefitLabel);
        final ImageView imgExpandBenefits = (ImageView) lytLearnAbout.findViewById(R.id.imgExpand);
        imgExpandBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Benefit> tempBenefits = new ArrayList<Benefit>();
                if (flagExpandedLytLegLearnMore) {
                    txtLearnAboutReadMore.setVisibility(View.GONE);
                    loadLearnAbout(mJsonArrayLearnMore, learnBenefitLabel,false);
                } else {
                    txtLearnAboutReadMore.setVisibility(View.VISIBLE);
                    loadLearnAbout(mJsonArrayLearnMore, learnBenefitLabel,true);
                }

                imgExpandBenefits.setImageResource(!flagExpandedLytLegLearnMore ? R.drawable.minus_icon : R.drawable.plus_icon);
                flagExpandedLytLegLearnMore = !flagExpandedLytLegLearnMore;
            }
        });
        txtLearnAboutReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment((Activity) view.getContext(), new LegProductLearnMoreFragment(), null, 0);
            }
        });
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
                int pauseTime = 5000;
                final int rotationRound = 3;
                boolean isFirst = true;

                final ImageLoader imageLoader = AppController.getInstance().getImageLoader();


                final BitmapDrawable imgDrawable[] = new BitmapDrawable[data.size()];
                for (int index = 0; index < data.size(); index++) {
                    //to check fragment is attached in the activity, check this fragment at top
                    if (!Utils.getTopFragmentInBackStack(getActivity()).equals(new LegProductsHomeFragment().getClass().getName())) {
                        return;
                    }
                    if(!isFirst)
                    {
                        isFirst = false;
                        try {
                            sleep(pauseTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
                                    if (Utils.getTopFragmentInBackStack(getActivity()).equals(new LegProductsHomeFragment().getClass().getName())) {
                                        if (response.getBitmap() != null) {
                                            imgLoader.setVisibility(View.GONE);
                                            Utils.setBackground(lytLPTopBanner, imgDrawable[finalIndex] = new BitmapDrawable(getResources(), response.getBitmap()));
                                            txtLpTopBanner.setText(data.get(finalIndex).getMessage().replace("&nbsp;",""));
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

                    if (Utils.getTopFragmentInBackStack(getActivity()).equals(new LegProductsHomeFragment().getClass().getName())) {
                        final int finalIndex = index;
                        handlerBanner.post(new Runnable() {
                            @Override
                            public void run() {
                                Utils.setBackground(lytLPTopBanner, imgDrawable[finalIndex % imgDrawable.length]);
                                txtLpTopBanner.setText(data.get(finalIndex % imgDrawable.length).getMessage().replace("&nbsp;",""));
                            }
                        });
                    } else {
                        return;
                    }
                }
            }
        }.start();

    }



    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private List<HomeTestimonial> addStaticTestimonial() {
        ArrayList<HomeTestimonial> testimonials = new ArrayList<>();
        String [] summary = getActivity().getResources().getStringArray(R.array.summary);
        String [] writer = getActivity().getResources().getStringArray(R.array.writer);
        String [] country = getActivity().getResources().getStringArray(R.array.country);
        for (int i = 0; i < 4; i++) {
            HomeTestimonial monial = new HomeTestimonial();
            monial.setSummury(summary[i]);
            monial.setWritter(writer[i]);
            monial.setCountry(country[i]);
            testimonials.add(monial);
        }
        return testimonials;
    }

    public  ViewFlipper legProductTestimonials(Context context, ViewFlipper parent, List<HomeTestimonial> list){

        LayoutInflater layoutInfralte=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInfralte.inflate(R.layout.child_testimonial, null);
        OTTextViewHtml txt_content_Testimonial = (OTTextViewHtml) view.findViewById(R.id.txt_content_Testimonial);
        OTTextView txtTestimonialName = (OTTextView) view.findViewById(R.id.txtTestimonialName);
        OTTextView txtTestimonialCountry = (OTTextView) view.findViewById(R.id.txtTestimonialCountry);
        txt_content_Testimonial.setHtml(list.get(AppVariables.count).getSummury());
        txtTestimonialName.setText(list.get(AppVariables.count).getWritter());
        txtTestimonialCountry.setText(list.get(AppVariables.count).getCountry());
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        parent.addView(view);
        AppVariables.count++;

        return parent;
    }




}

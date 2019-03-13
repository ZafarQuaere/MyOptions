package com.optiontown.app.view.fragment.legproducts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.adapter.BegifitsRecyclerViewAdapter;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.model.legproducthomepage.Home;
import com.optiontown.app.model.legproducthomepage.HomePageData;
import com.optiontown.app.model.legproducthomepage.LABLHomeLearnLabel;
import com.optiontown.app.model.legproducthomepage.LABLHomeStepsLabel;

import com.optiontown.app.model.legproducthomepage.LearnAbout;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.customview.OTTextViewHtml;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zafar.imam on 20-08-2016.
 */
public class LegProductLearnMoreFragment extends BaseFragment {
    View view;
    Home homeData;
    RecyclerView recyclerViewLearnAbout;
    NetworkImageView imgSteps2Upgrade;
    OTTextViewHtml txtSteps2Upgrade;
    BegifitsRecyclerViewAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageLoader imageLoader;
    private LinearLayout lyt_steps2Upgrade;
    String[] stepsToUpgrade;
    String steps2line;
    private boolean flagExpandedLytStepToUpgrade = false;
    private String label;
    private String img;
    private String mainTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_leg_product_learn_more, container, false);
        initUi(view);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        Utils.updateLegProductBottomBarForFeatures(getActivity(), view, this.getClass().getName());
        try {
            homeData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLegProductSessionData(getActivity())), Home.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HomePageData mHomePageData = homeData.getHomePageData();
        LABLHomeLearnLabel mLABLHomeLearnLabel =mHomePageData.getLABLHomeLearnLabel();
        List<LearnAbout> mJsonArrayLearnAbout= mLABLHomeLearnLabel.getLearnAbout();
        loadLearnAbout(mJsonArrayLearnAbout);
        LABLHomeStepsLabel step2 = mHomePageData.getLABLHomeStepsLabel();
        label = step2.getSteps().get(0).getLabel();
        img = step2.getSteps().get(0).getImage();
        mainTitle = step2.getMainTitle();
        step2Upgrade(img, label,mainTitle, false);
        return view;
    }



    private void initUi(View view) {
        imageLoader = AppController.getInstance().getImageLoader();
        Utils.updateLegProductBottomBarForFeatures(getActivity(),view, this.getClass().getName());
        recyclerViewLearnAbout = (RecyclerView) view.findViewById(R.id.recyclerViewLearnAbout);
        lyt_steps2Upgrade = (LinearLayout) view.findViewById(R.id.lyt_steps2Upgrade);
        txtSteps2Upgrade = (OTTextViewHtml) view.findViewById(R.id.txtSteps2Upgrade);
        imgSteps2Upgrade = (NetworkImageView) view.findViewById(R.id.imgSteps2Upgrade);
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


    /*private void step2Upgrade(String img, String label) {
        stepsToUpgrade = label.split("##");
        final StringBuilder faqDescription = new StringBuilder();
        for (int i = 0; i < stepsToUpgrade.length; i++) {

            faqDescription.append("\n• ").append(stepsToUpgrade[i]);
            if (i==1){
                steps2line = faqDescription.toString();
                txtSteps2Upgrade.setText(steps2line);
            }
        }
        imgSteps2Upgrade.setImageUrl(img, imageLoader);
        //  txtSteps2Upgrade.setText(faqDescription.toString());
        final ImageView imgExpandBenefits = (ImageView) lyt_steps2Upgrade.findViewById(R.id.imgExpand);
        imgExpandBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flagExpandedLytStepToUpgrade) {
                    txtSteps2Upgrade.setText(steps2line);
                } else {
                    txtSteps2Upgrade.setText(faqDescription.toString());

                }


                imgExpandBenefits.setImageResource(!flagExpandedLytStepToUpgrade ? R.drawable.minus_icon : R.drawable.plus_icon);
                flagExpandedLytStepToUpgrade = !flagExpandedLytStepToUpgrade;
            }
        });

        ((OTTextView) lyt_steps2Upgrade.findViewById(R.id.txtTitle)).setText(homeData.getHomePageData().getLABLHomeStepsLabel().getMainTitle());
    }*/
    private void loadLearnAbout(List<LearnAbout> mJsonArrayLearnAbout) {

        recyclerViewLearnAbout.setHasFixedSize(true);


        String title = "";
        String description = "";
        String spliter = "##";
        ArrayList<Benefit> mBenefits = new ArrayList<>();
        for (int i = 0; i < mJsonArrayLearnAbout.size(); i++) {
            try {
                Benefit mBenefit = new Benefit();
                String s = mJsonArrayLearnAbout.get(i).getLabel();
                if (s.indexOf(spliter) > 0) {
                    title = s.split(spliter)[0].toString();
                    description = s.substring(title.length() + spliter.length());
                    mBenefit.setBenefitName(title);
                    mBenefit.setBenefitDescription(description);
                    /*if(i==2){
                        break;
                    }*/

                } else {
                    mBenefit.setBenefitDescription(s);
                }


                mBenefit.setImageURL(mJsonArrayLearnAbout.get(i).getImage());
                mBenefit.setId(i);


                mBenefits.add(mBenefit);
            } catch (Exception exc) {

            }
        }
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewLearnAbout.setLayoutManager(mLinearLayoutManager);
        adapter = new BegifitsRecyclerViewAdapter(getActivity(), mBenefits, new LegProductLearnMoreFragment().getClass().getName());
        recyclerViewLearnAbout.setAdapter(adapter);
        //loader.dismiss();
    }


}

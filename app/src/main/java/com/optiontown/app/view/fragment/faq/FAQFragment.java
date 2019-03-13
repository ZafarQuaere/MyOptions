package com.optiontown.app.view.fragment.faq;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.optiontown.R;
import com.optiontown.app.adapter.FAQRecyclerViewAdapter;
import com.optiontown.app.model.legproducthomepage.Home;
import com.optiontown.app.model.session.FaqQuestion;
import com.optiontown.app.model.session.QuestionAnswer;
import com.optiontown.app.model.session.SessionIdData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 11-07-2016.
 */
public class FAQFragment extends BaseFragment {
    private View view;
    private AppSharedPrefs sp;

    SessionIdData data;
    Home lPdata;
    ArrayList<FaqQuestion> faqQuestionList;
    private Boolean isBottomBarFromMmp;
    private LinearLayout bottom_lay_less;
    private LinearLayout bottom_lay_more;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_faq, container, false);

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());


        getUIReference();


        return view;

    }



    private void getUIReference() {
        bottom_lay_less = (LinearLayout) view.findViewById(R.id.bottom_lay_less);
        bottom_lay_more = (LinearLayout) view.findViewById(R.id.bottom_lay_more);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewFAQ);
        recyclerView.setHasFixedSize(true);

        final ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView);

        LinearLayout lytBack = (LinearLayout) view.findViewById(R.id.lytBack);

        lytBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
            }
        });

        final LinearLayout lytQuestionAnswer = (LinearLayout) view.findViewById(R.id.lytQuestionAnswer);
        final OTTextView txtTitle = (OTTextView) view.findViewById(R.id.txtTitle);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        JSONObject json = null;
        if (AppVariables.leg_product_faq) {
            Utils.updateLegProductBottomBarForFeatures(getActivity(), view, this.getClass().getName());
            bottom_lay_less.setVisibility(View.GONE);
            bottom_lay_more.setVisibility(View.GONE);
            try {
                json = new JSONObject(Utils.getLegProductSessionData(getActivity()));
                Utils.DEBUG("session json : " + json.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lPdata = (ParseManager.getInstance().fromJSON(json, Home.class));
            faqQuestionList = lPdata.getIndustryList().getFaqQuestion();

            AppVariables.leg_product_faq = false;

        } else {

            updateBottomBar();

            try {
                json = new JSONObject(Utils.getSessionData(getActivity()));
                Utils.DEBUG("session json : " + json.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data = (ParseManager.getInstance().fromJSON(json, SessionIdData.class));
            faqQuestionList = data.getIndustryList().getFaqQuestion();
        }


        RecyclerView.Adapter adapter = new FAQRecyclerViewAdapter(getActivity(), faqQuestionList, new FAQRecyclerViewAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItem(View v, int position) {
                //first dismiss the recycler view
                recyclerView.setVisibility(View.GONE);
                //make scrollview visible
                scrollView.setVisibility(View.VISIBLE);
                //clear all question answer view
                lytQuestionAnswer.removeAllViews();

                txtTitle.setText(faqQuestionList.get(position).getFaqNAme());

                ArrayList<QuestionAnswer> questionAnswerList = faqQuestionList.get(position).getQuestionAnswer();
                for (int index = 0; index < questionAnswerList.size(); index++) {
                    final View viewQuestionAnswer = getActivity().getLayoutInflater().inflate(R.layout.layout_faq_one_question_answer_set, null, false);

                    OTTextView txtQuestion = ((OTTextView) viewQuestionAnswer.findViewById(R.id.txtQuestion));
                    txtQuestion.setText(questionAnswerList.get(index).getQues());

                    final OTTextView txtAnswer = ((OTTextView) viewQuestionAnswer.findViewById(R.id.txtAnswer));
                    txtAnswer.setText(questionAnswerList.get(index).getAns());

                    final ImageView imgExpand = (ImageView) viewQuestionAnswer.findViewById(R.id.imgExpand);

                    viewQuestionAnswer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View vi) {


                            for (int i = 0; i < lytQuestionAnswer.getChildCount(); i++) {
                                View v = lytQuestionAnswer.getChildAt(i);
                                OTTextView txtAnswer = ((OTTextView) v.findViewById(R.id.txtAnswer));
                                ImageView imgExpand = (ImageView) v.findViewById(R.id.imgExpand);

                                if(v.equals(viewQuestionAnswer))
                                {
                                    if (txtAnswer.getVisibility() == View.GONE) {
                                        txtAnswer.setVisibility(View.VISIBLE);
                                        imgExpand.setImageResource(R.drawable.minus_icon);
                                    } else {
                                        txtAnswer.setVisibility(View.GONE);
                                        imgExpand.setImageResource(R.drawable.plus_icon);
                                    }
                                }
                                else
                                {
                                    txtAnswer.setVisibility(View.GONE);
                                    imgExpand.setImageResource(R.drawable.plus_icon);
                                }
                            }

                        }
                    });

                    lytQuestionAnswer.addView(viewQuestionAnswer);
                }


            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void updateBottomBar() {

        try {
            isBottomBarFromMmp = (Boolean) getArguments().getSerializable(getString(R.string.key_serializable));
        }catch (Exception e)
        {
        }

        if(isBottomBarFromMmp != null)
        {
            if (isBottomBarFromMmp) {
                bottom_lay_less.setVisibility(View.VISIBLE);
                bottom_lay_more.setVisibility(View.VISIBLE);

                Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(),false);
            }else {
                Utils.updateBottomBarForFeatures(view, this.getClass().getName());
            }
        }else {

            bottom_lay_less.setVisibility(View.GONE);
            bottom_lay_more.setVisibility(View.GONE);

            Utils.updateBottomBarForFeatures(view, this.getClass().getName());
        }
    }
}

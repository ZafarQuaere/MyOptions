package com.optiontown.app.view.fragment.legproducts;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.optiontown.R;
import com.optiontown.app.adapter.FlexibilityRewardSearchResultRecyclerViewAdapter;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by zafar.imam on 03-08-2016.
 */
public class FlexibilityRewardSearchResultFragment extends BaseFragment {

    Button btnFroContinue;
    private AppSharedPrefs sp;
    private OTTextView txtFro, txtFroViewDetails, txtFroJoinOT;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flexibility_reward_search_result, container, false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        Init(view);

        return view;
    }

    private void Init(View view) {
        sp = AppSharedPrefs.getInstance(getActivity());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyViewFlexiReward);
        mRecyclerView.setHasFixedSize(true);
        ArrayList<Benefit> mBenefits = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Benefit mBenefit = new Benefit();
            mBenefit.setBenefitName("s" + " " + i);
            mBenefit.setImageURL("https://www.optiontown.com/images/alt/UTo_Benefits_Small_Image_1.jpg");
            mBenefit.setId(i);
            mBenefits.add(mBenefit);
        }
        mLinearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        adapter = new FlexibilityRewardSearchResultRecyclerViewAdapter(getActivity(), mBenefits);
        mRecyclerView.setAdapter(adapter);

        txtFroViewDetails = (OTTextView) view.findViewById(R.id.txtFroViewDetails);
        txtFroJoinOT = (OTTextView) view.findViewById(R.id.txtFroJoinOT);
        txtFroViewDetails.setPaintFlags(txtFroViewDetails.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtFroJoinOT.setPaintFlags(txtFroJoinOT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        txtFroViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new LegProductViewDetailsFragment(), null, 0);
            }
        });

        btnFroContinue = (Button) view.findViewById(R.id.btnFroContinue);
        btnFroContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.moveToFragment(getActivity(), new LegProductReviewFragment(), null, 0);

            }
        });

    }

}


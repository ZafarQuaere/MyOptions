package com.optiontown.app.view.fragment.support;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.optiontown.R;
import com.optiontown.app.adapter.BegifitsRecyclerViewAdapter;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by zafar.imam on 03-08-2016.
 */
public class IncludeProductBenefitsFragment extends BaseFragment {
    private AppSharedPrefs sp;


    private RecyclerView recyclerViewListBenefits;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;

    OTTextView txtTitle;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_benefits, container, false);
        Init(view);
        return view;
    }

    private void Init(View view) {
        sp = AppSharedPrefs.getInstance(getActivity());
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());


        recyclerViewListBenefits = (RecyclerView) view.findViewById(R.id.recyclerViewListBenefits);
        txtTitle = (OTTextView) view.findViewById(R.id.txtTitle);
        txtTitle.setText("Flight Pass Benefits");


        recyclerViewListBenefits.setHasFixedSize(true);
        ArrayList<Benefit> mBenefits= new ArrayList<>();
        for(int i=0; i< 5; i++){
            Benefit mBenefit= new Benefit();
            mBenefit.setBenefitName("s"+" "+ i);
            mBenefit.setImageURL("https://www.optiontown.com/images/alt/UTo_Benefits_Small_Image_1.jpg");
            mBenefit.setId(i);
            mBenefits.add(mBenefit);
        }
        mLinearLayoutManager = new LinearLayoutManager(this.getActivity(),  LinearLayoutManager.VERTICAL, false);
        recyclerViewListBenefits.setLayoutManager(mLinearLayoutManager);
        adapter= new BegifitsRecyclerViewAdapter(getActivity(), mBenefits, new IncludeProductBenefitsFragment().getClass().getName());
        recyclerViewListBenefits.setAdapter(adapter);


    }

}


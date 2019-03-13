package com.optiontown.app.view.fragment.segproducts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.optiontown.R;
import com.optiontown.app.model.segproduct.SegInputData;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

/**
 * Created by amit on 12-07-2017.
 */
public class SegSelectFlightInnerFragment extends BaseFragment
{
    private LinearLayout view;
    private SegDummyData segDummyData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = (LinearLayout) inflater.inflate(R.layout.fragment_seg_select_flight_inner, container, false);
        segDummyData = (SegDummyData) getArguments().get(getActivity().getString(R.string.key_serializable));

        createUI();

        return view;
    }

    private void createUI()
    {
        for (int index = 0; index < segDummyData.getFlightDetails().size(); index++)
        {
            String s = segDummyData.getFlightDetails().get(index);

            View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_seg_select_flight_inner_row, null);

            OTTextView txtFlightNumber = (OTTextView) view.findViewById(R.id.txtFlightNumber);
            txtFlightNumber.setText(s);

            final RelativeLayout lytInfoDetails = (RelativeLayout) view.findViewById(R.id.lytInfoDetails);
            LinearLayout lytRow = (LinearLayout) view.findViewById(R.id.lytRow);
            lytRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lytInfoDetails.setVisibility(lytInfoDetails.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }
            });


            ImageView imgMinus = (ImageView) view.findViewById(R.id.imgMinus);
            imgMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = (ImageView) v;
                    lytInfoDetails.setVisibility(View.GONE);
                }
            });

            this.view.addView(view);

        }



    }
}

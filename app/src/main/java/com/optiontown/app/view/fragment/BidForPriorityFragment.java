package com.optiontown.app.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.optiontown.R;
import com.optiontown.app.adapter.BoostPriorityRecyclerViewAdapter;
import com.optiontown.app.model.utosearchresult.BoostDetail;
import com.optiontown.app.model.utosearchresult.BoostMypriority;
import com.optiontown.app.model.utosearchresult.PriceUpcabinDetail;
import com.optiontown.app.model.utosearchresult.UpcabinNameDetail;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.customview.VerticalSeekBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zafar.imam on 06-08-2016.
 */
public class BidForPriorityFragment extends Fragment {
    View view;
    VerticalSeekBar mVerticalSeekBar;
    OTTextView txtAmount;
    int selection;
    ArrayList<Integer> values;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bid_for_priority, container, false);
        Init(view);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        values = new ArrayList<>();
        values.add(32);
        values.add(34);
        values.add(332);
        values.add(67);
        values.add(592);

        final int maxvalue = Collections.max(values);
        Collections.sort(values);

    /*    for(int i=0; i<values.size(); i++)
        {
            Utils.showToast(getActivity(), values.get(i).toString());
        }
*/


        mVerticalSeekBar.setMax(maxvalue);

        mVerticalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                /*if(selection> progress){
                   for(int i=0; i<values.size(); i++)
                    {

                    }
                }else{
                    for(int i=0; i<values.size(); i++)
                    {

                    }
                }
                selection= progress;
                txtAmount.setText(progress + "");*/


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    private void Init(View view) {
        mVerticalSeekBar = (VerticalSeekBar) view.findViewById(R.id.scroller);
        txtAmount = (OTTextView) view.findViewById(R.id.txtAmount);
    }


}

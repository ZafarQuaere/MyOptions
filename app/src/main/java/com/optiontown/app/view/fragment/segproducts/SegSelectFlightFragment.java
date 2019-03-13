package com.optiontown.app.view.fragment.segproducts;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.optiontown.R;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.WrapContentViewPager;
import com.optiontown.app.view.fragment.BaseFragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 11-07-2017.
 */
public class SegSelectFlightFragment extends BaseFragment
{
    private View view;

    private ArrayList<SegDummyData> listDummyData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_seg_select_flight, container, false);
        listDummyData = getDummyData();

        final WrapContentViewPager pager = (WrapContentViewPager) view.findViewById(R.id.pager);

        /** Getting fragment manager */
        FragmentManager fm = getActivity().getSupportFragmentManager();

        /** Instantiating FragmentPagerAdapter */
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(fm, listDummyData);

        /** Setting the pagerAdapter to the pager object */
        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Utils.DEBUG("onPageSelected >> " + position);
                pager.reMeasureCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final ImageView imgArrowLeft = (ImageView) view.findViewById(R.id.imgArrowLeft);
        final ImageView imgArrowRight = (ImageView) view.findViewById(R.id.imgArrowRight);

        imgArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pager.setCurrentItem(pager.getCurrentItem() - 1);
                setArrowVisibility(pager, imgArrowLeft, imgArrowRight);
            }
        });

        imgArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                setArrowVisibility(pager, imgArrowLeft, imgArrowRight);
            }
        });



        return view;
    }



    private ArrayList<SegDummyData> getDummyData() {

        ArrayList<SegDummyData> list = new ArrayList<>();

        SegDummyData d1 = new SegDummyData();
        d1.setDate("Aug 4");
        ArrayList<String> listFD1 = new ArrayList<>();
        listFD1.add("F 41");
        listFD1.add("F 42");
        listFD1.add("F 43");
        listFD1.add("F 44");
        d1.setFlightDetails(listFD1);
        list.add(d1);

        SegDummyData d2 = new SegDummyData();
        d2.setDate("Aug 5");
        ArrayList<String> listFD2 = new ArrayList<>();
        listFD2.add("F 51");
        listFD2.add("F 52");
        listFD2.add("F 53");
        listFD2.add("F 54");
        d2.setFlightDetails(listFD2);
        list.add(d2);

        SegDummyData d3 = new SegDummyData();
        d3.setDate("Aug 6");
        ArrayList<String> listFD3 = new ArrayList<>();
        listFD3.add("F 61");
        listFD3.add("F 62");
        /*listFD3.add("F 63");
        listFD3.add("F 64");*/
        d3.setFlightDetails(listFD3);
        list.add(d3);

        SegDummyData d4 = new SegDummyData();
        d4.setDate("Aug 7");
        ArrayList<String> listFD4 = new ArrayList<>();
        listFD4.add("F 71");
        /*listFD4.add("F 72");
        listFD4.add("F 73");
        listFD4.add("F 74");*/
        d4.setFlightDetails(listFD4);
        list.add(d4);

        SegDummyData d5 = new SegDummyData();
        d5.setDate("Aug 8");
        ArrayList<String> listFD5 = new ArrayList<>();
        listFD5.add("F 81");
        listFD5.add("F 82");
        listFD5.add("F 83");
        listFD5.add("F 84");
        d5.setFlightDetails(listFD5);
        list.add(d5);

        return list;
    }

    private void setArrowVisibility(ViewPager pager, ImageView imgArrowLeft, ImageView imgArrowRight)
    {
        imgArrowLeft.setVisibility(pager.getCurrentItem() == 0 ? View.GONE : View.VISIBLE);
        imgArrowRight.setVisibility(pager.getCurrentItem() == pager.getChildCount() ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.DEBUG("SegSelectFlightFragment >> onCreate() called");
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<SegDummyData> listDummyData;

        /** Constructor of the class */
        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<SegDummyData> listDummyData) {
            super(fm);
            this.listDummyData = listDummyData;
        }



        /** This method will be invoked when a page is requested to create */
        @Override
        public Fragment getItem(int position) {

            Utils.DEBUG("getItem >> " + position);

            SegSelectFlightInnerFragment myFragment = new SegSelectFlightInnerFragment();
            Bundle data = new Bundle();
            data.putSerializable(getActivity().getString(R.string.key_serializable), (Serializable) listDummyData.get(position));
            myFragment.setArguments(data);
            return myFragment;
        }



        @Override
        public long getItemId(int position) {
            Utils.DEBUG("getItemId >> " + position);
            return super.getItemId(position);
        }

        /** Returns the number of pages */
        @Override
        public int getCount() {
            return listDummyData.size();
        }
    }
}

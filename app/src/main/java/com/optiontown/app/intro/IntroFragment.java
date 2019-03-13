package com.optiontown.app.intro;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.optiontown.R;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.HomeFragment;


/**
 * Created by zafar.imam on 05-08-2017.
 */

public class IntroFragment extends Fragment {

    public int current = 1;
    private View view;
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button btnSkip;
    // private Button btnNext;
    private int[] layouts;
    private TextView[] dots;

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            current = position;

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private MyViewPagerAdapter myViewPagerAdapter;
    private RelativeLayout lytTopBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.welcome_fragment, null, false);

        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        lytTopBar = (RelativeLayout) getActivity().findViewById(R.id.lytTopBar);
        lytTopBar.setVisibility(View.GONE);

        Utils.setFirstTimeLaunch(getActivity(), false);

        initUI(view);
        return view;
    }

    private void initUI(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
        btnSkip = (Button) view.findViewById(R.id.btnSkip);
        // btnNext = (Button) view.findViewById(R.id.btn_next);

        // add few more layouts if you want
        layouts = new int[]{R.layout.welcome_slide1, R.layout.welcome_slide2, R.layout.welcome_slide3, R.layout.welcome_slide4};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });


    }


    private void setDynamicContent(int position, View layout) {

        LinearLayout linearLayout = (LinearLayout) layout.findViewById(R.id.linear_layout);
        OTTextView textView = (OTTextView) linearLayout.findViewById(R.id.txtTitle);

        switch (position){
            case 0:
                textView.setText("About Us " + position);
                break;
            case 1:
                textView.setText("Who We Are " + position);
                break;
            case 2:
                textView.setText("Our Partners " + position);
                break;
            case 3:
                textView.setText("Flight Pass " + position);
                break;
        }
        Utils.DEBUG("Title : " + textView.getText().toString()+" Positions : "+position);
    }



    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

     /*   int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);*/

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_35)));
            dots[i].setTextColor(getResources().getColor(R.color.color_red));
            dots[i].setGravity(Gravity.CENTER);
            dots[i].setPadding(0,0,0, (int) Utils.convertPixelToDp(getActivity(),getResources().getDimension(R.dimen.dp_5)));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length >= 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.color_font_gray));
    }

    private void launchHomeScreen() {

        lytTopBar.setVisibility(View.VISIBLE);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment f = new HomeFragment();
        transaction.add(R.id.lytMain, f);
        transaction.commit();
    }

    private void changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View currentPageView = layoutInflater.inflate(layouts[position], container, false);
            container.addView(currentPageView);

            setDynamicContent(position, currentPageView);

            return currentPageView;
        }


        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }


    }
}

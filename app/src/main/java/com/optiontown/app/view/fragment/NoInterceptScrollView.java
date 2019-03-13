package com.optiontown.app.view.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by amit on 25-07-2016.
 * To avoid nested scrolling
 */
public class NoInterceptScrollView extends ScrollView {

    public NoInterceptScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //to avoid nested scrolling
        return false;
    }

}

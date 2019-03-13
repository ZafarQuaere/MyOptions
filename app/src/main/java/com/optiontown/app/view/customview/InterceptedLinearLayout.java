package com.optiontown.app.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.optiontown.app.utils.Utils;

/**
 * Created by amit on 19-09-2016.
 */
public class InterceptedLinearLayout extends LinearLayout
{

    public InterceptedLinearLayout(Context context) {
        super(context);
    }

    public InterceptedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        //Utils.DEBUG("Intercept parent");
        onTouchEvent(ev);
        return true;
    }*/
}

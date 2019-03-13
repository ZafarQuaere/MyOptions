package com.optiontown.app.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by amit on 18-10-2016.
 */
public class InterceptTextView extends TextView
{

    public InterceptTextView(Context context) {
        super(context);
    }

    public InterceptTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return false;
    }


}

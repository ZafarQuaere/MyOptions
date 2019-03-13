package com.optiontown.app.view.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.optiontown.R;

/**
 * Base class for OT text view
 * Created by amit on 24-05-2016.
 */
public class OTTextView extends TextView
{
    public OTTextView(Context context) {
        super(context);
    }

    public OTTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OTTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        if(text == null)
        {
                super.setText(" ", type);//to avoid null


        }
        else
        {
                super.setText(((String) text).replace(this.getContext().getString(R.string.string_rs_html_1), this.getContext().getString(R.string.string_rs)), type);

        }
    }

    @Override
    public void setTypeface(Typeface tf, int style) {

        if(getResources().getBoolean(R.bool.bool_default_font))
        {
            super.setTypeface(tf, style);
        }
        else
        {
            String strFont = "";
            if(style == Typeface.BOLD)
            {
                strFont = "fonts/AmerType-Md-BT.TTF";
            }
            else if(style == Typeface.NORMAL)
            {
                strFont = "fonts/Znikomit.otf";
            }
            tf = Typeface.createFromAsset(getContext().getAssets(), strFont);
            super.setTypeface(tf, Typeface.BOLD);
        }
    }
}

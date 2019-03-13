package com.optiontown.app.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.optiontown.R;

/**
 * Created by amit on 06-08-2016.
 */
public class OTRadioButton extends RadioButton
{

    public OTRadioButton(Context context) {
        super(context);
    }

    public OTRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OTRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(text == null)
        {
            super.setText("", type);//to avoid null
        }
        else
        {
            super.setText(((String) text).replace(this.getContext().getString(R.string.string_rs_html_1), this.getContext().getString(R.string.string_rs)), type);
        }
    }
}

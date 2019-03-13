package com.optiontown.app.utils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.optiontown.R;

/**
 * Created by zafar.imam on 29-11-2016.
 */

public class MyClickableSpan extends ClickableSpan {
    private Context activity;
    private int pos;
    private String body;
    private String title;

    public MyClickableSpan(Context activity, int position,String body,String title) {
        this.pos = position;
        this.activity = activity;
        this.body = body;
        this.title = title;
    }

    @Override
    public void onClick(View widget) {
        Utils.showPopUp((FragmentActivity) activity,
                body, title);

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(false);
        ds.setColor(activity.getResources().getColor(R.color.color_font_blue));
    }
}

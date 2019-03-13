package com.optiontown.app.model.segproduct;

import java.io.Serializable;

/**
 * Created by amit on 17-07-2017.
 */
public class SelectedDateData implements Serializable
{
    private int position;
    private String date;
    private String dateWithText;
    private int viewId;

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateWithText() {
        return dateWithText;
    }

    public void setDateWithText(String dateWithText) {
        this.dateWithText = dateWithText;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

package com.optiontown.app.model.fpogetpass;

import android.provider.SearchRecentSuggestions;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 14/04/2017.
 */
public class PassSortDropDownList implements Serializable{

    private String PassSortDropDownValue;

    private String PassSortDropDownLabel;

    public String getPassSortDropDownLabel() {
        return PassSortDropDownLabel;
    }

    public void setPassSortDropDownLabel(String passSortDropDownLabel) {
        PassSortDropDownLabel = passSortDropDownLabel;
    }

    public String getPassSortDropDownValue() {
        return PassSortDropDownValue;
    }

    public void setPassSortDropDownValue(String passSortDropDownValue) {
        PassSortDropDownValue = passSortDropDownValue;
    }



}

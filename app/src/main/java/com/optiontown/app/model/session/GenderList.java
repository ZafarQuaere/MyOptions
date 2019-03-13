package com.optiontown.app.model.session;

import java.io.Serializable;

/**
 * Created by amit on 14-04-2017.
 */
public class GenderList implements Serializable
{
    private int GenderValue;

    public int getGenderValue() { return this.GenderValue; }

    public void setGenderValue(int GenderValue) { this.GenderValue = GenderValue; }

    private String GenderLabel;

    public String getGenderLabel() { return this.GenderLabel; }

    public void setGenderLabel(String GenderLabel) { this.GenderLabel = GenderLabel; }
}

package com.optiontown.app.model.selectproduct;

import android.widget.SectionIndexer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zafar.imam on 22-02-2017.
 */
public class Value implements Serializable{

    private int Feature_Id;

    public int getFeatureId() { return this.Feature_Id; }

    public void setFeatureId(int Feature_Id) { this.Feature_Id = Feature_Id; }

    private int FeatureType_ID;

    public int getFeatureTypeID() { return this.FeatureType_ID; }

    public void setFeatureTypeID(int FeatureType_ID) { this.FeatureType_ID = FeatureType_ID; }

    private String Feature_Short_title;

    public String getFeatureShortTitle() { return this.Feature_Short_title; }

    public void setFeatureShortTitle(String Feature_Short_title) { this.Feature_Short_title = Feature_Short_title; }

    private ArrayList<String> Saving_values;

    public ArrayList<String> getSavingValues() { return this.Saving_values; }

    public void setSavingValues(ArrayList<String> Saving_values) { this.Saving_values = Saving_values; }

    private String Feature_Name;

    public String getFeatureName() { return this.Feature_Name; }

    public void setFeatureName(String Feature_Name) { this.Feature_Name = Feature_Name; }

    private ArrayList<String> flexibility_values;

    public ArrayList<String> getFlexibilityValues() { return this.flexibility_values; }

    public void setFlexibilityValues(ArrayList<String> flexibility_values) { this.flexibility_values = flexibility_values; }

    private String Feature_Value;

    public String getFeatureValue() { return this.Feature_Value; }

    public void setFeatureValue(String Feature_Value) { this.Feature_Value = Feature_Value; }
}

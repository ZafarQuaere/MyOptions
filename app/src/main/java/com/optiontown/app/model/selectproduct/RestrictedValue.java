package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by zafar.imam on 22-02-2017.
 */
public class RestrictedValue implements Serializable{

    private String Feature_List_Id;

    public String getFeature_List_Id() {
        return Feature_List_Id;
    }

    public void setFeature_List_Id(String feature_List_Id) {
        Feature_List_Id = feature_List_Id;
    }

    private String FeatureValue;

    public String getFeatureValue() { return this.FeatureValue; }

    public void setFeatureValue(String FeatureValue) { this.FeatureValue = FeatureValue; }

    private int Feature_Id;

    public int getFeatureId() { return this.Feature_Id; }

    public void setFeatureId(int Feature_Id) { this.Feature_Id = Feature_Id; }

    private String Feature_Name;

    public String getFeatureName() { return this.Feature_Name; }

    public void setFeatureName(String Feature_Name) { this.Feature_Name = Feature_Name; }


}

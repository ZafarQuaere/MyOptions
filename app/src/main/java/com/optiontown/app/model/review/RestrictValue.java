package com.optiontown.app.model.review;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zafar.imam on 22-02-2017.
 */
public class RestrictValue implements Serializable{

    private ArrayList<FeatureDetail> Feature_Details;

    public ArrayList<FeatureDetail> getFeatureDetails() { return this.Feature_Details; }

    public void setFeatureDetails(ArrayList<FeatureDetail> Feature_Details) { this.Feature_Details = Feature_Details; }

    private String featureType_Name;

    public String getFeatureTypeName() { return this.featureType_Name; }

    public void setFeatureTypeName(String featureType_Name) { this.featureType_Name = featureType_Name; }






}

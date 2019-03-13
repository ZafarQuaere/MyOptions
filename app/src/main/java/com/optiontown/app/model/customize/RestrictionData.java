package com.optiontown.app.model.customize;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 27-02-2017.
 */
public class RestrictionData implements Serializable
{
    private String Feature_Color;

    public String getFeature_Color() {
        return Feature_Color;
    }

    public void setFeature_Color(String feature_Color) {
        Feature_Color = feature_Color;
    }

    private int ftId;

    public int getFtId() { return this.ftId; }

    public void setFtId(int ftId) { this.ftId = ftId; }

    private String Feature_ShortTitle;

    public String getFeatureShortTitle() { return this.Feature_ShortTitle; }

    public void setFeatureShortTitle(String Feature_ShortTitle) { this.Feature_ShortTitle = Feature_ShortTitle; }

    private ArrayList<String> Flexibility_IconDetails;

    public ArrayList<String> getFlexibilityIconDetails() { return this.Flexibility_IconDetails; }

    public void setFlexibilityIconDetails(ArrayList<String> Flexibility_IconDetails) { this.Flexibility_IconDetails = Flexibility_IconDetails; }

    private String fm_Name;

    public String getFmName() { return this.fm_Name; }

    public void setFmName(String fm_Name) { this.fm_Name = fm_Name; }

    private int fvId;

    public int getFvId() { return this.fvId; }

    public void setFvId(int fvId) { this.fvId = fvId; }

    private String Feature_Price;

    public String getFeaturePrice() { return this.Feature_Price; }

    public void setFeaturePrice(String Feature_Price) { this.Feature_Price = Feature_Price; }

    private String Feature_CurrSymbol;

    public String getFeatureCurrSymbol() { return this.Feature_CurrSymbol; }

    public void setFeatureCurrSymbol(String Feature_CurrSymbol) { this.Feature_CurrSymbol = Feature_CurrSymbol; }

    private String Feature_TypeSign;

    public String getFeatureTypeSign() { return this.Feature_TypeSign; }

    public void setFeatureTypeSign(String Feature_TypeSign) { this.Feature_TypeSign = Feature_TypeSign; }

    private String Feature_Name;

    public String getFeatureName() { return this.Feature_Name; }

    public void setFeatureName(String Feature_Name) { this.Feature_Name = Feature_Name; }

    private String Feature_PerFlightLabel;

    public String getFeaturePerFlightLabel() { return this.Feature_PerFlightLabel; }

    public void setFeaturePerFlightLabel(String Feature_PerFlightLabel) { this.Feature_PerFlightLabel = Feature_PerFlightLabel; }

    private String Price_Label;

    public String getPriceLabel() { return this.Price_Label; }

    public void setPriceLabel(String Price_Label) { this.Price_Label = Price_Label; }

    private String Feature_Heading_Image;

    public String getFeature_Heading_Image() {
        return Feature_Heading_Image;
    }

    public void setFeature_Heading_Image(String feature_Heading_Image) {
        Feature_Heading_Image = feature_Heading_Image;
    }

    private String Savings_Heading;
    private String Flexibility_Heading;
    private String Feature_LongTitle;
    private int Feature_isSelected;


    public int getFeature_isSelected() {
        return Feature_isSelected;
    }

    public void setFeature_isSelected(int feature_isSelected) {
        Feature_isSelected = feature_isSelected;
    }

    public String getFeature_LongTitle() {
        return Feature_LongTitle;
    }

    public void setFeature_LongTitle(String feature_LongTitle) {
        Feature_LongTitle = feature_LongTitle;
    }

    public String getFlexibility_Heading() {
        return Flexibility_Heading;
    }

    public void setFlexibility_Heading(String flexibility_Heading) {
        Flexibility_Heading = flexibility_Heading;
    }

    public String getSavings_Heading() {
        return Savings_Heading;
    }

    public void setSavings_Heading(String savings_Heading) {
        Savings_Heading = savings_Heading;
    }
}

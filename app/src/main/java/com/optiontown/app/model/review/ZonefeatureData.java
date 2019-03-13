package com.optiontown.app.model.review;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 22-06-2017.
 */
public class ZonefeatureData implements Serializable
{
    private String Long_description;

    private String Short_Decription;

    public String getLong_description() {
        return Long_description;
    }

    public void setLong_description(String long_description) {
        Long_description = long_description;
    }

    public String getShort_Decription() {
        return Short_Decription;
    }

    public void setShort_Decription(String short_Decription) {
        Short_Decription = short_Decription;
    }

    private String UniDirectionalArrow;

    public String getUniDirectionalArrow() { return this.UniDirectionalArrow; }

    public void setUniDirectionalArrow(String UniDirectionalArrow) { this.UniDirectionalArrow = UniDirectionalArrow; }

    private String LABL_Tiny_Label;

    public String getLABLTinyLabel() { return this.LABL_Tiny_Label; }

    public void setLABLTinyLabel(String LABL_Tiny_Label) { this.LABL_Tiny_Label = LABL_Tiny_Label; }

    private String LABL_Detail_Label;

    public String getLABLDetailLabel() { return this.LABL_Detail_Label; }

    public void setLABLDetailLabel(String LABL_Detail_Label) { this.LABL_Detail_Label = LABL_Detail_Label; }

    private String View_Less_Label;

    public String getViewLessLabel() { return this.View_Less_Label; }

    public void setViewLessLabel(String View_Less_Label) { this.View_Less_Label = View_Less_Label; }

    private String UniDirectionalTitle;

    public String getUniDirectionalTitle() { return this.UniDirectionalTitle; }

    public void setUniDirectionalTitle(String UniDirectionalTitle) { this.UniDirectionalTitle = UniDirectionalTitle; }

    private String View_All_Routes_Label;

    public String getViewAllRoutesLabel() { return this.View_All_Routes_Label; }

    public void setViewAllRoutesLabel(String View_All_Routes_Label) { this.View_All_Routes_Label = View_All_Routes_Label; }

    private String Destination_label;

    public String getDestinationLabel() { return this.Destination_label; }

    public void setDestinationLabel(String Destination_label) { this.Destination_label = Destination_label; }

    private int IsZoneAirportNameDisplay;

    public int getIsZoneAirportNameDisplay() { return this.IsZoneAirportNameDisplay; }

    public void setIsZoneAirportNameDisplay(int IsZoneAirportNameDisplay) { this.IsZoneAirportNameDisplay = IsZoneAirportNameDisplay; }

    private int MaxStopsCount;

    public int getMaxStopsCount() { return this.MaxStopsCount; }

    public void setMaxStopsCount(int MaxStopsCount) { this.MaxStopsCount = MaxStopsCount; }

    private String LABL_Summary_Label;

    public String getLABLSummaryLabel() { return this.LABL_Summary_Label; }

    public void setLABLSummaryLabel(String LABL_Summary_Label) { this.LABL_Summary_Label = LABL_Summary_Label; }

    private String Origin_label;

    public String getOriginLabel() { return this.Origin_label; }

    public void setOriginLabel(String Origin_label) { this.Origin_label = Origin_label; }

    private ArrayList<DepArrvZoneArray> DepArrvZoneArray;

    public ArrayList<DepArrvZoneArray> getDepArrvZoneArray() { return this.DepArrvZoneArray; }

    public void setDepArrvZoneArray(ArrayList<DepArrvZoneArray> DepArrvZoneArray) { this.DepArrvZoneArray = DepArrvZoneArray; }

    private int MinStopsCount;

    public int getMinStopsCount() { return this.MinStopsCount; }

    public void setMinStopsCount(int MinStopsCount) { this.MinStopsCount = MinStopsCount; }

    private int IsBidirectional;

    public int getIsBidirectional() { return this.IsBidirectional; }

    public void setIsBidirectional(int IsBidirectional) { this.IsBidirectional = IsBidirectional; }

    private String Stops_label;

    public String getStopsLabel() { return this.Stops_label; }

    public void setStopsLabel(String Stops_label) { this.Stops_label = Stops_label; }

    private String BiDirectionalArrow;

    public String getBiDirectionalArrow() { return this.BiDirectionalArrow; }

    public void setBiDirectionalArrow(String BiDirectionalArrow) { this.BiDirectionalArrow = BiDirectionalArrow; }

    private String Non_Stop_Label;

    public String getNonStopLabel() { return this.Non_Stop_Label; }

    public void setNonStopLabel(String Non_Stop_Label) { this.Non_Stop_Label = Non_Stop_Label; }
}

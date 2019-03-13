package com.optiontown.app.model.review;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zafar.imam on 22-02-2017.
 */
public class FeatureDetail implements Serializable{

    private String travel_period;

    public String getTravelPeriod() { return this.travel_period; }

    public void setTravelPeriod(String travel_period) { this.travel_period = travel_period; }

    private String view_all_label;

    public String getViewAllLabel() { return this.view_all_label; }

    public void setViewAllLabel(String view_all_label) { this.view_all_label = view_all_label; }

    private ArrayList<OdListWithDate> OdListWithDates;

    public ArrayList<OdListWithDate> getOdListWithDates() { return this.OdListWithDates; }

    public void setOdListWithDates(ArrayList<OdListWithDate> OdListWithDates) { this.OdListWithDates = OdListWithDates; }

    private String routes_all_lavel;

    public String getRoutesAllLavel() { return this.routes_all_lavel; }

    public void setRoutesAllLavel(String routes_all_lavel) { this.routes_all_lavel = routes_all_lavel; }

    private String routes_lavel;

    public String getRoutesLavel() { return this.routes_lavel; }

    public void setRoutesLavel(String routes_lavel) { this.routes_lavel = routes_lavel; }

    private String view_less_label;

    public String getViewLessLabel() { return this.view_less_label; }

    public void setViewLessLabel(String view_less_label) { this.view_less_label = view_less_label; }

    private String Fv_Shrt_Name;

    public String getFvShrtName() { return this.Fv_Shrt_Name; }

    public void setFvShrtName(String Fv_Shrt_Name) { this.Fv_Shrt_Name = Fv_Shrt_Name; }

    private String Fv_Shrt_Title;

    public String getFvShrtTitle() { return this.Fv_Shrt_Title; }

    public void setFvShrtTitle(String Fv_Shrt_Title) { this.Fv_Shrt_Title = Fv_Shrt_Title; }

}

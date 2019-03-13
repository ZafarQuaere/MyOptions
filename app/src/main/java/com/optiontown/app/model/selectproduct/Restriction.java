package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zafar.imam on 22-02-2017.
 */
public class Restriction implements Serializable{

    private String FName;

    public String getFName() { return this.FName; }

    public void setFName(String FName) { this.FName = FName; }

    private ArrayList<Value> Values;

    public ArrayList<Value> getValues() { return this.Values; }

    public void setValues(ArrayList<Value> Values) { this.Values = Values; }

    private int FID;

    public int getFID() { return this.FID; }

    public void setFID(int FID) { this.FID = FID; }

    private String Help_Label;

    public String getHelpLabel() { return this.Help_Label; }

    public void setHelpLabel(String Help_Label) { this.Help_Label = Help_Label; }

    private String Long_Title;

    public String getLongTitle() { return this.Long_Title; }

    public void setLongTitle(String Long_Title) { this.Long_Title = Long_Title; }

    private String Heading_Label;

    public String getHeadingLabel() { return this.Heading_Label; }

    public void setHeadingLabel(String Heading_Label) { this.Heading_Label = Heading_Label; }

    private String Done_Label;

    public String getDoneLabel() { return this.Done_Label; }

    public void setDoneLabel(String Done_Label) { this.Done_Label = Done_Label; }

    private String Flexibility_Heading;

    public String getFlexibilityHeading() { return this.Flexibility_Heading; }

    public void setFlexibilityHeading(String Flexibility_Heading) { this.Flexibility_Heading = Flexibility_Heading; }

    private String Help_Image;

    public String getHelpImage() { return this.Help_Image; }

    public void setHelpImage(String Help_Image) { this.Help_Image = Help_Image; }

}

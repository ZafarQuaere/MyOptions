package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class FlexibilitySetList implements Serializable
{
    private String Title;
    private String Help;

    public String getHelp() { return this.Help; }

    public void setHelp(String Help) { this.Help = Help; }

    private ArrayList<NotflexibilitySetList> notflexibilitySetList;

    public ArrayList<NotflexibilitySetList> getNotflexibilitySetList() { return this.notflexibilitySetList; }

    public void setNotflexibilitySetList(ArrayList<NotflexibilitySetList> notflexibilitySetList) { this.notflexibilitySetList = notflexibilitySetList; }

    private ArrayList<FlexibilitySetList> flexibilitySetList;

    public ArrayList<FlexibilitySetList> getFlexibilitySetList() { return this.flexibilitySetList; }

    public void setFlexibilitySetList(ArrayList<FlexibilitySetList> flexibilitySetList) { this.flexibilitySetList = flexibilitySetList; }

    private int FlexibilityId;

    public int getFlexibilityId() { return this.FlexibilityId; }

    public void setFlexibilityId(int FlexibilityId) { this.FlexibilityId = FlexibilityId; }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    private String Label1;
    private String MainLabel;
    private int FlexibilityRangeId;

    public String getLabel1() {
        return Label1;
    }

    public void setLabel1(String label1) {
        Label1 = label1;
    }

    public String getMainLabel() {
        return MainLabel;
    }

    public void setMainLabel(String mainLabel) {
        MainLabel = mainLabel;
    }

    public int getFlexibilityRangeId() {
        return FlexibilityRangeId;
    }

    public void setFlexibilityRangeId(int flexibilityRangeId) {
        FlexibilityRangeId = flexibilityRangeId;
    }
}

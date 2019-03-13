package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class Validity implements Serializable
{
    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    private String Help;

    public String getHelp() { return this.Help; }

    public void setHelp(String Help) { this.Help = Help; }

    private int validtyId;

    public int getValidtyId() { return this.validtyId; }

    public void setValidtyId(int validtyId) { this.validtyId = validtyId; }

    private String selectValidityBegin;

    public String getSelectValidityBegin() { return this.selectValidityBegin; }

    public void setSelectValidityBegin(String selectValidityBegin) { this.selectValidityBegin = selectValidityBegin; }

    private ArrayList<VList> VList;

    public ArrayList<VList> getVList() { return this.VList; }

    public void setVList(ArrayList<VList> VList) { this.VList = VList; }

    private String TravelValidLabel;

    public String getTravelValidLabel() { return this.TravelValidLabel; }

    public void setTravelValidLabel(String TravelValidLabel) { this.TravelValidLabel = TravelValidLabel; }
}

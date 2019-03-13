package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class AdvanceBookingList implements Serializable
{
    private String Title;
    private String Help;

    public String getHelp() { return this.Help; }

    public void setHelp(String Help) { this.Help = Help; }

    private ArrayList<AdvanceBookingList2> AdvanceBookingList2;

    public ArrayList<AdvanceBookingList2> getAdvanceBookingList2() { return this.AdvanceBookingList2; }

    public void setAdvanceBookingList2(ArrayList<AdvanceBookingList2> AdvanceBookingList2) { this.AdvanceBookingList2 = AdvanceBookingList2; }

    private ArrayList<AdvanceBookingList1> AdvanceBookingList1;

    public ArrayList<AdvanceBookingList1> getAdvanceBookingList1() { return this.AdvanceBookingList1; }

    public void setAdvanceBookingList1(ArrayList<AdvanceBookingList1> AdvanceBookingList1) { this.AdvanceBookingList1 = AdvanceBookingList1; }

    private int AdvanceBookingId;

    public int getAdvanceBookingId() { return this.AdvanceBookingId; }

    public void setAdvanceBookingId(int AdvanceBookingId) { this.AdvanceBookingId = AdvanceBookingId; }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }
}
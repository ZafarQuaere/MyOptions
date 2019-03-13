package com.optiontown.app.model.redeem.mmb;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 22/11/2016.
 */
public class MapOfPassAndConfirmedBooking implements Serializable {

    private String passId;

    public String getPassId() { return this.passId; }

    public void setPassId(String passId) { this.passId = passId; }

    private ArrayList<PassBookingList> passBookingList;

    public ArrayList<PassBookingList> getPassBookingList() { return this.passBookingList; }

    public void setPassBookingList(ArrayList<PassBookingList> passBookingList) { this.passBookingList = passBookingList; }

    private String passLabel;

    public String getPassLabel() { return this.passLabel; }

    public void setPassLabel(String passLabel) { this.passLabel = passLabel; }

}

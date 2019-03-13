package com.optiontown.app.model.seatview;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 21/08/2017.
 */

public class SeatMapRootData implements Serializable{

    private String Grand_Total_Label;
    private String Seat_Summary_Label;

    public String getGrand_Total_Label() {
        return Grand_Total_Label;
    }

    public void setGrand_Total_Label(String grand_Total_Label) {
        Grand_Total_Label = grand_Total_Label;
    }

    public String getSeat_Summary_Label() {
        return Seat_Summary_Label;
    }

    public void setSeat_Summary_Label(String seat_Summary_Label) {
        Seat_Summary_Label = seat_Summary_Label;
    }

    public String getOutbound_Journey_Label() {
        return Outbound_Journey_Label;
    }

    public void setOutbound_Journey_Label(String outbound_Journey_Label) {
        Outbound_Journey_Label = outbound_Journey_Label;
    }

    private String Outbound_Journey_Label;


    private String pantryImage;

    public String getPantryImage() { return this.pantryImage; }

    public void setPantryImage(String pantryImage) { this.pantryImage = pantryImage; }

    private ArrayList<Seatdetailarr> seatdetailarr;

    public ArrayList<Seatdetailarr> getSeatdetailarr() { return this.seatdetailarr; }

    public void setSeatdetailarr(ArrayList<Seatdetailarr> seatdetailarr) { this.seatdetailarr = seatdetailarr; }

    private String AircraftName;

    public String getAircraftName() { return this.AircraftName; }

    public void setAircraftName(String AircraftName) { this.AircraftName = AircraftName; }

    private int seatType;

    public int getSeatType() { return this.seatType; }

    public void setSeatType(int seatType) { this.seatType = seatType; }

    private ArrayList<SegmentArray> segmentArray;

    public ArrayList<SegmentArray> getSegmentArray() { return this.segmentArray; }

    public void setSegmentArray(ArrayList<SegmentArray> segmentArray) { this.segmentArray = segmentArray; }

    private ArrayList<SeatMarkarr> seatMarkarr;

    public ArrayList<SeatMarkarr> getSeatMarkarr() { return this.seatMarkarr; }

    public void setSeatMarkarr(ArrayList<SeatMarkarr> seatMarkarr) { this.seatMarkarr = seatMarkarr; }

    private String selectPassengerLbl;

    public String getSelectPassengerLbl() { return this.selectPassengerLbl; }

    public void setSelectPassengerLbl(String selectPassengerLbl) { this.selectPassengerLbl = selectPassengerLbl; }

    private String backlabel;

    public String getBacklabel() { return this.backlabel; }

    public void setBacklabel(String backlabel) { this.backlabel = backlabel; }

    private String lavatoryImage;

    public String getLavatoryImage() { return this.lavatoryImage; }

    public void setLavatoryImage(String lavatoryImage) { this.lavatoryImage = lavatoryImage; }

    private ArrayList<PassengerArray> passengerArray;

    public ArrayList<PassengerArray> getPassengerArray() { return this.passengerArray; }

    public void setPassengerArray(ArrayList<PassengerArray> passengerArray) { this.passengerArray = passengerArray; }
}

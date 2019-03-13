package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 09-09-2016.
 */
public class Itinerarry implements Serializable
{
    public boolean isFromMyBooking() {
        return isFromMyBooking;
    }

    public void setFromMyBooking(boolean fromMyBooking) {
        isFromMyBooking = fromMyBooking;
    }

    private boolean isFromMyBooking;

    private String Per_Person;

    public String getPer_Person() {
        return Per_Person;
    }

    public void setPer_Person(String per_Person) {
        Per_Person = per_Person;
    }

    private String DepartArr;

    private String TravelDate;

    private String passengerCabinTripType;

    public String getDepartArr() {
        return DepartArr;
    }

    public void setDepartArr(String departArr) {
        DepartArr = departArr;
    }

    public String getTravelDate() {
        return TravelDate;
    }

    public void setTravelDate(String travelDate) {
        TravelDate = travelDate;
    }

    public String getPassengerCabinTripType() {
        return passengerCabinTripType;
    }

    public void setPassengerCabinTripType(String passengerCabinTripType) {
        this.passengerCabinTripType = passengerCabinTripType;
    }

    private ArrayList<Segment> Segments;

    public ArrayList<Segment> getSegments() { return this.Segments; }

    public void setSegments(ArrayList<Segment> Segments) { this.Segments = Segments; }


}

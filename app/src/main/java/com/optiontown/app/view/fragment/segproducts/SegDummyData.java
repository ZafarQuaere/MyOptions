package com.optiontown.app.view.fragment.segproducts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-08-2017.
 */
public class SegDummyData implements Serializable
{
    private String date;
    private ArrayList<String> flightDetails;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(ArrayList<String> flightDetails) {
        this.flightDetails = flightDetails;
    }
}

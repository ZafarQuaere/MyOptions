package com.optiontown.app.model.redeem.mmb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.optiontown.app.model.redeem.Itinerarry;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 28-11-2016.
 */
public class MMBSelectFlightData implements Serializable
{
    private ArrayList<Itinerarry> Itinerarry;

    public ArrayList<Itinerarry> getItinerarry() { return this.Itinerarry; }

    public void setItinerarry(ArrayList<Itinerarry> Itinerarry) { this.Itinerarry = Itinerarry; }

    @SerializedName("PassengerNamelist")
    @Expose
    private ArrayList<String> passengerNamelist = new ArrayList<String>();
    public ArrayList<String> getPassengerNamelist() {
        return passengerNamelist;
    }

    public void setPassengerNamelist(ArrayList<String> passengerNamelist) {
        this.passengerNamelist = passengerNamelist;
    }

    @SerializedName("ManageMybooking")
    @Expose
    private ManageMybooking manageMybooking;
    public ManageMybooking getManageMybooking() {
        return manageMybooking;
    }

    public void setManageMybooking(ManageMybooking manageMybooking) {
        this.manageMybooking = manageMybooking;
    }
    public String getTop_Message() {
        return Top_Message;
    }

    public void setTop_Message(String top_Message) {
        Top_Message = top_Message;
    }

    private String Top_Message;
}

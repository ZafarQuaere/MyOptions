package com.optiontown.app.model.redeem.mmb;

import com.optiontown.app.model.redeem.Itinerarry;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 29-11-2016.
 */
public class MMBChangeFlightSelectNewData implements Serializable
{
    private String Message;
    private int Error;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getError() {
        return Error;
    }

    public void setError(int error) {
        Error = error;
    }

    private ArrayList<com.optiontown.app.model.redeem.Itinerarry> Itinerarry;

    public ArrayList<Itinerarry> getItinerarry() { return this.Itinerarry; }

    public void setItinerarry(ArrayList<Itinerarry> Itinerarry) { this.Itinerarry = Itinerarry; }

    private String Passenger_Label;
    private String LABL_Airline_Label;
    private String Airline_Name_Label;
    private String Booking_Ref_Pnr;
    private String LABL_Continue_Button_Label;
    private String Booking_Ref_Label;
    private String Change_Flight_Heading_Label;
    private String Current_Flight_Label;

    public String getTop_Message() {
        return Top_Message;
    }

    public void setTop_Message(String top_Message) {
        Top_Message = top_Message;
    }

    private String Top_Message;

    public ArrayList<String> getPassengerNamelist() {
        return PassengerNamelist;
    }

    public void setPassengerNamelist(ArrayList<String> passengerNamelist) {
        PassengerNamelist = passengerNamelist;
    }

    private ArrayList<String> PassengerNamelist;

    public String getPassenger_Label() {
        return Passenger_Label;
    }

    public void setPassenger_Label(String passenger_Label) {
        Passenger_Label = passenger_Label;
    }

    public String getLABL_Airline_Label() {
        return LABL_Airline_Label;
    }

    public void setLABL_Airline_Label(String LABL_Airline_Label) {
        this.LABL_Airline_Label = LABL_Airline_Label;
    }

    public String getAirline_Name_Label() {
        return Airline_Name_Label;
    }

    public void setAirline_Name_Label(String airline_Name_Label) {
        Airline_Name_Label = airline_Name_Label;
    }

    public String getBooking_Ref_Pnr() {
        return Booking_Ref_Pnr;
    }

    public void setBooking_Ref_Pnr(String booking_Ref_Pnr) {
        Booking_Ref_Pnr = booking_Ref_Pnr;
    }

    public String getLABL_Continue_Button_Label() {
        return LABL_Continue_Button_Label;
    }

    public void setLABL_Continue_Button_Label(String LABL_Continue_Button_Label) {
        this.LABL_Continue_Button_Label = LABL_Continue_Button_Label;
    }

    public String getBooking_Ref_Label() {
        return Booking_Ref_Label;
    }

    public void setBooking_Ref_Label(String booking_Ref_Label) {
        Booking_Ref_Label = booking_Ref_Label;
    }

    public String getChange_Flight_Heading_Label() {
        return Change_Flight_Heading_Label;
    }

    public void setChange_Flight_Heading_Label(String change_Flight_Heading_Label) {
        Change_Flight_Heading_Label = change_Flight_Heading_Label;
    }

    public String getCurrent_Flight_Label() {
        return Current_Flight_Label;
    }

    public void setCurrent_Flight_Label(String current_Flight_Label) {
        Current_Flight_Label = current_Flight_Label;
    }
}

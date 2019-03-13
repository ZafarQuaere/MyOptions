package com.optiontown.app.model.redeem.mmb;

import com.optiontown.app.model.redeem.Itinerarry;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 29-11-2016.
 */
public class MMBChangeFlightCurrentData implements Serializable
{
    private String Advance_Booking_MMB_Desc_Label;
    private int Booking_Days_Value;
    private String Advance_Booking_Label;

    private int Max_Range_Days;
    private String Current_Flight_Label;
    private String Passenger_Label;
    private String LABL_Airline_Label;
    private String Airline_Name_Label;
    private String Continue_Button_Label;
    private String Booking_Ref_Pnr;
    private String New_Depart_Date_Label;
    private String Booking_Ref_Label;
    private String New_Dept_Date_Label;
    private String Change_Flight_Heading_Label;

    private String Error_Message;
    private ArrayList<String> Passengers_List;

    public String getError_Message() {
        return Error_Message;
    }

    public void setError_Message(String error_Message) {
        Error_Message = error_Message;
    }
    public ArrayList<String> getPassengers_List() {
        return Passengers_List;
    }

    public void setPassengers_List(ArrayList<String> passengers_List) {
        Passengers_List = passengers_List;
    }



    public String getCurrent_Flight_Label() {
        return Current_Flight_Label;
    }

    public void setCurrent_Flight_Label(String current_Flight_Label) {
        Current_Flight_Label = current_Flight_Label;
    }

    public String getPassenger_Label() {
        return Passenger_Label;
    }

    public void setPassenger_Label(String passenger_Label) {
        Passenger_Label = passenger_Label;
    }

    public String getAirline_Name_Label() {
        return Airline_Name_Label;
    }

    public void setAirline_Name_Label(String airline_Name_Label) {
        Airline_Name_Label = airline_Name_Label;
    }

    public String getContinue_Button_Label() {
        return Continue_Button_Label;
    }

    public void setContinue_Button_Label(String continue_Button_Label) {
        Continue_Button_Label = continue_Button_Label;
    }

    public String getNew_Depart_Date_Label() {
        return New_Depart_Date_Label;
    }

    public void setNew_Depart_Date_Label(String new_Depart_Date_Label) {
        New_Depart_Date_Label = new_Depart_Date_Label;
    }

    public String getLABL_Airline_Label() {
        return LABL_Airline_Label;
    }

    public void setLABL_Airline_Label(String LABL_Airline_Label) {
        this.LABL_Airline_Label = LABL_Airline_Label;
    }

    public String getBooking_Ref_Pnr() {
        return Booking_Ref_Pnr;
    }

    public void setBooking_Ref_Pnr(String booking_Ref_Pnr) {
        Booking_Ref_Pnr = booking_Ref_Pnr;
    }

    public String getBooking_Ref_Label() {
        return Booking_Ref_Label;
    }

    public void setBooking_Ref_Label(String booking_Ref_Label) {
        Booking_Ref_Label = booking_Ref_Label;
    }

    public String getNew_Dept_Date_Label() {
        return New_Dept_Date_Label;
    }

    public void setNew_Dept_Date_Label(String new_Dept_Date_Label) {
        New_Dept_Date_Label = new_Dept_Date_Label;
    }

    public String getChange_Flight_Heading_Label() {
        return Change_Flight_Heading_Label;
    }

    public void setChange_Flight_Heading_Label(String change_Flight_Heading_Label) {
        Change_Flight_Heading_Label = change_Flight_Heading_Label;
    }

    public int getMax_Range_Days() {
        return Max_Range_Days;
    }

    public void setMax_Range_Days(int max_Range_Days) {
        Max_Range_Days = max_Range_Days;
    }

    public String getAdvance_Booking_MMB_Desc_Label() {
        return Advance_Booking_MMB_Desc_Label;
    }

    public void setAdvance_Booking_MMB_Desc_Label(String advance_Booking_MMB_Desc_Label) {
        Advance_Booking_MMB_Desc_Label = advance_Booking_MMB_Desc_Label;
    }

    public int getBooking_Days_Value() {
        return Booking_Days_Value;
    }

    public void setBooking_Days_Value(int booking_Days_Value) {
        Booking_Days_Value = booking_Days_Value;
    }

    public String getAdvance_Booking_Label() {
        return Advance_Booking_Label;
    }

    public void setAdvance_Booking_Label(String advance_Booking_Label) {
        Advance_Booking_Label = advance_Booking_Label;
    }

    private ArrayList<com.optiontown.app.model.redeem.Itinerarry> Itinerarry;

    public ArrayList<Itinerarry> getItinerarry() { return this.Itinerarry; }

    public void setItinerarry(ArrayList<Itinerarry> Itinerarry) { this.Itinerarry = Itinerarry; }


}

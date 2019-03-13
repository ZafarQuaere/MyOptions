package com.optiontown.app.model.redeem.mmb;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 22/11/2016.
 */
public class MMBData implements Serializable {

    private String Change_Flight_Heading_Label;

    public String getChangeFlightHeadingLabel() { return this.Change_Flight_Heading_Label; }

    public void setChangeFlightHeadingLabel(String Change_Flight_Heading_Label) { this.Change_Flight_Heading_Label = Change_Flight_Heading_Label; }

    private String Back_Button_Label;

    public String getBackButtonLabel() { return this.Back_Button_Label; }

    public void setBackButtonLabel(String Back_Button_Label) { this.Back_Button_Label = Back_Button_Label; }

    private ArrayList<MapOfPassAndConfirmedBooking> MapOfPassAndConfirmedBooking;

    public ArrayList<MapOfPassAndConfirmedBooking> getMapOfPassAndConfirmedBooking() { return this.MapOfPassAndConfirmedBooking; }

    public void setMapOfPassAndConfirmedBooking(ArrayList<MapOfPassAndConfirmedBooking> MapOfPassAndConfirmedBooking) { this.MapOfPassAndConfirmedBooking = MapOfPassAndConfirmedBooking; }

    private String Change_Flight_Subheading_Label;

    public String getChangeFlightSubheadingLabel() { return this.Change_Flight_Subheading_Label; }

    public void setChangeFlightSubheadingLabel(String Change_Flight_Subheading_Label) { this.Change_Flight_Subheading_Label = Change_Flight_Subheading_Label; }

    private String Manage_My_Booking_Heading_Label;

    public String getManageMyBookingHeadingLabel() { return this.Manage_My_Booking_Heading_Label; }

    public void setManageMyBookingHeadingLabel(String Manage_My_Booking_Heading_Label) { this.Manage_My_Booking_Heading_Label = Manage_My_Booking_Heading_Label; }

    private String Continue_Button_Label;

    public String getContinueButtonLabel() { return this.Continue_Button_Label; }

    public void setContinueButtonLabel(String Continue_Button_Label) { this.Continue_Button_Label = Continue_Button_Label; }

    public String getBooking_Label() {
        return Booking_Label;
    }

    public void setBooking_Label(String booking_Label) {
        Booking_Label = booking_Label;
    }

    private String Booking_Label;

    public String getPassId() {
        return passId;
    }

    public void setPassId(String passId) {
        this.passId = passId;
    }

    private String passId;

}

package com.optiontown.app.model.redeem.mmb;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 22/11/2016.
 */
public class PassBookingList implements Serializable {

    private String Select;
    private String Booking_Ref_Num;
    private String Flight_PNR_Id;
    private String Flight_PNR_Label;



    private String OCNNumber;

    public String getFlight_PNR_Id() {
        return Flight_PNR_Id;
    }

    public void setFlight_PNR_Id(String flight_PNR_Id) {
        Flight_PNR_Id = flight_PNR_Id;
    }

    public String getBooking_Ref_Num() {
        return Booking_Ref_Num;
    }

    public void setBooking_Ref_Num(String booking_Ref_Num) {
        Booking_Ref_Num = booking_Ref_Num;
    }

    public String getFlight_PNR_Label() {
        return Flight_PNR_Label;
    }

    public void setFlight_PNR_Label(String flight_PNR_Label) {
        Flight_PNR_Label = flight_PNR_Label;
    }

    public String getSelect() { return this.Select; }

    public void setSelect(String Select) { this.Select = Select; }

    public void setOCNNumber(String OCNNumber) {
        this.OCNNumber = OCNNumber;
    }
    public String getOCNNumber() {
        return OCNNumber;
    }
}

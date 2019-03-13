package com.optiontown.app.model.redeem.mmb;

import com.optiontown.app.model.redeem.Itinerarry;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 01-12-2016.
 */
public class MMBChangeFlightReviewData implements Serializable
{
    private String LABL_Process_Status_Bar_FPO_Confirm_Label;

    public String getLABL_Process_Status_Bar_FPO_Confirm_Label() {
        return LABL_Process_Status_Bar_FPO_Confirm_Label;
    }

    public void setLABL_Process_Status_Bar_FPO_Confirm_Label(String LABL_Process_Status_Bar_FPO_Confirm_Label) {
        this.LABL_Process_Status_Bar_FPO_Confirm_Label = LABL_Process_Status_Bar_FPO_Confirm_Label;
    }

    private boolean isDisplayPrice;

    public boolean isDisplayPrice() {
        return isDisplayPrice;
    }

    public void setDisplayPrice(boolean displayPrice) {
        isDisplayPrice = displayPrice;
    }

    private ArrayList<Itinerarry> Itinerarry_Current;
    private ArrayList<Itinerarry> Itinerarry_New;

    public ArrayList<Itinerarry> getItinerarry_Current() {
        return Itinerarry_Current;
    }

    public void setItinerarry_Current(ArrayList<Itinerarry> itinerarry_Current) {
        Itinerarry_Current = itinerarry_Current;
    }

    public ArrayList<Itinerarry> getItinerarry_New() {
        return Itinerarry_New;
    }

    public void setItinerarry_New(ArrayList<Itinerarry> itinerarry_New) {
        Itinerarry_New = itinerarry_New;
    }

    private String LABL_Pay_Amount_Now_Label;

    public String getLABLPayAmountNowLabel() { return this.LABL_Pay_Amount_Now_Label; }

    public void setLABLPayAmountNowLabel(String LABL_Pay_Amount_Now_Label) { this.LABL_Pay_Amount_Now_Label = LABL_Pay_Amount_Now_Label; }

    private String LABL_MMB_Current_Flight_Label;

    public String getLABLMMBCurrentFlightLabel() { return this.LABL_MMB_Current_Flight_Label; }

    public void setLABLMMBCurrentFlightLabel(String LABL_MMB_Current_Flight_Label) { this.LABL_MMB_Current_Flight_Label = LABL_MMB_Current_Flight_Label; }

    private String Passenger_Label;

    public String getPassengerLabel() { return this.Passenger_Label; }

    public void setPassengerLabel(String Passenger_Label) { this.Passenger_Label = Passenger_Label; }

    private int passNewPrice_Farediff;

    public int getPassNewPriceFarediff() { return this.passNewPrice_Farediff; }

    public void setPassNewPriceFarediff(int passNewPrice_Farediff) { this.passNewPrice_Farediff = passNewPrice_Farediff; }

    private String LABL_Airline_Label;

    public String getLABLAirlineLabel() { return this.LABL_Airline_Label; }

    public void setLABLAirlineLabel(String LABL_Airline_Label) { this.LABL_Airline_Label = LABL_Airline_Label; }

    private ArrayList<String> Passengers_List;

    public ArrayList<String> getPassengersList() { return this.Passengers_List; }

    public void setPassengersList(ArrayList<String> Passengers_List) { this.Passengers_List = Passengers_List; }

    private String LABL_Pass_Change_Fee_Label;

    public String getLABLPassChangeFeeLabel() { return this.LABL_Pass_Change_Fee_Label; }

    public void setLABLPassChangeFeeLabel(String LABL_Pass_Change_Fee_Label) { this.LABL_Pass_Change_Fee_Label = LABL_Pass_Change_Fee_Label; }

    private String ERR_Back_Button_Label;

    public String getERRBackButtonLabel() { return this.ERR_Back_Button_Label; }

    public void setERRBackButtonLabel(String ERR_Back_Button_Label) { this.ERR_Back_Button_Label = ERR_Back_Button_Label; }

    private String Change_Flight_Heading_Label;

    public String getChangeFlightHeadingLabel() { return this.Change_Flight_Heading_Label; }

    public void setChangeFlightHeadingLabel(String Change_Flight_Heading_Label) { this.Change_Flight_Heading_Label = Change_Flight_Heading_Label; }

    private String transaction_CurrencyCode;

    public String getTransactionCurrencyCode() { return this.transaction_CurrencyCode; }

    public void setTransactionCurrencyCode(String transaction_CurrencyCode) { this.transaction_CurrencyCode = transaction_CurrencyCode; }

    private String LABL_MMB_New_Flight_Label;

    public String getLABLMMBNewFlightLabel() { return this.LABL_MMB_New_Flight_Label; }

    public void setLABLMMBNewFlightLabel(String LABL_MMB_New_Flight_Label) { this.LABL_MMB_New_Flight_Label = LABL_MMB_New_Flight_Label; }

    private String LABL_MMB_Change_Flight_Heading_Label;

    public String getLABLMMBChangeFlightHeadingLabel() { return this.LABL_MMB_Change_Flight_Heading_Label; }

    public void setLABLMMBChangeFlightHeadingLabel(String LABL_MMB_Change_Flight_Heading_Label) { this.LABL_MMB_Change_Flight_Heading_Label = LABL_MMB_Change_Flight_Heading_Label; }

    private String LABL_Review_New_Flight_Label;

    public String getLABLReviewNewFlightLabel() { return this.LABL_Review_New_Flight_Label; }

    public void setLABLReviewNewFlightLabel(String LABL_Review_New_Flight_Label) { this.LABL_Review_New_Flight_Label = LABL_Review_New_Flight_Label; }

    private String LABL_Total_Label;

    public String getLABLTotalLabel() { return this.LABL_Total_Label; }

    public void setLABLTotalLabel(String LABL_Total_Label) { this.LABL_Total_Label = LABL_Total_Label; }



    private String Airline_Name_Label;

    public String getAirlineNameLabel() { return this.Airline_Name_Label; }

    public void setAirlineNameLabel(String Airline_Name_Label) { this.Airline_Name_Label = Airline_Name_Label; }

    private String Error_Message;

    public String getErrorMessage() { return this.Error_Message; }

    public void setErrorMessage(String Error_Message) { this.Error_Message = Error_Message; }

    private String LABL_MMO_Fare_Difference_Label;

    public String getLABLMMOFareDifferenceLabel() { return this.LABL_MMO_Fare_Difference_Label; }

    public void setLABLMMOFareDifferenceLabel(String LABL_MMO_Fare_Difference_Label) { this.LABL_MMO_Fare_Difference_Label = LABL_MMO_Fare_Difference_Label; }

    private String LABL_Proceed_To_Payment_Label;

    public String getLABLProceedToPaymentLabel() { return this.LABL_Proceed_To_Payment_Label; }

    public void setLABLProceedToPaymentLabel(String LABL_Proceed_To_Payment_Label) { this.LABL_Proceed_To_Payment_Label = LABL_Proceed_To_Payment_Label; }

    private int Max_Range_Days;

    public int getMaxRangeDays() { return this.Max_Range_Days; }

    public void setMaxRangeDays(int Max_Range_Days) { this.Max_Range_Days = Max_Range_Days; }

    private int passNewPrice_Feevalue;

    public int getPassNewPriceFeevalue() { return this.passNewPrice_Feevalue; }

    public void setPassNewPriceFeevalue(int passNewPrice_Feevalue) { this.passNewPrice_Feevalue = passNewPrice_Feevalue; }

    private String Booking_Ref_Pnr;

    public String getBookingRefPnr() { return this.Booking_Ref_Pnr; }

    public void setBookingRefPnr(String Booking_Ref_Pnr) { this.Booking_Ref_Pnr = Booking_Ref_Pnr; }

    private String Booking_Ref_Label;

    public String getBookingRefLabel() { return this.Booking_Ref_Label; }

    public void setBookingRefLabel(String Booking_Ref_Label) { this.Booking_Ref_Label = Booking_Ref_Label; }

    private String Current_Flight_Label;

    public String getCurrentFlightLabel() { return this.Current_Flight_Label; }

    public void setCurrentFlightLabel(String Current_Flight_Label) { this.Current_Flight_Label = Current_Flight_Label; }

    private int Totalamount;

    public int getTotalamount() { return this.Totalamount; }

    public void setTotalamount(int Totalamount) { this.Totalamount = Totalamount; }

}

package com.optiontown.app.model.redeem;

import com.optiontown.app.model.review.RestrictValue;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 03-09-2016.
 */
public class PassFullView implements Serializable
{

    private String Validity_Start_Date;

    public String getValidityStartDate() { return this.Validity_Start_Date; }

    public void setValidityStartDate(String Validity_Start_Date) { this.Validity_Start_Date = Validity_Start_Date; }

    private String Travel_Zone_Label;

    public String getTravelZoneLabel() { return this.Travel_Zone_Label; }

    public void setTravelZoneLabel(String Travel_Zone_Label) { this.Travel_Zone_Label = Travel_Zone_Label; }

    private int Flight_Pass_List_id;

    public int getFlightPassListId() { return this.Flight_Pass_List_id; }

    public void setFlightPassListId(int Flight_Pass_List_id) { this.Flight_Pass_List_id = Flight_Pass_List_id; }

    private int LABL_Book_Add_User_id;

    public int getLABLBookAddUserId() { return this.LABL_Book_Add_User_id; }

    public void setLABLBookAddUserId(int LABL_Book_Add_User_id) { this.LABL_Book_Add_User_id = LABL_Book_Add_User_id; }

    private String user_Tip;

    public String getUserTip() { return this.user_Tip; }

    public void setUserTip(String user_Tip) { this.user_Tip = user_Tip; }

    private String image_cabin;

    public String getImageCabin() { return this.image_cabin; }

    public void setImageCabin(String image_cabin) { this.image_cabin = image_cabin; }

    private int total_flight_number;

    public int getTotalFlightNumber() { return this.total_flight_number; }

    public void setTotalFlightNumber(int total_flight_number) { this.total_flight_number = total_flight_number; }

    private String Credit_Count_Label;

    public String getCreditCountLabel() { return this.Credit_Count_Label; }

    public void setCreditCountLabel(String Credit_Count_Label) { this.Credit_Count_Label = Credit_Count_Label; }

    private String Used_Flights_label;

    public String getUsedFlightsLabel() { return this.Used_Flights_label; }

    public void setUsedFlightsLabel(String Used_Flights_label) { this.Used_Flights_label = Used_Flights_label; }

    private String Validity_End_Date;

    public String getValidityEndDate() { return this.Validity_End_Date; }

    public void setValidityEndDate(String Validity_End_Date) { this.Validity_End_Date = Validity_End_Date; }

    private String Flexibility_Range_Value;

    public String getFlexibilityRangeValue() { return this.Flexibility_Range_Value; }

    public void setFlexibilityRangeValue(String Flexibility_Range_Value) { this.Flexibility_Range_Value = Flexibility_Range_Value; }

    private String ConfirmationNumber;

    public String getConfirmationNumber() { return this.ConfirmationNumber; }

    public void setConfirmationNumber(String ConfirmationNumber) { this.ConfirmationNumber = ConfirmationNumber; }

    private String image_advanceBooking_icon;

    public String getImageAdvanceBookingIcon() { return this.image_advanceBooking_icon; }

    public void setImageAdvanceBookingIcon(String image_advanceBooking_icon) { this.image_advanceBooking_icon = image_advanceBooking_icon; }

    private int in_process_flight_number;

    public int getInProcessFlightNumber() { return this.in_process_flight_number; }

    public void setInProcessFlightNumber(int in_process_flight_number) { this.in_process_flight_number = in_process_flight_number; }

    private String Flexibility_Range_Label;

    public String getFlexibilityRangeLabel() { return this.Flexibility_Range_Label; }

    public void setFlexibilityRangeLabel(String Flexibility_Range_Label) { this.Flexibility_Range_Label = Flexibility_Range_Label; }

    private String Advance_Booking_Label;

    public String getAdvanceBookingLabel() { return this.Advance_Booking_Label; }

    public void setAdvanceBookingLabel(String Advance_Booking_Label) { this.Advance_Booking_Label = Advance_Booking_Label; }

    private String In_process_label;

    public String getInProcessLabel() { return this.In_process_label; }

    public void setInProcessLabel(String In_process_label) { this.In_process_label = In_process_label; }

    private String image_Flexibility;

    public String getImageFlexibility() { return this.image_Flexibility; }

    public void setImageFlexibility(String image_Flexibility) { this.image_Flexibility = image_Flexibility; }

    private String Available_Flights_label;

    public String getAvailableFlightsLabel() { return this.Available_Flights_label; }

    public void setAvailableFlightsLabel(String Available_Flights_label) { this.Available_Flights_label = Available_Flights_label; }

    private String LABL_Book_View_Transaction_Details_Label;

    public String getLABLBookViewTransactionDetailsLabel() { return this.LABL_Book_View_Transaction_Details_Label; }

    public void setLABLBookViewTransactionDetailsLabel(String LABL_Book_View_Transaction_Details_Label) { this.LABL_Book_View_Transaction_Details_Label = LABL_Book_View_Transaction_Details_Label; }

    private int Used_Flights_number;

    public int getUsedFlightsNumber() { return this.Used_Flights_number; }

    public void setUsedFlightsNumber(int Used_Flights_number) { this.Used_Flights_number = Used_Flights_number; }

    private String From_label;

    public String getFromLabel() { return this.From_label; }

    public void setFromLabel(String From_label) { this.From_label = From_label; }

    private ArrayList<String> passengerNameArray;

    public ArrayList<String> getPassengerNameArray() { return this.passengerNameArray; }

    public void setPassengerNameArray(ArrayList<String> passengerNameArray) { this.passengerNameArray = passengerNameArray; }

    private String image_validity_icon;

    public String getImageValidityIcon() { return this.image_validity_icon; }

    public void setImageValidityIcon(String image_validity_icon) { this.image_validity_icon = image_validity_icon; }

    private String LABL_Book_Add_User_Label;

    public String getLABLBookAddUserLabel() { return this.LABL_Book_Add_User_Label; }

    public void setLABLBookAddUserLabel(String LABL_Book_Add_User_Label) { this.LABL_Book_Add_User_Label = LABL_Book_Add_User_Label; }

    private String Travel_Zone_Tips;

    public String getTravelZoneTips() { return this.Travel_Zone_Tips; }

    public void setTravelZoneTips(String Travel_Zone_Tips) { this.Travel_Zone_Tips = Travel_Zone_Tips; }

    private String LABL_Pass_Cabin_Disc_Long_Label;

    public String getLABLPassCabinDiscLongLabel() { return this.LABL_Pass_Cabin_Disc_Long_Label; }

    public void setLABLPassCabinDiscLongLabel(String LABL_Pass_Cabin_Disc_Long_Label) { this.LABL_Pass_Cabin_Disc_Long_Label = LABL_Pass_Cabin_Disc_Long_Label; }

    private String image_multiUser_icon;

    public String getImageMultiUserIcon() { return this.image_multiUser_icon; }

    public void setImageMultiUserIcon(String image_multiUser_icon) { this.image_multiUser_icon = image_multiUser_icon; }

    private String image_number_of_flight;

    public String getImageNumberOfFlight() { return this.image_number_of_flight; }

    public void setImageNumberOfFlight(String image_number_of_flight) { this.image_number_of_flight = image_number_of_flight; }

    private int LABL_MTP_User_Label;

    public int getLABLMTPUserLabel() { return this.LABL_MTP_User_Label; }

    public void setLABLMTPUserLabel(int LABL_MTP_User_Label) { this.LABL_MTP_User_Label = LABL_MTP_User_Label; }

    private String bookFlight_Update_User_label;

    public String getBookFlightUpdateUserLabel() { return this.bookFlight_Update_User_label; }

    public void setBookFlightUpdateUserLabel(String bookFlight_Update_User_label) { this.bookFlight_Update_User_label = bookFlight_Update_User_label; }

    private String Pass_User_Label;

    public String getPassUserLabel() { return this.Pass_User_Label; }

    public void setPassUserLabel(String Pass_User_Label) { this.Pass_User_Label = Pass_User_Label; }

    private String Validity_Tip_Detail;

    public String getValidityTipDetail() { return this.Validity_Tip_Detail; }

    public void setValidityTipDetail(String Validity_Tip_Detail) { this.Validity_Tip_Detail = Validity_Tip_Detail; }

    private String Credit_Type_Title;

    public String getCreditTypeTitle() { return this.Credit_Type_Title ; }

    public void setCreditTypeTitle(String Credit_Type_Title ) { this.Credit_Type_Title  = Credit_Type_Title ; }

    private String Validity_Period_Label;

    public String getValidityPeriodLabel() { return this.Validity_Period_Label; }

    public void setValidityPeriodLabel(String Validity_Period_Label) { this.Validity_Period_Label = Validity_Period_Label; }

    private String TimeUnit_Name_Plural;

    public String getTimeUnitNamePlural() { return this.TimeUnit_Name_Plural; }

    public void setTimeUnitNamePlural(String TimeUnit_Name_Plural) { this.TimeUnit_Name_Plural = TimeUnit_Name_Plural; }

    private String LABL_Optiontown_CN_Label;

    public String getLABLOptiontownCNLabel() { return this.LABL_Optiontown_CN_Label; }

    public void setLABLOptiontownCNLabel(String LABL_Optiontown_CN_Label) { this.LABL_Optiontown_CN_Label = LABL_Optiontown_CN_Label; }

    private String image_travel_zone;

    public String getImageTravelZone() { return this.image_travel_zone; }

    public void setImageTravelZone(String image_travel_zone) { this.image_travel_zone = image_travel_zone; }

    private String To_Label;

    public String getToLabel() { return this.To_Label; }

    public void setToLabel(String To_Label) { this.To_Label = To_Label; }

    private String Book_Flight_Label;

    public String getBookFlightLabel() { return this.Book_Flight_Label; }

    public void setBookFlightLabel(String Book_Flight_Label) { this.Book_Flight_Label = Book_Flight_Label; }

    private String Advance_number_Booking_Days;

    public String getAdvanceNumberBookingDays() { return this.Advance_number_Booking_Days; }

    public void setAdvanceNumberBookingDays(String Advance_number_Booking_Days) { this.Advance_number_Booking_Days = Advance_number_Booking_Days; }

    private String Advance_Booking_Days;

    public String getAdvanceBookingDays() { return this.Advance_Booking_Days; }

    public void setAdvanceBookingDays(String Advance_Booking_Days) { this.Advance_Booking_Days = Advance_Booking_Days; }

    private int Pass_Validity_Value;

    public int getPassValidityValue() { return this.Pass_Validity_Value; }

    public void setPassValidityValue(int Pass_Validity_Value) { this.Pass_Validity_Value = Pass_Validity_Value; }

    private String LABL_Pass_Flight_Count_Disc_Short_Label;

    public String getLABLPassFlightCountDiscShortLabel() { return this.LABL_Pass_Flight_Count_Disc_Short_Label; }

    public void setLABLPassFlightCountDiscShortLabel(String LABL_Pass_Flight_Count_Disc_Short_Label) { this.LABL_Pass_Flight_Count_Disc_Short_Label = LABL_Pass_Flight_Count_Disc_Short_Label; }

    private int Available_Flights_Number;

    public int getAvailableFlightsNumber() { return this.Available_Flights_Number; }

    public void setAvailableFlightsNumber(int Available_Flights_Number) { this.Available_Flights_Number = Available_Flights_Number; }

    private ArrayList<RestrictValue> Restrict_Values;

    public ArrayList<RestrictValue> getRestrictValues() { return this.Restrict_Values; }

    public void setRestrictValues(ArrayList<RestrictValue> Restrict_Values) { this.Restrict_Values = Restrict_Values; }
}

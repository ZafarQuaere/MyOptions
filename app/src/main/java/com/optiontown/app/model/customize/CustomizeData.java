package com.optiontown.app.model.customize;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 29-07-2016.
 */
public class CustomizeData implements Serializable
{
    private ArrayList<ArrayList<RestrictionData>> Restriction_Details;

    public ArrayList<ArrayList<RestrictionData>> getRestrictionDetails() { return this.Restriction_Details; }

    public void setRestrictionDetails(ArrayList<ArrayList<RestrictionData>> Restriction_Details) { this.Restriction_Details = Restriction_Details; }

    private String Passengers_Heading_Label;

    public String getPassengersHeadingLabel() { return this.Passengers_Heading_Label; }

    public void setPassengersHeadingLabel(String Passengers_Heading_Label) { this.Passengers_Heading_Label = Passengers_Heading_Label; }

    private UsersData UsersData;

    public UsersData getUsersData() { return this.UsersData; }

    public void setUsersData(UsersData UsersData) { this.UsersData = UsersData; }

    private FlexibilityData FlexibilityData;

    public FlexibilityData getFlexibilityData() { return this.FlexibilityData; }

    public void setFlexibilityData(FlexibilityData FlexibilityData) { this.FlexibilityData = FlexibilityData; }

    private int IsPassFlexibilityDisplay;

    public int getIsPassFlexibilityDisplay() { return this.IsPassFlexibilityDisplay; }

    public void setIsPassFlexibilityDisplay(int IsPassFlexibilityDisplay) { this.IsPassFlexibilityDisplay = IsPassFlexibilityDisplay; }

    private String Passengers_Long_Desc_Label;

    public String getPassengersLongDescLabel() { return this.Passengers_Long_Desc_Label; }

    public void setPassengersLongDescLabel(String Passengers_Long_Desc_Label) { this.Passengers_Long_Desc_Label = Passengers_Long_Desc_Label; }

    private String Add_Purchase_MaxHrs_Label;

    public String getAddPurchaseMaxHrsLabel() { return this.Add_Purchase_MaxHrs_Label; }

    public void setAddPurchaseMaxHrsLabel(String Add_Purchase_MaxHrs_Label) { this.Add_Purchase_MaxHrs_Label = Add_Purchase_MaxHrs_Label; }

    private String Depart_Flexibility_Label;

    public String getDepartFlexibilityLabel() { return this.Depart_Flexibility_Label; }

    public void setDepartFlexibilityLabel(String Depart_Flexibility_Label) { this.Depart_Flexibility_Label = Depart_Flexibility_Label; }

    private String Depart_Flexibility_Long_Desc;

    public String getDepartFlexibilityLongDesc() { return this.Depart_Flexibility_Long_Desc; }

    public void setDepartFlexibilityLongDesc(String Depart_Flexibility_Long_Desc) { this.Depart_Flexibility_Long_Desc = Depart_Flexibility_Long_Desc; }

    private String LABL_Passengers_Short_Desc_Label;

    public String getLABLPassengersShortDescLabel() { return this.LABL_Passengers_Short_Desc_Label; }

    public void setLABLPassengersShortDescLabel(String LABL_Passengers_Short_Desc_Label) { this.LABL_Passengers_Short_Desc_Label = LABL_Passengers_Short_Desc_Label; }

    private AdvanceBookingData AdvanceBookingData;

    public AdvanceBookingData getAdvanceBookingData() { return this.AdvanceBookingData; }

    public void setAdvanceBookingData(AdvanceBookingData AdvanceBookingData) { this.AdvanceBookingData = AdvanceBookingData; }

    private String Advance_Booking_Label;

    public String getAdvanceBookingLabel() { return this.Advance_Booking_Label; }

    public void setAdvanceBookingLabel(String Advance_Booking_Label) { this.Advance_Booking_Label = Advance_Booking_Label; }

    private String Advance_Booking_Long_Desc;

    public String getAdvanceBookingLongDesc() { return this.Advance_Booking_Long_Desc; }

    public void setAdvanceBookingLongDesc(String Advance_Booking_Long_Desc) { this.Advance_Booking_Long_Desc = Advance_Booking_Long_Desc; }

    private String LABL_Passengers_AddPurchaseMinLabel;

    public String getLABLPassengersAddPurchaseMinLabel() { return this.LABL_Passengers_AddPurchaseMinLabel; }

    public void setLABLPassengersAddPurchaseMinLabel(String LABL_Passengers_AddPurchaseMinLabel) { this.LABL_Passengers_AddPurchaseMinLabel = LABL_Passengers_AddPurchaseMinLabel; }
}

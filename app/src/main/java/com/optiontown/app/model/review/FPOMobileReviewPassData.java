package com.optiontown.app.model.review;

import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.fragment.fpo.review.ReviewFragment;
import com.optiontown.app.model.login.PasswordvalidationRequired;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 16-07-2016.
 */
public class FPOMobileReviewPassData implements Serializable
{
    private ZonefeatureData zonefeatureData;

    public ZonefeatureData getZonefeatureData() { return this.zonefeatureData; }

    public void setZonefeatureData(ZonefeatureData zonefeatureData) { this.zonefeatureData = zonefeatureData; }

    private int IsRequiredAddPax;
    private String Promo_Code_Display_Label;
    private String Promo_Have_Code_Label;
    private String Promo_Apply_Label;
    private String Promo_Code;
    private String Promo_Code_TNC_Label;
    private boolean Promo_DisplayPromoCode;
    private String Promo_TNC_Label;
    private String Promo_Code_Error_Message;
    private int Promo_Code_Error_Flag;
    private String Promo_DiscountPerCent;
    private String Promo_Discount_Value;
    private String Promo_Discount_Text_Label;
    private String Promo_Currency_Symbol;
    private String Promo_Remove_Label;
    private String Promo_Total_Price_Per_Pass;
    private String Promo_Total_Price_Per_Pass_Label;

    private String round_booking_lbl;

    public String getRoundBookingLbl() { return this.round_booking_lbl; }

    public void setRoundBookingLbl(String round_booking_lbl) { this.round_booking_lbl = round_booking_lbl; }


    public int getIsRequiredAddPax() {
        return IsRequiredAddPax;
    }

    public void setIsRequiredAddPax(int isRequiredAddPax) {
        IsRequiredAddPax = isRequiredAddPax;
    }

    public String getPromo_Total_Price_Per_Pass_Label() {
        return Promo_Total_Price_Per_Pass_Label;
    }

    public void setPromo_Total_Price_Per_Pass_Label(String promo_Total_Price_Per_Pass_Label) {
        Promo_Total_Price_Per_Pass_Label = promo_Total_Price_Per_Pass_Label;
    }

    public String getPromo_DiscountPerCent() {
        return Promo_DiscountPerCent;
    }

    public void setPromo_DiscountPerCent(String promo_DiscountPerCent) {
        Promo_DiscountPerCent = promo_DiscountPerCent;
    }

    public String getPromo_Discount_Value() {
        return Promo_Discount_Value;
    }

    public void setPromo_Discount_Value(String promo_Discount_Value) {
        Promo_Discount_Value = promo_Discount_Value;
    }

    public String getPromo_Discount_Text_Label() {
        return Promo_Discount_Text_Label;
    }

    public void setPromo_Discount_Text_Label(String promo_Discount_Text_Label) {
        Promo_Discount_Text_Label = promo_Discount_Text_Label;
    }

    public String getPromo_Currency_Symbol() {
        return Promo_Currency_Symbol;
    }

    public void setPromo_Currency_Symbol(String promo_Currency_Symbol) {
        Promo_Currency_Symbol = promo_Currency_Symbol;
    }

    public String getPromo_Remove_Label() {
        return Promo_Remove_Label;
    }

    public void setPromo_Remove_Label(String promo_Remove_Label) {
        Promo_Remove_Label = promo_Remove_Label;
    }

    public String getPromo_Total_Price_Per_Pass() {
        return Promo_Total_Price_Per_Pass;
    }

    public void setPromo_Total_Price_Per_Pass(String promo_Total_Price_Per_Pass) {
        Promo_Total_Price_Per_Pass = promo_Total_Price_Per_Pass;
    }

    public int getPromo_Code_Error_Flag() {
        return Promo_Code_Error_Flag;
    }

    public void setPromo_Code_Error_Flag(int promo_Code_Error_Flag) {
        Promo_Code_Error_Flag = promo_Code_Error_Flag;
    }

    public String getPromo_Code_Error_Message() {
        return Promo_Code_Error_Message;
    }

    public void setPromo_Code_Error_Message(String promo_Code_Error_Message) {
        Promo_Code_Error_Message = promo_Code_Error_Message;
    }

    public String getPromo_Code() {
        return Promo_Code;
    }

    public void setPromo_Code(String promo_Code) {
        Promo_Code = promo_Code;
    }

    public String getPromo_Code_TNC_Label() {
        return Promo_Code_TNC_Label;
    }

    public void setPromo_Code_TNC_Label(String promo_Code_TNC_Label) {
        Promo_Code_TNC_Label = promo_Code_TNC_Label;
    }

    public boolean isPromo_DisplayPromoCode() {
        return Promo_DisplayPromoCode;
    }

    public void setPromo_DisplayPromoCode(boolean promo_DisplayPromoCode) {
        Promo_DisplayPromoCode = promo_DisplayPromoCode;
    }

    public String getPromo_TNC_Label() {
        return Promo_TNC_Label;
    }

    public void setPromo_TNC_Label(String promo_TNC_Label) {
        Promo_TNC_Label = promo_TNC_Label;
    }

    public String getPromo_Code_Display_Label() {
        return Promo_Code_Display_Label;
    }

    public void setPromo_Code_Display_Label(String promo_Code_Display_Label) {
        Promo_Code_Display_Label = promo_Code_Display_Label;
    }

    public String getPromo_Have_Code_Label() {
        return Promo_Have_Code_Label;
    }

    public void setPromo_Have_Code_Label(String promo_Have_Code_Label) {
        Promo_Have_Code_Label = promo_Have_Code_Label;
    }

    public String getPromo_Apply_Label() {
        return Promo_Apply_Label;
    }

    public void setPromo_Apply_Label(String promo_Apply_Label) {
        Promo_Apply_Label = promo_Apply_Label;
    }

    private String iata_Tour_Code_Label;
    private String iata_Link_Label;
    private String iata_registration;
    private String iata_Hide_Label;

    public String getIata_Tour_Code_Label() {
        return iata_Tour_Code_Label;
    }

    public void setIata_Tour_Code_Label(String iata_Tour_Code_Label) {
        this.iata_Tour_Code_Label = iata_Tour_Code_Label;
    }

    public String getIata_Link_Label() {
        return iata_Link_Label;
    }

    public void setIata_Link_Label(String iata_Link_Label) {
        this.iata_Link_Label = iata_Link_Label;
    }

    public String getIata_registration() {
        return iata_registration;
    }

    public void setIata_registration(String iata_registration) {
        this.iata_registration = iata_registration;
    }

    public String getIata_Hide_Label() {
        return iata_Hide_Label;
    }

    public void setIata_Hide_Label(String iata_Hide_Label) {
        this.iata_Hide_Label = iata_Hide_Label;
    }

    private int iata_Display;

    public int getIata_Display() {
        return iata_Display;
    }

    public void setIata_Display(int iata_Display) {
        this.iata_Display = iata_Display;
    }

    private int AirlineId;

    public int getAirlineId() {
        return AirlineId;
    }

    public void setAirlineId(int airlineId) {
        AirlineId = airlineId;
    }

    private String FFpnumberHelpMessage;

    public String getFFpnumberHelpMessage() { return this.FFpnumberHelpMessage; }

    public void setFFpnumberHelpMessage(String FFpnumberHelpMessage) { this.FFpnumberHelpMessage = FFpnumberHelpMessage; }

    private String FFpnumberSortDesc;

    public String getFFpnumberSortDesc() { return this.FFpnumberSortDesc; }

    public void setFFpnumberSortDesc(String FFpnumberSortDesc) { this.FFpnumberSortDesc = FFpnumberSortDesc; }

    private String FFpnumberErrorMessage;

    public String getFFpnumberErrorMessage() { return this.FFpnumberErrorMessage; }

    public void setFFpnumberErrorMessage(String FFpnumberErrorMessage) { this.FFpnumberErrorMessage = FFpnumberErrorMessage; }

    private int FFPNumberMandatory;

    public int getFFPNumberMandatory() { return this.FFPNumberMandatory; }

    public void setFFPNumberMandatory(int FFPNumberMandatory) { this.FFPNumberMandatory = FFPNumberMandatory; }

    private int isDisplayFFPNumber;

    public int getIsDisplayFFPNumber() { return this.isDisplayFFPNumber; }

    public void setIsDisplayFFPNumber(int isDisplayFFPNumber) { this.isDisplayFFPNumber = isDisplayFFPNumber; }

    public FPOMobileReviewPassData(){Utils.testWorking(ReviewFragment.activity);};

    private String Add_User_Label;

    public String getAddUserLabel() { return this.Add_User_Label; }

    public void setAddUserLabel(String Add_User_Label) { this.Add_User_Label = Add_User_Label; }

    private String CurrencySymbolSavingAmount;

    public String getCurrencySymbolSavingAmount() { return this.CurrencySymbolSavingAmount; }

    public void setCurrencySymbolSavingAmount(String CurrencySymbolSavingAmount) { this.CurrencySymbolSavingAmount = CurrencySymbolSavingAmount; }

    private String Travel_Zone_Label;

    public String getTravelZoneLabel() { return this.Travel_Zone_Label; }

    public void setTravelZoneLabel(String Travel_Zone_Label) { this.Travel_Zone_Label = Travel_Zone_Label; }

    private String TSR_Total_Price_Label;

    public String getTSRTotalPriceLabel() { return this.TSR_Total_Price_Label; }

    public void setTSRTotalPriceLabel(String TSR_Total_Price_Label) { this.TSR_Total_Price_Label = TSR_Total_Price_Label; }

    private String Pass_Flexible_Payment_Label;

    public String getPassFlexiblePaymentLabel() { return this.Pass_Flexible_Payment_Label; }

    public void setPassFlexiblePaymentLabel(String Pass_Flexible_Payment_Label) { this.Pass_Flexible_Payment_Label = Pass_Flexible_Payment_Label; }

    private String Add_User_Help_Label;

    public String getAddUserHelpLabel() { return this.Add_User_Help_Label; }

    public void setAddUserHelpLabel(String Add_User_Help_Label) { this.Add_User_Help_Label = Add_User_Help_Label; }

    private String Admin_Fee_Desc_Label;

    public String getAdminFeeDescLabel() { return this.Admin_Fee_Desc_Label; }

    public void setAdminFeeDescLabel(String Admin_Fee_Desc_Label) { this.Admin_Fee_Desc_Label = Admin_Fee_Desc_Label; }

    private String Pass_Total_Price_Help_Label;

    public String getPassTotalPriceHelpLabel() { return this.Pass_Total_Price_Help_Label; }

    public void setPassTotalPriceHelpLabel(String Pass_Total_Price_Help_Label) { this.Pass_Total_Price_Help_Label = Pass_Total_Price_Help_Label; }

    private String Eligible_Passengers_Label;

    public String getEligiblePassengersLabel() { return this.Eligible_Passengers_Label; }

    public void setEligiblePassengersLabel(String Eligible_Passengers_Label) { this.Eligible_Passengers_Label = Eligible_Passengers_Label; }

    private String Advance_Booking;

    public String getAdvanceBooking() { return this.Advance_Booking; }

    public void setAdvanceBooking(String Advance_Booking) { this.Advance_Booking = Advance_Booking; }

    private String Travel_Period_Validity;

    public String getTravelPeriodValidity() { return this.Travel_Period_Validity; }

    public void setTravelPeriodValidity(String Travel_Period_Validity) { this.Travel_Period_Validity = Travel_Period_Validity; }

    private String Flight_Count_Label;

    public String getFlightCountLabel() { return this.Flight_Count_Label; }

    public void setFlightCountLabel(String Flight_Count_Label) { this.Flight_Count_Label = Flight_Count_Label; }

    private String Flight_Count_Credit;

    public String getFlightCountCredit() { return this.Flight_Count_Credit; }

    public void setFlightCountCredit(String Flight_Count_Credit) { this.Flight_Count_Credit = Flight_Count_Credit; }

    private String FullLabel;

    public String getFullLabel() { return this.FullLabel; }

    public void setFullLabel(String FullLabel) { this.FullLabel = FullLabel; }

    private String Short_Description;

    public String getShortDescription() { return this.Short_Description; }

    public void setShortDescription(String Short_Description) { this.Short_Description = Short_Description; }

    private String Eligible_Passengers_Min_Label;

    public String getEligiblePassengersMinLabel() { return this.Eligible_Passengers_Min_Label; }

    public void setEligiblePassengersMinLabel(String Eligible_Passengers_Min_Label) { this.Eligible_Passengers_Min_Label = Eligible_Passengers_Min_Label; }

    private String Advance_Booking_Label;

    public String getAdvanceBookingLabel() { return this.Advance_Booking_Label; }

    public void setAdvanceBookingLabel(String Advance_Booking_Label) { this.Advance_Booking_Label = Advance_Booking_Label; }

    private String UserAddPurchaseMaxHrsLabel;

    public String getUserAddPurchaseMaxHrsLabel() { return this.UserAddPurchaseMaxHrsLabel; }

    public void setUserAddPurchaseMaxHrsLabel(String UserAddPurchaseMaxHrsLabel) { this.UserAddPurchaseMaxHrsLabel = UserAddPurchaseMaxHrsLabel; }

    private String Number_of_Flights_Label;

    public String getNumberOfFlightsLabel() { return this.Number_of_Flights_Label; }

    public void setNumberOfFlightsLabel(String Number_of_Flights_Label) { this.Number_of_Flights_Label = Number_of_Flights_Label; }

    private String Eligible_Passengers;

    public String getEligiblePassengers() { return this.Eligible_Passengers; }

    public void setEligiblePassengers(String Eligible_Passengers) { this.Eligible_Passengers = Eligible_Passengers; }

    private String Pass_Flexible_Plan_Amount;

    public String getPassFlexiblePlanAmount() { return this.Pass_Flexible_Plan_Amount; }

    public void setPassFlexiblePlanAmount(String Pass_Flexible_Plan_Amount) { this.Pass_Flexible_Plan_Amount = Pass_Flexible_Plan_Amount; }

    private String Review_Heading_Label;

    public String getReviewHeadingLabel() { return this.Review_Heading_Label; }

    public void setReviewHeadingLabel(String Review_Heading_Label) { this.Review_Heading_Label = Review_Heading_Label; }

    private String Total_Price_Label;

    public String getTotalPriceLabel() { return this.Total_Price_Label; }

    public void setTotalPriceLabel(String Total_Price_Label) { this.Total_Price_Label = Total_Price_Label; }

    private ArrayList<Object> UserAddedList;

    public ArrayList<Object> getUserAddedList() { return this.UserAddedList; }

    public void setUserAddedList(ArrayList<Object> UserAddedList) { this.UserAddedList = UserAddedList; }

    private String Pass_Saving_Percent_Label;

    public String getPassSavingPercentLabel() { return this.Pass_Saving_Percent_Label; }

    public void setPassSavingPercentLabel(String Pass_Saving_Percent_Label) { this.Pass_Saving_Percent_Label = Pass_Saving_Percent_Label; }

    private String Cabin_Detail;

    public String getCabinDetail() { return this.Cabin_Detail; }

    public void setCabinDetail(String Cabin_Detail) { this.Cabin_Detail = Cabin_Detail; }

    private String AddPurchaseMaxHrsLabel;

    public String getAddPurchaseMaxHrsLabel() { return this.AddPurchaseMaxHrsLabel; }

    public void setAddPurchaseMaxHrsLabel(String AddPurchaseMaxHrsLabel) { this.AddPurchaseMaxHrsLabel = AddPurchaseMaxHrsLabel; }

    private String Pass_Flexible_Plan_Desc_Label;

    public String getPassFlexiblePlanDescLabel() { return this.Pass_Flexible_Plan_Desc_Label; }

    public void setPassFlexiblePlanDescLabel(String Pass_Flexible_Plan_Desc_Label) { this.Pass_Flexible_Plan_Desc_Label = Pass_Flexible_Plan_Desc_Label; }

    private String Cabin_Label;

    public String getCabinLabel() { return this.Cabin_Label; }

    public void setCabinLabel(String Cabin_Label) { this.Cabin_Label = Cabin_Label; }

    private String Price_Per_Flight;

    public String getPricePerFlight() { return this.Price_Per_Flight; }

    public void setPricePerFlight(String Price_Per_Flight) { this.Price_Per_Flight = Price_Per_Flight; }

    private String Pass_Based_hist_price_Label;

    public String getPassBasedHistPriceLabel() { return this.Pass_Based_hist_price_Label; }

    public void setPassBasedHistPriceLabel(String Pass_Based_hist_price_Label) { this.Pass_Based_hist_price_Label = Pass_Based_hist_price_Label; }

    private String Price_Per_Flight_Label;

    public String getPricePerFlightLabel() { return this.Price_Per_Flight_Label; }

    public void setPricePerFlightLabel(String Price_Per_Flight_Label) { this.Price_Per_Flight_Label = Price_Per_Flight_Label; }

    private String Travel_Period_Label;

    public String getTravelPeriodLabel() { return this.Travel_Period_Label; }

    public void setTravelPeriodLabel(String Travel_Period_Label) { this.Travel_Period_Label = Travel_Period_Label; }

    private String Travel_Period;

    public String getTravelPeriod() { return this.Travel_Period; }

    public void setTravelPeriod(String Travel_Period) { this.Travel_Period = Travel_Period; }

    private String Short_Description_Label;

    public String getShortDescriptionLabel() { return this.Short_Description_Label; }

    public void setShortDescriptionLabel(String Short_Description_Label) { this.Short_Description_Label = Short_Description_Label; }

    private String saveLabel;

    public String getSaveLabel() { return this.saveLabel; }

    public void setSaveLabel(String saveLabel) { this.saveLabel = saveLabel; }

    private String Travel_Period_Tip;

    public String getTravelPeriodTip() { return this.Travel_Period_Tip; }

    public void setTravelPeriodTip(String Travel_Period_Tip) { this.Travel_Period_Tip = Travel_Period_Tip; }

    private String Currency_Symbol_And_Amount;

    public String getCurrencySymbolAndAmount() { return this.Currency_Symbol_And_Amount; }

    public void setCurrencySymbolAndAmount(String Currency_Symbol_And_Amount) { this.Currency_Symbol_And_Amount = Currency_Symbol_And_Amount; }

    private String userAddPurchaseMinLabel;

    public String getUserAddPurchaseMinLabel() { return this.userAddPurchaseMinLabel; }

    public void setUserAddPurchaseMinLabel(String UserAddPurchaseMinLabel) { this.userAddPurchaseMinLabel = UserAddPurchaseMinLabel; }

    private String Number_of_Flights;

    public String getNumberOfFlights() { return this.Number_of_Flights; }

    public void setNumberOfFlights(String Number_of_Flights) { this.Number_of_Flights = Number_of_Flights; }

    private String CurrencySymbol;

    public String getCurrencySymbol() { return this.CurrencySymbol; }

    public void setCurrencySymbol(String CurrencySymbol) { this.CurrencySymbol = CurrencySymbol; }

    private String Travel_Zone;

    public String getTravelZone() { return this.Travel_Zone; }

    public void setTravelZone(String Travel_Zone) { this.Travel_Zone = Travel_Zone; }

    public int IsPassFlexibilityDisplay;

    public PasswordvalidationRequired PasswordValidationRequired;

    public PasswordvalidationRequired getPasswordValidationRequired() { return this.PasswordValidationRequired; }

    public void setPasswordValidationRequired(PasswordvalidationRequired PasswordValidationRequired) { this.PasswordValidationRequired = PasswordValidationRequired; }


    public int IsPassSavingDisplay;

    public int getIsPassFlexibilityDisplay() {
        return IsPassFlexibilityDisplay;
    }

    public void setIsPassFlexibilityDisplay(int isPassFlexibilityDisplay) {
        IsPassFlexibilityDisplay = isPassFlexibilityDisplay;
    }

    public int getIsPassSavingDisplay() {
        return IsPassSavingDisplay;
    }

    public void setIsPassSavingDisplay(int isPassSavingDisplay) {
        IsPassSavingDisplay = isPassSavingDisplay;
    }

    public String LABL_Pass_Flexible_Plan_Know_More_Label;

    public String getLABLPassFlexiblePlanKnowMoreLabel() { return this.LABL_Pass_Flexible_Plan_Know_More_Label; }

    public void setLABLPassFlexiblePlanKnowMoreLabel(String LABL_Pass_Flexible_Plan_Know_More_Label) { this.LABL_Pass_Flexible_Plan_Know_More_Label = LABL_Pass_Flexible_Plan_Know_More_Label; }

    private String Admin_Fee_Label;

    public String getAdminFeeLabel() { return this.Admin_Fee_Label; }

    public void setAdminFeeLabel(String Admin_Fee_Label) { this.Admin_Fee_Label = Admin_Fee_Label; }

    private String Admin_Fee;

    public String getAdminFee() { return this.Admin_Fee; }

    public void setAdminFee(String Admin_Fee) { this.Admin_Fee = Admin_Fee; }

    private String Depart_Flexibility = "";

    public String getDepartFlexibility() { return this.Depart_Flexibility; }

    public void setDepartFlexibility(String Depart_Flexibility) { this.Depart_Flexibility = Depart_Flexibility; }

    private String Depart_Flexibility_Label = "";

    public String getDepartFlexibilityLabel() { return this.Depart_Flexibility_Label; }

    public void setDepartFlexibilityLabel(String Depart_Flexibility_Label) { this.Depart_Flexibility_Label = Depart_Flexibility_Label; }

    private String Regular_Total_Price_Label;

    public String getRegularTotalPriceLabel() { return this.Regular_Total_Price_Label; }

    public void setRegularTotalPriceLabel(String Regular_Total_Price_Label) { this.Regular_Total_Price_Label = Regular_Total_Price_Label; }

    private String Regular_Total_Price;

    public String getRegularTotalPrice() { return this.Regular_Total_Price; }

    public void setRegularTotalPrice(String Regular_Total_Price) { this.Regular_Total_Price = Regular_Total_Price; }

    private int passIndex;

    private int cmmIndex;

    public int getPassIndex() {
        return passIndex;
    }

    public void setPassIndex(int passIndex) {
        this.passIndex = passIndex;
    }

    private ArrayList<RestrictValue> Restrict_Values;

    public ArrayList<RestrictValue> getRestrictValues() { return this.Restrict_Values; }

    public void setRestrictValues(ArrayList<RestrictValue> Restrict_Values) { this.Restrict_Values = Restrict_Values; }

    public int getCmmIndex() {
        return cmmIndex;
    }

    public void setCmmIndex(int cmmIndex) {
        this.cmmIndex = cmmIndex;
    }

    public String IsFlexibleEMIDisplay;

    public String getIsFlexibleEMIDisplay() {
        return IsFlexibleEMIDisplay;
    }

    public void setIsFlexibleEMIDisplay(String isFlexibleEMIDisplay) {
        this.IsFlexibleEMIDisplay = isFlexibleEMIDisplay;
    }

    public int Eligible_Passengers_Min;

    public int getEligible_Passengers_Min() {
        return Eligible_Passengers_Min;
    }

    public void setEligible_Passengers_Min(int eligible_Passengers_Min) {
        Eligible_Passengers_Min = eligible_Passengers_Min;
    }

    private int Eligible_Passengers_Max_Count;

    public int getEligible_Passengers_Max_Count() {
        return Eligible_Passengers_Max_Count;
    }

    public void setEligible_Passengers_Max_Count(int eligible_Passengers_Max_Count) {
        Eligible_Passengers_Max_Count = eligible_Passengers_Max_Count;
    }
}

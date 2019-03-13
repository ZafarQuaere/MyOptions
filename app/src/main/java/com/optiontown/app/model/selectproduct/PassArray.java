package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 02-06-2016.
 */

public class PassArray implements Serializable
{
    private String Taxes_Label;

    public String getTaxesLabel() { return this.Taxes_Label; }

    public void setTaxesLabel(String Taxes_Label) { this.Taxes_Label = Taxes_Label; }

    private String cabinName;

    public String getCabinName() { return this.cabinName; }

    public void setCabinName(String cabinName) { this.cabinName = cabinName; }

    private String TotalPrice;

    public String getTotalPrice() { return this.TotalPrice; }

    public void setTotalPrice(String TotalPrice) { this.TotalPrice = TotalPrice; }

    private String SavePrice;

    public String getSavePrice() { return this.SavePrice; }

    public void setSavePrice(String SavePrice) { this.SavePrice = SavePrice; }

    private String StrikedRegularPrice;

    public String getStrikedRegularPrice() { return this.StrikedRegularPrice; }

    public void setStrikedRegularPrice(String StrikedRegularPrice) { this.StrikedRegularPrice = StrikedRegularPrice; }

    private String RegularPriceLabel;

    public String getRegularPriceLabel() { return this.RegularPriceLabel; }

    public void setRegularPriceLabel(String RegularPriceLabel) { this.RegularPriceLabel = RegularPriceLabel; }

    private String AdvanceBookingValue;

    public String getAdvanceBookingValue() { return this.AdvanceBookingValue; }

    public void setAdvanceBookingValue(String AdvanceBookingValue) { this.AdvanceBookingValue = AdvanceBookingValue; }

    private String PerFlightCredit;

    public String getPerFlightCredit() { return this.PerFlightCredit; }

    public void setPerFlightCredit(String PerFlightCredit) { this.PerFlightCredit = PerFlightCredit; }

    private String Save_Label;

    public String getSaveLabel() { return this.Save_Label; }

    public void setSaveLabel(String Save_Label) { this.Save_Label = Save_Label; }

    private int PricePerPass;

    public int getPricePerPass() { return this.PricePerPass; }

    public void setPricePerPass(int PricePerPass) { this.PricePerPass = PricePerPass; }

    private String currCode;

    public String getCurrCode() { return this.currCode; }

    public void setCurrCode(String currCode) { this.currCode = currCode; }

    private String CurrencyCode;

    public String getCurrencyCode() { return this.CurrencyCode; }

    public void setCurrencyCode(String CurrencyCode) { this.CurrencyCode = CurrencyCode; }

    private String UpTo_Label;

    public String getUpToLabel() { return this.UpTo_Label; }

    public void setUpToLabel(String UpTo_Label) { this.UpTo_Label = UpTo_Label; }

    private String Month;

    public String getMonth() { return this.Month; }

    public void setMonth(String Month) { this.Month = Month; }

    private int Saving_Percentage;

    public int getSavingPercentage() { return this.Saving_Percentage; }

    public void setSavingPercentage(int Saving_Percentage) { this.Saving_Percentage = Saving_Percentage; }

    private String AdvanceBookingLabel;

    public String getAdvanceBookingLabel() { return this.AdvanceBookingLabel; }

    public void setAdvanceBookingLabel(String AdvanceBookingLabel) { this.AdvanceBookingLabel = AdvanceBookingLabel; }

    private String CabinSubString;

    public String getCabinSubString() { return this.CabinSubString; }

    public void setCabinSubString(String CabinSubString) { this.CabinSubString = CabinSubString; }

    private String PassSubString;

    public String getPassSubString() { return this.PassSubString; }

    public void setPassSubString(String PassSubString) { this.PassSubString = PassSubString; }

    private String Flights_Label;

    public String getFlightsLabel() { return this.Flights_Label; }

    public void setFlightsLabel(String Flights_Label) { this.Flights_Label = Flights_Label; }

    private String SavingOff;

    public String getSavingOff() { return this.SavingOff; }

    public void setSavingOff(String SavingOff) { this.SavingOff = SavingOff; }

    private String BannerImage;

    public String getBannerImage() { return this.BannerImage; }

    public void setBannerImage(String BannerImage) { this.BannerImage = BannerImage; }

    private int CreditCount;

    public int getCreditCount() { return this.CreditCount; }

    public void setCreditCount(int CreditCount) { this.CreditCount = CreditCount; }

    private String Total_Label;

    public String getTotalLabel() { return this.Total_Label; }

    public void setTotalLabel(String Total_Label) { this.Total_Label = Total_Label; }

    private String PassFullName;

    public String getPassFullName() { return this.PassFullName; }

    public void setPassFullName(String PassFullName) { this.PassFullName = PassFullName; }

    private int InnerIndex;

    private int OuterIndex;

    public int getInnerIndex() {
        return InnerIndex;
    }

    public void setInnerIndex(int innerIndex) {
        InnerIndex = innerIndex;
    }

    public int getOuterIndex() {
        return OuterIndex;
    }

    public void setOuterIndex(int outerIndex) {
        OuterIndex = outerIndex;
    }

    public String getPerFlightLabl() {
        return perFlightLabl;
    }

    public void setPerFlightLabl(String perFlightLabl) {
        this.perFlightLabl = perFlightLabl;
    }

    public String perFlightLabl;
}

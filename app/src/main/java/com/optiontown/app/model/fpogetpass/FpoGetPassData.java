package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 16-06-2016.
 */
public class FpoGetPassData implements Serializable
{

    private int CountryId;

    private int LanguageId;

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public int getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(int languageId) {
        LanguageId = languageId;
    }

    private String Error_Message;

    public String getError_Message() {
        return Error_Message;
    }

    public void setError_Message(String error_Message) {
        Error_Message = error_Message;
    }

    private ArrayList<PassObject> passObject;

    public ArrayList<PassObject> getPassObject() { return this.passObject; }

    public void setPassObject(ArrayList<PassObject> passObject) { this.passObject = passObject; }

    private ArrayList<ValidityList> ValidityList;

    public ArrayList<ValidityList> getValidityList() { return this.ValidityList; }

    public void setValidityList(ArrayList<ValidityList> ValidityList) { this.ValidityList = ValidityList; }

    private int MaxPrice;

    public int getMaxPrice() { return this.MaxPrice; }

    public void setMaxPrice(int MaxPrice) { this.MaxPrice = MaxPrice; }

    private String Customize;

    public String getCustomize() { return this.Customize; }

    public void setCustomize(String Customize) { this.Customize = Customize; }

    private ArrayList<ZoneMemberSet> ZoneMemberSet;

    public ArrayList<ZoneMemberSet> getZoneMemberSet() { return this.ZoneMemberSet; }

    public void setZoneMemberSet(ArrayList<ZoneMemberSet> ZoneMemberSet) { this.ZoneMemberSet = ZoneMemberSet; }

    private String flightLabel;

    public String getFlightLabel() { return this.flightLabel; }

    public void setFlightLabel(String flightLabel) { this.flightLabel = flightLabel; }

    private ArrayList<FlightCountList> FlightCountList;

    public ArrayList<FlightCountList> getFlightCountList() { return this.FlightCountList; }

    public void setFlightCountList(ArrayList<FlightCountList> FlightCountList) { this.FlightCountList = FlightCountList; }

    private String Buy;

    public String getBuy() { return this.Buy; }

    public void setBuy(String Buy) { this.Buy = Buy; }

    private String perflightLabel;

    public String getPerflightLabel() { return this.perflightLabel; }

    public void setPerflightLabel(String perflightLabel) { this.perflightLabel = perflightLabel; }

    private int MinPrice;

    public int getMinPrice() { return this.MinPrice; }

    public void setMinPrice(int MinPrice) { this.MinPrice = MinPrice; }

    private String CurrencyUnit;

    public String getCurrencyUnit() {
        return CurrencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        CurrencyUnit = currencyUnit;
    }

    public ArrayList<com.optiontown.app.model.fpogetpass.PassSortDropDownList> getPassSortDropDownList() {
        return PassSortDropDownList;
    }

    public void setPassSortDropDownList(ArrayList<com.optiontown.app.model.fpogetpass.PassSortDropDownList> passSortDropDownList) {
        PassSortDropDownList = passSortDropDownList;
    }

    private ArrayList<PassSortDropDownList> PassSortDropDownList;



    private String NumberOfFlightLabel;

    public String getNumberOfFlightLabel() {
        return NumberOfFlightLabel;
    }

    public void setNumberOfFlightLabel(String numberOfFlightLabel) {
        NumberOfFlightLabel = numberOfFlightLabel;
    }
}

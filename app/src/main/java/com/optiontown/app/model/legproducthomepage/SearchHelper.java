package com.optiontown.app.model.legproducthomepage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ravi.kumar on 26-08-2016.
 */
public class SearchHelper implements Serializable{
    public String tgProductId, LanguageId, CountryId, MarketingAirlineId, pnr, lastName, isSearchBy;
    public String departAirlineCode;
    public String arriveAirlineCode;
    public String departDate;
    public  String flightNumber;
    public  String firstName;
    public  String mobileCode;
    public  String mobileNumber;
    public  String email;
    public  String OCN;
    public  String departArptCode;
    public  String searchErrorMsg;
    public  boolean searchError;
    public  boolean isEmailCheck;
    public  String checkEmail;
    private HashMap<String, ArrayList<String>> selectedPax;
    private HashMap<String, Object> seatMapData;

    public String getCheckEmail() {
        return checkEmail;
    }

    public void setCheckEmail(String checkEmail) {
        this.checkEmail = checkEmail;
    }

    public boolean isEmailCheck() {
        return isEmailCheck;
    }

    public void setEmailCheck(boolean emailCheck) {
        isEmailCheck = emailCheck;
    }





    public String getSearchErrorMsg() {
        return searchErrorMsg;
    }

    public void setSearchErrorMsg(String searchErrorMsg) {
        this.searchErrorMsg = searchErrorMsg;
    }

    public boolean isSearchError() {
        return searchError;
    }

    public void setSearchError(boolean searchError) {
        this.searchError = searchError;
    }


    String arriveArptCode;

    public String getArriveArptCode() {
        return arriveArptCode;
    }

    public void setArriveArptCode(String arriveArptCode) {
        this.arriveArptCode = arriveArptCode;
    }

    public String getDepartArptCode() {
        return departArptCode;
    }

    public void setDepartArptCode(String departArptCode) {
        this.departArptCode = departArptCode;
    }


    public String getOCN() {
        return OCN;
    }

    public void setOCN(String OCN) {
        this.OCN = OCN;
    }

    public String getDepartAirlineCode() {
        return departAirlineCode;
    }

    public void setDepartAirlineCode(String departAirlineCode) {
        this.departAirlineCode = departAirlineCode;
    }

    public String getArriveAirlineCode() {
        return arriveAirlineCode;
    }

    public void setArriveAirlineCode(String arriveAirlineCode) {
        this.arriveAirlineCode = arriveAirlineCode;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getTgProductId() {
        return tgProductId;
    }

    public void setTgProductId(String tgProductId) {
        this.tgProductId = tgProductId;
    }

    public String getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(String languageId) {
        LanguageId = languageId;
    }

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public String getMarketingAirlineId() {
        return MarketingAirlineId;
    }

    public void setMarketingAirlineId(String marketingAirlineId) {
        MarketingAirlineId = marketingAirlineId;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIsSearchBy() {
        return isSearchBy;
    }

    public void setIsSearchBy(String isSearchBy) {
        this.isSearchBy = isSearchBy;
    }

    public void setSelectedPax(HashMap<String, ArrayList<String>> selectedPax) {
        this.selectedPax = selectedPax;
    }
    public HashMap<String, ArrayList<String>> getSelectedPax(){
        return selectedPax;
    }

    public void setSeatMapData(HashMap<String, Object> seatMapData) {
        this.seatMapData = seatMapData;
    }

    public HashMap<String, Object> getSeatMapData() {
        return seatMapData;
    }
}

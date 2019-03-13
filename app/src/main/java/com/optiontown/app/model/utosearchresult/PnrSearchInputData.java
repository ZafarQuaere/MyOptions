package com.optiontown.app.model.utosearchresult;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 04/05/2017.
 */

public class PnrSearchInputData implements Serializable {

    private String PNR;
    private String LastName;
    private String passId;
    private String pass_airline_id;

    public String getPass_airline_id() {
        return pass_airline_id;
    }

    public void setPass_airline_id(String pass_airline_id) {
        this.pass_airline_id = pass_airline_id;
    }



    public String getPassId() {
        return passId;
    }

    public void setPassId(String passId) {
        this.passId = passId;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }



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

    public String getOCN() {
        return OCN;
    }

    public void setOCN(String OCN) {
        this.OCN = OCN;
    }

    public String getDepartArptCode() {
        return departArptCode;
    }

    public void setDepartArptCode(String departArptCode) {
        this.departArptCode = departArptCode;
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

    public boolean isEmailCheck() {
        return isEmailCheck;
    }

    public void setEmailCheck(boolean emailCheck) {
        isEmailCheck = emailCheck;
    }

    public String getCheckEmail() {
        return checkEmail;
    }

    public void setCheckEmail(String checkEmail) {
        this.checkEmail = checkEmail;
    }

    String arriveArptCode;

    public String getArriveArptCode() {
        return arriveArptCode;
    }

    public void setArriveArptCode(String arriveArptCode) {
        this.arriveArptCode = arriveArptCode;
    }
}

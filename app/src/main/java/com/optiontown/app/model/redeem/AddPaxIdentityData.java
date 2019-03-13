package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 30/09/2016.
 */
public class AddPaxIdentityData implements Serializable {


    private String PassUserId;

    private String Prefix;
    private String FName;
    private String MName;
    private String LName;
    private String Email;
    private String MobExt;
    private String MobNum;
    private String DOBDay;
    private String DOBMonth;
    private String DOBYear;
    private String FFPnumber;
    private String IDCardType;
    private String IDCardNumber;
    private String IDCountry;
    private String PIssuePlace;
    private String EXPDay;
    private String EXPMonth;
    private String EXPYear;
    private boolean isUpgradRequired;
    private boolean isMmpUserAdditionCase;
    private int isDisplayFfpNumber;
    private String FFpnumberHelpMessage;
    private String FFpnumberErrorMessage;

    public String getFFpnumberErrorMessage() { return this.FFpnumberErrorMessage; }

    public void setFFpnumberErrorMessage(String FFpnumberErrorMessage) { this.FFpnumberErrorMessage = FFpnumberErrorMessage; }

    public String getFFpnumberHelpMessage() { return this.FFpnumberHelpMessage; }

    public void setFFpnumberHelpMessage(String FFpnumberHelpMessage) { this.FFpnumberHelpMessage = FFpnumberHelpMessage; }

    public int getFFPnumberMandatory() {
        return FFPnumberMandatory;
    }

    public void setFFPnumberMandatory(int FFPnumberMandatory) {
        this.FFPnumberMandatory = FFPnumberMandatory;
    }

    private int FFPnumberMandatory;


    public int getIsDisplayFfpNumber() {
        return isDisplayFfpNumber;
    }

    public void setIsDisplayFfpNumber(int isDisplayFfpNumber) {
        this.isDisplayFfpNumber = isDisplayFfpNumber;
    }

    public boolean isMmpUserAdditionCase() {
        return isMmpUserAdditionCase;
    }

    public void setMmpUserAdditionCase(boolean mmpUserAdditionCase) {
        isMmpUserAdditionCase = mmpUserAdditionCase;
    }

    public boolean isUpgradRequired() {
        return isUpgradRequired;
    }

    public void setUpgradRequired(boolean upgradRequired) {
        isUpgradRequired = upgradRequired;
    }


    public String getPassUserId() {
        return PassUserId;
    }

    public void setPassUserId(String passUserId) {
        PassUserId = passUserId;
    }


    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getMName() {
        return MName;
    }

    public void setMName(String MName) {
        this.MName = MName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobExt() {
        return MobExt;
    }

    public void setMobExt(String mobExt) {
        MobExt = mobExt;
    }

    public String getMobNum() {
        return MobNum;
    }

    public void setMobNum(String mobNum) {
        MobNum = mobNum;
    }

    public String getDOBDay() {
        return DOBDay;
    }

    public void setDOBDay(String DOBDay) {
        this.DOBDay = DOBDay;
    }

    public String getDOBMonth() {
        return DOBMonth;
    }

    public void setDOBMonth(String DOBMonth) {
        this.DOBMonth = DOBMonth;
    }

    public String getDOBYear() {
        return DOBYear;
    }

    public void setDOBYear(String DOBYear) {
        this.DOBYear = DOBYear;
    }

    public String getFFPnumber() {
        return FFPnumber;
    }

    public void setFFPnumber(String FFPnumber) {
        this.FFPnumber = FFPnumber;
    }

    private ArrayList<CardTypeArray> CardTypeArray;

    public ArrayList<CardTypeArray> getCardTypeArray() { return this.CardTypeArray; }

    public void setCardTypeArray(ArrayList<CardTypeArray> CardTypeArray) { this.CardTypeArray = CardTypeArray; }


    private ArrayList<CountryListArray> CountryListArray;

    public ArrayList<CountryListArray> getCountryListArray() { return this.CountryListArray; }

    public void setCountryListArray(ArrayList<CountryListArray> CountryListArray) { this.CountryListArray = CountryListArray; }


    public String getIDCardType() {
        return IDCardType;
    }

    public void setIDCardType(String IDCardType) {
        this.IDCardType = IDCardType;
    }

    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public void setIDCardNumber(String IDCardNumber) {
        this.IDCardNumber = IDCardNumber;
    }

    public String getIDCountry() {
        return IDCountry;
    }

    public void setIDCountry(String IDCountry) {
        this.IDCountry = IDCountry;
    }

    public String getPIssuePlace() {
        return PIssuePlace;
    }

    public void setPIssuePlace(String PIssuePlace) {
        this.PIssuePlace = PIssuePlace;
    }

    public String getEXPDay() {
        return EXPDay;
    }

    public void setEXPDay(String EXPDay) {
        this.EXPDay = EXPDay;
    }

    public String getEXPMonth() {
        return EXPMonth;
    }

    public void setEXPMonth(String EXPMonth) {
        this.EXPMonth = EXPMonth;
    }

    public String getEXPYear() {
        return EXPYear;
    }

    public void setEXPYear(String EXPYear) {
        this.EXPYear = EXPYear;
    }




}

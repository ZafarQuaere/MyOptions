package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 30/09/2016.
 */
public class AddPaxInfoData implements Serializable{

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


    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String prefix) {
        Prefix = prefix;
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



}

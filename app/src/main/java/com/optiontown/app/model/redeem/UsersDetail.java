package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by amit on 22-09-2016.
 */
public class UsersDetail implements Serializable
{
    private boolean isSelectedPassUser;

    public boolean isSelectedPassUser() {
        return isSelectedPassUser;
    }

    public void setSelectedPassUser(boolean selectedPassUser) {
        isSelectedPassUser = selectedPassUser;
    }

    private String IDDocExpMonth;

    public String getIDDocExpMonth() { return this.IDDocExpMonth; }

    public void setIDDocExpMonth(String IDDocExpMonth) { this.IDDocExpMonth = IDDocExpMonth; }

    private String IDnumber;

    public String getIDnumber() { return this.IDnumber; }

    public void setIDnumber(String IDnumber) { this.IDnumber = IDnumber; }

    private String DOBYear;

    public String getDOBYear() { return this.DOBYear; }

    public void setDOBYear(String DOBYear) { this.DOBYear = DOBYear; }

    private String CountryName;

    public String getCountryName() { return this.CountryName; }

    public void setCountryName(String CountryName) { this.CountryName = CountryName; }

    private int index;

    public int getIndex() { return this.index; }

    public void setIndex(int index) { this.index = index; }

    private String DOBDay;

    public String getDOBDay() { return this.DOBDay; }

    public void setDOBDay(String DOBDay) { this.DOBDay = DOBDay; }

    private String Prefix;

    public String getPrefix() { return this.Prefix; }

    public void setPrefix(String Prefix) { this.Prefix = Prefix; }

    private String PhoneExt;

    public String getPhoneExt() { return this.PhoneExt; }

    public void setPhoneExt(String PhoneExt) { this.PhoneExt = PhoneExt; }

    private String PassportIssuingPlace;

    public String getPassportIssuingPlace() { return this.PassportIssuingPlace; }

    public void setPassportIssuingPlace(String PassportIssuingPlace) { this.PassportIssuingPlace = PassportIssuingPlace; }

    private String FfpNumber;

    public String getFfpNumber() { return this.FfpNumber; }

    public void setFfpNumber(String FfpNumber) { this.FfpNumber = FfpNumber; }

    private String LName;

    public String getLName() { return this.LName; }

    public void setLName(String LName) { this.LName = LName; }

    private String FName;

    public String getFName() { return this.FName; }

    public void setFName(String FName) { this.FName = FName; }

    private String TelephoneMainPart;

    public String getTelephoneMainPart() { return this.TelephoneMainPart; }

    public void setTelephoneMainPart(String TelephoneMainPart) { this.TelephoneMainPart = TelephoneMainPart; }

    private String IDDocExpDay;

    public String getIDDocExpDay() { return this.IDDocExpDay; }

    public void setIDDocExpDay(String IDDocExpDay) { this.IDDocExpDay = IDDocExpDay; }

    private String IdCardType;

    public String getIdCardType() { return this.IdCardType; }

    public void setIdCardType(String IdCardType) { this.IdCardType = IdCardType; }

    private int UserId;

    public int getUserId() { return this.UserId; }

    public void setUserId(int UserId) { this.UserId = UserId; }

    private String MName;

    public String getMName() { return this.MName; }

    public void setMName(String MName) { this.MName = MName; }

    private String FUllName;

    public String getFUllName() { return this.FUllName; }

    public void setFUllName(String FUllName) { this.FUllName = FUllName; }

    private String Email;

    public String getEmail() { return this.Email; }

    public void setEmail(String Email) { this.Email = Email; }

    private String DOBMonth;

    public String getDOBMonth() { return this.DOBMonth; }

    public void setDOBMonth(String DOBMonth) { this.DOBMonth = DOBMonth; }

    private String IDDocExpYear;

    public String getIDDocExpYear() { return this.IDDocExpYear; }

    public void setIDDocExpYear(String IDDocExpYear) { this.IDDocExpYear = IDDocExpYear; }

    private boolean UpgradeRequired;

    public boolean getUpgradeRequired() { return this.UpgradeRequired; }

    public void setUpgradeRequired(boolean UpgradeRequired) { this.UpgradeRequired = UpgradeRequired; }
}

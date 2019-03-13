package com.optiontown.app.model.login;

import java.io.Serializable;

/**
 * Created by amit on 24-06-2016.
 */
public class ProfileDetails implements Serializable
{
    private String BirthYear;

    public String getBirthYear() { return this.BirthYear; }

    public void setBirthYear(String BirthYear) { this.BirthYear = BirthYear; }

    private String AlternateTelephoneCode;

    public String getAlternateTelephoneCode() { return this.AlternateTelephoneCode; }

    public void setAlternateTelephoneCode(String AlternateTelephoneCode) { this.AlternateTelephoneCode = AlternateTelephoneCode; }

    private String Extension;

    public String getExtension() { return this.Extension; }

    public void setExtension(String Extension) { this.Extension = Extension; }

    private String MiddleName;

    public String getMiddleName() { return this.MiddleName; }

    public void setMiddleName(String MiddleName) { this.MiddleName = MiddleName; }

    private String BirthDay;

    public String getBirthDay() { return this.BirthDay; }

    public void setBirthDay(String BirthDay) { this.BirthDay = BirthDay; }

    private int prefix;

    public int getPrefix() { return this.prefix; }

    public void setPrefix(int prefix) { this.prefix = prefix; }

    private String AlternateTelephoneNumberMainPart;

    public String getAlternateTelephoneNumberMainPart() { return this.AlternateTelephoneNumberMainPart; }

    public void setAlternateTelephoneNumberMainPart(String AlternateTelephoneNumberMainPart) { this.AlternateTelephoneNumberMainPart = AlternateTelephoneNumberMainPart; }

    private String FirstName;

    public String getFirstName() { return this.FirstName; }

    public void setFirstName(String FirstName) { this.FirstName = FirstName; }

    private String PhoneNumber;

    public String getPhoneNumber() { return this.PhoneNumber; }

    public void setPhoneNumber(String PhoneNumber) { this.PhoneNumber = PhoneNumber; }

    private String BirthMonth;

    public String getBirthMonth() { return this.BirthMonth; }

    public void setBirthMonth(String BirthMonth) { this.BirthMonth = BirthMonth; }

    private String LastName;

    public String getLastName() { return this.LastName; }

    public void setLastName(String LastName) { this.LastName = LastName; }

    private int Sex;

    public int getSex() { return this.Sex; }

    public void setSex(int Sex) { this.Sex = Sex; }
}

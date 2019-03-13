package com.optiontown.app.model.login;

import java.io.Serializable;

/**
 * Created by amit on 24-06-2016.
 */
public class CompleteProfile implements Serializable
{
    private String Message;

    public String getMessage() { return this.Message; }

    public void setMessage(String Message) { this.Message = Message; }

    private PasswordErrorList PasswordErrorList;

    public PasswordErrorList getPasswordErrorList() { return this.PasswordErrorList; }

    public void setPasswordErrorList(PasswordErrorList PasswordErrorList) { this.PasswordErrorList = PasswordErrorList; }

    private boolean isPasswrdValidationFail;

    public boolean getIsPasswrdValidationFail() { return this.isPasswrdValidationFail; }

    public void setIsPasswrdValidationFail(boolean isPasswrdValidationFail) { this.isPasswrdValidationFail = isPasswrdValidationFail; }

    private String Result;

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    private String BirthDay;

    public String getBirthDay() { return this.BirthDay; }

    public void setBirthDay(String BirthDay) { this.BirthDay = BirthDay; }

    private int Suffix;

    public int getSuffix() { return this.Suffix; }

    public void setSuffix(int Suffix) { this.Suffix = Suffix; }

    private int OPAlert;

    public int getOPAlert() { return this.OPAlert; }

    public void setOPAlert(int OPAlert) { this.OPAlert = OPAlert; }

    private String PaypalAccountEmail;

    public String getPaypalAccountEmail() { return this.PaypalAccountEmail; }

    public void setPaypalAccountEmail(String PaypalAccountEmail) { this.PaypalAccountEmail = PaypalAccountEmail; }

    private String PhoneNumber;

    public String getPhoneNumber() { return this.PhoneNumber; }

    public void setPhoneNumber(String PhoneNumber) { this.PhoneNumber = PhoneNumber; }

    private String Country;

    public String getCountry() { return this.Country; }

    public void setCountry(String Country) { this.Country = Country; }

    private String AlternateTelNumber;

    public String getAlternateTelNumber() { return this.AlternateTelNumber; }

    public void setAlternateTelNumber(String AlternateTelNumber) { this.AlternateTelNumber = AlternateTelNumber; }

    private String LastName;

    public String getLastName() { return this.LastName; }

    public void setLastName(String LastName) { this.LastName = LastName; }

    private String BirthMonth;

    public String getBirthMonth() { return this.BirthMonth; }

    public void setBirthMonth(String BirthMonth) { this.BirthMonth = BirthMonth; }

    private String ZipCode;

    public String getZipCode() { return this.ZipCode; }

    public void setZipCode(String ZipCode) { this.ZipCode = ZipCode; }

    private String City;

    public String getCity() { return this.City; }

    public void setCity(String City) { this.City = City; }

    private String AlternateTelCode;

    public String getAlternateTelCode() { return this.AlternateTelCode; }

    public void setAlternateTelCode(String AlternateTelCode) { this.AlternateTelCode = AlternateTelCode; }

    private String BirthYear;

    public String getBirthYear() { return this.BirthYear; }

    public void setBirthYear(String BirthYear) { this.BirthYear = BirthYear; }

    private String Extension;

    public String getExtension() { return this.Extension; }

    public void setExtension(String Extension) { this.Extension = Extension; }

    private int ReceiveOffers;

    public int getReceiveOffers() { return this.ReceiveOffers; }

    public void setReceiveOffers(int ReceiveOffers) { this.ReceiveOffers = ReceiveOffers; }

    private String MiddleName;

    public String getMiddleName() { return this.MiddleName; }

    public void setMiddleName(String MiddleName) { this.MiddleName = MiddleName; }

    private String State;

    public String getState() { return this.State; }

    public void setState(String State) { this.State = State; }

    private String Email;

    public String getEmail() { return this.Email; }

    public void setEmail(String Email) { this.Email = Email; }

    private String Address2;

    public String getAddress2() { return this.Address2; }

    public void setAddress2(String Address2) { this.Address2 = Address2; }

    private String Address1;

    public String getAddress1() { return this.Address1; }

    public void setAddress1(String Address1) { this.Address1 = Address1; }

    private int prefix;

    public int getPrefix() { return this.prefix; }

    public void setPrefix(int prefix) { this.prefix = prefix; }

    private String FirstName;

    public String getFirstName() { return this.FirstName; }

    public void setFirstName(String FirstName) { this.FirstName = FirstName; }

    private String CreditRecipient;

    public String getCreditRecipient() { return this.CreditRecipient; }

    public void setCreditRecipient(String CreditRecipient) { this.CreditRecipient = CreditRecipient; }

    private String Sex;

    public String getSex() { return this.Sex; }

    public void setSex(String Sex) { this.Sex = Sex; }
}

package com.optiontown.app.model.review;

import java.io.Serializable;

/**
 * Created by amit on 28-06-2016.
 */
public class UserDetails implements Serializable
{
    private String SelectUserId;
    private String BDay;
    private String BMonth;
    private String BYear;
    private String EmailId;
    private String FirstName;
    private String LastName;
    private String MiddleName;
    private String PrimaryPhoneCode;
    private String PrimaryPhoneNumber;
    private String CustomerId;
    private String PrefixId;
    private String FFPNumber;

    public String getFFPNumber() {
        return FFPNumber;
    }

    public void setFFPNumber(String FFPNumber) {
        this.FFPNumber = FFPNumber;
    }

    public String getSelectUserId() {
        return SelectUserId;
    }

    public void setSelectUserId(String selectUserId) {
        SelectUserId = selectUserId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getBDay() {
        return BDay;
    }

    public void setBDay(String BDay) {
        this.BDay = BDay;
    }

    public String getBMonth() {
        return BMonth;
    }

    public void setBMonth(String BMonth) {
        this.BMonth = BMonth;
    }

    public String getBYear() {
        return BYear;
    }

    public void setBYear(String BYear) {
        this.BYear = BYear;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getPrimaryPhoneCode() {
        return PrimaryPhoneCode;
    }

    public void setPrimaryPhoneCode(String primaryPhoneCode) {
        PrimaryPhoneCode = primaryPhoneCode;
    }

    public String getPrimaryPhoneNumber() {
        return PrimaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        PrimaryPhoneNumber = primaryPhoneNumber;
    }

    public String getPrefixId() {
        return PrefixId;
    }

    public void setPrefixId(String prefixId) {
        PrefixId = prefixId;
    }
}

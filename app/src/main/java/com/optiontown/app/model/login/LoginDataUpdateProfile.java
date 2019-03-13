package com.optiontown.app.model.login;

import java.io.Serializable;

/**
 * Created by amit on 01-07-2016.
 */
public class LoginDataUpdateProfile implements Serializable
{
    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    private String Result;

    private String message;

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    private CompleteProfile CompleteProfile;

    public CompleteProfile getCompleteProfile() { return this.CompleteProfile; }

    public void setCompleteProfile(CompleteProfile CompleteProfile) { this.CompleteProfile = CompleteProfile; }

    private ProfileDetails ProfileDetails;

    public ProfileDetails getProfileDetails() { return this.ProfileDetails; }

    public void setProfileDetails(ProfileDetails ProfileDetails) { this.ProfileDetails = ProfileDetails; }

    private int customerType;

    public int getCustomerType() { return this.customerType; }

    public void setCustomerType(int customerType) { this.customerType = customerType; }

    private int customerId;

    public int getCustomerId() { return this.customerId; }

    public void setCustomerId(int customerId) { this.customerId = customerId; }

    private String Email;

    public String getEmail() { return this.Email; }

    public void setEmail(String Email) { this.Email = Email; }

    private boolean isPasswrdValidationFail;

    public boolean getIsPasswrdValidationFail() { return this.isPasswrdValidationFail; }

    public void setIsPasswrdValidationFail(boolean isPasswrdValidationFail) { this.isPasswrdValidationFail = isPasswrdValidationFail; }

    private PasswordvalidationRequired PasswordvalidationRequired;

    public PasswordvalidationRequired getPasswordvalidationRequired() { return this.PasswordvalidationRequired; }

    public void setPasswordvalidationRequired(PasswordvalidationRequired PasswordvalidationRequired) { this.PasswordvalidationRequired = PasswordvalidationRequired; }

    private PasswordErrorList PasswordErrorList;

    public PasswordErrorList getPasswordErrorList() { return this.PasswordErrorList; }

    public void setPasswordErrorList(PasswordErrorList PasswordErrorList) { this.PasswordErrorList = PasswordErrorList; }
}

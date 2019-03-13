package com.optiontown.app.model.login;

import java.io.Serializable;

/**
 * Created by amit on 24-06-2016.
 */
public class LoginData implements Serializable
{
    public int getIsDisplayFFPNumber() {
        return isDisplayFFPNumber;
    }

    public void setIsDisplayFFPNumber(int isDisplayFFPNumber) {
        this.isDisplayFFPNumber = isDisplayFFPNumber;
    }

    private int isDisplayFFPNumber;

    private String FFpnumberErrorMessage;

    public String getFFpnumberErrorMessage() { return this.FFpnumberErrorMessage; }

    public void setFFpnumberErrorMessage(String FFpnumberErrorMessage) { this.FFpnumberErrorMessage = FFpnumberErrorMessage; }

    private String FFpnumberSortDesc;

    public String getFFpnumberSortDesc() { return this.FFpnumberSortDesc; }

    public void setFFpnumberSortDesc(String FFpnumberSortDesc) { this.FFpnumberSortDesc = FFpnumberSortDesc; }

    private int FFPNumberMandatory;

    public int getFFPNumberMandatory() {
        return FFPNumberMandatory;
    }

    public void setFFPNumberMandatory(int FFPNumberMandatory) {
        this.FFPNumberMandatory = FFPNumberMandatory;
    }

    private CompleteProfile CompleteProfile;

    public CompleteProfile getCompleteProfile() { return this.CompleteProfile; }

    public void setCompleteProfile(CompleteProfile CompleteProfile) { this.CompleteProfile = CompleteProfile; }

    private String Result;

    public String getResult() { return this.Result; }

    public void setResult(String Result) { this.Result = Result; }

    private int customerId;

    public int getCustomerId() { return this.customerId; }

    public void setCustomerId(int customerId) { this.customerId = customerId; }

    private int customerType;

    public int getCustomerType() { return this.customerType; }

    public void setCustomerType(int customerType) { this.customerType = customerType; }

    private String Email;

    public String getEmail() { return this.Email; }

    public void setEmail(String Email) { this.Email = Email; }

    private String sessionId;

    public String getSessionId() { return this.sessionId; }

    public void setSessionId(String sessionId) { this.sessionId = sessionId; }



    private ProfileDetails ProfileDetails;

    public ProfileDetails getProfileDetails() { return this.ProfileDetails; }

    public void setProfileDetails(ProfileDetails ProfileDetails) { this.ProfileDetails = ProfileDetails; }

    private PasswordErrorList PasswordErrorList;

    public PasswordErrorList getPasswordErrorList() { return this.PasswordErrorList; }

    public void setPasswordErrorList(PasswordErrorList PasswordErrorList) { this.PasswordErrorList = PasswordErrorList; }

    private String Message;

    public String getMessage() { return this.Message; }

    public void setMessage(String Message) { this.Message = Message; }



    private boolean isPasswrdValidationFail;

    public boolean getIsPasswrdValidationFail() { return this.isPasswrdValidationFail; }

    public void setIsPasswrdValidationFail(boolean isPasswrdValidationFail) { this.isPasswrdValidationFail = isPasswrdValidationFail; }

    private PasswordvalidationRequired PasswordvalidationRequired;

    public PasswordvalidationRequired getPasswordvalidationRequired() { return this.PasswordvalidationRequired; }

    public void setPasswordvalidationRequired(PasswordvalidationRequired PasswordvalidationRequired) { this.PasswordvalidationRequired = PasswordvalidationRequired; }

    public String getPasswdValidationRequired() {
        return passwdValidationRequired;
    }

    public void setPasswdValidationRequired(String passwdValidationRequired) {
        this.passwdValidationRequired = passwdValidationRequired;
    }

    public String passwdValidationRequired;

    public String getPasswordHelpMsg() {
        return passwordHelpMsg;
    }

    public String passwordHelpMsg;

    public void setPasswordHelpMsg(String passwordHelpMsg) {
        this.passwordHelpMsg = passwordHelpMsg;
    }
}

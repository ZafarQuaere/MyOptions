
package com.optiontown.app.model.redeem.mmp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserData implements Serializable{

    @SerializedName("Extension_List")
    @Expose
    private List<ExtensionList> extensionList = new ArrayList<ExtensionList>();
    @SerializedName("Country_Label")
    @Expose
    private String countryLabel;
    @SerializedName("Email_Label")
    @Expose
    private String emailLabel;
    @SerializedName("Primary_Phone_Label")
    @Expose
    private String primaryPhoneLabel;
    @SerializedName("Save_Label")
    @Expose
    private String saveLabel;
    @SerializedName("Select_Pass_Label")
    @Expose
    private String selectPassLabel;
    @SerializedName("Pass_Users")
    @Expose
    private List<PassUser> passUsers = new ArrayList<PassUser>();
    @SerializedName("Other_Label")
    @Expose
    private String otherLabel;
    @SerializedName("Cancel_Label")
    @Expose
    private String cancelLabel;
    @SerializedName("Country_List")
    @Expose
    private List<CountryList> countryList = new ArrayList<CountryList>();
    @SerializedName("Mandatory_Label")
    @Expose
    private String mandatoryLabel;
    @SerializedName("Number_Label")
    @Expose
    private String numberLabel;
    @SerializedName("Select_Passanger_Label")
    @Expose
    private String selectPassangerLabel;

    @SerializedName("isDisplayFFPNumber")
    @Expose
    private int isDisplayFFPNumber;

    @SerializedName("FFPNumberMandatory")
    @Expose
    private int FFPNumberMandatory;

    @SerializedName("FFpnumberErrorMessage")
    @Expose
    private String FFpnumberErrorMessage;
    @SerializedName("FFpnumberHelpMessage")
    @Expose
    private String FFpnumberHelpMessage;

    @SerializedName("FFpnumberSortDesc")
    @Expose
    private String FFpnumberSortDesc;

    public String getPassanger_Label() {
        return Passanger_Label;
    }

    public void setPassanger_Label(String passanger_Label) {
        Passanger_Label = passanger_Label;
    }

    @SerializedName("Passanger_Label")
    @Expose
    private String Passanger_Label;
    public int getIsDisplayFFPNumber() {
        return isDisplayFFPNumber;
    }

    public void setIsDisplayFFPNumber(int isDisplayFFPNumber) {
        this.isDisplayFFPNumber = isDisplayFFPNumber;
    }

    public int getFFPNumberMandatory() {
        return FFPNumberMandatory;
    }

    public void setFFPNumberMandatory(int FFPNumberMandatory) {
        this.FFPNumberMandatory = FFPNumberMandatory;
    }

    public String getFFpnumberErrorMessage() {
        return FFpnumberErrorMessage;
    }

    public void setFFpnumberErrorMessage(String FFpnumberErrorMessage) {
        this.FFpnumberErrorMessage = FFpnumberErrorMessage;
    }

    public String getFFpnumberHelpMessage() {
        return FFpnumberHelpMessage;
    }

    public void setFFpnumberHelpMessage(String FFpnumberHelpMessage) {
        this.FFpnumberHelpMessage = FFpnumberHelpMessage;
    }

    public String getFFpnumberSortDesc() {
        return FFpnumberSortDesc;
    }

    public void setFFpnumberSortDesc(String FFpnumberSortDesc) {
        this.FFpnumberSortDesc = FFpnumberSortDesc;
    }

    /**
     * 
     * @return
     *     The extensionList
     */
    public List<ExtensionList> getExtensionList() {
        return extensionList;
    }

    /**
     * 
     * @param extensionList
     *     The Extension_List
     */
    public void setExtensionList(List<ExtensionList> extensionList) {
        this.extensionList = extensionList;
    }

    /**
     * 
     * @return
     *     The countryLabel
     */
    public String getCountryLabel() {
        return countryLabel;
    }

    /**
     * 
     * @param countryLabel
     *     The Country_Label
     */
    public void setCountryLabel(String countryLabel) {
        this.countryLabel = countryLabel;
    }

    /**
     * 
     * @return
     *     The emailLabel
     */
    public String getEmailLabel() {
        return emailLabel;
    }

    /**
     * 
     * @param emailLabel
     *     The Email_Label
     */
    public void setEmailLabel(String emailLabel) {
        this.emailLabel = emailLabel;
    }

    /**
     * 
     * @return
     *     The primaryPhoneLabel
     */
    public String getPrimaryPhoneLabel() {
        return primaryPhoneLabel;
    }

    /**
     * 
     * @param primaryPhoneLabel
     *     The Primary_Phone_Label
     */
    public void setPrimaryPhoneLabel(String primaryPhoneLabel) {
        this.primaryPhoneLabel = primaryPhoneLabel;
    }

    /**
     * 
     * @return
     *     The saveLabel
     */
    public String getSaveLabel() {
        return saveLabel;
    }

    /**
     * 
     * @param saveLabel
     *     The Save_Label
     */
    public void setSaveLabel(String saveLabel) {
        this.saveLabel = saveLabel;
    }

    /**
     * 
     * @return
     *     The selectPassLabel
     */
    public String getSelectPassLabel() {
        return selectPassLabel;
    }

    /**
     * 
     * @param selectPassLabel
     *     The Select_Pass_Label
     */
    public void setSelectPassLabel(String selectPassLabel) {
        this.selectPassLabel = selectPassLabel;
    }

    /**
     * 
     * @return
     *     The passUsers
     */
    public List<PassUser> getPassUsers() {
        return passUsers;
    }

    /**
     * 
     * @param passUsers
     *     The Pass_Users
     */
    public void setPassUsers(List<PassUser> passUsers) {
        this.passUsers = passUsers;
    }

    /**
     * 
     * @return
     *     The otherLabel
     */
    public String getOtherLabel() {
        return otherLabel;
    }

    /**
     * 
     * @param otherLabel
     *     The Other_Label
     */
    public void setOtherLabel(String otherLabel) {
        this.otherLabel = otherLabel;
    }

    /**
     * 
     * @return
     *     The cancelLabel
     */
    public String getCancelLabel() {
        return cancelLabel;
    }

    /**
     * 
     * @param cancelLabel
     *     The Cancel_Label
     */
    public void setCancelLabel(String cancelLabel) {
        this.cancelLabel = cancelLabel;
    }

    /**
     * 
     * @return
     *     The countryList
     */
    public List<CountryList> getCountryList() {
        return countryList;
    }

    /**
     * 
     * @param countryList
     *     The Country_List
     */
    public void setCountryList(List<CountryList> countryList) {
        this.countryList = countryList;
    }

    /**
     * 
     * @return
     *     The mandatoryLabel
     */
    public String getMandatoryLabel() {
        return mandatoryLabel;
    }

    /**
     * 
     * @param mandatoryLabel
     *     The Mandatory_Label
     */
    public void setMandatoryLabel(String mandatoryLabel) {
        this.mandatoryLabel = mandatoryLabel;
    }

    /**
     * 
     * @return
     *     The numberLabel
     */
    public String getNumberLabel() {
        return numberLabel;
    }

    /**
     * 
     * @param numberLabel
     *     The Number_Label
     */
    public void setNumberLabel(String numberLabel) {
        this.numberLabel = numberLabel;
    }

    /**
     * 
     * @return
     *     The selectPassangerLabel
     */
    public String getSelectPassangerLabel() {
        return selectPassangerLabel;
    }

    /**
     * 
     * @param selectPassangerLabel
     *     The Select_Passanger_Label
     */
    public void setSelectPassangerLabel(String selectPassangerLabel) {
        this.selectPassangerLabel = selectPassangerLabel;
    }

}

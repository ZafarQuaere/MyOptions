
package com.optiontown.app.model.legviewdetails;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewDetails {

    @SerializedName("PassengersFullList")
    @Expose
    private List<PassengersFullList> passengersFullList = new ArrayList<PassengersFullList>();
    @SerializedName("Passengers_Label")
    @Expose
    private String passengersLabel;
    @SerializedName("Airline")
    @Expose
    private String airline;
    @SerializedName("Confirmation_Number")
    @Expose
    private String confirmationNumber;
    @SerializedName("Confirmation_Label")
    @Expose
    private String confirmationLabel;
    @SerializedName("ContactEmail")
    @Expose
    private String contactEmail;
    @SerializedName("Primary_Phone_Label")
    @Expose
    private String primaryPhoneLabel;
    @SerializedName("UtoRules")
    @Expose
    private UtoRules utoRules;
    @SerializedName("Pnr")
    @Expose
    private String pnr;
    @SerializedName("Transaction_History_Heading")
    @Expose
    private String transactionHistoryHeading;
    @SerializedName("Formatted_Phone_Number")
    @Expose
    private String formattedPhoneNumber;
    @SerializedName("Formatted_Other_Phone_Number")
    @Expose
    private String formattedOtherPhoneNumber;
    @SerializedName("Pnr_Label")
    @Expose
    private String pnrLabel;
    @SerializedName("LABL_Contact_Email_Label")
    @Expose
    private String lABLContactEmailLabel;
    @SerializedName("transactionList")
    @Expose
    private ArrayList<TransactionList> transactionList = new ArrayList<TransactionList>();
    @SerializedName("marketingAirline")
    @Expose
    private String marketingAirline;
    @SerializedName("Other_Phone_Label")
    @Expose
    private String otherPhoneLabel;

    /**
     *
     * @return
     * The passengersFullList
     */
    public List<PassengersFullList> getPassengersFullList() {
        return passengersFullList;
    }

    /**
     *
     * @param passengersFullList
     * The PassengersFullList
     */
    public void setPassengersFullList(List<PassengersFullList> passengersFullList) {
        this.passengersFullList = passengersFullList;
    }

    /**
     *
     * @return
     * The passengersLabel
     */
    public String getPassengersLabel() {
        return passengersLabel;
    }

    /**
     *
     * @param passengersLabel
     * The Passengers_Label
     */
    public void setPassengersLabel(String passengersLabel) {
        this.passengersLabel = passengersLabel;
    }

    /**
     *
     * @return
     * The airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     *
     * @param airline
     * The Airline
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     *
     * @return
     * The confirmationNumber
     */
    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     *
     * @param confirmationNumber
     * The Confirmation_Number
     */
    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    /**
     *
     * @return
     * The confirmationLabel
     */
    public String getConfirmationLabel() {
        return confirmationLabel;
    }

    /**
     *
     * @param confirmationLabel
     * The Confirmation_Label
     */
    public void setConfirmationLabel(String confirmationLabel) {
        this.confirmationLabel = confirmationLabel;
    }

    /**
     *
     * @return
     * The contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     *
     * @param contactEmail
     * The ContactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     *
     * @return
     * The primaryPhoneLabel
     */
    public String getPrimaryPhoneLabel() {
        return primaryPhoneLabel;
    }

    /**
     *
     * @param primaryPhoneLabel
     * The Primary_Phone_Label
     */
    public void setPrimaryPhoneLabel(String primaryPhoneLabel) {
        this.primaryPhoneLabel = primaryPhoneLabel;
    }

    /**
     *
     * @return
     * The utoRules
     */
    public UtoRules getUtoRules() {
        return utoRules;
    }

    /**
     *
     * @param utoRules
     * The UtoRules
     */
    public void setUtoRules(UtoRules utoRules) {
        this.utoRules = utoRules;
    }

    /**
     *
     * @return
     * The pnr
     */
    public String getPnr() {
        return pnr;
    }

    /**
     *
     * @param pnr
     * The Pnr
     */
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    /**
     *
     * @return
     * The transactionHistoryHeading
     */
    public String getTransactionHistoryHeading() {
        return transactionHistoryHeading;
    }

    /**
     *
     * @param transactionHistoryHeading
     * The Transaction_History_Heading
     */
    public void setTransactionHistoryHeading(String transactionHistoryHeading) {
        this.transactionHistoryHeading = transactionHistoryHeading;
    }

    /**
     *
     * @return
     * The formattedPhoneNumber
     */
    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    /**
     *
     * @param formattedPhoneNumber
     * The Formatted_Phone_Number
     */
    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    /**
     *
     * @return
     * The formattedOtherPhoneNumber
     */
    public String getFormattedOtherPhoneNumber() {
        return formattedOtherPhoneNumber;
    }

    /**
     *
     * @param formattedOtherPhoneNumber
     * The Formatted_Other_Phone_Number
     */
    public void setFormattedOtherPhoneNumber(String formattedOtherPhoneNumber) {
        this.formattedOtherPhoneNumber = formattedOtherPhoneNumber;
    }

    /**
     *
     * @return
     * The pnrLabel
     */
    public String getPnrLabel() {
        return pnrLabel;
    }

    /**
     *
     * @param pnrLabel
     * The Pnr_Label
     */
    public void setPnrLabel(String pnrLabel) {
        this.pnrLabel = pnrLabel;
    }

    /**
     *
     * @return
     * The lABLContactEmailLabel
     */
    public String getLABLContactEmailLabel() {
        return lABLContactEmailLabel;
    }

    /**
     *
     * @param lABLContactEmailLabel
     * The LABL_Contact_Email_Label
     */
    public void setLABLContactEmailLabel(String lABLContactEmailLabel) {
        this.lABLContactEmailLabel = lABLContactEmailLabel;
    }

    /**
     *
     * @return
     * The transactionList
     */
    public ArrayList<TransactionList> getTransactionList() {
        return transactionList;
    }

    /**
     *
     * @param transactionList
     * The transactionList
     */
    public void setTransactionList(ArrayList<TransactionList> transactionList) {
        this.transactionList = transactionList;
    }

    /**
     *
     * @return
     * The marketingAirline
     */
    public String getMarketingAirline() {
        return marketingAirline;
    }

    /**
     *
     * @param marketingAirline
     * The marketingAirline
     */
    public void setMarketingAirline(String marketingAirline) {
        this.marketingAirline = marketingAirline;
    }

    /**
     *
     * @return
     * The otherPhoneLabel
     */
    public String getOtherPhoneLabel() {
        return otherPhoneLabel;
    }

    /**
     *
     * @param otherPhoneLabel
     * The Other_Phone_Label
     */
    public void setOtherPhoneLabel(String otherPhoneLabel) {
        this.otherPhoneLabel = otherPhoneLabel;
    }

}
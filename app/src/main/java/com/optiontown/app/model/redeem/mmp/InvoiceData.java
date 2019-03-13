
package com.optiontown.app.model.redeem.mmp;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoiceData {

    @SerializedName("FlightsList")
    @Expose
    private List<FlightsList> flightsList = new ArrayList<FlightsList>();
    @SerializedName("City_Label")
    @Expose
    private String cityLabel;
    @SerializedName("Country_Label")
    @Expose
    private String countryLabel;
    @SerializedName("Address_Line1_Label")
    @Expose
    private String addressLine1Label;
    @SerializedName("Email_Label")
    @Expose
    private String emailLabel;
    @SerializedName("Invoice_Label")
    @Expose
    private String invoiceLabel;
    @SerializedName("Name_on_invoice_Label")
    @Expose
    private String nameOnInvoiceLabel;
    @SerializedName("Address_Line2_Label")
    @Expose
    private String addressLine2Label;
    @SerializedName("Zip_Postal")
    @Expose
    private String zipPostal;
    @SerializedName("CountryList")
    @Expose
    private ArrayList<CountryList> countryList = new ArrayList<CountryList>();
    @SerializedName("Billing_Address_Label")
    @Expose
    private String billingAddressLabel;
    @SerializedName("Flight_Pass_Label")
    @Expose
    private String flightPassLabel;
    @SerializedName("State_Province_Label")
    @Expose
    private String stateProvinceLabel;
    @SerializedName("Heading_Label")
    @Expose
    private String headingLabel;
    @SerializedName("Submit_Label")
    @Expose
    private String submitLabel;

    public String getMandatory_Label() {
        return Mandatory_Label;
    }

    public void setMandatory_Label(String mandatory_Label) {
        Mandatory_Label = mandatory_Label;
    }

    @SerializedName("Mandatory_Label")
    @Expose
    private String Mandatory_Label;

    /**
     * 
     * @return
     *     The flightsList
     */
    public List<FlightsList> getFlightsList() {
        return flightsList;
    }

    /**
     * 
     * @param flightsList
     *     The FlightsList
     */
    public void setFlightsList(List<FlightsList> flightsList) {
        this.flightsList = flightsList;
    }

    /**
     * 
     * @return
     *     The cityLabel
     */
    public String getCityLabel() {
        return cityLabel;
    }

    /**
     * 
     * @param cityLabel
     *     The City_Label
     */
    public void setCityLabel(String cityLabel) {
        this.cityLabel = cityLabel;
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
     *     The addressLine1Label
     */
    public String getAddressLine1Label() {
        return addressLine1Label;
    }

    /**
     * 
     * @param addressLine1Label
     *     The Address_Line1_Label
     */
    public void setAddressLine1Label(String addressLine1Label) {
        this.addressLine1Label = addressLine1Label;
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
     *     The invoiceLabel
     */
    public String getInvoiceLabel() {
        return invoiceLabel;
    }

    /**
     * 
     * @param invoiceLabel
     *     The Invoice_Label
     */
    public void setInvoiceLabel(String invoiceLabel) {
        this.invoiceLabel = invoiceLabel;
    }

    /**
     * 
     * @return
     *     The nameOnInvoiceLabel
     */
    public String getNameOnInvoiceLabel() {
        return nameOnInvoiceLabel;
    }

    /**
     * 
     * @param nameOnInvoiceLabel
     *     The Name_on_invoice_Label
     */
    public void setNameOnInvoiceLabel(String nameOnInvoiceLabel) {
        this.nameOnInvoiceLabel = nameOnInvoiceLabel;
    }

    /**
     * 
     * @return
     *     The addressLine2Label
     */
    public String getAddressLine2Label() {
        return addressLine2Label;
    }

    /**
     * 
     * @param addressLine2Label
     *     The Address_Line2_Label
     */
    public void setAddressLine2Label(String addressLine2Label) {
        this.addressLine2Label = addressLine2Label;
    }

    /**
     * 
     * @return
     *     The zipPostal
     */
    public String getZipPostal() {
        return zipPostal;
    }

    /**
     * 
     * @param zipPostal
     *     The Zip_Postal
     */
    public void setZipPostal(String zipPostal) {
        this.zipPostal = zipPostal;
    }

    /**
     * 
     * @return
     *     The countryList
     */
    public ArrayList<CountryList> getCountryList() {
        return countryList;
    }

    /**
     * 
     * @param countryList
     *     The CountryList
     */
    public void setCountryList(ArrayList<CountryList> countryList) {
        this.countryList = countryList;
    }

    /**
     * 
     * @return
     *     The billingAddressLabel
     */
    public String getBillingAddressLabel() {
        return billingAddressLabel;
    }

    /**
     * 
     * @param billingAddressLabel
     *     The Billing_Address_Label
     */
    public void setBillingAddressLabel(String billingAddressLabel) {
        this.billingAddressLabel = billingAddressLabel;
    }

    /**
     * 
     * @return
     *     The flightPassLabel
     */
    public String getFlightPassLabel() {
        return flightPassLabel;
    }

    /**
     * 
     * @param flightPassLabel
     *     The Flight_Pass_Label
     */
    public void setFlightPassLabel(String flightPassLabel) {
        this.flightPassLabel = flightPassLabel;
    }

    /**
     * 
     * @return
     *     The stateProvinceLabel
     */
    public String getStateProvinceLabel() {
        return stateProvinceLabel;
    }

    /**
     * 
     * @param stateProvinceLabel
     *     The State_Province_Label
     */
    public void setStateProvinceLabel(String stateProvinceLabel) {
        this.stateProvinceLabel = stateProvinceLabel;
    }

    /**
     * 
     * @return
     *     The headingLabel
     */
    public String getHeadingLabel() {
        return headingLabel;
    }

    /**
     * 
     * @param headingLabel
     *     The Heading_Label
     */
    public void setHeadingLabel(String headingLabel) {
        this.headingLabel = headingLabel;
    }

    /**
     * 
     * @return
     *     The submitLabel
     */
    public String getSubmitLabel() {
        return submitLabel;
    }

    /**
     * 
     * @param submitLabel
     *     The Submit_Label
     */
    public void setSubmitLabel(String submitLabel) {
        this.submitLabel = submitLabel;
    }

}

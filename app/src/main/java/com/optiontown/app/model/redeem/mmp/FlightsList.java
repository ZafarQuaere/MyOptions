
package com.optiontown.app.model.redeem.mmp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FlightsList implements Serializable{

    @SerializedName("Invoice_name_billing_address_Label")
    @Expose
    private String invoiceNameBillingAddressLabel;
    @SerializedName("Invoice_sent")
    @Expose
    private Boolean invoiceSent;
    @SerializedName("CustomerZipCode")
    @Expose
    private String customerZipCode;
    @SerializedName("CustomerCity")
    @Expose
    private String customerCity;
    @SerializedName("InvoiceDate")
    @Expose
    private String invoiceDate;
    @SerializedName("CustomerAdd1")
    @Expose
    private String customerAdd1;
    @SerializedName("CustomerAdd2")
    @Expose
    private String customerAdd2;
    @SerializedName("Label")
    @Expose
    private String label;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("CustomerState")
    @Expose
    private String customerState;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("CustomerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("CustomerCountry")
    @Expose
    private String customerCountry;
    @SerializedName("Invoice_Sent_Label")
    @Expose
    private String Invoice_Sent_Label;
    @SerializedName("Submit_Label")
    @Expose
    private String Submit_Label;
    public String getPassId() {
        return passId;
    }

    public void setPassId(String passId) {
        this.passId = passId;
    }

    private String passId;
    public String getCustomerCountryID() {
        return CustomerCountryID;
    }

    public void setCustomerCountryID(String customerCountryID) {
        CustomerCountryID = customerCountryID;
    }

    @SerializedName("CustomerCountryID")
    @Expose
    private String CustomerCountryID;


    public String getSubmit_Label() {
        return Submit_Label;
    }

    public void setSubmit_Label(String submit_Label) {
        Submit_Label = submit_Label;
    }

    public String getInvoice_Sent_Label() {
        return Invoice_Sent_Label;
    }

    public void setInvoice_Sent_Label(String invoice_Sent_Label) {
        Invoice_Sent_Label = invoice_Sent_Label;
    }




    /**
     * 
     * @return
     *     The invoiceNameBillingAddressLabel
     */
    public String getInvoiceNameBillingAddressLabel() {
        return invoiceNameBillingAddressLabel;
    }

    /**
     * 
     * @param invoiceNameBillingAddressLabel
     *     The Invoice_name_billing_address_Label
     */
    public void setInvoiceNameBillingAddressLabel(String invoiceNameBillingAddressLabel) {
        this.invoiceNameBillingAddressLabel = invoiceNameBillingAddressLabel;
    }

    /**
     * 
     * @return
     *     The invoiceSent
     */
    public Boolean getInvoiceSent() {
        return invoiceSent;
    }

    /**
     * 
     * @param invoiceSent
     *     The Invoice_sent
     */
    public void setInvoiceSent(Boolean invoiceSent) {
        this.invoiceSent = invoiceSent;
    }

    /**
     * 
     * @return
     *     The customerZipCode
     */
    public String getCustomerZipCode() {
        return customerZipCode;
    }

    /**
     * 
     * @param customerZipCode
     *     The CustomerZipCode
     */
    public void setCustomerZipCode(String customerZipCode) {
        this.customerZipCode = customerZipCode;
    }

    /**
     * 
     * @return
     *     The customerCity
     */
    public String getCustomerCity() {
        return customerCity;
    }

    /**
     * 
     * @param customerCity
     *     The CustomerCity
     */
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    /**
     * 
     * @return
     *     The invoiceDate
     */
    public String getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * 
     * @param invoiceDate
     *     The InvoiceDate
     */
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * 
     * @return
     *     The customerAdd1
     */
    public String getCustomerAdd1() {
        return customerAdd1;
    }

    /**
     * 
     * @param customerAdd1
     *     The CustomerAdd1
     */
    public void setCustomerAdd1(String customerAdd1) {
        this.customerAdd1 = customerAdd1;
    }

    /**
     * 
     * @return
     *     The customerAdd2
     */
    public String getCustomerAdd2() {
        return customerAdd2;
    }

    /**
     * 
     * @param customerAdd2
     *     The CustomerAdd2
     */
    public void setCustomerAdd2(String customerAdd2) {
        this.customerAdd2 = customerAdd2;
    }

    /**
     * 
     * @return
     *     The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The Label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The customerState
     */
    public String getCustomerState() {
        return customerState;
    }

    /**
     * 
     * @param customerState
     *     The CustomerState
     */
    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    /**
     * 
     * @return
     *     The customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 
     * @param customerName
     *     The CustomerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 
     * @return
     *     The customerEmail
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * 
     * @param customerEmail
     *     The CustomerEmail
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * 
     * @return
     *     The customerCountry
     */
    public String getCustomerCountry() {
        return customerCountry;
    }

    /**
     * 
     * @param customerCountry
     *     The CustomerCountry
     */
    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

}

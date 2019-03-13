
package com.optiontown.app.model.legreview;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ReviewDetails {

    @SerializedName("LABL_Telephone_Primary_Label")
    @Expose
    private String lABLTelephonePrimaryLabel;
    @SerializedName("LABL_Other_Phone_Label")
    @Expose
    private String lABLOtherPhoneLabel;
    @SerializedName("getMTListData")
    @Expose
    private List<GetMTListDatum> getMTListData = new ArrayList<GetMTListDatum>();
    @SerializedName("Contact_Message")
    @Expose
    private String contactMessage;
    @SerializedName("paymentDetail")
    @Expose
    private PaymentDetail paymentDetail;
    @SerializedName("priceSummary")
    @Expose
    private PriceSummary priceSummary;
    @SerializedName("LABL_Contact_Email_Label")
    @Expose
    private String lABLContactEmailLabel;

    /**
     *
     * @return
     *     The lABLTelephonePrimaryLabel
     */
    public String getLABLTelephonePrimaryLabel() {
        return lABLTelephonePrimaryLabel;
    }

    /**
     *
     * @param lABLTelephonePrimaryLabel
     *     The LABL_Telephone_Primary_Label
     */
    public void setLABLTelephonePrimaryLabel(String lABLTelephonePrimaryLabel) {
        this.lABLTelephonePrimaryLabel = lABLTelephonePrimaryLabel;
    }

    /**
     *
     * @return
     *     The lABLOtherPhoneLabel
     */
    public String getLABLOtherPhoneLabel() {
        return lABLOtherPhoneLabel;
    }

    /**
     *
     * @param lABLOtherPhoneLabel
     *     The LABL_Other_Phone_Label
     */
    public void setLABLOtherPhoneLabel(String lABLOtherPhoneLabel) {
        this.lABLOtherPhoneLabel = lABLOtherPhoneLabel;
    }

    /**
     *
     * @return
     *     The getMTListData
     */
    public List<GetMTListDatum> getGetMTListData() {
        return getMTListData;
    }

    /**
     *
     * @param getMTListData
     *     The getMTListData
     */
    public void setGetMTListData(List<GetMTListDatum> getMTListData) {
        this.getMTListData = getMTListData;
    }

    /**
     *
     * @return
     *     The contactMessage
     */
    public String getContactMessage() {
        return contactMessage;
    }

    /**
     *
     * @param contactMessage
     *     The Contact_Message
     */
    public void setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
    }

    /**
     *
     * @return
     *     The paymentDetail
     */
    public PaymentDetail getPaymentDetail() {
        return paymentDetail;
    }

    /**
     *
     * @param paymentDetail
     *     The paymentDetail
     */
    public void setPaymentDetail(PaymentDetail paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    /**
     *
     * @return
     *     The priceSummary
     */
    public PriceSummary getPriceSummary() {
        return priceSummary;
    }

    /**
     *
     * @param priceSummary
     *     The priceSummary
     */
    public void setPriceSummary(PriceSummary priceSummary) {
        this.priceSummary = priceSummary;
    }

    /**
     *
     * @return
     *     The lABLContactEmailLabel
     */
    public String getLABLContactEmailLabel() {
        return lABLContactEmailLabel;
    }

    /**
     *
     * @param lABLContactEmailLabel
     *     The LABL_Contact_Email_Label
     */
    public void setLABLContactEmailLabel(String lABLContactEmailLabel) {
        this.lABLContactEmailLabel = lABLContactEmailLabel;
    }


}

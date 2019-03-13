
package com.optiontown.app.model.redeem.mybooking;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyBookings {

    @SerializedName("pendingBookings")
    @Expose
    private List<ConfirmedBooking> pendingBookings = new ArrayList<ConfirmedBooking>();
    @SerializedName("LABL_My_Booking_FAQs_Ans1_label")
    @Expose
    private String lABLMyBookingFAQsAns1Label;
    @SerializedName("LABL_My_Booking_FAQs_Qst2_label")
    @Expose
    private String lABLMyBookingFAQsQst2Label;
    @SerializedName("labels")
    @Expose
    private Labels labels;
    @SerializedName("LABL_My_Booking_FAQs_Qst1_label")
    @Expose
    private String lABLMyBookingFAQsQst1Label;
    @SerializedName("LABL_My_Booking_FAQs_Ans2_label")
    @Expose
    private String lABLMyBookingFAQsAns2Label;
    @SerializedName("confirmedBooking")
    @Expose
    private List<ConfirmedBooking> confirmedBooking = new ArrayList<ConfirmedBooking>();
    @SerializedName("LABL_FAQ_Label")
    @Expose
    private String lABLFAQLabel;
    @SerializedName("LABL_My_Booking_FAQs_Qst3_label")
    @Expose
    private String lABLMyBookingFAQsQst3Label;
    @SerializedName("LABL_My_Booking_FAQs_Ans3_label")
    @Expose
    private String lABLMyBookingFAQsAns3Label;

    /**
     * 
     * @return
     *     The pendingBookings
     */
    public List<ConfirmedBooking> getPendingBookings() {
        return pendingBookings;
    }

    /**
     * 
     * @param pendingBookings
     *     The pendingBookings
     */
    public void setPendingBookings(List<ConfirmedBooking> pendingBookings) {
        this.pendingBookings = pendingBookings;
    }

    /**
     * 
     * @return
     *     The lABLMyBookingFAQsAns1Label
     */
    public String getLABLMyBookingFAQsAns1Label() {
        return lABLMyBookingFAQsAns1Label;
    }

    /**
     * 
     * @param lABLMyBookingFAQsAns1Label
     *     The LABL_My_Booking_FAQs_Ans1_label
     */
    public void setLABLMyBookingFAQsAns1Label(String lABLMyBookingFAQsAns1Label) {
        this.lABLMyBookingFAQsAns1Label = lABLMyBookingFAQsAns1Label;
    }

    /**
     * 
     * @return
     *     The lABLMyBookingFAQsQst2Label
     */
    public String getLABLMyBookingFAQsQst2Label() {
        return lABLMyBookingFAQsQst2Label;
    }

    /**
     * 
     * @param lABLMyBookingFAQsQst2Label
     *     The LABL_My_Booking_FAQs_Qst2_label
     */
    public void setLABLMyBookingFAQsQst2Label(String lABLMyBookingFAQsQst2Label) {
        this.lABLMyBookingFAQsQst2Label = lABLMyBookingFAQsQst2Label;
    }

    /**
     * 
     * @return
     *     The labels
     */
    public Labels getLabels() {
        return labels;
    }

    /**
     * 
     * @param labels
     *     The labels
     */
    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    /**
     * 
     * @return
     *     The lABLMyBookingFAQsQst1Label
     */
    public String getLABLMyBookingFAQsQst1Label() {
        return lABLMyBookingFAQsQst1Label;
    }

    /**
     * 
     * @param lABLMyBookingFAQsQst1Label
     *     The LABL_My_Booking_FAQs_Qst1_label
     */
    public void setLABLMyBookingFAQsQst1Label(String lABLMyBookingFAQsQst1Label) {
        this.lABLMyBookingFAQsQst1Label = lABLMyBookingFAQsQst1Label;
    }

    /**
     * 
     * @return
     *     The lABLMyBookingFAQsAns2Label
     */
    public String getLABLMyBookingFAQsAns2Label() {
        return lABLMyBookingFAQsAns2Label;
    }

    /**
     * 
     * @param lABLMyBookingFAQsAns2Label
     *     The LABL_My_Booking_FAQs_Ans2_label
     */
    public void setLABLMyBookingFAQsAns2Label(String lABLMyBookingFAQsAns2Label) {
        this.lABLMyBookingFAQsAns2Label = lABLMyBookingFAQsAns2Label;
    }

    /**
     * 
     * @return
     *     The confirmedBooking
     */
    public List<ConfirmedBooking> getConfirmedBooking() {
        return confirmedBooking;
    }

    /**
     * 
     * @param confirmedBooking
     *     The confirmedBooking
     */
    public void setConfirmedBooking(List<ConfirmedBooking> confirmedBooking) {
        this.confirmedBooking = confirmedBooking;
    }

    /**
     * 
     * @return
     *     The lABLFAQLabel
     */
    public String getLABLFAQLabel() {
        return lABLFAQLabel;
    }

    /**
     * 
     * @param lABLFAQLabel
     *     The LABL_FAQ_Label
     */
    public void setLABLFAQLabel(String lABLFAQLabel) {
        this.lABLFAQLabel = lABLFAQLabel;
    }

    /**
     * 
     * @return
     *     The lABLMyBookingFAQsQst3Label
     */
    public String getLABLMyBookingFAQsQst3Label() {
        return lABLMyBookingFAQsQst3Label;
    }

    /**
     * 
     * @param lABLMyBookingFAQsQst3Label
     *     The LABL_My_Booking_FAQs_Qst3_label
     */
    public void setLABLMyBookingFAQsQst3Label(String lABLMyBookingFAQsQst3Label) {
        this.lABLMyBookingFAQsQst3Label = lABLMyBookingFAQsQst3Label;
    }

    /**
     * 
     * @return
     *     The lABLMyBookingFAQsAns3Label
     */
    public String getLABLMyBookingFAQsAns3Label() {
        return lABLMyBookingFAQsAns3Label;
    }

    /**
     * 
     * @param lABLMyBookingFAQsAns3Label
     *     The LABL_My_Booking_FAQs_Ans3_label
     */
    public void setLABLMyBookingFAQsAns3Label(String lABLMyBookingFAQsAns3Label) {
        this.lABLMyBookingFAQsAns3Label = lABLMyBookingFAQsAns3Label;
    }

}

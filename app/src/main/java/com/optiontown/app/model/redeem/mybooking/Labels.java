
package com.optiontown.app.model.redeem.mybooking;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Labels {

    @SerializedName("LABL_Optiontown_Flight_Pass_Label")
    @Expose
    private String lABLOptiontownFlightPassLabel;
    @SerializedName("LABL_Pending_Booking_Label")
    @Expose
    private String lABLPendingBookingLabel;
    @SerializedName("LABL_Booking_Trips_Label")
    @Expose
    private String lABLBookingTripsLabel;
    @SerializedName("LABL_No_Bookings_Desc_Label")
    @Expose
    private String lABLNoBookingsDescLabel;
    @SerializedName("Faq_Label")
    @Expose
    private String faqLabel;
    @SerializedName("ViewDetail_label")
    @Expose
    private String viewDetailLabel;

    public String getLABL_Airline_Label() {
        return LABL_Airline_Label;
    }

    public void setLABL_Airline_Label(String LABL_Airline_Label) {
        this.LABL_Airline_Label = LABL_Airline_Label;
    }

    @SerializedName("LABL_Airline_Label")
    @Expose
    private String LABL_Airline_Label;


    /**
     * 
     * @return
     *     The lABLOptiontownFlightPassLabel
     */
    public String getLABLOptiontownFlightPassLabel() {
        return lABLOptiontownFlightPassLabel;
    }

    /**
     * 
     * @param lABLOptiontownFlightPassLabel
     *     The LABL_Optiontown_Flight_Pass_Label
     */
    public void setLABLOptiontownFlightPassLabel(String lABLOptiontownFlightPassLabel) {
        this.lABLOptiontownFlightPassLabel = lABLOptiontownFlightPassLabel;
    }

    /**
     * 
     * @return
     *     The lABLPendingBookingLabel
     */
    public String getLABLPendingBookingLabel() {
        return lABLPendingBookingLabel;
    }

    /**
     * 
     * @param lABLPendingBookingLabel
     *     The LABL_Pending_Booking_Label
     */
    public void setLABLPendingBookingLabel(String lABLPendingBookingLabel) {
        this.lABLPendingBookingLabel = lABLPendingBookingLabel;
    }

    /**
     * 
     * @return
     *     The lABLBookingTripsLabel
     */
    public String getLABLBookingTripsLabel() {
        return lABLBookingTripsLabel;
    }

    /**
     * 
     * @param lABLBookingTripsLabel
     *     The LABL_Booking_Trips_Label
     */
    public void setLABLBookingTripsLabel(String lABLBookingTripsLabel) {
        this.lABLBookingTripsLabel = lABLBookingTripsLabel;
    }

    /**
     * 
     * @return
     *     The lABLNoBookingsDescLabel
     */
    public String getLABLNoBookingsDescLabel() {
        return lABLNoBookingsDescLabel;
    }

    /**
     * 
     * @param lABLNoBookingsDescLabel
     *     The LABL_No_Bookings_Desc_Label
     */
    public void setLABLNoBookingsDescLabel(String lABLNoBookingsDescLabel) {
        this.lABLNoBookingsDescLabel = lABLNoBookingsDescLabel;
    }

    /**
     * 
     * @return
     *     The faqLabel
     */
    public String getFaqLabel() {
        return faqLabel;
    }

    /**
     * 
     * @param faqLabel
     *     The Faq_Label
     */
    public void setFaqLabel(String faqLabel) {
        this.faqLabel = faqLabel;
    }

    /**
     * 
     * @return
     *     The viewDetailLabel
     */
    public String getViewDetailLabel() {
        return viewDetailLabel;
    }

    /**
     * 
     * @param viewDetailLabel
     *     The ViewDetail_label
     */
    public void setViewDetailLabel(String viewDetailLabel) {
        this.viewDetailLabel = viewDetailLabel;
    }

}

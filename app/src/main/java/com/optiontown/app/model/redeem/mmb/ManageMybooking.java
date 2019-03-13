
package com.optiontown.app.model.redeem.mmb;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManageMybooking {

    @SerializedName("LABL_Confirmation_Number_Label")
    @Expose
    private String lABLConfirmationNumberLabel;
    @SerializedName("LABL_Airline_Label")
    @Expose
    private String lABLAirlineLabel;
    @SerializedName("Airline_Name_Label")
    @Expose
    private String airlineNameLabel;



    public String getBooking_Ref_Label() {
        return Booking_Ref_Label;
    }

    public void setBooking_Ref_Label(String booking_Ref_Label) {
        Booking_Ref_Label = booking_Ref_Label;
    }

    public String getBooking_Ref_Pnr() {
        return Booking_Ref_Pnr;
    }

    public void setBooking_Ref_Pnr(String booking_Ref_Pnr) {
        Booking_Ref_Pnr = booking_Ref_Pnr;
    }

    @SerializedName("Booking_Ref_Label")
    @Expose
    private String Booking_Ref_Label;
    @SerializedName("ERR_INVALID_BOOKING_ERROR")
    @Expose
    private String eRRINVALIDBOOKINGERROR;
    @SerializedName("LABL_SelectedBookFlightPass_Id_Label")
    @Expose
    private Integer lABLSelectedBookFlightPassIdLabel;
    @SerializedName("LABL_MMB_Change_Flight_Select_Pass_Label")
    @Expose
    private String lABLMMBChangeFlightSelectPassLabel;
    @SerializedName("LABL_Passenger_Label")
    @Expose
    private String lABLPassengerLabel;
    @SerializedName("LABL_MMB_Change_Flight_Select_Flight_Label")
    @Expose
    private String lABLMMBChangeFlightSelectFlightLabel;
    @SerializedName("Booking_Ref_Pnr")
    @Expose
    private String Booking_Ref_Pnr;
    @SerializedName("ERR_Back_Button_Label")
    @Expose
    private String eRRBackButtonLabel;
    @SerializedName("LABL_Continue_Button_Label")
    @Expose
    private String lABLContinueButtonLabel;
    @SerializedName("LABL_Optiontown_Flight_Pass_Label")
    @Expose
    private String lABLOptiontownFlightPassLabel;
    @SerializedName("LABL_MTP_FPO_Booking_Label")
    @Expose
    private String lABLMTPFPOBookingLabel;
    @SerializedName("LABL_Pass_Heading_Label")
    @Expose
    private String lABLPassHeadingLabel;
    @SerializedName("LABL_Optiontown_Confirmation_Label")
    @Expose
    private String lABLOptiontownConfirmationLabel;
    @SerializedName("Change_Flight_Heading_Label")
    @Expose
    private String Change_Flight_Heading_Label;

    public String getCurrent_Flight_Label() {
        return Current_Flight_Label;
    }

    public void setCurrent_Flight_Label(String current_Flight_Label) {
        Current_Flight_Label = current_Flight_Label;
    }

    @SerializedName("Current_Flight_Label")
    @Expose
    private String Current_Flight_Label;

    public String getChange_Flight_Heading_Label() {
        return Change_Flight_Heading_Label;
    }

    public void setChange_Flight_Heading_Label(String change_Flight_Heading_Label) {
        Change_Flight_Heading_Label = change_Flight_Heading_Label;
    }



    /**
     * 
     * @return
     *     The lABLConfirmationNumberLabel
     */
    public String getLABLConfirmationNumberLabel() {
        return lABLConfirmationNumberLabel;
    }

    /**
     * 
     * @param lABLConfirmationNumberLabel
     *     The LABL_Confirmation_Number_Label
     */
    public void setLABLConfirmationNumberLabel(String lABLConfirmationNumberLabel) {
        this.lABLConfirmationNumberLabel = lABLConfirmationNumberLabel;
    }

    /**
     * 
     * @return
     *     The lABLAirlineLabel
     */
    public String getLABLAirlineLabel() {
        return lABLAirlineLabel;
    }

    /**
     * 
     * @param lABLAirlineLabel
     *     The LABL_Airline_Label
     */
    public void setLABLAirlineLabel(String lABLAirlineLabel) {
        this.lABLAirlineLabel = lABLAirlineLabel;
    }

    /**
     * 
     * @return
     *     The airlineNameLabel
     */
    public String getAirlineNameLabel() {
        return airlineNameLabel;
    }

    /**
     * 
     * @param airlineNameLabel
     *     The Airline_Name_Label
     */
    public void setAirlineNameLabel(String airlineNameLabel) {
        this.airlineNameLabel = airlineNameLabel;
    }



    /**
     * 
     * @return
     *     The eRRINVALIDBOOKINGERROR
     */
    public String getERRINVALIDBOOKINGERROR() {
        return eRRINVALIDBOOKINGERROR;
    }

    /**
     * 
     * @param eRRINVALIDBOOKINGERROR
     *     The ERR_INVALID_BOOKING_ERROR
     */
    public void setERRINVALIDBOOKINGERROR(String eRRINVALIDBOOKINGERROR) {
        this.eRRINVALIDBOOKINGERROR = eRRINVALIDBOOKINGERROR;
    }

    /**
     * 
     * @return
     *     The lABLSelectedBookFlightPassIdLabel
     */
    public Integer getLABLSelectedBookFlightPassIdLabel() {
        return lABLSelectedBookFlightPassIdLabel;
    }

    /**
     * 
     * @param lABLSelectedBookFlightPassIdLabel
     *     The LABL_SelectedBookFlightPass_Id_Label
     */
    public void setLABLSelectedBookFlightPassIdLabel(Integer lABLSelectedBookFlightPassIdLabel) {
        this.lABLSelectedBookFlightPassIdLabel = lABLSelectedBookFlightPassIdLabel;
    }

    /**
     * 
     * @return
     *     The lABLMMBChangeFlightSelectPassLabel
     */
    public String getLABLMMBChangeFlightSelectPassLabel() {
        return lABLMMBChangeFlightSelectPassLabel;
    }

    /**
     * 
     * @param lABLMMBChangeFlightSelectPassLabel
     *     The LABL_MMB_Change_Flight_Select_Pass_Label
     */
    public void setLABLMMBChangeFlightSelectPassLabel(String lABLMMBChangeFlightSelectPassLabel) {
        this.lABLMMBChangeFlightSelectPassLabel = lABLMMBChangeFlightSelectPassLabel;
    }

    /**
     * 
     * @return
     *     The lABLPassengerLabel
     */
    public String getLABLPassengerLabel() {
        return lABLPassengerLabel;
    }

    /**
     * 
     * @param lABLPassengerLabel
     *     The LABL_Passenger_Label
     */
    public void setLABLPassengerLabel(String lABLPassengerLabel) {
        this.lABLPassengerLabel = lABLPassengerLabel;
    }

    /**
     * 
     * @return
     *     The lABLMMBChangeFlightSelectFlightLabel
     */
    public String getLABLMMBChangeFlightSelectFlightLabel() {
        return lABLMMBChangeFlightSelectFlightLabel;
    }

    /**
     * 
     * @param lABLMMBChangeFlightSelectFlightLabel
     *     The LABL_MMB_Change_Flight_Select_Flight_Label
     */
    public void setLABLMMBChangeFlightSelectFlightLabel(String lABLMMBChangeFlightSelectFlightLabel) {
        this.lABLMMBChangeFlightSelectFlightLabel = lABLMMBChangeFlightSelectFlightLabel;
    }


    /**
     * 
     * @return
     *     The eRRBackButtonLabel
     */
    public String getERRBackButtonLabel() {
        return eRRBackButtonLabel;
    }

    /**
     * 
     * @param eRRBackButtonLabel
     *     The ERR_Back_Button_Label
     */
    public void setERRBackButtonLabel(String eRRBackButtonLabel) {
        this.eRRBackButtonLabel = eRRBackButtonLabel;
    }

    /**
     * 
     * @return
     *     The lABLContinueButtonLabel
     */
    public String getLABLContinueButtonLabel() {
        return lABLContinueButtonLabel;
    }

    /**
     * 
     * @param lABLContinueButtonLabel
     *     The LABL_Continue_Button_Label
     */
    public void setLABLContinueButtonLabel(String lABLContinueButtonLabel) {
        this.lABLContinueButtonLabel = lABLContinueButtonLabel;
    }

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
     *     The lABLMTPFPOBookingLabel
     */
    public String getLABLMTPFPOBookingLabel() {
        return lABLMTPFPOBookingLabel;
    }

    /**
     * 
     * @param lABLMTPFPOBookingLabel
     *     The LABL_MTP_FPO_Booking_Label
     */
    public void setLABLMTPFPOBookingLabel(String lABLMTPFPOBookingLabel) {
        this.lABLMTPFPOBookingLabel = lABLMTPFPOBookingLabel;
    }

    /**
     * 
     * @return
     *     The lABLPassHeadingLabel
     */
    public String getLABLPassHeadingLabel() {
        return lABLPassHeadingLabel;
    }

    /**
     * 
     * @param lABLPassHeadingLabel
     *     The LABL_Pass_Heading_Label
     */
    public void setLABLPassHeadingLabel(String lABLPassHeadingLabel) {
        this.lABLPassHeadingLabel = lABLPassHeadingLabel;
    }

    /**
     * 
     * @return
     *     The lABLOptiontownConfirmationLabel
     */
    public String getLABLOptiontownConfirmationLabel() {
        return lABLOptiontownConfirmationLabel;
    }

    /**
     * 
     * @param lABLOptiontownConfirmationLabel
     *     The LABL_Optiontown_Confirmation_Label
     */
    public void setLABLOptiontownConfirmationLabel(String lABLOptiontownConfirmationLabel) {
        this.lABLOptiontownConfirmationLabel = lABLOptiontownConfirmationLabel;
    }

}

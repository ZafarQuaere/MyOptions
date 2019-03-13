
package com.optiontown.app.model.redeem.mybooking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.optiontown.app.model.redeem.Itinerarry;

public class ConfirmedBooking implements Serializable{

    private int bookingType;

    public int getBookingType() {
        return bookingType;
    }

    public void setBookingType(int bookingType) {
        this.bookingType = bookingType;
    }



    private boolean DisplayReturn_Flag;

    public boolean isDisplayReturn_Flag() {
        return DisplayReturn_Flag;
    }

    public void setDisplayReturn_Flag(boolean displayReturn_Flag) {
        DisplayReturn_Flag = displayReturn_Flag;
    }


    private String LABL_Arrive_Airport_Label;

    public String getLABLArriveAirportLabel() { return this.LABL_Arrive_Airport_Label; }

    public void setLABLArriveAirportLabel(String LABL_Arrive_Airport_Label) { this.LABL_Arrive_Airport_Label = LABL_Arrive_Airport_Label; }

    private String tgp_Pax_Booking_date;

    public String getTgpPaxBookingDate() { return this.tgp_Pax_Booking_date; }

    public void setTgpPaxBookingDate(String tgp_Pax_Booking_date) { this.tgp_Pax_Booking_date = tgp_Pax_Booking_date; }

    private String LABL_Processing_Label;

    public String getLABLProcessingLabel() { return this.LABL_Processing_Label; }

    public void setLABLProcessingLabel(String LABL_Processing_Label) { this.LABL_Processing_Label = LABL_Processing_Label; }

    private String tgp_pax_booking_confirmation_number;

    public String getTgpPaxBookingConfirmationNumber() { return this.tgp_pax_booking_confirmation_number; }

    public void setTgpPaxBookingConfirmationNumber(String tgp_pax_booking_confirmation_number) { this.tgp_pax_booking_confirmation_number = tgp_pax_booking_confirmation_number; }

    private String airlineName;

    public String getAirlineName() { return this.airlineName; }

    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }

    private String LABL_Depart_Airport_Label;

    public String getLABLDepartAirportLabel() { return this.LABL_Depart_Airport_Label; }

    public void setLABLDepartAirportLabel(String LABL_Depart_Airport_Label) { this.LABL_Depart_Airport_Label = LABL_Depart_Airport_Label; }

    private String LABL_FPO_Selected_Flights_Label;

    public String getLABLFPOSelectedFlightsLabel() { return this.LABL_FPO_Selected_Flights_Label; }

    public void setLABLFPOSelectedFlightsLabel(String LABL_FPO_Selected_Flights_Label) { this.LABL_FPO_Selected_Flights_Label = LABL_FPO_Selected_Flights_Label; }

    private String Booking_Pnr_number;

    public String getBookingPnrNumber() { return this.Booking_Pnr_number; }

    public void setBookingPnrNumber(String Booking_Pnr_number) { this.Booking_Pnr_number = Booking_Pnr_number; }

    private String LABL_Going_To_Label;

    public String getLABLGoingToLabel() { return this.LABL_Going_To_Label; }

    public void setLABLGoingToLabel(String LABL_Going_To_Label) { this.LABL_Going_To_Label = LABL_Going_To_Label; }

    private String flight_Cabin_Data;

    public String getFlightCabinData() { return this.flight_Cabin_Data; }

    public void setFlightCabinData(String flight_Cabin_Data) { this.flight_Cabin_Data = flight_Cabin_Data; }

    private String Outbound_Label;

    public String getOutboundLabel() { return this.Outbound_Label; }

    public void setOutboundLabel(String Outbound_Label) { this.Outbound_Label = Outbound_Label; }

    private String LABL_FPO_Selected_Passenger_Label;

    public String getLABLFPOSelectedPassengerLabel() { return this.LABL_FPO_Selected_Passenger_Label; }

    public void setLABLFPOSelectedPassengerLabel(String LABL_FPO_Selected_Passenger_Label) { this.LABL_FPO_Selected_Passenger_Label = LABL_FPO_Selected_Passenger_Label; }

    private String Pending_Booking_Label;

    public String getPendingBookingLabel() { return this.Pending_Booking_Label; }

    public void setPendingBookingLabel(String Pending_Booking_Label) { this.Pending_Booking_Label = Pending_Booking_Label; }

    private ArrayList<Passenger> passengers;

    public ArrayList<Passenger> getPassengers() { return this.passengers; }

    public void setPassengers(ArrayList<Passenger> passengers) { this.passengers = passengers; }

    private String LABL_Passenger_Label;

    public String getLABLPassengerLabel() { return this.LABL_Passenger_Label; }

    public void setLABLPassengerLabel(String LABL_Passenger_Label) { this.LABL_Passenger_Label = LABL_Passenger_Label; }

    private String flight_date_arrival;

    public String getFlightDateArrival() { return this.flight_date_arrival; }

    public void setFlightDateArrival(String flight_date_arrival) { this.flight_date_arrival = flight_date_arrival; }

    private String LABL_Optiontown_Pass_Id_Label;

    public String getLABLOptiontownPassIdLabel() { return this.LABL_Optiontown_Pass_Id_Label; }

    public void setLABLOptiontownPassIdLabel(String LABL_Optiontown_Pass_Id_Label) { this.LABL_Optiontown_Pass_Id_Label = LABL_Optiontown_Pass_Id_Label; }

    private String LABL_PNR_Label;

    public String getLABLPNRLabel() { return this.LABL_PNR_Label; }

    public void setLABLPNRLabel(String LABL_PNR_Label) { this.LABL_PNR_Label = LABL_PNR_Label; }

    private String Airline_Label;

    public String getAirlineLabel() { return this.Airline_Label; }

    public void setAirlineLabel(String Airline_Label) { this.Airline_Label = Airline_Label; }

    private String LABL_FPo_Booking_Confirmed_Label;

    public String getLABLFPoBookingConfirmedLabel() { return this.LABL_FPo_Booking_Confirmed_Label; }

    public void setLABLFPoBookingConfirmedLabel(String LABL_FPo_Booking_Confirmed_Label) { this.LABL_FPo_Booking_Confirmed_Label = LABL_FPo_Booking_Confirmed_Label; }

    private String Booking_Request_Date_Label;

    public String getBookingRequestDateLabel() { return this.Booking_Request_Date_Label; }

    public void setBookingRequestDateLabel(String Booking_Request_Date_Label) { this.Booking_Request_Date_Label = Booking_Request_Date_Label; }

    private String Top_Message;

    public String getTopMessage() { return this.Top_Message; }

    public void setTopMessage(String Top_Message) { this.Top_Message = Top_Message; }

    private ArrayList<Itinerarry> Itinerarry;

    public ArrayList<Itinerarry> getItinerarry() { return this.Itinerarry; }

    public void setItinerarry(ArrayList<Itinerarry> Itinerarry) { this.Itinerarry = Itinerarry; }

    private String flight_date_departure;

    public String getFlightDateDeparture() { return this.flight_date_departure; }

    public void setFlightDateDeparture(String flight_date_departure) { this.flight_date_departure = flight_date_departure; }

    private String LABL_SELECTED_ZONE;

    public String getLABLSELECTEDZONE() { return this.LABL_SELECTED_ZONE; }

    public void setLABLSELECTEDZONE(String LABL_SELECTED_ZONE) { this.LABL_SELECTED_ZONE = LABL_SELECTED_ZONE; }

    private String Confirmed_Booking_Label;

    public String getConfirmedBookingLabel() { return this.Confirmed_Booking_Label; }

    public void setConfirmedBookingLabel(String Confirmed_Booking_Label) { this.Confirmed_Booking_Label = Confirmed_Booking_Label; }

    private String LABL_FPO_Booking_Request_Date_Label;

    public String getLABLFPOBookingRequestDateLabel() { return this.LABL_FPO_Booking_Request_Date_Label; }

    public void setLABLFPOBookingRequestDateLabel(String LABL_FPO_Booking_Request_Date_Label) { this.LABL_FPO_Booking_Request_Date_Label = LABL_FPO_Booking_Request_Date_Label; }

    private int Pass_trans_id;

    public int getPassTransId() { return this.Pass_trans_id; }

    public void setPassTransId(int Pass_trans_id) { this.Pass_trans_id = Pass_trans_id; }

    private String LABL_Optiontown_CN_Label;

    public String getLABLOptiontownCNLabel() { return this.LABL_Optiontown_CN_Label; }

    public void setLABLOptiontownCNLabel(String LABL_Optiontown_CN_Label) { this.LABL_Optiontown_CN_Label = LABL_Optiontown_CN_Label; }

    private String flight_departure_airport_code;

    public String getFlightDepartureAirportCode() { return this.flight_departure_airport_code; }

    public void setFlightDepartureAirportCode(String flight_departure_airport_code) { this.flight_departure_airport_code = flight_departure_airport_code; }

    private String LABL_OCN_Short_Label;

    public String getLABLOCNShortLabel() { return this.LABL_OCN_Short_Label; }

    public void setLABLOCNShortLabel(String LABL_OCN_Short_Label) { this.LABL_OCN_Short_Label = LABL_OCN_Short_Label; }

    private String Return_Label;

    public String getReturnLabel() { return this.Return_Label; }

    public void setReturnLabel(String Return_Label) { this.Return_Label = Return_Label; }

    private String LABL_Depart_From_Label;

    public String getLABLDepartFromLabel() { return this.LABL_Depart_From_Label; }

    public void setLABLDepartFromLabel(String LABL_Depart_From_Label) { this.LABL_Depart_From_Label = LABL_Depart_From_Label; }

    private String LABL_Cabin_Label;

    public String getLABLCabinLabel() { return this.LABL_Cabin_Label; }

    public void setLABLCabinLabel(String LABL_Cabin_Label) { this.LABL_Cabin_Label = LABL_Cabin_Label; }

    private String LABL_Flight_Label;

    public String getLABLFlightLabel() { return this.LABL_Flight_Label; }

    public void setLABLFlightLabel(String LABL_Flight_Label) { this.LABL_Flight_Label = LABL_Flight_Label; }

    private String flight_departure_airport_display_name;

    public String getFlightDepartureAirportDisplayName() { return this.flight_departure_airport_display_name; }

    public void setFlightDepartureAirportDisplayName(String flight_departure_airport_display_name) { this.flight_departure_airport_display_name = flight_departure_airport_display_name; }



}

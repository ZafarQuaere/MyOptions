package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 05/09/2016.
 */
public class SelectedPassDataForSearchFlight implements Serializable
{

        private String Travel_Period_VAlidity_Begin_Date;
        private String Travel_Period_VAlidity_End_Date;
        private String Pass_Review_Image_Value;

        public String getTravel_Period_VAlidity_Begin_Date() {
                return Travel_Period_VAlidity_Begin_Date;
        }

        public void setTravel_Period_VAlidity_Begin_Date(String travel_Period_VAlidity_Begin_Date) {
                Travel_Period_VAlidity_Begin_Date = travel_Period_VAlidity_Begin_Date;
        }

        public String getTravel_Period_VAlidity_End_Date() {
                return Travel_Period_VAlidity_End_Date;
        }

        public void setTravel_Period_VAlidity_End_Date(String travel_Period_VAlidity_End_Date) {
                Travel_Period_VAlidity_End_Date = travel_Period_VAlidity_End_Date;
        }

        public String getPass_Review_Image_Value() {
                return Pass_Review_Image_Value;
        }

        public void setPass_Review_Image_Value(String pass_Review_Image_Value) {
                Pass_Review_Image_Value = pass_Review_Image_Value;
        }

        private String OpenJaw_To_Label;

        public String getOpenJawToLabel() { return this.OpenJaw_To_Label; }

        public void setOpenJawToLabel(String OpenJaw_To_Label) { this.OpenJaw_To_Label = OpenJaw_To_Label; }

        private String depart_window_Image;

        public String getDepartWindowImage() { return this.depart_window_Image; }

        public void setDepartWindowImage(String depart_window_Image) { this.depart_window_Image = depart_window_Image; }

        private String Travel_Flexibility_Range;

        public String getTravelFlexibilityRange() { return this.Travel_Flexibility_Range; }

        public void setTravelFlexibilityRange(String Travel_Flexibility_Range) { this.Travel_Flexibility_Range = Travel_Flexibility_Range; }

        private String OpenJaw_Flight2_Label;

        public String getOpenJawFlight2Label() { return this.OpenJaw_Flight2_Label; }

        public void setOpenJawFlight2Label(String OpenJaw_Flight2_Label) { this.OpenJaw_Flight2_Label = OpenJaw_Flight2_Label; }

        private String Zone_Tip_Label;

        public String getZoneTipLabel() { return this.Zone_Tip_Label; }

        public void setZoneTipLabel(String Zone_Tip_Label) { this.Zone_Tip_Label = Zone_Tip_Label; }

        private String SearchHeading_Label;

        public String getSearchHeadingLabel() { return this.SearchHeading_Label; }

        public void setSearchHeadingLabel(String SearchHeading_Label) { this.SearchHeading_Label = SearchHeading_Label; }

        private String Travel_Flexibility_Label;

        public String getTravelFlexibilityLabel() { return this.Travel_Flexibility_Label; }

        public void setTravelFlexibilityLabel(String Travel_Flexibility_Label) { this.Travel_Flexibility_Label = Travel_Flexibility_Label; }

        private String Product_Label;

        public String getProductLabel() { return this.Product_Label; }

        public void setProductLabel(String Product_Label) { this.Product_Label = Product_Label; }

        private ArrayList<DepartList> Depart_List;

        public ArrayList<DepartList> getDepartList() { return this.Depart_List; }

        public void setDepartList(ArrayList<DepartList> Depart_List) { this.Depart_List = Depart_List; }

        private String City_Or_Airport;

        public String getCityOrAirport() { return this.City_Or_Airport; }

        public void setCityOrAirport(String City_Or_Airport) { this.City_Or_Airport = City_Or_Airport; }

        private String Used_Flights_Label;

        public String getUsedFlightsLabel() { return this.Used_Flights_Label; }

        public void setUsedFlightsLabel(String Used_Flights_Label) { this.Used_Flights_Label = Used_Flights_Label; }

        private int Travel_Flexibility_Flag;

        public int getTravelFlexibilityFlag() { return this.Travel_Flexibility_Flag; }

        public void setTravelFlexibilityFlag(int Travel_Flexibility_Flag) { this.Travel_Flexibility_Flag = Travel_Flexibility_Flag; }

        private String FPO_Review_Label;

        public String getFPOReviewLabel() { return this.FPO_Review_Label; }

        public void setFPOReviewLabel(String FPO_Review_Label) { this.FPO_Review_Label = FPO_Review_Label; }

        private ArrayList<OpenJawFromList> OpenJaw_From_List;

        public ArrayList<OpenJawFromList> getOpenJawFromList() { return this.OpenJaw_From_List; }

        public void setOpenJawFromList(ArrayList<OpenJawFromList> OpenJaw_From_List) { this.OpenJaw_From_List = OpenJaw_From_List; }

        private String Advance_Book_Long_Late_Label;

        public String getAdvanceBookLongLateLabel() { return this.Advance_Book_Long_Late_Label; }

        public void setAdvanceBookLongLateLabel(String Advance_Book_Long_Late_Label) { this.Advance_Book_Long_Late_Label = Advance_Book_Long_Late_Label; }

        private String TripLabel;

        public String getTripLabel() { return this.TripLabel; }

        public void setTripLabel(String TripLabel) { this.TripLabel = TripLabel; }

        private String Flight_Heading_Label;

        public String getFlightHeadingLabel() { return this.Flight_Heading_Label; }

        public void setFlightHeadingLabel(String Flight_Heading_Label) { this.Flight_Heading_Label = Flight_Heading_Label; }

        private String FPO_Confirm_Label;

        public String getFPOConfirmLabel() { return this.FPO_Confirm_Label; }

        public void setFPOConfirmLabel(String FPO_Confirm_Label) { this.FPO_Confirm_Label = FPO_Confirm_Label; }

        private String Advance_Booking_Title;

        public String getAdvanceBookingTitle() { return this.Advance_Booking_Title; }

        public void setAdvanceBookingTitle(String Advance_Booking_Title) { this.Advance_Booking_Title = Advance_Booking_Title; }

        private String TravelZone_Title_Label;

        public String getTravelZoneTitleLabel() { return this.TravelZone_Title_Label; }

        public void setTravelZoneTitleLabel(String TravelZone_Title_Label) { this.TravelZone_Title_Label = TravelZone_Title_Label; }

        private String Cabin_Name;

        public String getCabinName() { return this.Cabin_Name; }

        public void setCabinName(String Cabin_Name) { this.Cabin_Name = Cabin_Name; }

        private String Pax_Label;

        public String getPaxLabel() { return this.Pax_Label; }

        public void setPaxLabel(String Pax_Label) { this.Pax_Label = Pax_Label; }

        private String Return_Label;

        public String getReturnLabel() { return this.Return_Label; }

        public void setReturnLabel(String Return_Label) { this.Return_Label = Return_Label; }

        private ArrayList<ArriveAirportList> ArriveAirport_List;

        public ArrayList<ArriveAirportList> getArriveAirportList() { return this.ArriveAirport_List; }

        public void setArriveAirportList(ArrayList<ArriveAirportList> ArriveAirport_List) { this.ArriveAirport_List = ArriveAirport_List; }

        private String ToLabel;

        public String getToLabel() { return this.ToLabel; }

        public void setToLabel(String ToLabel) { this.ToLabel = ToLabel; }

        private String FromLabel;

        public String getFromLabel() { return this.FromLabel; }

        public void setFromLabel(String FromLabel) { this.FromLabel = FromLabel; }

        private int TYPE_DIALOG = 0;

        public int getTYPE_DIALOG() {
                return TYPE_DIALOG;
        }

        public void setTYPE_DIALOG(int TYPE_DIALOG) {
                this.TYPE_DIALOG = TYPE_DIALOG;
        }


        public String getClose_Label() {
                return Close_Label;
        }

        public void setClose_Label(String close_Label) {
                Close_Label = close_Label;
        }

        private ArrayList<ZoneLatLongitude> Zone_Lat_Longitudes;

        public ArrayList<ZoneLatLongitude> getZoneLatLongitudes() { return this.Zone_Lat_Longitudes; }

        public void setZoneLatLongitudes(ArrayList<ZoneLatLongitude> Zone_Lat_Longitudes) { this.Zone_Lat_Longitudes = Zone_Lat_Longitudes; }

        private String CountryDepartName;

        public String getCountryDepartName() { return this.CountryDepartName; }

        public void setCountryDepartName(String CountryDepartName) { this.CountryDepartName = CountryDepartName; }

        private String Search_Label;

        public String getSearchLabel() { return this.Search_Label; }

        public void setSearchLabel(String Search_Label) { this.Search_Label = Search_Label; }

        private String Close_Label;

        public String getCloseLabel() { return this.Close_Label; }

        public void setCloseLabel(String Close_Label) { this.Close_Label = Close_Label; }

        private String Depart_Airport_Label;

        public String getDepartAirportLabel() { return this.Depart_Airport_Label; }

        public void setDepartAirportLabel(String Depart_Airport_Label) { this.Depart_Airport_Label = Depart_Airport_Label; }

        private ArrayList<TripDetail> TripDetails;

        public ArrayList<TripDetail> getTripDetails() { return this.TripDetails; }

        public void setTripDetails(ArrayList<TripDetail> TripDetails) { this.TripDetails = TripDetails; }

        private String Travel_Period_VAlidity_Under_Date_Label;

        public String getTravelPeriodVAlidityUnderDateLabel() { return this.Travel_Period_VAlidity_Under_Date_Label; }

        public void setTravelPeriodVAlidityUnderDateLabel(String Travel_Period_VAlidity_Under_Date_Label) { this.Travel_Period_VAlidity_Under_Date_Label = Travel_Period_VAlidity_Under_Date_Label; }

        private String Travel_Flexibility_Depart;

        public String getTravelFlexibilityDepart() { return this.Travel_Flexibility_Depart; }

        public void setTravelFlexibilityDepart(String Travel_Flexibility_Depart) { this.Travel_Flexibility_Depart = Travel_Flexibility_Depart; }

        private String Total_Flights_Label;

        public String getTotalFlightsLabel() { return this.Total_Flights_Label; }

        public void setTotalFlightsLabel(String Total_Flights_Label) { this.Total_Flights_Label = Total_Flights_Label; }

        private String Travel_Period_VAlidity_Date_Label;

        public String getTravelPeriodVAlidityDateLabel() { return this.Travel_Period_VAlidity_Date_Label; }

        public void setTravelPeriodVAlidityDateLabel(String Travel_Period_VAlidity_Date_Label) { this.Travel_Period_VAlidity_Date_Label = Travel_Period_VAlidity_Date_Label; }

        private String OpenJaw_Flight1_Label;

        public String getOpenJawFlight1Label() { return this.OpenJaw_Flight1_Label; }

        public void setOpenJawFlight1Label(String OpenJaw_Flight1_Label) { this.OpenJaw_Flight1_Label = OpenJaw_Flight1_Label; }

        private ArrayList<DepartAirportList> DepartAirport_List;

        public ArrayList<DepartAirportList> getDepartAirportList() { return this.DepartAirport_List; }

        public void setDepartAirportList(ArrayList<DepartAirportList> DepartAirport_List) { this.DepartAirport_List = DepartAirport_List; }

        private ArrayList<ArriveDepart> Arrive_Depart;

        public ArrayList<ArriveDepart> getArriveDepart() { return this.Arrive_Depart; }

        public void setArriveDepart(ArrayList<ArriveDepart> Arrive_Depart) { this.Arrive_Depart = Arrive_Depart; }

        private String Pass_Review_Image_Label;

        public String getPassReviewImageLabel() { return this.Pass_Review_Image_Label; }

        public void setPassReviewImageLabel(String Pass_Review_Image_Label) { this.Pass_Review_Image_Label = Pass_Review_Image_Label; }

        private String Number_of_Flights_Label;

        public String getNumberOfFlightsLabel() { return this.Number_of_Flights_Label; }

        public void setNumberOfFlightsLabel(String Number_of_Flights_Label) { this.Number_of_Flights_Label = Number_of_Flights_Label; }

        private String OpenJaw_From_Label;

        public String getOpenJawFromLabel() { return this.OpenJaw_From_Label; }

        public void setOpenJawFromLabel(String OpenJaw_From_Label) { this.OpenJaw_From_Label = OpenJaw_From_Label; }

        private String Travel_Period_Title;

        public String getTravelPeriodTitle() { return this.Travel_Period_Title; }

        public void setTravelPeriodTitle(String Travel_Period_Title) { this.Travel_Period_Title = Travel_Period_Title; }

        private String Available_Flights_Label;

        public String getAvailableFlightsLabel() { return this.Available_Flights_Label; }

        public void setAvailableFlightsLabel(String Available_Flights_Label) { this.Available_Flights_Label = Available_Flights_Label; }

        private ArrayList<PassListDropDown> PassListDropDown;

        public ArrayList<PassListDropDown> getPassListDropDown() { return this.PassListDropDown; }

        public void setPassListDropDown(ArrayList<PassListDropDown> PassListDropDown) { this.PassListDropDown = PassListDropDown; }

        private ArrayList<PaxList> Pax_List;

        public ArrayList<PaxList> getPaxList() { return this.Pax_List; }

        public void setPaxList(ArrayList<PaxList> Pax_List) { this.Pax_List = Pax_List; }

        private String TravelZone_Long_Label;

        public String getTravelZoneLongLabel() { return this.TravelZone_Long_Label; }

        public void setTravelZoneLongLabel(String TravelZone_Long_Label) { this.TravelZone_Long_Label = TravelZone_Long_Label; }

        private String Add_Passenger_Label;

        public String getAddPassengerLabel() { return this.Add_Passenger_Label; }

        public void setAddPassengerLabel(String Add_Passenger_Label) { this.Add_Passenger_Label = Add_Passenger_Label; }

        private String Cabin_Label;

        public String getCabinLabel() { return this.Cabin_Label; }

        public void setCabinLabel(String Cabin_Label) { this.Cabin_Label = Cabin_Label; }

        private ArrayList<OpenJawToList> OpenJaw_To_List;

        public ArrayList<OpenJawToList> getOpenJawToList() { return this.OpenJaw_To_List; }

        public void setOpenJawToList(ArrayList<OpenJawToList> OpenJaw_To_List) { this.OpenJaw_To_List = OpenJaw_To_List; }

        private String FPO_Select_Label;

        public String getFPOSelectLabel() { return this.FPO_Select_Label; }

        public void setFPOSelectLabel(String FPO_Select_Label) { this.FPO_Select_Label = FPO_Select_Label; }

}

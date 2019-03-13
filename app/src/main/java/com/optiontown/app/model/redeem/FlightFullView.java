package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by amit on 09-09-2016.
 */
public class FlightFullView implements Serializable
{
    private String OverNight;

    public String getOverNight() {
        return OverNight;
    }

    public void setOverNight(String overNight) {
        OverNight = overNight;
    }

    private String LayOverTime;

    public String getLayOverTime() {
        return LayOverTime;
    }

    public void setLayOverTime(String layOverTime) {
        LayOverTime = layOverTime;
    }

    private String Arrival_Airport_Display_Name;
    private String Depart_Airport_Display_Name;

    public String getArrival_Airport_Display_Name() {
        return Arrival_Airport_Display_Name;
    }

    public void setArrival_Airport_Display_Name(String arrival_Airport_Display_Name) {
        Arrival_Airport_Display_Name = arrival_Airport_Display_Name;
    }

    public String getDepart_Airport_Display_Name() {
        return Depart_Airport_Display_Name;
    }

    public void setDepart_Airport_Display_Name(String depart_Airport_Display_Name) {
        Depart_Airport_Display_Name = depart_Airport_Display_Name;
    }

    private String ArrivalAirline_Date;

    public String getArrivalAirlineDate() { return this.ArrivalAirline_Date; }

    public void setArrivalAirlineDate(String ArrivalAirline_Date) { this.ArrivalAirline_Date = ArrivalAirline_Date; }

    private String Stations;

    public String getStations() { return this.Stations; }

    public void setStations(String Stations) { this.Stations = Stations; }

    private String Display_Name;

    public String getDisplayName() { return this.Display_Name; }

    public void setDisplayName(String Display_Name) { this.Display_Name = Display_Name; }

    private String dpart_plane_icon;

    public String getDpartPlaneIcon() { return this.dpart_plane_icon; }

    public void setDpartPlaneIcon(String dpart_plane_icon) { this.dpart_plane_icon = dpart_plane_icon; }

    private String Close_Label;

    public String getCloseLabel() { return this.Close_Label; }

    public void setCloseLabel(String Close_Label) { this.Close_Label = Close_Label; }

    private String Depart_Airport_Label;

    public String getDepartAirportLabel() { return this.Depart_Airport_Label; }

    public void setDepartAirportLabel(String Depart_Airport_Label) { this.Depart_Airport_Label = Depart_Airport_Label; }

    private String Airbus_Label;

    public String getAirbusLabel() { return this.Airbus_Label; }

    public void setAirbusLabel(String Airbus_Label) { this.Airbus_Label = Airbus_Label; }

    private String Payment_Account_Detail_Label;

    public String getPaymentAccountDetailLabel() { return this.Payment_Account_Detail_Label; }

    public void setPaymentAccountDetailLabel(String Payment_Account_Detail_Label) { this.Payment_Account_Detail_Label = Payment_Account_Detail_Label; }

    private String Flight_Landing_Label;

    public String getFlightLandingLabel() { return this.Flight_Landing_Label; }

    public void setFlightLandingLabel(String Flight_Landing_Label) { this.Flight_Landing_Label = Flight_Landing_Label; }

    private String DepartAirline_Date;

    public String getDepartAirlineDate() { return this.DepartAirline_Date; }

    public void setDepartAirlineDate(String DepartAirline_Date) { this.DepartAirline_Date = DepartAirline_Date; }

    private String Flight_Logo;

    public String getFlightLogo() { return this.Flight_Logo; }

    public void setFlightLogo(String Flight_Logo) { this.Flight_Logo = Flight_Logo; }

    private String Dept_FormattedTime;

    public String getDeptFormattedTime() { return this.Dept_FormattedTime; }

    public void setDeptFormattedTime(String Dept_FormattedTime) { this.Dept_FormattedTime = Dept_FormattedTime; }

    private String ArrivalAirline_Time;

    public String getArrivalAirlineTime() { return this.ArrivalAirline_Time; }

    public void setArrivalAirlineTime(String ArrivalAirline_Time) { this.ArrivalAirline_Time = ArrivalAirline_Time; }

    private String Dept_AirportCode;

    public String getDeptAirportCode() { return this.Dept_AirportCode; }

    public void setDeptAirportCode(String Dept_AirportCode) { this.Dept_AirportCode = Dept_AirportCode; }

    private String Arrival_AirportCode;

    public String getArrivalAirportCode() { return this.Arrival_AirportCode; }

    public void setArrivalAirportCode(String Arrival_AirportCode) { this.Arrival_AirportCode = Arrival_AirportCode; }

    private String Take_off_Label;

    public String getTakeOffLabel() { return this.Take_off_Label; }

    public void setTakeOffLabel(String Take_off_Label) { this.Take_off_Label = Take_off_Label; }

    private String Non_Stop_Label;

    public String getNonStopLabel() { return this.Non_Stop_Label; }

    public void setNonStopLabel(String Non_Stop_Label) { this.Non_Stop_Label = Non_Stop_Label; }
}

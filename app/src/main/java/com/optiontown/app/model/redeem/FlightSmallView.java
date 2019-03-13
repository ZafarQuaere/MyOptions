package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by amit on 09-09-2016.
 */
public class FlightSmallView implements Serializable
{

    private boolean DisplayReturn_Flag;

    public boolean isDisplayReturn_Flag() {
        return DisplayReturn_Flag;
    }

    public void setDisplayReturn_Flag(boolean displayReturn_Flag) {
        DisplayReturn_Flag = displayReturn_Flag;
    }




    private String flight_arrow;

    public String getFlightArrow() { return this.flight_arrow; }

    public void setFlightArrow(String flight_arrow) { this.flight_arrow = flight_arrow; }

    private String Deadline_Label;

    public String getDeadlineLabel() { return this.Deadline_Label; }

    public void setDeadlineLabel(String Deadline_Label) { this.Deadline_Label = Deadline_Label; }

    private String Close_Label;

    public String getCloseLabel() { return this.Close_Label; }

    public void setCloseLabel(String Close_Label) { this.Close_Label = Close_Label; }

    private String Select_Button_Label;

    public String getSelectButtonLabel() { return this.Select_Button_Label; }

    public void setSelectButtonLabel(String Select_Button_Label) { this.Select_Button_Label = Select_Button_Label; }

    private String Airline_Dis_Name;

    public String getAirlineDisName() { return this.Airline_Dis_Name; }

    public void setAirlineDisName(String Airline_Dis_Name) { this.Airline_Dis_Name = Airline_Dis_Name; }

    private String ArrivalAirlineCode;

    public String getArrivalAirlineCode() { return this.ArrivalAirlineCode; }

    public void setArrivalAirlineCode(String ArrivalAirlineCode) { this.ArrivalAirlineCode = ArrivalAirlineCode; }

    private String Airline_Logo;

    public String getAirlineLogo() { return this.Airline_Logo; }

    public void setAirlineLogo(String Airline_Logo) { this.Airline_Logo = Airline_Logo; }

    private String DepartAirlineCode;

    public String getDepartAirlineCode() { return this.DepartAirlineCode; }

    public void setDepartAirlineCode(String DepartAirlineCode) { this.DepartAirlineCode = DepartAirlineCode; }

    private String Indexes;

    public String getIndexes() { return this.Indexes; }

    public void setIndexes(String Indexes) { this.Indexes = Indexes; }

    private String DepartAirlineDate;

    public String getDepartAirlineDate() { return this.DepartAirlineDate; }

    public void setDepartAirlineDate(String DepartAirlineDate) { this.DepartAirlineDate = DepartAirlineDate; }

    private String ArrivalAirlineTime;

    public String getArrivalAirlineTime() { return this.ArrivalAirlineTime; }

    public void setArrivalAirlineTime(String ArrivalAirlineTime) { this.ArrivalAirlineTime = ArrivalAirlineTime; }

    private String AirlineCode_FLightNum;

    public String getAirlineCodeFLightNum() { return this.AirlineCode_FLightNum; }

    public void setAirlineCodeFLightNum(String AirlineCode_FLightNum) { this.AirlineCode_FLightNum = AirlineCode_FLightNum; }

    private int Flight_Duration_Min;

    public int getFlight_Duration_Min() {
        return Flight_Duration_Min;
    }

    public void setFlight_Duration_Min(int flight_Duration_Min) {
        Flight_Duration_Min = flight_Duration_Min;
    }

    private String Flight_Duration;

    public String getFlightDuration() { return this.Flight_Duration; }

    public void setFlightDuration(String Flight_Duration) { this.Flight_Duration = Flight_Duration;
    }

    private String No_Of_Stops;

    public String getNoOfStops() { return this.No_Of_Stops; }

    public void setNoOfStops(String No_Of_Stops) { this.No_Of_Stops = No_Of_Stops; }

    private String AirlineCode;

    public String getAirlineCode() { return this.AirlineCode; }

    public void setAirlineCode(String AirlineCode) { this.AirlineCode = AirlineCode; }

    private String DepartAirlineTime;

    public String getDepartAirlineTime() { return this.DepartAirlineTime; }

    public void setDepartAirlineTime(String DepartAirlineTime) { this.DepartAirlineTime = DepartAirlineTime; }

    private String Selected_Button_Label;

    public String getSelectedButtonLabel() { return this.Selected_Button_Label; }

    public void setSelectedButtonLabel(String Selected_Button_Label) { this.Selected_Button_Label = Selected_Button_Label; }

    private String Footer_Label;

    public String getFooterLabel() { return this.Footer_Label; }

    public void setFooterLabel(String Footer_Label) { this.Footer_Label = Footer_Label; }
}

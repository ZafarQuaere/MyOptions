package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 06/09/2016.
 */
public class ZoneLatLongitude implements Serializable {

    private String Arrive_CityName;

    public String getArriveCityName() { return this.Arrive_CityName; }

    public void setArriveCityName(String Arrive_CityName) { this.Arrive_CityName = Arrive_CityName; }

    private double Depart_Longitude;

    public double getDepartLongitude() { return this.Depart_Longitude; }

    public void setDepartLongitude(double Depart_Longitude) { this.Depart_Longitude = Depart_Longitude; }

    private String Depart_CityName;

    public String getDepartCityName() { return this.Depart_CityName; }

    public void setDepartCityName(String Depart_CityName) { this.Depart_CityName = Depart_CityName; }

    private double Depart_Latitude;

    public double getDepartLatitude() { return this.Depart_Latitude; }

    public void setDepartLatitude(double Depart_Latitude) { this.Depart_Latitude = Depart_Latitude; }

    private double Arrive_Longitude;

    public double getArriveLongitude() { return this.Arrive_Longitude; }

    public void setArriveLongitude(double Arrive_Longitude) { this.Arrive_Longitude = Arrive_Longitude; }

    private double Arrive_Latitude;

    public double getArriveLatitude() { return this.Arrive_Latitude; }

    public void setArriveLatitude(double Arrive_Latitude) { this.Arrive_Latitude = Arrive_Latitude; }

}

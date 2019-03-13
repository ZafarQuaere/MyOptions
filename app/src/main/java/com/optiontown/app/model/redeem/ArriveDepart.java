package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 08/09/2016.
 */
public class ArriveDepart implements Serializable{

    private String Arrive_CityName;

    public String getArriveCityName() { return this.Arrive_CityName; }

    public void setArriveCityName(String Arrive_CityName) { this.Arrive_CityName = Arrive_CityName; }

    private String Depart_CityName;

    public String getDepartCityName() { return this.Depart_CityName; }

    public void setDepartCityName(String Depart_CityName) { this.Depart_CityName = Depart_CityName; }
}

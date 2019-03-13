package com.optiontown.app.model.review;

import java.io.Serializable;

/**
 * Created by amit on 22-06-2017.
 */
public class DepartZoneArr implements Serializable
{
    private String departCode;

    public String getDepartCode() { return this.departCode; }

    public void setDepartCode(String departCode) { this.departCode = departCode; }

    private String departCityName;

    public String getDepartCityName() { return this.departCityName; }

    public void setDepartCityName(String departCityName) { this.departCityName = departCityName; }

    private String departAirportName;

    public String getDepartAirportName() { return this.departAirportName; }

    public void setDepartAirportName(String departAirportName) { this.departAirportName = departAirportName; }
}

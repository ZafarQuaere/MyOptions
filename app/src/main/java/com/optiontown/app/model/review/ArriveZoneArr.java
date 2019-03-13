package com.optiontown.app.model.review;

import java.io.Serializable;

/**
 * Created by amit on 22-06-2017.
 */
public class ArriveZoneArr implements Serializable
{
    private String arriveCode;

    public String getArriveCode() { return this.arriveCode; }

    public void setArriveCode(String arriveCode) { this.arriveCode = arriveCode; }

    private String arriveAirportname;

    public String getArriveAirportname() { return this.arriveAirportname; }

    public void setArriveAirportname(String arriveAirportname) { this.arriveAirportname = arriveAirportname; }

    private String arriveCityname;

    public String getArriveCityname() { return this.arriveCityname; }

    public void setArriveCityname(String arriveCityname) { this.arriveCityname = arriveCityname; }
}

package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by amit on 09-09-2016.
 */
public class LegList implements Serializable
{
    private FlightFullView Flight_Full_View;

    public FlightFullView getFlightFullView() { return this.Flight_Full_View; }

    public void setFlightFullView(FlightFullView Flight_Full_View) { this.Flight_Full_View = Flight_Full_View; }

    private FlightSmallView flight_small_view;

    public FlightSmallView getFlightSmallView() { return this.flight_small_view; }

    public void setFlightSmallView(FlightSmallView flight_small_view) { this.flight_small_view = flight_small_view; }
}



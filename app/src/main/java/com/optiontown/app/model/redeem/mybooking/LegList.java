
package com.optiontown.app.model.redeem.mybooking;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LegList {

    @SerializedName("flight_small_view")
    @Expose
    private FlightSmallView flightSmallView;
    @SerializedName("Flight_Full_View")
    @Expose
    private FlightFullView flightFullView;

    /**
     * 
     * @return
     *     The flightSmallView
     */
    public FlightSmallView getFlightSmallView() {
        return flightSmallView;
    }

    /**
     * 
     * @param flightSmallView
     *     The flight_small_view
     */
    public void setFlightSmallView(FlightSmallView flightSmallView) {
        this.flightSmallView = flightSmallView;
    }

    /**
     * 
     * @return
     *     The flightFullView
     */
    public FlightFullView getFlightFullView() {
        return flightFullView;
    }

    /**
     * 
     * @param flightFullView
     *     The Flight_Full_View
     */
    public void setFlightFullView(FlightFullView flightFullView) {
        this.flightFullView = flightFullView;
    }

}

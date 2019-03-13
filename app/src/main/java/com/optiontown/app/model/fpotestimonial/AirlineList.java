
package com.optiontown.app.model.fpotestimonial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirlineList {

    @SerializedName("AirlineID")
    @Expose
    private String airlineID;
    @SerializedName("AirlineLabel")
    @Expose
    private String airlineLabel;

    /**
     * 
     * @return
     *     The airlineID
     */
    public String getAirlineID() {
        return airlineID;
    }

    /**
     * 
     * @param airlineID
     *     The AirlineID
     */
    public void setAirlineID(String airlineID) {
        this.airlineID = airlineID;
    }

    /**
     * 
     * @return
     *     The airlineLabel
     */
    public String getAirlineLabel() {
        return airlineLabel;
    }

    /**
     * 
     * @param airlineLabel
     *     The AirlineLabel
     */
    public void setAirlineLabel(String airlineLabel) {
        this.airlineLabel = airlineLabel;
    }

}

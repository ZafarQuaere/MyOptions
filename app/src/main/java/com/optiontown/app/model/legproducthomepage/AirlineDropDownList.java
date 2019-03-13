
package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AirlineDropDownList {

    @SerializedName("Airline_Code")
    @Expose
    private String airlineCode;
    @SerializedName("Airline_Name")
    @Expose
    private String airlineName;

    public boolean isSelectedAirline() {
        return selectedAirline;
    }

    private  boolean selectedAirline;

    /**
     * 
     * @return
     *     The airlineCode
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * 
     * @param airlineCode
     *     The Airline_Code
     */
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    /**
     * 
     * @return
     *     The airlineName
     */
    public String getAirlineName() {
        return airlineName;
    }

    /**
     * 
     * @param airlineName
     *     The Airline_Name
     */
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public void setSelectedAirline(boolean selectedAirline) {
        this.selectedAirline = selectedAirline;
    }
}

package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 02-06-2016.
 */


public class AirlineDropDownArray implements Serializable
{
    private String Airlline;

    public String getAirlline() { return this.Airlline; }

    public void setAirlline(String Airlline) { this.Airlline = Airlline; }

    private int AirlineId;

    public int getAirlineId() { return this.AirlineId; }

    public void setAirlineId(int AirlineId) { this.AirlineId = AirlineId; }

    private boolean selectedAirline;

    public boolean isSelectedAirline() {
        return selectedAirline;
    }

    public void setSelectedAirline(boolean selectedAirline) {
        this.selectedAirline = selectedAirline;
    }
}

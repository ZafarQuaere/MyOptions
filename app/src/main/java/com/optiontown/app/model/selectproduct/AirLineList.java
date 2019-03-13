package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class AirLineList implements Serializable {
    private String Help;

    public String getHelp() { return this.Help; }

    public void setHelp(String Help) { this.Help = Help; }

    private String Title;

    public String getTitle() { return this.Title; }

    public void setTitle(String Title) { this.Title = Title; }

    private ArrayList<AirlineDropDownArray> airlineDropDownArray;

    public ArrayList<AirlineDropDownArray> getAirlineDropDownArray() { return this.airlineDropDownArray; }

    public void setAirlineDropDownArray(ArrayList<AirlineDropDownArray> airlineDropDownArray) { this.airlineDropDownArray = airlineDropDownArray; }

    private int SelectedAirLineId;

    public int getSelectedAirLineId() {
        return SelectedAirLineId;
    }

    public void setSelectedAirLineId(int selectedAirLineId) {
        SelectedAirLineId = selectedAirLineId;
    }
}

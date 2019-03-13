package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class FList implements Serializable
{
    private String Help;

    public String getHelp() { return this.Help; }

    public void setHelp(String Help) { this.Help = Help; }

    private ArrayList<FlightsList> FlightsList;

    public ArrayList<FlightsList> getFlightsList() { return this.FlightsList; }

    public void setFlightsList(ArrayList<FlightsList> FlightsList) { this.FlightsList = FlightsList; }

    private String validtylabel;

    public String getValidtylabel() { return this.validtylabel; }

    public void setValidtylabel(String validtylabel) { this.validtylabel = validtylabel; }

    private Validity Validity;

    public Validity getValidity() { return this.Validity; }

    public void setValidity(Validity Validity) { this.Validity = Validity; }

    private int CreditId;

    public int getCreditId() { return this.CreditId; }

    public void setCreditId(int CreditId) { this.CreditId = CreditId; }

    private String Title;

    public String getTitle() { return this.Title; }

    public void setTitle(String Title) { this.Title = Title; }
}

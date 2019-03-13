package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 02-06-2016.
 */
public class FlightsList implements Serializable
{
    private String creditValue;

    public String getCreditValue() { return this.creditValue; }

    public void setCreditValue(String creditValue) { this.creditValue = creditValue; }

    private int creditId;

    public int getCreditId() { return this.creditId; }

    public void setCreditId(int creditId) { this.creditId = creditId; }

    public boolean isSelectedCredit() {
        return selectedCredit;
    }

    public void setSelectedCredit(boolean selectedCredit) {
        this.selectedCredit = selectedCredit;
    }

    private boolean selectedCredit;


}

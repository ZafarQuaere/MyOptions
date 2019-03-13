package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 02-06-2016.
 */


public class PassDataNormal implements Serializable
{
    private int Value;

    public int getValue() { return this.Value; }

    public void setValue(int Value) { this.Value = Value; }

    private String LABL_Passengers;

    public String getLABLPassengers() { return this.LABL_Passengers; }

    public void setLABLPassengers(String LABL_Passengers) { this.LABL_Passengers = LABL_Passengers; }

    private int passTypeId;

    public int getPassTypeId() { return this.passTypeId; }

    public void setPassTypeId(int passTypeId) { this.passTypeId = passTypeId; }

    private boolean selectedPassenger;

    public boolean isSelectedPassenger() {
        return selectedPassenger;
    }

    public void setSelectedPassenger(boolean selectedPassenger) {
        this.selectedPassenger = selectedPassenger;
    }



}


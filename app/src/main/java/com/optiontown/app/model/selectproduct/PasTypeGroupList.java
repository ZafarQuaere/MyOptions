package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by zafar.imam on 06-04-2017.
 */

public class PasTypeGroupList implements Serializable {

    private String LABL_Passengers;

    public String getLABLPassengers() { return this.LABL_Passengers; }

    public void setLABLPassengers(String LABL_Passengers) { this.LABL_Passengers = LABL_Passengers; }

    private int passTypeId;

    public int getPassTypeId() { return this.passTypeId; }

    public void setPassTypeId(int passTypeId) { this.passTypeId = passTypeId; }

    private String Value;

    public String getValue() { return this.Value; }

    public void setValue(String Value) { this.Value = Value; }

    private boolean selectedPassenger;

    public boolean isSelectedPassenger() {
        return selectedPassenger;
    }

    public void setSelectedPassenger(boolean selectedPassenger) {
        this.selectedPassenger = selectedPassenger;
    }
}

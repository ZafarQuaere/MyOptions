package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 02-06-2016.
 */
public class CabinArray implements Serializable
{
    private int CabinId;

    public int getCabinId() { return this.CabinId; }

    public void setCabinId(int CabinId) { this.CabinId = CabinId; }

    private String CabinName;

    public String getCabinName() { return this.CabinName; }

    public void setCabinName(String CabinName) { this.CabinName = CabinName; }

    private boolean selectedCabin;

    public boolean isSelectedCabin() {
        return selectedCabin;
    }

    public void setSelectedCabin(boolean selectedCabin) {
        this.selectedCabin = selectedCabin;
    }
}

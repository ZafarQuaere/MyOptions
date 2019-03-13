
package com.optiontown.app.model.utosearchresult;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PriceUpcabinDetail {


    @SerializedName("boostListWithDisplayValue")
    @Expose
    private List<BoostListWithDisplayValue> boostListWithDisplayValue = null;
    @SerializedName("UpcabinNameWithBoostList")
    @Expose
    private String upcabinNameWithBoostList;

    public List<BoostListWithDisplayValue> getBoostListWithDisplayValue() {
        return boostListWithDisplayValue;
    }

    public void setBoostListWithDisplayValue(List<BoostListWithDisplayValue> boostListWithDisplayValue) {
        this.boostListWithDisplayValue = boostListWithDisplayValue;
    }

    public String getUpcabinNameWithBoostList() {
        return upcabinNameWithBoostList;
    }

    public void setUpcabinNameWithBoostList(String upcabinNameWithBoostList) {
        this.upcabinNameWithBoostList = upcabinNameWithBoostList;
    }


    private boolean Selected;

    public boolean isSelected() {
        return Selected;
    }

    public void setSelected(boolean selected) {
        Selected = selected;
    }



}

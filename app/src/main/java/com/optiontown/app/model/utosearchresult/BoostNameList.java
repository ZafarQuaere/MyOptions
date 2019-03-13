
package com.optiontown.app.model.utosearchresult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoostNameList {

    @SerializedName("boostNameLabel")
    @Expose
    private String boostNameLabel;

    public String getBoostNameLabel() {
        return boostNameLabel;
    }

    public void setBoostNameLabel(String boostNameLabel) {
        this.boostNameLabel = boostNameLabel;
    }

}

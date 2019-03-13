
package com.optiontown.app.model.legviewdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassengersFullList {

    @SerializedName("paxName")
    @Expose
    private String paxName;

    /**
     * 
     * @return
     *     The paxName
     */
    public String getPaxName() {
        return paxName;
    }

    /**
     * 
     * @param paxName
     *     The paxName
     */
    public void setPaxName(String paxName) {
        this.paxName = paxName;
    }

}

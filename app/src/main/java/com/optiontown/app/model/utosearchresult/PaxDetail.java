
package com.optiontown.app.model.utosearchresult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaxDetail {

    @SerializedName("PaxName")
    @Expose
    private String paxName;
    @SerializedName("PaxID")
    @Expose
    private Integer paxID;

    public String getPaxName() {
        return paxName;
    }

    public void setPaxName(String paxName) {
        this.paxName = paxName;
    }

    public Integer getPaxID() {
        return paxID;
    }

    public void setPaxID(Integer paxID) {
        this.paxID = paxID;
    }

}

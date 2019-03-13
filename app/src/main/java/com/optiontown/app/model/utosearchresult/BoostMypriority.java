
package com.optiontown.app.model.utosearchresult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoostMypriority implements Serializable{

    @SerializedName("boostDetails")
    @Expose
    private List<BoostDetail> boostDetails = new ArrayList<BoostDetail>();

    /**
     * 
     * @return
     *     The boostDetails
     */
    public List<BoostDetail> getBoostDetails() {
        return boostDetails;
    }

    /**
     * 
     * @param boostDetails
     *     The boostDetails
     */
    public void setBoostDetails(List<BoostDetail> boostDetails) {
        this.boostDetails = boostDetails;
    }

}

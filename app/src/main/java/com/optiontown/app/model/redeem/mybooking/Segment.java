
package com.optiontown.app.model.redeem.mybooking;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment {

    @SerializedName("legList")
    @Expose
    private List<LegList> legList = new ArrayList<LegList>();

    /**
     * 
     * @return
     *     The legList
     */
    public List<LegList> getLegList() {
        return legList;
    }

    /**
     * 
     * @param legList
     *     The legList
     */
    public void setLegList(List<LegList> legList) {
        this.legList = legList;
    }

}

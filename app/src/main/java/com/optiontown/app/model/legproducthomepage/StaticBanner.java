
package com.optiontown.app.model.legproducthomepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaticBanner {

    @SerializedName("FrontBanner")
    @Expose
    private List<FrontBanner> frontBanner = new ArrayList<FrontBanner>();

    /**
     * 
     * @return
     *     The frontBanner
     */
    public List<FrontBanner> getFrontBanner() {
        return frontBanner;
    }

    /**
     * 
     * @param frontBanner
     *     The FrontBanner
     */
    public void setFrontBanner(List<FrontBanner> frontBanner) {
        this.frontBanner = frontBanner;
    }

}

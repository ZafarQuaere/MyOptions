
package com.optiontown.app.model.legreview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedFlightDetail {

    @SerializedName("credit_required")
    @Expose
    private Integer creditRequired;
    @SerializedName("flight_detail")
    @Expose
    private String flightDetail;

    public Integer getCreditRequired() {
        return creditRequired;
    }

    public void setCreditRequired(Integer creditRequired) {
        this.creditRequired = creditRequired;
    }

    public String getFlightDetail() {
        return flightDetail;
    }

    public void setFlightDetail(String flightDetail) {
        this.flightDetail = flightDetail;
    }

}


package com.optiontown.app.model.legreview;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassLevelPriceSummary {

    @SerializedName("flight_label")
    @Expose
    private String flightLabel;
    @SerializedName("opTotalRedTitle")
    @Expose
    private String opTotalRedTitle;
    @SerializedName("selected_flight_detail")
    @Expose
    private List<SelectedFlightDetail> selectedFlightDetail = null;
    @SerializedName("opCreditSummaryHed")
    @Expose
    private String opCreditSummaryHed;
    @SerializedName("total_credit_to_redeem")
    @Expose
    private Integer totalCreditToRedeem;
    @SerializedName("opCreditSummaryTitle")
    @Expose
    private String opCreditSummaryTitle;

    public String getFlightLabel() {
        return flightLabel;
    }

    public void setFlightLabel(String flightLabel) {
        this.flightLabel = flightLabel;
    }

    public String getOpTotalRedTitle() {
        return opTotalRedTitle;
    }

    public void setOpTotalRedTitle(String opTotalRedTitle) {
        this.opTotalRedTitle = opTotalRedTitle;
    }

    public List<SelectedFlightDetail> getSelectedFlightDetail() {
        return selectedFlightDetail;
    }

    public void setSelectedFlightDetail(List<SelectedFlightDetail> selectedFlightDetail) {
        this.selectedFlightDetail = selectedFlightDetail;
    }

    public String getOpCreditSummaryHed() {
        return opCreditSummaryHed;
    }

    public void setOpCreditSummaryHed(String opCreditSummaryHed) {
        this.opCreditSummaryHed = opCreditSummaryHed;
    }

    public Integer getTotalCreditToRedeem() {
        return totalCreditToRedeem;
    }

    public void setTotalCreditToRedeem(Integer totalCreditToRedeem) {
        this.totalCreditToRedeem = totalCreditToRedeem;
    }

    public String getOpCreditSummaryTitle() {
        return opCreditSummaryTitle;
    }

    public void setOpCreditSummaryTitle(String opCreditSummaryTitle) {
        this.opCreditSummaryTitle = opCreditSummaryTitle;
    }

}

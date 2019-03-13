
package com.optiontown.app.model.legreview;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceSummary {


    @SerializedName("pass_level_price_summary")
    @Expose
    private PassLevelPriceSummary passLevelPriceSummary;

    @SerializedName("tgpInitialPriceLabel")
    @Expose
    private String tgpInitialPriceLabel;
    @SerializedName("shiftPriceHelpLabel")
    @Expose
    private String shiftPriceHelpLabel;
    @SerializedName("flightDetail")
    @Expose
    private List<FlightDetail> flightDetail = new ArrayList<FlightDetail>();
    @SerializedName("tgpInitialPriceHelpLabel")
    @Expose
    private String tgpInitialPriceHelpLabel;
    @SerializedName("backSearchData")
    @Expose
    private BackSearchData backSearchData;
    @SerializedName("Pax_Count")
    @Expose
    private Integer paxCount;
    @SerializedName("totalAmountToPay")
    @Expose
    private Integer totalAmountToPay;
    @SerializedName("displayCurrencySymbol")
    @Expose
    private String displayCurrencySymbol;
    @SerializedName("priceSummaryHeading")
    @Expose
    private String priceSummaryHeading;
    @SerializedName("shiftPriceHeadingLabel")
    @Expose
    private String shiftPriceHeadingLabel;
    @SerializedName("product_label")
    @Expose
    private String productLabel;
    @SerializedName("purchaseAmountTopayLabel")
    @Expose
    private String purchaseAmountTopayLabel;
    @SerializedName("LABL_Flight_Label")
    @Expose
    private String lABLFlightLabel;
    @SerializedName("LABL_Pax_Count_Label")
    @Expose
    private String lABLPaxCountLabel;
    @SerializedName("IsPartialOn")
    @Expose
    private Boolean isPartialOn;

    public String getLABL_Total_Price_Label() {
        return LABL_Total_Price_Label;
    }

    public void setLABL_Total_Price_Label(String LABL_Total_Price_Label) {
        this.LABL_Total_Price_Label = LABL_Total_Price_Label;
    }

    @SerializedName("LABL_Total_Price_Label")
    @Expose
    private String LABL_Total_Price_Label;


    public Boolean getIsPartialOn() {
        return isPartialOn;
    }

    public void setIsPartialOn(Boolean isPartialOn) {
        this.isPartialOn = isPartialOn;
    }


    public PassLevelPriceSummary getPassLevelPriceSummary() {
        return passLevelPriceSummary;
    }

    public void setPassLevelPriceSummary(PassLevelPriceSummary passLevelPriceSummary) {
        this.passLevelPriceSummary = passLevelPriceSummary;
    }
    public String getTgpInitialPriceLabel() {
        return tgpInitialPriceLabel;
    }

    /**
     * 
     * @param tgpInitialPriceLabel
     *     The tgpInitialPriceLabel
     */
    public void setTgpInitialPriceLabel(String tgpInitialPriceLabel) {
        this.tgpInitialPriceLabel = tgpInitialPriceLabel;
    }

    /**
     * 
     * @return
     *     The shiftPriceHelpLabel
     */
    public String getShiftPriceHelpLabel() {
        return shiftPriceHelpLabel;
    }

    /**
     * 
     * @param shiftPriceHelpLabel
     *     The shiftPriceHelpLabel
     */
    public void setShiftPriceHelpLabel(String shiftPriceHelpLabel) {
        this.shiftPriceHelpLabel = shiftPriceHelpLabel;
    }

    /**
     * 
     * @return
     *     The flightDetail
     */
    public List<FlightDetail> getFlightDetail() {
        return flightDetail;
    }

    /**
     * 
     * @param flightDetail
     *     The flightDetail
     */
    public void setFlightDetail(List<FlightDetail> flightDetail) {
        this.flightDetail = flightDetail;
    }

    /**
     * 
     * @return
     *     The tgpInitialPriceHelpLabel
     */
    public String getTgpInitialPriceHelpLabel() {
        return tgpInitialPriceHelpLabel;
    }

    /**
     * 
     * @param tgpInitialPriceHelpLabel
     *     The tgpInitialPriceHelpLabel
     */
    public void setTgpInitialPriceHelpLabel(String tgpInitialPriceHelpLabel) {
        this.tgpInitialPriceHelpLabel = tgpInitialPriceHelpLabel;
    }

    /**
     * 
     * @return
     *     The backSearchData
     */
    public BackSearchData getBackSearchData() {
        return backSearchData;
    }

    /**
     * 
     * @param backSearchData
     *     The backSearchData
     */
    public void setBackSearchData(BackSearchData backSearchData) {
        this.backSearchData = backSearchData;
    }

    /**
     *
     * @return
     *     The paxCount
     */
    public Integer getPaxCount() {
        return paxCount;
    }

    /**
     *
     * @param paxCount
     *     The Pax_Count
     */
    public void setPaxCount(Integer paxCount) {
        this.paxCount = paxCount;
    }

    /**
     *
     * @return
     *     The totalAmountToPay
     */
    public Integer getTotalAmountToPay() {
        return totalAmountToPay;
    }

    /**
     *
     * @param totalAmountToPay
     *     The totalAmountToPay
     */
    public void setTotalAmountToPay(Integer totalAmountToPay) {
        this.totalAmountToPay = totalAmountToPay;
    }

    /**
     *
     * @return
     *     The displayCurrencySymbol
     */
    public String getDisplayCurrencySymbol() {
        return displayCurrencySymbol;
    }

    /**
     *
     * @param displayCurrencySymbol
     *     The displayCurrencySymbol
     */
    public void setDisplayCurrencySymbol(String displayCurrencySymbol) {
        this.displayCurrencySymbol = displayCurrencySymbol;
    }

    /**
     *
     * @return
     *     The priceSummaryHeading
     */
    public String getPriceSummaryHeading() {
        return priceSummaryHeading;
    }

    /**
     *
     * @param priceSummaryHeading
     *     The priceSummaryHeading
     */
    public void setPriceSummaryHeading(String priceSummaryHeading) {
        this.priceSummaryHeading = priceSummaryHeading;
    }

    /**
     *
     * @return
     *     The shiftPriceHeadingLabel
     */
    public String getShiftPriceHeadingLabel() {
        return shiftPriceHeadingLabel;
    }

    /**
     *
     * @param shiftPriceHeadingLabel
     *     The shiftPriceHeadingLabel
     */
    public void setShiftPriceHeadingLabel(String shiftPriceHeadingLabel) {
        this.shiftPriceHeadingLabel = shiftPriceHeadingLabel;
    }

    /**
     *
     * @return
     *     The productLabel
     */
    public String getProductLabel() {
        return productLabel;
    }

    /**
     *
     * @param productLabel
     *     The product_label
     */
    public void setProductLabel(String productLabel) {
        this.productLabel = productLabel;
    }

    /**
     *
     * @return
     *     The purchaseAmountTopayLabel
     */
    public String getPurchaseAmountTopayLabel() {
        return purchaseAmountTopayLabel;
    }

    /**
     *
     * @param purchaseAmountTopayLabel
     *     The purchaseAmountTopayLabel
     */
    public void setPurchaseAmountTopayLabel(String purchaseAmountTopayLabel) {
        this.purchaseAmountTopayLabel = purchaseAmountTopayLabel;
    }

    /**
     *
     * @return
     *     The lABLFlightLabel
     */
    public String getLABLFlightLabel() {
        return lABLFlightLabel;
    }

    /**
     *
     * @param lABLFlightLabel
     *     The LABL_Flight_Label
     */
    public void setLABLFlightLabel(String lABLFlightLabel) {
        this.lABLFlightLabel = lABLFlightLabel;
    }

    /**
     *
     * @return
     *     The lABLPaxCountLabel
     */
    public String getLABLPaxCountLabel() {
        return lABLPaxCountLabel;
    }

    /**
     *
     * @param lABLPaxCountLabel
     *     The LABL_Pax_Count_Label
     */
    public void setLABLPaxCountLabel(String lABLPaxCountLabel) {
        this.lABLPaxCountLabel = lABLPaxCountLabel;
    }

}

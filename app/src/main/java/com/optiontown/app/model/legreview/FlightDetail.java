
package com.optiontown.app.model.legreview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightDetail {

    @SerializedName("tgpInitialPriceLabel")
    @Expose
    private String tgpInitialPriceLabel;
    @SerializedName("AirlineLogoImage")
    @Expose
    private String airlineLogoImage;
    @SerializedName("ArriveAirport_DisplayName")
    @Expose
    private String arriveAirportDisplayName;
    @SerializedName("paxCount")
    @Expose
    private Integer paxCount;
    @SerializedName("LABL_Subtotal_Label")
    @Expose
    private String lABLSubtotalLabel;
    @SerializedName("CombineOptionPrices")
    @Expose
    private Integer combineOptionPrices;
    @SerializedName("displayCurrencySymbol")
    @Expose
    private String displayCurrencySymbol;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("DepartureAirport_code")
    @Expose
    private String departureAirportCode;
    @SerializedName("AirportCode")
    @Expose
    private String airportCode;
    @SerializedName("UpcabinMaxShiftPrice")
    @Expose
    private Integer upcabinMaxShiftPrice;
    @SerializedName("shiftPriceHeadingLabel")
    @Expose
    private String shiftPriceHeadingLabel;
    @SerializedName("product_label")
    @Expose
    private String productLabel;
    @SerializedName("ArriveAirport_code")
    @Expose
    private String arriveAirportCode;
    @SerializedName("DepartureAirport_DisplayName")
    @Expose
    private String departureAirportDisplayName;
    @SerializedName("AmountToPay")
    @Expose
    private Integer amountToPay;
    @SerializedName("purchaseAmountTopayLabel")
    @Expose
    private String purchaseAmountTopayLabel;
    @SerializedName("LABL_Flight_Label")
    @Expose
    private String lABLFlightLabel;
    @SerializedName("LABL_Pax_Count_Label")
    @Expose
    private String lABLPaxCountLabel;

    public String getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
    }

    @SerializedName("priceLabel")
    @Expose
    private String priceLabel;

    public String getTgpInitialPriceLabel() {
        return tgpInitialPriceLabel;
    }

    public void setTgpInitialPriceLabel(String tgpInitialPriceLabel) {
        this.tgpInitialPriceLabel = tgpInitialPriceLabel;
    }

    public String getAirlineLogoImage() {
        return airlineLogoImage;
    }

    public void setAirlineLogoImage(String airlineLogoImage) {
        this.airlineLogoImage = airlineLogoImage;
    }

    public String getArriveAirportDisplayName() {
        return arriveAirportDisplayName;
    }

    public void setArriveAirportDisplayName(String arriveAirportDisplayName) {
        this.arriveAirportDisplayName = arriveAirportDisplayName;
    }

    public Integer getPaxCount() {
        return paxCount;
    }

    public void setPaxCount(Integer paxCount) {
        this.paxCount = paxCount;
    }

    public String getLABLSubtotalLabel() {
        return lABLSubtotalLabel;
    }

    public void setLABLSubtotalLabel(String lABLSubtotalLabel) {
        this.lABLSubtotalLabel = lABLSubtotalLabel;
    }

    public Integer getCombineOptionPrices() {
        return combineOptionPrices;
    }

    public void setCombineOptionPrices(Integer combineOptionPrices) {
        this.combineOptionPrices = combineOptionPrices;
    }

    public String getDisplayCurrencySymbol() {
        return displayCurrencySymbol;
    }

    public void setDisplayCurrencySymbol(String displayCurrencySymbol) {
        this.displayCurrencySymbol = displayCurrencySymbol;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Integer getUpcabinMaxShiftPrice() {
        return upcabinMaxShiftPrice;
    }

    public void setUpcabinMaxShiftPrice(Integer upcabinMaxShiftPrice) {
        this.upcabinMaxShiftPrice = upcabinMaxShiftPrice;
    }

    public String getShiftPriceHeadingLabel() {
        return shiftPriceHeadingLabel;
    }

    public void setShiftPriceHeadingLabel(String shiftPriceHeadingLabel) {
        this.shiftPriceHeadingLabel = shiftPriceHeadingLabel;
    }

    public String getProductLabel() {
        return productLabel;
    }

    public void setProductLabel(String productLabel) {
        this.productLabel = productLabel;
    }

    public String getArriveAirportCode() {
        return arriveAirportCode;
    }

    public void setArriveAirportCode(String arriveAirportCode) {
        this.arriveAirportCode = arriveAirportCode;
    }

    public String getDepartureAirportDisplayName() {
        return departureAirportDisplayName;
    }

    public void setDepartureAirportDisplayName(String departureAirportDisplayName) {
        this.departureAirportDisplayName = departureAirportDisplayName;
    }

    public Integer getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Integer amountToPay) {
        this.amountToPay = amountToPay;
    }

    public String getPurchaseAmountTopayLabel() {
        return purchaseAmountTopayLabel;
    }

    public void setPurchaseAmountTopayLabel(String purchaseAmountTopayLabel) {
        this.purchaseAmountTopayLabel = purchaseAmountTopayLabel;
    }

    public String getLABLFlightLabel() {
        return lABLFlightLabel;
    }

    public void setLABLFlightLabel(String lABLFlightLabel) {
        this.lABLFlightLabel = lABLFlightLabel;
    }

    public String getLABLPaxCountLabel() {
        return lABLPaxCountLabel;
    }

    public void setLABLPaxCountLabel(String lABLPaxCountLabel) {
        this.lABLPaxCountLabel = lABLPaxCountLabel;
    }

}

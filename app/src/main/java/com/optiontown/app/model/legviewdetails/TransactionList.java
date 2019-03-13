
package com.optiontown.app.model.legviewdetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionList {

    @SerializedName("cabinPrice")
    @Expose
    private Integer cabinPrice;
    @SerializedName("CardNumberCurrencyAmount")
    @Expose
    private String cardNumberCurrencyAmount;
    @SerializedName("Departure_Date")
    @Expose
    private String departureDate;
    @SerializedName("Total_Amount_Value")
    @Expose
    private String totalAmountValue;
    @SerializedName("displayCurrencySymbol")
    @Expose
    private String displayCurrencySymbol;
    @SerializedName("Leg_Transaction_Date")
    @Expose
    private String legTransactionDate;
    @SerializedName("TransHistoryStatusLabel")
    @Expose
    private String transHistoryStatusLabel;
    @SerializedName("Airline_Code")
    @Expose
    private String airlineCode;
    @SerializedName("Total_Amount_Label")
    @Expose
    private String totalAmountLabel;
    @SerializedName("Trans_History_Event_Label")
    @Expose
    private String transHistoryEventLabel;
    @SerializedName("TransHistoryAccountLabel")
    @Expose
    private String transHistoryAccountLabel;
    @SerializedName("UpCabinName")
    @Expose
    private String upCabinName;
    @SerializedName("statusLabel")
    @Expose
    private String statusLabel;
    @SerializedName("Career_Flight_Number")
    @Expose
    private String careerFlightNumber;
    @SerializedName("Departure_Arrive_Airport")
    @Expose
    private String departureArriveAirport;
    @SerializedName("UpCabin_Names")
    @Expose
    private String upCabinNames;
    @SerializedName("pnd_Sucsess_label")
    @Expose
    private String pndSucsessLabel;

    public String getTrans_History_Event_Label_leg() {
        return Trans_History_Event_Label_leg;
    }

    public void setTrans_History_Event_Label_leg(String trans_History_Event_Label_leg) {
        Trans_History_Event_Label_leg = trans_History_Event_Label_leg;
    }

    @SerializedName("Trans_History_Event_Label_leg")
    @Expose
    private String Trans_History_Event_Label_leg;

    /**
     * 
     * @return
     *     The cabinPrice
     */
    public Integer getCabinPrice() {
        return cabinPrice;
    }

    /**
     * 
     * @param cabinPrice
     *     The cabinPrice
     */
    public void setCabinPrice(Integer cabinPrice) {
        this.cabinPrice = cabinPrice;
    }

    /**
     * 
     * @return
     *     The cardNumberCurrencyAmount
     */
    public String getCardNumberCurrencyAmount() {
        return cardNumberCurrencyAmount;
    }

    /**
     * 
     * @param cardNumberCurrencyAmount
     *     The CardNumberCurrencyAmount
     */
    public void setCardNumberCurrencyAmount(String cardNumberCurrencyAmount) {
        this.cardNumberCurrencyAmount = cardNumberCurrencyAmount;
    }

    /**
     * 
     * @return
     *     The departureDate
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * 
     * @param departureDate
     *     The Departure_Date
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * 
     * @return
     *     The totalAmountValue
     */
    public String getTotalAmountValue() {
        return totalAmountValue;
    }

    /**
     * 
     * @param totalAmountValue
     *     The Total_Amount_Value
     */
    public void setTotalAmountValue(String totalAmountValue) {
        this.totalAmountValue = totalAmountValue;
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
     *     The legTransactionDate
     */
    public String getLegTransactionDate() {
        return legTransactionDate;
    }

    /**
     * 
     * @param legTransactionDate
     *     The Leg_Transaction_Date
     */
    public void setLegTransactionDate(String legTransactionDate) {
        this.legTransactionDate = legTransactionDate;
    }

    /**
     * 
     * @return
     *     The transHistoryStatusLabel
     */
    public String getTransHistoryStatusLabel() {
        return transHistoryStatusLabel;
    }

    /**
     * 
     * @param transHistoryStatusLabel
     *     The TransHistoryStatusLabel
     */
    public void setTransHistoryStatusLabel(String transHistoryStatusLabel) {
        this.transHistoryStatusLabel = transHistoryStatusLabel;
    }

    /**
     * 
     * @return
     *     The airlineCode
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * 
     * @param airlineCode
     *     The Airline_Code
     */
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    /**
     * 
     * @return
     *     The totalAmountLabel
     */
    public String getTotalAmountLabel() {
        return totalAmountLabel;
    }

    /**
     * 
     * @param totalAmountLabel
     *     The Total_Amount_Label
     */
    public void setTotalAmountLabel(String totalAmountLabel) {
        this.totalAmountLabel = totalAmountLabel;
    }

    /**
     * 
     * @return
     *     The transHistoryEventLabel
     */
    public String getTransHistoryEventLabel() {
        return transHistoryEventLabel;
    }

    /**
     * 
     * @param transHistoryEventLabel
     *     The Trans_History_Event_Label
     */
    public void setTransHistoryEventLabel(String transHistoryEventLabel) {
        this.transHistoryEventLabel = transHistoryEventLabel;
    }

    /**
     * 
     * @return
     *     The transHistoryAccountLabel
     */
    public String getTransHistoryAccountLabel() {
        return transHistoryAccountLabel;
    }

    /**
     * 
     * @param transHistoryAccountLabel
     *     The TransHistoryAccountLabel
     */
    public void setTransHistoryAccountLabel(String transHistoryAccountLabel) {
        this.transHistoryAccountLabel = transHistoryAccountLabel;
    }

    /**
     * 
     * @return
     *     The upCabinName
     */
    public String getUpCabinName() {
        return upCabinName;
    }

    /**
     * 
     * @param upCabinName
     *     The UpCabinName
     */
    public void setUpCabinName(String upCabinName) {
        this.upCabinName = upCabinName;
    }

    /**
     * 
     * @return
     *     The statusLabel
     */
    public String getStatusLabel() {
        return statusLabel;
    }

    /**
     * 
     * @param statusLabel
     *     The statusLabel
     */
    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    /**
     * 
     * @return
     *     The careerFlightNumber
     */
    public String getCareerFlightNumber() {
        return careerFlightNumber;
    }

    /**
     * 
     * @param careerFlightNumber
     *     The Career_Flight_Number
     */
    public void setCareerFlightNumber(String careerFlightNumber) {
        this.careerFlightNumber = careerFlightNumber;
    }

    /**
     * 
     * @return
     *     The departureArriveAirport
     */
    public String getDepartureArriveAirport() {
        return departureArriveAirport;
    }

    /**
     * 
     * @param departureArriveAirport
     *     The Departure_Arrive_Airport
     */
    public void setDepartureArriveAirport(String departureArriveAirport) {
        this.departureArriveAirport = departureArriveAirport;
    }

    /**
     * 
     * @return
     *     The upCabinNames
     */
    public String getUpCabinNames() {
        return upCabinNames;
    }

    /**
     * 
     * @param upCabinNames
     *     The UpCabin_Names
     */
    public void setUpCabinNames(String upCabinNames) {
        this.upCabinNames = upCabinNames;
    }

    /**
     * 
     * @return
     *     The pndSucsessLabel
     */
    public String getPndSucsessLabel() {
        return pndSucsessLabel;
    }

    /**
     * 
     * @param pndSucsessLabel
     *     The pnd_Sucsess_label
     */
    public void setPndSucsessLabel(String pndSucsessLabel) {
        this.pndSucsessLabel = pndSucsessLabel;
    }

}

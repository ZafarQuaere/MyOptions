
package com.optiontown.app.model.legreview;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMTListDatum {

    @SerializedName("upcabinImage")
    @Expose
    private String upcabinImage;
    @SerializedName("mtFreeOfferLabel")
    @Expose
    private String mtFreeOfferLabel;
    @SerializedName("avgShiftPricePerPax")
    @Expose
    private String avgShiftPricePerPax;
    @SerializedName("selectedKey")
    @Expose
    private String selectedKey;
    @SerializedName("mtimage")
    @Expose
    private String mtimage;
    @SerializedName("currencySymbol")
    @Expose
    private String currencySymbol;
    @SerializedName("viewLabel")
    @Expose
    private String viewLabel;
    @SerializedName("upCabinName")
    @Expose
    private String upCabinName;
    @SerializedName("saveAmount")
    @Expose
    private String saveAmount;
    @SerializedName("mtESORules")
    @Expose
    private MtESORules mtESORules;
    @SerializedName("mtHeadingLabel")
    @Expose
    private String mtHeadingLabel;
    @SerializedName("saveLabel")
    @Expose
    private String saveLabel;
    @SerializedName("selectLabel")
    @Expose
    private String selectLabel;


    public String getSaveLabel() {
        return saveLabel;
    }

    public void setSaveLabel(String saveLabel) {
        this.saveLabel = saveLabel;
    }



    public String getSelectLabel() {
        return selectLabel;
    }

    public void setSelectLabel(String selectLabel) {
        this.selectLabel = selectLabel;
    }



    /**
     * 
     * @return
     *     The upcabinImage
     */
    public String getUpcabinImage() {
        return upcabinImage;
    }

    /**
     * 
     * @param upcabinImage
     *     The upcabinImage
     */
    public void setUpcabinImage(String upcabinImage) {
        this.upcabinImage = upcabinImage;
    }

    /**
     * 
     * @return
     *     The mtFreeOfferLabel
     */
    public String getMtFreeOfferLabel() {
        return mtFreeOfferLabel;
    }

    /**
     * 
     * @param mtFreeOfferLabel
     *     The mtFreeOfferLabel
     */
    public void setMtFreeOfferLabel(String mtFreeOfferLabel) {
        this.mtFreeOfferLabel = mtFreeOfferLabel;
    }

    /**
     * 
     * @return
     *     The avgShiftPricePerPax
     */
    public String getAvgShiftPricePerPax() {
        return avgShiftPricePerPax;
    }

    /**
     * 
     * @param avgShiftPricePerPax
     *     The avgShiftPricePerPax
     */
    public void setAvgShiftPricePerPax(String avgShiftPricePerPax) {
        this.avgShiftPricePerPax = avgShiftPricePerPax;
    }

    /**
     * 
     * @return
     *     The selectedKey
     */
    public String getSelectedKey() {
        return selectedKey;
    }

    /**
     * 
     * @param selectedKey
     *     The selectedKey
     */
    public void setSelectedKey(String selectedKey) {
        this.selectedKey = selectedKey;
    }

    /**
     * 
     * @return
     *     The mtimage
     */
    public String getMtimage() {
        return mtimage;
    }

    /**
     * 
     * @param mtimage
     *     The mtimage
     */
    public void setMtimage(String mtimage) {
        this.mtimage = mtimage;
    }

    /**
     * 
     * @return
     *     The currencySymbol
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * 
     * @param currencySymbol
     *     The currencySymbol
     */
    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    /**
     * 
     * @return
     *     The viewLabel
     */
    public String getViewLabel() {
        return viewLabel;
    }

    /**
     * 
     * @param viewLabel
     *     The viewLabel
     */
    public void setViewLabel(String viewLabel) {
        this.viewLabel = viewLabel;
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
     *     The upCabinName
     */
    public void setUpCabinName(String upCabinName) {
        this.upCabinName = upCabinName;
    }

    /**
     * 
     * @return
     *     The saveAmount
     */
    public String getSaveAmount() {
        return saveAmount;
    }

    /**
     * 
     * @param saveAmount
     *     The saveAmount
     */
    public void setSaveAmount(String saveAmount) {
        this.saveAmount = saveAmount;
    }

    /**
     * 
     * @return
     *     The mtESORules
     */
    public MtESORules getMtESORules() {
        return mtESORules;
    }

    /**
     * 
     * @param mtESORules
     *     The mtESORules
     */
    public void setMtESORules(MtESORules mtESORules) {
        this.mtESORules = mtESORules;
    }

    /**
     * 
     * @return
     *     The mtHeadingLabel
     */
    public String getMtHeadingLabel() {
        return mtHeadingLabel;
    }

    /**
     * 
     * @param mtHeadingLabel
     *     The mtHeadingLabel
     */
    public void setMtHeadingLabel(String mtHeadingLabel) {
        this.mtHeadingLabel = mtHeadingLabel;
    }

}

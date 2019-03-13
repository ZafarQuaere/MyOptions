package com.optiontown.app.model.utosearchresult;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CabinDetailList {


    @SerializedName("StatusLabel")
    @Expose
    private String StatusLabel;

    @SerializedName("creditTitle")
    @Expose
    private String creditTitle;
    @SerializedName("creditRequiredLabel")
    @Expose
    private Integer creditRequiredLabel;
    @SerializedName("UtoAvailablePassNotApp")
    @Expose
    private String utoAvailablePassNotApp;

    @SerializedName("beforePndLabel")
    @Expose
    private String beforePndLabel;
    @SerializedName("utoSelectedImage")
    @Expose
    private String utoSelectedImage;
    @SerializedName("originalShiftPrice1")
    @Expose
    private String originalShiftPrice1;
    @SerializedName("cabinName")
    @Expose
    private String cabinName;
    @SerializedName("styleIdUpcabinConfirm")
    @Expose
    private String styleIdUpcabinConfirm;
    @SerializedName("isConfirmUpgradeDisplay")
    @Expose
    private Boolean isConfirmUpgradeDisplay;
    @SerializedName("styleIdUpcabinStandBy")
    @Expose
    private String styleIdUpcabinStandBy;
    @SerializedName("confirmUpgradePrice")
    @Expose
    private String confirmUpgradePrice;
    @SerializedName("Up_Cabin_image")
    @Expose
    private String Up_Cabin_image;
    @SerializedName("confirmedUpgradePrice")
    @Expose
    private String confirmedUpgradePrice;

    @SerializedName("isAvailable")
    @Expose
    private Integer isAvailable;
    @SerializedName("isPndDoneOnLeg")
    @Expose
    private Boolean isPndDoneOnLeg;
    @SerializedName("isUpgraded")
    @Expose
    private Integer isUpgraded;

    public Boolean getSeatMapShow() {
        return seatMapShow;
    }

    public void setSeatMapShow(Boolean seatMapShow) {
        this.seatMapShow = seatMapShow;
    }

    private Boolean seatMapShow;


    public Boolean getIsPndDoneOnLeg() {
        return isPndDoneOnLeg;
    }

    public void setIsPndDoneOnLeg(Boolean isPndDoneOnLeg) {
        this.isPndDoneOnLeg = isPndDoneOnLeg;
    }

    public String getCreditTitle() {
        return creditTitle;
    }

    public void setCreditTitle(String creditTitle) {
        this.creditTitle = creditTitle;
    }

    public Integer getCreditRequiredLabel() {
        return creditRequiredLabel;
    }

    public void setCreditRequiredLabel(Integer creditRequiredLabel) {
        this.creditRequiredLabel = creditRequiredLabel;
    }

    public String getUtoAvailablePassNotApp() {
        return utoAvailablePassNotApp;
    }

    public void setUtoAvailablePassNotApp(String utoAvailablePassNotApp) {
        this.utoAvailablePassNotApp = utoAvailablePassNotApp;
    }



    public Integer getIsUpgraded() {
        return isUpgraded;
    }

    public void setIsUpgraded(Integer isUpgraded) {
        this.isUpgraded = isUpgraded;
    }

    private boolean StandBy, Confirmed;

    public String getBeforePndLabel() {
        return beforePndLabel;
    }

    public void setBeforePndLabel(String beforePndLabel) {
        this.beforePndLabel = beforePndLabel;
    }

    public String getUtoSelectedImage() {
        return utoSelectedImage;
    }

    public void setUtoSelectedImage(String utoSelectedImage) {
        this.utoSelectedImage = utoSelectedImage;
    }

    public String getUp_Cabin_image() {
        return Up_Cabin_image;
    }

    public void setUp_Cabin_image(String up_Cabin_image) {
        Up_Cabin_image = up_Cabin_image;
    }

    public String getStatusLabel() {
        return StatusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        StatusLabel = statusLabel;
    }

    public Boolean getConfirmUpgradeDisplay() {
        return isConfirmUpgradeDisplay;
    }

    public void setConfirmUpgradeDisplay(Boolean confirmUpgradeDisplay) {
        isConfirmUpgradeDisplay = confirmUpgradeDisplay;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean getPndDoneOnLeg() {
        return isPndDoneOnLeg;
    }

    public void setPndDoneOnLeg(Boolean pndDoneOnLeg) {
        isPndDoneOnLeg = pndDoneOnLeg;
    }

    public boolean isStandBy() {
        return StandBy;
    }

    public void setStandBy(boolean standBy) {
        StandBy = standBy;
    }

    public boolean isConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        Confirmed = confirmed;
    }

    /**
     * @return The originalShiftPrice1
     */
    public String getOriginalShiftPrice1() {
        return originalShiftPrice1;
    }

    /**
     * @param originalShiftPrice1 The originalShiftPrice1
     */
    public void setOriginalShiftPrice1(String originalShiftPrice1) {
        this.originalShiftPrice1 = originalShiftPrice1;
    }

    /**
     * @return The cabinName
     */
    public String getCabinName() {
        return cabinName;
    }

    /**
     * @param cabinName The cabinName
     */
    public void setCabinName(String cabinName) {
        this.cabinName = cabinName;
    }

    /**
     * @return The styleIdUpcabinConfirm
     */
    public String getStyleIdUpcabinConfirm() {
        return styleIdUpcabinConfirm;
    }

    /**
     * @param styleIdUpcabinConfirm The styleIdUpcabinConfirm
     */
    public void setStyleIdUpcabinConfirm(String styleIdUpcabinConfirm) {
        this.styleIdUpcabinConfirm = styleIdUpcabinConfirm;
    }

    /**
     * @return The isConfirmUpgradeDisplay
     */
    public Boolean getIsConfirmUpgradeDisplay() {
        return isConfirmUpgradeDisplay;
    }

    /**
     * @param isConfirmUpgradeDisplay The isConfirmUpgradeDisplay
     */
    public void setIsConfirmUpgradeDisplay(Boolean isConfirmUpgradeDisplay) {
        this.isConfirmUpgradeDisplay = isConfirmUpgradeDisplay;
    }

    /**
     * @return The styleIdUpcabinStandBy
     */
    public String getStyleIdUpcabinStandBy() {
        return styleIdUpcabinStandBy;
    }

    /**
     * @param styleIdUpcabinStandBy The styleIdUpcabinStandBy
     */
    public void setStyleIdUpcabinStandBy(String styleIdUpcabinStandBy) {
        this.styleIdUpcabinStandBy = styleIdUpcabinStandBy;
    }

    /**
     * @return The confirmUpgradePrice
     */
    public String getConfirmUpgradePrice() {
        return confirmUpgradePrice;
    }

    /**
     * @param confirmUpgradePrice The confirmUpgradePrice
     */
    public void setConfirmUpgradePrice(String confirmUpgradePrice) {
        this.confirmUpgradePrice = confirmUpgradePrice;
    }

    /**
     * @return The confirmedUpgradePrice
     */
    public String getConfirmedUpgradePrice() {
        return confirmedUpgradePrice;
    }

    /**
     * @param confirmedUpgradePrice The confirmedUpgradePrice
     */
    public void setConfirmedUpgradePrice(String confirmedUpgradePrice) {
        this.confirmedUpgradePrice = confirmedUpgradePrice;
    }

}

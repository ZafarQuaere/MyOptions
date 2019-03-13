
package com.optiontown.app.model.utosearchresult;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoostDetail {

    @SerializedName("priceUpcabinDetails")
    @Expose
    private List<PriceUpcabinDetail> priceUpcabinDetails = null;
    @SerializedName("Boost_Priority_Label")
    @Expose
    private String boostPriorityLabel;
    @SerializedName("LABL_Get_TGP_Direct_Confirm_Button_Label")
    @Expose
    private String lABLGetTGPDirectConfirmButtonLabel;
    @SerializedName("boostNameList")
    @Expose
    private List<BoostNameList> boostNameList = null;
    @SerializedName("UpcabinNameDetails")
    @Expose
    private List<UpcabinNameDetail> upcabinNameDetails = null;
    @SerializedName("Priority_Prices_Message")
    @Expose
    private String priorityPricesMessage;
    @SerializedName("Priority_Small_Message")
    @Expose
    private String prioritySmallMessage;
    @SerializedName("Boost_My_Priority")
    @Expose
    private String boostMyPriority;
    @SerializedName("LABL_Close_Label")
    @Expose
    private String lABLCloseLabel;

    public List<PriceUpcabinDetail> getPriceUpcabinDetails() {
        return priceUpcabinDetails;
    }

    public void setPriceUpcabinDetails(List<PriceUpcabinDetail> priceUpcabinDetails) {
        this.priceUpcabinDetails = priceUpcabinDetails;
    }

    public String getBoostPriorityLabel() {
        return boostPriorityLabel;
    }

    public void setBoostPriorityLabel(String boostPriorityLabel) {
        this.boostPriorityLabel = boostPriorityLabel;
    }

    public String getLABLGetTGPDirectConfirmButtonLabel() {
        return lABLGetTGPDirectConfirmButtonLabel;
    }

    public void setLABLGetTGPDirectConfirmButtonLabel(String lABLGetTGPDirectConfirmButtonLabel) {
        this.lABLGetTGPDirectConfirmButtonLabel = lABLGetTGPDirectConfirmButtonLabel;
    }

    public List<BoostNameList> getBoostNameList() {
        return boostNameList;
    }

    public void setBoostNameList(List<BoostNameList> boostNameList) {
        this.boostNameList = boostNameList;
    }

    public List<UpcabinNameDetail> getUpcabinNameDetails() {
        return upcabinNameDetails;
    }

    public void setUpcabinNameDetails(List<UpcabinNameDetail> upcabinNameDetails) {
        this.upcabinNameDetails = upcabinNameDetails;
    }

    public String getPriorityPricesMessage() {
        return priorityPricesMessage;
    }

    public void setPriorityPricesMessage(String priorityPricesMessage) {
        this.priorityPricesMessage = priorityPricesMessage;
    }

    public String getPrioritySmallMessage() {
        return prioritySmallMessage;
    }

    public void setPrioritySmallMessage(String prioritySmallMessage) {
        this.prioritySmallMessage = prioritySmallMessage;
    }

    public String getBoostMyPriority() {
        return boostMyPriority;
    }

    public void setBoostMyPriority(String boostMyPriority) {
        this.boostMyPriority = boostMyPriority;
    }

    public String getLABLCloseLabel() {
        return lABLCloseLabel;
    }

    public void setLABLCloseLabel(String lABLCloseLabel) {
        this.lABLCloseLabel = lABLCloseLabel;
    }

}

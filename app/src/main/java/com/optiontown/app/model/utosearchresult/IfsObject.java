
package com.optiontown.app.model.utosearchresult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IfsObject implements Serializable{

    @SerializedName("LABL_Master_UTO_Available_Link_Label")
    @Expose
    private String lABLMasterUTOAvailableLinkLabel;
    @SerializedName("Initial_Price_Label")
    @Expose
    private String initialPriceLabel;
    @SerializedName("ProuductLabel")
    @Expose
    private String prouductLabel;
    @SerializedName("LABL_Cabin_Get_TGP_Label")
    @Expose
    private String lABLCabinGetTGPLabel;
    @SerializedName("Display_Currency_Symbol")
    @Expose
    private String displayCurrencySymbol;
    @SerializedName("Journey_Type_Label")
    @Expose
    private String journeyTypeLabel;
    @SerializedName("LABL_UTO_initial_Price_Help_Label")
    @Expose
    private String lABLUTOInitialPriceHelpLabel;
    @SerializedName("product_Help_Mobile_Label")
    @Expose
    private String productHelpMobileLabel;
    @SerializedName("LABL_Depart_Label")
    @Expose
    private String lABLDepartLabel;
    @SerializedName("LABL_TSR_Shift_Price_Label")
    @Expose
    private String lABLTSRShiftPriceLabel;
    @SerializedName("UTO_Available_Heading_guide_Label")
    @Expose
    private String uTOAvailableHeadingGuideLabel;
    @SerializedName("legListObj")
    @Expose
    private List<LegListObj> legListObj = new ArrayList<LegListObj>();
    @SerializedName("LABL_Flight_Label")
    @Expose
    private String lABLFlightLabel;
    @SerializedName("LABL_Arrive_Label")
    @Expose
    private String lABLArriveLabel;


    @SerializedName("CanShowPriceComment")
    @Expose
    private boolean CanShowPriceComment;


    public boolean isCanShowPriceComment() {
        return CanShowPriceComment;
    }

    public void setCanShowPriceComment(boolean canShowPriceComment) {
        CanShowPriceComment = canShowPriceComment;
    }


    public String getLABLMasterUTOAvailableLinkLabel() {
        return lABLMasterUTOAvailableLinkLabel;
    }

    public void setLABLMasterUTOAvailableLinkLabel(String lABLMasterUTOAvailableLinkLabel) {
        this.lABLMasterUTOAvailableLinkLabel = lABLMasterUTOAvailableLinkLabel;
    }


    public String getInitialPriceLabel() {
        return initialPriceLabel;
    }

    public void setInitialPriceLabel(String initialPriceLabel) {
        this.initialPriceLabel = initialPriceLabel;
    }


    public String getProuductLabel() {
        return prouductLabel;
    }


    public void setProuductLabel(String prouductLabel) {
        this.prouductLabel = prouductLabel;
    }

    public String getLABLCabinGetTGPLabel() {
        return lABLCabinGetTGPLabel;
    }


    public void setLABLCabinGetTGPLabel(String lABLCabinGetTGPLabel) {
        this.lABLCabinGetTGPLabel = lABLCabinGetTGPLabel;
    }


    public String getDisplayCurrencySymbol() {
        return displayCurrencySymbol;
    }


    public void setDisplayCurrencySymbol(String displayCurrencySymbol) {
        this.displayCurrencySymbol = displayCurrencySymbol;
    }


    public String getJourneyTypeLabel() {
        return journeyTypeLabel;
    }

    public void setJourneyTypeLabel(String journeyTypeLabel) {
        this.journeyTypeLabel = journeyTypeLabel;
    }


    public String getLABLUTOInitialPriceHelpLabel() {
        return lABLUTOInitialPriceHelpLabel;
    }


    public void setLABLUTOInitialPriceHelpLabel(String lABLUTOInitialPriceHelpLabel) {
        this.lABLUTOInitialPriceHelpLabel = lABLUTOInitialPriceHelpLabel;
    }


    public String getProductHelpMobileLabel() {
        return productHelpMobileLabel;
    }


    public void setProductHelpMobileLabel(String productHelpMobileLabel) {
        this.productHelpMobileLabel = productHelpMobileLabel;
    }


    public String getLABLDepartLabel() {
        return lABLDepartLabel;
    }


    public void setLABLDepartLabel(String lABLDepartLabel) {
        this.lABLDepartLabel = lABLDepartLabel;
    }


    public String getLABLTSRShiftPriceLabel() {
        return lABLTSRShiftPriceLabel;
    }


    public void setLABLTSRShiftPriceLabel(String lABLTSRShiftPriceLabel) {
        this.lABLTSRShiftPriceLabel = lABLTSRShiftPriceLabel;
    }


    public String getUTOAvailableHeadingGuideLabel() {
        return uTOAvailableHeadingGuideLabel;
    }


    public void setUTOAvailableHeadingGuideLabel(String uTOAvailableHeadingGuideLabel) {
        this.uTOAvailableHeadingGuideLabel = uTOAvailableHeadingGuideLabel;
    }


    public List<LegListObj> getLegListObj() {
        return legListObj;
    }

    public void setLegListObj(List<LegListObj> legListObj) {
        this.legListObj = legListObj;
    }


    public String getLABLFlightLabel() {
        return lABLFlightLabel;
    }


    public void setLABLFlightLabel(String lABLFlightLabel) {
        this.lABLFlightLabel = lABLFlightLabel;
    }


    public String getLABLArriveLabel() {
        return lABLArriveLabel;
    }


    public void setLABLArriveLabel(String lABLArriveLabel) {
        this.lABLArriveLabel = lABLArriveLabel;
    }

}

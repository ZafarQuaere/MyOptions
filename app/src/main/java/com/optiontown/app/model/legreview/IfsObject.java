
package com.optiontown.app.model.legreview;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class IfsObject {

    @SerializedName("LABL_Master_UTO_Available_Link_Label")
    @Expose
    private String lABLMasterUTOAvailableLinkLabel;
    @SerializedName("LABL_UTO_initial_Price_Help_Label")
    @Expose
    private String lABLUTOInitialPriceHelpLabel;
    @SerializedName("Initial_Price_Label")
    @Expose
    private String initialPriceLabel;
    @SerializedName("LABL_Cabin_Get_TGP_Label")
    @Expose
    private String lABLCabinGetTGPLabel;
    @SerializedName("LABL_Depart_Label")
    @Expose
    private String lABLDepartLabel;
    @SerializedName("Display_Currency_Symbol")
    @Expose
    private String displayCurrencySymbol;
    @SerializedName("LABL_TSR_Shift_Price_Label")
    @Expose
    private String lABLTSRShiftPriceLabel;
    @SerializedName("UTO_Available_Heading_guide_Label")
    @Expose
    private String uTOAvailableHeadingGuideLabel;
    @SerializedName("legListObj")
    @Expose
    private List<LegListObj> legListObj = new ArrayList<LegListObj>();
    @SerializedName("Journey_Type_Label")
    @Expose
    private String journeyTypeLabel;
    @SerializedName("LABL_Arrive_Label")
    @Expose
    private String lABLArriveLabel;
    @SerializedName("LABL_Flight_Label")
    @Expose
    private String lABLFlightLabel;

    /**
     * 
     * @return
     *     The lABLMasterUTOAvailableLinkLabel
     */
    public String getLABLMasterUTOAvailableLinkLabel() {
        return lABLMasterUTOAvailableLinkLabel;
    }

    /**
     * 
     * @param lABLMasterUTOAvailableLinkLabel
     *     The LABL_Master_UTO_Available_Link_Label
     */
    public void setLABLMasterUTOAvailableLinkLabel(String lABLMasterUTOAvailableLinkLabel) {
        this.lABLMasterUTOAvailableLinkLabel = lABLMasterUTOAvailableLinkLabel;
    }

    /**
     * 
     * @return
     *     The lABLUTOInitialPriceHelpLabel
     */
    public String getLABLUTOInitialPriceHelpLabel() {
        return lABLUTOInitialPriceHelpLabel;
    }

    /**
     * 
     * @param lABLUTOInitialPriceHelpLabel
     *     The LABL_UTO_initial_Price_Help_Label
     */
    public void setLABLUTOInitialPriceHelpLabel(String lABLUTOInitialPriceHelpLabel) {
        this.lABLUTOInitialPriceHelpLabel = lABLUTOInitialPriceHelpLabel;
    }

    /**
     * 
     * @return
     *     The initialPriceLabel
     */
    public String getInitialPriceLabel() {
        return initialPriceLabel;
    }

    /**
     * 
     * @param initialPriceLabel
     *     The Initial_Price_Label
     */
    public void setInitialPriceLabel(String initialPriceLabel) {
        this.initialPriceLabel = initialPriceLabel;
    }

    /**
     * 
     * @return
     *     The lABLCabinGetTGPLabel
     */
    public String getLABLCabinGetTGPLabel() {
        return lABLCabinGetTGPLabel;
    }

    /**
     * 
     * @param lABLCabinGetTGPLabel
     *     The LABL_Cabin_Get_TGP_Label
     */
    public void setLABLCabinGetTGPLabel(String lABLCabinGetTGPLabel) {
        this.lABLCabinGetTGPLabel = lABLCabinGetTGPLabel;
    }

    /**
     * 
     * @return
     *     The lABLDepartLabel
     */
    public String getLABLDepartLabel() {
        return lABLDepartLabel;
    }

    /**
     * 
     * @param lABLDepartLabel
     *     The LABL_Depart_Label
     */
    public void setLABLDepartLabel(String lABLDepartLabel) {
        this.lABLDepartLabel = lABLDepartLabel;
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
     *     The Display_Currency_Symbol
     */
    public void setDisplayCurrencySymbol(String displayCurrencySymbol) {
        this.displayCurrencySymbol = displayCurrencySymbol;
    }

    /**
     * 
     * @return
     *     The lABLTSRShiftPriceLabel
     */
    public String getLABLTSRShiftPriceLabel() {
        return lABLTSRShiftPriceLabel;
    }

    /**
     * 
     * @param lABLTSRShiftPriceLabel
     *     The LABL_TSR_Shift_Price_Label
     */
    public void setLABLTSRShiftPriceLabel(String lABLTSRShiftPriceLabel) {
        this.lABLTSRShiftPriceLabel = lABLTSRShiftPriceLabel;
    }

    /**
     * 
     * @return
     *     The uTOAvailableHeadingGuideLabel
     */
    public String getUTOAvailableHeadingGuideLabel() {
        return uTOAvailableHeadingGuideLabel;
    }

    /**
     * 
     * @param uTOAvailableHeadingGuideLabel
     *     The UTO_Available_Heading_guide_Label
     */
    public void setUTOAvailableHeadingGuideLabel(String uTOAvailableHeadingGuideLabel) {
        this.uTOAvailableHeadingGuideLabel = uTOAvailableHeadingGuideLabel;
    }

    /**
     * 
     * @return
     *     The legListObj
     */
    public List<LegListObj> getLegListObj() {
        return legListObj;
    }

    /**
     * 
     * @param legListObj
     *     The legListObj
     */
    public void setLegListObj(List<LegListObj> legListObj) {
        this.legListObj = legListObj;
    }

    /**
     * 
     * @return
     *     The journeyTypeLabel
     */
    public String getJourneyTypeLabel() {
        return journeyTypeLabel;
    }

    /**
     * 
     * @param journeyTypeLabel
     *     The Journey_Type_Label
     */
    public void setJourneyTypeLabel(String journeyTypeLabel) {
        this.journeyTypeLabel = journeyTypeLabel;
    }

    /**
     * 
     * @return
     *     The lABLArriveLabel
     */
    public String getLABLArriveLabel() {
        return lABLArriveLabel;
    }

    /**
     * 
     * @param lABLArriveLabel
     *     The LABL_Arrive_Label
     */
    public void setLABLArriveLabel(String lABLArriveLabel) {
        this.lABLArriveLabel = lABLArriveLabel;
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

}

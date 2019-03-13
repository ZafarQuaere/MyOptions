
package com.optiontown.app.model.legreview;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LegListObj {

    @SerializedName("Operating_Airline_Code")
    @Expose
    private String operatingAirlineCode;
    @SerializedName("Depart_Time_Formatted")
    @Expose
    private String departTimeFormatted;
    @SerializedName("numberOfLegs")
    @Expose
    private String numberOfLegs;
    @SerializedName("creditTitle")
    @Expose
    private String creditTitle;
    @SerializedName("beforePndAfterPndFlag")
    @Expose
    private String beforePndAfterPndFlag;
    @SerializedName("numberOfSegments")
    @Expose
    private String numberOfSegments;
    @SerializedName("TGPTotalSaveSummaryRegular")
    @Expose
    private String tGPTotalSaveSummaryRegular;
    @SerializedName("utoSelectedImage")
    @Expose
    private String utoSelectedImage;
    @SerializedName("saveAmount")
    @Expose
    private String saveAmount;
    @SerializedName("isPndDoneOnLeg")
    @Expose
    private Boolean isPndDoneOnLeg;
    @SerializedName("Up_Cabin_image")
    @Expose
    private String upCabinImage;
    @SerializedName("LABL_Master_UTO_PND_OFS_Confirm_Label")
    @Expose
    private String lABLMasterUTOPNDOFSConfirmLabel;
    @SerializedName("LABL_UTO_Not_Available_Label")
    @Expose
    private String lABLUTONotAvailableLabel;
    @SerializedName("upCabin1")
    @Expose
    private String upCabin1;
    @SerializedName("UpGradedImage")
    @Expose
    private String upGradedImage;
    @SerializedName("LABL_Master_UTO_Available_Not_Selected_Label")
    @Expose
    private String lABLMasterUTOAvailableNotSelectedLabel;
    @SerializedName("upCabin2")
    @Expose
    private String upCabin2;
    @SerializedName("discountLabel")
    @Expose
    private String discountLabel;
    @SerializedName("styleId")
    @Expose
    private String styleId;
    @SerializedName("LABL_Master_UTO_Before_PND_Confirm_Cabin_Help_Label")
    @Expose
    private String lABLMasterUTOBeforePNDConfirmCabinHelpLabel;
    @SerializedName("Arrive_Time_Formatted")
    @Expose
    private String arriveTimeFormatted;
    @SerializedName("Depart_Airport_Code")
    @Expose
    private String departAirportCode;
    @SerializedName("UtoAvailablePassNotApp")
    @Expose
    private String utoAvailablePassNotApp;
    @SerializedName("Upgrade_Done_Icon")
    @Expose
    private String upgradeDoneIcon;
    @SerializedName("Cabin_Name")
    @Expose
    private String cabinName;
    @SerializedName("multiLegPriceDiscountHelp")
    @Expose
    private String multiLegPriceDiscountHelp;
    @SerializedName("Arrive_Airport_Name")
    @Expose
    private String arriveAirportName;
    @SerializedName("utoNotAvailable")
    @Expose
    private String utoNotAvailable;
    @SerializedName("standByOption")
    @Expose
    private String standByOption;
    @SerializedName("Operating_Airline_Logo")
    @Expose
    private String operatingAirlineLogo;
    @SerializedName("UpCabinName")
    @Expose
    private String upCabinName;
    @SerializedName("Depart_Time")
    @Expose
    private String departTime;
    @SerializedName("master_UTO_PND_OFS_Label")
    @Expose
    private String masterUTOPNDOFSLabel;
    @SerializedName("addCabinToUto")
    @Expose
    private String addCabinToUto;
    @SerializedName("cabinDetailList")
    @Expose
    private List<Object> cabinDetailList = new ArrayList<Object>();
    @SerializedName("Arrive_Airport_Code")
    @Expose
    private String arriveAirportCode;
    @SerializedName("High_Upgrade_chance_Label")
    @Expose
    private String highUpgradeChanceLabel;
    @SerializedName("Depart_Airport_Name")
    @Expose
    private String departAirportName;
    @SerializedName("Operating_Airline_Carrier_Flight_Number")
    @Expose
    private String operatingAirlineCarrierFlightNumber;
    @SerializedName("LABL_Master_UTO_Selected_Label")
    @Expose
    private String lABLMasterUTOSelectedLabel;
    @SerializedName("Days")
    @Expose
    private Integer days;
    @SerializedName("confirmUpgradeSelectLabel")
    @Expose
    private String confirmUpgradeSelectLabel;
    @SerializedName("totalSaveRegularLabel")
    @Expose
    private String totalSaveRegularLabel;
    @SerializedName("beforePndCabinLabel")
    @Expose
    private String beforePndCabinLabel;
    @SerializedName("Arrive_Time")
    @Expose
    private String arriveTime;
    @SerializedName("LABL_Master_Confirm_UTO_PND_IFS_Cabin_Label")
    @Expose
    private String lABLMasterConfirmUTOPNDIFSCabinLabel;
    @SerializedName("numberUpCabins")
    @Expose
    private String numberUpCabins;
    @SerializedName("utoNotAvailavble")
    @Expose
    private String utoNotAvailavble;
    @SerializedName("Is_DirectFlight_Selection_Check_Success")
    @Expose
    private Boolean isDirectFlightSelectionCheckSuccess;
    @SerializedName("MTP_Save_Label")
    @Expose
    private String mTPSaveLabel;
    @SerializedName("Option_Price")
    @Expose
    private String optionPrice;
    @SerializedName("UpGraded_SelectedImage")
    @Expose
    private String upGradedSelectedImage;
    @SerializedName("creditRequiredLabel")
    @Expose
    private String creditRequiredLabel;
    @SerializedName("shiftPrice")
    @Expose
    private Integer shiftPrice;
    @SerializedName("LABL_Day_Label")
    @Expose
    private String lABLDayLabel;
    @SerializedName("pndIfsLabel")
    @Expose
    private String pndIfsLabel;
    @SerializedName("isPurchasedLeg")
    @Expose
    private Boolean isPurchasedLeg;
    @SerializedName("displayCurrencySymbol")
    @Expose
    private String displayCurrencySymbol;

    /**
     * 
     * @return
     *     The operatingAirlineCode
     */
    public String getOperatingAirlineCode() {
        return operatingAirlineCode;
    }

    /**
     * 
     * @param operatingAirlineCode
     *     The Operating_Airline_Code
     */
    public void setOperatingAirlineCode(String operatingAirlineCode) {
        this.operatingAirlineCode = operatingAirlineCode;
    }

    /**
     * 
     * @return
     *     The departTimeFormatted
     */
    public String getDepartTimeFormatted() {
        return departTimeFormatted;
    }

    /**
     * 
     * @param departTimeFormatted
     *     The Depart_Time_Formatted
     */
    public void setDepartTimeFormatted(String departTimeFormatted) {
        this.departTimeFormatted = departTimeFormatted;
    }

    /**
     * 
     * @return
     *     The numberOfLegs
     */
    public String getNumberOfLegs() {
        return numberOfLegs;
    }

    /**
     * 
     * @param numberOfLegs
     *     The numberOfLegs
     */
    public void setNumberOfLegs(String numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    /**
     * 
     * @return
     *     The creditTitle
     */
    public String getCreditTitle() {
        return creditTitle;
    }

    /**
     * 
     * @param creditTitle
     *     The creditTitle
     */
    public void setCreditTitle(String creditTitle) {
        this.creditTitle = creditTitle;
    }

    /**
     * 
     * @return
     *     The beforePndAfterPndFlag
     */
    public String getBeforePndAfterPndFlag() {
        return beforePndAfterPndFlag;
    }

    /**
     * 
     * @param beforePndAfterPndFlag
     *     The beforePndAfterPndFlag
     */
    public void setBeforePndAfterPndFlag(String beforePndAfterPndFlag) {
        this.beforePndAfterPndFlag = beforePndAfterPndFlag;
    }

    /**
     * 
     * @return
     *     The numberOfSegments
     */
    public String getNumberOfSegments() {
        return numberOfSegments;
    }

    /**
     * 
     * @param numberOfSegments
     *     The numberOfSegments
     */
    public void setNumberOfSegments(String numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
    }

    /**
     * 
     * @return
     *     The tGPTotalSaveSummaryRegular
     */
    public String getTGPTotalSaveSummaryRegular() {
        return tGPTotalSaveSummaryRegular;
    }

    /**
     * 
     * @param tGPTotalSaveSummaryRegular
     *     The TGPTotalSaveSummaryRegular
     */
    public void setTGPTotalSaveSummaryRegular(String tGPTotalSaveSummaryRegular) {
        this.tGPTotalSaveSummaryRegular = tGPTotalSaveSummaryRegular;
    }

    /**
     * 
     * @return
     *     The utoSelectedImage
     */
    public String getUtoSelectedImage() {
        return utoSelectedImage;
    }

    /**
     * 
     * @param utoSelectedImage
     *     The utoSelectedImage
     */
    public void setUtoSelectedImage(String utoSelectedImage) {
        this.utoSelectedImage = utoSelectedImage;
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
     *     The isPndDoneOnLeg
     */
    public Boolean getIsPndDoneOnLeg() {
        return isPndDoneOnLeg;
    }

    /**
     * 
     * @param isPndDoneOnLeg
     *     The isPndDoneOnLeg
     */
    public void setIsPndDoneOnLeg(Boolean isPndDoneOnLeg) {
        this.isPndDoneOnLeg = isPndDoneOnLeg;
    }

    /**
     * 
     * @return
     *     The upCabinImage
     */
    public String getUpCabinImage() {
        return upCabinImage;
    }

    /**
     * 
     * @param upCabinImage
     *     The Up_Cabin_image
     */
    public void setUpCabinImage(String upCabinImage) {
        this.upCabinImage = upCabinImage;
    }

    /**
     * 
     * @return
     *     The lABLMasterUTOPNDOFSConfirmLabel
     */
    public String getLABLMasterUTOPNDOFSConfirmLabel() {
        return lABLMasterUTOPNDOFSConfirmLabel;
    }

    /**
     * 
     * @param lABLMasterUTOPNDOFSConfirmLabel
     *     The LABL_Master_UTO_PND_OFS_Confirm_Label
     */
    public void setLABLMasterUTOPNDOFSConfirmLabel(String lABLMasterUTOPNDOFSConfirmLabel) {
        this.lABLMasterUTOPNDOFSConfirmLabel = lABLMasterUTOPNDOFSConfirmLabel;
    }

    /**
     * 
     * @return
     *     The lABLUTONotAvailableLabel
     */
    public String getLABLUTONotAvailableLabel() {
        return lABLUTONotAvailableLabel;
    }

    /**
     * 
     * @param lABLUTONotAvailableLabel
     *     The LABL_UTO_Not_Available_Label
     */
    public void setLABLUTONotAvailableLabel(String lABLUTONotAvailableLabel) {
        this.lABLUTONotAvailableLabel = lABLUTONotAvailableLabel;
    }

    /**
     * 
     * @return
     *     The upCabin1
     */
    public String getUpCabin1() {
        return upCabin1;
    }

    /**
     * 
     * @param upCabin1
     *     The upCabin1
     */
    public void setUpCabin1(String upCabin1) {
        this.upCabin1 = upCabin1;
    }

    /**
     * 
     * @return
     *     The upGradedImage
     */
    public String getUpGradedImage() {
        return upGradedImage;
    }

    /**
     * 
     * @param upGradedImage
     *     The UpGradedImage
     */
    public void setUpGradedImage(String upGradedImage) {
        this.upGradedImage = upGradedImage;
    }

    /**
     * 
     * @return
     *     The lABLMasterUTOAvailableNotSelectedLabel
     */
    public String getLABLMasterUTOAvailableNotSelectedLabel() {
        return lABLMasterUTOAvailableNotSelectedLabel;
    }

    /**
     * 
     * @param lABLMasterUTOAvailableNotSelectedLabel
     *     The LABL_Master_UTO_Available_Not_Selected_Label
     */
    public void setLABLMasterUTOAvailableNotSelectedLabel(String lABLMasterUTOAvailableNotSelectedLabel) {
        this.lABLMasterUTOAvailableNotSelectedLabel = lABLMasterUTOAvailableNotSelectedLabel;
    }

    /**
     * 
     * @return
     *     The upCabin2
     */
    public String getUpCabin2() {
        return upCabin2;
    }

    /**
     * 
     * @param upCabin2
     *     The upCabin2
     */
    public void setUpCabin2(String upCabin2) {
        this.upCabin2 = upCabin2;
    }

    /**
     * 
     * @return
     *     The discountLabel
     */
    public String getDiscountLabel() {
        return discountLabel;
    }

    /**
     * 
     * @param discountLabel
     *     The discountLabel
     */
    public void setDiscountLabel(String discountLabel) {
        this.discountLabel = discountLabel;
    }

    /**
     * 
     * @return
     *     The styleId
     */
    public String getStyleId() {
        return styleId;
    }

    /**
     * 
     * @param styleId
     *     The styleId
     */
    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    /**
     * 
     * @return
     *     The lABLMasterUTOBeforePNDConfirmCabinHelpLabel
     */
    public String getLABLMasterUTOBeforePNDConfirmCabinHelpLabel() {
        return lABLMasterUTOBeforePNDConfirmCabinHelpLabel;
    }

    /**
     * 
     * @param lABLMasterUTOBeforePNDConfirmCabinHelpLabel
     *     The LABL_Master_UTO_Before_PND_Confirm_Cabin_Help_Label
     */
    public void setLABLMasterUTOBeforePNDConfirmCabinHelpLabel(String lABLMasterUTOBeforePNDConfirmCabinHelpLabel) {
        this.lABLMasterUTOBeforePNDConfirmCabinHelpLabel = lABLMasterUTOBeforePNDConfirmCabinHelpLabel;
    }

    /**
     * 
     * @return
     *     The arriveTimeFormatted
     */
    public String getArriveTimeFormatted() {
        return arriveTimeFormatted;
    }

    /**
     * 
     * @param arriveTimeFormatted
     *     The Arrive_Time_Formatted
     */
    public void setArriveTimeFormatted(String arriveTimeFormatted) {
        this.arriveTimeFormatted = arriveTimeFormatted;
    }

    /**
     * 
     * @return
     *     The departAirportCode
     */
    public String getDepartAirportCode() {
        return departAirportCode;
    }

    /**
     * 
     * @param departAirportCode
     *     The Depart_Airport_Code
     */
    public void setDepartAirportCode(String departAirportCode) {
        this.departAirportCode = departAirportCode;
    }

    /**
     * 
     * @return
     *     The utoAvailablePassNotApp
     */
    public String getUtoAvailablePassNotApp() {
        return utoAvailablePassNotApp;
    }

    /**
     * 
     * @param utoAvailablePassNotApp
     *     The UtoAvailablePassNotApp
     */
    public void setUtoAvailablePassNotApp(String utoAvailablePassNotApp) {
        this.utoAvailablePassNotApp = utoAvailablePassNotApp;
    }

    /**
     * 
     * @return
     *     The upgradeDoneIcon
     */
    public String getUpgradeDoneIcon() {
        return upgradeDoneIcon;
    }

    /**
     * 
     * @param upgradeDoneIcon
     *     The Upgrade_Done_Icon
     */
    public void setUpgradeDoneIcon(String upgradeDoneIcon) {
        this.upgradeDoneIcon = upgradeDoneIcon;
    }

    /**
     * 
     * @return
     *     The cabinName
     */
    public String getCabinName() {
        return cabinName;
    }

    /**
     * 
     * @param cabinName
     *     The Cabin_Name
     */
    public void setCabinName(String cabinName) {
        this.cabinName = cabinName;
    }

    /**
     * 
     * @return
     *     The multiLegPriceDiscountHelp
     */
    public String getMultiLegPriceDiscountHelp() {
        return multiLegPriceDiscountHelp;
    }

    /**
     * 
     * @param multiLegPriceDiscountHelp
     *     The multiLegPriceDiscountHelp
     */
    public void setMultiLegPriceDiscountHelp(String multiLegPriceDiscountHelp) {
        this.multiLegPriceDiscountHelp = multiLegPriceDiscountHelp;
    }

    /**
     * 
     * @return
     *     The arriveAirportName
     */
    public String getArriveAirportName() {
        return arriveAirportName;
    }

    /**
     * 
     * @param arriveAirportName
     *     The Arrive_Airport_Name
     */
    public void setArriveAirportName(String arriveAirportName) {
        this.arriveAirportName = arriveAirportName;
    }

    /**
     * 
     * @return
     *     The utoNotAvailable
     */
    public String getUtoNotAvailable() {
        return utoNotAvailable;
    }

    /**
     * 
     * @param utoNotAvailable
     *     The utoNotAvailable
     */
    public void setUtoNotAvailable(String utoNotAvailable) {
        this.utoNotAvailable = utoNotAvailable;
    }

    /**
     * 
     * @return
     *     The standByOption
     */
    public String getStandByOption() {
        return standByOption;
    }

    /**
     * 
     * @param standByOption
     *     The standByOption
     */
    public void setStandByOption(String standByOption) {
        this.standByOption = standByOption;
    }

    /**
     * 
     * @return
     *     The operatingAirlineLogo
     */
    public String getOperatingAirlineLogo() {
        return operatingAirlineLogo;
    }

    /**
     * 
     * @param operatingAirlineLogo
     *     The Operating_Airline_Logo
     */
    public void setOperatingAirlineLogo(String operatingAirlineLogo) {
        this.operatingAirlineLogo = operatingAirlineLogo;
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
     *     The departTime
     */
    public String getDepartTime() {
        return departTime;
    }

    /**
     * 
     * @param departTime
     *     The Depart_Time
     */
    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    /**
     * 
     * @return
     *     The masterUTOPNDOFSLabel
     */
    public String getMasterUTOPNDOFSLabel() {
        return masterUTOPNDOFSLabel;
    }

    /**
     * 
     * @param masterUTOPNDOFSLabel
     *     The master_UTO_PND_OFS_Label
     */
    public void setMasterUTOPNDOFSLabel(String masterUTOPNDOFSLabel) {
        this.masterUTOPNDOFSLabel = masterUTOPNDOFSLabel;
    }

    /**
     * 
     * @return
     *     The addCabinToUto
     */
    public String getAddCabinToUto() {
        return addCabinToUto;
    }

    /**
     * 
     * @param addCabinToUto
     *     The addCabinToUto
     */
    public void setAddCabinToUto(String addCabinToUto) {
        this.addCabinToUto = addCabinToUto;
    }

    /**
     * 
     * @return
     *     The cabinDetailList
     */
    public List<Object> getCabinDetailList() {
        return cabinDetailList;
    }

    /**
     * 
     * @param cabinDetailList
     *     The cabinDetailList
     */
    public void setCabinDetailList(List<Object> cabinDetailList) {
        this.cabinDetailList = cabinDetailList;
    }

    /**
     * 
     * @return
     *     The arriveAirportCode
     */
    public String getArriveAirportCode() {
        return arriveAirportCode;
    }

    /**
     * 
     * @param arriveAirportCode
     *     The Arrive_Airport_Code
     */
    public void setArriveAirportCode(String arriveAirportCode) {
        this.arriveAirportCode = arriveAirportCode;
    }

    /**
     * 
     * @return
     *     The highUpgradeChanceLabel
     */
    public String getHighUpgradeChanceLabel() {
        return highUpgradeChanceLabel;
    }

    /**
     * 
     * @param highUpgradeChanceLabel
     *     The High_Upgrade_chance_Label
     */
    public void setHighUpgradeChanceLabel(String highUpgradeChanceLabel) {
        this.highUpgradeChanceLabel = highUpgradeChanceLabel;
    }

    /**
     * 
     * @return
     *     The departAirportName
     */
    public String getDepartAirportName() {
        return departAirportName;
    }

    /**
     * 
     * @param departAirportName
     *     The Depart_Airport_Name
     */
    public void setDepartAirportName(String departAirportName) {
        this.departAirportName = departAirportName;
    }

    /**
     * 
     * @return
     *     The operatingAirlineCarrierFlightNumber
     */
    public String getOperatingAirlineCarrierFlightNumber() {
        return operatingAirlineCarrierFlightNumber;
    }

    /**
     * 
     * @param operatingAirlineCarrierFlightNumber
     *     The Operating_Airline_Carrier_Flight_Number
     */
    public void setOperatingAirlineCarrierFlightNumber(String operatingAirlineCarrierFlightNumber) {
        this.operatingAirlineCarrierFlightNumber = operatingAirlineCarrierFlightNumber;
    }

    /**
     * 
     * @return
     *     The lABLMasterUTOSelectedLabel
     */
    public String getLABLMasterUTOSelectedLabel() {
        return lABLMasterUTOSelectedLabel;
    }

    /**
     * 
     * @param lABLMasterUTOSelectedLabel
     *     The LABL_Master_UTO_Selected_Label
     */
    public void setLABLMasterUTOSelectedLabel(String lABLMasterUTOSelectedLabel) {
        this.lABLMasterUTOSelectedLabel = lABLMasterUTOSelectedLabel;
    }

    /**
     * 
     * @return
     *     The days
     */
    public Integer getDays() {
        return days;
    }

    /**
     * 
     * @param days
     *     The Days
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * 
     * @return
     *     The confirmUpgradeSelectLabel
     */
    public String getConfirmUpgradeSelectLabel() {
        return confirmUpgradeSelectLabel;
    }

    /**
     * 
     * @param confirmUpgradeSelectLabel
     *     The confirmUpgradeSelectLabel
     */
    public void setConfirmUpgradeSelectLabel(String confirmUpgradeSelectLabel) {
        this.confirmUpgradeSelectLabel = confirmUpgradeSelectLabel;
    }

    /**
     * 
     * @return
     *     The totalSaveRegularLabel
     */
    public String getTotalSaveRegularLabel() {
        return totalSaveRegularLabel;
    }

    /**
     * 
     * @param totalSaveRegularLabel
     *     The totalSaveRegularLabel
     */
    public void setTotalSaveRegularLabel(String totalSaveRegularLabel) {
        this.totalSaveRegularLabel = totalSaveRegularLabel;
    }

    /**
     * 
     * @return
     *     The beforePndCabinLabel
     */
    public String getBeforePndCabinLabel() {
        return beforePndCabinLabel;
    }

    /**
     * 
     * @param beforePndCabinLabel
     *     The beforePndCabinLabel
     */
    public void setBeforePndCabinLabel(String beforePndCabinLabel) {
        this.beforePndCabinLabel = beforePndCabinLabel;
    }

    /**
     * 
     * @return
     *     The arriveTime
     */
    public String getArriveTime() {
        return arriveTime;
    }

    /**
     * 
     * @param arriveTime
     *     The Arrive_Time
     */
    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * 
     * @return
     *     The lABLMasterConfirmUTOPNDIFSCabinLabel
     */
    public String getLABLMasterConfirmUTOPNDIFSCabinLabel() {
        return lABLMasterConfirmUTOPNDIFSCabinLabel;
    }

    /**
     * 
     * @param lABLMasterConfirmUTOPNDIFSCabinLabel
     *     The LABL_Master_Confirm_UTO_PND_IFS_Cabin_Label
     */
    public void setLABLMasterConfirmUTOPNDIFSCabinLabel(String lABLMasterConfirmUTOPNDIFSCabinLabel) {
        this.lABLMasterConfirmUTOPNDIFSCabinLabel = lABLMasterConfirmUTOPNDIFSCabinLabel;
    }

    /**
     * 
     * @return
     *     The numberUpCabins
     */
    public String getNumberUpCabins() {
        return numberUpCabins;
    }

    /**
     * 
     * @param numberUpCabins
     *     The numberUpCabins
     */
    public void setNumberUpCabins(String numberUpCabins) {
        this.numberUpCabins = numberUpCabins;
    }

    /**
     * 
     * @return
     *     The utoNotAvailavble
     */
    public String getUtoNotAvailavble() {
        return utoNotAvailavble;
    }

    /**
     * 
     * @param utoNotAvailavble
     *     The utoNotAvailavble
     */
    public void setUtoNotAvailavble(String utoNotAvailavble) {
        this.utoNotAvailavble = utoNotAvailavble;
    }

    /**
     * 
     * @return
     *     The isDirectFlightSelectionCheckSuccess
     */
    public Boolean getIsDirectFlightSelectionCheckSuccess() {
        return isDirectFlightSelectionCheckSuccess;
    }

    /**
     * 
     * @param isDirectFlightSelectionCheckSuccess
     *     The Is_DirectFlight_Selection_Check_Success
     */
    public void setIsDirectFlightSelectionCheckSuccess(Boolean isDirectFlightSelectionCheckSuccess) {
        this.isDirectFlightSelectionCheckSuccess = isDirectFlightSelectionCheckSuccess;
    }

    /**
     * 
     * @return
     *     The mTPSaveLabel
     */
    public String getMTPSaveLabel() {
        return mTPSaveLabel;
    }

    /**
     * 
     * @param mTPSaveLabel
     *     The MTP_Save_Label
     */
    public void setMTPSaveLabel(String mTPSaveLabel) {
        this.mTPSaveLabel = mTPSaveLabel;
    }

    /**
     * 
     * @return
     *     The optionPrice
     */
    public String getOptionPrice() {
        return optionPrice;
    }

    /**
     * 
     * @param optionPrice
     *     The Option_Price
     */
    public void setOptionPrice(String optionPrice) {
        this.optionPrice = optionPrice;
    }

    /**
     * 
     * @return
     *     The upGradedSelectedImage
     */
    public String getUpGradedSelectedImage() {
        return upGradedSelectedImage;
    }

    /**
     * 
     * @param upGradedSelectedImage
     *     The UpGraded_SelectedImage
     */
    public void setUpGradedSelectedImage(String upGradedSelectedImage) {
        this.upGradedSelectedImage = upGradedSelectedImage;
    }

    /**
     * 
     * @return
     *     The creditRequiredLabel
     */
    public String getCreditRequiredLabel() {
        return creditRequiredLabel;
    }

    /**
     * 
     * @param creditRequiredLabel
     *     The creditRequiredLabel
     */
    public void setCreditRequiredLabel(String creditRequiredLabel) {
        this.creditRequiredLabel = creditRequiredLabel;
    }

    /**
     * 
     * @return
     *     The shiftPrice
     */
    public Integer getShiftPrice() {
        return shiftPrice;
    }

    /**
     * 
     * @param shiftPrice
     *     The shiftPrice
     */
    public void setShiftPrice(Integer shiftPrice) {
        this.shiftPrice = shiftPrice;
    }

    /**
     * 
     * @return
     *     The lABLDayLabel
     */
    public String getLABLDayLabel() {
        return lABLDayLabel;
    }

    /**
     * 
     * @param lABLDayLabel
     *     The LABL_Day_Label
     */
    public void setLABLDayLabel(String lABLDayLabel) {
        this.lABLDayLabel = lABLDayLabel;
    }

    /**
     * 
     * @return
     *     The pndIfsLabel
     */
    public String getPndIfsLabel() {
        return pndIfsLabel;
    }

    /**
     * 
     * @param pndIfsLabel
     *     The pndIfsLabel
     */
    public void setPndIfsLabel(String pndIfsLabel) {
        this.pndIfsLabel = pndIfsLabel;
    }

    /**
     * 
     * @return
     *     The isPurchasedLeg
     */
    public Boolean getIsPurchasedLeg() {
        return isPurchasedLeg;
    }

    /**
     * 
     * @param isPurchasedLeg
     *     The isPurchasedLeg
     */
    public void setIsPurchasedLeg(Boolean isPurchasedLeg) {
        this.isPurchasedLeg = isPurchasedLeg;
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

}


package com.optiontown.app.model.utosearchresult;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtosearchresultHome {

    @SerializedName("tgpFgSortDesc")
    @Expose
    private String tgpFgSortDesc;
    @SerializedName("passCabinNames")
    @Expose
    private String passCabinNames;
    @SerializedName("creditHelpMessage")
    @Expose
    private String creditHelpMessage;

    @SerializedName("AirlineSpecificColorCode")
    @Expose
    private AirlineSpecificColorCode airlineSpecificColorCode;
    @SerializedName("IfsObject")
    @Expose
    private List<IfsObject> ifsObject = new ArrayList<IfsObject>();
    @SerializedName("AutoAccount")
    @Expose
    private AutoAccount autoAccount;
    @SerializedName("isShowContinueButton")
    @Expose
    private Boolean isShowContinueButton;
    @SerializedName("is_pass_flow")
    @Expose
    private Boolean isPassFlow;
    @SerializedName("errorExists")
    @Expose
    private Boolean errorExists;
    @SerializedName("Proceed_To_Payment_Now_Label")
    @Expose
    private String proceedToPaymentNowLabel;

    public String getProceedToPaymentNowLabel() {
        return proceedToPaymentNowLabel;
    }

    public void setProceedToPaymentNowLabel(String proceedToPaymentNowLabel) {
        this.proceedToPaymentNowLabel = proceedToPaymentNowLabel;
    }



    public String getTgpFgSortDesc() {
        return tgpFgSortDesc;
    }

    public void setTgpFgSortDesc(String tgpFgSortDesc) {
        this.tgpFgSortDesc = tgpFgSortDesc;
    }

    public String getPassCabinNames() {
        return passCabinNames;
    }

    public void setPassCabinNames(String passCabinNames) {
        this.passCabinNames = passCabinNames;
    }

    public String getCreditHelpMessage() {
        return creditHelpMessage;
    }

    public void setCreditHelpMessage(String creditHelpMessage) {
        this.creditHelpMessage = creditHelpMessage;
    }

    public AirlineSpecificColorCode getAirlineSpecificColorCode() {
        return airlineSpecificColorCode;
    }

    /**
     * 
     * @param airlineSpecificColorCode
     *     The AirlineSpecificColorCode
     */
    public void setAirlineSpecificColorCode(AirlineSpecificColorCode airlineSpecificColorCode) {
        this.airlineSpecificColorCode = airlineSpecificColorCode;
    }

    /**
     * 
     * @return
     *     The ifsObject
     */
    public List<IfsObject> getIfsObject() {
        return ifsObject;
    }

    /**
     * 
     * @param ifsObject
     *     The IfsObject
     */
    public void setIfsObject(List<IfsObject> ifsObject) {
        this.ifsObject = ifsObject;
    }

    /**
     * 
     * @return
     *     The autoAccount
     */
    public AutoAccount getAutoAccount() {
        return autoAccount;
    }

    /**
     * 
     * @param autoAccount
     *     The AutoAccount
     */
    public void setAutoAccount(AutoAccount autoAccount) {
        this.autoAccount = autoAccount;
    }

    public Boolean getShowContinueButton() {
        return isShowContinueButton;
    }

    public void setShowContinueButton(Boolean showContinueButton) {
        isShowContinueButton = showContinueButton;
    }

    public Boolean getIsPassFlow() {
        return isPassFlow;
    }

    public void setIsPassFlow(Boolean isPassFlow) {
        this.isPassFlow = isPassFlow;
    }

    public Boolean getErrorExists() {
        return errorExists;
    }

    public void setErrorExists(Boolean errorExists) {
        this.errorExists = errorExists;
    }

}

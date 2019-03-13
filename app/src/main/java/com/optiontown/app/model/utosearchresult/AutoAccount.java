
package com.optiontown.app.model.utosearchresult;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutoAccount {

    @SerializedName("JoinOTCheck")
    @Expose
    private Boolean joinOTCheck;
    @SerializedName("LABL_MA_Account_Created_Auto_Fill_Box_Label")
    @Expose
    private String lABLMAAccountCreatedAutoFillBoxLabel;
    @SerializedName("LABL_Email_Short_Label")
    @Expose
    private String lABLEmailShortLabel;

    public String getSpecial_offer() {
        return special_offer;
    }

    public void setSpecial_offer(String special_offer) {
        this.special_offer = special_offer;
    }

    @SerializedName("special_offer")
    @Expose
    private String special_offer;

    /**
     * 
     * @return
     *     The joinOTCheck
     */
    public Boolean getJoinOTCheck() {
        return joinOTCheck;
    }

    /**
     * 
     * @param joinOTCheck
     *     The JoinOTCheck
     */
    public void setJoinOTCheck(Boolean joinOTCheck) {
        this.joinOTCheck = joinOTCheck;
    }

    /**
     * 
     * @return
     *     The lABLMAAccountCreatedAutoFillBoxLabel
     */
    public String getLABLMAAccountCreatedAutoFillBoxLabel() {
        return lABLMAAccountCreatedAutoFillBoxLabel;
    }

    /**
     * 
     * @param lABLMAAccountCreatedAutoFillBoxLabel
     *     The LABL_MA_Account_Created_Auto_Fill_Box_Label
     */
    public void setLABLMAAccountCreatedAutoFillBoxLabel(String lABLMAAccountCreatedAutoFillBoxLabel) {
        this.lABLMAAccountCreatedAutoFillBoxLabel = lABLMAAccountCreatedAutoFillBoxLabel;
    }

    /**
     * 
     * @return
     *     The lABLEmailShortLabel
     */
    public String getLABLEmailShortLabel() {
        return lABLEmailShortLabel;
    }

    /**
     * 
     * @param lABLEmailShortLabel
     *     The LABL_Email_Short_Label
     */
    public void setLABLEmailShortLabel(String lABLEmailShortLabel) {
        this.lABLEmailShortLabel = lABLEmailShortLabel;
    }

}

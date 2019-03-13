
package com.optiontown.app.model.redeem.mmp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsersAdded implements Serializable{

    @SerializedName("DOB")
    @Expose
    private String dOB;
    @SerializedName("Alter_Ph_Code")
    @Expose
    private String alterPhCode;
    @SerializedName("UserValuew")
    @Expose
    private String userValuew;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Prefix")
    @Expose
    private String prefix;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("Ph_Code")
    @Expose
    private String phCode;
    @SerializedName("LName")
    @Expose
    private String lName;
    @SerializedName("FName")
    @Expose
    private String fName;
    @SerializedName("FFPNumber")
    @Expose
    private String fFPNumber;
    @SerializedName("MName")
    @Expose
    private String mName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Alter_Ph_Num")
    @Expose
    private String alterPhNum;
    @SerializedName("Primary_Ph_Num")
    @Expose
    private String primaryPhNum;




    private boolean selectedUser ;
    public String getPassId() {
        return passId;
    }

    public void setPassId(String passId) {
        this.passId = passId;
    }

    private String passId;

    public String getPassLabel() {
        return passLabel;
    }

    String passLabel;

    public int getDisplayFFPNumber() {
        return displayFFPNumber;
    }

    private int displayFFPNumber;

    public int getIsFfpMandatory() {
        return isFfpMandatory;
    }

    public void setIsFfpMandatory(int isFfpMandatory) {
        this.isFfpMandatory = isFfpMandatory;
    }

    private int isFfpMandatory;

    public String getFFpnumberSortDesc() {
        return FFpnumberSortDesc;
    }

    public void setFFpnumberSortDesc(String FFpnumberSortDesc) {
        this.FFpnumberSortDesc = FFpnumberSortDesc;
    }

    public String getFFpnumberHelpMessage() {
        return FFpnumberHelpMessage;
    }

    public void setFFpnumberHelpMessage(String FFpnumberHelpMessage) {
        this.FFpnumberHelpMessage = FFpnumberHelpMessage;
    }

    public String getFFpnumberErrorMessage() {
        return FFpnumberErrorMessage;
    }

    public void setFFpnumberErrorMessage(String FFpnumberErrorMessage) {
        this.FFpnumberErrorMessage = FFpnumberErrorMessage;
    }

    private String FFpnumberSortDesc;
    private String FFpnumberHelpMessage;
    private String FFpnumberErrorMessage;
    /**
     *
     * @return
     *     The dOB
     */
    public String getDOB() {
        return dOB;
    }

    /**
     *
     * @param dOB
     *     The DOB
     */
    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

    /**
     *
     * @return
     *     The alterPhCode
     */
    public String getAlterPhCode() {
        return alterPhCode;
    }

    /**
     *
     * @param alterPhCode
     *     The Alter_Ph_Code
     */
    public void setAlterPhCode(String alterPhCode) {
        this.alterPhCode = alterPhCode;
    }

    /**
     *
     * @return
     *     The userValuew
     */
    public String getUserValuew() {
        return userValuew;
    }

    /**
     *
     * @param userValuew
     *     The UserValuew
     */
    public void setUserValuew(String userValuew) {
        this.userValuew = userValuew;
    }

    /**
     *
     * @return
     *     The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     *     The UserName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     *     The prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     *
     * @param prefix
     *     The Prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     *
     * @return
     *     The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     *     The UserId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     *     The phCode
     */
    public String getPhCode() {
        return phCode;
    }

    /**
     *
     * @param phCode
     *     The Ph_Code
     */
    public void setPhCode(String phCode) {
        this.phCode = phCode;
    }

    /**
     *
     * @return
     *     The lName
     */
    public String getLName() {
        return lName;
    }

    /**
     *
     * @param lName
     *     The LName
     */
    public void setLName(String lName) {
        this.lName = lName;
    }

    /**
     *
     * @return
     *     The fName
     */
    public String getFName() {
        return fName;
    }

    /**
     *
     * @param fName
     *     The FName
     */
    public void setFName(String fName) {
        this.fName = fName;
    }

    /**
     *
     * @return
     *     The fFPNumber
     */
    public String getFFPNumber() {
        return fFPNumber;
    }

    /**
     *
     * @param fFPNumber
     *     The FFPNumber
     */
    public void setFFPNumber(String fFPNumber) {
        this.fFPNumber = fFPNumber;
    }

    /**
     *
     * @return
     *     The mName
     */
    public String getMName() {
        return mName;
    }

    /**
     *
     * @param mName
     *     The MName
     */
    public void setMName(String mName) {
        this.mName = mName;
    }

    /**
     *
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     *     The Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     *     The alterPhNum
     */
    public String getAlterPhNum() {
        return alterPhNum;
    }

    /**
     *
     * @param alterPhNum
     *     The Alter_Ph_Num
     */
    public void setAlterPhNum(String alterPhNum) {
        this.alterPhNum = alterPhNum;
    }

    /**
     *
     * @return
     *     The primaryPhNum
     */
    public String getPrimaryPhNum() {
        return primaryPhNum;
    }

    /**
     *
     * @param primaryPhNum
     *     The Primary_Ph_Num
     */
    public void setPrimaryPhNum(String primaryPhNum) {
        this.primaryPhNum = primaryPhNum;
    }

    public boolean isSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(boolean selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void setDisplayFFPNumber(int displayFFPNumber) {
        this.displayFFPNumber = displayFFPNumber;
    }

    public void setPassLabel(String passLabel) {
        this.passLabel = passLabel;
    }


}

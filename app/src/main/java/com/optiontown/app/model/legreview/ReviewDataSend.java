package com.optiontown.app.model.legreview;

import java.io.Serializable;

/**
 * Created by zafar.imam on 03-09-2016.
 */
public class ReviewDataSend implements Serializable {
    private String express;
    private String isBankAccount;
    private String email;
    private String primaryCode;
    private String primaryMainPart;
    private String othercode;
    private String otherMainPart;
    private String CardCategoryId;
    private String selectedKey;

    public Boolean isConfirmPage() {
        return isConfirmPage;
    }

    public void setConfirmPage(Boolean confirmPage) {
        isConfirmPage = confirmPage;
    }

    private Boolean isConfirmPage = false;

    public String getSelectedKey() {
        return selectedKey;
    }

    public void setSelectedKey(String selectedKey) {
        this.selectedKey = selectedKey;
    }



    public String getTotalAmountToPay() {
        return totalAmountToPay;
    }

    public void setTotalAmountToPay(String totalAmountToPay) {
        this.totalAmountToPay = totalAmountToPay;
    }

    private String totalAmountToPay;

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getIsBankAccount() {
        return isBankAccount;
    }

    public void setIsBankAccount(String isBankAccount) {
        this.isBankAccount = isBankAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimaryCode() {
        return primaryCode;
    }

    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }

    public String getPrimaryMainPart() {
        return primaryMainPart;
    }

    public void setPrimaryMainPart(String primaryMainPart) {
        this.primaryMainPart = primaryMainPart;
    }

    public String getOthercode() {
        return othercode;
    }

    public void setOthercode(String othercode) {
        this.othercode = othercode;
    }

    public String getOtherMainPart() {
        return otherMainPart;
    }

    public void setOtherMainPart(String otherMainPart) {
        this.otherMainPart = otherMainPart;
    }

    public String getCardCategoryId() {
        return CardCategoryId;
    }

    public void setCardCategoryId(String cardCategoryId) {
        CardCategoryId = cardCategoryId;
    }



}

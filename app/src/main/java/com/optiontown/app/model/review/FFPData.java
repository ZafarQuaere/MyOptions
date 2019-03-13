package com.optiontown.app.model.review;

import java.io.Serializable;

/**
 * Created by amit on 18-02-2017.
 */
public class FFPData implements Serializable
{
    public int getIsDisplayFFPNumber() {
        return isDisplayFFPNumber;
    }

    public void setIsDisplayFFPNumber(int isDisplayFFPNumber) {
        this.isDisplayFFPNumber = isDisplayFFPNumber;
    }

    private int isDisplayFFPNumber;

    private String FFpnumberErrorMessage;

    public String getFFpnumberErrorMessage() { return this.FFpnumberErrorMessage; }

    public void setFFpnumberErrorMessage(String FFpnumberErrorMessage) { this.FFpnumberErrorMessage = FFpnumberErrorMessage; }

    private String FFpnumberSortDesc;

    public String getFFpnumberSortDesc() { return this.FFpnumberSortDesc; }

    public void setFFpnumberSortDesc(String FFpnumberSortDesc) { this.FFpnumberSortDesc = FFpnumberSortDesc; }

    private int FFPNumberMandatory;

    public int getFFPNumberMandatory() {
        return FFPNumberMandatory;
    }

    public void setFFPNumberMandatory(int FFPNumberMandatory) {
        this.FFPNumberMandatory = FFPNumberMandatory;
    }
}

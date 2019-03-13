package com.optiontown.app.model.customize;

import java.io.Serializable;

/**
 * Created by amit on 08-08-2016.
 */
public class AdvanceBookingDataRoot implements Serializable
{
    private int passIndex;
    private int cmmIndex;


    public int getPassIndex() {
        return passIndex;
    }

    public void setPassIndex(int passIndex) {
        this.passIndex = passIndex;
    }

    public int getCmmIndex() {
        return cmmIndex;
    }

    public void setCmmIndex(int cmmIndex) {
        this.cmmIndex = cmmIndex;
    }

    private AdvanceBookingData AdvanceBookingData;

    public com.optiontown.app.model.customize.AdvanceBookingData getAdvanceBookingData() {
        return AdvanceBookingData;
    }

    public void setAdvanceBookingData(com.optiontown.app.model.customize.AdvanceBookingData advanceBookingData) {
        AdvanceBookingData = advanceBookingData;
    }
}

package com.optiontown.app.model.customize;

import java.io.Serializable;

/**
 * Created by amit on 08-08-2016.
 */
public class FlexibilityDataRoot implements Serializable
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

    private FlexibilityData FlexibilityData;

    public com.optiontown.app.model.customize.FlexibilityData getFlexibilityData() {
        return FlexibilityData;
    }

    public void setFlexibilityData(com.optiontown.app.model.customize.FlexibilityData flexibilityData) {
        FlexibilityData = flexibilityData;
    }
}

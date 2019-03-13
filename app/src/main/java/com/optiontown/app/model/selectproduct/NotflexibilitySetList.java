package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 02-06-2016.
 */
public class NotflexibilitySetList implements Serializable
{
    private String FlexibilityLabel;

    public String getFlexibilityLabel() { return this.FlexibilityLabel; }

    public void setFlexibilityLabel(String FlexibilityLabel) { this.FlexibilityLabel = FlexibilityLabel; }

    private int FlexibilityRangeId;

    public int getFlexibilityRangeId() { return this.FlexibilityRangeId; }

    public void setFlexibilityRangeId(int FlexibilityRangeId) { this.FlexibilityRangeId = FlexibilityRangeId; }
}

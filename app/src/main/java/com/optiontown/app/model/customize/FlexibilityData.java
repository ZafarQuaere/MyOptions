package com.optiontown.app.model.customize;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 01-08-2016.
 */
public class FlexibilityData implements Serializable
{
    private ArrayList<FlexibilitySetArray> FlexibilitySet_Array;

    public ArrayList<FlexibilitySetArray> getFlexibilitySetArray() { return this.FlexibilitySet_Array; }

    public void setFlexibilitySetArray(ArrayList<FlexibilitySetArray> FlexibilitySet_Array) { this.FlexibilitySet_Array = FlexibilitySet_Array; }

    private ArrayList<NoFlexibilitySetArray> NoFlexibilitySet_Array;

    public ArrayList<NoFlexibilitySetArray> getNoFlexibilitySetArray() { return this.NoFlexibilitySet_Array; }

    public void setNoFlexibilitySetArray(ArrayList<NoFlexibilitySetArray> NoFlexibilitySet_Array) { this.NoFlexibilitySet_Array = NoFlexibilitySet_Array; }
}

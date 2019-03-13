package com.optiontown.app.model.review;

import java.io.Serializable;

/**
 * Created by amit on 01-08-2016.
 */
public class InnerOuterIndexData implements Serializable
{
    private int InnerIndex;
    private int OuterIndex;
    private int ExpCheck;


    public int getExpCheck() {
        return ExpCheck;
    }

    public void setExpCheck(int expCheck) {
        ExpCheck = expCheck;
    }

    public int getInnerIndex() {
        return InnerIndex;
    }

    public void setInnerIndex(int innerIndex) {
        InnerIndex = innerIndex;
    }

    public int getOuterIndex() {
        return OuterIndex;
    }

    public void setOuterIndex(int outerIndex) {
        OuterIndex = outerIndex;
    }
}

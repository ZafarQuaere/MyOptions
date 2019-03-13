package com.optiontown.app.model.review;

import java.io.Serializable;

/**
 * Created by amit on 29-07-2016.
 */
public class PassCMMIndexData implements Serializable
{
    private int passIndex;
    private int cmmIndex;
    private int ExpCheck;

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

    public int getExpCheck() {
        return ExpCheck;
    }

    public void setExpCheck(int expCheck) {
        ExpCheck = expCheck;
    }
}

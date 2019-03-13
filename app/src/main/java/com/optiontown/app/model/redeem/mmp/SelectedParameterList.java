package com.optiontown.app.model.redeem.mmp;

import java.io.Serializable;

/**
 * Created by amit on 08-11-2016.
 */
public class SelectedParameterList implements Serializable
{
    private String ID;
    private String Label;
    private String Err_NotValid;


    public String getErr_NotValid() {
        return Err_NotValid;
    }

    public void setErr_NotValid(String err_NotValid) {
        Err_NotValid = err_NotValid;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}

package com.optiontown.app.model.utosearchresult;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 12/05/2017.
 */

public class PassDropDownList implements Serializable {


    private String pasDisplayLevel;

    public String getPasDisplayLevel() { return this.pasDisplayLevel; }

    public void setPasDisplayLevel(String pasDisplayLevel) { this.pasDisplayLevel = pasDisplayLevel; }

    private String pasDisplayValue;

    public String getPasDisplayValue() { return this.pasDisplayValue; }

    public void setPasDisplayValue(String pasDisplayValue) { this.pasDisplayValue = pasDisplayValue; }
}

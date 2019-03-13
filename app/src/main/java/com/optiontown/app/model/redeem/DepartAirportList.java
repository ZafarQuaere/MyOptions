package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 06/09/2016.
 */
public class DepartAirportList implements Serializable{
    private String Value;

    public String getValue() { return this.Value; }

    public void setValue(String Value) { this.Value = Value; }

    private String Label;

    public String getLabel() { return this.Label; }

    public void setLabel(String Label) { this.Label = Label; }
}

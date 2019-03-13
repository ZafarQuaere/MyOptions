package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by amit on 22-09-2016.
 */
public class PrefixArray implements Serializable
{
    private String Label;

    public String getLabel() { return this.Label; }

    public void setLabel(String Label) { this.Label = Label; }

    private String Value;

    public String getValue() { return this.Value; }

    public void setValue(String Value) { this.Value = Value; }
}

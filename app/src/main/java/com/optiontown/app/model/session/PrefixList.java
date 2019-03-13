package com.optiontown.app.model.session;

import java.io.Serializable;

/**
 * Created by amit on 14-04-2017.
 */
public class PrefixList implements Serializable
{
    private String PrefixValue;

    public String getPrefixValue() { return this.PrefixValue; }

    public void setPrefixValue(String PrefixValue) { this.PrefixValue = PrefixValue; }

    private String PrefixLabel;

    public String getPrefixLabel() { return this.PrefixLabel; }

    public void setPrefixLabel(String PrefixLabel) { this.PrefixLabel = PrefixLabel; }
}

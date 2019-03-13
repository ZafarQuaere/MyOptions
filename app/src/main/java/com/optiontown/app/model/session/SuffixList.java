package com.optiontown.app.model.session;

import java.io.Serializable;

/**
 * Created by amit on 14-04-2017.
 */
public class SuffixList implements Serializable
{
    private String SuffixLabel;

    public String getSuffixLabel() { return this.SuffixLabel; }

    public void setSuffixLabel(String SuffixLabel) { this.SuffixLabel = SuffixLabel; }

    private String SuffixValue;

    public String getSuffixValue() { return this.SuffixValue; }

    public void setSuffixValue(String SuffixValue) { this.SuffixValue = SuffixValue; }
}

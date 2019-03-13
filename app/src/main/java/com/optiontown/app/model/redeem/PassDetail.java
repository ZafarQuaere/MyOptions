package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by amit on 13-09-2016.
 */
public class PassDetail implements Serializable
{
    private String tab;

    public String getTab() { return this.tab; }

    public void setTab(String tab) { this.tab = tab; }

    private String ifsIndex;

    public String getIfsIndex() { return this.ifsIndex; }

    public void setIfsIndex(String ifsIndex) { this.ifsIndex = ifsIndex; }
}

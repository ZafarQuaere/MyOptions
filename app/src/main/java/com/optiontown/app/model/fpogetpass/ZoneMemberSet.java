package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;

/**
 * Created by amit on 16-06-2016.
 */
public class ZoneMemberSet implements Serializable
{
    private int TgpfgId;

    public int getTgpfgId() { return this.TgpfgId; }

    public void setTgpfgId(int TgpfgId) { this.TgpfgId = TgpfgId; }

    private String ZoneSubGroupId;

    public String getZoneSubGroupId() { return this.ZoneSubGroupId; }

    public void setZoneSubGroupId(String ZoneSubGroupId) { this.ZoneSubGroupId = ZoneSubGroupId; }

    private String ShortDescription;

    public String getShortDescription() { return this.ShortDescription; }

    public void setShortDescription(String ShortDescription) { this.ShortDescription = ShortDescription; }

    private int ZoneGroupId;

    public int getZoneGroupId() { return this.ZoneGroupId; }

    public void setZoneGroupId(int ZoneGroupId) { this.ZoneGroupId = ZoneGroupId; }

    private int ZoneId;

    public int getZoneId() { return this.ZoneId; }

    public void setZoneId(int ZoneId) { this.ZoneId = ZoneId; }
}

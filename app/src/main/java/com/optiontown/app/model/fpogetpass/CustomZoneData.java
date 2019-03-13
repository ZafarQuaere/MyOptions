package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;

/**
 * Created by amit on 18-04-2017.
 */
public class CustomZoneData implements Serializable
{
    private String zonesubGroupId;

    public String getZonesubGroupId() { return this.zonesubGroupId; }

    public void setZonesubGroupId(String zonesubGroupId) { this.zonesubGroupId = zonesubGroupId; }

    private boolean Sucess;

    public boolean getSucess() { return this.Sucess; }

    public void setSucess(boolean Sucess) { this.Sucess = Sucess; }

    private String zoneName;

    public String getZoneName() { return this.zoneName; }

    public void setZoneName(String zoneName) { this.zoneName = zoneName; }

    private int zoneId;

    public int getZoneId() { return this.zoneId; }

    public void setZoneId(int zoneId) { this.zoneId = zoneId; }

    private int tgpFgId;

    public int getTgpFgId() { return this.tgpFgId; }

    public void setTgpFgId(int tgpFgId) { this.tgpFgId = tgpFgId; }
}

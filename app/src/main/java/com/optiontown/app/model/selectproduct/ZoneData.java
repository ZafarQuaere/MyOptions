package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class ZoneData implements Serializable
{
    private String Help;
    private String ZoneSubGroupId;
    private int ZoneGroupId;
    private int ZoneId;
    private long TgpFgId;
    private String ZoneName;

    public String getZoneName() {
        return ZoneName;
    }

    public void setZoneName(String zoneName) {
        ZoneName = zoneName;
    }

    public String getZoneSubGroupId() {
        return ZoneSubGroupId;
    }

    public void setZoneSubGroupId(String zoneSubGroupId) {
        ZoneSubGroupId = zoneSubGroupId;
    }

    public int getZoneGroupId() {
        return ZoneGroupId;
    }

    public void setZoneGroupId(int zoneGroupId) {
        ZoneGroupId = zoneGroupId;
    }

    public int getZoneId() {
        return ZoneId;
    }

    public void setZoneId(int zoneId) {
        ZoneId = zoneId;
    }

    public long getTgpFgId() {
        return TgpFgId;
    }

    public void setTgpFgId(long tgpFgId) {
        TgpFgId = tgpFgId;
    }

    public String getHelp() { return this.Help; }

    public void setHelp(String Help) { this.Help = Help; }

    private ArrayList<Zone> Zone;

    public ArrayList<Zone> getZone() { return this.Zone; }

    public void setZone(ArrayList<Zone> Zone) { this.Zone = Zone; }

    private String Title;

    public String getTitle() { return this.Title; }

    public void setTitle(String Title) { this.Title = Title; }


}

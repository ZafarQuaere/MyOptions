package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class ZoneChild implements Serializable
{
    private String ZoneSubGroupId;

    public String getZoneSubGroupId() { return this.ZoneSubGroupId; }

    public void setZoneSubGroupId(String ZoneSubGroupId) { this.ZoneSubGroupId = ZoneSubGroupId; }

    private ArrayList<SubChild> SubChild;

    public ArrayList<SubChild> getSubChild() { return this.SubChild; }

    public void setSubChild(ArrayList<SubChild> SubChild) { this.SubChild = SubChild; }

    private long TGpFGId;

    public long getTGpFGId() { return this.TGpFGId; }

    public void setTGpFGId(long TGpFGId) { this.TGpFGId = TGpFGId; }

    private String BigRouteMap;

    public String getBigRouteMap() { return this.BigRouteMap; }

    public void setBigRouteMap(String BigRouteMap) { this.BigRouteMap = BigRouteMap; }

    private int ZoneGroupId;

    public int getZoneGroupId() { return this.ZoneGroupId; }

    public void setZoneGroupId(int ZoneGroupId) { this.ZoneGroupId = ZoneGroupId; }

    private String ShortDesc;

    public String getShortDesc() { return this.ShortDesc; }

    public void setShortDesc(String ShortDesc) { this.ShortDesc = ShortDesc; }

    private int ZoneId;

    public int getZoneId() { return this.ZoneId; }

    public void setZoneId(int ZoneId) { this.ZoneId = ZoneId; }

    private String LongDesc;

    public String getLongDesc() { return this.LongDesc; }

    public void setLongDesc(String LongDesc) { this.LongDesc = LongDesc; }
}

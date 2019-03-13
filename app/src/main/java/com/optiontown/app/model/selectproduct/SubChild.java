package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 02-06-2016.
 */
public class SubChild implements Serializable
{
   private String ZoneSubGroupId;

    public String getZoneSubGroupId() { return this.ZoneSubGroupId; }

    public void setZoneSubGroupId(String ZoneSubGroupId) { this.ZoneSubGroupId = ZoneSubGroupId; }

    private int TGpFGId;

    public int getTGpFGId() { return this.TGpFGId; }

    public void setTGpFGId(int TGpFGId) { this.TGpFGId = TGpFGId; }

    private String Exist;

    public String getExist() { return this.Exist; }

    public void setExist(String Exist) { this.Exist = Exist; }

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

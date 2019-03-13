package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class Zone implements Serializable
{
    private String ZoneName;

    public String getZoneName() { return this.ZoneName; }

    public void setZoneName(String ZoneName) { this.ZoneName = ZoneName; }

    private ArrayList<ZoneChild> ZoneChild;

    public ArrayList<ZoneChild> getZoneChild() { return this.ZoneChild; }

    public void setZoneChild(ArrayList<ZoneChild> ZoneChild) { this.ZoneChild = ZoneChild; }
}
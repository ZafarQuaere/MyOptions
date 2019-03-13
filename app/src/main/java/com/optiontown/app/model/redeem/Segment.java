package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 09-09-2016.
 */
public class Segment implements Serializable
{
    private ArrayList<LegList> legList;

    public ArrayList<LegList> getLegList() { return this.legList; }

    public void setLegList(ArrayList<LegList> legList) { this.legList = legList; }
}

package com.optiontown.app.model.review;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 05-06-2017.
 */
public class OdListWithDate implements Serializable
{
    private String restrict_date_range;

    public String getRestrictDateRange() { return this.restrict_date_range; }

    public void setRestrictDateRange(String restrict_date_range) { this.restrict_date_range = restrict_date_range; }

    private ArrayList<Odlist> odlist;

    public ArrayList<Odlist> getOdlist() { return this.odlist; }

    public void setOdlist(ArrayList<Odlist> odlist) { this.odlist = odlist; }
}

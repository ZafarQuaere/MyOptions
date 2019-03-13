package com.optiontown.app.model.countryextlist;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 14-07-2016.
 */
public class CountryExtData implements Serializable
{
    private ArrayList<CountryList> CountryList;

    public ArrayList<CountryList> getCountryList() { return this.CountryList; }

    public void setCountryList(ArrayList<CountryList> CountryList) { this.CountryList = CountryList; }

    private ArrayList<Data> Data;

    public ArrayList<Data> getData() { return this.Data; }

    public void setData(ArrayList<Data> Data) { this.Data = Data; }
}

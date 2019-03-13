package com.optiontown.app.model.session;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 21-06-2016.
 */
public class CountryListAPI implements Serializable
{
    private ArrayList<CountryList> CountryList;

    public ArrayList<CountryList> getCountryList() { return this.CountryList; }

    public void setCountryList(ArrayList<CountryList> CountryList) { this.CountryList = CountryList; }
}

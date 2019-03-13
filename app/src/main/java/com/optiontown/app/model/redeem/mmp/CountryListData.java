package com.optiontown.app.model.redeem.mmp;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zafar.imam on 03-11-2016.
 */

public class CountryListData implements Serializable {
    private ArrayList<CountryList> countryLists;
    public ArrayList<CountryList> getCountryLists() {
        return countryLists;
    }

    public void setCountryLists(ArrayList<CountryList> countryLists) {
        this.countryLists = countryLists;
    }


}


package com.optiontown.app.model.homepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CountryListAPI {

    @SerializedName("CountryList")
    @Expose
    private List<CountryList> countryList = new ArrayList<CountryList>();

    /**
     * 
     * @return
     *     The countryList
     */
    public List<CountryList> getCountryList() {
        return countryList;
    }

    /**
     * 
     * @param countryList
     *     The CountryList
     */
    public void setCountryList(List<CountryList> countryList) {
        this.countryList = countryList;
    }

}

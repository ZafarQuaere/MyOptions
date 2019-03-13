package com.optiontown.app.model.countryextlist;

import java.io.Serializable;

/**
 * Created by amit on 14-07-2016.
 */
public class CountryList implements Serializable
{
    private String CountryId;

    public String getCountryId() { return this.CountryId; }

    public void setCountryId(String CountryId) { this.CountryId = CountryId; }

    private String Country;

    public String getCountry() { return this.Country; }

    public void setCountry(String Country) { this.Country = Country; }
}

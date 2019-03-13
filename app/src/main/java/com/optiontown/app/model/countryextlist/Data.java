package com.optiontown.app.model.countryextlist;

import java.io.Serializable;

/**
 * Created by amit on 14-07-2016.
 */
public class Data implements Serializable
{
    private String Extension;

    public String getExtension() { return this.Extension; }

    public void setExtension(String Extension) { this.Extension = Extension; }

    private String Country;

    public String getCountry() { return this.Country; }

    public void setCountry(String Country) { this.Country = Country; }
}

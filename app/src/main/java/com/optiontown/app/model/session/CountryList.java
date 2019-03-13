package com.optiontown.app.model.session;

import java.io.Serializable;

/**
 * Created by amit on 21-06-2016.
 */
public class CountryList implements Serializable
{
    private String CountryName;

    public String getCountryName() { return this.CountryName; }

    public void setCountryName(String CountryName) { this.CountryName = CountryName; }

    private int CountryID;

    public int getCountryID() { return this.CountryID; }

    public void setCountryID(int CountryID) { this.CountryID = CountryID; }

    private String Language;

    public String getLanguage() { return this.Language; }

    public void setLanguage(String Language) { this.Language = Language; }

    private int LanguageID;

    public int getLanguageID() { return this.LanguageID; }

    public void setLanguageID(int LanguageID) { this.LanguageID = LanguageID; }

    private String CountryLogo;

    public String getCountryLogo() {
        return CountryLogo;
    }

    public void setCountryLogo(String countryLogo) {
        CountryLogo = countryLogo;
    }
}

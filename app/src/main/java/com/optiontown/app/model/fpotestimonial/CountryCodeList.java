
package com.optiontown.app.model.fpotestimonial;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CountryCodeList {

    @SerializedName("CountryLabel")
    @Expose
    private String countryLabel;
    @SerializedName("CountryID")
    @Expose
    private String countryID;

    /**
     * 
     * @return
     *     The countryLabel
     */
    public String getCountryLabel() {
        return countryLabel;
    }

    /**
     * 
     * @param countryLabel
     *     The CountryLabel
     */
    public void setCountryLabel(String countryLabel) {
        this.countryLabel = countryLabel;
    }

    /**
     * 
     * @return
     *     The countryID
     */
    public String getCountryID() {
        return countryID;
    }

    /**
     * 
     * @param countryID
     *     The CountryID
     */
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

}

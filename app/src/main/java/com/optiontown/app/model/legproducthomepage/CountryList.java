
package com.optiontown.app.model.legproducthomepage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CountryList {

    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("ISOCode")
    @Expose
    private String iSOCode;
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("CountryLogo")
    @Expose
    private String countryLogo;
    @SerializedName("LanguageID")
    @Expose
    private Integer languageID;

    /**
     * 
     * @return
     *     The countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 
     * @param countryName
     *     The CountryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 
     * @return
     *     The iSOCode
     */
    public String getISOCode() {
        return iSOCode;
    }

    /**
     * 
     * @param iSOCode
     *     The ISOCode
     */
    public void setISOCode(String iSOCode) {
        this.iSOCode = iSOCode;
    }

    /**
     * 
     * @return
     *     The countryID
     */
    public Integer getCountryID() {
        return countryID;
    }

    /**
     * 
     * @param countryID
     *     The CountryID
     */
    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    /**
     * 
     * @return
     *     The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The Language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @return
     *     The countryLogo
     */
    public String getCountryLogo() {
        return countryLogo;
    }

    /**
     * 
     * @param countryLogo
     *     The CountryLogo
     */
    public void setCountryLogo(String countryLogo) {
        this.countryLogo = countryLogo;
    }

    /**
     * 
     * @return
     *     The languageID
     */
    public Integer getLanguageID() {
        return languageID;
    }

    /**
     * 
     * @param languageID
     *     The LanguageID
     */
    public void setLanguageID(Integer languageID) {
        this.languageID = languageID;
    }

}

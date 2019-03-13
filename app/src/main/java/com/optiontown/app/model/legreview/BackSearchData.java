
package com.optiontown.app.model.legreview;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BackSearchData {

    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("tgProductId")
    @Expose
    private Integer tgProductId;
    @SerializedName("OCN")
    @Expose
    private String oCN;
    @SerializedName("isSearchBy")
    @Expose
    private String isSearchBy;
    @SerializedName("MarketingAirlineId")
    @Expose
    private Integer marketingAirlineId;
    @SerializedName("CountryId")
    @Expose
    private Integer countryId;
    @SerializedName("pnr")
    @Expose
    private String pnr;
    @SerializedName("LanguageId")
    @Expose
    private Integer languageId;

    /**
     * 
     * @return
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *     The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     *     The tgProductId
     */
    public Integer getTgProductId() {
        return tgProductId;
    }

    /**
     * 
     * @param tgProductId
     *     The tgProductId
     */
    public void setTgProductId(Integer tgProductId) {
        this.tgProductId = tgProductId;
    }

    /**
     * 
     * @return
     *     The oCN
     */
    public String getOCN() {
        return oCN;
    }

    /**
     * 
     * @param oCN
     *     The OCN
     */
    public void setOCN(String oCN) {
        this.oCN = oCN;
    }

    /**
     * 
     * @return
     *     The isSearchBy
     */
    public String getIsSearchBy() {
        return isSearchBy;
    }

    /**
     * 
     * @param isSearchBy
     *     The isSearchBy
     */
    public void setIsSearchBy(String isSearchBy) {
        this.isSearchBy = isSearchBy;
    }

    /**
     * 
     * @return
     *     The marketingAirlineId
     */
    public Integer getMarketingAirlineId() {
        return marketingAirlineId;
    }

    /**
     * 
     * @param marketingAirlineId
     *     The MarketingAirlineId
     */
    public void setMarketingAirlineId(Integer marketingAirlineId) {
        this.marketingAirlineId = marketingAirlineId;
    }

    /**
     * 
     * @return
     *     The countryId
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * 
     * @param countryId
     *     The CountryId
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * 
     * @return
     *     The pnr
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * 
     * @param pnr
     *     The pnr
     */
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    /**
     * 
     * @return
     *     The languageId
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * 
     * @param languageId
     *     The LanguageId
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

}

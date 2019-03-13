
package com.optiontown.app.model.homepage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HomeStatic {

    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("IndustryList")
    @Expose
    private IndustryList industryList;
    @SerializedName("StaticBanner")
    @Expose
    private StaticBanner staticBanner;
    @SerializedName("CountryListAPI")
    @Expose
    private CountryListAPI countryListAPI;

    /**
     * 
     * @return
     *     The sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * 
     * @param sessionId
     *     The sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 
     * @return
     *     The industryList
     */
    public IndustryList getIndustryList() {
        return industryList;
    }

    /**
     * 
     * @param industryList
     *     The IndustryList
     */
    public void setIndustryList(IndustryList industryList) {
        this.industryList = industryList;
    }

    /**
     * 
     * @return
     *     The staticBanner
     */
    public StaticBanner getStaticBanner() {
        return staticBanner;
    }

    /**
     * 
     * @param staticBanner
     *     The StaticBanner
     */
    public void setStaticBanner(StaticBanner staticBanner) {
        this.staticBanner = staticBanner;
    }

    /**
     * 
     * @return
     *     The countryListAPI
     */
    public CountryListAPI getCountryListAPI() {
        return countryListAPI;
    }

    /**
     * 
     * @param countryListAPI
     *     The CountryListAPI
     */
    public void setCountryListAPI(CountryListAPI countryListAPI) {
        this.countryListAPI = countryListAPI;
    }

}

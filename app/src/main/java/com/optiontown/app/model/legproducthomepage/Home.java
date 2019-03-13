
package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Home implements Serializable {

    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("IndustryList")
    @Expose
    private IndustryList industryList;
    @SerializedName("StaticBanner")
    @Expose
    private StaticBanner staticBanner;
    @SerializedName("HomePageData")
    @Expose
    private HomePageData homePageData;
    @SerializedName("CountryListAPI")
    @Expose
    private CountryListAPI countryListAPI;

    public String getTestimonial_Image() {
        return Testimonial_Image;
    }

    public void setTestimonial_Image(String testimonial_Image) {
        Testimonial_Image = testimonial_Image;
    }

    @SerializedName("Testimonial_Image")
    @Expose
    private String Testimonial_Image;

    public String getTestimonial_Label() {
        return Testimonial_Label;
    }

    public void setTestimonial_Label(String testimonial_Label) {
        Testimonial_Label = testimonial_Label;
    }

    private String Testimonial_Label;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public IndustryList getIndustryList() {
        return industryList;
    }


    public void setIndustryList(IndustryList industryList) {
        this.industryList = industryList;
    }


    public StaticBanner getStaticBanner() {
        return staticBanner;
    }


    public void setStaticBanner(StaticBanner staticBanner) {
        this.staticBanner = staticBanner;
    }


    public HomePageData getHomePageData() {
        return homePageData;
    }


    public void setHomePageData(HomePageData homePageData) {
        this.homePageData = homePageData;
    }


    public CountryListAPI getCountryListAPI() {
        return countryListAPI;
    }


    public void setCountryListAPI(CountryListAPI countryListAPI) {
        this.countryListAPI = countryListAPI;
    }

}

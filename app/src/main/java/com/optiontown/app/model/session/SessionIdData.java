package com.optiontown.app.model.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 21-06-2016.
 */
public class SessionIdData implements Serializable {

    private ArrayList<PrefixList> PrefixList;

    public ArrayList<PrefixList> getPrefixList() { return this.PrefixList; }

    public void setPrefixList(ArrayList<PrefixList> PrefixList) { this.PrefixList = PrefixList; }

    private ArrayList<SuffixList> SuffixList;

    public ArrayList<SuffixList> getSuffixList() { return this.SuffixList; }

    public void setSuffixList(ArrayList<SuffixList> SuffixList) { this.SuffixList = SuffixList; }

    private ArrayList<GenderList> GenderList;

    public ArrayList<GenderList> getGenderList() { return this.GenderList; }

    public void setGenderList(ArrayList<GenderList> GenderList) { this.GenderList = GenderList; }

    public String getTgProductId() {
        return tgProductId;
    }

    public void setTgProductId(String tgProductId) {
        this.tgProductId = tgProductId;
    }

    private String tgProductId;

    private PasswordValidatioJson passwordValidatioJson;

    public PasswordValidatioJson getPasswordValidatioJson() { return this.passwordValidatioJson; }

    public void setPasswordValidatioJson(PasswordValidatioJson passwordValidatioJson) { this.passwordValidatioJson = passwordValidatioJson; }

    private String Testimonial_Label;

    public String getTestimonialLabel() {
        return this.Testimonial_Label;
    }

    public void setTestimonialLabel(String Testimonial_Label) {
        this.Testimonial_Label = Testimonial_Label;
    }

    private String Testimonial_Image;

    public String getTestimonialImage() {
        return this.Testimonial_Image;
    }

    public void setTestimonialImage(String Testimonial_Image) {
        this.Testimonial_Image = Testimonial_Image;
    }


    private int CountryId;
    private int LanguageId;

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public int getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(int languageId) {
        LanguageId = languageId;
    }


    private String sessionId;

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private IndustryList IndustryList;

    public IndustryList getIndustryList() {
        return this.IndustryList;
    }

    public void setIndustryList(IndustryList IndustryList) {
        this.IndustryList = IndustryList;
    }

    private ArrayList<Testimonial> Testimonial;

    public ArrayList<Testimonial> getTestimonial() {
        return this.Testimonial;
    }

    public void setTestimonial(ArrayList<Testimonial> Testimonial) {
        this.Testimonial = Testimonial;
    }

    private CountryListAPI CountryListAPI;

    public CountryListAPI getCountryListAPI() {
        return this.CountryListAPI;
    }

    public void setCountryListAPI(CountryListAPI CountryListAPI) {
        this.CountryListAPI = CountryListAPI;
    }


    /*
    * paras
    * data provided for home page (in case of language change takes place)
    * */
    private LearnAboutFlightPass LearnAboutFlightPass;

    public LearnAboutFlightPass getLearnAboutFlightPass() {
        return this.LearnAboutFlightPass;
    }

    public void setLearnAboutFlightPass(LearnAboutFlightPass LearnAboutFlightPass) {
        this.LearnAboutFlightPass = LearnAboutFlightPass;
    }
}




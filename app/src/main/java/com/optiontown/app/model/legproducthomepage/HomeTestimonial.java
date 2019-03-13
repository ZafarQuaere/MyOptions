
package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HomeTestimonial {

    @SerializedName("summury")
    @Expose
    private String summury;
    @SerializedName("writter")
    @Expose
    private String writter;
    @SerializedName("country")
    @Expose
    private String country;

    /**
     * 
     * @return
     *     The summury
     */
    public String getSummury() {
        return summury;
    }

    /**
     * 
     * @param summury
     *     The summury
     */
    public void setSummury(String summury) {
        this.summury = summury;
    }

    /**
     * 
     * @return
     *     The writter
     */
    public String getWritter() {
        return writter;
    }

    /**
     * 
     * @param writter
     *     The writter
     */
    public void setWritter(String writter) {
        this.writter = writter;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

}

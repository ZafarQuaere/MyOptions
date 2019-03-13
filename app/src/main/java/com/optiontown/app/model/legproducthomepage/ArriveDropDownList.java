
package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArriveDropDownList {

    @SerializedName("arrive_name")
    @Expose
    private String arriveName;
    @SerializedName("arrive_Code")
    @Expose
    private String arriveCode;

    /**
     * 
     * @return
     *     The arriveName
     */
    public String getArriveName() {
        return arriveName;
    }

    /**
     * 
     * @param arriveName
     *     The arrive_name
     */
    public void setArriveName(String arriveName) {
        this.arriveName = arriveName;
    }

    /**
     * 
     * @return
     *     The arriveCode
     */
    public String getArriveCode() {
        return arriveCode;
    }

    /**
     * 
     * @param arriveCode
     *     The arrive_Code
     */
    public void setArriveCode(String arriveCode) {
        this.arriveCode = arriveCode;
    }

}


package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartDropDownList {

    @SerializedName("depart_Name")
    @Expose
    private String departName;
    @SerializedName("depart_Code")
    @Expose
    private String departCode;

    /**
     * 
     * @return
     *     The departName
     */
    public String getDepartName() {
        return departName;
    }

    /**
     * 
     * @param departName
     *     The depart_Name
     */
    public void setDepartName(String departName) {
        this.departName = departName;
    }

    /**
     * 
     * @return
     *     The departCode
     */
    public String getDepartCode() {
        return departCode;
    }

    /**
     * 
     * @param departCode
     *     The depart_Code
     */
    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

}

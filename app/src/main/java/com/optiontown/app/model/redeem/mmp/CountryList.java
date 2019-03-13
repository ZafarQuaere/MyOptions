
package com.optiontown.app.model.redeem.mmp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryList {

    @SerializedName("C_Name")
    @Expose
    private String cName;
    @SerializedName("C_Id")
    @Expose
    private String cId;

    /**
     * 
     * @return
     *     The cName
     */
    public String getCName() {
        return cName;
    }

    /**
     * 
     * @param cName
     *     The C_Name
     */
    public void setCName(String cName) {
        this.cName = cName;
    }

    /**
     * 
     * @return
     *     The cId
     */
    public String getCId() {
        return cId;
    }

    /**
     * 
     * @param cId
     *     The C_Id
     */
    public void setCId(String cId) {
        this.cId = cId;
    }

}


package com.optiontown.app.model.utosearchresult;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcabinNameDetail {

    @SerializedName("UpCabinNames")
    @Expose
    private String upCabinNames;

    /**
     * 
     * @return
     *     The upCabinNames
     */
    public String getUpCabinNames() {
        return upCabinNames;
    }

    /**
     * 
     * @param upCabinNames
     *     The UpCabinNames
     */
    public void setUpCabinNames(String upCabinNames) {
        this.upCabinNames = upCabinNames;
    }

}


package com.optiontown.app.model.redeem.mmp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExtensionList implements Serializable{

    @SerializedName("Ph_Label")
    @Expose
    private String phLabel;
    @SerializedName("Ph_Ext")
    @Expose
    private String phExt;

    /**
     * 
     * @return
     *     The phLabel
     */
    public String getPhLabel() {
        return phLabel;
    }

    /**
     * 
     * @param phLabel
     *     The Ph_Label
     */
    public void setPhLabel(String phLabel) {
        this.phLabel = phLabel;
    }

    /**
     * 
     * @return
     *     The phExt
     */
    public String getPhExt() {
        return phExt;
    }

    /**
     * 
     * @param phExt
     *     The Ph_Ext
     */
    public void setPhExt(String phExt) {
        this.phExt = phExt;
    }

}

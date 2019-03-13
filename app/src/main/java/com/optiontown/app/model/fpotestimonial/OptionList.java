
package com.optiontown.app.model.fpotestimonial;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OptionList {

    @SerializedName("OptionLabel")
    @Expose
    private String optionLabel;
    @SerializedName("OptionID")
    @Expose
    private String optionID;

    /**
     * 
     * @return
     *     The optionLabel
     */
    public String getOptionLabel() {
        return optionLabel;
    }

    /**
     * 
     * @param optionLabel
     *     The OptionLabel
     */
    public void setOptionLabel(String optionLabel) {
        this.optionLabel = optionLabel;
    }

    /**
     * 
     * @return
     *     The optionID
     */
    public String getOptionID() {
        return optionID;
    }

    /**
     * 
     * @param optionID
     *     The OptionID
     */
    public void setOptionID(String optionID) {
        this.optionID = optionID;
    }

}

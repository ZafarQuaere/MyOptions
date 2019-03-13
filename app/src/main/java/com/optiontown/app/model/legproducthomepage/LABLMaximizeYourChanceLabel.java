
package com.optiontown.app.model.legproducthomepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LABLMaximizeYourChanceLabel {

    @SerializedName("ReadMore_Label")
    @Expose
    private String readMoreLabel;
    @SerializedName("MainTitle")
    @Expose
    private String mainTitle;
    @SerializedName("Maximize")
    @Expose
    private List<Maximize> maximize = new ArrayList<Maximize>();

    /**
     * 
     * @return
     *     The readMoreLabel
     */
    public String getReadMoreLabel() {
        return readMoreLabel;
    }

    /**
     * 
     * @param readMoreLabel
     *     The ReadMore_Label
     */
    public void setReadMoreLabel(String readMoreLabel) {
        this.readMoreLabel = readMoreLabel;
    }

    /**
     * 
     * @return
     *     The mainTitle
     */
    public String getMainTitle() {
        return mainTitle;
    }

    /**
     * 
     * @param mainTitle
     *     The MainTitle
     */
    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    /**
     * 
     * @return
     *     The maximize
     */
    public List<Maximize> getMaximize() {
        return maximize;
    }

    /**
     * 
     * @param maximize
     *     The Maximize
     */
    public void setMaximize(List<Maximize> maximize) {
        this.maximize = maximize;
    }

}

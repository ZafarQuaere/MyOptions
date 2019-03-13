
package com.optiontown.app.model.legproducthomepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LABLHomeBenifitLabel {

    @SerializedName("ReadMore_Label")
    @Expose
    private String readMoreLabel;
    @SerializedName("MainTitle")
    @Expose
    private String mainTitle;
    @SerializedName("Benefits")
    @Expose
    private List<Benefit> benefits = new ArrayList<Benefit>();

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
     *     The benefits
     */
    public List<Benefit> getBenefits() {
        return benefits;
    }

    /**
     * 
     * @param benefits
     *     The Benefits
     */
    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

}

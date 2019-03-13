
package com.optiontown.app.model.legproducthomepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LABLHomeFAQsLabel {

    @SerializedName("ReadMore_Label")
    @Expose
    private String readMoreLabel;
    @SerializedName("FAQs")
    @Expose
    private List<FAQ> fAQs = new ArrayList<FAQ>();
    @SerializedName("MainTitle")
    @Expose
    private String mainTitle;

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
     *     The fAQs
     */
    public List<FAQ> getFAQs() {
        return fAQs;
    }

    /**
     * 
     * @param fAQs
     *     The FAQs
     */
    public void setFAQs(List<FAQ> fAQs) {
        this.fAQs = fAQs;
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

}

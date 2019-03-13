
package com.optiontown.app.model.legproducthomepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LABLHomeLearnLabel {

    @SerializedName("ReadMore_Label")
    @Expose
    private String readMoreLabel;
    @SerializedName("Learn_about")
    @Expose
    private List<LearnAbout> learnAbout = new ArrayList<LearnAbout>();
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
     *     The learnAbout
     */
    public List<LearnAbout> getLearnAbout() {
        return learnAbout;
    }

    /**
     * 
     * @param learnAbout
     *     The Learn_about
     */
    public void setLearnAbout(List<LearnAbout> learnAbout) {
        this.learnAbout = learnAbout;
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

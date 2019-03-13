
package com.optiontown.app.model.homepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class IndustryList {

    @SerializedName("FaqQuestion")
    @Expose
    private List<Object> faqQuestion = new ArrayList<Object>();
    @SerializedName("groupArray")
    @Expose
    private List<Object> groupArray = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The faqQuestion
     */
    public List<Object> getFaqQuestion() {
        return faqQuestion;
    }

    /**
     * 
     * @param faqQuestion
     *     The FaqQuestion
     */
    public void setFaqQuestion(List<Object> faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    /**
     * 
     * @return
     *     The groupArray
     */
    public List<Object> getGroupArray() {
        return groupArray;
    }

    /**
     * 
     * @param groupArray
     *     The groupArray
     */
    public void setGroupArray(List<Object> groupArray) {
        this.groupArray = groupArray;
    }

}

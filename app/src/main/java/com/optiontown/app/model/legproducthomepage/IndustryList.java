
package com.optiontown.app.model.legproducthomepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.optiontown.app.model.session.FaqQuestion;


public class IndustryList {

    @SerializedName("FaqQuestion")
    @Expose
    private ArrayList<FaqQuestion> faqQuestion = new ArrayList<FaqQuestion>();
    @SerializedName("groupArray")
    @Expose
    private List<Object> groupArray = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The faqQuestion
     */
    public ArrayList<FaqQuestion> getFaqQuestion() {
        return faqQuestion;
    }

    /**
     * 
     * @param faqQuestion
     *     The FaqQuestion
     */
    public void setFaqQuestion(ArrayList<FaqQuestion> faqQuestion) {
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

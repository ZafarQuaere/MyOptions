package com.optiontown.app.model.session;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 21-06-2016.
 */
public class IndustryList implements Serializable {
    private ArrayList<FaqQuestion> FaqQuestion;

    public ArrayList<FaqQuestion> getFaqQuestion() {
        return this.FaqQuestion;
    }

    public void setFaqQuestion(ArrayList<FaqQuestion> FaqQuestion) {
        this.FaqQuestion = FaqQuestion;
    }

    private ArrayList<GroupArray> groupArray;

    public ArrayList<GroupArray> getGroupArray() {
        return this.groupArray;
    }

    public void setGroupArray(ArrayList<GroupArray> groupArray) {
        this.groupArray = groupArray;
    }
}

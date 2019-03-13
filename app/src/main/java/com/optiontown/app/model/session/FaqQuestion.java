package com.optiontown.app.model.session;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 21-06-2016.
 */
public class FaqQuestion implements Serializable
{
    private ArrayList<QuestionAnswer> QuestionAnswer;

    public ArrayList<QuestionAnswer> getQuestionAnswer() { return this.QuestionAnswer; }

    public void setQuestionAnswer(ArrayList<QuestionAnswer> QuestionAnswer) { this.QuestionAnswer = QuestionAnswer; }

    private String FaqNAme;

    public String getFaqNAme() { return this.FaqNAme; }

    public void setFaqNAme(String FaqNAme) { this.FaqNAme = FaqNAme; }
}

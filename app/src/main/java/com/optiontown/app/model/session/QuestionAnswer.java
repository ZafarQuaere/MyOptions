package com.optiontown.app.model.session;

import java.io.Serializable;

/**
 * Created by amit on 21-06-2016.
 */
public class QuestionAnswer implements Serializable
{
    private String Ans;

    public String getAns() { return this.Ans; }

    public void setAns(String Ans) { this.Ans = Ans; }

    private String Ques;

    public String getQues() { return this.Ques; }

    public void setQues(String Ques) { this.Ques = Ques; }
}

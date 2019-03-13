
package com.optiontown.app.model.legviewdetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Rule {

    @SerializedName("Title")
    @Expose
    private String title;

    public String getRule() {
        return Rule;
    }

    public void setRule(String rule) {
        Rule = rule;
    }

    @SerializedName("Rule")
    @Expose
    private String Rule;

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }



}

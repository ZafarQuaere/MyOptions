
package com.optiontown.app.model.legviewdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UtoRules implements Serializable{

    @SerializedName("Rules")
    @Expose
    private List<Rule> rules = new ArrayList<Rule>();

    /**
     *
     * @return
     * The rules
     */
    public List<Rule> getRules() {
        return rules;
    }

    /**
     *
     * @param rules
     * The Rules
     */
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

}
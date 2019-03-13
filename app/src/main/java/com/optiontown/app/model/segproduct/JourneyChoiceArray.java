package com.optiontown.app.model.segproduct;

import java.io.Serializable;

/**
 * Created by amit on 21-07-2017.
 */
public class JourneyChoiceArray implements Serializable
{
    private String choiceValue;

    public String getChoiceValue() { return this.choiceValue; }

    public void setChoiceValue(String choiceValue) { this.choiceValue = choiceValue; }

    private String choicLabel;

    public String getChoicLabel() { return this.choicLabel; }

    public void setChoicLabel(String choicLabel) { this.choicLabel = choicLabel; }

    private String choiceDate;

    public String getChoiceDate() { return this.choiceDate; }

    public void setChoiceDate(String choiceDate) { this.choiceDate = choiceDate; }
}

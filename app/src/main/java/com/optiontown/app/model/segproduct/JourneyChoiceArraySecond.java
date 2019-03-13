package com.optiontown.app.model.segproduct;

import java.io.Serializable;

/**
 * Created by amit on 21-07-2017.
 */
public class JourneyChoiceArraySecond implements Serializable
{
    private String choiceValue1;

    public String getChoiceValue1() { return this.choiceValue1; }

    public void setChoiceValue1(String choiceValue1) { this.choiceValue1 = choiceValue1; }

    private String choicLabel;

    public String getChoicLabel() { return this.choicLabel; }

    public void setChoicLabel(String choicLabel) { this.choicLabel = choicLabel; }

    private String beforeTravalLabl;

    public String getBeforeTravalLabl() { return this.beforeTravalLabl; }

    public void setBeforeTravalLabl(String beforeTravalLabl) { this.beforeTravalLabl = beforeTravalLabl; }
}

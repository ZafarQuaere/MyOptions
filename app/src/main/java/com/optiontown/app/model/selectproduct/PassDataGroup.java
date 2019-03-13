package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zafar.imam on 06-04-2017.
 */

public class PassDataGroup implements Serializable{

    private ArrayList<PasTypeGroupList> PasTypeGroupList;

    public ArrayList<PasTypeGroupList> getPasTypeGroupList() { return this.PasTypeGroupList; }

    public void setPasTypeGroupList(ArrayList<PasTypeGroupList> PasTypeGroupList) { this.PasTypeGroupList = PasTypeGroupList; }

    private String pasTypeRange;

    public String getPasTypeRange() { return this.pasTypeRange; }

    public void setPasTypeRange(String pasTypeRange) { this.pasTypeRange = pasTypeRange; }

    private String PasTypeGroupName;

    public String getPasTypeGroupName() { return this.PasTypeGroupName; }

    public void setPasTypeGroupName(String PasTypeGroupName) { this.PasTypeGroupName = PasTypeGroupName; }


}

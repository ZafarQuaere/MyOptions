
package com.optiontown.app.model.redeem.mmp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MmpLabel implements Serializable{

    private int Id;

    public int getId() { return this.Id; }

    public void setId(int Id) { this.Id = Id; }

    private String ManageMypassLabelList;

    public String getManageMypassLabelList() { return this.ManageMypassLabelList; }

    public void setManageMypassLabelList(String ManageMypassLabelList) { this.ManageMypassLabelList = ManageMypassLabelList; }

}

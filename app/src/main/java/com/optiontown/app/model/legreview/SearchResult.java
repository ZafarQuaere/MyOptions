
package com.optiontown.app.model.legreview;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SearchResult {

    @SerializedName("IfsObject")
    @Expose
    private List<IfsObject> ifsObject = new ArrayList<IfsObject>();


    public List<IfsObject> getIfsObject() {
        return ifsObject;
    }


    public void setIfsObject(List<IfsObject> ifsObject) {
        this.ifsObject = ifsObject;
    }

    public String getProceed_To_Payment_Now_Label() {
        return Proceed_To_Payment_Now_Label;
    }

    public void setProceed_To_Payment_Now_Label(String proceed_To_Payment_Now_Label) {
        Proceed_To_Payment_Now_Label = proceed_To_Payment_Now_Label;
    }

    private String Proceed_To_Payment_Now_Label;

}

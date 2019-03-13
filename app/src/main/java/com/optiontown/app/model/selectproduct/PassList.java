package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;
import com.optiontown.app.model.selectproduct.PassDataNormal;

/**
 * Created by amit on 02-06-2016.
 */
public class PassList implements Serializable
{
    private String Help;

    public String getHelp() { return this.Help; }

    public void setHelp(String Help) { this.Help = Help; }


    private ArrayList<PassDataGroup> PassDataGroup;

    public ArrayList<PassDataGroup> getPassDataGroup() { return this.PassDataGroup; }

    public void setPassDataGroup(ArrayList<PassDataGroup> PassDataGroup) { this.PassDataGroup = PassDataGroup; }



    private ArrayList<PassDataNormal> PassDataNormal;

    public ArrayList<PassDataNormal> getPassDataNormal() { return this.PassDataNormal; }

    public void setPassDataNormal(ArrayList<PassDataNormal> PassDataNormal) { this.PassDataNormal = PassDataNormal; }

    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    private int SelectedValue;

    public int getSelectedValue() {
        return SelectedValue;
    }

    public void setSelectedValue(int selectedValue) {
        SelectedValue = selectedValue;
    }


}
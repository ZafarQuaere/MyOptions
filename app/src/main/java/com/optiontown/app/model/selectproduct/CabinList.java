package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class CabinList implements Serializable
{
    private ArrayList<CabinArray> CabinArray;

    public ArrayList<CabinArray> getCabinArray() { return this.CabinArray; }

    public void setCabinArray(ArrayList<CabinArray> CabinArray) { this.CabinArray = CabinArray; }

    private String Help;

    public String getHelp() { return this.Help; }

    public void setHelp(String Help) { this.Help = Help; }

    private String Title;

    public String getTitle() { return this.Title; }

    public void setTitle(String Title) { this.Title = Title; }

    private int SelectedCabinId;

    public int getSelectedCabinId() {
        return SelectedCabinId;
    }

    public void setSelectedCabinId(int selectedCabinId) {
        SelectedCabinId = selectedCabinId;
    }
}
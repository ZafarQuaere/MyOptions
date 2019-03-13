package com.optiontown.app.model.customize;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 01-03-2017.
 */
public class RestrictionDataRoot implements Serializable
{
    private int passIndex;
    private int cmmIndex;


    public int getPassIndex() {
        return passIndex;
    }

    public void setPassIndex(int passIndex) {
        this.passIndex = passIndex;
    }

    public int getCmmIndex() {
        return cmmIndex;
    }

    public void setCmmIndex(int cmmIndex) {
        this.cmmIndex = cmmIndex;
    }

    private ArrayList<ArrayList<RestrictionData>> Restriction_Details;

    public ArrayList<ArrayList<RestrictionData>> getRestrictionDetails() { return this.Restriction_Details; }

    public void setRestrictionDetails(ArrayList<ArrayList<RestrictionData>> Restriction_Details) { this.Restriction_Details = Restriction_Details; }
}

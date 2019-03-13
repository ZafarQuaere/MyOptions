package com.optiontown.app.model.redeem.mmp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 08-11-2016.
 */
public class MMPUserSelectedData implements Serializable
{
    public String getSelectedPassLabel() {
        return selectedPassLabel;
    }

    public void setSelectedPassLabel(String selectedPassLabel) {
        this.selectedPassLabel = selectedPassLabel;
    }

    private String selectedPassLabel;
    private String selectedpassid;

    public String getPassChangeType() {
        return PassChangeType;
    }

    public void setPassChangeType(String passChangeType) {
        PassChangeType = passChangeType;
    }

    private String PassChangeType;
    private String selectedZone;

    private ArrayList<String> UserDetails;

    public ArrayList<String> getUserDetails() { return this.UserDetails; }

    public void setUserDetails(ArrayList<String> UserDetails) { this.UserDetails = UserDetails; }


    public String getSelectedpassid() {
        return selectedpassid;
    }

    public void setSelectedpassid(String selectedpassid) {
        this.selectedpassid = selectedpassid;
    }



    public String getSelectedZone() {
        return selectedZone;
    }

    public void setSelectedZone(String selectedZone) {
        this.selectedZone = selectedZone;
    }


}

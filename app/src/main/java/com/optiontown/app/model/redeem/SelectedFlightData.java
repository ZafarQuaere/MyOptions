package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 13-09-2016.
 */
public class SelectedFlightData implements Serializable
{
    private ArrayList<PassDetail> PassDetails;

    public ArrayList<PassDetail> getPassDetails() { return this.PassDetails; }

    public void setPassDetails(ArrayList<PassDetail> PassDetails) { this.PassDetails = PassDetails; }
}

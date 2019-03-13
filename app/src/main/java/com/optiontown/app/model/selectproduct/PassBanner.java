package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */

public class PassBanner implements Serializable
{
    private ArrayList<PassArray> PassArray;

    public ArrayList<PassArray> getPassArray() { return this.PassArray; }

    public void setPassArray(ArrayList<PassArray> PassArray) { this.PassArray = PassArray; }

    private String Hot_Deals;

    public String getHotDeals() { return this.Hot_Deals; }

    public void setHotDeals(String Hot_Deals) { this.Hot_Deals = Hot_Deals; }

    private String Heading;

    public String getHeading() { return this.Heading; }

    public void setHeading(String Heading) { this.Heading = Heading; }
}
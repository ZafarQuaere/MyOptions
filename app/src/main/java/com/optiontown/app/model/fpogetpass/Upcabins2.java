package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 16-06-2016.
 */
public class Upcabins2 implements Serializable
{
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private ArrayList<Object> otherPriorityPrices;

    public ArrayList<Object> getOtherPriorityPrices() { return this.otherPriorityPrices; }

    public void setOtherPriorityPrices(ArrayList<Object> otherPriorityPrices) { this.otherPriorityPrices = otherPriorityPrices; }
}
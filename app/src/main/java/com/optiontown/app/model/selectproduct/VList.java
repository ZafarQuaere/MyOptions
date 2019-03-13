package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 02-06-2016.
 */
public class VList implements Serializable
{
    private String Validity;

    public String getValidity() { return this.Validity; }

    public void setValidity(String Validity) { this.Validity = Validity; }

    private int Id;

    public int getId() { return this.Id; }

    public void setId(int Id) { this.Id = Id; }
}
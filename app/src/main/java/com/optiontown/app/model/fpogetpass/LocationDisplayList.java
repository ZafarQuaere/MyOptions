package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;

/**
 * Created by amit on 16-06-2016.
 */
public class LocationDisplayList implements Serializable
{
    private String companyLocation;

    public String getCompanyLocation() { return this.companyLocation; }

    public void setCompanyLocation(String companyLocation) { this.companyLocation = companyLocation; }
}
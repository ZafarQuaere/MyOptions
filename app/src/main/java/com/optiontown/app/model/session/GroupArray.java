package com.optiontown.app.model.session;

import java.io.Serializable;

/**
 * Created by amit on 21-06-2016.
 */
public class GroupArray implements Serializable
{
    private String Name;

    public String getName() { return this.Name; }

    public void setName(String Name) { this.Name = Name; }

    private String Logo;

    public String getLogo() { return this.Logo; }

    public void setLogo(String Logo) { this.Logo = Logo; }

    private String Industry_Background;

    public String getIndustryBackground() { return this.Industry_Background; }

    public void setIndustryBackground(String Industry_Background) { this.Industry_Background = Industry_Background; }

    private int ID;

    public int getID() { return this.ID; }

    public void setID(int ID) { this.ID = ID; }
}

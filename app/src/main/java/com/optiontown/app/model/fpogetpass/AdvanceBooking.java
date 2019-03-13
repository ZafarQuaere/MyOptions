package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;

/**
 * Created by amit on 16-06-2016.
 */
public class AdvanceBooking implements Serializable
{
    private String Name;

    public String getName() { return this.Name; }

    public void setName(String Name) { this.Name = Name; }

    private String Tag;

    public String getTag() { return this.Tag; }

    public void setTag(String Tag) { this.Tag = Tag; }

    private int Value;

    public int getValue() { return this.Value; }

    public void setValue(int Value) { this.Value = Value; }

    private String Time;

    public String getTime() { return this.Time; }

    public void setTime(String Time) { this.Time = Time; }
}
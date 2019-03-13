package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;

/**
 * Created by amit on 16-06-2016.
 */
public class FlightCountList  implements Serializable
{
    private String PassValue;

    public String getPassValue() { return this.PassValue; }

    public void setPassValue(String PassValue) { this.PassValue = PassValue; }
}
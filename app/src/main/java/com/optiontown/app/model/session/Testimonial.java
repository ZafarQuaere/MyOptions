package com.optiontown.app.model.session;

import java.io.Serializable;

/**
 * Created by amit on 13-07-2016.
 */
public class Testimonial implements Serializable
{
    private String Text;

    public String getText() { return this.Text; }

    public void setText(String Text) { this.Text = Text; }

    private String Country;

    public String getCountry() { return this.Country; }

    public void setCountry(String Country) { this.Country = Country; }

    private String LastName;

    public String getLastName() { return this.LastName; }

    public void setLastName(String LastName) { this.LastName = LastName; }
}

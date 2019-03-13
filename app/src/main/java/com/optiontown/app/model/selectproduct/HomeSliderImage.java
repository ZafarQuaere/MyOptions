package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 17-07-2016.
 */
public class HomeSliderImage implements Serializable
{
    private String message;

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    private String image;

    public String getImage() { return this.image; }

    public void setImage(String image) { this.image = image; }
}

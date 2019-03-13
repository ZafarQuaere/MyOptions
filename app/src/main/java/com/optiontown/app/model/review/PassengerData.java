package com.optiontown.app.model.review;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 28-06-2016.
 */
public class PassengerData implements Serializable
{
    private int iata_Display;

    public int getIata_Display() {
        return iata_Display;
    }

    public void setIata_Display(int iata_Display) {
        this.iata_Display = iata_Display;
    }

    private String iata_Tour_Code;
    private String iata_registration;

    public String getIata_Tour_Code() {
        return iata_Tour_Code;
    }

    public void setIata_Tour_Code(String iata_Tour_Code) {
        this.iata_Tour_Code = iata_Tour_Code;
    }

    public String getIata_registration() {
        return iata_registration;
    }

    public void setIata_registration(String iata_registration) {
        this.iata_registration = iata_registration;
    }

    private ArrayList<UserDetails> userDetails;

    public ArrayList<UserDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(ArrayList<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }
}

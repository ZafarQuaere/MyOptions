package com.optiontown.app.model.redeem.mybooking;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 21/10/2016.
 */
public class Passenger implements Serializable{

    private String number_of_selected_passengers;

    public String getNumberOfSelectedPassengers() { return this.number_of_selected_passengers; }

    public void setNumberOfSelectedPassengers(String number_of_selected_passengers) { this.number_of_selected_passengers = number_of_selected_passengers; }

}

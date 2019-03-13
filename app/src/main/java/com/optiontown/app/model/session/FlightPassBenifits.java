package com.optiontown.app.model.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by parasmani.sharma on 17/08/2016.
 */
public class FlightPassBenifits implements Serializable{


    private String Heading_Flight_Pass_Benefits;

    public String getHeadingFlightPassBenefits() { return this.Heading_Flight_Pass_Benefits; }

    public void setHeadingFlightPassBenefits(String Heading_Flight_Pass_Benefits) { this.Heading_Flight_Pass_Benefits = Heading_Flight_Pass_Benefits; }

    private ArrayList<Benifit> Benifit;

    public ArrayList<Benifit> getBenifit() { return this.Benifit; }

    public void setBenifit(ArrayList<Benifit> Benifit) { this.Benifit = Benifit; }

}

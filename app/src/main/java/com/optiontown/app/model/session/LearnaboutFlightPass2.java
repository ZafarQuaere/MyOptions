package com.optiontown.app.model.session;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 17/08/2016.
 */
public class LearnaboutFlightPass2 implements Serializable{

    private String Heading_Learn_about_Flight_Pass;

    public String getHeadingLearnAboutFlightPass() { return this.Heading_Learn_about_Flight_Pass; }

    public void setHeadingLearnAboutFlightPass(String Heading_Learn_about_Flight_Pass) { this.Heading_Learn_about_Flight_Pass = Heading_Learn_about_Flight_Pass; }

    private String Video;

    public String getVideo() { return this.Video; }

    public void setVideo(String Video) { this.Video = Video; }

    private ArrayList<FlightPass> FlightPass;

    public ArrayList<FlightPass> getFlightPass() { return this.FlightPass; }

    public void setFlightPass(ArrayList<FlightPass> FlightPass) { this.FlightPass = FlightPass; }
}

package com.optiontown.app.model.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by parasmani.sharma on 17/08/2016.
 */
public class LearnAboutFlightPass implements Serializable {

    private LearnaboutFlightPass2 LearnaboutFlight_Pass;

    public LearnaboutFlightPass2 getLearnaboutFlightPass() { return this.LearnaboutFlight_Pass; }

    public void setLearnaboutFlightPass(LearnaboutFlightPass2 LearnaboutFlight_Pass) { this.LearnaboutFlight_Pass = LearnaboutFlight_Pass; }

    private ArrayList<HowToPurchase> HowToPurchase;

    public ArrayList<HowToPurchase> getHowToPurchase() { return this.HowToPurchase; }

    public void setHowToPurchase(ArrayList<HowToPurchase> HowToPurchase) { this.HowToPurchase = HowToPurchase; }

    private FlightPassBenifits Flight_Pass_Benifits;

    public FlightPassBenifits getFlightPassBenifits() { return this.Flight_Pass_Benifits; }

    public void setFlightPassBenifits(FlightPassBenifits Flight_Pass_Benifits) { this.Flight_Pass_Benifits = Flight_Pass_Benifits; }

    private FrequentlyAskedQuestions Frequently_Asked_Questions;

    public FrequentlyAskedQuestions getFrequentlyAskedQuestions() { return this.Frequently_Asked_Questions; }

    public void setFrequentlyAskedQuestions(FrequentlyAskedQuestions Frequently_Asked_Questions) { this.Frequently_Asked_Questions = Frequently_Asked_Questions; }

}

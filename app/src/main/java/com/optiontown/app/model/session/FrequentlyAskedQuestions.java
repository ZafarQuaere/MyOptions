package com.optiontown.app.model.session;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 19/08/2016.
 */
public class FrequentlyAskedQuestions implements Serializable{
    private ArrayList<FAQ> FAQs;

    public ArrayList<FAQ> getFAQs() { return this.FAQs; }

    public void setFAQs(ArrayList<FAQ> FAQs) { this.FAQs = FAQs; }

    private String Heading_Flight_Pass_Benefits;

    public String getHeadingFlightPassBenefits() { return this.Heading_Flight_Pass_Benefits; }

    public void setHeadingFlightPassBenefits(String Heading_Flight_Pass_Benefits) { this.Heading_Flight_Pass_Benefits = Heading_Flight_Pass_Benefits; }
}

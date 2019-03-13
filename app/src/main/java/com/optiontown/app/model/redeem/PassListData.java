package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 03-09-2016.
 */
public class PassListData implements Serializable
{



    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    private ArrayList<ListOfPass> listOfPasses;

    public ArrayList<ListOfPass> getListOfPasses() { return this.listOfPasses; }

    public void setListOfPasses(ArrayList<ListOfPass> listOfPasses) { this.listOfPasses = listOfPasses; }

    private ArrayList<ListOfPass> listOfInactivePasses;

    public ArrayList<ListOfPass> getListOfInactivePasses() { return this.listOfInactivePasses; }

    public void setListOfInactivePasses(ArrayList<ListOfPass> listOfInactivePasses) { this.listOfInactivePasses = listOfInactivePasses; }

    private TgpAirlineLang tgp_airline_lang;

    public TgpAirlineLang getTgpAirlineLang() { return this.tgp_airline_lang; }

    public void setTgpAirlineLang(TgpAirlineLang tgp_airline_lang) { this.tgp_airline_lang = tgp_airline_lang; }

}

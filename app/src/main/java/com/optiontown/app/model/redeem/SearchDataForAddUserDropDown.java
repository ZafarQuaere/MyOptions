package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 01/10/2016.
 */
public class SearchDataForAddUserDropDown implements Serializable {

    private ArrayList<CardTypeArray> CardTypeArray;

    public ArrayList<CardTypeArray> getCardTypeArray() { return this.CardTypeArray; }

    public void setCardTypeArray(ArrayList<CardTypeArray> CardTypeArray) { this.CardTypeArray = CardTypeArray; }


    private ArrayList<CountryListArray> CountryListArray;

    public ArrayList<CountryListArray> getCountryListArray() { return this.CountryListArray; }

    public void setCountryListArray(ArrayList<CountryListArray> CountryListArray) { this.CountryListArray = CountryListArray; }


}

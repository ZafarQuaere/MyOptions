package com.optiontown.app.model.fpotestimonial;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 28/09/2016.
 */
public class CreditCardList implements Serializable{

    private String CreditCardLabel;

    public String getCreditCardLabel() { return this.CreditCardLabel; }

    public void setCreditCardLabel(String CreditCardLabel) { this.CreditCardLabel = CreditCardLabel; }

    private String CreditCardID;

    public String getCreditCardID() { return this.CreditCardID; }

    public void setCreditCardID(String CreditCardID) { this.CreditCardID = CreditCardID; }

}

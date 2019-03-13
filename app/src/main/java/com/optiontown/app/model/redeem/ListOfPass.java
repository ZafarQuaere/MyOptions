package com.optiontown.app.model.redeem;



import com.optiontown.app.model.review.ZonefeatureData;
import com.optiontown.app.model.utosearchresult.UToPnrInformation;

import java.io.Serializable;

/**
 * Created by amit on 03-09-2016.
 */
public class ListOfPass implements Serializable
{
    private ZonefeatureData zonefeatureData;

    public ZonefeatureData getZonefeatureData() { return this.zonefeatureData; }

    public void setZonefeatureData(ZonefeatureData zonefeatureData) { this.zonefeatureData = zonefeatureData; }

    private TransactionHistory TransactionHistory;

    public TransactionHistory getTransactionHistory() { return this.TransactionHistory; }

    public void setTransactionHistory(TransactionHistory TransactionHistory) { this.TransactionHistory = TransactionHistory; }

    private PassFullView passFullView;

    public PassFullView getPassFullView() { return this.passFullView; }

    public void setPassFullView(PassFullView passFullView) { this.passFullView = passFullView; }

    private PassSmallView PassSmallView;

    public PassSmallView getPassSmallView() { return this.PassSmallView; }

    public void setPassSmallView(PassSmallView PassSmallView) { this.PassSmallView = PassSmallView; }

    public String getPass_transaction_id() {
        return pass_transaction_id;
    }

    public void setPass_transaction_id(String pass_transaction_id) {
        this.pass_transaction_id = pass_transaction_id;
    }

    private String pass_transaction_id;

    public String getPass_airline_id() {
        return pass_airline_id;
    }

    public void setPass_airline_id(String pass_airline_id) {
        this.pass_airline_id = pass_airline_id;
    }

    private String pass_airline_id;


}

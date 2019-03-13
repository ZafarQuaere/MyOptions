package com.optiontown.app.model.seatview;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 01/08/2017.
 */

public class SeatPassengerInfo implements Serializable{

    String passengerName ;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String amount ;

}

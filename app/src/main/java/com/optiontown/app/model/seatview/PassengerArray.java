package com.optiontown.app.model.seatview;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 21/08/2017.
 */

public class PassengerArray implements Serializable {

    private int paxId;

    public int getPaxId() { return this.paxId; }

    public void setPaxId(int paxId) { this.paxId = paxId; }

    private String paxFullName;

    public String getPaxFullName() { return this.paxFullName; }

    public void setPaxFullName(String paxFullName) { this.paxFullName = paxFullName; }

    public boolean isAllowedToSelectSeat() {
        return allowedToSelectSeat;
    }

    public void setAllowedToSelectSeat(boolean allowedToSelectSeat) {
        this.allowedToSelectSeat = allowedToSelectSeat;
    }

    private boolean allowedToSelectSeat;

    public boolean isSeatSelectionDone() {
        return seatSelectionDone;
    }

    public void setSeatSelectionDone(boolean seatSelectionDone) {
        this.seatSelectionDone = seatSelectionDone;
    }

    private boolean seatSelectionDone;


    /*
    * Seat summary data w.r.t this passenger
    * */

    public String getSeatSelectedNumber() {
        return seatSelectedNumber;
    }

    public void setSeatSelectedNumber(String seatSelectedNumber) {
        this.seatSelectedNumber = seatSelectedNumber;
    }

    private String seatSelectedNumber;

    public String getSeatAmount() {
        return seatAmount;
    }

    public void setSeatAmount(String seatAmount) {
        this.seatAmount = seatAmount;
    }

    private String seatAmount;






}

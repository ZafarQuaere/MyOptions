package com.optiontown.app.model.seatview;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 21/08/2017.
 */

public class SeatMarkarr implements Serializable {

    private boolean seatMarkSpace;

    public boolean getSeatMarkSpace() { return this.seatMarkSpace; }

    public void setSeatMarkSpace(boolean seatMarkSpace) { this.seatMarkSpace = seatMarkSpace; }

    private String seatMark;

    public String getSeatMark() { return this.seatMark; }

    public void setSeatMark(String seatMark) { this.seatMark = seatMark; }

}

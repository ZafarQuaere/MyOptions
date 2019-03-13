package com.optiontown.app.model.seatview;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 21/08/2017.
 */

public class Seatdetailarr implements Serializable,Comparable {

    private Integer seatShiftPrice;

    public Integer getSeatShiftPrice() { return this.seatShiftPrice; }

    public void setSeatShiftPrice(Integer seatShiftPrice) { this.seatShiftPrice = seatShiftPrice; }

    private boolean seatDetailSpace;

    public boolean getSeatDetailSpace() { return this.seatDetailSpace; }

    public void setSeatDetailSpace(boolean seatDetailSpace) { this.seatDetailSpace = seatDetailSpace; }

    private String SeatDrawbacks;

    public String getSeatDrawbacks() { return this.SeatDrawbacks; }

    public void setSeatDrawbacks(String SeatDrawbacks) { this.SeatDrawbacks = SeatDrawbacks; }

    private String SeatDesignator;

    public String getSeatDesignator() { return this.SeatDesignator; }

    public void setSeatDesignator(String SeatDesignator) { this.SeatDesignator = SeatDesignator; }

    private String frontSeatCSS;

    public String getFrontSeatCSS() { return this.frontSeatCSS; }

    public void setFrontSeatCSS(String frontSeatCSS) { this.frontSeatCSS = frontSeatCSS; }

    private String SeatImageDiscription;

    public String getSeatImageDiscription() { return this.SeatImageDiscription; }

    public void setSeatImageDiscription(String SeatImageDiscription) { this.SeatImageDiscription = SeatImageDiscription; }

    private int columnNumber;

    public int getColumnNumber() { return this.columnNumber; }

    public void setColumnNumber(int columnNumber) { this.columnNumber = columnNumber; }

    private int seatAssignable;

    public int getSeatAssignable() { return this.seatAssignable; }

    public void setSeatAssignable(int seatAssignable) { this.seatAssignable = seatAssignable; }

    private int seatAvailable;

    public int getSeatAvailable() { return this.seatAvailable; }

    public void setSeatAvailable(int seatAvailable) { this.seatAvailable = seatAvailable; }

    private String styleId2;

    public String getStyleId2() { return this.styleId2; }

    public void setStyleId2(String styleId2) { this.styleId2 = styleId2; }

    private String SeatBenefit;

    public String getSeatBenefit() { return this.SeatBenefit; }

    public void setSeatBenefit(String SeatBenefit) { this.SeatBenefit = SeatBenefit; }

    private String SeatAvailableStatus;

    public String getSeatAvailableStatus() { return this.SeatAvailableStatus; }

    public void setSeatAvailableStatus(String SeatAvailableStatus) { this.SeatAvailableStatus = SeatAvailableStatus; }

    private String SeatName;

    public String getSeatName() { return this.SeatName; }

    public void setSeatName(String SeatName) { this.SeatName = SeatName; }

    @Override
    public boolean equals(Object o) {
        if(this.columnNumber == ((Seatdetailarr)o).columnNumber)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return this.columnNumber;
    }

    @Override
    public int compareTo(@NonNull Object another) {
        if (this.columnNumber < ((Seatdetailarr)another).columnNumber){
            return -1;
        }
        else  if (this.columnNumber > ((Seatdetailarr)another).columnNumber){
            return 1;
        }
        else
        return 0;
    }
}

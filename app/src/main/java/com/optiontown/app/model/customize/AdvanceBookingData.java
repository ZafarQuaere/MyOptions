package com.optiontown.app.model.customize;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 01-08-2016.
 */
public class AdvanceBookingData implements Serializable
{
    private ArrayList<AdvanceBooking2Array> AdvanceBooking_2_Array;

    public ArrayList<AdvanceBooking2Array> getAdvanceBooking2Array() { return this.AdvanceBooking_2_Array; }

    public void setAdvanceBooking2Array(ArrayList<AdvanceBooking2Array> AdvanceBooking_2_Array) { this.AdvanceBooking_2_Array = AdvanceBooking_2_Array; }

    private ArrayList<AdvanceBooking1Array> AdvanceBooking_1_Array;

    public ArrayList<AdvanceBooking1Array> getAdvanceBooking1Array() { return this.AdvanceBooking_1_Array; }

    public void setAdvanceBooking1Array(ArrayList<AdvanceBooking1Array> AdvanceBooking_1_Array) { this.AdvanceBooking_1_Array = AdvanceBooking_1_Array; }
}

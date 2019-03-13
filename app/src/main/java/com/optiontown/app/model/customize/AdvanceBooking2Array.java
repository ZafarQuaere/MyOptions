package com.optiontown.app.model.customize;

import java.io.Serializable;

/**
 * Created by amit on 29-07-2016.
 */
public class AdvanceBooking2Array implements Serializable
{
    private String Pass_Advance_book_early_Label;

    public String getPassAdvanceBookEarlyLabel() { return this.Pass_Advance_book_early_Label; }

    public void setPassAdvanceBookEarlyLabel(String Pass_Advance_book_early_Label) { this.Pass_Advance_book_early_Label = Pass_Advance_book_early_Label; }

    private String advanceBookingChecked;

    public String getAdvanceBookingChecked() { return this.advanceBookingChecked; }

    public void setAdvanceBookingChecked(String advanceBookingChecked) { this.advanceBookingChecked = advanceBookingChecked; }

    private int advanceBooking_Id;

    public int getAdvanceBookingId() { return this.advanceBooking_Id; }

    public void setAdvanceBookingId(int advanceBooking_Id) { this.advanceBooking_Id = advanceBooking_Id; }

    private String advanceBookingSelectedValue_Label;

    public String getAdvanceBookingSelectedValueLabel() { return this.advanceBookingSelectedValue_Label; }

    public void setAdvanceBookingSelectedValueLabel(String advanceBookingSelectedValue_Label) { this.advanceBookingSelectedValue_Label = advanceBookingSelectedValue_Label; }

    private String advanceBooking_Price_Value;

    public String getAdvanceBookingPriceValue() { return this.advanceBooking_Price_Value; }

    public void setAdvanceBookingPriceValue(String advanceBooking_Price_Value) { this.advanceBooking_Price_Value = advanceBooking_Price_Value; }

    private String advanceBooking_Value;

    public String getAdvanceBookingValue() { return this.advanceBooking_Value; }

    public void setAdvanceBookingValue(String advanceBooking_Value) { this.advanceBooking_Value = advanceBooking_Value; }
}

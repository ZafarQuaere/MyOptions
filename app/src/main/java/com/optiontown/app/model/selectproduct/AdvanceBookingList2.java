package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 02-06-2016.
 */
public class AdvanceBookingList2 implements Serializable
{
    private String advanceAnyTimeLabel;

    public String getAdvanceAnyTimeLabel() { return this.advanceAnyTimeLabel; }

    public void setAdvanceAnyTimeLabel(String advanceAnyTimeLabel) { this.advanceAnyTimeLabel = advanceAnyTimeLabel; }

    private String BeforeTravelTag;

    public String getBeforeTravelTag() { return this.BeforeTravelTag; }

    public void setBeforeTravelTag(String BeforeTravelTag) { this.BeforeTravelTag = BeforeTravelTag; }

    private int AdvanceBookingId;

    public int getAdvanceBookingId() { return this.AdvanceBookingId; }

    public void setAdvanceBookingId(int AdvanceBookingId) { this.AdvanceBookingId = AdvanceBookingId; }

    private String BookingMainLabel;

    public String getBookingMainLabel() { return this.BookingMainLabel; }

    public void setBookingMainLabel(String BookingMainLabel) { this.BookingMainLabel = BookingMainLabel; }
}
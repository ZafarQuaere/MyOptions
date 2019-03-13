package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 02-06-2016.
 */
public class DefaultValues implements Serializable
{
    private String ZoneName;

    public String getZoneName() { return this.ZoneName; }

    public void setZoneName(String ZoneName) { this.ZoneName = ZoneName; }

    private long TGpFGId;

    public long getTGpFGId() { return this.TGpFGId; }

    public void setTGpFGId(long TGpFGId) { this.TGpFGId = TGpFGId; }

    private String AirlineName;

    public String getAirlineName() { return this.AirlineName; }

    public void setAirlineName(String AirlineName) { this.AirlineName = AirlineName; }

    private int FlexibilityRangeId;

    public int getFlexibilityRangeId() { return this.FlexibilityRangeId; }

    public void setFlexibilityRangeId(int FlexibilityRangeId) { this.FlexibilityRangeId = FlexibilityRangeId; }

    private int CabinId;

    public int getCabinId() { return this.CabinId; }

    public void setCabinId(int CabinId) { this.CabinId = CabinId; }

    private int ZoneId;

    public int getZoneId() { return this.ZoneId; }

    public void setZoneId(int ZoneId) { this.ZoneId = ZoneId; }

    private String ZoneSubGroupId;

    public String getZoneSubGroupId() { return this.ZoneSubGroupId; }

    public void setZoneSubGroupId(String ZoneSubGroupId) { this.ZoneSubGroupId = ZoneSubGroupId; }

    private int PassUserCountId;

    public int getPassUserCountId() { return this.PassUserCountId; }

    public void setPassUserCountId(int PassUserCountId) { this.PassUserCountId = PassUserCountId; }

    private String cabinDisplayName;

    public String getCabinDisplayName() { return this.cabinDisplayName; }

    public void setCabinDisplayName(String cabinDisplayName) { this.cabinDisplayName = cabinDisplayName; }

    private int PassCreditId;

    public int getPassCreditId() { return this.PassCreditId; }

    public void setPassCreditId(int PassCreditId) { this.PassCreditId = PassCreditId; }

    private int AdvanceBookingId;

    public int getAdvanceBookingId() { return this.AdvanceBookingId; }

    public void setAdvanceBookingId(int AdvanceBookingId) { this.AdvanceBookingId = AdvanceBookingId; }

    private int ZoneGroupId;

    public int getZoneGroupId() { return this.ZoneGroupId; }

    public void setZoneGroupId(int ZoneGroupId) { this.ZoneGroupId = ZoneGroupId; }

    private int PassUsercount;

    public int getPassUsercount() { return this.PassUsercount; }

    public void setPassUsercount(int PassUsercount) { this.PassUsercount = PassUsercount; }

    private String SelectValidityBegin;

    public String getSelectValidityBegin() {
        return SelectValidityBegin;
    }

    public void setSelectValidityBegin(String selectValidityBegin) {
        SelectValidityBegin = selectValidityBegin;
    }
    private ArrayList<RestrictedValue> Restricted_Values;

    public ArrayList<RestrictedValue> getRestrictedValues() { return this.Restricted_Values; }

    public void setRestrictedValues(ArrayList<RestrictedValue> Restricted_Values) { this.Restricted_Values = Restricted_Values; }

}

package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * used to hold flight pass deal data
 * @author amit
 */
public class FlightPassDealData implements Serializable
{
    private String SessionId;

    public String getSessionId() { return this.SessionId; }

    public void setSessionId(String SessionId) { this.SessionId = SessionId; }

    private int TGPLevel;

    public int getTGPLevel() { return this.TGPLevel; }

    public void setTGPLevel(int TGPLevel) { this.TGPLevel = TGPLevel; }

    private int TGPRef;

    public int getTGPRef() { return this.TGPRef; }

    public void setTGPRef(int TGPRef) { this.TGPRef = TGPRef; }

    private int IsSelectedAirLineId;

    public int getIsSelectedAirLineId() { return this.IsSelectedAirLineId; }

    public void setIsSelectedAirLineId(int IsSelectedAirLineId) { this.IsSelectedAirLineId = IsSelectedAirLineId; }

    private String Redeem_Book_Label;

    public String getRedeemBookLabel() { return this.Redeem_Book_Label; }

    public void setRedeemBookLabel(String Redeem_Book_Label) { this.Redeem_Book_Label = Redeem_Book_Label; }

    private String Buy_Flight_Pass_Label;

    public String getBuyFlightPassLabel() { return this.Buy_Flight_Pass_Label; }

    public void setBuyFlightPassLabel(String Buy_Flight_Pass_Label) { this.Buy_Flight_Pass_Label = Buy_Flight_Pass_Label; }

    private ArrayList<Object> OpmiBanner;

    public ArrayList<Object> getOpmiBanner() { return this.OpmiBanner; }

    public void setOpmiBanner(ArrayList<Object> OpmiBanner) { this.OpmiBanner = OpmiBanner; }

    private DefaultValues DefaultValues;

    public DefaultValues getDefaultValues() { return this.DefaultValues; }

    public void setDefaultValues(DefaultValues DefaultValues) { this.DefaultValues = DefaultValues; }

    private PassList PassList;

    public PassList getPassList() { return this.PassList; }

    public void setPassList(PassList PassList) { this.PassList = PassList; }

    private int IsPassFlexibilityDisplay;

    public int getIsPassFlexibilityDisplay() { return this.IsPassFlexibilityDisplay; }

    public void setIsPassFlexibilityDisplay(int IsPassFlexibilityDisplay) { this.IsPassFlexibilityDisplay = IsPassFlexibilityDisplay; }

    private AirLineList AirLineList;

    public AirLineList getAirLineList() { return this.AirLineList; }

    public void setAirLineList(AirLineList AirLineList) { this.AirLineList = AirLineList; }

    private int SelectMode;

    public int getSelectMode() { return this.SelectMode; }

    public void setSelectMode(int SelectMode) { this.SelectMode = SelectMode; }

    private FList FList;

    public FList getFList() { return this.FList; }

    public void setFList(FList FList) { this.FList = FList; }

    private CabinList CabinList;

    public CabinList getCabinList() { return this.CabinList; }

    public void setCabinList(CabinList CabinList) { this.CabinList = CabinList; }

    private ArrayList<Restriction> Restrictions;

    public ArrayList<Restriction> getRestrictions() { return this.Restrictions; }

    public void setRestrictions(ArrayList<Restriction> Restrictions) { this.Restrictions = Restrictions; }

    private AdvanceBookingList AdvanceBookingList;

    public AdvanceBookingList getAdvanceBookingList() { return this.AdvanceBookingList; }

    public void setAdvanceBookingList(AdvanceBookingList AdvanceBookingList) { this.AdvanceBookingList = AdvanceBookingList; }

    private ArrayList<PassBanner> PassBanner;

    public ArrayList<PassBanner> getPassBanner() { return this.PassBanner; }

    public void setPassBanner(ArrayList<PassBanner> PassBanner) { this.PassBanner = PassBanner; }

    private FlexibilitySetList FlexibilitySetList;

    public FlexibilitySetList getFlexibilitySetList() { return this.FlexibilitySetList; }

    public void setFlexibilitySetList(FlexibilitySetList FlexibilitySetList) { this.FlexibilitySetList = FlexibilitySetList; }

    private ZoneData ZoneData;

    public ZoneData getZoneData() { return this.ZoneData; }

    public void setZoneData(ZoneData ZoneData) { this.ZoneData = ZoneData; }

    private ArrayList<HomeSliderImage> HomeSliderImage;

    public ArrayList<com.optiontown.app.model.selectproduct.HomeSliderImage> getHomeSliderImage() {
        return HomeSliderImage;
    }

    public void setHomeSliderImage(ArrayList<com.optiontown.app.model.selectproduct.HomeSliderImage> homeSliderImage) {
        HomeSliderImage = homeSliderImage;
    }

    private OriginData OriginData;

    public OriginData getOriginData() { return this.OriginData; }

    public void setOriginData(OriginData OriginData) { this.OriginData = OriginData; }

    private String LABL_Personalised_Zone_Label;

    public String getLABL_Personalised_Zone_Label() {
        return LABL_Personalised_Zone_Label;
    }

    public void setLABL_Personalised_Zone_Label(String LABL_Personalised_Zone_Label) {
        this.LABL_Personalised_Zone_Label = LABL_Personalised_Zone_Label;
    }
}

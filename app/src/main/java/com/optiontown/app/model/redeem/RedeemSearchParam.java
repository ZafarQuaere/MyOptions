package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by amit on 07-09-2016.
 */
public class RedeemSearchParam implements Serializable
{
    private String selectFlexbilityHours1;

    public String getSelectFlexbilityHours1() {
        return selectFlexbilityHours1;
    }

    public void setSelectFlexbilityHours1(String selectFlexbilityHours1) {
        this.selectFlexbilityHours1 = selectFlexbilityHours1;
    }

    private String PassId;

    public String getPassId() { return this.PassId; }

    public void setPassId(String PassId) { this.PassId = PassId; }

    private String JourneyType;

    public String getJourneyType() { return this.JourneyType; }

    public void setJourneyType(String JourneyType) { this.JourneyType = JourneyType; }

    private String NumberOfPax;

    public String getNumberOfPax() { return this.NumberOfPax; }

    public void setNumberOfPax(String NumberOfPax) { this.NumberOfPax = NumberOfPax; }

    private String DepartDateJourney1;

    public String getDepartDateJourney1() { return this.DepartDateJourney1; }

    public void setDepartDateJourney1(String DepartDateJourney1) { this.DepartDateJourney1 = DepartDateJourney1; }

    private String DepartAirportJourney1;

    public String getDepartAirportJourney1() { return this.DepartAirportJourney1; }

    public void setDepartAirportJourney1(String DepartAirportJourney1) { this.DepartAirportJourney1 = DepartAirportJourney1; }

    private String ArriveAirportJourney1;

    public String getArriveAirportJourney1() { return this.ArriveAirportJourney1; }

    public void setArriveAirportJourney1(String ArriveAirportJourney1) { this.ArriveAirportJourney1 = ArriveAirportJourney1; }

    /*private String DepartAirportJourney2;

    public String getDepartAirportJourney2() {
        return DepartAirportJourney2;
    }

    public void setDepartAirportJourney2(String departAirportJourney2) {
        DepartAirportJourney2 = departAirportJourney2;
    }

    public String getArriveAirportJourney2() {
        return ArriveAirportJourney2;
    }

    public void setArriveAirportJourney2(String arriveAirportJourney2) {
        ArriveAirportJourney2 = arriveAirportJourney2;
    }

    private String ArriveAirportJourney2;*/
}

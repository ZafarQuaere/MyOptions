package com.optiontown.app.model.segproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 21-07-2017.
 */
public class SegmentArray implements Serializable
{
    private String convenienceLabl1Part2;

    public String getConvenienceLabl1Part2() {
        return convenienceLabl1Part2;
    }

    public void setConvenienceLabl1Part2(String convenienceLabl1Part2) {
        this.convenienceLabl1Part2 = convenienceLabl1Part2;
    }

    private String convenienceLablPart2;

    public String getConvenienceLablPart2() {
        return convenienceLablPart2;
    }

    public void setConvenienceLablPart2(String convenienceLablPart2) {
        this.convenienceLablPart2 = convenienceLablPart2;
    }

    private String convenienceLabl1Part1;

    public String getConvenienceLabl1Part1() {
        return convenienceLabl1Part1;
    }

    public void setConvenienceLabl1Part1(String convenienceLabl1Part1) {
        this.convenienceLabl1Part1 = convenienceLabl1Part1;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private String convenienceLablPart1;

    public String getConvenienceLablPart1() {
        return convenienceLablPart1;
    }

    public void setConvenienceLablPart1(String convenienceLablPart1) {
        this.convenienceLablPart1 = convenienceLablPart1;
    }

    private String FlightAvailabilityMsg;

    public String getFlightAvailabilityMsg() {
        return FlightAvailabilityMsg;
    }

    public void setFlightAvailabilityMsg(String flightAvailabilityMsg) {
        FlightAvailabilityMsg = flightAvailabilityMsg;
    }

    private String desiredDateLabl1;

    public String getDesiredDateLabl1() { return this.desiredDateLabl1; }

    public void setDesiredDateLabl1(String desiredDateLabl1) { this.desiredDateLabl1 = desiredDateLabl1; }

    private String someOtherDayLbl;

    public String getSomeOtherDayLbl() { return this.someOtherDayLbl; }

    public void setSomeOtherDayLbl(String someOtherDayLbl) { this.someOtherDayLbl = someOtherDayLbl;}

    private String flightDepartureName;

    public String getFlightDepartureName() { return this.flightDepartureName; }

    public void setFlightDepartureName(String flightDepartureName) { this.flightDepartureName = flightDepartureName; }

    private String journeytypeLbl;

    public String getJourneytypeLbl() {
        return journeytypeLbl;
    }

    public void setJourneytypeLbl(String journeytypeLbl) {
        this.journeytypeLbl = journeytypeLbl;
    }

    private ArrayList<JourneyChoiceArray> journeyChoiceArray;

    public ArrayList<JourneyChoiceArray> getJourneyChoiceArray() { return this.journeyChoiceArray; }

    public void setJourneyChoiceArray(ArrayList<JourneyChoiceArray> journeyChoiceArray) { this.journeyChoiceArray = journeyChoiceArray; }

    private String convenienceShortLabl1;

    public String getConvenienceShortLabl1() { return this.convenienceShortLabl1; }

    public void setConvenienceShortLabl1(String convenienceShortLabl1) { this.convenienceShortLabl1 = convenienceShortLabl1; }

    private String flightDepartureCode;

    public String getFlightDepartureCode() { return this.flightDepartureCode; }

    public void setFlightDepartureCode(String flightDepartureCode) { this.flightDepartureCode = flightDepartureCode; }

    private String flightDeparturedate;

    public String getFlightDeparturedate() { return this.flightDeparturedate; }

    public void setFlightDeparturedate(String flightDeparturedate) { this.flightDeparturedate = flightDeparturedate; }

    private int outboundStartRange;

    public int getOutboundStartRange() { return this.outboundStartRange; }

    public void setOutboundStartRange(int outboundStartRange) { this.outboundStartRange = outboundStartRange; }

    private String flightArrivalCode;

    public String getFlightArrivalCode() { return this.flightArrivalCode; }

    public void setFlightArrivalCode(String flightArrivalCode) { this.flightArrivalCode = flightArrivalCode; }

    private ArrayList<JourneyChoiceArraySecond> journeyChoiceArraySecond;

    public ArrayList<JourneyChoiceArraySecond> getJourneyChoiceArraySecond() { return this.journeyChoiceArraySecond; }

    public void setJourneyChoiceArraySecond(ArrayList<JourneyChoiceArraySecond> journeyChoiceArraySecond) { this.journeyChoiceArraySecond = journeyChoiceArraySecond; }

    private String flightArrivalName;

    public String getFlightArrivalName() { return this.flightArrivalName; }

    public void setFlightArrivalName(String flightArrivalName) { this.flightArrivalName = flightArrivalName; }

    private String someOtherDayValue;

    public String getSomeOtherDayValue() { return this.someOtherDayValue; }

    public void setSomeOtherDayValue(String someOtherDayValue) { this.someOtherDayValue = someOtherDayValue; }

    private String desiredDateLabl;

    public String getDesiredDateLabl() { return this.desiredDateLabl; }

    public void setDesiredDateLabl(String desiredDateLabl) { this.desiredDateLabl = desiredDateLabl; }

    private int outboundEndRange;

    public int getOutboundEndRange() { return this.outboundEndRange; }

    public void setOutboundEndRange(int outboundEndRange) { this.outboundEndRange = outboundEndRange; }

    private String convenienceShortLabl;

    public String getConvenienceShortLabl() { return this.convenienceShortLabl; }

    public void setConvenienceShortLabl(String convenienceShortLabl) { this.convenienceShortLabl = convenienceShortLabl; }
}

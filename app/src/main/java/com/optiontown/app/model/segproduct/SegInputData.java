package com.optiontown.app.model.segproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 21-07-2017.
 */
public class SegInputData implements Serializable
{
    private String ErrorMessage;

    public String getErrorMessage() { return this.ErrorMessage; }

    public void setErrorMessage(String errorMessage) { this.ErrorMessage = errorMessage; }

    private boolean errorExists;

    public boolean getErrorExists() { return this.errorExists; }

    public void setErrorExists(boolean errorExists) { this.errorExists = errorExists; }

    private String airlineLogo;

    public String getAirlineLogo() { return this.airlineLogo; }

    public void setAirlineLogo(String airlineLogo) { this.airlineLogo = airlineLogo; }

    private String productHeading;

    public String getProductHeading() { return this.productHeading; }

    public void setProductHeading(String productHeading) { this.productHeading = productHeading; }

    private String outboundLabel;

    public String getOutboundLabel() { return this.outboundLabel; }

    public void setOutboundLabel(String outboundLabel) { this.outboundLabel = outboundLabel; }

    private ArrayList<SegmentArray> segmentArray;

    public ArrayList<SegmentArray> getSegmentArray() { return this.segmentArray; }

    public void setSegmentArray(ArrayList<SegmentArray> segmentArray) { this.segmentArray = segmentArray; }

    private String selectFlightLabel;

    public String getSelectFlightLabel() { return this.selectFlightLabel; }

    public void setSelectFlightLabel(String selectFlightLabel) { this.selectFlightLabel = selectFlightLabel; }

    private String bothLabel;

    public String getBothLabel() { return this.bothLabel; }

    public void setBothLabel(String bothLabel) { this.bothLabel = bothLabel; }

    private String pnr;

    public String getPnr() { return this.pnr; }

    public void setPnr(String pnr) { this.pnr = pnr; }

    private String returnLabel;

    public String getReturnLabel() { return this.returnLabel; }

    public void setReturnLabel(String returnLabel) { this.returnLabel = returnLabel; }

    private int journyCount;

    public int getJournyCount() { return this.journyCount; }

    public void setJournyCount(int journyCount) { this.journyCount = journyCount; }

    private String airlineDisplayName;

    public String getAirlineDisplayName() { return this.airlineDisplayName; }

    public void setAirlineDisplayName(String airlineDisplayName) { this.airlineDisplayName = airlineDisplayName; }

    private String productTagLine;

    public String getProductTagLine() { return this.productTagLine; }

    public void setProductTagLine(String productTagLine) { this.productTagLine = productTagLine; }

    private String buttonText;

    public String getButtonText() { return this.buttonText; }

    public void setButtonText(String buttonText) { this.buttonText = buttonText; }
}

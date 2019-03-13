package com.optiontown.app.model.selectproduct;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 04-08-2016.
 */
public class OriginData implements Serializable
{
    private ArrayList<String> OriginDestinationList;

    public ArrayList<String> getOriginDestinationList() { return this.OriginDestinationList; }

    public void setOriginDestinationList(ArrayList<String> OriginDestinationList) { this.OriginDestinationList = OriginDestinationList; }

    private String Help_Label;

    public String getHelpLabel() { return this.Help_Label; }

    public void setHelpLabel(String Help_Label) { this.Help_Label = Help_Label; }

    private ArrayList<String> CitySetList;

    public ArrayList<String> getCitySetList() { return this.CitySetList; }

    public void setCitySetList(ArrayList<String> CitySetList) { this.CitySetList = CitySetList; }

    private ArrayList<String> AirportSetList;

    public ArrayList<String> getAirportSetList() { return this.AirportSetList; }

    public void setAirportSetList(ArrayList<String> AirportSetList) { this.AirportSetList = AirportSetList; }

    private ArrayList<String> StateSetList;

    public ArrayList<String> getStateSetList() { return this.StateSetList; }

    public void setStateSetList(ArrayList<String> StateSetList) { this.StateSetList = StateSetList; }

    private CZParamEntity CZParamEntity;

    public CZParamEntity getCZParamEntity() { return this.CZParamEntity; }

    public void setCZParamEntity(CZParamEntity CZParamEntity) { this.CZParamEntity = CZParamEntity; }

    private String HubAirportList;

    public String getHubAirportList() { return this.HubAirportList; }

    public void setHubAirportList(String HubAirportList) { this.HubAirportList = HubAirportList; }

    private ArrayList<String> AirportDataList;

    public ArrayList<String> getAirportDataList() { return this.AirportDataList; }

    public void setAirportDataList(ArrayList<String> AirportDataList) { this.AirportDataList = AirportDataList; }

    private ArrayList<String> CityDataList;

    public ArrayList<String> getCityDataList() { return this.CityDataList; }

    public void setCityDataList(ArrayList<String> CityDataList) { this.CityDataList = CityDataList; }

    private ArrayList<String> CountrySetList;

    public ArrayList<String> getCountrySetList() { return this.CountrySetList; }

    public void setCountrySetList(ArrayList<String> CountrySetList) { this.CountrySetList = CountrySetList; }
}

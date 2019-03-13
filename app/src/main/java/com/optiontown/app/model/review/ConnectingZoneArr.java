package com.optiontown.app.model.review;

import java.io.Serializable;

/**
 * Created by amit on 22-06-2017.
 */
public class ConnectingZoneArr implements Serializable
{
    private String connectingZoneCode;
    private String connectingZoneCityName;


    public String getConnectingZoneCode() {
        return connectingZoneCode;
    }

    public void setConnectingZoneCode(String connectingZoneCode) {
        this.connectingZoneCode = connectingZoneCode;
    }

    public String getConnectingZoneCityName() {
        return connectingZoneCityName;
    }

    public void setConnectingZoneCityName(String connectingZoneCityName) {
        this.connectingZoneCityName = connectingZoneCityName;
    }
}

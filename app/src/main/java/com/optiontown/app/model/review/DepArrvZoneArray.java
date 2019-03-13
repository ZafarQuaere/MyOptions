package com.optiontown.app.model.review;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 22-06-2017.
 */
public class DepArrvZoneArray implements Serializable
{
    private ArrayList<ConnectingZoneArr> connectingZoneArr;

    public ArrayList<ConnectingZoneArr> getConnectingZoneArr() { return this.connectingZoneArr; }

    public void setConnectingZoneArr(ArrayList<ConnectingZoneArr> connectingZoneArr) { this.connectingZoneArr = connectingZoneArr; }

    private ArrayList<DepartZoneArr> departZoneArr;

    public ArrayList<DepartZoneArr> getDepartZoneArr() { return this.departZoneArr; }

    public void setDepartZoneArr(ArrayList<DepartZoneArr> departZoneArr) { this.departZoneArr = departZoneArr; }

    private ArrayList<ArriveZoneArr> arriveZoneArr;

    public ArrayList<ArriveZoneArr> getArriveZoneArr() { return this.arriveZoneArr; }

    public void setArriveZoneArr(ArrayList<ArriveZoneArr> arriveZoneArr) { this.arriveZoneArr = arriveZoneArr; }
}

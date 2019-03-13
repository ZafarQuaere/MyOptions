package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 13/09/2016.
 */
public class SelectedDataForFlight2 implements Serializable {

    private ArrayList<ArriveAirport> ArriveAirport;

    public ArrayList<ArriveAirport> getArriveAirport() { return this.ArriveAirport; }

    public void setArriveAirport(ArrayList<ArriveAirport> ArriveAirport) { this.ArriveAirport = ArriveAirport; }

    private ArrayList<DepartAirport> DepartAirport;

    public ArrayList<DepartAirport> getDepartAirport() { return this.DepartAirport; }

    public void setDepartAirport(ArrayList<DepartAirport> DepartAirport) { this.DepartAirport = DepartAirport; }

}

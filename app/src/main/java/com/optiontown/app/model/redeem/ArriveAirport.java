package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 13/09/2016.
 */
public class ArriveAirport implements Serializable {

    private String DeptLabel;

    public String getDeptLabel() { return this.DeptLabel; }

    public void setDeptLabel(String DeptLabel) { this.DeptLabel = DeptLabel; }

    private String DeptValue;

    public String getDeptValue() { return this.DeptValue; }

    public void setDeptValue(String DeptValue) { this.DeptValue = DeptValue; }

}

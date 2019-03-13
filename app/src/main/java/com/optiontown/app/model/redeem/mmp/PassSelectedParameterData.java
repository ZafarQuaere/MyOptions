package com.optiontown.app.model.redeem.mmp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 08-11-2016.
 */
public class PassSelectedParameterData implements Serializable
{
    private String LABL_No_Active_User_Label;

    public String getLABL_No_Active_User_Label() {
        return LABL_No_Active_User_Label;
    }

    public void setLABL_No_Active_User_Label(String LABL_No_Active_User_Label) {
        this.LABL_No_Active_User_Label = LABL_No_Active_User_Label;
    }

    private String MonthsLbl;

    public String getMonthsLbl() {
        return MonthsLbl;
    }

    public void setMonthsLbl(String monthsLbl) {
        MonthsLbl = monthsLbl;
    }

    private String NewdateNow;
    private String NewdateAfter;

    private String CurrentdateNow;
    private String CurrentdateAfter;

    public String getNewdateNow() {
        return NewdateNow;
    }

    public void setNewdateNow(String newdateNow) {
        NewdateNow = newdateNow;
    }

    public String getNewdateAfter() {
        return NewdateAfter;
    }

    public void setNewdateAfter(String newdateAfter) {
        NewdateAfter = newdateAfter;
    }

    public String getCurrentdateNow() {
        return CurrentdateNow;
    }

    public void setCurrentdateNow(String currentdateNow) {
        CurrentdateNow = currentdateNow;
    }

    public String getCurrentdateAfter() {
        return CurrentdateAfter;
    }

    public void setCurrentdateAfter(String currentdateAfter) {
        CurrentdateAfter = currentdateAfter;
    }

    private ChangeParameterData mangemypassrefreshdata;

    public ChangeParameterData getMangemypassrefreshdata() {
        return mangemypassrefreshdata;
    }

    public void setMangemypassrefreshdata(ChangeParameterData mangemypassrefreshdata) {
        this.mangemypassrefreshdata = mangemypassrefreshdata;
    }

    private String Calculate_Label;

    public String getCalculateLabel() { return this.Calculate_Label; }

    public void setCalculateLabel(String Calculate_Label) { this.Calculate_Label = Calculate_Label; }

    private String Existing_Value;

    public String getExistingValue() { return this.Existing_Value; }

    public void setExistingValue(String Existing_Value) { this.Existing_Value = Existing_Value; }

    private String SelectPassChangeLabel;

    public String getSelectPassChangeLabel() { return this.SelectPassChangeLabel; }

    public void setSelectPassChangeLabel(String SelectPassChangeLabel) { this.SelectPassChangeLabel = SelectPassChangeLabel; }

    private String SelectedParameter;

    public String getSelectedParameter() {
        return SelectedParameter;
    }

    public void setSelectedParameter(String selectedParameter) {
        SelectedParameter = selectedParameter;
    }

    private ArrayList<SelectedParameterList> SelectedParameterList;

    public ArrayList<SelectedParameterList> getSelectedParameterList() { return this.SelectedParameterList; }

    public void setSelectedParameterList(ArrayList<SelectedParameterList> SelectedParameterList) { this.SelectedParameterList = SelectedParameterList; }

    private String New_Value;

    public String getNewValue() { return this.New_Value; }

    public void setNewValue(String New_Value) { this.New_Value = New_Value; }
}

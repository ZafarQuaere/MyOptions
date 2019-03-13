package com.optiontown.app.model.redeem.mmp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 05-11-2016.
 */
public class ChangeParameterData implements Serializable
{
    private String LABL_Continue_Label;

    public String getLABL_Continue_Label() {
        return LABL_Continue_Label;
    }

    public void setLABL_Continue_Label(String LABL_Continue_Label) {
        this.LABL_Continue_Label = LABL_Continue_Label;
    }

    private String LABL_FPO_Select_parameter_to_change_Label;

    public String getLABLFPOSelectParameterToChangeLabel() { return this.LABL_FPO_Select_parameter_to_change_Label; }

    public void setLABLFPOSelectParameterToChangeLabel(String LABL_FPO_Select_parameter_to_change_Label) { this.LABL_FPO_Select_parameter_to_change_Label = LABL_FPO_Select_parameter_to_change_Label; }

    private String LABL_ERR_Change_Type_Label;

    public String getLABLERRChangeTypeLabel() { return this.LABL_ERR_Change_Type_Label; }

    public void setLABLERRChangeTypeLabel(String LABL_ERR_Change_Type_Label) { this.LABL_ERR_Change_Type_Label = LABL_ERR_Change_Type_Label; }

    private String LABL_Submit_Label;

    public String getLABLSubmitLabel() { return this.LABL_Submit_Label; }

    public void setLABLSubmitLabel(String LABL_Submit_Label) { this.LABL_Submit_Label = LABL_Submit_Label; }

    private String LABL_FPO_Manage_My_Pass_Heading_Label;

    public String getLABLFPOManageMyPassHeadingLabel() { return this.LABL_FPO_Manage_My_Pass_Heading_Label; }

    public void setLABLFPOManageMyPassHeadingLabel(String LABL_FPO_Manage_My_Pass_Heading_Label) { this.LABL_FPO_Manage_My_Pass_Heading_Label = LABL_FPO_Manage_My_Pass_Heading_Label; }

    private String LABL_FPO_Select_Flight_Pass_Label;

    public String getLABLFPOSelectFlightPassLabel() { return this.LABL_FPO_Select_Flight_Pass_Label; }

    public void setLABLFPOSelectFlightPassLabel(String LABL_FPO_Select_Flight_Pass_Label) { this.LABL_FPO_Select_Flight_Pass_Label = LABL_FPO_Select_Flight_Pass_Label; }


    private ArrayList<ChangeFlightParameter> changeFlightParameter;

    public ArrayList<ChangeFlightParameter> getChangeFlightParameter() { return this.changeFlightParameter; }

    public void setChangeFlightParameter(ArrayList<ChangeFlightParameter> changeFlightParameter) { this.changeFlightParameter = changeFlightParameter; }

    private String LABL_FPO_Change_My_Flight_Pass_Label;

    public String getLABLFPOChangeMyFlightPassLabel() { return this.LABL_FPO_Change_My_Flight_Pass_Label; }

    public void setLABLFPOChangeMyFlightPassLabel(String LABL_FPO_Change_My_Flight_Pass_Label) { this.LABL_FPO_Change_My_Flight_Pass_Label = LABL_FPO_Change_My_Flight_Pass_Label; }
}

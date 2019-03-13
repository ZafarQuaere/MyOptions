package com.optiontown.app.model.seatview;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 21/08/2017.
 */

public class LegListArray implements Serializable {

    private String styleId;

    public String getStyleId() { return this.styleId; }

    public void setStyleId(String styleId) { this.styleId = styleId; }

    private String ArriveCOde;

    public String getArriveCOde() { return this.ArriveCOde; }

    public void setArriveCOde(String ArriveCOde) { this.ArriveCOde = ArriveCOde; }

    private String selectedStyleId;

    public String getSelectedStyleId() { return this.selectedStyleId; }

    public void setSelectedStyleId(String selectedStyleId) { this.selectedStyleId = selectedStyleId; }

    private String departCOde;

    public String getDepartCOde() { return this.departCOde; }

    public void setDepartCOde(String departCOde) { this.departCOde = departCOde; }

}

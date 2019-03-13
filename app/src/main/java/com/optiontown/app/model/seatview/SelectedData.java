package com.optiontown.app.model.seatview;

import java.io.Serializable;

/**
 * Created by zafar.imam on 08-09-2017.
 */

public class SelectedData implements Serializable{

   private String paxFullName;
   private String paxId;
   private String styleId2;
   private String seatDesignator;

    public String getPaxFullName() {
        return paxFullName;
    }

    public void setPaxFullName(String paxFullName) {
        this.paxFullName = paxFullName;
    }

    public String getPaxId() {
        return paxId;
    }

    public void setPaxId(String paxId) {
        this.paxId = paxId;
    }

    public String getStyleId2() {
        return styleId2;
    }

    public void setStyleId2(String styleId2) {
        this.styleId2 = styleId2;
    }

    public String getSeatDesignator() {
        return seatDesignator;
    }

    public void setSeatDesignator(String seatDesignator) {
        this.seatDesignator = seatDesignator;
    }
}

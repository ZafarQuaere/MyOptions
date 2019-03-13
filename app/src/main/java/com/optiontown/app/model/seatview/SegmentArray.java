package com.optiontown.app.model.seatview;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 21/08/2017.
 */

public class SegmentArray implements Serializable {

    private ArrayList<LegListArray> legListArray;

    public ArrayList<LegListArray> getLegListArray() { return this.legListArray; }

    public void setLegListArray(ArrayList<LegListArray> legListArray) { this.legListArray = legListArray; }

}

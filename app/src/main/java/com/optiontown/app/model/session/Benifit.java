package com.optiontown.app.model.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by parasmani.sharma on 17/08/2016.
 */
public class Benifit implements Serializable{

    private ArrayList<Object> innerList;

    public ArrayList<Object> getInnerList() { return this.innerList; }

    public void setInnerList(ArrayList<Object> innerList) { this.innerList = innerList; }

    private String title_Sub_Heading;

    public String getTitleSubHeading() { return this.title_Sub_Heading; }

    public void setTitleSubHeading(String title_Sub_Heading) { this.title_Sub_Heading = title_Sub_Heading; }

    private String image;

    public String getImage() { return this.image; }

    public void setImage(String image) { this.image = image; }

    private String titleHeading;

    public String getTitleHeading() { return this.titleHeading; }

    public void setTitleHeading(String titleHeading) { this.titleHeading = titleHeading; }

    private String title_Para2;

    public String getTitlePara2() { return this.title_Para2; }

    public void setTitlePara2(String title_Para2) { this.title_Para2 = title_Para2; }

    private String title_Para1;

    public String getTitlePara1() { return this.title_Para1; }

    public void setTitlePara1(String title_Para1) { this.title_Para1 = title_Para1; }
}

package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by parasmani.sharma on 08/02/2017.
 */

public class BlackListDates implements Serializable {

    private String blackListDays;

    public String getBlackListDays() { return this.blackListDays; }

    public void setBlackListDays(String blackListDays) { this.blackListDays = blackListDays; }

}

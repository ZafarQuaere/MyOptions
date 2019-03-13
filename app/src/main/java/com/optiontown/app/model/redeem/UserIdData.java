package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 28-09-2016.
 */
public class UserIdData implements Serializable
{
    private ArrayList<String> UserId;

    public ArrayList<String> getUserId() { return this.UserId; }

    public void setUserId(ArrayList<String> UserId) { this.UserId = UserId; }
}

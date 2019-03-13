package com.optiontown.app.model.login;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 24-06-2016.
 */
public class SelectedUserData implements Serializable
{
    private ArrayList<AddUserData> AddUserData;

    public ArrayList<AddUserData> getAddUserData() { return this.AddUserData; }

    public void setAddUserData(ArrayList<AddUserData> AddUserData) { this.AddUserData = AddUserData; }
}

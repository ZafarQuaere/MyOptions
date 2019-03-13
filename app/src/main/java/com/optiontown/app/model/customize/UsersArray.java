package com.optiontown.app.model.customize;

import java.io.Serializable;

/**
 * Created by amit on 29-07-2016.
 */
public class UsersArray implements Serializable
{
    private String UserSelected_Label;

    public String getUserSelectedLabel() { return this.UserSelected_Label; }

    public void setUserSelectedLabel(String UserSelected_Label) { this.UserSelected_Label = UserSelected_Label; }

    private String UsersShowValue;

    public String getUsersShowValue() { return this.UsersShowValue; }

    public void setUsersShowValue(String UsersShowValue) { this.UsersShowValue = UsersShowValue; }

    private String UsersShowValue_Label;

    public String getUsersShowValueLabel() { return this.UsersShowValue_Label; }

    public void setUsersShowValueLabel(String UsersShowValue_Label) { this.UsersShowValue_Label = UsersShowValue_Label; }

    private int UsersShowId;

    public int getUsersShowId() { return this.UsersShowId; }

    public void setUsersShowId(int UsersShowId) { this.UsersShowId = UsersShowId; }

    private String UserChecked;

    public String getUserChecked() { return this.UserChecked; }

    public void setUserChecked(String UserChecked) { this.UserChecked = UserChecked; }
}

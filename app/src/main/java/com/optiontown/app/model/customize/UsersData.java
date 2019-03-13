package com.optiontown.app.model.customize;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 01-08-2016.
 */
public class UsersData implements Serializable
{
    private ArrayList<UsersArray> Users_Array;

    public ArrayList<UsersArray> getUsersArray() { return this.Users_Array; }

    public void setUsersArray(ArrayList<UsersArray> Users_Array) { this.Users_Array = Users_Array; }
}

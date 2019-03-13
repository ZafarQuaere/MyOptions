package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 29-09-2016.
 */
public class ListUsersDetail implements Serializable
{
    private boolean Is_Error;
    private String Error_Message;

    public boolean is_Error() {
        return Is_Error;
    }

    public void setIs_Error(boolean is_Error) {
        Is_Error = is_Error;
    }

    public String getError_Message() {
        return Error_Message;
    }

    public void setError_Message(String error_Message) {
        Error_Message = error_Message;
    }

    private ArrayList<UsersDetail> UsersDetail;

    public ArrayList<UsersDetail> getUsersDetail() { return this.UsersDetail; }

    public void setUsersDetail(ArrayList<UsersDetail> UsersDetail) { this.UsersDetail = UsersDetail; }
}

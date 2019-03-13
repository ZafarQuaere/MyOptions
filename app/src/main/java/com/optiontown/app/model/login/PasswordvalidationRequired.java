package com.optiontown.app.model.login;

import java.io.Serializable;

/**
 * Created by amit on 09-03-2017.
 */
public class PasswordvalidationRequired implements Serializable
{
    private String PasswordHelpMsg;

    public String getPasswordHelpMsg() { return this.PasswordHelpMsg; }

    public void setPasswordHelpMsg(String PasswordHelpMsg) { this.PasswordHelpMsg = PasswordHelpMsg; }

    private String UpdateErrorHelpMsg;

    public String getUpdateErrorHelpMsg() { return this.UpdateErrorHelpMsg; }

    public void setUpdateErrorHelpMsg(String UpdateErrorHelpMsg) { this.UpdateErrorHelpMsg = UpdateErrorHelpMsg; }

    private int PasswordRequired;

    public int getPasswordRequired() { return this.PasswordRequired; }

    public void setPasswordRequired(int PasswordRequired) { this.PasswordRequired = PasswordRequired; }
}

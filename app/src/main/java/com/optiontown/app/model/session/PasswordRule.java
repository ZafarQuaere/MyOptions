package com.optiontown.app.model.session;

import java.io.Serializable;

/**
 * Created by amit on 09-03-2017.
 */
public class PasswordRule implements Serializable
{
    private String PasswordHelpMsg;

    public String getPasswordHelpMsg() { return this.PasswordHelpMsg; }

    public void setPasswordHelpMsg(String PasswordHelpMsg) { this.PasswordHelpMsg = PasswordHelpMsg; }

    private int PasswordRequired;

    public int getPasswordRequired() { return this.PasswordRequired; }

    public void setPasswordRequired(int PasswordRequired) { this.PasswordRequired = PasswordRequired; }

    private String UpdateErrorHelpMsg;

    public String getUpdateErrorHelpMsg() { return this.UpdateErrorHelpMsg; }

    public void setUpdateErrorHelpMsg(String UpdateErrorHelpMsg) { this.UpdateErrorHelpMsg = UpdateErrorHelpMsg; }
}

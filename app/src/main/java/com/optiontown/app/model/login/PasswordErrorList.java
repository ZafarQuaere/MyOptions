package com.optiontown.app.model.login;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 09-03-2017.
 */
public class PasswordErrorList implements Serializable
{
    private ArrayList<PasswordValidationData> PasswordValidationData;

    public ArrayList<PasswordValidationData> getPasswordValidationData() { return this.PasswordValidationData; }

    public void setPasswordValidationData(ArrayList<PasswordValidationData> PasswordValidationData) { this.PasswordValidationData = PasswordValidationData; }
}

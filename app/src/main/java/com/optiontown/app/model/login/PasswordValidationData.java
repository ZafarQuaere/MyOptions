package com.optiontown.app.model.login;

import java.io.Serializable;

/**
 * Created by amit on 09-03-2017.
 */
public class PasswordValidationData implements Serializable
{
    private String errorMessage;

    public String getErrorMessage() { return this.errorMessage; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}

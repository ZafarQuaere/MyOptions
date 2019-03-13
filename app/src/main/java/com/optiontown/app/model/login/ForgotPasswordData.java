package com.optiontown.app.model.login;

import java.io.Serializable;

/**
 * Created by amit on 05-07-2016.
 */
public class ForgotPasswordData implements Serializable
{
    private String message;

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    private String Result;

    public String getResult() { return this.Result; }

    public void setResult(String Result) { this.Result = Result; }
}

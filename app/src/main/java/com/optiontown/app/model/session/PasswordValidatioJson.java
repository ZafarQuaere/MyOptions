package com.optiontown.app.model.session;

import java.io.Serializable;

/**
 * Created by amit on 09-03-2017.
 */
public class PasswordValidatioJson implements Serializable
{
    private PasswordRule PasswordRule;

    public PasswordRule getPasswordRule() { return this.PasswordRule; }

    public void setPasswordRule(PasswordRule PasswordRule) { this.PasswordRule = PasswordRule; }
}

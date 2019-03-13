
package com.optiontown.app.model.redeem.mmp;

import java.io.Serializable;
import java.util.ArrayList;

import com.optiontown.app.model.login.PasswordvalidationRequired;

public class MMP implements Serializable{

    private ArrayList<MmpLabel> MmpLabels;

    public ArrayList<MmpLabel> getMmpLabels() { return this.MmpLabels; }

    public void setMmpLabels(ArrayList<MmpLabel> MmpLabels) { this.MmpLabels = MmpLabels; }

    private PasswordvalidationRequired PasswordValidationRequired;

    public PasswordvalidationRequired getPasswordValidationRequired() {
        return PasswordValidationRequired;
    }

    public void setPasswordValidationRequired(PasswordvalidationRequired passwordValidationRequired) {
        this.PasswordValidationRequired = passwordValidationRequired;
    }
}

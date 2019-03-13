package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;

/**
 * Created by amit on 16-06-2016.
 */
public class PassModel implements Serializable
{
    private PassModel2 PassModel;

    public PassModel2 getPassModel() { return this.PassModel; }

    public void setPassModel(PassModel2 PassModel) { this.PassModel = PassModel; }
}
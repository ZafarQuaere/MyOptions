package com.coupling;

/**
 * Created by zafar.imam on 26-07-2017.
 */

public class TightCouplingDemo {

    private TCDatabase TCDatabase;

    TightCouplingDemo(TCDatabase TCDatabase){
        this.TCDatabase = TCDatabase;
    }

    public void add(String customerName){
        TCDatabase.addRow("NameKey",customerName);
    }
}


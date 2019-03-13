package com.coupling;

/**
 * Created by zafar.imam on 26-07-2017.
 */

public class LooseCouplingDemo {
    private LCDatabase database;

    public LooseCouplingDemo(LCDatabase database){
        this.database = database;
    }

    public void add(String name){
        database.addRow("NameKey", name);
    }

}


package com.optiontown.app.model.review;

import java.io.Serializable;

/**
 * Created by amit on 02-08-2016.
 */
public class ResultCode implements Serializable
{
    private String Result;

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}

package com.optiontown.app.model.review;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by amit on 07-02-2017.
 */
public class FfpNumberData extends JSONObject implements Serializable
{
    private int isFFPValid;
    private String message;
    private String updatedFfp;

    public String getUpdatedFfp() {
        return updatedFfp;
    }

    public void setUpdatedFfp(String updatedFfp) {
        this.updatedFfp = updatedFfp;
    }



    public int getIsFFPValid() {
        return isFFPValid;
    }

    public void setIsFFPValid(int isFFPValid) {
        this.isFFPValid = isFFPValid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

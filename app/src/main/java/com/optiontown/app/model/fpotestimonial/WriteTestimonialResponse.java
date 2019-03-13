package com.optiontown.app.model.fpotestimonial;

/**
 * Created by parasmani.sharma on 05/08/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WriteTestimonialResponse {



    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Result")
    @Expose
    private String result;

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The Message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The result
     */
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The Result
     */
    public void setResult(String result) {
        this.result = result;
    }

}

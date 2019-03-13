
package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class QuestionAnswer {

    @SerializedName("Ans")
    @Expose
    private String ans;
    @SerializedName("Ques")
    @Expose
    private String ques;

    /**
     * 
     * @return
     *     The ans
     */
    public String getAns() {
        return ans;
    }

    /**
     * 
     * @param ans
     *     The Ans
     */
    public void setAns(String ans) {
        this.ans = ans;
    }

    /**
     * 
     * @return
     *     The ques
     */
    public String getQues() {
        return ques;
    }

    /**
     * 
     * @param ques
     *     The Ques
     */
    public void setQues(String ques) {
        this.ques = ques;
    }

}

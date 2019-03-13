
package com.optiontown.app.model.legreview;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardDetail {

    @SerializedName("cardCategoryId")
    @Expose
    private Integer cardCategoryId;
    @SerializedName("paypalHelpLabel")
    @Expose
    private String paypalHelpLabel;
    @SerializedName("CardCategoryLabel")
    @Expose
    private String cardCategoryLabel;
    @SerializedName("paypalHeading")
    @Expose
    private String paypalHeading;

    @SerializedName("paypalImage")
    @Expose
    private String paypalImage;
    @SerializedName("CardCategoryDescryption")
    @Expose
    private String CardCategoryDescryption;

    public String getCardCategoryDescryption() {
        return CardCategoryDescryption;
    }

    public void setCardCategoryDescryption(String cardCategoryDescryption) {
        CardCategoryDescryption = cardCategoryDescryption;
    }

    public String getPaypalImage() {
        return paypalImage;
    }

    public void setPaypalImage(String paypalImage) {
        this.paypalImage = paypalImage;
    }



    /**
     * 
     * @return
     *     The cardCategoryId
     */
    public Integer getCardCategoryId() {
        return cardCategoryId;
    }

    /**
     * 
     * @param cardCategoryId
     *     The cardCategoryId
     */
    public void setCardCategoryId(Integer cardCategoryId) {
        this.cardCategoryId = cardCategoryId;
    }

    /**
     * 
     * @return
     *     The paypalHelpLabel
     */
    public String getPaypalHelpLabel() {
        return paypalHelpLabel;
    }

    /**
     * 
     * @param paypalHelpLabel
     *     The paypalHelpLabel
     */
    public void setPaypalHelpLabel(String paypalHelpLabel) {
        this.paypalHelpLabel = paypalHelpLabel;
    }

    /**
     * 
     * @return
     *     The cardCategoryLabel
     */
    public String getCardCategoryLabel() {
        return cardCategoryLabel;
    }

    /**
     * 
     * @param cardCategoryLabel
     *     The CardCategoryLabel
     */
    public void setCardCategoryLabel(String cardCategoryLabel) {
        this.cardCategoryLabel = cardCategoryLabel;
    }

    /**
     * 
     * @return
     *     The paypalHeading
     */
    public String getPaypalHeading() {
        return paypalHeading;
    }

    /**
     * 
     * @param paypalHeading
     *     The paypalHeading
     */
    public void setPaypalHeading(String paypalHeading) {
        this.paypalHeading = paypalHeading;
    }

}

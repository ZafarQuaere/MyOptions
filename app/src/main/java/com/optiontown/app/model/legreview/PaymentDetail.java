
package com.optiontown.app.model.legreview;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PaymentDetail {

    @SerializedName("LABL_PayPal_Express_Checkout_Help_Label")
    @Expose
    private String lABLPayPalExpressCheckoutHelpLabel;
    @SerializedName("cardDetail")
    @Expose
    private List<CardDetail> cardDetail = new ArrayList<CardDetail>();
    @SerializedName("LABL_Card_Category_Label")
    @Expose
    private String lABLCardCategoryLabel;
    @SerializedName("LABL_PayPal_Express_Checkout_Label")
    @Expose
    private String lABLPayPalExpressCheckoutLabel;
    @SerializedName("isDisplayExpressCheckout")
    @Expose
    private Integer isDisplayExpressCheckout;


    public Integer getIsDisplayExpressCheckout() {
        return isDisplayExpressCheckout;
    }

    public void setIsDisplayExpressCheckout(Integer isDisplayExpressCheckout) {
        this.isDisplayExpressCheckout = isDisplayExpressCheckout;
    }


    /**
     * 
     * @return
     *     The lABLPayPalExpressCheckoutHelpLabel
     */
    public String getLABLPayPalExpressCheckoutHelpLabel() {
        return lABLPayPalExpressCheckoutHelpLabel;
    }

    /**
     * 
     * @param lABLPayPalExpressCheckoutHelpLabel
     *     The LABL_PayPal_Express_Checkout_Help_Label
     */
    public void setLABLPayPalExpressCheckoutHelpLabel(String lABLPayPalExpressCheckoutHelpLabel) {
        this.lABLPayPalExpressCheckoutHelpLabel = lABLPayPalExpressCheckoutHelpLabel;
    }

    /**
     * 
     * @return
     *     The cardDetail
     */
    public List<CardDetail> getCardDetail() {
        return cardDetail;
    }

    /**
     * 
     * @param cardDetail
     *     The cardDetail
     */
    public void setCardDetail(List<CardDetail> cardDetail) {
        this.cardDetail = cardDetail;
    }

    /**
     * 
     * @return
     *     The lABLCardCategoryLabel
     */
    public String getLABLCardCategoryLabel() {
        return lABLCardCategoryLabel;
    }

    /**
     * 
     * @param lABLCardCategoryLabel
     *     The LABL_Card_Category_Label
     */
    public void setLABLCardCategoryLabel(String lABLCardCategoryLabel) {
        this.lABLCardCategoryLabel = lABLCardCategoryLabel;
    }

    /**
     * 
     * @return
     *     The lABLPayPalExpressCheckoutLabel
     */
    public String getLABLPayPalExpressCheckoutLabel() {
        return lABLPayPalExpressCheckoutLabel;
    }

    /**
     * 
     * @param lABLPayPalExpressCheckoutLabel
     *     The LABL_PayPal_Express_Checkout_Label
     */
    public void setLABLPayPalExpressCheckoutLabel(String lABLPayPalExpressCheckoutLabel) {
        this.lABLPayPalExpressCheckoutLabel = lABLPayPalExpressCheckoutLabel;
    }

}

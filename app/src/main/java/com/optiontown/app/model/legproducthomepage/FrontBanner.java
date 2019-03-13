
package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FrontBanner {

    @SerializedName("Button_Icon")
    @Expose
    private String buttonIcon;
    @SerializedName("Short_Label")
    @Expose
    private String shortLabel;
    @SerializedName("Product_ID")
    @Expose
    private String productID;

    /**
     * 
     * @return
     *     The buttonIcon
     */
    public String getButtonIcon() {
        return buttonIcon;
    }

    /**
     * 
     * @param buttonIcon
     *     The Button_Icon
     */
    public void setButtonIcon(String buttonIcon) {
        this.buttonIcon = buttonIcon;
    }

    /**
     * 
     * @return
     *     The shortLabel
     */
    public String getShortLabel() {
        return shortLabel;
    }

    /**
     * 
     * @param shortLabel
     *     The Short_Label
     */
    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    /**
     * 
     * @return
     *     The productID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * 
     * @param productID
     *     The Product_ID
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

}

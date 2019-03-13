
package com.optiontown.app.model.utosearchresult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoostListWithDisplayValue {

    @SerializedName("PricesCurrencyCode")
    @Expose
    private String pricesCurrencyCode;
    @SerializedName("PricesgetPrice")
    @Expose
    private Integer pricesgetPrice;
    @SerializedName("indexString")
    @Expose
    private String indexString;
    @SerializedName("labelDisplay")
    @Expose
    private String labelDisplay;

    public String getPricesCurrencyCode() {
        return pricesCurrencyCode;
    }

    public void setPricesCurrencyCode(String pricesCurrencyCode) {
        this.pricesCurrencyCode = pricesCurrencyCode;
    }

    public Integer getPricesgetPrice() {
        return pricesgetPrice;
    }

    public void setPricesgetPrice(Integer pricesgetPrice) {
        this.pricesgetPrice = pricesgetPrice;
    }

    public String getIndexString() {
        return indexString;
    }

    public void setIndexString(String indexString) {
        this.indexString = indexString;
    }

    public String getLabelDisplay() {
        return labelDisplay;
    }

    public void setLabelDisplay(String labelDisplay) {
        this.labelDisplay = labelDisplay;
    }

}

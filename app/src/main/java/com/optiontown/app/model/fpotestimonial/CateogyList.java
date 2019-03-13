
package com.optiontown.app.model.fpotestimonial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CateogyList {

    @SerializedName("CategoryLabel")
    @Expose
    private String categoryLabel;
    @SerializedName("CategoryID")
    @Expose
    private String categoryID;

    /**
     * 
     * @return
     *     The categoryLabel
     */
    public String getCategoryLabel() {
        return categoryLabel;
    }

    /**
     * 
     * @param categoryLabel
     *     The CategoryLabel
     */
    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    /**
     * 
     * @return
     *     The categoryID
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     * 
     * @param categoryID
     *     The CategoryID
     */
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

}

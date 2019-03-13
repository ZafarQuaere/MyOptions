
package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StatusFormObject {

    @SerializedName("LABL_Last_Name_Label")
    @Expose
    private String lABLLastNameLabel;
    @SerializedName("Last_Name_Image")
    @Expose
    private String lastNameImage;


    @SerializedName("Optiontown_CN_Image")
    @Expose
    private String Optiontown_CN_Image;
    @SerializedName("LABL_Optiontown_CN_Label")
    @Expose
    private String lABLOptiontownCNLabel;

    /**
     * 
     * @return
     *     The lABLLastNameLabel
     */
    public String getLABLLastNameLabel() {
        return lABLLastNameLabel;
    }

    /**
     * 
     * @param lABLLastNameLabel
     *     The LABL_Last_Name_Label
     */
    public void setLABLLastNameLabel(String lABLLastNameLabel) {
        this.lABLLastNameLabel = lABLLastNameLabel;
    }
    public String getOptiontown_CN_Image() {
        return Optiontown_CN_Image;
    }

    public void setOptiontown_CN_Image(String optiontown_CN_Image) {
        Optiontown_CN_Image = optiontown_CN_Image;
    }

    /**
     * 
     * @return
     *     The lastNameImage
     */
    public String getLastNameImage() {
        return lastNameImage;
    }

    /**
     * 
     * @param lastNameImage
     *     The Last_Name_Image
     */
    public void setLastNameImage(String lastNameImage) {
        this.lastNameImage = lastNameImage;
    }

    /**
     * 
     * @return
     *     The lABLOptiontownCNLabel
     */
    public String getLABLOptiontownCNLabel() {
        return lABLOptiontownCNLabel;
    }

    /**
     * 
     * @param lABLOptiontownCNLabel
     *     The LABL_Optiontown_CN_Label
     */
    public void setLABLOptiontownCNLabel(String lABLOptiontownCNLabel) {
        this.lABLOptiontownCNLabel = lABLOptiontownCNLabel;
    }

}

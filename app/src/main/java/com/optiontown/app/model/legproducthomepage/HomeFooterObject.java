
package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HomeFooterObject {

    @SerializedName("buyIcon")
    @Expose
    private String buyIcon;
    @SerializedName("homeIcon_Image")
    @Expose
    private String homeIconImage;
    @SerializedName("statusIcon_Image")
    @Expose
    private String statusIconImage;
    @SerializedName("LABL_Footer_Home_Title_Label")
    @Expose
    private String lABLFooterHomeTitleLabel;
    @SerializedName("learnMoreIcon_Image")
    @Expose
    private String learnMoreIconImage;
    @SerializedName("faqsIcon_Image")
    @Expose
    private String faqsIconImage;

    /**
     * 
     * @return
     *     The buyIcon
     */
    public String getBuyIcon() {
        return buyIcon;
    }

    /**
     * 
     * @param buyIcon
     *     The buyIcon
     */
    public void setBuyIcon(String buyIcon) {
        this.buyIcon = buyIcon;
    }

    /**
     * 
     * @return
     *     The homeIconImage
     */
    public String getHomeIconImage() {
        return homeIconImage;
    }

    /**
     * 
     * @param homeIconImage
     *     The homeIcon_Image
     */
    public void setHomeIconImage(String homeIconImage) {
        this.homeIconImage = homeIconImage;
    }

    /**
     * 
     * @return
     *     The statusIconImage
     */
    public String getStatusIconImage() {
        return statusIconImage;
    }

    /**
     * 
     * @param statusIconImage
     *     The statusIcon_Image
     */
    public void setStatusIconImage(String statusIconImage) {
        this.statusIconImage = statusIconImage;
    }

    /**
     * 
     * @return
     *     The lABLFooterHomeTitleLabel
     */
    public String getLABLFooterHomeTitleLabel() {
        return lABLFooterHomeTitleLabel;
    }

    /**
     * 
     * @param lABLFooterHomeTitleLabel
     *     The LABL_Footer_Home_Title_Label
     */
    public void setLABLFooterHomeTitleLabel(String lABLFooterHomeTitleLabel) {
        this.lABLFooterHomeTitleLabel = lABLFooterHomeTitleLabel;
    }

    /**
     * 
     * @return
     *     The learnMoreIconImage
     */
    public String getLearnMoreIconImage() {
        return learnMoreIconImage;
    }

    /**
     * 
     * @param learnMoreIconImage
     *     The learnMoreIcon_Image
     */
    public void setLearnMoreIconImage(String learnMoreIconImage) {
        this.learnMoreIconImage = learnMoreIconImage;
    }

    /**
     * 
     * @return
     *     The faqsIconImage
     */
    public String getFaqsIconImage() {
        return faqsIconImage;
    }

    /**
     * 
     * @param faqsIconImage
     *     The faqsIcon_Image
     */
    public void setFaqsIconImage(String faqsIconImage) {
        this.faqsIconImage = faqsIconImage;
    }

}

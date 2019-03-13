
package com.optiontown.app.model.legproducthomepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HomeBannerObject {

    @SerializedName("buyIcon")
    @Expose
    private String buyIcon;
    @SerializedName("title_bar")
    @Expose
    private String titleBar;
    @SerializedName("LABL_Tgp_SignUp_Title_Label")
    @Expose
    private String lABLTgpSignUpTitleLabel;
    @SerializedName("LABL_Tgp_Status_Title_Label")
    @Expose
    private String lABLTgpStatusTitleLabel;
    @SerializedName("Home_Slider_Images_Path")
    @Expose
    private String homeSliderImagesPath;
    @SerializedName("statusIcon_Image")
    @Expose
    private String statusIconImage;
    @SerializedName("Home_Slider_Images_Text")
    @Expose
    private List<HomeSliderImagesText> homeSliderImagesText = new ArrayList<HomeSliderImagesText>();

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
     *     The titleBar
     */
    public String getTitleBar() {
        return titleBar;
    }

    /**
     * 
     * @param titleBar
     *     The title_bar
     */
    public void setTitleBar(String titleBar) {
        this.titleBar = titleBar;
    }

    /**
     * 
     * @return
     *     The lABLTgpSignUpTitleLabel
     */
    public String getLABLTgpSignUpTitleLabel() {
        return lABLTgpSignUpTitleLabel;
    }

    /**
     * 
     * @param lABLTgpSignUpTitleLabel
     *     The LABL_Tgp_SignUp_Title_Label
     */
    public void setLABLTgpSignUpTitleLabel(String lABLTgpSignUpTitleLabel) {
        this.lABLTgpSignUpTitleLabel = lABLTgpSignUpTitleLabel;
    }

    /**
     * 
     * @return
     *     The lABLTgpStatusTitleLabel
     */
    public String getLABLTgpStatusTitleLabel() {
        return lABLTgpStatusTitleLabel;
    }

    /**
     * 
     * @param lABLTgpStatusTitleLabel
     *     The LABL_Tgp_Status_Title_Label
     */
    public void setLABLTgpStatusTitleLabel(String lABLTgpStatusTitleLabel) {
        this.lABLTgpStatusTitleLabel = lABLTgpStatusTitleLabel;
    }

    /**
     * 
     * @return
     *     The homeSliderImagesPath
     */
    public String getHomeSliderImagesPath() {
        return homeSliderImagesPath;
    }

    /**
     * 
     * @param homeSliderImagesPath
     *     The Home_Slider_Images_Path
     */
    public void setHomeSliderImagesPath(String homeSliderImagesPath) {
        this.homeSliderImagesPath = homeSliderImagesPath;
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
     *     The homeSliderImagesText
     */
    public List<HomeSliderImagesText> getHomeSliderImagesText() {
        return homeSliderImagesText;
    }

    /**
     * 
     * @param homeSliderImagesText
     *     The Home_Slider_Images_Text
     */
    public void setHomeSliderImagesText(List<HomeSliderImagesText> homeSliderImagesText) {
        this.homeSliderImagesText = homeSliderImagesText;
    }

}

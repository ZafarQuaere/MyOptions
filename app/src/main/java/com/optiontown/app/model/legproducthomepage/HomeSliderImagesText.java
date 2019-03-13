
package com.optiontown.app.model.legproducthomepage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HomeSliderImagesText {

    @SerializedName("SliderImage")
    @Expose
    private String sliderImage;
    @SerializedName("SliderImageText")
    @Expose
    private String sliderImageText;

    /**
     * 
     * @return
     *     The sliderImage
     */
    public String getSliderImage() {
        return sliderImage;
    }

    /**
     * 
     * @param sliderImage
     *     The SliderImage
     */
    public void setSliderImage(String sliderImage) {
        this.sliderImage = sliderImage;
    }

    /**
     * 
     * @return
     *     The sliderImageText
     */
    public String getSliderImageText() {
        return sliderImageText;
    }

    /**
     * 
     * @param sliderImageText
     *     The SliderImageText
     */
    public void setSliderImageText(String sliderImageText) {
        this.sliderImageText = sliderImageText;
    }

}

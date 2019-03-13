
package com.optiontown.app.model.legproducthomepage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HomePageData {

    @SerializedName("LABL_Home_FAQs_Label")
    @Expose
    private LABLHomeFAQsLabel lABLHomeFAQsLabel;
    @SerializedName("LABL_Airline_Label")
    @Expose
    private String lABLAirlineLabel;
    @SerializedName("Status_Form_Object")
    @Expose
    private StatusFormObject statusFormObject;
    @SerializedName("Home_Banner_Object")
    @Expose
    private HomeBannerObject homeBannerObject;
    @SerializedName("Selected_Airline_id")
    @Expose
    private Integer selectedAirlineId;
    @SerializedName("Advance_Search_Form")
    @Expose
    private AdvanceSearchForm advanceSearchForm;
    @SerializedName("Airline_Drop_Down_List")
    @Expose
    private List<AirlineDropDownList> airlineDropDownList = new ArrayList<AirlineDropDownList>();
    @SerializedName("Buy_Form_Object")
    @Expose
    private BuyFormObject buyFormObject;
    @SerializedName("Home_Footer_object")
    @Expose
    private HomeFooterObject homeFooterObject;
    @SerializedName("LABL_Home_Steps_Label")
    @Expose
    private LABLHomeStepsLabel lABLHomeStepsLabel;
    @SerializedName("Home_Testimonial")
    @Expose
    private List<HomeTestimonial> homeTestimonial = new ArrayList<HomeTestimonial>();
    @SerializedName("LABL_Home_Learn_Label")
    @Expose
    private LABLHomeLearnLabel lABLHomeLearnLabel;
    @SerializedName("Airline_Image")
    @Expose
    private String airlineImage;
    @SerializedName("LABL_Home_Benifit_Label")
    @Expose
    private LABLHomeBenifitLabel lABLHomeBenifitLabel;
    @SerializedName("Arrive_Drop_Down_List")
    @Expose
    private List<ArriveDropDownList> arriveDropDownList = new ArrayList<ArriveDropDownList>();
    @SerializedName("Depart_Drop_Down_List")
    @Expose
    private List<DepartDropDownList> departDropDownList = new ArrayList<DepartDropDownList>();




    /**
     *
     * @return
     *     The arriveDropDownList
     */
    public List<ArriveDropDownList> getArriveDropDownList() {
        return arriveDropDownList;
    }

    /**
     *
     * @param arriveDropDownList
     *     The Arrive_Drop_Down_List
     */
    public void setArriveDropDownList(List<ArriveDropDownList> arriveDropDownList) {
        this.arriveDropDownList = arriveDropDownList;
    }
    /**
     *
     * @return
     *     The departDropDownList
     */
    public List<DepartDropDownList> getDepartDropDownList() {
        return departDropDownList;
    }

    /**
     *
     * @param departDropDownList
     *     The Depart_Drop_Down_List
     */
    public void setDepartDropDownList(List<DepartDropDownList> departDropDownList) {
        this.departDropDownList = departDropDownList;
    }

    public LABLMaximizeYourChanceLabel getlABLMaximizeYourChanceLabel() {
        return lABLMaximizeYourChanceLabel;
    }

    public void setlABLMaximizeYourChanceLabel(LABLMaximizeYourChanceLabel lABLMaximizeYourChanceLabel) {
        this.lABLMaximizeYourChanceLabel = lABLMaximizeYourChanceLabel;
    }

    @SerializedName("LABL_Maximize_Your_Chance_Label")
    @Expose
    private LABLMaximizeYourChanceLabel lABLMaximizeYourChanceLabel;
    /**
     *
     * @return
     *     The lABLHomeFAQsLabel
     */
    public LABLHomeFAQsLabel getLABLHomeFAQsLabel() {
        return lABLHomeFAQsLabel;
    }

    /**
     *
     * @param lABLHomeFAQsLabel
     *     The LABL_Home_FAQs_Label
     */
    public void setLABLHomeFAQsLabel(LABLHomeFAQsLabel lABLHomeFAQsLabel) {
        this.lABLHomeFAQsLabel = lABLHomeFAQsLabel;
    }

    /**
     *
     * @return
     *     The lABLAirlineLabel
     */
    public String getLABLAirlineLabel() {
        return lABLAirlineLabel;
    }

    /**
     *
     * @param lABLAirlineLabel
     *     The LABL_Airline_Label
     */
    public void setLABLAirlineLabel(String lABLAirlineLabel) {
        this.lABLAirlineLabel = lABLAirlineLabel;
    }

    /**
     *
     * @return
     *     The statusFormObject
     */
    public StatusFormObject getStatusFormObject() {
        return statusFormObject;
    }

    /**
     *
     * @param statusFormObject
     *     The Status_Form_Object
     */
    public void setStatusFormObject(StatusFormObject statusFormObject) {
        this.statusFormObject = statusFormObject;
    }

    /**
     *
     * @return
     *     The homeBannerObject
     */
    public HomeBannerObject getHomeBannerObject() {
        return homeBannerObject;
    }

    /**
     *
     * @param homeBannerObject
     *     The Home_Banner_Object
     */
    public void setHomeBannerObject(HomeBannerObject homeBannerObject) {
        this.homeBannerObject = homeBannerObject;
    }

    /**
     *
     * @return
     *     The selectedAirlineId
     */
    public Integer getSelectedAirlineId() {
        return selectedAirlineId;
    }

    /**
     *
     * @param selectedAirlineId
     *     The Selected_Airline_id
     */
    public void setSelectedAirlineId(Integer selectedAirlineId) {
        this.selectedAirlineId = selectedAirlineId;
    }

    /**
     *
     * @return
     *     The advanceSearchForm
     */
    public AdvanceSearchForm getAdvanceSearchForm() {
        return advanceSearchForm;
    }

    /**
     *
     * @param advanceSearchForm
     *     The Advance_Search_Form
     */
    public void setAdvanceSearchForm(AdvanceSearchForm advanceSearchForm) {
        this.advanceSearchForm = advanceSearchForm;
    }

    /**
     *
     * @return
     *     The airlineDropDownList
     */
    public List<AirlineDropDownList> getAirlineDropDownList() {
        return airlineDropDownList;
    }

    /**
     *
     * @param airlineDropDownList
     *     The Airline_Drop_Down_List
     */
    public void setAirlineDropDownList(List<AirlineDropDownList> airlineDropDownList) {
        this.airlineDropDownList = airlineDropDownList;
    }

    /**
     *
     * @return
     *     The buyFormObject
     */
    public BuyFormObject getBuyFormObject() {
        return buyFormObject;
    }

    /**
     *
     * @param buyFormObject
     *     The Buy_Form_Object
     */
    public void setBuyFormObject(BuyFormObject buyFormObject) {
        this.buyFormObject = buyFormObject;
    }

    /**
     *
     * @return
     *     The homeFooterObject
     */
    public HomeFooterObject getHomeFooterObject() {
        return homeFooterObject;
    }

    /**
     *
     * @param homeFooterObject
     *     The Home_Footer_object
     */
    public void setHomeFooterObject(HomeFooterObject homeFooterObject) {
        this.homeFooterObject = homeFooterObject;
    }

    /**
     *
     * @return
     *     The lABLHomeStepsLabel
     */
    public LABLHomeStepsLabel getLABLHomeStepsLabel() {
        return lABLHomeStepsLabel;
    }

    /**
     *
     * @param lABLHomeStepsLabel
     *     The LABL_Home_Steps_Label
     */
    public void setLABLHomeStepsLabel(LABLHomeStepsLabel lABLHomeStepsLabel) {
        this.lABLHomeStepsLabel = lABLHomeStepsLabel;
    }

    /**
     *
     * @return
     *     The homeTestimonial
     */
    public List<HomeTestimonial> getHomeTestimonial() {
        return homeTestimonial;
    }

    /**
     *
     * @param homeTestimonial
     *     The Home_Testimonial
     */
    public void setHomeTestimonial(List<HomeTestimonial> homeTestimonial) {
        this.homeTestimonial = homeTestimonial;
    }

    /**
     *
     * @return
     *     The lABLHomeLearnLabel
     */
    public LABLHomeLearnLabel getLABLHomeLearnLabel() {
        return lABLHomeLearnLabel;
    }

    /**
     *
     * @param lABLHomeLearnLabel
     *     The LABL_Home_Learn_Label
     */
    public void setLABLHomeLearnLabel(LABLHomeLearnLabel lABLHomeLearnLabel) {
        this.lABLHomeLearnLabel = lABLHomeLearnLabel;
    }

    /**
     *
     * @return
     *     The airlineImage
     */
    public String getAirlineImage() {
        return airlineImage;
    }

    /**
     *
     * @param airlineImage
     *     The Airline_Image
     */
    public void setAirlineImage(String airlineImage) {
        this.airlineImage = airlineImage;
    }

    /**
     *
     * @return
     *     The lABLHomeBenifitLabel
     */
    public LABLHomeBenifitLabel getLABLHomeBenifitLabel() {
        return lABLHomeBenifitLabel;
    }

    /**
     *
     * @param lABLHomeBenifitLabel
     *     The LABL_Home_Benifit_Label
     */
    public void setLABLHomeBenifitLabel(LABLHomeBenifitLabel lABLHomeBenifitLabel) {
        this.lABLHomeBenifitLabel = lABLHomeBenifitLabel;
    }

}

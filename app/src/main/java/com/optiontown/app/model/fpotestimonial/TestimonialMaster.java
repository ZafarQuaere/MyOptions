package com.optiontown.app.model.fpotestimonial;

/**
 * Created by parasmani.sharma on 03/08/2016.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TestimonialMaster implements Serializable {

    private String Name_Label;

    public String getNameLabel() { return this.Name_Label; }

    public void setNameLabel(String Name_Label) { this.Name_Label = Name_Label; }

    private String Search_Booking_Flight1_Label;

    public String getSearchBookingFlight1Label() { return this.Search_Booking_Flight1_Label; }

    public void setSearchBookingFlight1Label(String Search_Booking_Flight1_Label) { this.Search_Booking_Flight1_Label = Search_Booking_Flight1_Label; }

    private String Search_Booking_Flight2_Big_Label;

    public String getSearchBookingFlight2BigLabel() { return this.Search_Booking_Flight2_Big_Label; }

    public void setSearchBookingFlight2BigLabel(String Search_Booking_Flight2_Big_Label) { this.Search_Booking_Flight2_Big_Label = Search_Booking_Flight2_Big_Label; }

    private ArrayList<TelephoneCodeList> Telephone_Code_List;

    public ArrayList<TelephoneCodeList> getTelephoneCodeList() { return this.Telephone_Code_List; }

    public void setTelephoneCodeList(ArrayList<TelephoneCodeList> Telephone_Code_List) { this.Telephone_Code_List = Telephone_Code_List; }

    private String Last_Name_Label;

    public String getLastNameLabel() { return this.Last_Name_Label; }

    public void setLastNameLabel(String Last_Name_Label) { this.Last_Name_Label = Last_Name_Label; }

    private ArrayList<CountryCodeList> Country_Code_List;

    public ArrayList<CountryCodeList> getCountryCodeList() { return this.Country_Code_List; }

    public void setCountryCodeList(ArrayList<CountryCodeList> Country_Code_List) { this.Country_Code_List = Country_Code_List; }

    private String FeedBack_Desc_Label;

    public String getFeedBackDescLabel() { return this.FeedBack_Desc_Label; }

    public void setFeedBackDescLabel(String FeedBack_Desc_Label) { this.FeedBack_Desc_Label = FeedBack_Desc_Label; }

    private String Email_Us_Label;

    public String getEmailUsLabel() { return this.Email_Us_Label; }

    public void setEmailUsLabel(String Email_Us_Label) { this.Email_Us_Label = Email_Us_Label; }

    private String Error_Mandatory_Field;

    public String getErrorMandatoryField() { return this.Error_Mandatory_Field; }

    public void setErrorMandatoryField(String Error_Mandatory_Field) { this.Error_Mandatory_Field = Error_Mandatory_Field; }

    private String CombineMultiple_Booking_Label;

    public String getCombineMultipleBookingLabel() { return this.CombineMultiple_Booking_Label; }

    public void setCombineMultipleBookingLabel(String CombineMultiple_Booking_Label) { this.CombineMultiple_Booking_Label = CombineMultiple_Booking_Label; }

    private String Address_Heading_Label;

    public String getAddressHeadingLabel() { return this.Address_Heading_Label; }

    public void setAddressHeadingLabel(String Address_Heading_Label) { this.Address_Heading_Label = Address_Heading_Label; }

    private ArrayList<PrefixList> Prefix_List;

    public ArrayList<PrefixList> getPrefixList() { return this.Prefix_List; }

    public void setPrefixList(ArrayList<PrefixList> Prefix_List) { this.Prefix_List = Prefix_List; }

    private String Mandatory_Field_Label;

    public String getMandatoryFieldLabel() { return this.Mandatory_Field_Label; }

    public void setMandatoryFieldLabel(String Mandatory_Field_Label) { this.Mandatory_Field_Label = Mandatory_Field_Label; }

    private String Prefix_Label;

    public String getPrefixLabel() { return this.Prefix_Label; }

    public void setPrefixLabel(String Prefix_Label) { this.Prefix_Label = Prefix_Label; }

    private ArrayList<AirlineList> Airline_List;

    public ArrayList<AirlineList> getAirlineList() { return this.Airline_List; }

    public void setAirlineList(ArrayList<AirlineList> Airline_List) { this.Airline_List = Airline_List; }

    private String Address_Label;

    public String getAddressLabel() { return this.Address_Label; }

    public void setAddressLabel(String Address_Label) { this.Address_Label = Address_Label; }

    private String Booking_Information_Label;

    public String getBookingInformationLabel() { return this.Booking_Information_Label; }

    public void setBookingInformationLabel(String Booking_Information_Label) { this.Booking_Information_Label = Booking_Information_Label; }

    private String Option_Label;

    public String getOptionLabel() { return this.Option_Label; }

    public void setOptionLabel(String Option_Label) { this.Option_Label = Option_Label; }

    private String Edit_Contact_Details_Label;

    public String getEditContactDetailsLabel() { return this.Edit_Contact_Details_Label; }

    public void setEditContactDetailsLabel(String Edit_Contact_Details_Label) { this.Edit_Contact_Details_Label = Edit_Contact_Details_Label; }

    private String Infprmation_Label;

    public String getInfprmationLabel() { return this.Infprmation_Label; }

    public void setInfprmationLabel(String Infprmation_Label) { this.Infprmation_Label = Infprmation_Label; }

    private String Heading_Label;

    public String getHeadingLabel() { return this.Heading_Label; }

    public void setHeadingLabel(String Heading_Label) { this.Heading_Label = Heading_Label; }

    private String Email1_Label;

    public String getEmail1Label() { return this.Email1_Label; }

    public void setEmail1Label(String Email1_Label) { this.Email1_Label = Email1_Label; }

    private String Airline_Label;

    public String getAirlineLabel() { return this.Airline_Label; }

    public void setAirlineLabel(String Airline_Label) { this.Airline_Label = Airline_Label; }

    private String CombineMultiple_Booking_Desc_Label;

    public String getCombineMultipleBookingDescLabel() { return this.CombineMultiple_Booking_Desc_Label; }

    public void setCombineMultipleBookingDescLabel(String CombineMultiple_Booking_Desc_Label) { this.CombineMultiple_Booking_Desc_Label = CombineMultiple_Booking_Desc_Label; }

    private String Contact_Information_Label;

    public String getContactInformationLabel() { return this.Contact_Information_Label; }

    public void setContactInformationLabel(String Contact_Information_Label) { this.Contact_Information_Label = Contact_Information_Label; }

    private String PNR_New_Label;

    public String getPNRNewLabel() { return this.PNR_New_Label; }

    public void setPNRNewLabel(String PNR_New_Label) { this.PNR_New_Label = PNR_New_Label; }

    private String OptiobTownAccount_Label;

    public String getOptiobTownAccountLabel() { return this.OptiobTownAccount_Label; }

    public void setOptiobTownAccountLabel(String OptiobTownAccount_Label) { this.OptiobTownAccount_Label = OptiobTownAccount_Label; }

    private String Country_Label;

    public String getCountryLabel() { return this.Country_Label; }

    public void setCountryLabel(String Country_Label) { this.Country_Label = Country_Label; }

    private String OptiobTownAccount_Desc_Label;

    public String getOptiobTownAccountDescLabel() { return this.OptiobTownAccount_Desc_Label; }

    public void setOptiobTownAccountDescLabel(String OptiobTownAccount_Desc_Label) { this.OptiobTownAccount_Desc_Label = OptiobTownAccount_Desc_Label; }

    private String ForgotUsername_And_Password_Label;

    public String getForgotUsernameAndPasswordLabel() { return this.ForgotUsername_And_Password_Label; }

    public void setForgotUsernameAndPasswordLabel(String ForgotUsername_And_Password_Label) { this.ForgotUsername_And_Password_Label = ForgotUsername_And_Password_Label; }

    private String Refund_Status_Desc_Label;

    public String getRefundStatusDescLabel() { return this.Refund_Status_Desc_Label; }

    public void setRefundStatusDescLabel(String Refund_Status_Desc_Label) { this.Refund_Status_Desc_Label = Refund_Status_Desc_Label; }

    private String Cell_Label;

    public String getCellLabel() { return this.Cell_Label; }

    public void setCellLabel(String Cell_Label) { this.Cell_Label = Cell_Label; }

    private String Telephone_Number_Label;

    public String getTelephoneNumberLabel() { return this.Telephone_Number_Label; }

    public void setTelephoneNumberLabel(String Telephone_Number_Label) { this.Telephone_Number_Label = Telephone_Number_Label; }

    private String Join_Buzz_Label;

    public String getJoinBuzzLabel() { return this.Join_Buzz_Label; }

    public void setJoinBuzzLabel(String Join_Buzz_Label) { this.Join_Buzz_Label = Join_Buzz_Label; }

    private ArrayList<CreditCardList> CreditCard_List;

    public ArrayList<CreditCardList> getCreditCardList() { return this.CreditCard_List; }

    public void setCreditCardList(ArrayList<CreditCardList> CreditCard_List) { this.CreditCard_List = CreditCard_List; }

    private String First_Name_Label;

    public String getFirstNameLabel() { return this.First_Name_Label; }

    public void setFirstNameLabel(String First_Name_Label) { this.First_Name_Label = First_Name_Label; }

    private String Payment_Failed_Desc_Label;

    public String getPaymentFailedDescLabel() { return this.Payment_Failed_Desc_Label; }

    public void setPaymentFailedDescLabel(String Payment_Failed_Desc_Label) { this.Payment_Failed_Desc_Label = Payment_Failed_Desc_Label; }

    private ArrayList<OptionList> Option_List;

    public ArrayList<OptionList> getOptionList() { return this.Option_List; }

    public void setOptionList(ArrayList<OptionList> Option_List) { this.Option_List = Option_List; }

    private ArrayList<CateogyList> Cateogy_List;

    public ArrayList<CateogyList> getCateogyList() { return this.Cateogy_List; }

    public void setCateogyList(ArrayList<CateogyList> Cateogy_List) { this.Cateogy_List = Cateogy_List; }

    private String Category_Label;

    public String getCategoryLabel() { return this.Category_Label; }

    public void setCategoryLabel(String Category_Label) { this.Category_Label = Category_Label; }

    private String Chat_Heading_Label;

    public String getChatHeadingLabel() { return this.Chat_Heading_Label; }

    public void setChatHeadingLabel(String Chat_Heading_Label) { this.Chat_Heading_Label = Chat_Heading_Label; }


    private String airlineid;

    public String getLsproductid() {
        return lsproductid;
    }

    public void setLsproductid(String lsproductid) {
        this.lsproductid = lsproductid;
    }

    public String getAirlineid() {
        return airlineid;
    }

    public void setAirlineid(String airlineid) {
        this.airlineid = airlineid;
    }

    private String lsproductid;
}




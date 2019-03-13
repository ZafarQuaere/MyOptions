package com.optiontown.app.model.utosearchresult;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by parasmani.sharma on 09/05/2017.
 */

public class UToPnrInformation implements Serializable {


    private ArrayList<PnrList> PnrList;

    public ArrayList<PnrList> getPnrList() { return this.PnrList; }

    public void setPnrList(ArrayList<PnrList> PnrList) { this.PnrList = PnrList; }

    private String Book_Eso_On_New_Booking;

    public String getBookEsoOnNewBooking() { return this.Book_Eso_On_New_Booking; }

    public void setBookEsoOnNewBooking(String Book_Eso_On_New_Booking) { this.Book_Eso_On_New_Booking = Book_Eso_On_New_Booking; }

    private ArrayList<PassDropDownList> passDropDownList;

    public ArrayList<PassDropDownList> getPassDropDownList() { return this.passDropDownList; }

    public void setPassDropDownList(ArrayList<PassDropDownList> passDropDownList) { this.passDropDownList = passDropDownList; }

    private String Err_User_Add_Lbl;

    public String getErrUserAddLbl() { return this.Err_User_Add_Lbl; }

    public void setErrUserAddLbl(String Err_User_Add_Lbl) { this.Err_User_Add_Lbl = Err_User_Add_Lbl; }

    private String Show_Redeem_Button;

    public String getShowRedeemButton() { return this.Show_Redeem_Button; }

    public void setShowRedeemButton(String Show_Redeem_Button) { this.Show_Redeem_Button = Show_Redeem_Button; }

    private String Book_Eso_On_Existing_Booking;

    public String getBookEsoOnExistingBooking() { return this.Book_Eso_On_Existing_Booking; }

    public void setBookEsoOnExistingBooking(String Book_Eso_On_Existing_Booking) { this.Book_Eso_On_Existing_Booking = Book_Eso_On_Existing_Booking; }




}

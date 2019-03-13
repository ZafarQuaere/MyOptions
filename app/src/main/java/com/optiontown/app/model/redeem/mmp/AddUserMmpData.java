package com.optiontown.app.model.redeem.mmp;

import com.optiontown.app.model.redeem.CardTypeArray;

import java.io.Serializable;
import java.util.ArrayList;
import com.optiontown.app.model.redeem.CardTypeArray;
import com.optiontown.app.model.redeem.CountryListArray;
import com.optiontown.app.model.redeem.PhoneExtArray;
import com.optiontown.app.model.redeem.PrefixArray;

/**
 * Created by parasmani.sharma on 07/11/2016.
 */
public class AddUserMmpData implements Serializable{


    private String FFpnumberErrorMessage;

    public String getFFpnumberErrorMessage() { return this.FFpnumberErrorMessage; }

    public void setFFpnumberErrorMessage(String FFpnumberErrorMessage) { this.FFpnumberErrorMessage = FFpnumberErrorMessage; }

    private ArrayList<CardTypeArray> CardTypeArray;

    public ArrayList<CardTypeArray> getCardTypeArray() { return this.CardTypeArray; }

    public void setCardTypeArray(ArrayList<CardTypeArray> CardTypeArray) { this.CardTypeArray = CardTypeArray; }

    private int FFPNumberMandatory;

    public int getFFPNumberMandatory() { return this.FFPNumberMandatory; }

    public void setFFPNumberMandatory(int FFPNumberMandatory) { this.FFPNumberMandatory = FFPNumberMandatory; }

    private ArrayList<String> UserList;

    public ArrayList<String> getUserList() { return this.UserList; }

    public void setUserList(ArrayList<String> UserList) { this.UserList = UserList; }

    private String Users_Label;

    public String getUsersLabel() { return this.Users_Label; }

    public void setUsersLabel(String Users_Label) { this.Users_Label = Users_Label; }

    private String AddUser_Label;

    public String getAddUserLabel() { return this.AddUser_Label; }

    public void setAddUserLabel(String AddUser_Label) { this.AddUser_Label = AddUser_Label; }

    private ArrayList<PhoneExtArray> PhoneExtArray;

    public ArrayList<PhoneExtArray> getPhoneExtArray() { return this.PhoneExtArray; }

    public void setPhoneExtArray(ArrayList<PhoneExtArray> PhoneExtArray) { this.PhoneExtArray = PhoneExtArray; }

    private String FFpnumberSortDesc;

    public String getFFpnumberSortDesc() { return this.FFpnumberSortDesc; }

    public void setFFpnumberSortDesc(String FFpnumberSortDesc) { this.FFpnumberSortDesc = FFpnumberSortDesc; }

    private ArrayList<PrefixArray> PrefixArray;

    public ArrayList<PrefixArray> getPrefixArray() { return this.PrefixArray; }

    public void setPrefixArray(ArrayList<PrefixArray> PrefixArray) { this.PrefixArray = PrefixArray; }

    private int MaxUserCount;

    public int getMaxUserCount() { return this.MaxUserCount; }

    public void setMaxUserCount(int MaxUserCount) { this.MaxUserCount = MaxUserCount; }

    private String FFpnumberHelpMessage;

    public String getFFpnumberHelpMessage() { return this.FFpnumberHelpMessage; }

    public void setFFpnumberHelpMessage(String FFpnumberHelpMessage) { this.FFpnumberHelpMessage = FFpnumberHelpMessage; }

    private int isDisplayFFPNumber;

    public int getIsDisplayFFPNumber() { return this.isDisplayFFPNumber; }

    public void setIsDisplayFFPNumber(int isDisplayFFPNumber) { this.isDisplayFFPNumber = isDisplayFFPNumber; }

    private ArrayList<CountryListArray> CountryListArray;

    public ArrayList<CountryListArray> getCountryListArray() { return this.CountryListArray; }

    public void setCountryListArray(ArrayList<CountryListArray> CountryListArray) { this.CountryListArray = CountryListArray; }



}

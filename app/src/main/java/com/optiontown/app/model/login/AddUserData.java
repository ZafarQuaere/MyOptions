package com.optiontown.app.model.login;

import java.io.Serializable;

/**
 * Created by amit on 24-06-2016.
 */
public class AddUserData implements Serializable
{
    private String FFPNumber;

    public String getFFPNumber() { return this.FFPNumber; }

    public void setFFPNumber(String FFPNumber) { this.FFPNumber = FFPNumber; }

    private int UserType;

    public int getUserType() { return this.UserType; }

    public void setUserType(int UserType) { this.UserType = UserType; }

    private String MiddleName;

    public String getMiddleName() { return this.MiddleName; }

    public void setMiddleName(String MiddleName) { this.MiddleName = MiddleName; }

    private String Email;

    public String getEmail() { return this.Email; }

    public void setEmail(String Email) { this.Email = Email; }

    private String DOB;

    public String getDOB() { return this.DOB; }

    public void setDOB(String DOB) { this.DOB = DOB; }

    private String Lastname;

    public String getLastname() { return this.Lastname; }

    public void setLastname(String Lastname) { this.Lastname = Lastname; }

    private String FirstName;

    public String getFirstName() { return this.FirstName; }

    public void setFirstName(String FirstName) { this.FirstName = FirstName; }

    private int UserNumber;

    public int getUserNumber() { return this.UserNumber; }

    public void setUserNumber(int UserNumber) { this.UserNumber = UserNumber; }

    private String PrimaryPhExt;

    public String getPrimaryPhExt() {
        return PrimaryPhExt;
    }

    public void setPrimaryPhExt(String primaryPhExt) {
        PrimaryPhExt = primaryPhExt;
    }

    private String PrimaryPhNO;

    public String getPrimaryPhNO() { return this.PrimaryPhNO; }

    public void setPrimaryPhNO(String PrimaryPhNO) { this.PrimaryPhNO = PrimaryPhNO; }

    private int SelectUserId;

    public int getSelectUserId() { return this.SelectUserId; }

    public void setSelectUserId(int SelectUserId) { this.SelectUserId = SelectUserId; }

    private int PrefixID;

    public int getPrefixID() { return this.PrefixID; }

    public void setPrefixID(int PrefixID) { this.PrefixID = PrefixID; }
}

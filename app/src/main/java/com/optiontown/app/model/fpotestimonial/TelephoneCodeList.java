
package com.optiontown.app.model.fpotestimonial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TelephoneCodeList {

    @SerializedName("TelephoneCountryLabel")
    @Expose
    private String telephoneCountryLabel;
    @SerializedName("TelephoneCountryID")
    @Expose
    private String telephoneCountryID;

    /**
     * 
     * @return
     *     The telephoneCountryLabel
     */
    public String getTelephoneCountryLabel() {
        return telephoneCountryLabel;
    }

    /**
     * 
     * @param telephoneCountryLabel
     *     The TelephoneCountryLabel
     */
    public void setTelephoneCountryLabel(String telephoneCountryLabel) {
        this.telephoneCountryLabel = telephoneCountryLabel;
    }

    /**
     * 
     * @return
     *     The telephoneCountryID
     */
    public String getTelephoneCountryID() {
        return telephoneCountryID;
    }

    /**
     * 
     * @param telephoneCountryID
     *     The TelephoneCountryID
     */
    public void setTelephoneCountryID(String telephoneCountryID) {
        this.telephoneCountryID = telephoneCountryID;
    }

}

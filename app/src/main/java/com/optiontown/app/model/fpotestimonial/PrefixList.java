
package com.optiontown.app.model.fpotestimonial;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrefixList {

    @SerializedName("OptionLabel")
    @Expose
    private String optionLabel;
    @SerializedName("PrefixID")
    @Expose
    private String prefixID;
    @SerializedName("P 08-03 00:20:16.437 1475-1475/com.optiontown D/OT >>: OT >> refixID")
    @Expose
    private String p080300201643714751475ComOptiontownDOTOTRefixID;

    /**
     * 
     * @return
     *     The optionLabel
     */
    public String getOptionLabel() {
        return optionLabel;
    }

    /**
     * 
     * @param optionLabel
     *     The OptionLabel
     */
    public void setOptionLabel(String optionLabel) {
        this.optionLabel = optionLabel;
    }

    /**
     * 
     * @return
     *     The prefixID
     */
    public String getPrefixID() {
        return prefixID;
    }

    /**
     * 
     * @param prefixID
     *     The PrefixID
     */
    public void setPrefixID(String prefixID) {
        this.prefixID = prefixID;
    }

    /**
     * 
     * @return
     *     The p080300201643714751475ComOptiontownDOTOTRefixID
     */
    public String getP080300201643714751475ComOptiontownDOTOTRefixID() {
        return p080300201643714751475ComOptiontownDOTOTRefixID;
    }

    /**
     * 
     * @param p080300201643714751475ComOptiontownDOTOTRefixID
     *     The P 08-03 00:20:16.437 1475-1475/com.optiontown D/OT >>: OT >> refixID
     */
    public void setP080300201643714751475ComOptiontownDOTOTRefixID(String p080300201643714751475ComOptiontownDOTOTRefixID) {
        this.p080300201643714751475ComOptiontownDOTOTRefixID = p080300201643714751475ComOptiontownDOTOTRefixID;
    }

}

package com.optiontown.app.model.redeem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amit on 03-09-2016.
 */
public class TransactionHistory implements Serializable
{


    private ArrayList<Transdetail> transdetail;

    public ArrayList<Transdetail> getTransdetail() { return this.transdetail; }

    public void setTransdetail(ArrayList<Transdetail> transdetail) { this.transdetail = transdetail; }

    private String LABL_Transaction_Date_Label;

    public String getLABLTransactionDateLabel() { return this.LABL_Transaction_Date_Label; }

    public void setLABLTransactionDateLabel(String LABL_Transaction_Date_Label) { this.LABL_Transaction_Date_Label = LABL_Transaction_Date_Label; }

    private String Total_Amount_Label;

    public String getTotalAmountLabel() { return this.Total_Amount_Label; }

    public void setTotalAmountLabel(String Total_Amount_Label) { this.Total_Amount_Label = Total_Amount_Label; }

    private String Trans_History_Event_Label;

    public String getTransHistoryEventLabel() { return this.Trans_History_Event_Label; }

    public void setTransHistoryEventLabel(String Trans_History_Event_Label) { this.Trans_History_Event_Label = Trans_History_Event_Label; }

    private String LABL_Tansaction_History_Account_Heading_Label;

    public String getLABLTansactionHistoryAccountHeadingLabel() { return this.LABL_Tansaction_History_Account_Heading_Label; }

    public void setLABLTansactionHistoryAccountHeadingLabel(String LABL_Tansaction_History_Account_Heading_Label) { this.LABL_Tansaction_History_Account_Heading_Label = LABL_Tansaction_History_Account_Heading_Label; }

    private String LABL_Transaction_History_Heading_Label;

    public String getLABLTransactionHistoryHeadingLabel() { return this.LABL_Transaction_History_Heading_Label; }

    public void setLABLTransactionHistoryHeadingLabel(String LABL_Transaction_History_Heading_Label) { this.LABL_Transaction_History_Heading_Label = LABL_Transaction_History_Heading_Label; }

    private String LABL_Event_Description_Label;

    public String getLABLEventDescriptionLabel() { return this.LABL_Event_Description_Label; }

    public void setLABLEventDescriptionLabel(String LABL_Event_Description_Label) { this.LABL_Event_Description_Label = LABL_Event_Description_Label; }

    private String Added_Redeemed;

    public String getAddedRedeemed() { return this.Added_Redeemed; }

    public void setAddedRedeemed(String Added_Redeemed) { this.Added_Redeemed = Added_Redeemed; }

    private String Pass_Credits ;

    public String getPassCredits() { return this.Pass_Credits ; }

    public void setPassCredits(String Pass_Credits ) { this.Pass_Credits  = Pass_Credits ; }

}


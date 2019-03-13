package com.optiontown.app.model.redeem.mmp;

import java.io.Serializable;

/**
 * Created by amit on 12-11-2016.
 */
public class CalculatePriceData implements Serializable
{
    private PassSelectedParameterData PassSelectedParameterData;

    public com.optiontown.app.model.redeem.mmp.PassSelectedParameterData getPassSelectedParameterData() {
        return PassSelectedParameterData;
    }

    public void setPassSelectedParameterData(com.optiontown.app.model.redeem.mmp.PassSelectedParameterData passSelectedParameterData) {
        PassSelectedParameterData = passSelectedParameterData;
    }

    private String Fare_Difference_Label;

    public String getFareDifferenceLabel() { return this.Fare_Difference_Label; }

    public void setFareDifferenceLabel(String Fare_Difference_Label) { this.Fare_Difference_Label = Fare_Difference_Label; }

    private String Change_Fee_Label;

    public String getChangeFeeLabel() { return this.Change_Fee_Label; }

    public void setChangeFeeLabel(String Change_Fee_Label) { this.Change_Fee_Label = Change_Fee_Label; }

    private int Total_Amount;

    public int getTotalAmount() { return this.Total_Amount; }

    public void setTotalAmount(int Total_Amount) { this.Total_Amount = Total_Amount; }

    private int Price_fareDiff;

    public int getPriceFareDiff() { return this.Price_fareDiff; }

    public void setPriceFareDiff(int Price_fareDiff) { this.Price_fareDiff = Price_fareDiff; }

    private String Trans_Currency_code;

    public String getTransCurrencyCode() { return this.Trans_Currency_code; }

    public void setTransCurrencyCode(String Trans_Currency_code) { this.Trans_Currency_code = Trans_Currency_code; }

    private int changeFeeAmount;

    public int getChangeFeeAmount() { return this.changeFeeAmount; }

    public void setChangeFeeAmount(int changeFeeAmount) { this.changeFeeAmount = changeFeeAmount; }

    private String LABL_Proceed_To_Payment_Label;

    public String getLABLProceedToPaymentLabel() { return this.LABL_Proceed_To_Payment_Label; }

    public void setLABLProceedToPaymentLabel(String LABL_Proceed_To_Payment_Label) { this.LABL_Proceed_To_Payment_Label = LABL_Proceed_To_Payment_Label; }

    private String LABL_Cancel_Label;

    public String getLABLCancelLabel() { return this.LABL_Cancel_Label; }

    public void setLABLCancelLabel(String LABL_Cancel_Label) { this.LABL_Cancel_Label = LABL_Cancel_Label; }

    private String Amount_Label;

    public String getAmountLabel() { return this.Amount_Label; }

    public void setAmountLabel(String Amount_Label) { this.Amount_Label = Amount_Label; }

    private String Confirm_Button_Label;

    public String getConfirmButtonLabel() { return this.Confirm_Button_Label; }

    public void setConfirmButtonLabel(String Confirm_Button_Label) { this.Confirm_Button_Label = Confirm_Button_Label; }
}

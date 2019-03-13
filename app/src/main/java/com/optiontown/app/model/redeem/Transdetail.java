package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by amit on 03-09-2016.
 */
public class Transdetail implements Serializable
{

    private String LABL_Users_Label;

    public String getLABLUsersLabel() { return this.LABL_Users_Label; }

    public void setLABLUsersLabel(String LABL_Users_Label) { this.LABL_Users_Label = LABL_Users_Label; }

    private String transaction_history_label;

    public String getTransactionHistoryLabel() { return this.transaction_history_label; }

    public void setTransactionHistoryLabel(String transaction_history_label) { this.transaction_history_label = transaction_history_label; }

    private String CreditCardNoEncyptd;

    public String getCreditCardNoEncyptd() { return this.CreditCardNoEncyptd; }

    public void setCreditCardNoEncyptd(String CreditCardNoEncyptd) { this.CreditCardNoEncyptd = CreditCardNoEncyptd; }

    private String Value_New_Value;

    public String getValueNewValue() { return this.Value_New_Value; }

    public void setValueNewValue(String Value_New_Value) { this.Value_New_Value = Value_New_Value; }

    private int tgpEventId;

    public int getTgpEventId() { return this.tgpEventId; }

    public void setTgpEventId(int tgpEventId) { this.tgpEventId = tgpEventId; }

    private String changetype;

    public String getChangetype() { return this.changetype; }

    public void setChangetype(String changetype) { this.changetype = changetype; }

    private String New_Value_Label;

    public String getNewValueLabel() { return this.New_Value_Label; }

    public void setNewValueLabel(String New_Value_Label) { this.New_Value_Label = New_Value_Label; }

    private String Event_Type_Description_Label;

    public String getEventTypeDescriptionLabel() { return this.Event_Type_Description_Label; }

    public void setEventTypeDescriptionLabel(String Event_Type_Description_Label) { this.Event_Type_Description_Label = Event_Type_Description_Label; }

    private String LABL_Months_Validity_Label;

    public String getLABLMonthsValidityLabel() { return this.LABL_Months_Validity_Label; }

    public void setLABLMonthsValidityLabel(String LABL_Months_Validity_Label) { this.LABL_Months_Validity_Label = LABL_Months_Validity_Label; }

    private String LABL_Flights_Label;

    public String getLABLFlightsLabel() { return this.LABL_Flights_Label; }

    public void setLABLFlightsLabel(String LABL_Flights_Label) { this.LABL_Flights_Label = LABL_Flights_Label; }

    private int price;

    public int getPrice() { return this.price; }

    public void setPrice(int price) { this.price = price; }

    private String Transaction_Date_Format;

    public String getTransactionDateFormat() { return this.Transaction_Date_Format; }

    public void setTransactionDateFormat(String Transaction_Date_Format) { this.Transaction_Date_Format = Transaction_Date_Format; }

    private String Payment_Currency;

    public String getPaymentCurrency() { return this.Payment_Currency; }

    public void setPaymentCurrency(String Payment_Currency) { this.Payment_Currency = Payment_Currency; }

    private String Value_Old_Value;

    public String getValueOldValue() { return this.Value_Old_Value; }

    public void setValueOldValue(String Value_Old_Value) { this.Value_Old_Value = Value_Old_Value; }

    private String Old_Value_Label;

    public String getOldValueLabel() { return this.Old_Value_Label; }

    public void setOldValueLabel(String Old_Value_Label) { this.Old_Value_Label = Old_Value_Label; }

    private String Event_Type_Description_Value;

    public String getEventTypeDescriptionValue() { return this.Event_Type_Description_Value; }

    public void setEventTypeDescriptionValue(String Event_Type_Description_Value) { this.Event_Type_Description_Value = Event_Type_Description_Value; }

    private String event_label;

    public String getEventLabel() { return this.event_label; }

    public void setEventLabel(String event_label) { this.event_label = event_label; }

    private String Credit_Title;

    public String getCreditTitle() { return this.Credit_Title; }

    public void setCreditTitle(String Credit_Title) { this.Credit_Title = Credit_Title; }

    private int AgreedCreditCount;

    public int getAgreedCreditCount() { return this.AgreedCreditCount; }

    public void setAgreedCreditCount(int AgreedCreditCount) { this.AgreedCreditCount = AgreedCreditCount; }

}

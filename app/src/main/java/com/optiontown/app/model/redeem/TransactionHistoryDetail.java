package com.optiontown.app.model.redeem;

import java.io.Serializable;

/**
 * Created by amit on 04-10-2016.
 */
public class TransactionHistoryDetail implements Serializable
{
    private String transaction_Date;

    public String getTransactionDate() { return this.transaction_Date; }

    public void setTransactionDate(String transaction_Date) { this.transaction_Date = transaction_Date; }

    private String price_with_currency;

    public String getPriceWithCurrency() { return this.price_with_currency; }

    public void setPriceWithCurrency(String price_with_currency) { this.price_with_currency = price_with_currency; }

    private String tgp_fg_short_description;

    public String getTgpFgShortDescription() { return this.tgp_fg_short_description; }

    public void setTgpFgShortDescription(String tgp_fg_short_description) { this.tgp_fg_short_description = tgp_fg_short_description; }

    private String old_pass_value;

    public String getOldPassValue() { return this.old_pass_value; }

    public void setOldPassValue(String old_pass_value) { this.old_pass_value = old_pass_value; }

    private String account_number;

    public String getAccountNumber() { return this.account_number; }

    public void setAccountNumber(String account_number) { this.account_number = account_number; }

    private String event_label;

    public String getEventLabel() { return this.event_label; }

    public void setEventLabel(String event_label) { this.event_label = event_label; }

    private String new_pass_value;

    public String getNewPassValue() { return this.new_pass_value; }

    public void setNewPassValue(String new_pass_value) { this.new_pass_value = new_pass_value; }
}

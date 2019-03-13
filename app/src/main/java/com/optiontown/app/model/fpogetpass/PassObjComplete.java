package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;

/**
 * Created by amit on 16-06-2016.
 */
public class PassObjComplete implements Serializable
{
    private float SavingMinPercent;

    public float getSavingMinPercent() { return this.SavingMinPercent; }

    public void setSavingMinPercent(float SavingMinPercent) { this.SavingMinPercent = SavingMinPercent; }

    private int PassDownPayment;

    public int getPassDownPayment() { return this.PassDownPayment; }

    public void setPassDownPayment(int PassDownPayment) { this.PassDownPayment = PassDownPayment; }

    private int SavingFullAmount;

    public int getSavingFullAmount() { return this.SavingFullAmount; }

    public void setSavingFullAmount(int SavingFullAmount) { this.SavingFullAmount = SavingFullAmount; }

    private String PassSavingAmountLabel;

    public String getPassSavingAmountLabel() { return this.PassSavingAmountLabel; }

    public void setPassSavingAmountLabel(String PassSavingAmountLabel) { this.PassSavingAmountLabel = PassSavingAmountLabel; }

    private int SavingPerDisplay;

    public int getSavingPerDisplay() { return this.SavingPerDisplay; }

    public void setSavingPerDisplay(int SavingPerDisplay) { this.SavingPerDisplay = SavingPerDisplay; }

    private int SavingPercent;

    public int getSavingPercent() { return this.SavingPercent; }

    public void setSavingPercent(int SavingPercent) { this.SavingPercent = SavingPercent; }

    private int OldPricePerPass;

    public int getOldPricePerPass() { return this.OldPricePerPass; }

    public void setOldPricePerPass(int OldPricePerPass) { this.OldPricePerPass = OldPricePerPass; }

    private int SavingAmounDisplay;

    public int getSavingAmounDisplay() { return this.SavingAmounDisplay; }

    public void setSavingAmounDisplay(int SavingAmounDisplay) { this.SavingAmounDisplay = SavingAmounDisplay; }

    private int FlexPlanAmtDisplay;

    public int getFlexPlanAmtDisplay() { return this.FlexPlanAmtDisplay; }

    public void setFlexPlanAmtDisplay(int FlexPlanAmtDisplay) { this.FlexPlanAmtDisplay = FlexPlanAmtDisplay; }

    private double SavingAmount;

    public double getSavingAmount() { return this.SavingAmount; }

    public void setSavingAmount(double SavingAmount) { this.SavingAmount = SavingAmount; }

    private String FullAmountLabel;

    public String getFullAmountLabel() { return this.FullAmountLabel; }

    public void setFullAmountLabel(String FullAmountLabel) { this.FullAmountLabel = FullAmountLabel; }

    private double DownPaymentFactor;

    public double getDownPaymentFactor() { return this.DownPaymentFactor; }

    public void setDownPaymentFactor(double DownPaymentFactor) { this.DownPaymentFactor = DownPaymentFactor; }

    private String SavingPercentLabel;

    public String getSavingPercentLabel() { return this.SavingPercentLabel; }

    public void setSavingPercentLabel(String SavingPercentLabel) { this.SavingPercentLabel = SavingPercentLabel; }

    private int SavingFullAmtDisplay;

    public int getSavingFullAmtDisplay() { return this.SavingFullAmtDisplay; }

    public void setSavingFullAmtDisplay(int SavingFullAmtDisplay) { this.SavingFullAmtDisplay = SavingFullAmtDisplay; }

    private int NewPricePerPass;

    public int getNewPricePerPass() { return this.NewPricePerPass; }

    public void setNewPricePerPass(int NewPricePerPass) { this.NewPricePerPass = NewPricePerPass; }

    private String FlexPlanAmtLabel;

    public String getFlexPlanAmtLabel() { return this.FlexPlanAmtLabel; }

    public void setFlexPlanAmtLabel(String FlexPlanAmtLabel) { this.FlexPlanAmtLabel = FlexPlanAmtLabel; }
}
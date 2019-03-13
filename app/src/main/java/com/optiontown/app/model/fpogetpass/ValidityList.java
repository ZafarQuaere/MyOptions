package com.optiontown.app.model.fpogetpass;

import java.io.Serializable;

/**
 * Created by amit on 16-06-2016.
 */
public class ValidityList implements Serializable
{
    private String Name;

    public String getName() { return this.Name; }

    public void setName(String Name) { this.Name = Name; }

    private String TimeUnitPluralName;

    public String getTimeUnitPluralName() { return this.TimeUnitPluralName; }

    public void setTimeUnitPluralName(String TimeUnitPluralName) { this.TimeUnitPluralName = TimeUnitPluralName; }

    private String TimeUnitName;

    public String getTimeUnitName() { return this.TimeUnitName; }

    public void setTimeUnitName(String TimeUnitName) { this.TimeUnitName = TimeUnitName; }

    private int TimeUnit;

    public int getTimeUnit() { return this.TimeUnit; }

    public void setTimeUnit(int TimeUnit) { this.TimeUnit = TimeUnit; }

    private int ValiditiId;

    public int getValiditiId() { return this.ValiditiId; }

    public void setValiditiId(int ValiditiId) { this.ValiditiId = ValiditiId; }

    private int PassValidityValue;

    public int getPassValidityValue() { return this.PassValidityValue; }

    public void setPassValidityValue(int PassValidityValue) { this.PassValidityValue = PassValidityValue; }

    private Object SortOrder;

    public Object getSortOrder() { return this.SortOrder; }

    public void setSortOrder(Object SortOrder) { this.SortOrder = SortOrder; }
}
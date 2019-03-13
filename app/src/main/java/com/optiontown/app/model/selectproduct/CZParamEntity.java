package com.optiontown.app.model.selectproduct;

import java.io.Serializable;

/**
 * Created by amit on 11-08-2016.
 */
public class CZParamEntity implements Serializable
{
    private String customZoneOriginLabel;

    public String getCustomZoneOriginLabel() { return this.customZoneOriginLabel; }

    public void setCustomZoneOriginLabel(String customZoneOriginLabel) { this.customZoneOriginLabel = customZoneOriginLabel; }

    private String customZoneDestinationLabel;

    public String getCustomZoneDestinationLabel() { return this.customZoneDestinationLabel; }

    public void setCustomZoneDestinationLabel(String customZoneDestinationLabel) { this.customZoneDestinationLabel = customZoneDestinationLabel; }

    private String isCustomSearchOn;

    public String getIsCustomSearchOn() { return this.isCustomSearchOn; }

    public void setIsCustomSearchOn(String isCustomSearchOn) { this.isCustomSearchOn = isCustomSearchOn; }

    private String customZoneDescriptionLabel;

    public String getCustomZoneDescriptionLabel() { return this.customZoneDescriptionLabel; }

    public void setCustomZoneDescriptionLabel(String customZoneDescriptionLabel) { this.customZoneDescriptionLabel = customZoneDescriptionLabel; }
}

package com.optiontown.app.model.benifits;

import java.io.Serializable;

/**
 * Created by ravi.kumar on 30-07-2016.
 */
public class Benefit  implements Serializable {
    String ImageURL;

    public String getBenefitName() {
        return BenefitName;
    }

    public void setBenefitName(String benefitName) {
        BenefitName = benefitName;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    String BenefitName;

    public String getBenefitDescription() {
        return BenefitDescription;
    }

    public void setBenefitDescription(String benefitDescription) {
        BenefitDescription = benefitDescription;
    }

    String BenefitDescription;


    int Id;


    public String getLabl_StepsForProductLabel() {
        return Labl_StepsForProductLabel;
    }

    public void setLabl_StepsForProductLabel(String labl_StepsForProductLabel) {
        Labl_StepsForProductLabel = labl_StepsForProductLabel;
    }

    public String Labl_StepsForProductLabel;

}

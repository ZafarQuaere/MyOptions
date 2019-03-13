package com.optiontown.app.model.legproducthomepage;

import com.optiontown.app.model.utosearchresult.CabinDetailList;
import com.optiontown.app.model.utosearchresult.IfsObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ravi.kumar on 29-08-2016.
 */
public class UpgradeHelper implements Serializable {
    List<CabinDetailList> mCabinDetailLists;
    List<IfsObject> mIfsObject;

    public List<IfsObject> getmIfsObject() {
        return mIfsObject;
    }

    public void setmIfsObject(List<IfsObject> mIfsObject) {
        this.mIfsObject = mIfsObject;
    }

    public List<CabinDetailList> getmCabinDetailLists() {
        return mCabinDetailLists;
    }

    public void setmCabinDetailLists(List<CabinDetailList> mCabinDetailLists) {
        this.mCabinDetailLists = mCabinDetailLists;
    }
}

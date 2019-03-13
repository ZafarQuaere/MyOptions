package com.common;

/**
 * Created by amit on 12-08-2017.
 */
public class Building
{
    private String buildingName;
    private int floorCount;


    public Building(String buildingName, int floorCount)
    {
        this.buildingName = buildingName;
        this.floorCount = floorCount;
    }

    //returns true if content is equal otherwise false
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        Building building = (Building) obj;
        if(this.buildingName.equals(building.buildingName) && this.floorCount == building.floorCount)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

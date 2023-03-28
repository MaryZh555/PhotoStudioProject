package com.maryzh555.photo_studio.models;

/**
 * Created by zhmas on 18.03.2023.
 */
public class Locations {

    private String name;

    private int rentingCost;

    private String description;

    Locations(String name, int rentingCost, String description) {
        this.name = name;
        this.rentingCost = rentingCost;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRentingCost() {
        return rentingCost;
    }

    public void setRentingCost(int rentingCost) {
        this.rentingCost = rentingCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
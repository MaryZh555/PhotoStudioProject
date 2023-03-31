package com.maryzh555.photo_studio.enums;

/**
 * Created by Zhang M. on 18.03.2023.
 */
public enum Location {

    CHROMA_CHARM(
            10,
            "A cozy and intimate space with chromakey and all necessary attributes for portrait photography."),
    GROUP_HUB(
            25,
            "A spacious room perfect for group and team photo shoots with a variety of backdrops and props available."),
    RIVER_VIEW_TERRACE(
            30,
            "An outdoor location featuring a picturesque view of a small bridge over a nearby river, perfect for natural and scenic photography."
    );


    private int rentingCost;

    private String description;

    Location(int rentingCost, String description) {
        this.rentingCost = rentingCost;
        this.description = description;
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
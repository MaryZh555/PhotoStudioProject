package com.maryzh555.photo_studio.enums;

/**
 * Enum representing different photography locations available for rent.
 * Each location has a renting cost and a description.
 * The toString() method is overridden to return the name with underscores replaced by spaces for printing usage.
 *
 * @author Zhang M. on 18.03.2023.
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

    private final int rentingCost;

    private final String description;

    Location(int rentingCost, String description) {
        this.rentingCost = rentingCost;
        this.description = description;
    }

    public String toString() {
        return name().replace("_", " ");
    }

    public int getRentingCost() {
        return rentingCost;
    }

    public String getDescription() {
        return description;
    }

}
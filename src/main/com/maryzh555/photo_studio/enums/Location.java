package main.com.maryzh555.photo_studio.enums;

import main.com.maryzh555.photo_studio.util.myFileReader;

/**
 * Enum representing different photography locations available for rent.
 * Each location has a renting cost and a description.
 * The toString() method is overridden to return the name with underscores replaced by spaces for printing usage.
 *
 * @author Zhang M. on 18.03.2023.
 */
public enum Location {

    CHROMA_CHARM(10),
    GROUP_HUB(25),
    RIVER_VIEW_TERRACE(30);

    private final int rentingCost;

    private final String description;

    Location(int rentingCost) {
        this.rentingCost = rentingCost;
        this.description = loadDescription();
    }

    private String loadDescription() {
        String descriptionFilePath = "/Descriptions - locations.txt";
        myFileReader reader = new myFileReader();
        return reader.extractDescription(toString(), descriptionFilePath);
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
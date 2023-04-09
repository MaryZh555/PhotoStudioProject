package com.maryzh555.photo_studio.enums;

import com.maryzh555.photo_studio.util.myFileReader;

/**
 * This enum represents the different types of photography available for customers to choose from.
 * Each photo type has a description and an estimated time in hours required for the photo shoot.
 *
 * @author Zhang M. on 17.03.2023.
 */
public enum PhotoType{
    PORTRAIT(1),
    GROUP( 2),
    TEAM(3);


    private final String description;

    private final int hours;


    PhotoType(int hours) {
        this.description = loadDescription();
        this.hours = hours;
    }

    private String loadDescription(){
        String descriptionFilePath = "/Descriptions - photo types.txt";
        myFileReader reader = new myFileReader();
        return reader.extractDescription(toString(), descriptionFilePath);
    }

    public String getDescription() {
        return description;
    }

    public int getHours() {
        return hours;
    }

}

package com.maryzh555.photo_studio.enums;

/**
 * Created by Zhang M. on 17.03.2023.
 */
public enum PhotoType{
    PORTRAIT("For one person photo shoot. It will take 1 hour.", 1),
    GROUP("It is the best for 2-10 people, like families, friends and lovers. It will take 2 hours.", 2),
    TEAM("Photo for 10-50 people, it can be sport teams, company members and students. It will take 3 hours.", 3);

    private String description;

    private int hours;

    PhotoType(String description,  int hours) {
        this.description = description;
        this.hours = hours;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

}

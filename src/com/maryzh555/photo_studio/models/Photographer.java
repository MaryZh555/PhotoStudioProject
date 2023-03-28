package com.maryzh555.photo_studio.models;
/**
 * Created by zhmas on 18.03.2023.
 */
public class Photographer {

    private String name;

    private int yearsOfExperience;

    private int hourlyRate;

    Photographer(String name, int years, int hourlyRate) {
        this.name = name;
        this.yearsOfExperience = years;
        this.hourlyRate = hourlyRate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int years) {
        this.yearsOfExperience = years;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

}

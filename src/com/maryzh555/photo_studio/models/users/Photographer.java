package com.maryzh555.photo_studio.models.users;

import com.maryzh555.photo_studio.interfaces.Report;

/**
 * This class represents a photographer, a type of human, who can work in the PhotoStudio.
 * * The idCounter field is a temporary solution for id generation until a database is implemented.*
 *
 * @author Zhang M. on 18.03.2023.
 */
public class Photographer extends Worker implements Report {

    private int yearsOfExperience;

    public Photographer(String name, int years, int hourlyRate) {
        super(name);// for id and name
        this.yearsOfExperience = years;
        this.hourlyRate = hourlyRate;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int years) {
        this.yearsOfExperience = years;
    }

    @Override
    public void report() {

    }
}

package com.maryzh555.photo_studio.models.users;

import com.maryzh555.photo_studio.interfaces.Report;
import com.maryzh555.photo_studio.models.PhotoStudio;

/**
 * This class represents a photographer, a type of human, who can work in the PhotoStudio.
 * * The idCounter field is a temporary solution for id generation until a database is implemented.*
 *
 * @author Zhang M. on 18.03.2023.
 */
public class Photographer extends Worker implements Report {

    private int yearsOfExperience;

    private int photoShootsToday;


    public Photographer(String name, int years, int hourlyRate) {
        super(name);// for id and name
        this.yearsOfExperience = years;
        this.hourlyRate = hourlyRate;
    }


    public void askForRest(PhotoStudio photoStudio){
        if(this.hoursWorkedToday == 8 ||this.hoursWorkedToday > 8){
            //remove photographer from the list of available photographers
            photoStudio.getDirector().removeFromAvailablePhotographers(this);
        }
    }

    public void addToHoursWorkedToday(int hoursWorkedToday,PhotoStudio photoStudio) {
        this.hoursWorkedToday += hoursWorkedToday;
        this.askForRest(photoStudio);
    }

    @Override
    public void report() {
        System.out.println("( Photographer " + this.getName() + " reports: " +
                "Photo shoots today: " + photoShootsToday + ". " +
                "Hours worked: " + hoursWorkedToday + ")");
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int years) {
        this.yearsOfExperience = years;
    }


    public int getPhotoShootsToday() {
        return photoShootsToday;
    }

    public void setPhotoShootsToday(int photoShootsToday) {
        this.photoShootsToday = photoShootsToday;
    }

    public void addToPhotoShootsToday(int photoShootsToday) {
        this.photoShootsToday += photoShootsToday;
    }
}

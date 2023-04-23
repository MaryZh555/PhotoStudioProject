package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.interfaces.IReport;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public abstract class Worker extends User implements IReport {

    public int hourlyRate;

    public int hoursWorkedToday;

    public int neededExperience;

    Worker() {
        super();
    }

    Worker(String name) {
        super(name);
    }

    public void addToHoursWorkedToday(int hoursWorkedToday, PhotoStudio photoStudio) {
        this.hoursWorkedToday += hoursWorkedToday;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorkedToday() {
        return hoursWorkedToday;
    }

    public void setHoursWorkedToday(int hoursWorkedToday) {
        this.hoursWorkedToday = hoursWorkedToday;
    }

    public int getNeededExperience() {
        return neededExperience;
    }

    public void setNeededExperience(int neededExperience) {
        this.neededExperience = neededExperience;
    }
}

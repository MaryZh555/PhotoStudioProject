package com.maryzh555.photo_studio.models.users;

import com.maryzh555.photo_studio.interfaces.Report;
import com.maryzh555.photo_studio.models.PhotoStudio;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public abstract class Worker extends User implements Report {

    public int hourlyRate;

    public int hoursWorkedToday;

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
}

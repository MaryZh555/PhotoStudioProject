package com.maryzh555.photo_studio.models.humans;

import com.maryzh555.photo_studio.interfaces.Report;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public abstract class Worker extends Human implements Report {

    protected int hourlyRate;

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}

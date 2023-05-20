package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.WorkerType;

/**
 * @author by Zhang M. on 14.05.2023.
 */
public class Vacancy {

    private WorkerType workerType;

    private int minExperience;

    private int maxExperience;

    private int maxSalary;

    public Vacancy(WorkerType workerType, int minExperience, int maxExperience, int maxSalary) {
        this.workerType = workerType;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
        this.maxSalary = maxSalary;
    }


    public WorkerType getWorkerType() {
        return workerType;
    }

    public void setWorkerType(WorkerType workerType) {
        this.workerType = workerType;
    }

    public int getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(int minExperience) {
        this.minExperience = minExperience;
    }

    public int getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(int maxExperience) {
        this.maxExperience = maxExperience;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

}


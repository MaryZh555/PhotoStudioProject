package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.enums.WorkerType;

/**
 * @author by Zhang M. on 21.04.2023.
 */
public class Candidate extends User{

    private WorkerType workerType;

    private int yearsOfExperience;

    private int age;

    private int hourlyRate;

    private boolean borrowCamera;

    private final int retirementAge;

    private final int legalWorkingAge;


    public Candidate(){
        this.retirementAge = 64;//represent the company admitted age of retirement
        this.legalWorkingAge = 18;//represents the minimum legal age of the worker
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public boolean isBorrowCamera() {
        return borrowCamera;
    }

    public void setBorrowCamera(boolean borrowCamera) {
        this.borrowCamera = borrowCamera;
    }

    public WorkerType getWorkerType() {
        return workerType;
    }

    public void setWorkerType(WorkerType workerType) {
        this.workerType = workerType;
    }

    public int getRetirementAge() {
        return retirementAge;
    }

    public int getLegalWorkingAge() {
        return legalWorkingAge;
    }
}

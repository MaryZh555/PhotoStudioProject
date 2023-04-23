package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.enums.WorkerType;

/**
 * @author by Zhang M. on 21.04.2023.
 */
public class Candidate extends User{

    private WorkerType workerType;

    private int minExperience;

    private int maxExperience;

    private int maxSalary;

    private int yearsOfExperience;

    private int age;

    private int hourlyRate;

    public Candidate(){}
    public Candidate(WorkerType workerType, String name, int yearsOfExperience){
        super(name);
        this.workerType = workerType;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Candidate(WorkerType workerType, int minExperience, int maxExperience, int maxSalary) {
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
}

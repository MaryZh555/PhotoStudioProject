package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.interfaces.IReport;
import main.com.maryzh555.photo_studio.models.PhotoStudio;


/**
 * @author by Zhang M. on 04.04.2023.
 */
public class HRManager extends Worker implements IReport {


    private int maxAgeOfCandidates;

    private int retirementAgeOfCandidate;

    private Candidate userCandidate;

    private int candidatesToday;

    public HRManager(String name, int hourlyRate) {
        super(name);//set id and name
        this.hourlyRate = hourlyRate;
    }


    public boolean checkNewCandidate(PhotoStudio photoStudio) {
        Candidate userCandidate = this.userCandidate;
        boolean result = false;

        boolean ageRequirements = userCandidate.getAge() >= 18 && userCandidate.getAge() <= maxAgeOfCandidates;
        boolean experienceRequirements = false;
        boolean salaryRequirements = false;

        for (Candidate vacancyCandidate : photoStudio.getDigitalStorage().getVacancies()) {
            if (vacancyCandidate.getWorkerType() == userCandidate.getWorkerType()) {

                if (userCandidate.getYearsOfExperience() >= vacancyCandidate.getMinExperience() && userCandidate.getYearsOfExperience() <= vacancyCandidate.getMaxExperience()) {
                    experienceRequirements = true;
                }
                if (userCandidate.getHourlyRate() <= vacancyCandidate.getMaxSalary()) {
                    salaryRequirements = true;
                }

                if (experienceRequirements && salaryRequirements) {
                    // Exit
                    break;
                }
            }
        }

        if (ageRequirements && experienceRequirements && salaryRequirements) result = true;

        return result;
    }

    public void addCandidate(){
        this.candidatesToday++;
    }

    @Override
    public void report() {
        System.out.println("( HR Manager " + this.getName() + " reports: " + "Checked candidates today: " + this.candidatesToday + " )");
    }


    public int getMaxAgeOfCandidates() {
        return maxAgeOfCandidates;
    }

    public void setMaxAgeOfCandidates(int maxAgeOfCandidates) {
        this.maxAgeOfCandidates = maxAgeOfCandidates;
    }

    public int getRetirementAgeOfCandidate() {
        return retirementAgeOfCandidate;
    }

    public void setRetirementAgeOfCandidate(int retirementAgeOfCandidate) {
        this.retirementAgeOfCandidate = retirementAgeOfCandidate;
    }

    public Candidate getUserCandidate() {
        return userCandidate;
    }

    public void setUserCandidate(Candidate userCandidate) {
        this.userCandidate = userCandidate;
    }

    public int getCandidatesToday() {
        return candidatesToday;
    }

    public void setCandidatesToday(int candidatesToday) {
        this.candidatesToday = candidatesToday;
    }
}

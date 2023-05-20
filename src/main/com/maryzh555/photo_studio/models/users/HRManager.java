package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.interfaces.IReport;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.Vacancy;


/**
 * @author by Zhang M. on 04.04.2023.
 */
public class HRManager extends Worker implements IReport {


    private Candidate userCandidate;//todo in question does it need to be here or maybe in the photostudio?

    private int candidatesToday;

    public HRManager(String name, int hourlyRate) {
        super(name);//set id and name
        this.hourlyRate = hourlyRate;
    }


    public boolean checkNewCandidate(PhotoStudio photoStudio, Candidate candidate) {
        boolean result = false;

        boolean ageRequirements = candidate.getAge() >= candidate.getLegalWorkingAge() && userCandidate.getAge() <= candidate.getRetirementAge();
        boolean experienceRequirements = false;
        boolean salaryRequirements = false;

        for (Vacancy vacancy : photoStudio.getDigitalStorage().getVacancies()) {
            if (vacancy.getWorkerType() == candidate.getWorkerType()) {

                if (candidate.getYearsOfExperience() >= vacancy.getMinExperience() && userCandidate.getYearsOfExperience() <= vacancy.getMaxExperience()) {
                    experienceRequirements = true;
                }
                if (candidate.getHourlyRate() <= vacancy.getMaxSalary()) {
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

    public Vacancy findSuitableVacancy(PhotoStudio photoStudio, Candidate candidate){
        boolean ageRequirements = false;
        boolean experienceRequirements = false;
        boolean salaryRequirements = false;

        for (Vacancy vacancyCandidate : photoStudio.getDigitalStorage().getVacancies()) {
            if (vacancyCandidate.getWorkerType() == candidate.getWorkerType()) {
                if( candidate.getAge() >= candidate.getLegalWorkingAge() && candidate.getAge() <= candidate.getRetirementAge()){
                    ageRequirements = true;
                }

                if (candidate.getYearsOfExperience() >= vacancyCandidate.getMinExperience() && candidate.getYearsOfExperience() <= vacancyCandidate.getMaxExperience()) {
                    experienceRequirements = true;
                }
                if (candidate.getHourlyRate() <= vacancyCandidate.getMaxSalary()) {
                    salaryRequirements = true;
                }

                if (ageRequirements && experienceRequirements && salaryRequirements) {
                    // Exit
                    return vacancyCandidate;
                }
            }
        }
        return null;
    }

    public void addCandidate(){
        this.candidatesToday++;
    }

    @Override
    public void report() {
        System.out.println("( HR Manager " + this.getName() + " reports: " + "Checked candidates today: " + this.candidatesToday + " )");
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

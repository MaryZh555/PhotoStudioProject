package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.enums.WorkerType;
import main.com.maryzh555.photo_studio.interfaces.IReport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public class HRManager extends Worker implements IReport {

    private List<Candidate> vacancies;

    private int maxAgeOfCandidates;

    private int retirementAgeOfCandidate;

    private Candidate userCandidate;

    private int candidatesToday;

    public HRManager(String name, int hourlyRate) {
        super(name);//set id and name
        this.hourlyRate = hourlyRate;
        this.vacancies = fillVacancies();
    }

    private List<Candidate> fillVacancies() {
        List<Candidate> result = new ArrayList<>();
        result.add(new Candidate(WorkerType.PHOTOGRAPHER, 5, 49, 30));//67-18 = 49 // maxExperience can be whatever number HRmanager chooses;
        result.add(new Candidate(WorkerType.PHOTOGRAPHER, 10, 49, 30));
        result.add(new Candidate(WorkerType.SUPPLY_MANAGER, 1, 49, 15));
        return result;
    }


    public List<Candidate> matchVacancies(WorkerType type) {
        List<Candidate> result = new ArrayList<>();
        for (Candidate candidate : this.vacancies) {
            if (candidate.getWorkerType() == type) {
                result.add(candidate);
            }
        }
        return result;
    }

    public boolean checkNewCandidate() {
        Candidate userCandidate = this.userCandidate;
        boolean result = false;

        boolean ageRequirements = userCandidate.getAge() >= 18 && userCandidate.getAge() <= maxAgeOfCandidates;
        boolean experienceRequirements = false;
        boolean salaryRequirements = false;

        for (Candidate vacancyCandidate : vacancies) {
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


    @Override
    public void report() {
        System.out.println("( HR Manager " + this.getName() + " reports: " + "Checked candidates today: " + this.candidatesToday + " )");
    }

    public List<Candidate> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Candidate> vacancies) {
        this.vacancies = vacancies;
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

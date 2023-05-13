package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.exceptions.WrongAgeException;
import main.com.maryzh555.photo_studio.exceptions.WrongNameException;
import main.com.maryzh555.photo_studio.interfaces.IValidateName;
import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.CustomerManager;
import main.com.maryzh555.photo_studio.models.users.HRManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 21.04.2023.
 */
public class CandidateDataForm extends Menu implements IValidateName {

    public CandidateDataForm(Scanner scanner, PhotoStudio photoStudio) {
        showMenu(scanner, null, photoStudio);
    }

    @Override
    public <T extends OrderOrClient> void showMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio){

        callWorker(photoStudio, HRManager.class).setMaxAgeOfCandidates(50);
        int maxAge = callWorker(photoStudio, HRManager.class).getMaxAgeOfCandidates();

        callWorker(photoStudio, HRManager.class).setRetirementAgeOfCandidate(67);
        int retirementAge = callWorker(photoStudio, HRManager.class).getRetirementAgeOfCandidate();

        String name;
        int age;
        int yearsOfExperience;
        int hourlyRate;

        while (true) {
            try {
                System.out.println("Please enter your NAME:");
                System.out.println(" * Note: " +
                        "The name should contain at least 3 English letters, " +
                        "and should not contain any numbers or special symbols.");
                name = scanner.nextLine();
                if (validateName(name)) throw new WrongNameException();
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Please enter your AGE:");
                System.out.println(" * Note: We consider minimum age as 18, and maximum as retirement age - " + retirementAge);
                System.out.println(" IF you are YOUNGER or OLDER than legal hiring age, please enter 'quit' to leave the program.");
                String answer = scanner.nextLine();


                if (!answer.matches(".*\\d+.*") && !answer.trim().equalsIgnoreCase("quit"))
                    throw new NoSuchOptionException();

                if (answer.trim().equalsIgnoreCase("quit")) {
                    new NewCustomerMenu(scanner, photoStudio);
                }

                age = Integer.parseInt(answer);
                if (age < 18 || age > retirementAge) throw new WrongAgeException(maxAge);

                break;
            } catch (WrongAgeException | NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "Invalid input: Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
        while (true) {
            try {
                System.out.println("Please enter your YEARS OF EXPERIENCE:\n " +
                        " * Note: Counts as years of relevant experience after 18 years old." +
                        "\n If you are 18 years old, please enter 0");

                yearsOfExperience = scanner.nextInt();
                int maxPossibleYears = callWorker(photoStudio, CustomerManager.class).calculateMaxYearsOfExperience(age);

                if (yearsOfExperience > maxPossibleYears) {
                    throw new WrongAgeException(maxAge);
                }

                break;
            } catch (WrongAgeException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.println("Please enter your desired HOURLY RATE:\n " +
                        " * Note: you can enter minimum 1$/hour, maximum 100$/hour");

                hourlyRate = scanner.nextInt();

                if (hourlyRate < 1 || hourlyRate > 100) {
                    throw new NoSuchOptionException();
                }

                System.out.println("Please check if the data is correct" +
                        "\n NAME: " + name +
                        "\n AGE: " + age +
                        "\n YEARS OF EXPERIENCE: " + yearsOfExperience +
                        "\n HOURLY RATE: " + hourlyRate);

                callWorker(photoStudio, HRManager.class).getUserCandidate().setYearsOfExperience(yearsOfExperience);
                callWorker(photoStudio, HRManager.class).getUserCandidate().setName(name);
                callWorker(photoStudio, HRManager.class).getUserCandidate().setAge(age);
                callWorker(photoStudio, HRManager.class).getUserCandidate().setHourlyRate(hourlyRate);

                new RedoMenu(scanner, null, photoStudio, this);
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "Invalid input: Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

    @Override
    public boolean validateName(String string) {
        return string.matches(".*\\d+.*") ||
                string.trim().length() < 3 ||
                !string.trim().matches("[a-zA-Z]+");
    }
}

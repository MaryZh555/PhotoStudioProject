package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.enums.WorkerType;
import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Candidate;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author by Zhang M. on 19.04.2023.
 */
public class CandidateMenu extends Menu {

    public CandidateMenu(Scanner scanner, PhotoStudio photoStudio) {
        showMenu(scanner, null, photoStudio);
    }


    public void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("We are glad you chose our company!");
                System.out.println("Please choose one of our vacancy categories:" +
                        "\n 1 - Photographer" +
                        "\n 2 - Supply Manager" +
                        "\n 3 - Customer Manager" +
                        "\n 4 - HR Manager");

                int answer = scanner.nextInt();
                List<Candidate> vacancies;
                Candidate newCandidate = new Candidate();
                switch (answer) {
                    case 1:
                        vacancies = callHRManager(photoStudio).matchVacancies(WorkerType.PHOTOGRAPHER);
                        newCandidate.setWorkerType(WorkerType.PHOTOGRAPHER);
                        break;
                    case 2:
                        vacancies = callHRManager(photoStudio).matchVacancies(WorkerType.SUPPLY_MANAGER);
                        newCandidate.setWorkerType(WorkerType.SUPPLY_MANAGER);
                        break;
                    case 3:
                        vacancies = callHRManager(photoStudio).matchVacancies(WorkerType.CUSTOMER_MANAGER);
                        newCandidate.setWorkerType(WorkerType.CUSTOMER_MANAGER);
                        break;
                    case 4:
                        vacancies = callHRManager(photoStudio).matchVacancies(WorkerType.HR_MANAGER);
                        newCandidate.setWorkerType(WorkerType.HR_MANAGER);
                        break;
                    default:
                        throw new NoSuchOptionException();
                }
                if (vacancies.isEmpty()) {
                    System.out.println("Sorry! We don't have any opened vacancy for your job title!");
                    System.out.println(" Choose: " +
                            "\n 1 - Redo" +
                            "\n 2 - Go to previous ->(User distribution)");
                    int answer2 = scanner.nextInt();
                    switch (answer2) {
                        case 1:
                            showMenu(scanner, null, photoStudio);
                            break;
                        case 2:
                            new UserDistributionMenu(scanner, photoStudio);
                            break;
                        default:
                            throw new NoSuchOptionException();
                    }
                } else {
                    System.out.println("We have these opened vacancies:");
                    int i = 1;
                    for (Candidate candidate : vacancies) {
                        System.out.println(" " + i + " - " + candidate.getWorkerType() + ", with minimum " + candidate.getMinExperience() + " years of experience.");
                        i++;
                    }
                }
                callHRManager(photoStudio).setUserCandidate(newCandidate);
                new RedoMenu(scanner, null, photoStudio, this);
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            /*} catch (EmptyListException e) {
                System.out.println(e.getMessage());
                System.exit(0);*/
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "Invalid input: Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

}

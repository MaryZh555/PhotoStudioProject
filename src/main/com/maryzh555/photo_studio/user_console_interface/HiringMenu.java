package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.enums.WorkerType;
import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.test;
import main.com.maryzh555.photo_studio.models.users.Candidate;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 23.04.2023.
 */
public class HiringMenu extends Menu {
    public HiringMenu(Scanner scanner, PhotoStudio photoStudio) {
        showMenu(scanner, null, photoStudio);
    }


    public void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {


        boolean doHire = photoStudio.getDirector().getHrManager().checkNewCandidate();

        if (doHire) {
            while (true) {
                try {
                    System.out.println("You fit our requirements, congratulations!");
                    System.out.println("We will add you to our list of workers, waiting to see you in our company!");
                    if (photoStudio.getDirector().getHrManager().getUserCandidate().getWorkerType() == WorkerType.PHOTOGRAPHER) {
                        System.out.println("\nOur Studio offers new photographers to borrow professional cameras, if they don't have any." +
                                "\n Do you have your own camera for work?" +
                                "\n 1 - Yes" +
                                "\n 2 - No");
                        int answer = scanner.nextInt();
                        switch (answer) {
                            case 1:
                                photoStudio.getDirector().getHrManager().getUserCandidate().setBorrowCamera(false);
                                break;
                            case 2:
                                photoStudio.getDirector().getHrManager().getUserCandidate().setBorrowCamera(true);
                                break;
                            default:
                                throw new NoSuchOptionException();
                        }
                        photoStudio.getDirector().hireJobCandidate(photoStudio, photoStudio.getDirector().getHrManager().getUserCandidate());
                    }
                    break;
                } catch (NoSuchOptionException e) {
                    System.out.println("It seems like we don't have an order with this id, please check if the inputted id is correct");
                } catch (InputMismatchException e) {
                    System.out.println("---------\n" +
                            "ERROR: Invalid input. Not an integer" +
                            "\n---------");
                    scanner.next(); // clear the input buffer
                }
            }


        } else {
            System.out.println("Sorry! You don't fit our job requirements, we can't offer you a job.");
            System.out.println("Have a nice day!");
        }
        test.printAllWorkers(photoStudio);//test
        new NewCustomerMenu(scanner, photoStudio);
    }
}

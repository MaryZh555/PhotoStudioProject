package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.test;

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
            System.out.println("You fit our requirements, congratulations!");
            System.out.println("We will add you to our list of workers, waiting to see you in our company!");
            photoStudio.getDirector().hireJobCandidate(photoStudio.getDirector().getHrManager().getUserCandidate());

        } else {
            System.out.println("Sorry! You don't fit our job requirements, we can't offer you a job.");
            System.out.println("Have a nice day!");
        }
        test.printAllWorkers(photoStudio);//test
        new NewCustomerMenu(scanner, photoStudio);

    }
}

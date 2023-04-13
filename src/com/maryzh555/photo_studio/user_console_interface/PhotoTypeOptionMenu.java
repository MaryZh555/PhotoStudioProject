package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.enums.PhotoType;
import com.maryzh555.photo_studio.models.users.Client;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class PhotoTypeOptionMenu implements IShowRedoMenu {
    public PhotoTypeOptionMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, client, order, photoStudio);
    }

    private void showMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Ok! How many people would take part in the photo shoot?");

                int answer = scanner.nextInt();
                if (answer < 1 || answer > 50) throw new NoSuchOptionException();

                PhotoType chosenType = photoStudio.getDirector().getCustomerManager().matchPhotoType(answer);
                order.getOrderedPhoto().setPhotoType(chosenType);

                System.out.println("Got it! " +
                        "We can suggest the " + chosenType + " photo shoot. \n" +
                        chosenType.getDescription());

                showRedoMenu(scanner, client, order, photoStudio);
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("---------\n" +
                        "Sorry, we only can only service 1-50 people. Please adjust your number" +
                        "\n---------");
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }


    public void showRedoMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nDo you want to continue or to redo?\n " +
                        "1 - Let's continue.\n " +
                        "2 - I want to redo.");

                int answer = scanner.nextInt();
                switch (answer) {
                    case 1:
//                        //set hours of work for workers
//                        order.getDesiredPhotographer().addToHoursWorkedToday(order.getOrderedPhoto().getPhotoType().getHours(), photoStudio);
                        new LocationOptionMenu(scanner, client, order, photoStudio);
                        break;
                    case 2:
                        new PhotoTypeOptionMenu(scanner, client, order, photoStudio);
                        break;
                    default:
                        throw new NoSuchOptionException();
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }
}

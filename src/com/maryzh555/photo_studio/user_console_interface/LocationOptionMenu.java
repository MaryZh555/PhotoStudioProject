package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.User;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.enums.Location;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class LocationOptionMenu implements IShowRedoMenu {
    public LocationOptionMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, user, order, photoStudio);
    }

    private void showMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nNow the last thing. " +
                        "Our studio offers a location renting.\n" +
                        "For " + order.getDesiredPhotoType() +
                        " photo shoot we can suggest:");

                int i = 0;
                for (Location location : photoStudio.matchLocations(order.getDesiredPhotoType())) {
                    System.out.println(
                            " " + i + " - " + location + " :\n" +
                                    "     " + location.getDescription() + "\n     " +
                                    "Renting cost is " + location.getRentingCost() + " per hour.");
                    i++;
                }

                if (order.getDesiredPhotoType().ordinal() == 2) {
                    order.setDesiredLocation(Location.GROUP_HUB);
                    showRedoMenu(scanner, user, order, photoStudio);
                } else {
                    showLocationOptions(scanner, user, order, photoStudio);
                }
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

    private void showLocationOptions(Scanner scanner, User user, Order order, PhotoStudio photoStudio) throws NoSuchOptionException {
        System.out.println("Which location you would prefer?");

        int answer = scanner.nextInt();
        if (answer < 0 || answer > 1) throw new NoSuchOptionException();
        order.setDesiredLocation(photoStudio.matchLocations(order.getDesiredPhotoType())[answer]);

        System.out.println(
                "You chose a " +
                        order.getDesiredLocation() +
                        " location. Good choice!");

        showRedoMenu(scanner, user, order, photoStudio);
    }

    public void showRedoMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println(
                        " \nAre you ready to calculate the total price or you want to redo?\n " +
                                "1 - Let's continue.\n " +
                                "2 - I want to redo.");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new CalculateTotalMenu(scanner, user, order);
                        break;
                    case 2:
                        new PhotoTypeOptionMenu(scanner, user, order, photoStudio);
                        break;
                    default:
                        throw new NoSuchOptionException();
                }
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
}

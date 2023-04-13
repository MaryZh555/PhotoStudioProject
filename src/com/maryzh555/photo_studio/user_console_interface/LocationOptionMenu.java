package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.EmptyListException;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.users.Client;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.enums.Location;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class LocationOptionMenu implements IShowRedoMenu {
    public LocationOptionMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, client, order, photoStudio);
    }

    private void showMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nOur studio offers a location renting.\n" +
                        "For " + order.getOrderedPhoto().getPhotoType() +
                        " photo shoot we can suggest:");

                List<Location> locationsList = photoStudio.getDirector().getCustomerManager().matchLocations(order.getOrderedPhoto().getPhotoType());

                int i = 0;
                for (Location location : locationsList) {
                    System.out.println(
                            " " + i + " - " + location + " :\n" +
                                    "     " + location.getDescription() + "\n     " +
                                    "Renting cost is " + location.getRentingCost() + " per hour.");
                    i++;
                }
                if(locationsList.isEmpty()) throw new EmptyListException("LOCATIONS");
                if (order.getOrderedPhoto().getPhotoType().ordinal() == 2) {
                    order.setDesiredLocation(Location.GROUP_HUB);
                    showRedoMenu(scanner, client, order, photoStudio);
                } else {
                    showLocationOptions(scanner, client, order, photoStudio);
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (EmptyListException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "Invalid input: Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

    private void showLocationOptions(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) throws NoSuchOptionException {
        System.out.println("\nWhich location you would prefer?");

        int answer = scanner.nextInt();
        if (answer < 0 || answer > 1) throw new NoSuchOptionException();
        order.setDesiredLocation(photoStudio.getDirector().getCustomerManager().matchLocations(order.getOrderedPhoto().getPhotoType()).get(answer));

        System.out.println(
                "You chose a " +
                        order.getDesiredLocation() +
                        " location. Good choice!");

        showRedoMenu(scanner, client, order, photoStudio);
    }

    public void showRedoMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println(
                        " \nAre you ready to continue?\n " +
                                "1 - Let's continue.\n " +
                                "2 - I want to redo.");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new PrintingMenu(scanner, client, order, photoStudio);
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
                        "Invalid input: Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }
}

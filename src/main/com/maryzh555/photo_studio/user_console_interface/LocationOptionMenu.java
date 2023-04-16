package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.EmptyListException;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.interfaces.ShowQuitMenu;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.users.Client;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.enums.Location;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class LocationOptionMenu implements IShowRedoMenu, ShowQuitMenu {
    public LocationOptionMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, order, photoStudio);
    }

    private void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
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
                if (locationsList.isEmpty()) throw new EmptyListException("LOCATIONS");
                if (order.getOrderedPhoto().getPhotoType().ordinal() == 2) {
                    order.setDesiredLocation(Location.GROUP_HUB);
                    showRedoMenu(scanner, order, photoStudio);
                } else {
                    showLocationOptions(scanner, order, photoStudio);
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

    private void showLocationOptions(Scanner scanner, Order order, PhotoStudio photoStudio) throws NoSuchOptionException {
        System.out.println("\nWhich location you would prefer?");

        int answer = scanner.nextInt();
        if (answer < 0 || answer > 1) throw new NoSuchOptionException();
        order.setDesiredLocation(photoStudio.getDirector().getCustomerManager().matchLocations(order.getOrderedPhoto().getPhotoType()).get(answer));

        System.out.println(
                "You chose a " +
                        order.getDesiredLocation() +
                        " location. Good choice!");

        showRedoMenu(scanner, order, photoStudio);
    }

    public void showRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nAre you ready to continue?" +
                        "\n 1 - Let's continue ->(to Print)" +
                        "\n 2 - I want to redo ->(Back to Photo Type)" +
                        "\n 3 - Leave(quit)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new PrintingMenu(scanner, order, photoStudio);
                        break;
                    case 2:
                        new PhotoTypeOptionMenu(scanner, order, photoStudio);
                        break;
                    case 3:
                        showQuitMenu(scanner, order, photoStudio);
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

    @Override
    public void showQuitMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println(" Are you sere you want to quit? " +
                        "\n 1 - Yes(quit)" +
                        "\n 2 - No ->(Redo)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new NewCustomerMenu(scanner, photoStudio);
                        break;
                    case 2:
                        showRedoMenu(scanner, order, photoStudio);
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

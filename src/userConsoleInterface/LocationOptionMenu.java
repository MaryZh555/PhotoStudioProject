package userConsoleInterface;

import exceptions.*;

import models.PhotoType;
import models.User;
import models.Locations;

import java.util.Scanner;

/**
 * Created by zhmas on 20.03.2023.
 */

public class LocationOptionMenu extends Menus implements IShowRedoMenu {
    public LocationOptionMenu(Scanner scanner, User user) {
        showMenu(scanner, user);
    }

    private void showMenu(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println("\nNow the last thing. " +
                        "Our studio offers a location renting.\n" +
                        "For " + user.getDesiredPhotoType().getName() +
                        " photo we can suggest:");

                int i = 0;
                for (Locations location : user.getDesiredPhotoType().getLinkedLocations()) {
                    System.out.println(
                            " " + i + " - " + location.getName() + " :\n" +
                                    "     " + location.getDescription() + "\n     " +
                                    "Renting cost is " + location.getCost() + " per hour.");
                    i++;
                }

                if (user.getDesiredPhotoType().equals(PhotoType.photoTypes[0]) ||
                        user.getDesiredPhotoType().equals(PhotoType.photoTypes[1])) {
                    showLocationOptions(scanner, user);
                } else {
                    user.setDesiredLocation(user.getDesiredPhotoType().getLinkedLocations().get(0));
                    showRedoMenu(scanner, user);
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("---------\nThere is no such options. Please try again.\n---------");
            } catch (NotAnIntegerException e) {
                System.out.println("---------\nInvalid input: Not an integer\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

    private void showLocationOptions(Scanner scanner, User user) throws NoSuchOptionException, NotAnIntegerException {
        System.out.println("Which location you would prefer?");

        int answer = checkingForException(scanner, 0, 1);
        user.setDesiredLocation(user.getDesiredPhotoType().getLinkedLocations().get(answer));

        System.out.println("You chose a " +
                user.getDesiredPhotoType().getLinkedLocations().get(answer).getName() +
                " location. Good choice!");

        showRedoMenu(scanner, user);
    }

    public void showRedoMenu(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println(
                        " \nAre you ready to calculate the total price or you want to redo?\n " +
                                "1 - Let's continue.\n " +
                                "2 - I want to redo.");

                int answer = checkingForException(scanner, 1, 2);

                switch (answer) {
                    case 1:
                        new CalculateTotalMenu(scanner, user);
                        break;
                    case 2:
                        new PhotoTypeOptionMenu(scanner, user);
                        break;
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("---------\nThere is no such options. Please try again.\n---------");
            } catch (NotAnIntegerException e) {
                System.out.println("---------\nInvalid input: Not an integer\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }
}

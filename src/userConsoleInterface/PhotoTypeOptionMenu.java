package userConsoleInterface;

import exceptions.*;

import models.PhotoType;
import models.User;

import java.util.Scanner;

/**
 * Created by zhmas on 20.03.2023.
 */

public class PhotoTypeOptionMenu extends Menus implements IShowRedoMenu {
    public PhotoTypeOptionMenu(Scanner scanner, User user) {
        showMenu(scanner, user);
        showRedoMenu(scanner, user);
    }

    private void showMenu(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println("\nOk! How many people would take part in the photo shoot?");

                int numberOfPeople = checkingForException(scanner, 1, 50);
                user.setNumbersOfPeople(numberOfPeople);

                //Calculating the chosen Photo type based on the number of people
                PhotoType chosenType = null;
                for (PhotoType type : PhotoType.photoTypes) {
                    for (int i : type.getNumberOfPeople()) {
                        if (i == numberOfPeople) {
                            chosenType = type;
                            break;
                        }
                    }
                }
                user.setDesiredPhotoType(chosenType);

                if (chosenType != null) {
                    System.out.println("Got it! " +
                            "We can suggest the " + chosenType.getName() + " photo shoot. " +
                            chosenType.getDescription());
                }
                break;

            } catch (NoSuchOptionException e) {
                System.out.println("---------\nSorry, we only can service 1-50 people. Please adjust your number\n---------");
            } catch (NotAnIntegerException e) {
                System.out.println("---------\nInvalid input: Not an integer\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

    public void showRedoMenu(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println("\nDo you want to continue or to redo?\n 1 - Let's continue.\n 2 - I want to redo.");

                int answer = checkingForException(scanner, 1, 2);

                switch (answer) {
                    case 1:
                        new LocationOptionMenu(scanner, user);
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

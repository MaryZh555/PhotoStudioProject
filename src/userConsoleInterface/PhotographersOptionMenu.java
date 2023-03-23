package userConsoleInterface;

import exceptions.*;

import models.Photographer;
import models.User;

import java.util.List;
import java.util.Scanner;

/**
 * Created by zhmas on 20.03.2023.
 */

public class PhotographersOptionMenu extends Menus implements IShowRedoMenu {

    public PhotographersOptionMenu(Scanner scanner, User user) {
        System.out.println("Hello " + user.getName() + "!");
        showCategoryMenu(scanner, user);
        showNameMenu(scanner, user);
        showRedoMenu(scanner, user);
    }

    List<Photographer> category = null;

    private void showCategoryMenu(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println(" Please choose one category of our photographers: \n " +
                        "0 - Novice(1-2 years of experience)\n " +
                        "1 - Experienced(3-4 years of experience) \n " +
                        "2 - Experts(5-6 years of experience)");

                int answer = checkingForException(scanner, 0, 2);

                switch (answer) {
                    case 0:
                        category = Photographer.getNoviceList();
                        user.setDesiredPhotographerType("Novice");
                        break;
                    case 1:
                        category = Photographer.getExperiencedList();
                        user.setDesiredPhotographerType("Experienced");
                        break;
                    case 2:
                        category = Photographer.getExpertsList();
                        user.setDesiredPhotographerType("Expert");
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

    private void showNameMenu(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println("Please choose one of our " + user.getDesiredPhotographerType() + " photographers: ");
                int i = 0;
                for (Photographer photographer : category) {
                    System.out.println(i + " - " + photographer.getName());
                    i++;
                }

                int answer = checkingForException(scanner, 0, 1);

                String PhotographerName;
                switch (answer) {
                    case 0:
                        PhotographerName = category.get(0).getName();
                        user.setDesiredPhotographer(category.get(0));
                        break;
                    case 1:
                        PhotographerName = category.get(1).getName();
                        user.setDesiredPhotographer(category.get(1));
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                int cost = category.get(answer).getCost();

                System.out.println("You chose a " + user.getDesiredPhotographerType() + " photographer " +
                        PhotographerName + ". They would take " +
                        cost + "$ per hour.\n");
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("---------\nThere is no such options. Please try again.\n---------");
            } catch (NotAnIntegerException e) {
                System.out.println("---------\nInvalid input: Not an integer\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

    @Override
    public void showRedoMenu(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println("Do you want to continue or to redo?\n 1 - Let's continue.\n 2 - I want to redo.");

                int answer = checkingForException(scanner, 1, 2);

                switch (answer) {
                    case 1:
                        new PhotoTypeOptionMenu(scanner, user);
                        break;
                    case 2:
                        new PhotographersOptionMenu(scanner, user);
                        break;
                }
                break;
            } catch (NotAnIntegerException e) {
                System.out.println("---------\nInvalid input: Not an integer\n---------");
                scanner.next(); // clear the input buffer
            } catch (NoSuchOptionException e) {
                System.out.println("---------\nThere is no such options. Please try again.\n---------");
            }
        }
    }
}

package com.maryzh555.photo_studio.user_console_interface;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.Photographer;
import com.maryzh555.photo_studio.models.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zhmas on 20.03.2023.
 */

public class PhotographersOptionMenu implements IShowRedoMenu {

    public PhotographersOptionMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        System.out.println("Hi " + user.getName() + " " + user.getSurname() + "!");
        showCategoryMenu(scanner, order, photoStudio);
        showNameMenu(scanner, order);
        showRedoMenu(scanner, user, order, photoStudio);
    }

    List<Photographer> category = null;

    private void showCategoryMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Please choose one category of our photographers: \n  " +
                        "0 - Novice(1-2 years of experience)\n  " +
                        "1 - Experienced(3-4 years of experience) \n  " +
                        "2 - Experts(5-6 years of experience)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 0:
                        category = photoStudio.getFullPhotographersList().get(0);
                        order.setDesiredPhotographerType("Novice");
                        break;
                    case 1:
                        category = photoStudio.getFullPhotographersList().get(1);
                        order.setDesiredPhotographerType("Experienced");
                        break;
                    case 2:
                        category = photoStudio.getFullPhotographersList().get(2);
                        order.setDesiredPhotographerType("Expert");
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

    private void showNameMenu(Scanner scanner, Order order) {
        while (true) {
            try {
                System.out.println("Please choose one of our " + order.getDesiredPhotographerType() + " photographers: ");
                int i = 0;
                for (Photographer photographer : category) {
                    System.out.println("  " + i + " - " + photographer.getName());
                    i++;
                }

                int answer = scanner.nextInt();

                String PhotographerName;
                switch (answer) {
                    case 0:
                        PhotographerName = category.get(0).getName();
                        order.setDesiredPhotographer(category.get(0));
                        break;
                    case 1:
                        PhotographerName = category.get(1).getName();
                        order.setDesiredPhotographer(category.get(1));
                        break;
                    default:
                        throw new NoSuchOptionException();
                }
                int cost = category.get(answer).getHourlyRate();

                System.out.println("You chose a " + order.getDesiredPhotographerType() + " photographer " +
                        PhotographerName + ". They would take " +
                        cost + "$ per hour.\n");
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

    @Override
    public void showRedoMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Do you want to continue or to redo?\n " +
                        "1 - Let's continue.\n " +
                        "2 - I want to redo.");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new PhotoTypeOptionMenu(scanner, user, order, photoStudio);
                        break;
                    case 2:
                        new PhotographersOptionMenu(scanner, user, order, photoStudio);
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

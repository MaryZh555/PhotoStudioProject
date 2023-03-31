package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.Photographer;
import com.maryzh555.photo_studio.models.User;

/**
 * Created by Zhang M. on 20.03.2023.
 */

public class PhotographersOptionMenu implements IShowRedoMenu {
    public PhotographersOptionMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        System.out.println("Hi " + user.getName() + " " + user.getSurname() + "!");
        showYearsOptionsMenu(scanner, user, order, photoStudio);
    }

    List<Photographer> list = null; //the list is used in both methods

    private void showYearsOptionsMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {

        while (true) {
            try {
                list = null; // empties the list when redo
                System.out.println("Please enter the number of years of experience you prefer for your photographer: \n" +
                        "* Note: Our photographers have between 1 to 6 years of experience.");
                int answer = scanner.nextInt();
                if (answer < 1 || answer > 6) throw new NoSuchOptionException();

                System.out.println("We have such photographers with " + answer + " years of experience: ");

                list = photoStudio.matchPhotographers(answer);
                int i = 1;
                for (Photographer photographer : list) {
                    System.out.println("  " + i + " - " + photographer.getName() +
                            " (" + photographer.getHourlyRate() + "$/hour)");
                    i++;
                }

                if(list.size() == 0 ){ //For rare cases
                    System.out.println("*Oops* It seems like we didn't find the photographer with needed years of experience. " +
                            "Let's try again\n");
                    photoStudio = new PhotoStudio();
                    showYearsOptionsMenu(scanner, user, order, photoStudio);
                } else if (list.size() == 1) {
                    System.out.println("They are the only one, who might suit you.");
                    order.setDesiredPhotographer(list.get(0));
                    showRedoMenu(scanner, user, order, photoStudio);
                } else {
                    showResultNamesMenu(scanner, user, order, photoStudio);
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

    private void showResultNamesMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Which one would you prefer?");
                int answer = scanner.nextInt();
                if (answer > list.size() || answer < 1) throw new NoSuchOptionException();
                order.setDesiredPhotographer(list.get(answer - 1));

                System.out.println("You chose a photographer " + order.getDesiredPhotographer().getName() +
                        ", who has " + order.getDesiredPhotographer().getYearsOfExperience() + " years of experience. " +
                        "They will take " + order.getDesiredPhotographer().getHourlyRate() + "$ per hour.\n");
                showRedoMenu(scanner, user, order, photoStudio);
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

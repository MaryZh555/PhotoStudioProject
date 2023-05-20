package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.EmptyListException;
import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Photographer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class PhotographersOptionMenu extends Menu {
    public PhotographersOptionMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                if (order.getDesiredPhotographer() != null) {
                    System.out.println("You already chose " + order.getDesiredPhotographer().getName() +
                            ". Do you want to chose another one?" +
                            "\n 1 - Chose the new one" +
                            "\n 2 - Back to the Order Menu");
                    int answer = scanner.nextInt();
                    switch (answer) {
                        case 2:
                            new OrderMenu(scanner, order, photoStudio);
                            break;
                        case 1:
                            order.setDesiredPhotographer(null);
                            showMenu(scanner, order, photoStudio);
                            break;
                        default:
                            throw new NoSuchOptionException();
                    }
                } else{
                    showMenu(scanner, order, photoStudio);
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public <T extends OrderOrClient> void showMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio){
        System.out.println("Hi " + ((Order)orderOrClient).getClient().getName() + " " + ((Order)orderOrClient).getClient().getSurname() + "!");
        List<Photographer> list;
        while (true) {
            try {
                int[] maxMinValues = photoStudio.getCustomerManager().calculateYearsOfExperience(photoStudio);
                System.out.println("Please enter the number of years of experience you prefer for your photographer: " +
                        "\n* Note: Our photographers have between " + maxMinValues[0] + " to " + maxMinValues[1] + " years of experience.");
                int answer = scanner.nextInt();
                if (answer < maxMinValues[0] || answer > maxMinValues[1]) throw new NoSuchOptionException();

                System.out.println("Let's see which photographers have " + answer + " years of experience: ");

                list = photoStudio.getCustomerManager().matchPhotographers(answer, photoStudio);

                if (list.size() == 0) { //For cases when there is no photographers with needed experience
                    System.out.println("Unfortunately, we don't have any photographer with " + answer + " years of experience.\n" +
                            "But we can suggest some with similar experience: ");
                    list = photoStudio.getCustomerManager().findAlternativePhotographers(answer, photoStudio);
                }

                int i = 1;
                for (Photographer photographer : list) {
                    System.out.println("  " + i + " - " + photographer.getName() +
                            " (" + photographer.getYearsOfExperience() + " years, " + photographer.getHourlyRate() + "$/hour)");
                    i++;
                }

                if (list.size() == 0) {
                    throw new EmptyListException("PHOTOGRAPHERS");
                } else if (list.size() == 1) {
                    System.out.println("They are the only one, who might suit you.");
                    ((Order)orderOrClient).setDesiredPhotographer(list.get(0));
                    new RedoMenu(scanner, ((Order)orderOrClient), photoStudio, this);//returns to this showMenu
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (EmptyListException e) {
                System.out.println(e.getMessage());
                System.exit(0);//end of the program
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }

        if (list.size() > 1) {
            while (true) {
                try {
                    System.out.println("Which one would you prefer?");
                    int answer = scanner.nextInt();
                    if (answer > list.size() || answer < 1) throw new NoSuchOptionException();
                    ((Order)orderOrClient).setDesiredPhotographer(list.get(answer - 1));

                    System.out.println("You chose a photographer " + ((Order)orderOrClient).getDesiredPhotographer().getName() +
                            ", who has " + ((Order)orderOrClient).getDesiredPhotographer().getYearsOfExperience() + " years of experience. " +
                            "They will take " + ((Order)orderOrClient).getDesiredPhotographer().getHourlyRate() + "$ per hour.\n");

                    new RedoMenu(scanner, ((Order)orderOrClient), photoStudio, this);//redo to this showMenu

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
}

package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.EmptyListException;
import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Photographer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class PhotographersOptionMenu extends Menu{
    public PhotographersOptionMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, order, photoStudio);
    }

    public void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        System.out.println("Hi " + order.getClient().getName() + " " + order.getClient().getSurname() + "!");
        List<Photographer> list;
        while (true) {
            try {
                System.out.println("Please enter the number of years of experience you prefer for your photographer: " +
                        "\n* Note: Our photographers have between 1 to 6 years of experience."); //todo #upgrade : method to find max and min years photographers
                int answer = scanner.nextInt();
                if (answer < 1 || answer > 6) throw new NoSuchOptionException();

                System.out.println("Let's see which photographers have " + answer + " years of experience: ");

                list = callCustomerManager(photoStudio).matchPhotographers(answer, photoStudio);

                if (list.size() == 0) { //For cases when there is no photographers with needed experience
                    System.out.println("Unfortunately, we don't have any photographer with " + answer + " years of experience.\n" +
                            "But we can suggest some with similar experience: ");
                    list = callCustomerManager(photoStudio).findAlternativePhotographers(answer, photoStudio);
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
                    order.setDesiredPhotographer(list.get(0));
                    new RedoMenu(scanner, order, photoStudio, this);
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

        if(list.size() > 1) {
            while (true) {
                try {
                    System.out.println("Which one would you prefer?");
                    int answer = scanner.nextInt();
                    if (answer > list.size() || answer < 1) throw new NoSuchOptionException();
                    order.setDesiredPhotographer(list.get(answer - 1));

                    System.out.println("You chose a photographer " + order.getDesiredPhotographer().getName() +
                            ", who has " + order.getDesiredPhotographer().getYearsOfExperience() + " years of experience. " +
                            "They will take " + order.getDesiredPhotographer().getHourlyRate() + "$ per hour.\n");

                    new RedoMenu(scanner, order, photoStudio, this);

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

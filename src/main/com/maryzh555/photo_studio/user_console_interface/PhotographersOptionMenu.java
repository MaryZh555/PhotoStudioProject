package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.EmptyListException;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.interfaces.ShowQuitMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.test;
import com.maryzh555.photo_studio.models.users.Photographer;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class PhotographersOptionMenu implements IShowRedoMenu, ShowQuitMenu {
    public PhotographersOptionMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        System.out.println("Hi " + order.getClient().getName() + " " + order.getClient().getSurname() + "!");
        showYearsOptionsMenu(scanner, order, photoStudio);
    }

    private void showYearsOptionsMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Please enter the number of years of experience you prefer for your photographer: " +
                        "\n* Note: Our photographers have between 1 to 6 years of experience."); //todo #upgrade : method to find max and min years photographers
                int answer = scanner.nextInt();
                if (answer < 1 || answer > 6) throw new NoSuchOptionException();

                System.out.println("Let's see which photographers have " + answer + " years of experience: ");

                List<Photographer> list = photoStudio.getDirector().getCustomerManager().matchPhotographers(answer, photoStudio);

                if (list.size() == 0) { //For cases when there is no photographers with needed experience
                    System.out.println("Unfortunately, we don't have any photographer with " + answer + " years of experience.\n" +
                            "But we can suggest some with similar experience: ");
                    list = photoStudio.getDirector().getCustomerManager().findAlternativePhotographers(answer, photoStudio);
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
                    showRedoMenu(scanner, order, photoStudio);
                } else {
                    showResultNamesMenu(scanner,order, photoStudio, list);
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
    }

    private void showResultNamesMenu(Scanner scanner, Order order, PhotoStudio photoStudio, List<Photographer> list) {
        while (true) {
            try {
                System.out.println("Which one would you prefer?");
                int answer = scanner.nextInt();
                if (answer > list.size() || answer < 1) throw new NoSuchOptionException();
                order.setDesiredPhotographer(list.get(answer - 1));

                System.out.println("You chose a photographer " + order.getDesiredPhotographer().getName() +
                        ", who has " + order.getDesiredPhotographer().getYearsOfExperience() + " years of experience. " +
                        "They will take " + order.getDesiredPhotographer().getHourlyRate() + "$ per hour.\n");
                showRedoMenu(scanner,order, photoStudio);
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
    public void showRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nDo you want to continue or to redo?" +
                        "\n 1 - Let's continue." +
                        "\n 2 - Redo." +
                        "\n 3 - Go to previous ->(Client Data)" + //check if the go to previous doesn't saved as new order and saved correctly
                        "\n 4 - Leave(quit)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                            test.testAvailablePhotographers(photoStudio);//todo test
                        new PhotoTypeOptionMenu(scanner,  order, photoStudio);
                        break;
                    case 2:
                        new PhotographersOptionMenu(scanner, order, photoStudio);
                        break;
                    case 3:
                        new ClientDataFormMenu(new Scanner(System.in), photoStudio); //check if the new Scanner fix the problem with error Name when go to previous from Photographers
                        break;
                    case 4:
                        showQuitMenu(scanner,  order, photoStudio);
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

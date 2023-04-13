package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.models.Photo;
import com.maryzh555.photo_studio.models.PhotoStudio;

/**
 * @author Zhang M. on 20.03.2023.
 */
public class MainMenu {
    public MainMenu(PhotoStudio photoStudio) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Lumina Photo Studio!");
            photoStudio.getDirector().getCustomerManager().introduceYourself();
            clientDistributionMenu(scanner, photoStudio); //can be upgraded, add employeeDistributionMenu
        }
    }

    public void clientDistributionMenu(Scanner scanner,PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Do you want to make order, or to pick up printed photo?\n" +
                        " 1 - Make an order.\n" +
                        " 2 - Pick my photos.");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        //new Scanner resolves the bug when the NameForm instantly trows an WrongNameException
                        new ClientDataFormMenu(new Scanner(System.in), photoStudio);
                        break;
                    case 2:
                        showPickUpMenu(scanner, photoStudio);
                        break;
                    default:
                        throw new NoSuchOptionException();
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("It seems like we don't have an order with this id, please check if the inputted id is correct");
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

    public void showPickUpMenu(Scanner scanner, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Please enter your order id: ");
                int id = scanner.nextInt();
//                    test.printStoragePhotoPackList(photoStudio); //todo  test
                List<Photo> pack = photoStudio.getDirector().getSupplyManager().findPickUpPhotoPack(id, photoStudio);
                if (pack == null) {
                    throw new NoSuchOptionException();
                }
                photoStudio.getStorage().takeFromTheStorage(pack);
//                test.printStoragePhotoPackList(photoStudio);//todo  test
                System.out.println("Here you go. Your " + pack.size() +
                        " photos are ready. Have a nice day!");
                new NewCustomerMenu(scanner, photoStudio);
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("---------\n" +
                        "It seems like we don't have an order with this id, please check if the inputted id is correct" +
                        "\n---------");
                showQuitMenu(scanner, photoStudio);
                break;
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

    public void showQuitMenu(Scanner scanner, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println(" Do you want to return next time, when you check your order id? " +
                        "\n 1 - Yes, I will come back.(quit)" +
                        "\n 2 - No, i just mistyped it. Let me input again.");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new NewCustomerMenu(scanner, photoStudio);
                        break;
                    case 2:
                        showPickUpMenu(scanner, photoStudio);
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





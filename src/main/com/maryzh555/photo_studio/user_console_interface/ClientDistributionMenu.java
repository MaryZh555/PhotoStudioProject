package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 15.04.2023.
 */
public class ClientDistributionMenu {

    public ClientDistributionMenu(Scanner scanner, PhotoStudio photoStudio) {
        showMenu(scanner, photoStudio);
    }

    public void showMenu(Scanner scanner, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Do you want to make order, or to pick up printed photo?\n" +
                        " 1 - Make an order.\n" +
                        " 2 - Pick up my photos.\n" +
                        " 3 - Leave(quit)");

                int answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        scanner.nextLine();
                        new ClientDataFormMenu(scanner, new Order(), photoStudio);
                        break;
                    case 2:
                        new PickUpPhotoMenu(scanner, photoStudio);
                        break;
                    case 3:
                        new NewCustomerMenu(scanner, photoStudio);
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
}

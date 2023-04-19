package main.com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.enums.PhotoType;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class PhotoTypeOptionMenu extends Menu{
    public PhotoTypeOptionMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, order, photoStudio);
    }

     public void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Ok! How many people would take part in the photo shoot?");

                int answer = scanner.nextInt();
                if (answer < 1 || answer > 50) throw new NoSuchOptionException();

                PhotoType chosenType = photoStudio.getDirector().getCustomerManager().matchPhotoType(answer);
                order.getOrderedPhoto().setType(chosenType);

                System.out.println("Got it! " +
                        "We can suggest the " + chosenType + " photo shoot. \n" +
                        chosenType.getDescription());
                new RedoMenu(scanner, order, photoStudio, this);//showRedoMenu(scanner, order, photoStudio);
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("---------\n" +
                        "Sorry, we only can only service 1-50 people. Please adjust your number" +
                        "\n---------");
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }
}

package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.enums.PhotoType;
import main.com.maryzh555.photo_studio.models.users.CustomerManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class PhotoTypeOptionMenu extends Menu{
    public PhotoTypeOptionMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                if(order.getOrderedPhoto().getType() != null){
                    System.out.println("You already chose " + order.getOrderedPhoto().getType().name() +
                            ". Do you want to chose another one?" +
                            "\n 1 - Chose the new one" +
                            "\n 2 - Back to the Order Menu");
                    int answer = scanner.nextInt();

                    switch (answer) {
                        case 2:
                            new OrderMenu(scanner, order, photoStudio);
                            break;
                        case 1:
                            order.getOrderedPhoto().setType(null);
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
        while (true) {
            try {
                System.out.println("Ok! How many people would take part in the photo shoot?");

                int answer = scanner.nextInt();
                if (answer < 1 || answer > 50) throw new NoSuchOptionException();

                PhotoType chosenType = callWorker(photoStudio, CustomerManager.class).matchPhotoType(answer);
                ((Order)orderOrClient).getOrderedPhoto().setType(chosenType);

                System.out.println("Got it! " +
                        "We can suggest the " + chosenType + " photo shoot. \n" +
                        chosenType.getDescription());
                new RedoMenu(scanner, ((Order)orderOrClient), photoStudio, this);
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

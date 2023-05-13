package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 01.05.2023.
 */
public class OrderMenu extends Menu {

    public <T extends OrderOrClient> OrderMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio) {
        showMenu(scanner, orderOrClient, photoStudio);
    }

    @Override
    public <T extends OrderOrClient> void showMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio) {
        Order order;
        if (orderOrClient instanceof Client) {
            order = new Order();
            order.setClient((Client) orderOrClient);
        } else {
            order = (Order) orderOrClient;
        }

        //test.testOrderMenu(order);//test

        while (true) {
            try {
                System.out.println("-- Order Menu --");
                System.out.println("Choose an option:" +
                        "\n 1 - Choose Photographer: " + (order.getDesiredPhotographer() != null ? order.getDesiredPhotographer().getName() : "N/A") + "(current)" +
                        "\n 2 - Choose Photo type: " + (order.getOrderedPhoto().getType() != null ? order.getOrderedPhoto().getType().name() : "N/A" + "(current)") +
                        "\n 3 - Choose Location: " + (order.getDesiredLocation() != null ? order.getDesiredLocation().name() : "N/A" + "(current)") +
                        "\n 4 - Choose Printing options: " + (order.getOrderedPhoto().getPrintStandardQty() != -1 ? order.getOrderedPhoto().isToPrint() : "N/A" + "(current)") +
                        "\n 5 - Check out " +
                        "\n 6 - Go to previous -> (Option Menu)" +
                        "\n 7 - Exit menu");
                int answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        new PhotographersOptionMenu(scanner, order, photoStudio);
                        break;
                    case 2:
                        new PhotoTypeOptionMenu(scanner, order, photoStudio);
                        break;
                    case 3:
                        new LocationOptionMenu(scanner, order, photoStudio);
                        break;
                    case 4:
                        new PrintingMenu(scanner, order, photoStudio);
                        break;
                    case 5:
                        if (photoStudio.checkIfReadyToCheckOut(order)) {
                            new CalculateTotalMenu(scanner, order, photoStudio);
                        } else {
                            System.out.println("ERROR: Please make sure you chose Photographer, Photo Type, Locations and Printing option before you check out.");
                            showMenu(scanner, order, photoStudio);
                        }
                        break;
                    case 6:
                        new ClientOptionMenu(scanner, order.getClient(), photoStudio);
                        break;
                    case 7:
                        new QuitMenu(scanner, order, photoStudio, this);
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
                scanner.next();
            }
        }
    }
}

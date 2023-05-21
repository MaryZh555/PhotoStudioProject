package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 30.04.2023.
 */
public class ClientOptionMenu extends Menu {

    public ClientOptionMenu(Scanner scanner, Client client, PhotoStudio photoStudio) {
        showMenu(scanner, client, photoStudio);
    }

    @Override
    public void showMenu(Scanner scanner, Client client, PhotoStudio photoStudio) {
        System.out.println("\nWelcome to the Option Menu!");
        while (true) {
            try {
                System.out.println("Choose option:" +
                        "\n 1 - Make an order" +
                        "\n 2 - Pick up printed photos" +
                        "\n 3 - Log out");
                int answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        Order order = new Order();
                        order.setClient(client);
                        new OrderMenu(scanner, order, photoStudio);
                        break;
                    case 2:
                        if (client.getOrderList().isEmpty()) {
                            System.out.println("You didn't order any photos yet.");
                            showMenu(scanner, client, photoStudio);
                        } else {
                            new PickUpPhotoMenu(scanner, client, photoStudio);
                        }
                        break;
                    case 3:
                        new QuitMenu(scanner, null, photoStudio, this); //todo does the order in the quit menu should be null?
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
                scanner.next();
            }
        }
    }

}

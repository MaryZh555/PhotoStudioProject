package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
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
    public <T extends OrderOrClient> void showMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio) {
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
                        new OrderMenu(scanner, ((Client)orderOrClient), photoStudio);
                        break;
                    case 2:
                        if (((Client)orderOrClient).getOrderList().isEmpty()) {
                            System.out.println("You didn't order any photos yet.");
                            showMenu(scanner, ((Client)orderOrClient),photoStudio);
                        } else {
                            new PickUpPhotoMenu(scanner, ((Client)orderOrClient), photoStudio);
                        }
                        break;
                    case 3:
                        new QuitMenu(scanner, null, photoStudio, this);
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

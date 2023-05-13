package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 15.04.2023.
 */
public class ClientDistributionMenu extends Menu {

    public ClientDistributionMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, order, photoStudio);
    }

    @Override
    public <T extends OrderOrClient> void showMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio){
        while (true) {
            try {
                System.out.println("Please choose:" +
                        "\n 1 - Register" +
                        "\n 2 - Sign In" +
                        "\n 3 - Leave(quit)");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        scanner.nextLine();
                        new RegisterSignInMenu(scanner, "Register", photoStudio);
                        break;
                    case 2:
                        scanner.nextLine();
                        new RegisterSignInMenu(scanner, "SignIn", photoStudio);
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
                scanner.next(); // clear the input buffer
            }
        }
    }
}

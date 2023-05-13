package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.IShowQuitMenu;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 15.04.2023.
 */
public class QuitMenu implements IShowQuitMenu {

    public QuitMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu) {
        showQuitMenu(scanner, order, photoStudio, menu);
    }

    @Override
    public void showQuitMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu) {
        while (true) {
            try {
                System.out.println(" Are you sure you want to quit? " +
                        "\n 1 - Yes(quit)" +
                        "\n 2 - No ->(Redo)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new NewCustomerMenu(scanner, photoStudio);
                        break;
                    case 2:
                        menu.callRedoMenu(scanner, order, photoStudio, menu);
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

package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 19.04.2023.
 */
public class UserDistributionMenu extends Menu {

    public UserDistributionMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, order, photoStudio);
    }

    @Override
    public  void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio){
        while (true) {
            try {
                System.out.println("Are you a client or a job candidate?\n" +
                        " 1 - Client.\n" +
                        " 2 - Job candidate.\n" +
                        " 3 - Leave(quit)");

                int answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        new ClientDistributionMenu(scanner, order, photoStudio);
                        break;
                    case 2:
                        new CandidateMenu(scanner, photoStudio);
                        break;
                    case 3:
                        new NewCustomerMenu(scanner, photoStudio);
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

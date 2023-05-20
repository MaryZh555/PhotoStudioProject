package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 07.04.2023.
 */
public class NewCustomerMenu extends Menu{
    public NewCustomerMenu(Scanner scanner, PhotoStudio photoStudio) {
        showNewCustomerMenu(scanner, photoStudio);//uses this instance of PhotoStudio, but creates new order and user to work with
    }
    private void showNewCustomerMenu(Scanner scanner, PhotoStudio photoStudio){
        while (true) {
            try {
                System.out.println("\n(Is there any customers left?)\n" +
                        " 1 - (No, they was the last one)(end the program)\n" +
                        " 2 - (Yes, there are more customers to serve.)");
                int answer = scanner.nextInt();
//                test.printOrderList(photoStudio); //test
                switch (answer){
                    case 1:
                        System.out.println("Great! Let's close the shift!");

                        //reports
                        photoStudio.getDirector().askForReport(photoStudio);
                        break;
                    case 2:
                        //test.testAvailablePhotographers(photoStudio);//test
                        new MainMenu(photoStudio);
                        break;
                    default: throw new NoSuchOptionException();
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next();// clear the input buffer, stops the infinite loop
            }
        }
    }
}

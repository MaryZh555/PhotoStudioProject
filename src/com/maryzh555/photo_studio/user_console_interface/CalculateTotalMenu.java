package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.humans.User;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class CalculateTotalMenu {
    public CalculateTotalMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, user, order, photoStudio);
        showNewCustomerMenu(scanner, photoStudio);//uses this instance of PhotoStudio, but creates new order and user to work with
    }

    private void showMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                int resultTotal = order.calculateTotal(order);
                order.setTotal(resultTotal);

                System.out.println("  Your order is a " + order.getDesiredPhotoType() +
                        " photo type, with the photographer " +
                        order.getDesiredPhotographer().getName() +
                        ". And location you are renting is the " +
                        order.getDesiredLocation() + ".");
                System.out.println("  For printing you chose " + order.getPrintStandard() + " copies of STANDARD sized photo, "
                        + order.getPrintLarge() + " copies of LARGE sized photo, and " +
                        order.getPrintProfessional() + " copies of PROFESSIONAL sized photo.");

                System.out.println("It will cost you " + order.getTotal() + "$ total.\n");

                System.out.println("Is it ok, or you want to redo your order?\n " +
                        "1 - It's fine.\n " +
                        "2 - I want to redo the whole order.");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        System.out.println("Great! See you in our Photo Studio, " + user.getName() +
                                "! Your order id is #" + order.getId() +
                                ". \nWe will contact you using the telephone number provided. " +
                                "Have a nice day!");
                        break;
                    case 2:
                        new MainMenu(photoStudio, user, order); //returns to the first menu
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
                scanner.next();// clear the input buffer, stops the infinite loop
            }
        }
    }

    private void showNewCustomerMenu(Scanner scanner, PhotoStudio photoStudio){
        while (true) {
            try {
                System.out.println("\n(Is there any customers left?)\n" +
                        " 1 - (No, they was the last one.)\n" +
                        " 2 - (Yes, there are more customers to serve. Let's open a new order.)");
                int answer = scanner.nextInt();
                switch (answer){
                    case 1:
                        System.out.println("Great! Let's close the shift!");
                        scanner.close();
                        break;
                    case 2:
                        new MainMenu(photoStudio, new User(), new Order());
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

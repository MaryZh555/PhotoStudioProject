package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.User;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class CalculateTotalMenu {
    public CalculateTotalMenu(Scanner scanner, User user, Order order) {
        showMenu(scanner, user, order);
    }

    private void showMenu(Scanner scanner, User user, Order order) {
        while (true) {
            try {
                int resultTotal = order.calculateTotal(order);
                order.setTotal(resultTotal);

                System.out.println("Your order is a " + order.getDesiredPhotoType() +
                        " photo type, with the photographer " +
                        order.getDesiredPhotographer().getName() +
                        ". And location you are renting is the " +
                        order.getDesiredLocation() + ".");

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
                        new MainMenu(); //returns to the first menu
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
}

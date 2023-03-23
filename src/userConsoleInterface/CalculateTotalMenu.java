package userConsoleInterface;

import exceptions.*;

import models.User;

import java.util.Scanner;

/**
 * Created by zhmas on 20.03.2023.
 */

public class CalculateTotalMenu extends Menus {
    public CalculateTotalMenu(Scanner scanner, User user) {
        showMenu(scanner, user);
    }

    private void showMenu(Scanner scanner, User user) {
        while (true) {
            try {
                int total = (user.getDesiredPhotographer().getCost() * user.getDesiredPhotoType().getHours()) + user.getDesiredLocation().getCost();
                user.setTotal(total);

                System.out.println("Your order is a " + user.getDesiredPhotoType().getName() +
                        " photo type, with the " + user.getDesiredPhotographerType() + " photographer " +
                        user.getDesiredPhotographer().getName() +
                        ". And location you are renting is the " +
                        user.getDesiredLocation().getName() + ".");

                System.out.println("It will cost you " + total + "$ total.\n");

                System.out.println("Is it ok, or you want to redo your order?\n " +
                        "1 - It's fine.\n " +
                        "2 - I want to redo the whole order.");

                int answer = checkingForException(scanner, 1, 2);

                switch (answer) {
                    case 1:
                        System.out.println("Great! See you in our Photo Studio! Have a nice day!");
                        break;
                    case 2:
                        new PhotographersOptionMenu(scanner, user);
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("---------\nThere is no such option\n---------");
            } catch (NotAnIntegerException e) {
                System.out.println("---------\nInvalid input: Not an integer\n---------");
                scanner.next();// clear the input buffer, stops the infinite loop
            }
        }
    }
}

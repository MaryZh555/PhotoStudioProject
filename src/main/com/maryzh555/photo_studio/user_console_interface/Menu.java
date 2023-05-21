package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Client;

import java.util.Scanner;

/**
 * @author by Zhang M. on 16.04.2023.
 */
public class Menu implements IShowRedoMenu {

    public void showMenu(Scanner scanner, PhotoStudio photoStudio) {
    }

    public void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
    }

    public void showMenu(Scanner scanner, Client client, PhotoStudio photoStudio) {
    }

    public void callRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu) {
        new RedoMenu(scanner, order, photoStudio, menu);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }


    @Override
    public void showRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu) {

    }
}

package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.Scanner;
/**
 * @author Zhang M. on 20.03.2023.
 */
public class MainMenu extends Menu {

    public MainMenu(PhotoStudio photoStudio) {
        try (Scanner scanner = new Scanner(System.in)) {
            showMenu(scanner, photoStudio);
        }
    }

    private void showMenu(Scanner scanner, PhotoStudio photoStudio) {
        System.out.println("Welcome to the Lumina Photo Studio!");
        callCustomerManager(photoStudio).introduceYourself();
        new UserDistributionMenu(scanner, photoStudio);
    }
}





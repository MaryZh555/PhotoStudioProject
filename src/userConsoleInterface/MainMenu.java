package userConsoleInterface;

import java.util.Scanner;

import exceptions.*;
import models.*;
import models.User;

/**
 * Created by zhmas on 20.03.2023.
 */
public class MainMenu {
    public MainMenu() {
        try (Scanner scanner = new Scanner(System.in)) { //Auto close Scanner
            showMenu(scanner);
        }
    }

    public void showMenu(Scanner scanner) {
        Photographer.createPhotographers();
        Locations.createLocations();
        PhotoType.createPhotoTypes();
        User user = new User();

        while (true) {
        try {
            System.out.println("Please enter your name:");
            String name = scanner.nextLine();
            if (name.matches(".*\\d+.*") || name.trim().length() < 3 || !name.trim().matches("[a-zA-Z]+"))
                throw new NoSuchOptionException();

            user.setName(name.trim());
            new PhotographersOptionMenu(scanner, user);
            break;
        } catch (NoSuchOptionException e) {
            System.out.println(
                    "---------\n Name must contain at least 3 letters, cannot be empty, contain numbers or special symbols.\n---------");
        }
    }
    }


}





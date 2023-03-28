package com.maryzh555.photo_studio.user_console_interface;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.exceptions.WrongNameException;
import com.maryzh555.photo_studio.exceptions.WrongNumberException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.User;
import com.maryzh555.photo_studio.models.Order;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by zhmas on 20.03.2023.
 */
public class MainMenu implements IShowRedoMenu {
    public MainMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            PhotoStudio photoStudio = new PhotoStudio();
            User user = new User();
            Order order = new Order();

            showNameForm(scanner, user);
            showRedoMenu(scanner, user, order, photoStudio);
        }
    }

    public void showNameForm(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println("Please enter your NAME:");
                System.out.println(" * Note: " +
                        "The name should contain at least 3 English letters, " +
                        "and should not contain any numbers or special symbols.");
                String name = scanner.nextLine();

                if (name.matches(".*\\d+.*") || name.trim().length() < 3 || !name.trim().matches("[a-zA-Z]+"))
                    throw new WrongNameException();

                user.setName(name.trim());

                showSurnameForm(scanner, user);
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showSurnameForm(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println("Please enter your SURNAME: ");
                System.out.println(" * Note: " +
                        "The surname should contain at least 3 English letters, " +
                        "and should not contain any numbers or special symbols.");
                String surname = scanner.nextLine();

                if (surname.matches(".*\\d+.*") || surname.trim().length() < 3 || !surname.trim().matches("[a-zA-Z]+"))
                    throw new WrongNameException();

                user.setSurname(surname.trim());

                showContactNumberForm(scanner, user);
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showContactNumberForm(Scanner scanner, User user) {
        while (true) {
            try {
                System.out.println("Please enter your contact phone number: \n " +
                        "* Note: Phone number should only contain 10 digits, and start with a zero.");
                String contactNumber = scanner.nextLine();

                if (contactNumber.trim().length() < 10 || contactNumber.trim().matches("[a-zA-Z]+") || !contactNumber.matches(".*\\d+.*") || contactNumber.charAt(0) != '0')
                    throw new WrongNumberException();

                user.setContactNumber(contactNumber.trim());

                break;
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void showRedoMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Please check if the contact data are correct" +
                        "\n NAME: "+ user.getName() +
                        "\n SURNAME: "+ user.getSurname() +
                        "\n CONTACT NUMBER: "+ user.getContactNumber() );

                System.out.println("\nIs it correct?\n "+
                        "1 - Yes, the data is correct.\n " +
                        "2 - No, I want to rewrite.");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new PhotographersOptionMenu(scanner, user, order, photoStudio);
                        break;
                    case 2:
                        new MainMenu();
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





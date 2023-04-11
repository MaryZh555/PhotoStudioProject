package com.maryzh555.photo_studio.user_console_interface;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.exceptions.WrongNameException;
import com.maryzh555.photo_studio.exceptions.WrongNumberException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.users.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 07.04.2023.
 */
public class ClientDataFormMenu implements IShowRedoMenu {
    public ClientDataFormMenu(Scanner scanner,PhotoStudio photoStudio) {
        Order order = new Order(); //order is created only if the client want to make it //resolves the problem with order creates for pick up
        Client orderClient = new Client();
        order.setClient(orderClient);
        showNameForm(scanner, orderClient);
        showRedoMenu(scanner, orderClient, order, photoStudio);
    }
    public void showNameForm(Scanner scanner, Client orderClient) {
        while (true) {
            try {
                System.out.println("Please enter your NAME:");
                System.out.println(" * Note: " +
                        "The name should contain at least 3 English letters, " +
                        "and should not contain any numbers or special symbols.");

                String name = scanner.nextLine();
                if (name.matches(".*\\d+.*") ||
                        name.trim().length() < 3 ||
                        !name.trim().matches("[a-zA-Z]+"))
                    throw new WrongNameException();

                orderClient.setName(name.trim());

                showSurnameForm(scanner, orderClient);
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showSurnameForm(Scanner scanner, Client orderClient) {
        while (true) {
            try {
                System.out.println("Please enter your SURNAME: ");
                System.out.println(" * Note: " +
                        "The surname should contain at least 3 English letters, " +
                        "and should not contain any numbers or special symbols.");
                String surname = scanner.nextLine();

                if (surname.matches(".*\\d+.*") ||
                        surname.trim().length() < 3 ||
                        !surname.trim().matches("[a-zA-Z]+"))
                    throw new WrongNameException();

                orderClient.setSurname(surname.trim());

                showContactNumberForm(scanner, orderClient);
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showContactNumberForm(Scanner scanner, Client orderClient) {
        while (true) {
            try {
                System.out.println("Please enter your contact PHONE NUMBER: \n " +
                        "* Note: Phone number should only contain 10 digits, " +
                        "and start with a zero.");
                String contactNumber = scanner.nextLine();

                if (contactNumber.trim().length() != 10 ||
                        contactNumber.trim().matches("[a-zA-Z]+") ||
                        !contactNumber.matches(".*\\d+.*") ||
                        contactNumber.charAt(0) != '0')
                    throw new WrongNumberException();

                orderClient.setContactNumber(contactNumber.trim());

                break;
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void showRedoMenu(Scanner scanner, Client orderClient, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Please check if the contact data is correct" +
                        "\n NAME: " + orderClient.getName() +
                        "\n SURNAME: " + orderClient.getSurname() +
                        "\n CONTACT NUMBER: " + orderClient.getContactNumber());

                System.out.println("\nIs it correct?\n " +
                        "1 - Yes, the data is correct.\n " +
                        "2 - No, I want to rewrite.");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new PhotographersOptionMenu(scanner, orderClient, order, photoStudio);
                        break;
                    case 2:
                        new MainMenu(photoStudio);
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

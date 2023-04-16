package com.maryzh555.photo_studio.user_console_interface;

import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.exceptions.WrongNameException;
import com.maryzh555.photo_studio.exceptions.WrongNumberException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.interfaces.ShowQuitMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.users.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 07.04.2023.
 */
public class ClientDataFormMenu implements IShowRedoMenu, ShowQuitMenu {
    public ClientDataFormMenu(Scanner scanner, PhotoStudio photoStudio) {
        Order order = new Order(); //order is created only if the client want to make it //resolves the problem with order creates for pick up
        showNameForm(scanner, order, photoStudio);
    }

    public void showNameForm(Scanner scanner, Order order, PhotoStudio photoStudio) {
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

                order.getClient().setName(name.trim());

                showSurnameForm(scanner, order, photoStudio);
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showSurnameForm(Scanner scanner, Order order, PhotoStudio photoStudio) {
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

                order.getClient().setSurname(surname.trim());

                showContactNumberForm(scanner, order, photoStudio);
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showContactNumberForm(Scanner scanner, Order order, PhotoStudio photoStudio) {
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

                order.getClient().setContactNumber(contactNumber.trim());

                showRedoMenu(scanner, order, photoStudio);
                break;
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void showRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Please check if the contact data is correct" +
                        "\n NAME: " + order.getClient().getName() +
                        "\n SURNAME: " + order.getClient().getSurname() +
                        "\n CONTACT NUMBER: " + order.getClient().getContactNumber());

                System.out.println("\nIs it correct?" +
                        "\n 1 - Yes, the data is correct" +
                        "\n 2 - No, the data is incorrect" +
                        "\n 3 - Go to previous ->(Order/Pick up)" +
                        "\n 4 - I want to leave (quit)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new PhotographersOptionMenu(scanner, order, photoStudio);
                        break;
                    case 2:
                        showNameForm(scanner, order, photoStudio);
                        break;
                    case 3:
                        new MainMenu(photoStudio);
                        break;
                    case 4:
                        showQuitMenu(scanner, order, photoStudio);
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

    @Override
    public void showQuitMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println(" Are you sure you want to quit? " +
                        "\n 1 - Yes(quit)" +
                        "\n 2 - No ->(Redo)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new NewCustomerMenu(scanner, photoStudio);
                        break;
                    case 2:
                        showRedoMenu(scanner, order, photoStudio);
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

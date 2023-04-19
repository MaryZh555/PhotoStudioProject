package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.WrongNameException;
import main.com.maryzh555.photo_studio.exceptions.WrongNumberException;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.Scanner;

/**
 * @author by Zhang M. on 07.04.2023.
 */
public class ClientDataFormMenu extends Menu{
    public ClientDataFormMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, order, photoStudio);
    }


    public void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
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
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
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
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
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

                System.out.println("Please check if the contact data is correct" +
                        "\n NAME: " + order.getClient().getName() +
                        "\n SURNAME: " + order.getClient().getSurname() +
                        "\n CONTACT NUMBER: " + order.getClient().getContactNumber());

                new RedoMenu(scanner, order, photoStudio, this);//this.redoMenu = new RedoMenu(scanner, order, photoStudio, this);//showRedoMenu(scanner, order, photoStudio);
                break;
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

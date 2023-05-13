package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.exceptions.WrongNameException;
import main.com.maryzh555.photo_studio.exceptions.WrongNumberException;
import main.com.maryzh555.photo_studio.interfaces.IValidateName;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.test;
import main.com.maryzh555.photo_studio.models.users.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 29.04.2023.
 */
public class RegisterSignInMenu extends Menu implements IValidateName {

    public RegisterSignInMenu(Scanner scanner, String choice, PhotoStudio photoStudio) {
        showRegisterMenu(scanner, choice, photoStudio);
    }

    public void showRegisterMenu(Scanner scanner, String choice, PhotoStudio photoStudio) {
        Client client = new Client();
        //NAME
        while (true) {
            try {
                System.out.println("Please enter your NAME:");
                System.out.println(" * Note: " +
                        "The name should contain at least 3 English letters, " +
                        "and should not contain any numbers or special symbols.");
                String name = scanner.nextLine();
                if (validateName(name)) throw new WrongNameException();

                client.setName(name.trim());
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
        //SURNAME
        while (true) {
            try {
                System.out.println("Please enter your SURNAME: ");
                System.out.println(" * Note: " +
                        "The surname should contain at least 3 English letters, " +
                        "and should not contain any numbers or special symbols.");
                String surname = scanner.nextLine();

                if (validateName(surname)) throw new WrongNameException();

                client.setSurname(surname.trim());
                break;
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            }
        }
        //NUMBER
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

                client.setContactNumber(contactNumber.trim());

                /*System.out.println("Please c/heck if the contact data is correct" +
                        "\n NAME: " + client.getName() +
                        "\n SURNAME: " + client.getSurname() +
                        "\n CONTACT NUMBER: " + client.getContactNumber());*/
                /*
                new RedoMenu(scanner, null, photoStudio, this);
                */
                break;
            } catch (WrongNumberException e) {
                System.out.println(e.getMessage());
            }
        }

        if (choice.matches("Register")) {
            //CHECKING Register
            while (true) {
                try {
                    if (photoStudio.checkIfRegistered(client)) {
                        System.out.println("---------\n" +
                                "ERROR: The client with these data is already registered." +
                                "\n---------");
                        new RedoMenu(scanner, null, photoStudio, this);
                    } else {
                        System.out.println("Please check if the contact data is correct" +
                                "\n NAME: " + client.getName() +
                                "\n SURNAME: " + client.getSurname() +
                                "\n CONTACT NUMBER: " + client.getContactNumber());
                        System.out.println("\n Choose:" +
                                "\n 1 - Correct" +
                                "\n 2 - Redo" +
                                "\n 3 - Quit");
                        int answer = scanner.nextInt();
                        switch (answer) {
                            case 1:
                                System.out.println("Registration successful!");
                                photoStudio.addToRegisteredList(client);
                                    test.printRegisteredList(photoStudio);//test
                                new ClientOptionMenu(scanner, client, photoStudio);
                                break;
                            case 2:
                                scanner.nextLine();
                                showRegisterMenu(scanner, "Register", photoStudio);
                                break;
                            case 3:
                                new QuitMenu(scanner, null, photoStudio, this);
                                break;
                            default:
                                throw new NoSuchOptionException();
                        }
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
        } else if (choice.matches("SignIn")) {
            //CHECKING Sign In
            if (!photoStudio.checkIfRegistered(client)) {
                System.out.println("---------\n" +
                        "ERROR: No client with these data." +
                        "\n---------");
                new RedoMenu(scanner, null, photoStudio, this);
            } else {
                client = photoStudio.returnRegisteredClient(client);
                System.out.println("Sign In Successful!");
                new ClientOptionMenu(scanner, client, photoStudio);
            }
        }
    }

    @Override
    public boolean validateName(String string) {
        return string.matches(".*\\d+.*") ||
                string.trim().length() < 3 ||
                !string.trim().matches("[a-zA-Z]+");
    }
}

package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.enums.PhotoPaperType;
import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 05.04.2023.
 */
public class PrintingMenu extends Menu {
    public PrintingMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                if (order.getOrderedPhoto().getPrintStandardQty() != -1) {

                    String message = null;
                    if (order.getOrderedPhoto().getPrintStandardQty() != 0 ||
                            order.getOrderedPhoto().getPrintLargeQty() != 0 ||
                            order.getOrderedPhoto().getPrintProfessionalQty() != 0) {
                        message =       order.getOrderedPhoto().getPrintStandardQty() + " copies of STANDARD sized photo, " +
                                        order.getOrderedPhoto().getPrintLargeQty() + " copies of LARGE sized photo, and " +
                                        order.getOrderedPhoto().getPrintProfessionalQty() + " copies of PROFESSIONAL sized photo";
                    } else if(order.getOrderedPhoto().getPrintStandardQty() == 0 &&
                            order.getOrderedPhoto().getPrintLargeQty() == 0 &&
                            order.getOrderedPhoto().getPrintProfessionalQty() == 0){
                        message = "to have no printed photos";
                    }

                    System.out.println("You already chose " + message +
                            ". Do you want to change your choice?" +
                            "\n 1 - Change" +
                            "\n 2 - Back to the Order Menu");

                    int answer = scanner.nextInt();

                    switch (answer) {
                        case 2:
                            new OrderMenu(scanner, order, photoStudio);
                            break;
                        case 1:
                            order.getOrderedPhoto().setPrintStandardQty(-1);
                            showMenu(scanner, order, photoStudio);
                            break;
                        default:
                            throw new NoSuchOptionException();
                    }
                } else {
                    showMenu(scanner, order, photoStudio);
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public <T extends OrderOrClient> void showMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio){
        //TO PRINT true/false
        boolean toPrint = false;
        while (true) {
            try {
                System.out.println("\nDo you want us to print your photos?" +
                        "\n 1 - Yes! I want to have printed photos!" +
                        "\n 2 - No, digital ones are fine for me.");

                int answer = scanner.nextInt();
                if (answer < 1 || answer > 2) throw new NoSuchOptionException();

                switch (answer) {
                    case 1:
                        ((Order)orderOrClient).getOrderedPhoto().setToPrint(true);
                        System.out.println("Ok! So, we can offer you 3 types of photo paper sizes to print: ");
                        for (PhotoPaperType type : PhotoPaperType.values()) {
                            System.out.println(" - " + type + " (" + type.getSizeInInches() + ", " +
                                    type.getCostPerCopy() + "$ per copy)");
                        }
                        toPrint = true;
                        break;
                    case 2:
                        ((Order)orderOrClient).getOrderedPhoto().setToPrint(false);
                        ((Order)orderOrClient).getOrderedPhoto().setPrintStandardQty(0);
                        ((Order)orderOrClient).getOrderedPhoto().setPrintLargeQty(0);
                        ((Order)orderOrClient).getOrderedPhoto().setPrintProfessionalQty(0);
                        break;
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "Invalid input: Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
        if (toPrint) {
            // STANDARD SIZE QTY
            while (true) {
                try {
                    System.out.println("\nPlease enter how much STANDARD sized photo you want us to print.\n" +
                            " * Note : Maximum is 50, zero means no additional copies, and 1 means only one additional copy");
                    int answer = scanner.nextInt();
                    if (answer < 0 || answer > 50) throw new NoSuchOptionException();

                    ((Order)orderOrClient).getOrderedPhoto().setPrintStandardQty(answer);
                    break;
                } catch (NoSuchOptionException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("---------\n" +
                            "Invalid input: Not an integer" +
                            "\n---------");
                    scanner.next(); // clear the input buffer
                }
            }
            //LARGE SIZE QTY
            while (true) {
                try {
                    System.out.println("\nPlease enter how much LARGE sized photo you want us to print.\n" +
                            " * Note : Maximum is 25, 0 means no additional copies.");
                    int answer2 = scanner.nextInt();
                    if (answer2 < 0 || answer2 > 25) throw new NoSuchOptionException();

                    ((Order)orderOrClient).getOrderedPhoto().setPrintLargeQty(answer2);

                    break;
                } catch (NoSuchOptionException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("---------\n" +
                            "Invalid input: Not an integer" +
                            "\n---------");
                    scanner.next(); // clear the input buffer
                }
            }
            //PROFESSIONAL SIZE QTY
            while (true) {
                try {
                    System.out.println("\nPlease enter how much PROFESSIONAL sized photo you want us to print.\n" +
                            " * Note : Maximum is 10, 0 means no additional copies.");
                    int answer3 = scanner.nextInt();
                    if (answer3 < 0 || answer3 > 10) throw new NoSuchOptionException();

                    ((Order)orderOrClient).getOrderedPhoto().setPrintProfessionalQty(answer3);

                    break;
                } catch (NoSuchOptionException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("---------\n" +
                            "Invalid input: Not an integer" +
                            "\n---------");
                    scanner.next(); // clear the input buffer
                }

            }
            //COLORED true/false
            while (true) {
                try {
                    System.out.println("\nPlease select, do you want your printed photos to be black-and-white or colored?" +
                            "\n * Note: colored will cost +1$ per photo sheet" +
                            "\n 1 - Black-and-white sounds pretty good." +
                            "\n 2 - Colored ones are better.");

                    int answer = scanner.nextInt();
                    switch (answer) {
                        case 1:
                            ((Order)orderOrClient).getOrderedPhoto().setColored(false);
                            break;
                        case 2:
                            ((Order)orderOrClient).getOrderedPhoto().setColored(true);
                            break;
                        default:
                            throw new NoSuchOptionException();
                    }
                    break;
                } catch (NoSuchOptionException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("---------\n" +
                            "Invalid input: Not an integer" +
                            "\n---------");
                    scanner.next(); // clear the input buffer
                }
            }
            //SUMMARY
            if (((Order)orderOrClient).getOrderedPhoto().getPrintStandardQty() != 0 ||
                    ((Order)orderOrClient).getOrderedPhoto().getPrintLargeQty() != 0 ||
                    ((Order)orderOrClient).getOrderedPhoto().getPrintProfessionalQty() != 0) {
                System.out.println(
                        "You choose " +
                                ((Order)orderOrClient).getOrderedPhoto().getPrintStandardQty() + " copies of STANDARD sized photo, " +
                                ((Order)orderOrClient).getOrderedPhoto().getPrintLargeQty() + " copies of LARGE sized photo, and " +
                                ((Order)orderOrClient).getOrderedPhoto().getPrintProfessionalQty() + " copies of PROFESSIONAL sized photo.");
            }
            if (((Order)orderOrClient).getOrderedPhoto().getPrintStandardQty() == 0 &&
                    ((Order)orderOrClient).getOrderedPhoto().getPrintLargeQty() == 0 &&
                    ((Order)orderOrClient).getOrderedPhoto().getPrintProfessionalQty() == 0) {
                System.out.println("Well, you chose to have no printed photos.");
            }

        }
        new RedoMenu(scanner, ((Order)orderOrClient), photoStudio, this);
    }
}

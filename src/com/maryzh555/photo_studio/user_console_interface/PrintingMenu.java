package com.maryzh555.photo_studio.user_console_interface;

import com.maryzh555.photo_studio.enums.PhotoPaperType;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.users.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 05.04.2023.
 */
public class PrintingMenu implements IShowRedoMenu {
    public PrintingMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
//        test.paperTest(photoStudio);  //TODO test
//        test.printObject(photoStudio, order, client);// todo test
        showMenu(scanner, client, order, photoStudio);
    }

    private void showMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nDo you want us to print your photos?");
                System.out.println(" 1 - Yes! I want to have printed photos!\n" +
                        " 2 - No, digital one is fine for me.");
                int answer = scanner.nextInt();
                if (answer < 1 || answer > 2) throw new NoSuchOptionException();
                switch (answer) {
                    case 1:
                        order.getOrderedPhoto().setToPrint(true);
                        System.out.println("Ok! So, we can offer you 3 types of photo paper sizes to print: ");
                        for (PhotoPaperType type : PhotoPaperType.values()) {
                            System.out.println(" - " + type + " (" + type.getSizeInInches() + ", " +
                                    type.getCostPerCopy() + "$ per copy)");
                        }
                        showStandardSizeMenu(scanner, client, order, photoStudio);
                        break;
                    case 2:
                        order.getOrderedPhoto().setToPrint(false);
                        order.getOrderedPhoto().setPrintStandardQty(0);
                        order.getOrderedPhoto().setPrintLargeQty(0);
                        order.getOrderedPhoto().setPrintProfessionalQty(0);
                        showRedoMenu(scanner, client, order, photoStudio);
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
    }

    private void showStandardSizeMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nPlease enter how much STANDARD sized photo you want us to print.\n" +
                        " * Note : Maximum is 50, zero means no additional copies, and 1 means only one additional copy");
                int answer = scanner.nextInt();
                if (answer < 0 || answer > 50) throw new NoSuchOptionException();

                order.getOrderedPhoto().setPrintStandardQty(answer);

                showLargeSizeMenu(scanner, client, order, photoStudio);
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
    }

    private void showLargeSizeMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nPlease enter how much LARGE sized photo you want us to print.\n" +
                        " * Note : Maximum is 25, 0 means no additional copies.");
                int answer2 = scanner.nextInt();
                if (answer2 < 0 || answer2 > 25) throw new NoSuchOptionException();
                order.getOrderedPhoto().setPrintLargeQty(answer2);

//                test.paperTest(photoStudio);  //todo test

                showProfessionalSizeMenu(scanner, client, order, photoStudio);
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
    }

    private void showProfessionalSizeMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nPlease enter how much PROFESSIONAL sized photo you want us to print.\n" +
                        " * Note : Maximum is 10, 0 means no additional copies.");
                int answer3 = scanner.nextInt();
                if (answer3 < 0 || answer3 > 10) throw new NoSuchOptionException();
                order.getOrderedPhoto().setPrintProfessionalQty(answer3);

//                test.paperTest(photoStudio);  //todo test

                showColorMenu(scanner, client, order, photoStudio);
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
    }

    private void showColorMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nPlease select, do you want your printed photos to be black-and-white or colored?" +
                        "\n * Note: colored will cost +1$ per photo sheet" +
                        "\n 1 - Black-and-white sounds pretty good." +
                        "\n 2 - Colored ones are better.");

                int answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        order.getOrderedPhoto().setColored(false);
                        break;
                    case 2:
                        order.getOrderedPhoto().setColored(true);
                        break;
                    default:
                        throw new NoSuchOptionException();
                }

                showRedoMenu(scanner, client, order, photoStudio);
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
    }

    @Override
    public void showRedoMenu(Scanner scanner, Client client, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {

//                test.paperTest(photoStudio);  //todo test

                if (order.getOrderedPhoto().getPrintStandardQty() != 0 ||
                        order.getOrderedPhoto().getPrintLargeQty() != 0 ||
                        order.getOrderedPhoto().getPrintProfessionalQty() != 0) {
                    System.out.println(
                            "You choose " +
                                    order.getOrderedPhoto().getPrintStandardQty() + " additional copies of STANDARD sized photo, " +
                                    order.getOrderedPhoto().getPrintLargeQty() + " copies of LARGE sized photo, and " +
                                    order.getOrderedPhoto().getPrintProfessionalQty() + " copies of PROFESSIONAL sized photo.");
                }

                System.out.println("\nDo you want to calculate the total price or to redo your printing choice?\n " +
                        "1 - Let's continue.\n " +
                        "2 - I want to redo.");

                int answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        //here all the changes are submitted

                        //Using the paper from the studio to print
                        photoStudio.useStudioPhotoPaper("STANDARD", order.getOrderedPhoto().getPrintStandardQty());
                        photoStudio.useStudioPhotoPaper("LARGE", order.getOrderedPhoto().getPrintLargeQty());
                        photoStudio.useStudioPhotoPaper("PROFESSIONAL", order.getOrderedPhoto().getPrintProfessionalQty());

                        //adding to the photoPack (the printing result)
                        order.addToPhotoPack(order.getOrderedPhoto().getPrintStandardQty(), PhotoPaperType.STANDARD, order.getOrderedPhoto().isColored());
                        order.addToPhotoPack(order.getOrderedPhoto().getPrintLargeQty(), PhotoPaperType.LARGE, order.getOrderedPhoto().isColored());
                        order.addToPhotoPack(order.getOrderedPhoto().getPrintProfessionalQty(), PhotoPaperType.PROFESSIONAL, order.getOrderedPhoto().isColored());

//                        test.testPhotoPack(order);//todo test

                        new CalculateTotalMenu(scanner, client, order, photoStudio);
                        break;
                    case 2:
                        photoStudio.getStorage().setTotalUseOfPaper(0);
                        new PrintingMenu(scanner, client, order, photoStudio);
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

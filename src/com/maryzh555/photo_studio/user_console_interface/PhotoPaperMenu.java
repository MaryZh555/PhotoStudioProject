package com.maryzh555.photo_studio.user_console_interface;


import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.enums.PhotoPaper;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.humans.User;
import com.maryzh555.photo_studio.models.test;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author by Zhang M. on 05.04.2023.
 */
public class PhotoPaperMenu implements IShowRedoMenu {
    public PhotoPaperMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        test.paperTest(photoStudio);  //todo delete
        test.printObject(photoStudio, order, user);// todo delete
        showMenu(scanner, user, order, photoStudio);
    }

    private void showMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nDo you want us to print your photos?");
                System.out.println(" 1 - Yes! I want some more copies!\n" +
                        " 2 - No, 1 standard is fine for me.");
                int answer = scanner.nextInt();
                if (answer < 1 || answer > 2) throw new NoSuchOptionException();
                switch (answer) {
                    case 1:
                        System.out.println("Ok! So, we can offer you 3 types of photo paper sizes to print: ");
                        for (PhotoPaper paper : PhotoPaper.values()) {
                            System.out.println(" - " + paper + " (" + paper.getSizeInInches() + ", " +
                                    paper.getCostPerCopy() + "$ per copy)");
                        }
                        showStandardSizeMenu(scanner, user, order, photoStudio);
                        break;
                    case 2:
                        order.setPrintStandard(0);
                        order.setPrintLarge(0);
                        order.setPrintProfessional(0);
                        showRedoMenu(scanner, user, order, photoStudio);
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

    private void showStandardSizeMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nPlease enter how much STANDARD sized photo you want us to print.\n" +
                        " * Note : Maximum is 50, zero means no additional copies, and 1 means only one additional copy");
                int answer = scanner.nextInt();
                if (answer < 0 || answer > 50) throw new NoSuchOptionException();
                order.setPrintStandard(answer);
                photoStudio.usePhotoPaper("STANDARD" , order.getPrintStandard());

                test.paperTest(photoStudio);  //todo delete

                showLargeSizeMenu(scanner, user, order, photoStudio);
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

    private void showLargeSizeMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nPlease enter how much LARGE sized photo you want us to print.\n" +
                        " * Note : Maximum is 25, 0 means no additional copies.");
                int answer2 = scanner.nextInt();
                if (answer2 < 0 || answer2 > 25) throw new NoSuchOptionException();
                order.setPrintLarge(answer2);
                photoStudio.usePhotoPaper("LARGE", order.getPrintLarge());

                test.paperTest(photoStudio);  //todo delete

                showProfessionalSizeMenu(scanner, user, order, photoStudio);
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

    private void showProfessionalSizeMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("\nPlease enter how much PROFESSIONAL sized photo you want us to print.\n" +
                        " * Note : Maximum is 10, 0 means no additional copies.");
                int answer3 = scanner.nextInt();
                if (answer3 < 0 || answer3 > 10) throw new NoSuchOptionException();
                order.setPrintProfessional(answer3);
                photoStudio.usePhotoPaper("PROFESSIONAL", order.getPrintProfessional());

                test.paperTest(photoStudio);  //todo delete

                showRedoMenu(scanner, user, order, photoStudio);
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
    public void showRedoMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                photoStudio.getSupplyManager().checkPhotoPaper(photoStudio);

                test.paperTest(photoStudio);  //todo delete

                System.out.println("You choose " + order.getPrintStandard() + " additional copies of STANDARD sized photo, "
                        + order.getPrintLarge() + " copies of LARGE sized photo, and " +
                        order.getPrintProfessional() + " copies of PROFESSIONAL sized photo.");
//                System.out.println("The printing work will cost you: " + costOfPrinting + "$");

                System.out.println("\nDo you want to continue or to redo?\n " +
                        "1 - Let's continue.\n " +
                        "2 - I want to redo.");

                int answer = scanner.nextInt();
                switch (answer) {
                    case 1:
                        new CalculateTotalMenu(scanner, user, order, photoStudio);
                        break;
                    case 2:
                        PhotoStudio.totalUseOfPaper = 0;
                        new PhotoPaperMenu(scanner, user, order, photoStudio);
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

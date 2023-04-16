package com.maryzh555.photo_studio.user_console_interface;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.maryzh555.photo_studio.enums.PhotoPaperType;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.ShowQuitMenu;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
//import com.maryzh555.photo_studio.models.test;
import com.maryzh555.photo_studio.models.test;
import com.maryzh555.photo_studio.models.users.Client;

/**
 * @author Zhang M. on 20.03.2023.
 */

public class CalculateTotalMenu implements ShowQuitMenu {
    public CalculateTotalMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, order, photoStudio);
    }

    private void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                int resultTotal = order.calculateTotal(order);
                order.setTotal(resultTotal);

                System.out.println("\nYour order is a " + order.getOrderedPhoto().getPhotoType() +
                        " photo type, with the photographer " +
                        order.getDesiredPhotographer().getName() +
                        ". And location you are renting is the " +
                        order.getDesiredLocation() + ".");
                if (order.getOrderedPhoto().getPrintStandardQty() == 0 &&
                        order.getOrderedPhoto().getPrintLargeQty() == 0 &&
                        order.getOrderedPhoto().getPrintProfessionalQty() == 0) {
                    System.out.println("You chose to have only digital photos.");
                } else {
                    String colorInfo;
                    if (order.getOrderedPhoto().isColored()) {
                        colorInfo = " colored.";
                    } else {
                        colorInfo = " black-&-white.";
                    }
                    System.out.println(
                            "\nFor printing you chose " + order.getOrderedPhoto().getPrintStandardQty() + " copies of STANDARD sized photo, "
                                    + order.getOrderedPhoto().getPrintLargeQty() + " copies of LARGE sized photo, and " +
                                    order.getOrderedPhoto().getPrintProfessionalQty() + " copies of PROFESSIONAL sized photo. " +
                                    "All " + colorInfo);
                }
                System.out.println("\nIt will cost you " + order.getTotal() + "$ total.\n");

                System.out.println("Is it ok, or you want to redo your order?" +
                        "\n 1 - It's fine." +
                        "\n 2 - I want to redo the whole order." +
                        "\n 3 - Leave(quit)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
//                        // When the order is submitted we add it to the system:
//                        //// Setting the Photo object to the Order
//                        //// If the Photo will be printed, then add it to the Storage(passing the printing process)
//                        //// The supply manager will checki if there is no paper in the studio.
//                        photoStudio.getDirector().getCustomerManager().addOrderToTheSystemList(order);
//                        test.printOrderList(photoStudio); // todo test
//                        if (order.getOrderedPhoto().isToPrint()) {
//                            photoStudio.getStorage().addPhotoPackToStore(order.getPhotoPack());
//                            photoStudio.getDirector().getSupplyManager().checkPhotoPaperInStudio(photoStudio);
//                            test.paperTest(photoStudio); //todo test
////                            test.printStoragePhotoList(photoStudio);//todo test
//                        }
//
//                        //set hours of work for workers //todo maybe make a method in DIRECTOR
//                        order.getDesiredPhotographer().addToHoursWorkedToday(order.getOrderedPhoto().getPhotoType().getHours(), photoStudio);
//                        order.getDesiredPhotographer().addToPhotoShootsToday(1);
//
//                        photoStudio.getDirector().getCustomerManager().addServicedClients();//for new order
                        submitChanges(photoStudio, order);

                        System.out.println("Great! See you in our Photo Studio, " + order.getClient().getName() +
                                "! Your order id is #" + order.getId() +
                                ". \nWe will contact you using the telephone number provided. " +
                                "Have a nice day!");
                        new NewCustomerMenu(scanner, photoStudio);
                        break;
                    case 2:
                        new MainMenu(photoStudio); //returns to the first menu
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
                scanner.next();// clear the input buffer, stops the infinite loop
            }
        }
    }

    private void submitChanges(PhotoStudio photoStudio, Order order) {
        // When the order is submitted we add it to the system:
        //// Setting the Photo object to the Order
        //// If the Photo will be printed, then add it to the Storage(passing the printing process)
        //// The supply manager will control if there is no paper in the studio.
        photoStudio.getDirector().getCustomerManager().addOrderToTheSystemList(order);
            test.printOrderList(photoStudio); // todo test
            test.printAllOrderListInfo(photoStudio);
        if (order.getOrderedPhoto().isToPrint()) {
            photoStudio.getStorage().addPhotoPackToStore(order.getPhotoPack());
            photoStudio.getDirector().getSupplyManager().checkPhotoPaperInStudio(photoStudio);
                test.paperTest(photoStudio); //todo test
//                            test.printStoragePhotoList(photoStudio);//todo test
        }

        //set hours of work for workers //todo maybe make a method in DIRECTOR
        order.getDesiredPhotographer().addToHoursWorkedToday(order.getOrderedPhoto().getPhotoType().getHours(), photoStudio);
        order.getDesiredPhotographer().addToPhotoShootsToday(1); //check add here the test to print the available photographers

        photoStudio.getDirector().getCustomerManager().addServicedClients();//for new order
    }

    @Override
    public void showQuitMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println(" Are you sere you want to quit? " +
                        "\n 1 - Yes(quit)" +
                        "\n 2 - No ->(Redo)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        new NewCustomerMenu(scanner, photoStudio);
                        break;
                    case 2:
                        showMenu(scanner, order, photoStudio);
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

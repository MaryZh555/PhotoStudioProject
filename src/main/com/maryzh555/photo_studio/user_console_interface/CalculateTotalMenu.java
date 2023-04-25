package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.enums.PhotoPaperType;
import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.CustomerManager;
import main.com.maryzh555.photo_studio.models.users.SupplyManager;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * @author Zhang M. on 20.03.2023.
 */

public class CalculateTotalMenu extends Menu implements IShowRedoMenu {
    public CalculateTotalMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        showMenu(scanner, order, photoStudio);
    }

    public void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {

        int resultTotal = order.calculateTotal(order);
        order.setTotal(resultTotal);

        System.out.println("\nYour order is a " + order.getOrderedPhoto().getType() +
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

        showRedoMenu(scanner, order, photoStudio, this);

    }

    private void submitChanges(PhotoStudio photoStudio, Order order) {
        // When the order is submitted we add it to the system:

        //// Using the paper from the studio to print
        callWorker(photoStudio, SupplyManager.class).useStudioPhotoPaper(photoStudio, "STANDARD", order.getOrderedPhoto().getPrintStandardQty());
        callWorker(photoStudio, SupplyManager.class).useStudioPhotoPaper(photoStudio, "LARGE", order.getOrderedPhoto().getPrintLargeQty());
        callWorker(photoStudio, SupplyManager.class).useStudioPhotoPaper(photoStudio, "PROFESSIONAL", order.getOrderedPhoto().getPrintProfessionalQty());

        // Adding to the photoPack (the printing result)
        order.addToPhotoPack(order.getOrderedPhoto().getPrintStandardQty(), PhotoPaperType.STANDARD, order.getOrderedPhoto().isColored());
        order.addToPhotoPack(order.getOrderedPhoto().getPrintLargeQty(), PhotoPaperType.LARGE, order.getOrderedPhoto().isColored());
        order.addToPhotoPack(order.getOrderedPhoto().getPrintProfessionalQty(), PhotoPaperType.PROFESSIONAL, order.getOrderedPhoto().isColored());


        //// Setting the Photo object to the Order
        //// If the Photo will be printed, then add it to the Storage(passing the printing process)
        //// The supply manager will control if there is no paper in the studio.
        callWorker(photoStudio, CustomerManager.class).addOrderToTheSystemList(order);
//        test.printOrderList(photoStudio); // test
//        test.printAllOrderListInfo(photoStudio);
        if (order.getOrderedPhoto().isToPrint()) {
            photoStudio.getStorage().addPhotoPackToStore(order.getPhotoPack());
            callWorker(photoStudio, SupplyManager.class).checkPhotoPaperInStudio(photoStudio);
//            test.paperTest(photoStudio); //test
//          test.printStoragePhotoList(photoStudio);//test
        }

        //set hours of work for workers
        order.getDesiredPhotographer().addToHoursWorkedToday(order.getOrderedPhoto().getType().getHours(), photoStudio);
        order.getDesiredPhotographer().addToPhotoShootsToday(1);

        callWorker(photoStudio, CustomerManager.class).addServicedClients();//for new order
    }


    @Override
    public void showRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu) {
        while (true) {
            try {
                System.out.println("Is it ok, or you want to redo your order?" +
                        "\n 1 - It's fine." +
                        "\n 2 - I want to redo the whole order." +
                        "\n 3 - Leave(quit)");

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
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
                    case 3:
                        new QuitMenu(scanner, order, photoStudio, this);
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
    }
}

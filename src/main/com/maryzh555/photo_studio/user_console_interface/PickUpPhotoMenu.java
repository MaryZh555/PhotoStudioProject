package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.Photo;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Client;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author by Zhang M. on 15.04.2023.
 */
public class PickUpPhotoMenu extends Menu{

    public PickUpPhotoMenu(Scanner scanner, Client client, PhotoStudio photoStudio){
        showMenu(scanner, client,  photoStudio);
    }

    @Override
    public void showMenu(Scanner scanner, Client client, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Please enter your order id: ");
                int id = scanner.nextInt();
//                    test.printStoragePhotoPackList(photoStudio); // test
                List<Photo> pack = photoStudio.getSupplyManager().findPickUpPhotoPack(id, photoStudio);
                if (pack == null) {
                    throw new NoSuchOptionException();
                }
                photoStudio.getStorage().takeFromTheStorage(pack);
//                test.printStoragePhotoPackList(photoStudio);//test
                System.out.println("Here you go. Your " + pack.size() +
                        " photos are ready. Have a nice day!");
                photoStudio.getCustomerManager().addServicedClients();
                new ClientOptionMenu(scanner, client, photoStudio);/*new NewCustomerMenu(scanner, photoStudio);*/
                break;
            } catch (NoSuchOptionException e) {
                System.out.println("---------\n" +
                        "It seems like we don't have an order with this id, please check if the inputted id is correct" +
                        "\n---------");
                new RedoMenu(scanner, client.getOrderList().get(client.getOrderList().size() - 1), photoStudio, this);
                break;
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }
}

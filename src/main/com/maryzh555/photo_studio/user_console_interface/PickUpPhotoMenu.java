package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.Photo;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author by Zhang M. on 15.04.2023.
 */
public class PickUpPhotoMenu extends Menu {
    private static final Logger logger = LogManager.getLogger(PickUpPhotoMenu.class);

    public PickUpPhotoMenu(Scanner scanner, Client client, PhotoStudio photoStudio) {
        showMenu(scanner, client, photoStudio);
    }

    @Override
    public void showMenu(Scanner scanner, Client client, PhotoStudio photoStudio) {
        while (true) {
            try {
                System.out.println("Please enter your order id: ");
                int id = scanner.nextInt();

                //logger //test.printStoragePhotoPackList(photoStudio); // test
                if (photoStudio.getStorage().getStoredPhotoPacks().isEmpty()) {
                    logger.info("**** 2 PACK IN STORAGE ****");
                    logger.info("* Empty");
                    logger.info("****************");
                } else {
                    int i = 1;
                    for (List<Photo> pack : photoStudio.getStorage().getStoredPhotoPacks()) {
                        logger.info("** PACK IN STORAGE {} **", i);
                        int j = 1;
                        for (Photo photo : pack) {
                            logger.info("**** {}: {}, is colored - {}", j, photo.getPaperType(), photo.isColored());
                            j++;
                        }
                        i++;
                    }
                    logger.info("***********************\n");
                }
                ////////////


                List<Photo> pack = photoStudio.getSupplyManager().findPickUpPhotoPack(id, photoStudio);
                if (pack == null) {
                    throw new NoSuchOptionException();
                }
                photoStudio.getStorage().takeFromTheStorage(pack);

                //logger //test.printStoragePhotoPackList(photoStudio); // test
                if (photoStudio.getStorage().getStoredPhotoPacks().isEmpty()) {
                    logger.info("**** 2 PACK IN STORAGE ****");
                    logger.info("* Empty");
                    logger.info("****************");
                } else {
                    int i = 1;
                    for (List<Photo> pack1 : photoStudio.getStorage().getStoredPhotoPacks()) {
                        logger.info("** PACK IN STORAGE {} **", i);
                        int j = 1;
                        for (Photo photo : pack1) {
                            logger.info("**** {}: {}, is colored - {}", j, photo.getPaperType(), photo.isColored());
                            j++;
                        }
                        i++;
                    }
                    logger.info("********************\n");
                }
                /////////

                System.out.println("Here you go. Your " + pack.size() +
                        " photos are ready. Have a nice day!");
                photoStudio.getCustomerManager().addServicedClients();
                new ClientOptionMenu(scanner, client, photoStudio);
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

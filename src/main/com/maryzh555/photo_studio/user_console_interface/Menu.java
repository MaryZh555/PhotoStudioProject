package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.interfaces.ICallWorkers;
import main.com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Worker;

import java.util.Scanner;

/**
 * @author by Zhang M. on 16.04.2023.
 */
public class Menu implements IShowRedoMenu, ICallWorkers {


    public <T extends OrderOrClient> void showMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio){}

    public void callRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu) {
        new RedoMenu(scanner, order, photoStudio, menu);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public <T extends OrderOrClient> void showRedoMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio, Menu menu) {}

    @Override
    public <T extends Worker> T callWorker(PhotoStudio photoStudio, Class<T> clazz) {
        switch (clazz.getSimpleName()) {
            case "CustomerManager":
                return clazz.cast(photoStudio.getDirector().getCustomerManager());
            case "SupplyManager":
                return clazz.cast(photoStudio.getDirector().getSupplyManager());
            case "HRManager":
                return clazz.cast(photoStudio.getDirector().getHrManager());
            default:
                return null;
        }
    }
}

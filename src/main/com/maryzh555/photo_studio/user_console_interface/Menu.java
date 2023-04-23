package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.interfaces.ICallWorkers;
import main.com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.CustomerManager;
import main.com.maryzh555.photo_studio.models.users.Director;
import main.com.maryzh555.photo_studio.models.users.HRManager;
import main.com.maryzh555.photo_studio.models.users.SupplyManager;

import java.util.Scanner;

/**
 * @author by Zhang M. on 16.04.2023.
 */
public class Menu implements IShowRedoMenu, ICallWorkers {

    public void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio){

    }
    @Override
    public void showRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu) {

    }

    public void callRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu){
        new RedoMenu(scanner, order, photoStudio, menu);
    }

    public String toString(){
        return getClass().getSimpleName();
    }

    @Override
    public Director callDirector(PhotoStudio photoStudio) {
        return photoStudio.getDirector();
    }

    @Override
    public HRManager callHRManager(PhotoStudio photoStudio) {
        return photoStudio.getDirector().getHrManager();
    }

    @Override
    public SupplyManager callSupplyManager(PhotoStudio photoStudio) {
        return photoStudio.getDirector().getSupplyManager();
    }

    @Override
    public CustomerManager callCustomerManager(PhotoStudio photoStudio) {
        return photoStudio.getDirector().getCustomerManager();
    }
}

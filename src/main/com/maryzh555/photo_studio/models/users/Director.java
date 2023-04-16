package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author by Zhang M. on 12.04.2023.
 */
public class Director extends User {
// Director oversees other workers
// All workers report to them

    private final List<Photographer> allPhotographers;

    private List<Photographer> availablePhotographers;

    private final SupplyManager supplyManager;    // represents the current manager, who is having the shift today(When photoStudio is created)//set in the director

    private final CustomerManager customerManager;


    public Director() {
        this.allPhotographers = fillPhotographers();
        this.availablePhotographers = allPhotographers;
        this.supplyManager = choseSupplyManager();
        this.customerManager = choseCustomerManager();
    }

    public void askForReport(PhotoStudio photoStudio) {
        //Photographers
        List<Photographer> workedTodayPhotographers = findPhotographersWorkedToday(photoStudio);
        for (Photographer photographer :
                workedTodayPhotographers) {
            photographer.report();
        }
        //SupplyManagers
        supplyManager.report();
        //Customer Manager
        customerManager.report();
    }


    //Reaction to AskForRest()
    public void removeFromAvailablePhotographers(Photographer photographer) {
        availablePhotographers.remove(photographer);
    }

    public List<Photographer> findPhotographersWorkedToday(PhotoStudio photoStudio) {
        List<Photographer> result = new ArrayList<>();
        for (Order order : photoStudio.getDirector().getCustomerManager().getListOfOrders()) {
            Photographer photographer = order.getDesiredPhotographer();
            if (!result.contains(photographer)) {
                result.add(photographer);
            }
        }
        return result;
    }


    //The photographers data is written here until the database is implemented.
    public List<Photographer> fillPhotographers() {
        List<Photographer> result = new ArrayList<>();
        result.add(new Photographer("Tasha", 1, 10));
        result.add(new Photographer("Sasha", 2, 12));
        result.add(new Photographer("Misha", 3, 15));
        result.add(new Photographer("Dasha", 4, 18));
        result.add(new Photographer("Masha", 5, 25));
        result.add(new Photographer("Dimas", 6, 35));
        return result;
    }


    //The method works with Random() because of lack of workers schedule and time management in current version of program
    private SupplyManager choseSupplyManager() {
        Random random = new Random();
        List<SupplyManager> list = new ArrayList<>();
        list.add(new SupplyManager("Oleg", 15));
        list.add(new SupplyManager("Marina", 12));
        list.add(new SupplyManager("Antonina", 20));
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    private CustomerManager choseCustomerManager() {
        Random random = new Random();
        List<CustomerManager> list = new ArrayList<>();
        list.add(new CustomerManager("Oleg", 15));
        list.add(new CustomerManager("Marina", 12));
        list.add(new CustomerManager("Antonina", 20));
        int index = random.nextInt(list.size());
        return list.get(index);
    }


    public List<Photographer> getAllPhotographers() {
        return allPhotographers;
    }

    public List<Photographer> getAvailablePhotographers() {
        return availablePhotographers;
    }

    public void setAvailablePhotographers(List<Photographer> availablePhotographers) {
        this.availablePhotographers = availablePhotographers;
    }

    public SupplyManager getSupplyManager() {
        return supplyManager;
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }
}

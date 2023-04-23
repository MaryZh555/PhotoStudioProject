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

    private final HRManager hrManager;

    private final List<HRManager> hrManagerList;

    private final List<CustomerManager> customerManagerList;

    private final List<SupplyManager> supplyManagerList;




    public Director() {
        this.allPhotographers = fillPhotographers();
        this.availablePhotographers = allPhotographers;
        this.hrManagerList = fillHRManagers();
        this.customerManagerList = fillCustomerManager();
        this.supplyManagerList = fillSupplyManagers();

        this.supplyManager = choseSupplyManager();
        this.customerManager = choseCustomerManager();
        this.hrManager = choseRandomHRManager();

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
        //HR Manager
        hrManager.report();
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
        result.add(new Photographer("Tasha", 1, 10, false, null));
        result.add(new Photographer("Sasha", 2, 12, false, null));
        result.add(new Photographer("Misha", 3, 15, false, null));
        result.add(new Photographer("Dasha", 4, 18, false, null));
        result.add(new Photographer("Masha", 5, 25, false, null));
        result.add(new Photographer("Dimas", 6, 35, false, null));
        return result;
    }


    public List<HRManager> fillHRManagers(){
        List<HRManager> list = new ArrayList<>();
        list.add(new HRManager("Oleg", 15));
        list.add(new HRManager("Marina", 12));
        list.add(new HRManager("Antonina", 20));
        return  list;
    }

    public List<SupplyManager> fillSupplyManagers(){
        List<SupplyManager> list = new ArrayList<>();
        list.add(new SupplyManager("Oleg", 15));
        list.add(new SupplyManager("Marina", 12));
        list.add(new SupplyManager("Antonina", 20));
        return  list;
    }

    public List<CustomerManager> fillCustomerManager(){
        List<CustomerManager> list = new ArrayList<>();
        list.add(new CustomerManager("Oleg", 15));
        list.add(new CustomerManager("Marina", 12));
        list.add(new CustomerManager("Antonina", 20));
        return  list;
    }
    //The method works with Random() because of lack of workers schedule and time management in current version of program


    private HRManager choseRandomHRManager(){
        Random random = new Random();
        List<HRManager> list = hrManagerList;
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    private SupplyManager choseSupplyManager() {
        Random random = new Random();
        List<SupplyManager> list = supplyManagerList;
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    private CustomerManager choseCustomerManager() {
        Random random = new Random();
        List<CustomerManager> list = customerManagerList;
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public void hireJobCandidate(PhotoStudio photoStudio, Candidate candidate){
        switch (candidate.getWorkerType()){
            case PHOTOGRAPHER:
                allPhotographers.add(new Photographer(candidate.getName(), candidate.getYearsOfExperience(), candidate.getHourlyRate(), candidate.isBorrowCamera(), photoStudio.getDirector().getSupplyManager().findCameraForCandidate(photoStudio) ));
                break;
            case HR_MANAGER:
                hrManagerList.add(new HRManager(candidate.getName(), candidate.getHourlyRate()));
                break;
            case SUPPLY_MANAGER:
                supplyManagerList.add(new SupplyManager(candidate.getName(), candidate.getHourlyRate()));
                break;
            case CUSTOMER_MANAGER:
                customerManagerList.add(new CustomerManager(candidate.getName(), candidate.getHourlyRate()));
                break;
        }
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

    public HRManager getHrManager() {
        return hrManager;
    }

    public List<HRManager> getHrManagerList() {
        return hrManagerList;
    }

    public List<CustomerManager> getCustomerManagerList() {
        return customerManagerList;
    }

    public List<SupplyManager> getSupplyManagerList() {
        return supplyManagerList;
    }
}

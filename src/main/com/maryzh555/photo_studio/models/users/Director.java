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

    public Director() {
    }

    public void askForReport(PhotoStudio photoStudio) {
        //Photographers
        List<Photographer> workedTodayPhotographers = findPhotographersWorkedToday(photoStudio);
        for (Photographer photographer :
                workedTodayPhotographers) {
            photographer.report();
        }
        //SupplyManagers
        photoStudio.getSupplyManager().report();
        //Customer Manager
        photoStudio.getCustomerManager().report();
        //HR Manager
        photoStudio.getHrManager().report();
    }


    //Reaction to AskForRest()
    public void removeFromAvailablePhotographers(PhotoStudio photoStudio, Photographer photographer) {
        photoStudio.getAvailablePhotographers().remove(photographer);
    }

    public List<Photographer> findPhotographersWorkedToday(PhotoStudio photoStudio) {
        List<Photographer> result = new ArrayList<>();
        for (Order order : photoStudio.getDigitalStorage().getListOfOrders()) {
            Photographer photographer = order.getDesiredPhotographer();
            if (!result.contains(photographer)) {
                result.add(photographer);
            }
        }
        return result;
    }


    //The method works with Random() because of lack of workers schedule and time management in current version of program
    public HRManager choseRandomHRManager(PhotoStudio photoStudio){//todo chose but save it in the PhotoStudio 'Workers room'
        Random random = new Random();
        int index = random.nextInt(photoStudio.getDigitalStorage().getHrManagerList().size());
        return photoStudio.getDigitalStorage().fillHRManagers().get(index);
    }

    public SupplyManager choseRandomSupplyManager(PhotoStudio photoStudio) {
        Random random = new Random();
        int index = random.nextInt(photoStudio.getDigitalStorage().getSupplyManagerList().size());
        return photoStudio.getDigitalStorage().getSupplyManagerList().get(index);
    }

    public CustomerManager choseRandomCustomerManager(PhotoStudio photoStudio) {
        Random random = new Random();
        int index = random.nextInt(photoStudio.getDigitalStorage().getCustomerManagerList().size());
        return photoStudio.getDigitalStorage().getCustomerManagerList().get(index);
    }

    public void hireJobCandidate(PhotoStudio photoStudio, Candidate candidate){
        switch (candidate.getWorkerType()){
            case PHOTOGRAPHER:
                photoStudio.getDigitalStorage().getAllPhotographers().add(new Photographer(candidate.getName(), candidate.getYearsOfExperience(), candidate.getHourlyRate(), candidate.isBorrowCamera(), photoStudio.getSupplyManager().findCameraForCandidate(photoStudio)));
                break;
            case HR_MANAGER:
                photoStudio.getDigitalStorage().getHrManagerList().add(new HRManager(candidate.getName(), candidate.getHourlyRate()));
                break;
            case SUPPLY_MANAGER:
                photoStudio.getDigitalStorage().getSupplyManagerList().add(new SupplyManager(candidate.getName(), candidate.getHourlyRate()));
                break;
            case CUSTOMER_MANAGER:
                photoStudio.getDigitalStorage().getCustomerManagerList().add(new CustomerManager(candidate.getName(), candidate.getHourlyRate()));
                break;
        }
    }

}

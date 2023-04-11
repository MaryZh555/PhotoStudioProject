package com.maryzh555.photo_studio.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.maryzh555.photo_studio.enums.Location;
import com.maryzh555.photo_studio.enums.PhotoType;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.models.users.Photographer;
import com.maryzh555.photo_studio.models.users.SupplyManager;

/**
 * A class representing a photo studio that offers photography services.
 * It contains methods to match a photo type to the number of people being photographed,
 * to match photographers by their years of experience, and to match locations to a given photo type.
 * It also includes a list of photographers and a method to populate it with predefined data.
 *
 * @author Zhang M. on 23.03.2023.
 */
public class PhotoStudio {

    private final List<Photographer> photographers;


    // represents the current manager, who is having the shift today(When photoStudio is created)
    private final SupplyManager supplyManager;


    //contain all created orders in the run of the program
    private List<Order> listOfOrders;

    private Storage storage;


    //represents qty in use by studio, not storage // maybe will be placed in the printer later
    private int qtyOfStandardPaper;

    private int qtyOfLargePaper;

    private int qtyOfProfessionalPaper;

    public PhotoStudio() {
        this.photographers = fillPhotographers();
        this.supplyManager = choseSupplyManager();
        this.storage = new Storage();
        this.listOfOrders = new ArrayList<>();
        prepareEquipment();
    }


    //The photographers data is written here until the database is implemented.
    private List<Photographer> fillPhotographers() {
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

    private void prepareEquipment() {
        //this method will also implement future methods of other workers.
        this.supplyManager.refillPhotoPaper(this, 50, 25, 10);
    }

    public void addOrderToTheSystemList(Order order) {
        this.listOfOrders.add(order);
    }

    public void useStudioPhotoPaper(String paperName, int useQty) {
        switch (paperName) {
            case "STANDARD":
                this.setQtyOfStandardPaper(this.getQtyOfStandardPaper() - useQty);
                break;
            case "LARGE":
                this.setQtyOfLargePaper(this.getQtyOfLargePaper() - useQty);
                break;
            case "PROFESSIONAL":
                this.setQtyOfProfessionalPaper(this.getQtyOfProfessionalPaper() - useQty);
        }
        test.paperTest(this);
    }

    public List<Photo> findPickUpPhotoPack(int orderId, PhotoStudio studio) {
        for (Order order : studio.getListOfOrders()) {
            if (orderId == order.getId()) {
                List<List<Photo>> storedPhotoPacks = studio.getStorage().getStoredPhotoPacks();
                for (List<Photo> photoPack : storedPhotoPacks) {
                    if (photoPack.equals(order.getPhotoPack())) {
                        return photoPack;
                    }
                }
            }
        }
        return null;
    }

    public PhotoType matchPhotoType(int numberOfPeople) throws NoSuchOptionException {
        PhotoType type;
        if (numberOfPeople == 1) {
            type = PhotoType.PORTRAIT;
        } else if (numberOfPeople > 1 && numberOfPeople <= 10) {
            type = PhotoType.GROUP;
        } else if (numberOfPeople > 10 && numberOfPeople <= 50) {
            type = PhotoType.TEAM;
        } else throw new NoSuchOptionException();
        return type;
    }


    public List<Location> matchLocations(PhotoType photoType) {
        List<Location> list = new ArrayList<>();
        switch (photoType) {
            case PORTRAIT:
                list.addAll(Arrays.asList(Location.CHROMA_CHARM, Location.RIVER_VIEW_TERRACE));
                break;
            case GROUP:
                list.addAll(Arrays.asList(Location.GROUP_HUB, Location.RIVER_VIEW_TERRACE));
                break;
            case TEAM:
                list.add(Location.GROUP_HUB);
                break;
            default:
                break;
        }
        return list;
    }

    public List<Photographer> matchPhotographers(int years) {
        List<Photographer> result = new ArrayList<>();
        for (Photographer photographer : photographers) {
            if (photographer.getYearsOfExperience() == years) {
                result.add(photographer);
            }
        }
        return result;
    }

    public List<Photographer> findAlternativePhotographers(int years) {

        int maxYears = 6; // set here as the temporary solution, until HR is added, and the MaxAllowedYears variable is set

        List<Photographer> result = new ArrayList<>();
        int diff = 1;
        while (result.isEmpty()) {
            for (Photographer photographer : photographers) {
                if (photographer.getYearsOfExperience() == years + diff || photographer.getYearsOfExperience() == years - diff) {
                    result.add(photographer);
                }
            }
            diff++;
            if (diff > maxYears) {
                break;
            }
        }
        return result;
    }


    //neededQty represents the maximum qty that can be stored in the studio itself, and not in the storage
    //maybe will be replaced to the Printer
    public void addStandardPaperToStudio(int neededQty) {
        // Add from the STORAGE
        int currentQty = this.getQtyOfStandardPaper();
        int toAdd = neededQty - currentQty;
        this.storage.setStoredStandardPaper(this.storage.getStoredStandardPaper() - toAdd);
        this.qtyOfStandardPaper += toAdd;
    }

    public void addLargePaper(int neededQty) {
        int currentQty = this.getQtyOfLargePaper();
        int toAdd = neededQty - currentQty;
        this.storage.setStoredLargePaper(this.storage.getStoredLargePaper() - toAdd);
        this.qtyOfLargePaper += toAdd;
    }

    public void addProfessionalPaper(int neededQty) {
        int currentQty = this.getQtyOfProfessionalPaper();
        int toAdd = neededQty - currentQty;
        this.storage.setStoredProfessionalPaper(this.storage.getStoredProfessionalPaper() - toAdd);
        this.qtyOfProfessionalPaper += toAdd;
    }

    public List<Photographer> getPhotographers() {
        return photographers;
    }

    public int getQtyOfStandardPaper() {
        return qtyOfStandardPaper;
    }

    public int getQtyOfLargePaper() {
        return qtyOfLargePaper;
    }

    public int getQtyOfProfessionalPaper() {
        return qtyOfProfessionalPaper;
    }

    public SupplyManager getSupplyManager() {
        return supplyManager;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public void setQtyOfStandardPaper(int qtyOfStandardPaper) {
        this.qtyOfStandardPaper = qtyOfStandardPaper;
    }

    public void setQtyOfLargePaper(int qtyOfLargePaper) {
        this.qtyOfLargePaper = qtyOfLargePaper;
    }

    public void setQtyOfProfessionalPaper(int qtyOfProfessionalPaper) {
        this.qtyOfProfessionalPaper = qtyOfProfessionalPaper;
    }
}
package com.maryzh555.photo_studio.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.maryzh555.photo_studio.enums.Location;
import com.maryzh555.photo_studio.enums.PhotoType;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.models.humans.Photographer;
import com.maryzh555.photo_studio.models.humans.SupplyManager;

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

    private int qtyOfStandardPaper;

    private int qtyOfLargePaper;

    private int qtyOfProfessionalPaper;

    public static int totalUseOfPaper;//total use of Photo Paper for a shift

    private final SupplyManager supplyManager;
    // represents the current manager, who is having the shift today(When photoStudio is created)


    public PhotoStudio() { //workers will be past as an argument when creating photoStudio instance
        this.photographers = fillPhotographers();
        this.supplyManager = choseSupplyManager();
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
    private SupplyManager choseSupplyManager(){
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
        this.supplyManager.checkPhotoPaper(this);
    }


    public void usePhotoPaper(String paperName, int useQty) {
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
        this.calculateUsedPaper(useQty);
    }


    public int calculateUsedPaper(int useQty){
        System.out.println(" ------------\n CALCULATE PAPER USE METHOD " + totalUseOfPaper );
        return totalUseOfPaper += useQty;
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


    // The array used for compact initialization compared to List's lengthy initialization.
    public Location[] matchLocations(PhotoType photoType) {
        Location[] array;
        switch (photoType.ordinal()) {
            case 0:
                array = new Location[]{Location.CHROMA_CHARM, Location.RIVER_VIEW_TERRACE};
                break;
            case 1:
                array = new Location[]{Location.GROUP_HUB, Location.RIVER_VIEW_TERRACE};
                break;
            case 2:
                array = new Location[]{Location.GROUP_HUB};
                break;
            default:
                array = null;
        }
        return array;
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
        List<Photographer> result = new ArrayList<>();
        for (Photographer photographer : photographers) {
            if (photographer.getYearsOfExperience() == years + 1 || photographer.getYearsOfExperience() == years - 1) {
                result.add(photographer);
            }
        }
        return result;
    }


    public void addStandardPaper(int neededQty) {
        int currentQty = this.getQtyOfStandardPaper();
        int toAdd = neededQty - currentQty;
        this.qtyOfStandardPaper += toAdd;
    }

    public void addLargePaper(int neededQty) {
        int currentQty = this.getQtyOfLargePaper();
        int toAdd = neededQty - currentQty;
        this.qtyOfLargePaper += toAdd;
    }

    public void addProfessionalPaper(int neededQty) {
        int currentQty = this.getQtyOfProfessionalPaper();
        int toAdd = neededQty - currentQty;
        this.qtyOfProfessionalPaper += toAdd;
    }

    public List<Photographer> getPhotographers() {
        return photographers;
    }

    public int getQtyOfStandardPaper() {
        return qtyOfStandardPaper;
    }

    public void setQtyOfStandardPaper(int qtyOfStandardPaper) {
        this.qtyOfStandardPaper = qtyOfStandardPaper;
    }

    public int getQtyOfLargePaper() {
        return qtyOfLargePaper;
    }

    public void setQtyOfLargePaper(int qtyOfLargePaper) {
        this.qtyOfLargePaper = qtyOfLargePaper;
    }

    public int getQtyOfProfessionalPaper() {
        return qtyOfProfessionalPaper;
    }

    public void setQtyOfProfessionalPaper(int qtyOfProfessionalPaper) {
        this.qtyOfProfessionalPaper = qtyOfProfessionalPaper;
    }

    public SupplyManager getSupplyManager() {
        return supplyManager;
    }
}
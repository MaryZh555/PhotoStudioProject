package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.PhotoPaperType;
import main.com.maryzh555.photo_studio.models.users.CustomerManager;
import main.com.maryzh555.photo_studio.models.users.Director;
import main.com.maryzh555.photo_studio.models.users.HRManager;
import main.com.maryzh555.photo_studio.models.users.Photographer;
import main.com.maryzh555.photo_studio.models.users.SupplyManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Zhang M. on 23.03.2023.
 */
public class PhotoStudio {

    private Director director;

    private Storage storage;

    private DigitalStorage digitalStorage;

    private List<Paper> storedPaper;

    private List<Photographer> availablePhotographers;

    private final SupplyManager supplyManager;    // represents the current manager, who is having the shift today(When photoStudio is created)//set in the director

    private final CustomerManager customerManager;

    private final HRManager hrManager;



    public PhotoStudio() {
        this.digitalStorage = new DigitalStorage();
        this.storage = new Storage();
        this.director = new Director();

        this.availablePhotographers = digitalStorage.fillPhotographers();
        this.supplyManager = director.choseRandomSupplyManager(this);
        this.customerManager = director.choseRandomCustomerManager(this);
        this.hrManager = director.choseRandomHRManager(this);
        prepareEquipment(this);
//        test.paperTest(this);
    }


    private void prepareEquipment(PhotoStudio photoStudio) {
        //this method will also implement future methods of other workers.
        photoStudio.storedPaper = photoStudio.setStoredPaper(0, 0, 0);
        photoStudio.getSupplyManager().addPaperToStorage(photoStudio, 1050, 525, 110);
        photoStudio.getSupplyManager().refillPhotoPaperInStudio(photoStudio, 50, 25, 10);
        photoStudio.getSupplyManager().fillStoredCameras(photoStudio, 10);
    }


    public boolean checkIfReadyToCheckOut(Order order) {
        return order.getDesiredPhotographer() != null &&
                order.getOrderedPhoto().getType() != null &&
                order.getOrderedPhoto().getPrintStandardQty() != -1 &&
                order.getDesiredLocation() != null;
    }

    /// Getters & Setters
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public int getQtyOfStandardPaper() {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.STANDARD) {
                return paper.getQty();
            }
        return 0;
    }

    public int getQtyOfLargePaper() {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.LARGE) {
                return paper.getQty();
            }
        return 0;
    }

    public int getQtyOfProfessionalPaper() {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.PROFESSIONAL) {
                return paper.getQty();
            }
        return 0;
    }

    public void setQtyOfStandardPaper(int qtyOfStandardPaper) {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.STANDARD) {
                paper.setQty(qtyOfStandardPaper);
            }
    }

    public void setQtyOfLargePaper(int qtyOfLargePaper) {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.LARGE) {
                paper.setQty(qtyOfLargePaper);
            }
    }

    public void setQtyOfProfessionalPaper(int qtyOfProfessionalPaper) {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.PROFESSIONAL) {
                paper.setQty(qtyOfProfessionalPaper);
            }
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Paper> getStoredPaper() {
        return storedPaper;
    }

    public List<Paper> setStoredPaper(int qtyStandard, int qtyLarge, int qtyPro) {
        return new ArrayList<>(Arrays.asList(
                new Paper(qtyStandard, PhotoPaperType.STANDARD),
                new Paper(qtyLarge, PhotoPaperType.LARGE),
                new Paper(qtyPro, PhotoPaperType.PROFESSIONAL)
        ));
    }

    public DigitalStorage getDigitalStorage() {
        return digitalStorage;
    }

    public void setDigitalStorage(DigitalStorage digitalStorage) {
        this.digitalStorage = digitalStorage;
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
}
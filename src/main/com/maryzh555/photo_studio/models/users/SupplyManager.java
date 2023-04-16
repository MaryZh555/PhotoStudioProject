package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.interfaces.IReport;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.Photo;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.test;

import java.util.List;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public class SupplyManager extends Worker implements IReport {
    // responsible for the items restoring and fixing, they will restore the paper, fix cameras and printers

    private int totalUseOfPaper;

    public SupplyManager(String name, int hourlyRate) {
        super(name);//set id and name
        this.hourlyRate = hourlyRate;
    }

    public void checkPhotoPaperInStudio(PhotoStudio photoStudio) {
        int standardQty = photoStudio.getQtyOfStandardPaper();
        int largeQty = photoStudio.getQtyOfLargePaper();
        int professionalQty = photoStudio.getQtyOfProfessionalPaper();
        if (standardQty == 0 || largeQty == 0 || professionalQty == 0) {
            refillPhotoPaper(photoStudio, 50, 25, 10);
        }
    }

    public void refillPhotoPaper(PhotoStudio photoStudio, int neededQtyStandard, int neededQtyLarge, int neededQtyPro) {
        this.addStandardPaperToStudio(neededQtyStandard, photoStudio);
        this.addLargePaper(neededQtyLarge, photoStudio);
        this.addProfessionalPaper(neededQtyPro, photoStudio);
    }

    //neededQty represents the maximum qty that can be stored in the studio itself, and not in the storage
    //maybe will be replaced to the Printer
    public void addStandardPaperToStudio(int neededQty, PhotoStudio photoStudio) {
        int currentQty = photoStudio.getQtyOfStandardPaper();
        int toAdd = neededQty - currentQty;
        photoStudio.getStorage().setStoredStandardPaper(photoStudio.getStorage().getStoredStandardPaper() - toAdd);
        photoStudio.setQtyOfStandardPaper(photoStudio.getQtyOfStandardPaper() + toAdd);
    }

    public void addLargePaper(int neededQty, PhotoStudio photoStudio) {
        int currentQty = photoStudio.getQtyOfLargePaper();
        int toAdd = neededQty - currentQty;
        photoStudio.getStorage().setStoredLargePaper(photoStudio.getStorage().getStoredLargePaper() - toAdd);
        photoStudio.setQtyOfLargePaper(photoStudio.getQtyOfLargePaper() + toAdd);
    }

    public void addProfessionalPaper(int neededQty, PhotoStudio photoStudio) {
        int currentQty = photoStudio.getQtyOfProfessionalPaper();
        int toAdd = neededQty - currentQty;
        photoStudio.getStorage().setStoredProfessionalPaper(photoStudio.getStorage().getStoredProfessionalPaper() - toAdd);
        photoStudio.setQtyOfProfessionalPaper(photoStudio.getQtyOfProfessionalPaper() + toAdd);
    }

    public void useStudioPhotoPaper(PhotoStudio photoStudio, String paperName, int useQty) {
        switch (paperName) {
            case "STANDARD":
                photoStudio.setQtyOfStandardPaper(photoStudio.getQtyOfStandardPaper() - useQty);
                break;
            case "LARGE":
                photoStudio.setQtyOfLargePaper(photoStudio.getQtyOfLargePaper() - useQty);
                break;
            case "PROFESSIONAL":
                photoStudio.setQtyOfProfessionalPaper(photoStudio.getQtyOfProfessionalPaper() - useQty);
        }
        test.paperTest(photoStudio);
        this.addToTotalUseOfPaper(useQty);
    }

    public List<Photo> findPickUpPhotoPack(int orderId, PhotoStudio studio) { //will be the work of the supply manager
        for (Order order : studio.getDirector().getCustomerManager().getListOfOrders()) {
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


    @Override
    public void report() {
        //The supply manager will report the situation on the end of photoStudio instance run.
        System.out.println("( Supply Manager " + this.getName() + " reports: " +
                "Used paper today: " + totalUseOfPaper + " photo papers. )");

        //Fixes today etc

        //Hours worked, to report salary needs, calculated with how much order was done in one run. Every new order is 1 hour for workers
    }

    public int getTotalUseOfPaper() {
        return totalUseOfPaper;
    }

    public void addToTotalUseOfPaper(int useOfPaper) {
        this.totalUseOfPaper += useOfPaper;
    }
}

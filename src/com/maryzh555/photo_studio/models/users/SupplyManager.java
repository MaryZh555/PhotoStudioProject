package com.maryzh555.photo_studio.models.users;

import com.maryzh555.photo_studio.interfaces.Report;
import com.maryzh555.photo_studio.models.PhotoStudio;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public class SupplyManager extends Worker implements Report {
    // responsible for the items restoring and fixing, they will restore the paper, fix cameras and printers

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
        photoStudio.addStandardPaperToStudio(neededQtyStandard);
        photoStudio.addLargePaper(neededQtyLarge);
        photoStudio.addProfessionalPaper(neededQtyPro);
    }

    @Override
    public void report() {
        //The supply manager will report the situation on the end of photoStudio instance run.
//        System.out.println("Used paper today: " + totalUseOfPaper + " photo papers.");

        //Fixes today etc

        //Hours worked, to report salary needs, calculated with how much order was done in one run. Every new order is 1 hour for workers
    }

}

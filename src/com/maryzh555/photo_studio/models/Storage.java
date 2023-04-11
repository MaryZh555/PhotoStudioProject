package com.maryzh555.photo_studio.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Zhang M. on 07.04.2023.
 */
public class Storage {

    private List<List<Photo>> storedPhotoPacks = new ArrayList<>();

    private int storedStandardPaper = 1050;

    private int storedLargePaper = 525;

    private int storedProfessionalPaper = 110;

    private int totalUseOfPaper;//total use of Photo Paper for a shift, will be used in the supplyManager report

//    public void takePaperFromStorage(String paperName, int useQty){
//        switch (paperName) {
//            case "STANDARD":
//                this.setStoredStandardPaper(this.getStoredStandardPaper() - useQty);
//                break;
//            case "LARGE":
//                this.setStoredLargePaper(this.getStoredLargePaper() - useQty);
//                break;
//            case "PROFESSIONAL":
//                this.setStoredProfessionalPaper(this.getStoredProfessionalPaper() - useQty);
//        }
//        totalUseOfPaper += useQty;
//    }

    // will be used after printed in printer by the supply manager
    public void addPhotoPackToStore(List<Photo> photoPack){ // To store Photo as the product, before the client will come to take it.
        storedPhotoPacks.add(photoPack);
    }

    public void takeFromTheStorage(List<Photo> pack){ // When client takes the printing photo
        storedPhotoPacks.remove(pack);
    }

    public  int getStoredStandardPaper() {
        return storedStandardPaper;
    }

    public  void setStoredStandardPaper(int storedStandardPaper) {
        this.storedStandardPaper = storedStandardPaper;
    }

    public  int getStoredLargePaper() {
        return storedLargePaper;
    }

    public  void setStoredLargePaper(int storedLargePaper) {
        this.storedLargePaper = storedLargePaper;
    }

    public  int getStoredProfessionalPaper() {
        return storedProfessionalPaper;
    }

    public  void setStoredProfessionalPaper(int storedProfessionalPaper) {
        this.storedProfessionalPaper = storedProfessionalPaper;
    }
    public int getTotalUseOfPaper() {
        return totalUseOfPaper;
    }

    public void setTotalUseOfPaper(int totalUseOfPaper) {
        this.totalUseOfPaper = totalUseOfPaper;
    }

    public List<List<Photo>> getStoredPhotoPacks() {
        return this.storedPhotoPacks;
    }
}

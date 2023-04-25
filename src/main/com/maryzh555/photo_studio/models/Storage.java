package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.PhotoPaperType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by Zhang M. on 07.04.2023.
 */
public class Storage {

    private final List<List<Photo>> storedPhotoPacks = new ArrayList<>();

    private List<Paper> storedPaper;

    private List<Camera> storedCameras;

    public Storage() {}

    public void addPaper(int qtyStandard, int qtyLarge, int qtyPro){
        this.storedPaper = setStoredPaper(qtyStandard, qtyLarge, qtyPro);
    }

    public List<Paper> setStoredPaper(int qtyStandard, int qtyLarge, int qtyPro) {
        return new ArrayList<>(Arrays.asList(
                new Paper(qtyStandard, PhotoPaperType.STANDARD),
                new Paper(qtyLarge, PhotoPaperType.LARGE),
                new Paper(qtyPro, PhotoPaperType.PROFESSIONAL)
        ));
    }

    // will be used after printed in printer by the supply manager
    public void addPhotoPackToStore(List<Photo> photoPack) { // To store Photo as the product, before the client will come to take it.
        storedPhotoPacks.add(photoPack);
    }

    public void takeFromTheStorage(List<Photo> pack) { // When client takes the printing photo
        storedPhotoPacks.remove(pack);
    }

    public int getStoredStandardPaper() {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.STANDARD) {
                return paper.getQty();
            }
        return 0;
    }

    public void setStoredStandardPaper(int storedStandardPaper) {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.STANDARD) {
                paper.setQty(storedStandardPaper);
            }
    }

    public int getStoredLargePaper() {
        for (Paper paper : storedPaper) {
            if (paper.getType() == PhotoPaperType.LARGE) {
                return paper.getQty();
            }
        }
        return 0;
    }

    public void setStoredLargePaper(int storedLargePaper) {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.LARGE) {
                paper.setQty(storedLargePaper);
            }
    }

    public int getStoredProfessionalPaper() {
        for (Paper paper : storedPaper) {
            if (paper.getType() == PhotoPaperType.PROFESSIONAL) {
                return paper.getQty();
            }
        }
        return 0;
    }

    public void setStoredProfessionalPaper(int storedProfessionalPaper) {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.PROFESSIONAL) {
                paper.setQty(storedProfessionalPaper);
            }
    }

    public List<List<Photo>> getStoredPhotoPacks() {
        return this.storedPhotoPacks;
    }

    public List<Paper> getStoredPaper() {
        return storedPaper;
    }

    public void setStoredPaper(List<Paper> storedPaper) {
        this.storedPaper = storedPaper;
    }

    public List<Camera> getStoredCameras() {
        return storedCameras;
    }

    public void setStoredCameras(List<Camera> storedCameras) {
        this.storedCameras = storedCameras;
    }
}

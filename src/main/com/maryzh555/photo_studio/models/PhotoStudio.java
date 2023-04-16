package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.models.users.Director;

/**
 * A class representing a photo studio that offers photography services.
 * It contains methods to match a photo type to the number of people being photographed,
 * to match photographers by their years of experience, and to match locations to a given photo type.
 * It also includes a list of photographers and a method to populate it with predefined data.
 *
 * @author Zhang M. on 23.03.2023.
 */
public class PhotoStudio {

    private Director director;

    private Storage storage;


    //represents qty in use by studio, not storage // maybe will be placed in the printer later
    private int qtyOfStandardPaper;

    private int qtyOfLargePaper;

    private int qtyOfProfessionalPaper;


    public PhotoStudio() {
        this.director = new Director();
        this.storage = new Storage();
        prepareEquipment();
    }


    private void prepareEquipment() {
        //this method will also implement future methods of other workers.
        this.director.getSupplyManager().refillPhotoPaper(this, 50, 25, 10);
    }


    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
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

    public void setQtyOfStandardPaper(int qtyOfStandardPaper) {
        this.qtyOfStandardPaper = qtyOfStandardPaper;
    }

    public void setQtyOfLargePaper(int qtyOfLargePaper) {
        this.qtyOfLargePaper = qtyOfLargePaper;
    }

    public void setQtyOfProfessionalPaper(int qtyOfProfessionalPaper) {
        this.qtyOfProfessionalPaper = qtyOfProfessionalPaper;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

}
package com.maryzh555.photo_studio.models;

import com.maryzh555.photo_studio.enums.Location;
import com.maryzh555.photo_studio.enums.PhotoPaper;
import com.maryzh555.photo_studio.enums.PhotoType;
import com.maryzh555.photo_studio.models.humans.Photographer;

/**
 * This class represents an order made by a customer, containing information about the desired photographer,
 * photo type, and location. It also includes methods for calculating the total cost of the order.
 *
 * @author Zhang M. on 23.03.2023.
 */
public class Order {

    // The id value will be reset to 1 on every run of the program, the id field is added for the future database use.
    // The idCounter is a temporary solution until the database will be added.
    private static int idCounter = 1;

    private final int id;

    private Photographer desiredPhotographer;

    private PhotoType desiredPhotoType;

    private Location desiredLocation;

    private int printStandard;

    private int printLarge;

    private int printProfessional;

    private int total;


    public Order() {
        this.id = idCounter++; //temporary solution
    }

    public int calculateTotal(Order order) {
        int costOfPhoto = (order.getDesiredPhotographer().getHourlyRate() + order.getDesiredLocation().getRentingCost()) * order.getDesiredPhotoType().getHours();
        int costOfPrinting = (order.getPrintStandard() * PhotoPaper.STANDARD.getCostPerCopy()) +
                (order.getPrintLarge() * PhotoPaper.LARGE.getCostPerCopy()) +
                (order.getPrintProfessional() * PhotoPaper.PROFESSIONAL.getCostPerCopy());
        return (costOfPhoto + costOfPrinting);
    }

    public Photographer getDesiredPhotographer() {
        return desiredPhotographer;
    }

    public void setDesiredPhotographer(Photographer desiredPhotographer) {
        this.desiredPhotographer = desiredPhotographer;
    }

    public PhotoType getDesiredPhotoType() {
        return desiredPhotoType;
    }

    public void setDesiredPhotoType(PhotoType desiredPhotoType) {
        this.desiredPhotoType = desiredPhotoType;
    }

    public Location getDesiredLocation() {
        return desiredLocation;
    }

    public void setDesiredLocation(Location desiredLocation) {
        this.desiredLocation = desiredLocation;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public int getPrintStandard() {
        return printStandard;
    }

    public void setPrintStandard(int printStandard) {
        this.printStandard = printStandard;
    }

    public int getPrintLarge() {
        return printLarge;
    }

    public void setPrintLarge(int printLarge) {
        this.printLarge = printLarge;
    }

    public int getPrintProfessional() {
        return printProfessional;
    }

    public void setPrintProfessional(int printProfessional) {
        this.printProfessional = printProfessional;
    }
}

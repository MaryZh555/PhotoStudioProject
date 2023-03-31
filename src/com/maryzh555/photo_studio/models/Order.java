package com.maryzh555.photo_studio.models;

import com.maryzh555.photo_studio.enums.Location;
import com.maryzh555.photo_studio.enums.PhotoType;

/**
 * Created by Zhang M. on 23.03.2023.
 */
public class Order {

    private Photographer desiredPhotographer;

    private PhotoType desiredPhotoType;

    private Location desiredLocation;

    private int total;

    public Order() {

    }

    public int calculateTotal(Order order) {
        int result = (order.getDesiredPhotographer().getHourlyRate() +order.getDesiredLocation().getRentingCost()) * order.getDesiredPhotoType().getHours();
        return result;
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
}

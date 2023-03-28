package com.maryzh555.photo_studio.models;

/**
 * Created by zhmas on 23.03.2023.
 */
public class Order {

    private String desiredPhotographerType;

    private Photographer desiredPhotographer;

    private int desiredNumbersOfPeople;

    private PhotoType desiredPhotoType;

    private Locations desiredLocation;

    private int total;

    public Order(){

    }

    public static int calculateTotal(Order order) {
        int total = (order.getDesiredPhotographer().getHourlyRate() * order.getDesiredPhotoType().getHours()) + order.getDesiredLocation().getRentingCost();
        order.setTotal(total);
        return total;
    }

    public void setDesiredPhotographerType(String desiredPhotographerType) {
        this.desiredPhotographerType = desiredPhotographerType;
    }

    public String getDesiredPhotographerType() {
        return desiredPhotographerType;
    }

    public Photographer getDesiredPhotographer() {
        return desiredPhotographer;
    }

    public void setDesiredPhotographer(Photographer desiredPhotographer) {
        this.desiredPhotographer = desiredPhotographer;
    }

    public int getDesiredNumbersOfPeople() {
        return desiredNumbersOfPeople;
    }

    public void setDesiredNumbersOfPeople(int number) {
        this.desiredNumbersOfPeople = number;
    }

    public PhotoType getDesiredPhotoType() {
        return desiredPhotoType;
    }

    public void setDesiredPhotoType(PhotoType desiredPhotoType) {
        this.desiredPhotoType = desiredPhotoType;
    }

    public Locations getDesiredLocation() {
        return desiredLocation;
    }

    public void setDesiredLocation(Locations desiredLocation) {
        this.desiredLocation = desiredLocation;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

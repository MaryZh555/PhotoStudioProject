package models;

/**
 * Created by zhmas on 17.03.2023.
 */

public class User {
    private String name;
    private String desiredPhotographerType;
    private Photographer desiredPhotographer;
    private int numbersOfPeople;
    private PhotoType desiredPhotoType;
    private Locations desiredLocation;
    private int total;


    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public int getNumbersOfPeople() {
        return numbersOfPeople;
    }

    public void setNumbersOfPeople(int number) {
        this.numbersOfPeople = number;
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



package com.maryzh555.photo_studio.models;

import java.util.List;

/**
 * Created by zhmas on 17.03.2023.
 */
public class PhotoType{

    private String name;

    private String description;

    private int[] numberOfPeople;

    private int hours;

    private List<Locations> linkedLocations;


    PhotoType(String name, String description, int[] numberOfPeople, int hours, List<Locations> linkedLocations) {
        this.name = name;
        this.description = description;
        this.numberOfPeople = numberOfPeople;
        this.hours = hours;
        this.linkedLocations = linkedLocations;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int[] numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<Locations> getLinkedLocations() {
        return linkedLocations;
    }

    public void setLinkedLocations(List<Locations> linkedLocations) {
        this.linkedLocations = linkedLocations;
    }
}

package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhmas on 17.03.2023.
 */
public class PhotoType {
    private String name;
    private String description;
    private int[] numberOfPeople;
    private int hours;
    private List<Locations> linkedLocations;


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


    PhotoType(String name, String description, int[] numberOfPeople, int hours, List<Locations> linkedLocations) {
        this.name = name;
        this.description = description;
        this.numberOfPeople = numberOfPeople;
        this.hours = hours;
        this.linkedLocations = linkedLocations;
    }


    public static PhotoType[] photoTypes;
    public static void createPhotoTypes() {
        //Creating an array with (11-50) for the Team PhotoType numberOfPeople
        int[] numbersTeam = new int[40];
        int current = 11;
        for (int i = 0; i < numbersTeam.length; i++) {
            numbersTeam[i] = current;
            current++;
        }

        //Linked locations
        List<Locations> portraitLinks = new ArrayList<>();
        portraitLinks.add(Locations.getLocations()[0]);
        portraitLinks.add(Locations.getLocations()[2]);

        List<Locations> groupLinks = new ArrayList<>();
        groupLinks.add(Locations.getLocations()[1]);
        groupLinks.add(Locations.getLocations()[2]);

        List<Locations> teamLinks = new ArrayList<>();
        teamLinks.add(Locations.getLocations()[1]);


        photoTypes = new PhotoType[]{
                new PhotoType(
                        "Portrait",
                        "For one person photo shoot. It will take 1 hour.",
                        new int[]{1},
                        1,
                        portraitLinks),
                new PhotoType(
                        "Group",
                        "It is the best for 2-10 people, like families, friends and lovers. It will take 2 hours.",
                        new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10},
                        2,
                        groupLinks),
                new PhotoType(
                        "Team",
                        "Photo of 10-50 people, it can be sport teams, company members and students. It will take 3 hours.",
                        numbersTeam,
                        3,
                        teamLinks),
        };

    }
}

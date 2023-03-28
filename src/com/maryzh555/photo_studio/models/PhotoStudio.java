package com.maryzh555.photo_studio.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhmas on 23.03.2023.
 */
public class PhotoStudio {

    private List<List<Photographer>> fullPhotographersList = new ArrayList<>(fillPhotographers());

    private PhotoType[] photoTypes;

    private Locations[] locations;

    public PhotoStudio() {
        fillPhotographers();
        fillLocations();
        fillPhotoTypes();
    }

    //Predefine data
    private List<List<Photographer>> fillPhotographers() {

        List<Photographer> novice = new ArrayList<>();

        List<Photographer> experienced = new ArrayList<>();

        List<Photographer> expert = new ArrayList<>();

        // Predefine photographers
        Photographer[] photographers = {
                new Photographer("Tasha", 1, 10),
                new Photographer("Sasha", 2, 12),
                new Photographer("Misha", 3, 15),
                new Photographer("Dasha", 4, 18),
                new Photographer("Masha", 5, 25),
                new Photographer("Dimas", 6, 35),
        };

        //Categorizing into lists novice, experienced and expert
        for (Photographer photographer : photographers) {
            if (photographer.getYearsOfExperience() <= 2) {
                novice.add(photographer);
            } else if (photographer.getYearsOfExperience() > 2 && photographer.getYearsOfExperience() < 5) {
                experienced.add(photographer);
            } else {
                expert.add(photographer);
            }
        }
        //Adding all lists into fullPhotographersList
        List<List<Photographer>> fullPhotographersList = new ArrayList<>();
        fullPhotographersList.add(novice);
        fullPhotographersList.add(experienced);
        fullPhotographersList.add(expert);

        return fullPhotographersList;
    }

    public void fillPhotoTypes() {

        //Creating an array numbersTeam with (11-50) numbers for the PhotoType's Team
        int[] numbersTeam = new int[40];
        int current = 11;
        for (int i = 0; i < numbersTeam.length; i++) {
            numbersTeam[i] = current;
            current++;
        }

        //Setting applicable locations for different types of photo
        List<Locations> portraitLinks = new ArrayList<>();
        portraitLinks.add(locations[0]);
        portraitLinks.add(locations[2]);

        List<Locations> groupLinks = new ArrayList<>();
        groupLinks.add(locations[1]);
        groupLinks.add(locations[2]);

        List<Locations> teamLinks = new ArrayList<>();
        teamLinks.add(locations[1]);

        // Predefine Photo types
        photoTypes = new PhotoType[]{
                new PhotoType(
                        "Portrait",
                        "For one person PhotoType shoot. It will take 1 hour.",
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
                        "PhotoType of 10-50 people, it can be sport teams, company members and students. It will take 3 hours.",
                        numbersTeam,
                        3,
                        teamLinks),
        };
    }

    public void fillLocations() {
        // Predefine locations
        locations = new Locations[]{
                new Locations(
                        "ChromaCharm Room",
                        10,
                        "A cozy and intimate space with chromakey and all necessary attributes for portrait photography."),
                new Locations(
                        "Group Hub",
                        25,
                        "A spacious room perfect for group and team PhotoType shoots with a variety of backdrops and props available."),
                new Locations(
                        "River View Terrace",
                        30,
                        "An outdoor location featuring a picturesque view of a small bridge over a nearby river, perfect for natural and scenic photography."),
        };
    }


    // Return suitable Photo type based on provided number of people
    public PhotoType matchPhotoType(int numberOfPeople) {
        for (PhotoType type : this.getPhotoTypes()) {
            for (int i : type.getNumberOfPeople()) {
                if (i == numberOfPeople) {
                    return type;
                }
            }
        }
        return null;
    }


    public PhotoType[] getPhotoTypes() {
        return photoTypes;
    }

    public Locations[] getLocations() {
        return locations;
    }

    public List<List<Photographer>> getFullPhotographersList() {
        return fullPhotographersList;
    }

    public void setFullPhotographersList(List<List<Photographer>> fullPhotographersList) {
        this.fullPhotographersList = fullPhotographersList;
    }
}

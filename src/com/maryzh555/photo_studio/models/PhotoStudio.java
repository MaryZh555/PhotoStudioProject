package com.maryzh555.photo_studio.models;

import java.util.ArrayList;
import java.util.List;

import com.maryzh555.photo_studio.enums.Location;
import com.maryzh555.photo_studio.enums.PhotoType;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;

/**
 * A class representing a photo studio that offers photography services.
 * It contains methods to match a photo type to the number of people being photographed,
 * to match photographers by their years of experience, and to match locations to a given photo type.
 * It also includes a list of photographers and a method to populate it with predefined data.
 *
 * @author Zhang M. on 23.03.2023.
 */
public class PhotoStudio {

    private final List<Photographer> photographers;

    public PhotoStudio() {
        photographers = fillPhotographers();
    }


    //The photographers data is written here until the database is implemented.
    private List<Photographer> fillPhotographers() {
        List<Photographer> result = new ArrayList<>();
        result.add(new Photographer("Tasha", 1, 10));
        result.add(new Photographer("Sasha", 2, 12));
        result.add(new Photographer("Misha", 3, 15));
        result.add(new Photographer("Dasha", 4, 18));
        result.add(new Photographer("Masha", 5, 25));
        result.add(new Photographer("Dimas", 6, 35));
        return result;
    }

    public PhotoType matchPhotoType(int numberOfPeople) throws NoSuchOptionException {
        PhotoType type;
        if (numberOfPeople == 1) {
            type = PhotoType.PORTRAIT;
        } else if (numberOfPeople > 1 && numberOfPeople <= 10) {
            type = PhotoType.GROUP;
        } else if (numberOfPeople > 10 && numberOfPeople <= 50) {
            type = PhotoType.TEAM;
        } else throw new NoSuchOptionException();
        return type;
    }


    // The array used for compact initialization compared to List's lengthy initialization.
    public Location[] matchLocations(PhotoType photoType) {
        Location[] array;
        switch (photoType.ordinal()) {
            case 0:
                array = new Location[]{Location.CHROMA_CHARM, Location.RIVER_VIEW_TERRACE};
                break;
            case 1:
                array = new Location[]{Location.GROUP_HUB, Location.RIVER_VIEW_TERRACE};
                break;
            case 2:
                array = new Location[]{Location.GROUP_HUB};
                break;
            default:
                array = null;
        }
        return array;
    }

    public List<Photographer> matchPhotographers(int years) {
        List<Photographer> result = new ArrayList<>();
        for (Photographer photographer : photographers) {
            if (photographer.getYearsOfExperience() == years) {
                result.add(photographer);
            }
        }
        return result;
    }

    public List<Photographer> findAlternativePhotographers(int years) {
        List<Photographer> result = new ArrayList<>();
        for (Photographer photographer : photographers) {
            if (photographer.getYearsOfExperience() == years+1 || photographer.getYearsOfExperience() == years-1) {
                result.add(photographer);
            }
        }
        return result;
    }

    public List<Photographer> getPhotographers() {
        return photographers;
    }

}
package com.maryzh555.photo_studio.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.maryzh555.photo_studio.enums.Location;
import com.maryzh555.photo_studio.enums.PhotoType;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;

/**
 * Created by Zhang M. on 23.03.2023.
 */
public class PhotoStudio {

    private final List<Photographer> photographers;

    private final List<PhotoType> photoTypes;

    private final List<Location> locations;

    public PhotoStudio() {
        photographers = createRandomPhotographers(12);
        photoTypes = fillPhotoTypeList();
        locations = fillLocationsList();
    }

    /**
     * This method generates Photographers and appends them to the result list.
     * The Photographers are divided into 4 groups with predefined ranges of years of experience and hourly rates.
     * The goal pf the loop is to gradually increase years of experience and hourly rates within each group.
     * And to increase the probability of having every year of experience in the range [1, 6].
     * param j - divides in 3 groups
     * param i - creates 4 photographers for each group
     * param k - sets the lower bound of years of experience for each group, result ranges: [1,2], [3,4], [5,6]
     * param l - sets the lower and higher bound of hourlyRate for each group, result ranges: [10,15],[16,27],[28,51]
     */
    private List<Photographer> createRandomPhotographers(int quantity) {
        List<Photographer> result = new ArrayList<>();
        String[] NAMES = {"Oleg", "Igor", "Katya", "Tanya"};
        Random random = new Random();
        for (int j = 0, k = 1, l = 6;
             j < 3;
             j++, k += 2, l *= 2) {
            for (int i = 0; i < quantity / 3; i++) {
                Photographer photographer = new Photographer(
                        NAMES[random.nextInt(4)],
                        random.nextInt(2) + k,
                        random.nextInt(l) + (l + 4));
                result.add(photographer);
            }
        }
        return result;
    }

    private List<PhotoType> fillPhotoTypeList() {
        List<PhotoType> result = new ArrayList<>();
        Collections.addAll(result, PhotoType.values());
        return result;
    }

    private List<Location> fillLocationsList() {
        List<Location> result = new ArrayList<>();
        Collections.addAll(result, Location.values());
        return result;
    }

    public PhotoType matchPhotoType(int numberOfPeople) throws NoSuchOptionException {
        PhotoType type;
        if (numberOfPeople == 1) {
            type = photoTypes.get(0);
        } else if (numberOfPeople > 1 && numberOfPeople <= 10) {
            type = photoTypes.get(1);
        } else if (numberOfPeople > 10 && numberOfPeople <= 50) {
            type = photoTypes.get(2);
        } else throw new NoSuchOptionException();
        return type;
    }


    public Location[] returnArrayOfLocations(PhotoType photoType) {
        Location[] array;    // The array used for compact initialization compared to List's lengthy initialization.
        switch (photoType.ordinal()) {
            case 0:
                array = new Location[]{locations.get(0), locations.get(2)};
                break;
            case 1:
                array = new Location[]{locations.get(1), locations.get(2)};
                break;
            case 2:
                array = new Location[]{locations.get(1)};
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


    public List<Photographer> getPhotographers() {
        return photographers;
    }

    public List<PhotoType> getPhotoTypes() {
        return photoTypes;
    }

    public List<Location> getLocations() {
        return locations;
    }
}
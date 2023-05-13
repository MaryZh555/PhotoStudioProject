package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.enums.Location;
import main.com.maryzh555.photo_studio.enums.PhotoType;
import main.com.maryzh555.photo_studio.exceptions.EmptyListException;
import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.IReport;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public class CustomerManager extends Worker implements IReport {

    private int servicedClients;


    public CustomerManager(String name, int hourlyRate) {
        super(name);//set id and name
        this.hourlyRate = hourlyRate;
    }

    public CustomerManager(int neededYears) {
        this.neededExperience = neededYears;
    }


    public void introduceYourself() {
        System.out.println("My name is " + this.getName() + ". ");
    }

    public void addOrderToTheSystemList(PhotoStudio photoStudio, Order order) {
        photoStudio.getDigitalStorage().addOrder(order);
    }

    public void addServicedClients() {
        this.servicedClients++;
    }


    public int[] calculateYearsOfExperience(PhotoStudio photoStudio) throws EmptyListException {
        if (photoStudio.getDirector().getAllPhotographers().isEmpty()) {
            System.out.println("The list of photographers is empty.");
            throw new EmptyListException("All Photographers");
        }

        int[] result = new int[2]; // Array to store min and max values
        int minExperience = Integer.MAX_VALUE;
        int maxExperience = Integer.MIN_VALUE;

        for (Photographer photographer : photoStudio.getDirector().getAllPhotographers()) {
            int yearsOfExperience = photographer.getYearsOfExperience();
            if (yearsOfExperience < minExperience) {
                minExperience = yearsOfExperience;
            }
            if (yearsOfExperience > maxExperience) {
                maxExperience = yearsOfExperience;
            }
        }

        result[0] = minExperience;
        result[1] = maxExperience;

        return result;
    }

    /// All match method can be done by the customer manager ///
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


    public List<Location> matchLocations(PhotoType photoType) {
        List<Location> list = new ArrayList<>();
        switch (photoType) {
            case PORTRAIT:
                list.addAll(Arrays.asList(Location.CHROMA_CHARM, Location.RIVER_VIEW_TERRACE));
                break;
            case GROUP:
                list.addAll(Arrays.asList(Location.GROUP_HUB, Location.RIVER_VIEW_TERRACE));
                break;
            case TEAM:
                list.add(Location.GROUP_HUB);
                break;
            default:
                break;
        }
        return list;
    }

    public List<Photographer> matchPhotographers(int years, PhotoStudio photoStudio) {
        List<Photographer> result = new ArrayList<>();
        for (Photographer photographer : photoStudio.getDirector().getAllPhotographers()) {
            if (photographer.getYearsOfExperience() == years) {
                result.add(photographer);
            }
        }
        return result;
    }

    public List<Photographer> findAlternativePhotographers(int years, PhotoStudio photoStudio) {
        int maxYears = 6; // set here as the temporary solution, until HR is added, and the MaxAllowedYears variable is set
        List<Photographer> result = new ArrayList<>();
        int diff = 1;
        while (result.isEmpty()) {
            for (Photographer photographer : photoStudio.getDirector().getAllPhotographers()) {
                if (photographer.getYearsOfExperience() == years + diff || photographer.getYearsOfExperience() == years - diff) {
                    result.add(photographer);
                }
            }
            diff++;
            if (diff > maxYears) {
                break;
            }
        }
        return result;
    }

    public int calculateMaxYearsOfExperience(int age) {
        int maxYears;
        maxYears = age - 18;
        return maxYears;
    }

    @Override
    public void report() {
        System.out.println("( Customer Manager " + this.getName() + " reports: " +
                "Serviced clients today: " + this.servicedClients + " )");
    }


    public int getServicedClients() {
        return servicedClients;
    }

    public void setServicedClients(int servicedClients) {
        this.servicedClients = servicedClients;
    }
}

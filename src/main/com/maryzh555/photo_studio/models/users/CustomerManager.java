package com.maryzh555.photo_studio.models.users;

import com.maryzh555.photo_studio.enums.Location;
import com.maryzh555.photo_studio.enums.PhotoType;
import com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import com.maryzh555.photo_studio.interfaces.Report;
import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public class CustomerManager extends Worker implements Report {
    // Costumer manages is the one who manages the client.
    // They should introduce their name when servicing the client.
    // The client menu will have "rate our service" form. So we know how the managers works.
    // What to report? CustomerManager reports how many customers they serviced and how customers rated their work.
    // If 5 client in row rates less than 3, the manager should talk with director about their performance

    private final List<Order> listOfOrders;

    private int servicedClients;


    public CustomerManager(String name, int hourlyRate) {
        super(name);//set id and name
        this.hourlyRate = hourlyRate;
        this.listOfOrders = new ArrayList<>();
    }


    public void introduceYourself(){
        System.out.println("My name is " + this.getName() + ". ");
    }

    public void addOrderToTheSystemList(Order order) {
        this.listOfOrders.add(order);
    }

    public void addServicedClients(){
        this.servicedClients++;
    }

    public void removeServicedClients(){this.servicedClients--;}


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

    @Override
    public void report() {
        System.out.println("( Customer Manager " + this.getName() + " reports: " +
                "Serviced clients today: " + this.servicedClients + " )");
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public int getServicedClients() {
        return servicedClients;
    }

    public void setServicedClients(int servicedClients) {
        this.servicedClients = servicedClients;
    }
}

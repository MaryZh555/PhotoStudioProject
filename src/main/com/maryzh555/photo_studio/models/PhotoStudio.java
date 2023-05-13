package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.PhotoPaperType;
import main.com.maryzh555.photo_studio.models.users.Client;
import main.com.maryzh555.photo_studio.models.users.Director;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Zhang M. on 23.03.2023.
 */
public class PhotoStudio {

    private Director director;

    private Storage storage;

    private List<Paper> storedPaper;

    private List<Client> registeredClients;


    public PhotoStudio() {
        this.director = new Director();
        this.storage = new Storage();
        this.registeredClients = new ArrayList<>();
        prepareEquipment(this);
//        test.paperTest(this);
    }


    private void prepareEquipment(PhotoStudio photoStudio) {
        //this method will also implement future methods of other workers.
        photoStudio.storedPaper = photoStudio.setStoredPaper(0, 0, 0);
        photoStudio.director.getSupplyManager().addPaperToStorage(photoStudio, 1050, 525, 110);
        photoStudio.director.getSupplyManager().refillPhotoPaperInStudio(photoStudio, 50, 25, 10);
        photoStudio.director.getSupplyManager().fillStoredCameras(photoStudio, 10);
    }

    //todo reconsider the object orientation logic for the hole code
    // where to put registered clients list? In real life it exist in the database, make class DigitalStorage to contain DigitalLists?
    // RegisteredClientsList, listOfOrders
    public void addToRegisteredList(Client client) {
        this.registeredClients.add(client);
    }

    public boolean checkIfRegistered(Client client) {
        boolean result = false;
        if (this.registeredClients.isEmpty()) {
            return false;
        } else {
            for (Client client1 :
                    this.registeredClients) {
                if (client1.getName().equals(client.getName()) &&
                        client1.getSurname().equals(client.getSurname()) &&
                        client1.getContactNumber().equals(client.getContactNumber())) {
                    result = true;
                }
            }
        }
        return result;
    }

    public Client returnRegisteredClient(Client client) {
        for (Client registeredClient : this.registeredClients) {
            if (registeredClient.getName().equals(client.getName()) &&
                    registeredClient.getSurname().equals(client.getSurname()) &&
                    registeredClient.getContactNumber().equals(client.getContactNumber())) {
                return registeredClient;
            }
        }
        return null;
    }

    public boolean checkIfReadyToCheckOut(Order order) {
        return order.getDesiredPhotographer() != null &&
                order.getOrderedPhoto().getType() != null &&
                order.getOrderedPhoto().getPrintStandardQty() != -1 &&
                order.getDesiredLocation() != null;
    }

    /// Getters & Setters
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public int getQtyOfStandardPaper() {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.STANDARD) {
                return paper.getQty();
            }
        return 0;
    }

    public int getQtyOfLargePaper() {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.LARGE) {
                return paper.getQty();
            }
        return 0;
    }

    public int getQtyOfProfessionalPaper() {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.PROFESSIONAL) {
                return paper.getQty();
            }
        return 0;
    }

    public void setQtyOfStandardPaper(int qtyOfStandardPaper) {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.STANDARD) {
                paper.setQty(qtyOfStandardPaper);
            }
    }

    public void setQtyOfLargePaper(int qtyOfLargePaper) {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.LARGE) {
                paper.setQty(qtyOfLargePaper);
            }
    }

    public void setQtyOfProfessionalPaper(int qtyOfProfessionalPaper) {
        for (Paper paper : storedPaper)
            if (paper.getType() == PhotoPaperType.PROFESSIONAL) {
                paper.setQty(qtyOfProfessionalPaper);
            }
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Paper> getStoredPaper() {
        return storedPaper;
    }

    public List<Paper> setStoredPaper(int qtyStandard, int qtyLarge, int qtyPro) {
        return new ArrayList<>(Arrays.asList(
                new Paper(qtyStandard, PhotoPaperType.STANDARD),
                new Paper(qtyLarge, PhotoPaperType.LARGE),
                new Paper(qtyPro, PhotoPaperType.PROFESSIONAL)
        ));
    }

    public List<Client> getRegisteredClients() {
        return registeredClients;
    }

    public void setRegisteredClients(List<Client> registeredClients) {
        this.registeredClients = registeredClients;
    }
}
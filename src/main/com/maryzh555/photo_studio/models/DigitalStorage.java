package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.WorkerType;
import main.com.maryzh555.photo_studio.models.users.Client;
import main.com.maryzh555.photo_studio.models.users.CustomerManager;
import main.com.maryzh555.photo_studio.models.users.HRManager;
import main.com.maryzh555.photo_studio.models.users.Photographer;
import main.com.maryzh555.photo_studio.models.users.SupplyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Zhang M. on 13.05.2023.
 */
public class DigitalStorage {

    private List<Client> registeredClients; //from PhotoStudio

    private final List<Order> listOfOrders; //from CustomerManager

    private final List<HRManager> hrManagerList; // from Director

    private final List<CustomerManager> customerManagerList;  // from Director

    private final List<SupplyManager> supplyManagerList;  // from Director

    private final List<Photographer> allPhotographers; // from Director

    private List<Vacancy> vacancies;  //from HRManager


    public DigitalStorage() {
        this.registeredClients = new ArrayList<>();
        this.listOfOrders = new ArrayList<>();
        this.hrManagerList = fillHRManagers();
        this.customerManagerList = fillCustomerManager();
        this.supplyManagerList = fillSupplyManagers();
        this.allPhotographers = fillPhotographers();
        this.vacancies = fillVacancies();
    }

    public void addToRegisteredList(Client client) {
        this.registeredClients.add(client);
    }

    public void addOrder(Order order) {
        this.listOfOrders.add(order);
    }


    //NOTE:
    // The ArrayList is used instead of List.of to avoid the UnsupportedOperationException exception when the new worker is added to the list when hiring
    public List<HRManager> fillHRManagers() {
        List<HRManager> result = new ArrayList<>();
        result.add(new HRManager("Oleg", 15));
        result.add(new HRManager("Marina", 12));
        result.add(new HRManager("Antonina", 20));
        return result;

    }

    public List<SupplyManager> fillSupplyManagers() {
        List<SupplyManager> result = new ArrayList<>();
        result.add(new SupplyManager("Oleg", 15));
        result.add(new SupplyManager("Marina", 12));
        result.add(new SupplyManager("Antonina", 20));
        return result;

    }

    public List<CustomerManager> fillCustomerManager() {
        List<CustomerManager> result = new ArrayList<>();
        result.add(new CustomerManager("Oleg", 15));
        result.add(new CustomerManager("Marina", 12));
        result.add(new CustomerManager("Antonina", 20));
        return result;
    }

    //The photographers data is written here until the database is implemented.
    public List<Photographer> fillPhotographers() {
        List<Photographer> result = new ArrayList<>();
        result.add(new Photographer("Tasha", 1, 10, false, null));
        result.add(new Photographer("Sasha", 2, 12, false, null));
        result.add(new Photographer("Misha", 3, 15, false, null));
        result.add(new Photographer("Dasha", 4, 18, false, null));
        result.add(new Photographer("Masha", 5, 25, false, null));
        result.add(new Photographer("Dimas", 6, 35, false, null));
        return result;
    }

    private List<Vacancy> fillVacancies() {
        ArrayList<Vacancy> result = new ArrayList<>();
        result.add(new Vacancy(WorkerType.PHOTOGRAPHER, 5, 49, 30));
        result.add(new Vacancy(WorkerType.PHOTOGRAPHER, 10, 49, 30));
        result.add(new Vacancy(WorkerType.SUPPLY_MANAGER, 1, 49, 15));
        return result;

    }

    public void deleteVacancy(Vacancy vacancy){
        this.vacancies.remove(vacancy);
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
                    break;
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

    public List<Vacancy> matchVacancies(WorkerType type) {
        List<Vacancy> result = new ArrayList<>();
        for (Vacancy vacancy : this.vacancies) {
            if (vacancy.getWorkerType() == type) {
                result.add(vacancy);
            }
        }
        return result;
    }


    public List<Client> getRegisteredClients() {
        return registeredClients;
    }

    public void setRegisteredClients(List<Client> registeredClients) {
        this.registeredClients = registeredClients;
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public List<HRManager> getHrManagerList() {
        return hrManagerList;
    }

    public List<CustomerManager> getCustomerManagerList() {
        return customerManagerList;
    }

    public List<SupplyManager> getSupplyManagerList() {
        return supplyManagerList;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public List<Photographer> getAllPhotographers() {
        return allPhotographers;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

}

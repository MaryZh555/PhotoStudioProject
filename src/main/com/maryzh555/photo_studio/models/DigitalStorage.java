package main.com.maryzh555.photo_studio.models;

import main.com.maryzh555.photo_studio.enums.WorkerType;
import main.com.maryzh555.photo_studio.models.users.*;

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

    private List<Candidate> vacancies;  //from HRManager

    //todo put the final fields of max and minYears of experience for candidates here?


    public DigitalStorage() {
        this.registeredClients = new ArrayList<>();
        this.listOfOrders = new ArrayList<>();
        this.hrManagerList = fillHRManagers();
        this.customerManagerList = fillCustomerManager();
        this.supplyManagerList = fillSupplyManagers();
        this.vacancies = fillVacancies();
    }

    public void addToRegisteredList(Client client) {
        this.registeredClients.add(client);
    }

    public void addOrder(Order order) {
        this.listOfOrders.add(order);
    }

    public List<HRManager> fillHRManagers() {
        return List.of(
                new HRManager("Oleg", 15),
                new HRManager("Marina", 12),
                new HRManager("Antonina", 20)
        );
    }

    public List<SupplyManager> fillSupplyManagers() {
        return List.of(
                new SupplyManager("Oleg", 15),
                new SupplyManager("Marina", 12),
                new SupplyManager("Antonina", 20)
        );
    }

    public List<CustomerManager> fillCustomerManager() {
        return List.of(
                new CustomerManager("Oleg", 15),
                new CustomerManager("Marina", 12),
                new CustomerManager("Antonina", 20)
        );
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

    private List<Candidate> fillVacancies() {
        List<Candidate> result = new ArrayList<>();
        result.add(new Candidate(WorkerType.PHOTOGRAPHER, 5, 49, 30));//67-18 = 49 // maxExperience can be whatever number HRmanager chooses;
        result.add(new Candidate(WorkerType.PHOTOGRAPHER, 10, 49, 30));
        result.add(new Candidate(WorkerType.SUPPLY_MANAGER, 1, 49, 15));
        return result;
    }

    public List<Candidate> matchVacancies(WorkerType type) {
        List<Candidate> result = new ArrayList<>();
        for (Candidate candidate : this.vacancies) {
            if (candidate.getWorkerType() == type) {
                result.add(candidate);
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

    public List<Candidate> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Candidate> vacancies) {
        this.vacancies = vacancies;
    }
}

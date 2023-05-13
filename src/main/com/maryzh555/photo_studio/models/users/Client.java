package main.com.maryzh555.photo_studio.models.users;

import main.com.maryzh555.photo_studio.interfaces.OrderOrClient;
import main.com.maryzh555.photo_studio.models.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang M. on 17.03.2023.
 */
public class Client extends User implements OrderOrClient {

    private String surname;

    private String contactNumber;

    private List<Order> orderList;


    public Client() {
        super();
        this.orderList = new ArrayList<>();
    }

    public void addToOrderList(Order order){
        this.orderList.add(order);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}



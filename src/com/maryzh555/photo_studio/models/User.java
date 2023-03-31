package com.maryzh555.photo_studio.models;

/**
 * Created by Zhang M. on 17.03.2023.
 */
public class User {

    private String name;

    private String surname;

    private String contactNumber;


    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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


}



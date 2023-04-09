package com.maryzh555.photo_studio.models.humans;

/**
 * @author Zhang M. on 17.03.2023.
 */
public class User extends Human {

    private String surname;

    private String contactNumber;

    public User() {
        this.id = idCounter++; //temporary solution for id
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



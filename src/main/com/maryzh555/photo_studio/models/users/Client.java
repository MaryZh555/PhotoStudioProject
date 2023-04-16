package main.com.maryzh555.photo_studio.models.users;

/**
 * @author Zhang M. on 17.03.2023.
 */
public class Client extends User {

    private String surname;

    private String contactNumber;

    public Client() {
        super();
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



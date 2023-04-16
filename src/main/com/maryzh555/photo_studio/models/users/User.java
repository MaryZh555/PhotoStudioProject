package main.com.maryzh555.photo_studio.models.users;

/**
 * The abstract class Human represents a basic human with an identifier and a name.
 *
 * @author Zhang M. on 03.04.2023.
 */
public abstract class User {
    // The id value will be reset to 1 on every run of the program, the id field is added for the future database use.
    // The idCounter is a temporary solution until the database will be implemented.
    static int idCounter = 1;

    private int id;

    private String name;

    public User(){
        this.id = idCounter++;
    }

    public User(String name) {
        this();
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

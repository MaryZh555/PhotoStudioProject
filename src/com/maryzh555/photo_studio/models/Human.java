package com.maryzh555.photo_studio.models;

/**
 * The abstract class Human represents a basic human with an identifier and a name.
 *
 * @author Zhang M. on 03.04.2023.
 */
public abstract class Human {
    // The id value will be reset to 1 on every run of the program, the id field is added for the future database use.
    // The idCounter is a temporary solution until the database will be implemented.
    protected static int idCounter = 1;

    protected int id;

    protected String name;


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

package com.maryzh555.photo_studio.models;

/**
 * The abstract class Human represents a basic human with an identifier and a name.
 * @author Zhang M. on 03.04.2023.
 */
public abstract class Human {
    public int id;
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

}

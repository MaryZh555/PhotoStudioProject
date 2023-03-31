package com.maryzh555.photo_studio.exceptions;

/**
 * Created by Zhang M. on 23.03.2023.
 */
public class NoSuchOptionException extends Exception{

    public NoSuchOptionException(){
        super("---------\n" +
                "ERROR: There is no such options! Please try again." +
                "\n---------");
    }
}

package com.maryzh555.photo_studio.exceptions;

/**
 * Created by Zhang M. on 25.03.2023.
 */
public class WrongNumberException extends Exception{
    public WrongNumberException(){
        super("---------\n" +
                "ERROR: Phone number should contain 10 digits, and start with a zero!" +
                "\n---------");
    }
}

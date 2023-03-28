package com.maryzh555.photo_studio.exceptions;

/**
 * Created by zhmas on 23.03.2023.
 */
public class WrongNameException extends Exception {
    public WrongNameException() {
        super("---------\n" +
                "ERROR: Name/Surname must contain at least 3 letters, cannot be empty, contain numbers or special symbols!" +
                "\n---------");

    }
}

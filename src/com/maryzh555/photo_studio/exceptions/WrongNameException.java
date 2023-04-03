package com.maryzh555.photo_studio.exceptions;

/**
 * An exception that is thrown when the name or surname entered by the user is not valid.
 * The name/surname must contain at least 3 English letters, cannot be empty, and cannot contain numbers or special symbols.
 *
 * @author Zhang M. on 23.03.2023.
 */
public class WrongNameException extends Exception {
    public WrongNameException() {
        super("---------\n" +
                "ERROR: Name/Surname must contain at least 3 letters, cannot be empty, contain numbers or special symbols!" +
                "\n---------");

    }
}

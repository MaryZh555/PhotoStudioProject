package main.com.maryzh555.photo_studio.exceptions;

/**
 * Exception thrown when a phone number is invalid.
 * The phone number should contain 10 digits, and start with a zero.
 *
 * @author Zhang M. on 25.03.2023.
 */
public class WrongNumberException extends Exception{
    public WrongNumberException(){
        super("---------\n" +
                "ERROR: Phone number should contain 10 digits, and start with a zero!" +
                "\n---------");
    }
}

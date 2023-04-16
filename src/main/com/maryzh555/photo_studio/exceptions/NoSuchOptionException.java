package main.com.maryzh555.photo_studio.exceptions;

/**
 * This exception is thrown when the user enters a number that is out of the given range of options.
 * It extends the Exception class and provides a custom message that is displayed to the user when it is thrown.
 *
 * @author Zhang M. on 23.03.2023.
 */
public class NoSuchOptionException extends Exception{

    public NoSuchOptionException(){
        super("---------\n" +
                "ERROR: There is no such options! Please try again." +
                "\n---------");
    }
}

package main.com.maryzh555.photo_studio.exceptions;

/**
 * @author by Zhang M. on 11.04.2023.
 */
public class EmptyListException extends Exception {
    public EmptyListException(String listName){
        super("---------\n" +
                "ERROR: It seems like the " + listName + " list is empty. " +
                "The program will end.\n" +
                "Please contact the customer support to fix the problem" +
                "\n---------");
    }
}

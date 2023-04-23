package main.com.maryzh555.photo_studio.exceptions;

/**
 * @author by Zhang M. on 21.04.2023.
 */
public class WrongAgeException extends Exception {

    public WrongAgeException(int retirementAge) {
        super("---------\n" +
                "ERROR: We are hiring workers with age between 18 - " + retirementAge + ". " +
                "\nThe years of experience are counted after 18" +
                "\n---------");
    }

}

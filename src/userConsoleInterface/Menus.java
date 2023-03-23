package userConsoleInterface;

import exceptions.*;

import java.util.Scanner;

/**
 * Created by zhmas on 23.03.2023.
 */
public class Menus {

    public static int checkingForException(Scanner scanner, int min, int max) throws NoSuchOptionException, NotAnIntegerException {
        int answer;
        if (scanner.hasNextInt()) {
            answer = scanner.nextInt();
            if (answer < min || answer > max)
                throw new NoSuchOptionException();
        } else {
            throw new NotAnIntegerException();
        }
        return answer;
    }
}






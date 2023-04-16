package main.com.maryzh555.photo_studio;

import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.user_console_interface.MainMenu;

/**
 * The Main class, that starts the program
 *
 * @author Zhang M. on 20.03.2023.
 */
public class Main {
    public static void main(String[] args) {
        new MainMenu(new PhotoStudio());
    }
}

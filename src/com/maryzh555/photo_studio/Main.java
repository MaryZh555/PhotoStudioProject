package com.maryzh555.photo_studio;

import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.humans.User;
import com.maryzh555.photo_studio.user_console_interface.MainMenu;

/**
 * The Main class, that starts the program
 *
 * @author Zhang M. on 20.03.2023.
 */
public class Main {
    public static void main(String[] args) {
        // todo why creating new objects in the main method
        PhotoStudio photoStudio = new PhotoStudio();
        User user = new User();
        Order order = new Order();

        new MainMenu(photoStudio, user, order);
    }
}

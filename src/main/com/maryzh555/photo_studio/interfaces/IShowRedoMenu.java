package main.com.maryzh555.photo_studio.interfaces;

import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.user_console_interface.Menu;

import java.util.Scanner;

/**
 * @author by Zhang M. on 18.04.2023.
 */
public interface IShowRedoMenu {

    <T extends OrderOrClient> void showRedoMenu(Scanner scanner, T orderOrClient, PhotoStudio photoStudio, Menu menu);
}

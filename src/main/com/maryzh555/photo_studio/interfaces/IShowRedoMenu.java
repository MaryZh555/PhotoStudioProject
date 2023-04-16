package main.com.maryzh555.photo_studio.interfaces;

import java.util.Scanner;

import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

/**
 * @author Zhang M. on 17.03.2023.
 */
public interface IShowRedoMenu {
    void showRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio);
}

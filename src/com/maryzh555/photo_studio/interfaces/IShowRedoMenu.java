package com.maryzh555.photo_studio.interfaces;

import com.maryzh555.photo_studio.models.Order;
import com.maryzh555.photo_studio.models.PhotoStudio;
import com.maryzh555.photo_studio.models.User;

import java.util.Scanner;

public interface IShowRedoMenu {
    void showRedoMenu(Scanner scanner, User user, Order order, PhotoStudio photoStudio);
}

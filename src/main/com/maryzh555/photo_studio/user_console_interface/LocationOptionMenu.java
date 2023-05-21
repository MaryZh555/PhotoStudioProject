package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.EmptyListException;
import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.enums.Location;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 * @author Zhang M. on 20.03.2023.
 */

public class LocationOptionMenu extends Menu{
    public LocationOptionMenu(Scanner scanner, Order order, PhotoStudio photoStudio) {
        while (true) {
            try {
                if(order.getDesiredLocation() != null){
                    System.out.println("You already chose " + order.getDesiredLocation().name() +
                            ". Do you want to chose another one?" +
                            "\n 1 - Chose the new one" +
                            "\n 2 - Back to the Order Menu");
                    int answer = scanner.nextInt();

                    switch (answer) {
                        case 2:
                            new OrderMenu(scanner, order, photoStudio);
                            break;
                        case 1:
                            order.setDesiredLocation(null);
                            showMenu(scanner, order, photoStudio);
                            break;
                        default:
                            throw new NoSuchOptionException();
                    }
                } else{
                    showMenu(scanner, order, photoStudio);
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public  void showMenu(Scanner scanner, Order order, PhotoStudio photoStudio){
        while (true) {
            try {
                System.out.println("\nOur studio offers a location renting.\n" +
                        "For " + order.getOrderedPhoto().getType() +
                        " photo shoot we can suggest:");

                List<Location> locationsList = photoStudio.getCustomerManager().matchLocations(order.getOrderedPhoto().getType());

                int i = 0;
                for (Location location : locationsList) {
                    System.out.println(
                            " " + i + " - " + location + " :\n" +
                                    "     " + location.getDescription() + "\n     " +
                                    "Renting cost is " + location.getRentingCost() + " per hour.");
                    i++;
                }
                if (locationsList.isEmpty()) throw new EmptyListException("LOCATIONS");
                if (order.getOrderedPhoto().getType().ordinal() == 2) {
                    order.setDesiredLocation(Location.GROUP_HUB);
                } else {
                    System.out.println("\nWhich location you would prefer?");

                    int answer = scanner.nextInt();
                    if (answer < 0 || answer > 1) throw new NoSuchOptionException();
                    order.setDesiredLocation(photoStudio.getCustomerManager().matchLocations(order.getOrderedPhoto().getType()).get(answer));

                    System.out.println(
                            "You chose a " +
                                    order.getDesiredLocation() +
                                    " location. Good choice!");

                }
                new RedoMenu(scanner, order, photoStudio,this);
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (EmptyListException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "Invalid input: Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

}

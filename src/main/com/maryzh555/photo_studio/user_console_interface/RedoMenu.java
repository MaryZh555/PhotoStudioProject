package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.InputMismatchException;
import java.util.Scanner;

//todo PickUophoto redo
/**
 * @author by Zhang M. on 18.04.2023.
 */
public class RedoMenu implements IShowRedoMenu {

    public RedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu) {
        showRedoMenu(scanner, order, photoStudio, menu);
    }

    @Override
    public void showRedoMenu(Scanner scanner, Order order, PhotoStudio photoStudio, Menu menu) {
        while (true) {
            try {
                //test.testMenuName(menu);//test
                ///// menu text previous
                String previousMenuName;
                String message = null;
                switch (menu.toString()) {
                    case "CandidateMenu":
                        previousMenuName = "(User distribution)";
                        break;
                    case "CandidateDataForm":
                        previousMenuName = "(Job Candidate)";
                        break;
                    case "HiringMenu":
                        previousMenuName = "(Candidate Data Form)";
                        break;

                    case "ClientDistributionMenu":
                        previousMenuName = "(User distribution)";
                        message = "\nDo you want to quit or to redo?" +
                                "\n 2 - Redo" +
                                "\n 3 - Go to previous ->" + previousMenuName +
                                "\n 4 - Leave(quit)";
                        break;

                    case "ClientOptionMenu":
                        previousMenuName = "(Client distribution)";
                        message = "\nDo you want to quit or to redo?" +
                                "\n 2 - Redo" +
                                "\n 3 - Go to previous ->" + previousMenuName +
                                "\n 4 - Leave(quit)";
                        break;

                    case "RegisterSignInMenu":
                        previousMenuName = "(Client distribution)";
                        message = "\nDo you want to quit or to redo?" +
                                "\n 2 - Redo ->" + previousMenuName +
                                "\n 4 - Leave(quit)";
                        break;

                    case "PickUpPhotoMenu":
                    case "OrderMenu":
                        previousMenuName = "(Client option Menu)";
                        message = "\nDo you want to quit or to redo?" +
                                "\n 2 - Redo" +
                                "\n 3 - Go to previous ->" + previousMenuName +
                                "\n 4 - Leave(quit)";
                        break;

                    case "PhotographersOptionMenu":
                    case "PhotoTypeOptionMenu":
                    case "LocationOptionMenu":
                    case "PrintingMenu":
                        previousMenuName = "(Order Menu)";
                        break;

                    default:
                        previousMenuName = "";
                        break;
                }


                if (message == null) {
                    message = "\nDo you want to continue or to redo?" +
                            "\n 1 - Let's continue." +
                            "\n 2 - Redo." +
                            "\n 3 - Go to previous ->" + previousMenuName +
                            "\n 4 - Leave(quit)";
                }

                System.out.println(message);

                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:

                        switch (menu.toString()) {

                            case "CandidateMenu":
                                scanner.nextLine();
                                new CandidateDataForm(scanner, photoStudio);
                                break;
                            case "CandidateDataForm":
                                new HiringMenu(scanner, photoStudio);
                                break;
                            case "PhotographersOptionMenu":
                            case "PhotoTypeOptionMenu":
                            case "LocationOptionMenu":
                            case "PrintingMenu":
                                new OrderMenu(scanner, order, photoStudio);
                                break;
                            case "PickUpPhotoMenu":
                            case "RegisterSignInMenu":
                            case "ClientDistributionMenu":
                            case "ClientOptionMenu":
                            case "OrderMenu":
                                throw new NoSuchOptionException();

                        }
                        break;

                    case 2:

                        switch (menu.toString()) {
                            case "RegisterSignInMenu":
                                new ClientDistributionMenu(scanner, order, photoStudio);
                                break;
                            case "PickUpPhotoMenu":
                                scanner.nextLine();
                                menu.showMenu(scanner, order.getClient(), photoStudio);
                                break;
                            default:
                                scanner.nextLine();
                                menu.showMenu(scanner, order, photoStudio);
                                break;
                        }
                        break;
                    case 3:

                        switch (menu.toString()) {
                            case "CandidateMenu":
                            case "ClientDistributionMenu":
                                new UserDistributionMenu(scanner, order, photoStudio);
                                break;
                            case "CandidateDataForm":
                                new CandidateMenu(scanner, photoStudio);
                                break;
                            case "PhotographersOptionMenu":
                            case "PhotoTypeOptionMenu":
                            case "LocationOptionMenu":
                            case "PrintingMenu":
                                scanner.nextLine();
                                new OrderMenu(scanner, order, photoStudio);
                                break;
                            case "ClientOptionMenu":
                                new ClientDistributionMenu(scanner, order, photoStudio);
                                break;
                            case "PickUpPhotoMenu":
                            case "OrderMenu":
                                new ClientOptionMenu(scanner, order.getClient(), photoStudio);
                                break;
                            case "RegisterSignInMenu":
                                throw new NoSuchOptionException();
                        }
                        break;
                    case 4:
                        new QuitMenu(scanner, order, photoStudio, menu);
                        break;
                    default:
                        throw new NoSuchOptionException();
                }
                break;
            } catch (NoSuchOptionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("---------\n" +
                        "ERROR: Invalid input. Not an integer" +
                        "\n---------");
                scanner.next(); // clear the input buffer
            }
        }
    }

}

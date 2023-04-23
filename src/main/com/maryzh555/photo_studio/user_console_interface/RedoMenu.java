package main.com.maryzh555.photo_studio.user_console_interface;

import main.com.maryzh555.photo_studio.exceptions.NoSuchOptionException;
import main.com.maryzh555.photo_studio.interfaces.IShowRedoMenu;
import main.com.maryzh555.photo_studio.models.Order;
import main.com.maryzh555.photo_studio.models.PhotoStudio;

import java.util.InputMismatchException;
import java.util.Scanner;

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
                    case "ClientDataFormMenu":
                        previousMenuName = "(Order/Pick up)";
                        break;
                    case "PhotographersOptionMenu":
                        previousMenuName = "(Client Data)";
                        break;
                    case "PhotoTypeOptionMenu":
                        previousMenuName = "(Photographers)";
                        break;
                    case "LocationOptionMenu":
                        previousMenuName = "(Photo Type)";
                        break;
                    case "PrintingMenu":
                        previousMenuName = "(Location)";
                        break;
                    default:
                        previousMenuName = "";
                        break;
                }
                /////
                System.out.println("\nDo you want to continue or to redo?" +
                        "\n 1 - Let's continue." +
                        "\n 2 - Redo." +
                        "\n 3 - Go to previous ->" + previousMenuName +
                        "\n 4 - Leave(quit)");

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
                            case "ClientDataFormMenu":
                                new PhotographersOptionMenu(scanner, order, photoStudio);
                                break;
                            case "PhotographersOptionMenu":
                                new PhotoTypeOptionMenu(scanner, order, photoStudio);
                                break;
                            case "PhotoTypeOptionMenu":
                                new LocationOptionMenu(scanner, order, photoStudio);
                                break;
                            case "LocationOptionMenu":
                                new PrintingMenu(scanner, order, photoStudio);
                                break;
                            case "PrintingMenu":
                                new CalculateTotalMenu(scanner, order, photoStudio);
                                break;
                        }
                        break;

                    case 2:
                        scanner.nextLine();
                        menu.showMenu(scanner, order, photoStudio);
                        break;

                    case 3:

                        switch (menu.toString()) {
                            case "CandidateMenu":
                                new UserDistributionMenu(scanner, photoStudio);
                                break;
                            case "CandidateDataForm":
                                new CandidateMenu(scanner, photoStudio);
                                break;
                            case "ClientDataFormMenu":
                                new MainMenu(photoStudio);
                                break;
                            case "PhotographersOptionMenu":
                                scanner.nextLine();
                                new ClientDataFormMenu(scanner, order, photoStudio);
                                break;
                            case "PhotoTypeOptionMenu":
                                new PhotographersOptionMenu(scanner, order, photoStudio);
                                break;
                            case "LocationOptionMenu":
                                new PhotoTypeOptionMenu(scanner, order, photoStudio);
                                break;
                            case "PrintingMenu":
                                new LocationOptionMenu(scanner, order, photoStudio);
                                break;
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

package View;

import Controller.ChangeMenuController;
import Controller.MainController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private ChangeMenuController changeMenuController;
    private MainController maincontroller;
    private Matcher matcher;
    private String input;
    public MainMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.maincontroller = changeMenuController.getMainController();
    }
    
    public String run(Scanner scanner) {
        while (true) {
            input = scanner.nextLine();
    
            if ((matcher = Commands.getMatcher(input,Commands.LOGOUT)).find()) {
                return "logout";
            }
            
            if ((matcher = Commands.getMatcher(input,Commands.SHOWMAP)).find()) {
                return "mapMenu";
            }
            
            if
        }
    }
}

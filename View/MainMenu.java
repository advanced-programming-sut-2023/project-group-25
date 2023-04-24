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
            
            else if ((matcher = Commands.getMatcher(input,Commands.SHOWMAP)).find()) {
                return "map menu";
            }
            
            else if ((matcher = Commands.getMatcher(input,Commands.ENTERPROFILEMENU)).find()) {
                return "profile menu";
            }
            
            else if ((matcher = Commands.getMatcher(input,Commands.ENTERTRADEMENU)).find()) {
                return "trade menu";
            }
        }
    }
}

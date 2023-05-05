package View;

import Controller.ChangeMenuController;
import Controller.MainController;
import Controller.RegisterLoginController;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private ChangeMenuController changeMenuController;
    private RegisterLoginController registerLoginController;
    private MainController maincontroller;
    private Matcher matcher;
    private String input;
    public MainMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.maincontroller = changeMenuController.getMainController();
        this.registerLoginController = changeMenuController.getRegisterLoginController();
    }
    
    public String run(Scanner scanner) {
        System.out.println(registerLoginController.showCurrentMenuName("MAIN MENU"));
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input,Commands.LOGOUT)) != null) {
                registerLoginController.addStayLoggedInForUser(RegisterLoginController.getCurrentUser().getUsername(), false);
                return "logout";
            }
            else if (((matcher = Commands.getMatcher(input, Commands.SHOW_MAP_BEFORE_STARTING_THE_GAME))) != null) {

                return "map menu";
            }
//            else if (((matcher = Commands.getMatcher(input, Commands.SHOW_MAP))) != null) {
//                return "map menu";
//            }
            
            else if (((matcher = Commands.getMatcher(input, Commands.ENTERPROFILEMENU))) != null) {
                return "profile menu";
            }
            
            else if (((matcher = Commands.getMatcher(input, Commands.ENTERTRADEMENU))) != null) {
                return "trade menu";
            }
             else {
                System.out.println("Invalid command!");
            }
        }
    }
}

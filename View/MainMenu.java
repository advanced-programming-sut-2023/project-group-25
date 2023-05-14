package View;

import Controller.*;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    private ChangeMenuController changeMenuController;
    private RegisterLoginController registerLoginController;
    private MainController maincontroller;
    private GameController gameController;
    private Matcher matcher;
    private String input;
    public MainMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.maincontroller = changeMenuController.getMainController();
        this.registerLoginController = changeMenuController.getRegisterLoginController();
        this.gameController = changeMenuController.getgameController();
    }
    
    public String run(Scanner scanner) {
        System.out.println(registerLoginController.showCurrentMenuName("MAIN MENU"));
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input,Commands.LOGOUT)) != null) {
                FileController.addStayLoggedInForUser(RegisterLoginController.getCurrentUser().getUsername(), false);
                return "logout";
            } else if (((matcher = Commands.getMatcher(input, Commands.SHOW_MAP_BEFORE_STARTING_THE_GAME))) != null) {

                return "map menu";
            } else if (((matcher = Commands.getMatcher(input, Commands.ENTERPROFILEMENU))) != null) {
                return "profile menu";
            } else if(((matcher = Commands.getMatcher(input,Commands.NEW_GAME))) != null) {
                System.out.println("Please enter the players' usernames separated with [-]: (except yourself)");
                while (true) {
                    input = scanner.nextLine();
                    String resultMessage = gameController.newGame(input);
                    if (Pattern.compile("^New game created successfully!").matcher(resultMessage).find())
                        return "map menu";
                    else if (input.equals("cancel")) {
                        System.out.println("New game creation canceled!");
                        break;
                    } else if((matcher = Commands.getMatcher(input,Commands.LOGOUT)) != null) {
                        FileController.addStayLoggedInForUser(RegisterLoginController.getCurrentUser().getUsername(), false);
                        return "logout";
                    }
                    System.out.println(resultMessage);
                }
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}

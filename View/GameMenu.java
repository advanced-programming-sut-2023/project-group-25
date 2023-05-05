package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MainController;
import Controller.RegisterLoginController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private final ChangeMenuController changeMenuController;
    private final RegisterLoginController registerLoginController;
    private final GameController gamecontroller;
    private Matcher matcher;
    private String input;
    public GameMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.gamecontroller = changeMenuController.getGameController();
        this.registerLoginController = changeMenuController.getRegisterLoginController();
    }
    public String run(Scanner scanner) {
        System.out.println(registerLoginController.showCurrentMenuName("GAME MENU"));
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.BACK)) != null)
                return "main menu";
        }
    }

}

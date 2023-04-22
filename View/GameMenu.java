package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MainController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private final ChangeMenuController changeMenuController;
    private final GameController gamecontroller;
    private Matcher matcher;
    private String input;
    public GameMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.gamecontroller = changeMenuController.getGameController();
    }
    public String run(Scanner scanner) {
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input,Commands.BACK)).find()) {
                return "mainMenu";
            }
        }
    }

}

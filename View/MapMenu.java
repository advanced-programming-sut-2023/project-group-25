package View;

import Controller.ChangeMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private final ChangeMenuController changeMenuController;
    private Matcher matcher;
    private String input;
    
    public MapMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
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
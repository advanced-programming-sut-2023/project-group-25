package View;

import Controller.ChangeMenuController;
import Controller.MainController;
import Controller.ProfileController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private final ChangeMenuController changeMenuController;
    private final ProfileController profileController;
    private Matcher matcher;
    private String input;
    
    public ProfileMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.profileController = changeMenuController.getProfileController();
    }
    
    public String run(Scanner scanner) {
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input,Commands.BACK)).find()) {
                return "main menu";
            }
        }
    }
}

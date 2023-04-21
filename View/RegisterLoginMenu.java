package View;

import Controller.ChangeMenuController;
import Controller.RegisterLoginController;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterLoginMenu {
    private final ChangeMenuController changeMenuController;
    private final RegisterLoginController registerLoginController;
    private Matcher matcher;
    private String input;

    public RegisterLoginMenu(ChangeMenuController controller) {
        this.changeMenuController = controller;
        this.registerLoginController = controller.getRegisterLoginController();
    }

    public String run(Scanner scanner) {
        while (true) {
            input = scanner.nextLine();
            if ((Objects.requireNonNull(matcher = Commands.getMatcher(input, Commands.EXIT))).find()) {
                return "exit";
            } else if ((matcher = Commands.getMatcher(input,Commands.LOGIN)).find()) {
                
                
                //if login is successful:
                return "mainMenu";
            }
        }
    }

}

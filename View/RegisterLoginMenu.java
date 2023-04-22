package View;

import Controller.ChangeMenuController;
import Controller.RegisterLoginController;

import java.util.ArrayList;
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
            if ((matcher = Commands.getMatcher(input, Commands.EXIT)) != null) {
                return "exit";
            } else if ((matcher = Commands.getMatcher(input, Commands.CREATE_USER)) != null) {
                ArrayList<String> allOptions = new ArrayList<>() {{
                    add("u");
                    add("p");
                    add("e");
                    add("n");
                    add("s");
                }};
                String resultMessage = registerLoginController.register(matcher, allOptions,true);
                if(resultMessage.equals("success")) {
                    System.out.println("Please pick up a security question:");
                }
                else {
                    System.out.println(resultMessage);
                }
            } else if ((matcher = Commands.getMatcher(input, Commands.LOGIN)) != null) {
                //if login is successful:
                return "mainMenu";
            }
        }
    }

    public void pickSecurityRun(Scanner scanner) {
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.PICK_QUESTION)) != null) {
                break;
            } else {
                System.out.println("Invalid command format; Please pick a security question:");
            }
        }
    }
}

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
                    resultMessage = pickSecurityRun(scanner);
                    if(resultMessage.equals("exit"))
                        return "exit";
                    System.out.println("User register succeeded!");
                    System.out.println("Username: " + RegisterLoginController.getCurrentUser().getUsername() +
                            "SecurityQ: " + RegisterLoginController.getCurrentUser().getSecurityQuestion()  +
                            "Ans: " + RegisterLoginController.getCurrentUser().getSecurityAnswer() );
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

    public String pickSecurityRun(Scanner scanner) {
        ArrayList<String> questions = registerLoginController.showSecurityQuestions();
        System.out.println("Pick a security question from the following list:");
        for(String question : questions) {
            System.out.println(question);
        }
        while (true) {
            ArrayList<String> allOptions = new ArrayList<>() {{
                add("q");
                add("a");
                add("c");
            }};
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.PICK_QUESTION)) != null) {
                String resultMessage = registerLoginController.pickQuestion(matcher,allOptions);
                if(resultMessage.equals("success")) {
                    return "success";
                }
                else {
                    System.out.println(resultMessage);
                }
            } else if ((matcher = Commands.getMatcher(input, Commands.EXIT)) != null) {
                return "exit";
            }  else {
                System.out.println("Invalid command format; Please pick a security question:");
            }
        }
    }
}

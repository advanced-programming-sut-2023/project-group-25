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
        String resultMessage = "";
        Matcher tmpMatcher = null;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.EXIT)) != null) {
                return "exit";
            } else if ((matcher = Commands.getMatcher(input, Commands.CREATE_USER_WITH_SLOGAN)) != null ||
                    (tmpMatcher = Commands.getMatcher(input, Commands.CREATE_USER_WITHOUT_SLOGAN)) != null) {
                ArrayList<String> allOptions = new ArrayList<>() {{
                    add("u");
                    add("p");
                    add("e");
                    add("n");
                    add("s");
                }};
                ArrayList<String> allOptionsWithoutSlogan = new ArrayList<>() {{
                    add("u");
                    add("p");
                    add("e");
                    add("n");
                }};
                if(matcher != null)
                    resultMessage = registerLoginController.register(matcher, allOptions, true);
                else if(tmpMatcher != null)
                    resultMessage = registerLoginController.register(tmpMatcher, allOptionsWithoutSlogan, false);
                if (resultMessage.equals("success")) {
                    randomPasswordRun(scanner);
                    resultMessage = pickSecurityQuestionRun(scanner);
                    if (resultMessage.equals("exit"))
                        return "exit";
                    System.out.println("User registered successfully!");
                } else {
                    System.out.println(resultMessage);
                }
            } else if ((matcher = Commands.getMatcher(input, Commands.LOGIN)) != null) {
                //if login is successful:
                return "mainMenu";
            }
        }
    }

    public String pickSecurityQuestionRun(Scanner scanner) {
        ArrayList<String> questions = registerLoginController.showSecurityQuestions();
        System.out.println("Pick a security question from the following list:");
        for (String question : questions) {
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
                String resultMessage = registerLoginController.pickQuestion(matcher, allOptions);
                if (resultMessage.equals("success")) {
                    return "success";
                } else {
                    System.out.println(resultMessage);
                }
            } else if ((matcher = Commands.getMatcher(input, Commands.EXIT)) != null) {
                return "exit";
            } else {
                System.out.println("Invalid command format; Please pick a security question:");
            }
        }
    }

    public void randomPasswordRun(Scanner scanner) {
        if (RegisterLoginController.getCurrentUser().isSloganRandom())
            System.out.println("Your slogan is: \"" + RegisterLoginController.getCurrentUser().getSlogan() + "\"");
        if (RegisterLoginController.getCurrentUser().isPasswordRandom()) {
            System.out.println("Your random password is:" + RegisterLoginController.getCurrentUser().getPassword());
            System.out.println("Please re-enter your password here:");
            while (true) {
                input = scanner.nextLine();
                if(input.equals(RegisterLoginController.getCurrentUser().getPassword()))
                    break;
                else
                    System.out.println("Please re-enter the password correctly!");
            }
        }
    }
}

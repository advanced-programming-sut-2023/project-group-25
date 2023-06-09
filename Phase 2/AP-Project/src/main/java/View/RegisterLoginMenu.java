package View;

import Controller.ChangeMenuController;
import Controller.FileController;
import Controller.RegisterLoginController;
import Model.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
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

    public String run(Scanner scanner) throws NoSuchAlgorithmException {
        FileController.initializeUsersFile();
        User user = FileController.getFirstStayLoggedIn();
        if (user != null) {
            RegisterLoginController.setCurrentUser(user);
            return "mainMenu";
        }
        System.out.println(registerLoginController.showCurrentMenuName("REGISTER/LOGIN MENU"));
        String resultMessage = "";
        Matcher tmpMatcher = null;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.EXIT)) != null) {
                return "exit";
            } else if ((matcher = Commands.getMatcher(input, Commands.CREATE_USER_WITH_SLOGAN)) != null ||
                    (tmpMatcher = Commands.getMatcher(input, Commands.CREATE_USER_WITHOUT_SLOGAN)) != null) {
                ArrayList<String> allOptions = new ArrayList<String>() {{
                    add("u");
                    add("p");
                    add("e");
                    add("n");
                    add("s");
                }};
                ArrayList<String> allOptionsWithoutSlogan = new ArrayList<String>() {{
                    add("u");
                    add("p");
                    add("e");
                    add("n");
                }};
                if (matcher != null)
                    resultMessage = registerLoginController.register(matcher, allOptions, true);
                else if (tmpMatcher != null)
                    resultMessage = registerLoginController.register(tmpMatcher, allOptionsWithoutSlogan, false);
                if (resultMessage.equals("success")) {
                    resultMessage = randomPasswordRun(scanner);
                    if (resultMessage.equals("exit")) {
                        System.out.println("User register Canceled!");
                        return "exit";
                    }
                    resultMessage = pickSecurityQuestionRun(scanner);
                    if (resultMessage.equals("exit")) {
                        System.out.println("User register Canceled!");
                        return "exit";
                    }
                    resultMessage = captchaRun(scanner);
                    if (resultMessage.equals("exit")) {
                        System.out.println("User register Canceled!");
                        return "exit";
                    }
                    FileController.addUserToFile(RegisterLoginController.getCurrentUser());
                    System.out.println("User registered successfully!");
                } else {
                    System.out.println(resultMessage);
                }

            } else if ((matcher = Commands.getMatcher(input, Commands.LOGIN)) != null ||
                    (tmpMatcher = Commands.getMatcher(input, Commands.LOGIN_WITH_LOGGED_IN)) != null) {
                ArrayList<String> allOptions = new ArrayList<String>() {{
                    add("u");
                    add("p");
                }};
                if (matcher != null)
                    resultMessage = registerLoginController.login(matcher, allOptions, false);
                else if (tmpMatcher != null)
                    resultMessage = registerLoginController.login(tmpMatcher, allOptions, true);
                if (resultMessage.equals("success")) {
                    resultMessage = captchaRun(scanner);
                    if (resultMessage.equals("exit")) {
                        System.out.println("User register Canceled!");
                        return "exit";
                    }
                    return "mainMenu";
                } else
                    System.out.println(resultMessage);
            } else if ((matcher = Commands.getMatcher(input, Commands.FORGOT_PASSWORD)) != null) {
                resultMessage = registerLoginController.forgotPasswordShowQuestion(matcher);
                if (resultMessage.equals("fail")) {
                    System.out.println("This username doesn't exist!");
                } else {
                    System.out.println("Answer the following question to change password:");
                    System.out.println(resultMessage);
                    while (true) {
                        input = scanner.nextLine();
                        if (!FileController.isAnswerCorrect(matcher.group("username"), input)) {
                            System.out.println("Incorrect Answer; Please try again!");
                        } else {
                            break;
                        }
                    }
                    System.out.print("Enter your new password: ");
                    while (true) {
                        input = scanner.nextLine();
                        if (!registerLoginController.isPasswordWeak(input).equals("success")) {
                            System.out.println("This password is not valid; " +
                                    registerLoginController.isPasswordWeak(input));
                            System.out.print("Enter your new password: ");
                        } else
                            break;
                    }

                    System.out.print("Please re-enter the new password: ");
                    while (true) {
                        String confirm = scanner.nextLine();
                        if (!confirm.equals(input)) {
                            System.out.print("Please re-enter the new password correctly: ");
                        } else {
                            break;
                        }
                    }
                    FileController.changePassword(matcher.group("username"), input);
                    System.out.println("Password changed successfully!");
                }
            } else
                System.out.println("Invalid command!");
        }
    }

    public String pickSecurityQuestionRun(Scanner scanner) {
        ArrayList<String> questions = registerLoginController.showSecurityQuestions();
        System.out.println("Pick a security question from the following list:");
        for (String question : questions) {
            System.out.println(question);
        }
        while (true) {
            ArrayList<String> allOptions = new ArrayList<String>() {{
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

    public String randomPasswordRun(Scanner scanner) {
        if (RegisterLoginController.getCurrentUser().isSloganRandom())
            System.out.println("Your slogan is: \"" + RegisterLoginController.getCurrentUser().getSlogan() + "\"");
        if (RegisterLoginController.getCurrentUser().isPasswordRandom()) {
            System.out.println("Your random password is: " + RegisterLoginController.getCurrentUser().getPassword());
            System.out.print("Please re-enter your password here: ");
            while (true) {
                input = scanner.nextLine();
                if (input.equals(RegisterLoginController.getCurrentUser().getPassword()))
                    return "success";
                else if (input.equals("exit"))
                    return "exit";
                else
                    System.out.print("Please re-enter the password correctly: ");
            }
        }
        return "";
    }

    public String captchaRun(Scanner scanner) {
        String captcha = registerLoginController.generateCaptchaString();
        registerLoginController.asciiArt(captcha);
        System.out.print("To confirm the register,login, Enter the CAPTCHA: ");
        while (true) {
            input = scanner.nextLine();
            if (input.equals(captcha)) {
                return "success";
            } else if (input.equals("exit"))
                return "exit";
            else
                System.out.print("Invalid CAPTCHA; please re-enter the CAPTCHA correctly: ");
        }

    }
}

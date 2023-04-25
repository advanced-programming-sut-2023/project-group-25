package View;

import Controller.ChangeMenuController;
import Controller.MainController;
import Controller.ProfileController;
import Controller.RegisterLoginController;
import Model.User;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private final ChangeMenuController changeMenuController;
    private final ProfileController profileController;
    private RegisterLoginController registerLoginController;
    private Matcher matcher;
    private String input;

    public ProfileMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.profileController = changeMenuController.getProfileController();
    }

    public String run(Scanner scanner) throws NoSuchAlgorithmException {
        User currentUser=registerLoginController.getCurrentUser();
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.BACK))!=null)
                return "main menu";
            else if ((matcher = Commands.getMatcher(input, Commands.CHANGE_INFO))!=null)
                System.out.println(profileController.changeInfo(currentUser,matcher.group("option"), matcher.group("input")));
            else if ((matcher = Commands.getMatcher(input, Commands.CHANGE_PASSWORD))!=null) {
                String result = profileController.changePassword(currentUser,matcher.group("input1"), matcher.group("input2"));
                while (result.equals("enter new password")) {
                    System.out.println("Please enter a new password");
                    input = scanner.nextLine();
                    result = profileController.changePassword(currentUser,matcher.group("input1"), input);
                }
                if (result.equals("success")) {
                    String captcha = captchaRun(scanner);
                    while (!captcha.equals("success"))
                        captcha = captchaRun(scanner);
                    System.out.println("Please enter your new password again");
                    input=scanner.nextLine();
                    if(input.equals(matcher.group("input2"))){
                        System.out.println("Password changed successfully");
                        profileController.setFinalPassword(currentUser,matcher.group("input2"));
                    }
                }
                System.out.println(result);
            } else if ((matcher = Commands.getMatcher(input, Commands.REMOVE_SLOGAN))!=null)
                System.out.println(profileController.removeSlogan(currentUser));
            else if ((matcher = Commands.getMatcher(input, Commands.DISPLAY_INFO))!=null)
                System.out.println(profileController.displayProfile(currentUser,matcher));
        }
    }


    public String captchaRun(Scanner scanner) {
        String captcha = registerLoginController.generateCaptchaString();
        registerLoginController.asciiArt(captcha);
        System.out.print("To confirm the password, Enter the CAPTCHA: ");
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

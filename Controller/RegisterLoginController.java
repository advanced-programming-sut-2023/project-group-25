package Controller;

import Model.User;

import java.util.Random;

public class RegisterLoginController {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isUsernameValid(String username) {
        if (username.matches("^[a-zA-Z0-9_]+$"))
            return true;
        else
            return false;
    }

    //return Sentence is the reason for being weak!
    public String isPasswordWeak(String password) {
        if (password.length() < 6)
            return "The password is too short!";
        else if (!password.matches("(.*[a-z].*)"))
            return "Please enter at least one lowercase letter!";
        else if (!password.matches("(.*[A-Z].*)"))
            return "Please enter at least one uppercase letter!";
        else if (!password.matches("(.*[0-9].*)"))
            return "Please enter at least one digit!";
        else if (!password.matches("(.*[^a-zA-Z\\d\\s:].*)"))
            return "Please enter at least one alphanumeric character!";
        else
            return "success";
    }

    public boolean isEmailValid(String email) {
        if (email.matches("^(?<firstGroup>\\S+)@(?<secondGroup>\\S+)\\.(?<thirdGroup>\\S+)$"))
            return true;
        else
            return false;
    }

    public String generateRandomPassword() {
        char[] specialCharacters = {'~', '`', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-',
                '_', '=', '+', '[', '{', ']', '}', '\\', '|', ';', ':', '\'', '"', ',', '<', '.', '>', '/', '?'};
        String password = "";
        Random random = new Random();
        for (int i = 0; i<random.nextInt(3) + 2; i++)
            password += (char) ('a' + random.nextInt(26));
        for (int i = 0; i<random.nextInt(3) + 2; i++)
            password += random.nextInt(10);
        for (int i = 0; i<random.nextInt(3) + 2; i++)
            password += (char) ('A' + random.nextInt(26));
        for (int i = 0; i<random.nextInt(3) + 2; i++)
            password += specialCharacters[random.nextInt(32)];
        return password;
    }
}

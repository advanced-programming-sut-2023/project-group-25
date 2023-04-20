package Controller;

import Model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileController {
    Scanner scanner;
    User currentUser = RegisterLoginController.getCurrentUser();

    public ProfileController(Scanner scanner) {
        this.scanner = scanner;
    }

    public String changeInfo(String field, String content) {
        if (content.length() == 0)
            return "This field is empty!";
        if (field.equals("username")) {
            if (!RegisterLoginController.isUsernameValid(content))
                return "Invalid username format!";
            else if (!currentUser.isUserNameUnique(content))
                return "This username is already used!";
            currentUser.setUsername(content);
            return "Username changed successfully.";
        } else if (field.equals("nickname")) {
            currentUser.setNickname(content);
            return "Nickname changed successfully.";
        } else if (field.equals("email")){
            if(!currentUser.isEmailUnique(content))
                return "This email is already used!";
            else if(!RegisterLoginController.isEmailValid(content))
                return "Invalid email format!";
            currentUser.setEmail(content);
            return "Email changed successfully.";
        }
        else if (field.equals("slogan")){
            currentUser.setSlogan(content);
            return "Slogan changed successfully.";
        }
    }

    public String changePassword(String oldPassword, String newPassword) {
        if (currentUser.getPassword().equals(oldPassword)) {
            if (oldPassword.equals(newPassword))
                return "Please enter a new password";
            else if (!RegisterLoginController.isPasswordWeak(newPassword))
                return "New password is weak!";
            currentUser.setPassword(newPassword);
            System.out.println("Password changed successfully.");
            return "Please enter your new password again";
        }
        return "Current password is incorrect!";
    }

    public void removeSlogan() {
        currentUser.setSlogan("");
        System.out.println("Slogan field is now empty.");
    }

    public String displayProfile(Matcher matcher) {
        String field = matcher.group("field");
        StringBuilder result = new StringBuilder();
        if (field.equals("highscore"))
            result.append("Highscore: ").append(currentUser.getHighScore());
        else if (field.equals("rank"))
            result.append("Rank: ").append(getRank());
        else if (field.equals("slogan")) {
            if (currentUser.getSlogan().equals(""))
                result.append("Slogan is empty!");
            else result.append("Slogan: ").append(currentUser.getSlogan());
        } else {
            result.append("Highscore: ").append(currentUser.getHighScore()).append("\nRank: ").append(getRank());
            if (!currentUser.getSlogan().equals(""))
                result.append("\nSlogan: ").append(currentUser.getSlogan()).append('\n');
        }
        return result.toString();
    }

    public int getRank() {
        ArrayList<User> sortedUsers = new ArrayList<User>(User.getUsers());
        Comparator<User> highScoreComparator = Comparator
                .comparing(User::getHighScore);
        Comparator<User> fieldComparator = highScoreComparator;
        sortedUsers.sort(fieldComparator);

        int rank = 1;
        for (User user : sortedUsers) {
            if (user.equals(currentUser))
                return rank;
            rank++;
        }
        return -1;
    }
}

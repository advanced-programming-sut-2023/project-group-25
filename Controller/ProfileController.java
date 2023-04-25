package Controller;

import Model.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;

public class ProfileController {
    
    private RegisterLoginController registerLoginController = new RegisterLoginController();
    
    public String changeInfo(User currentUser, String field, String content) {
        if (content.length() == 0)
            return "This field is empty!";
        if (field.equals("username")) {
            if (!registerLoginController.isUsernameValid(content))
                return "Invalid username format!";
            else if (!registerLoginController.isUserNameUnique(content))
                return "This username is already used!";
            currentUser.setUsername(content);
            return "Username changed successfully.";
        } else if (field.equals("nickname")) {
            currentUser.setNickname(content);
            return "Nickname changed successfully.";
        } else if (field.equals("email")) {
            if (!registerLoginController.isEmailUnique(content))
                return "This email is already used!";
            else if (!registerLoginController.isEmailValid(content))
                return "Invalid email format!";
            currentUser.setEmail(content);
            return "Email changed successfully.";
        } else if (field.equals("slogan")) {
            currentUser.setSlogan(content);
            return "Slogan changed successfully.";
        }
        return null;
    }
    
    public String changePassword(User currentUser, String oldPassword, String newPassword) {
        if (currentUser.getPassword().equals(oldPassword)) {
            if (oldPassword.equals(newPassword))
                return "enter new password";
            else if (!registerLoginController.isPasswordWeak(newPassword).equals("success"))
                return ("This password is not valid; " + registerLoginController.isPasswordWeak(currentUser.getPassword()));

            return "success";
        }
        return "Current password is incorrect!";
    }

    public void setFinalPassword(User currentUser, String newPassword) throws NoSuchAlgorithmException {
        registerLoginController.changePassword(currentUser.getUsername(),newPassword);
    }
    
    public String removeSlogan(User currentUser) {
       currentUser.setSlogan("");
        return "Slogan field is now empty.";
    }
    
    public String displayProfile(User currentUser, Matcher matcher) {
        String field = matcher.group("field");
        StringBuilder result = new StringBuilder();
        if(field==null){
            result.append("Highscore: ").append(currentUser.getHighScore()).append("\nRank: ").append(getRank(currentUser));
            if (!currentUser.getSlogan().equals(""))
                result.append("\nSlogan: ").append(currentUser.getSlogan()).append('\n');
        }
        else if (field.equals("highscore"))
            result.append("Highscore: ").append(currentUser.getHighScore());
        else if (field.equals("rank"))
            result.append("Rank: ").append(getRank(currentUser));
        else if (field.equals("slogan")) {
            if (currentUser.getSlogan().equals(""))
                result.append("Slogan is empty!");
            else result.append("Slogan: ").append(currentUser.getSlogan());
        }
        return result.toString();
    }
    
    public int getRank(User currentUser) {
        ArrayList<User> sortedUsers = new ArrayList<User>(registerLoginController.getAllUsers("Users.txt"));
        Comparator<User> highScoreComparator = Comparator
                .comparing(User::getHighScore);
        Comparator<User> fieldComparator = highScoreComparator;
        sortedUsers.sort(fieldComparator);
        
        int rank = 1;
        for (User user : sortedUsers) {
            if (user.getUsername().equals(currentUser.getUsername()))
                return rank;
            rank++;
        }
        return -1;
    }
    
}

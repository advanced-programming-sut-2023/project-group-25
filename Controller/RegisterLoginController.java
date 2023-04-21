package Controller;

import Model.User;

import java.util.*;
import java.util.regex.Matcher;

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
        for (int i = 0; i < random.nextInt(3) + 2; i++)
            password += (char) ('a' + random.nextInt(26));
        for (int i = 0; i < random.nextInt(3) + 2; i++)
            password += random.nextInt(10);
        for (int i = 0; i < random.nextInt(3) + 2; i++)
            password += (char) ('A' + random.nextInt(26));
        for (int i = 0; i < random.nextInt(3) + 2; i++)
            password += specialCharacters[random.nextInt(32)];
        return password;
    }
    
    public String generateRandomSlogan() {
        String[] preparedSlogans = {"Through adversity comes strength!", "Don’t be afraid to fail!", "Compete with yourself!",
                "Play for fun, not stakes beyond your control!", "Keep calm and check mate the king!", "It’s your world!",
                "There’s never been a better time to game!", "Race in the ultimate automotive playground!", "Build and Explore!",
                "Are you ready for a new level of excitement?", "Nothing is impossible, it just a matter of perspective!", "A level up!",
                "Hone your skills!", "Play with anyone, anywhere, on any device!", "A game is never over until it’s over!", "Get invited to play!"};
        Random random = new Random();
        return preparedSlogans[random.nextInt(16)];
    }
    
    public String getOptionsFromMatcher(Matcher matcher, String option, int numberOfOptions) {
        for (int i = 0; i < numberOfOptions; i++) {
            if (matcher.group(("option" + (i + 1))).equals(option))
                return matcher.group(("input" + (i + 1)));
        }
        return null;
    }
    

    /*public boolean checkAllOptionsExist(Matcher matcher, ArrayList<String> allOptions) {
        ArrayList<String> matcherExistingOptions = new ArrayList<>();
        for(int i = 0; i<allOptions.size();i++) {
            matcherExistingOptions.add(matcher.group(("option" + (i+1))));
        }
        Collections.sort(matcherExistingOptions);
        Collections.sort(allOptions);
        if(matcherExistingOptions.equals(allOptions))
            return true;
        else
            return false;
    }*/
}

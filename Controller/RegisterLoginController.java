package Controller;

import Model.User;

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

    //return word is the reason for being weak!
    public String isPasswordWeak(String password) {
        if(password.length() < 6)
            return "The password is too short!";
        else if(!password.matches("(.*[a-z].*)"))
            return "Please enter at least one lowercase letter!";
        else if(!password.matches("(.*[A-Z].*)"))
            return "Please enter at least one uppercase letter!";
        else if(!password.matches("(.*[0-9].*)"))
            return "Please enter at least one digit!";
        else if(!password.matches("(.*[^a-zA-Z\\d\\s:].*)"))
            return "Please enter at least one alphanumeric character!";
        else
            return "success";
    }
}

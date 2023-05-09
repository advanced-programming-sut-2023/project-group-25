package Model;

import java.util.ArrayList;

public class User {
    private static ArrayList<User> users = new ArrayList<>();
    private String username;
    private String password;
    private String passwordConfirmation;
    private String nickname;
    private String email;
    private String slogan;
    private int highScore;
    private String securityQuestion;
    private String securityAnswer;
    private boolean isPasswordRandom;
    private boolean isSloganRandom;
    
    public User(String username, String password, String passwordConfirmation, String nickname, String email, String slogan) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.highScore = 0;
        isPasswordRandom = false;
        isSloganRandom = false;
    }

    public void setPasswordRandom(boolean passwordRandom) {
        isPasswordRandom = passwordRandom;
    }

    public void setSloganRandom(boolean sloganRandom) {
        isSloganRandom = sloganRandom;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public boolean isPasswordRandom() {
        return isPasswordRandom;
    }

    public boolean isSloganRandom() {
        return isSloganRandom;
    }

    /*public static boolean isUserNameUnique(String username) {
        for (User user : users) {
            if (user.username.equals(username)) return false;
        }
        return true;
    }*/
    
    /*public static boolean isEmailUnique(String email) {
        for (User user : users) {
            if (user.email.equals(email)) return false;
        }
        return true;
    }*/

    /*public void removeUser() {
        users.remove(this);
    }*/
    
    /*public static void addUser(User user) {
        users.add(user);
    }*/
    
    /*public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.username.equals(username)) return user;
        }
        return null;
    }*/
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSlogan() {
        return slogan;
    }
    
    public static ArrayList<User> getUsers() {
        return users;
    }
    
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
    
    public int getHighScore() {
        return highScore;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }
    
    public String getSecurityAnswer() {
        return securityAnswer;
    }
    
    public boolean isPasswordCorrect(String password) {
        return password.equals(this.password);
    }
    
}

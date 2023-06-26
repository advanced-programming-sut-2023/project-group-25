package Controller;

import Model.User;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;

public class RegisterLoginController {
    private static User currentUser;
    private static User registeringUser;

    private String tryingToLoginUsername = "";
    private int numberOfTries = 0;

    long timeOrigin = 1000;
    int timeToWait = 0;



    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static void setCurrentUser(User currentUser) {
        RegisterLoginController.currentUser = currentUser;
    }

    public static String passwordToSHA(String password) throws NoSuchAlgorithmException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-256");
        crypt.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = crypt.digest();
        BigInteger bi = new BigInteger(1, bytes);
        String encryptedPassword = String.format("%0" + (bytes.length << 1) + "x", bi);
        return encryptedPassword;
    }

    private static void assignCaptchaDigits(int captchaLength, int captchaNumber, int[] captchaDigits) {
        for (int i = captchaLength - 1; i >= 0; i--) {
            captchaDigits[i] = captchaNumber % 10;
            captchaNumber /= 10;
        }
    }
    
    public boolean isUsernameValid(String username) {
        if(username.length() < 5)
            return false;
        if (username.matches("^[a-zA-Z0-9_]+$"))
            return true;
        else
            return false;
    }


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

    public String generateCaptchaString() {
        Random random = new Random();
        int captchaLength = 4 + random.nextInt(5);
        StringBuilder captchaBuilder = new StringBuilder();
        for (int i = 0; i < captchaLength; i++) {
            String CHARACTERS = "0123456789";
            captchaBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return captchaBuilder.toString();
    }

    public void asciiArt(String captcha) {
        String[] line = {"", "", "", "", "", "", "", ""};
        int captchaLength = captcha.length(), captchaNumber = Integer.parseInt(captcha);
        int[] captchaDigits = new int[captchaLength];
        assignCaptchaDigits(captchaLength, captchaNumber, captchaDigits);
        //noise
        int toBeNoisedLine1 = (4 + (int) (Math.random() * 5)) % 7 + 1;
        int toBeNoisedLine2 = (4 + (int) (Math.random() * 5)) % 7 + 1;
        while (toBeNoisedLine2 == toBeNoisedLine1) {
            toBeNoisedLine2 = (4 + (int) (Math.random() * 5)) % 7 + 1;
        }
        line[toBeNoisedLine1] += " ";
        line[toBeNoisedLine2] += " ";
        for (int i = 0; i < captchaLength; i++) {
            for (int j = 1; j <= 7; j++) {
                callFillingLineMethods(captchaDigits[i], j, line);
            }
        }
        for (int i = 1; i < 8; i++) {
            System.out.println(line[i]);
        }
    }
    
    private void callFillingLineMethods(int digit, int lineNumber, String[] lines) {
        if (lineNumber == 1) fillCaptchaLine1(digit, lines);
        else if (lineNumber == 2) fillCaptchaLine2(digit, lines);
        else if (lineNumber == 3) fillCaptchaLine3(digit, lines);
        else if (lineNumber == 4) fillCaptchaLine4(digit, lines);
        else if (lineNumber == 5) fillCaptchaLine5(digit, lines);
        else if (lineNumber == 6) fillCaptchaLine6(digit, lines);
        else if (lineNumber == 7) fillCaptchaLine7(digit, lines);
    }
    
    private void fillCaptchaLine1(int number, String[] lines) {
        if (number == 0) lines[1] += " *****      ";
        else if (number == 1) lines[1] += "*     ";
        else if (number == 4) lines[1] += "*     *     ";
        else lines[1] += "*******     ";
    }
    
    private void fillCaptchaLine2(int number, String[] lines) {
        if (number == 1) lines[2] += "*     ";
        else if (number == 2 || number == 3) lines[2] += "      *     ";
        else if (number == 5 || number == 6) lines[2] += "*           ";
        else lines[2] += "*     *     ";
    }
    
    private void fillCaptchaLine3(int number, String[] lines) {
        if (number == 0) lines[3] += "*     *     ";
        else if (number == 1) lines[3] += "*     ";
        else if (number == 2 || number == 3) lines[3] += "      *     ";
        else if (number == 4) lines[3] += "*******     ";
        else if (number == 5 || number == 6) lines[3] += "*           ";
        else lines[3] += "*     *     ";
    }
    
    private void fillCaptchaLine4(int number, String[] lines) {
        if (number == 0) lines[4] += "*     *     ";
        else if (number == 1) lines[4] += "*     ";
        else if (number == 3) lines[4] += " ******     ";
        else if (number == 4 || number == 7) lines[4] += "      *     ";
        else lines[4] += "*******     ";
    }
    
    private void fillCaptchaLine5(int number, String[] lines) {
        if (number == 0 || number == 6 || number == 8) lines[5] += "*     *     ";
        else if (number == 1) lines[5] += "*     ";
        else if (number == 2) lines[5] += "*           ";
        else lines[5] += "      *     ";
    }
    
    private void fillCaptchaLine6(int number, String[] lines) {
        if (number == 0 || number == 6 || number == 8) lines[6] += "*     *     ";
        else if (number == 1) lines[6] += "*     ";
        else if (number == 2) lines[6] += "*           ";
        else lines[6] += "      *     ";
    }
    
    private void fillCaptchaLine7(int number, String[] lines) {
        if (number == 0) lines[7] += " *****      ";
        else if (number == 1) lines[7] += "*     ";
        else if (number == 4 || number == 7) lines[7] += "      *     ";
        else lines[7] += "*******     ";
    }

    public void getRegisterOptions(Matcher matcher, boolean hasSlogan) {
        String password, passwordConfirm, slogan;
        boolean isPasswordRandom = false, isSloganRandom = false;
        int numberOfOptions = 5;
        if (!hasSlogan)
            numberOfOptions--;
        String username = MainController.getOptionsFromMatcher(matcher, "u", numberOfOptions);
        String nickname = MainController.getOptionsFromMatcher(matcher, "n", numberOfOptions);
        String email = MainController.getOptionsFromMatcher(matcher, "e", numberOfOptions);
        String passwordGroup = MainController.getOptionsFromMatcher(matcher, "p", numberOfOptions);
        if (hasSlogan)
            slogan = MainController.getOptionsFromMatcher(matcher, "s", numberOfOptions);
        else
            slogan = "";
        if (passwordGroup.equals("random")) {
            password = generateRandomPassword();
            passwordConfirm = password;
            isPasswordRandom = true;
        } else {
            password = passwordGroup.split("\\s")[0];
            if (passwordGroup.split("\\s").length > 1)
                passwordConfirm = passwordGroup.split("\\s")[1];
            else
                passwordConfirm = "";
        }
        if (slogan.equals("random")) {
            slogan = generateRandomSlogan();
            isSloganRandom = true;
        }
        registeringUser = new User(username, password, passwordConfirm, nickname, email, slogan);
        registeringUser.setPasswordRandom(isPasswordRandom);
        registeringUser.setSloganRandom(isSloganRandom);
    }

    //FirstPage Functions:
    public String register(Matcher matcher, ArrayList<String> allOptions, boolean hasSlogan) {
        String resultMessage;
        if (!MainController.checkAllOptionsExist(matcher, allOptions))
            resultMessage = "Please enter valid options!";
        else {
            getRegisterOptions(matcher, hasSlogan);
            if (registeringUser.getUsername().matches("\\s+") || registeringUser.getNickname().matches("\\s+") ||
                    registeringUser.getEmail().matches("\\s+") || registeringUser.getPassword().matches("\\s+") ||
                    registeringUser.getPasswordConfirmation().matches("\\s+") || (hasSlogan && registeringUser.getSlogan().matches("\\s+")))
                resultMessage = "Empty Field Exists; Please enter all options completely!";
            else if (!isUsernameValid(registeringUser.getUsername()))
                resultMessage = "This username is not valid!";
            else if (!FileController.isUserNameUnique(registeringUser.getUsername()))
                resultMessage = "This username already exists!";
            else if (!isPasswordWeak(registeringUser.getPassword()).equals("success"))
                resultMessage = ("This password is not valid; " + isPasswordWeak(registeringUser.getPassword()));
            else if (!registeringUser.getPassword().equals(registeringUser.getPasswordConfirmation()))
                resultMessage = "The password confirmation doesn't match the original one!";
            else if (!FileController.isEmailUnique(registeringUser.getEmail()))
                resultMessage = "This email already exists!";
            else if (!isEmailValid(registeringUser.getEmail()))
                resultMessage = "This email is not valid!";
            else {
                currentUser = registeringUser;
                resultMessage = "success";
            }
        }
        return resultMessage;
    }

    public String login(Matcher matcher, ArrayList<String> allOptions, boolean hasLoggedIn) throws NoSuchAlgorithmException {
        String resultMessage;
        if (!MainController.checkAllOptionsExist(matcher, allOptions))
            resultMessage = "Please enter valid options!";
        else {
            String username = MainController.getOptionsFromMatcher(matcher, "u", 2);
            String password = MainController.getOptionsFromMatcher(matcher, "p", 2);
            if (username.matches("\\s+") || password.matches("\\s+"))
                resultMessage = "Empty Field Exists; Please enter all options completely!";
            else if (FileController.isUserNameUnique(username))
                resultMessage = "This username doesn't exist!";
            else if (tryingToLoginUsername.equals(username) && System.currentTimeMillis() < (timeOrigin + timeToWait * 1000))
                resultMessage = ("You have to wait [" + timeToWait + "] seconds to login with this username again!");
            else if (!FileController.isPasswordCorrect(username, password)) {
                resultMessage = ("Username and password didn't match!");
                stopWrongPassword(username);
                resultMessage += ("\nYou have entered your password [" + numberOfTries + "] times wrong!");
            } else {
                if (hasLoggedIn)
                    FileController.addStayLoggedInForUser(username, true);
                currentUser = FileController.getUserByUsername(username);
                resultMessage = "success";
                timeOrigin = 1000;
                tryingToLoginUsername = "";
            }
        }
        return resultMessage;
    }

    public void stopWrongPassword(String username) {
        String resultMessage;
        if (tryingToLoginUsername.equals(username)) {
            numberOfTries++;
        } else {
            tryingToLoginUsername = username;
            numberOfTries = 1;
        }
        if (numberOfTries >= 5) {
            timeToWait = (numberOfTries - 4) * 5;
        }
        timeOrigin = System.currentTimeMillis();
    }


    public String pickQuestion(Matcher matcher, ArrayList<String> allOptions) {
        String resultMessage;
        ArrayList<String> questions = showSecurityQuestions();
        String questionNumber = MainController.getOptionsFromMatcher(matcher, "q", 3);
        String answer = MainController.getOptionsFromMatcher(matcher, "a", 3);
        String answerConfirm = MainController.getOptionsFromMatcher(matcher, "c", 3);
        if (!MainController.checkAllOptionsExist(matcher, allOptions))
            resultMessage = "Please enter valid options!";
        else {
            if (questionNumber.matches("\\s*") || answer.matches("\\s*") || answerConfirm.matches("\\s*"))
                resultMessage = "Empty Field Exists; Please enter all options completely!";
            else if (!isNumber(questionNumber))
                resultMessage = "Invalid question number; Please enter a number!";
            else if (!answer.equals(answerConfirm))
                resultMessage = "The answer confirmation doesn't match the original one!";
            else {
                int number = Integer.parseInt(questionNumber);
                if (number < 1 || number > 5)
                    resultMessage = "Invalid question number; There is no question with this number!";
                else {
                    currentUser.setSecurityQuestion(questions.get(number - 1));
                    currentUser.setSecurityAnswer(answer);
                    resultMessage = "success";
                }
            }
        }
        return resultMessage;
    }
    
    public String forgotPasswordShowQuestion(Matcher matcher) {
        User user = FileController.getUserByUsername(matcher.group("username"));
        if (user == null)
            return "fail";
        else {
            return user.getSecurityQuestion();
        }
    }

    public boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public ArrayList<String> showSecurityQuestions() {
        ArrayList<String> questions = new ArrayList<>();
        questions.add("1- What city were you born in?");
        questions.add("2- What is your oldest sibling’s middle name?");
        questions.add("3- In what city or town did your parents meet?");
        questions.add("4- What is your mother’s last name?");
        questions.add("5- What was your first pet’s name?");
        return questions;
    }

    public String showCurrentMenuName(String menuName) {
        int nameLength = menuName.toCharArray().length;
        String result = "";
        for (int i = 0; i < nameLength + 6; i++)
            result += "-";
        result += ("\n|  " + menuName + "  |\n");
        for (int i = 0; i < nameLength + 6; i++)
            result += "-";
        return result;
    }

    public String getRandomCaptcha() {
        int[] captchaValue = {1181,1381,1491,1722,1959,2163,2177,3541,4185,5463};
        Random random = new Random();
        int number = random.nextInt(10) + 1;
        String path = "/images/captcha/" + number +".png";
        path += ("-" + captchaValue[number -1]);
        return path;
    }
}

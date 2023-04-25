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

    public static User getCurrentUser() {
        return currentUser;
    }

    //File Functions:

    public ArrayList<String> readFileContent(String path) {
        ArrayList<String> content = new ArrayList<>();
        File Users = new File(path);
        try {
            FileReader fileReader = new FileReader(Users);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                content.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return content;
    }

    public void writeToFileContent(String path, ArrayList<String> content, boolean isAppend) {
        File Users = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(Users, isAppend);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter writer = new PrintWriter(bufferedWriter);
            for (int i = 0; i < content.size(); i++) {
                writer.println(content.get(i));
            }
            writer.close();
        } catch (Exception e) {
        }
    }

    //User Functions:
    public User getUserByUsername(String username) {
        User wantedUser;
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 1; i < (content.size() / 10); i++) {
            if (content.get((10 * i) + 1).equals(username)) {
                wantedUser = new User(content.get(10 * i), content.get((10 * i) + 1), content.get((10 * i) + 1), content.get((10 * i) + 2)
                        , content.get((10 * i) + 3), content.get((10 * i) + 4));
                wantedUser.setSecurityQuestion(content.get((10 * i) + 6));
                wantedUser.setSecurityQuestion(content.get((10 * i) + 7));
                return wantedUser;
            }
        }
        return null;
    }

    public boolean isUserNameUnique(String username) {
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 1; i < (content.size() / 10); i++) {
            if (content.get(10 * i).equals(username)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmailUnique(String email) {
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 1; i < (content.size() / 10); i++) {
            if (content.get((10 * i) + 3).equals(email)) {
                return false;
            }
        }
        return true;
    }

    public void addUserToFile(User user) throws NoSuchAlgorithmException {
        ArrayList<String> content = new ArrayList<>();
        content.add(user.getUsername());
        content.add(passwordToSHA(user.getPassword()));
        content.add(user.getNickname());
        content.add(user.getEmail());
        content.add(user.getSlogan());
        content.add(String.valueOf(user.getHighScore()));
        content.add(user.getSecurityQuestion());
        content.add(user.getSecurityAnswer());
        content.add("false");
        content.add("_____________________________________________________");
        writeToFileContent("Users.txt",content,true);
    }

    //Check Validation Functions:
    public boolean isUsernameValid(String username) {
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

    //Generate randoms(Password, Slogan, CAPTCHA):

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
        String[] line = new String[8];
        for (int i = 1; i < 8; i++) {
            line[i] = "";
        }
        int captchaLength = captcha.length();
        int captchaNumber = Integer.parseInt(captcha);
        int[] captchaDigits = new int[captchaLength];
        for (int i = captchaLength - 1; i >= 0; i--) {
            captchaDigits[i] = captchaNumber % 10;
            captchaNumber /= 10;
        }
        //noise
        int toBeNoisedLine1 = (4 + (int) (Math.random() * 5)) % 7 + 1;
        int toBeNoisedLine2 = (4 + (int) (Math.random() * 5)) % 7 + 1;
        while (toBeNoisedLine2 == toBeNoisedLine1) {
            toBeNoisedLine2 = (4 + (int) (Math.random() * 5)) % 7 + 1;
        }
        line[toBeNoisedLine1] += " ";
        line[toBeNoisedLine2] += " ";
        for (int i = 0; i < captchaLength; i++) {
            switch (captchaDigits[i]) {
                case 0: {
                    line[1] += " *****      ";
                    line[2] += "*     *     ";
                    line[3] += "*     *     ";
                    line[4] += "*     *     ";
                    line[5] += "*     *     ";
                    line[6] += "*     *     ";
                    line[7] += " *****      ";
                    break;
                }
                case 1: {
                    line[1] += "*    ";
                    line[2] += "*    ";
                    line[3] += "*    ";
                    line[4] += "*    ";
                    line[5] += "*    ";
                    line[6] += "*    ";
                    line[7] += "*    ";
                    break;
                }
                case 2: {
                    line[1] += "*******     ";
                    line[2] += "      *     ";
                    line[3] += "      *     ";
                    line[4] += "*******     ";
                    line[5] += "*           ";
                    line[6] += "*           ";
                    line[7] += "*******     ";
                    break;
                }
                case 3: {
                    line[1] += "*******     ";
                    line[2] += "      *     ";
                    line[3] += "      *     ";
                    line[4] += " ******     ";
                    line[5] += "      *     ";
                    line[6] += "      *     ";
                    line[7] += "*******     ";
                    break;
                }
                case 4: {
                    line[1] += "*     *     ";
                    line[2] += "*     *     ";
                    line[3] += "*******     ";
                    line[4] += "      *     ";
                    line[5] += "      *     ";
                    line[6] += "      *     ";
                    line[7] += "      *     ";
                    break;
                }
                case 5: {
                    line[1] += "*******     ";
                    line[2] += "*           ";
                    line[3] += "*           ";
                    line[4] += "*******     ";
                    line[5] += "      *     ";
                    line[6] += "      *     ";
                    line[7] += "*******     ";
                    break;
                }
                case 6: {
                    line[1] += "*******     ";
                    line[2] += "*           ";
                    line[3] += "*           ";
                    line[4] += "*******     ";
                    line[5] += "*     *     ";
                    line[6] += "*     *     ";
                    line[7] += "*******     ";
                    break;
                }
                case 7: {
                    line[1] += "*******     ";
                    line[2] += "*     *     ";
                    line[3] += "*     *     ";
                    line[4] += "      *     ";
                    line[5] += "      *     ";
                    line[6] += "      *     ";
                    line[7] += "      *     ";
                    break;
                }
                case 8: {
                    line[1] += "*******     ";
                    line[2] += "*     *     ";
                    line[3] += "*     *     ";
                    line[4] += "*******     ";
                    line[5] += "*     *     ";
                    line[6] += "*     *     ";
                    line[7] += "*******     ";
                    break;
                }
                case 9: {
                    line[1] += "*******     ";
                    line[2] += "*     *     ";
                    line[3] += "*     *     ";
                    line[4] += "*******     ";
                    line[5] += "      *     ";
                    line[6] += "      *     ";
                    line[7] += "*******     ";
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            System.out.println(line[i]);
        }
    }

    //Option Functions:

    public String getOptionsFromMatcher(Matcher matcher, String option, int numberOfOptions) {
        for (int i = 0; i < numberOfOptions; i++) {
            if (matcher.group(("option" + (i + 1))).equals(option))
                return matcher.group(("input" + (i + 1)));
        }
        return null;
    }

    public boolean checkAllOptionsExist(Matcher matcher, ArrayList<String> allOptions) {
        ArrayList<String> matcherExistingOptions = new ArrayList<>();
        for (int i = 0; i < allOptions.size(); i++) {
            matcherExistingOptions.add(matcher.group(("option" + (i + 1))));
        }
        Collections.sort(matcherExistingOptions);
        Collections.sort(allOptions);
        if (matcherExistingOptions.equals(allOptions))
            return true;
        else
            return false;
    }

    public void getRegisterOptions(Matcher matcher, boolean hasSlogan) {
        String password, passwordConfirm, slogan;
        boolean isPasswordRandom = false, isSloganRandom = false;
        int numberOfOptions = 5;
        if (!hasSlogan)
            numberOfOptions--;
        String username = getOptionsFromMatcher(matcher, "u", numberOfOptions);
        String nickname = getOptionsFromMatcher(matcher, "n", numberOfOptions);
        String email = getOptionsFromMatcher(matcher, "e", numberOfOptions);
        String passwordGroup = getOptionsFromMatcher(matcher, "p", numberOfOptions);
        if (hasSlogan)
            slogan = getOptionsFromMatcher(matcher, "s", numberOfOptions);
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

    //Main Functions:
    public String register(Matcher matcher, ArrayList<String> allOptions, boolean hasSlogan) {
        String resultMessage;
        if (!checkAllOptionsExist(matcher, allOptions))
            resultMessage = "Please enter valid options!";
        else {
            getRegisterOptions(matcher, hasSlogan);
            if (registeringUser.getUsername().matches("\\s+") || registeringUser.getNickname().matches("\\s+") ||
                    registeringUser.getEmail().matches("\\s+") || registeringUser.getPassword().matches("\\s+") ||
                    registeringUser.getPasswordConfirmation().matches("\\s+") || (hasSlogan && registeringUser.getSlogan().matches("\\s+")))
                resultMessage = "Empty Field Exists; Please enter all options completely!";
            else if (!isUsernameValid(registeringUser.getUsername()))
                resultMessage = "This username is not valid!";
            else if (!isUserNameUnique(registeringUser.getUsername()))
                resultMessage = "This username already exists!";
            else if (!isPasswordWeak(registeringUser.getPassword()).equals("success"))
                resultMessage = ("This password is not valid; " + isPasswordWeak(registeringUser.getPassword()));
            else if (!registeringUser.getPassword().equals(registeringUser.getPasswordConfirmation()))
                resultMessage = "The password confirmation doesn't match the original one!";
            else if (!isEmailUnique(registeringUser.getEmail()))
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

    public String pickQuestion(Matcher matcher, ArrayList<String> allOptions) {
        String resultMessage;
        ArrayList<String> questions = showSecurityQuestions();
        String questionNumber = getOptionsFromMatcher(matcher, "q", 3);
        String answer = getOptionsFromMatcher(matcher, "a", 3);
        String answerConfirm = getOptionsFromMatcher(matcher, "c", 3);
        if (!checkAllOptionsExist(matcher, allOptions))
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

    //Other Functions:

    public boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public String passwordToSHA(String password) throws NoSuchAlgorithmException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-256");
        crypt.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = crypt.digest();
        BigInteger bi = new BigInteger(1, bytes);
        String encryptedPassword = String.format("%0" + (bytes.length << 1) + "x", bi);
        return encryptedPassword;
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
        for(int i = 0; i<nameLength + 6; i++)
            result += "-";
        result += ("\n|  " + menuName + "  |\n");
        for(int i = 0; i<nameLength + 6; i++)
            result += "-";
        return result;
    }
}

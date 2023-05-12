package Controller;

import Model.Game;
import Model.Kingdom;
import Model.User;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class FileController {
    private final RegisterLoginController registerLoginController = new RegisterLoginController();

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

    public void initializeUsersFile() {
        File Users = new File("Users.txt");
        ArrayList<String> content = readFileContent("Users.txt");
        if(content.size() < 9) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--USERNAME--");
            initial.add("--PASSWORD--");
            initial.add("--NICKNAME--");
            initial.add("--EMAIL--");
            initial.add("--SLOGAN--");
            initial.add("--HIGHSCORE--");
            initial.add("--SECURITY QUESTION--");
            initial.add("--SECURITY ANSWER--");
            initial.add("--STAY LOGGED IN? (boolean)--");
            initial.add("_____________________________________________________");
            writeToFileContent("Users.txt",initial,false);
        }
    }

    public User getUserByUsername(String username) {
        User wantedUser;
        ArrayList<String> content =  readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get(10 * i).equals(username)) {
                wantedUser = new User(content.get(10 * i), content.get((10 * i) + 1), content.get((10 * i) + 1), content.get((10 * i) + 2)
                        , content.get((10 * i) + 3), content.get((10 * i) + 4));
                wantedUser.setSecurityQuestion(content.get((10 * i) + 6));
                wantedUser.setSecurityAnswer(content.get((10 * i) + 7));
                return wantedUser;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers(String path){
        ArrayList<User> allUsers = new ArrayList<>();
        ArrayList<String> content =  readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            allUsers.add(getUserByUsername(content.get(10*i)));
        }
        return allUsers;
    }

    public boolean isUserNameUnique(String username) {
        ArrayList<String> content =  readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get(10 * i).equals(username)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmailUnique(String email) {
        ArrayList<String> content =  readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get((10 * i) + 3).equals(email)) {
                return false;
            }
        }
        return true;
    }

    public User getFirstStayLoggedIn() {
        ArrayList<String> content =  readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get((10 * i) + 8).equals("true")) {
                return getUserByUsername(content.get(10 * i));
            }
        }
        return null;
    }

    public void addUserToFile(User user) throws NoSuchAlgorithmException {
        ArrayList<String> content = new ArrayList<>();
        content.add(user.getUsername());
        content.add(registerLoginController.passwordToSHA(user.getPassword()));
        content.add(user.getNickname());
        content.add(user.getEmail());
        content.add(user.getSlogan());
        content.add(String.valueOf(user.getHighScore()));
        content.add(user.getSecurityQuestion());
        content.add(user.getSecurityAnswer());
        content.add("false");
        content.add("_____________________________________________________");
        writeToFileContent("Users.txt", content, true);
    }

    public boolean isPasswordCorrect(String username, String password) throws NoSuchAlgorithmException {
        User user = getUserByUsername(username);
        password = registerLoginController.passwordToSHA(password);
        if (user.getPassword().equals(password))
            return true;
        return false;
    }

    public boolean isAnswerCorrect(String username, String answer) {
        User user = getUserByUsername(username);
        if(user.getSecurityAnswer().equals(answer))
            return true;
        return false;
    }

    public void addStayLoggedInForUser(String username, boolean isLoggedIn) {
        ArrayList<String> content =  readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get(10 * i).equals(username)) {
                content.remove((10 * i) + 8);
                content.add(((10 * i) + 8), String.valueOf(isLoggedIn));
            }
        }
        writeToFileContent("Users.txt", content, false);
    }

    public void changePassword(String username, String password) throws NoSuchAlgorithmException {
        ArrayList<String> content =  readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get(10 * i).equals(username)) {
                content.remove((10 * i) + 1);
                content.add(((10 * i) + 1), registerLoginController.passwordToSHA(password));
            }
        }
        writeToFileContent("Users.txt", content, false);
    }

    public void initializeGamesFile() {
        File Games = new File("Games.txt");
        ArrayList<String> content = readFileContent("Games.txt");
        if (content.size() < 4) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--GAME ID--");
            initial.add("--ALL PLAYERS(separated with ,)--");
            initial.add("--MAP TEMPLATE NUMBER--");
            initial.add("_____________________________________________________");
            writeToFileContent("Games.txt", initial, false);
        }
    }

    public void addGameToFile(Game game) {
        initializeGamesFile();
        ArrayList<String> content = new ArrayList<>();
        content.add(String.valueOf(game.getGameId()));
        String usernames = "";
        for (int i = 0; i < game.getKingdoms().size(); i++) {
            usernames += (game.getKingdoms().get(i).getKing().getUsername() + "-");
        }
        content.add(usernames);
        content.add(String.valueOf(game.getMapTemplateNumber()));
        content.add("_____________________________________________________");
        writeToFileContent("Games.txt", content, true);
    }

    public void initializeKingdomsFile() {
        File Kingdoms = new File("Kingdoms.txt");
        ArrayList<String> content = readFileContent("Kingdoms.txt");
        if (content.size() < 9) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--GAME ID--");
            initial.add("--KING'S USERNAME--");
            initial.add("--INVENTORY--");
            initial.add("--JOBLESS COUNTER--");
            initial.add("--KING'S BUILDINGS WITH LOCATION {testBuilding|x:X-y:Y,}--");
            initial.add("--KING'S PRODUCTS(SEPARATED WITH ,)--");
            initial.add("--KING'S PEOPLE WITH LOCATION {testPerson|x:X-y:Y,}--");
            initial.add("--KING'S POPULARITY FACTORS {factor1:amount, }--");
            initial.add("--KING'S ATTACK EQUIPMENTS(SEPARATED WITH ,)--");
            initial.add("_____________________________________________________");
            writeToFileContent("Kingdoms.txt", initial, false);
        }
    }

    public void addKingdomToFile(Kingdom kingdom) {
        initializeKingdomsFile();
        ArrayList<String> content = new ArrayList<>();
        content.add(String.valueOf(kingdom.getGameId()));
        content.add(kingdom.getKing().getUsername());
        content.add(String.valueOf(kingdom.getInventory()));
        content.add(String.valueOf(kingdom.getJoblessCounter()));
        content.add("null");
        content.add("null");
        content.add("null");
        content.add("null");
        content.add("null");
        content.add("_____________________________________________________");
        writeToFileContent("Kingdoms.txt", content, true);
    }
}

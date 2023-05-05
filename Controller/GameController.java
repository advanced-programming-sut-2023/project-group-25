package Controller;

import Model.*;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class GameController {
    private Game currentGame;
    private int numberOfPlayers = 2;

    private final RegisterLoginController registerLoginController = new RegisterLoginController();
    private final MainController mainController = new MainController();

    public void initializeGamesFile() {
        File Games = new File("Games.txt");
        ArrayList<String> content = mainController.readFileContent("Games.txt");
        if(content.size() < 4) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--GAME ID--");
            initial.add("--ALL PLAYERS(separated with ,)--");
            initial.add("--MAP TEMPLATE NUMBER--");
            initial.add("_____________________________________________________");
            mainController.writeToFileContent("Games.txt",initial,false);
        }
    }

    public void addGameToFile(Game game) {
        initializeGamesFile();
        ArrayList<String> content = new ArrayList<>();
        content.add(String.valueOf(game.getGameId()));
        String usernames = "";
        for(int i = 0; i<game.getKingdoms().size(); i++) {
            usernames += (game.getKingdoms().get(i).getKing().getUsername() + "-");
        }
        content.add(usernames);
        content.add(String.valueOf(game.getMapTemplateNumber()));
        content.add("_____________________________________________________");
        mainController.writeToFileContent("Games.txt", content, true);
    }

    public void initializeKingdomsFile() {
        File Kingdoms = new File("Kingdoms.txt");
        ArrayList<String> content = mainController.readFileContent("Kingdoms.txt");
        if(content.size() < 9) {
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
            mainController.writeToFileContent("Kingdoms.txt",initial,false);
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
        mainController.writeToFileContent("Kingdoms.txt", content, true);
    }

    /*public User getGameByID(String id) {
        Game wantedGame;
        ArrayList<String> content =  mainController.readFileContent("Games.txt");
        for (int i = 0; i < (content.size() / 4); i++) {
            if (content.get(4 * i).equals(id)) {
                String[] allPlayersUsername = content.get((10 * i) + 1).split(",");

                ArrayList<Kingdom>
                wantedGame = new Game(content.get(10 * i), content.get((10 * i) + 1), content.get((10 * i) + 1), content.get((10 * i) + 2)
                        , content.get((10 * i) + 3), content.get((10 * i) + 4));
                wantedUser.setSecurityQuestion(content.get((10 * i) + 6));
                wantedUser.setSecurityAnswer(content.get((10 * i) + 7));
                return wantedUser;
            }
        }
        return null;
    }*/

    public String newGame(String line) {
        String resultMessage = "";
        String[] usernames = line.split("-");
        for(int i = 0; i<usernames.length; i++) {
            if(registerLoginController.getUserByUsername(usernames[i]) == null)
                resultMessage = ("New game creation failed! Username [" + usernames[i] + "] does not exist!");
        }
        if(resultMessage.equals("")) {
            File Games = new File("Games.txt");
            ArrayList<String> content = mainController.readFileContent("Games.txt");
            int gameId = content.size()/4 + 1;
            ArrayList<Kingdom> kingdoms = new ArrayList<>();
            for(int i = 0; i<usernames.length; i++) {
                Kingdom newKingdom = new Kingdom(registerLoginController.getUserByUsername(usernames[i]), gameId);
                addKingdomToFile(newKingdom);
                kingdoms.add(newKingdom);
            }
            Game game = new Game(gameId,kingdoms);
            addGameToFile(game);
            resultMessage = "New game created successfully! Game's ID: " + gameId;
        }
        return resultMessage;
    }
    
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    
    public Game getCurrentGame() {
        return currentGame;
    }
    
    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}

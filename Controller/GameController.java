package Controller;

import Model.Game;

import java.util.ArrayList;

public class GameController {
    private Game currentGame;
    private int numberOfPlayers = 2;

    private final RegisterLoginController registerLoginController = new RegisterLoginController();

    public String newGame(String line) {
        String resultMessage = "";
        String[] usernames = line.split(",");
        for(int i = 0; i<usernames.length; i++) {
            if(registerLoginController.getUserByUsername(usernames[i]) == null)
                resultMessage = ("New game creation failed! Username [" + usernames[i] + "] does not exist!");
        }
        if(resultMessage.equals("")) {
            resultMessage = "New game created successfully! Game's ID: ";
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

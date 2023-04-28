package Controller;

import Model.Game;

public class GameController {
    private Game currentGame;
    private int numberOfPlayers = 2;
    
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

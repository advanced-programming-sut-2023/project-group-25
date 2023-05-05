package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Kingdom> kingdoms = new ArrayList<Kingdom>();
    private int numberOfPlayers = 4;

    private int gameId;
    
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    private Map map;
    private int mapTemplateNumber = 1;

    public void addKingdom(Kingdom kingdom){
        kingdoms.add(kingdom);
    }

    public Game(int gameId, ArrayList<Kingdom> allKingdoms) {
        this.gameId = gameId;
        this.kingdoms = allKingdoms;
        this.numberOfPlayers = allKingdoms.size();
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ArrayList<Kingdom> getKingdoms() {
        return kingdoms;
    }

    public void setMapTemplateNumber(int mapTemplateNumber) {
        this.mapTemplateNumber = mapTemplateNumber;
    }

    public int getGameId() {
        return gameId;
    }

    public int getMapTemplateNumber() {
        return mapTemplateNumber;
    }
}

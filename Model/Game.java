package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Kingdom> kingdoms = new ArrayList<Kingdom>();
    private int numberOfPlayers = 4;
    private int gameId;
    private int mapTemplateNumber;
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    private Map map;
    public Kingdom getKingdomByKing(User king) {
        for (Kingdom kingdom : kingdoms) {
            if (kingdom.getKing().getUsername().equals(king.getUsername()))
                return kingdom;
        }
        return null;
    }

    public void addKingdom(Kingdom kingdom){
        kingdoms.add(kingdom);
    }

    public Map getMap() {
        return map;
    }
    public Game(int gameId, ArrayList<Kingdom> allKingdoms) {
        this.gameId = gameId;
        this.kingdoms = allKingdoms;
        this.numberOfPlayers = allKingdoms.size();
    }

    public void setMap(Map map) {
        this.map = map;
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

    public ArrayList<Kingdom> getKingdoms() {
        return kingdoms;
    }
}

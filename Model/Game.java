package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Kingdom> kingdoms = new ArrayList<Kingdom>();
    private int numberOfPlayers = 4;
    private int gameId;
    public Turn turn;
    private int mapTemplateNumber;

    private boolean firstLoaded = false;
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    private Map map;
    public Kingdom getKingdomByKing(String username) {
        for (Kingdom kingdom : kingdoms) {
            if (kingdom.getKing().getUsername().equals(username))
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
    public void setColorOfKingdom(int index, String color) {
        kingdoms.get(index).setColor(color);
    }

    public void setFirstLoaded(boolean firstLoaded) {
        this.firstLoaded = firstLoaded;
    }

    public boolean isFirstLoaded() {
        return firstLoaded;
    }
}

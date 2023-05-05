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

    public Kingdom getKingdomByKing(User king){
        for (Kingdom kingdom: kingdoms) {
            if(kingdom.getKing().equals(king))
                return kingdom;
        }
        return null;
    }

    public void addKingdom(Kingdom kingdom){
        kingdoms.add(kingdom);
    }

<<<<<<< HEAD
    public Map getMap() {
        return map;
=======
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
>>>>>>> 5b69c4b439edbac0f43c0d3fec2493dfb2bc61b8
    }

    public int getMapTemplateNumber() {
        return mapTemplateNumber;
    }
}

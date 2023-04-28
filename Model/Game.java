package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Kingdom> kingdoms = new ArrayList<Kingdom>();
    private int numberOfPlayers = 4;
    
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    private Map map;
    private int mapTemplateNumber;

    public Game(Map map) {
        this.map = map;
    }

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

    public Map getMap() {
        return map;
    }

    public int getMapTemplateNumber() {
        return mapTemplateNumber;
    }
}

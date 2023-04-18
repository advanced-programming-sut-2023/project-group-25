package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Kingdom> kingdoms = new ArrayList<Kingdom>();
    private Map map;
    private int mapTemplateNumber;

    public Game(Map map) {
        this.map = map;
    }

    public void addKingdom(Kingdom kingdom){
        kingdoms.add(kingdom);
    }
}

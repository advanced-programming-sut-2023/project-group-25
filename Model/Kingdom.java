package Model;

import java.util.ArrayList;

public class Kingdom {
    private ArrayList<Building> kingBuildings = new ArrayList<>();
    private ArrayList<Product> kingProduct = new ArrayList<>();
    private ArrayList<Person> kingPeople = new ArrayList<>();
    private ArrayList<PopularityFactor> kingPopularityFactors = new ArrayList<>();
    private ArrayList<AttackEquipment> kingAttackEquipments = new ArrayList<>();
    private  User king;
    private int inventory;
    private int joblessCounter;
    private int gameId;
    
    public Kingdom(User king, int gameId) {
        this.king = king;
        this.inventory = 0;
        this.joblessCounter = 8;
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public ArrayList<Building> getKingBuildings() {
        return kingBuildings;
    }
    
    public ArrayList<Product> getKingProduct() {
        return kingProduct;
    }
    
    public ArrayList<Person> getKingPeople() {
        return kingPeople;
    }
    
    public ArrayList<PopularityFactor> getKingPopularityFactors() {
        return kingPopularityFactors;
    }
    
    public ArrayList<AttackEquipment> getKingAttackEquipments() {
        return kingAttackEquipments;
    }
    
    public User getKing() {
        return king;
    }
    
    public int getInventory() {
        return inventory;
    }
    
    public int getJoblessCounter() {
        return joblessCounter;
    }
    
    
}

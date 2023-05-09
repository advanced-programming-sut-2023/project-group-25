package Model;

import java.util.ArrayList;

public class Kingdom {
    private ArrayList<Building> kingBuildings = new ArrayList<>();
    private ArrayList<Product> kingProducts = new ArrayList<>();
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
    
    public ArrayList<Product> getKingProducts() {
        return kingProducts;
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
    
    public void removePerson(Person person) {
        this.kingPeople.remove(person);
    }
    
    public void addPerson(Person person) {
        this.kingPeople.add(person);
    }
    
    public int getKingFreePeopleNumber() {
        int count = 0;
        for (Person person : kingPeople) {
            if (!(person instanceof MilitaryPerson)) count++;
        }
        return count;
    }
    
    public void addAttackEquipment(AttackEquipment attackEquipment) {
        kingAttackEquipments.add(attackEquipment);
    }
}

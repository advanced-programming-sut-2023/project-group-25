package Model;

import java.util.ArrayList;

public class MilitaryPerson extends Person {
    private static ArrayList<MilitaryPerson> allMilitaryPerson = new ArrayList<MilitaryPerson>();
    private ArrayList<Product> neededProducts = new ArrayList<Product>();
    private int firePower;
    private int defendPower;
    private int speed;
    private int trainingCost;
    private int shootingRange;
    private String mode;
    
    public MilitaryPerson(User king, String type, MilitaryPerson militaryPerson) {
        super(type);
        this.firePower = militaryPerson.firePower;
        this.defendPower = militaryPerson.defendPower;
        this.speed = militaryPerson.speed;
        this.trainingCost = militaryPerson.trainingCost;
        this.mode = militaryPerson.mode;
    }
    
    public MilitaryPerson(String type, ArrayList<Product> neededProducts,
                          int firePower, int defendPower, int speed) {
        super(type);
        this.neededProducts = neededProducts;
        this.firePower = firePower;
        this.defendPower = defendPower;
        this.speed = speed;
        this.trainingCost = trainingCost;
    }
    
    public static ArrayList<MilitaryPerson> getAllMilitaryPerson() {
        return allMilitaryPerson;
    }
    
    public int getShootingRange() {
        return shootingRange;
    }
    
    public void setShootingRange(int shootingRange) {
        this.shootingRange = shootingRange;
    }
    
    private MilitaryPerson getMilitaryPersonByType(String type) {
        for (MilitaryPerson person : allMilitaryPerson)
            if (person.getType().equals(type))
                return person;
        return null;
    }
    
    public ArrayList<Product> getNeededProducts() {
        return neededProducts;
    }
    
    public int getFirePower() {
        return firePower;
    }
    
    public int getDefendPower() {
        return defendPower;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public int getTrainingCost() {
        return trainingCost;
    }
    
    public String getMode() {
        return mode;
    }
    
    public void setMode(String mode) {
        this.mode = mode;
    }
    
}

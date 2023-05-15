package Model;

import java.util.ArrayList;

public class MilitaryPerson extends Person {
    private static ArrayList<MilitaryPerson> allMilitaryPerson = new ArrayList<MilitaryPerson>();
    private ArrayList<Product> neededProducts = new ArrayList<Product>();
    private int firePower;
    private int defendPower;
    private int speed;
    private boolean hasOil = false;
    private int trainingCost;
    private int shootingRange;
    private String mode;
    private String nationality;
    
    public MilitaryPerson(User king, String type, MilitaryPerson militaryPerson) {
        super(king, type);
        this.firePower = militaryPerson.firePower;
        this.defendPower = militaryPerson.defendPower;
        this.speed = militaryPerson.speed;
        initializeTrainingCost();
        this.mode = militaryPerson.mode;
        this.movingRange = speed * 2;
    }
    
    public MilitaryPerson(String type, ArrayList<Product> neededProducts,
                          int firePower, int defendPower, int speed, String nationality) {
        super(type);
        this.neededProducts = neededProducts;
        this.firePower = firePower;
        this.defendPower = defendPower;
        this.speed = speed;
        this.nationality = nationality;
        initializeTrainingCost();
        this.movingRange = speed * 2;
    }
    
    public static ArrayList<MilitaryPerson> getAllMilitaryPerson() {
        return allMilitaryPerson;
    }
    
    public static void setAllMilitaryPerson(ArrayList<MilitaryPerson> allMilitaryPerson) {
        MilitaryPerson.allMilitaryPerson = allMilitaryPerson;
    }
    
    public boolean getHasOil() {
        return hasOil;
    }
    
    public void setHasOil(boolean hasOil) {
        this.hasOil = hasOil;
    }

    public int getMovingRange() {
        return movingRange;
    }

    public void setMovingRange(int movingRange) {
        this.movingRange = movingRange;
    }

    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    private void initializeTrainingCost() {
        this.trainingCost = 500;
        for (Product neededProduct : neededProducts) {
            trainingCost += neededProduct.getCost();
        }
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
    
    public void setNeededProducts(ArrayList<Product> neededProducts) {
        this.neededProducts = neededProducts;
    }
    
    public int getFirePower() {
        return firePower;
    }
    
    public void setFirePower(int firePower) {
        this.firePower = firePower;
    }
    
    public int getDefendPower() {
        return defendPower;
    }
    
    public void setDefendPower(int defendPower) {
        this.defendPower = defendPower;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getTrainingCost() {
        return trainingCost;
    }
    
    public void setTrainingCost(int trainingCost) {
        this.trainingCost = trainingCost;
    }
    
    public String getMode() {
        return mode;
    }
    
    public void setMode(String mode) {
        this.mode = mode;
    }
    
}

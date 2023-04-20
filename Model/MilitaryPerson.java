package Model;

import java.util.ArrayList;

public class MilitaryPerson extends Person {
    private static ArrayList<MilitaryPerson> allMilitaryPerson = new ArrayList<MilitaryPerson>();
    private ArrayList<Product> neededProducts = new ArrayList<Product>();
    private int firePower;
    private int defendPower;
    private int speed;
    private int trainingCost;
    private String mode;


    public MilitaryPerson(User king, String type, Cell location, MilitaryPerson militaryPerson) {
        super(king, type, location);
        this.firePower = militaryPerson.firePower;
        this.defendPower = militaryPerson.defendPower;
        this.speed = militaryPerson.speed;
        this.trainingCost = militaryPerson.trainingCost;
        this.mode = militaryPerson.mode;
    }

    public MilitaryPerson(User king, String type, Cell location, ArrayList<Product> neededProducts,
                          int firePower, int defendPower, int speed, int trainingCost, String mode) {
        super(king, type, location);
        this.neededProducts = neededProducts;
        this.firePower = firePower;
        this.defendPower = defendPower;
        this.speed = speed;
        this.trainingCost = trainingCost;
        this.mode = mode;
    }

    private MilitaryPerson getMilitaryPersonByType(String type) {
        for (MilitaryPerson person : allMilitaryPerson)
            if (person.getType().equals(type))
                return person;
        return null;
    }

    public static ArrayList<MilitaryPerson> getAllMilitaryPerson() {
        return allMilitaryPerson;
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
}

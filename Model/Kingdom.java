package Model;

import java.util.ArrayList;

public class Kingdom {
    private final int gameId;
    private ArrayList<Building> kingBuildings = new ArrayList<>();
    private ArrayList<Product> kingProducts = new ArrayList<>();
    private ArrayList<Person> kingPeople = new ArrayList<>();
    private ArrayList<Person> kingUnusedUnits = new ArrayList<>();
    private ArrayList<PopularityFactor> kingPopularityFactors = new ArrayList<>();
    private ArrayList<AttackEquipment> kingAttackEquipments = new ArrayList<>();
    private User king;
    private double inventory;
    private String color;
    private Cell mainCastleLocation;
    
    public void setKingPeople(ArrayList<Person> kingPeople) {
        this.kingPeople = kingPeople;
    }
    
    public Cell getMainCastleLocation() {
        return mainCastleLocation;
    }
    
    public void setMainCastleLocation(Cell mainCastleLocation) {
        this.mainCastleLocation = mainCastleLocation;
    }
    
    public Kingdom(User king, int gameId) {
        this.king = king;
        this.inventory = 4000;
        this.gameId = gameId;
        this.kingPopularityFactors.add(new PopularityFactor("food"));
        this.kingPopularityFactors.add(new PopularityFactor("tax"));
        this.kingPopularityFactors.add(new PopularityFactor("religion"));
        this.kingPopularityFactors.add(new PopularityFactor("fear"));
    }

    public ArrayList<Person> getKingUnusedUnits() {
        return kingUnusedUnits;
    }

    public void setKingUnusedUnits(ArrayList<Person> kingUnusedUnits) {
        this.kingUnusedUnits = kingUnusedUnits;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getGameId() {
        return gameId;
    }

    public ArrayList<Building> getKingBuildings() {
        return kingBuildings;
    }

    public void setKingBuildings(ArrayList<Building> kingBuildings) {
        this.kingBuildings = kingBuildings;
    }

    public ArrayList<Product> getKingProducts() {
        return kingProducts;
    }

    public void setKingProducts(ArrayList<Product> kingProducts) {
        this.kingProducts = kingProducts;
    }

    public ArrayList<Person> getKingPeople() {
        return kingPeople;
    }

    public void setKingPeople(Person kingPerson) {
        kingPeople.add(kingPerson);
    }

    public ArrayList<PopularityFactor> getKingPopularityFactors() {
        return kingPopularityFactors;
    }

    public void setKingPopularityFactors(ArrayList<PopularityFactor> kingPopularityFactors) {
        this.kingPopularityFactors = kingPopularityFactors;
    }

    public ArrayList<AttackEquipment> getKingAttackEquipments() {
        return kingAttackEquipments;
    }

    public void setKingAttackEquipments(ArrayList<AttackEquipment> kingAttackEquipments) {
        this.kingAttackEquipments = kingAttackEquipments;
    }

    public User getKing() {
        return king;
    }

    public void setKing(User king) {
        this.king = king;
    }

    public double getInventory() {
        return inventory;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    public void removePerson(Person person) {
        this.kingPeople.remove(person);
    }

    public void addPerson(Person person) {
        this.kingPeople.add(person);
    }

//    public int getKingFreePeopleNumber() {
//        int count = 0;
//        for (Person person : kingPeople) {
//            if (!(person instanceof MilitaryPerson)) count++;
//        }
//        return count;
//    }


    public void addAttackEquipment(AttackEquipment attackEquipment) {
        kingAttackEquipments.add(attackEquipment);
    }

    public void addUnusedUnit(MilitaryPerson unit) {
        kingUnusedUnits.add(unit);
    }

    public void reduceJoblessCounter() {
        ArrayList<Person> peopleCopy = new ArrayList<Person>(getKingPeople());
        for (Person person : peopleCopy) {
            if (person.getType().equals("jobless")) {
                kingPeople.remove(person);
                break;
            }
        }
    }

    public int getJoblessCounter() {
        int countJobless = 0;
        for (Person person : getKingPeople()) {
            if (person.getType().equals("jobless")) {
                countJobless++;
            }
        }
        return countJobless;
    }

    public void setJoblessCounter(int count) {
        for (int i = 0; i < count; i++) {
            WorkerPerson workerPerson = new WorkerPerson(getKing(), "jobless", null);
            kingPeople.add(workerPerson);
        }
    }
}

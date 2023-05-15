package Model;

import java.util.ArrayList;

public class Building {
    private static final ArrayList<Building> allBuildings = new ArrayList<>();
    private String type;
    private String category;
    private ArrayList<Product> neededProducts;
    private int workerCounter;
    private ArrayList<WorkerPerson> workerPeople = new ArrayList<>();
    private boolean isMainCastlePart;
    private int hitPoint;
    private Cell location;
    private User king;
    
    public Building(String type, String category, ArrayList<Product> buildingCosts, int workerCounter, int hitPoint) {
        this.type = type;
        this.category = category;
        this.neededProducts = buildingCosts;
        this.workerCounter = workerCounter;
        this.hitPoint = hitPoint;
    }
    
    public Building(Building building) {
        this.type = building.type;
        this.category = building.category;
        this.neededProducts = building.neededProducts;
        this.workerCounter = building.workerCounter;
        this.hitPoint = building.hitPoint;
    }
    
    public ArrayList<Product> getNeededProducts() {
        return neededProducts;
    }
    
    public void setNeededProducts(ArrayList<Product> neededProducts) {
        this.neededProducts = neededProducts;
    }
    
    public ArrayList<WorkerPerson> getWorkerPeople() {
        return workerPeople;
    }
    
    public void setWorkerPeople(ArrayList<WorkerPerson> workerPeople) {
        this.workerPeople = workerPeople;
    }
    
    public Building getBuildingByType(String type) {
        for (int i = 0; i < allBuildings.size(); i++) {
            if (allBuildings.get(i).getType().equals(type))
                return allBuildings.get(i);
        }
        return null;
    }
    
    public int calculateBuildingCost() {
        int price = 0;
        for (int i = 0; i < this.neededProducts.size(); i++) {
            price += this.neededProducts.get(i).getPrice();
        }
        return price;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public ArrayList<Product> getBuildingNeededProducts() {
        return neededProducts;
    }
    
    public int getWorkerCounter() {
        return workerCounter;
    }
    
    public void setWorkerCounter(int workerCounter) {
        this.workerCounter = workerCounter;
    }
    
    public int getHitPoint() {
        return hitPoint;
    }
    
    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }
    
    public Cell getLocation() {
        return location;
    }
    
    public void setLocation(Cell location) {
        this.location = location;
    }
    
    public User getKing() {
        return king;
    }
    
    public void setKing(User king) {
        this.king = king;
    }
    
    public boolean isMainCastlePart(String type) {
        switch (type) {
            case "siege tent":
            case "caged war dogs":
            case "oil smelter":
            case "enginner guild":
            case "mercenary post":
            case "barracks":
            case "armoury":
            case "square tower":
            case "defence turret":
            case "perimeter tower":
            case "lookout tower":
            case "round tower":
            case "drawbridge":
            case "large stone gatehouse":
            case "small stone gatehouse":
            case "stable":
                return true;
        }
        return false;
    }
}

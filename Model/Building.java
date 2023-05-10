package Model;

import java.util.ArrayList;

public class Building {
    private static final ArrayList<Building> allBuildings = new ArrayList<>();
    private String type;
    private String category;
    private ArrayList<Product> buildingCosts;
    private int workerCounter;
    private int hitPoint;
    private Cell location;
    private User king;
    public Building(String type, String category, ArrayList<Product> buildingCosts,int workerCounter, int hitPoint) {
        this.type = type;
        this.category = category;
        this.buildingCosts = buildingCosts;
        this.workerCounter = workerCounter;
        this.hitPoint = hitPoint;
    }
    public Building(Building building) {
        this.type = building.type;
        this.category = building.category;
        this.buildingCosts = building.buildingCosts;
        this.workerCounter = building.workerCounter;
        this.hitPoint = building.hitPoint;
        this.location = location;
        this.king = king;
    }
    public Building getBuildingByType(String type) {
        for(int i = 0; i<allBuildings.size(); i++) {
            if(allBuildings.get(i).getType().equals(type))
                return allBuildings.get(i);
        }
        return null;
    }
    public int calculateBuildingCost() {
        int price = 0;
        for(int i = 0; i<this.buildingCosts.size(); i++) {
            price += this.buildingCosts.get(i).getPrice();
        }
        return price;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<Product> getBuildingCosts() {
        return buildingCosts;
    }

    public int getWorkerCounter() {
        return workerCounter;
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

    public User getKing() {
        return king;
    }

    public void setKing(User king) {
        this.king = king;
    }

    public void setLocation(Cell location) {
        this.location = location;
    }
}

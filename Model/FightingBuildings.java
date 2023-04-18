package Model;

import java.util.ArrayList;

public class FightingBuildings extends Building{
    private ArrayList<MilitaryPerson> military;
    private int fireRange;
    private int defendRange;
    private int maxHitPoint;
    
    public FightingBuildings(Building building, Cell location, User king, int fireRange, int defendRange) {
        super(building, location, king);
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.maxHitPoint = building.getHitPoint();
    }
    public int calculateRepairCost(){
        return (1 - (this.getHitPoint() / this.getMaxHitPoint() * this.calculateBuildingCost()));
    }

    public ArrayList<MilitaryPerson> getMilitary() {
        return military;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getMaxHitPoint() {
        return maxHitPoint;
    }
}

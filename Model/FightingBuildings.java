package Model;

import java.util.ArrayList;

public class FightingBuildings extends Building{
    private ArrayList<MilitaryPerson> military;
    private int fireRange;
    private int defendRange;
    private int maxHitPoint;

    public FightingBuildings(Building building, int fireRange, int defendRange) {
        super(building);
        this.fireRange = fireRange;
        this.defendRange = defendRange;
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

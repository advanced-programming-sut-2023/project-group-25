package Model;

import java.util.ArrayList;

public class TrainingBuildings extends Building {
    private ArrayList<MilitaryPerson> outputMilitaryPeople;

    private int cost;

    public TrainingBuildings(Building building, ArrayList<MilitaryPerson> outputMilitaryPeople, int cost) {
        super(building);
        this.outputMilitaryPeople = outputMilitaryPeople;
        this.cost = cost;
    }

    public ArrayList<MilitaryPerson> getOutputMilitaryPeople() {
        return outputMilitaryPeople;
    }

}

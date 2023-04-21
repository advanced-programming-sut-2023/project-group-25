package Model;

import java.util.ArrayList;

public class TrainingBuildings extends Building {
    private ArrayList<MilitaryPerson> outputMilitaryPeople;

    public TrainingBuildings(Building building, Cell location, User king, ArrayList<MilitaryPerson> outputMilitaryPeople) {
        super(building, location, king);
        this.outputMilitaryPeople = outputMilitaryPeople;
    }

    public ArrayList<MilitaryPerson> getOutputMilitaryPeople() {
        return outputMilitaryPeople;
    }

}

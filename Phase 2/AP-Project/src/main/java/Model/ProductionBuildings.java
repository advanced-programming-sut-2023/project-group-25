package Model;

import java.util.ArrayList;

public class ProductionBuildings extends Building{
    private int rate;
    public ProductionBuildings(Building building, int rate) {
        super(building);
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

}

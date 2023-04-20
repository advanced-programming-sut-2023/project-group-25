package Model;

import java.util.ArrayList;

public class ProductionBuildings extends Building{
    private int rate;
    public ProductionBuildings(Building building, Cell location, User king, int rate) {
        super(building, location, king);
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}

package Model;

public class ProductionBuilding extends Building{
    private int rate;
    public ProductionBuilding(Building building, Cell location, User king, int rate) {
        super(building, location, king);
        this.rate = rate;
    }
}

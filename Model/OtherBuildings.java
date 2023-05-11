package Model;

public class OtherBuildings extends Building {
    private boolean isWorking;
    public OtherBuildings(Building building, Cell location, User king, boolean isWorking) {
        super(building);
        this.isWorking = isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        isWorking = isWorking;
    }

    public boolean isWorking() {
        return isWorking;
    }

}
